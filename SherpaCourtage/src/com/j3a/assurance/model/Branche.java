package com.j3a.assurance.model;

// Generated 6 ao�t 2015 16:35:56 by Hibernate Tools 4.3.1

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
 * Branche generated by hbm2java
 */
@Entity
@Table(name = "branche", catalog = "zeusbd")
public class Branche implements java.io.Serializable {

	private String codeBranche;
	private String branche;
	private String etatBranche;
	private Integer anneeDebutExploitation;
	private String referenceAgrement;
	private Date dateAgrement;
	private Set<BrancheExercice> brancheExercices = new HashSet<BrancheExercice>(
			0);

	public Branche() {
	}

	public Branche(String codeBranche) {
		this.codeBranche = codeBranche;
	}

	public Branche(String codeBranche, String branche, String etatBranche,
			Integer anneeDebutExploitation, String referenceAgrement,
			Date dateAgrement, Set<BrancheExercice> brancheExercices) {
		this.codeBranche = codeBranche;
		this.branche = branche;
		this.etatBranche = etatBranche;
		this.anneeDebutExploitation = anneeDebutExploitation;
		this.referenceAgrement = referenceAgrement;
		this.dateAgrement = dateAgrement;
		this.brancheExercices = brancheExercices;
	}

	@Id
	@Column(name = "CODE_BRANCHE", unique = true, nullable = false, length = 20)
	public String getCodeBranche() {
		return this.codeBranche;
	}

	public void setCodeBranche(String codeBranche) {
		this.codeBranche = codeBranche;
	}

	@Column(name = "BRANCHE", length = 80)
	public String getBranche() {
		return this.branche;
	}

	public void setBranche(String branche) {
		this.branche = branche;
	}

	@Column(name = "ETAT_BRANCHE", length = 10)
	public String getEtatBranche() {
		return this.etatBranche;
	}

	public void setEtatBranche(String etatBranche) {
		this.etatBranche = etatBranche;
	}

	@Column(name = "ANNEE_DEBUT_EXPLOITATION")
	public Integer getAnneeDebutExploitation() {
		return this.anneeDebutExploitation;
	}

	public void setAnneeDebutExploitation(Integer anneeDebutExploitation) {
		this.anneeDebutExploitation = anneeDebutExploitation;
	}

	@Column(name = "REFERENCE_AGREMENT", length = 30)
	public String getReferenceAgrement() {
		return this.referenceAgrement;
	}

	public void setReferenceAgrement(String referenceAgrement) {
		this.referenceAgrement = referenceAgrement;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_AGREMENT", length = 10)
	public Date getDateAgrement() {
		return this.dateAgrement;
	}

	public void setDateAgrement(Date dateAgrement) {
		this.dateAgrement = dateAgrement;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "branche")
	public Set<BrancheExercice> getBrancheExercices() {
		return this.brancheExercices;
	}

	public void setBrancheExercices(Set<BrancheExercice> brancheExercices) {
		this.brancheExercices = brancheExercices;
	}

}
