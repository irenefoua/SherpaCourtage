package com.j3a.assurance.model;

// Generated 6 ao�t 2015 16:35:56 by Hibernate Tools 4.3.1

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
 * RcTarif8 generated by hbm2java
 */
@Entity
@Table(name = "rc_tarif8", catalog = "zeusbd")
public class RcTarif8 implements java.io.Serializable {

	private String codeRcTarif8;
	private BigDecimal vhlTourDies0Ess12Zone1;
	private BigDecimal vhlTourDies0Ess12Zone2;
	private BigDecimal vhlTourDies0Ess12Zone3;
	private BigDecimal vhlTourDies24Ess36Zone1;
	private BigDecimal vhlTourDies24Ess36Zone2;
	private BigDecimal vhlTourDies24Ess36Zone3;
	private BigDecimal vhlTourDies56Ess79Zone1;
	private BigDecimal vhlTourDies56Ess79Zone2;
	private BigDecimal vhlTourDies56Ess79Zone3;
	private BigDecimal vhlTourDies78Ess1011Zone1;
	private BigDecimal vhlTourDies78Ess1011Zone2;
	private BigDecimal vhlTourDies78Ess1011Zone3;
	private BigDecimal vhlTourDies9Ess12Zone1;
	private BigDecimal vhlTourDies9Ess12Zone2;
	private BigDecimal vhlTourDies9Ess12Zone3;
	private BigDecimal vhlCat21Zone1;
	private BigDecimal vhlCat21Zone2;
	private BigDecimal vhlCat21Zone3;
	private BigDecimal vhlCat22Zone1;
	private BigDecimal vhlCat22Zone2;
	private BigDecimal vhlCat22Zone3;
	private BigDecimal vhlCat23Zone1;
	private BigDecimal vhlCat23Zone2;
	private BigDecimal vhlCat23Zone3;
	private BigDecimal vhlCat24Zone1;
	private BigDecimal vhlCat24Zone2;
	private BigDecimal vhlCat24Zone3;
	private BigDecimal vhlCat25Zone1;
	private BigDecimal vhlCat25Zone2;
	private BigDecimal vhlCat25Zone3;
	private BigDecimal vhlCat26Zone1;
	private BigDecimal vhlCat26Zone2;
	private BigDecimal vhlCat26Zone3;
	private BigDecimal vhlCat27Zone1;
	private BigDecimal vhlCat27Zone2;
	private BigDecimal vhlCat27Zone3;
	private BigDecimal vhlCat28Zone1;
	private BigDecimal vhlCat28Zone2;
	private BigDecimal vhlCat28Zone3;
	private BigDecimal vhlCat29Zone1;
	private BigDecimal vhlCat29Zone2;
	private BigDecimal vhlCat29Zone3;
	private BigDecimal vhlCat31Zone1;
	private BigDecimal vhlCat31Zone2;
	private BigDecimal vhlCat31Zone3;
	private BigDecimal vhlCat32Zone1;
	private BigDecimal vhlCat32Zone2;
	private BigDecimal vhlCat32Zone3;
	private BigDecimal vhlCat33Zone1;
	private BigDecimal vhlCat33Zone2;
	private BigDecimal vhlCat33Zone3;
	private BigDecimal vhlCat34Zone1;
	private BigDecimal vhlCat34Zone2;
	private BigDecimal vhlCat34Zone3;
	private BigDecimal vhlCat35Zone1;
	private BigDecimal vhlCat35Zone2;
	private BigDecimal vhlCat35Zone3;
	private BigDecimal vhlCat36Zone1;
	private BigDecimal vhlCat36Zone2;
	private BigDecimal vhlCat36Zone3;
	private BigDecimal vhlCat37Zone1;
	private BigDecimal vhlCat37Zone2;
	private BigDecimal vhlCat37Zone3;
	private Set<Tarif> tarifs = new HashSet<Tarif>(0);

	public RcTarif8() {
	}

	public RcTarif8(String codeRcTarif8) {
		this.codeRcTarif8 = codeRcTarif8;
	}

	public RcTarif8(String codeRcTarif8, BigDecimal vhlTourDies0Ess12Zone1,
			BigDecimal vhlTourDies0Ess12Zone2,
			BigDecimal vhlTourDies0Ess12Zone3,
			BigDecimal vhlTourDies24Ess36Zone1,
			BigDecimal vhlTourDies24Ess36Zone2,
			BigDecimal vhlTourDies24Ess36Zone3,
			BigDecimal vhlTourDies56Ess79Zone1,
			BigDecimal vhlTourDies56Ess79Zone2,
			BigDecimal vhlTourDies56Ess79Zone3,
			BigDecimal vhlTourDies78Ess1011Zone1,
			BigDecimal vhlTourDies78Ess1011Zone2,
			BigDecimal vhlTourDies78Ess1011Zone3,
			BigDecimal vhlTourDies9Ess12Zone1,
			BigDecimal vhlTourDies9Ess12Zone2,
			BigDecimal vhlTourDies9Ess12Zone3, BigDecimal vhlCat21Zone1,
			BigDecimal vhlCat21Zone2, BigDecimal vhlCat21Zone3,
			BigDecimal vhlCat22Zone1, BigDecimal vhlCat22Zone2,
			BigDecimal vhlCat22Zone3, BigDecimal vhlCat23Zone1,
			BigDecimal vhlCat23Zone2, BigDecimal vhlCat23Zone3,
			BigDecimal vhlCat24Zone1, BigDecimal vhlCat24Zone2,
			BigDecimal vhlCat24Zone3, BigDecimal vhlCat25Zone1,
			BigDecimal vhlCat25Zone2, BigDecimal vhlCat25Zone3,
			BigDecimal vhlCat26Zone1, BigDecimal vhlCat26Zone2,
			BigDecimal vhlCat26Zone3, BigDecimal vhlCat27Zone1,
			BigDecimal vhlCat27Zone2, BigDecimal vhlCat27Zone3,
			BigDecimal vhlCat28Zone1, BigDecimal vhlCat28Zone2,
			BigDecimal vhlCat28Zone3, BigDecimal vhlCat29Zone1,
			BigDecimal vhlCat29Zone2, BigDecimal vhlCat29Zone3,
			BigDecimal vhlCat31Zone1, BigDecimal vhlCat31Zone2,
			BigDecimal vhlCat31Zone3, BigDecimal vhlCat32Zone1,
			BigDecimal vhlCat32Zone2, BigDecimal vhlCat32Zone3,
			BigDecimal vhlCat33Zone1, BigDecimal vhlCat33Zone2,
			BigDecimal vhlCat33Zone3, BigDecimal vhlCat34Zone1,
			BigDecimal vhlCat34Zone2, BigDecimal vhlCat34Zone3,
			BigDecimal vhlCat35Zone1, BigDecimal vhlCat35Zone2,
			BigDecimal vhlCat35Zone3, BigDecimal vhlCat36Zone1,
			BigDecimal vhlCat36Zone2, BigDecimal vhlCat36Zone3,
			BigDecimal vhlCat37Zone1, BigDecimal vhlCat37Zone2,
			BigDecimal vhlCat37Zone3, Set<Tarif> tarifs) {
		this.codeRcTarif8 = codeRcTarif8;
		this.vhlTourDies0Ess12Zone1 = vhlTourDies0Ess12Zone1;
		this.vhlTourDies0Ess12Zone2 = vhlTourDies0Ess12Zone2;
		this.vhlTourDies0Ess12Zone3 = vhlTourDies0Ess12Zone3;
		this.vhlTourDies24Ess36Zone1 = vhlTourDies24Ess36Zone1;
		this.vhlTourDies24Ess36Zone2 = vhlTourDies24Ess36Zone2;
		this.vhlTourDies24Ess36Zone3 = vhlTourDies24Ess36Zone3;
		this.vhlTourDies56Ess79Zone1 = vhlTourDies56Ess79Zone1;
		this.vhlTourDies56Ess79Zone2 = vhlTourDies56Ess79Zone2;
		this.vhlTourDies56Ess79Zone3 = vhlTourDies56Ess79Zone3;
		this.vhlTourDies78Ess1011Zone1 = vhlTourDies78Ess1011Zone1;
		this.vhlTourDies78Ess1011Zone2 = vhlTourDies78Ess1011Zone2;
		this.vhlTourDies78Ess1011Zone3 = vhlTourDies78Ess1011Zone3;
		this.vhlTourDies9Ess12Zone1 = vhlTourDies9Ess12Zone1;
		this.vhlTourDies9Ess12Zone2 = vhlTourDies9Ess12Zone2;
		this.vhlTourDies9Ess12Zone3 = vhlTourDies9Ess12Zone3;
		this.vhlCat21Zone1 = vhlCat21Zone1;
		this.vhlCat21Zone2 = vhlCat21Zone2;
		this.vhlCat21Zone3 = vhlCat21Zone3;
		this.vhlCat22Zone1 = vhlCat22Zone1;
		this.vhlCat22Zone2 = vhlCat22Zone2;
		this.vhlCat22Zone3 = vhlCat22Zone3;
		this.vhlCat23Zone1 = vhlCat23Zone1;
		this.vhlCat23Zone2 = vhlCat23Zone2;
		this.vhlCat23Zone3 = vhlCat23Zone3;
		this.vhlCat24Zone1 = vhlCat24Zone1;
		this.vhlCat24Zone2 = vhlCat24Zone2;
		this.vhlCat24Zone3 = vhlCat24Zone3;
		this.vhlCat25Zone1 = vhlCat25Zone1;
		this.vhlCat25Zone2 = vhlCat25Zone2;
		this.vhlCat25Zone3 = vhlCat25Zone3;
		this.vhlCat26Zone1 = vhlCat26Zone1;
		this.vhlCat26Zone2 = vhlCat26Zone2;
		this.vhlCat26Zone3 = vhlCat26Zone3;
		this.vhlCat27Zone1 = vhlCat27Zone1;
		this.vhlCat27Zone2 = vhlCat27Zone2;
		this.vhlCat27Zone3 = vhlCat27Zone3;
		this.vhlCat28Zone1 = vhlCat28Zone1;
		this.vhlCat28Zone2 = vhlCat28Zone2;
		this.vhlCat28Zone3 = vhlCat28Zone3;
		this.vhlCat29Zone1 = vhlCat29Zone1;
		this.vhlCat29Zone2 = vhlCat29Zone2;
		this.vhlCat29Zone3 = vhlCat29Zone3;
		this.vhlCat31Zone1 = vhlCat31Zone1;
		this.vhlCat31Zone2 = vhlCat31Zone2;
		this.vhlCat31Zone3 = vhlCat31Zone3;
		this.vhlCat32Zone1 = vhlCat32Zone1;
		this.vhlCat32Zone2 = vhlCat32Zone2;
		this.vhlCat32Zone3 = vhlCat32Zone3;
		this.vhlCat33Zone1 = vhlCat33Zone1;
		this.vhlCat33Zone2 = vhlCat33Zone2;
		this.vhlCat33Zone3 = vhlCat33Zone3;
		this.vhlCat34Zone1 = vhlCat34Zone1;
		this.vhlCat34Zone2 = vhlCat34Zone2;
		this.vhlCat34Zone3 = vhlCat34Zone3;
		this.vhlCat35Zone1 = vhlCat35Zone1;
		this.vhlCat35Zone2 = vhlCat35Zone2;
		this.vhlCat35Zone3 = vhlCat35Zone3;
		this.vhlCat36Zone1 = vhlCat36Zone1;
		this.vhlCat36Zone2 = vhlCat36Zone2;
		this.vhlCat36Zone3 = vhlCat36Zone3;
		this.vhlCat37Zone1 = vhlCat37Zone1;
		this.vhlCat37Zone2 = vhlCat37Zone2;
		this.vhlCat37Zone3 = vhlCat37Zone3;
		this.tarifs = tarifs;
	}

	@Id
	@Column(name = "CODE_RC_TARIF8", unique = true, nullable = false, length = 16)
	public String getCodeRcTarif8() {
		return this.codeRcTarif8;
	}

	public void setCodeRcTarif8(String codeRcTarif8) {
		this.codeRcTarif8 = codeRcTarif8;
	}

	@Column(name = "VHL_TOUR_DIES0_ESS12_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhlTourDies0Ess12Zone1() {
		return this.vhlTourDies0Ess12Zone1;
	}

	public void setVhlTourDies0Ess12Zone1(BigDecimal vhlTourDies0Ess12Zone1) {
		this.vhlTourDies0Ess12Zone1 = vhlTourDies0Ess12Zone1;
	}

	@Column(name = "VHL_TOUR_DIES0_ESS12_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhlTourDies0Ess12Zone2() {
		return this.vhlTourDies0Ess12Zone2;
	}

	public void setVhlTourDies0Ess12Zone2(BigDecimal vhlTourDies0Ess12Zone2) {
		this.vhlTourDies0Ess12Zone2 = vhlTourDies0Ess12Zone2;
	}

	@Column(name = "VHL_TOUR_DIES0_ESS12_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhlTourDies0Ess12Zone3() {
		return this.vhlTourDies0Ess12Zone3;
	}

	public void setVhlTourDies0Ess12Zone3(BigDecimal vhlTourDies0Ess12Zone3) {
		this.vhlTourDies0Ess12Zone3 = vhlTourDies0Ess12Zone3;
	}

	@Column(name = "VHL_TOUR_DIES24_ESS36_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhlTourDies24Ess36Zone1() {
		return this.vhlTourDies24Ess36Zone1;
	}

	public void setVhlTourDies24Ess36Zone1(BigDecimal vhlTourDies24Ess36Zone1) {
		this.vhlTourDies24Ess36Zone1 = vhlTourDies24Ess36Zone1;
	}

	@Column(name = "VHL_TOUR_DIES24_ESS36_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhlTourDies24Ess36Zone2() {
		return this.vhlTourDies24Ess36Zone2;
	}

	public void setVhlTourDies24Ess36Zone2(BigDecimal vhlTourDies24Ess36Zone2) {
		this.vhlTourDies24Ess36Zone2 = vhlTourDies24Ess36Zone2;
	}

	@Column(name = "VHL_TOUR_DIES24_ESS36_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhlTourDies24Ess36Zone3() {
		return this.vhlTourDies24Ess36Zone3;
	}

	public void setVhlTourDies24Ess36Zone3(BigDecimal vhlTourDies24Ess36Zone3) {
		this.vhlTourDies24Ess36Zone3 = vhlTourDies24Ess36Zone3;
	}

	@Column(name = "VHL_TOUR_DIES56_ESS79_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhlTourDies56Ess79Zone1() {
		return this.vhlTourDies56Ess79Zone1;
	}

	public void setVhlTourDies56Ess79Zone1(BigDecimal vhlTourDies56Ess79Zone1) {
		this.vhlTourDies56Ess79Zone1 = vhlTourDies56Ess79Zone1;
	}

	@Column(name = "VHL_TOUR_DIES56_ESS79_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhlTourDies56Ess79Zone2() {
		return this.vhlTourDies56Ess79Zone2;
	}

	public void setVhlTourDies56Ess79Zone2(BigDecimal vhlTourDies56Ess79Zone2) {
		this.vhlTourDies56Ess79Zone2 = vhlTourDies56Ess79Zone2;
	}

	@Column(name = "VHL_TOUR_DIES56_ESS79_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhlTourDies56Ess79Zone3() {
		return this.vhlTourDies56Ess79Zone3;
	}

	public void setVhlTourDies56Ess79Zone3(BigDecimal vhlTourDies56Ess79Zone3) {
		this.vhlTourDies56Ess79Zone3 = vhlTourDies56Ess79Zone3;
	}

	@Column(name = "VHL_TOUR_DIES78_ESS1011_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhlTourDies78Ess1011Zone1() {
		return this.vhlTourDies78Ess1011Zone1;
	}

	public void setVhlTourDies78Ess1011Zone1(
			BigDecimal vhlTourDies78Ess1011Zone1) {
		this.vhlTourDies78Ess1011Zone1 = vhlTourDies78Ess1011Zone1;
	}

	@Column(name = "VHL_TOUR_DIES78_ESS1011_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhlTourDies78Ess1011Zone2() {
		return this.vhlTourDies78Ess1011Zone2;
	}

	public void setVhlTourDies78Ess1011Zone2(
			BigDecimal vhlTourDies78Ess1011Zone2) {
		this.vhlTourDies78Ess1011Zone2 = vhlTourDies78Ess1011Zone2;
	}

	@Column(name = "VHL_TOUR_DIES78_ESS1011_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhlTourDies78Ess1011Zone3() {
		return this.vhlTourDies78Ess1011Zone3;
	}

	public void setVhlTourDies78Ess1011Zone3(
			BigDecimal vhlTourDies78Ess1011Zone3) {
		this.vhlTourDies78Ess1011Zone3 = vhlTourDies78Ess1011Zone3;
	}

	@Column(name = "VHL_TOUR_DIES9_ESS12_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhlTourDies9Ess12Zone1() {
		return this.vhlTourDies9Ess12Zone1;
	}

	public void setVhlTourDies9Ess12Zone1(BigDecimal vhlTourDies9Ess12Zone1) {
		this.vhlTourDies9Ess12Zone1 = vhlTourDies9Ess12Zone1;
	}

	@Column(name = "VHL_TOUR_DIES9_ESS12_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhlTourDies9Ess12Zone2() {
		return this.vhlTourDies9Ess12Zone2;
	}

	public void setVhlTourDies9Ess12Zone2(BigDecimal vhlTourDies9Ess12Zone2) {
		this.vhlTourDies9Ess12Zone2 = vhlTourDies9Ess12Zone2;
	}

	@Column(name = "VHL_TOUR_DIES9_ESS12_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhlTourDies9Ess12Zone3() {
		return this.vhlTourDies9Ess12Zone3;
	}

	public void setVhlTourDies9Ess12Zone3(BigDecimal vhlTourDies9Ess12Zone3) {
		this.vhlTourDies9Ess12Zone3 = vhlTourDies9Ess12Zone3;
	}

	@Column(name = "VHL_CAT21_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhlCat21Zone1() {
		return this.vhlCat21Zone1;
	}

	public void setVhlCat21Zone1(BigDecimal vhlCat21Zone1) {
		this.vhlCat21Zone1 = vhlCat21Zone1;
	}

	@Column(name = "VHL_CAT21_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhlCat21Zone2() {
		return this.vhlCat21Zone2;
	}

	public void setVhlCat21Zone2(BigDecimal vhlCat21Zone2) {
		this.vhlCat21Zone2 = vhlCat21Zone2;
	}

	@Column(name = "VHL_CAT21_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhlCat21Zone3() {
		return this.vhlCat21Zone3;
	}

	public void setVhlCat21Zone3(BigDecimal vhlCat21Zone3) {
		this.vhlCat21Zone3 = vhlCat21Zone3;
	}

	@Column(name = "VHL_CAT22_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhlCat22Zone1() {
		return this.vhlCat22Zone1;
	}

	public void setVhlCat22Zone1(BigDecimal vhlCat22Zone1) {
		this.vhlCat22Zone1 = vhlCat22Zone1;
	}

	@Column(name = "VHL_CAT22_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhlCat22Zone2() {
		return this.vhlCat22Zone2;
	}

	public void setVhlCat22Zone2(BigDecimal vhlCat22Zone2) {
		this.vhlCat22Zone2 = vhlCat22Zone2;
	}

	@Column(name = "VHL_CAT22_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhlCat22Zone3() {
		return this.vhlCat22Zone3;
	}

	public void setVhlCat22Zone3(BigDecimal vhlCat22Zone3) {
		this.vhlCat22Zone3 = vhlCat22Zone3;
	}

	@Column(name = "VHL_CAT23_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhlCat23Zone1() {
		return this.vhlCat23Zone1;
	}

	public void setVhlCat23Zone1(BigDecimal vhlCat23Zone1) {
		this.vhlCat23Zone1 = vhlCat23Zone1;
	}

	@Column(name = "VHL_CAT23_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhlCat23Zone2() {
		return this.vhlCat23Zone2;
	}

	public void setVhlCat23Zone2(BigDecimal vhlCat23Zone2) {
		this.vhlCat23Zone2 = vhlCat23Zone2;
	}

	@Column(name = "VHL_CAT23_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhlCat23Zone3() {
		return this.vhlCat23Zone3;
	}

	public void setVhlCat23Zone3(BigDecimal vhlCat23Zone3) {
		this.vhlCat23Zone3 = vhlCat23Zone3;
	}

	@Column(name = "VHL_CAT24_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhlCat24Zone1() {
		return this.vhlCat24Zone1;
	}

	public void setVhlCat24Zone1(BigDecimal vhlCat24Zone1) {
		this.vhlCat24Zone1 = vhlCat24Zone1;
	}

	@Column(name = "VHL_CAT24_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhlCat24Zone2() {
		return this.vhlCat24Zone2;
	}

	public void setVhlCat24Zone2(BigDecimal vhlCat24Zone2) {
		this.vhlCat24Zone2 = vhlCat24Zone2;
	}

	@Column(name = "VHL_CAT24_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhlCat24Zone3() {
		return this.vhlCat24Zone3;
	}

	public void setVhlCat24Zone3(BigDecimal vhlCat24Zone3) {
		this.vhlCat24Zone3 = vhlCat24Zone3;
	}

	@Column(name = "VHL_CAT25_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhlCat25Zone1() {
		return this.vhlCat25Zone1;
	}

	public void setVhlCat25Zone1(BigDecimal vhlCat25Zone1) {
		this.vhlCat25Zone1 = vhlCat25Zone1;
	}

	@Column(name = "VHL_CAT25_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhlCat25Zone2() {
		return this.vhlCat25Zone2;
	}

	public void setVhlCat25Zone2(BigDecimal vhlCat25Zone2) {
		this.vhlCat25Zone2 = vhlCat25Zone2;
	}

	@Column(name = "VHL_CAT25_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhlCat25Zone3() {
		return this.vhlCat25Zone3;
	}

	public void setVhlCat25Zone3(BigDecimal vhlCat25Zone3) {
		this.vhlCat25Zone3 = vhlCat25Zone3;
	}

	@Column(name = "VHL_CAT26_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhlCat26Zone1() {
		return this.vhlCat26Zone1;
	}

	public void setVhlCat26Zone1(BigDecimal vhlCat26Zone1) {
		this.vhlCat26Zone1 = vhlCat26Zone1;
	}

	@Column(name = "VHL_CAT26_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhlCat26Zone2() {
		return this.vhlCat26Zone2;
	}

	public void setVhlCat26Zone2(BigDecimal vhlCat26Zone2) {
		this.vhlCat26Zone2 = vhlCat26Zone2;
	}

	@Column(name = "VHL_CAT26_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhlCat26Zone3() {
		return this.vhlCat26Zone3;
	}

	public void setVhlCat26Zone3(BigDecimal vhlCat26Zone3) {
		this.vhlCat26Zone3 = vhlCat26Zone3;
	}

	@Column(name = "VHL_CAT27_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhlCat27Zone1() {
		return this.vhlCat27Zone1;
	}

	public void setVhlCat27Zone1(BigDecimal vhlCat27Zone1) {
		this.vhlCat27Zone1 = vhlCat27Zone1;
	}

	@Column(name = "VHL_CAT27_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhlCat27Zone2() {
		return this.vhlCat27Zone2;
	}

	public void setVhlCat27Zone2(BigDecimal vhlCat27Zone2) {
		this.vhlCat27Zone2 = vhlCat27Zone2;
	}

	@Column(name = "VHL_CAT27_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhlCat27Zone3() {
		return this.vhlCat27Zone3;
	}

	public void setVhlCat27Zone3(BigDecimal vhlCat27Zone3) {
		this.vhlCat27Zone3 = vhlCat27Zone3;
	}

	@Column(name = "VHL_CAT28_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhlCat28Zone1() {
		return this.vhlCat28Zone1;
	}

	public void setVhlCat28Zone1(BigDecimal vhlCat28Zone1) {
		this.vhlCat28Zone1 = vhlCat28Zone1;
	}

	@Column(name = "VHL_CAT28_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhlCat28Zone2() {
		return this.vhlCat28Zone2;
	}

	public void setVhlCat28Zone2(BigDecimal vhlCat28Zone2) {
		this.vhlCat28Zone2 = vhlCat28Zone2;
	}

	@Column(name = "VHL_CAT28_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhlCat28Zone3() {
		return this.vhlCat28Zone3;
	}

	public void setVhlCat28Zone3(BigDecimal vhlCat28Zone3) {
		this.vhlCat28Zone3 = vhlCat28Zone3;
	}

	@Column(name = "VHL_CAT29_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhlCat29Zone1() {
		return this.vhlCat29Zone1;
	}

	public void setVhlCat29Zone1(BigDecimal vhlCat29Zone1) {
		this.vhlCat29Zone1 = vhlCat29Zone1;
	}

	@Column(name = "VHL_CAT29_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhlCat29Zone2() {
		return this.vhlCat29Zone2;
	}

	public void setVhlCat29Zone2(BigDecimal vhlCat29Zone2) {
		this.vhlCat29Zone2 = vhlCat29Zone2;
	}

	@Column(name = "VHL_CAT29_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhlCat29Zone3() {
		return this.vhlCat29Zone3;
	}

	public void setVhlCat29Zone3(BigDecimal vhlCat29Zone3) {
		this.vhlCat29Zone3 = vhlCat29Zone3;
	}

	@Column(name = "VHL_CAT31_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhlCat31Zone1() {
		return this.vhlCat31Zone1;
	}

	public void setVhlCat31Zone1(BigDecimal vhlCat31Zone1) {
		this.vhlCat31Zone1 = vhlCat31Zone1;
	}

	@Column(name = "VHL_CAT31_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhlCat31Zone2() {
		return this.vhlCat31Zone2;
	}

	public void setVhlCat31Zone2(BigDecimal vhlCat31Zone2) {
		this.vhlCat31Zone2 = vhlCat31Zone2;
	}

	@Column(name = "VHL_CAT31_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhlCat31Zone3() {
		return this.vhlCat31Zone3;
	}

	public void setVhlCat31Zone3(BigDecimal vhlCat31Zone3) {
		this.vhlCat31Zone3 = vhlCat31Zone3;
	}

	@Column(name = "VHL_CAT32_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhlCat32Zone1() {
		return this.vhlCat32Zone1;
	}

	public void setVhlCat32Zone1(BigDecimal vhlCat32Zone1) {
		this.vhlCat32Zone1 = vhlCat32Zone1;
	}

	@Column(name = "VHL_CAT32_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhlCat32Zone2() {
		return this.vhlCat32Zone2;
	}

	public void setVhlCat32Zone2(BigDecimal vhlCat32Zone2) {
		this.vhlCat32Zone2 = vhlCat32Zone2;
	}

	@Column(name = "VHL_CAT32_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhlCat32Zone3() {
		return this.vhlCat32Zone3;
	}

	public void setVhlCat32Zone3(BigDecimal vhlCat32Zone3) {
		this.vhlCat32Zone3 = vhlCat32Zone3;
	}

	@Column(name = "VHL_CAT33_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhlCat33Zone1() {
		return this.vhlCat33Zone1;
	}

	public void setVhlCat33Zone1(BigDecimal vhlCat33Zone1) {
		this.vhlCat33Zone1 = vhlCat33Zone1;
	}

	@Column(name = "VHL_CAT33_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhlCat33Zone2() {
		return this.vhlCat33Zone2;
	}

	public void setVhlCat33Zone2(BigDecimal vhlCat33Zone2) {
		this.vhlCat33Zone2 = vhlCat33Zone2;
	}

	@Column(name = "VHL_CAT33_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhlCat33Zone3() {
		return this.vhlCat33Zone3;
	}

	public void setVhlCat33Zone3(BigDecimal vhlCat33Zone3) {
		this.vhlCat33Zone3 = vhlCat33Zone3;
	}

	@Column(name = "VHL_CAT34_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhlCat34Zone1() {
		return this.vhlCat34Zone1;
	}

	public void setVhlCat34Zone1(BigDecimal vhlCat34Zone1) {
		this.vhlCat34Zone1 = vhlCat34Zone1;
	}

	@Column(name = "VHL_CAT34_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhlCat34Zone2() {
		return this.vhlCat34Zone2;
	}

	public void setVhlCat34Zone2(BigDecimal vhlCat34Zone2) {
		this.vhlCat34Zone2 = vhlCat34Zone2;
	}

	@Column(name = "VHL_CAT34_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhlCat34Zone3() {
		return this.vhlCat34Zone3;
	}

	public void setVhlCat34Zone3(BigDecimal vhlCat34Zone3) {
		this.vhlCat34Zone3 = vhlCat34Zone3;
	}

	@Column(name = "VHL_CAT35_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhlCat35Zone1() {
		return this.vhlCat35Zone1;
	}

	public void setVhlCat35Zone1(BigDecimal vhlCat35Zone1) {
		this.vhlCat35Zone1 = vhlCat35Zone1;
	}

	@Column(name = "VHL_CAT35_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhlCat35Zone2() {
		return this.vhlCat35Zone2;
	}

	public void setVhlCat35Zone2(BigDecimal vhlCat35Zone2) {
		this.vhlCat35Zone2 = vhlCat35Zone2;
	}

	@Column(name = "VHL_CAT35_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhlCat35Zone3() {
		return this.vhlCat35Zone3;
	}

	public void setVhlCat35Zone3(BigDecimal vhlCat35Zone3) {
		this.vhlCat35Zone3 = vhlCat35Zone3;
	}

	@Column(name = "VHL_CAT36_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhlCat36Zone1() {
		return this.vhlCat36Zone1;
	}

	public void setVhlCat36Zone1(BigDecimal vhlCat36Zone1) {
		this.vhlCat36Zone1 = vhlCat36Zone1;
	}

	@Column(name = "VHL_CAT36_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhlCat36Zone2() {
		return this.vhlCat36Zone2;
	}

	public void setVhlCat36Zone2(BigDecimal vhlCat36Zone2) {
		this.vhlCat36Zone2 = vhlCat36Zone2;
	}

	@Column(name = "VHL_CAT36_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhlCat36Zone3() {
		return this.vhlCat36Zone3;
	}

	public void setVhlCat36Zone3(BigDecimal vhlCat36Zone3) {
		this.vhlCat36Zone3 = vhlCat36Zone3;
	}

	@Column(name = "VHL_CAT37_ZONE1", precision = 15, scale = 3)
	public BigDecimal getVhlCat37Zone1() {
		return this.vhlCat37Zone1;
	}

	public void setVhlCat37Zone1(BigDecimal vhlCat37Zone1) {
		this.vhlCat37Zone1 = vhlCat37Zone1;
	}

	@Column(name = "VHL_CAT37_ZONE2", precision = 15, scale = 3)
	public BigDecimal getVhlCat37Zone2() {
		return this.vhlCat37Zone2;
	}

	public void setVhlCat37Zone2(BigDecimal vhlCat37Zone2) {
		this.vhlCat37Zone2 = vhlCat37Zone2;
	}

	@Column(name = "VHL_CAT37_ZONE3", precision = 15, scale = 3)
	public BigDecimal getVhlCat37Zone3() {
		return this.vhlCat37Zone3;
	}

	public void setVhlCat37Zone3(BigDecimal vhlCat37Zone3) {
		this.vhlCat37Zone3 = vhlCat37Zone3;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rcTarif8")
	public Set<Tarif> getTarifs() {
		return this.tarifs;
	}

	public void setTarifs(Set<Tarif> tarifs) {
		this.tarifs = tarifs;
	}

}
