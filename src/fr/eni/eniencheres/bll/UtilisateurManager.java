package fr.eni.eniencheres.bll;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import fr.eni.eniencheres.bo.Utilisateur;
import fr.eni.eniencheres.dal.DALException;
import fr.eni.eniencheres.dal.DAOFactory;
import fr.eni.eniencheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	
	private UtilisateurDAO utilisateurDAO;
	
	public UtilisateurManager() {
		this.utilisateurDAO=DAOFactory.getUtilisateurDAO();
	}
	
	public Utilisateur getByPseudo(String pseudo) {
		Utilisateur utilisateur;
		utilisateur=utilisateurDAO.selectByPseudo(pseudo);
		return utilisateur;
	}
	
	public Map<String, String> getAllIdentifiants(){
		Map<String, String> allIdentifiants = new HashMap<String, String>();
		try {
			allIdentifiants = utilisateurDAO.selectAllIdentifiants();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allIdentifiants;
		
	}
	public void addUtilisateur(Utilisateur utilisateur) {
		try {
			utilisateurDAO.insert(utilisateur);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateUtilisateur(Utilisateur utilisateur) {
		try {
			utilisateurDAO.update(utilisateur);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(Utilisateur utilisateur) {
		try {
			utilisateurDAO.delete(utilisateur);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Copy past from https://www.mkyong.com/java/java-sha-hashing-example/
	 * @param motDePasse
	 * @return
	 */
	static String hash(String motDePasse) {
		String encoded = null;
		try {
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        byte[] hashInBytes = md.digest(motDePasse.getBytes(StandardCharsets.UTF_8));

			// bytes to hex
	        StringBuilder sb = new StringBuilder();
	        for (byte b : hashInBytes) {
	            sb.append(String.format("%02x", b));
	        }
	        encoded = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encoded;

		
	}

}

