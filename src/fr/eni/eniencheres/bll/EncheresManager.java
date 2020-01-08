package fr.eni.eniencheres.bll;

import fr.eni.eniencheres.bo.Enchere;
import fr.eni.eniencheres.dal.DALException;
import fr.eni.eniencheres.dal.DAOFactory;
import fr.eni.eniencheres.dal.EnchereDAO;
import fr.eni.eniencheres.dal.RetraitDAO;

public class EncheresManager {

	private EnchereDAO enchereDAO;

	/**
	 * Constructeur de ArticleManager
	 */
	public EncheresManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
	}

	/**
	 * Ajoute l'enchere à la BDD
	 * 
	 * @param enchere
	 */
	public void addEnchere(Enchere enchere) {
		try {
			if (enchereDAO.select(enchere) == null) {
				enchereDAO.insert(enchere);
			} else {
				enchereDAO.update(enchere);
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
