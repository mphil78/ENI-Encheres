package fr.eni.eniencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import fr.eni.eniencheres.bo.Categorie;
import fr.eni.eniencheres.bo.Enchere;
import fr.eni.eniencheres.dal.ArticleVenduDAO;
import fr.eni.eniencheres.dal.DALException;
import fr.eni.eniencheres.dal.DAOFactory;
import fr.eni.eniencheres.dal.EnchereDAO;
import fr.eni.eniencheres.dal.UtilisateurDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	private static final String sqlInsert =
			"insert "
			+ "into ENCHERES(no_utilisateur, no_article, date_enchere, montant_enchere)"
			+ " values(?,?,?,?)";
	private static final String sqlSelect =
			"select *" 
			+" from ENCHERES"
			+ " where no_utilisateur = ? and no_article=?";
	private static final String sqlUpdate =
			"update ENCHERES "
			+ "set date_enchere=?, montant_enchere=? where no_utilisateur=? and no_article=?";

	
	
	@Override
	public void insert(Enchere enchere) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		java.sql.Date sqlDateEnchere = java.sql.Date.valueOf(enchere.getDateEnchere());  
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlInsert);
			rqt.setInt(1, enchere.getActicleVendu().getAcheteur().getNoUtilisateur());
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

	@Override
	public Object select(Enchere enchere) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Enchere enchereBdd=null;
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlSelect);
			rqt.setInt(1, enchere.getEncherisseur().getNoUtilisateur());
			rqt.setInt(2, enchere.getActicleVendu().getNoArticle());
			rs = rqt.executeQuery();
			if (rs.next()){
				ArticleVenduDAO articleDAO = DAOFactory.getArticleDAO();
				UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
				enchereBdd = new Enchere(
						rs.getDate("date_enchere").toLocalDate(),
						rs.getInt("montant_enchere"),
						utilisateurDAO.selectById(rs.getInt("no_utilisateur")),
						articleDAO.selectById(rs.getInt("no_article"))
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return enchereBdd;
	}

	@Override
	public void update(Enchere enchere) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlUpdate);
			rqt.setDate(1, java.sql.Date.valueOf(enchere.getDateEnchere()));
			rqt.setInt(2, enchere.getMontantEnchere());
			rqt.setInt(3, enchere.getEncherisseur().getNoUtilisateur());
			rqt.setInt(4, enchere.getActicleVendu().getNoArticle());

			rqt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("L'update de l'enchere a échoué - " + enchere.toString(), e);
		} finally {
			try {
				if (rqt != null){
					rqt.close();
				}
				if(cnx !=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}	
	}

}
