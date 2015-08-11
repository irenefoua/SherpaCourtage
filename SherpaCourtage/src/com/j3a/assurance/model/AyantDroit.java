package com.j3a.assurance.model;

// Generated 11 ao�t 2015 12:07:31 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
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
 * AyantDroit generated by hbm2java
 */
@Entity
@Table(name = "ayant_droit", catalog = "zeusbd")
public class AyantDroit implements java.io.Serializable {

	private String codeAyantDroit;
	private Victime victime;
	private String nom;
	private String prenom;
	private Integer age;
	private String sexe;
	private BigDecimal montantPrejudice;
	private Boolean etatIndemniser;
	private String typeAyantDroit;
	private String adresseAyantDroit;
	private String contactAyantDroit;
	private Set<ReglementSinistre> reglementSinistres = new HashSet<ReglementSinistre>(
			0);
	private Set<Prejudice> prejudices = new HashSet<Prejudice>(0);

	public AyantDroit() {
	}

	public AyantDroit(String codeAyantDroit, Victime victime) {
		this.codeAyantDroit = codeAyantDroit;
		this.victime = victime;
	}

	public AyantDroit(String codeAyantDroit, Victime victime, String nom,
			String prenom, Integer age, String sexe,
			BigDecimal montantPrejudice, Boolean etatIndemniser,
			String typeAyantDroit, String adresseAyantDroit,
			String contactAyantDroit,
			Set<ReglementSinistre> reglementSinistres, Set<Prejudice> prejudices) {
		this.codeAyantDroit = codeAyantDroit;
		this.victime = victime;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.sexe = sexe;
		this.montantPrejudice = montantPrejudice;
		this.etatIndemniser = etatIndemniser;
		this.typeAyantDroit = typeAyantDroit;
		this.adresseAyantDroit = adresseAyantDroit;
		this.contactAyantDroit = contactAyantDroit;
		this.reglementSinistres = reglementSinistres;
		this.prejudices = prejudices;
	}

	@Id
	@Column(name = "CODE_AYANT_DROIT", unique = true, nullable = false, length = 10)
	public String getCodeAyantDroit() {
		return this.codeAyantDroit;
	}

	public void setCodeAyantDroit(String codeAyantDroit) {
		this.codeAyantDroit = codeAyantDroit;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NUM_VICTIME", nullable = false)
	public Victime getVictime() {
		return this.victime;
	}

	public void setVictime(Victime victime) {
		this.victime = victime;
	}

	@Column(name = "NOM", length = 30)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "PRENOM", length = 100)
	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Column(name = "AGE")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "SEXE", length = 2)
	public String getSexe() {
		return this.sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	@Column(name = "MONTANT_PREJUDICE", precision = 15, scale = 3)
	public BigDecimal getMontantPrejudice() {
		return this.montantPrejudice;
	}

	public void setMontantPrejudice(BigDecimal montantPrejudice) {
		this.montantPrejudice = montantPrejudice;
	}

	@Column(name = "ETAT_INDEMNISER")
	public Boolean getEtatIndemniser() {
		return this.etatIndemniser;
	}

	public void setEtatIndemniser(Boolean etatIndemniser) {
		this.etatIndemniser = etatIndemniser;
	}

	@Column(name = "TYPE_AYANT_DROIT", length = 100)
	public String getTypeAyantDroit() {
		return this.typeAyantDroit;
	}

	public void setTypeAyantDroit(String typeAyantDroit) {
		this.typeAyantDroit = typeAyantDroit;
	}

	@Column(name = "ADRESSE_AYANT_DROIT", length = 100)
	public String getAdresseAyantDroit() {
		return this.adresseAyantDroit;
	}

	public void setAdresseAyantDroit(String adresseAyantDroit) {
		this.adresseAyantDroit = adresseAyantDroit;
	}

	@Column(name = "CONTACT_AYANT_DROIT", length = 20)
	public String getContactAyantDroit() {
		return this.contactAyantDroit;
	}

	public void setContactAyantDroit(String contactAyantDroit) {
		this.contactAyantDroit = contactAyantDroit;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ayantDroit")
	public Set<ReglementSinistre> getReglementSinistres() {
		return this.reglementSinistres;
	}

	public void setReglementSinistres(Set<ReglementSinistre> reglementSinistres) {
		this.reglementSinistres = reglementSinistres;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ayantDroit")
	public Set<Prejudice> getPrejudices() {
		return this.prejudices;
	}

	public void setPrejudices(Set<Prejudice> prejudices) {
		this.prejudices = prejudices;
	}

}
