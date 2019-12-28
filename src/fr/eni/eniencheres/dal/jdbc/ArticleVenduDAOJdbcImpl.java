package fr.eni.eniencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.eniencheres.bo.ArticleVendu;
import fr.eni.eniencheres.dal.ArticleVenduDAO;
import fr.eni.eniencheres.dal.CategorieDAO;
import fr.eni.eniencheres.dal.DALException;
import fr.eni.eniencheres.dal.DAOFactory;
import fr.eni.eniencheres.dal.UtilisateurDAO;

//TODO vérifier les catch block
public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

	private static final String sqlSelectById =
			"select *" 
			+" from ARTICLES_VENDUS"
			+ " where no_article = ?";
	private static final String sqlSelectAll =
			"select *" 
			+" from ARTICLES_VENDUS";
	private static final String sqlSelectByCategorie =
			"select *" 
			+" from ARTICLES_VENDUS"
			+ " where no_categorie = ?";
	//TODO finir la requete et la corriger
	private static final String sqlSelectByFiltres =
			"select *" 
			+" from ARTICLES_VENDUS"
			+ " where no_categorie = ? and (description like \'%?%\' or nom_article like \'%?%\')";
	private static final String sqlInsert =
			"insert "
			+ "into ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial,prix_vente, no_vendeur, no_acheteur, no_categorie, etat_vente  )"
			+ " values(?,?,?,?,?,?,?,?,?,?)";

	@Override
	public ArticleVendu selectById(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		ArticleVendu articleVendu = null;
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlSelectById);
			rqt.setInt(1, id);
			rs = rqt.executeQuery();
			if (rs.next()){		
				articleVendu = new ArticleVendu(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(),
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						rs.getInt("etat_vente")
						);
			}
				UtilisateurDAO utilistateurDAO = DAOFactory.getUtilisateurDAO();
				CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
				articleVendu.setVendeur(utilistateurDAO.selectById(rs.getInt("no_vendeur")));
				articleVendu.setCategorie(categorieDAO.selectById(rs.getInt("no_categorie")));
				articleVendu.setAcheteur(utilistateurDAO.selectById(rs.getInt("no_acheteur")));		
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
		return null;
	}

	@Override
	public List<ArticleVendu> selectAll() throws DALException {
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<ArticleVendu> articles = new ArrayList<>();
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAll);
			while (rs.next()){		
				ArticleVendu articleVendu = new ArticleVendu(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(),
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						rs.getInt("etat_vente")
						);
				UtilisateurDAO utilistateurDAO = DAOFactory.getUtilisateurDAO();
				CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
				articleVendu.setVendeur(utilistateurDAO.selectById(rs.getInt("no_vendeur")));
				articleVendu.setAcheteur(utilistateurDAO.selectById(rs.getInt("no_acheteur")));
				articleVendu.setCategorie(categorieDAO.selectById(rs.getInt("no_categorie")));
				articles.add(articleVendu);
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
		return articles;
	}

	@Override
	public void insert(ArticleVendu article) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		java.sql.Date sqlDateDebut = java.sql.Date.valueOf(article.getDateDebutEncheres());  
		java.sql.Date sqlDateFin = java.sql.Date.valueOf(article.getDateFinEncheres());  
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, article.getNomArticle());
			rqt.setString(2, article.getDescription());
			rqt.setDate(3, sqlDateDebut);
			rqt.setDate(4, sqlDateFin);
			rqt.setInt(5, article.getMiseAPrix());
			rqt.setInt(6, article.getPrixVente());
			rqt.setInt(7, article.getVendeur().getNoUtilisateur());
			rqt.setInt(8, article.getAcheteur().getNoUtilisateur());
			rqt.setInt(9, article.getCategorie().getNoCategorie());
			rqt.setInt(10, article.getEtatVente());
			
			int nbRows = rqt.executeUpdate();
			if(nbRows == 1){
				ResultSet rs = rqt.getGeneratedKeys();
				if(rs.next()){
					article.setNoArticle(rs.getInt(1));
				}
			}
		}catch(SQLException e){
			System.out.println(e);
			throw new DALException("La creation de l'utilisateur a echoue - " + article.getNomArticle(), e);
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
	public void delete(ArticleVendu articleVendu) throws DALException {
		
	}

	@Override
	public void update(ArticleVendu articleVendu) throws DALException {
		
	}

	@Override
	public List<ArticleVendu> selectByMotCle(String motCle) throws DALException {
		return null;
	}
	
	@Override
	public List<ArticleVendu> selectByCategorie(int noCategorie) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<ArticleVendu> articles = new ArrayList<>();
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlSelectByCategorie);
			rqt.setInt(1, noCategorie);
			rs = rqt.executeQuery();
			if (rs.next()){		
				ArticleVendu articleVendu = new ArticleVendu(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(),
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						rs.getInt("etat_vente")
						);
				UtilisateurDAO utilistateurDAO = DAOFactory.getUtilisateurDAO();
				CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
				articleVendu.setVendeur(utilistateurDAO.selectById(rs.getInt("no_vendeur")));
				articleVendu.setAcheteur(utilistateurDAO.selectById(rs.getInt("no_acheteur")));
				articleVendu.setCategorie(categorieDAO.selectById(noCategorie));
				articles.add(articleVendu);
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
		return articles;
	}

	//TODO A tester
	@Override
	public List<ArticleVendu> selectByFiltres(String motCle, int noCategorie) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<ArticleVendu> articles = new ArrayList<>();
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlSelectByFiltres);
			rqt.setInt(1, noCategorie);
			rqt.setString(2, motCle);
			rqt.setString(3, motCle);
			rs = rqt.executeQuery();
			if (rs.next()){		
				ArticleVendu articleVendu = new ArticleVendu(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(),
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						rs.getInt("etat_vente")
						);
				UtilisateurDAO utilistateurDAO = DAOFactory.getUtilisateurDAO();
				CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
				articleVendu.setVendeur(utilistateurDAO.selectById(rs.getInt("no_vendeur")));
				articleVendu.setAcheteur(utilistateurDAO.selectById(rs.getInt("no_acheteur")));
				articleVendu.setCategorie(categorieDAO.selectById(noCategorie));
				articles.add(articleVendu);
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
		return articles;
	}	

}
