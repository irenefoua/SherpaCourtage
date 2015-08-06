package com.j3a.assurance.model;

// Generated 6 juil. 2015 11:25:44 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Intervenant generated by hbm2java
 */
@Entity
@Table(name = "intervenant", catalog = "zeusbd")
public class Intervenant implements java.io.Serializable {

	private String codeIntervenant;
	private String nomIntervenant;
	private String contactIntervenant;
	private String typeIntervenant;
	private String adresse;
	private String telFixe;
	private String telCell;
	private String email;
	private Set<Intervention> interventions = new HashSet<Intervention>(0);

	public Intervenant() {
	}

	public Intervenant(String codeIntervenant) {
		this.codeIntervenant = codeIntervenant;
	}

	public Intervenant(String codeIntervenant, String nomIntervenant,
			String contactIntervenant, String typeIntervenant, String adresse,
			String telFixe, String telCell, String email,
			Set<Intervention> interventions) {
		this.codeIntervenant = codeIntervenant;
		this.nomIntervenant = nomIntervenant;
		this.contactIntervenant = contactIntervenant;
		this.typeIntervenant = typeIntervenant;
		this.adresse = adresse;
		this.telFixe = telFixe;
		this.telCell = telCell;
		this.email = email;
		this.interventions = interventions;
	}

	@Id
	@Column(name = "CODE_INTERVENANT", unique = true, nullable = false, length = 10)
	public String getCodeIntervenant() {
		return this.codeIntervenant;
	}

	public void setCodeIntervenant(String codeIntervenant) {
		this.codeIntervenant = codeIntervenant;
	}

	@Column(name = "NOM_INTERVENANT", length = 60)
	public String getNomIntervenant() {
		return this.nomIntervenant;
	}

	public void setNomIntervenant(String nomIntervenant) {
		this.nomIntervenant = nomIntervenant;
	}

	@Column(name = "CONTACT_INTERVENANT", length = 14)
	public String getContactIntervenant() {
		return this.contactIntervenant;
	}

	public void setContactIntervenant(String contactIntervenant) {
		this.contactIntervenant = contactIntervenant;
	}

	@Column(name = "TYPE_INTERVENANT", length = 100)
	public String getTypeIntervenant() {
		return this.typeIntervenant;
	}

	public void setTypeIntervenant(String typeIntervenant) {
		this.typeIntervenant = typeIntervenant;
	}

	@Column(name = "ADRESSE", length = 60)
	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Column(name = "TEL_FIXE", length = 15)
	public String getTelFixe() {
		return this.telFixe;
	}

	public void setTelFixe(String telFixe) {
		this.telFixe = telFixe;
	}

	@Column(name = "TEL_CELL", length = 15)
	public String getTelCell() {
		return this.telCell;
	}

	public void setTelCell(String telCell) {
		this.telCell = telCell;
	}

	@Column(name = "EMAIL", length = 60)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "intervenant")
	public Set<Intervention> getInterventions() {
		return this.interventions;
	}

	public void setInterventions(Set<Intervention> interventions) {
		this.interventions = interventions;
	}

}
