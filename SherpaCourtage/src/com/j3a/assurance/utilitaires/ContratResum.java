package com.j3a.assurance.utilitaires;

import java.util.ArrayList;
import java.util.List;

import com.j3a.assurance.model.Avenant;
import com.j3a.assurance.model.Conducteur;
import com.j3a.assurance.model.Contrat;
import com.j3a.assurance.model.Garantie;
import com.j3a.assurance.model.GarantieChoisie;
import com.j3a.assurance.model.Morale;
import com.j3a.assurance.model.Personne;
import com.j3a.assurance.model.Physique;
import com.j3a.assurance.model.Vehicule;
//import com.j3a.assurance.hibernate.bean.ProprietesVehicule;

public class ContratResum {
	private Personne personneResum = new Personne();
	private Contrat policeResum = new Contrat();
	private Avenant avenantResum = new Avenant();
	private Physique physiqueResum = new Physique();
	private Morale moraleResum = new Morale();
	private Vehicule vehiculeResum = new Vehicule();
	private Conducteur conducteurResum = new Conducteur();
	private GarantieChoisie garantieChoisieResum = new GarantieChoisie();
	private List<Garantie> listGaratieResume = new ArrayList<>();
	
	
	//*******************************ACCESSEUR***********************//
	
	public Personne getPersonneResum() {
		return personneResum;
	}
	public void setPersonneResum(Personne personneResum) {
		this.personneResum = personneResum;
	}
	
	public Avenant getAvenantResum() {
		return avenantResum;
	}
	public void setAvenantResum(Avenant avenantResum) {
		this.avenantResum = avenantResum;
	}
	
	public Physique getPhysiqueResum() {
		return physiqueResum;
	}
	public void setPhysiqueResum(Physique physiqueResum) {
		this.physiqueResum = physiqueResum;
	}
	public Morale getMoraleResum() {
		return moraleResum;
	}
	public void setMoraleResum(Morale moraleResum) {
		this.moraleResum = moraleResum;
	}

	public GarantieChoisie getGarantieChoisieResum() {
		return garantieChoisieResum;
	}
	public void setGarantieChoisieResum(GarantieChoisie garantieChoisieResum) {
		this.garantieChoisieResum = garantieChoisieResum;
	}
	public List<Garantie> getListGaratieResume() {
		return listGaratieResume;
	}
	public void setListGaratieResume(List<Garantie> listGaratieResume) {
		this.listGaratieResume = listGaratieResume;
	}
	public Vehicule getVehiculeResum() {
		return vehiculeResum;
	}
	public void setVehiculeResum(Vehicule vehiculeResum) {
		this.vehiculeResum = vehiculeResum;
	}
	public Contrat getPoliceResum() {
		return policeResum;
	}
	public void setPoliceResum(Contrat policeResum) {
		this.policeResum = policeResum;
	}
	public Conducteur getConducteurResum() {
		return conducteurResum;
	}
	public void setConducteurResum(Conducteur conducteurResum) {
		this.conducteurResum = conducteurResum;
	}	
}
