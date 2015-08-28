package com.j3a.assurance.utilitaires.hybride;

import java.math.BigDecimal;

import com.j3a.assurance.model.Avenant;
import com.j3a.assurance.model.Quittance;

public class ChiffreAffaireRow {
	private BigDecimal ca;
	private Avenant avenant=new Avenant();
	private Quittance quittance=new Quittance();
	private int coExercice;
	private int mois;

	public BigDecimal getCa() {
		return ca;
	}

	public void setCa(BigDecimal ca) {
		this.ca = ca;
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

	public int getCoExercice() {
		return coExercice;
	}

	public void setCoExercice(int coExercice) {
		this.coExercice = coExercice;
	}

	public int getMois() {
		return mois;
	}

	public void setMois(int mois) {
		this.mois = mois;
	}

}
