package com.j3a.assurance.utilitaires.hybride;

import java.math.BigDecimal;

import com.j3a.assurance.model.Avenant;
import com.j3a.assurance.model.Contrat;
import com.j3a.assurance.model.Personne;
import com.j3a.assurance.model.Quittance;
import com.j3a.assurance.model.Reglement;

public class CompteClient {
	private Personne personne=new Personne();
	private Quittance quittance=new Quittance();
	private Reglement reglement=new Reglement();
	private Avenant avenant=new Avenant();
	private Contrat contrat=new Contrat();
	private BigDecimal solde=BigDecimal.ZERO;
	
	
	public Personne getPersonne() {
		return personne;
	}
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
	public Quittance getQuittance() {
		return quittance;
	}
	public void setQuittance(Quittance quittance) {
		this.quittance = quittance;
	}
	public Reglement getReglement() {
		return reglement;
	}
	public void setReglement(Reglement reglement) {
		this.reglement = reglement;
	}
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
	public BigDecimal getSolde() {
		return solde;
	}
	public void setSolde(BigDecimal solde) {
		this.solde = solde;
	}

}
