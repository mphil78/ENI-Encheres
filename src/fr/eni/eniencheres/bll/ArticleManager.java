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
	 * Retourne la liste de tous les articles vendus par un utilisateur en fonction de son pseudo.
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
	 * Retourne la liste de tous les articles vendus par un utilisateur en fonction de son pseudo dont l'état est "CREEE"
	 * @param pseudo
	 * @return List<ArticleVendu> toutesLesVentesCreees
	 */
	public List<ArticleVendu> getVenteNonDebuteesByPseudo(String pseudo){
		List<ArticleVendu> toutesLesVentesCreees = new ArrayList<ArticleVendu>();
		try {
			toutesLesVentesCreees = articleDAO.selectVenteCreeeByPseudo(pseudo);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toutesLesVentesCreees;	
	}
	
	/**
	 * Retourne la liste de tous les articles vendus par un utilisateur en fonction de son pseudo dont l'état est "ENCOURS"
	 * @param pseudo
	 * @return List<ArticleVendu> toutesLesVentesEnCours
	 */
	public List<ArticleVendu> getVenteEnCoursByPseudo(String pseudo){
		List<ArticleVendu> toutesLesVentesEnCours = new ArrayList<ArticleVendu>();
		try {
			toutesLesVentesEnCours = articleDAO.selectVenteEnCoursByPseudo(pseudo);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toutesLesVentesEnCours;	
	}
	
	/**
	 * Retourne la liste de tous les articles vendus par un utilisateur en fonction de son pseudo dont l'état est "TERMINEE"
	 * @param pseudo
	 * @return List<ArticleVendu> toutesLesVentesTerminees
	 */
	public List<ArticleVendu> getVenteTermineeByPseudo(String pseudo){
		List<ArticleVendu> toutesLesVentesTerminees = new ArrayList<ArticleVendu>();
		try {
			toutesLesVentesTerminees = articleDAO.selectVenteTermineeByPseudo(pseudo);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toutesLesVentesTerminees;	
	}	
	
}
