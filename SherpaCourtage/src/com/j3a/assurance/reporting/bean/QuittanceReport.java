package com.j3a.assurance.reporting.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class QuittanceReport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String police;
	private String risque;
	private String numAvenant;
	private String nom;
	private String prenom;
	private String adresse;
	private String profession;
	private String mail;
	private String telephone;
	private String fax;
	private String intermediaire;
	private String categorie;
	private String mouvement;
	private String bareme;
	private String agence;
	private String idQuittance;
	private String typeAssurance;
	private int duree;
	private String numSouscripteur;
	private String nomSouscripteur;
	private Date dateEffet;
	private Date dateEcheance;
	private Date emission;
	private BigDecimal primeNette;
	private BigDecimal accessoires;
	private BigDecimal taxes;
    private BigDecimal netAPayer;
    private BigDecimal primeCedee;
	private BigDecimal acessoirgest;
	private BigDecimal accesoirCompagnie;
	private BigDecimal accesoirIntermediair;
	private BigDecimal comConseil;
	private BigDecimal comGestionnaire;
	private BigDecimal comAperition;
	private BigDecimal commission;
	private BigDecimal comInterm;
	

	public String getPolice() {
		return police;
	}

	public void setPolice(String police) {
		this.police = police;
	}

	public String getNumAvenant() {
		return numAvenant;
	}

	public void setNumAvenant(String numAvenant) {
		this.numAvenant = numAvenant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Date getDateEffet() {
		return dateEffet;
	}

	public void setDateEffet(Date dateEffet) {
		this.dateEffet = dateEffet;
	}

	public void setPrimeNette(BigDecimal primeNette) {
		this.primeNette = primeNette;
	}

	public void setAccessoires(BigDecimal accessoires) {
		this.accessoires = accessoires;
	}

	public void setTaxes(BigDecimal taxes) {
		this.taxes = taxes;
	}


	public Date getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(Date dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public BigDecimal getPrimeNette() {
		return primeNette;
	}

	public BigDecimal getAccessoires() {
		return accessoires;
	}

	public BigDecimal getTaxes() {
		return taxes;
	}

	public String getAgence() {
		return agence;
	}

	public void setAgence(String agence) {
		this.agence = agence;
	}

	public String getIdQuittance() {
		return idQuittance;
	}

	public void setIdQuittance(String idQuittance) {
		this.idQuittance = idQuittance;
	}

	public String getTypeAssurance() {
		return typeAssurance;
	}

	public void setTypeAssurance(String typeAssurance) {
		this.typeAssurance = typeAssurance;
	}

	public Date getEmission() {
		return emission;
	}

	public void setEmission(Date emission) {
		this.emission = emission;
	}

	public BigDecimal getAcessoirgest() {
		return acessoirgest;
	}

	public void setAcessoirgest(BigDecimal acessoirgest) {
		this.acessoirgest = acessoirgest;
	}

	public BigDecimal getAccesoirCompagnie() {
		return accesoirCompagnie;
	}

	public void setAccesoirCompagnie(BigDecimal accesoirCompagnie) {
		this.accesoirCompagnie = accesoirCompagnie;
	}

	public BigDecimal getAccesoirIntermediair() {
		return accesoirIntermediair;
	}

	public void setAccesoirIntermediair(BigDecimal accesoirIntermediair) {
		this.accesoirIntermediair = accesoirIntermediair;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getIntermediaire() {
		return intermediaire;
	}

	public void setIntermediaire(String intermediaire) {
		this.intermediaire = intermediaire;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getMouvement() {
		return mouvement;
	}

	public void setMouvement(String mouvement) {
		this.mouvement = mouvement;
	}

	public String getBareme() {
		return bareme;
	}

	public void setBareme(String bareme) {
		this.bareme = bareme;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public java.math.BigDecimal getComConseil() {
		return comConseil;
	}

	public void setComConseil(java.math.BigDecimal comConseil) {
		this.comConseil = comConseil;
	}

	public java.math.BigDecimal getComGestionnaire() {
		return comGestionnaire;
	}

	public void setComGestionnaire(java.math.BigDecimal comGestionnaire) {
		this.comGestionnaire = comGestionnaire;
	}

	public java.math.BigDecimal getComAperition() {
		return comAperition;
	}

	public void setComAperition(java.math.BigDecimal comAperition) {
		this.comAperition = comAperition;
	}

	public java.math.BigDecimal getNetAPayer() {
		return netAPayer;
	}

	public void setNetAPayer(java.math.BigDecimal netAPayer) {
		this.netAPayer = netAPayer;
	}

	public java.math.BigDecimal getPrimeCedee() {
		return primeCedee;
	}

	public void setPrimeCedee(java.math.BigDecimal primeCedee) {
		this.primeCedee = primeCedee;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	

	public java.math.BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(java.math.BigDecimal commission) {
		this.commission = commission;
	}

	public java.math.BigDecimal getComInterm() {
		return comInterm;
	}

	public void setComInterm(java.math.BigDecimal comInterm) {
		this.comInterm = comInterm;
	}

	public String getRisque() {
		return risque;
	}

	public void setRisque(String risque) {
		this.risque = risque;
	}


	public String getNumSouscripteur() {
		return numSouscripteur;
	}

	public void setNumSouscripteur(String numSouscripteur) {
		this.numSouscripteur = numSouscripteur;
	}


	public String getNomSouscripteur() {
		return nomSouscripteur;
	}

	public void setNomSouscripteur(String nomSouscripteur) {
		this.nomSouscripteur = nomSouscripteur;
	}

	
}
