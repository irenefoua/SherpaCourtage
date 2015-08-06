package com.j3a.assurance.utilitaires;

import java.math.BigDecimal;

import com.j3a.assurance.model.Risque;

public class ChiffreAffAndRisq {
	
	public ChiffreAffAndRisq(){
		
	}

	private BigDecimal chiffrAffaire;
	private Risque risque;
	public BigDecimal getChiffrAffaire() {
		return chiffrAffaire;
	}
	public Risque getRisque() {
		return risque;
	}
	public void setChiffrAffaire(BigDecimal chiffrAffaire) {
		this.chiffrAffaire = chiffrAffaire;
	}
	public void setRisque(Risque risque) {
		this.risque = risque;
	}
	
	
}
