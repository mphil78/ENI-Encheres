package fr.eni.eniencheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.eniencheres.bo.Categorie;
import fr.eni.eniencheres.dal.CategorieDAO;
import fr.eni.eniencheres.dal.DALException;
import fr.eni.eniencheres.dal.DAOFactory;

public class CategorieManager {

	public Categorie getByNom(String libelle) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getAllLibelles() {
		CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
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
