package com.j3a.assurance.utilitaires;

import java.util.List;

import com.j3a.assurance.model.PointVente;

public class nbrerisquesinistrebyPV {

	public nbrerisquesinistrebyPV() {
		// TODO Auto-generated constructor stub
	}


	private PointVente ptVte;
	private List<nbrebyrisquesinistre> listCAandRisq;

	
	public List<nbrebyrisquesinistre> getListCAandRisq() {
		return listCAandRisq;
	}

	public void setListCAandRisq(List<nbrebyrisquesinistre> listCAandRisq) {
		this.listCAandRisq = listCAandRisq;
	}

	public PointVente getPtVte() {
		return ptVte;
	}

	public void setPtVte(PointVente ptVte) {
		this.ptVte = ptVte;
	}
	
	
	
}
