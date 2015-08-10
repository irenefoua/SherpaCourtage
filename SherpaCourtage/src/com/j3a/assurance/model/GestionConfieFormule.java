package com.j3a.assurance.model;

// Generated 10 ao�t 2015 15:53:59 by Hibernate Tools 4.3.1

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
 * GestionConfieFormule generated by hbm2java
 */
@Entity
@Table(name = "gestion_confie_formule", catalog = "zeusbd")
public class GestionConfieFormule implements java.io.Serializable {

	private GestionConfieFormuleId id;
	private FormuleGc formuleGc;
	private GestionConfiee gestionConfiee;
	private Date dateFormule;
	private BigDecimal primeFormuleGc;

	public GestionConfieFormule() {
	}

	public GestionConfieFormule(GestionConfieFormuleId id, FormuleGc formuleGc,
			GestionConfiee gestionConfiee) {
		this.id = id;
		this.formuleGc = formuleGc;
		this.gestionConfiee = gestionConfiee;
	}

	public GestionConfieFormule(GestionConfieFormuleId id, FormuleGc formuleGc,
			GestionConfiee gestionConfiee, Date dateFormule,
			BigDecimal primeFormuleGc) {
		this.id = id;
		this.formuleGc = formuleGc;
		this.gestionConfiee = gestionConfiee;
		this.dateFormule = dateFormule;
		this.primeFormuleGc = primeFormuleGc;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codeGestionConfie", column = @Column(name = "CODE_GESTION_CONFIE", nullable = false, length = 60)),
			@AttributeOverride(name = "codeFormuleGc", column = @Column(name = "CODE_FORMULE_GC", nullable = false, length = 60)) })
	public GestionConfieFormuleId getId() {
		return this.id;
	}

	public void setId(GestionConfieFormuleId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_FORMULE_GC", nullable = false, insertable = false, updatable = false)
	public FormuleGc getFormuleGc() {
		return this.formuleGc;
	}

	public void setFormuleGc(FormuleGc formuleGc) {
		this.formuleGc = formuleGc;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_GESTION_CONFIE", nullable = false, insertable = false, updatable = false)
	public GestionConfiee getGestionConfiee() {
		return this.gestionConfiee;
	}

	public void setGestionConfiee(GestionConfiee gestionConfiee) {
		this.gestionConfiee = gestionConfiee;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_FORMULE", length = 19)
	public Date getDateFormule() {
		return this.dateFormule;
	}

	public void setDateFormule(Date dateFormule) {
		this.dateFormule = dateFormule;
	}

	@Column(name = "PRIME_FORMULE_GC", precision = 15, scale = 3)
	public BigDecimal getPrimeFormuleGc() {
		return this.primeFormuleGc;
	}

	public void setPrimeFormuleGc(BigDecimal primeFormuleGc) {
		this.primeFormuleGc = primeFormuleGc;
	}

}
