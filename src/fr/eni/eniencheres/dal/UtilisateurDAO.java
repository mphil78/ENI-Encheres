package fr.eni.eniencheres.dal;

import java.util.List;
import java.util.Map;

import fr.eni.eniencheres.bo.Utilisateur;

public interface UtilisateurDAO {
	
	/**
	 * Sélectionner tous les pseudos
	 * @return List<String>
	 * @throws DALException
	 */
	public List<String> selectAllPseudos() throws DALException;

	/**
	 * Sélectionner un utilisateur par son id
	 * @param id
	 * @return Utilisateur
	 * @throws DALException
	 */
	public Utilisateur selectById(int id) throws DALException;
	
	/**
	 * Sélectionner tous les utlisateurs
	 * @return List<Utilisateur>
	 * @throws DALException
	 */
	public List<Utilisateur> selectAll() throws DALException;
	
	/**
	 * Insérer un nouvel utilisateur
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
	 * Retourne un objet utilisateur en fonction du pseudo
	 * @param pseudo
	 * @return Utilisateur
	 * @throws DALException
	 */
	public Utilisateur selectByPseudo(String pseudo) throws DALException;

	
}
