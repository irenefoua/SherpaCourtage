package com.j3a.assurance.model;

// Generated 10 ao�t 2015 15:05:20 by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Personne generated by hbm2java
 */
@Entity
@Table(name = "personne", catalog = "zeusbd")
public class Personne implements java.io.Serializable {

	private String numSouscripteur;
	private Pays pays;
	private UserRole userRole;
	private Date datePers;
	private String nomRaisonSociale;
	private String adresseGeo;
	private String adresse;
	private String telephone;
	private String fax;
	private String email;
	private String loginPers;
	private String motPassePers;
	private Boolean enable;
	private Set<Contrat> contrats = new HashSet<Contrat>(0);
	private Set<Etre> etres = new HashSet<Etre>(0);
	private Physique physique;
	private Morale morale;
	private Set<PersonneNationalite> personneNationalites = new HashSet<PersonneNationalite>(
			0);

	public Personne() {
	}

	public Personne(String numSouscripteur, UserRole userRole) {
		this.numSouscripteur = numSouscripteur;
		this.userRole = userRole;
	}

	public Personne(String numSouscripteur, Pays pays, UserRole userRole,
			Date datePers, String nomRaisonSociale, String adresseGeo,
			String adresse, String telephone, String fax, String email,
			String loginPers, String motPassePers, Boolean enable,
			Set<Contrat> contrats, Set<Etre> etres, Physique physique,
			Morale morale, Set<PersonneNationalite> personneNationalites) {
		this.numSouscripteur = numSouscripteur;
		this.pays = pays;
		this.userRole = userRole;
		this.datePers = datePers;
		this.nomRaisonSociale = nomRaisonSociale;
		this.adresseGeo = adresseGeo;
		this.adresse = adresse;
		this.telephone = telephone;
		this.fax = fax;
		this.email = email;
		this.loginPers = loginPers;
		this.motPassePers = motPassePers;
		this.enable = enable;
		this.contrats = contrats;
		this.etres = etres;
		this.physique = physique;
		this.morale = morale;
		this.personneNationalites = personneNationalites;
	}

	@Id
	@Column(name = "NUM_SOUSCRIPTEUR", unique = true, nullable = false, length = 15)
	public String getNumSouscripteur() {
		return this.numSouscripteur;
	}

	public void setNumSouscripteur(String numSouscripteur) {
		this.numSouscripteur = numSouscripteur;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_PAYS")
	public Pays getPays() {
		return this.pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ROLE_ID", nullable = false)
	public UserRole getUserRole() {
		return this.userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_PERS", length = 10)
	public Date getDatePers() {
		return this.datePers;
	}

	public void setDatePers(Date datePers) {
		this.datePers = datePers;
	}

	@Column(name = "NOM_RAISON_SOCIALE", length = 50)
	public String getNomRaisonSociale() {
		return this.nomRaisonSociale;
	}

	public void setNomRaisonSociale(String nomRaisonSociale) {
		this.nomRaisonSociale = nomRaisonSociale;
	}

	@Column(name = "ADRESSE_GEO", length = 75)
	public String getAdresseGeo() {
		return this.adresseGeo;
	}

	public void setAdresseGeo(String adresseGeo) {
		this.adresseGeo = adresseGeo;
	}

	@Column(name = "ADRESSE", length = 60)
	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Column(name = "TELEPHONE", length = 16)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "FAX", length = 16)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "EMAIL", length = 60)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "LOGIN_PERS", length = 20)
	public String getLoginPers() {
		return this.loginPers;
	}

	public void setLoginPers(String loginPers) {
		this.loginPers = loginPers;
	}

	@Column(name = "MOT_PASSE_PERS", length = 10)
	public String getMotPassePers() {
		return this.motPassePers;
	}

	public void setMotPassePers(String motPassePers) {
		this.motPassePers = motPassePers;
	}

	@Column(name = "ENABLE")
	public Boolean getEnable() {
		return this.enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "personne")
	public Set<Contrat> getContrats() {
		return this.contrats;
	}

	public void setContrats(Set<Contrat> contrats) {
		this.contrats = contrats;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "personne")
	public Set<Etre> getEtres() {
		return this.etres;
	}

	public void setEtres(Set<Etre> etres) {
		this.etres = etres;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "personne")
	public Physique getPhysique() {
		return this.physique;
	}

	public void setPhysique(Physique physique) {
		this.physique = physique;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "personne")
	public Morale getMorale() {
		return this.morale;
	}

	public void setMorale(Morale morale) {
		this.morale = morale;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "personne")
	public Set<PersonneNationalite> getPersonneNationalites() {
		return this.personneNationalites;
	}

	public void setPersonneNationalites(
			Set<PersonneNationalite> personneNationalites) {
		this.personneNationalites = personneNationalites;
	}

}
