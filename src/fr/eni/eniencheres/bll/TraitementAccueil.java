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


/**
 * Servlet implementation class TraitementAccueil
 */
@WebServlet("/TraitementAccueil")
public class TraitementAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		//Instanciation des manager
		CategorieManager categorieManager = new CategorieManager();
		ArticleManager articleManager = new ArticleManager();
		//récupération des catégories et de la liste des articles
		List<String> listeLibellesCategories = new ArrayList<>();
		List<ArticleVendu> listeArticles = new ArrayList<>(); 
		listeLibellesCategories = categorieManager.getAllLibelles();
		listeArticles = articleManager.getAllArticles();
		request.setAttribute("articles", listeArticles);
		request.setAttribute("libelles", listeLibellesCategories);
		//redirection
		RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
		rd.forward(request, response);
	}

	/**
	 * Forward vers l'accueil en envoyant la liste des libelles et des articles demandés
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Passage par doPost TraitementAccueil");
		//Récupération de la session
		HttpSession session = request.getSession();
		
		boolean connecte = session.getAttribute("pseudo")==null?false:true;
		boolean motCle = (request.getParameter("motCle")==null||request.getParameter("motCle").equals(""))?false:true;

		//Instanciation des managers
		CategorieManager categorieManager = new CategorieManager();
		ArticleManager articleManager = new ArticleManager();

		//récupération des catégories et de la liste des articles
		List<String> listeLibellesCategories = new ArrayList<>();
		List<ArticleVendu> listeArticles = new ArrayList<>(); 
		listeLibellesCategories = categorieManager.getAllLibelles();
		
		
		if(motCle) {
			listeArticles = articleManager.getArticlesByMotCle(request.getParameter("motCle"));
			//envoie les attributs à la request
			request.setAttribute("articles", listeArticles);
			request.setAttribute("libelles", listeLibellesCategories);
			//redirection
			RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
			rd.forward(request, response);
		}
		
		doGet(request, response);
	}

}
