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
 * Garantie generated by hbm2java
 */
@Entity
@Table(name = "garantie", catalog = "zeusbd")
public class Garantie implements java.io.Serializable {

	private String codeGarantie;
	private Risque risque;
	private String libelleGarantie;
	private String capitalGarantie;
	private String franchise;
	private String categorieGarantie;
	private Set<GarantieFormuleGc> garantieFormuleGcs = new HashSet<GarantieFormuleGc>(
			0);
	private Set<GarantieGarantieChoisieMrh> garantieGarantieChoisieMrhs = new HashSet<GarantieGarantieChoisieMrh>(
			0);
	private Set<GarantieGarantieChoisieTransport> garantieGarantieChoisieTransports = new HashSet<GarantieGarantieChoisieTransport>(
			0);
	private Set<GarantieGarantieChoisieSante> garantieGarantieChoisieSantes = new HashSet<GarantieGarantieChoisieSante>(
			0);
	private Set<GarantieSinistre> garantieSinistres = new HashSet<GarantieSinistre>(
			0);
	private Set<GarantieGarantieChoisieCorps> garantieGarantieChoisieCorpses = new HashSet<GarantieGarantieChoisieCorps>(
			0);
	private Set<GarantieGarantieChoisie> garantieGarantieChoisies = new HashSet<GarantieGarantieChoisie>(
			0);
	private Set<GarantieGarantieChoisieIa> garantieGarantieChoisieIas = new HashSet<GarantieGarantieChoisieIa>(
			0);
	private Set<GarantieGarantieChoisieNta> garantieGarantieChoisieNtas = new HashSet<GarantieGarantieChoisieNta>(
			0);

	public Garantie() {
	}

	public Garantie(String codeGarantie) {
		this.codeGarantie = codeGarantie;
	}

	public Garantie(
			String codeGarantie,
			Risque risque,
			String libelleGarantie,
			String capitalGarantie,
			String franchise,
			String categorieGarantie,
			Set<GarantieFormuleGc> garantieFormuleGcs,
			Set<GarantieGarantieChoisieMrh> garantieGarantieChoisieMrhs,
			Set<GarantieGarantieChoisieTransport> garantieGarantieChoisieTransports,
			Set<GarantieGarantieChoisieSante> garantieGarantieChoisieSantes,
			Set<GarantieSinistre> garantieSinistres,
			Set<GarantieGarantieChoisieCorps> garantieGarantieChoisieCorpses,
			Set<GarantieGarantieChoisie> garantieGarantieChoisies,
			Set<GarantieGarantieChoisieIa> garantieGarantieChoisieIas,
			Set<GarantieGarantieChoisieNta> garantieGarantieChoisieNtas) {
		this.codeGarantie = codeGarantie;
		this.risque = risque;
		this.libelleGarantie = libelleGarantie;
		this.capitalGarantie = capitalGarantie;
		this.franchise = franchise;
		this.categorieGarantie = categorieGarantie;
		this.garantieFormuleGcs = garantieFormuleGcs;
		this.garantieGarantieChoisieMrhs = garantieGarantieChoisieMrhs;
		this.garantieGarantieChoisieTransports = garantieGarantieChoisieTransports;
		this.garantieGarantieChoisieSantes = garantieGarantieChoisieSantes;
		this.garantieSinistres = garantieSinistres;
		this.garantieGarantieChoisieCorpses = garantieGarantieChoisieCorpses;
		this.garantieGarantieChoisies = garantieGarantieChoisies;
		this.garantieGarantieChoisieIas = garantieGarantieChoisieIas;
		this.garantieGarantieChoisieNtas = garantieGarantieChoisieNtas;
	}

	@Id
	@Column(name = "CODE_GARANTIE", unique = true, nullable = false, length = 12)
	public String getCodeGarantie() {
		return this.codeGarantie;
	}

	public void setCodeGarantie(String codeGarantie) {
		this.codeGarantie = codeGarantie;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_RISQUE")
	public Risque getRisque() {
		return this.risque;
	}

	public void setRisque(Risque risque) {
		this.risque = risque;
	}

	@Column(name = "LIBELLE_GARANTIE", length = 100)
	public String getLibelleGarantie() {
		return this.libelleGarantie;
	}

	public void setLibelleGarantie(String libelleGarantie) {
		this.libelleGarantie = libelleGarantie;
	}

	@Column(name = "CAPITAL_GARANTIE", length = 65535)
	public String getCapitalGarantie() {
		return this.capitalGarantie;
	}

	public void setCapitalGarantie(String capitalGarantie) {
		this.capitalGarantie = capitalGarantie;
	}

	@Column(name = "FRANCHISE", length = 65535)
	public String getFranchise() {
		return this.franchise;
	}

	public void setFranchise(String franchise) {
		this.franchise = franchise;
	}

	@Column(name = "CATEGORIE_GARANTIE", length = 20)
	public String getCategorieGarantie() {
		return this.categorieGarantie;
	}

	public void setCategorieGarantie(String categorieGarantie) {
		this.categorieGarantie = categorieGarantie;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garantie")
	public Set<GarantieFormuleGc> getGarantieFormuleGcs() {
		return this.garantieFormuleGcs;
	}

	public void setGarantieFormuleGcs(Set<GarantieFormuleGc> garantieFormuleGcs) {
		this.garantieFormuleGcs = garantieFormuleGcs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garantie")
	public Set<GarantieGarantieChoisieMrh> getGarantieGarantieChoisieMrhs() {
		return this.garantieGarantieChoisieMrhs;
	}

	public void setGarantieGarantieChoisieMrhs(
			Set<GarantieGarantieChoisieMrh> garantieGarantieChoisieMrhs) {
		this.garantieGarantieChoisieMrhs = garantieGarantieChoisieMrhs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garantie")
	public Set<GarantieGarantieChoisieTransport> getGarantieGarantieChoisieTransports() {
		return this.garantieGarantieChoisieTransports;
	}

	public void setGarantieGarantieChoisieTransports(
			Set<GarantieGarantieChoisieTransport> garantieGarantieChoisieTransports) {
		this.garantieGarantieChoisieTransports = garantieGarantieChoisieTransports;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garantie")
	public Set<GarantieGarantieChoisieSante> getGarantieGarantieChoisieSantes() {
		return this.garantieGarantieChoisieSantes;
	}

	public void setGarantieGarantieChoisieSantes(
			Set<GarantieGarantieChoisieSante> garantieGarantieChoisieSantes) {
		this.garantieGarantieChoisieSantes = garantieGarantieChoisieSantes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garantie")
	public Set<GarantieSinistre> getGarantieSinistres() {
		return this.garantieSinistres;
	}

	public void setGarantieSinistres(Set<GarantieSinistre> garantieSinistres) {
		this.garantieSinistres = garantieSinistres;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garantie")
	public Set<GarantieGarantieChoisieCorps> getGarantieGarantieChoisieCorpses() {
		return this.garantieGarantieChoisieCorpses;
	}

	public void setGarantieGarantieChoisieCorpses(
			Set<GarantieGarantieChoisieCorps> garantieGarantieChoisieCorpses) {
		this.garantieGarantieChoisieCorpses = garantieGarantieChoisieCorpses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garantie")
	public Set<GarantieGarantieChoisie> getGarantieGarantieChoisies() {
		return this.garantieGarantieChoisies;
	}

	public void setGarantieGarantieChoisies(
			Set<GarantieGarantieChoisie> garantieGarantieChoisies) {
		this.garantieGarantieChoisies = garantieGarantieChoisies;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garantie")
	public Set<GarantieGarantieChoisieIa> getGarantieGarantieChoisieIas() {
		return this.garantieGarantieChoisieIas;
	}

	public void setGarantieGarantieChoisieIas(
			Set<GarantieGarantieChoisieIa> garantieGarantieChoisieIas) {
		this.garantieGarantieChoisieIas = garantieGarantieChoisieIas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "garantie")
	public Set<GarantieGarantieChoisieNta> getGarantieGarantieChoisieNtas() {
		return this.garantieGarantieChoisieNtas;
	}

	public void setGarantieGarantieChoisieNtas(
			Set<GarantieGarantieChoisieNta> garantieGarantieChoisieNtas) {
		this.garantieGarantieChoisieNtas = garantieGarantieChoisieNtas;
	}

}
