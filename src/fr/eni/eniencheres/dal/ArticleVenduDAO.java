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
	 * Retourne la liste des articles vendus dans l'état CREEE par un utilisateur en fonction de son pseudo
	 * @param pseudo
	 * @return
	 * @throws DALException
	 */
	public List<ArticleVendu> selectVenteCreeeByPseudo(String pseudo) throws DALException;

	/**
	 * Retourne la liste des articles vendus dans l'état ENCOURS par un utilisateur en fonction de son pseudo
	 * @param pseudo
	 * @return
	 * @throws DALException
	 */
	public List<ArticleVendu> selectVenteEnCoursByPseudo(String pseudo) throws DALException;

	/**
	 * Retourne la liste des articles vendus dans l'état TERMINEE par un utilisateur en fonction de son pseudo
	 * @param pseudo
	 * @return
	 * @throws DALException
	 */
	public List<ArticleVendu> selectVenteTermineeByPseudo(String pseudo) throws DALException;
}

