package fr.eni.eniencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.eniencheres.bo.Categorie;
import fr.eni.eniencheres.dal.CategorieDAO;
import fr.eni.eniencheres.dal.DALException;

public class CategorieDAOJdbcImpl implements CategorieDAO {

	private static final String sqlSelectById =
			"select *" 
			+" from CATEGORIES"
			+ " where no_categorie = ?";
	
	private static final String sqlSelectByNom =
			"select *" 
			+" from CATEGORIES"
			+ " where libelle = ?";
	private static final String sqlSelectAll =
			"select *" 
			+" from CATEGORIES";
	
	private static final String sqlInsert =
			"insert "
			+ "into CATEGORIES(libelle)"
			+ " values(?)";
	
	@Override
	public Categorie selectById(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Categorie categorie=null;
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlSelectById);
			rqt.setInt(1, id);
			rs = rqt.executeQuery();
			if (rs.next()){
				categorie = new Categorie(
						rs.getInt("no_categorie"),
						rs.getString("libelle")
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
		return categorie;


	}


	@Override
	public Categorie selectByNom(String nom) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Categorie categorie=null;
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlSelectByNom);
			rqt.setString(1, nom);
			rs = rqt.executeQuery();
			if (rs.next()){
				categorie = new Categorie(
						rs.getInt("no_categorie"),
						rs.getString("libelle")
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
		return categorie;


		
	}


	@Override
	public void insert(Categorie categorie) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, categorie.getLibelle());
			
			int nbRows = rqt.executeUpdate();
			if(nbRows == 1){
				ResultSet rs = rqt.getGeneratedKeys();
				if(rs.next()){
					categorie.setNoCategorie(rs.getInt(1));
				}
			}
		}catch(SQLException e){
			System.out.println(e);
			throw new DALException("La Création de la catégorie a échoué - " + categorie.getLibelle(), e);
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
				throw new DALException("La fermeture de la connexion a échoué - ", e);
			}

		}		
	}


	@Override
	public List<Categorie> selectAll() throws DALException {
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<Categorie> categories = new ArrayList<>();
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAll);
			while (rs.next()){
				Categorie categorie = new Categorie(
												rs.getInt("no_categorie"),
												rs.getString("libelle")
												);

				categories.add(categorie);
			}
		} catch (SQLException e) {
			throw new DALException("selectAll a écouché - " , e);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return categories;
	}

}
