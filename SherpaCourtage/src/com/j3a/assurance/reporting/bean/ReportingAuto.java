package com.j3a.assurance.reporting.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.j3a.assurance.model.Avenant;
import com.j3a.assurance.model.Contrat;
import com.j3a.assurance.model.Morale;
import com.j3a.assurance.model.Personne;
import com.j3a.assurance.model.Physique;
import com.j3a.assurance.model.PointVente;
import com.j3a.assurance.model.Quittance;
import com.j3a.assurance.model.Risque;
import com.j3a.assurance.model.SocieteAssurance;
import com.j3a.assurance.model.Vehicule;
import com.j3a.assurance.model.VehiculesAssures;
import com.j3a.assurance.utilitaires.QuittanceAuto;
import com.j3a.assurance.utilitaires.VehiculeRow;

@Component
public class ReportingAuto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Attribut d'instance
	private SocieteAssurance societeAssurance = new SocieteAssurance();
	private Contrat contrat = new Contrat();
	private Risque risque = new Risque();
	private Personne personne = new Personne();
	private String Nom;
	private Physique  physique = new Physique();
	private Morale morale = new Morale();
	private Avenant avenant = new Avenant();
	private PointVente pointVente = new PointVente();
	private Quittance quittance = new Quittance();
	private QuittanceAuto quittanceAuto = new QuittanceAuto();
	private VehiculesAssures vehiculesAssures = new VehiculesAssures();
	private List<Vehicule> listVehiculeAssure = new ArrayList<>();
	private List<VehiculeRow> listVehiculeRow = new ArrayList<VehiculeRow>();

		
		
	/************************************ACCESSEURS****************************************************/
	public Personne getPersonne() {
		return personne;
	}
	public void setPersonne(Personne personne) {
		this.personne = personne;
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

	public Contrat getContrat() {
		return contrat;
	}
	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}
	public Risque getRisque() {
		return risque;
	}
	public void setRisque(Risque risque) {
		this.risque = risque;
	}
	public Avenant getAvenant() {
		return avenant;
	}
	public void setAvenant(Avenant avenant) {
		this.avenant = avenant;
	}
	public Quittance getQuittance() {
		return quittance;
	}
	public void setQuittance(Quittance quittance) {
		this.quittance = quittance;
	}
	
	public PointVente getPointVente() {
		return pointVente;
	}
	public void setPointVente(PointVente pointVente) {
		this.pointVente = pointVente;
	}
	
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	
	public VehiculesAssures getVehiculesAssures() {
		return vehiculesAssures;
	}
	public void setVehiculesAssures(VehiculesAssures vehiculesAssures) {
		this.vehiculesAssures = vehiculesAssures;
	}
	public List<Vehicule> getListVehiculeAssure() {
		return listVehiculeAssure;
	}
	public void setListVehiculeAssure(List<Vehicule> listVehiculeAssure) {
		this.listVehiculeAssure = listVehiculeAssure;
	}
	public SocieteAssurance getSocieteAssurance() {
		return societeAssurance;
	}
	public void setSocieteAssurance(SocieteAssurance societeAssurance) {
		this.societeAssurance = societeAssurance;
	}
	public List<VehiculeRow> getListVehiculeRow() {
		return listVehiculeRow;
	}
	public void setListVehiculeRow(List<VehiculeRow> listVehiculeRow) {
		this.listVehiculeRow = listVehiculeRow;
	}
	public QuittanceAuto getQuittanceAuto() {
		return quittanceAuto;
	}
	public void setQuittanceAuto(QuittanceAuto quittanceAuto) {
		this.quittanceAuto = quittanceAuto;
	}
	
}
