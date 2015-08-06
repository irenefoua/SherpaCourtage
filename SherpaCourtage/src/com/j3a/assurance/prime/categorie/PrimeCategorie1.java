package com.j3a.assurance.prime.categorie;
import java.math.BigDecimal;

import com.j3a.assurance.model.Tarif;

public class PrimeCategorie1 implements PrimeCategorieInterface {

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
	private short nbreTransporte;
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
	private java.math.BigDecimal individuelChauf1 = BigDecimal.ZERO;
	private java.math.BigDecimal individuelChauf2 = BigDecimal.ZERO;
	private java.math.BigDecimal individuelChauf3 = BigDecimal.ZERO;
	private java.math.BigDecimal reduction = BigDecimal.ZERO;
	private String optionIndCh;
	private int dureeContrat;
	private int ageVehicule;
	private java.math.BigDecimal chargeUtile= BigDecimal.ZERO;
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
	
	public java.math.BigDecimal getPrimeBase() {
		 primeBase = BigDecimal.ZERO;
		 //Ligne1
		 try {
			if ((energie.equals("essence") & puissFisc.intValue() >= 1 & puissFisc.intValue() <= 2)
						|| (energie.equals("diesel") & puissFisc.intValue() == 1)) {
					if (zone.equals("zone1"))
						
						primeBase = getTarif().getRcTarif1().getPfE12d1Zone1();
					if (zone.equals("zone2"))
						
					primeBase = getTarif().getRcTarif1().getPfE12d1Zone2();
					if (zone.equals("zone3"))
						
						primeBase = getTarif().getRcTarif1().getPfE12d1Zone3();
				}
			 //Ligne2
				if ((energie.equals("essence") & puissFisc.intValue() >= 3 & puissFisc.intValue() <= 6)
						|| (energie.equals("diesel") & puissFisc.intValue() >= 2 & puissFisc.intValue() <= 4)) {
					if (zone.equals("zone1"))
						primeBase = getTarif().getRcTarif1().getPfE36d24Zone1();
					if (zone.equals("zone2"))
						primeBase = getTarif().getRcTarif1().getPfE36d24Zone2();
					if (zone.equals("zone3"))
						primeBase = getTarif().getRcTarif1().getPfE36d24Zone3();
				}
				//Ligne3
				if ((energie.equals("essence") & puissFisc.intValue() >= 7 & puissFisc.intValue() <= 9)
						|| (energie.equals("diesel") & puissFisc.intValue() >= 5 & puissFisc.intValue() <= 6)) {
					if (zone.equals("zone1"))
						primeBase = getTarif().getRcTarif1().getPfE79d56Zone1();
					if (zone.equals("zone2"))
						primeBase = getTarif().getRcTarif1().getPfE79d56Zone2();
					if (zone.equals("zone3"))
						primeBase = getTarif().getRcTarif1().getPfE79d56Zone3();
				}
				//Ligne4
				if ((energie.equals("essence") & puissFisc.intValue() >= 10 & puissFisc.intValue() <= 11)
						|| (energie.equals("diesel") & puissFisc.intValue() >= 7 & puissFisc.intValue() <= 8)) {
					if (zone.equals("zone1"))
						primeBase = getTarif().getRcTarif1().getPfE1011d78Zone1();
					if (zone.equals("zone2"))
						primeBase = getTarif().getRcTarif1().getPfE1011d78Zone2();
					if (zone.equals("zone3"))
						primeBase = getTarif().getRcTarif1().getPfE1011d78Zone3();
				}
				//Ligne5
				if ((energie.equals("essence") & puissFisc.intValue() >= 12)
						|| (energie.equals("diesel") & puissFisc.intValue() >= 9)) {
					if (zone.equals("zone1"))
						primeBase = getTarif().getRcTarif1().getPfE12d9Zone1();
					if (zone.equals("zone2"))
						primeBase = getTarif().getRcTarif1().getPfE12d9Zone2();
					if (zone.equals("zone3"))
						primeBase = getTarif().getRcTarif1().getPfE12d9Zone3();
				}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		 System.out.println("Prime de Base=========="+primeBase);
		return primeBase;
	}
	
	
	/*---------------	calcul de la prime RC-------------------------------*/
	public java.math.BigDecimal getPrimeRc() {
		java.math.BigDecimal red = BigDecimal.ZERO;
		getPrimeBase();
		 primeRc = primeBase;
		 System.out.println("-------------PrimeRC----------------"+primeRc);
		 try {
			if (dureePermis <= 2) {
				classe = "classe1";
			}
			if (dureePermis > 2) {
				classe = "classe2";
			}

			if (classe.equals("classe2")) {
				
				//primeRc = primeRc.multiply(BigDecimal.valueOf(0.95));
				red = red.add(primeBase.multiply(BigDecimal.valueOf(getTarif().getRcTarif1().getTauxMajredPermis2())).multiply(BigDecimal.valueOf(0.01)));
			}

			if (statut.equals("a")) {
				//primeRc = primeRc.multiply(BigDecimal.valueOf(0.95));
				red = red.add(primeBase.multiply(BigDecimal.valueOf(getTarif().getRcTarif1().getTauxMajredStatutproa())).multiply(BigDecimal.valueOf(0.01)));
			}
			
			if (statut.equals("b")) {
				//primeRc = primeRc.multiply(BigDecimal.valueOf(0.95));
				red = red.add(primeBase.multiply(BigDecimal.valueOf(getTarif().getRcTarif1().getTauxMajredStatutprob())).multiply(BigDecimal.valueOf(0.01)));
			}
			
			if (statut.equals("c")) {
				//primeRc = primeRc.multiply(BigDecimal.valueOf(0.95));
				red = red.add(primeBase.multiply(BigDecimal.valueOf(getTarif().getRcTarif1().getTauxMajredStatutproc())).multiply(BigDecimal.valueOf(0.01)));
			}

			if (statut.equals("d")) {
				//primeRc = primeRc.multiply(BigDecimal.valueOf(0.90));
				red = red.add(primeBase.multiply(BigDecimal.valueOf(getTarif().getRcTarif1().getTauxMajredStatutprod())).multiply(BigDecimal.valueOf(0.01)));
			}
			
			if (statut.equals("e")) {
				//primeRc = primeRc.multiply(BigDecimal.valueOf(0.90));
				red = red.add(primeBase.multiply(BigDecimal.valueOf(getTarif().getRcTarif1().getTauxMajredStatutproe())).multiply(BigDecimal.valueOf(0.01)));
			}

			if (remorque == true) {
				//primeRc = primeRc.multiply(BigDecimal.valueOf(1.2));
				primeRemorque = primeRemorque.add(primeBase.multiply(BigDecimal.valueOf(getTarif().getRcTarif1().getTauxMajredRemorque())).multiply(BigDecimal.valueOf(0.01)));
			
			}
			//System.out.println("-------------Reduction----------------"+red);
			reduction = red;
			//System.out.println("-------------Remorque----------------"+primeRemorque);
			primeRc = primeRc.add(primeRemorque).subtract(reduction);
			
			//System.out.println("-------------Prime RC----------------"+primeRc);
			//calcul des surprimes pour la RC
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//if(get)
		
		return primeRc;
	}

	/*------------determination de la prime en defense recours--------------------------------*/
	public java.math.BigDecimal getDefenseRecours() {
		try {
			defenseRecours = getTarif().getDrSansDomage();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return defenseRecours;
	}

	public java.math.BigDecimal getDefenseRecoursDommage() {
		try{
		defenseRecoursDommage = getTarif().getDrDomage();
	} catch (NullPointerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return defenseRecoursDommage;
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
		try {
			if (getValeurVenale().intValue() > 0 & getValeurVenale().intValue() <= 10000000) {
				volMain = getValeurVenale().multiply(BigDecimal.valueOf(getTarif().getTauxPrimeVol1())).multiply(BigDecimal.valueOf(0.01));
			}
			if (getValeurVenale().intValue() > 10000001 & getValeurVenale().intValue() <= 20000000) {
				volMain = getValeurVenale().multiply(BigDecimal.valueOf(getTarif().getTauxPrimeVol2())).multiply(BigDecimal.valueOf(0.01));
			}
			if (getValeurVenale().intValue() > 20000001) {
				volMain = getValeurVenale().multiply(BigDecimal.valueOf(getTarif().getTauxPrimeVol3())).multiply(BigDecimal.valueOf(0.01));
			}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return volMain;
	}

	/*-------------------determination de la prime en vol accessoires--------------------*/
	public java.math.BigDecimal getVolAccessoires() {
		try{
		volAccessoires = getMontantAccessoires().multiply(BigDecimal.valueOf(getTarif().getTauxPrimeVolAcc())).multiply(BigDecimal.valueOf(0.01));
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return volAccessoires;
	}
	/*-------------------determination de la prime en vol vandalisme--------------------*/
	public java.math.BigDecimal getVolVandalisme() {
		try{
		volVandalisme = getTarif().getPrimeVandal();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return volVandalisme;
	}

	/*-------------------determination de la prime en incendie--------------------*/

	public java.math.BigDecimal getIncendie() {
		try {
			if (getValeurVenale().intValue() > 0 & getValeurVenale().intValue() <= 10000000) {
				incendie = getValeurVenale().multiply(BigDecimal.valueOf(getTarif().getTauxPrimeIncendie1())).multiply(BigDecimal.valueOf(0.01));
			}
			if (getValeurVenale().intValue() > 10000001 & getValeurVenale().intValue() <= 20000000) {
				incendie = getValeurVenale().multiply(BigDecimal.valueOf(getTarif().getTauxPrimeIncendie2())).multiply(BigDecimal.valueOf(0.01));
			}
			if (getValeurVenale().intValue() > 20000001) {
				incendie = getValeurVenale().multiply(BigDecimal.valueOf(getTarif().getTauxPrimeIncendie3())).multiply(BigDecimal.valueOf(0.01));
			}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return incendie;
	}

	/*-------------------determination de la prime en securit�--------------------*/
	public java.math.BigDecimal getSecuriteRoutiere1() {
		try {
			securiteRoutiere1 = getTarif().getSrPrime1();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return securiteRoutiere1;
	}
	
	public java.math.BigDecimal getSecuriteRoutiere2() {
		try {
			securiteRoutiere2 = getTarif().getSrPrime2();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return securiteRoutiere2;
	}
	public java.math.BigDecimal getSecuriteRoutiere3() {
		try {
			securiteRoutiere3 = getTarif().getSrPrime3();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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


	public java.math.BigDecimal getPuissReel() {
		return puissReel;
	}


	public void setPuissReel(java.math.BigDecimal puissReel) {
		this.puissReel = puissReel;
	}


	public short getNbreTransporte() {
		return nbreTransporte;
	}


	public void setNbreTransporte(short nbreTransporte) {
		this.nbreTransporte = nbreTransporte;
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


	public String getCategorie() {
		return categorie;
	}


	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
	
}
