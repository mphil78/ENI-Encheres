package fr.eni.eniencheres.dal;

import fr.eni.eniencheres.bo.Enchere;

public interface EnchereDAO {
	
	/**
	 * Inserer une nouvelle enchere
	 * @param enchere
	 * @throws DALException
	 */
	public void insert(Enchere enchere) throws DALException;
	
	/**
	 * select enchere
	 * @param enchere
	 * @throws DALException
	 */
	public Object select(Enchere enchere) throws DALException;

	/**
	 * update enchere
	 * @param enchere
	 * @throws DALException
	 */
	public void update(Enchere enchere) throws DALException;
}
