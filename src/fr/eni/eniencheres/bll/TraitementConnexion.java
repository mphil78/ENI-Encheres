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

/**
 * Servlet implementation class TraitementUtilisateur
 */
@WebServlet("/TraitementConnexion")
public class TraitementConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TraitementConnexion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		RequestDispatcher rd = request.getRequestDispatcher("/TraitementAccueil");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		log("INFO", "method doPost Servlet : Connexion");

		
		String pseudoConnexion = request.getParameter("identifiant");
		//TODO Creer un Hash du mot de passe des la reception pour et ne transporter que le hash dans les methodes.
		String mdpConnexion = UtilisateurManager.hash(request.getParameter("motDePasse"));

		//TODO Lire le Cookie remember me dans le cooKie
		

		
		boolean connexionOk = isIdentOK(pseudoConnexion, mdpConnexion);
		
		//redirection adaptee
		if (connexionOk) {
			HttpSession session = request.getSession(true);
			session.setAttribute("pseudo", pseudoConnexion);
			RequestDispatcher rd = request.getRequestDispatcher("/TraitementAccueil");
			rd.forward(request, response);
		} else {
			request.setAttribute("erreurConnexion", "La connexion a échoué. Veuillez réessayer.");
			RequestDispatcher rd = request.getRequestDispatcher("/Connexion");
			rd.forward(request, response);
		}

	}

	/**
	 * teste si pseudo et mdp correrspondent a  une entree dans la bdd
	 * TODO reflechir a centraliser les regeles de gestion dans le manager La servlet regroupe tout ce qui est Http
	 * @param pseudoConnexion
	 * @param mdpConnexion
	 * @return boolean connexion
	 * 
	 */
	private boolean isIdentOK(final String pseudoConnexion, final String mdpConnexion) {
		//Access aux donnees en base
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Map<String, String> listeIdentifiants = utilisateurManager.getAllIdentifiants();
		
		//
		boolean connexionOk = false;
		for(Entry<String, String> user : listeIdentifiants.entrySet()) {
			String pseudo = user.getKey();
			String mdp = user.getValue();
		    if (pseudo.equals(pseudoConnexion)) {
				if (mdp.equals(mdpConnexion)) {
					connexionOk = true;
				}
		    }
		}
		return connexionOk;
	}

	
	private static void log(final String level, final String message) {
		System.out.println(level + message);
	}
}
