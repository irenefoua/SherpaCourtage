package com.j3a.assurance.managedBean.Auto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.CompagnieAssurance;
import com.j3a.assurance.model.Conducteur;
import com.j3a.assurance.model.Garantie;
import com.j3a.assurance.model.GarantieOption;
import com.j3a.assurance.model.GarantieOptionId;
import com.j3a.assurance.model.OptionsGarantie;
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
import com.j3a.assurance.utilitaires.GarantiesParOption;
import com.j3a.assurance.utilitaires.RecupObjetRow;
import com.j3a.assurance.utilitaires.TarifwebComp;
import com.j3a.assurance.utilitaires.VehiculeRow;
@Component
public class GarantiesCompagnies implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	ObjectService objectService;
	@Autowired
	private RecupObjetRow recupObjetRow;
	
	private List<TarifwebComp> listTarifwebComp = new ArrayList<TarifwebComp>();
	private List<TarifwebComp> listTarifwebCompFiltre = new ArrayList<TarifwebComp>();
	private TarifwebComp selectedTarifWeb = new TarifwebComp();
	private double duree;
	private long dureeJour;
	public String codeRisque;
	private String numImmatriculation;
	
	public VehiculeRow returnVehiculeRow(){
		
		//récupération d'un vehicule en base de données
		
		
		
		Vehicule vehicule =new Vehicule();
		//vehicule.setCodeVehicule("003KA1213AUT001-V001");
		vehicule = getObjectService().findVehicule(numImmatriculation);
		
		if(vehicule==null){
			 FacesMessage msg = new FacesMessage("Vehicule :", numImmatriculation+" Non trouvé!");
		        FacesContext.getCurrentInstance().addMessage(null, msg);

			return null;	
		}else{
			 VehiculeRow vehiculeRow =new VehiculeRow();
		vehiculeRow = getRecupObjetRow().returnVehiculeRow(vehicule);
		
		vehiculeRow.getTarifwebComp().addAll(returnTarif(vehiculeRow));
		
		//	System.out.println("-------------Liste de tarifs ds VRow----------------"+vehiculeRow.getTarifwebComp().size());
		
		
		 FacesMessage msg = new FacesMessage("Vehicule :", numImmatriculation+" Trouvé!");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		return vehiculeRow;	
		}
	}
	
	public List<TarifwebComp> returnTarif(VehiculeRow vehiculeRow){
		
		getListTarifwebComp().clear();
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
				
				 List<TarifwebComp> tarifwebComp = new ArrayList<TarifwebComp>();
				 int numTarif = 0;
				for(CompagnieAssurance ca: compAssList){
					//Couple TarifWebs et compagnie
					TarifwebComp twc = new TarifwebComp();
					twc.setDureeJContrat(getDureeJour());
					twc.setDureeMoisContrat(getDuree());
					twc.setNumOrdr(numTarif++);
					twc.setCompagnieAssurance(ca);
					System.out.println("-------------Recupération des tarifs de la compagnie---------------- "+ca.getRaisonSocialeCompAss()+
							" -------numKey  "+numTarif);	
					List<Tarifweb> tarifwebCompList = new ArrayList<Tarifweb>();
					for(TarifwebSousCat twsc : tarifwList){
						
						if(twsc.getTarifweb().getCompagnieAssurance().getCodeCompagnieAssurance().equalsIgnoreCase(ca.getCodeCompagnieAssurance())){
							tarifwebCompList.add(twsc.getTarifweb());
							twc.setTarifweb(twsc.getTarifweb());
							System.out.println("-------------Tarif web --------------- "+twsc.getTarifweb().getCodeTarifWeb()+" ---------Compagnie Assurances "+
									twsc.getTarifweb().getCompagnieAssurance().getRaisonSocialeCompAss()+" ---------- Tarif ------ "+twsc.getTarifweb().getTarif().getCodeTarif());
						}
					}
					twc.getTarifwebList().addAll(tarifwebCompList);
					// Calcul des garanties du véhicule
					
					twc.getListegaranties().addAll(calculGarantieAuto(vehiculeRow,twc));
					
					//tri des garanties
					returnListGarantiesOption(twc);
					
					//Calcul de la prime
					calculPrime(twc);
					
					tarifwebComp.add(twc);

					//Ajout des data sur les compagnies et les tarifs
					
					
				}
				//ajout du Tarif au vehicule
				
				getListTarifwebComp().addAll(tarifwebComp);
			
				return tarifwebComp;	
			
	}
	
	public List<Garanties> calculGarantieAuto(VehiculeRow vehiculeRow, TarifwebComp twc) {
		List<Garanties> garantieList = new ArrayList<Garanties>();
			System.out.println("-------------Calcul Garantie Debut----------------");
		
				
			PrimeCategorie primeCategorie = new PrimeCategorie();
			CalculPrimeGlobale primeGlobale = new CalculPrimeGlobale();

			// prÃ©voir une mÃ©thode de vÃ©rification de la sous catÃ©gorie Ã  plus tard
			//Boucle de Gestion des tarif web par Compagnie
			//for(Tarifweb tw :)
			
			Tarif tarif = twc.getTarifweb().getTarif();
			if(tarif != null){
				System.out.println("-------------Code Tarif----------------"+tarif.getCodeTarif());
			
			//System.out.println("-------------Code RC1----------------"+tarif.getRcTarif1().getCodeRcTarif1());
			primeGlobale.setCategorie(tarif.getCodeTarif());
			primeGlobale.primeGlobale();
			PrimeCategorieInterface prime = primeGlobale.primeGlobale();

			CalculPrimeProrata prorata = new CalculPrimeProrata();
			
			prime.setTarif(tarif);
			prime.setCategorie(tarif.getCodeTarif());
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
			
			garantieList.clear();
			List listObject = getObjectService().getListGarantieByRisque(
					"1");
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
					BigDecimal rcm = prorata.primeProrata(1, prime.getPrimeRc());
					BigDecimal v1 = prime.getPrimeRc();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesMensuelle(rcm);
					garantie.setPrimesProrata(rc);
					garantie.setPrimeRC(rc);
					garantie.setChoix(true);
				
				}

				if (garantie.getCodeGarantie().equals("104") == true) {

					BigDecimal dr = prorata.primeProrata(duree,
							prime.getDefenseRecours());
					BigDecimal drm = prorata.primeProrata(1,
							prime.getDefenseRecours());
					BigDecimal v1 = prime.getDefenseRecours();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesMensuelle(drm);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesProrata(dr);
					garantie.setChoix(true);
				
				}
				if (garantie.getCodeGarantie().equals("105") == true) {

					BigDecimal ra = prorata.primeProrata(duree,
							prime.getRemboursemmentAnticipe());
					BigDecimal ram = prorata.primeProrata(1,
							prime.getRemboursemmentAnticipe());
					BigDecimal v1 = prime.getRemboursemmentAnticipe();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesMensuelle(ram);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesProrata(ra);
				
				}

				if (garantie.getCodeGarantie().equals("106") == true) {

					BigDecimal d = prorata.primeProrata(duree, prime.getDommage());
					BigDecimal dm = prorata.primeProrata(1, prime.getDommage());
					BigDecimal v1 = prime.getDommage();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesMensuelle(dm);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesProrata(d);
				

				}

				if (garantie.getCodeGarantie().equals("107") == true) {

					BigDecimal d = prorata
							.primeProrata(duree, prime.getCollision());
					BigDecimal dm = prorata
							.primeProrata(1, prime.getCollision());
					BigDecimal v1 = prime.getCollision();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesMensuelle(dm);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesProrata(d);
					

				}
				if (garantie.getCodeGarantie().equals("108") == true) {

					BigDecimal bdg = prorata.primeProrata(duree,
							prime.getBrisGlaceRC());
					BigDecimal bdgm = prorata.primeProrata(1,
							prime.getBrisGlaceRC());
					BigDecimal v1 = prime.getBrisGlaceRC();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesMensuelle(bdgm);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesProrata(bdg);
			

				}

				if (garantie.getCodeGarantie().equals("109") == true) {

					BigDecimal im = prorata.primeProrata(duree,
							prime.getImmobilisation());
					BigDecimal imm = prorata.primeProrata(1,
							prime.getImmobilisation());
					BigDecimal v1 = prime.getImmobilisation();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesMensuelle(imm);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesProrata(im);
					

				}

				if (garantie.getCodeGarantie().equals("110") == true) {

					BigDecimal inc = prorata.primeProrata(duree,
							prime.getIncendie());
					BigDecimal incm = prorata.primeProrata(1,
							prime.getIncendie());
					BigDecimal v1 = prime.getIncendie();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesMensuelle(incm);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesProrata(inc);
				

				}

				if (garantie.getCodeGarantie().equals("111") == true) {

					BigDecimal vol = prorata
							.primeProrata(duree, prime.getVolMain());
					BigDecimal volm = prorata
							.primeProrata(1, prime.getVolMain());
					BigDecimal v1 = prime.getVolMain();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesMensuelle(volm);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesProrata(vol);
				

				}
				if (garantie.getCodeGarantie().equals("112") == true) {

					BigDecimal volA = prorata.primeProrata(duree,
							prime.getVolAccessoires());
					BigDecimal volAm = prorata.primeProrata(1,
							prime.getVolAccessoires());
					BigDecimal v1 = prime.getVolAccessoires();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesMensuelle(volAm);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesProrata(volA);
				

				}
				if (garantie.getCodeGarantie().equals("113") == true) {

					BigDecimal v = prorata.primeProrata(duree,
							prime.getVolVandalisme());
					BigDecimal vm = prorata.primeProrata(1,
							prime.getVolVandalisme());
					BigDecimal v1 = prime.getVolVandalisme();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesMensuelle(vm);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesProrata(v);
				

				}

				if (garantie.getCodeGarantie().equals("114") == true) {

					BigDecimal sr = prorata.primeProrata(duree,
							prime.getSecuriteRoutiere1());
					BigDecimal srm = prorata.primeProrata(1,
							prime.getSecuriteRoutiere1());
					BigDecimal v1 = prime.getSecuriteRoutiere1();
					garantie.setPrimesAnnuelle(v1);
					garantie.setPrimesMensuelle(srm);
					garantie.setPrimesNetteAnnuelle(v1);
					garantie.setPrimesProrata(sr);
				

				}
				
				System.out.println("--------------------------------------------------------------------- ");
				System.out.println("---------------------"
						+ garantie.getCategorieGarantie());
				garantieList.add(garantie);
			}
			System.out.println("-------------Calcul Garantie fin----------------");
			return garantieList;
			}else{
				System.out.println("-------------Problème ds la recup des garanties cause tarif null----------------");
				return new  ArrayList<Garanties>();
			}
		
		
	}
	
	public void  returnListGarantiesOption(TarifwebComp twc){  
		twc.getListegarantiesParOption().clear();
		System.out.println("-------------ListGaranties twc taille----------------= "+twc.getListegaranties().size());
		//recupération de la liste des Options de Garantie
		for (Iterator it = twc.getCompagnieAssurance().getOptionsGaranties().iterator(); it.hasNext();){
			OptionsGarantie og = (OptionsGarantie)it.next();
			
			System.out.println("-------------Option Garanties Iterator taille----------------= "+og.getGarantieOptions().size()+"-- "+og.getCodeOptionsGarantie());
			
			GarantiesParOption gpo = new GarantiesParOption();
			List<GarantieOption> listGarantieOption = new ArrayList<GarantieOption>();
			listGarantieOption.clear();
			listGarantieOption.addAll(og.getGarantieOptions());
			
			System.out.println("-------------Option Garanties List taille----------------= "+og.getGarantieOptions().size()+"-- "+og.getCodeOptionsGarantie());
			//Tri sur chaque formule
			switch (og.getFormuleGarantie().getCodeFormuleGarantie()) {
			case "TS":
				gpo.setOptionGarantie("TS");
				//parcour de la liste de 'association garantie et option pour recupérer les garanties de l'option
				
				
				for (GarantieOption go: listGarantieOption){
					
				
					//recupération des garanties de l'option
					
					for(Garanties gs: twc.getListegaranties()){
						
					if(go.getId().getCodeGarantie().equalsIgnoreCase(gs.getCodeGarantie())){
						gpo.getListegaranties().add(gs);
						
					}
					
					}
					
				}
				twc.getListegarantiesParOption().add(gpo);
				System.out.println("-------------Taille Garanties TS---------------- "+gpo.getListegaranties().size());
					
			
				break;
				
			case "TC":
				
				gpo.setOptionGarantie("TC");
				for (GarantieOption go: listGarantieOption){
					
					
					//recupération des garanties de l'option
					for(Garanties gs: twc.getListegaranties()){
						
					if(go.getId().getCodeGarantie().equalsIgnoreCase(gs.getCodeGarantie())){
						gpo.getListegaranties().add(gs);
						
					}
					
					}
					
				}
				twc.getListegarantiesParOption().add(gpo);
				
				System.out.println("-------------Taille Garanties TC---------------- "+gpo.getListegaranties().size());
				break;
				
			case "TR":
				gpo.setOptionGarantie("TR");
				for (GarantieOption go: listGarantieOption){
					
					
					//recupération des garanties de l'option
					for(Garanties gs: twc.getListegaranties()){
						
					if(go.getId().getCodeGarantie().equalsIgnoreCase(gs.getCodeGarantie())){
						gpo.getListegaranties().add(gs);
						
					}
					
					}
					
				}
				twc.getListegarantiesParOption().add(gpo);
				System.out.println("-------------Taille Garanties TR---------------- "+gpo.getListegaranties().size());
				break;
			}
				
		} 
		
		
	}
	
	public void onEditRow(RowEditEvent event) {

		 FacesMessage msg = new FacesMessage("Edit Valid", ((TarifwebComp) event.getObject()).getCompagnieAssurance().getRaisonSocialeCompAss());
	        FacesContext.getCurrentInstance().addMessage(null, msg);

		TarifwebComp twc = (TarifwebComp) event.getObject();
		calculPrime(twc);
			
	}
	 public void calculPrime(TarifwebComp twc) {
		 //init
		 twc.getListegarantieSelectd().clear();
		 Float tauxRed = twc.getCompagnieAssurance().getTauxReduction(), tauxAcc = twc.getCompagnieAssurance().getTauxAccessoire();
		    BigDecimal primeMen = BigDecimal.ZERO, primeAn = BigDecimal.ZERO, prime = BigDecimal.ZERO, primeNette = BigDecimal.ZERO;
		    BigDecimal primeMenTTC = BigDecimal.ZERO, primeAnTTC = BigDecimal.ZERO, primeTTC = BigDecimal.ZERO;
			BigDecimal redMen = BigDecimal.ZERO, redAn = BigDecimal.ZERO, redP = BigDecimal.ZERO;
			BigDecimal accMen = BigDecimal.ZERO, accAn = BigDecimal.ZERO, accP = BigDecimal.ZERO;
			
			java.math.BigDecimal taxeEnrP = BigDecimal.ZERO,taxeFGAP = BigDecimal.ZERO;
			java.math.BigDecimal taxeEnrMen = BigDecimal.ZERO,taxeFGAMen = BigDecimal.ZERO;
			java.math.BigDecimal taxeEnrAn = BigDecimal.ZERO,taxeFGAAn = BigDecimal.ZERO;
			switch (twc.getOptionGarantie()) {
			case "TS":
				twc.setFormule("TIER SIMPLE");
				for(GarantiesParOption gpo: twc.getListegarantiesParOption()){
					if(gpo.getOptionGarantie().equalsIgnoreCase(twc.getOptionGarantie())){
						
						twc.getListegarantieSelectd().addAll(gpo.getListegaranties());
					}
				}
				
				
				break;
				
			case "TC":
				twc.setFormule("TIER COMPLET");
				for(GarantiesParOption gpo: twc.getListegarantiesParOption()){
					if(gpo.getOptionGarantie().equalsIgnoreCase(twc.getOptionGarantie())){
						
						twc.getListegarantieSelectd().addAll(gpo.getListegaranties());
					}
				}
			
				break;
				
			case "TR":
				twc.setFormule("TOUS RISQUES");
				for(GarantiesParOption gpo: twc.getListegarantiesParOption()){
					if(gpo.getOptionGarantie().equalsIgnoreCase(twc.getOptionGarantie())){
						
						twc.getListegarantieSelectd().addAll(gpo.getListegaranties());
					}
				}
				break;
			
			}
			
			//Calcul des primes Accessoires et Reductions
			for(Garanties g: twc.getListegarantieSelectd()){
				BigDecimal redM = BigDecimal.ZERO, redA = BigDecimal.ZERO, red = BigDecimal.ZERO;
				//Calcul des reductions sauf la Rc
				if(!g.getCodeGarantie().equalsIgnoreCase("101")){
					
					
					redM =	g.getPrimesMensuelle().multiply(new BigDecimal(tauxRed)).divide(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
					red =	g.getPrimesProrata().multiply(new BigDecimal(tauxRed)).divide(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
					redA =	g.getPrimesAnnuelle().multiply(new BigDecimal(tauxRed)).divide(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
					
					g.setReductions(red);
					g.setMontantRed(red);
					g.setTauxRed(new BigDecimal(tauxRed));
					redP = redP.add(red);
					redMen = redMen.add(redM);
					redAn = redAn.add(redA);
					
					
					
				}
				
				
				//Calcul del'accessoire
				BigDecimal accM = BigDecimal.ZERO, accA = BigDecimal.ZERO, acc = BigDecimal.ZERO;
				
				acc =	(g.getPrimesProrata().multiply(new BigDecimal(tauxAcc).divide(new BigDecimal(100))).setScale(0, BigDecimal.ROUND_HALF_UP));
				accM =	(g.getPrimesMensuelle().multiply(new BigDecimal(tauxAcc).divide(new BigDecimal(100))).setScale(0, BigDecimal.ROUND_HALF_UP));
				accA =	(g.getPrimesAnnuelle().multiply(new BigDecimal(tauxAcc).divide(new BigDecimal(100))).setScale(0, BigDecimal.ROUND_HALF_UP));
				
				accP = accP.add(acc);
				accMen = accMen.add(accM);
				accAn = accAn.add(accA);
				
			
				//Calcule des taxes FGA et Taxe
				java.math.BigDecimal taxeEnr = BigDecimal.ZERO,taxeFGA = BigDecimal.ZERO;
				java.math.BigDecimal taxeEnrM = BigDecimal.ZERO,taxeFGAM = BigDecimal.ZERO;
				java.math.BigDecimal taxeEnrA = BigDecimal.ZERO,taxeFGAA = BigDecimal.ZERO;
				
				taxeEnr = ( taxeEnr.add(g.getPrimesProrata()).add(red).multiply(new BigDecimal(0.145))).setScale(0, BigDecimal.ROUND_HALF_UP);
				taxeEnrM =  (taxeEnrM.add(g.getPrimesMensuelle()).add(red).multiply(new BigDecimal(0.145))).setScale(0, BigDecimal.ROUND_HALF_UP);
				taxeEnrA = ( taxeEnrA.add(g.getPrimesAnnuelle()).add(red).multiply(new BigDecimal(0.145))).setScale(0, BigDecimal.ROUND_HALF_UP);
				
				taxeFGA =  (taxeFGA.add(g.getPrimesProrata()).add(red).multiply(new BigDecimal(0.02))).setScale(0, BigDecimal.ROUND_HALF_UP);
				taxeFGAM =  (taxeFGAM.add(g.getPrimesMensuelle()).add(red).multiply(new BigDecimal(0.02))).setScale(0, BigDecimal.ROUND_HALF_UP);
				taxeFGAA =  (taxeFGAA.add(g.getPrimesAnnuelle()).add(red).multiply(new BigDecimal(0.02))).setScale(0, BigDecimal.ROUND_HALF_UP);
				
				taxeEnrP = taxeEnrP.add(taxeEnr);
				taxeEnrMen = taxeEnrMen.add(taxeEnrM);
				taxeEnrAn = taxeEnrAn.add(taxeEnrA);
				
				taxeFGAP = taxeFGAP.add(taxeFGA);
				taxeFGAMen = taxeFGAMen.add(taxeFGAM);
				taxeFGAAn = taxeFGAAn.add(taxeFGAA);
					
				//Calcul de la prime
				
				prime = prime.add(g.getPrimesProrata());
				
				primeNette = primeNette.add(g.getPrimesProrata().subtract(red));
				primeMen = primeMen.add(g.getPrimesMensuelle().subtract(redM));
				primeAn = primeAn.add(g.getPrimesAnnuelle().subtract(redA));
				
				primeTTC = primeTTC.add(g.getPrimesProrata()).add(acc).add(taxeEnr).add(taxeFGA).subtract(red);
				primeMenTTC = primeMenTTC.add(g.getPrimesMensuelle()).add(accM).add(taxeEnrM).add(taxeFGAM).subtract(redM);
				primeAnTTC = primeAnTTC.add(g.getPrimesAnnuelle()).add(accA).add(taxeEnrA).add(taxeFGAA).subtract(redA);
				
				g.setPrimesNetteProrata((g.getPrimesProrata()).subtract(red));
				g.setPrimesMensuelle((g.getPrimesMensuelle()).subtract(redM));
				g.setPrimesNetteAnnuelle((g.getPrimesAnnuelle()).subtract(redA));
				
				
			}
			
			
			twc.setAccessoireAnnuelle(accAn);
			twc.setAccessoire(accP);
			twc.setAccessoireMensuelle(accMen);
			twc.setReductionAnnuelle(redAn);
			twc.setReductionMensuelle(redMen);
			twc.setReduction(redP);
			twc.setPrimeMensuelle(primeMen.setScale(0, BigDecimal.ROUND_HALF_UP));
			twc.setPrimeNette(primeNette.setScale(0, BigDecimal.ROUND_HALF_UP));
			twc.setPrimes(prime.setScale(0, BigDecimal.ROUND_HALF_UP));
			twc.setPrimeAnnuelle(primeAn.setScale(0, BigDecimal.ROUND_HALF_UP));
			
			twc.setPrimeMensuelleTTC(primeMenTTC.setScale(0, BigDecimal.ROUND_HALF_UP));
			twc.setPrimeNetteTTC(primeTTC.setScale(0, BigDecimal.ROUND_HALF_UP));
			twc.setPrimeAnnuelleTTC(primeAnTTC.setScale(0, BigDecimal.ROUND_HALF_UP));
			
			twc.setTaxe(taxeEnrP);
			twc.setTaxeMensuelle(taxeEnrMen);
			twc.setTaxeAnnuelle(taxeEnrAn);
			
			twc.setFga(taxeFGAP);
			twc.setFgaMensuelle(taxeFGAMen);
			twc.setFgaAnnuelle(taxeFGAAn);
			
			System.out.println("--------------------------------------------------------------------- ");
			
			System.out.println("-------------Taux reduction--------------- "+tauxRed);
			
			System.out.println("-------------Taux Accessoire--------------- "+tauxAcc);
			
System.out.println("--------------------------------------------------------------------- ");
		
			System.out.println("-------------Prime Prorata--------------- "+twc.getPrimeNette());
			System.out.println("-------------Reduction Prorata----------- "+twc.getReduction());
			System.out.println("-------------Accessoire Prorata---------- "+twc.getAccessoire());
			System.out.println("-------------Taxe Prorata---------------- "+twc.getTaxe());
			System.out.println("-------------FGA Prorata----------------- "+twc.getFga());
			
			System.out.println("--------------------------------------------------------------------- ");
			
			System.out.println("-------------Prime mensuelle------------- "+twc.getPrimeMensuelle());
			System.out.println("-------------Reduction mensuelle--------- "+twc.getReductionMensuelle());
			System.out.println("-------------Accessoire mensuelle-------- "+twc.getAccessoireMensuelle());
			System.out.println("-------------Taxe Mensuelle-------------- "+twc.getTaxeMensuelle());
			System.out.println("-------------FGA Mensuelle--------------- "+twc.getFgaMensuelle());
			
			System.out.println("--------------------------------------------------------------------- ");
			
			System.out.println("-------------Prime Annuelle-------------- "+twc.getPrimeAnnuelle());
			System.out.println("-------------Reduction Annuelle---------- "+twc.getReductionAnnuelle());
			System.out.println("-------------Accessoire Annuelle--------- "+twc.getAccessoireAnnuelle());
			System.out.println("-------------Taxe Annuelle--------------- "+twc.getTaxeAnnuelle());
			System.out.println("-------------FGA Annuelle---------------- "+twc.getFgaAnnuelle());
			
			System.out.println("--------------------------------------------------------------------- ");
			
			
			
			
			setSelectedTarifWeb(twc);
	    }
	
	 public String validerTarif() {
		if(getSelectedTarifWeb()!=null){
			
			//getSelectedTarifWeb().setDureeJContrat(dureeJour);
		      System.out.println("-------------selectdTarifwebComp---------------- "+getSelectedTarifWeb().getCompagnieAssurance().getRaisonSocialeCompAss());
		      FacesMessage msg = new FacesMessage("Compagnie sélectionnée :", getSelectedTarifWeb().getCompagnieAssurance().getRaisonSocialeCompAss());
		        FacesContext.getCurrentInstance().addMessage(null, msg);
		        return "validationchoixOK";
		}else{
			 FacesMessage msg = new FacesMessage("Sélectionnez un Tarif");
		        FacesContext.getCurrentInstance().addMessage(null, msg);
		        return "validationchoixNO";
		}
		     
	        
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

	public List<TarifwebComp> getListTarifwebComp() {
		return listTarifwebComp;
	}

	public void setListTarifwebComp(List<TarifwebComp> listTarifwebComp) {
		this.listTarifwebComp = listTarifwebComp;
	}

	public TarifwebComp getSelectedTarifWeb() {
		return selectedTarifWeb;
	}

	public void setSelectedTarifWeb(TarifwebComp selectedTarifWeb) {
		this.selectedTarifWeb = selectedTarifWeb;
	}


	public List<TarifwebComp> getListTarifwebCompFiltre() {
		return listTarifwebCompFiltre;
	}

	public void setListTarifwebCompFiltre(List<TarifwebComp> listTarifwebCompFiltre) {
		this.listTarifwebCompFiltre = listTarifwebCompFiltre;
	}

	public String getNumImmatriculation() {
		return numImmatriculation;
	}

	public void setNumImmatriculation(String numImmatriculation) {
		this.numImmatriculation = numImmatriculation;
	}

	public long getDureeJour() {
		return dureeJour;
	}

	public void setDureeJour(long dureeJour) {
		this.dureeJour = dureeJour;
	}

}
