package fr.eni.eniencheres.bll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.eniencheres.bo.ArticleVendu;
import fr.eni.eniencheres.bo.Enchere;
import fr.eni.eniencheres.bo.Utilisateur;

/**
 * Servlet implementation class TraitementProfile
 */
@WebServlet("/TraitementProfile")
public class TraitementProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraitementProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur utilisateur;
		boolean modifier=false;
		boolean supprimer=false;
		if(request.getParameter("modifier")!=null) {
			modifier=true;
		}
		if(request.getParameter("supprimer")!=null) {
			supprimer=true;
		}
		
		if (modifier) {
			String pseudoAAfficher = (String) request.getParameter("pseudoAAfficher");
			utilisateur = utilisateurManager.getByPseudo(pseudoAAfficher);
			request.setAttribute("utilisateurAAfficher", utilisateur);
			RequestDispatcher rd = request.getRequestDispatcher("/MonProfile");
			rd.forward(request, response);
		}
		
		if (supprimer) {
			String pseudoASupprimer = (String) request.getParameter("pseudoASupprimer");
			utilisateur = utilisateurManager.getByPseudo(pseudoASupprimer);
			System.out.println("effacer utilisateur : " + utilisateur.getNom());
			utilisateurManager.delete(utilisateur);
			RequestDispatcher rd = request.getRequestDispatcher("/TraitementConnexion");
			rd.forward(request, response);
		}
		
		String pseudoAAfficher = (String) request.getParameter("pseudoAAfficher");
		utilisateur = utilisateurManager.getByPseudo(pseudoAAfficher);		
		request.setAttribute("utilisateurAAfficher", utilisateur);
		RequestDispatcher rd = request.getRequestDispatcher("/Profile");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		String pseudo = (String) session.getAttribute("pseudo");
		Map<String, String> listeIdentifiants = utilisateurManager.getAllIdentifiants();
		boolean pseudoOk = true;
		Utilisateur utilisateurModifie=null;
		
		//teste si pseudo correspond à  une entrée dans la bdd
		for(Entry<String, String> user : listeIdentifiants.entrySet()) {
			String pseudoBdd = user.getKey();
		    if (pseudoBdd.equals(pseudo)) {
				pseudoOk = false;
				utilisateurModifie = utilisateurManager.getByPseudo(pseudo);
		    }
		}
		
		
		//crée une instance pojo utilisateur avec les données
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNom(request.getParameter("nom"));
		utilisateur.setPrenom(request.getParameter("prenom"));
		utilisateur.setEmail(request.getParameter("email"));
		utilisateur.setTelephone(request.getParameter("telephone"));
		utilisateur.setRue(request.getParameter("rue"));
		utilisateur.setCodePostal(request.getParameter("codePostal"));
		utilisateur.setVille(request.getParameter("ville"));
		utilisateur.setMotDePasse(request.getParameter("password"));
		int credit = 0;
		boolean administrateur = false;
		List<ArticleVendu> vente = new ArrayList<>();
		List<ArticleVendu> listeArticlesAchete = new ArrayList<>();
		List<Enchere> listeEncheres = new ArrayList<>();
		utilisateur.setCredit(credit);
		utilisateur.setAdministrateur(administrateur);
		utilisateur.setVente(vente);
		utilisateur.setListeArticlesAchete(listeArticlesAchete);
		utilisateur.setListeEncheres(listeEncheres);
		
		
		if (!pseudoOk) {
			//si le pseudo existe déjà
			if (request.getParameter("update")!=null) {
				utilisateurModifie.setEmail(utilisateur.getEmail());
				utilisateurModifie.setNom(utilisateur.getNom());
				utilisateurModifie.setPrenom(utilisateur.getPrenom());
				utilisateurModifie.setTelephone(utilisateur.getTelephone());
				utilisateurModifie.setRue(utilisateur.getRue());
				utilisateurModifie.setCodePostal(utilisateur.getCodePostal());
				utilisateurModifie.setVille(utilisateur.getVille());
				utilisateurManager.updateUtilisateur(utilisateurModifie);
				RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
				rd.forward(request, response);
			} else {
				//on envoie l'erreur sur le pseudo et l'objet utilisateur
				request.setAttribute("erreurPseudo", "Veuillez choisir un autre pseudo.");
				request.setAttribute("utilisateur", utilisateur);
				RequestDispatcher rd = request.getRequestDispatcher("/MonProfile");
				rd.forward(request, response);
			}
		}else {
			//sinon on inclut l'utilisateur
			utilisateur.setPseudo(request.getParameter("pseudo"));
			utilisateurManager.addUtilisateur(utilisateur);
			session.setAttribute("pseudo", utilisateur.getPseudo());
			RequestDispatcher rd = request.getRequestDispatcher("/TraitementAccueil");
			rd.forward(request, response);
		}
		
		
		
		
	}

}
