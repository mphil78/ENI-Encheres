package fr.eni.eniencheres.dal;

import java.util.List;

import fr.eni.eniencheres.bo.ArticleVendu;



public interface ArticleVenduDAO {
	/**
	 * Selectionner un article par son id
	 * @param id
	 * @return ArticleVendu
	 * @throws DALException
	 */		
	public ArticleVendu selectById(int id) throws DALException;
		
	/**
	 * Selectionner tous les articles
	 * @return List<ArticleVendu>
	 * @throws DALException
	 */		
	public List<ArticleVendu> selectAll() throws DALException;
	
	/**
	 * Selectionner tous les articles en cours
	 * @return List<ArticleVendu>
	 * @throws DALException
	 */		
	public List<ArticleVendu> selectAllEnCours() throws DALException;
	
	/**
	 * Inserer un nouvel article
	 * @param article
	 * @throws DALException
	 */		
	public void insert(ArticleVendu article) throws DALException;
		
	/**
	 * Supprimer un article
	 * @param articleVendu
	 * @throws DALException
	 */		
	public void delete(ArticleVendu articleVendu) throws DALException;
		
	/**
	 * Modifier un article
	 * @param articleVendu
	 * @throws DALException
	 */
	public void update(ArticleVendu articleVendu) throws DALException;
		
	/**
	 * Selectionner les articles par mot cle
	 * On recherche le mot cle dans le nom et la description
	 * @param motCle
	 * @return List<ArticleVendu>
	 * @throws DALException
	 */
	public List<ArticleVendu> selectByMotCle(String motCle) throws DALException;

	/**
	 * Selectionner les articles par mot cle et l'état
	 * On recherche le mot cle dans le nom et la description
	 * @param motCle
	 * @return List<ArticleVendu>
	 * @throws DALException
	 */
	public List<ArticleVendu> selectByMotCleAndEtat(String motCle, final int ETAT) throws DALException;
	
	/**
	 * Selectionner les articles par categorie
	 * @param noCategorie
	 * @return List<ArticleVendu>
	 * @throws DALException
	 */		
	public List<ArticleVendu> selectByCategorie(int noCategorie) throws DALException;
		
	/**
	 * Selectionner les articles par mot cle (nom et description) ET categorie
	 * @param motCle
	 * @param noCategorie
	 * @return List<ArticleVendu>
	 * @throws DALException
	 */
	public List<ArticleVendu> selectByFiltres(String motCle, int noCategorie) throws DALException;

	/**
	 * Retourne la liste des articles vendus par un utilisateur en fonction de son pseudo
	 * @param pseudo
	 * @return
	 * @throws DALException
	 */
	public List<ArticleVendu> selectVenteByPseudo(String pseudo) throws DALException;

	/**
	 * Retourne la liste des ventes en fonction du pseudo de l'utilisateur et de l'état de la vente
	 * @param ETAT 
	 * @param pseudo 
	 * @return
	 */
	public List<ArticleVendu> selectVenteByPseudoAndEtat(String pseudo, final int ETAT) throws DALException;

	
	/**
	 * Retourne la liste des ventes EN COURS en fonction du pseudo de l'utilisateur
	 * @param ETAT 
	 * @param pseudo 
	 * @return
	 */
	public List<ArticleVendu> selectVenteEnCoursByPseudo(String pseudo) throws DALException;

	/**
	 * Retourne la liste des ventes NON DEBUTEES en fonction du pseudo de l'utilisateur
	 * @param ETAT 
	 * @param pseudo 
	 * @return
	 */
	public List<ArticleVendu> selectVenteNonDebuteesByPseudo(String pseudo) throws DALException;

	/**
	 * Retourne la liste des ventes TERMINEES en fonction du pseudo de l'utilisateur
	 * @param ETAT 
	 * @param pseudo 
	 * @return
	 */
	public List<ArticleVendu> selectVenteTermineesByPseudo(String pseudo) throws DALException;
}

