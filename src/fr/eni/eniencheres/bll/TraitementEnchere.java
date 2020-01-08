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
import fr.eni.eniencheres.dal.DAOFactory;
import fr.eni.eniencheres.dal.UtilisateurDAO;
import fr.eni.eniencheres.dal.jdbc.UtilisateurDAOJdbcImpl;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recuperation de la session
		HttpSession session = request.getSession();
		
//		//Si la session est déconnectée on redirige vers l'accueil
//		if(session.getAttribute("pseudo")!=null) {
//			response.sendRedirect("/TraitementAccueil");
//		}
		
		//si la session est connectée on récupère les infos utilisateur et article et on redirige vers /detailVente
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur utilisateur = utilisateurManager.getByPseudo((String)session.getAttribute("pseudo"));
		ArticleManager articleManager = new ArticleManager();
		ArticleVendu articleAAfficher = articleManager.getById(Integer.parseInt(request.getParameter("idArticle")));
		
		//redirection vers detailVente
		request.setAttribute("utilisateur",utilisateur);
		request.setAttribute("articleAAfficher", articleAAfficher);
		RequestDispatcher rd = request.getRequestDispatcher("./DetailVente");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//recuperation de la session
		HttpSession session = request.getSession();
		
		//Si la session est déconnectée on redirige vers l'accueil
		if(session.getAttribute("pseudo")!=null) {
			response.sendRedirect("/TraitementAccueil");
		}
		
		//si la session est connectée on récupère les infos utilisateur et article et on redirige vers /detailVente
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur utilisateur = utilisateurManager.getByPseudo((String)session.getAttribute("pseudo"));
		ArticleManager articleManager = new ArticleManager();
		ArticleVendu article = articleManager.getById(Integer.parseInt(request.getParameter("idArticle")));
		int maProposition = Integer.parseInt(request.getParameter("maProposition"));
		EncheresManager enchereManager = new EncheresManager();
		
		//Test si l'enchere est terminée
		if (article.getDateFinEncheres().isBefore(LocalDate.now()))
		{
			response.sendRedirect("/TraitementAccueil");
		}
		else
		{
			//Test si la proposition est valable
			if (maProposition<=article.getPrixVente())
			{
				//si non valable on revient en arrière avec une erreur
				request.setAttribute("erreurProposition","Veuillez faire une proposition plus élevée.");
				request.setAttribute("utilisateur", utilisateur);
				request.setAttribute("ArticleAAfficher", article);
				RequestDispatcher rd = request.getRequestDispatcher("./DetailVente");
				rd.forward(request, response);
			}
			else
			{
				//Création de l'objet enchere
				Enchere enchere = new Enchere(LocalDate.now(),maProposition,utilisateur,article);
				enchereManager.addEnchere(enchere);
				response.sendRedirect("/TraitementAccueil");
			}
		}
	}

}
