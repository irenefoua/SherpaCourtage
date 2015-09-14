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
	private VehiculeRow vehiculeRow =new VehiculeRow();
	private List<TarifwebComp> listTarifwebComp = new ArrayList<TarifwebComp>();
	private List<TarifwebComp> listTarifwebCompFiltre = new ArrayList<TarifwebComp>();
	private TarifwebComp selectedTarifWeb = new TarifwebComp();
	private double duree = 1;
	public String codeRisque;
	
	public void testCourtage(){
		getListTarifwebComp().clear();
		//récupération d'un vehicule en base de données
		
		
		
		Vehicule vehicule =new Vehicule();
		//vehicule.setCodeVehicule("003KA1213AUT001-V001");
		vehicule = (Vehicule)getObjectService().getObjectById("003KA1213AUT001-V001", "Vehicule");
		vehiculeRow = getRecupObjetRow().returnVehiculeRow(vehicule);
		
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
		vehiculeRow.getTarifwebComp().addAll(tarifwebComp);
		
		System.out.println("-------------Liste de tarifs ds VRow----------------"+vehiculeRow.getTarifwebComp().size());
		
	
	
		
		//System.out.println("-------------Tarif web souscat----------------");
		//System.out.println("-------------Calcul Garantie Debut----------------");
		//System.out.println("-------------Calcul Garantie Debut----------------");
		//System.out.println("-------------Calcul Garantie Debut----------------");
		//calculGarantieAuto(vehiculeR,ca);
		
		
				
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
		    BigDecimal primeMen = BigDecimal.ZERO, primeAn = BigDecimal.ZERO;
			BigDecimal redMen = BigDecimal.ZERO, redAn = BigDecimal.ZERO;
			BigDecimal accMen = BigDecimal.ZERO, accAn = BigDecimal.ZERO;
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
				
				//Calcul des reductions sauf la Rc
				if(!g.getCodeGarantie().equalsIgnoreCase("101")){
					BigDecimal redM = BigDecimal.ZERO, redA = BigDecimal.ZERO;
					
					redM =	g.getPrimesProrata().multiply(BigDecimal.valueOf(tauxRed/100));
					redA =	g.getPrimesNetteAnnuelle().multiply(BigDecimal.valueOf(tauxRed/100));
					
					
					redMen = redMen.add(redM);
					redAn = redAn.add(redA);
					
					
				}
				
				//Calcul del'accessoire
				BigDecimal accM = BigDecimal.ZERO, accA = BigDecimal.ZERO;
				
				accM =	g.getPrimesProrata().multiply(BigDecimal.valueOf(tauxAcc).divide(new BigDecimal(100)));
				accA =	g.getPrimesNetteAnnuelle().multiply(BigDecimal.valueOf(tauxAcc).divide(new BigDecimal(100)));
				
				accMen = accMen.add(accM);
				accAn = accAn.add(accA);
				
			
				
					
				//Calcul de la prime
				primeMen = primeMen.add(g.getPrimesProrata()).add(accM).subtract(redMen);
				primeAn = primeAn.add(g.getPrimesNetteAnnuelle()).add(accA).subtract(redAn);
				
				
			}
			
			
			twc.setAccessoireAnnuelle(accAn);
			twc.setAccessoireMensuelle(accMen);
			twc.setReductionAnnuelle(redAn);
			twc.setReductionMensuelle(redMen);
			twc.setPrimeMensuelle(primeMen);
			twc.setPrimeAnnuelle(primeAn);
			
			System.out.println("-------------Prime mensuelle---------------- "+twc.getPrimeMensuelle());
			System.out.println("-------------Prime Annuelle---------------- "+twc.getPrimeAnnuelle());
			System.out.println("-------------ReductionAn---------------- "+twc.getReductionAnnuelle());
			System.out.println("-------------ReductionMens---------------- "+twc.getReductionMensuelle());
			System.out.println("-------------Accessoire mensuelle---------------- "+twc.getAccessoireMensuelle());
			System.out.println("-------------Accessoire Annuelle---------------- "+twc.getAccessoireAnnuelle());
			
			
			
			setSelectedTarifWeb(twc);
	    }
	
	 public String validerTarif() {
		if(getSelectedTarifWeb()!=null){
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

	public VehiculeRow getVehiculeRow() {
		return vehiculeRow;
	}

	public void setVehiculeRow(VehiculeRow vehiculeRow) {
		this.vehiculeRow = vehiculeRow;
	}

	public List<TarifwebComp> getListTarifwebCompFiltre() {
		return listTarifwebCompFiltre;
	}

	public void setListTarifwebCompFiltre(List<TarifwebComp> listTarifwebCompFiltre) {
		this.listTarifwebCompFiltre = listTarifwebCompFiltre;
	}

}
