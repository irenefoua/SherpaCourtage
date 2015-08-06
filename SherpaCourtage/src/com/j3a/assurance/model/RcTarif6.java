package com.j3a.assurance.model;

// Generated 6 juil. 2015 11:25:44 by Hibernate Tools 4.3.1

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
 * RcTarif6 generated by hbm2java
 */
@Entity
@Table(name = "rc_tarif6", catalog = "zeusbd")
public class RcTarif6 implements java.io.Serializable {

	private String codeRcTarif6;
	private BigDecimal vhl1Zone1;
	private BigDecimal vhl1Zone2;
	private BigDecimal vhl1Zone3;
	private BigDecimal vhl2Zone1;
	private BigDecimal vhl2Zone2;
	private BigDecimal vhl2Zone3;
	private BigDecimal vhl3Zone1;
	private BigDecimal vhl3Zone2;
	private BigDecimal vhl3Zone3;
	private BigDecimal vhl4Zone1;
	private BigDecimal vhl4Zone2;
	private BigDecimal vhl4Zone3;
	private BigDecimal vhl5Zone1;
	private BigDecimal vhl5Zone2;
	private BigDecimal vhl5Zone3;
	private BigDecimal cyclo1Zone1;
	private BigDecimal cyclo1Zone2;
	private BigDecimal cyclo1Zone3;
	private BigDecimal cyclo2Zone1;
	private BigDecimal cyclo2Zone2;
	private BigDecimal cyclo2Zone3;
	private BigDecimal cyclo3Zone1;
	private BigDecimal cyclo3Zone2;
	private BigDecimal cyclo3Zone3;
	private BigDecimal cyclo4Zone1;
	private BigDecimal cyclo4Zone2;
	private BigDecimal cyclo4Zone3;
	private BigDecimal cyclo5Zone1;
	private BigDecimal cyclo5Zone2;
	private BigDecimal cyclo5Zone3;
	private BigDecimal autresVhl1Zone1;
	private BigDecimal autresVhl1Zone2;
	private BigDecimal autresVhl1Zone3;
	private BigDecimal autresVhl2Zone1;
	private BigDecimal autresVhl2Zone2;
	private BigDecimal autresVhl2Zone3;
	private BigDecimal autresVhl3Zone1;
	private BigDecimal autresVhl3Zone2;
	private BigDecimal autresVhl3Zone3;
	private BigDecimal autresVhl4Zone1;
	private BigDecimal autresVhl4Zone2;
	private BigDecimal autresVhl4Zone3;
	private BigDecimal autresVhl5Zone1;
	private BigDecimal autresVhl5Zone2;
	private BigDecimal autresVhl5Zone3;
	private Set<Tarif> tarifs = new HashSet<Tarif>(0);

	public RcTarif6() {
	}

	public RcTarif6(String codeRcTarif6) {
		this.codeRcTarif6 = codeRcTarif6;
	}

	public RcTarif6(String codeRcTarif6, BigDecimal vhl1Zone1,
			BigDecimal vhl1Zone2, BigDecimal vhl1Zone3, BigDecimal vhl2Zone1,
			BigDecimal vhl2Zone2, BigDecimal vhl2Zone3, BigDecimal vhl3Zone1,
			BigDecimal vhl3Zone2, BigDecimal vhl3Zone3, BigDecimal vhl4Zone1,
			BigDecimal vhl4Zone2, BigDecimal vhl4Zone3, BigDecimal vhl5Zone1,
			BigDecimal vhl5Zone2, BigDecimal vhl5Zone3, BigDecimal cyclo1Zone1,
			BigDecimal cyclo1Zone2, BigDecimal cyclo1Zone3,
			BigDecimal cyclo2Zone1, BigDecimal cyclo2Zone2,
			BigDecimal cyclo2Zone3, BigDecimal cyclo3Zone1,
			BigDecimal cyclo3Zone2, BigDecimal cyclo3Zone3,
			BigDecimal cyclo4Zone1, BigDecimal cyclo4Zone2,
			BigDecimal cyclo4Zone3, BigDecimal cyclo5Zone1,
			BigDecimal cyclo5Zone2, BigDecimal cyclo5Zone3,
			BigDecimal autresVhl1Zone1, BigDecimal autresVhl1Zone2,
			BigDecimal autresVhl1Zone3, BigDecimal autresVhl2Zone1,
			BigDecimal autresVhl2Zone2, BigDecimal autresVhl2Zone3,
			BigDecimal autresVhl3Zone1, BigDecimal autresVhl3Zone2,
			BigDecimal autresVhl3Zone3, BigDecimal autresVhl4Zone1,
			BigDecimal autresVhl4Zone2, BigDecimal autresVhl4Zone3,
			BigDecimal autresVhl5Zone1, BigDecimal autresVhl5Zone2,
			BigDecimal autresVhl5Zone3, Set<Tarif> tarifs) {
		this.codeRcTarif6 = codeRcTarif6;
		this.vhl1Zone1 = vhl1Zone1;
		this.vhl1Zone2 = vhl1Zone2;
		this.vhl1Zone3 = vhl1Zone3;
		this.vhl2Zone1 = vhl2Zone1;
		this.vhl2Zone2 = vhl2Zone2;
		this.vhl2Zone3 = vhl2Zone3;
		this.vhl3Zone1 = vhl3Zone1;
		this.vhl3Zone2 = vhl3Zone2;
		this.vhl3Zone3 = vhl3Zone3;
		this.vhl4Zone1 = vhl4Zone1;
		this.vhl4Zone2 = vhl4Zone2;
		this.vhl4Zone3 = vhl4Zone3;
		this.vhl5Zone1 = vhl5Zone1;
		this.vhl5Zone2 = vhl5Zone2;
		this.vhl5Zone3 = vhl5Zone3;
		this.cyclo1Zone1 = cyclo1Zone1;
		this.cyclo1Zone2 = cyclo1Zone2;
		this.cyclo1Zone3 = cyclo1Zone3;
		this.cyclo2Zone1 = cyclo2Zone1;
		this.cyclo2Zone2 = cyclo2Zone2;
		this.cyclo2Zone3 = cyclo2Zone3;
		this.cyclo3Zone1 = cyclo3Zone1;
		this.cyclo3Zone2 = cyclo3Zone2;
		this.cyclo3Zone3 = cyclo3Zone3;
		this.cyclo4Zone1 = cyclo4Zone1;
		this.cyclo4Zone2 = cyclo4Zone2;
		this.cyclo4Zone3 = cyclo4Zone3;
		this.cyclo5Zone1 = cyclo5Zone1;
		this.cyclo5Zone2 = cyclo5Zone2;
		this.cyclo5Zone3 = cyclo5Zone3;
		this.autresVhl1Zone1 = autresVhl1Zone1;
		this.autresVhl1Zone2 = autresVhl1Zone2;
		this.autresVhl1Zone3 = autresVhl1Zone3;
		this.autresVhl2Zone1 = autresVhl2Zone1;
		this.autresVhl2Zone2 = autresVhl2Zone2;
		this.autresVhl2Zone3 = autresVhl2Zone3;
		this.autresVhl3Zone1 = autresVhl3Zone1;
		this.autresVhl3Zone2 = autresVhl3Zone2;
		this.autresVhl3Zone3 = autresVhl3Zone3;
		this.autresVhl4Zone1 = autresVhl4Zone1;
		this.autresVhl4Zone2 = autresVhl4Zone2;
		this.autresVhl4Zone3 = autresVhl4Zone3;
		this.autresVhl5Zone1 = autresVhl5Zone1;
		this.autresVhl5Zone2 = autresVhl5Zone2;
		this.autresVhl5Zone3 = autresVhl5Zone3;
		this.tarifs = tarifs;
	}

	@Id
	@Column(name = "CODE_RC_TARIF6", unique = true, nullable = false, length = 16)
	public String getCodeRcTarif6() {
		return this.codeRcTarif6;
	}

	public void setCodeRcTarif6(String codeRcTarif6) {
		this.codeRcTarif6 = codeRcTarif6;
	}

	@Column(name = "VHL1_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhl1Zone1() {
		return this.vhl1Zone1;
	}

	public void setVhl1Zone1(BigDecimal vhl1Zone1) {
		this.vhl1Zone1 = vhl1Zone1;
	}

	@Column(name = "VHL1_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhl1Zone2() {
		return this.vhl1Zone2;
	}

	public void setVhl1Zone2(BigDecimal vhl1Zone2) {
		this.vhl1Zone2 = vhl1Zone2;
	}

	@Column(name = "VHL1_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhl1Zone3() {
		return this.vhl1Zone3;
	}

	public void setVhl1Zone3(BigDecimal vhl1Zone3) {
		this.vhl1Zone3 = vhl1Zone3;
	}

	@Column(name = "VHL2_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhl2Zone1() {
		return this.vhl2Zone1;
	}

	public void setVhl2Zone1(BigDecimal vhl2Zone1) {
		this.vhl2Zone1 = vhl2Zone1;
	}

	@Column(name = "VHL2_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhl2Zone2() {
		return this.vhl2Zone2;
	}

	public void setVhl2Zone2(BigDecimal vhl2Zone2) {
		this.vhl2Zone2 = vhl2Zone2;
	}

	@Column(name = "VHL2_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhl2Zone3() {
		return this.vhl2Zone3;
	}

	public void setVhl2Zone3(BigDecimal vhl2Zone3) {
		this.vhl2Zone3 = vhl2Zone3;
	}

	@Column(name = "VHL3_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhl3Zone1() {
		return this.vhl3Zone1;
	}

	public void setVhl3Zone1(BigDecimal vhl3Zone1) {
		this.vhl3Zone1 = vhl3Zone1;
	}

	@Column(name = "VHL3_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhl3Zone2() {
		return this.vhl3Zone2;
	}

	public void setVhl3Zone2(BigDecimal vhl3Zone2) {
		this.vhl3Zone2 = vhl3Zone2;
	}

	@Column(name = "VHL3_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhl3Zone3() {
		return this.vhl3Zone3;
	}

	public void setVhl3Zone3(BigDecimal vhl3Zone3) {
		this.vhl3Zone3 = vhl3Zone3;
	}

	@Column(name = "VHL4_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhl4Zone1() {
		return this.vhl4Zone1;
	}

	public void setVhl4Zone1(BigDecimal vhl4Zone1) {
		this.vhl4Zone1 = vhl4Zone1;
	}

	@Column(name = "VHL4_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhl4Zone2() {
		return this.vhl4Zone2;
	}

	public void setVhl4Zone2(BigDecimal vhl4Zone2) {
		this.vhl4Zone2 = vhl4Zone2;
	}

	@Column(name = "VHL4_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhl4Zone3() {
		return this.vhl4Zone3;
	}

	public void setVhl4Zone3(BigDecimal vhl4Zone3) {
		this.vhl4Zone3 = vhl4Zone3;
	}

	@Column(name = "VHL5_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhl5Zone1() {
		return this.vhl5Zone1;
	}

	public void setVhl5Zone1(BigDecimal vhl5Zone1) {
		this.vhl5Zone1 = vhl5Zone1;
	}

	@Column(name = "VHL5_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhl5Zone2() {
		return this.vhl5Zone2;
	}

	public void setVhl5Zone2(BigDecimal vhl5Zone2) {
		this.vhl5Zone2 = vhl5Zone2;
	}

	@Column(name = "VHL5_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhl5Zone3() {
		return this.vhl5Zone3;
	}

	public void setVhl5Zone3(BigDecimal vhl5Zone3) {
		this.vhl5Zone3 = vhl5Zone3;
	}

	@Column(name = "CYCLO1_ZONE1", precision = 15, scale = 3)
	public BigDecimal getCyclo1Zone1() {
		return this.cyclo1Zone1;
	}

	public void setCyclo1Zone1(BigDecimal cyclo1Zone1) {
		this.cyclo1Zone1 = cyclo1Zone1;
	}

	@Column(name = "CYCLO1_ZONE2", precision = 15, scale = 3)
	public BigDecimal getCyclo1Zone2() {
		return this.cyclo1Zone2;
	}

	public void setCyclo1Zone2(BigDecimal cyclo1Zone2) {
		this.cyclo1Zone2 = cyclo1Zone2;
	}

	@Column(name = "CYCLO1_ZONE3", precision = 15, scale = 3)
	public BigDecimal getCyclo1Zone3() {
		return this.cyclo1Zone3;
	}

	public void setCyclo1Zone3(BigDecimal cyclo1Zone3) {
		this.cyclo1Zone3 = cyclo1Zone3;
	}

	@Column(name = "CYCLO2_ZONE1", precision = 15, scale = 3)
	public BigDecimal getCyclo2Zone1() {
		return this.cyclo2Zone1;
	}

	public void setCyclo2Zone1(BigDecimal cyclo2Zone1) {
		this.cyclo2Zone1 = cyclo2Zone1;
	}

	@Column(name = "CYCLO2_ZONE2", precision = 15, scale = 3)
	public BigDecimal getCyclo2Zone2() {
		return this.cyclo2Zone2;
	}

	public void setCyclo2Zone2(BigDecimal cyclo2Zone2) {
		this.cyclo2Zone2 = cyclo2Zone2;
	}

	@Column(name = "CYCLO2_ZONE3", precision = 15, scale = 3)
	public BigDecimal getCyclo2Zone3() {
		return this.cyclo2Zone3;
	}

	public void setCyclo2Zone3(BigDecimal cyclo2Zone3) {
		this.cyclo2Zone3 = cyclo2Zone3;
	}

	@Column(name = "CYCLO3_ZONE1", precision = 15, scale = 3)
	public BigDecimal getCyclo3Zone1() {
		return this.cyclo3Zone1;
	}

	public void setCyclo3Zone1(BigDecimal cyclo3Zone1) {
		this.cyclo3Zone1 = cyclo3Zone1;
	}

	@Column(name = "CYCLO3_ZONE2", precision = 15, scale = 3)
	public BigDecimal getCyclo3Zone2() {
		return this.cyclo3Zone2;
	}

	public void setCyclo3Zone2(BigDecimal cyclo3Zone2) {
		this.cyclo3Zone2 = cyclo3Zone2;
	}

	@Column(name = "CYCLO3_ZONE3", precision = 15, scale = 3)
	public BigDecimal getCyclo3Zone3() {
		return this.cyclo3Zone3;
	}

	public void setCyclo3Zone3(BigDecimal cyclo3Zone3) {
		this.cyclo3Zone3 = cyclo3Zone3;
	}

	@Column(name = "CYCLO4_ZONE1", precision = 15, scale = 3)
	public BigDecimal getCyclo4Zone1() {
		return this.cyclo4Zone1;
	}

	public void setCyclo4Zone1(BigDecimal cyclo4Zone1) {
		this.cyclo4Zone1 = cyclo4Zone1;
	}

	@Column(name = "CYCLO4_ZONE2", precision = 15, scale = 3)
	public BigDecimal getCyclo4Zone2() {
		return this.cyclo4Zone2;
	}

	public void setCyclo4Zone2(BigDecimal cyclo4Zone2) {
		this.cyclo4Zone2 = cyclo4Zone2;
	}

	@Column(name = "CYCLO4_ZONE3", precision = 15, scale = 3)
	public BigDecimal getCyclo4Zone3() {
		return this.cyclo4Zone3;
	}

	public void setCyclo4Zone3(BigDecimal cyclo4Zone3) {
		this.cyclo4Zone3 = cyclo4Zone3;
	}

	@Column(name = "CYCLO5_ZONE1", precision = 15, scale = 3)
	public BigDecimal getCyclo5Zone1() {
		return this.cyclo5Zone1;
	}

	public void setCyclo5Zone1(BigDecimal cyclo5Zone1) {
		this.cyclo5Zone1 = cyclo5Zone1;
	}

	@Column(name = "CYCLO5_ZONE2", precision = 15, scale = 3)
	public BigDecimal getCyclo5Zone2() {
		return this.cyclo5Zone2;
	}

	public void setCyclo5Zone2(BigDecimal cyclo5Zone2) {
		this.cyclo5Zone2 = cyclo5Zone2;
	}

	@Column(name = "CYCLO5_ZONE3", precision = 15, scale = 3)
	public BigDecimal getCyclo5Zone3() {
		return this.cyclo5Zone3;
	}

	public void setCyclo5Zone3(BigDecimal cyclo5Zone3) {
		this.cyclo5Zone3 = cyclo5Zone3;
	}

	@Column(name = "AUTRES_VHL1_ZONE1", precision = 15, scale = 3)
	public BigDecimal getAutresVhl1Zone1() {
		return this.autresVhl1Zone1;
	}

	public void setAutresVhl1Zone1(BigDecimal autresVhl1Zone1) {
		this.autresVhl1Zone1 = autresVhl1Zone1;
	}

	@Column(name = "AUTRES_VHL1_ZONE2", precision = 15, scale = 3)
	public BigDecimal getAutresVhl1Zone2() {
		return this.autresVhl1Zone2;
	}

	public void setAutresVhl1Zone2(BigDecimal autresVhl1Zone2) {
		this.autresVhl1Zone2 = autresVhl1Zone2;
	}

	@Column(name = "AUTRES_VHL1_ZONE3", precision = 15, scale = 3)
	public BigDecimal getAutresVhl1Zone3() {
		return this.autresVhl1Zone3;
	}

	public void setAutresVhl1Zone3(BigDecimal autresVhl1Zone3) {
		this.autresVhl1Zone3 = autresVhl1Zone3;
	}

	@Column(name = "AUTRES_VHL2_ZONE1", precision = 15, scale = 3)
	public BigDecimal getAutresVhl2Zone1() {
		return this.autresVhl2Zone1;
	}

	public void setAutresVhl2Zone1(BigDecimal autresVhl2Zone1) {
		this.autresVhl2Zone1 = autresVhl2Zone1;
	}

	@Column(name = "AUTRES_VHL2_ZONE2", precision = 15, scale = 3)
	public BigDecimal getAutresVhl2Zone2() {
		return this.autresVhl2Zone2;
	}

	public void setAutresVhl2Zone2(BigDecimal autresVhl2Zone2) {
		this.autresVhl2Zone2 = autresVhl2Zone2;
	}

	@Column(name = "AUTRES_VHL2_ZONE3", precision = 15, scale = 3)
	public BigDecimal getAutresVhl2Zone3() {
		return this.autresVhl2Zone3;
	}

	public void setAutresVhl2Zone3(BigDecimal autresVhl2Zone3) {
		this.autresVhl2Zone3 = autresVhl2Zone3;
	}

	@Column(name = "AUTRES_VHL3_ZONE1", precision = 15, scale = 3)
	public BigDecimal getAutresVhl3Zone1() {
		return this.autresVhl3Zone1;
	}

	public void setAutresVhl3Zone1(BigDecimal autresVhl3Zone1) {
		this.autresVhl3Zone1 = autresVhl3Zone1;
	}

	@Column(name = "AUTRES_VHL3_ZONE2", precision = 15, scale = 3)
	public BigDecimal getAutresVhl3Zone2() {
		return this.autresVhl3Zone2;
	}

	public void setAutresVhl3Zone2(BigDecimal autresVhl3Zone2) {
		this.autresVhl3Zone2 = autresVhl3Zone2;
	}

	@Column(name = "AUTRES_VHL3_ZONE3", precision = 15, scale = 3)
	public BigDecimal getAutresVhl3Zone3() {
		return this.autresVhl3Zone3;
	}

	public void setAutresVhl3Zone3(BigDecimal autresVhl3Zone3) {
		this.autresVhl3Zone3 = autresVhl3Zone3;
	}

	@Column(name = "AUTRES_VHL4_ZONE1", precision = 15, scale = 3)
	public BigDecimal getAutresVhl4Zone1() {
		return this.autresVhl4Zone1;
	}

	public void setAutresVhl4Zone1(BigDecimal autresVhl4Zone1) {
		this.autresVhl4Zone1 = autresVhl4Zone1;
	}

	@Column(name = "AUTRES_VHL4_ZONE2", precision = 15, scale = 3)
	public BigDecimal getAutresVhl4Zone2() {
		return this.autresVhl4Zone2;
	}

	public void setAutresVhl4Zone2(BigDecimal autresVhl4Zone2) {
		this.autresVhl4Zone2 = autresVhl4Zone2;
	}

	@Column(name = "AUTRES_VHL4_ZONE3", precision = 15, scale = 3)
	public BigDecimal getAutresVhl4Zone3() {
		return this.autresVhl4Zone3;
	}

	public void setAutresVhl4Zone3(BigDecimal autresVhl4Zone3) {
		this.autresVhl4Zone3 = autresVhl4Zone3;
	}

	@Column(name = "AUTRES_VHL5_ZONE1", precision = 15, scale = 3)
	public BigDecimal getAutresVhl5Zone1() {
		return this.autresVhl5Zone1;
	}

	public void setAutresVhl5Zone1(BigDecimal autresVhl5Zone1) {
		this.autresVhl5Zone1 = autresVhl5Zone1;
	}

	@Column(name = "AUTRES_VHL5_ZONE2", precision = 15, scale = 3)
	public BigDecimal getAutresVhl5Zone2() {
		return this.autresVhl5Zone2;
	}

	public void setAutresVhl5Zone2(BigDecimal autresVhl5Zone2) {
		this.autresVhl5Zone2 = autresVhl5Zone2;
	}

	@Column(name = "AUTRES_VHL5_ZONE3", precision = 15, scale = 3)
	public BigDecimal getAutresVhl5Zone3() {
		return this.autresVhl5Zone3;
	}

	public void setAutresVhl5Zone3(BigDecimal autresVhl5Zone3) {
		this.autresVhl5Zone3 = autresVhl5Zone3;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rcTarif6")
	public Set<Tarif> getTarifs() {
		return this.tarifs;
	}

	public void setTarifs(Set<Tarif> tarifs) {
		this.tarifs = tarifs;
	}

}
