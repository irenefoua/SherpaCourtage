package com.j3a.assurance.prime;

import com.j3a.assurance.prime.categorie.PrimeCategorie1;
import com.j3a.assurance.prime.categorie.PrimeCategorie10;
import com.j3a.assurance.prime.categorie.PrimeCategorie11;
import com.j3a.assurance.prime.categorie.PrimeCategorie12;
import com.j3a.assurance.prime.categorie.PrimeCategorie2;
import com.j3a.assurance.prime.categorie.PrimeCategorie3;
import com.j3a.assurance.prime.categorie.PrimeCategorie4;
import com.j3a.assurance.prime.categorie.PrimeCategorie5;
import com.j3a.assurance.prime.categorie.PrimeCategorie6;
import com.j3a.assurance.prime.categorie.PrimeCategorie7;
import com.j3a.assurance.prime.categorie.PrimeCategorie8;
import com.j3a.assurance.prime.categorie.PrimeCategorie9;
import com.j3a.assurance.prime.categorie.PrimeCategorieInterface;

public class CalculPrimeGlobale {

	private String categorie;

	/***************** calcul la prime en fonction de la cat�gorie *********************/
	public PrimeCategorieInterface primeGlobale() {

		if (categorie.equals("TARIF1")) {
			PrimeCategorie1 primeCat1 = new PrimeCategorie1();
			return primeCat1;
		}
		if (categorie.equals("TARIF2")) {
			PrimeCategorie2 primeCat2 = new PrimeCategorie2();
			return primeCat2;
		}
		if (categorie.equals("TARIF3")) {
			PrimeCategorie3 primeCat3 = new PrimeCategorie3();
			return primeCat3;
		}
		if (categorie.equals("TARIF4")) {
			PrimeCategorie4 primeCat4 = new PrimeCategorie4();
			return primeCat4;
		}
		if (categorie.equals("TARIF5")) {
			PrimeCategorie5 primeCat5 = new PrimeCategorie5();
			return primeCat5;
		}
		if (categorie.equals("TARIF6")) {
			PrimeCategorie6 primeCat6 = new PrimeCategorie6();
			return primeCat6;
		}
		if (categorie.equals("TARIF7")) {
			PrimeCategorie7 primeCat7 = new PrimeCategorie7();
			return primeCat7;
		}
		if (categorie.equals("TARIF8")) {
			PrimeCategorie8 primeCat8 = new PrimeCategorie8();
			return primeCat8;
		}
		if (categorie.equals("TARIF9")) {
			PrimeCategorie9 primeCat9 = new PrimeCategorie9();
			return primeCat9;
		}
		if (categorie.equals("TARIF10")) {
			PrimeCategorie10 primeCat10 = new PrimeCategorie10();
			return primeCat10;
		}
		if (categorie.equals("TARIF11")) {
			PrimeCategorie11 primeCat11 = new PrimeCategorie11();
			return primeCat11;
		}
		if (categorie.equals("TARIF12")) {
			PrimeCategorie12 primeCat12 = new PrimeCategorie12();
			return primeCat12;
		}
		return new PrimeCategorie1();// return la prime de la categorie 1 par
										// defaut
	}

	/*************** getters et setters **************/
	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
}
