package com.j3a.assurance.reporting.bean;

import com.j3a.assurance.model.Garantie;
import com.j3a.assurance.model.GarantieGarantieChoisie;

/**
 * Classe qui encapsule la garantie et sa prime
 * 
 * @author J3A-Poste4
 * 
 */
public class PrimeByGarantie  {

	private Garantie garantie;
	private GarantieGarantieChoisie choisie;

	public Garantie getGarantie() {
		return garantie;
	}

	public void setGarantie(Garantie garantie) {
		this.garantie = garantie;
	}

	public GarantieGarantieChoisie getChoisie() {
		return choisie;
	}

	public void setChoisie(GarantieGarantieChoisie choisie) {
		this.choisie = choisie;
	}
}
