package com.j3a.assurance.prime.categorie;
import java.math.BigDecimal;

import com.j3a.assurance.model.Tarif;

public class PrimeCategorie6 implements PrimeCategorieInterface {
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

		
		try{
		 primeBase = BigDecimal.valueOf(0);
		 if(typeVehicule.equalsIgnoreCase("VP")){
		if (getNbrecarte() == 1) {
			if (zone.equals("zone1"))
				primeBase = getTarif().getRcTarif6().getVhl1Zone1();
			if (zone.equals("zone2"))
				// setPrimeBase(55741);
				primeBase = getTarif().getRcTarif6().getVhl1Zone2();
			if (zone.equals("zone3"))
				// setPrimeBase(52808);
				primeBase = getTarif().getRcTarif6().getVhl1Zone3();
		}

			if (getNbrecarte() == 2) {
				if (zone.equals("zone1"))
					// setPrimeBase(58675);
					primeBase = getTarif().getRcTarif6().getVhl2Zone1();
				if (zone.equals("zone2"))
					// setPrimeBase(55741);
					primeBase = getTarif().getRcTarif6().getVhl2Zone2();
				if (zone.equals("zone3"))
					// setPrimeBase(52808);
					primeBase = getTarif().getRcTarif6().getVhl2Zone3();
			}

			if (getNbrecarte() == 3) {
				if (zone.equals("zone1"))
					// setPrimeBase(58675);
					primeBase = getTarif().getRcTarif6().getVhl3Zone1();
				if (zone.equals("zone2"))
					// setPrimeBase(55741);
					primeBase = getTarif().getRcTarif6().getVhl3Zone2();
				if (zone.equals("zone3"))
					// setPrimeBase(52808);
					primeBase = getTarif().getRcTarif6().getVhl3Zone3();
			}
			
			if (getNbrecarte() == 4) {
				if (zone.equals("zone1"))
					// setPrimeBase(58675);
					primeBase = getTarif().getRcTarif6().getVhl4Zone1();
				if (zone.equals("zone2"))
					// setPrimeBase(55741);
					primeBase = getTarif().getRcTarif6().getVhl4Zone2();
				if (zone.equals("zone3"))
					// setPrimeBase(52808);
					primeBase = getTarif().getRcTarif6().getVhl4Zone3();
			}
			if (getNbrecarte() > 4) {
				if (zone.equals("zone1"))
					// setPrimeBase(58675);
					//primeBase = primeBase.add(BigDecimal.valueOf(175983*(getNbrecarte()-4)));
					primeBase = getTarif().getRcTarif6().getVhl5Zone1();
				if (zone.equals("zone2"))
					// setPrimeBase(55741);
					primeBase = getTarif().getRcTarif6().getVhl5Zone2();
				if (zone.equals("zone3"))
					// setPrimeBase(52808);
					primeBase = getTarif().getRcTarif6().getVhl5Zone3();
			}
			}
		 
		 if(typeVehicule.equalsIgnoreCase("VC")||typeVehicule.equalsIgnoreCase("VT")){
				if (getNbrecarte() == 1) {
					if (zone.equals("zone1"))
						// setPrimeBase(58675);
						primeBase = getTarif().getRcTarif6().getCyclo1Zone1();
					if (zone.equals("zone2"))
						// setPrimeBase(55741);
						primeBase = getTarif().getRcTarif6().getCyclo1Zone2();
					if (zone.equals("zone3"))
						// setPrimeBase(52808);
						primeBase = getTarif().getRcTarif6().getCyclo1Zone3();
				}

					if (getNbrecarte() == 2) {
						if (zone.equals("zone1"))
							// setPrimeBase(58675);
							primeBase = getTarif().getRcTarif6().getCyclo2Zone1();
						if (zone.equals("zone2"))
							// setPrimeBase(55741);
							primeBase = getTarif().getRcTarif6().getCyclo2Zone2();
						if (zone.equals("zone3"))
							// setPrimeBase(52808);
							primeBase = getTarif().getRcTarif6().getCyclo2Zone3();
					}

					if (getNbrecarte() == 3) {
						if (zone.equals("zone1"))
							// setPrimeBase(58675);
							primeBase = getTarif().getRcTarif6().getCyclo3Zone1();
						if (zone.equals("zone2"))
							// setPrimeBase(55741);
							primeBase = getTarif().getRcTarif6().getCyclo3Zone2();
						if (zone.equals("zone3"))
							// setPrimeBase(52808);
							primeBase = getTarif().getRcTarif6().getCyclo3Zone3();
					}
					
					if (getNbrecarte() == 4) {
						if (zone.equals("zone1"))
							// setPrimeBase(58675);
							primeBase = getTarif().getRcTarif6().getCyclo4Zone1();
						if (zone.equals("zone2"))
							// setPrimeBase(55741);
							primeBase = getTarif().getRcTarif6().getCyclo4Zone2();
						if (zone.equals("zone3"))
							// setPrimeBase(52808);
							primeBase = getTarif().getRcTarif6().getCyclo4Zone3();
					}
					if (getNbrecarte() > 4) {
						if (zone.equals("zone1"))
							// setPrimeBase(58675);
							//primeBase = primeBase.add(BigDecimal.valueOf(20672*(getNbrecarte()-4)));
						primeBase = getTarif().getRcTarif6().getCyclo5Zone1();
						
						if (zone.equals("zone2"))
							// setPrimeBase(55741);
							primeBase = getTarif().getRcTarif6().getCyclo5Zone2();
						
						if (zone.equals("zone3"))
							// setPrimeBase(52808);
							primeBase = getTarif().getRcTarif6().getCyclo5Zone3();
						
					}
					}
				 
		 
		 if(typeVehicule.equalsIgnoreCase("VA")){
				if (getNbrecarte() == 1) {
					if (zone.equals("zone1"))
						// setPrimeBase(58675);
						primeBase = getTarif().getRcTarif6().getAutresVhl1Zone1();
					if (zone.equals("zone2"))
						// setPrimeBase(55741);
						primeBase = getTarif().getRcTarif6().getAutresVhl1Zone2();
					if (zone.equals("zone3"))
						// setPrimeBase(52808);
						primeBase = getTarif().getRcTarif6().getAutresVhl1Zone3();
				}

					if (getNbrecarte() == 2) {
						if (zone.equals("zone1"))
							// setPrimeBase(58675);
							primeBase = getTarif().getRcTarif6().getAutresVhl2Zone1();
						if (zone.equals("zone2"))
							// setPrimeBase(55741);
							primeBase = getTarif().getRcTarif6().getAutresVhl2Zone2();
						if (zone.equals("zone3"))
							// setPrimeBase(52808);
							primeBase = getTarif().getRcTarif6().getAutresVhl2Zone3();
					}

					if (getNbrecarte() == 3) {
						if (zone.equals("zone1"))
							// setPrimeBase(58675);
							primeBase = getTarif().getRcTarif6().getAutresVhl3Zone1();;
						if (zone.equals("zone2"))
							// setPrimeBase(55741);
							primeBase = getTarif().getRcTarif6().getAutresVhl3Zone2();
						if (zone.equals("zone3"))
							// setPrimeBase(52808);
							primeBase = getTarif().getRcTarif6().getAutresVhl3Zone3();
					}
					
					if (getNbrecarte() == 4) {
						if (zone.equals("zone1"))
							// setPrimeBase(58675);
							primeBase = getTarif().getRcTarif6().getAutresVhl4Zone1();
						if (zone.equals("zone2"))
							// setPrimeBase(55741);
							primeBase = getTarif().getRcTarif6().getAutresVhl4Zone2();
						if (zone.equals("zone3"))
							// setPrimeBase(52808);
							primeBase = getTarif().getRcTarif6().getAutresVhl4Zone3();
					}
					if (getNbrecarte() > 4) {
						if (zone.equals("zone1"))
							// setPrimeBase(58675);
							primeBase = getTarif().getRcTarif6().getAutresVhl5Zone1();
						
						if (zone.equals("zone2"))
							// setPrimeBase(55741);
							primeBase = getTarif().getRcTarif6().getAutresVhl5Zone2();
							
						if (zone.equals("zone3"))
							// setPrimeBase(52808);
							primeBase = getTarif().getRcTarif6().getAutresVhl5Zone3();
					
					}
					}
		 
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return primeBase;
	}

	/*---------------	calcul de la prime RC-------------------------------*/
	public java.math.BigDecimal getPrimeRc() {
		primeRc = getPrimeBase();
		return primeRc;
	}

	/*------------determination de la prime en defense recours--------------------------------*/
	public java.math.BigDecimal getDefenseRecours() {
		defenseRecours = BigDecimal.valueOf(0);
		return defenseRecours;
	}

	public java.math.BigDecimal getDefenseRecoursDommage() {
		defenseRecoursDommage = BigDecimal.valueOf(0);
		return defenseRecoursDommage;
	}

	/*-------------------determination de la prime en immobilisation--------------------*/
	public java.math.BigDecimal getImmobilisation() {
		immobilisation = BigDecimal.valueOf(0);
		return immobilisation;
	}

	/*-------------------determination de la prime en remboursement anticipe--------------------*/
	public java.math.BigDecimal getRemboursemmentAnticipe() {
		if(dureeContrat<12){
		remboursemmentAnticipe = BigDecimal.valueOf(0);
		}else{
		remboursemmentAnticipe = BigDecimal.valueOf(0);
		}
		return remboursemmentAnticipe;
	}

	/*-------------------determination de la prime en brise de glace--------------------*/
	public java.math.BigDecimal getBrisGlaceRC() {
		
		//brisGlaceRC = getValeurNeuve().multiply(BigDecimal.valueOf(0.003));
		return brisGlaceRC;
	}
	
	public java.math.BigDecimal getBrisGlaceRNC1() {
		
		brisGlaceRNC1 = BigDecimal.valueOf(0);
		return brisGlaceRNC1;
	}
	public java.math.BigDecimal getBrisGlaceRNC2() {
		
		brisGlaceRNC2 = BigDecimal.valueOf(0);
		return brisGlaceRNC2;
	}

	/*-------------------determination de la prime en dommage--------------------*/
	public java.math.BigDecimal getDommage() {
		dommage = BigDecimal.valueOf(0);
		return dommage;
	}
	
	public java.math.BigDecimal getCollision() {
		collision = BigDecimal.valueOf(0);
		
		return collision;
	}

	/*-------------------determination de la prime en vol � main arm�e--------------------*/

	public java.math.BigDecimal getVolMain() {
		
			volMain =BigDecimal.valueOf(0);
		return volMain;
	}

	/*-------------------determination de la prime en vol accessoires--------------------*/
	public java.math.BigDecimal getVolAccessoires() {
		volAccessoires = BigDecimal.valueOf(0);
		return volAccessoires;
	}
	/*-------------------determination de la prime en vol vandalisme--------------------*/
	public java.math.BigDecimal getVolVandalisme() {
		volVandalisme = BigDecimal.valueOf(0);
		return volVandalisme;
	}

	/*-------------------determination de la prime en incendie--------------------*/

	public java.math.BigDecimal getIncendie() {
		
			incendie = BigDecimal.valueOf(0);
		
		return incendie;
	}

	/*-------------------determination de la prime en securit�--------------------*/
	public java.math.BigDecimal getSecuriteRoutiere1() {
		securiteRoutiere1 = BigDecimal.valueOf(0);
		return securiteRoutiere1;
	}
	
	public java.math.BigDecimal getSecuriteRoutiere2() {
		securiteRoutiere2 = BigDecimal.valueOf(0);
		return securiteRoutiere2;
	}
	public java.math.BigDecimal getSecuriteRoutiere3() {
		securiteRoutiere3 = BigDecimal.valueOf(0);
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

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}	
	
}
