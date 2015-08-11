package com.j3a.assurance.model;

// Generated 11 ao�t 2015 12:07:31 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SousCatVehicule generated by hbm2java
 */
@Entity
@Table(name = "sous_cat_vehicule", catalog = "zeusbd")
public class SousCatVehicule implements java.io.Serializable {

	private String codeSousCatVehicule;
	private Categorie categorie;
	private Tarif tarif;
	private String libelleSousCatVehicule;
	private String tarif_1;
	private Set<Vehicule> vehicules = new HashSet<Vehicule>(0);
	private Set<Tarifweb> tarifwebs = new HashSet<Tarifweb>(0);

	public SousCatVehicule() {
	}

	public SousCatVehicule(String codeSousCatVehicule) {
		this.codeSousCatVehicule = codeSousCatVehicule;
	}

	public SousCatVehicule(String codeSousCatVehicule, Categorie categorie,
			Tarif tarif, String libelleSousCatVehicule, String tarif_1,
			Set<Vehicule> vehicules, Set<Tarifweb> tarifwebs) {
		this.codeSousCatVehicule = codeSousCatVehicule;
		this.categorie = categorie;
		this.tarif = tarif;
		this.libelleSousCatVehicule = libelleSousCatVehicule;
		this.tarif_1 = tarif_1;
		this.vehicules = vehicules;
		this.tarifwebs = tarifwebs;
	}

	@Id
	@Column(name = "CODE_SOUS_CAT_VEHICULE", unique = true, nullable = false, length = 15)
	public String getCodeSousCatVehicule() {
		return this.codeSousCatVehicule;
	}

	public void setCodeSousCatVehicule(String codeSousCatVehicule) {
		this.codeSousCatVehicule = codeSousCatVehicule;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_CATEGORIE")
	public Categorie getCategorie() {
		return this.categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_TARIF")
	public Tarif getTarif() {
		return this.tarif;
	}

	public void setTarif(Tarif tarif) {
		this.tarif = tarif;
	}

	@Column(name = "LIBELLE_SOUS_CAT_VEHICULE", length = 40)
	public String getLibelleSousCatVehicule() {
		return this.libelleSousCatVehicule;
	}

	public void setLibelleSousCatVehicule(String libelleSousCatVehicule) {
		this.libelleSousCatVehicule = libelleSousCatVehicule;
	}

	@Column(name = "TARIF", length = 10)
	public String getTarif_1() {
		return this.tarif_1;
	}

	public void setTarif_1(String tarif_1) {
		this.tarif_1 = tarif_1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sousCatVehicule")
	public Set<Vehicule> getVehicules() {
		return this.vehicules;
	}

	public void setVehicules(Set<Vehicule> vehicules) {
		this.vehicules = vehicules;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "sousCatVehicules")
	public Set<Tarifweb> getTarifwebs() {
		return this.tarifwebs;
	}

	public void setTarifwebs(Set<Tarifweb> tarifwebs) {
		this.tarifwebs = tarifwebs;
	}

}
