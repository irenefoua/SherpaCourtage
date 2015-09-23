package com.j3a.assurance.utilitaires;

import java.util.ArrayList;
import java.util.List;

public class GarantiesParOption {
	private List<Garanties> listegaranties  = new ArrayList<Garanties>();
	private String optionGarantie = "TS";
	
	
	public List<Garanties> getListegaranties() {
		return listegaranties;
	}
	public void setListegaranties(List<Garanties> listegaranties) {
		this.listegaranties = listegaranties;
	}
	public String getOptionGarantie() {
		return optionGarantie;
	}
	public void setOptionGarantie(String optionGarantie) {
		this.optionGarantie = optionGarantie;
	}
	
	
}
