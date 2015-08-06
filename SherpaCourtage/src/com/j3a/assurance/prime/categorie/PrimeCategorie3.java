package com.j3a.assurance.prime.categorie;
import java.math.BigDecimal;

import com.j3a.assurance.model.Tarif;

public class PrimeCategorie3 implements PrimeCategorieInterface {
	private String energie;
	private String typeVehicule;
	private java.math.BigDecimal puissFisc = BigDecimal.ZERO;
	private java.math.BigDecimal puissReel = BigDecimal.ZERO;
	private String categorie;
	private String zone;
	private String classe;
	private String statut;
	private java.math.BigDecimal primeBase = BigDecimal.ZERO;
	private boolean remorque;
	private java.math.BigDecimal primeRemorque = BigDecimal.ZERO;
	private java.math.BigDecimal surprimes = BigDecimal.ZERO;
	private String typeTransporte;
	private java.math.BigDecimal primeRc = BigDecimal.ZERO;
	private java.math.BigDecimal defenseRecours = BigDecimal.ZERO;
	private java.math.BigDecimal defenseRecoursDommage = BigDecimal.ZERO;
	private java.math.BigDecimal immobilisation = BigDecimal.ZERO;
	private java.math.BigDecimal remboursemmentAnticipe = BigDecimal.ZERO;
	private java.math.BigDecimal brisGlaceRC = BigDecimal.ZERO;
	private java.math.BigDecimal brisGlaceRNC1 = BigDecimal.ZERO;
	private java.math.BigDecimal brisGlaceRNC2 = BigDecimal.ZERO;
	private java.math.BigDecimal valeurVenale = BigDecimal.ZERO;
	private java.math.BigDecimal valeurNeuve = BigDecimal.ZERO;
	private int dureePermis;
	private java.math.BigDecimal dommage = BigDecimal.ZERO;
	private java.math.BigDecimal collision = BigDecimal.ZERO;
	private java.math.BigDecimal volMain = BigDecimal.ZERO;
	private java.math.BigDecimal volAccessoires = BigDecimal.ZERO;
	private java.math.BigDecimal volVandalisme = BigDecimal.ZERO;
	private java.math.BigDecimal montantAccessoires = BigDecimal.ZERO;
	private java.math.BigDecimal incendie = BigDecimal.ZERO;
	private java.math.BigDecimal securiteRoutiere1 = BigDecimal.ZERO;
	private java.math.BigDecimal securiteRoutiere2 = BigDecimal.ZERO;
	private java.math.BigDecimal securiteRoutiere3= BigDecimal.ZERO;
	private String optionSR;
	private java.math.BigDecimal chargeUtile= BigDecimal.ZERO;
	private java.math.BigDecimal individuelChauf1 = BigDecimal.ZERO;
	private java.math.BigDecimal individuelChauf2 = BigDecimal.ZERO;
	private java.math.BigDecimal individuelChauf3 = BigDecimal.ZERO;
	private java.math.BigDecimal reduction = BigDecimal.ZERO;
	private String optionIndCh;
	private int dureeContrat;
	private int ageVehicule;
	private short nbreTransporte;
	private short nbrecarte;
	private short nbrePlaceCab;
	private short nbrePlaceHorscab;

	private Tarif tarif;
	
	public Tarif getTarif() {
		return tarif;
	}

	public void setTarif(Tarif tarif) {
		this.tarif = tarif;
	}
	/*---------------determination de la prime li�e aux garanties----------------------------------*/

	/*------------Determine la valeur de la prime de base------------------*/
	public java.math.BigDecimal getPrimeBase() {

		
		try{
		 primeBase = BigDecimal.ZERO;
		if (getChargeUtile().intValue() <= 1) {
			if (zone.equals("zone1"))
				primeBase = getTarif().getRcTarif3().getPfCu1Zone1t3();
			if (zone.equals("zone2"))
				primeBase = getTarif().getRcTarif3().getPfCu1Zone2t3();
			if (zone.equals("zone3"))
				primeBase = getTarif().getRcTarif3().getPfCu1Zone3t3();
		}

		if (getChargeUtile().intValue() > 1 && getChargeUtile().intValue() <= 3) {
			if (zone.equals("zone1"))
				// setPrimeBase(66885);
				primeBase = getTarif().getRcTarif3().getPfCu2Zone1t3();
			if (zone.equals("zone2"))
				// setPrimeBase(63541);
				primeBase = getTarif().getRcTarif3().getPfCu2Zone2t3();
			if (zone.equals("zone3"))
				// setPrimeBase(60197);
				primeBase = getTarif().getRcTarif3().getPfCu2Zone3t3();
		}

		if (getChargeUtile().intValue() > 3 && getChargeUtile().intValue() <= 5) {
			if (zone.equals("zone1"))
				// setPrimeBase(73415);
				primeBase = getTarif().getRcTarif3().getPfCu3Zone1t3();
			if (zone.equals("zone2"))
				// setPrimeBase(69744);
				primeBase = getTarif().getRcTarif3().getPfCu3Zone2t3();
			if (zone.equals("zone3"))
				// setPrimeBase(66074);
				primeBase = getTarif().getRcTarif3().getPfCu3Zone3t3();
		}
		if (getChargeUtile().intValue() > 5 && getChargeUtile().intValue() <= 9) {
			if (zone.equals("zone1"))
				primeBase = getTarif().getRcTarif3().getPfCu4Zone1t3();
			if (zone.equals("zone2"))
				primeBase = getTarif().getRcTarif3().getPfCu4Zone2t3();
			if (zone.equals("zone3"))
				primeBase = getTarif().getRcTarif3().getPfCu4Zone3t3();
		}
		if (getChargeUtile().intValue() > 9 && getChargeUtile().intValue() <= 12) {
			if (zone.equals("zone1"))
				primeBase = getTarif().getRcTarif3().getPfCu5Zone1t3();
			if (zone.equals("zone2"))
				primeBase = getTarif().getRcTarif3().getPfCu5Zone2t3();
			if (zone.equals("zone3"))
				primeBase = getTarif().getRcTarif3().getPfCu5Zone3t3();
		}
		if (getChargeUtile().intValue() > 12 && getChargeUtile().intValue() <= 15) {
			if (zone.equals("zone1"))
				primeBase = getTarif().getRcTarif3().getPfCu6Zone1t3();
			if (zone.equals("zone2"))
				primeBase = getTarif().getRcTarif3().getPfCu6Zone2t3();
			if (zone.equals("zone3"))
				// setPrimeBase(116152);
				primeBase = getTarif().getRcTarif3().getPfCu6Zone3t3();
		}
		
		if (getChargeUtile().intValue() > 15|| getTypeVehicule().equalsIgnoreCase("VAT")) {
			if (zone.equals("zone1"))
				primeBase = getTarif().getRcTarif3().getPfCu7Zone1t3();
			if (zone.equals("zone2"))
				primeBase = getTarif().getRcTarif3().getPfCu7Zone2t3();
			if (zone.equals("zone3"))
				primeBase = getTarif().getRcTarif3().getPfCu7Zone3t3();
		}

		
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return primeBase;
	}

	/*---------------	calcul de la prime RC-------------------------------*/
	public java.math.BigDecimal getPrimeRc() {
		java.math.BigDecimal red = BigDecimal.ZERO;
		primeRc = getPrimeBase();
		
		try {
		if (dureePermis <= 2) {
			classe = "classe1";
		}
		if (dureePermis > 2) {
			classe = "classe2";
		}

		if (classe.equals("classe2")) {
			//primeRc = primeRc.multiply(BigDecimal.valueOf(0.95));
			
			red = red.add(primeBase.multiply(BigDecimal.valueOf(getTarif().getRcTarif3().getTauxMajredPermis2t3())).multiply(BigDecimal.valueOf(0.01)));
		}

		if (statut.equals("a")) {
			//primeRc = primeRc.multiply(BigDecimal.valueOf(0.95));
			red = red.add(primeBase.multiply(BigDecimal.valueOf(getTarif().getRcTarif3().getTauxMajredStatutproat3())).multiply(BigDecimal.valueOf(0.01)));
		}
		
		if (statut.equals("b")) {
			//primeRc = primeRc.multiply(BigDecimal.valueOf(0.95));
			red = red.add(primeBase.multiply(BigDecimal.valueOf(getTarif().getRcTarif3().getTauxMajredStatutprobt3())).multiply(BigDecimal.valueOf(0.01)));
		}
		
		if (statut.equals("c")) {
			//primeRc = primeRc.multiply(BigDecimal.valueOf(0.95));
			red = red.add(primeBase.multiply(BigDecimal.valueOf(getTarif().getRcTarif3().getTauxMajredStatutproct3())).multiply(BigDecimal.valueOf(0.01)));
		}

		if (statut.equals("d")) {
			//primeRc = primeRc.multiply(BigDecimal.valueOf(0.90));
			red = red.add(primeBase.multiply(BigDecimal.valueOf(getTarif().getRcTarif3().getTauxMajredStatutprodt3())).multiply(BigDecimal.valueOf(0.01)));
		}
		
		if (statut.equals("e")) {
			//primeRc = primeRc.multiply(BigDecimal.valueOf(0.90));
			red = red.add(primeBase.multiply(BigDecimal.valueOf(getTarif().getRcTarif3().getTauxMajredStatutproet3())).multiply(BigDecimal.valueOf(0.01)));
		}
		
		/*if(getTypeVehicule().equals("VAT")){
			red = red.add(primeBase.multiply(BigDecimal.valueOf(getTarif().getRcTarif3().getTauxMajredStatutproautrest3())).multiply(BigDecimal.valueOf(0.01)));	
			
		}*/
			
			
	    if (remorque == true) {
				//primeRc = primeRc.multiply(BigDecimal.valueOf(1.2));
				primeRemorque = primeRemorque.add(primeBase.multiply(BigDecimal.valueOf(getTarif().getRcTarif3().getTauxMajredRemorquet3())).multiply(BigDecimal.valueOf(0.01)));
			}
			
		
		
		reduction = red;
		//calcul de la surprime
		surprimes=BigDecimal.ZERO;
		
		 
		nbreTransporte = (short) (nbrePlaceCab + nbrePlaceHorscab-3);
		
		if(typeTransporte.equalsIgnoreCase("TH")){	
			surprimes = surprimes.add(primeBase.multiply(BigDecimal.valueOf(getTarif().getRcTarif3().getTauxTranspHydrocar3t3())).multiply(BigDecimal.valueOf(0.01)));
		}
		
          
		if(typeTransporte.equalsIgnoreCase("TA")){	
			surprimes = surprimes.add(primeBase.multiply(BigDecimal.valueOf(getTarif().getRcTarif3().getTauxRemorquet3())).multiply(BigDecimal.valueOf(0.01)));
		}
      
		
		primeRc = primeRc.add(primeRemorque).add(surprimes).subtract(reduction);
		
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return primeRc;
	}

	
	
		
		

	/*------------determination de la prime en defense recours--------------------------------*/
	public java.math.BigDecimal getDefenseRecours() {
		try {
			
			defenseRecours = defenseRecours.add(primeBase.multiply(BigDecimal.valueOf(getTarif().getTauxDrPrimeFinale())).multiply(BigDecimal.valueOf(0.01)));
			
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return defenseRecours;
	}

	
	

	/*-------------------determination de la prime en immobilisation--------------------*/
	public java.math.BigDecimal getImmobilisation() {
		try{
		immobilisation = getTarif().getImmobVehicule();
	} catch (NullPointerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return immobilisation;
	}
	
	/*-------------------determination de la prime en remboursement anticipe--------------------*/
	public java.math.BigDecimal getRemboursemmentAnticipe() {
		try{
		if(dureeContrat<12){
		remboursemmentAnticipe = getTarif().getGraCourt();
		}else{
		remboursemmentAnticipe = getTarif().getGraAnnuelle();
		}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return remboursemmentAnticipe;
	}
	
	/*-------------------determination de la prime en brise de glace--------------------*/
	public java.math.BigDecimal getBrisGlaceRC() {
		try{
		brisGlaceRC = brisGlaceRC.add(getValeurNeuve().multiply(BigDecimal.valueOf(getTarif().getTauxBgOpt1())).multiply(BigDecimal.valueOf(0.01)));
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return brisGlaceRC;
	}
	
	public java.math.BigDecimal getBrisGlaceRNC1() {
		try{
		brisGlaceRNC1 = brisGlaceRNC1.add(getValeurNeuve().multiply(BigDecimal.valueOf(getTarif().getTauxBgOpt2())).multiply(BigDecimal.valueOf(0.01)));
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return brisGlaceRNC1;
	}
	public java.math.BigDecimal getBrisGlaceRNC2() {
		try{
		brisGlaceRNC2 = brisGlaceRNC2.add(getValeurNeuve().multiply(BigDecimal.valueOf(getTarif().getTauxBgOpt3())).multiply(BigDecimal.valueOf(0.01)));
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return brisGlaceRNC2;
	}

	/*-------------------determination de la prime en dommage--------------------*/
	public java.math.BigDecimal getDommage() {
		try{
		dommage = dommage.add(getValeurNeuve().multiply(BigDecimal.valueOf(getTarif().getTauxPrimeDomAcc())).multiply(BigDecimal.valueOf(0.01)));
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dommage;
	}
	
	public java.math.BigDecimal getCollision() {
		try{
		collision = collision.add(getValeurNeuve().multiply(BigDecimal.valueOf(getTarif().getTauxPrimeDomCol())).multiply(BigDecimal.valueOf(0.01)));
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return collision;
	}
	/*-------------------determination de la prime en vol � main arm�e--------------------*/

	public java.math.BigDecimal getVolMain() {
		
		//2ans
		try {
			if(getAgeVehicule() <= 2){
			
			
			if (getValeurVenale().intValue() > 0 & getValeurVenale().intValue() <= 4000000) {
				volMain = getTarif().getPrimeVol11();
			}
			if (getValeurVenale().intValue() > 4000001 & getValeurVenale().intValue() <= 6000000) {
				volMain = getTarif().getPrimeVol12();
			}
			if (getValeurVenale().intValue() > 6000001 & getValeurVenale().intValue() <= 10000000) {
				volMain = getTarif().getPrimeVol13();
			}
			
			
			if (getValeurVenale().intValue() > 10000001 & getValeurVenale().intValue() <= 16000000) {
				volMain = getTarif().getPrimeVol14();
			}
			if (getValeurVenale().intValue() > 16000001 & getValeurVenale().intValue() <= 99999999) {
				volMain = getTarif().getPrimeVol15();
			}
			}
			else {
				
				
				if (getValeurVenale().intValue() > 0 & getValeurVenale().intValue() <= 4000000) {
					volMain = getTarif().getPrimeVol21();
				}
				if (getValeurVenale().intValue() > 4000001 & getValeurVenale().intValue() <= 6000000) {
					volMain = getTarif().getPrimeVol22();
				}
				if (getValeurVenale().intValue() > 6000001 & getValeurVenale().intValue() <= 10000000) {
					volMain = getTarif().getPrimeVol23();
				}
				
				
				if (getValeurVenale().intValue() > 10000001 & getValeurVenale().intValue() <= 16000000) {
					volMain = getTarif().getPrimeVol24();
				}
				if (getValeurVenale().intValue() > 16000001  & getValeurVenale().intValue() <= 99999999) {
					volMain = getTarif().getPrimeVol25();
				}
				}
				
				
			
			
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return volMain;
	}

	
	
	
	

	/*-------------------determination de la prime en incendie--------------------*/

	public java.math.BigDecimal getIncendie() {
		try {
			if(getAgeVehicule() <= 2){
			
			if (getValeurVenale().intValue() > 0 & getValeurVenale().intValue() <= 4000000) {
				incendie = getTarif().getPrimeIncendie11();
			}
			if (getValeurVenale().intValue() > 4000001 & getValeurVenale().intValue() <= 6000000 ) {
				incendie = getTarif().getPrimeIncendie12();
			}
			if (getValeurVenale().intValue() > 6000001  & getValeurVenale().intValue() <= 10000000 ) {
				incendie = getTarif().getPrimeIncendie13();
			}
			
			if (getValeurVenale().intValue() > 10000001 & getValeurVenale().intValue() <= 16000000 ) {
				incendie = getTarif().getPrimeIncendie14();
			}
			if (getValeurVenale().intValue() > 16000001  & getValeurVenale().intValue() <= 99999999) {
				incendie = getTarif().getPrimeIncendie15();
			}
			
			
			
			else {
				if (getValeurVenale().intValue() > 0 & getValeurVenale().intValue() <= 4000000) {
					incendie = getTarif().getPrimeIncendie21();
				}
				if (getValeurVenale().intValue() > 4000001 & getValeurVenale().intValue() <= 6000000 ) {
					incendie = getTarif().getPrimeIncendie22();
				}
				if (getValeurVenale().intValue() > 6000001  & getValeurVenale().intValue() <= 10000000 ) {
					incendie = getTarif().getPrimeIncendie23();
				}
				
				if (getValeurVenale().intValue() > 10000001 & getValeurVenale().intValue() <= 16000000 ) {
					incendie = getTarif().getPrimeIncendie24();
				}
				if (getValeurVenale().intValue() > 16000001  & getValeurVenale().intValue() <= 99999999) {
					incendie = getTarif().getPrimeIncendie25();
				}
			}}
				
			
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return incendie;
	}
	
	
	/*-------------------determination de la prime en securit�--------------------*/
	public java.math.BigDecimal getSecuriteRoutiere1() {
		securiteRoutiere1 = BigDecimal.ZERO;
		return securiteRoutiere1;
	}
	
	public java.math.BigDecimal getSecuriteRoutiere2() {
		securiteRoutiere2 = BigDecimal.ZERO;
		return securiteRoutiere2;
	}
	public java.math.BigDecimal getSecuriteRoutiere3() {
		securiteRoutiere3 = BigDecimal.ZERO;
		return securiteRoutiere3;
	}
	
	public java.math.BigDecimal getIndividuelChauf1(){
		return individuelChauf1;
	}
	
	public java.math.BigDecimal getIndividuelChauf2(){
		return individuelChauf2;
	}
	
	public java.math.BigDecimal getIndividuelChauf3(){
		return individuelChauf3;
	}

	/*----------les Setters --------------------------------------------------------------*/

	public void setPrimeRc(java.math.BigDecimal primeRc) {
		this.primeRc = primeRc;
	}


	/*--------------autres Getter and setter------------------------------*/
	public String getEnergie() {
		return energie;
	}

	public void setEnergie(String energie) {
		this.energie = energie;
	}

	public java.math.BigDecimal getPuissFisc() {
		return puissFisc;
	}

	public void setPuissFisc(java.math.BigDecimal puissFisc) {
		this.puissFisc = puissFisc;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public boolean isRemorque() {
		return remorque;
	}

	public void setRemorque(boolean remorque) {
		this.remorque = remorque;
	}

	public int getDureePermis() {
		return dureePermis;
	}

	public void setDureePermis(int dureePermis) {
		this.dureePermis = dureePermis;
	}

	public void setValeurNeuve(java.math.BigDecimal valeurNeuve) {
		this.valeurNeuve = valeurNeuve;
	}

	public java.math.BigDecimal getMontantAccessoires() {
		return montantAccessoires;
	}

	public void setMontantAccessoires(java.math.BigDecimal montantAccessoires) {
		this.montantAccessoires = montantAccessoires;
	}

	public String getOptionSR() {
		return optionSR;
	}

	public void setOptionSR(String optionSR) {
		this.optionSR = optionSR;
	}

	public java.math.BigDecimal getValeurVenale() {
		return valeurVenale;
	}

	public void setValeurVenale(java.math.BigDecimal valeurVenale) {
		this.valeurVenale = valeurVenale;
	}

	public java.math.BigDecimal getValeurNeuve() {
		return valeurNeuve;
	}

	public void setPrimeBase(java.math.BigDecimal primeBase) {
		this.primeBase = primeBase;
	}


	public String getOptionIndCh() {
		return optionIndCh;
	}

	public void setOptionIndCh(String optionIndCh) {
		this.optionIndCh = optionIndCh;
	}

	public String getTypeVehicule() {
		return typeVehicule;
	}


	public void setTypeVehicule(String typeVehicule) {
		this.typeVehicule = typeVehicule;
	}


	public java.math.BigDecimal getReduction() {
		return reduction;
	}
	public void setReduction(java.math.BigDecimal reduction) {
		this.reduction = reduction;
	}

	public java.math.BigDecimal getPrimeRemorque() {
		return primeRemorque;
	}

	public void setPrimeRemorque(java.math.BigDecimal primeRemorque) {
		this.primeRemorque = primeRemorque;
	}

	public java.math.BigDecimal getChargeUtile() {
		return chargeUtile;
	}

	public void setChargeUtile(java.math.BigDecimal chargeUtile) {
		this.chargeUtile = chargeUtile;
	}
	public java.math.BigDecimal getSurprimes() {
		return surprimes;
	}
	public void setSurprimes(java.math.BigDecimal surprimes) {
		this.surprimes = surprimes;
	}

	public String getTypeTransporte() {
		return typeTransporte;
	}

	public void setTypeTransporte(String typeTransporte) {
		this.typeTransporte = typeTransporte;
	}

	public short getNbreTransporte() {
		return nbreTransporte;
	}

	public void setNbreTransporte(short nbreTransporte) {
		this.nbreTransporte = nbreTransporte;
	}

	public short getNbrecarte() {
		return nbrecarte;
	}

	public void setNbrecarte(short nbrecarte) {
		this.nbrecarte = nbrecarte;
	}

	public short getNbrePlaceCab() {
		return nbrePlaceCab;
	}

	public void setNbrePlaceCab(short nbrePlaceCab) {
		this.nbrePlaceCab = nbrePlaceCab;
	}

	public short getNbrePlaceHorscab() {
		return nbrePlaceHorscab;
	}

	public void setNbrePlaceHorscab(short nbrePlaceHorscab) {
		this.nbrePlaceHorscab = nbrePlaceHorscab;
	}

	public java.math.BigDecimal getPuissReel() {
		return puissReel;
	}

	public void setPuissReel(java.math.BigDecimal puissReel) {
		this.puissReel = puissReel;
	}

	public int getDureeContrat() {
		return dureeContrat;
	}

	public void setDureeContrat(int dureeContrat) {
		this.dureeContrat = dureeContrat;
	}

	public int getAgeVehicule() {
		return ageVehicule;
	}

	public void setAgeVehicule(int ageVehicule) {
		this.ageVehicule = ageVehicule;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public java.math.BigDecimal getVolAccessoires() {
		return volAccessoires;
	}

	public void setVolAccessoires(java.math.BigDecimal volAccessoires) {
		this.volAccessoires = volAccessoires;
	}

	public java.math.BigDecimal getVolVandalisme() {
		return volVandalisme;
	}

	public void setVolVandalisme(java.math.BigDecimal volVandalisme) {
		this.volVandalisme = volVandalisme;
	}

	public java.math.BigDecimal getDefenseRecoursDommage() {
		return defenseRecoursDommage;
	}

	public void setDefenseRecoursDommage(java.math.BigDecimal defenseRecoursDommage) {
		this.defenseRecoursDommage = defenseRecoursDommage;
	}
	
	
}
