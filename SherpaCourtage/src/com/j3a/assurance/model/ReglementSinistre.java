package com.j3a.assurance.model;

// Generated 6 ao�t 2015 16:35:56 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
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
 * ReglementSinistre generated by hbm2java
 */
@Entity
@Table(name = "reglement_sinistre", catalog = "zeusbd")
public class ReglementSinistre implements java.io.Serializable {

	private String codeReglementSinistre;
	private AyantDroit ayantDroit;
	private Facture facture;
	private Intervention intervention;
	private Victime victime;
	private BigDecimal montantReglement;
	private Date dateReglement;
	private String modeReglement;
	private String numCheque;
	private String cpteBanqueRegltSinistre;
	private String nomTireurRegltSinistre;
	private String steBanqueRegltSinistre;

	public ReglementSinistre() {
	}

	public ReglementSinistre(String codeReglementSinistre) {
		this.codeReglementSinistre = codeReglementSinistre;
	}

	public ReglementSinistre(String codeReglementSinistre,
			AyantDroit ayantDroit, Facture facture, Intervention intervention,
			Victime victime, BigDecimal montantReglement, Date dateReglement,
			String modeReglement, String numCheque,
			String cpteBanqueRegltSinistre, String nomTireurRegltSinistre,
			String steBanqueRegltSinistre) {
		this.codeReglementSinistre = codeReglementSinistre;
		this.ayantDroit = ayantDroit;
		this.facture = facture;
		this.intervention = intervention;
		this.victime = victime;
		this.montantReglement = montantReglement;
		this.dateReglement = dateReglement;
		this.modeReglement = modeReglement;
		this.numCheque = numCheque;
		this.cpteBanqueRegltSinistre = cpteBanqueRegltSinistre;
		this.nomTireurRegltSinistre = nomTireurRegltSinistre;
		this.steBanqueRegltSinistre = steBanqueRegltSinistre;
	}

	@Id
	@Column(name = "CODE_REGLEMENT_SINISTRE", unique = true, nullable = false, length = 10)
	public String getCodeReglementSinistre() {
		return this.codeReglementSinistre;
	}

	public void setCodeReglementSinistre(String codeReglementSinistre) {
		this.codeReglementSinistre = codeReglementSinistre;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_AYANT_DROIT")
	public AyantDroit getAyantDroit() {
		return this.ayantDroit;
	}

	public void setAyantDroit(AyantDroit ayantDroit) {
		this.ayantDroit = ayantDroit;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_FACTURE")
	public Facture getFacture() {
		return this.facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REF_INTERVENTION")
	public Intervention getIntervention() {
		return this.intervention;
	}

	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NUM_VICTIME")
	public Victime getVictime() {
		return this.victime;
	}

	public void setVictime(Victime victime) {
		this.victime = victime;
	}

	@Column(name = "MONTANT_REGLEMENT", precision = 15, scale = 3)
	public BigDecimal getMontantReglement() {
		return this.montantReglement;
	}

	public void setMontantReglement(BigDecimal montantReglement) {
		this.montantReglement = montantReglement;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_REGLEMENT", length = 10)
	public Date getDateReglement() {
		return this.dateReglement;
	}

	public void setDateReglement(Date dateReglement) {
		this.dateReglement = dateReglement;
	}

	@Column(name = "MODE_REGLEMENT", length = 50)
	public String getModeReglement() {
		return this.modeReglement;
	}

	public void setModeReglement(String modeReglement) {
		this.modeReglement = modeReglement;
	}

	@Column(name = "NUM_CHEQUE", length = 50)
	public String getNumCheque() {
		return this.numCheque;
	}

	public void setNumCheque(String numCheque) {
		this.numCheque = numCheque;
	}

	@Column(name = "CPTE_BANQUE_REGLT_SINISTRE", length = 20)
	public String getCpteBanqueRegltSinistre() {
		return this.cpteBanqueRegltSinistre;
	}

	public void setCpteBanqueRegltSinistre(String cpteBanqueRegltSinistre) {
		this.cpteBanqueRegltSinistre = cpteBanqueRegltSinistre;
	}

	@Column(name = "NOM_TIREUR_REGLT_SINISTRE", length = 150)
	public String getNomTireurRegltSinistre() {
		return this.nomTireurRegltSinistre;
	}

	public void setNomTireurRegltSinistre(String nomTireurRegltSinistre) {
		this.nomTireurRegltSinistre = nomTireurRegltSinistre;
	}

	@Column(name = "STE_BANQUE_REGLT_SINISTRE", length = 60)
	public String getSteBanqueRegltSinistre() {
		return this.steBanqueRegltSinistre;
	}

	public void setSteBanqueRegltSinistre(String steBanqueRegltSinistre) {
		this.steBanqueRegltSinistre = steBanqueRegltSinistre;
	}

}
