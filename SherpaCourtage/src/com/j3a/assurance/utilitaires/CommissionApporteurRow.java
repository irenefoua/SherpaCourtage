package com.j3a.assurance.utilitaires;

import java.math.BigDecimal;
import java.util.Date;

import com.j3a.assurance.model.Apporteur;
import com.j3a.assurance.model.ApporteurVehicule;

public class CommissionApporteurRow {
	
	private Date dateAssure;
	private String elementAssure;
	private ApporteurVehicule apporteurVehicule=new ApporteurVehicule();
	private BigDecimal tauxApporteur=BigDecimal.ZERO;
	private BigDecimal montantCommission=BigDecimal.ZERO;
	private Apporteur apporteur=new Apporteur();
	public BigDecimal getTauxApporteur() {
		return tauxApporteur;
	}
	public void setTauxApporteur(BigDecimal tauxApporteur) {
		this.tauxApporteur = tauxApporteur;
	}
	public BigDecimal getMontantCommission() {
		return montantCommission;
	}
	public void setMontantCommission(BigDecimal montantCommission) {
		this.montantCommission = montantCommission;
	}
	public String getElementAssure() {
		return elementAssure;
	}
	public void setElementAssure(String elementAssure) {
		this.elementAssure = elementAssure;
	}
	public Date getDateAssure() {
		return dateAssure;
	}
	public void setDateAssure(Date dateAssure) {
		this.dateAssure = dateAssure;
	}
	public ApporteurVehicule getApporteurVehicule() {
		return apporteurVehicule;
	}
	public void setApporteurVehicule(ApporteurVehicule apporteurVehicule) {
		this.apporteurVehicule = apporteurVehicule;
	}
	public Apporteur getApporteur() {
		return apporteur;
	}
	public void setApporteur(Apporteur apporteur) {
		this.apporteur = apporteur;
	}
	
	

}
