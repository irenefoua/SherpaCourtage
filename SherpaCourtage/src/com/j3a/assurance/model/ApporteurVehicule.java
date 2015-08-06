package com.j3a.assurance.model;

// Generated 6 juil. 2015 11:25:44 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ApporteurVehicule generated by hbm2java
 */
@Entity
@Table(name = "apporteur_vehicule", catalog = "zeusbd")
public class ApporteurVehicule implements java.io.Serializable {

	private ApporteurVehiculeId id;
	private Apporteur apporteur;
	private Vehicule vehicule;
	private Date dateApporteurVehicule;
	private BigDecimal tauxComApporteur;
	private BigDecimal montantComApporteur;

	public ApporteurVehicule() {
	}

	public ApporteurVehicule(ApporteurVehiculeId id, Apporteur apporteur,
			Vehicule vehicule) {
		this.id = id;
		this.apporteur = apporteur;
		this.vehicule = vehicule;
	}

	public ApporteurVehicule(ApporteurVehiculeId id, Apporteur apporteur,
			Vehicule vehicule, Date dateApporteurVehicule,
			BigDecimal tauxComApporteur, BigDecimal montantComApporteur) {
		this.id = id;
		this.apporteur = apporteur;
		this.vehicule = vehicule;
		this.dateApporteurVehicule = dateApporteurVehicule;
		this.tauxComApporteur = tauxComApporteur;
		this.montantComApporteur = montantComApporteur;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codeApporteur", column = @Column(name = "CODE_APPORTEUR", nullable = false, length = 10)),
			@AttributeOverride(name = "codeVehicule", column = @Column(name = "CODE_VEHICULE", nullable = false, length = 30)) })
	public ApporteurVehiculeId getId() {
		return this.id;
	}

	public void setId(ApporteurVehiculeId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_APPORTEUR", nullable = false, insertable = false, updatable = false)
	public Apporteur getApporteur() {
		return this.apporteur;
	}

	public void setApporteur(Apporteur apporteur) {
		this.apporteur = apporteur;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_VEHICULE", nullable = false, insertable = false, updatable = false)
	public Vehicule getVehicule() {
		return this.vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_APPORTEUR_VEHICULE", length = 19)
	public Date getDateApporteurVehicule() {
		return this.dateApporteurVehicule;
	}

	public void setDateApporteurVehicule(Date dateApporteurVehicule) {
		this.dateApporteurVehicule = dateApporteurVehicule;
	}

	@Column(name = "TAUX_COM_APPORTEUR", precision = 6, scale = 4)
	public BigDecimal getTauxComApporteur() {
		return this.tauxComApporteur;
	}

	public void setTauxComApporteur(BigDecimal tauxComApporteur) {
		this.tauxComApporteur = tauxComApporteur;
	}

	@Column(name = "MONTANT_COM_APPORTEUR", precision = 15, scale = 4)
	public BigDecimal getMontantComApporteur() {
		return this.montantComApporteur;
	}

	public void setMontantComApporteur(BigDecimal montantComApporteur) {
		this.montantComApporteur = montantComApporteur;
	}

}
