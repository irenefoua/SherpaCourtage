package com.j3a.assurance.model;

// Generated 10 ao�t 2015 09:53:29 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Portefeuilles generated by hbm2java
 */
@Entity
@Table(name = "portefeuilles", catalog = "zeusbd")
public class Portefeuilles implements java.io.Serializable {

	private String codePortefeilles;
	private Vente vente;
	private Achat achat;
	private Set<PoertefeilExercice> poertefeilExercices = new HashSet<PoertefeilExercice>(
			0);

	public Portefeuilles() {
	}

	public Portefeuilles(String codePortefeilles) {
		this.codePortefeilles = codePortefeilles;
	}

	public Portefeuilles(String codePortefeilles, Vente vente, Achat achat,
			Set<PoertefeilExercice> poertefeilExercices) {
		this.codePortefeilles = codePortefeilles;
		this.vente = vente;
		this.achat = achat;
		this.poertefeilExercices = poertefeilExercices;
	}

	@Id
	@Column(name = "CODE_PORTEFEILLES", unique = true, nullable = false, length = 20)
	public String getCodePortefeilles() {
		return this.codePortefeilles;
	}

	public void setCodePortefeilles(String codePortefeilles) {
		this.codePortefeilles = codePortefeilles;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "portefeuilles")
	public Vente getVente() {
		return this.vente;
	}

	public void setVente(Vente vente) {
		this.vente = vente;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "portefeuilles")
	public Achat getAchat() {
		return this.achat;
	}

	public void setAchat(Achat achat) {
		this.achat = achat;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "portefeuilles")
	public Set<PoertefeilExercice> getPoertefeilExercices() {
		return this.poertefeilExercices;
	}

	public void setPoertefeilExercices(
			Set<PoertefeilExercice> poertefeilExercices) {
		this.poertefeilExercices = poertefeilExercices;
	}

}
