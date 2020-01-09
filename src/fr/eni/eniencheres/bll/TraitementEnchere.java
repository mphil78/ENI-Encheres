package fr.eni.eniencheres.bll;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.eniencheres.bo.Enchere;
import fr.eni.eniencheres.bo.ArticleVendu;
import fr.eni.eniencheres.bo.Utilisateur;


/**
 * Servlet implementation class TraitementEnchere
 */
@WebServlet("/TraitementEnchere")
public class TraitementEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TraitementEnchere() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recuperation de la session
		HttpSession session = request.getSession();

		// on récupère les infos utilisateur et article et
		// on redirige vers /detailVente
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur utilisateur = utilisateurManager.getByPseudo((String) session.getAttribute("pseudo"));
		ArticleManager articleManager = new ArticleManager();
		ArticleVendu articleAAfficher = articleManager.getById(Integer.parseInt(request.getParameter("idArticle")));
		boolean retirer = request.getParameter("retirer")!=null?true:false;
		
		// vérification si l'article est remporté
		Utilisateur gagnant = utilisateurManager.isRemporte(articleAAfficher);
		
		//si le bouton confirmer la récéption est actionné
		if (gagnant!=null && retirer) {
			//maj article
			articleAAfficher.setEtatVente(ArticleVendu.RETIREE);
			articleManager.majEtatVente(articleAAfficher);
			//maj vendeur
			articleAAfficher.getVendeur().setCredit(articleAAfficher.getVendeur().getCredit()+articleAAfficher.getPrixVente());
			utilisateurManager.updateUtilisateur(articleAAfficher.getVendeur());
		}
	
		// redirection vers detailVente
		request.setAttribute("utilisateur", utilisateur);
		request.setAttribute("articleAAfficher", articleAAfficher);
		request.setAttribute("gagnant", gagnant);
		
		// redirection
		RequestDispatcher rd = request.getRequestDispatcher("./DetailVente");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recuperation de la session
		HttpSession session = request.getSession();

		// Si la session est déconnectée on redirige vers l'accueil
		if (session.getAttribute("pseudo") == null) {
			response.sendRedirect("./TraitementAccueil");
		}

		// si la session est connectée on récupère les infos utilisateur et article et
		// on redirige vers /detailVente
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur utilisateur = utilisateurManager.getByPseudo((String) session.getAttribute("pseudo"));
		ArticleManager articleManager = new ArticleManager();
		ArticleVendu article = articleManager.getById(Integer.parseInt(request.getParameter("idArticle")));
		int maProposition = Integer.parseInt(request.getParameter("maProposition"));
		EncheresManager enchereManager = new EncheresManager();

		// Test si l'enchere est terminée
		if (article.getDateFinEncheres().isBefore(LocalDate.now())) {
			response.sendRedirect("./TraitementAccueil");
		} else {
			if (maProposition > utilisateur.getCredit()) {
				request.setAttribute("erreurProposition", "Désolé vous n'avez pas assez de crédits.");
				request.setAttribute("utilisateur", utilisateur);
				request.setAttribute("articleAAfficher", article);
				RequestDispatcher rd = request.getRequestDispatcher("./DetailVente");
				rd.forward(request, response);
			}
			else
			{
			// Test si la proposition est valable
			if (maProposition <= article.getPrixVente()) {
				// si non valable on revient en arrière avec une erreur
				request.setAttribute("erreurProposition", "Veuillez faire une proposition plus élevée.");
				request.setAttribute("utilisateur", utilisateur);
				request.setAttribute("articleAAfficher", article);
				RequestDispatcher rd = request.getRequestDispatcher("./DetailVente");
				rd.forward(request, response);
			} else {
				// Tout est bon ! Création de l'objet enchere
				Enchere enchere = new Enchere(LocalDate.now(), maProposition, utilisateur, article);
				
				//maj de l'enchérisseur
				utilisateur.setCredit(utilisateur.getCredit()-maProposition);
				utilisateurManager.updateUtilisateur(utilisateur);
				
				//maj de l'enchérisseur précédent s'il existe
				Utilisateur encherisseurPrecedent = utilisateurManager.getEncherisseur(article);
				if(encherisseurPrecedent!=null) {
					encherisseurPrecedent.setCredit(encherisseurPrecedent.getCredit()+article.getPrixVente());
					utilisateurManager.updateUtilisateur(encherisseurPrecedent);
				}
				
				//TODO maj du vendeur à faire après que la vente soit retirée
				
				//maj de l'article
				article.setAcheteur(utilisateur);
				article.setPrixVente(maProposition);
				articleManager.majEnchere(article);
				
				//enregistrement de l'enchere dans la bdd
				enchereManager.addEnchere(enchere);
				
				response.sendRedirect("./TraitementAccueil");
			}
			}
		}
	}

}
