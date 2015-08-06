package com.j3a.assurance.utilitaires;
import java.util.List;

import com.j3a.assurance.model.GarantieChoisie;
import com.j3a.assurance.model.GarantieGarantieChoisie;
import com.j3a.assurance.model.GarantieGarantieChoisieId;

public class ListGarantieparVehicule {
	/** 
	 * Classe d'ajout d'une liste de garanties choisir pour un Vehicule
	 * 
	 * **/
public ListGarantieparVehicule(){	
	
}


private List<GarantieGarantieChoisie> garantieGarantieChoisieList;
private List<GarantieGarantieChoisieId> garantieGarantieChoisieIdList;
private GarantieChoisie garantieChoisie;

private java.math.BigDecimal primeAnnuelle,primeNetteAnnuelle, primeProrata,  primeRC, totalRed ;

private java.math.BigDecimal  tauxBonus, tauxMalus,tauxPermis, tauxFlotte,tauxRed;






public java.math.BigDecimal getPrimeAnnuelle() {
	return primeAnnuelle;
}
public void setPrimeAnnuelle(java.math.BigDecimal primeAnnuelle) {
	this.primeAnnuelle = primeAnnuelle;
}
public java.math.BigDecimal getPrimeProrata() {
	return primeProrata;
}
public void setPrimeProrata(java.math.BigDecimal primeProrata) {
	this.primeProrata = primeProrata;
}

public java.math.BigDecimal getPrimeRC() {
	return primeRC;
}
public void setPrimeRC(java.math.BigDecimal primeRC) {
	this.primeRC = primeRC;
}

public java.math.BigDecimal getTotalRed() {
	return totalRed;
}
public void setTotalRed(java.math.BigDecimal totalRed) {
	this.totalRed = totalRed;
}
public java.math.BigDecimal getTauxBonus() {
	return tauxBonus;
}
public void setTauxBonus(java.math.BigDecimal tauxBonus) {
	this.tauxBonus = tauxBonus;
}
public java.math.BigDecimal getTauxMalus() {
	return tauxMalus;
}
public void setTauxMalus(java.math.BigDecimal tauxMalus) {
	this.tauxMalus = tauxMalus;
}
public java.math.BigDecimal getTauxPermis() {
	return tauxPermis;
}
public void setTauxPermis(java.math.BigDecimal tauxPermis) {
	this.tauxPermis = tauxPermis;
}
public java.math.BigDecimal getTauxFlotte() {
	return tauxFlotte;
}
public void setTauxFlotte(java.math.BigDecimal tauxFlotte) {
	this.tauxFlotte = tauxFlotte;
}
public List<GarantieGarantieChoisie> getGarantieGarantieChoisieList() {
	return garantieGarantieChoisieList;
}
public void setGarantieGarantieChoisieList(List<GarantieGarantieChoisie> garantieGarantieChoisieList) {
	this.garantieGarantieChoisieList = garantieGarantieChoisieList;
}

public List<GarantieGarantieChoisieId> getGarantieGarantieChoisieIdList() {
	return garantieGarantieChoisieIdList;
}
public void setGarantieGarantieChoisieIdList(
		List<GarantieGarantieChoisieId> garantieGarantieChoisieIdList) {
	this.garantieGarantieChoisieIdList = garantieGarantieChoisieIdList;
}
public GarantieChoisie getGarantieChoisie() {
	return garantieChoisie;
}
public void setGarantieChoisie(GarantieChoisie garantieChoisie) {
	this.garantieChoisie = garantieChoisie;
}
public java.math.BigDecimal getTauxRed() {
	return tauxRed;
}
public void setTauxRed(java.math.BigDecimal tauxRed) {
	this.tauxRed = tauxRed;
}
public java.math.BigDecimal getPrimeNetteAnnuelle() {
	return primeNetteAnnuelle;
}
public void setPrimeNetteAnnuelle(java.math.BigDecimal primeNetteAnnuelle) {
	this.primeNetteAnnuelle = primeNetteAnnuelle;
}


}
