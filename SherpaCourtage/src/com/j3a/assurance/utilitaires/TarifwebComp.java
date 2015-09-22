package com.j3a.assurance.utilitaires;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.j3a.assurance.model.CompagnieAssurance;
import com.j3a.assurance.model.Tarifweb;

public class TarifwebComp {
	private int numOrdr;
	private CompagnieAssurance compagnieAssurance = new CompagnieAssurance();
	private List<Tarifweb> tarifwebList = new ArrayList<Tarifweb>();
	private Tarifweb tarifweb = new Tarifweb();
	private List<Garanties> listegaranties  = new ArrayList<Garanties>();
	private List<Garanties> listegarantieSelectd  = new ArrayList<Garanties>();
	private List<GarantiesParOption> listegarantiesParOption  = new ArrayList<GarantiesParOption>();
	private java.math.BigDecimal primes = BigDecimal.ZERO;
	private java.math.BigDecimal primeNette = BigDecimal.ZERO;
	private java.math.BigDecimal primeMensuelle = BigDecimal.ZERO;
	private java.math.BigDecimal primeAnnuelle = BigDecimal.ZERO;
	
	private java.math.BigDecimal primeNetteTTC = BigDecimal.ZERO;
	private java.math.BigDecimal primeMensuelleTTC = BigDecimal.ZERO;
	private java.math.BigDecimal primeAnnuelleTTC = BigDecimal.ZERO;
	
	private java.math.BigDecimal reductionMensuelle = BigDecimal.ZERO;
	private java.math.BigDecimal reductionAnnuelle = BigDecimal.ZERO;
	private java.math.BigDecimal accessoireMensuelle = BigDecimal.ZERO;
	private java.math.BigDecimal accessoireAnnuelle = BigDecimal.ZERO;
	private java.math.BigDecimal taxe = BigDecimal.ZERO;
	private java.math.BigDecimal fga = BigDecimal.ZERO;
	private java.math.BigDecimal reduction = BigDecimal.ZERO;
	private java.math.BigDecimal accessoire = BigDecimal.ZERO;
	private java.math.BigDecimal taxeMensuelle = BigDecimal.ZERO;
	private java.math.BigDecimal taxeAnnuelle = BigDecimal.ZERO;
	private java.math.BigDecimal fgaMensuelle = BigDecimal.ZERO;
	private java.math.BigDecimal fgaAnnuelle = BigDecimal.ZERO;
	private String optionGarantie = "TS";
	private String formule = "TIER SIMPLE";
	private Boolean choixComp = false;
	private long dureeJContrat;
	private double dureeMoisContrat;
//	private
	public TarifwebComp() {
	
	}
	public int getNumOrdr() {
		return numOrdr;
	}
	public void setNumOrdr(int numOrdr) {
		this.numOrdr = numOrdr;
	}
	public CompagnieAssurance getCompagnieAssurance() {
		return compagnieAssurance;
	}
	public void setCompagnieAssurance(CompagnieAssurance compagnieAssurance) {
		this.compagnieAssurance = compagnieAssurance;
	}
	public List<Tarifweb> getTarifwebList() {
		return tarifwebList;
	}
	public void setTarifwebList(List<Tarifweb> tarifwebList) {
		this.tarifwebList = tarifwebList;
	}
	public Tarifweb getTarifweb() {
		return tarifweb;
	}
	public void setTarifweb(Tarifweb tarifweb) {
		this.tarifweb = tarifweb;
	}
	public List<Garanties> getListegaranties() {
		return listegaranties;
	}
	public void setListegaranties(List<Garanties> listegaranties) {
		this.listegaranties = listegaranties;
	}
	public java.math.BigDecimal getPrimeMensuelle() {
		return primeMensuelle;
	}
	public void setPrimeMensuelle(java.math.BigDecimal primeMensuelle) {
		this.primeMensuelle = primeMensuelle;
	}
	public java.math.BigDecimal getPrimeAnnuelle() {
		return primeAnnuelle;
	}
	public void setPrimeAnnuelle(java.math.BigDecimal primeAnnuelle) {
		this.primeAnnuelle = primeAnnuelle;
	}
	public String getOptionGarantie() {
		return optionGarantie;
	}
	public void setOptionGarantie(String optionGarantie) {
		this.optionGarantie = optionGarantie;
	}
	public Boolean getChoixComp() {
		return choixComp;
	}
	public void setChoixComp(Boolean choixComp) {
		this.choixComp = choixComp;
	}
	public String getFormule() {
		return formule;
	}
	public void setFormule(String formule) {
		this.formule = formule;
	}
	public List<GarantiesParOption> getListegarantiesParOption() {
		return listegarantiesParOption;
	}
	public void setListegarantiesParOption(
			List<GarantiesParOption> listegarantiesParOption) {
		this.listegarantiesParOption = listegarantiesParOption;
	}
	public List<Garanties> getListegarantieSelectd() {
		return listegarantieSelectd;
	}
	public void setListegarantieSelectd(List<Garanties> listegarantieSelectd) {
		this.listegarantieSelectd = listegarantieSelectd;
	}
	public java.math.BigDecimal getReductionMensuelle() {
		return reductionMensuelle;
	}
	public void setReductionMensuelle(java.math.BigDecimal reductionMensuelle) {
		this.reductionMensuelle = reductionMensuelle;
	}
	public java.math.BigDecimal getReductionAnnuelle() {
		return reductionAnnuelle;
	}
	public void setReductionAnnuelle(java.math.BigDecimal reductionAnnuelle) {
		this.reductionAnnuelle = reductionAnnuelle;
	}
	public java.math.BigDecimal getAccessoireMensuelle() {
		return accessoireMensuelle;
	}
	public void setAccessoireMensuelle(java.math.BigDecimal accessoireMensuelle) {
		this.accessoireMensuelle = accessoireMensuelle;
	}
	public java.math.BigDecimal getAccessoireAnnuelle() {
		return accessoireAnnuelle;
	}
	public void setAccessoireAnnuelle(java.math.BigDecimal accessoireAnnuelle) {
		this.accessoireAnnuelle = accessoireAnnuelle;
	}
	public java.math.BigDecimal getTaxeMensuelle() {
		return taxeMensuelle;
	}
	public void setTaxeMensuelle(java.math.BigDecimal taxeMensuelle) {
		this.taxeMensuelle = taxeMensuelle;
	}
	public java.math.BigDecimal getTaxeAnnuelle() {
		return taxeAnnuelle;
	}
	public void setTaxeAnnuelle(java.math.BigDecimal taxeAnnuelle) {
		this.taxeAnnuelle = taxeAnnuelle;
	}
	public java.math.BigDecimal getFgaMensuelle() {
		return fgaMensuelle;
	}
	public void setFgaMensuelle(java.math.BigDecimal fgaMensuelle) {
		this.fgaMensuelle = fgaMensuelle;
	}
	public java.math.BigDecimal getFgaAnnuelle() {
		return fgaAnnuelle;
	}
	public void setFgaAnnuelle(java.math.BigDecimal fgaAnnuelle) {
		this.fgaAnnuelle = fgaAnnuelle;
	}
	public java.math.BigDecimal getPrimeNette() {
		return primeNette;
	}
	public void setPrimeNette(java.math.BigDecimal primeNette) {
		this.primeNette = primeNette;
	}
	public java.math.BigDecimal getTaxe() {
		return taxe;
	}
	public void setTaxe(java.math.BigDecimal taxe) {
		this.taxe = taxe;
	}
	public java.math.BigDecimal getFga() {
		return fga;
	}
	public void setFga(java.math.BigDecimal fga) {
		this.fga = fga;
	}
	public java.math.BigDecimal getReduction() {
		return reduction;
	}
	public void setReduction(java.math.BigDecimal reduction) {
		this.reduction = reduction;
	}
	public java.math.BigDecimal getAccessoire() {
		return accessoire;
	}
	public void setAccessoire(java.math.BigDecimal accessoire) {
		this.accessoire = accessoire;
	}
	public long getDureeJContrat() {
		return dureeJContrat;
	}
	public void setDureeJContrat(long dureeJContrat) {
		this.dureeJContrat = dureeJContrat;
	}
	public double getDureeMoisContrat() {
		return dureeMoisContrat;
	}
	public void setDureeMoisContrat(double dureeMoisContrat) {
		this.dureeMoisContrat = dureeMoisContrat;
	}
	public java.math.BigDecimal getPrimeNetteTTC() {
		return primeNetteTTC;
	}
	public void setPrimeNetteTTC(java.math.BigDecimal primeNetteTTC) {
		this.primeNetteTTC = primeNetteTTC;
	}
	public java.math.BigDecimal getPrimeMensuelleTTC() {
		return primeMensuelleTTC;
	}
	public void setPrimeMensuelleTTC(java.math.BigDecimal primeMensuelleTTC) {
		this.primeMensuelleTTC = primeMensuelleTTC;
	}
	public java.math.BigDecimal getPrimeAnnuelleTTC() {
		return primeAnnuelleTTC;
	}
	public void setPrimeAnnuelleTTC(java.math.BigDecimal primeAnnuelleTTC) {
		this.primeAnnuelleTTC = primeAnnuelleTTC;
	}
	public java.math.BigDecimal getPrimes() {
		return primes;
	}
	public void setPrimes(java.math.BigDecimal primes) {
		this.primes = primes;
	}
	
}
