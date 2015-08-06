package com.j3a.assurance.reporting.bean;

import java.io.Serializable;
import java.util.List;

import com.j3a.assurance.model.GarantieChoisie;
import com.j3a.assurance.model.Vehicule;

/**
 * @author J3A-Poste4
 * 
 *         objet: Elle permet d'associer un vehicule à ses propriétés et
 *         garanties valides
 * 
 */
public class VehiculeAttributsBean implements Serializable {
	private Vehicule vehicule;
	
	private GarantieChoisie garantieChoisie;
	/**
	 * propriété qui recupère la liste des objets {@link PrimeByGarantie}
	 */
	List<PrimeByGarantie> listPrimeGaranties;

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	public GarantieChoisie getGarantieChoisie() {
		return garantieChoisie;
	}

	public void setGarantieChoisie(GarantieChoisie garantieChoisie) {
		this.garantieChoisie = garantieChoisie;
	}

	public List<PrimeByGarantie> getListPrimeGaranties() {
		return listPrimeGaranties;
	}

	public void setListPrimeGaranties(List<PrimeByGarantie> listPrimeGaranties) {
		this.listPrimeGaranties = listPrimeGaranties;
	}
}
