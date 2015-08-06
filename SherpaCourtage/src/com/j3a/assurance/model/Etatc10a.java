package com.j3a.assurance.model;

// Generated 6 ao�t 2015 16:35:56 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Etatc10a generated by hbm2java
 */
@Entity
@Table(name = "etatc10a", catalog = "zeusbd")
public class Etatc10a implements java.io.Serializable {

	private String codeEtatc10a;
	private EtatCima etatCima;
	private String libelleEtatc10a;
	private Set<Etatc10aCategorie> etatc10aCategories = new HashSet<Etatc10aCategorie>(
			0);

	public Etatc10a() {
	}

	public Etatc10a(String codeEtatc10a) {
		this.codeEtatc10a = codeEtatc10a;
	}

	public Etatc10a(String codeEtatc10a, EtatCima etatCima,
			String libelleEtatc10a, Set<Etatc10aCategorie> etatc10aCategories) {
		this.codeEtatc10a = codeEtatc10a;
		this.etatCima = etatCima;
		this.libelleEtatc10a = libelleEtatc10a;
		this.etatc10aCategories = etatc10aCategories;
	}

	@Id
	@Column(name = "CODE_ETATC10A", unique = true, nullable = false, length = 20)
	public String getCodeEtatc10a() {
		return this.codeEtatc10a;
	}

	public void setCodeEtatc10a(String codeEtatc10a) {
		this.codeEtatc10a = codeEtatc10a;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_ETAT_CIMA")
	public EtatCima getEtatCima() {
		return this.etatCima;
	}

	public void setEtatCima(EtatCima etatCima) {
		this.etatCima = etatCima;
	}

	@Column(name = "LIBELLE_ETATC10A", length = 30)
	public String getLibelleEtatc10a() {
		return this.libelleEtatc10a;
	}

	public void setLibelleEtatc10a(String libelleEtatc10a) {
		this.libelleEtatc10a = libelleEtatc10a;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "etatc10a")
	public Set<Etatc10aCategorie> getEtatc10aCategories() {
		return this.etatc10aCategories;
	}

	public void setEtatc10aCategories(Set<Etatc10aCategorie> etatc10aCategories) {
		this.etatc10aCategories = etatc10aCategories;
	}

}
