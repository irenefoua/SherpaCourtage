package com.j3a.assurance.prime.categorie;

import java.math.BigDecimal;

public class PrimeCategorie  {

	private String energie;
	private String typeVehicule;
	private java.math.BigDecimal puissFisc = BigDecimal.ZERO;
	private String zone;
	private String classe;
	private String statut;
	private java.math.BigDecimal primeBase = BigDecimal.ZERO;
	private boolean remorque;
	private java.math.BigDecimal remorques = BigDecimal.ZERO;
	private java.math.BigDecimal surprimes = BigDecimal.ZERO;
	private java.math.BigDecimal primeRc = BigDecimal.ZERO;
	private java.math.BigDecimal defenseRecours = BigDecimal.ZERO;
	private java.math.BigDecimal defenseRecoursDommage = BigDecimal.ZERO;
	private java.math.BigDecimal immobilisation = BigDecimal.ZERO;
	private java.math.BigDecimal remboursemmentAnticipe = BigDecimal.ZERO;
	private java.math.BigDecimal brisGlaceRC = BigDecimal.ZERO;
	private java.math.BigDecimal brisGlaceRNC1 = BigDecimal.ZERO;
	private java.math.BigDecimal brisGlaceRNC2 = BigDecimal.ZERO;
	private java.math.BigDecimal valeurVenale = BigDecimal.ZERO;
	private java.math.BigDecimal valeurNeuve = BigDecimal.ZERO;
	private int dureePermis;
	private java.math.BigDecimal dommage = BigDecimal.ZERO;
	private java.math.BigDecimal collision = BigDecimal.ZERO;
	private java.math.BigDecimal volMain = BigDecimal.ZERO;
	private java.math.BigDecimal volAccessoires = BigDecimal.ZERO;
	private java.math.BigDecimal volVandalisme = BigDecimal.ZERO;
	private java.math.BigDecimal montantAccessoires = BigDecimal.ZERO;
	private java.math.BigDecimal incendie = BigDecimal.ZERO;
	private java.math.BigDecimal securiteRoutiere1 = BigDecimal.ZERO;
	private java.math.BigDecimal securiteRoutiere2 = BigDecimal.ZERO;
	private java.math.BigDecimal securiteRoutiere3= BigDecimal.ZERO;
	private String optionSR;
	private java.math.BigDecimal individuelChauf1 = BigDecimal.ZERO;
	private java.math.BigDecimal individuelChauf2 = BigDecimal.ZERO;
	private java.math.BigDecimal individuelChauf3 = BigDecimal.ZERO;
	private java.math.BigDecimal reduction = BigDecimal.ZERO;
	private String optionIndCh;
	private int dureeContrat;
	private int ageVehicule;
	private java.math.BigDecimal chargeUtile= BigDecimal.ZERO;
	public String getEnergie() {
		return energie;
	}
	public void setEnergie(String energie) {
		this.energie = energie;
	}
	public String getTypeVehicule() {
		return typeVehicule;
	}
	public void setTypeVehicule(String typeVehicule) {
		this.typeVehicule = typeVehicule;
	}
	public java.math.BigDecimal getPuissFisc() {
		return puissFisc;
	}
	public void setPuissFisc(java.math.BigDecimal puissFisc) {
		this.puissFisc = puissFisc;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public java.math.BigDecimal getPrimeBase() {
		return primeBase;
	}
	public void setPrimeBase(java.math.BigDecimal primeBase) {
		this.primeBase = primeBase;
	}
	public boolean isRemorque() {
		return remorque;
	}
	public void setRemorque(boolean remorque) {
		this.remorque = remorque;
	}
	public java.math.BigDecimal getRemorques() {
		return remorques;
	}
	public void setRemorques(java.math.BigDecimal remorques) {
		this.remorques = remorques;
	}
	public java.math.BigDecimal getPrimeRc() {
		return primeRc;
	}
	public void setPrimeRc(java.math.BigDecimal primeRc) {
		this.primeRc = primeRc;
	}
	public java.math.BigDecimal getDefenseRecours() {
		return defenseRecours;
	}
	public void setDefenseRecours(java.math.BigDecimal defenseRecours) {
		this.defenseRecours = defenseRecours;
	}
	public java.math.BigDecimal getDefenseRecoursDommage() {
		return defenseRecoursDommage;
	}
	public void setDefenseRecoursDommage(java.math.BigDecimal defenseRecoursDommage) {
		this.defenseRecoursDommage = defenseRecoursDommage;
	}
	public java.math.BigDecimal getImmobilisation() {
		return immobilisation;
	}
	public void setImmobilisation(java.math.BigDecimal immobilisation) {
		this.immobilisation = immobilisation;
	}
	public java.math.BigDecimal getRemboursemmentAnticipe() {
		return remboursemmentAnticipe;
	}
	public void setRemboursemmentAnticipe(
			java.math.BigDecimal remboursemmentAnticipe) {
		this.remboursemmentAnticipe = remboursemmentAnticipe;
	}
	public java.math.BigDecimal getBrisGlaceRC() {
		return brisGlaceRC;
	}
	public void setBrisGlaceRC(java.math.BigDecimal brisGlaceRC) {
		this.brisGlaceRC = brisGlaceRC;
	}
	public java.math.BigDecimal getBrisGlaceRNC1() {
		return brisGlaceRNC1;
	}
	public void setBrisGlaceRNC1(java.math.BigDecimal brisGlaceRNC1) {
		this.brisGlaceRNC1 = brisGlaceRNC1;
	}
	public java.math.BigDecimal getBrisGlaceRNC2() {
		return brisGlaceRNC2;
	}
	public void setBrisGlaceRNC2(java.math.BigDecimal brisGlaceRNC2) {
		this.brisGlaceRNC2 = brisGlaceRNC2;
	}
	public java.math.BigDecimal getValeurVenale() {
		return valeurVenale;
	}
	public void setValeurVenale(java.math.BigDecimal valeurVenale) {
		this.valeurVenale = valeurVenale;
	}
	public java.math.BigDecimal getValeurNeuve() {
		return valeurNeuve;
	}
	public void setValeurNeuve(java.math.BigDecimal valeurNeuve) {
		this.valeurNeuve = valeurNeuve;
	}
	public int getDureePermis() {
		return dureePermis;
	}
	public void setDureePermis(int dureePermis) {
		this.dureePermis = dureePermis;
	}
	public java.math.BigDecimal getDommage() {
		return dommage;
	}
	public void setDommage(java.math.BigDecimal dommage) {
		this.dommage = dommage;
	}
	public java.math.BigDecimal getCollision() {
		return collision;
	}
	public void setCollision(java.math.BigDecimal collision) {
		this.collision = collision;
	}
	public java.math.BigDecimal getVolMain() {
		return volMain;
	}
	public void setVolMain(java.math.BigDecimal volMain) {
		this.volMain = volMain;
	}
	public java.math.BigDecimal getVolAccessoires() {
		return volAccessoires;
	}
	public void setVolAccessoires(java.math.BigDecimal volAccessoires) {
		this.volAccessoires = volAccessoires;
	}
	public java.math.BigDecimal getVolVandalisme() {
		return volVandalisme;
	}
	public void setVolVandalisme(java.math.BigDecimal volVandalisme) {
		this.volVandalisme = volVandalisme;
	}
	public java.math.BigDecimal getMontantAccessoires() {
		return montantAccessoires;
	}
	public void setMontantAccessoires(java.math.BigDecimal montantAccessoires) {
		this.montantAccessoires = montantAccessoires;
	}
	public java.math.BigDecimal getIncendie() {
		return incendie;
	}
	public void setIncendie(java.math.BigDecimal incendie) {
		this.incendie = incendie;
	}
	public java.math.BigDecimal getSecuriteRoutiere1() {
		return securiteRoutiere1;
	}
	public void setSecuriteRoutiere1(java.math.BigDecimal securiteRoutiere1) {
		this.securiteRoutiere1 = securiteRoutiere1;
	}
	public java.math.BigDecimal getSecuriteRoutiere2() {
		return securiteRoutiere2;
	}
	public void setSecuriteRoutiere2(java.math.BigDecimal securiteRoutiere2) {
		this.securiteRoutiere2 = securiteRoutiere2;
	}
	public java.math.BigDecimal getSecuriteRoutiere3() {
		return securiteRoutiere3;
	}
	public void setSecuriteRoutiere3(java.math.BigDecimal securiteRoutiere3) {
		this.securiteRoutiere3 = securiteRoutiere3;
	}
	public String getOptionSR() {
		return optionSR;
	}
	public void setOptionSR(String optionSR) {
		this.optionSR = optionSR;
	}
	public java.math.BigDecimal getIndividuelChauf1() {
		return individuelChauf1;
	}
	public void setIndividuelChauf1(java.math.BigDecimal individuelChauf1) {
		this.individuelChauf1 = individuelChauf1;
	}
	public java.math.BigDecimal getIndividuelChauf2() {
		return individuelChauf2;
	}
	public void setIndividuelChauf2(java.math.BigDecimal individuelChauf2) {
		this.individuelChauf2 = individuelChauf2;
	}
	public java.math.BigDecimal getIndividuelChauf3() {
		return individuelChauf3;
	}
	public void setIndividuelChauf3(java.math.BigDecimal individuelChauf3) {
		this.individuelChauf3 = individuelChauf3;
	}
	public java.math.BigDecimal getReduction() {
		return reduction;
	}
	public void setReduction(java.math.BigDecimal reduction) {
		this.reduction = reduction;
	}
	public String getOptionIndCh() {
		return optionIndCh;
	}
	public void setOptionIndCh(String optionIndCh) {
		this.optionIndCh = optionIndCh;
	}
	public int getDureeContrat() {
		return dureeContrat;
	}
	public void setDureeContrat(int dureeContrat) {
		this.dureeContrat = dureeContrat;
	}
	public int getAgeVehicule() {
		return ageVehicule;
	}
	public void setAgeVehicule(int ageVehicule) {
		this.ageVehicule = ageVehicule;
	}
	public java.math.BigDecimal getChargeUtile() {
		return chargeUtile;
	}
	public void setChargeUtile(java.math.BigDecimal chargeUtile) {
		this.chargeUtile = chargeUtile;
	}
	public java.math.BigDecimal getSurprimes() {
		return surprimes;
	}
	public void setSurprimes(java.math.BigDecimal surprimes) {
		this.surprimes = surprimes;
	}
	
	

}