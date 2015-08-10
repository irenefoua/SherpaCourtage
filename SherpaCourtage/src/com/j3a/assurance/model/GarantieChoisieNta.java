package com.j3a.assurance.model;

// Generated 10 ao�t 2015 09:53:29 by Hibernate Tools 4.3.1

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
 * GarantieChoisieNta generated by hbm2java
 */
@Entity
@Table(name = "garantie_choisie_nta", catalog = "zeusbd")
public class GarantieChoisieNta implements java.io.Serializable {

	private String codeGarantieChoisienta;
	private RisqueNta risqueNta;
	private String libelleGarantieChoisienta;
	private Date dateGarantieChoisienta;
	private BigDecimal primeNettenta;
	private BigDecimal comgesnta;
	private BigDecimal cominternta;
	private BigDecimal comconsnta;
	private BigDecimal taxenta;
	private BigDecimal reductionnta;
	private BigDecimal comaperNta;
	private BigDecimal comcoassnta;
	private BigDecimal accessoirenta;
	private String codeAvenantNta;
	private Set<GarantieGarantieChoisieNta> garantieGarantieChoisieNtas = new HashSet<GarantieGarantieChoisieNta>(
			0);

	public GarantieChoisieNta() {
	}

	public GarantieChoisieNta(String codeGarantieChoisienta) {
		this.codeGarantieChoisienta = codeGarantieChoisienta;
	}

	public GarantieChoisieNta(String codeGarantieChoisienta,
			RisqueNta risqueNta, String libelleGarantieChoisienta,
			Date dateGarantieChoisienta, BigDecimal primeNettenta,
			BigDecimal comgesnta, BigDecimal cominternta,
			BigDecimal comconsnta, BigDecimal taxenta, BigDecimal reductionnta,
			BigDecimal comaperNta, BigDecimal comcoassnta,
			BigDecimal accessoirenta, String codeAvenantNta,
			Set<GarantieGarantieChoisieNta> garantieGarantieChoisieNtas) {
		this.codeGarantieChoisienta = codeGarantieChoisienta;
		this.risqueNta = risqueNta;
		this.libelleGarantieChoisienta = libelleGarantieChoisienta;
		this.dateGarantieChoisienta = dateGarantieChoisienta;
		this.primeNettenta = primeNettenta;
		this.comgesnta = comgesnta;
		this.cominternta = cominternta;
		this.comconsnta = comconsnta;
		this.taxenta = taxenta;
		this.reductionnta = reductionnta;
		this.comaperNta = comaperNta;
		this.comcoassnta = comcoassnta;
		this.accessoirenta = accessoirenta;
		this.codeAvenantNta = codeAvenantNta;
		this.garantieGarantieChoisieNtas = garantieGarantieChoisieNtas;
	}

	@Id
	@Column(name = "CODE_GARANTIE_CHOISIENTA", unique = true, nullable = false, length = 35)
	public String getCodeGarantieChoisienta() {
		return this.codeGarantieChoisienta;
	}

	public void setCodeGarantieChoisienta(String codeGarantieChoisienta) {
		this.codeGarantieChoisienta = codeGarantieChoisienta;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_RISQUENTA")
	public RisqueNta getRisqueNta() {
		return this.risqueNta;
	}

	public void setRisqueNta(RisqueNta risqueNta) {
		this.risqueNta = risqueNta;
	}

	@Column(name = "LIBELLE_GARANTIE_CHOISIENTA", length = 50)
	public String getLibelleGarantieChoisienta() {
		return this.libelleGarantieChoisienta;
	}

	public void setLibelleGarantieChoisienta(String libelleGarantieChoisienta) {
		this.libelleGarantieChoisienta = libelleGarantieChoisienta;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_GARANTIE_CHOISIENTA", length = 10)
	public Date getDateGarantieChoisienta() {
		return this.dateGarantieChoisienta;
	}

	public void setDateGarantieChoisienta(Date dateGarantieChoisienta) {
		this.dateGarantieChoisienta = dateGarantieChoisienta;
	}

	@Column(name = "PRIME_NETTENTA", precision = 15, scale = 3)
	public BigDecimal getPrimeNettenta() {
		return this.primeNettenta;
	}

	public void setPrimeNettenta(BigDecimal primeNettenta) {
		this.primeNettenta = primeNettenta;
	}

	@Column(name = "COMGESNTA", precision = 15, scale = 3)
	public BigDecimal getComgesnta() {
		return this.comgesnta;
	}

	public void setComgesnta(BigDecimal comgesnta) {
		this.comgesnta = comgesnta;
	}

	@Column(name = "COMINTERNTA", precision = 15, scale = 3)
	public BigDecimal getCominternta() {
		return this.cominternta;
	}

	public void setCominternta(BigDecimal cominternta) {
		this.cominternta = cominternta;
	}

	@Column(name = "COMCONSNTA", precision = 15, scale = 3)
	public BigDecimal getComconsnta() {
		return this.comconsnta;
	}

	public void setComconsnta(BigDecimal comconsnta) {
		this.comconsnta = comconsnta;
	}

	@Column(name = "TAXENTA", precision = 15, scale = 3)
	public BigDecimal getTaxenta() {
		return this.taxenta;
	}

	public void setTaxenta(BigDecimal taxenta) {
		this.taxenta = taxenta;
	}

	@Column(name = "REDUCTIONNTA", precision = 15, scale = 3)
	public BigDecimal getReductionnta() {
		return this.reductionnta;
	}

	public void setReductionnta(BigDecimal reductionnta) {
		this.reductionnta = reductionnta;
	}

	@Column(name = "COMAPER_NTA", precision = 15, scale = 3)
	public BigDecimal getComaperNta() {
		return this.comaperNta;
	}

	public void setComaperNta(BigDecimal comaperNta) {
		this.comaperNta = comaperNta;
	}

	@Column(name = "COMCOASSNTA", precision = 15, scale = 3)
	public BigDecimal getComcoassnta() {
		return this.comcoassnta;
	}

	public void setComcoassnta(BigDecimal comcoassnta) {
		this.comcoassnta = comcoassnta;
	}

	@Column(name = "ACCESSOIRENTA", precision = 15, scale = 3)
	public BigDecimal getAccessoirenta() {
		return this.accessoirenta;
	}

	public void setAccessoirenta(BigDecimal accessoirenta) {
		this.accessoirenta = accessoirenta;
	}

	@Column(name = "CODE_AVENANT_NTA", length = 28)
	public String getCodeAvenantNta() {
		return this.codeAvenantNta;
	}

	public void setCodeAvenantNta(String codeAvenantNta) {
		this.codeAvenantNta = codeAvenantNta;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garantieChoisieNta")
	public Set<GarantieGarantieChoisieNta> getGarantieGarantieChoisieNtas() {
		return this.garantieGarantieChoisieNtas;
	}

	public void setGarantieGarantieChoisieNtas(
			Set<GarantieGarantieChoisieNta> garantieGarantieChoisieNtas) {
		this.garantieGarantieChoisieNtas = garantieGarantieChoisieNtas;
	}

}
