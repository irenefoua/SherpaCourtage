package com.j3a.assurance.model;

// Generated 12 ao�t 2015 16:21:18 by Hibernate Tools 4.3.1

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
 * GarantieChoisieSante generated by hbm2java
 */
@Entity
@Table(name = "garantie_choisie_sante", catalog = "zeusbd")
public class GarantieChoisieSante implements java.io.Serializable {

	private String codeGarantieChoisiesante;
	private AffilieSante affilieSante;
	private String libelleGarantieChoisiesante;
	private Date dateGarantieChoisiesante;
	private BigDecimal primeNettesante;
	private BigDecimal comgessante;
	private BigDecimal comintersante;
	private BigDecimal comconssante;
	private BigDecimal taxesante;
	private BigDecimal reductionsante;
	private BigDecimal comaperSante;
	private BigDecimal comcoasssante;
	private BigDecimal accessoiresante;
	private String codeAvenantSante;
	private Set<GarantieGarantieChoisieSante> garantieGarantieChoisieSantes = new HashSet<GarantieGarantieChoisieSante>(
			0);

	public GarantieChoisieSante() {
	}

	public GarantieChoisieSante(String codeGarantieChoisiesante) {
		this.codeGarantieChoisiesante = codeGarantieChoisiesante;
	}

	public GarantieChoisieSante(String codeGarantieChoisiesante,
			AffilieSante affilieSante, String libelleGarantieChoisiesante,
			Date dateGarantieChoisiesante, BigDecimal primeNettesante,
			BigDecimal comgessante, BigDecimal comintersante,
			BigDecimal comconssante, BigDecimal taxesante,
			BigDecimal reductionsante, BigDecimal comaperSante,
			BigDecimal comcoasssante, BigDecimal accessoiresante,
			String codeAvenantSante,
			Set<GarantieGarantieChoisieSante> garantieGarantieChoisieSantes) {
		this.codeGarantieChoisiesante = codeGarantieChoisiesante;
		this.affilieSante = affilieSante;
		this.libelleGarantieChoisiesante = libelleGarantieChoisiesante;
		this.dateGarantieChoisiesante = dateGarantieChoisiesante;
		this.primeNettesante = primeNettesante;
		this.comgessante = comgessante;
		this.comintersante = comintersante;
		this.comconssante = comconssante;
		this.taxesante = taxesante;
		this.reductionsante = reductionsante;
		this.comaperSante = comaperSante;
		this.comcoasssante = comcoasssante;
		this.accessoiresante = accessoiresante;
		this.codeAvenantSante = codeAvenantSante;
		this.garantieGarantieChoisieSantes = garantieGarantieChoisieSantes;
	}

	@Id
	@Column(name = "CODE_GARANTIE_CHOISIESANTE", unique = true, nullable = false, length = 35)
	public String getCodeGarantieChoisiesante() {
		return this.codeGarantieChoisiesante;
	}

	public void setCodeGarantieChoisiesante(String codeGarantieChoisiesante) {
		this.codeGarantieChoisiesante = codeGarantieChoisiesante;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_AFFILIE")
	public AffilieSante getAffilieSante() {
		return this.affilieSante;
	}

	public void setAffilieSante(AffilieSante affilieSante) {
		this.affilieSante = affilieSante;
	}

	@Column(name = "LIBELLE_GARANTIE_CHOISIESANTE", length = 50)
	public String getLibelleGarantieChoisiesante() {
		return this.libelleGarantieChoisiesante;
	}

	public void setLibelleGarantieChoisiesante(
			String libelleGarantieChoisiesante) {
		this.libelleGarantieChoisiesante = libelleGarantieChoisiesante;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_GARANTIE_CHOISIESANTE", length = 19)
	public Date getDateGarantieChoisiesante() {
		return this.dateGarantieChoisiesante;
	}

	public void setDateGarantieChoisiesante(Date dateGarantieChoisiesante) {
		this.dateGarantieChoisiesante = dateGarantieChoisiesante;
	}

	@Column(name = "PRIME_NETTESANTE", precision = 15, scale = 3)
	public BigDecimal getPrimeNettesante() {
		return this.primeNettesante;
	}

	public void setPrimeNettesante(BigDecimal primeNettesante) {
		this.primeNettesante = primeNettesante;
	}

	@Column(name = "COMGESSANTE", precision = 15, scale = 3)
	public BigDecimal getComgessante() {
		return this.comgessante;
	}

	public void setComgessante(BigDecimal comgessante) {
		this.comgessante = comgessante;
	}

	@Column(name = "COMINTERSANTE", precision = 15, scale = 3)
	public BigDecimal getComintersante() {
		return this.comintersante;
	}

	public void setComintersante(BigDecimal comintersante) {
		this.comintersante = comintersante;
	}

	@Column(name = "COMCONSSANTE", precision = 15, scale = 3)
	public BigDecimal getComconssante() {
		return this.comconssante;
	}

	public void setComconssante(BigDecimal comconssante) {
		this.comconssante = comconssante;
	}

	@Column(name = "TAXESANTE", precision = 15, scale = 3)
	public BigDecimal getTaxesante() {
		return this.taxesante;
	}

	public void setTaxesante(BigDecimal taxesante) {
		this.taxesante = taxesante;
	}

	@Column(name = "REDUCTIONSANTE", precision = 15, scale = 3)
	public BigDecimal getReductionsante() {
		return this.reductionsante;
	}

	public void setReductionsante(BigDecimal reductionsante) {
		this.reductionsante = reductionsante;
	}

	@Column(name = "COMAPER_SANTE", precision = 15, scale = 3)
	public BigDecimal getComaperSante() {
		return this.comaperSante;
	}

	public void setComaperSante(BigDecimal comaperSante) {
		this.comaperSante = comaperSante;
	}

	@Column(name = "COMCOASSSANTE", precision = 15, scale = 3)
	public BigDecimal getComcoasssante() {
		return this.comcoasssante;
	}

	public void setComcoasssante(BigDecimal comcoasssante) {
		this.comcoasssante = comcoasssante;
	}

	@Column(name = "ACCESSOIRESANTE", precision = 15, scale = 3)
	public BigDecimal getAccessoiresante() {
		return this.accessoiresante;
	}

	public void setAccessoiresante(BigDecimal accessoiresante) {
		this.accessoiresante = accessoiresante;
	}

	@Column(name = "CODE_AVENANT_SANTE", length = 28)
	public String getCodeAvenantSante() {
		return this.codeAvenantSante;
	}

	public void setCodeAvenantSante(String codeAvenantSante) {
		this.codeAvenantSante = codeAvenantSante;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garantieChoisieSante")
	public Set<GarantieGarantieChoisieSante> getGarantieGarantieChoisieSantes() {
		return this.garantieGarantieChoisieSantes;
	}

	public void setGarantieGarantieChoisieSantes(
			Set<GarantieGarantieChoisieSante> garantieGarantieChoisieSantes) {
		this.garantieGarantieChoisieSantes = garantieGarantieChoisieSantes;
	}

}
