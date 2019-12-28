package fr.eni.eniencheres.servlets.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.eniencheres.bo.Categorie;
import fr.eni.eniencheres.bo.Retrait;
import fr.eni.eniencheres.bo.Utilisateur;
import fr.eni.eniencheres.dal.CategorieDAO;
import fr.eni.eniencheres.dal.DALException;
import fr.eni.eniencheres.dal.DAOFactory;
import fr.eni.eniencheres.dal.RetraitDAO;
import fr.eni.eniencheres.dal.UtilisateurDAO;
import fr.eni.eniencheres.dal.jdbc.CategorieDAOJdbcImpl;

/**
 * Servlet implementation class ServletTestDAL
 */
@WebServlet("/ServletTestDAL")
public class ServletTestDAL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTestDAL() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();
		RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
		//Instanciation du jeu d'essai 
//			Utilisateur u1 = new Utilisateur(
//					"titi", 
//					"DELAFONTAINE", 
//					"Robert", 
//					"tito@gmail.com", 
//					"0606060606", 
//					"12 rue du Corbeau et du Renard", 
//					"44444",
//					"TDCDM",
//					"motdepasse", 
//					600, 
//					false);
//			Utilisateur u2 = new Utilisateur(
//					"toto", 
//					"DELAFONTAINE", 
//					"Robert", 
//					"tito@gmail.com", 
//					"0606060606", 
//					"12 rue du Corbeau et du Renard", 
//					"44444",
//					"TDCDM",
//					"motdepasse", 
//					600, 
//					false);
//			Utilisateur u3 = new Utilisateur(
//					"tutu", 
//					"DELAFONTAINE", 
//					"Robert", 
//					"tito@gmail.com", 
//					"0606060606", 
//					"12 rue du Corbeau et du Renard", 
//					"44444",
//					"TDCDM",
//					"motdepasse", 
//					600, 
//					false);

//			Categorie c1 = new Categorie("Informatique");
//			Categorie c2 = new Categorie("Ameublement");
//			Categorie c3= new Categorie("V�tement");
//			Categorie c4 = new Categorie("Sport&Loisir");
			Retrait retrait = new Retrait("7 chemin des ragots", "49320", "St Jean des Mauvrets");

			try {
				retraitDAO.insert(retrait, 3);
//				categorieDAO.insert(c1);
//				categorieDAO.insert(c2);
//				categorieDAO.insert(c3);
//				categorieDAO.insert(c4);

//				utilisateurDAO.insert(u1);
//				utilisateurDAO.insert(u2);
//				utilisateurDAO.insert(u3);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
