package com.j3a.assurance.model;

// Generated 10 ao�t 2015 09:53:29 by Hibernate Tools 4.3.1

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
 * Attestation generated by hbm2java
 */
@Entity
@Table(name = "attestation", catalog = "zeusbd")
public class Attestation implements java.io.Serializable {

	private String codeAttestation;
	private Stock stock;
	private Vehicule vehicule;
	private String etat;
	private Date debutperiode;
	private Date finperiode;
	private String numaven;

	public Attestation() {
	}

	public Attestation(String codeAttestation) {
		this.codeAttestation = codeAttestation;
	}

	public Attestation(String codeAttestation, Stock stock, Vehicule vehicule,
			String etat, Date debutperiode, Date finperiode, String numaven) {
		this.codeAttestation = codeAttestation;
		this.stock = stock;
		this.vehicule = vehicule;
		this.etat = etat;
		this.debutperiode = debutperiode;
		this.finperiode = finperiode;
		this.numaven = numaven;
	}

	@Id
	@Column(name = "CODE_ATTESTATION", unique = true, nullable = false, length = 16)
	public String getCodeAttestation() {
		return this.codeAttestation;
	}

	public void setCodeAttestation(String codeAttestation) {
		this.codeAttestation = codeAttestation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_STOCK")
	public Stock getStock() {
		return this.stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_VEHICULE")
	public Vehicule getVehicule() {
		return this.vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	@Column(name = "ETAT", length = 50)
	public String getEtat() {
		return this.etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DEBUTPERIODE", length = 19)
	public Date getDebutperiode() {
		return this.debutperiode;
	}

	public void setDebutperiode(Date debutperiode) {
		this.debutperiode = debutperiode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FINPERIODE", length = 19)
	public Date getFinperiode() {
		return this.finperiode;
	}

	public void setFinperiode(Date finperiode) {
		this.finperiode = finperiode;
	}

	@Column(name = "NUMAVEN", length = 50)
	public String getNumaven() {
		return this.numaven;
	}

	public void setNumaven(String numaven) {
		this.numaven = numaven;
	}

}
