package com.j3a.assurance.utilitaires;

import java.util.ArrayList;
import java.util.List;

import com.j3a.assurance.model.PointVente;

public class PointVenteRow {
	PointVente pointVent;
	List<RisqueRow> ListRisque = new ArrayList<RisqueRow>();

	public PointVenteRow() {
		// TODO Auto-generated constructor stub
	}

	public PointVente getPointVent() {
		return pointVent;
	}

	public void setPointVent(PointVente pointVent) {
		this.pointVent = pointVent;
	}

	public List<RisqueRow> getListRisque() {
		return ListRisque;
	}

	public void setListRisqueRow(List<RisqueRow> listRisque) {
		ListRisque = listRisque;
	}

}
