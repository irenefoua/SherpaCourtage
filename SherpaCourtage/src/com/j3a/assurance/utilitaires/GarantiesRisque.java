package com.j3a.assurance.utilitaires;

import java.util.ArrayList;
import java.util.List;

import com.j3a.assurance.model.Risque;

public class GarantiesRisque {
	private Risque risque;
	private List<Garanties> ListGaranties = new ArrayList<Garanties>();

	public GarantiesRisque() {
		// TODO Auto-generated constructor stub
	}

	public Risque getRisque() {
		return risque;
	}

	public void setRisque(Risque risque) {
		this.risque = risque;
	}

	public List<Garanties> getListGaranties() {
		return ListGaranties;
	}

	public void setListGaranties(List<Garanties> listGaranties) {
		ListGaranties = listGaranties;
	}

}
