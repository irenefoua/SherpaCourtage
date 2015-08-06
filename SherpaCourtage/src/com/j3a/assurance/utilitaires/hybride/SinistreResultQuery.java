package com.j3a.assurance.utilitaires.hybride;

import java.math.BigDecimal;

public class SinistreResultQuery {

	private java.lang.String libelle;
	private java.math.BigDecimal totalNette;
	private java.math.BigDecimal totalBrute;
	
	public SinistreResultQuery(){
		setLibelle("");
		setTotalBrute(BigDecimal.ZERO);
		setTotalNette(BigDecimal.ZERO);
	}
	public java.lang.String getLibelle() {
		return libelle;
	}
	public void setLibelle(java.lang.String libelle) {
		this.libelle = libelle;
	}
	public java.math.BigDecimal getTotalNette() {
		return totalNette;
	}
	public void setTotalNette(java.math.BigDecimal totalNette) {
		this.totalNette = totalNette;
	}
	public java.math.BigDecimal getTotalBrute() {
		return totalBrute;
	}
	public void setTotalBrute(java.math.BigDecimal totalBrute) {
		this.totalBrute = totalBrute;
	}
}
