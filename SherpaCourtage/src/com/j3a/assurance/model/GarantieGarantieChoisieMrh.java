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
 * GarantieGarantieChoisieMrh generated by hbm2java
 */
@Entity
@Table(name = "garantie_garantie_choisie_mrh", catalog = "zeusbd")
public class GarantieGarantieChoisieMrh implements java.io.Serializable {

	private GarantieGarantieChoisieMrhId id;
	private Garantie garantie;
	private GarantieChoisieMrh garantieChoisieMrh;
	private BigDecimal primeannuelleMrh;
	private String natureMrh;
	private BigDecimal tauxMrh;
	private BigDecimal minimumMrh;
	private BigDecimal maximumMrh;
	private BigDecimal primeNetteMrh;
	private BigDecimal tauxFranchiseMr;
	private BigDecimal reductionMrh;
	private String smpMrh;
	private BigDecimal tauxPrimeMrh;
	private BigDecimal tauxLciMrh;
	private BigDecimal tauxRedMrh;
	private BigDecimal tauxGesMrh;
	private BigDecimal tauxComMrh;
	private BigDecimal tauxintermMrh;
	private BigDecimal tauxConsMrh;
	private BigDecimal tauxredfranchMrh;
	private BigDecimal tauxcouvMrh;
	private String observationMrh;
	private Date dateGarantieGarantieChoisieMrh;
	private BigDecimal facteurMrh;
	private BigDecimal franchiseMrh;
	private BigDecimal capitalMrh;

	public GarantieGarantieChoisieMrh() {
	}

	public GarantieGarantieChoisieMrh(GarantieGarantieChoisieMrhId id,
			Garantie garantie, GarantieChoisieMrh garantieChoisieMrh) {
		this.id = id;
		this.garantie = garantie;
		this.garantieChoisieMrh = garantieChoisieMrh;
	}

	public GarantieGarantieChoisieMrh(GarantieGarantieChoisieMrhId id,
			Garantie garantie, GarantieChoisieMrh garantieChoisieMrh,
			BigDecimal primeannuelleMrh, String natureMrh, BigDecimal tauxMrh,
			BigDecimal minimumMrh, BigDecimal maximumMrh,
			BigDecimal primeNetteMrh, BigDecimal tauxFranchiseMr,
			BigDecimal reductionMrh, String smpMrh, BigDecimal tauxPrimeMrh,
			BigDecimal tauxLciMrh, BigDecimal tauxRedMrh,
			BigDecimal tauxGesMrh, BigDecimal tauxComMrh,
			BigDecimal tauxintermMrh, BigDecimal tauxConsMrh,
			BigDecimal tauxredfranchMrh, BigDecimal tauxcouvMrh,
			String observationMrh, Date dateGarantieGarantieChoisieMrh,
			BigDecimal facteurMrh, BigDecimal franchiseMrh,
			BigDecimal capitalMrh) {
		this.id = id;
		this.garantie = garantie;
		this.garantieChoisieMrh = garantieChoisieMrh;
		this.primeannuelleMrh = primeannuelleMrh;
		this.natureMrh = natureMrh;
		this.tauxMrh = tauxMrh;
		this.minimumMrh = minimumMrh;
		this.maximumMrh = maximumMrh;
		this.primeNetteMrh = primeNetteMrh;
		this.tauxFranchiseMr = tauxFranchiseMr;
		this.reductionMrh = reductionMrh;
		this.smpMrh = smpMrh;
		this.tauxPrimeMrh = tauxPrimeMrh;
		this.tauxLciMrh = tauxLciMrh;
		this.tauxRedMrh = tauxRedMrh;
		this.tauxGesMrh = tauxGesMrh;
		this.tauxComMrh = tauxComMrh;
		this.tauxintermMrh = tauxintermMrh;
		this.tauxConsMrh = tauxConsMrh;
		this.tauxredfranchMrh = tauxredfranchMrh;
		this.tauxcouvMrh = tauxcouvMrh;
		this.observationMrh = observationMrh;
		this.dateGarantieGarantieChoisieMrh = dateGarantieGarantieChoisieMrh;
		this.facteurMrh = facteurMrh;
		this.franchiseMrh = franchiseMrh;
		this.capitalMrh = capitalMrh;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codeGarantie", column = @Column(name = "CODE_GARANTIE", nullable = false, length = 12)),
			@AttributeOverride(name = "codeGarantieChoisiemrh", column = @Column(name = "CODE_GARANTIE_CHOISIEMRH", nullable = false, length = 35)) })
	public GarantieGarantieChoisieMrhId getId() {
		return this.id;
	}

	public void setId(GarantieGarantieChoisieMrhId id) {
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
	@JoinColumn(name = "CODE_GARANTIE_CHOISIEMRH", nullable = false, insertable = false, updatable = false)
	public GarantieChoisieMrh getGarantieChoisieMrh() {
		return this.garantieChoisieMrh;
	}

	public void setGarantieChoisieMrh(GarantieChoisieMrh garantieChoisieMrh) {
		this.garantieChoisieMrh = garantieChoisieMrh;
	}

	@Column(name = "PRIMEANNUELLE_MRH", precision = 15, scale = 3)
	public BigDecimal getPrimeannuelleMrh() {
		return this.primeannuelleMrh;
	}

	public void setPrimeannuelleMrh(BigDecimal primeannuelleMrh) {
		this.primeannuelleMrh = primeannuelleMrh;
	}

	@Column(name = "NATURE_MRH", length = 50)
	public String getNatureMrh() {
		return this.natureMrh;
	}

	public void setNatureMrh(String natureMrh) {
		this.natureMrh = natureMrh;
	}

	@Column(name = "TAUX_MRH", precision = 6, scale = 4)
	public BigDecimal getTauxMrh() {
		return this.tauxMrh;
	}

	public void setTauxMrh(BigDecimal tauxMrh) {
		this.tauxMrh = tauxMrh;
	}

	@Column(name = "MINIMUM_MRH", precision = 15, scale = 3)
	public BigDecimal getMinimumMrh() {
		return this.minimumMrh;
	}

	public void setMinimumMrh(BigDecimal minimumMrh) {
		this.minimumMrh = minimumMrh;
	}

	@Column(name = "MAXIMUM_MRH", precision = 15, scale = 3)
	public BigDecimal getMaximumMrh() {
		return this.maximumMrh;
	}

	public void setMaximumMrh(BigDecimal maximumMrh) {
		this.maximumMrh = maximumMrh;
	}

	@Column(name = "PRIME_NETTE_MRH", precision = 15, scale = 3)
	public BigDecimal getPrimeNetteMrh() {
		return this.primeNetteMrh;
	}

	public void setPrimeNetteMrh(BigDecimal primeNetteMrh) {
		this.primeNetteMrh = primeNetteMrh;
	}

	@Column(name = "TAUX_FRANCHISE_MR", precision = 6, scale = 4)
	public BigDecimal getTauxFranchiseMr() {
		return this.tauxFranchiseMr;
	}

	public void setTauxFranchiseMr(BigDecimal tauxFranchiseMr) {
		this.tauxFranchiseMr = tauxFranchiseMr;
	}

	@Column(name = "REDUCTION_MRH", precision = 15, scale = 3)
	public BigDecimal getReductionMrh() {
		return this.reductionMrh;
	}

	public void setReductionMrh(BigDecimal reductionMrh) {
		this.reductionMrh = reductionMrh;
	}

	@Column(name = "SMP_MRH", length = 30)
	public String getSmpMrh() {
		return this.smpMrh;
	}

	public void setSmpMrh(String smpMrh) {
		this.smpMrh = smpMrh;
	}

	@Column(name = "TAUX_PRIME_MRH", precision = 6, scale = 4)
	public BigDecimal getTauxPrimeMrh() {
		return this.tauxPrimeMrh;
	}

	public void setTauxPrimeMrh(BigDecimal tauxPrimeMrh) {
		this.tauxPrimeMrh = tauxPrimeMrh;
	}

	@Column(name = "TAUX_LCI_MRH", precision = 6, scale = 4)
	public BigDecimal getTauxLciMrh() {
		return this.tauxLciMrh;
	}

	public void setTauxLciMrh(BigDecimal tauxLciMrh) {
		this.tauxLciMrh = tauxLciMrh;
	}

	@Column(name = "TAUX_RED_MRH", precision = 6, scale = 4)
	public BigDecimal getTauxRedMrh() {
		return this.tauxRedMrh;
	}

	public void setTauxRedMrh(BigDecimal tauxRedMrh) {
		this.tauxRedMrh = tauxRedMrh;
	}

	@Column(name = "TAUX_GES_MRH", precision = 6, scale = 4)
	public BigDecimal getTauxGesMrh() {
		return this.tauxGesMrh;
	}

	public void setTauxGesMrh(BigDecimal tauxGesMrh) {
		this.tauxGesMrh = tauxGesMrh;
	}

	@Column(name = "TAUX_COM_MRH", precision = 6, scale = 4)
	public BigDecimal getTauxComMrh() {
		return this.tauxComMrh;
	}

	public void setTauxComMrh(BigDecimal tauxComMrh) {
		this.tauxComMrh = tauxComMrh;
	}

	@Column(name = "TAUXINTERM_MRH", precision = 6, scale = 4)
	public BigDecimal getTauxintermMrh() {
		return this.tauxintermMrh;
	}

	public void setTauxintermMrh(BigDecimal tauxintermMrh) {
		this.tauxintermMrh = tauxintermMrh;
	}

	@Column(name = "TAUX_CONS_MRH", precision = 6, scale = 4)
	public BigDecimal getTauxConsMrh() {
		return this.tauxConsMrh;
	}

	public void setTauxConsMrh(BigDecimal tauxConsMrh) {
		this.tauxConsMrh = tauxConsMrh;
	}

	@Column(name = "TAUXREDFRANCH_MRH", precision = 6, scale = 4)
	public BigDecimal getTauxredfranchMrh() {
		return this.tauxredfranchMrh;
	}

	public void setTauxredfranchMrh(BigDecimal tauxredfranchMrh) {
		this.tauxredfranchMrh = tauxredfranchMrh;
	}

	@Column(name = "TAUXCOUV_MRH", precision = 6, scale = 4)
	public BigDecimal getTauxcouvMrh() {
		return this.tauxcouvMrh;
	}

	public void setTauxcouvMrh(BigDecimal tauxcouvMrh) {
		this.tauxcouvMrh = tauxcouvMrh;
	}

	@Column(name = "OBSERVATION_MRH", length = 50)
	public String getObservationMrh() {
		return this.observationMrh;
	}

	public void setObservationMrh(String observationMrh) {
		this.observationMrh = observationMrh;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_GARANTIE_GARANTIE_CHOISIE_MRH", length = 10)
	public Date getDateGarantieGarantieChoisieMrh() {
		return this.dateGarantieGarantieChoisieMrh;
	}

	public void setDateGarantieGarantieChoisieMrh(
			Date dateGarantieGarantieChoisieMrh) {
		this.dateGarantieGarantieChoisieMrh = dateGarantieGarantieChoisieMrh;
	}

	@Column(name = "FACTEUR_MRH", precision = 15, scale = 3)
	public BigDecimal getFacteurMrh() {
		return this.facteurMrh;
	}

	public void setFacteurMrh(BigDecimal facteurMrh) {
		this.facteurMrh = facteurMrh;
	}

	@Column(name = "FRANCHISE_MRH", precision = 15, scale = 3)
	public BigDecimal getFranchiseMrh() {
		return this.franchiseMrh;
	}

	public void setFranchiseMrh(BigDecimal franchiseMrh) {
		this.franchiseMrh = franchiseMrh;
	}

	@Column(name = "CAPITAL_MRH", precision = 15, scale = 3)
	public BigDecimal getCapitalMrh() {
		return this.capitalMrh;
	}

	public void setCapitalMrh(BigDecimal capitalMrh) {
		this.capitalMrh = capitalMrh;
	}

}
