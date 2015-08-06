package com.j3a.assurance.utilitaires;

import java.util.ArrayList;
import java.util.List;

public class MouvementRow {
	private String mouvement;
	List<AvenantByPointVenteRow> ListAvenantRow = new ArrayList<AvenantByPointVenteRow>();

	public MouvementRow() {

	}

	public String getMouvement() {
		return mouvement;
	}

	public void setMouvement(String mouvement) {
		this.mouvement = mouvement;
	}

	public List<AvenantByPointVenteRow> getListAvenantRow() {
		return ListAvenantRow;
	}

	public void setListAvenantRow(List<AvenantByPointVenteRow> listAvenantRow) {
		ListAvenantRow = listAvenantRow;
	}

}
