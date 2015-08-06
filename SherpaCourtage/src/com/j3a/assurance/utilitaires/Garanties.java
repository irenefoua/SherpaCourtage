package com.j3a.assurance.utilitaires;

import java.math.BigDecimal;

import com.j3a.assurance.model.Categorie;
import com.j3a.assurance.model.Garantie;
import com.j3a.assurance.model.Risque;

public class Garanties extends Garantie {
	private static final long serialVersionUID = 1L;

	public Garanties() {
		// TODO Auto-generated constructor stub
	}

	private Object garantieGarantiesChoisie;
	private Risque risque = new Risque();
	private Categorie categorie = new Categorie();

	private java.math.BigDecimal reduction = BigDecimal.ZERO;
	private java.math.BigDecimal montantRed = BigDecimal.ZERO;
	private java.math.BigDecimal primeAnnuelle = BigDecimal.ZERO;
	private java.math.BigDecimal primeNetAn = BigDecimal.ZERO;
	private java.math.BigDecimal prime = BigDecimal.ZERO;
	private java.math.BigDecimal primesProrata = BigDecimal.ZERO;
	private java.math.BigDecimal capitals = BigDecimal.ZERO;
	private java.math.BigDecimal franchises = BigDecimal.ZERO;
	private java.math.BigDecimal primeRC = BigDecimal.ZERO;
	private java.math.BigDecimal bonus = BigDecimal.ZERO;
	private java.math.BigDecimal malus = BigDecimal.ZERO;
	private int nOrdre;

	private java.lang.String nature = "";
	private java.math.BigDecimal taux = BigDecimal.ZERO;
	private java.math.BigDecimal minimum = BigDecimal.ZERO;
	private java.math.BigDecimal maximum = BigDecimal.ZERO;
	private java.util.Date dateGarantieChoisie;
	private java.math.BigDecimal montantGarantie = BigDecimal.ZERO;
	private java.math.BigDecimal tauxFranchise = BigDecimal.ZERO;
	private java.math.BigDecimal primesAnnuelle = BigDecimal.ZERO;
	private java.math.BigDecimal primesNetteAnnuelle = BigDecimal.ZERO;
	private java.math.BigDecimal primesNette = BigDecimal.ZERO;
	private java.math.BigDecimal reductions = BigDecimal.ZERO;
	private java.math.BigDecimal facteur = BigDecimal.ZERO;
	private java.lang.String smp = "";
	private java.math.BigDecimal tauxPrime = BigDecimal.ZERO;
	private java.math.BigDecimal tauxLci = BigDecimal.ZERO;
	private java.math.BigDecimal tauxRed = BigDecimal.ZERO;
	private java.math.BigDecimal tauxGes = BigDecimal.ZERO;
	private java.math.BigDecimal tauxComcoass = BigDecimal.ZERO;
	private java.math.BigDecimal tauxinterm = BigDecimal.ZERO;
	private java.math.BigDecimal tauxcons = BigDecimal.ZERO;
	private java.math.BigDecimal tauxredfranch = BigDecimal.ZERO;
	private java.math.BigDecimal tauxcouv = BigDecimal.ZERO;
	private java.lang.String observation = "";
	private java.math.BigDecimal comCons = BigDecimal.ZERO;
	private java.math.BigDecimal comGes = BigDecimal.ZERO;
	private java.math.BigDecimal comInter = BigDecimal.ZERO;
	private java.math.BigDecimal comCoass = BigDecimal.ZERO;
	private java.lang.Boolean choix = false;
	private java.lang.String observationFacteurMajoration = "";
	private java.lang.String surprimeFacteurMajoration;

	private java.util.Date delaiCarence;
	private java.math.BigDecimal tauxFacteurMajoration = BigDecimal.ZERO;;

	public java.math.BigDecimal getReduction() {
		return reduction;
	}

	public void setReduction(java.math.BigDecimal reduction) {
		this.reduction = reduction;
	}

	public java.math.BigDecimal getMontantRed() {
		return montantRed;
	}

	public void setMontantRed(java.math.BigDecimal montantRed) {
		this.montantRed = montantRed;
	}

	public java.math.BigDecimal getPrimeAnnuelle() {
		return primeAnnuelle;
	}

	public void setPrimeAnnuelle(java.math.BigDecimal primeAnnuelle) {
		this.primeAnnuelle = primeAnnuelle;
	}

	public java.math.BigDecimal getPrimeNetAn() {
		return primeNetAn;
	}

	public void setPrimeNetAn(java.math.BigDecimal primeNetAn) {
		this.primeNetAn = primeNetAn;
	}

	public java.math.BigDecimal getPrime() {
		return prime;
	}

	public void setPrime(java.math.BigDecimal prime) {
		this.prime = prime;
	}

	public java.math.BigDecimal getPrimeRC() {
		return primeRC;
	}

	public void setPrimeRC(java.math.BigDecimal primeRC) {
		this.primeRC = primeRC;
	}

	public java.math.BigDecimal getPrimesProrata() {
		return primesProrata;
	}

	public void setPrimesProrata(java.math.BigDecimal primesProrata) {
		this.primesProrata = primesProrata;
	}

	public java.math.BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(java.math.BigDecimal bonus) {
		this.bonus = bonus;
	}

	public java.math.BigDecimal getMalus() {
		return malus;
	}

	public void setMalus(java.math.BigDecimal malus) {
		this.malus = malus;
	}

	public java.lang.String getNature() {
		return nature;
	}

	public void setNature(java.lang.String nature) {
		this.nature = nature;
	}

	public java.math.BigDecimal getTaux() {
		return taux;
	}

	public void setTaux(java.math.BigDecimal taux) {
		this.taux = taux;
	}

	public java.math.BigDecimal getMinimum() {
		return minimum;
	}

	public void setMinimum(java.math.BigDecimal minimum) {
		this.minimum = minimum;
	}

	public java.math.BigDecimal getMaximum() {
		return maximum;
	}

	public void setMaximum(java.math.BigDecimal maximum) {
		this.maximum = maximum;
	}

	public java.util.Date getDateGarantieChoisie() {
		return dateGarantieChoisie;
	}

	public void setDateGarantieChoisie(java.util.Date dateGarantieChoisie) {
		this.dateGarantieChoisie = dateGarantieChoisie;
	}

	public java.math.BigDecimal getMontantGarantie() {
		return montantGarantie;
	}

	public void setMontantGarantie(java.math.BigDecimal montantGarantie) {
		this.montantGarantie = montantGarantie;
	}

	public java.math.BigDecimal getTauxFranchise() {
		return tauxFranchise;
	}

	public void setTauxFranchise(java.math.BigDecimal tauxFranchise) {
		this.tauxFranchise = tauxFranchise;
	}

	public java.math.BigDecimal getPrimesAnnuelle() {
		return primesAnnuelle;
	}

	public void setPrimesAnnuelle(java.math.BigDecimal primesAnnuelle) {
		this.primesAnnuelle = primesAnnuelle;
	}

	public java.math.BigDecimal getPrimesNetteAnnuelle() {
		return primesNetteAnnuelle;
	}

	public void setPrimesNetteAnnuelle(java.math.BigDecimal primesNetteAnnuelle) {
		this.primesNetteAnnuelle = primesNetteAnnuelle;
	}

	public java.math.BigDecimal getReductions() {
		return reductions;
	}

	public void setReductions(java.math.BigDecimal reductions) {
		this.reductions = reductions;
	}

	public java.lang.String getSmp() {
		return smp;
	}

	public void setSmp(java.lang.String smp) {
		this.smp = smp;
	}

	public java.math.BigDecimal getTauxPrime() {
		return tauxPrime;
	}

	public void setTauxPrime(java.math.BigDecimal tauxPrime) {
		this.tauxPrime = tauxPrime;
	}

	public java.math.BigDecimal getTauxLci() {
		return tauxLci;
	}

	public void setTauxLci(java.math.BigDecimal tauxLci) {
		this.tauxLci = tauxLci;
	}

	public java.math.BigDecimal getTauxRed() {
		return tauxRed;
	}

	public void setTauxRed(java.math.BigDecimal tauxRed) {
		this.tauxRed = tauxRed;
	}

	public java.math.BigDecimal getTauxGes() {
		return tauxGes;
	}

	public void setTauxGes(java.math.BigDecimal tauxGes) {
		this.tauxGes = tauxGes;
	}

	public java.math.BigDecimal getTauxComcoass() {
		return tauxComcoass;
	}

	public void setTauxComcoass(java.math.BigDecimal tauxComcoass) {
		this.tauxComcoass = tauxComcoass;
	}

	public java.math.BigDecimal getTauxinterm() {
		return tauxinterm;
	}

	public void setTauxinterm(java.math.BigDecimal tauxinterm) {
		this.tauxinterm = tauxinterm;
	}

	public java.math.BigDecimal getTauxcons() {
		return tauxcons;
	}

	public void setTauxcons(java.math.BigDecimal tauxcons) {
		this.tauxcons = tauxcons;
	}

	public java.math.BigDecimal getTauxredfranch() {
		return tauxredfranch;
	}

	public void setTauxredfranch(java.math.BigDecimal tauxredfranch) {
		this.tauxredfranch = tauxredfranch;
	}

	public java.math.BigDecimal getTauxcouv() {
		return tauxcouv;
	}

	public void setTauxcouv(java.math.BigDecimal tauxcouv) {
		this.tauxcouv = tauxcouv;
	}

	public java.lang.String getObservation() {
		return observation;
	}

	public void setObservation(java.lang.String observation) {
		this.observation = observation;
	}

	public java.math.BigDecimal getComCons() {
		return comCons;
	}

	public void setComCons(java.math.BigDecimal comCons) {
		this.comCons = comCons;
	}

	public java.math.BigDecimal getComGes() {
		return comGes;
	}

	public void setComGes(java.math.BigDecimal comGes) {
		this.comGes = comGes;
	}

	public java.math.BigDecimal getComInter() {
		return comInter;
	}

	public void setComInter(java.math.BigDecimal comInter) {
		this.comInter = comInter;
	}

	public java.math.BigDecimal getComCoass() {
		return comCoass;
	}

	public void setComCoass(java.math.BigDecimal comCoass) {
		this.comCoass = comCoass;
	}

	public java.math.BigDecimal getCapitals() {
		return capitals;
	}

	public void setCapitals(java.math.BigDecimal capitals) {
		this.capitals = capitals;
	}

	public java.math.BigDecimal getFranchises() {
		return franchises;
	}

	public void setFranchises(java.math.BigDecimal franchises) {
		this.franchises = franchises;
	}

	public java.math.BigDecimal getFacteur() {
		return facteur;
	}

	public void setFacteur(java.math.BigDecimal facteur) {
		this.facteur = facteur;
	}

	public java.math.BigDecimal getPrimesNette() {
		return primesNette;
	}

	public void setPrimesNette(java.math.BigDecimal primesNette) {
		this.primesNette = primesNette;
	}

	public int getnOrdre() {
		return nOrdre;
	}

	public void setnOrdre(int nOrdre) {
		this.nOrdre = nOrdre;
	}

	public java.lang.Boolean getChoix() {
		return choix;
	}

	public void setChoix(java.lang.Boolean choix) {
		this.choix = choix;
	}

	public java.util.Date getDelaiCarence() {
		return delaiCarence;
	}

	public void setDelaiCarence(java.util.Date delaiCarence) {
		this.delaiCarence = delaiCarence;
	}

	public java.math.BigDecimal getTauxFacteurMajoration() {
		return tauxFacteurMajoration;
	}

	public void setTauxFacteurMajoration(
			java.math.BigDecimal tauxFacteurMajoration) {
		this.tauxFacteurMajoration = tauxFacteurMajoration;
	}

	public java.lang.String getObservationFacteurMajoration() {
		return observationFacteurMajoration;
	}

	public void setObservationFacteurMajoration(
			java.lang.String observationFacteurMajoration) {
		this.observationFacteurMajoration = observationFacteurMajoration;
	}

	public java.lang.String getSurprimeFacteurMajoration() {
		return surprimeFacteurMajoration;
	}

	public void setSurprimeFacteurMajoration(
			java.lang.String surprimeFacteurMajoration) {
		this.surprimeFacteurMajoration = surprimeFacteurMajoration;
	}

	public Object getGarantieGarantiesChoisie() {
		return garantieGarantiesChoisie;
	}

	public void setGarantieGarantiesChoisie(Object garantieGarantiesChoisie) {
		this.garantieGarantiesChoisie = garantieGarantiesChoisie;
	}

	public Risque getRisque() {
		return risque;
	}

	public void setRisque(Risque risque) {
		this.risque = risque;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

}
