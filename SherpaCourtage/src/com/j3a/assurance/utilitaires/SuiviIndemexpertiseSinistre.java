package com.j3a.assurance.utilitaires;

import java.math.BigDecimal;

public class SuiviIndemexpertiseSinistre {
	
	private String sinistre;
	private String refexpertise;
	private String codeexpert;
	private String nomexpert; 
	private String codefacture;
	private BigDecimal frais;
	private String statut;
	
	
	
	public String getSinistre() {
		return sinistre;
	}
	public void setSinistre(String sinistre) {
		this.sinistre = sinistre;
	}
	public String getNomexpert() {
		return nomexpert;
	}
	public void setNomexpert(String nomexpert) {
		this.nomexpert = nomexpert;
	}
	public BigDecimal getFrais() {
		return frais;
	}
	public void setFrais(BigDecimal frais) {
		this.frais = frais;
	}
	public String getRefexpertise() {
		return refexpertise;
	}
	public void setRefexpertise(String refexpertise) {
		this.refexpertise = refexpertise;
	}
	public String getCodeexpert() {
		return codeexpert;
	}
	public void setCodeexpert(String codeexpert) {
		this.codeexpert = codeexpert;
	}
	public String getCodefacture() {
		return codefacture;
	}
	public void setCodefacture(String codefacture) {
		this.codefacture = codefacture;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	

}
