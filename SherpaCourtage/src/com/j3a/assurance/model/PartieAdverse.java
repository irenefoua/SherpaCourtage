package com.j3a.assurance.model;

// Generated 11 ao�t 2015 12:07:31 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PartieAdverse generated by hbm2java
 */
@Entity
@Table(name = "partie_adverse", catalog = "zeusbd")
public class PartieAdverse implements java.io.Serializable {

	private String codePartieAdverse;
	private ConducteurAdverse conducteurAdverse;
	private Sinistre sinistre;
	private SocieteAdverse societeAdverse;
	private String assureurPartieAdverse;
	private String numPolicePartieAdverse;
	private String autres;
	private String adresse;
	private String numSinistreAdverse;
	private String typeRecours;
	private String qualiteRecours;
	private BigDecimal montant;
	private String garantie;
	private String dommagesMateriels;
	private String dommagesCorporels;
	private String nomPartieAdverse;
	private String prenomsPartieAdverse;

	public PartieAdverse() {
	}

	public PartieAdverse(String codePartieAdverse, Sinistre sinistre) {
		this.codePartieAdverse = codePartieAdverse;
		this.sinistre = sinistre;
	}

	public PartieAdverse(String codePartieAdverse,
			ConducteurAdverse conducteurAdverse, Sinistre sinistre,
			SocieteAdverse societeAdverse, String assureurPartieAdverse,
			String numPolicePartieAdverse, String autres, String adresse,
			String numSinistreAdverse, String typeRecours,
			String qualiteRecours, BigDecimal montant, String garantie,
			String dommagesMateriels, String dommagesCorporels,
			String nomPartieAdverse, String prenomsPartieAdverse) {
		this.codePartieAdverse = codePartieAdverse;
		this.conducteurAdverse = conducteurAdverse;
		this.sinistre = sinistre;
		this.societeAdverse = societeAdverse;
		this.assureurPartieAdverse = assureurPartieAdverse;
		this.numPolicePartieAdverse = numPolicePartieAdverse;
		this.autres = autres;
		this.adresse = adresse;
		this.numSinistreAdverse = numSinistreAdverse;
		this.typeRecours = typeRecours;
		this.qualiteRecours = qualiteRecours;
		this.montant = montant;
		this.garantie = garantie;
		this.dommagesMateriels = dommagesMateriels;
		this.dommagesCorporels = dommagesCorporels;
		this.nomPartieAdverse = nomPartieAdverse;
		this.prenomsPartieAdverse = prenomsPartieAdverse;
	}

	@Id
	@Column(name = "CODE_PARTIE_ADVERSE", unique = true, nullable = false, length = 10)
	public String getCodePartieAdverse() {
		return this.codePartieAdverse;
	}

	public void setCodePartieAdverse(String codePartieAdverse) {
		this.codePartieAdverse = codePartieAdverse;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_CONDUCTEUR_ADVERSE")
	public ConducteurAdverse getConducteurAdverse() {
		return this.conducteurAdverse;
	}

	public void setConducteurAdverse(ConducteurAdverse conducteurAdverse) {
		this.conducteurAdverse = conducteurAdverse;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_SINISTRE", nullable = false)
	public Sinistre getSinistre() {
		return this.sinistre;
	}

	public void setSinistre(Sinistre sinistre) {
		this.sinistre = sinistre;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_SCTEADVERSE")
	public SocieteAdverse getSocieteAdverse() {
		return this.societeAdverse;
	}

	public void setSocieteAdverse(SocieteAdverse societeAdverse) {
		this.societeAdverse = societeAdverse;
	}

	@Column(name = "ASSUREUR_PARTIE_ADVERSE", length = 20)
	public String getAssureurPartieAdverse() {
		return this.assureurPartieAdverse;
	}

	public void setAssureurPartieAdverse(String assureurPartieAdverse) {
		this.assureurPartieAdverse = assureurPartieAdverse;
	}

	@Column(name = "NUM_POLICE_PARTIE_ADVERSE", length = 16)
	public String getNumPolicePartieAdverse() {
		return this.numPolicePartieAdverse;
	}

	public void setNumPolicePartieAdverse(String numPolicePartieAdverse) {
		this.numPolicePartieAdverse = numPolicePartieAdverse;
	}

	@Column(name = "AUTRES", length = 50)
	public String getAutres() {
		return this.autres;
	}

	public void setAutres(String autres) {
		this.autres = autres;
	}

	@Column(name = "ADRESSE", length = 60)
	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Column(name = "NUM_SINISTRE_ADVERSE", length = 50)
	public String getNumSinistreAdverse() {
		return this.numSinistreAdverse;
	}

	public void setNumSinistreAdverse(String numSinistreAdverse) {
		this.numSinistreAdverse = numSinistreAdverse;
	}

	@Column(name = "TYPE_RECOURS", length = 100)
	public String getTypeRecours() {
		return this.typeRecours;
	}

	public void setTypeRecours(String typeRecours) {
		this.typeRecours = typeRecours;
	}

	@Column(name = "QUALITE_RECOURS", length = 100)
	public String getQualiteRecours() {
		return this.qualiteRecours;
	}

	public void setQualiteRecours(String qualiteRecours) {
		this.qualiteRecours = qualiteRecours;
	}

	@Column(name = "MONTANT", precision = 15, scale = 3)
	public BigDecimal getMontant() {
		return this.montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	@Column(name = "GARANTIE", length = 100)
	public String getGarantie() {
		return this.garantie;
	}

	public void setGarantie(String garantie) {
		this.garantie = garantie;
	}

	@Column(name = "DOMMAGES_MATERIELS", length = 65535)
	public String getDommagesMateriels() {
		return this.dommagesMateriels;
	}

	public void setDommagesMateriels(String dommagesMateriels) {
		this.dommagesMateriels = dommagesMateriels;
	}

	@Column(name = "DOMMAGES_CORPORELS", length = 65535)
	public String getDommagesCorporels() {
		return this.dommagesCorporels;
	}

	public void setDommagesCorporels(String dommagesCorporels) {
		this.dommagesCorporels = dommagesCorporels;
	}

	@Column(name = "NOM_PARTIE_ADVERSE", length = 20)
	public String getNomPartieAdverse() {
		return this.nomPartieAdverse;
	}

	public void setNomPartieAdverse(String nomPartieAdverse) {
		this.nomPartieAdverse = nomPartieAdverse;
	}

	@Column(name = "PRENOMS_PARTIE_ADVERSE", length = 50)
	public String getPrenomsPartieAdverse() {
		return this.prenomsPartieAdverse;
	}

	public void setPrenomsPartieAdverse(String prenomsPartieAdverse) {
		this.prenomsPartieAdverse = prenomsPartieAdverse;
	}

}
