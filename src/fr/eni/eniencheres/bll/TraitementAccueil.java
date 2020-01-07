package fr.eni.eniencheres.bll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.eniencheres.bo.ArticleVendu;
import fr.eni.eniencheres.bo.Categorie;


/**
 * Servlet implementation class TraitementAccueil
 */
@WebServlet("/TraitementAccueil")
public class TraitementAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int ACHATS=0;
	private static final int VENTES=1;
	private static final int A_OUVERTES=1;
	private static final int A_ENCOURS=1;
	private static final int A_REMPORTES=2;
	private static final int V_ENCOURS=1;
	private static final int V_NONDEBUTEES=1;
	private static final int V_TERMINEES=2;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraitementAccueil() {
        super();
    }

    /**
	 * Forward vers /Accueil en envoyant la liste des libelles et la liste de tous les articles
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Passage par doGet : TraitementAccueil");
		
		//Instanciation des manager
		CategorieManager categorieManager = new CategorieManager();
		ArticleManager articleManager = new ArticleManager();
		
		//récupération des catégories et de la liste des articles
		List<Categorie> listeCategories = new ArrayList<>();
		List<ArticleVendu> listeArticles = new ArrayList<>();
		listeCategories = categorieManager.getAll();
		listeArticles = articleManager.getAllArticles();
		
		//affectation des attributs du request
		request.setAttribute("articles", listeArticles);
		request.setAttribute("categories", listeCategories);
		
		//redirection
		RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
		rd.forward(request, response);
	}

	/**
	 * Forward vers l'accueil en envoyant la liste des libelles et des articles demandés
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Passage par doPost : TraitementAccueil");
		System.out.println(request.getParameter("motCle"));
		
		//Récupération de la session
		HttpSession session = request.getSession();
		
		//récupération des données du formulaire
		boolean connecte = session.getAttribute("pseudo")==null?false:true;
		String motCle = request.getParameter("motCle")==null?"":request.getParameter("motCle");
		int categorie = Integer.parseInt(request.getParameter("categorie"));

		//Instanciation des managers
		CategorieManager categorieManager = new CategorieManager();
		ArticleManager articleManager = new ArticleManager();

		//récupération des catégories et de la liste des articles
		List<Categorie> listeCategories = new ArrayList<>();
		listeCategories = categorieManager.getAll();
		
		//TODO A finir : gerer le formulaire en mode connecté et faire la DAO
		//récupération de la liste des articles
		List<ArticleVendu> listeArticles = new ArrayList<>(); 
		if (!connecte) {
			listeArticles = articleManager.getArticlesByMotCleAndCate(motCle, categorie);

		} else {
			String pseudo = (String)session.getAttribute("pseudo");
			int achatVente = Integer.parseInt(request.getParameter("achatVente"));
			switch(achatVente) {
				case ACHATS : 
					List<ArticleVendu> achatsOuvertes = new ArrayList<ArticleVendu>();
					List<ArticleVendu> achatsEnCours = new ArrayList<ArticleVendu>();
					List<ArticleVendu> achatsRemportees = new ArrayList<ArticleVendu>();
					if (request.getParameter("chk1")!=null) {
//						achatsEnCours = articleManager.getAchatByPseudoAndEtat(pseudo, ArticleVendu.TOUTETAT);
					}
					if (request.getParameter("chk2")!=null) {
//						achatsEnCours = articleManager.getAchatByPseudoAndEtat(pseudo, ArticleVendu.CREEE);
					}
					if (request.getParameter("chk3")!=null) {
//						achatsRemportees = articleManager.getAchatByPseudoAndEtat(pseudo, ArticleVendu.TERMINEE);
					}
					listeArticles.addAll(achatsOuvertes);
					listeArticles.addAll(achatsEnCours);
					listeArticles.addAll(achatsRemportees);
					
					break;
				case VENTES :
					List<ArticleVendu> ventesEnCours = new ArrayList<ArticleVendu>();
					List<ArticleVendu> ventesNonDebutees = new ArrayList<ArticleVendu>();
					List<ArticleVendu> ventesTerminees = new ArrayList<ArticleVendu>();
					if (request.getParameter("chk1")!=null) {
						ventesEnCours = articleManager.getVenteByPseudoAndEtat(pseudo, ArticleVendu.ENCOURS);
					}
					if (request.getParameter("chk2")!=null) {
						ventesNonDebutees = articleManager.getVenteByPseudoAndEtat(pseudo, ArticleVendu.CREEE);
					}
					if (request.getParameter("chk3")!=null) {
						ventesTerminees = articleManager.getVenteByPseudoAndEtat(pseudo, ArticleVendu.TERMINEE);
					}
					listeArticles.addAll(ventesEnCours);
					listeArticles.addAll(ventesTerminees);
					listeArticles.addAll(ventesNonDebutees);
					break;
			}
		}
		
		//redirection
		request.setAttribute("articles", listeArticles);
		request.setAttribute("categories", listeCategories);
		RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
		rd.forward(request, response);
		
	}

}
