package com.j3a.assurance.model;

// Generated 10 ao�t 2015 15:05:20 by Hibernate Tools 4.3.1

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
 * GarantieGarantieChoisieIa generated by hbm2java
 */
@Entity
@Table(name = "garantie_garantie_choisie_ia", catalog = "zeusbd")
public class GarantieGarantieChoisieIa implements java.io.Serializable {

	private GarantieGarantieChoisieIaId id;
	private Garantie garantie;
	private GarantieChoisieIa garantieChoisieIa;
	private BigDecimal primeAnnuelleIa;
	private String natureIa;
	private BigDecimal tauxIa;
	private BigDecimal maximumIa;
	private BigDecimal minimumIa;
	private BigDecimal primeNetteIa;
	private BigDecimal tauxFranchiseIa;
	private BigDecimal reductionIa;
	private String smpIa;
	private BigDecimal tauxPrimeIa;
	private BigDecimal tauxLciIa;
	private BigDecimal tauxRedIa;
	private BigDecimal tauxGesIa;
	private BigDecimal tauxComIa;
	private BigDecimal tauxintermIa;
	private BigDecimal tauxConsIa;
	private BigDecimal tauxredfranchIa;
	private BigDecimal tauxcouvIa;
	private String observationIa;
	private Date dateGarantieGarantieChoisieIa;
	private BigDecimal facteurIa;
	private BigDecimal franchiseIa;
	private BigDecimal capitalIa;

	public GarantieGarantieChoisieIa() {
	}

	public GarantieGarantieChoisieIa(GarantieGarantieChoisieIaId id,
			Garantie garantie, GarantieChoisieIa garantieChoisieIa) {
		this.id = id;
		this.garantie = garantie;
		this.garantieChoisieIa = garantieChoisieIa;
	}

	public GarantieGarantieChoisieIa(GarantieGarantieChoisieIaId id,
			Garantie garantie, GarantieChoisieIa garantieChoisieIa,
			BigDecimal primeAnnuelleIa, String natureIa, BigDecimal tauxIa,
			BigDecimal maximumIa, BigDecimal minimumIa,
			BigDecimal primeNetteIa, BigDecimal tauxFranchiseIa,
			BigDecimal reductionIa, String smpIa, BigDecimal tauxPrimeIa,
			BigDecimal tauxLciIa, BigDecimal tauxRedIa, BigDecimal tauxGesIa,
			BigDecimal tauxComIa, BigDecimal tauxintermIa,
			BigDecimal tauxConsIa, BigDecimal tauxredfranchIa,
			BigDecimal tauxcouvIa, String observationIa,
			Date dateGarantieGarantieChoisieIa, BigDecimal facteurIa,
			BigDecimal franchiseIa, BigDecimal capitalIa) {
		this.id = id;
		this.garantie = garantie;
		this.garantieChoisieIa = garantieChoisieIa;
		this.primeAnnuelleIa = primeAnnuelleIa;
		this.natureIa = natureIa;
		this.tauxIa = tauxIa;
		this.maximumIa = maximumIa;
		this.minimumIa = minimumIa;
		this.primeNetteIa = primeNetteIa;
		this.tauxFranchiseIa = tauxFranchiseIa;
		this.reductionIa = reductionIa;
		this.smpIa = smpIa;
		this.tauxPrimeIa = tauxPrimeIa;
		this.tauxLciIa = tauxLciIa;
		this.tauxRedIa = tauxRedIa;
		this.tauxGesIa = tauxGesIa;
		this.tauxComIa = tauxComIa;
		this.tauxintermIa = tauxintermIa;
		this.tauxConsIa = tauxConsIa;
		this.tauxredfranchIa = tauxredfranchIa;
		this.tauxcouvIa = tauxcouvIa;
		this.observationIa = observationIa;
		this.dateGarantieGarantieChoisieIa = dateGarantieGarantieChoisieIa;
		this.facteurIa = facteurIa;
		this.franchiseIa = franchiseIa;
		this.capitalIa = capitalIa;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codeGarantie", column = @Column(name = "CODE_GARANTIE", nullable = false, length = 12)),
			@AttributeOverride(name = "codeGrtieChoisieIa", column = @Column(name = "CODE_GRTIE_CHOISIE_IA", nullable = false, length = 50)) })
	public GarantieGarantieChoisieIaId getId() {
		return this.id;
	}

	public void setId(GarantieGarantieChoisieIaId id) {
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
	@JoinColumn(name = "CODE_GRTIE_CHOISIE_IA", nullable = false, insertable = false, updatable = false)
	public GarantieChoisieIa getGarantieChoisieIa() {
		return this.garantieChoisieIa;
	}

	public void setGarantieChoisieIa(GarantieChoisieIa garantieChoisieIa) {
		this.garantieChoisieIa = garantieChoisieIa;
	}

	@Column(name = "PRIME_ANNUELLE_IA", precision = 15, scale = 3)
	public BigDecimal getPrimeAnnuelleIa() {
		return this.primeAnnuelleIa;
	}

	public void setPrimeAnnuelleIa(BigDecimal primeAnnuelleIa) {
		this.primeAnnuelleIa = primeAnnuelleIa;
	}

	@Column(name = "NATURE_IA", length = 50)
	public String getNatureIa() {
		return this.natureIa;
	}

	public void setNatureIa(String natureIa) {
		this.natureIa = natureIa;
	}

	@Column(name = "TAUX_IA", precision = 6, scale = 4)
	public BigDecimal getTauxIa() {
		return this.tauxIa;
	}

	public void setTauxIa(BigDecimal tauxIa) {
		this.tauxIa = tauxIa;
	}

	@Column(name = "MAXIMUM_IA", precision = 15, scale = 3)
	public BigDecimal getMaximumIa() {
		return this.maximumIa;
	}

	public void setMaximumIa(BigDecimal maximumIa) {
		this.maximumIa = maximumIa;
	}

	@Column(name = "MINIMUM_IA", precision = 15, scale = 3)
	public BigDecimal getMinimumIa() {
		return this.minimumIa;
	}

	public void setMinimumIa(BigDecimal minimumIa) {
		this.minimumIa = minimumIa;
	}

	@Column(name = "PRIME_NETTE_IA", precision = 15, scale = 3)
	public BigDecimal getPrimeNetteIa() {
		return this.primeNetteIa;
	}

	public void setPrimeNetteIa(BigDecimal primeNetteIa) {
		this.primeNetteIa = primeNetteIa;
	}

	@Column(name = "TAUX_FRANCHISE_IA", precision = 6, scale = 4)
	public BigDecimal getTauxFranchiseIa() {
		return this.tauxFranchiseIa;
	}

	public void setTauxFranchiseIa(BigDecimal tauxFranchiseIa) {
		this.tauxFranchiseIa = tauxFranchiseIa;
	}

	@Column(name = "REDUCTION_IA", precision = 15, scale = 3)
	public BigDecimal getReductionIa() {
		return this.reductionIa;
	}

	public void setReductionIa(BigDecimal reductionIa) {
		this.reductionIa = reductionIa;
	}

	@Column(name = "SMP_IA", length = 30)
	public String getSmpIa() {
		return this.smpIa;
	}

	public void setSmpIa(String smpIa) {
		this.smpIa = smpIa;
	}

	@Column(name = "TAUX_PRIME_IA", precision = 6, scale = 4)
	public BigDecimal getTauxPrimeIa() {
		return this.tauxPrimeIa;
	}

	public void setTauxPrimeIa(BigDecimal tauxPrimeIa) {
		this.tauxPrimeIa = tauxPrimeIa;
	}

	@Column(name = "TAUX_LCI_IA", precision = 6, scale = 4)
	public BigDecimal getTauxLciIa() {
		return this.tauxLciIa;
	}

	public void setTauxLciIa(BigDecimal tauxLciIa) {
		this.tauxLciIa = tauxLciIa;
	}

	@Column(name = "TAUX_RED_IA", precision = 6, scale = 4)
	public BigDecimal getTauxRedIa() {
		return this.tauxRedIa;
	}

	public void setTauxRedIa(BigDecimal tauxRedIa) {
		this.tauxRedIa = tauxRedIa;
	}

	@Column(name = "TAUX_GES_IA", precision = 6, scale = 4)
	public BigDecimal getTauxGesIa() {
		return this.tauxGesIa;
	}

	public void setTauxGesIa(BigDecimal tauxGesIa) {
		this.tauxGesIa = tauxGesIa;
	}

	@Column(name = "TAUX_COM_IA", precision = 6, scale = 4)
	public BigDecimal getTauxComIa() {
		return this.tauxComIa;
	}

	public void setTauxComIa(BigDecimal tauxComIa) {
		this.tauxComIa = tauxComIa;
	}

	@Column(name = "TAUXINTERM_IA", precision = 6, scale = 4)
	public BigDecimal getTauxintermIa() {
		return this.tauxintermIa;
	}

	public void setTauxintermIa(BigDecimal tauxintermIa) {
		this.tauxintermIa = tauxintermIa;
	}

	@Column(name = "TAUX_CONS_IA", precision = 6, scale = 4)
	public BigDecimal getTauxConsIa() {
		return this.tauxConsIa;
	}

	public void setTauxConsIa(BigDecimal tauxConsIa) {
		this.tauxConsIa = tauxConsIa;
	}

	@Column(name = "TAUXREDFRANCH_IA", precision = 6, scale = 4)
	public BigDecimal getTauxredfranchIa() {
		return this.tauxredfranchIa;
	}

	public void setTauxredfranchIa(BigDecimal tauxredfranchIa) {
		this.tauxredfranchIa = tauxredfranchIa;
	}

	@Column(name = "TAUXCOUV_IA", precision = 6, scale = 4)
	public BigDecimal getTauxcouvIa() {
		return this.tauxcouvIa;
	}

	public void setTauxcouvIa(BigDecimal tauxcouvIa) {
		this.tauxcouvIa = tauxcouvIa;
	}

	@Column(name = "OBSERVATION_IA", length = 50)
	public String getObservationIa() {
		return this.observationIa;
	}

	public void setObservationIa(String observationIa) {
		this.observationIa = observationIa;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_GARANTIE_GARANTIE_CHOISIE_IA", length = 10)
	public Date getDateGarantieGarantieChoisieIa() {
		return this.dateGarantieGarantieChoisieIa;
	}

	public void setDateGarantieGarantieChoisieIa(
			Date dateGarantieGarantieChoisieIa) {
		this.dateGarantieGarantieChoisieIa = dateGarantieGarantieChoisieIa;
	}

	@Column(name = "FACTEUR_IA", precision = 15, scale = 3)
	public BigDecimal getFacteurIa() {
		return this.facteurIa;
	}

	public void setFacteurIa(BigDecimal facteurIa) {
		this.facteurIa = facteurIa;
	}

	@Column(name = "FRANCHISE_IA", precision = 15, scale = 3)
	public BigDecimal getFranchiseIa() {
		return this.franchiseIa;
	}

	public void setFranchiseIa(BigDecimal franchiseIa) {
		this.franchiseIa = franchiseIa;
	}

	@Column(name = "CAPITAL_IA", precision = 15, scale = 3)
	public BigDecimal getCapitalIa() {
		return this.capitalIa;
	}

	public void setCapitalIa(BigDecimal capitalIa) {
		this.capitalIa = capitalIa;
	}

}
