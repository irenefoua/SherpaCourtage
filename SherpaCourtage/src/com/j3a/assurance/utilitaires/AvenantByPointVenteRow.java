package com.j3a.assurance.utilitaires;

import java.math.BigDecimal;

import com.j3a.assurance.model.Apporteur;
import com.j3a.assurance.model.Avenant;
import com.j3a.assurance.model.Contrat;
import com.j3a.assurance.model.Exercice;
import com.j3a.assurance.model.Personne;
import com.j3a.assurance.model.PointVente;
import com.j3a.assurance.model.Quittance;

public class AvenantByPointVenteRow {

	public AvenantByPointVenteRow() {
		// TODO Auto-generated constructor stub
	}


	private Avenant avenant;
	private Contrat contrat;
	private PointVente ptVte;
	private Quittance quittance;
	private Personne client;
	private String nomRsClient;
	private String prenomClient;
	private Exercice exercice;
	private Apporteur apporteur;
	private String coAp,libelle;
	private BigDecimal chiffreAff=BigDecimal.ZERO;
	
	
	public Avenant getAvenant() {
		return avenant;
	}
	public void setAvenant(Avenant avenant) {
		this.avenant = avenant;
	}
	public Contrat getContrat() {
		return contrat;
	}
	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}
	public PointVente getPtVte() {
		return ptVte;
	}
	public void setPtVte(PointVente ptVte) {
		this.ptVte = ptVte;
	}
	public Quittance getQuittance() {
		return quittance;
	}
	public void setQuittance(Quittance quittance) {
		this.quittance = quittance;
	}
	
	
	
	public Exercice getExercice() {
		return exercice;
	}
	public void setExercice(Exercice exercice) {
		this.exercice = exercice;
	}
	public Personne getClient() {
			
		return client;
	}
	public void setClient(Personne client) {
		this.client = client;
	}
	public String getNomRsClient() {
		if(getClient().getMorale() != null){
			nomRsClient = getClient().getNomRaisonSociale();	
				}
		if(getClient().getPhysique() != null){
			nomRsClient = getClient().getPhysique().getNomRaisonSociale();	
				}
		return nomRsClient;
	}
	public void setNomRsClient(String nomRsClient) {
		this.nomRsClient = nomRsClient;
	}
	public String getPrenomClient() {
		if(getClient().getMorale() != null){
			prenomClient = "";	
				}
		if(getClient().getPhysique() != null){
			prenomClient = getClient().getPhysique().getPrenomPers();	
				}
		
		return prenomClient;
	}
	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}
	public Apporteur getApporteur() {
		return apporteur;
	}
	public void setApporteur(Apporteur apporteur) {
		this.apporteur = apporteur;
	}
	public BigDecimal getChiffreAff() {
		return chiffreAff;
	}
	public void setChiffreAff(BigDecimal chiffreAff) {
		this.chiffreAff = chiffreAff;
	}
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getCoAp() {
		return coAp;
	}
	public void setCoAp(String coAp) {
		this.coAp = coAp;
	}
	
	
	
	
	
}
