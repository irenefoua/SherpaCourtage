package com.j3a.assurance.model;

// Generated 10 ao�t 2015 09:53:29 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * EntrepriseCaution generated by hbm2java
 */
@Entity
@Table(name = "entreprise_caution", catalog = "zeusbd")
public class EntrepriseCaution implements java.io.Serializable {

	private String codeEntrepCaut;
	private String nomsEntrepCaut;
	private String prenomsEntrepCaut;
	private String qualificationEntrepCaut;
	private String objetEntrepCaut;
	private Set<EntreprisecautionExercice> entreprisecautionExercices = new HashSet<EntreprisecautionExercice>(
			0);

	public EntrepriseCaution() {
	}

	public EntrepriseCaution(String codeEntrepCaut) {
		this.codeEntrepCaut = codeEntrepCaut;
	}

	public EntrepriseCaution(String codeEntrepCaut, String nomsEntrepCaut,
			String prenomsEntrepCaut, String qualificationEntrepCaut,
			String objetEntrepCaut,
			Set<EntreprisecautionExercice> entreprisecautionExercices) {
		this.codeEntrepCaut = codeEntrepCaut;
		this.nomsEntrepCaut = nomsEntrepCaut;
		this.prenomsEntrepCaut = prenomsEntrepCaut;
		this.qualificationEntrepCaut = qualificationEntrepCaut;
		this.objetEntrepCaut = objetEntrepCaut;
		this.entreprisecautionExercices = entreprisecautionExercices;
	}

	@Id
	@Column(name = "CODE_ENTREP_CAUT", unique = true, nullable = false, length = 20)
	public String getCodeEntrepCaut() {
		return this.codeEntrepCaut;
	}

	public void setCodeEntrepCaut(String codeEntrepCaut) {
		this.codeEntrepCaut = codeEntrepCaut;
	}

	@Column(name = "NOMS_ENTREP_CAUT", length = 20)
	public String getNomsEntrepCaut() {
		return this.nomsEntrepCaut;
	}

	public void setNomsEntrepCaut(String nomsEntrepCaut) {
		this.nomsEntrepCaut = nomsEntrepCaut;
	}

	@Column(name = "PRENOMS_ENTREP_CAUT", length = 50)
	public String getPrenomsEntrepCaut() {
		return this.prenomsEntrepCaut;
	}

	public void setPrenomsEntrepCaut(String prenomsEntrepCaut) {
		this.prenomsEntrepCaut = prenomsEntrepCaut;
	}

	@Column(name = "QUALIFICATION_ENTREP_CAUT", length = 30)
	public String getQualificationEntrepCaut() {
		return this.qualificationEntrepCaut;
	}

	public void setQualificationEntrepCaut(String qualificationEntrepCaut) {
		this.qualificationEntrepCaut = qualificationEntrepCaut;
	}

	@Column(name = "OBJET_ENTREP_CAUT", length = 20)
	public String getObjetEntrepCaut() {
		return this.objetEntrepCaut;
	}

	public void setObjetEntrepCaut(String objetEntrepCaut) {
		this.objetEntrepCaut = objetEntrepCaut;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entrepriseCaution")
	public Set<EntreprisecautionExercice> getEntreprisecautionExercices() {
		return this.entreprisecautionExercices;
	}

	public void setEntreprisecautionExercices(
			Set<EntreprisecautionExercice> entreprisecautionExercices) {
		this.entreprisecautionExercices = entreprisecautionExercices;
	}

}
