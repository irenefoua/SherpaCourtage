package com.j3a.assurance.prime.categorie;

import com.j3a.assurance.model.Tarif;

public interface PrimeCategorieInterface {

	/*---------------------------------------les Getters -----------------------------------------------*/
   
	
	public java.math.BigDecimal getPrimeBase();
	public java.math.BigDecimal getPrimeRc();
	public java.math.BigDecimal getPrimeRemorque();
	public java.math.BigDecimal getDefenseRecours();
	public java.math.BigDecimal getDefenseRecoursDommage();
	public java.math.BigDecimal getImmobilisation();
	public java.math.BigDecimal getRemboursemmentAnticipe();
	public java.math.BigDecimal getBrisGlaceRC();
	public java.math.BigDecimal getBrisGlaceRNC1();
	public java.math.BigDecimal getBrisGlaceRNC2();
	public java.math.BigDecimal getDommage();
	public java.math.BigDecimal getCollision();
	public java.math.BigDecimal getVolMain();
	public java.math.BigDecimal getVolAccessoires();
	public java.math.BigDecimal getVolVandalisme();
	public java.math.BigDecimal getIncendie();
	
	public java.math.BigDecimal getSecuriteRoutiere1();
	public java.math.BigDecimal getSecuriteRoutiere2();
	public java.math.BigDecimal getSecuriteRoutiere3();
	public java.math.BigDecimal getIndividuelChauf1();
	public java.math.BigDecimal getIndividuelChauf2();
	public java.math.BigDecimal getIndividuelChauf3();
	public java.math.BigDecimal getReduction();
	public java.math.BigDecimal getSurprimes();
	public java.math.BigDecimal getChargeUtile();
	public java.math.BigDecimal getPuissReel();
	public java.math.BigDecimal getPuissFisc();
	public java.math.BigDecimal getValeurNeuve();
	public java.math.BigDecimal getValeurVenale();
	
	public Tarif getTarif();
	public String getCategorie();
	public String getTypeVehicule();
	public String getTypeTransporte();
	public String getOptionIndCh();
	public String getStatut();
	public String getZone();
	public String getEnergie();
	public String getClasse();
	
	public int getDureePermis();
	public int getDureeContrat();
	public int getAgeVehicule();
	
	public short getNbreTransporte();
	public short getNbrecarte();
	public short getNbrePlaceCab();
	public short getNbrePlaceHorscab();
	
	public boolean isRemorque();
	
	
	
	
	/*---------------------------------------les Setters -----------------------------------------------*/


	
	
	public void setPrimeRc(java.math.BigDecimal primeRc);
	//public void setPrimeRemorque(java.math.BigDecimal primeRemorque);
	public void setPuissFisc(java.math.BigDecimal puissFisc);
	public void setPuissReel(java.math.BigDecimal puissReel);
	public void setChargeUtile(java.math.BigDecimal chargeUtile);
	//public void setSurprimes(java.math.BigDecimal surprimes);
	public void setValeurVenale(java.math.BigDecimal valeurVenale);
	public void setValeurNeuve(java.math.BigDecimal valeurNeuve);
	
	public void setTarif(Tarif tarif);
	public void setCategorie(String categorie);
	public void setEnergie(String energie);
	public void setZone(String zone);
	public void setClasse(String classe);
	public void setStatut(String statut);
	public void setTypeVehicule(String typeVehicule);
	public void setTypeTransporte(String typeTransporte);
	
	public void setDureePermis(int dureePermis);
	public void setDureeContrat(int dureeContrat);
	public void setAgeVehicule(int ageVehicule);

	public void setNbreTransporte(short nbreTransporte);
	public void setNbrecarte(short nbrecarte);	
	public void setNbrePlaceCab(short nbrePlaceCab);
	public void setNbrePlaceHorscab(short nbrePlaceHorscab);
	
	public void setRemorque(boolean remorque);
	
	
}
