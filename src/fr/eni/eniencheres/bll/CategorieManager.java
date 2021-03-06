package fr.eni.eniencheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.eniencheres.bo.Categorie;
import fr.eni.eniencheres.dal.CategorieDAO;
import fr.eni.eniencheres.dal.DALException;
import fr.eni.eniencheres.dal.DAOFactory;

//TODO vérifier les catch block

public class CategorieManager {

	private CategorieDAO categorieDAO;

	/**
	 * constructeur de CategorieManager
	 */
	public CategorieManager() {
		this.categorieDAO=DAOFactory.getCategorieDAO();
	}
	
	/**
	 * renvoie l'objet Categorie par son libelle
	 * @param libelle
	 * @return Categorie
	 */
	public Categorie getByNom(String libelle) {
		Categorie categorie=null;
		try {
			categorie = categorieDAO.selectByNom(libelle);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return categorie;
	}

	
	/**
	 * renvoie une liste de tous les libelles
	 * @return List<String>
	 */
	public List<String> getAllLibelles() {
		List<Categorie> listCategories = new ArrayList<>();
		List<String> listeLibelles = new ArrayList<>();
		try {
			listCategories = categorieDAO.selectAll();
			for (Categorie cat : listCategories) {
				listeLibelles.add(cat.getLibelle());
			}
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		return listeLibelles;
	}
}
