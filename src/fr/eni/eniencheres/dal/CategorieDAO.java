package fr.eni.eniencheres.dal;

import java.util.List;

import fr.eni.eniencheres.bo.Categorie;

public interface CategorieDAO {

	//Sélectionner une categorie par son id pour article
	public Categorie selectByIdArticle(int id) throws DALException;
	
	//Sélectionner une categorie par son id pour article
	public Categorie selectByNom(String nom) throws DALException;
	
	//Sélectionner une categorie par son id pour article
	public void insert(Categorie categorie) throws DALException;
	
	//Sélectionner toutes les catégories
	public List<Categorie> selectAll() throws DALException;
	
}
