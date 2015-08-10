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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AssureIa generated by hbm2java
 */
@Entity
@Table(name = "assure_ia", catalog = "zeusbd")
public class AssureIa implements java.io.Serializable {

	private String numIdentification;
	private Categorie categorie;
	private ClasseIa classeIa;
	private ListeAssureIa listeAssureIa;
	private String nomAssIa;
	private String prenomAssIa;
	private Date datenaissAssIa;
	private String lieunaissAssIa;
	private Integer dureeIj;
	private String activiteAssIa;
	private String professionAssIa;
	private String usageAssIa;
	private String sexeAssIa;
	private String adresseAssIa;
	private String statutassureia;
	private Set<AssureIaSinistre> assureIaSinistres = new HashSet<AssureIaSinistre>(
			0);
	private Set<GarantieChoisieIa> garantieChoisieIas = new HashSet<GarantieChoisieIa>(
			0);
	private Set<ApporteurAssure> apporteurAssures = new HashSet<ApporteurAssure>(
			0);
	private Set<Beneficiaire> beneficiaires = new HashSet<Beneficiaire>(0);

	public AssureIa() {
	}

	public AssureIa(String numIdentification) {
		this.numIdentification = numIdentification;
	}

	public AssureIa(String numIdentification, Categorie categorie,
			ClasseIa classeIa, ListeAssureIa listeAssureIa, String nomAssIa,
			String prenomAssIa, Date datenaissAssIa, String lieunaissAssIa,
			Integer dureeIj, String activiteAssIa, String professionAssIa,
			String usageAssIa, String sexeAssIa, String adresseAssIa,
			String statutassureia, Set<AssureIaSinistre> assureIaSinistres,
			Set<GarantieChoisieIa> garantieChoisieIas,
			Set<ApporteurAssure> apporteurAssures,
			Set<Beneficiaire> beneficiaires) {
		this.numIdentification = numIdentification;
		this.categorie = categorie;
		this.classeIa = classeIa;
		this.listeAssureIa = listeAssureIa;
		this.nomAssIa = nomAssIa;
		this.prenomAssIa = prenomAssIa;
		this.datenaissAssIa = datenaissAssIa;
		this.lieunaissAssIa = lieunaissAssIa;
		this.dureeIj = dureeIj;
		this.activiteAssIa = activiteAssIa;
		this.professionAssIa = professionAssIa;
		this.usageAssIa = usageAssIa;
		this.sexeAssIa = sexeAssIa;
		this.adresseAssIa = adresseAssIa;
		this.statutassureia = statutassureia;
		this.assureIaSinistres = assureIaSinistres;
		this.garantieChoisieIas = garantieChoisieIas;
		this.apporteurAssures = apporteurAssures;
		this.beneficiaires = beneficiaires;
	}

	@Id
	@Column(name = "NUM_IDENTIFICATION", unique = true, nullable = false, length = 25)
	public String getNumIdentification() {
		return this.numIdentification;
	}

	public void setNumIdentification(String numIdentification) {
		this.numIdentification = numIdentification;
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
	@JoinColumn(name = "CODE_CLASSE_IA")
	public ClasseIa getClasseIa() {
		return this.classeIa;
	}

	public void setClasseIa(ClasseIa classeIa) {
		this.classeIa = classeIa;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_LISTE_ASSURE_IA")
	public ListeAssureIa getListeAssureIa() {
		return this.listeAssureIa;
	}

	public void setListeAssureIa(ListeAssureIa listeAssureIa) {
		this.listeAssureIa = listeAssureIa;
	}

	@Column(name = "NOM_ASS_IA", length = 20)
	public String getNomAssIa() {
		return this.nomAssIa;
	}

	public void setNomAssIa(String nomAssIa) {
		this.nomAssIa = nomAssIa;
	}

	@Column(name = "PRENOM_ASS_IA", length = 60)
	public String getPrenomAssIa() {
		return this.prenomAssIa;
	}

	public void setPrenomAssIa(String prenomAssIa) {
		this.prenomAssIa = prenomAssIa;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATENAISS_ASS_IA", length = 10)
	public Date getDatenaissAssIa() {
		return this.datenaissAssIa;
	}

	public void setDatenaissAssIa(Date datenaissAssIa) {
		this.datenaissAssIa = datenaissAssIa;
	}

	@Column(name = "LIEUNAISS_ASS_IA", length = 20)
	public String getLieunaissAssIa() {
		return this.lieunaissAssIa;
	}

	public void setLieunaissAssIa(String lieunaissAssIa) {
		this.lieunaissAssIa = lieunaissAssIa;
	}

	@Column(name = "DUREE_IJ")
	public Integer getDureeIj() {
		return this.dureeIj;
	}

	public void setDureeIj(Integer dureeIj) {
		this.dureeIj = dureeIj;
	}

	@Column(name = "ACTIVITE_ASS_IA", length = 30)
	public String getActiviteAssIa() {
		return this.activiteAssIa;
	}

	public void setActiviteAssIa(String activiteAssIa) {
		this.activiteAssIa = activiteAssIa;
	}

	@Column(name = "PROFESSION_ASS_IA", length = 40)
	public String getProfessionAssIa() {
		return this.professionAssIa;
	}

	public void setProfessionAssIa(String professionAssIa) {
		this.professionAssIa = professionAssIa;
	}

	@Column(name = "USAGE_ASS_IA", length = 40)
	public String getUsageAssIa() {
		return this.usageAssIa;
	}

	public void setUsageAssIa(String usageAssIa) {
		this.usageAssIa = usageAssIa;
	}

	@Column(name = "SEXE_ASS_IA", length = 20)
	public String getSexeAssIa() {
		return this.sexeAssIa;
	}

	public void setSexeAssIa(String sexeAssIa) {
		this.sexeAssIa = sexeAssIa;
	}

	@Column(name = "ADRESSE_ASS_IA_", length = 50)
	public String getAdresseAssIa() {
		return this.adresseAssIa;
	}

	public void setAdresseAssIa(String adresseAssIa) {
		this.adresseAssIa = adresseAssIa;
	}

	@Column(name = "STATUTASSUREIA", length = 40)
	public String getStatutassureia() {
		return this.statutassureia;
	}

	public void setStatutassureia(String statutassureia) {
		this.statutassureia = statutassureia;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "assureIa")
	public Set<AssureIaSinistre> getAssureIaSinistres() {
		return this.assureIaSinistres;
	}

	public void setAssureIaSinistres(Set<AssureIaSinistre> assureIaSinistres) {
		this.assureIaSinistres = assureIaSinistres;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "assureIa")
	public Set<GarantieChoisieIa> getGarantieChoisieIas() {
		return this.garantieChoisieIas;
	}

	public void setGarantieChoisieIas(Set<GarantieChoisieIa> garantieChoisieIas) {
		this.garantieChoisieIas = garantieChoisieIas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "assureIa")
	public Set<ApporteurAssure> getApporteurAssures() {
		return this.apporteurAssures;
	}

	public void setApporteurAssures(Set<ApporteurAssure> apporteurAssures) {
		this.apporteurAssures = apporteurAssures;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "assureIa")
	public Set<Beneficiaire> getBeneficiaires() {
		return this.beneficiaires;
	}

	public void setBeneficiaires(Set<Beneficiaire> beneficiaires) {
		this.beneficiaires = beneficiaires;
	}

}
