package com.j3a.assurance.model;

// Generated 6 ao�t 2015 16:35:56 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ActionnaireConseil generated by hbm2java
 */
@Entity
@Table(name = "actionnaire_conseil", catalog = "zeusbd")
public class ActionnaireConseil implements java.io.Serializable {

	private String codeAc;
	private String nomAc;
	private String prenomsAc;
	private Date dateNaisAc;
	private String lieuNaisAc;
	private String sexeAc;
	private String nationaliteAc;
	private String domicileAc;
	private Integer nombreActions;
	private BigDecimal valeursActions;
	private String grade;
	private String typeAc;
	private Set<AcExercice> acExercices = new HashSet<AcExercice>(0);

	public ActionnaireConseil() {
	}

	public ActionnaireConseil(String codeAc) {
		this.codeAc = codeAc;
	}

	public ActionnaireConseil(String codeAc, String nomAc, String prenomsAc,
			Date dateNaisAc, String lieuNaisAc, String sexeAc,
			String nationaliteAc, String domicileAc, Integer nombreActions,
			BigDecimal valeursActions, String grade, String typeAc,
			Set<AcExercice> acExercices) {
		this.codeAc = codeAc;
		this.nomAc = nomAc;
		this.prenomsAc = prenomsAc;
		this.dateNaisAc = dateNaisAc;
		this.lieuNaisAc = lieuNaisAc;
		this.sexeAc = sexeAc;
		this.nationaliteAc = nationaliteAc;
		this.domicileAc = domicileAc;
		this.nombreActions = nombreActions;
		this.valeursActions = valeursActions;
		this.grade = grade;
		this.typeAc = typeAc;
		this.acExercices = acExercices;
	}

	@Id
	@Column(name = "CODE_AC", unique = true, nullable = false, length = 20)
	public String getCodeAc() {
		return this.codeAc;
	}

	public void setCodeAc(String codeAc) {
		this.codeAc = codeAc;
	}

	@Column(name = "NOM_AC", length = 20)
	public String getNomAc() {
		return this.nomAc;
	}

	public void setNomAc(String nomAc) {
		this.nomAc = nomAc;
	}

	@Column(name = "PRENOMS_AC", length = 50)
	public String getPrenomsAc() {
		return this.prenomsAc;
	}

	public void setPrenomsAc(String prenomsAc) {
		this.prenomsAc = prenomsAc;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_NAIS_AC", length = 10)
	public Date getDateNaisAc() {
		return this.dateNaisAc;
	}

	public void setDateNaisAc(Date dateNaisAc) {
		this.dateNaisAc = dateNaisAc;
	}

	@Column(name = "LIEU_NAIS_AC", length = 30)
	public String getLieuNaisAc() {
		return this.lieuNaisAc;
	}

	public void setLieuNaisAc(String lieuNaisAc) {
		this.lieuNaisAc = lieuNaisAc;
	}

	@Column(name = "SEXE_AC", length = 10)
	public String getSexeAc() {
		return this.sexeAc;
	}

	public void setSexeAc(String sexeAc) {
		this.sexeAc = sexeAc;
	}

	@Column(name = "NATIONALITE_AC", length = 25)
	public String getNationaliteAc() {
		return this.nationaliteAc;
	}

	public void setNationaliteAc(String nationaliteAc) {
		this.nationaliteAc = nationaliteAc;
	}

	@Column(name = "DOMICILE_AC", length = 100)
	public String getDomicileAc() {
		return this.domicileAc;
	}

	public void setDomicileAc(String domicileAc) {
		this.domicileAc = domicileAc;
	}

	@Column(name = "NOMBRE_ACTIONS")
	public Integer getNombreActions() {
		return this.nombreActions;
	}

	public void setNombreActions(Integer nombreActions) {
		this.nombreActions = nombreActions;
	}

	@Column(name = "VALEURS_ACTIONS", precision = 15, scale = 3)
	public BigDecimal getValeursActions() {
		return this.valeursActions;
	}

	public void setValeursActions(BigDecimal valeursActions) {
		this.valeursActions = valeursActions;
	}

	@Column(name = "GRADE", length = 20)
	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "TYPE_AC", length = 20)
	public String getTypeAc() {
		return this.typeAc;
	}

	public void setTypeAc(String typeAc) {
		this.typeAc = typeAc;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "actionnaireConseil")
	public Set<AcExercice> getAcExercices() {
		return this.acExercices;
	}

	public void setAcExercices(Set<AcExercice> acExercices) {
		this.acExercices = acExercices;
	}

}
