package com.j3a.assurance.model;

// Generated 11 ao�t 2015 12:07:31 by Hibernate Tools 4.3.1

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
 * Categorie generated by hbm2java
 */
@Entity
@Table(name = "categorie", catalog = "zeusbd")
public class Categorie implements java.io.Serializable {

	private String codeCategorie;
	private Risque risque;
	private String libelleCategorie;
	private Set<AdherentsSante> adherentsSantes = new HashSet<AdherentsSante>(0);
	private Set<AssureIa> assureIas = new HashSet<AssureIa>(0);
	private Set<Habitation> habitations = new HashSet<Habitation>(0);
	private Set<Aliment> aliments = new HashSet<Aliment>(0);
	private Set<SousCatVehicule> sousCatVehicules = new HashSet<SousCatVehicule>(
			0);
	private Set<CorpsEngin> corpsEngins = new HashSet<CorpsEngin>(0);
	private Set<GestionConfiee> gestionConfiees = new HashSet<GestionConfiee>(0);
	private Set<RisqueNta> risqueNtas = new HashSet<RisqueNta>(0);
	private Set<TypeApporteurCategorie> typeApporteurCategories = new HashSet<TypeApporteurCategorie>(
			0);

	public Categorie() {
	}

	public Categorie(String codeCategorie) {
		this.codeCategorie = codeCategorie;
	}

	public Categorie(String codeCategorie, Risque risque,
			String libelleCategorie, Set<AdherentsSante> adherentsSantes,
			Set<AssureIa> assureIas, Set<Habitation> habitations,
			Set<Aliment> aliments, Set<SousCatVehicule> sousCatVehicules,
			Set<CorpsEngin> corpsEngins, Set<GestionConfiee> gestionConfiees,
			Set<RisqueNta> risqueNtas,
			Set<TypeApporteurCategorie> typeApporteurCategories) {
		this.codeCategorie = codeCategorie;
		this.risque = risque;
		this.libelleCategorie = libelleCategorie;
		this.adherentsSantes = adherentsSantes;
		this.assureIas = assureIas;
		this.habitations = habitations;
		this.aliments = aliments;
		this.sousCatVehicules = sousCatVehicules;
		this.corpsEngins = corpsEngins;
		this.gestionConfiees = gestionConfiees;
		this.risqueNtas = risqueNtas;
		this.typeApporteurCategories = typeApporteurCategories;
	}

	@Id
	@Column(name = "CODE_CATEGORIE", unique = true, nullable = false, length = 15)
	public String getCodeCategorie() {
		return this.codeCategorie;
	}

	public void setCodeCategorie(String codeCategorie) {
		this.codeCategorie = codeCategorie;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_RISQUE")
	public Risque getRisque() {
		return this.risque;
	}

	public void setRisque(Risque risque) {
		this.risque = risque;
	}

	@Column(name = "LIBELLE_CATEGORIE", length = 30)
	public String getLibelleCategorie() {
		return this.libelleCategorie;
	}

	public void setLibelleCategorie(String libelleCategorie) {
		this.libelleCategorie = libelleCategorie;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categorie")
	public Set<AdherentsSante> getAdherentsSantes() {
		return this.adherentsSantes;
	}

	public void setAdherentsSantes(Set<AdherentsSante> adherentsSantes) {
		this.adherentsSantes = adherentsSantes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categorie")
	public Set<AssureIa> getAssureIas() {
		return this.assureIas;
	}

	public void setAssureIas(Set<AssureIa> assureIas) {
		this.assureIas = assureIas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categorie")
	public Set<Habitation> getHabitations() {
		return this.habitations;
	}

	public void setHabitations(Set<Habitation> habitations) {
		this.habitations = habitations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categorie")
	public Set<Aliment> getAliments() {
		return this.aliments;
	}

	public void setAliments(Set<Aliment> aliments) {
		this.aliments = aliments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categorie")
	public Set<SousCatVehicule> getSousCatVehicules() {
		return this.sousCatVehicules;
	}

	public void setSousCatVehicules(Set<SousCatVehicule> sousCatVehicules) {
		this.sousCatVehicules = sousCatVehicules;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categorie")
	public Set<CorpsEngin> getCorpsEngins() {
		return this.corpsEngins;
	}

	public void setCorpsEngins(Set<CorpsEngin> corpsEngins) {
		this.corpsEngins = corpsEngins;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categorie")
	public Set<GestionConfiee> getGestionConfiees() {
		return this.gestionConfiees;
	}

	public void setGestionConfiees(Set<GestionConfiee> gestionConfiees) {
		this.gestionConfiees = gestionConfiees;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categorie")
	public Set<RisqueNta> getRisqueNtas() {
		return this.risqueNtas;
	}

	public void setRisqueNtas(Set<RisqueNta> risqueNtas) {
		this.risqueNtas = risqueNtas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categorie")
	public Set<TypeApporteurCategorie> getTypeApporteurCategories() {
		return this.typeApporteurCategories;
	}

	public void setTypeApporteurCategories(
			Set<TypeApporteurCategorie> typeApporteurCategories) {
		this.typeApporteurCategories = typeApporteurCategories;
	}

}
