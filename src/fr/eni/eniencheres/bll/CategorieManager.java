package fr.eni.eniencheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.eniencheres.bo.Categorie;
import fr.eni.eniencheres.dal.CategorieDAO;
import fr.eni.eniencheres.dal.DALException;
import fr.eni.eniencheres.dal.DAOFactory;
import fr.eni.eniencheres.dal.UtilisateurDAO;

public class CategorieManager {

	private CategorieDAO categorieDAO;


	public CategorieManager() {
		this.categorieDAO=DAOFactory.getCategorieDAO();
	}
	
	public Categorie getByNom(String libelle) {
		Categorie categorie=null;
		try {
			categorie = categorieDAO.selectByNom(libelle);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categorie;
	}

	public List<String> getAllLibelles() {
		List<Categorie> listCategories = new ArrayList<>();
		List<String> listeLibelles = new ArrayList<>();
		try {
			listCategories = categorieDAO.selectAll();
			for (Categorie cat : listCategories) {
				listeLibelles.add(cat.getLibelle());
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listeLibelles;
	}
}
