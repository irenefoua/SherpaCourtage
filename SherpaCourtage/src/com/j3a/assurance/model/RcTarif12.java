package com.j3a.assurance.model;

// Generated 10 ao�t 2015 09:53:29 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * RcTarif12 generated by hbm2java
 */
@Entity
@Table(name = "rc_tarif12", catalog = "zeusbd")
public class RcTarif12 implements java.io.Serializable {

	private String codeRcTarif12;
	private BigDecimal pfEss12Dies1Zone1;
	private BigDecimal pfEss36Dies24Zone1;
	private BigDecimal pfEss79Dies56Zone1;
	private BigDecimal pfEss1011Dies78Zone1;
	private BigDecimal pfEss12Dies9Zone1;
	private BigDecimal pfEss12Dies1Zone2;
	private BigDecimal pfEss36Dies24Zone2;
	private BigDecimal pfEss79Dies56Zone2;
	private BigDecimal pfEss1011Dies78Zone2;
	private BigDecimal pfEss12Dies9Zone2;
	private BigDecimal pfEss12Dies1Zone3;
	private BigDecimal pfEss36Dies24Zone3;
	private BigDecimal pfEss79Dies56Zone3;
	private BigDecimal pfEss1011Dies78Zone3;
	private BigDecimal pfEss12Dies9Zone3;
	private Float tauxMajredt12Remorque;
	private Float tauxMajredt12Permi1;
	private Float tauxMajredt12Permis2;
	private Float tauxMajredt12Statutproa;
	private Float tauxMajredt12Statutprob;
	private Float tauxMajredt12Statutproc;
	private Float tauxMajredt12Statutproautres;
	private Set<Tarif> tarifs = new HashSet<Tarif>(0);

	public RcTarif12() {
	}

	public RcTarif12(String codeRcTarif12) {
		this.codeRcTarif12 = codeRcTarif12;
	}

	public RcTarif12(String codeRcTarif12, BigDecimal pfEss12Dies1Zone1,
			BigDecimal pfEss36Dies24Zone1, BigDecimal pfEss79Dies56Zone1,
			BigDecimal pfEss1011Dies78Zone1, BigDecimal pfEss12Dies9Zone1,
			BigDecimal pfEss12Dies1Zone2, BigDecimal pfEss36Dies24Zone2,
			BigDecimal pfEss79Dies56Zone2, BigDecimal pfEss1011Dies78Zone2,
			BigDecimal pfEss12Dies9Zone2, BigDecimal pfEss12Dies1Zone3,
			BigDecimal pfEss36Dies24Zone3, BigDecimal pfEss79Dies56Zone3,
			BigDecimal pfEss1011Dies78Zone3, BigDecimal pfEss12Dies9Zone3,
			Float tauxMajredt12Remorque, Float tauxMajredt12Permi1,
			Float tauxMajredt12Permis2, Float tauxMajredt12Statutproa,
			Float tauxMajredt12Statutprob, Float tauxMajredt12Statutproc,
			Float tauxMajredt12Statutproautres, Set<Tarif> tarifs) {
		this.codeRcTarif12 = codeRcTarif12;
		this.pfEss12Dies1Zone1 = pfEss12Dies1Zone1;
		this.pfEss36Dies24Zone1 = pfEss36Dies24Zone1;
		this.pfEss79Dies56Zone1 = pfEss79Dies56Zone1;
		this.pfEss1011Dies78Zone1 = pfEss1011Dies78Zone1;
		this.pfEss12Dies9Zone1 = pfEss12Dies9Zone1;
		this.pfEss12Dies1Zone2 = pfEss12Dies1Zone2;
		this.pfEss36Dies24Zone2 = pfEss36Dies24Zone2;
		this.pfEss79Dies56Zone2 = pfEss79Dies56Zone2;
		this.pfEss1011Dies78Zone2 = pfEss1011Dies78Zone2;
		this.pfEss12Dies9Zone2 = pfEss12Dies9Zone2;
		this.pfEss12Dies1Zone3 = pfEss12Dies1Zone3;
		this.pfEss36Dies24Zone3 = pfEss36Dies24Zone3;
		this.pfEss79Dies56Zone3 = pfEss79Dies56Zone3;
		this.pfEss1011Dies78Zone3 = pfEss1011Dies78Zone3;
		this.pfEss12Dies9Zone3 = pfEss12Dies9Zone3;
		this.tauxMajredt12Remorque = tauxMajredt12Remorque;
		this.tauxMajredt12Permi1 = tauxMajredt12Permi1;
		this.tauxMajredt12Permis2 = tauxMajredt12Permis2;
		this.tauxMajredt12Statutproa = tauxMajredt12Statutproa;
		this.tauxMajredt12Statutprob = tauxMajredt12Statutprob;
		this.tauxMajredt12Statutproc = tauxMajredt12Statutproc;
		this.tauxMajredt12Statutproautres = tauxMajredt12Statutproautres;
		this.tarifs = tarifs;
	}

	@Id
	@Column(name = "CODE_RC_TARIF12", unique = true, nullable = false, length = 16)
	public String getCodeRcTarif12() {
		return this.codeRcTarif12;
	}

	public void setCodeRcTarif12(String codeRcTarif12) {
		this.codeRcTarif12 = codeRcTarif12;
	}

	@Column(name = "PF_ESS12_DIES1_ZONE1", precision = 15, scale = 3)
	public BigDecimal getPfEss12Dies1Zone1() {
		return this.pfEss12Dies1Zone1;
	}

	public void setPfEss12Dies1Zone1(BigDecimal pfEss12Dies1Zone1) {
		this.pfEss12Dies1Zone1 = pfEss12Dies1Zone1;
	}

	@Column(name = "PF_ESS36_DIES24_ZONE1", precision = 15, scale = 3)
	public BigDecimal getPfEss36Dies24Zone1() {
		return this.pfEss36Dies24Zone1;
	}

	public void setPfEss36Dies24Zone1(BigDecimal pfEss36Dies24Zone1) {
		this.pfEss36Dies24Zone1 = pfEss36Dies24Zone1;
	}

	@Column(name = "PF_ESS79_DIES56_ZONE1", precision = 15, scale = 3)
	public BigDecimal getPfEss79Dies56Zone1() {
		return this.pfEss79Dies56Zone1;
	}

	public void setPfEss79Dies56Zone1(BigDecimal pfEss79Dies56Zone1) {
		this.pfEss79Dies56Zone1 = pfEss79Dies56Zone1;
	}

	@Column(name = "PF_ESS1011_DIES78_ZONE1", precision = 15, scale = 3)
	public BigDecimal getPfEss1011Dies78Zone1() {
		return this.pfEss1011Dies78Zone1;
	}

	public void setPfEss1011Dies78Zone1(BigDecimal pfEss1011Dies78Zone1) {
		this.pfEss1011Dies78Zone1 = pfEss1011Dies78Zone1;
	}

	@Column(name = "PF_ESS12_DIES9_ZONE1", precision = 15, scale = 3)
	public BigDecimal getPfEss12Dies9Zone1() {
		return this.pfEss12Dies9Zone1;
	}

	public void setPfEss12Dies9Zone1(BigDecimal pfEss12Dies9Zone1) {
		this.pfEss12Dies9Zone1 = pfEss12Dies9Zone1;
	}

	@Column(name = "PF_ESS12_DIES1_ZONE2", precision = 15, scale = 3)
	public BigDecimal getPfEss12Dies1Zone2() {
		return this.pfEss12Dies1Zone2;
	}

	public void setPfEss12Dies1Zone2(BigDecimal pfEss12Dies1Zone2) {
		this.pfEss12Dies1Zone2 = pfEss12Dies1Zone2;
	}

	@Column(name = "PF_ESS36_DIES24_ZONE2", precision = 15, scale = 3)
	public BigDecimal getPfEss36Dies24Zone2() {
		return this.pfEss36Dies24Zone2;
	}

	public void setPfEss36Dies24Zone2(BigDecimal pfEss36Dies24Zone2) {
		this.pfEss36Dies24Zone2 = pfEss36Dies24Zone2;
	}

	@Column(name = "PF_ESS79_DIES56_ZONE2", precision = 15, scale = 3)
	public BigDecimal getPfEss79Dies56Zone2() {
		return this.pfEss79Dies56Zone2;
	}

	public void setPfEss79Dies56Zone2(BigDecimal pfEss79Dies56Zone2) {
		this.pfEss79Dies56Zone2 = pfEss79Dies56Zone2;
	}

	@Column(name = "PF_ESS1011_DIES78_ZONE2", precision = 15, scale = 3)
	public BigDecimal getPfEss1011Dies78Zone2() {
		return this.pfEss1011Dies78Zone2;
	}

	public void setPfEss1011Dies78Zone2(BigDecimal pfEss1011Dies78Zone2) {
		this.pfEss1011Dies78Zone2 = pfEss1011Dies78Zone2;
	}

	@Column(name = "PF_ESS12_DIES9_ZONE2", precision = 15, scale = 3)
	public BigDecimal getPfEss12Dies9Zone2() {
		return this.pfEss12Dies9Zone2;
	}

	public void setPfEss12Dies9Zone2(BigDecimal pfEss12Dies9Zone2) {
		this.pfEss12Dies9Zone2 = pfEss12Dies9Zone2;
	}

	@Column(name = "PF_ESS12_DIES1_ZONE3", precision = 15, scale = 3)
	public BigDecimal getPfEss12Dies1Zone3() {
		return this.pfEss12Dies1Zone3;
	}

	public void setPfEss12Dies1Zone3(BigDecimal pfEss12Dies1Zone3) {
		this.pfEss12Dies1Zone3 = pfEss12Dies1Zone3;
	}

	@Column(name = "PF_ESS36_DIES24_ZONE3", precision = 15, scale = 3)
	public BigDecimal getPfEss36Dies24Zone3() {
		return this.pfEss36Dies24Zone3;
	}

	public void setPfEss36Dies24Zone3(BigDecimal pfEss36Dies24Zone3) {
		this.pfEss36Dies24Zone3 = pfEss36Dies24Zone3;
	}

	@Column(name = "PF_ESS79_DIES56_ZONE3", precision = 15, scale = 3)
	public BigDecimal getPfEss79Dies56Zone3() {
		return this.pfEss79Dies56Zone3;
	}

	public void setPfEss79Dies56Zone3(BigDecimal pfEss79Dies56Zone3) {
		this.pfEss79Dies56Zone3 = pfEss79Dies56Zone3;
	}

	@Column(name = "PF_ESS1011_DIES78_ZONE3", precision = 15, scale = 3)
	public BigDecimal getPfEss1011Dies78Zone3() {
		return this.pfEss1011Dies78Zone3;
	}

	public void setPfEss1011Dies78Zone3(BigDecimal pfEss1011Dies78Zone3) {
		this.pfEss1011Dies78Zone3 = pfEss1011Dies78Zone3;
	}

	@Column(name = "PF_ESS12_DIES9_ZONE3", precision = 15, scale = 3)
	public BigDecimal getPfEss12Dies9Zone3() {
		return this.pfEss12Dies9Zone3;
	}

	public void setPfEss12Dies9Zone3(BigDecimal pfEss12Dies9Zone3) {
		this.pfEss12Dies9Zone3 = pfEss12Dies9Zone3;
	}

	@Column(name = "TAUX_MAJREDT12_REMORQUE", precision = 12, scale = 0)
	public Float getTauxMajredt12Remorque() {
		return this.tauxMajredt12Remorque;
	}

	public void setTauxMajredt12Remorque(Float tauxMajredt12Remorque) {
		this.tauxMajredt12Remorque = tauxMajredt12Remorque;
	}

	@Column(name = "TAUX_MAJREDT12_PERMI1", precision = 12, scale = 0)
	public Float getTauxMajredt12Permi1() {
		return this.tauxMajredt12Permi1;
	}

	public void setTauxMajredt12Permi1(Float tauxMajredt12Permi1) {
		this.tauxMajredt12Permi1 = tauxMajredt12Permi1;
	}

	@Column(name = "TAUX_MAJREDT12_PERMIS2", precision = 12, scale = 0)
	public Float getTauxMajredt12Permis2() {
		return this.tauxMajredt12Permis2;
	}

	public void setTauxMajredt12Permis2(Float tauxMajredt12Permis2) {
		this.tauxMajredt12Permis2 = tauxMajredt12Permis2;
	}

	@Column(name = "TAUX_MAJREDT12_STATUTPROA", precision = 12, scale = 0)
	public Float getTauxMajredt12Statutproa() {
		return this.tauxMajredt12Statutproa;
	}

	public void setTauxMajredt12Statutproa(Float tauxMajredt12Statutproa) {
		this.tauxMajredt12Statutproa = tauxMajredt12Statutproa;
	}

	@Column(name = "TAUX_MAJREDT12_STATUTPROB", precision = 12, scale = 0)
	public Float getTauxMajredt12Statutprob() {
		return this.tauxMajredt12Statutprob;
	}

	public void setTauxMajredt12Statutprob(Float tauxMajredt12Statutprob) {
		this.tauxMajredt12Statutprob = tauxMajredt12Statutprob;
	}

	@Column(name = "TAUX_MAJREDT12_STATUTPROC", precision = 12, scale = 0)
	public Float getTauxMajredt12Statutproc() {
		return this.tauxMajredt12Statutproc;
	}

	public void setTauxMajredt12Statutproc(Float tauxMajredt12Statutproc) {
		this.tauxMajredt12Statutproc = tauxMajredt12Statutproc;
	}

	@Column(name = "TAUX_MAJREDT12_STATUTPROAUTRES", precision = 12, scale = 0)
	public Float getTauxMajredt12Statutproautres() {
		return this.tauxMajredt12Statutproautres;
	}

	public void setTauxMajredt12Statutproautres(
			Float tauxMajredt12Statutproautres) {
		this.tauxMajredt12Statutproautres = tauxMajredt12Statutproautres;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rcTarif12")
	public Set<Tarif> getTarifs() {
		return this.tarifs;
	}

	public void setTarifs(Set<Tarif> tarifs) {
		this.tarifs = tarifs;
	}

}
