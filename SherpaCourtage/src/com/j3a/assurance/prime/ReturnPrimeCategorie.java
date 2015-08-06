package com.j3a.assurance.prime;

import com.j3a.assurance.prime.categorie.PrimeCategorie;
import com.j3a.assurance.prime.categorie.PrimeCategorieInterface;

public class ReturnPrimeCategorie {

	public ReturnPrimeCategorie() {
	
	}
public PrimeCategorie returnPrimecategorie(PrimeCategorieInterface prime){
	 PrimeCategorie primeCategorie = new PrimeCategorie();
	 primeCategorie.setDefenseRecours(prime.getDefenseRecours());
	 primeCategorie.setDefenseRecoursDommage(prime.getDefenseRecoursDommage());
	
	 primeCategorie.setBrisGlaceRC(prime.getBrisGlaceRC());
	 primeCategorie.setBrisGlaceRNC1(prime.getBrisGlaceRNC1());
	 primeCategorie.setBrisGlaceRNC2(prime.getBrisGlaceRNC2());
	 primeCategorie.setSecuriteRoutiere1(prime.getSecuriteRoutiere1());
	 primeCategorie.setSecuriteRoutiere2(prime.getSecuriteRoutiere2());
	 primeCategorie.setSecuriteRoutiere3(prime.getSecuriteRoutiere3());
	 primeCategorie.setIndividuelChauf1(prime.getIndividuelChauf1());
	 primeCategorie.setIndividuelChauf2(prime.getIndividuelChauf2());
	 primeCategorie.setIndividuelChauf3(prime.getIndividuelChauf3());
return primeCategorie;
}
}
