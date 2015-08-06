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
 * PaysAction generated by hbm2java
 */
@Entity
@Table(name = "pays_action", catalog = "zeusbd")
public class PaysAction implements java.io.Serializable {

	private String codePa;
	private String paysPa;
	private String branchePa;
	private Date anneeDebutExploitPa;
	private String referenceAgremementPa;
	private Set<PaysactionExercice> paysactionExercices = new HashSet<PaysactionExercice>(
			0);

	public PaysAction() {
	}

	public PaysAction(String codePa) {
		this.codePa = codePa;
	}

	public PaysAction(String codePa, String paysPa, String branchePa,
			Date anneeDebutExploitPa, String referenceAgremementPa,
			Set<PaysactionExercice> paysactionExercices) {
		this.codePa = codePa;
		this.paysPa = paysPa;
		this.branchePa = branchePa;
		this.anneeDebutExploitPa = anneeDebutExploitPa;
		this.referenceAgremementPa = referenceAgremementPa;
		this.paysactionExercices = paysactionExercices;
	}

	@Id
	@Column(name = "CODE_PA", unique = true, nullable = false, length = 20)
	public String getCodePa() {
		return this.codePa;
	}

	public void setCodePa(String codePa) {
		this.codePa = codePa;
	}

	@Column(name = "PAYS_PA", length = 20)
	public String getPaysPa() {
		return this.paysPa;
	}

	public void setPaysPa(String paysPa) {
		this.paysPa = paysPa;
	}

	@Column(name = "BRANCHE_PA", length = 20)
	public String getBranchePa() {
		return this.branchePa;
	}

	public void setBranchePa(String branchePa) {
		this.branchePa = branchePa;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ANNEE_DEBUT_EXPLOIT_PA", length = 10)
	public Date getAnneeDebutExploitPa() {
		return this.anneeDebutExploitPa;
	}

	public void setAnneeDebutExploitPa(Date anneeDebutExploitPa) {
		this.anneeDebutExploitPa = anneeDebutExploitPa;
	}

	@Column(name = "REFERENCE_AGREMEMENT_PA", length = 30)
	public String getReferenceAgremementPa() {
		return this.referenceAgremementPa;
	}

	public void setReferenceAgremementPa(String referenceAgremementPa) {
		this.referenceAgremementPa = referenceAgremementPa;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "paysAction")
	public Set<PaysactionExercice> getPaysactionExercices() {
		return this.paysactionExercices;
	}

	public void setPaysactionExercices(
			Set<PaysactionExercice> paysactionExercices) {
		this.paysactionExercices = paysactionExercices;
	}

}
