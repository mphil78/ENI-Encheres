package fr.eni.eniencheres.bo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Enchere {
	
	//Attributs d'instance
	private LocalDate dateEnchere;
	private int montantEnchere;
	private Utilisateur encherisseur;
	private ArticleVendu acticleVendu;
	
	
	//Getters et setters
	public LocalDate getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public int getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(int montant_enchere) {
		this.montantEnchere = montant_enchere;
	}
	public Utilisateur getEncherisseur() {
		return encherisseur;
	}
	public void setEncherisseur(Utilisateur encherisseur) {
		this.encherisseur = encherisseur;
	}
	public ArticleVendu getActicleVendu() {
		return acticleVendu;
	}
	public void setActicleVendu(ArticleVendu acticleVendu) {
		this.acticleVendu = acticleVendu;
	}
	
	//constructeurs
	public Enchere(LocalDate dateEnchere, int montant_enchere, Utilisateur encherisseur, ArticleVendu acticleVendu) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montant_enchere;
		this.encherisseur = encherisseur;
		this.acticleVendu = acticleVendu;
	}
	public Enchere() {
		super();
		
	}

	//override toString
	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montant_enchere=" + montantEnchere + ", encherisseur="
				+ encherisseur.getPseudo() + ", acticleVendu=" + acticleVendu.getNomArticle() + "]";
	}
	
	
}
