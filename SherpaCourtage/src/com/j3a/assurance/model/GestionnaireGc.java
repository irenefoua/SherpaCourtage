package com.j3a.assurance.model;

// Generated 11 ao�t 2015 12:07:31 by Hibernate Tools 4.3.1

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
 * GestionnaireGc generated by hbm2java
 */
@Entity
@Table(name = "gestionnaire_gc", catalog = "zeusbd")
public class GestionnaireGc implements java.io.Serializable {

	private GestionnaireGcId id;
	private GestionConfiee gestionConfiee;
	private Gestionnaire gestionnaire;
	private BigDecimal tauxComGestgc;
	private BigDecimal comGestgc;
	private BigDecimal tauxAccGestgc;
	private BigDecimal accGestgc;
	private BigDecimal tauxTvaGestgc;
	private BigDecimal tvaGestgc;
	private BigDecimal minCgrie;
	private Date dateGestionnaireGc;

	public GestionnaireGc() {
	}

	public GestionnaireGc(GestionnaireGcId id, GestionConfiee gestionConfiee,
			Gestionnaire gestionnaire) {
		this.id = id;
		this.gestionConfiee = gestionConfiee;
		this.gestionnaire = gestionnaire;
	}

	public GestionnaireGc(GestionnaireGcId id, GestionConfiee gestionConfiee,
			Gestionnaire gestionnaire, BigDecimal tauxComGestgc,
			BigDecimal comGestgc, BigDecimal tauxAccGestgc,
			BigDecimal accGestgc, BigDecimal tauxTvaGestgc,
			BigDecimal tvaGestgc, BigDecimal minCgrie, Date dateGestionnaireGc) {
		this.id = id;
		this.gestionConfiee = gestionConfiee;
		this.gestionnaire = gestionnaire;
		this.tauxComGestgc = tauxComGestgc;
		this.comGestgc = comGestgc;
		this.tauxAccGestgc = tauxAccGestgc;
		this.accGestgc = accGestgc;
		this.tauxTvaGestgc = tauxTvaGestgc;
		this.tvaGestgc = tvaGestgc;
		this.minCgrie = minCgrie;
		this.dateGestionnaireGc = dateGestionnaireGc;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codeGestionConfie", column = @Column(name = "CODE_GESTION_CONFIE", nullable = false, length = 60)),
			@AttributeOverride(name = "codeGestionnaire", column = @Column(name = "CODE_GESTIONNAIRE", nullable = false, length = 30)) })
	public GestionnaireGcId getId() {
		return this.id;
	}

	public void setId(GestionnaireGcId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_GESTION_CONFIE", nullable = false, insertable = false, updatable = false)
	public GestionConfiee getGestionConfiee() {
		return this.gestionConfiee;
	}

	public void setGestionConfiee(GestionConfiee gestionConfiee) {
		this.gestionConfiee = gestionConfiee;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_GESTIONNAIRE", nullable = false, insertable = false, updatable = false)
	public Gestionnaire getGestionnaire() {
		return this.gestionnaire;
	}

	public void setGestionnaire(Gestionnaire gestionnaire) {
		this.gestionnaire = gestionnaire;
	}

	@Column(name = "TAUX_COM_GESTGC", precision = 6, scale = 4)
	public BigDecimal getTauxComGestgc() {
		return this.tauxComGestgc;
	}

	public void setTauxComGestgc(BigDecimal tauxComGestgc) {
		this.tauxComGestgc = tauxComGestgc;
	}

	@Column(name = "COM_GESTGC", precision = 15, scale = 3)
	public BigDecimal getComGestgc() {
		return this.comGestgc;
	}

	public void setComGestgc(BigDecimal comGestgc) {
		this.comGestgc = comGestgc;
	}

	@Column(name = "TAUX_ACC_GESTGC", precision = 6, scale = 4)
	public BigDecimal getTauxAccGestgc() {
		return this.tauxAccGestgc;
	}

	public void setTauxAccGestgc(BigDecimal tauxAccGestgc) {
		this.tauxAccGestgc = tauxAccGestgc;
	}

	@Column(name = "ACC_GESTGC", precision = 15, scale = 3)
	public BigDecimal getAccGestgc() {
		return this.accGestgc;
	}

	public void setAccGestgc(BigDecimal accGestgc) {
		this.accGestgc = accGestgc;
	}

	@Column(name = "TAUX_TVA_GESTGC", precision = 6, scale = 4)
	public BigDecimal getTauxTvaGestgc() {
		return this.tauxTvaGestgc;
	}

	public void setTauxTvaGestgc(BigDecimal tauxTvaGestgc) {
		this.tauxTvaGestgc = tauxTvaGestgc;
	}

	@Column(name = "TVA_GESTGC", precision = 15, scale = 3)
	public BigDecimal getTvaGestgc() {
		return this.tvaGestgc;
	}

	public void setTvaGestgc(BigDecimal tvaGestgc) {
		this.tvaGestgc = tvaGestgc;
	}

	@Column(name = "MIN_CGRIE", precision = 15, scale = 3)
	public BigDecimal getMinCgrie() {
		return this.minCgrie;
	}

	public void setMinCgrie(BigDecimal minCgrie) {
		this.minCgrie = minCgrie;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_GESTIONNAIRE_GC", length = 19)
	public Date getDateGestionnaireGc() {
		return this.dateGestionnaireGc;
	}

	public void setDateGestionnaireGc(Date dateGestionnaireGc) {
		this.dateGestionnaireGc = dateGestionnaireGc;
	}

}
