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
 * LgBilanPassif generated by hbm2java
 */
@Entity
@Table(name = "lg_bilan_passif", catalog = "zeusbd")
public class LgBilanPassif implements java.io.Serializable {

	private String idlgbilpassif;
	private Feuillebilan feuillebilan;
	private String libellelgpas;
	private BigDecimal mtbrutanpas;
	private BigDecimal mtampranpas;
	private BigDecimal mtnetanpas;
	private BigDecimal mtnetanprcpas;
	private Integer numordrlgpass;

	public LgBilanPassif() {
	}

	public LgBilanPassif(String idlgbilpassif) {
		this.idlgbilpassif = idlgbilpassif;
	}

	public LgBilanPassif(String idlgbilpassif, Feuillebilan feuillebilan,
			String libellelgpas, BigDecimal mtbrutanpas,
			BigDecimal mtampranpas, BigDecimal mtnetanpas,
			BigDecimal mtnetanprcpas, Integer numordrlgpass) {
		this.idlgbilpassif = idlgbilpassif;
		this.feuillebilan = feuillebilan;
		this.libellelgpas = libellelgpas;
		this.mtbrutanpas = mtbrutanpas;
		this.mtampranpas = mtampranpas;
		this.mtnetanpas = mtnetanpas;
		this.mtnetanprcpas = mtnetanprcpas;
		this.numordrlgpass = numordrlgpass;
	}

	@Id
	@Column(name = "IDLGBILPASSIF", unique = true, nullable = false, length = 50)
	public String getIdlgbilpassif() {
		return this.idlgbilpassif;
	}

	public void setIdlgbilpassif(String idlgbilpassif) {
		this.idlgbilpassif = idlgbilpassif;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDBILAN")
	public Feuillebilan getFeuillebilan() {
		return this.feuillebilan;
	}

	public void setFeuillebilan(Feuillebilan feuillebilan) {
		this.feuillebilan = feuillebilan;
	}

	@Column(name = "LIBELLELGPAS", length = 75)
	public String getLibellelgpas() {
		return this.libellelgpas;
	}

	public void setLibellelgpas(String libellelgpas) {
		this.libellelgpas = libellelgpas;
	}

	@Column(name = "MTBRUTANPAS", precision = 15, scale = 3)
	public BigDecimal getMtbrutanpas() {
		return this.mtbrutanpas;
	}

	public void setMtbrutanpas(BigDecimal mtbrutanpas) {
		this.mtbrutanpas = mtbrutanpas;
	}

	@Column(name = "MTAMPRANPAS", precision = 15, scale = 3)
	public BigDecimal getMtampranpas() {
		return this.mtampranpas;
	}

	public void setMtampranpas(BigDecimal mtampranpas) {
		this.mtampranpas = mtampranpas;
	}

	@Column(name = "MTNETANPAS", precision = 15, scale = 3)
	public BigDecimal getMtnetanpas() {
		return this.mtnetanpas;
	}

	public void setMtnetanpas(BigDecimal mtnetanpas) {
		this.mtnetanpas = mtnetanpas;
	}

	@Column(name = "MTNETANPRCPAS", precision = 15, scale = 3)
	public BigDecimal getMtnetanprcpas() {
		return this.mtnetanprcpas;
	}

	public void setMtnetanprcpas(BigDecimal mtnetanprcpas) {
		this.mtnetanprcpas = mtnetanprcpas;
	}

	@Column(name = "NUMORDRLGPASS")
	public Integer getNumordrlgpass() {
		return this.numordrlgpass;
	}

	public void setNumordrlgpass(Integer numordrlgpass) {
		this.numordrlgpass = numordrlgpass;
	}

}
