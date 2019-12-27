package fr.eni.eniencheres.dal;

import fr.eni.eniencheres.bo.Enchere;

public interface EnchereDAO {
	
	/**
	 * Inserer une nouvelle enchere
	 * @param enchere
	 * @throws DALException
	 */
	public void insert(Enchere enchere) throws DALException;
}
