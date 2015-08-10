package com.j3a.assurance.model;

// Generated 10 ao�t 2015 15:05:20 by Hibernate Tools 4.3.1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ReglementApporteur generated by hbm2java
 */
@Entity
@Table(name = "reglement_apporteur", catalog = "zeusbd")
public class ReglementApporteur implements java.io.Serializable {

	private ReglementApporteurId id;
	private AffaireApporteur affaireApporteur;

	public ReglementApporteur() {
	}

	public ReglementApporteur(ReglementApporteurId id,
			AffaireApporteur affaireApporteur) {
		this.id = id;
		this.affaireApporteur = affaireApporteur;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codeAffaire", column = @Column(name = "CODE_AFFAIRE", nullable = false, length = 30)),
			@AttributeOverride(name = "codeReglementApp", column = @Column(name = "CODE_REGLEMENT_APP", nullable = false, length = 20)),
			@AttributeOverride(name = "dateReglementApp", column = @Column(name = "DATE_REGLEMENT_APP", length = 10)),
			@AttributeOverride(name = "montantReglementApp", column = @Column(name = "MONTANT_REGLEMENT_APP", precision = 15, scale = 3)),
			@AttributeOverride(name = "typeReglement", column = @Column(name = "TYPE_REGLEMENT", length = 60)) })
	public ReglementApporteurId getId() {
		return this.id;
	}

	public void setId(ReglementApporteurId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_AFFAIRE", nullable = false, insertable = false, updatable = false)
	public AffaireApporteur getAffaireApporteur() {
		return this.affaireApporteur;
	}

	public void setAffaireApporteur(AffaireApporteur affaireApporteur) {
		this.affaireApporteur = affaireApporteur;
	}

}
