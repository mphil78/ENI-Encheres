package fr.eni.eniencheres.dal;

import java.util.List;

import fr.eni.eniencheres.bo.ArticleVendu;



public interface ArticleVenduDAO {
	
		/**
		 * Sélectionner un article par son id
		 * @param id
		 * @return ArticleVendu
		 * @throws DALException
		 */
		public ArticleVendu selectById(int id) throws DALException;
		
		/**
		 * Sélectionner tous les articles
		 * @return List<ArticleVendu>
		 * @throws DALException
		 */
		public List<ArticleVendu> selectAll() throws DALException;
		
		/**
		 * Insérer un nouvel article
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
		 * Sélectionner les articles par mot clé
		 * On recherche le mot clé dans le nom et la description
		 * @param motCle
		 * @return List<ArticleVendu>
		 * @throws DALException
		 */
		public List<ArticleVendu> selectByMotCle(String motCle) throws DALException;
		
		/**
		 * Sélectionner les articles par catégorie
		 * @param noCategorie
		 * @return List<ArticleVendu>
		 * @throws DALException
		 */
		public List<ArticleVendu> selectByCategorie(int noCategorie) throws DALException;
		
		/**
		 * Selectionner les articles par mot clé(nom et description) ET catégorie
		 * @param motCle
		 * @param noCategorie
		 * @return List<ArticleVendu>
		 * @throws DALException
		 */
		public List<ArticleVendu> selectByFiltres(String motCle, int noCategorie) throws DALException;
}

