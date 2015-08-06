package com.j3a.assurance.utilitaires;
import java.math.BigDecimal;

public class Quittances {

	private java.math.BigDecimal primeNette = BigDecimal.ZERO,primeCedee = BigDecimal.ZERO , primeTotale = BigDecimal.ZERO;
	private java.math.BigDecimal  commissionAper = BigDecimal.ZERO ,commissionCoassu = BigDecimal.ZERO,commissionConseil = BigDecimal.ZERO,commissionInterm = BigDecimal.ZERO, commissionGest = BigDecimal.ZERO;
	private java.math.BigDecimal  accessoireComp = BigDecimal.ZERO, accessoireInterm = BigDecimal.ZERO,accessoireGest= BigDecimal.ZERO,accessoire=BigDecimal.ZERO;
	private java.math.BigDecimal totalAccessoire  = BigDecimal.ZERO, totalCommission = BigDecimal.ZERO ;
	private java.math.BigDecimal redCommerciale = BigDecimal.ZERO, redSpeciale = BigDecimal.ZERO;
	private java.math.BigDecimal  taxeEnr = BigDecimal.ZERO,taxeFGA = BigDecimal.ZERO;
	private java.math.BigDecimal netteApayer = BigDecimal.ZERO,tauxgest=BigDecimal.ZERO,tauxinter=BigDecimal.ZERO,tauxcons=BigDecimal.ZERO;
	private java.math.BigDecimal primeExoEncours = BigDecimal.ZERO,primeReport = BigDecimal.ZERO,prec = BigDecimal.ZERO;
	
	public java.math.BigDecimal getPrimeNette() {
		return primeNette;
	}
	public void setPrimeNette(java.math.BigDecimal primeNette) {
		this.primeNette = primeNette;
	}
	public java.math.BigDecimal getPrimeCedee() {
		return primeCedee;
	}
	public void setPrimeCedee(java.math.BigDecimal primeCedee) {
		this.primeCedee = primeCedee;
	}
	public java.math.BigDecimal getCommissionAper() {
		return commissionAper;
	}
	public void setCommissionAper(java.math.BigDecimal commissionAper) {
		this.commissionAper = commissionAper;
	}
	public java.math.BigDecimal getCommissionCoassu() {
		return commissionCoassu;
	}
	public void setCommissionCoassu(java.math.BigDecimal commissionCoassu) {
		this.commissionCoassu = commissionCoassu;
	}
	public java.math.BigDecimal getCommissionConseil() {
		return commissionConseil;
	}
	public void setCommissionConseil(java.math.BigDecimal commissionConseil) {
		this.commissionConseil = commissionConseil;
	}
	public java.math.BigDecimal getCommissionInterm() {
		return commissionInterm;
	}
	public void setCommissionInterm(java.math.BigDecimal commissionInterm) {
		this.commissionInterm = commissionInterm;
	}
	public java.math.BigDecimal getAccessoireComp() {
		return accessoireComp;
	}
	public void setAccessoireComp(java.math.BigDecimal accessoireComp) {
		this.accessoireComp = accessoireComp;
	}
	public java.math.BigDecimal getAccessoireInterm() {
		return accessoireInterm;
	}
	public void setAccessoireInterm(java.math.BigDecimal accessoireInterm) {
		this.accessoireInterm = accessoireInterm;
	}
	public java.math.BigDecimal getTotalAccessoire() {
		return totalAccessoire;
	}
	public void setTotalAccessoire(java.math.BigDecimal totalAccessoire) {
		this.totalAccessoire = totalAccessoire;
	}
	public java.math.BigDecimal getTotalCommission() {
		return totalCommission;
	}
	public void setTotalCommission(java.math.BigDecimal totalCommission) {
		this.totalCommission = totalCommission;
	}
	public java.math.BigDecimal getRedCommerciale() {
		return redCommerciale;
	}
	public void setRedCommerciale(java.math.BigDecimal redCommerciale) {
		this.redCommerciale = redCommerciale;
	}
	public java.math.BigDecimal getRedSpeciale() {
		return redSpeciale;
	}
	public void setRedSpeciale(java.math.BigDecimal redSpeciale) {
		this.redSpeciale = redSpeciale;
	}
	public java.math.BigDecimal getTaxeEnr() {
		return taxeEnr;
	}
	public void setTaxeEnr(java.math.BigDecimal taxeEnr) {
		this.taxeEnr = taxeEnr;
	}
	public java.math.BigDecimal getNetteApayer() {
		return netteApayer;
	}
	public void setNetteApayer(java.math.BigDecimal netteApayer) {
		this.netteApayer = netteApayer;
	}
	public java.math.BigDecimal getAccessoireGest() {
		return accessoireGest;
	}
	public void setAccessoireGest(java.math.BigDecimal accessoireGest) {
		this.accessoireGest = accessoireGest;
	}
	public java.math.BigDecimal getCommissionGest() {
		return commissionGest;
	}
	public void setCommissionGest(java.math.BigDecimal commissionGest) {
		this.commissionGest = commissionGest;
	}
	public java.math.BigDecimal getTauxgest() {
		return tauxgest;
	}
	public void setTauxgest(java.math.BigDecimal tauxgest) {
		this.tauxgest = tauxgest;
	}
	public java.math.BigDecimal getTauxinter() {
		return tauxinter;
	}
	public void setTauxinter(java.math.BigDecimal tauxinter) {
		this.tauxinter = tauxinter;
	}
	public java.math.BigDecimal getTauxcons() {
		return tauxcons;
	}
	public void setTauxcons(java.math.BigDecimal tauxcons) {
		this.tauxcons = tauxcons;
	}
	public java.math.BigDecimal getAccessoire() {
		return accessoire;
	}
	public void setAccessoire(java.math.BigDecimal accessoire) {
		this.accessoire = accessoire;
	}
	public java.math.BigDecimal getTaxeFGA() {
		return taxeFGA;
	}
	public void setTaxeFGA(java.math.BigDecimal taxeFGA) {
		this.taxeFGA = taxeFGA;
	}
	public java.math.BigDecimal getPrimeExoEncours() {
		return primeExoEncours;
	}
	public void setPrimeExoEncours(java.math.BigDecimal primeExoEncours) {
		this.primeExoEncours = primeExoEncours;
	}
	public java.math.BigDecimal getPrimeReport() {
		return primeReport;
	}
	public void setPrimeReport(java.math.BigDecimal primeReport) {
		this.primeReport = primeReport;
	}
	public java.math.BigDecimal getPrec() {
		return prec;
	}
	public void setPrec(java.math.BigDecimal prec) {
		this.prec = prec;
	}
	public java.math.BigDecimal getPrimeTotale() {
		return primeTotale;
	}
	public void setPrimeTotale(java.math.BigDecimal primeTotale) {
		this.primeTotale = primeTotale;
	}
	
	
}
