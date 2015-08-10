package com.j3a.assurance.model;

// Generated 10 ao�t 2015 15:53:59 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Pays generated by hbm2java
 */
@Entity
@Table(name = "pays", catalog = "zeusbd")
public class Pays implements java.io.Serializable {

	private String codePays;
	private String libellePays;
	private Set<CompagnieAssurance> compagnieAssurances = new HashSet<CompagnieAssurance>(
			0);
	private Set<Personne> personnes = new HashSet<Personne>(0);
	private Set<Province> provinces = new HashSet<Province>(0);
	private Set<Ville> villes = new HashSet<Ville>(0);

	public Pays() {
	}

	public Pays(String codePays) {
		this.codePays = codePays;
	}

	public Pays(String codePays, String libellePays,
			Set<CompagnieAssurance> compagnieAssurances,
			Set<Personne> personnes, Set<Province> provinces, Set<Ville> villes) {
		this.codePays = codePays;
		this.libellePays = libellePays;
		this.compagnieAssurances = compagnieAssurances;
		this.personnes = personnes;
		this.provinces = provinces;
		this.villes = villes;
	}

	@Id
	@Column(name = "CODE_PAYS", unique = true, nullable = false, length = 15)
	public String getCodePays() {
		return this.codePays;
	}

	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}

	@Column(name = "LIBELLE_PAYS", length = 30)
	public String getLibellePays() {
		return this.libellePays;
	}

	public void setLibellePays(String libellePays) {
		this.libellePays = libellePays;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pays")
	public Set<CompagnieAssurance> getCompagnieAssurances() {
		return this.compagnieAssurances;
	}

	public void setCompagnieAssurances(
			Set<CompagnieAssurance> compagnieAssurances) {
		this.compagnieAssurances = compagnieAssurances;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pays")
	public Set<Personne> getPersonnes() {
		return this.personnes;
	}

	public void setPersonnes(Set<Personne> personnes) {
		this.personnes = personnes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pays")
	public Set<Province> getProvinces() {
		return this.provinces;
	}

	public void setProvinces(Set<Province> provinces) {
		this.provinces = provinces;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pays")
	public Set<Ville> getVilles() {
		return this.villes;
	}

	public void setVilles(Set<Ville> villes) {
		this.villes = villes;
	}

}
