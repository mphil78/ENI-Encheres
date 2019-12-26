package fr.eni.eniencheres.dal;

import fr.eni.eniencheres.bo.Enchere;

public interface EnchereDAO {
	
	/**
	 * Insérer une nouvelle enchère
	 * @param enchere
	 * @throws DALException
	 */
	public void insert(Enchere enchere) throws DALException;
}
