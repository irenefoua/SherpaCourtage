package com.j3a.assurance.utilitaires;

import java.util.ArrayList;
import java.util.List;

public class AvenantPvRow {
	List<PointVenteRow> ListPointVente = new ArrayList<PointVenteRow>();

	public AvenantPvRow() {

	}

	public List<PointVenteRow> getListPointVente() {
		return ListPointVente;
	}

	public void setListPointVente(List<PointVenteRow> listPointVente) {
		ListPointVente = listPointVente;
	}

}
