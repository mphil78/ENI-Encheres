package fr.eni.eniencheres.dal;

import java.util.List;
import java.util.Map;

import fr.eni.eniencheres.bo.ArticleVendu;
import fr.eni.eniencheres.bo.Utilisateur;

public interface UtilisateurDAO {
	
	/**
	 * Selectionner tous les pseudos
	 * @return List<String>
	 * @throws DALException
	 */
	public List<String> selectAllPseudos() throws DALException;


	/**
	 * Selectionner un utilisateur par son id
	 * @param id
	 * @return Utilisateur
	 * @throws DALException
	 */	
	public Utilisateur selectById(int id) throws DALException;
	
	
	/**
	 * Selectionner tous les utlisateurs
	 * @return List<Utilisateur>
	 * @throws DALException
	 */
	public List<Utilisateur> selectAll() throws DALException;
	
	/**
	 * Inserer un nouvel utilisateur
	 * @param utilisateur
	 * @throws DALException
	 */
	public void insert(Utilisateur utilisateur) throws DALException;
	
	/**
	 * Supprimer un utilisateur
	 * @param utilisateur
	 * @throws DALException
	 */
	public void delete(Utilisateur utilisateur) throws DALException;
	
	/**
	 * Modifier un utilisateur
	 * @param utilisateur
	 * @throws DALException
	 */
	public void update(Utilisateur utilisateur) throws DALException;
	
	/**
	 * Retourne une HashMap de tous les <pseudo, mdp>
	 * @return
	 * @throws DALException
	 */
	public Map<String, String> selectAllIdentifiants() throws DALException;

	/**
	 * Retourne une HashMap de tous les <pseudo,[mdp,email]>
	 * @return
	 * @throws DALException
	 */
	public Map<String, String[]> selectAllIdentifiantsUniques() throws DALException;
	
	/**
	 * Retourne un objet utilisateur en fonction du pseudo
	 * @param pseudo
	 * @return Utilisateur
	 * @throws DALException
	 */
	public Utilisateur selectByPseudo(String pseudo) throws DALException;

	/**
	 * Retourne une HashMap de tous les <[pseudo,email], mdp>
	 * @return
	 * @throws DALException
	 */
	Map<String[], String> selectAllPseudoEmail() throws DALException;

	
	/**
	 * Retourne un objet utilisateur en fonction de son email
	 * @param email
	 * @return
	 */
	public Utilisateur selectByEmail(String email) throws DALException;

	/**
	 * Retourne le meilleur encherisseur d'un article
	 * @param email
	 * @return
	 */
	public Utilisateur selectMeilleurEncherisseurByArticle(ArticleVendu article) throws DALException;


	/**
	 * Retourne l'acquereur d'un article s'il existe, null sinon
	 * @param email
	 * @return
	 */
	public Utilisateur selectGagnant(ArticleVendu article) throws DALException;

	/**
	 * Retourne true si l'utilisateur est encore impliqué dans une enchere, false sinon
	 * @param utilisateur
	 * @return
	 */
	public boolean isActive(Utilisateur utilisateur) throws DALException;
	
	/**
	 * Envoie la liste des meilleurs enchérisseurs d'articles non retirés.
	 * @return
	 * @throws DALException
	 */
	public boolean isMeilleurEncherisseurArticlesNonRetires(Utilisateur utilisateur) throws DALException;
	
	/**
	 * Liste les articles d'un vendeur qui ne son pas retirés
	 * @param vendeur
	 * @return
	 * @throws DALException
	 */
	public boolean haveArticlesEnVenteNonRetires(Utilisateur vendeur) throws DALException;
}
