package com.j3a.assurance.model;

// Generated 10 ao�t 2015 15:53:59 by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Morale generated by hbm2java
 */
@Entity
@Table(name = "morale", catalog = "zeusbd")
public class Morale implements java.io.Serializable {

	private String numSouscripteur;
	private Personne personne;
	private String codePays;
	private Integer userRoleId;
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
	private String dirigeant;
	private String numCc;
	private String numRc;
	private String mailDirigeant;

	public Morale() {
	}

	public Morale(Personne personne) {
		this.personne = personne;
	}

	public Morale(Personne personne, String codePays, Integer userRoleId,
			Date datePers, String nomRaisonSociale, String adresseGeo,
			String adresse, String telephone, String fax, String email,
			String loginPers, String motPassePers, Boolean enable,
			String dirigeant, String numCc, String numRc, String mailDirigeant) {
		this.personne = personne;
		this.codePays = codePays;
		this.userRoleId = userRoleId;
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
		this.dirigeant = dirigeant;
		this.numCc = numCc;
		this.numRc = numRc;
		this.mailDirigeant = mailDirigeant;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "personne"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "NUM_SOUSCRIPTEUR", unique = true, nullable = false, length = 15)
	public String getNumSouscripteur() {
		return this.numSouscripteur;
	}

	public void setNumSouscripteur(String numSouscripteur) {
		this.numSouscripteur = numSouscripteur;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Personne getPersonne() {
		return this.personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	@Column(name = "CODE_PAYS", length = 15)
	public String getCodePays() {
		return this.codePays;
	}

	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}

	@Column(name = "USER_ROLE_ID")
	public Integer getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
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

	@Column(name = "DIRIGEANT", length = 70)
	public String getDirigeant() {
		return this.dirigeant;
	}

	public void setDirigeant(String dirigeant) {
		this.dirigeant = dirigeant;
	}

	@Column(name = "NUM_CC", length = 16)
	public String getNumCc() {
		return this.numCc;
	}

	public void setNumCc(String numCc) {
		this.numCc = numCc;
	}

	@Column(name = "NUM_RC", length = 16)
	public String getNumRc() {
		return this.numRc;
	}

	public void setNumRc(String numRc) {
		this.numRc = numRc;
	}

	@Column(name = "MAIL_DIRIGEANT", length = 40)
	public String getMailDirigeant() {
		return this.mailDirigeant;
	}

	public void setMailDirigeant(String mailDirigeant) {
		this.mailDirigeant = mailDirigeant;
	}

}
