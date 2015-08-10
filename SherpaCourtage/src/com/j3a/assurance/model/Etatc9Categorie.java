package com.j3a.assurance.model;

// Generated 10 ao�t 2015 09:53:29 by Hibernate Tools 4.3.1

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
 * Etatc9Categorie generated by hbm2java
 */
@Entity
@Table(name = "etatc9_categorie", catalog = "zeusbd")
public class Etatc9Categorie implements java.io.Serializable {

	private String codec9categorie;
	private CategorieCima categorieCima;
	private Etatc9 etatc9;
	private String libelleetatc9cate;
	private Set<C9Exercice> c9Exercices = new HashSet<C9Exercice>(0);

	public Etatc9Categorie() {
	}

	public Etatc9Categorie(String codec9categorie, Etatc9 etatc9) {
		this.codec9categorie = codec9categorie;
		this.etatc9 = etatc9;
	}

	public Etatc9Categorie(String codec9categorie, CategorieCima categorieCima,
			Etatc9 etatc9, String libelleetatc9cate, Set<C9Exercice> c9Exercices) {
		this.codec9categorie = codec9categorie;
		this.categorieCima = categorieCima;
		this.etatc9 = etatc9;
		this.libelleetatc9cate = libelleetatc9cate;
		this.c9Exercices = c9Exercices;
	}

	@Id
	@Column(name = "CODEC9CATEGORIE", unique = true, nullable = false, length = 20)
	public String getCodec9categorie() {
		return this.codec9categorie;
	}

	public void setCodec9categorie(String codec9categorie) {
		this.codec9categorie = codec9categorie;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODECATEGORIECIMA")
	public CategorieCima getCategorieCima() {
		return this.categorieCima;
	}

	public void setCategorieCima(CategorieCima categorieCima) {
		this.categorieCima = categorieCima;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODEETATC9", nullable = false)
	public Etatc9 getEtatc9() {
		return this.etatc9;
	}

	public void setEtatc9(Etatc9 etatc9) {
		this.etatc9 = etatc9;
	}

	@Column(name = "LIBELLEETATC9CATE", length = 100)
	public String getLibelleetatc9cate() {
		return this.libelleetatc9cate;
	}

	public void setLibelleetatc9cate(String libelleetatc9cate) {
		this.libelleetatc9cate = libelleetatc9cate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "etatc9Categorie")
	public Set<C9Exercice> getC9Exercices() {
		return this.c9Exercices;
	}

	public void setC9Exercices(Set<C9Exercice> c9Exercices) {
		this.c9Exercices = c9Exercices;
	}

}
