package com.j3a.assurance.model;

// Generated 10 ao�t 2015 09:53:29 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * LgBilanActif generated by hbm2java
 */
@Entity
@Table(name = "lg_bilan_actif", catalog = "zeusbd")
public class LgBilanActif implements java.io.Serializable {

	private String idlgbilactif;
	private Feuillebilan feuillebilan;
	private String libellelgact;
	private BigDecimal mtbrutanact;
	private BigDecimal mtampranact;
	private BigDecimal mtnetanact;
	private BigDecimal mtnetanprcact;
	private Integer numordrlgact;

	public LgBilanActif() {
	}

	public LgBilanActif(String idlgbilactif) {
		this.idlgbilactif = idlgbilactif;
	}

	public LgBilanActif(String idlgbilactif, Feuillebilan feuillebilan,
			String libellelgact, BigDecimal mtbrutanact,
			BigDecimal mtampranact, BigDecimal mtnetanact,
			BigDecimal mtnetanprcact, Integer numordrlgact) {
		this.idlgbilactif = idlgbilactif;
		this.feuillebilan = feuillebilan;
		this.libellelgact = libellelgact;
		this.mtbrutanact = mtbrutanact;
		this.mtampranact = mtampranact;
		this.mtnetanact = mtnetanact;
		this.mtnetanprcact = mtnetanprcact;
		this.numordrlgact = numordrlgact;
	}

	@Id
	@Column(name = "IDLGBILACTIF", unique = true, nullable = false, length = 50)
	public String getIdlgbilactif() {
		return this.idlgbilactif;
	}

	public void setIdlgbilactif(String idlgbilactif) {
		this.idlgbilactif = idlgbilactif;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDBILAN")
	public Feuillebilan getFeuillebilan() {
		return this.feuillebilan;
	}

	public void setFeuillebilan(Feuillebilan feuillebilan) {
		this.feuillebilan = feuillebilan;
	}

	@Column(name = "LIBELLELGACT", length = 75)
	public String getLibellelgact() {
		return this.libellelgact;
	}

	public void setLibellelgact(String libellelgact) {
		this.libellelgact = libellelgact;
	}

	@Column(name = "MTBRUTANACT", precision = 15, scale = 3)
	public BigDecimal getMtbrutanact() {
		return this.mtbrutanact;
	}

	public void setMtbrutanact(BigDecimal mtbrutanact) {
		this.mtbrutanact = mtbrutanact;
	}

	@Column(name = "MTAMPRANACT", precision = 15, scale = 3)
	public BigDecimal getMtampranact() {
		return this.mtampranact;
	}

	public void setMtampranact(BigDecimal mtampranact) {
		this.mtampranact = mtampranact;
	}

	@Column(name = "MTNETANACT", precision = 15, scale = 3)
	public BigDecimal getMtnetanact() {
		return this.mtnetanact;
	}

	public void setMtnetanact(BigDecimal mtnetanact) {
		this.mtnetanact = mtnetanact;
	}

	@Column(name = "MTNETANPRCACT", precision = 15, scale = 3)
	public BigDecimal getMtnetanprcact() {
		return this.mtnetanprcact;
	}

	public void setMtnetanprcact(BigDecimal mtnetanprcact) {
		this.mtnetanprcact = mtnetanprcact;
	}

	@Column(name = "NUMORDRLGACT")
	public Integer getNumordrlgact() {
		return this.numordrlgact;
	}

	public void setNumordrlgact(Integer numordrlgact) {
		this.numordrlgact = numordrlgact;
	}

}
