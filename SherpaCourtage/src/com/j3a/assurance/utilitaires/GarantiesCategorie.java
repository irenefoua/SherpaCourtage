package com.j3a.assurance.utilitaires;

import java.util.ArrayList;
import java.util.List;

import com.j3a.assurance.model.Categorie;

public class GarantiesCategorie {
	private Categorie categorie;
	private List<Garanties> ListGaranties = new ArrayList<Garanties>();

	public GarantiesCategorie() {
		// TODO Auto-generated constructor stub
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Garanties> getListGaranties() {
		return ListGaranties;
	}

	public void setListGaranties(List<Garanties> listGaranties) {
		ListGaranties = listGaranties;
	}

}
