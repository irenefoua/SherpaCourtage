package com.j3a.assurance.model;

// Generated 10 ao�t 2015 09:53:29 by Hibernate Tools 4.3.1

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
 * ApporteurHabitation generated by hbm2java
 */
@Entity
@Table(name = "apporteur_habitation", catalog = "zeusbd")
public class ApporteurHabitation implements java.io.Serializable {

	private ApporteurHabitationId id;
	private Apporteur apporteur;
	private Habitation habitation;
	private Date dateApporteurHabitation;
	private BigDecimal tauxComApporteurHabitation;
	private BigDecimal montantComApporteurHabitation;

	public ApporteurHabitation() {
	}

	public ApporteurHabitation(ApporteurHabitationId id, Apporteur apporteur,
			Habitation habitation) {
		this.id = id;
		this.apporteur = apporteur;
		this.habitation = habitation;
	}

	public ApporteurHabitation(ApporteurHabitationId id, Apporteur apporteur,
			Habitation habitation, Date dateApporteurHabitation,
			BigDecimal tauxComApporteurHabitation,
			BigDecimal montantComApporteurHabitation) {
		this.id = id;
		this.apporteur = apporteur;
		this.habitation = habitation;
		this.dateApporteurHabitation = dateApporteurHabitation;
		this.tauxComApporteurHabitation = tauxComApporteurHabitation;
		this.montantComApporteurHabitation = montantComApporteurHabitation;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codeApporteur", column = @Column(name = "CODE_APPORTEUR", nullable = false, length = 10)),
			@AttributeOverride(name = "codeHabitation", column = @Column(name = "CODE_HABITATION", nullable = false, length = 30)) })
	public ApporteurHabitationId getId() {
		return this.id;
	}

	public void setId(ApporteurHabitationId id) {
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
	@JoinColumn(name = "CODE_HABITATION", nullable = false, insertable = false, updatable = false)
	public Habitation getHabitation() {
		return this.habitation;
	}

	public void setHabitation(Habitation habitation) {
		this.habitation = habitation;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_APPORTEUR_HABITATION", length = 19)
	public Date getDateApporteurHabitation() {
		return this.dateApporteurHabitation;
	}

	public void setDateApporteurHabitation(Date dateApporteurHabitation) {
		this.dateApporteurHabitation = dateApporteurHabitation;
	}

	@Column(name = "TAUX_COM_APPORTEUR_HABITATION", precision = 6, scale = 4)
	public BigDecimal getTauxComApporteurHabitation() {
		return this.tauxComApporteurHabitation;
	}

	public void setTauxComApporteurHabitation(
			BigDecimal tauxComApporteurHabitation) {
		this.tauxComApporteurHabitation = tauxComApporteurHabitation;
	}

	@Column(name = "MONTANT_COM_APPORTEUR_HABITATION", precision = 15, scale = 4)
	public BigDecimal getMontantComApporteurHabitation() {
		return this.montantComApporteurHabitation;
	}

	public void setMontantComApporteurHabitation(
			BigDecimal montantComApporteurHabitation) {
		this.montantComApporteurHabitation = montantComApporteurHabitation;
	}

}
