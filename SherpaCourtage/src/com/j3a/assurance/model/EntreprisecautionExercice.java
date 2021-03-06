package com.j3a.assurance.model;

// Generated 10 ao�t 2015 09:53:29 by Hibernate Tools 4.3.1

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
 * EntreprisecautionExercice generated by hbm2java
 */
@Entity
@Table(name = "entreprisecaution_exercice", catalog = "zeusbd")
public class EntreprisecautionExercice implements java.io.Serializable {

	private EntreprisecautionExerciceId id;
	private EntrepriseCaution entrepriseCaution;
	private EtatCima etatCima;

	public EntreprisecautionExercice() {
	}

	public EntreprisecautionExercice(EntreprisecautionExerciceId id,
			EntrepriseCaution entrepriseCaution, EtatCima etatCima) {
		this.id = id;
		this.entrepriseCaution = entrepriseCaution;
		this.etatCima = etatCima;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codeEtatCima", column = @Column(name = "CODE_ETAT_CIMA", nullable = false, length = 20)),
			@AttributeOverride(name = "codeEntrepCaut", column = @Column(name = "CODE_ENTREP_CAUT", nullable = false, length = 20)) })
	public EntreprisecautionExerciceId getId() {
		return this.id;
	}

	public void setId(EntreprisecautionExerciceId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_ENTREP_CAUT", nullable = false, insertable = false, updatable = false)
	public EntrepriseCaution getEntrepriseCaution() {
		return this.entrepriseCaution;
	}

	public void setEntrepriseCaution(EntrepriseCaution entrepriseCaution) {
		this.entrepriseCaution = entrepriseCaution;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_ETAT_CIMA", nullable = false, insertable = false, updatable = false)
	public EtatCima getEtatCima() {
		return this.etatCima;
	}

	public void setEtatCima(EtatCima etatCima) {
		this.etatCima = etatCima;
	}

}
