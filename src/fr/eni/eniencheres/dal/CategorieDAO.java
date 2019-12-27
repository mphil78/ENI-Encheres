package fr.eni.eniencheres.dal;

import java.util.List;

import fr.eni.eniencheres.bo.Categorie;

public interface CategorieDAO {

	/**
	 * Selectionner une categorie par son id pour article
	 * @param id
	 * @return Categorie
	 * @throws DALException
	 */	public Categorie selectById(int id) throws DALException;
	
	/**
	 * Selectionner une categorie par son id pour article
	 * @param nom
	 * @return Categorie
	 * @throws DALException
	 */
	 public Categorie selectByNom(String nom) throws DALException;
	
	/**
	 * Selectionner une categorie par son id pour article
	 * @param categorie
	 * @throws DALException
	 */
	 public void insert(Categorie categorie) throws DALException;
	
	/**
	 * Selectionner toutes les categories
	 * @return List<Categorie>
	 * @throws DALException
	 */
	 public List<Categorie> selectAll() throws DALException;
	
}
