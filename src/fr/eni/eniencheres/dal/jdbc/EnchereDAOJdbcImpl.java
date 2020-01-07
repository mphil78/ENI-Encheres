package fr.eni.eniencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.eniencheres.bo.Enchere;
import fr.eni.eniencheres.dal.DALException;
import fr.eni.eniencheres.dal.EnchereDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	private static final String sqlInsert =
			"insert "
			+ "into ENCHERES(no_utilisateur, no_article, date_enchere, montant_enchere)"
			+ " values(?,?,?,?)";
	
	@Override
	public void insert(Enchere enchere) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		java.sql.Date sqlDateEnchere = java.sql.Date.valueOf(enchere.getDateEnchere());  
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			rqt.setInt(1, enchere.getActicleVendu().getVendeur().getNoUtilisateur());
			rqt.setInt(2, enchere.getActicleVendu().getNoArticle());
			rqt.setDate(3, sqlDateEnchere);
			rqt.setInt(4, enchere.getMontantEnchere());
			rqt.executeUpdate();
		}catch(SQLException e){
			System.out.println(e);
			throw new DALException("La creation de l'enchere a echoue - " + enchere.getEncherisseur() + enchere.getDateEnchere(), e);
		}
		finally {
			try {
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				throw new DALException("La fermeture de la connexion a echoue - ", e);
			}

		}		
	}

}
