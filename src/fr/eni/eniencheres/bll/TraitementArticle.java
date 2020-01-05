package fr.eni.eniencheres.bll;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import fr.eni.eniencheres.bo.ArticleVendu;
import fr.eni.eniencheres.bo.Categorie;
import fr.eni.eniencheres.bo.Retrait;
import fr.eni.eniencheres.bo.Utilisateur;

/**
 * Servlet implementation class TraitementArticle
 */
@WebServlet("/TraitementArticle")
//TODO réfléchir sur les tailles
@MultipartConfig(location="c:/images/", fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class TraitementArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final int TAILLE_TAMPON = 10240;
    //TODO où enregistrer les fichiers ?
    public static final String CHEMIN_FICHIERS = "c:/images/";

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraitementArticle() {
        super();
    }

    /**
	 * Forward vers /NouvelleVente en passant en attribut l'objet Vendeur et la liste des libelles
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//récupère la session
		HttpSession session = request.getSession();
		
		//instanciation des managers
		CategorieManager categorieManager = new CategorieManager();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		//recuperation du vendeur
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
		String nom = getValeur(request.getPart("nom"));
		String description = getValeur(request.getPart("description"));
		LocalDate debutEncheres = LocalDate.parse(getValeur(request.getPart("debutEnchere")));
		LocalDate finEncheres = LocalDate.parse(getValeur(request.getPart("finEnchere")));
		int miseAPrix = Integer.valueOf(getValeur(request.getPart("defPrix")));
		String rue = getValeur(request.getPart("rue"));
		String codePostal = getValeur(request.getPart("codePostal"));
		String ville = getValeur(request.getPart("ville"));
		
		
		//Récupération de l'objet catégorie
		Categorie cat = categorieManager.getByNom(getValeur(request.getPart("categorie")));
		
		//Récupération de l'objet vendeur
		Utilisateur vendeur = utilisateurManager.getByPseudo((String)session.getAttribute("pseudo"));
		
		//Création de l'objet article
		ArticleVendu article = new ArticleVendu(
								nom,
								description,
								debutEncheres,
								finEncheres,
								miseAPrix,
								//le prix de vente "initial" (sans enchérisseur) est égal au prix initial
								miseAPrix,
								ArticleVendu.CREEE,
								cat,
								vendeur,
								//l'encherisseur est le vendeur tant qu'il n'y a pas d'enchères
								vendeur
								);

		
		 //ajoute l'article dans la bdd
		arcticleManager.addArticle(article);
		
		//Création du lieu de retrait
		Retrait lieuRetrait = new Retrait(rue, codePostal, ville, article);
		article.setLieuRetrait(lieuRetrait);
		
		//ajoute le lieu de retrait dans la bdd
		retraitManager.addRetrait(lieuRetrait);
		
		
		
		//récupération de l'image
		Part part = request.getPart( "photoArticle" );
	    String nomFichier = getNomFichier( part );
        if (nomFichier != null && !nomFichier.isEmpty()) {
            // On écrit définitivement le fichier sur le disque
        	String[] extension = nomFichier.split("[.]");
        	//le nom du fichier devient l'id de l'article suivi de l'extension
        	String nomImage = article.getNoArticle() + "." + extension[extension.length-1];
            ecrireFichier(part, nomImage, CHEMIN_FICHIERS);
        }
				
		//redirection vers /DetailVente avec les parametres utilisateur et article
		request.setAttribute("utilisateur",vendeur);
		request.setAttribute("articleAAfficher", article);
		RequestDispatcher rd = request.getRequestDispatcher("./DetailVente");
		rd.forward(request, response);
	}

	/** 
	 * Méthode utilitaire qui a pour but d'analyser l'en-tête "content-disposition",
	 * et de vérifier si le paramètre "filename"  y est présent. Si oui, alors le champ traité
	 * est de type File et la méthode retourne son nom, sinon il s'agit d'un champ de formulaire 
	 * classique et la méthode retourne null. 
	 */
	private static String getNomFichier( Part part ) {
	    /* Boucle sur chacun des paramètres de l'en-tête "content-disposition". */
	    for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
	        /* Recherche de l'éventuelle présence du paramètre "filename". */
	        if ( contentDisposition.trim().startsWith("filename") ) {
	            /* Si "filename" est présent, alors renvoi de sa valeur, c'est-à-dire du nom de fichier. */
	            return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
	        }
	    }
	    /* Et pour terminer, si rien n'a été trouvé... */
	    return null;
	}
	
	/**
	 * Méthode utilitaire qui a pour but d'écrire le fichier passé en paramètre
	 * sur le disque, dans le répertoire donné et avec le nom donné.
	 */
    private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            File fichier=new File(chemin + nomFichier);
            sortie = new BufferedOutputStream(new FileOutputStream(fichier), TAILLE_TAMPON);

            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            try {
                sortie.close();
            } catch (IOException ignore) {
            }
            try {
                entree.close();
            } catch (IOException ignore) {
            }
        }
    }

    /**
     * Méthode utilitaire qui a pour unique but de lire l'InputStream contenu
     * dans l'objet part, et de le convertir en une banale chaîne de caractères.
     */
    private String getValeur( Part part ) throws IOException {
        BufferedReader reader = new BufferedReader( new InputStreamReader( part.getInputStream(), "UTF-8" ) );
        StringBuilder valeur = new StringBuilder();
        char[] buffer = new char[1024];
        int longueur = 0;
        while ( ( longueur = reader.read( buffer ) ) > 0 ) {
            valeur.append( buffer, 0, longueur );
        }
        return valeur.toString();
    }
}
