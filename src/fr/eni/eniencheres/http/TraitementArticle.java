package fr.eni.eniencheres.http;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.eniencheres.bll.managers.ArticleManager;
import fr.eni.eniencheres.bll.managers.CategorieManager;
import fr.eni.eniencheres.bll.managers.RetraitManager;
import fr.eni.eniencheres.bll.managers.UtilisateurManager;
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
    }

	/**
	 * Forward vers /NouvelleVente en passant en attribut l'objet Vendeur et la liste des libellés
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//récupère la session
		HttpSession session = request.getSession();
		
		//instanciation des managers
		CategorieManager categorieManager = new CategorieManager();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur vendeur = utilisateurManager.getByPseudo((String)session.getAttribute("pseudo"));
		
		//récupération de la liste des libelles
		List<String> listeLibellesCategories = new ArrayList<>();
		listeLibellesCategories = categorieManager.getAllLibelles();
		
		//affection des attributs et forward
		request.setAttribute("vendeur",vendeur);
		request.setAttribute("libelles", listeLibellesCategories);
		RequestDispatcher rd = request.getRequestDispatcher("./NouvelleVente");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO A terminer
		
		//Récupération de la session
		HttpSession session = request.getSession();
		
		//instanciation des managers
		ArticleManager arcticleManager = new ArticleManager();
		CategorieManager categorieManager = new CategorieManager();
		RetraitManager retraitManager = new RetraitManager();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		//Récupération des données
		String nom = request.getParameter("nom");
		String description = request.getParameter("description");
		LocalDate debutEncheres = LocalDate.parse(request.getParameter("debutEnchere"));
		LocalDate finEncheres = LocalDate.parse(request.getParameter("finEnchere"));
		int miseAPrix = Integer.valueOf(request.getParameter("defPrix"));
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		
		//Récupération de l'objet catégorie
		String categorie = request.getParameter("categorie");
		Categorie cat = categorieManager.getByNom(categorie);
		
		//Récupération de l'objet vendeur
		Utilisateur vendeur = utilisateurManager.getByPseudo((String)session.getAttribute("pseudo"));
		
		//Création de l'objet article
		ArticleVendu article = new ArticleVendu(
								nom,
								description,
								debutEncheres,
								finEncheres,
								miseAPrix,
								//TODO prix vente initial mettre un magic number
								0,
								//TODO etat vente mettre un magic number
								0,
								cat,
								vendeur,
								//l'acheteur est le vendeur tant qu'il n'y a pas d'enchère
								vendeur
								);

		//ajoute l'article dans la bdd
		arcticleManager.addArticle(article);
		
		//Création du lieu de retrait
		Retrait lieuRetrait = new Retrait(rue, codePostal, ville,article);
		
		//ajoute le lieu de retrait dans la bdd
		//TODO A placer dans article manager
		retraitManager.addRetrait(lieuRetrait);
		
		//TODO faire le forward
		RequestDispatcher rd = request.getRequestDispatcher("/TraitementAccueil");
		rd.forward(request, response);
	}

}
