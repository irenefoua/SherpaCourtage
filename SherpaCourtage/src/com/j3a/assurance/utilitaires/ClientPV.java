package com.j3a.assurance.utilitaires;

public class ClientPV {
	private String pointVente;
	private int totalSouscripteur;
	private String mouvement;
	
	
	
	
	/************************ACCESSEURS****************************/
	public int getTotalSouscripteur() {
		return totalSouscripteur;
	}
	public void setTotalSouscripteur(int totalSouscripteur) {
		this.totalSouscripteur = totalSouscripteur;
	}
	public String getMouvement() {
		return mouvement;
	}
	public void setMouvement(String mouvement) {
		this.mouvement = mouvement;
	}
	public String getPointVente() {
		return pointVente;
	}
	public void setPointVente(String pointVente) {
		this.pointVente = pointVente;
	}

}
