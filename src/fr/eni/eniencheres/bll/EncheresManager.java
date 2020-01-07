package fr.eni.eniencheres.bll;

import fr.eni.eniencheres.bo.Enchere;
import fr.eni.eniencheres.dal.DALException;
import fr.eni.eniencheres.dal.EnchereDAO;
import fr.eni.eniencheres.dal.RetraitDAO;


public class EncheresManager {

	
	private EnchereDAO enchereDAO;
	public final static int PADDING = 10;

	public void addEnchere(Enchere enchere) {
		
		try {
			enchereDAO.insert(enchere);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
}
