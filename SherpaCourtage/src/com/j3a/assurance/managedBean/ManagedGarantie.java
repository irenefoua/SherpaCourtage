package com.j3a.assurance.managedBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.Garantie;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.utilitaires.Garanties;

@Component
@Scope("session")
public class ManagedGarantie implements Serializable  {
	private static final long serialVersionUID = 1L;
	@Autowired
	ObjectService objectService;
	
	
	Logger logger = Logger.getLogger(ManagedGarantie.class);
	private String choix, vuePrimes = "true",
			vuecalculPrime = "true", vueQuittance, statutList = "NO";
	private int valT = 0, tauxBonus= 0, tauxMalus= 0, tauxRed= 0, tauxFlotte= 0, tauxpermis= 0;
	private java.math.BigDecimal conseiller=BigDecimal.ZERO ,gestionnaire=BigDecimal.ZERO ,coassurance=BigDecimal.ZERO ,intermediaire=BigDecimal.ZERO;
	private java.math.BigDecimal reductionAutres=BigDecimal.ZERO ,reductionFlote=BigDecimal.ZERO ,reductionPermis=BigDecimal.ZERO,totalReductions=BigDecimal.ZERO,primeNette = BigDecimal.ZERO,reductions=BigDecimal.ZERO;
	private java.math.BigDecimal  surprimes=BigDecimal.ZERO,primeProrata=BigDecimal.ZERO, accessoires = BigDecimal.ZERO;
	private java.math.BigDecimal  bonus=BigDecimal.ZERO, malus=BigDecimal.ZERO;
	private List<String> elements;
	private int  durreeGarantie;
	
	List<Garanties> listegaranties = new ArrayList<Garanties>();
	List<Garanties> listeGarantiesSelect= new ArrayList<Garanties>();

	// voir cette déclaration
	
	public String mouvement;
	public String codeRisque;
	private String optionGarantie;
	


	/*-------ici on retrouve les methodes pour afficher et calculer les garanties ----------------*/

	public void affichegaranties() {
		getListegaranties().clear();
		System.out.println("INSIDE AFFICHE GAR :::::::::*************");
	
		getListegaranties().addAll(calculGarantie());
		
		System.out.println("Liste Garantie size::::::"+getListegaranties().size());
	}
	
	
	public List<Garanties> calculGarantie() {

		List<Garanties> garantiesList = new ArrayList<Garanties>();
		
		List listObject = getObjectService().getListGarantieByRisque(getCodeRisque());
		for (Iterator it = listObject.iterator(); it.hasNext();) {
			Garantie garantie = (Garantie) it.next();
			Garanties garanties = new Garanties(); 
			garanties.setCodeGarantie(garantie.getCodeGarantie());
			garanties.setLibelleGarantie(garantie.getLibelleGarantie());
			garantiesList.add(garanties);
		}
		return garantiesList;
	}

	public void cleanChamps(){
		 reductionAutres=BigDecimal.ZERO;
		reductionFlote=BigDecimal.ZERO ;
				reductionPermis=BigDecimal.ZERO;
				totalReductions=BigDecimal.ZERO;
				primeNette = BigDecimal.ZERO;
		 surprimes=BigDecimal.ZERO;
				 primeProrata=BigDecimal.ZERO;
				 accessoires = BigDecimal.ZERO;
		  bonus=BigDecimal.ZERO;
				   malus=BigDecimal.ZERO;
		
	}

	public String vueQuittance() {
		return null;
	}

	//Methode de choix des ganranties provenant d'un Avenant de modification des garanties.

/*-----------------les Getter et Setter -------------------------------*/

	

	public String getMouvement() {
		return mouvement;
	}

	public void setMouvement(String mouvement) {
		this.mouvement = mouvement;
	}


	



	// autres getter setter

	public String getVuecalculPrime() {

		return vuecalculPrime;
	}

	public void setVuecalculPrime(String vuecalculPrime) {
		this.vuecalculPrime = vuecalculPrime;
	}

	public String getVuePrimes() {
		
		return vuePrimes;
	}

	public void setVuePrimes(String vuePrimes) {
		this.vuePrimes = vuePrimes;
	}

	public String getVueQuittance() {
		/*if (vehiculeList.size() == 0) {
			vueQuittance = "false";
		} else {
			vueQuittance = "true";
		}*/
		return vueQuittance;
	}

	public void setVueQuittance(String vueQuittance) {
		this.vueQuittance = vueQuittance;
	}


	public void setElements(List<String> elements) {
		this.elements = elements;
	}

	public List<String> getElements() {
		if (elements == null) {
			elements = new ArrayList<String>();
		}
		return elements;
	}

	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}

	public List<Garanties> getListegaranties() {
		return listegaranties;
	}

	public void setListegaranties(List<Garanties> listegaranties) {
		this.listegaranties = listegaranties;
	}

	public List<Garanties> getListeGarantiesSelect() {
		return listeGarantiesSelect;
	}

	public void setListeGarantiesSelect(List<Garanties> listeGarantiesSelect) {
		this.listeGarantiesSelect = listeGarantiesSelect;
	}

	public int getDurreeGarantie() {
		return durreeGarantie;
	}

	public void setDurreeGarantie(int durreeGarantie) {
		this.durreeGarantie = durreeGarantie;
	}

	public java.math.BigDecimal getPrimeNette() {
		return primeNette;
	}

	public void setPrimeNette(java.math.BigDecimal primeNette) {
		this.primeNette = primeNette;
	}

	public String getCodeRisque() {
		return codeRisque;
	}
	public void setCodeRisque(String codeRisque) {
		this.codeRisque = codeRisque;
	}
	public String getOptionGarantie() {
		return optionGarantie;
	}
	public void setOptionGarantie(String optionGarantie) {
		this.optionGarantie = optionGarantie;
	}

	public java.math.BigDecimal getPrimeProrata() {
		return primeProrata;
	}
	public void setPrimeProrata(java.math.BigDecimal primeProrata) {
		this.primeProrata = primeProrata;
	}


	public java.math.BigDecimal getReductionAutres() {
		return reductionAutres;
	}


	public void setReductionAutres(java.math.BigDecimal reductionAutres) {
		this.reductionAutres = reductionAutres;
	}


	public java.math.BigDecimal getReductionFlote() {
		return reductionFlote;
	}


	public void setReductionFlote(java.math.BigDecimal reductionFlote) {
		this.reductionFlote = reductionFlote;
	}


	public java.math.BigDecimal getReductionPermis() {
		return reductionPermis;
	}


	public void setReductionPermis(java.math.BigDecimal reductionPermis) {
		this.reductionPermis = reductionPermis;
	}


	public java.math.BigDecimal getTotalReductions() {
		return totalReductions;
	}


	public void setTotalReductions(java.math.BigDecimal totalReductions) {
		this.totalReductions = totalReductions;
	}
	
	public java.math.BigDecimal getSurprimes() {
		return surprimes;
	}


	public void setSurprimes(java.math.BigDecimal surprimes) {
		this.surprimes = surprimes;
	}


	public java.math.BigDecimal getAccessoires() {
		return accessoires;
	}

	public void setAccessoires(java.math.BigDecimal accessoires) {
		this.accessoires = accessoires;
	}


	public java.math.BigDecimal getBonus() {
		return bonus;
	}


	public void setBonus(java.math.BigDecimal bonus) {
		this.bonus = bonus;
	}


	public java.math.BigDecimal getMalus() {
		return malus;
	}


	public void setMalus(java.math.BigDecimal malus) {
		this.malus = malus;
	}


	public java.math.BigDecimal getConseiller() {
		return conseiller;
	}


	public void setConseiller(java.math.BigDecimal conseiller) {
		this.conseiller = conseiller;
	}


	public java.math.BigDecimal getGestionnaire() {
		return gestionnaire;
	}


	public void setGestionnaire(java.math.BigDecimal gestionnaire) {
		this.gestionnaire = gestionnaire;
	}


	public java.math.BigDecimal getCoassurance() {
		return coassurance;
	}


	public void setCoassurance(java.math.BigDecimal coassurance) {
		this.coassurance = coassurance;
	}


	public java.math.BigDecimal getIntermediaire() {
		return intermediaire;
	}


	public void setIntermediaire(java.math.BigDecimal intermediaire) {
		this.intermediaire = intermediaire;
	}


	public java.math.BigDecimal getReductions() {
		return reductions;
	}


	public void setReductions(java.math.BigDecimal reductions) {
		this.reductions = reductions;
	}
	
}
