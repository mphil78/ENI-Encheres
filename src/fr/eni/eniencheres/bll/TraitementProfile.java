package fr.eni.eniencheres.bll;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    }

	/**
	 * Redirige vers :
	 * -/MonProfile en cas de modificationdu profile avec l'objet utilisateur en attribut de requete </br>
	 * -/TraitementConnexion en casde suppression apres avoir supprimer l'utilisateur de la BDD</br>
	 * -/Profile en cas de simple affichage avec en parametre l'objet utilisateur 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur utilisateur;
		boolean modifier=false;
		boolean supprimer=false;
		
		//récupère le parametre modifier ou supprimer
		if(request.getParameter("modifier")!=null) {
			modifier=true;
		}
		if(request.getParameter("supprimer")!=null) {
			supprimer=true;
		}
		
		//cas de mofification
		if (modifier) {
			String pseudoAAfficher = (String) request.getParameter("pseudoAAfficher");
			utilisateur = utilisateurManager.getByPseudo(pseudoAAfficher);
			request.setAttribute("utilisateurAAfficher", utilisateur);
			RequestDispatcher rd = request.getRequestDispatcher("/MonProfile");
			rd.forward(request, response);
		}
		
		//cas de suppression
		if (supprimer) {
			String pseudoASupprimer = (String) request.getParameter("pseudoASupprimer");
			utilisateur = utilisateurManager.getByPseudo(pseudoASupprimer);
			if (utilisateurManager.isActif(utilisateur)) {
				
				//TODO récupération des donnees de vente et d'achat de l'utilisateur pour tester si suppression possible
				System.out.println("effacer utilisateur : " + utilisateur.getNom());
				utilisateurManager.delete(utilisateur);
			}
			//TODO récupération des donnees de vente et d'achat de l'utilisateur pour tester si suppression possible
			System.out.println("effacer utilisateur : " + utilisateur.getNom());
			utilisateurManager.delete(utilisateur);
			RequestDispatcher rd = request.getRequestDispatcher("/TraitementConnexion");
			rd.forward(request, response);
		}
		
		//cas de simple affichage
		String pseudoAAfficher = (String) request.getParameter("pseudoAAfficher");
		utilisateur = utilisateurManager.getByPseudo(pseudoAAfficher);		
		request.setAttribute("utilisateurAAfficher", utilisateur);
		RequestDispatcher rd = request.getRequestDispatcher("/Profile");
		rd.forward(request, response);
	}

	/**
	 * En cas de creation de compte : Controle l'unicite de l'email et du pseudo avant d'ajouter l'utilisateur dans la BDD ou de retourner une erreur le cas échéant</br>
	 * En cas de modification du profile : controle l'unicite de l'email et la concordance des passwords avant de modifier la BDD</br>
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//récuperation de la session
		HttpSession session = request.getSession();

		//intanciation du manager
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		//récupération des emails et pseudos et mdp
		Map<String[],String> listePseudosEmails = utilisateurManager.getAllPseudoEmail();
		
		
		//CAS DE LA CREATION DE COMPTE
		//----------------------------
		if (request.getParameter("creation")!=null) {
			boolean erreur = false;
			
			//crée une instance pojo utilisateur avec les données non uniques
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setNom(request.getParameter("nom"));
			utilisateur.setPrenom(request.getParameter("prenom"));
			utilisateur.setTelephone(request.getParameter("telephone"));
			utilisateur.setRue(request.getParameter("rue"));
			utilisateur.setCodePostal(request.getParameter("codePostal"));
			utilisateur.setVille(request.getParameter("ville"));
			utilisateur.setCredit(0);
			utilisateur.setAdministrateur(false);
			
			//détermine si il y a une erreur
				//vérifie si les pseudo ou email sont uniques
				for(Entry<String[], String> user : listePseudosEmails.entrySet()) {
					String pseudoBdd = user.getKey()[0];
					String emailBdd = user.getKey()[1];
				    if (pseudoBdd.equals(request.getParameter("pseudo"))) {
						request.setAttribute("erreurPseudo", "Pseudo déjà utilisé. Veuillez choisir un autre pseudo.");
						erreur=true;
					}
					if (emailBdd.equals(request.getParameter("email"))) {
						request.setAttribute("erreurEmail", "Email déjà utilisé. Veuillez choisir un autre email.");
						erreur=true;
				    }
				}
				//vérifie la concordance des mots de passes
				if(!request.getParameter("password").equals(request.getParameter("password2"))) {
					utilisateur.setMotDePasse("");
					request.setAttribute("erreurMotDePasse", "Les mots de passes ne correspondent pas.");
					erreur = true;
				}
			
			//si il y au moins une ereeur on renvoie les données de l'utilisateur et les erreurs à la jsp
			if(erreur) {
				request.setAttribute("utilisateur", utilisateur);
				RequestDispatcher rd = request.getRequestDispatcher("/MonProfile");
				rd.forward(request, response);
			} 
			
			//sinon on complète le pojo avant de l'incure dans la bdd, on attribue le pseudo à la session et on renvoie à l'accueil
			else {
				utilisateur.setPseudo(request.getParameter("pseudo"));
				utilisateur.setEmail(request.getParameter("email"));
				utilisateur.setMotDePasse(UtilisateurManager.hash(request.getParameter("password")));
				utilisateurManager.addUtilisateur(utilisateur);
				session.setAttribute("pseudo", utilisateur.getPseudo());
				response.sendRedirect("./TraitementAccueil"); 
			}
		}
		
		//CAS DE L'UPDATE
		//---------------
		if (request.getParameter("update")!=null) {
			Utilisateur utilisateurModifie = utilisateurManager.getByPseudo((String)session.getAttribute("pseudo"));
			boolean erreur = false;
			//détermine si il y a une erreur
				//détermine si l'email choisi existe déjà et que le mot de passe est correct et le cas échant le nouveau mot de passe choisi est ok
				for(Entry<String[], String> user : listePseudosEmails.entrySet()) {
					String pseudoBdd = user.getKey()[0];
					String emailBdd = user.getKey()[1];
				    if (!pseudoBdd.equals(session.getAttribute("pseudo"))) {
				    	if (emailBdd.equals(request.getParameter("email"))) {
							request.setAttribute("erreurEmail", "Veuillez choisir un autre email.");
							erreur=true;
				    	}
				    } else {
				    	if (!UtilisateurManager.hash(request.getParameter("password3")).equals(utilisateurModifie.getMotDePasse())) {
							erreur=true;
							request.setAttribute("erreurMotDePasse", "Le mot de passe actuel est erroné.");
				    	} else {
				    		if (!request.getParameter("password4").equals(request.getParameter("password5"))) {
								request.setAttribute("erreurMotDePasse2", "Les deux mots de passe ne correspondent pas. Veuillez rectifier.");
								erreur=true;
				    		}
				    	}
				    }
				}
			//en cas d'erreur on renvoie à la page de modification de profile avec le profile inchangé
				if (erreur) {
					request.setAttribute("utilisateur", utilisateurModifie);
					RequestDispatcher rd = request.getRequestDispatcher("/MonProfile");
					rd.forward(request, response);
				}
			//s'il n'y a pas d'erreur on inclut les modifications dans la BDD	
				else {
					utilisateurModifie.setNom(request.getParameter("nom"));
					utilisateurModifie.setPrenom(request.getParameter("prenom"));
					utilisateurModifie.setEmail(request.getParameter("email"));
					utilisateurModifie.setTelephone(request.getParameter("telephone"));
					utilisateurModifie.setRue(request.getParameter("rue"));
					utilisateurModifie.setCodePostal(request.getParameter("codePostal"));
					utilisateurModifie.setVille(request.getParameter("ville"));
					utilisateurModifie.setCredit(Integer.parseInt(request.getParameter("credit")));
					if (!request.getParameter("password4").equals("")) {
						utilisateurModifie.setMotDePasse(UtilisateurManager.hash(request.getParameter("password4")));
					}
					utilisateurManager.updateUtilisateur(utilisateurModifie);
					//redirection
					response.sendRedirect("./TraitementAccueil"); 
				}
		}
	}
}
