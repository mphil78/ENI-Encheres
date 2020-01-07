package fr.eni.eniencheres.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.eniencheres.bo.ArticleVendu;
import fr.eni.eniencheres.dal.ArticleVenduDAO;
import fr.eni.eniencheres.dal.DALException;
import fr.eni.eniencheres.dal.DAOFactory;

//TODO Faire les méthodes de séléction d'articles en fonction de l'état et faire le ménage

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
	 * Retourne l'article en fonction de son id
	 * @return ArticleVendu
	 */
	public ArticleVendu getById(int id) {
		ArticleVendu article = new ArticleVendu();
		try {
			article =  articleDAO.selectById(id);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return article;
	}
	
	/**
	 * Retourne la list de tous les objets articles en cours
	 * @return List<ArticleVendu> tousLesArticles
	 */
	public List<ArticleVendu> getAllArticlesEnCours() {
		List<ArticleVendu> tousLesArticles = new ArrayList<ArticleVendu>();
		try {
			tousLesArticles =  articleDAO.selectAllEnCours();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tousLesArticles;
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
	
	public List<ArticleVendu> getArticlesByMotCle(String motCle) {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		try {
			listeArticles = articleDAO.selectByMotCle(motCle);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeArticles;
		
	}

	public List<ArticleVendu> getArticlesByMotCleAndCate(String motCle, int noCategorie) {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		try {
			if (noCategorie==-1) {
				listeArticles=articleDAO.selectByMotCle(motCle);
			} else {
				listeArticles = articleDAO.selectByFiltres(motCle, noCategorie);
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeArticles;
	}

	
	public void majArticlesBDD() {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		listeArticles = getAllArticles();
		for (ArticleVendu article : listeArticles) {
			if (article.getEtatVente()!=ArticleVendu.ANNULEE && article.getEtatVente()!=ArticleVendu.RETIREE) 
			{			
				if (LocalDate.now().isAfter(article.getDateFinEncheres()) || LocalDate.now().isEqual(article.getDateFinEncheres())) 
				{
					article.setEtatVente(ArticleVendu.TERMINEE);
				} 
				else 
				{
					if (LocalDate.now().isAfter(article.getDateDebutEncheres()) || LocalDate.now().isEqual(article.getDateDebutEncheres())) 
					{
						article.setEtatVente(ArticleVendu.ENCOURS);
					} 
					else 
					{
						article.setEtatVente(ArticleVendu.CREEE);
					}
				}
			}
			try {
				articleDAO.update(article);
			} catch (DALException e) {
				e.printStackTrace();
			}
		}
	}

	public List<ArticleVendu> getVentesEnCoursByPseudo(String pseudo) {
		List<ArticleVendu> ventes = new ArrayList<ArticleVendu>();
		try {
			ventes = articleDAO.selectVenteEnCoursByPseudo(pseudo);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ventes;
	}

	public List<ArticleVendu> getVentesNonDebuteesByPseudo(String pseudo) {
		List<ArticleVendu> ventes = new ArrayList<ArticleVendu>();
		try {
			ventes = articleDAO.selectVenteNonDebuteesByPseudo(pseudo);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ventes;
	}

	public List<ArticleVendu> getVentesTermineesByPseudo(String pseudo) {
		List<ArticleVendu> ventes = new ArrayList<ArticleVendu>();
		try {
			ventes = articleDAO.selectVenteTermineesByPseudo(pseudo);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ventes;
	}
	
	
}
