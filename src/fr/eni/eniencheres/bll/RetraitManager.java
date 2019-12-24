package fr.eni.eniencheres.bll;

import fr.eni.eniencheres.bo.Retrait;
import fr.eni.eniencheres.dal.DALException;
import fr.eni.eniencheres.dal.DAOFactory;
import fr.eni.eniencheres.dal.RetraitDAO;

public class RetraitManager {

	private RetraitDAO retraitDAO;


	public RetraitManager() {
		this.retraitDAO=DAOFactory.getRetraitDAO();
	}
	
	public void addRetrait(Retrait retrait) {
		
		try {
			retraitDAO.insert(retrait);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addRetraitWIdArticle(Retrait retrait, int noArticle) {
		try {
			retraitDAO.insert(retrait, noArticle);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
