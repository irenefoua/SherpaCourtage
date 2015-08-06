package com.j3a.assurance.utilitaires;

import java.util.ArrayList;
import java.util.List;

import com.j3a.assurance.model.Risque;

public class RisqueRow {
	Risque risque;
	List<MouvementRow> ListMouvement = new ArrayList<MouvementRow>();

	public RisqueRow() {
		// TODO Auto-generated constructor stub
	}

	public Risque getRisque() {
		return risque;
	}

	public void setRisque(Risque risque) {
		this.risque = risque;
	}

	public List<MouvementRow> getListMouvement() {
		return ListMouvement;
	}

	public void setListMouvement(List<MouvementRow> listMouvement) {
		ListMouvement = listMouvement;
	}

}
