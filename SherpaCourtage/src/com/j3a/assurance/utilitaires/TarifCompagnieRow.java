package com.j3a.assurance.utilitaires;

import java.util.ArrayList;
import java.util.List;

import com.j3a.assurance.model.CompagnieAssurance;
import com.j3a.assurance.model.Tarif;
import com.j3a.assurance.model.Tarifweb;

public class TarifCompagnieRow {
 private CompagnieAssurance compagnieAssurance  = new CompagnieAssurance();
 private Tarifweb tarifweb = new Tarifweb();
 private Tarif tarif = new Tarif();
 private List<Garanties> garantiesList = new ArrayList<Garanties>();
 private int numOrdr;
	public TarifCompagnieRow() {
		
	}
	
	
	public CompagnieAssurance getCompagnieAssurance() {
		return compagnieAssurance;
	}
	public void setCompagnieAssurance(CompagnieAssurance compagnieAssurance) {
		this.compagnieAssurance = compagnieAssurance;
	}
	public Tarifweb getTarifweb() {
		return tarifweb;
	}
	public void setTarifweb(Tarifweb tarifweb) {
		this.tarifweb = tarifweb;
	}
	public Tarif getTarif() {
		return tarif;
	}
	public void setTarif(Tarif tarif) {
		this.tarif = tarif;
	}
	public List<Garanties> getGarantiesList() {
		return garantiesList;
	}
	public void setGarantiesList(List<Garanties> garantiesList) {
		this.garantiesList = garantiesList;
	}

	public int getNumOrdr() {
		return numOrdr;
	}

	public void setNumOrdr(int numOrdr) {
		this.numOrdr = numOrdr;
	}

}
