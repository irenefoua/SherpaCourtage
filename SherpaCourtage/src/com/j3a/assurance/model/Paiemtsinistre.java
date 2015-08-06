package com.j3a.assurance.model;

// Generated 6 juil. 2015 11:25:44 by Hibernate Tools 4.3.1

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
 * Paiemtsinistre generated by hbm2java
 */
@Entity
@Table(name = "paiemtsinistre", catalog = "zeusbd")
public class Paiemtsinistre implements java.io.Serializable {

	private int idpaiemtsin;
	private Sinistresurvenu sinistresurvenu;
	private Integer numpaiemt;
	private Date dateDebut;
	private Date dateFin;
	private String nomPeriode;
	private BigDecimal mtantpaye;

	public Paiemtsinistre() {
	}

	public Paiemtsinistre(int idpaiemtsin) {
		this.idpaiemtsin = idpaiemtsin;
	}

	public Paiemtsinistre(int idpaiemtsin, Sinistresurvenu sinistresurvenu,
			Integer numpaiemt, Date dateDebut, Date dateFin, String nomPeriode,
			BigDecimal mtantpaye) {
		this.idpaiemtsin = idpaiemtsin;
		this.sinistresurvenu = sinistresurvenu;
		this.numpaiemt = numpaiemt;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nomPeriode = nomPeriode;
		this.mtantpaye = mtantpaye;
	}

	@Id
	@Column(name = "IDPAIEMTSIN", unique = true, nullable = false)
	public int getIdpaiemtsin() {
		return this.idpaiemtsin;
	}

	public void setIdpaiemtsin(int idpaiemtsin) {
		this.idpaiemtsin = idpaiemtsin;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDSINSURV")
	public Sinistresurvenu getSinistresurvenu() {
		return this.sinistresurvenu;
	}

	public void setSinistresurvenu(Sinistresurvenu sinistresurvenu) {
		this.sinistresurvenu = sinistresurvenu;
	}

	@Column(name = "NUMPAIEMT")
	public Integer getNumpaiemt() {
		return this.numpaiemt;
	}

	public void setNumpaiemt(Integer numpaiemt) {
		this.numpaiemt = numpaiemt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_DEBUT", length = 10)
	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_FIN", length = 10)
	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	@Column(name = "NOM_PERIODE", length = 60)
	public String getNomPeriode() {
		return this.nomPeriode;
	}

	public void setNomPeriode(String nomPeriode) {
		this.nomPeriode = nomPeriode;
	}

	@Column(name = "MTANTPAYE")
	public BigDecimal getMtantpaye() {
		return this.mtantpaye;
	}

	public void setMtantpaye(BigDecimal mtantpaye) {
		this.mtantpaye = mtantpaye;
	}

}
