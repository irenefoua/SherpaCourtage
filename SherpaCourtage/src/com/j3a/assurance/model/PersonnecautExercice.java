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
 * PersonnecautExercice generated by hbm2java
 */
@Entity
@Table(name = "personnecaut_exercice", catalog = "zeusbd")
public class PersonnecautExercice implements java.io.Serializable {

	private PersonnecautExerciceId id;
	private EtatCima etatCima;
	private PersonneCaution personneCaution;

	public PersonnecautExercice() {
	}

	public PersonnecautExercice(PersonnecautExerciceId id, EtatCima etatCima,
			PersonneCaution personneCaution) {
		this.id = id;
		this.etatCima = etatCima;
		this.personneCaution = personneCaution;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codeEtatCima", column = @Column(name = "CODE_ETAT_CIMA", nullable = false, length = 20)),
			@AttributeOverride(name = "codePersCaut", column = @Column(name = "CODE_PERS_CAUT", nullable = false, length = 20)) })
	public PersonnecautExerciceId getId() {
		return this.id;
	}

	public void setId(PersonnecautExerciceId id) {
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
	@JoinColumn(name = "CODE_PERS_CAUT", nullable = false, insertable = false, updatable = false)
	public PersonneCaution getPersonneCaution() {
		return this.personneCaution;
	}

	public void setPersonneCaution(PersonneCaution personneCaution) {
		this.personneCaution = personneCaution;
	}

}
