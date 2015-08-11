package com.j3a.assurance.model;

// Generated 11 ao�t 2015 12:07:31 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * GarantieChoisie generated by hbm2java
 */
@Entity
@Table(name = "garantie_choisie", catalog = "zeusbd")
public class GarantieChoisie implements java.io.Serializable {

	private String codeGarantieChoisie;
	private Vehicule vehicule;
	private String libelleGarantieChosie;
	private Date dateGarantieChoisie;
	private BigDecimal primeNetteAnnuelle;
	private BigDecimal primeAnnuelle;
	private BigDecimal primeNetteProrata;
	private BigDecimal bonus;
	private BigDecimal malus;
	private BigDecimal reductionSocioProf;
	private BigDecimal reductionPermis;
	private BigDecimal reductionCommercial;
	private BigDecimal autre;
	private BigDecimal montantReduction;
	private BigDecimal accessoireauto;
	private String codeAvenantAuto;
	private BigDecimal surprime;
	private Set<GarantieGarantieChoisie> garantieGarantieChoisies = new HashSet<GarantieGarantieChoisie>(
			0);

	public GarantieChoisie() {
	}

	public GarantieChoisie(String codeGarantieChoisie, Vehicule vehicule) {
		this.codeGarantieChoisie = codeGarantieChoisie;
		this.vehicule = vehicule;
	}

	public GarantieChoisie(String codeGarantieChoisie, Vehicule vehicule,
			String libelleGarantieChosie, Date dateGarantieChoisie,
			BigDecimal primeNetteAnnuelle, BigDecimal primeAnnuelle,
			BigDecimal primeNetteProrata, BigDecimal bonus, BigDecimal malus,
			BigDecimal reductionSocioProf, BigDecimal reductionPermis,
			BigDecimal reductionCommercial, BigDecimal autre,
			BigDecimal montantReduction, BigDecimal accessoireauto,
			String codeAvenantAuto, BigDecimal surprime,
			Set<GarantieGarantieChoisie> garantieGarantieChoisies) {
		this.codeGarantieChoisie = codeGarantieChoisie;
		this.vehicule = vehicule;
		this.libelleGarantieChosie = libelleGarantieChosie;
		this.dateGarantieChoisie = dateGarantieChoisie;
		this.primeNetteAnnuelle = primeNetteAnnuelle;
		this.primeAnnuelle = primeAnnuelle;
		this.primeNetteProrata = primeNetteProrata;
		this.bonus = bonus;
		this.malus = malus;
		this.reductionSocioProf = reductionSocioProf;
		this.reductionPermis = reductionPermis;
		this.reductionCommercial = reductionCommercial;
		this.autre = autre;
		this.montantReduction = montantReduction;
		this.accessoireauto = accessoireauto;
		this.codeAvenantAuto = codeAvenantAuto;
		this.surprime = surprime;
		this.garantieGarantieChoisies = garantieGarantieChoisies;
	}

	@Id
	@Column(name = "CODE_GARANTIE_CHOISIE", unique = true, nullable = false, length = 30)
	public String getCodeGarantieChoisie() {
		return this.codeGarantieChoisie;
	}

	public void setCodeGarantieChoisie(String codeGarantieChoisie) {
		this.codeGarantieChoisie = codeGarantieChoisie;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_VEHICULE", nullable = false)
	public Vehicule getVehicule() {
		return this.vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	@Column(name = "LIBELLE_GARANTIE_CHOSIE", length = 70)
	public String getLibelleGarantieChosie() {
		return this.libelleGarantieChosie;
	}

	public void setLibelleGarantieChosie(String libelleGarantieChosie) {
		this.libelleGarantieChosie = libelleGarantieChosie;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_GARANTIE_CHOISIE", length = 10)
	public Date getDateGarantieChoisie() {
		return this.dateGarantieChoisie;
	}

	public void setDateGarantieChoisie(Date dateGarantieChoisie) {
		this.dateGarantieChoisie = dateGarantieChoisie;
	}

	@Column(name = "PRIME_NETTE_ANNUELLE", precision = 15, scale = 3)
	public BigDecimal getPrimeNetteAnnuelle() {
		return this.primeNetteAnnuelle;
	}

	public void setPrimeNetteAnnuelle(BigDecimal primeNetteAnnuelle) {
		this.primeNetteAnnuelle = primeNetteAnnuelle;
	}

	@Column(name = "PRIME_ANNUELLE", precision = 15, scale = 3)
	public BigDecimal getPrimeAnnuelle() {
		return this.primeAnnuelle;
	}

	public void setPrimeAnnuelle(BigDecimal primeAnnuelle) {
		this.primeAnnuelle = primeAnnuelle;
	}

	@Column(name = "PRIME_NETTE_PRORATA", precision = 15, scale = 3)
	public BigDecimal getPrimeNetteProrata() {
		return this.primeNetteProrata;
	}

	public void setPrimeNetteProrata(BigDecimal primeNetteProrata) {
		this.primeNetteProrata = primeNetteProrata;
	}

	@Column(name = "BONUS", precision = 15, scale = 3)
	public BigDecimal getBonus() {
		return this.bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	@Column(name = "MALUS", precision = 15, scale = 3)
	public BigDecimal getMalus() {
		return this.malus;
	}

	public void setMalus(BigDecimal malus) {
		this.malus = malus;
	}

	@Column(name = "REDUCTION_SOCIO_PROF", precision = 15, scale = 3)
	public BigDecimal getReductionSocioProf() {
		return this.reductionSocioProf;
	}

	public void setReductionSocioProf(BigDecimal reductionSocioProf) {
		this.reductionSocioProf = reductionSocioProf;
	}

	@Column(name = "REDUCTION_PERMIS", precision = 15, scale = 3)
	public BigDecimal getReductionPermis() {
		return this.reductionPermis;
	}

	public void setReductionPermis(BigDecimal reductionPermis) {
		this.reductionPermis = reductionPermis;
	}

	@Column(name = "REDUCTION_COMMERCIAL", precision = 15, scale = 3)
	public BigDecimal getReductionCommercial() {
		return this.reductionCommercial;
	}

	public void setReductionCommercial(BigDecimal reductionCommercial) {
		this.reductionCommercial = reductionCommercial;
	}

	@Column(name = "AUTRE", precision = 15, scale = 3)
	public BigDecimal getAutre() {
		return this.autre;
	}

	public void setAutre(BigDecimal autre) {
		this.autre = autre;
	}

	@Column(name = "MONTANT_REDUCTION", precision = 15, scale = 3)
	public BigDecimal getMontantReduction() {
		return this.montantReduction;
	}

	public void setMontantReduction(BigDecimal montantReduction) {
		this.montantReduction = montantReduction;
	}

	@Column(name = "ACCESSOIREAUTO", precision = 15, scale = 3)
	public BigDecimal getAccessoireauto() {
		return this.accessoireauto;
	}

	public void setAccessoireauto(BigDecimal accessoireauto) {
		this.accessoireauto = accessoireauto;
	}

	@Column(name = "CODE_AVENANT_AUTO", length = 28)
	public String getCodeAvenantAuto() {
		return this.codeAvenantAuto;
	}

	public void setCodeAvenantAuto(String codeAvenantAuto) {
		this.codeAvenantAuto = codeAvenantAuto;
	}

	@Column(name = "SURPRIME", precision = 15, scale = 3)
	public BigDecimal getSurprime() {
		return this.surprime;
	}

	public void setSurprime(BigDecimal surprime) {
		this.surprime = surprime;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garantieChoisie")
	public Set<GarantieGarantieChoisie> getGarantieGarantieChoisies() {
		return this.garantieGarantieChoisies;
	}

	public void setGarantieGarantieChoisies(
			Set<GarantieGarantieChoisie> garantieGarantieChoisies) {
		this.garantieGarantieChoisies = garantieGarantieChoisies;
	}

}
