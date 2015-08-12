package com.j3a.assurance.model;

// Generated 12 ao�t 2015 16:21:18 by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AffilieSante generated by hbm2java
 */
@Entity
@Table(name = "affilie_sante", catalog = "zeusbd")
public class AffilieSante implements java.io.Serializable {

	private String codeAffilie;
	private String nomAfflilie;
	private String prenomAffilie;
	private Date naissAffilie;
	private String adresseAffilie;
	private String sexeAffilie;
	private String lienAffilie;
	private String residenceAffilie;
	private String mobileAffilie;
	private String courrielAffilie;
	private String natExtAffilie;
	private String formuleAffilie;
	private String statutaffiliesante;
	private Set<AffilieListeAffilie> affilieListeAffilies = new HashSet<AffilieListeAffilie>(
			0);
	private Set<GarantieChoisieSante> garantieChoisieSantes = new HashSet<GarantieChoisieSante>(
			0);

	public AffilieSante() {
	}

	public AffilieSante(String codeAffilie) {
		this.codeAffilie = codeAffilie;
	}

	public AffilieSante(String codeAffilie, String nomAfflilie,
			String prenomAffilie, Date naissAffilie, String adresseAffilie,
			String sexeAffilie, String lienAffilie, String residenceAffilie,
			String mobileAffilie, String courrielAffilie, String natExtAffilie,
			String formuleAffilie, String statutaffiliesante,
			Set<AffilieListeAffilie> affilieListeAffilies,
			Set<GarantieChoisieSante> garantieChoisieSantes) {
		this.codeAffilie = codeAffilie;
		this.nomAfflilie = nomAfflilie;
		this.prenomAffilie = prenomAffilie;
		this.naissAffilie = naissAffilie;
		this.adresseAffilie = adresseAffilie;
		this.sexeAffilie = sexeAffilie;
		this.lienAffilie = lienAffilie;
		this.residenceAffilie = residenceAffilie;
		this.mobileAffilie = mobileAffilie;
		this.courrielAffilie = courrielAffilie;
		this.natExtAffilie = natExtAffilie;
		this.formuleAffilie = formuleAffilie;
		this.statutaffiliesante = statutaffiliesante;
		this.affilieListeAffilies = affilieListeAffilies;
		this.garantieChoisieSantes = garantieChoisieSantes;
	}

	@Id
	@Column(name = "CODE_AFFILIE", unique = true, nullable = false, length = 60)
	public String getCodeAffilie() {
		return this.codeAffilie;
	}

	public void setCodeAffilie(String codeAffilie) {
		this.codeAffilie = codeAffilie;
	}

	@Column(name = "NOM_AFFLILIE", length = 60)
	public String getNomAfflilie() {
		return this.nomAfflilie;
	}

	public void setNomAfflilie(String nomAfflilie) {
		this.nomAfflilie = nomAfflilie;
	}

	@Column(name = "PRENOM_AFFILIE", length = 80)
	public String getPrenomAffilie() {
		return this.prenomAffilie;
	}

	public void setPrenomAffilie(String prenomAffilie) {
		this.prenomAffilie = prenomAffilie;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "NAISS_AFFILIE", length = 10)
	public Date getNaissAffilie() {
		return this.naissAffilie;
	}

	public void setNaissAffilie(Date naissAffilie) {
		this.naissAffilie = naissAffilie;
	}

	@Column(name = "ADRESSE_AFFILIE", length = 80)
	public String getAdresseAffilie() {
		return this.adresseAffilie;
	}

	public void setAdresseAffilie(String adresseAffilie) {
		this.adresseAffilie = adresseAffilie;
	}

	@Column(name = "SEXE_AFFILIE", length = 9)
	public String getSexeAffilie() {
		return this.sexeAffilie;
	}

	public void setSexeAffilie(String sexeAffilie) {
		this.sexeAffilie = sexeAffilie;
	}

	@Column(name = "LIEN_AFFILIE", length = 60)
	public String getLienAffilie() {
		return this.lienAffilie;
	}

	public void setLienAffilie(String lienAffilie) {
		this.lienAffilie = lienAffilie;
	}

	@Column(name = "RESIDENCE_AFFILIE", length = 60)
	public String getResidenceAffilie() {
		return this.residenceAffilie;
	}

	public void setResidenceAffilie(String residenceAffilie) {
		this.residenceAffilie = residenceAffilie;
	}

	@Column(name = "MOBILE_AFFILIE", length = 20)
	public String getMobileAffilie() {
		return this.mobileAffilie;
	}

	public void setMobileAffilie(String mobileAffilie) {
		this.mobileAffilie = mobileAffilie;
	}

	@Column(name = "COURRIEL_AFFILIE", length = 80)
	public String getCourrielAffilie() {
		return this.courrielAffilie;
	}

	public void setCourrielAffilie(String courrielAffilie) {
		this.courrielAffilie = courrielAffilie;
	}

	@Column(name = "NAT_EXT_AFFILIE", length = 30)
	public String getNatExtAffilie() {
		return this.natExtAffilie;
	}

	public void setNatExtAffilie(String natExtAffilie) {
		this.natExtAffilie = natExtAffilie;
	}

	@Column(name = "FORMULE_AFFILIE", length = 80)
	public String getFormuleAffilie() {
		return this.formuleAffilie;
	}

	public void setFormuleAffilie(String formuleAffilie) {
		this.formuleAffilie = formuleAffilie;
	}

	@Column(name = "STATUTAFFILIESANTE", length = 20)
	public String getStatutaffiliesante() {
		return this.statutaffiliesante;
	}

	public void setStatutaffiliesante(String statutaffiliesante) {
		this.statutaffiliesante = statutaffiliesante;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "affilieSante")
	public Set<AffilieListeAffilie> getAffilieListeAffilies() {
		return this.affilieListeAffilies;
	}

	public void setAffilieListeAffilies(
			Set<AffilieListeAffilie> affilieListeAffilies) {
		this.affilieListeAffilies = affilieListeAffilies;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "affilieSante")
	public Set<GarantieChoisieSante> getGarantieChoisieSantes() {
		return this.garantieChoisieSantes;
	}

	public void setGarantieChoisieSantes(
			Set<GarantieChoisieSante> garantieChoisieSantes) {
		this.garantieChoisieSantes = garantieChoisieSantes;
	}

}
