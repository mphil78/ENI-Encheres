package fr.eni.eniencheres.bll;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.eniencheres.bo.ArticleVendu;
import fr.eni.eniencheres.bo.Categorie;
import fr.eni.eniencheres.bo.Retrait;
import fr.eni.eniencheres.bo.Utilisateur;

/**
 * Servlet implementation class TraitementArticle
 */
@WebServlet("/TraitementArticle")
public class TraitementArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraitementArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO A terminer
		//Récupération de la session
		HttpSession session = request.getSession();
		//instanciation des managers
		CategorieManager categorieManager = new CategorieManager();
		RetraitManager retraitManager = new RetraitManager();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		//Récupération des données
		String nom = request.getParameter("nom");
		String description = request.getParameter("description");
		LocalDate debutEncheres = LocalDate.parse(request.getParameter("debutEnchere"));
		LocalDate finEncheres = LocalDate.parse(request.getParameter("finEnchere"));
		int miseAPrix = Integer.valueOf(request.getParameter("defPrix")) ;
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		//TODO Récupération de l'objet catégorie
		String categorie = request.getParameter("categorie");
		Categorie cat = categorieManager.getByNom(categorie);
		//Récupération de l'objet vendeur
		Utilisateur vendeur = utilisateurManager.getByPseudo((String)session.getAttribute("pseudo"));
		//Création du lieu de retrait
		Retrait lieuRetrait = null;
		//Création de l'objet article
		ArticleVendu article = new ArticleVendu(
								nom,
								description,
								debutEncheres,
								finEncheres,
								miseAPrix,
								0,
								0,
								cat,
								vendeur,
								lieuRetrait
								);
		lieuRetrait = new Retrait(rue, codePostal, ville, article);
		
		// TODO : inserer Retrait dans BDD, rechercher categorie dans BDD, inserer categorie dans BDD 

	}

}
