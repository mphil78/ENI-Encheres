package fr.eni.eniencheres.bll;

import fr.eni.eniencheres.bo.Retrait;
import fr.eni.eniencheres.dal.DALException;
import fr.eni.eniencheres.dal.DAOFactory;
import fr.eni.eniencheres.dal.RetraitDAO;

public class RetraitManager {

	private RetraitDAO retraitDAO;

	//constructeur
	public RetraitManager() {
		this.retraitDAO=DAOFactory.getRetraitDAO();
	}
	
	//ajoute un retrait dans la base de donnees
	public void addRetrait(Retrait retrait) {
		
		try {
			retraitDAO.insert(retrait);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//ajoute un retrait dans la base de donnees en précisant le numéro de l'article
	public void addRetraitWIdArticle(Retrait retrait, int noArticle) {
		try {
			retraitDAO.insert(retrait, noArticle);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
