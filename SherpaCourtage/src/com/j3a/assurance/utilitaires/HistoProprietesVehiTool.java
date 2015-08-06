package com.j3a.assurance.utilitaires;
import com.j3a.assurance.model.HistoProprietesVehicule;
import com.j3a.assurance.model.Vehicule;

public class HistoProprietesVehiTool {

	public HistoProprietesVehiTool() {
		// TODO Auto-generated constructor stub
	}

	public static void setProperties(HistoProprietesVehicule hPv, Vehicule codeVehicule){

		hPv.setChargeUtile(codeVehicule.getChargeUtile());
		hPv.setChassis(codeVehicule.getChassis());
		hPv.setCouleur(codeVehicule.getCouleur());
		hPv.setDateImmat(codeVehicule.getDateImmat());
		hPv.setDateImmatPrec(codeVehicule.getDateImmatPrec());
		hPv.setEnergie(codeVehicule.getEnergie());
		hPv.setGage(codeVehicule.getGage());
		hPv.setGenre(codeVehicule.getGenre());
		hPv.setMarque(codeVehicule.getMarque());
		hPv.setNbreCarte(codeVehicule.getNbreCarte());
		hPv.setNbrePlaceCab(codeVehicule.getNbrePlaceCab());
		hPv.setNbrePlaceHorscab(codeVehicule.getNbrePlaceHorscab());
		hPv.setNumImmat(codeVehicule.getNumImmat());
		hPv.setNumImmatPrec(codeVehicule.getNumImmatPrec());
		hPv.setNumMoteur(codeVehicule.getNumMoteur());
		hPv.setPoidsVide(codeVehicule.getPoidsVide());
		hPv.setProtection(codeVehicule.getProtection());
		hPv.setPuissFisc(codeVehicule.getPuissFisc());
		hPv.setPuissReelle(codeVehicule.getPuissReelle());
		hPv.setStatut(codeVehicule.getStatut());
		hPv.setTypeCommercial(codeVehicule.getTypeCommercial());
		
		hPv.setValNeuf(codeVehicule.getValNeuf());
		hPv.setValVenale(codeVehicule.getValVenale());
		

	}
}
