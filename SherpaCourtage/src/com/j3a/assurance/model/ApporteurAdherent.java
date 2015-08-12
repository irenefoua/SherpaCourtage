package com.j3a.assurance.model;

// Generated 12 ao�t 2015 16:21:18 by Hibernate Tools 4.3.1

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
 * ApporteurAdherent generated by hbm2java
 */
@Entity
@Table(name = "apporteur_adherent", catalog = "zeusbd")
public class ApporteurAdherent implements java.io.Serializable {

	private ApporteurAdherentId id;
	private AdherentsSante adherentsSante;
	private Apporteur apporteur;
	private Date dateApporteurAdherent;
	private BigDecimal tauxComApporteurAdherent;
	private BigDecimal montantComApporteurAdherent;

	public ApporteurAdherent() {
	}

	public ApporteurAdherent(ApporteurAdherentId id,
			AdherentsSante adherentsSante, Apporteur apporteur) {
		this.id = id;
		this.adherentsSante = adherentsSante;
		this.apporteur = apporteur;
	}

	public ApporteurAdherent(ApporteurAdherentId id,
			AdherentsSante adherentsSante, Apporteur apporteur,
			Date dateApporteurAdherent, BigDecimal tauxComApporteurAdherent,
			BigDecimal montantComApporteurAdherent) {
		this.id = id;
		this.adherentsSante = adherentsSante;
		this.apporteur = apporteur;
		this.dateApporteurAdherent = dateApporteurAdherent;
		this.tauxComApporteurAdherent = tauxComApporteurAdherent;
		this.montantComApporteurAdherent = montantComApporteurAdherent;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codeApporteur", column = @Column(name = "CODE_APPORTEUR", nullable = false, length = 10)),
			@AttributeOverride(name = "codeAdherent", column = @Column(name = "CODE_ADHERENT", nullable = false, length = 60)) })
	public ApporteurAdherentId getId() {
		return this.id;
	}

	public void setId(ApporteurAdherentId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_ADHERENT", nullable = false, insertable = false, updatable = false)
	public AdherentsSante getAdherentsSante() {
		return this.adherentsSante;
	}

	public void setAdherentsSante(AdherentsSante adherentsSante) {
		this.adherentsSante = adherentsSante;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_APPORTEUR", nullable = false, insertable = false, updatable = false)
	public Apporteur getApporteur() {
		return this.apporteur;
	}

	public void setApporteur(Apporteur apporteur) {
		this.apporteur = apporteur;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_APPORTEUR_ADHERENT", length = 19)
	public Date getDateApporteurAdherent() {
		return this.dateApporteurAdherent;
	}

	public void setDateApporteurAdherent(Date dateApporteurAdherent) {
		this.dateApporteurAdherent = dateApporteurAdherent;
	}

	@Column(name = "TAUX_COM_APPORTEUR_ADHERENT", precision = 6, scale = 4)
	public BigDecimal getTauxComApporteurAdherent() {
		return this.tauxComApporteurAdherent;
	}

	public void setTauxComApporteurAdherent(BigDecimal tauxComApporteurAdherent) {
		this.tauxComApporteurAdherent = tauxComApporteurAdherent;
	}

	@Column(name = "MONTANT_COM_APPORTEUR_ADHERENT", precision = 15, scale = 4)
	public BigDecimal getMontantComApporteurAdherent() {
		return this.montantComApporteurAdherent;
	}

	public void setMontantComApporteurAdherent(
			BigDecimal montantComApporteurAdherent) {
		this.montantComApporteurAdherent = montantComApporteurAdherent;
	}

}
