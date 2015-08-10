package com.j3a.assurance.model;

// Generated 10 ao�t 2015 15:53:59 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * GarantieGarantieChoisie generated by hbm2java
 */
@Entity
@Table(name = "garantie_garantie_choisie", catalog = "zeusbd")
public class GarantieGarantieChoisie implements java.io.Serializable {

	private GarantieGarantieChoisieId id;
	private Garantie garantie;
	private GarantieChoisie garantieChoisie;
	private BigDecimal primeAnnuelle;
	private BigDecimal primeNetteProrata;
	private Date dateGarantieGarantieChoisie;
	private BigDecimal primeNetteAnnuelle;
	private BigDecimal montantReduction;
	private BigDecimal bonus;
	private BigDecimal malus;
	private BigDecimal reductionPermis;
	private BigDecimal reductionFlotte;
	private BigDecimal autreReduction;
	private BigDecimal tauxMalus;
	private BigDecimal tauxBonus;
	private BigDecimal tauxFlotte;
	private BigDecimal tauxPermis;
	private BigDecimal tauxAutreReduction;
	private BigDecimal accessoireAuto;
	private BigDecimal taxeEnrAuto;
	private BigDecimal fgaAuto;

	public GarantieGarantieChoisie() {
	}

	public GarantieGarantieChoisie(GarantieGarantieChoisieId id,
			Garantie garantie, GarantieChoisie garantieChoisie) {
		this.id = id;
		this.garantie = garantie;
		this.garantieChoisie = garantieChoisie;
	}

	public GarantieGarantieChoisie(GarantieGarantieChoisieId id,
			Garantie garantie, GarantieChoisie garantieChoisie,
			BigDecimal primeAnnuelle, BigDecimal primeNetteProrata,
			Date dateGarantieGarantieChoisie, BigDecimal primeNetteAnnuelle,
			BigDecimal montantReduction, BigDecimal bonus, BigDecimal malus,
			BigDecimal reductionPermis, BigDecimal reductionFlotte,
			BigDecimal autreReduction, BigDecimal tauxMalus,
			BigDecimal tauxBonus, BigDecimal tauxFlotte, BigDecimal tauxPermis,
			BigDecimal tauxAutreReduction, BigDecimal accessoireAuto,
			BigDecimal taxeEnrAuto, BigDecimal fgaAuto) {
		this.id = id;
		this.garantie = garantie;
		this.garantieChoisie = garantieChoisie;
		this.primeAnnuelle = primeAnnuelle;
		this.primeNetteProrata = primeNetteProrata;
		this.dateGarantieGarantieChoisie = dateGarantieGarantieChoisie;
		this.primeNetteAnnuelle = primeNetteAnnuelle;
		this.montantReduction = montantReduction;
		this.bonus = bonus;
		this.malus = malus;
		this.reductionPermis = reductionPermis;
		this.reductionFlotte = reductionFlotte;
		this.autreReduction = autreReduction;
		this.tauxMalus = tauxMalus;
		this.tauxBonus = tauxBonus;
		this.tauxFlotte = tauxFlotte;
		this.tauxPermis = tauxPermis;
		this.tauxAutreReduction = tauxAutreReduction;
		this.accessoireAuto = accessoireAuto;
		this.taxeEnrAuto = taxeEnrAuto;
		this.fgaAuto = fgaAuto;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codeGarantieChoisie", column = @Column(name = "CODE_GARANTIE_CHOISIE", nullable = false, length = 30)),
			@AttributeOverride(name = "codeGarantie", column = @Column(name = "CODE_GARANTIE", nullable = false, length = 12)) })
	public GarantieGarantieChoisieId getId() {
		return this.id;
	}

	public void setId(GarantieGarantieChoisieId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_GARANTIE", nullable = false, insertable = false, updatable = false)
	public Garantie getGarantie() {
		return this.garantie;
	}

	public void setGarantie(Garantie garantie) {
		this.garantie = garantie;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_GARANTIE_CHOISIE", nullable = false, insertable = false, updatable = false)
	public GarantieChoisie getGarantieChoisie() {
		return this.garantieChoisie;
	}

	public void setGarantieChoisie(GarantieChoisie garantieChoisie) {
		this.garantieChoisie = garantieChoisie;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_GARANTIE_GARANTIE_CHOISIE", length = 10)
	public Date getDateGarantieGarantieChoisie() {
		return this.dateGarantieGarantieChoisie;
	}

	public void setDateGarantieGarantieChoisie(Date dateGarantieGarantieChoisie) {
		this.dateGarantieGarantieChoisie = dateGarantieGarantieChoisie;
	}

	@Column(name = "PRIME_NETTE_ANNUELLE", precision = 15, scale = 3)
	public BigDecimal getPrimeNetteAnnuelle() {
		return this.primeNetteAnnuelle;
	}

	public void setPrimeNetteAnnuelle(BigDecimal primeNetteAnnuelle) {
		this.primeNetteAnnuelle = primeNetteAnnuelle;
	}

	@Column(name = "MONTANT_REDUCTION", precision = 15, scale = 3)
	public BigDecimal getMontantReduction() {
		return this.montantReduction;
	}

	public void setMontantReduction(BigDecimal montantReduction) {
		this.montantReduction = montantReduction;
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

	@Column(name = "REDUCTION_PERMIS", precision = 15, scale = 3)
	public BigDecimal getReductionPermis() {
		return this.reductionPermis;
	}

	public void setReductionPermis(BigDecimal reductionPermis) {
		this.reductionPermis = reductionPermis;
	}

	@Column(name = "REDUCTION_FLOTTE", precision = 15, scale = 3)
	public BigDecimal getReductionFlotte() {
		return this.reductionFlotte;
	}

	public void setReductionFlotte(BigDecimal reductionFlotte) {
		this.reductionFlotte = reductionFlotte;
	}

	@Column(name = "AUTRE_REDUCTION", precision = 15, scale = 3)
	public BigDecimal getAutreReduction() {
		return this.autreReduction;
	}

	public void setAutreReduction(BigDecimal autreReduction) {
		this.autreReduction = autreReduction;
	}

	@Column(name = "TAUX_MALUS", precision = 6, scale = 4)
	public BigDecimal getTauxMalus() {
		return this.tauxMalus;
	}

	public void setTauxMalus(BigDecimal tauxMalus) {
		this.tauxMalus = tauxMalus;
	}

	@Column(name = "TAUX_BONUS", precision = 6, scale = 4)
	public BigDecimal getTauxBonus() {
		return this.tauxBonus;
	}

	public void setTauxBonus(BigDecimal tauxBonus) {
		this.tauxBonus = tauxBonus;
	}

	@Column(name = "TAUX_FLOTTE", precision = 6, scale = 4)
	public BigDecimal getTauxFlotte() {
		return this.tauxFlotte;
	}

	public void setTauxFlotte(BigDecimal tauxFlotte) {
		this.tauxFlotte = tauxFlotte;
	}

	@Column(name = "TAUX_PERMIS", precision = 6, scale = 4)
	public BigDecimal getTauxPermis() {
		return this.tauxPermis;
	}

	public void setTauxPermis(BigDecimal tauxPermis) {
		this.tauxPermis = tauxPermis;
	}

	@Column(name = "TAUX_AUTRE_REDUCTION", precision = 6, scale = 4)
	public BigDecimal getTauxAutreReduction() {
		return this.tauxAutreReduction;
	}

	public void setTauxAutreReduction(BigDecimal tauxAutreReduction) {
		this.tauxAutreReduction = tauxAutreReduction;
	}

	@Column(name = "ACCESSOIRE_AUTO", precision = 15, scale = 3)
	public BigDecimal getAccessoireAuto() {
		return this.accessoireAuto;
	}

	public void setAccessoireAuto(BigDecimal accessoireAuto) {
		this.accessoireAuto = accessoireAuto;
	}

	@Column(name = "TAXE_ENR_AUTO", precision = 15, scale = 3)
	public BigDecimal getTaxeEnrAuto() {
		return this.taxeEnrAuto;
	}

	public void setTaxeEnrAuto(BigDecimal taxeEnrAuto) {
		this.taxeEnrAuto = taxeEnrAuto;
	}

	@Column(name = "FGA_AUTO", precision = 15, scale = 3)
	public BigDecimal getFgaAuto() {
		return this.fgaAuto;
	}

	public void setFgaAuto(BigDecimal fgaAuto) {
		this.fgaAuto = fgaAuto;
	}

}
