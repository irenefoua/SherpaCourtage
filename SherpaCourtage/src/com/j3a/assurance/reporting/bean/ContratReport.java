package com.j3a.assurance.reporting.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.j3a.assurance.model.Avenant;
import com.j3a.assurance.model.Contrat;
import com.j3a.assurance.model.Morale;
import com.j3a.assurance.model.Personne;
import com.j3a.assurance.model.Physique;
import com.j3a.assurance.model.Quittance;

/**
 * Classe qui encapsule les informations des conditions Particulières
 * 
 * @author J3A-Poste4
 * 
 */
public class ContratReport implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numSouscripteur;
	private String police;
	private String numAttestation;
	private String nomSouscripteur;
	private String prenomSouscripteur;
	private String adresseSouscripteur;
	private String telSouscripteur;
	private String agent;
	private String modeReconduction;
	private Date effet;
	private Date echeance;
	private int duree;

	private Quittance quittance;
	private Avenant avenant;
	private Contrat contrat;
	private Personne personne;
	private Physique physique;
	private Morale morale;
	private PrimeByGarantie primeByGarantie;

	List<VehiculeAttributsBean> listVehiculeAttributs;

	public String getNumSouscripteur() {
		return numSouscripteur;
	}

	public void setNumSouscripteur(String numSouscripteur) {
		this.numSouscripteur = numSouscripteur;
	}

	public String getPolice() {
		return police;
	}

	public void setPolice(String police) {
		this.police = police;
	}

	public String getNumAttestation() {
		return numAttestation;
	}

	public Physique getPhysique() {
		return physique;
	}

	public void setPhysique(Physique physique) {
		this.physique = physique;
	}

	public Morale getMorale() {
		return morale;
	}

	public void setMorale(Morale morale) {
		this.morale = morale;
	}

	public void setNumAttestation(String numAttestation) {
		this.numAttestation = numAttestation;
	}

	public String getNomSouscripteur() {
		return nomSouscripteur;
	}

	public void setNomSouscripteur(String nomSouscripteur) {
		this.nomSouscripteur = nomSouscripteur;
	}

	public String getPrenomSouscripteur() {
		return prenomSouscripteur;
	}

	public void setPrenomSouscripteur(String prenomSouscripteur) {
		this.prenomSouscripteur = prenomSouscripteur;
	}

	public String getAdresseSouscripteur() {
		return adresseSouscripteur;
	}

	public void setAdresseSouscripteur(String adresseSouscripteur) {
		this.adresseSouscripteur = adresseSouscripteur;
	}

	public String getTelSouscripteur() {
		return telSouscripteur;
	}

	public void setTelSouscripteur(String telSouscripteur) {
		this.telSouscripteur = telSouscripteur;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getModeReconduction() {
		return modeReconduction;
	}

	public void setModeReconduction(String modeReconduction) {
		this.modeReconduction = modeReconduction;
	}

	public Date getEffet() {
		return effet;
	}

	public void setEffet(Date effet) {
		this.effet = effet;
	}

	public Date getEcheance() {
		return echeance;
	}

	public void setEcheance(Date echeance) {
		this.echeance = echeance;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public Quittance getQuittance() {
		return quittance;
	}

	public void setQuittance(Quittance quittance) {
		this.quittance = quittance;
	}

	public Avenant getAvenant() {
		return avenant;
	}

	public void setAvenant(Avenant avenant) {
		this.avenant = avenant;
	}

	public Contrat getContrat() {
		return contrat;
	}

	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public List<VehiculeAttributsBean> getListVehiculeAttributs() {
		return listVehiculeAttributs;
	}

	public void setListVehiculeAttributs(
			List<VehiculeAttributsBean> listVehiculeAttributs) {
		this.listVehiculeAttributs = listVehiculeAttributs;
	}

	public PrimeByGarantie getPrimeByGarantie() {
		return primeByGarantie;
	}

	public void setPrimeByGarantie(PrimeByGarantie primeByGarantie) {
		this.primeByGarantie = primeByGarantie;
	}
}
