package com.j3a.assurance.model;

// Generated 6 ao�t 2015 16:35:56 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ExpertSinistre generated by hbm2java
 */
@Entity
@Table(name = "expert_sinistre", catalog = "zeusbd")
public class ExpertSinistre implements java.io.Serializable {

	private String codeExpertSinistre;
	private String nomExpert;
	private String adresseExpert;
	private String contactExpert;
	private String mailExpert;
	private Set<Expertise> expertises = new HashSet<Expertise>(0);

	public ExpertSinistre() {
	}

	public ExpertSinistre(String codeExpertSinistre) {
		this.codeExpertSinistre = codeExpertSinistre;
	}

	public ExpertSinistre(String codeExpertSinistre, String nomExpert,
			String adresseExpert, String contactExpert, String mailExpert,
			Set<Expertise> expertises) {
		this.codeExpertSinistre = codeExpertSinistre;
		this.nomExpert = nomExpert;
		this.adresseExpert = adresseExpert;
		this.contactExpert = contactExpert;
		this.mailExpert = mailExpert;
		this.expertises = expertises;
	}

	@Id
	@Column(name = "CODE_EXPERT_SINISTRE", unique = true, nullable = false, length = 20)
	public String getCodeExpertSinistre() {
		return this.codeExpertSinistre;
	}

	public void setCodeExpertSinistre(String codeExpertSinistre) {
		this.codeExpertSinistre = codeExpertSinistre;
	}

	@Column(name = "NOM_EXPERT", length = 70)
	public String getNomExpert() {
		return this.nomExpert;
	}

	public void setNomExpert(String nomExpert) {
		this.nomExpert = nomExpert;
	}

	@Column(name = "ADRESSE_EXPERT", length = 60)
	public String getAdresseExpert() {
		return this.adresseExpert;
	}

	public void setAdresseExpert(String adresseExpert) {
		this.adresseExpert = adresseExpert;
	}

	@Column(name = "CONTACT_EXPERT", length = 20)
	public String getContactExpert() {
		return this.contactExpert;
	}

	public void setContactExpert(String contactExpert) {
		this.contactExpert = contactExpert;
	}

	@Column(name = "MAIL_EXPERT", length = 40)
	public String getMailExpert() {
		return this.mailExpert;
	}

	public void setMailExpert(String mailExpert) {
		this.mailExpert = mailExpert;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "expertSinistre")
	public Set<Expertise> getExpertises() {
		return this.expertises;
	}

	public void setExpertises(Set<Expertise> expertises) {
		this.expertises = expertises;
	}

}
