package com.j3a.assurance.utilitaires;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.GarantieChoisie;
import com.j3a.assurance.model.GarantieGarantieChoisie;
import com.j3a.assurance.model.Vehicule;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.reporting.bean.PrimeGarantieObject;

@Component
public class RecupObjetRow implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
 ObjectService objectService;
	private Scanner sc;
	private Scanner sc1;

	public RecupObjetRow() {

	}

	public List<PrimeGarantieObject> getPrimeGarantieAuto(
			GarantieChoisie garantieChoisie) {
		// objet qui recupère la collection de PrimeGarantieAuto
		List<PrimeGarantieObject> listPrimeGaranties = new ArrayList<PrimeGarantieObject>();
		// recupère la collection de garantie
	
		// pour chaque garantie de la list
		for (GarantieGarantieChoisie ggc : garantieChoisie.getGarantieGarantieChoisies()) {
			PrimeGarantieObject pimeGarantieAuto = new PrimeGarantieObject();
			// contruire le clé primaire de GarantieGarantieChoisie
	
			pimeGarantieAuto.setChoisieAuto(ggc);
			pimeGarantieAuto.setGarantie(ggc.getGarantie());
			// ajouter a la list des PrimeGarantieTransport
			listPrimeGaranties.add(pimeGarantieAuto);
		}
		return listPrimeGaranties;
	}

	

	// Méthodes de recupération des objets dans la BD
	public VehiculeRow returnVehiculeRow(Vehicule vehicule) {
		Date date = new Date();
		date = Calendar.getInstance().getTime();
		VehiculeRow vehi = new VehiculeRow();
		vehi.setStatutVehiculeBD(true);
		// onajoute un le engin
		vehi.setVehi(vehicule);
		// gestion de l'apporteur
	

		System.out.println("sdfcfrrfvrdf" + vehicule.getCodeVehicule());

		// souscatégorie, zoneGeo et le conducteur
		vehi.setSouCatVehi(vehicule.getSousCatVehicule());
		if(vehicule.getVehiculeZoneGeographiques().iterator().hasNext()){
		vehi.setZonGeo(vehicule.getVehiculeZoneGeographiques().iterator().next().getZoneGeographique());
		}
		if (vehicule.getConduireVehicules().iterator().hasNext()) {
			vehi.setConduHab(vehicule.getConduireVehicules().iterator().next().getConducteur());
		}

		// liste des garanties de l'engin
		List<PrimeGarantieObject> primeGarantieAutotList = new ArrayList<PrimeGarantieObject>();
		GarantieChoisie GC = new GarantieChoisie();
		for (Iterator its = vehicule.getGarantieChoisies().iterator(); its
				.hasNext();) {
			GC = (GarantieChoisie) its.next();

			// construction des listes Garanties avec les PK
			primeGarantieAutotList.clear();
			primeGarantieAutotList.addAll(getPrimeGarantieAuto(GC));
		}
		// reconstitution des garanties ds enginRow
		List<Garanties> garantieList = new ArrayList<Garanties>();
		for (PrimeGarantieObject PG : primeGarantieAutotList) {
			Garanties GT = new Garanties();

			GT.setCodeGarantie(PG.getGarantie().getCodeGarantie());
			GT.setLibelleGarantie(PG.getGarantie().getLibelleGarantie());
			GT.setCapitalGarantie(PG.getGarantie().getCapitalGarantie());
			GT.setFranchise(PG.getGarantie().getFranchise());
		

			GT.setPrimesAnnuelle(PG.getChoisieAuto().getPrimeAnnuelle());
			GT.setPrimesProrata(PG.getChoisieAuto().getPrimeNetteProrata());
			GT.setPrimesNetteAnnuelle(PG.getChoisieAuto()
					.getPrimeNetteAnnuelle());

			// GT.setTauxRed(PG.getChoisieAuto().getTauxAutreReduction());
			GT.setBonus(PG.getChoisieAuto().getTauxBonus());
			GT.setMalus(PG.getChoisieAuto().getTauxMalus());
			// GT.setTauxRed(PG.getChoisieAuto().getTauxAutreReduction());

			// commission
			GT.setComCoass(BigDecimal.ZERO);
			GT.setComCons(BigDecimal.ZERO);
			GT.setComGes(BigDecimal.ZERO);
			GT.setComInter(BigDecimal.ZERO);
			System.out.println("/*****************/" + GT.getLibelleGarantie());

			garantieList.add(GT);
		}
		// on ajoute à enginRow
		BigDecimal prime = BigDecimal.ZERO;
		for (Garanties G : garantieList) {
			prime = G.getPrimesProrata();
		}
		vehi.setPrimeNette(prime);
		vehi.getListegaranties().addAll(garantieList);
		return vehi;
	}

	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}


}
