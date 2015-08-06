package com.j3a.assurance.prime.categorie;
import java.math.BigDecimal;

import com.j3a.assurance.model.Tarif;

public class PrimeCategorie9 implements PrimeCategorieInterface {

	private String energie;
	private String typeVehicule;
	private java.math.BigDecimal puissFisc = BigDecimal.ZERO;
	private String categorie;
	private String zone;
	private String classe;
	private String statut;
	private java.math.BigDecimal primeBase = BigDecimal.ZERO;
	private boolean remorque;
	private java.math.BigDecimal primeRemorque = BigDecimal.ZERO;
	private java.math.BigDecimal surprimes = BigDecimal.ZERO;
	private String typeTransporte="";
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

		try {
		 primeBase = BigDecimal.ZERO;
		if (remorque == true) {
			if (zone.equals("zone1"))
				primeBase = getTarif().getRcTarif9().getVhlRemorqueZone1();
			if (zone.equals("zone2"))
				primeBase = getTarif().getRcTarif9().getVhlRemorqueZone2();
			if (zone.equals("zone3"))
				primeBase = getTarif().getRcTarif9().getVhlRemorqueZone3();
		}

		if (remorque == false) {
			if (zone.equals("zone1"))
				// setPrimeBase(66885);
				primeBase = getTarif().getRcTarif9().getVhlSrZone1();
			if (zone.equals("zone2"))
				// setPrimeBase(63541);
				primeBase = getTarif().getRcTarif9().getVhlSrZone2();
			if (zone.equals("zone3"))
				// setPrimeBase(60197);
				primeBase = getTarif().getRcTarif9().getVhlSrZone3();
		}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return primeBase;
	}

	/*---------------	calcul de la prime RC-------------------------------*/
	public java.math.BigDecimal getPrimeRc() {
		//primeRc = getPrimeBase();
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
			
				volMain = getValeurVenale().multiply(BigDecimal.valueOf(getTarif().getTauxPrimeVol1())).multiply(BigDecimal.valueOf(0.01));
			
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return volMain;
	}
	

	/*-------------------determination de la prime en brise de glace--------------------*/
	public java.math.BigDecimal getBrisGlaceRC() {
		
		return brisGlaceRC;
	}
	
	public java.math.BigDecimal getBrisGlaceRNC1() {
		
		return brisGlaceRNC1;
	}
	public java.math.BigDecimal getBrisGlaceRNC2() {
		
		return brisGlaceRNC2;
	}

	/*-------------------determination de la prime en vol accessoires--------------------*/
	public java.math.BigDecimal getVolAccessoires() {
		//volAccessoires = getMontantAccessoires().multiply(BigDecimal.valueOf(0.08));
		return volAccessoires;
	}
	/*-------------------determination de la prime en vol vandalisme--------------------*/
	public java.math.BigDecimal getVolVandalisme() {
		//volVandalisme = BigDecimal.valueOf(12000);
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
		//securiteRoutiere1 = BigDecimal.valueOf(9650);
		return securiteRoutiere1;
	}
	
	public java.math.BigDecimal getSecuriteRoutiere2() {
		//securiteRoutiere2 = BigDecimal.valueOf(16420);
		return securiteRoutiere2;
	}
	public java.math.BigDecimal getSecuriteRoutiere3() {
		//securiteRoutiere3 = BigDecimal.valueOf(19120);
		return securiteRoutiere3;
	}
	
	public java.math.BigDecimal getIndividuelChauf1(){
		
		try {
		individuelChauf1 = getTarif().getIndivChauffDeces();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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


	@Override
	public BigDecimal getPuissReel() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setPuissReel(BigDecimal puissReel) {
		// TODO Auto-generated method stub
		
	}


	public String getTypeVehicule() {
		return typeVehicule;
	}


	public void setTypeVehicule(String typeVehicule) {
		this.typeVehicule = typeVehicule;
	}


	@Override
	public int getDureeContrat() {
		// TODO Auto-generated method stub
		return dureeContrat;
	}


	@Override
	public void setDureeContrat(int dureeContrat) {
		// TODO Auto-generated method stub
		this.dureeContrat = dureeContrat;
	}


	@Override
	public int getAgeVehicule() {
		// TODO Auto-generated method stub
		return ageVehicule;
	}


	@Override
	public void setAgeVehicule(int ageVehicule) {
		// TODO Auto-generated method stub
		this.ageVehicule = ageVehicule;
	}


	public java.math.BigDecimal getReduction() {
		return reduction;
	}
	public void setReduction(java.math.BigDecimal reduction) {
		this.reduction = reduction;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
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
	
	
}
