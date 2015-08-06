package com.j3a.assurance.model;

// Generated 6 ao�t 2015 16:35:56 by Hibernate Tools 4.3.1

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
 * Contrat generated by hbm2java
 */
@Entity
@Table(name = "contrat", catalog = "zeusbd")
public class Contrat implements java.io.Serializable {

	private String numPolice;
	private Apporteur apporteur;
	private Personne personne;
	private PointVente pointVente;
	private Risque risque;
	private SocieteAssurance societeAssurance;
	private String mentionParticuliere;
	private String remplace;
	private String reference;
	private BigDecimal commission;
	private String bareme;
	private String modeReconduction;
	private String typeContrat;
	private Set<Sinistre> sinistres = new HashSet<Sinistre>(0);
	private Set<Avenant> avenants = new HashSet<Avenant>(0);
	private Set<Coassurance> coassurances = new HashSet<Coassurance>(0);
	private Set<Reassurance> reassurances = new HashSet<Reassurance>(0);

	public Contrat() {
	}

	public Contrat(String numPolice, Personne personne, PointVente pointVente,
			Risque risque, SocieteAssurance societeAssurance) {
		this.numPolice = numPolice;
		this.personne = personne;
		this.pointVente = pointVente;
		this.risque = risque;
		this.societeAssurance = societeAssurance;
	}

	public Contrat(String numPolice, Apporteur apporteur, Personne personne,
			PointVente pointVente, Risque risque,
			SocieteAssurance societeAssurance, String mentionParticuliere,
			String remplace, String reference, BigDecimal commission,
			String bareme, String modeReconduction, String typeContrat,
			Set<Sinistre> sinistres, Set<Avenant> avenants,
			Set<Coassurance> coassurances, Set<Reassurance> reassurances) {
		this.numPolice = numPolice;
		this.apporteur = apporteur;
		this.personne = personne;
		this.pointVente = pointVente;
		this.risque = risque;
		this.societeAssurance = societeAssurance;
		this.mentionParticuliere = mentionParticuliere;
		this.remplace = remplace;
		this.reference = reference;
		this.commission = commission;
		this.bareme = bareme;
		this.modeReconduction = modeReconduction;
		this.typeContrat = typeContrat;
		this.sinistres = sinistres;
		this.avenants = avenants;
		this.coassurances = coassurances;
		this.reassurances = reassurances;
	}

	@Id
	@Column(name = "NUM_POLICE", unique = true, nullable = false, length = 20)
	public String getNumPolice() {
		return this.numPolice;
	}

	public void setNumPolice(String numPolice) {
		this.numPolice = numPolice;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_APPORTEUR")
	public Apporteur getApporteur() {
		return this.apporteur;
	}

	public void setApporteur(Apporteur apporteur) {
		this.apporteur = apporteur;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NUM_SOUSCRIPTEUR", nullable = false)
	public Personne getPersonne() {
		return this.personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_POINT_VENTE", nullable = false)
	public PointVente getPointVente() {
		return this.pointVente;
	}

	public void setPointVente(PointVente pointVente) {
		this.pointVente = pointVente;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_RISQUE", nullable = false)
	public Risque getRisque() {
		return this.risque;
	}

	public void setRisque(Risque risque) {
		this.risque = risque;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_SOCIETE_ASSURANCE", nullable = false)
	public SocieteAssurance getSocieteAssurance() {
		return this.societeAssurance;
	}

	public void setSocieteAssurance(SocieteAssurance societeAssurance) {
		this.societeAssurance = societeAssurance;
	}

	@Column(name = "MENTION_PARTICULIERE", length = 70)
	public String getMentionParticuliere() {
		return this.mentionParticuliere;
	}

	public void setMentionParticuliere(String mentionParticuliere) {
		this.mentionParticuliere = mentionParticuliere;
	}

	@Column(name = "REMPLACE", length = 16)
	public String getRemplace() {
		return this.remplace;
	}

	public void setRemplace(String remplace) {
		this.remplace = remplace;
	}

	@Column(name = "REFERENCE", length = 16)
	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	@Column(name = "COMMISSION", precision = 15, scale = 3)
	public BigDecimal getCommission() {
		return this.commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	@Column(name = "BAREME", length = 30)
	public String getBareme() {
		return this.bareme;
	}

	public void setBareme(String bareme) {
		this.bareme = bareme;
	}

	@Column(name = "MODE_RECONDUCTION", length = 50)
	public String getModeReconduction() {
		return this.modeReconduction;
	}

	public void setModeReconduction(String modeReconduction) {
		this.modeReconduction = modeReconduction;
	}

	@Column(name = "TYPE_CONTRAT", length = 50)
	public String getTypeContrat() {
		return this.typeContrat;
	}

	public void setTypeContrat(String typeContrat) {
		this.typeContrat = typeContrat;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contrat")
	public Set<Sinistre> getSinistres() {
		return this.sinistres;
	}

	public void setSinistres(Set<Sinistre> sinistres) {
		this.sinistres = sinistres;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contrat")
	public Set<Avenant> getAvenants() {
		return this.avenants;
	}

	public void setAvenants(Set<Avenant> avenants) {
		this.avenants = avenants;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contrat")
	public Set<Coassurance> getCoassurances() {
		return this.coassurances;
	}

	public void setCoassurances(Set<Coassurance> coassurances) {
		this.coassurances = coassurances;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contrat")
	public Set<Reassurance> getReassurances() {
		return this.reassurances;
	}

	public void setReassurances(Set<Reassurance> reassurances) {
		this.reassurances = reassurances;
	}

}
