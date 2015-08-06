package com.j3a.assurance.model;

// Generated 6 juil. 2015 11:25:44 by Hibernate Tools 4.3.1

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
 * ConduireVehicule generated by hbm2java
 */
@Entity
@Table(name = "conduire_vehicule", catalog = "zeusbd")
public class ConduireVehicule implements java.io.Serializable {

	private ConduireVehiculeId id;
	private Conducteur conducteur;
	private Vehicule vehicule;
	private Date dateConduite;

	public ConduireVehicule() {
	}

	public ConduireVehicule(ConduireVehiculeId id, Conducteur conducteur,
			Vehicule vehicule) {
		this.id = id;
		this.conducteur = conducteur;
		this.vehicule = vehicule;
	}

	public ConduireVehicule(ConduireVehiculeId id, Conducteur conducteur,
			Vehicule vehicule, Date dateConduite) {
		this.id = id;
		this.conducteur = conducteur;
		this.vehicule = vehicule;
		this.dateConduite = dateConduite;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codeVehicule", column = @Column(name = "CODE_VEHICULE", nullable = false, length = 30)),
			@AttributeOverride(name = "numCond", column = @Column(name = "NUM_COND", nullable = false, length = 15)) })
	public ConduireVehiculeId getId() {
		return this.id;
	}

	public void setId(ConduireVehiculeId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NUM_COND", nullable = false, insertable = false, updatable = false)
	public Conducteur getConducteur() {
		return this.conducteur;
	}

	public void setConducteur(Conducteur conducteur) {
		this.conducteur = conducteur;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_VEHICULE", nullable = false, insertable = false, updatable = false)
	public Vehicule getVehicule() {
		return this.vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_CONDUITE", length = 10)
	public Date getDateConduite() {
		return this.dateConduite;
	}

	public void setDateConduite(Date dateConduite) {
		this.dateConduite = dateConduite;
	}

}
