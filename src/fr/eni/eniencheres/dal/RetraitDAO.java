package fr.eni.eniencheres.dal;

import fr.eni.eniencheres.bo.Retrait;

public interface RetraitDAO {
	//selectionner un retrait par l'id de l'article
	public Retrait selectByIdArticle(int id) throws DALException;
	
}
