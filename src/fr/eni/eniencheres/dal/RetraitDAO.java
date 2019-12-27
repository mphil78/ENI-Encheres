package fr.eni.eniencheres.dal;

import fr.eni.eniencheres.bo.Retrait;

public interface RetraitDAO {

	/**
	 * selectionner un retrait par l'id de l'article
	 * @param id
	 * @return Retrait
	 * @throws DALException
	 */
	public Retrait selectByIdArticle(int id) throws DALException;

	/**
	 * insert un nouveau retrait
	 * @param retrait
	 * @throws DALException
	 */
	public void insert(Retrait retrait) throws DALException;
	
	/**
	 * insert un nouveau retrait en indiquant l'id de l'article 
	 * @param retrait
	 * @param idArticle
	 * @throws DALException
	 */	
	public void insert(Retrait retrait, int idArticle) throws DALException;

	
}
