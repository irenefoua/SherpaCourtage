package com.j3a.assurance.utilitaires;

import java.util.ArrayList;
import java.util.List;

import com.j3a.assurance.model.GarantieChoisie;
import com.j3a.assurance.model.GarantieGarantieChoisie;
import com.j3a.assurance.model.Vehicule;

public class VehiAssur {

	public VehiAssur() {
		// TODO Auto-generated constructor stub
	}

	private Vehicule vehi;
	private GarantieChoisie vehiGc;
	private List<GarantieGarantieChoisie> infoGarList = new ArrayList<GarantieGarantieChoisie>();
	public Vehicule getVehi() {
		return vehi;
	}
	public void setVehi(Vehicule vehi) {
		this.vehi = vehi;
	}
	public GarantieChoisie getVehiGc() {
		return vehiGc;
	}
	public void setVehiGc(GarantieChoisie vehiGc) {
		this.vehiGc = vehiGc;
	}
	public List<GarantieGarantieChoisie> getInfoGarList() {
		return infoGarList;
	}
	public void setInfoGarList(List<GarantieGarantieChoisie> infoGarList) {
		this.infoGarList = infoGarList;
	}
	
	
	
	
}
