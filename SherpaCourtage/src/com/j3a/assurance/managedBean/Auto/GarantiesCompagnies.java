package com.j3a.assurance.managedBean.Auto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.CompagnieAssurance;
import com.j3a.assurance.model.Conducteur;
import com.j3a.assurance.model.Garantie;
import com.j3a.assurance.model.SousCatVehicule;
import com.j3a.assurance.model.Tarif;
import com.j3a.assurance.model.Tarifweb;
import com.j3a.assurance.model.TarifwebSousCat;
import com.j3a.assurance.model.Vehicule;
import com.j3a.assurance.model.ZoneGeographique;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.prime.CalculPrimeGlobale;
import com.j3a.assurance.prime.CalculPrimeProrata;
import com.j3a.assurance.prime.ReturnPrimeCategorie;
import com.j3a.assurance.prime.categorie.PrimeCategorie;
import com.j3a.assurance.prime.categorie.PrimeCategorieInterface;
import com.j3a.assurance.utilitaires.Garanties;
import com.j3a.assurance.utilitaires.RecupObjetRow;
import com.j3a.assurance.utilitaires.VehiculeRow;
@Component
public class GarantiesCompagnies implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	ObjectService objectService;
	@Autowired
	private RecupObjetRow recupObjetRow;
	private double duree;
	public String codeRisque;
	
	public void testCourtage(){
		
		//récupération d'un vehicule en base de données
		
		VehiculeRow vehiculeR =new VehiculeRow();
		
		Vehicule vehicule =new Vehicule();
		//vehicule.setCodeVehicule("003KA1213AUT001-V001");
		vehicule = (Vehicule)getObjectService().getObjectById("003KA1213AUT001-V001", "Vehicule");
		vehiculeR = getRecupObjetRow().returnVehiculeRow(vehicule);
		
		//gestion des compagnies d'assurance pour la cote d'ivoire ( par pays)
		System.out.println("------------gestion des compagnies d'assurance pour la cote d'ivoire ( par pays)----------------");
		List<CompagnieAssurance> compAssList = new ArrayList<CompagnieAssurance>();
		for(Iterator itca = getObjectService().getObjects("CompagnieAssurance").iterator(); itca.hasNext();){
			CompagnieAssurance cass =  (CompagnieAssurance)itca.next();
			if(cass.getPays().getCodePays().equalsIgnoreCase("225")){
				compAssList.add(cass);
				System.out.println("-------------Compagnie---------------- "+cass.getRaisonSocialeCompAss());
			}
		}
		
		
		System.out.println("------------Pour chaque compagnie recupération de la liste des tarifs pour la sousCat 1----------------");
		
		//Pour les Souscats
		SousCatVehicule sc = (SousCatVehicule)getObjectService().getObjectById("SCAT1","SousCatVehicule");
		System.out.println("------------SousCatVehicule----------------"+sc.getCodeSousCatVehicule());
		System.out.println("-----tarif web--------SousCatVehicule----------------"+sc.getTarifwebSousCats().isEmpty());
		System.out.println("-----tarif web--------SousCatVehicule----------------"+sc.getTarifwebSousCats().size());
		
		List<TarifwebSousCat> tarifwList = new ArrayList<TarifwebSousCat>();
		for(Iterator it = sc.getTarifwebSousCats().iterator();it.hasNext();){
			TarifwebSousCat twsc = (TarifwebSousCat)it.next();
			tarifwList.add(twsc);
		}
		
		
		for(CompagnieAssurance ca: compAssList){
			System.out.println("-------------Recupération des tarifs de la compagnie---------------- "+ca.getRaisonSocialeCompAss());	
			List<Tarifweb> tarifwebCompList = new ArrayList<Tarifweb>();
			for(TarifwebSousCat twsc : tarifwList){
				
				if(twsc.getTarifweb().getCompagnieAssurance().getCodeCompagnieAssurance().equalsIgnoreCase(ca.getCodeCompagnieAssurance())){
					tarifwebCompList.add(twsc.getTarifweb());
					System.out.println("-------------Tarif web --------------- "+twsc.getTarifweb().getCodeTarifWeb()+" ---------Compagnie Assurances "+
							twsc.getTarifweb().getCompagnieAssurance().getRaisonSocialeCompAss()+" ---------- Tarif ------ "+twsc.getTarifweb().getTarif().getCodeTarif());
				}
			}
			
		}
	

		
		
	
	
		
		//System.out.println("-------------Tarif web souscat----------------");
		//System.out.println("-------------Calcul Garantie Debut----------------");
		//System.out.println("-------------Calcul Garantie Debut----------------");
		//System.out.println("-------------Calcul Garantie Debut----------------");
		//calculGarantieAuto(vehiculeR,ca);
		
		
				
	}
	
	public List<Garanties> calculGarantieAuto(VehiculeRow vehiculeRow, CompagnieAssurance compagnieAssurance) {
		
			//System.out.println("-------------Calcul Garantie Debut----------------");
		/*for (TarifwebSousCat twsc: (TarifwebSousCat)vehiculeRow.getSouCatVehi().getTarifwebSousCats().iterator().next()){
			
		}*/
		TarifwebSousCat twsc = new TarifwebSousCat();
		//twsc.getId().
				
			PrimeCategorie primeCategorie = new PrimeCategorie();
			CalculPrimeGlobale primeGlobale = new CalculPrimeGlobale();

			// prÃ©voir une mÃ©thode de vÃ©rification de la sous catÃ©gorie Ã  plus tard
			//Boucle de Gestion des tarif web par Compagnie
			//for(Tarifweb tw :)
			Tarif tarif;
			
				tarif = (Tarif)getObjectService().getObjectById(vehiculeRow.getSouCatVehi().getTarif().getCodeTarif(), "Tarif");
				System.out.println("-------------Code Tarif----------------"+tarif.getCodeTarif());
			
			//System.out.println("-------------Code RC1----------------"+tarif.getRcTarif1().getCodeRcTarif1());
			primeGlobale.setCategorie(tarif.getCodeTarif());
			primeGlobale.primeGlobale();
			PrimeCategorieInterface prime = primeGlobale.primeGlobale();

			CalculPrimeProrata prorata = new CalculPrimeProrata();
			
			prime.setTarif(tarif);
			prime.setCategorie(vehiculeRow.getSouCatVehi().getTarif().getCodeTarif());
			prime.setEnergie(vehiculeRow.getVehi().getEnergie());
			prime.setNbrecarte(vehiculeRow.getVehi().getNbreCarte());
			prime.setChargeUtile(vehiculeRow.getVehi().getChargeUtile());
			prime.setTypeTransporte(vehiculeRow.getVehi().getTypeTransporte());
			prime.setPuissFisc(vehiculeRow.getVehi().getPuissFisc());
			prime.setPuissReel(vehiculeRow.getVehi().getPuissReelle());
			prime.setZone(vehiculeRow.getZonGeo().getCodeZoneGeo());
			prime.setValeurNeuve(vehiculeRow.getVehi().getValNeuf());
			prime.setValeurVenale(vehiculeRow.getVehi().getValVenale());
			prime.setRemorque(vehiculeRow.getVehi().getRemorque());
			prime.setTypeVehicule(vehiculeRow.getVehi().getTypeVehicule());
			// Conducteur
			prime.setStatut(vehiculeRow.getConduHab().getCategsocioprocond());
			prime.setDureePermis(vehiculeRow.getConduHab().getDureepermiscond());

			// prime.setMontantAccessoires();
			ReturnPrimeCategorie r = new ReturnPrimeCategorie();
			primeCategorie= r.returnPrimecategorie(prime);
			System.out.println("valeur de primecategorie "
					+ primeCategorie.getBrisGlaceRC());
			List<Garanties> garantieList = new ArrayList<Garanties>();
			garantieList.clear();
			List listObject = getObjectService().getListGarantieByRisque(
					getCodeRisque());
			for (Iterator it = listObject.iterator(); it.hasNext();) {
				Garantie gar = (Garantie) it.next();
				Garanties garantie = new Garanties();
				garantie.setCodeGarantie(gar.getCodeGarantie());
				garantie.setLibelleGarantie(gar.getLibelleGarantie());
				garantie.setCapitalGarantie(gar.getCapitalGarantie());
				garantie.setFranchise(gar.getFranchise());
				garantie.setCategorieGarantie(gar.getCategorieGarantie());

				if (garantie.getCodeGarantie().equalsIgnoreCase("101")) {
					BigDecimal rc = prorata.primeProrata(duree, prime.getPrimeRc());
					BigDecimal v1 = prime.getPrimeRc();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesProrata(rc);
					garantie.setPrimeRC(rc);
					garantie.setChoix(true);
				}

				if (garantie.getCodeGarantie().equals("104") == true) {

					BigDecimal dr = prorata.primeProrata(duree,
							prime.getDefenseRecours());
					BigDecimal v1 = prime.getDefenseRecours();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesProrata(dr);
					garantie.setChoix(true);
				}
				if (garantie.getCodeGarantie().equals("105") == true) {

					BigDecimal ra = prorata.primeProrata(duree,
							prime.getRemboursemmentAnticipe());
					BigDecimal v1 = prime.getRemboursemmentAnticipe();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesProrata(ra);
				}

				if (garantie.getCodeGarantie().equals("106") == true) {

					BigDecimal d = prorata.primeProrata(duree, prime.getDommage());
					BigDecimal v1 = prime.getDommage();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesProrata(d);

				}

				if (garantie.getCodeGarantie().equals("107") == true) {

					BigDecimal d = prorata
							.primeProrata(duree, prime.getCollision());
					BigDecimal v1 = prime.getCollision();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesProrata(d);

				}
				if (garantie.getCodeGarantie().equals("108") == true) {

					BigDecimal bdg = prorata.primeProrata(duree,
							prime.getBrisGlaceRC());
					BigDecimal v1 = prime.getBrisGlaceRC();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesProrata(bdg);

				}

				if (garantie.getCodeGarantie().equals("109") == true) {

					BigDecimal im = prorata.primeProrata(duree,
							prime.getImmobilisation());
					BigDecimal v1 = prime.getImmobilisation();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesProrata(im);

				}

				if (garantie.getCodeGarantie().equals("110") == true) {

					BigDecimal inc = prorata.primeProrata(duree,
							prime.getIncendie());
					BigDecimal v1 = prime.getIncendie();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesProrata(inc);

				}

				if (garantie.getCodeGarantie().equals("111") == true) {

					BigDecimal vol = prorata
							.primeProrata(duree, prime.getVolMain());
					BigDecimal v1 = prime.getVolMain();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesProrata(vol);

				}
				if (garantie.getCodeGarantie().equals("112") == true) {

					BigDecimal volA = prorata.primeProrata(duree,
							prime.getVolAccessoires());
					BigDecimal v1 = prime.getVolAccessoires();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesProrata(volA);

				}
				if (garantie.getCodeGarantie().equals("113") == true) {

					BigDecimal v = prorata.primeProrata(duree,
							prime.getVolVandalisme());
					BigDecimal v1 = prime.getVolVandalisme();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesProrata(v);

				}

				if (garantie.getCodeGarantie().equals("114") == true) {

					BigDecimal sr = prorata.primeProrata(duree,
							prime.getSecuriteRoutiere1());
					BigDecimal v1 = prime.getSecuriteRoutiere1();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesProrata(sr);

				}
				
				System.out.println("-------------Calcul Garantie Debut----------------");
				System.out.println("ssssssssssssssssssssssssssssssssssss"
						+ garantie.getCategorieGarantie());
				garantieList.add(garantie);
			}

			return garantieList;
		
		
		
	}

	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}

	public double getDuree() {
		return duree;
	}

	public void setDuree(double duree) {
		this.duree = duree;
	}

	public String getCodeRisque() {
		return codeRisque;
	}

	public void setCodeRisque(String codeRisque) {
		this.codeRisque = codeRisque;
	}

	public RecupObjetRow getRecupObjetRow() {
		return recupObjetRow;
	}

	public void setRecupObjetRow(RecupObjetRow recupObjetRow) {
		this.recupObjetRow = recupObjetRow;
	}

}
