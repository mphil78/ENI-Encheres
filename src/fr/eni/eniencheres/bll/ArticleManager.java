package fr.eni.eniencheres.bll;

import fr.eni.eniencheres.bo.ArticleVendu;
import fr.eni.eniencheres.dal.ArticleVenduDAO;
import fr.eni.eniencheres.dal.DALException;
import fr.eni.eniencheres.dal.DAOFactory;

//TODO vérifier les catch block
public class ArticleManager {

	private ArticleVenduDAO articleDAO;
	
	/**
	 * Constructeur de ArticleManager
	 */
	public ArticleManager() {
		this.articleDAO=DAOFactory.getArticleDAO();
	}
	
	/**
	 * Ajoute un article dans la BDD
	 * @param article
	 */	 
	public void addArticle(ArticleVendu article) {
		try {
			articleDAO.insert(article);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
	
}
