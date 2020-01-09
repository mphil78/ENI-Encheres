package fr.eni.eniencheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eni.eniencheres.bo.ArticleVendu;
import fr.eni.eniencheres.bo.Utilisateur;
import fr.eni.eniencheres.dal.DALException;
import fr.eni.eniencheres.dal.UtilisateurDAO;


public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	private static final String sqlSelectById =
			"select *" 
			+" from UTILISATEURS"
			+ " where no_utilisateur = ?";
	private static final String sqlSelectByEmail =
			"select *" 
			+" from UTILISATEURS"
			+ " where email = ?";
	private static final String sqlSelectByPseudo =
			"select no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur" 
			+" from UTILISATEURS"
			+ " where pseudo = ?";
	private static final String sqlSelectAll =
			"select *"
			+ " from UTILISATEURS";
	private static final String sqlSelectAllPseudos =
			"select pseudo"
			+ " from UTILISATEURS";
	private static final String sqlInsert =
			"insert "
			+ "into UTILISATEURS(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur)"
			+ " values(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String sqlDelete =
			"delete from UTILISATEURS"
			+ " where no_utilisateur=?";
	private static final String sqlUpdate =
			"update UTILISATEURS "
			+ "set nom=?,prenom=?,email=?,telephone=?,rue=?,code_postal=?,ville=?,mot_de_passe=?,credit=?,administrateur=? where no_utilisateur=?";
	private static final String sqlSelectMeilleurEncherisseurByArticle =
			"select no_utilisateur "
			+ "from ENCHERES "
			+ "where no_article=? "
			+ "order by montant_enchere desc";
	private static final String sqlSelectGagnant =
			"select no_acheteur" 
			+" from ARTICLES_VENDUS"
			+ " where no_article=? and date_fin_encheres < GETDATE()";
	private static final String sqlIsMeilleurEncherisseurArticlesNonRetires =
			"select no_article" 
			+" from ARTICLES_VENDUS"
			+ " where no_acheteur=? and no_acheteur<>no_vendeur and etat_vente=0";
	private static final String sqlHaveArticlesEnVenteNonRetires =
			"select no_article" 
			+" from ARTICLES_VENDUS"
			+ " where no_vendeur=? and etat_vente=0";
	
	@Override
	public Utilisateur selectById(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Utilisateur utilisateur=null;

		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlSelectById);
			rqt.setInt(1, id);
			rs = rqt.executeQuery();
			if (rs.next()){
				utilisateur = new Utilisateur(
						rs.getInt("no_utilisateur"),
						rs.getString("pseudo"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("email"),
						rs.getString("telephone"),
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville"),
						rs.getString("mot_de_passe"),
						rs.getInt("credit"),
						rs.getByte("administrateur")==0?false:true
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
		return utilisateur;

	}
	
	@Override
	public List<Utilisateur> selectAll() throws DALException {
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<Utilisateur> utilisateurs = new ArrayList<>();
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAll);
			while (rs.next()){
				Utilisateur utilisateur = new Utilisateur(
												rs.getInt("no_utilisateur"),
												rs.getString("pseudo"),
												rs.getString("nom"),
												rs.getString("prenom"),
												rs.getString("email"),
												rs.getString("telephone"),
												rs.getString("rue"),
												rs.getString("code_postal"),
												rs.getString("ville"),
												rs.getString("mot_de_passe"),
												rs.getInt("credit"),
												rs.getByte("administrateur")==0?false:true
										       );
				utilisateurs.add(utilisateur);
			}
		} catch (SQLException e) {
			throw new DALException("selectAll failed - " , e);
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
		return utilisateurs;
	}
	
	@Override
	public void insert(Utilisateur utilisateur) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, utilisateur.getPseudo());
			rqt.setString(2, utilisateur.getNom());
			rqt.setString(3, utilisateur.getPrenom());
			rqt.setString(4, utilisateur.getEmail());
			rqt.setString(5, utilisateur.getTelephone());
			rqt.setString(6, utilisateur.getRue());
			rqt.setString(7, utilisateur.getCodePostal());
			rqt.setString(8, utilisateur.getVille());
			rqt.setString(9, utilisateur.getMotDePasse());
			rqt.setInt(10, utilisateur.getCredit());
			rqt.setByte(11, (byte) (utilisateur.isAdministrateur()==false?0:1));
			int nbRows = rqt.executeUpdate();
			if(nbRows == 1){
				ResultSet rs = rqt.getGeneratedKeys();
				if(rs.next()){
					utilisateur.setNoUtilisateur(rs.getInt(1));
				}
			}
		}catch(SQLException e){
			System.out.println(e);
			throw new DALException("La creation de l'utilisateur a echoue - " + utilisateur.getNom(), e);
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
	public void delete(Utilisateur utilisateur) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlDelete);
			rqt.setInt(1, utilisateur.getNoUtilisateur());
			rqt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("La suppression de l'utilisateur a échoué - " + utilisateur.getNom(), e);
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

	@Override
	public void update(Utilisateur utilisateur) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlUpdate);
			rqt.setString(1, utilisateur.getNom());
			rqt.setString(2, utilisateur.getPrenom());
			rqt.setString(3, utilisateur.getEmail());
			rqt.setString(4, utilisateur.getTelephone());
			rqt.setString(5, utilisateur.getRue());
			rqt.setString(6, utilisateur.getCodePostal());
			rqt.setString(7, utilisateur.getVille());
			rqt.setString(8, utilisateur.getMotDePasse());
			rqt.setInt(9, utilisateur.getCredit());
			rqt.setByte(10, (byte) (utilisateur.isAdministrateur()==false?0:1));
			rqt.setInt(11, utilisateur.getNoUtilisateur());
			rqt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("L'update de l'utilisateur a �chou� - " + utilisateur.getNom(), e);
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

	@Override
	public List<String> selectAllPseudos() throws DALException {
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<String> pseudosUtilisateurs = new ArrayList<>();
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAllPseudos);
			while (rs.next()){
				pseudosUtilisateurs.add(rs.getString("pseudo"));
			}
		} catch (SQLException e) {
			throw new DALException("selectAllPseudos failed - " , e);
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
		return pseudosUtilisateurs;
	}

	@Override
	public Map<String, String> selectAllIdentifiants() throws DALException {
		Map<String, String> identifiants =new HashMap<String, String>();
		List<Utilisateur> utilisateurs = this.selectAll();
		for (Utilisateur user : utilisateurs) {
			identifiants.put(user.getPseudo(), user.getMotDePasse());
		}
		return identifiants;
	}
	
	@Override
	public Map<String[], String> selectAllPseudoEmail() throws DALException {
		Map<String[], String> identifiants =new HashMap<>();
		List<Utilisateur> utilisateurs = this.selectAll();
		for (Utilisateur user : utilisateurs) {
			String[] pseudoEmail = new String[2];
			pseudoEmail[0]=user.getPseudo();
			pseudoEmail[1]=user.getEmail();
			identifiants.put(pseudoEmail, user.getMotDePasse());
		}
		return identifiants;
	}
	
	
	@Override
	public Utilisateur selectByPseudo(String pseudo) throws DALException{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Utilisateur utilisateur=new Utilisateur();
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlSelectByPseudo);
			rqt.setString(1, pseudo);
			rs = rqt.executeQuery();
			if (rs.next()){
				utilisateur = new Utilisateur(
						rs.getInt("no_utilisateur"),
						rs.getString("pseudo"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("email"),
						rs.getString("telephone"),
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville"),
						rs.getString("mot_de_passe"),
						rs.getInt("credit"),
						rs.getByte("administrateur")==(byte)0?false:true
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
		return utilisateur;

	}

	@Override
	public Map<String, String[]> selectAllIdentifiantsUniques() throws DALException {
		Map<String, String[]> identifiants =new HashMap<>();
		List<Utilisateur> utilisateurs = this.selectAll();
		for (Utilisateur user : utilisateurs) {
			String[] mdpEmail= new String[2];
			mdpEmail[0]=user.getMotDePasse();
			mdpEmail[1]=user.getEmail();
			identifiants.put(user.getPseudo(), mdpEmail);
		}
		return identifiants;
	}

	@Override
	public Utilisateur selectByEmail(String email) {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Utilisateur utilisateur=null;

		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlSelectByEmail);
			rqt.setString(1, email);
			rs = rqt.executeQuery();
			if (rs.next()){
				utilisateur = new Utilisateur(
						rs.getInt("no_utilisateur"),
						rs.getString("pseudo"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("email"),
						rs.getString("telephone"),
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville"),
						rs.getString("mot_de_passe"),
						rs.getInt("credit"),
						rs.getByte("administrateur")==0?false:true
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
		return utilisateur;

	}

	@Override
	public Utilisateur selectMeilleurEncherisseurByArticle(ArticleVendu article) throws DALException {
		Utilisateur meilleurEncherisseur = null;
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;

		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlSelectMeilleurEncherisseurByArticle);
			rqt.setInt(1, article.getNoArticle());
			rs = rqt.executeQuery();
			if (rs.next()){
				meilleurEncherisseur = this.selectById(rs.getInt("no_utilisateur"));
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

		return meilleurEncherisseur;
	}

	@Override
	public Utilisateur selectGagnant(ArticleVendu article) throws DALException {
		Utilisateur gagnant = null;
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;

		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlSelectGagnant);
			rqt.setInt(1, article.getNoArticle());
			rs = rqt.executeQuery();
			if (rs.next()){
				if (rs.getInt("no_acheteur")!=article.getVendeur().getNoUtilisateur()) {
					gagnant = this.selectById(rs.getInt("no_acheteur"));					
				}
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

		return gagnant;
	}

	@Override
	public boolean isActive(Utilisateur utilisateur) throws DALException {
		boolean actif=false;
		if (isMeilleurEncherisseurArticlesNonRetires(utilisateur) || haveArticlesEnVenteNonRetires(utilisateur)) {
			actif=true;
		}
		return actif;
	}

	@Override
	public boolean isMeilleurEncherisseurArticlesNonRetires(Utilisateur utilisateur) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		boolean resultat = false;
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlIsMeilleurEncherisseurArticlesNonRetires);
			rqt.setInt(1, utilisateur.getNoUtilisateur());
			rs = rqt.executeQuery();
			if (rs.next()){
				resultat = true;
			}
		} catch (SQLException e) {
			throw new DALException("selectAll failed - " , e);
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
		return resultat;
	}

	@Override
	public boolean haveArticlesEnVenteNonRetires(Utilisateur vendeur) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		boolean resultat = false;
		try {
			cnx = ConnectionProvider.getConnection();
			rqt = cnx.prepareStatement(sqlHaveArticlesEnVenteNonRetires);
			rqt.setInt(1, vendeur.getNoUtilisateur());
			rs = rqt.executeQuery();
			if (rs.next()){
				resultat = true;
			}
		} catch (SQLException e) {
			throw new DALException("selectAll failed - " , e);
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
		return resultat;
		}
	
	
}
