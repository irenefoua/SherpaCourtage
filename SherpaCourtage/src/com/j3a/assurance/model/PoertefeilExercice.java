package com.j3a.assurance.model;

// Generated 6 ao�t 2015 16:35:56 by Hibernate Tools 4.3.1

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
 * PoertefeilExercice generated by hbm2java
 */
@Entity
@Table(name = "poertefeil_exercice", catalog = "zeusbd")
public class PoertefeilExercice implements java.io.Serializable {

	private PoertefeilExerciceId id;
	private EtatCima etatCima;
	private Portefeuilles portefeuilles;

	public PoertefeilExercice() {
	}

	public PoertefeilExercice(PoertefeilExerciceId id, EtatCima etatCima,
			Portefeuilles portefeuilles) {
		this.id = id;
		this.etatCima = etatCima;
		this.portefeuilles = portefeuilles;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codePortefeilles", column = @Column(name = "CODE_PORTEFEILLES", nullable = false, length = 20)),
			@AttributeOverride(name = "codeEtatCima", column = @Column(name = "CODE_ETAT_CIMA", nullable = false, length = 20)) })
	public PoertefeilExerciceId getId() {
		return this.id;
	}

	public void setId(PoertefeilExerciceId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_ETAT_CIMA", nullable = false, insertable = false, updatable = false)
	public EtatCima getEtatCima() {
		return this.etatCima;
	}

	public void setEtatCima(EtatCima etatCima) {
		this.etatCima = etatCima;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_PORTEFEILLES", nullable = false, insertable = false, updatable = false)
	public Portefeuilles getPortefeuilles() {
		return this.portefeuilles;
	}

	public void setPortefeuilles(Portefeuilles portefeuilles) {
		this.portefeuilles = portefeuilles;
	}

}
