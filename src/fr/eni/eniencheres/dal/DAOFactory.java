package fr.eni.eniencheres.dal;

public class DAOFactory {
	public static UtilisateurDAO getUtilisateurDAO() {
		return new fr.eni.eniencheres.dal.jdbc.UtilisateurDAOJdbcImpl();
	}

	public static CategorieDAO getCategorieDAO() {
		return new fr.eni.eniencheres.dal.jdbc.CategorieDAOJdbcImpl();
	}

	public static RetraitDAO getRetraitDAO() {
		return new fr.eni.eniencheres.dal.jdbc.RetraitDAOJdbcImp();
	}

	public static ArticleVenduDAO getArticleDAO() {
		return new fr.eni.eniencheres.dal.jdbc.ArticleVenduDAOJdbcImpl();
	}

}
