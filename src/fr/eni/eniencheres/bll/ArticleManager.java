package fr.eni.eniencheres.bll;

import java.util.ArrayList;
import java.util.List;

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

	/**
	 * Retourne la list de tous les objets articles
	 * @return List<ArticleVendu> tousLesArticles
	 */
	public List<ArticleVendu> getAllArticles() {
		List<ArticleVendu> tousLesArticles = new ArrayList<ArticleVendu>();
		try {
			tousLesArticles =  articleDAO.selectAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tousLesArticles;
	}
	
	/**
	 * Retourne la liste de tous les articles vendus par un utilisateur en fonction de son pseudo quelque soit son état.
	 * @param pseudo
	 * @return List<ArticleVendu> toutesLesVentes
	 */
	public List<ArticleVendu> getVenteByPseudo(String pseudo){
		List<ArticleVendu> toutesLesVentes = new ArrayList<ArticleVendu>();
		try {
			toutesLesVentes = articleDAO.selectVenteByPseudo(pseudo);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toutesLesVentes;	
	}
	
	/**
	 * Retourne la liste de tous les articles vendus par un utilisateur en fonction de son pseudo de l'état<br>
	 * Rappel des états possibles :<br>
	 * CREEE=0 (non commencée), ENCOURS=1, TERMINEE=2 RETIREE=3, ANNULEE=4<br>
	 * @param pseudo
	 * @param ETAT
	 * @return
	 */
	public List<ArticleVendu> getVenteByPseudoAndEtat(String pseudo, final int ETAT) {
		List<ArticleVendu> ventes = new ArrayList<ArticleVendu>();
		try {
			ventes = articleDAO.selectVenteByPseudoAndEtat(pseudo, ETAT);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ventes;
		
	}
	
}
