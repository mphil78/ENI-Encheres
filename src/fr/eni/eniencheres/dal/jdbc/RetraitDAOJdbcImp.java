package fr.eni.eniencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.eniencheres.bo.Retrait;
import fr.eni.eniencheres.dal.DALException;
import fr.eni.eniencheres.dal.RetraitDAO;

public class RetraitDAOJdbcImp implements RetraitDAO {
	
	private static final String sqlSelectById =
			"select *" 
			+" from RETRAITS"
			+ " where no_article = ?";
	private static final String sqlInsert =
			"insert "
			+ "into RETRAITS(rue,code_postal,ville,no_article)"
			+ " values(?,?,?,?)";
	
	@Override
	public Retrait selectByIdArticle(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Retrait retrait = null; 
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlSelectById);
			rqt.setInt(1, id);
			rs = rqt.executeQuery();
			if (rs.next()){
				retrait = new Retrait(
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville")
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
		return retrait;


	}

	@Override
	public void insert(Retrait retrait) throws DALException {
		// TODO Auto-generated method stub
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlInsert);
			rqt.setString(1, retrait.getRue());
			rqt.setString(2, retrait.getCode_postal());
			rqt.setString(3, retrait.getVille());
			rqt.setInt(4, retrait.getArticleVendu().getNoArticle());
			rqt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
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
	public void insert(Retrait retrait, int idArticle) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlInsert);
			rqt.setString(1, retrait.getRue());
			rqt.setString(2, retrait.getCode_postal());
			rqt.setString(3, retrait.getVille());
			rqt.setInt(4, idArticle);
			rqt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
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
