package com.j3a.assurance.model;

// Generated 10 ao�t 2015 15:53:59 by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ActeMedical generated by hbm2java
 */
@Entity
@Table(name = "acte_medical", catalog = "zeusbd")
public class ActeMedical implements java.io.Serializable {

	private String referenceActe;
	private Facture facture;
	private Medecin medecin;
	private Victime victime;
	private Date dateActeMedical;
	private String conclusion;
	private String garantieSupport;

	public ActeMedical() {
	}

	public ActeMedical(String referenceActe, Facture facture, Medecin medecin,
			Victime victime) {
		this.referenceActe = referenceActe;
		this.facture = facture;
		this.medecin = medecin;
		this.victime = victime;
	}

	public ActeMedical(String referenceActe, Facture facture, Medecin medecin,
			Victime victime, Date dateActeMedical, String conclusion,
			String garantieSupport) {
		this.referenceActe = referenceActe;
		this.facture = facture;
		this.medecin = medecin;
		this.victime = victime;
		this.dateActeMedical = dateActeMedical;
		this.conclusion = conclusion;
		this.garantieSupport = garantieSupport;
	}

	@Id
	@Column(name = "REFERENCE_ACTE", unique = true, nullable = false, length = 15)
	public String getReferenceActe() {
		return this.referenceActe;
	}

	public void setReferenceActe(String referenceActe) {
		this.referenceActe = referenceActe;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_FACTURE", nullable = false)
	public Facture getFacture() {
		return this.facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_MEDECIN", nullable = false)
	public Medecin getMedecin() {
		return this.medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NUM_VICTIME", nullable = false)
	public Victime getVictime() {
		return this.victime;
	}

	public void setVictime(Victime victime) {
		this.victime = victime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_ACTE_MEDICAL", length = 10)
	public Date getDateActeMedical() {
		return this.dateActeMedical;
	}

	public void setDateActeMedical(Date dateActeMedical) {
		this.dateActeMedical = dateActeMedical;
	}

	@Column(name = "CONCLUSION", length = 65535)
	public String getConclusion() {
		return this.conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	@Column(name = "GARANTIE_SUPPORT", length = 100)
	public String getGarantieSupport() {
		return this.garantieSupport;
	}

	public void setGarantieSupport(String garantieSupport) {
		this.garantieSupport = garantieSupport;
	}

}
