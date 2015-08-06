package com.j3a.assurance.managedBean.Auto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.j3a.assurance.managedBean.ManagedGarantie;
import com.j3a.assurance.model.Garantie;
import com.j3a.assurance.prime.CalculPrimeGlobale;
import com.j3a.assurance.prime.CalculPrimeProrata;
import com.j3a.assurance.prime.ReturnPrimeCategorie;
import com.j3a.assurance.prime.categorie.PrimeCategorie;
import com.j3a.assurance.prime.categorie.PrimeCategorieInterface;
import com.j3a.assurance.utilitaires.GarantieTable;
import com.j3a.assurance.utilitaires.Garanties;
import com.j3a.assurance.utilitaires.VehiculeRow;

public class ManagedGarantieAuto extends ManagedGarantie {
	Logger logger = Logger.getLogger(ManagedGarantieAuto.class);
	private GarantieTable garantieTable = new GarantieTable();
	private double duree;

	private Boolean statuRed;
	private Boolean statuBonus;
	private Boolean statuMalus;
	private int chekDom = 0, chekSR, chekBDG;
	private String choix, vehiculeSelect, vuePrimes = "true",
			vuecalculPrime = "true", vueQuittance, statutList = "NO";
	private int valT = 0;
	private java.math.BigDecimal tauxBonus = BigDecimal.ZERO,
			tauxMalus = BigDecimal.ZERO, tauxRed = BigDecimal.ZERO,
			tauxFlotte = BigDecimal.ZERO, tauxpermis = BigDecimal.ZERO;
	private java.math.BigDecimal primeTotale = BigDecimal.ZERO, primeAnnuelle,
			primeNetAn, primeRC, totalRed = BigDecimal.ZERO;
	private List<String> elements;
	public List<Garanties> listeGroupGaranties = new ArrayList<Garanties>();
	public List<Garanties> listegarantieFiltre = new ArrayList<Garanties>();
	public List<Garanties> listegarantieAll = new ArrayList<Garanties>();

	public Garanties selectGarantieGroup;

	public String mouvement;
	public Garantie objetGarantie;
	private List<Garantie> garantiesChoisies;
	private List<Garantie> mesGarantiesList;
	public String monString;

	// déclaration & appels par Benhanna
	private String id;
	private String libelleGarantie;
	private List<Garantie> GarantiesList;

	private UIComponent buttonadd;
	private UIComponent buttonupdate;
	private UIComponent buttondelete;
	private PrimeCategorie primeCategorie = new PrimeCategorie();

	public ManagedGarantieAuto() {
	}

	

	public List<Garanties> affichegarantiesAuto(VehiculeRow vehiculeRow) {
		getListeGroupGaranties().clear();
		getListegaranties().clear();
		System.out.println("INSIDE AFFICHE GAR :::::::::*************");
		primeTotale = BigDecimal.ZERO;
		primeAnnuelle = BigDecimal.ZERO;
		cleanChamps();
		// reductionG = 0;
		// bonus = 0;
		// malus = 0;
		vuePrimes = "false";

		getListegaranties().addAll(calculGarantieAuto(vehiculeRow));
		Garanties garantie1 = new Garanties();
		garantie1.setCodeGarantie("101");
		garantie1.setLibelleGarantie("RESPONSABILITE CIVILE");
		garantie1.setnOrdre(1);
		getListeGroupGaranties().add(garantie1);

		Garanties garantie2 = new Garanties();
		garantie2.setCodeGarantie("104");
		garantie2.setLibelleGarantie("DEFENSE ET RECOURS");
		garantie2.setnOrdre(2);
		getListeGroupGaranties().add(garantie2);

		Garanties garantie3 = new Garanties();
		garantie3.setCodeGarantie("106");
		garantie3.setLibelleGarantie("DOMMAGES");
		garantie3.setnOrdre(3);
		getListeGroupGaranties().add(garantie3);

		Garanties garantie4 = new Garanties();
		garantie4.setCodeGarantie("110");
		garantie4.setnOrdre(4);
		garantie4.setLibelleGarantie("INCENDIES");
		getListeGroupGaranties().add(garantie4);

		Garanties garantie5 = new Garanties();
		garantie5.setCodeGarantie("111");
		garantie5.setnOrdre(5);
		garantie5.setLibelleGarantie("VOLS");
		getListeGroupGaranties().add(garantie5);

		Garanties garantie6 = new Garanties();
		garantie6.setCodeGarantie("114");
		garantie6.setnOrdre(6);
		garantie6.setLibelleGarantie("SECURITE ROUTIERE");
		getListeGroupGaranties().add(garantie6);

		calculPrimeGroupe();
		// on se pointe sur la 1ier liste
		setSelectGarantieGroup(garantie1);
		getListegarantieFiltre().clear();
		for (Garanties G : getListegaranties()) {
			if (G.getCategorieGarantie().equalsIgnoreCase("RC")) {
				getListegarantieFiltre().add(G);
			}
		}

		return getListegaranties();
	}

	public List<Garanties> calculGarantieAuto(VehiculeRow vehiculeRow) {

		setPrimeCategorie(new PrimeCategorie());
		CalculPrimeGlobale primeGlobale = new CalculPrimeGlobale();

		// prévoir une méthode de vérification de la sous catégorie à plus tard

		primeGlobale.setCategorie(vehiculeRow.getSouCatVehi().getTarif().getCodeTarif());
		primeGlobale.primeGlobale();
		PrimeCategorieInterface prime = primeGlobale.primeGlobale();

		CalculPrimeProrata prorata = new CalculPrimeProrata();
		
		prime.setCategorie(vehiculeRow.getSouCatVehi().getTarif().getCodeTarif());
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
		setPrimeCategorie(r.returnPrimecategorie(prime));
		System.out.println("valeur de primecategorie"
				+ getPrimeCategorie().getBrisGlaceRC());
		List<Garanties> garantieList = new ArrayList<Garanties>();
		garantieList.clear();
		List listObject = getObjectService().getListGarantieByRisque(
				getCodeRisque());
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
			/*
			 * if (garantie.getCodeGarantie().equals("115") == true) { BigDecimal sr =
			 * prorata.primeProrata(duree, prime.getSecuriteRoutiere1());
			 * BigDecimal v1 = prime.getSecuriteRoutiere1();
			 * garantie.setPrimesAnnuelle(v1);
			 * garantie.setPrimesNetteAnnuelle(v1);
			 * garantie.setPrimesProrata(sr);
			 * 
			 * } if (garantie.getCodeGarantie().equals("116") == true) { BigDecimal sr =
			 * prorata.primeProrata(duree, prime.getSecuriteRoutiere1());
			 * BigDecimal v1 = prime.getSecuriteRoutiere1();
			 * garantie.setPrimesAnnuelle(v1);
			 * garantie.setPrimesNetteAnnuelle(v1);
			 * garantie.setPrimesProrata(sr);
			 * 
			 * }
			 */

			System.out.println("ssssssssssssssssssssssssssssssssssss"
					+ garantie.getCategorieGarantie());
			garantieList.add(garantie);
		}

		return garantieList;
	}

	public void onEditRow(RowEditEvent event) {

		// gestion de RC et Defence Recours Obligatoire

		if (((Garanties) event.getObject()).getCodeGarantie().equalsIgnoreCase("101")
				|| ((Garanties) event.getObject()).getCodeGarantie().equalsIgnoreCase(
						"104")) {
			((Garanties) event.getObject()).setChoix(true);
		}

		setVuePrimes("false");
		java.math.BigDecimal accessoire = getAccessoires();
		setAccessoires(accessoire);
		BigDecimal tauxred = ((Garanties) event.getObject()).getTauxRed();
		BigDecimal tauxbonus = ((Garanties) event.getObject()).getBonus();
		BigDecimal tauxmalus = ((Garanties) event.getObject()).getMalus();

		java.math.BigDecimal primeAn = BigDecimal.ZERO, primeProrata = BigDecimal.ZERO, reduction = BigDecimal.ZERO, reductionProrata = BigDecimal.ZERO;
		primeAn = ((Garanties) event.getObject()).getPrimesAnnuelle();
		primeProrata = ((Garanties) event.getObject()).getPrimesProrata();

		reduction = primeAn
				.multiply(tauxred.add(tauxbonus).subtract(tauxmalus)).divide(
						new BigDecimal(100));
		reductionProrata = primeProrata.multiply(
				tauxred.add(tauxbonus).subtract(tauxmalus)).divide(
				new BigDecimal(100));

		primeAn = primeAn.subtract(reduction);
		primeProrata = primeProrata.subtract(reductionProrata);
		((Garanties) event.getObject()).setPrimesNetteAnnuelle(primeAn);
		((Garanties) event.getObject()).setPrimesProrata(primeProrata);
		((Garanties) event.getObject()).setReductions(reductionProrata);
		System.out.println("ssssssssssssssssssssssssssssssssssss"
				+ reductionProrata);

		calculPrimeGroupe();
	}

	public void calculPrimeGroupe() {

		double primeRC = 0, primeDR = 0, primeSR = 0, primeBDG = 0, primeDOM = 0, primeINC = 0, primeGRA = 0, primeVOL = 0, primeIMMO = 0, primeTotal = 0;
		for (Garanties G : getListegaranties()) {
			if (G.getCategorieGarantie().equalsIgnoreCase("RC")
					&& G.getChoix() == true) {
				primeRC = primeRC + G.getPrimesProrata().doubleValue();
			}

			if (G.getCategorieGarantie().equalsIgnoreCase("DR")
					&& G.getChoix() == true) {
				primeDR = primeDR + G.getPrimesProrata().doubleValue();
			}

			if (G.getCategorieGarantie().equalsIgnoreCase("SR")
					&& G.getChoix() == true) {
				primeSR = primeSR + G.getPrimesProrata().doubleValue();
			}

			if (G.getCategorieGarantie().equalsIgnoreCase("IMMO")
					&& G.getChoix() == true) {
				primeIMMO = primeIMMO + G.getPrimesProrata().doubleValue();
			}

			if (G.getCategorieGarantie().equalsIgnoreCase("GRA")
					&& G.getChoix() == true) {
				primeGRA = primeGRA + G.getPrimesProrata().doubleValue();
			}

			if (G.getCategorieGarantie().equalsIgnoreCase("DOM")
					&& G.getChoix() == true) {
				primeDOM = primeDOM + G.getPrimesProrata().doubleValue();
			}

			if (G.getCategorieGarantie().equalsIgnoreCase("BDG")
					&& G.getChoix() == true) {
				primeBDG = primeBDG + G.getPrimesProrata().doubleValue();
			}

			if (G.getCategorieGarantie().equalsIgnoreCase("VOL")
					&& G.getChoix() == true) {
				primeVOL = primeVOL + G.getPrimesProrata().doubleValue();
			}

			if (G.getCategorieGarantie().equalsIgnoreCase("INC")
					&& G.getChoix() == true) {
				primeINC = primeINC + G.getPrimesProrata().doubleValue();
			}
		}

		primeTotal = primeRC + primeDR + primeSR + primeBDG + primeDOM
				+ primeINC + primeGRA + primeVOL + primeIMMO;
		primeTotale = BigDecimal.valueOf(primeTotal);
		for (Garanties G : getListeGroupGaranties()) {
			if (G.getnOrdre() == 1) {
				G.setPrimesProrata(BigDecimal.valueOf(primeRC));
			}

			if (G.getnOrdre() == 2) {
				G.setPrimesProrata(BigDecimal.valueOf(primeDR));
			}
			if (G.getnOrdre() == 3) {
				G.setPrimesProrata(BigDecimal.valueOf(primeDOM));
			}
			if (G.getnOrdre() == 4) {
				G.setPrimesProrata(BigDecimal.valueOf(primeINC));
			}
			if (G.getnOrdre() == 5) {
				G.setPrimesProrata(BigDecimal.valueOf(primeVOL));
			}
			if (G.getnOrdre() == 6) {
				G.setPrimesProrata(BigDecimal.valueOf(primeSR));
			}
			if (G.getnOrdre() == 7) {
				G.setPrimesProrata(BigDecimal.valueOf(primeBDG));
			}
			if (G.getnOrdre() == 8) {
				G.setPrimesProrata(BigDecimal.valueOf(primeVOL));
			}
			if (G.getnOrdre() == 9) {
				G.setPrimesProrata(BigDecimal.valueOf(primeINC));
			}

		}
	}

	public void validerGarantie() {
		setVuePrimes("true");
		List<Garanties> garListe = new ArrayList<Garanties>();
		for (Garanties g : getListegaranties()) {
			if (g.getChoix() == true) {
				garListe.add(g);
			}
		}
		getListeGarantiesSelect().clear();
		getListeGarantiesSelect().addAll(garListe);

		BigDecimal primeTotal = BigDecimal.ZERO;
		setPrimeNette(primeTotal);
		System.out.println("Garantie valider taille = "
				+ getListeGarantiesSelect().size());
		getAccessoires();
		System.out
				.println("voir l'accessoire Accessoire = " + getAccessoires());
		calculAcc();
	}

	public void editerGarantieAuto() {

	}

	public void onRowSelectGroupGarantie(SelectEvent event) {
		getListegarantieFiltre().clear();
		if (((Garanties) event.getObject()).getnOrdre() == 1) {
			System.out
					.println("voir la taille des garanties ds ListeGaranties = "
							+ getListegaranties().size());
			System.out.println("voir la Catégorie des Garanties = "
					+ getListegaranties().get(0).getCategorieGarantie());
			for (Garanties G : getListegaranties()) {
				if (G.getCategorieGarantie().equalsIgnoreCase("RC")) {
					getListegarantieFiltre().add(G);
				}
			}

		}

		if (((Garanties) event.getObject()).getnOrdre() == 2) {

			for (Garanties G : getListegaranties()) {
				if (G.getCategorieGarantie().equalsIgnoreCase("DR")) {
					getListegarantieFiltre().add(G);
				}
			}

		}

		if (((Garanties) event.getObject()).getnOrdre() == 3) {

			for (Garanties G : getListegaranties()) {
				if (G.getCategorieGarantie().equalsIgnoreCase("DOM")) {
					getListegarantieFiltre().add(G);
				}
			}

		}

		if (((Garanties) event.getObject()).getnOrdre() == 4) {

			for (Garanties G : getListegaranties()) {
				if (G.getCategorieGarantie().equalsIgnoreCase("INC")) {
					getListegarantieFiltre().add(G);
				}
			}

		}

		if (((Garanties) event.getObject()).getnOrdre() == 5) {

			for (Garanties G : getListegaranties()) {
				if (G.getCategorieGarantie().equalsIgnoreCase("VOL")) {
					getListegarantieFiltre().add(G);
				}
			}

		}

		if (((Garanties) event.getObject()).getnOrdre() == 6) {

			for (Garanties G : getListegaranties()) {
				if (G.getCategorieGarantie().equalsIgnoreCase("SR")) {
					getListegarantieFiltre().add(G);
				}
			}

		}

	}

	public void calculAcc() {
		java.math.BigDecimal prime = BigDecimal.ZERO;
		java.math.BigDecimal access = getAccessoires();
		java.math.BigDecimal cons = BigDecimal.ZERO;
		java.math.BigDecimal ges = BigDecimal.ZERO;
		java.math.BigDecimal inter = BigDecimal.ZERO;
		java.math.BigDecimal coass = BigDecimal.ZERO;
		java.math.BigDecimal red = BigDecimal.ZERO;
		for (Garanties g : getListeGarantiesSelect()) {
			prime = prime.add(g.getPrimesProrata());
			cons = cons.add(g.getComCons());
			ges = ges.add(g.getComGes());
			inter = inter.add(g.getComInter());
			coass = coass.add(g.getComCoass());
			red = red.add(g.getReductions());

		}
		setPrimeNette(prime);
		/*setConseiller(cons);
		setGestionnaire(ges);
		setIntermediaire(inter);
		setCoassurance(coass);*/
		setAccessoires(access);
		setTotalReductions(red);

	}

	// ici on fè les choix de Garantie et on passe à un autre vehicule
	public void validerPrime() {

		// code de récupération de l' id garantie choisie après vérification
		int val = 0;
		vuePrimes = "true";
		vuecalculPrime = "true";
		String key = null;

		// -----ici on retir ds ListegarantieparV et ds la carte grise------//

		/*
		 * GarantieChoisie garantiechoix = new GarantieChoisie();
		 * garantiechoix.setCodeVehicule(vehiculeRow.getVehi());
		 * listGarantieparVehicule.clear();
		 * 
		 * garantieGarantieChoisieList = new
		 * ArrayList<GarantieGarantieChoisie>(); garantieGarantieChoisiePKList =
		 * new ArrayList<GarantieGarantieChoisiePK>(); if (listegarantie.size()
		 * == 0) {
		 * 
		 * } else { // code pour ajouter les garanties au choix de garantie ds
		 * la table // GarantieGarantieChoisie for (int i = 0; i <
		 * listegarantie.size(); i++) {
		 * 
		 * 
		 * Garantie garantie = new Garantie();
		 * 
		 * garantie.setId(listegarantie.get(i).getCodeGarantie());
		 * 
		 * GarantieGarantieChoisie GarantieGarantieChoisie = new
		 * GarantieGarantieChoisie(); GarantieGarantieChoisiePK
		 * GarantieGarantieChoisiePK = new GarantieGarantieChoisiePK();
		 * GarantieGarantieChoisiePK.setCodeGarantie(garantie);
		 * GarantieGarantieChoisiePK.setCodeGarantieChoisie(garantiechoix);
		 * 
		 * 
		 * GarantieGarantieChoisie.setId(GarantieGarantieChoisiePK);
		 * 
		 * // A voir ici pour les primes
		 * 
		 * BigDecimal d = listegarantie.get(i).getPrimeAnnuelle(); BigDecimal bd
		 * = d; GarantieGarantieChoisie.setPrimeAnnuelle(bd);
		 * 
		 * BigDecimal d2 = listegarantie.get(i).getPrime(); BigDecimal bd2 = d2;
		 * GarantieGarantieChoisie.setPrimeNetteProrata(bd2);
		 * 
		 * 
		 * 
		 * GarantieGarantieChoisie.setPrimeNetteAnnuelle(
		 * listegarantie.get(i).getPrimeNetAn());
		 * 
		 * GarantieGarantieChoisie.setMontantReduction(
		 * listegarantie.get(i).getMontantRed());
		 * 
		 * garantieGarantieChoisieList.add(i, GarantieGarantieChoisie);
		 * garantieGarantieChoisiePKList.add(i, GarantieGarantieChoisiePK);
		 * 
		 * System.out.println(
		 * "--------  comportement de GarantieGarantieChoisie ------------"); }
		 * 
		 * 
		 * ListGarantieparVehicule garantieparvehi = new
		 * ListGarantieparVehicule();
		 * garantieparvehi.setGarantieGarantieChoisieList
		 * (garantieGarantieChoisieList);
		 * garantieparvehi.setGarantieGarantieChoisiePKList
		 * (garantieGarantieChoisiePKList);
		 * 
		 * garantiechoix.setLibelleGarantieChosie("");
		 * garantiechoix.setPrimeAnnuelle(primeAnnuelle);
		 * garantiechoix.setPrimeNetteAnnuelle(primeNetAn);
		 * garantiechoix.setPrimeNetteProrata(Prime);
		 * garantiechoix.setMontantReduction(getTotalRed());
		 * garantiechoix.setBonus(getTauxBonus());
		 * garantiechoix.setMalus(getTauxMalus());
		 * garantiechoix.setAutre(getTauxRed());
		 * garantiechoix.setReductionCommercial(0.0);
		 * garantiechoix.setReductionPermis(0);
		 * garantiechoix.setReductionSocioProf(0);
		 * garantieparvehi.setGarantieChoisie(garantiechoix); //
		 * garantieparvehi.setPrime(Prime);
		 * garantieparvehi.setPrimeAnnuelle(primeAnnuelle);
		 * garantieparvehi.setPrimeNetteAnnuelle(primeNetAn);
		 * garantieparvehi.setPrimeRC(primeRC);
		 * garantieparvehi.setTotalRed(getTotalRed());
		 * garantieparvehi.setTauxBonus(getTauxBonus());
		 * garantieparvehi.setTauxMalus(getTauxMalus());
		 * garantieparvehi.setTauxFlotte(getTauxFlotte());
		 * garantieparvehi.setTauxPermis(getTauxpermis());
		 * 
		 * 
		 * listGarantieparVehicule.add(garantieparvehi);
		 * 
		 * 
		 * // code pour retirer le vehicule dans la liste des VehiculeRow
		 * vehiculeList.remove(vehiculeRow);
		 */

	}

	public String validerGarantieAuto() {

		return "garantieOK";

	}

	public String addGarantieChoisie() {
		/*
		 * Date date = new Date(); date = Calendar.getInstance().getTime();
		 * 
		 * if (listGarantieparVehicule.size() == 0) {
		 * 
		 * } else { for (int i = 0; i < listGarantieparVehicule.size(); i++) {
		 * GarantieChoisie garchoi = new GarantieChoisie(); garchoi =
		 * listGarantieparVehicule.get(i).getGarantieChoisie();
		 * garchoi.setDateGarantieChoisie(date); try {
		 * getObjectService().addObject(garchoi); } catch (Exception e) {
		 * e.printStackTrace(); }
		 * 
		 * // enreg de GarantieGarantieChoisie for (int j = 0; j <
		 * listGarantieparVehicule.get(i)
		 * .getGarantieGarantieChoisieList().size(); j++) {
		 * 
		 * GarantieGarantieChoisie GarantieGarantieChoisie = new
		 * GarantieGarantieChoisie(); GarantieGarantieChoisie =
		 * listGarantieparVehicule.get(i)
		 * .getGarantieGarantieChoisieList().get(j);
		 * GarantieGarantieChoisie.setId(listGarantieparVehicule
		 * .get(i).getGarantieGarantieChoisiePKList().get(j));
		 * GarantieGarantieChoisie.setDateGarantieGarantieChoisie(date); try {
		 * getObjectService().addObject(GarantieGarantieChoisie); } catch
		 * (Exception e) { e.printStackTrace(); }
		 * 
		 * }
		 * 
		 * } }
		 */
		return "choix";
	}

	public String vueQuittance() {
		return null;
	}

	public void checkbox() {
		/*
		 * Prime = BigDecimal.ZERO; primeAnnuelle = BigDecimal.ZERO; primeNetAn=
		 * BigDecimal.ZERO; garantieTable.setStatutRC(true);
		 * garantieTable.setStatutDR(true);
		 * 
		 * listegarantie.clear();
		 * 
		 * garantieTable.setTauxRedRC(0); garantieTable.setTauxRedDR(tauxRed);
		 * garantieTable.setTauxRedSROpt1(tauxRed);
		 * garantieTable.setTauxRedSROpt2(tauxRed);
		 * garantieTable.setTauxRedSROpt3(tauxRed);
		 * garantieTable.setTauxRedIndChaufOpt1(tauxRed);
		 * garantieTable.setTauxRedIndChaufOpt2(tauxRed);
		 * garantieTable.setTauxRedIndChaufOpt3(tauxRed);
		 * garantieTable.setTauxRedIndChaufSp(tauxRed);
		 * garantieTable.setTauxRedDommagesCol(tauxRed);
		 * garantieTable.setTauxRedDommagesTA(tauxRed);
		 * garantieTable.setTauxRedBDGOpt1(tauxRed);
		 * garantieTable.setTauxRedBDGOpt2(tauxRed);
		 * garantieTable.setTauxRedBDGOpt3(tauxRed);
		 * garantieTable.setTauxRedRA(tauxRed);
		 * garantieTable.setTauxRedVol(tauxRed);
		 * garantieTable.setTauxRedVolAcc(tauxRed);
		 * garantieTable.setTauxRedVandalisme(tauxRed);
		 * garantieTable.setTauxRedIncendie(tauxRed);
		 * garantieTable.setTauxRedImmob(tauxRed);
		 * 
		 * garantieTable.setTauxBonusRC(tauxBonus);
		 * garantieTable.setTauxBonusDR(tauxBonus);
		 * garantieTable.setTauxBonusSROpt1(tauxBonus);
		 * garantieTable.setTauxBonusSROpt2(tauxBonus);
		 * garantieTable.setTauxBonusSROpt3(tauxBonus);
		 * garantieTable.setTauxBonusIndChaufOpt1(tauxBonus);
		 * garantieTable.setTauxBonusIndChaufOpt2(tauxBonus);
		 * garantieTable.setTauxBonusIndChaufOpt3(tauxBonus);
		 * garantieTable.setTauxBonusIndChaufSp(tauxBonus);
		 * garantieTable.setTauxBonusDommagesCol(tauxBonus);
		 * garantieTable.setTauxBonusDommagesTA(tauxBonus);
		 * garantieTable.setTauxBonusBDGOpt1(tauxBonus);
		 * garantieTable.setTauxBonusBDGOpt2(tauxBonus);
		 * garantieTable.setTauxBonusBDGOpt3(tauxBonus);
		 * garantieTable.setTauxBonusRA(tauxBonus);
		 * garantieTable.setTauxBonusVol(tauxBonus);
		 * garantieTable.setTauxBonusVolAcc(tauxBonus);
		 * garantieTable.setTauxBonusVandalisme(tauxBonus);
		 * garantieTable.setTauxBonusIncendie(tauxBonus);
		 * garantieTable.setTauxBonusImmob(tauxBonus);
		 * 
		 * garantieTable.setTauxMalusRC(tauxMalus);
		 * garantieTable.setTauxMalusDR(tauxMalus);
		 * garantieTable.setTauxMalusSROpt1(tauxMalus);
		 * garantieTable.setTauxMalusSROpt2(tauxMalus);
		 * garantieTable.setTauxMalusSROpt3(tauxMalus);
		 * garantieTable.setTauxMalusIndChaufOpt1(tauxMalus);
		 * garantieTable.setTauxMalusIndChaufOpt2(tauxMalus);
		 * garantieTable.setTauxMalusIndChaufOpt3(tauxMalus);
		 * garantieTable.setTauxMalusIndChaufSp(tauxMalus);
		 * garantieTable.setTauxMalusDommagesCol(tauxMalus);
		 * garantieTable.setTauxMalusDommagesTA(tauxMalus);
		 * garantieTable.setTauxMalusBDGOpt1(tauxMalus);
		 * garantieTable.setTauxMalusBDGOpt2(tauxMalus);
		 * garantieTable.setTauxMalusBDGOpt3(tauxMalus);
		 * garantieTable.setTauxMalusRA(tauxMalus);
		 * garantieTable.setTauxMalusVol(tauxMalus);
		 * garantieTable.setTauxMalusVolAcc(tauxMalus);
		 * garantieTable.setTauxMalusVandalisme(tauxMalus);
		 * garantieTable.setTauxMalusIncendie(tauxMalus);
		 * garantieTable.setTauxMalusImmob(tauxMalus);
		 * 
		 * garantieTable.setTauxFlotteRC(tauxFlotte);
		 * garantieTable.setTauxFlotteDR(tauxFlotte);
		 * garantieTable.setTauxFlotteSROpt1(tauxFlotte);
		 * garantieTable.setTauxFlotteSROpt2(tauxFlotte);
		 * garantieTable.setTauxFlotteSROpt3(tauxFlotte);
		 * garantieTable.setTauxFlotteIndChaufOpt1(tauxFlotte);
		 * garantieTable.setTauxFlotteIndChaufOpt2(tauxFlotte);
		 * garantieTable.setTauxFlotteIndChaufOpt3(tauxFlotte);
		 * garantieTable.setTauxFlotteIndChaufSp(tauxFlotte);
		 * garantieTable.setTauxFlotteDommagesCol(tauxFlotte);
		 * garantieTable.setTauxFlotteDommagesTA(tauxFlotte);
		 * garantieTable.setTauxFlotteBDGOpt1(tauxFlotte);
		 * garantieTable.setTauxFlotteBDGOpt2(tauxFlotte);
		 * garantieTable.setTauxFlotteBDGOpt3(tauxFlotte);
		 * garantieTable.setTauxFlotteRA(tauxFlotte);
		 * garantieTable.setTauxFlotteVol(tauxFlotte);
		 * garantieTable.setTauxFlotteVolAcc(tauxFlotte);
		 * garantieTable.setTauxFlotteVandalisme(tauxFlotte);
		 * garantieTable.setTauxFlotteIncendie(tauxFlotte);
		 * garantieTable.setTauxFlotteImmob(tauxFlotte);
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * CalculPrime prorata = new CalculPrime();
		 * 
		 * for(int j= 0;j<selectGaranties.size();j++){
		 * if(selectGaranties.get(j).getCodeGarantie().equalsIgnoreCase("GA001")){
		 * selectGaranties.get(j).setReduction(BigDecimal.ZERO);
		 * selectGaranties.get(j).setBonus(tauxBonus);
		 * selectGaranties.get(j).setMalus(tauxMalus);
		 * selectGaranties.get(j).setMalus(tauxFlotte);
		 * 
		 * BigDecimal p = selectGaranties.get(j).getPrimeAnnuelle(); int tb=
		 * tauxBonus, tm=tauxMalus, tf=tauxFlotte;
		 * 
		 * BigDecimal red= p.multiply(BigDecimal.valueOf((tb + tf - tm)/100));
		 * 
		 * BigDecimal pr = p.subtract(red);
		 * 
		 * selectGaranties.get(j).setPrimeNetAn((java.math.BigDecimal)pr);
		 * 
		 * selectGaranties.get(j).setPrime((java.math.BigDecimal)prorata.Prime(duree
		 * , pr));
		 * selectGaranties.get(j).setPrimeRC(selectGaranties.get(j).getPrime());
		 * selectGaranties.get(j).setMontantRed(red);
		 * 
		 * 
		 * 
		 * }else{
		 * 
		 * 
		 * selectGaranties.get(j).setReduction(tauxRed);
		 * selectGaranties.get(j).setBonus(tauxBonus);
		 * selectGaranties.get(j).setMalus(tauxMalus);
		 * selectGaranties.get(j).setMalus(tauxFlotte);
		 * 
		 * BigDecimal p = selectGaranties.get(j).getPrimeAnnuelle(); int tb=
		 * tauxBonus, tm=tauxMalus, tf=tauxFlotte, tr = tauxRed;
		 * 
		 * BigDecimal red= p.multiply(BigDecimal.valueOf((tr + tb +tf -
		 * tm)/100));
		 * 
		 * BigDecimal pr = p.subtract(red);
		 * 
		 * selectGaranties.get(j).setPrimeNetAn((java.math.BigDecimal)pr);
		 * 
		 * selectGaranties.get(j).setPrime((java.math.BigDecimal)prorata.Prime(duree
		 * , pr));
		 * selectGaranties.get(j).setMontantRed((java.math.BigDecimal)red);
		 * 
		 * 
		 * } }
		 * 
		 * 
		 * 
		 * 
		 * for (int i = 0; i < selectGaranties.size(); i++) {
		 * 
		 * if (listegarantie.size() == 0) {
		 * 
		 * if (selectGaranties.get(i).getCodeGarantie().equals("GA001")) {
		 * garantieTable.setMontantRedRC
		 * (selectGaranties.get(i).getMontantRed());
		 * 
		 * garantieTable.setPrimeNetAnRC(selectGaranties.get(i).getPrimeNetAn());
		 * garantieTable.setPrimeRC(selectGaranties.get(i).getPrime()) ;
		 * 
		 * listegarantie.add(selectGaranties.get(i)); }
		 * 
		 * } if (listegarantie.size() == 1) { if
		 * (selectGaranties.get(i).getCodeGarantie().equals("GA002")) {
		 * garantieTable.setMontantRedDR
		 * (selectGaranties.get(i).getMontantRed());
		 * 
		 * garantieTable.setPrimeNetAnDR(selectGaranties.get(i).getPrimeNetAn());
		 * garantieTable.setPrimeDR(selectGaranties.get(i).getPrime()) ;
		 * 
		 * listegarantie.add(selectGaranties.get(i)); }
		 * 
		 * } if (selectGaranties.get(i).getCodeGarantie().equals("GA003")) { if
		 * (garantieTable.isStatutRA() == true) {
		 * 
		 * 
		 * listegarantie.add(selectGaranties.get(i));
		 * garantieTable.setPrimeAnRA(selectGaranties.get(i)
		 * .getPrimeAnnuelle()); garantieTable.setPrimeRA(selectGaranties.get(i)
		 * .getPrime());
		 * garantieTable.setMontantRedRA(selectGaranties.get(i).getMontantRed
		 * ());
		 * 
		 * } else {
		 * 
		 * listegarantie.remove(selectGaranties.get(i));
		 * garantieTable.setPrimeAnRA(BigDecimal.ZERO);
		 * garantieTable.setPrimeRA(BigDecimal.ZERO);
		 * garantieTable.setMontantRedRA(BigDecimal.ZERO); } } if
		 * (selectGaranties.get(i).getCodeGarantie().equals("GA004")) { if
		 * (garantieTable.isStatutDommagesCol() == true) {
		 * 
		 * 
		 * 
		 * listegarantie.add(selectGaranties.get(i));
		 * garantieTable.setPrimeAnDommagesCol(selectGaranties.get(i)
		 * .getPrimeAnnuelle());
		 * garantieTable.setPrimeDommagesCol(selectGaranties.get(i)
		 * .getPrime());
		 * garantieTable.setMontantRedDommagesCol(selectGaranties.get
		 * (i).getMontantRed());
		 * 
		 * } else {
		 * 
		 * listegarantie.remove(selectGaranties.get(i));
		 * garantieTable.setPrimeAnDommagesCol(BigDecimal.ZERO);
		 * garantieTable.setPrimeDommagesCol(BigDecimal.ZERO);
		 * garantieTable.setMontantRedDommagesCol(BigDecimal.ZERO); } }
		 * 
		 * if (selectGaranties.get(i).getCodeGarantie().equals("GA005")) { if
		 * (garantieTable.isStatutDommagesTA() == true) {
		 * 
		 * 
		 * 
		 * listegarantie.add(selectGaranties.get(i));
		 * garantieTable.setPrimeAnDommagesTA(selectGaranties.get(i)
		 * .getPrimeAnnuelle());
		 * garantieTable.setPrimeDommagesTA(selectGaranties.get(i) .getPrime());
		 * garantieTable
		 * .setMontantRedDommagesTA(selectGaranties.get(i).getMontantRed());
		 * 
		 * } else { chekDom = 0; listegarantie.remove(selectGaranties.get(i));
		 * garantieTable.setPrimeAnDommagesTA(BigDecimal.ZERO);
		 * garantieTable.setPrimeDommagesTA(BigDecimal.ZERO);
		 * garantieTable.setMontantRedDommagesTA(BigDecimal.ZERO); } }
		 * 
		 * if (selectGaranties.get(i).getCodeGarantie().equals("GA006")) { if
		 * (garantieTable.isStatutIncendie() == true) {
		 * 
		 * 
		 * listegarantie.add(selectGaranties.get(i));
		 * garantieTable.setPrimeAnIncendie(selectGaranties.get(i)
		 * .getPrimeAnnuelle());
		 * garantieTable.setPrimeIncendie(selectGaranties.get(i) .getPrime());
		 * garantieTable
		 * .setMontantRedIncendie(selectGaranties.get(i).getMontantRed());
		 * 
		 * } else {
		 * 
		 * listegarantie.remove(selectGaranties.get(i));
		 * garantieTable.setPrimeAnIncendie(BigDecimal.ZERO);
		 * garantieTable.setPrimeIncendie(BigDecimal.ZERO);
		 * garantieTable.setMontantRedIncendie(BigDecimal.ZERO); } } if
		 * (selectGaranties.get(i).getCodeGarantie().equals("GA007")) { if
		 * (garantieTable.isStatutImmob() == true) {
		 * 
		 * 
		 * listegarantie.add(selectGaranties.get(i));
		 * garantieTable.setPrimeAnImmob(selectGaranties.get(i)
		 * .getPrimeAnnuelle());
		 * garantieTable.setPrimeImmob(selectGaranties.get(i) .getPrime());
		 * garantieTable
		 * .setMontantRedImmob(selectGaranties.get(i).getMontantRed());
		 * 
		 * } else {
		 * 
		 * listegarantie.remove(selectGaranties.get(i));
		 * garantieTable.setPrimeAnImmob(BigDecimal.ZERO);
		 * garantieTable.setPrimeImmob(BigDecimal.ZERO);
		 * garantieTable.setMontantRedImmob(BigDecimal.ZERO); } } if
		 * (selectGaranties.get(i).getCodeGarantie().equals("GA008")) { if
		 * (garantieTable.isStatutBDGOpt1() == true) {
		 * 
		 * garantieTable.setStatutBDGOpt2(false);
		 * garantieTable.setStatutBDGOpt3(false);
		 * 
		 * listegarantie.add(selectGaranties.get(i));
		 * garantieTable.setPrimeAnBDGOpt1(selectGaranties.get(i)
		 * .getPrimeAnnuelle());
		 * garantieTable.setPrimeBDGOpt1(selectGaranties.get(i) .getPrime());
		 * garantieTable
		 * .setMontantRedBDGOpt1(selectGaranties.get(i).getMontantRed());
		 * 
		 * } else {
		 * 
		 * listegarantie.remove(selectGaranties.get(i));
		 * garantieTable.setPrimeAnBDGOpt1(BigDecimal.ZERO);
		 * garantieTable.setPrimeBDGOpt1(BigDecimal.ZERO);
		 * garantieTable.setMontantRedBDGOpt1(BigDecimal.ZERO); } } if
		 * (selectGaranties.get(i).getCodeGarantie().equals("GA009")) { if
		 * (garantieTable.isStatutBDGOpt2() == true) {
		 * 
		 * garantieTable.setStatutBDGOpt1(false);
		 * garantieTable.setStatutBDGOpt3(false);
		 * 
		 * listegarantie.add(selectGaranties.get(i));
		 * garantieTable.setPrimeAnBDGOpt2(selectGaranties.get(i)
		 * .getPrimeAnnuelle());
		 * garantieTable.setPrimeBDGOpt2(selectGaranties.get(i) .getPrime());
		 * garantieTable
		 * .setMontantRedBDGOpt2(selectGaranties.get(i).getMontantRed());
		 * 
		 * } else {
		 * 
		 * listegarantie.remove(selectGaranties.get(i));
		 * garantieTable.setPrimeAnBDGOpt2(BigDecimal.ZERO);
		 * garantieTable.setPrimeBDGOpt2(BigDecimal.ZERO);
		 * garantieTable.setMontantRedBDGOpt2(BigDecimal.ZERO); } } if
		 * (selectGaranties.get(i).getCodeGarantie().equals("GA010")) { if
		 * (garantieTable.isStatutBDGOpt3() == true) {
		 * 
		 * garantieTable.setStatutBDGOpt2(false);
		 * garantieTable.setStatutBDGOpt1(false);
		 * 
		 * listegarantie.add(selectGaranties.get(i));
		 * garantieTable.setPrimeAnBDGOpt3(selectGaranties.get(i)
		 * .getPrimeAnnuelle());
		 * garantieTable.setPrimeBDGOpt3(selectGaranties.get(i) .getPrime());
		 * garantieTable
		 * .setMontantRedBDGOpt3(selectGaranties.get(i).getMontantRed());
		 * 
		 * } else {
		 * 
		 * listegarantie.remove(selectGaranties.get(i));
		 * garantieTable.setPrimeAnBDGOpt3(BigDecimal.ZERO);
		 * garantieTable.setPrimeBDGOpt3(BigDecimal.ZERO);
		 * garantieTable.setMontantRedBDGOpt3(BigDecimal.ZERO); } }
		 * 
		 * if (selectGaranties.get(i).getCodeGarantie().equals("GA011")) { if
		 * (garantieTable.isStatutVol() == true) {
		 * 
		 * 
		 * listegarantie.add(selectGaranties.get(i));
		 * garantieTable.setPrimeAnVol(selectGaranties.get(i)
		 * .getPrimeAnnuelle());
		 * garantieTable.setPrimeVol(selectGaranties.get(i) .getPrime());
		 * garantieTable
		 * .setMontantRedVol(selectGaranties.get(i).getMontantRed());
		 * 
		 * } else {
		 * 
		 * listegarantie.remove(selectGaranties.get(i));
		 * garantieTable.setPrimeAnVol(BigDecimal.ZERO);
		 * garantieTable.setPrimeVol(BigDecimal.ZERO);
		 * garantieTable.setMontantRedVol(BigDecimal.ZERO); } } if
		 * (selectGaranties.get(i).getCodeGarantie().equals("GA012")) { if
		 * (garantieTable.isStatutVolAcc() == true) {
		 * 
		 * 
		 * listegarantie.add(selectGaranties.get(i));
		 * garantieTable.setPrimeAnVolAcc(selectGaranties.get(i)
		 * .getPrimeAnnuelle());
		 * garantieTable.setPrimeVolAcc(selectGaranties.get(i) .getPrime());
		 * garantieTable
		 * .setMontantRedVolAcc(selectGaranties.get(i).getMontantRed());
		 * 
		 * } else {
		 * 
		 * listegarantie.remove(selectGaranties.get(i));
		 * garantieTable.setPrimeAnVolAcc(BigDecimal.ZERO);
		 * garantieTable.setPrimeVolAcc(BigDecimal.ZERO);
		 * garantieTable.setMontantRedVolAcc(BigDecimal.ZERO); } } if
		 * (selectGaranties.get(i).getCodeGarantie().equals("GA013")) { if
		 * (garantieTable.isStatutVandalisme() == true) {
		 * 
		 * 
		 * listegarantie.add(selectGaranties.get(i));
		 * garantieTable.setPrimeAnVandalisme(selectGaranties.get(i)
		 * .getPrimeAnnuelle());
		 * garantieTable.setPrimeVandalisme(selectGaranties.get(i) .getPrime());
		 * garantieTable
		 * .setMontantRedVandalisme(selectGaranties.get(i).getMontantRed());
		 * 
		 * } else {
		 * 
		 * listegarantie.remove(selectGaranties.get(i));
		 * garantieTable.setPrimeAnVandalisme(BigDecimal.ZERO);
		 * garantieTable.setPrimeVandalisme(BigDecimal.ZERO);
		 * garantieTable.setMontantRedVandalisme(BigDecimal.ZERO); } }
		 * 
		 * if (selectGaranties.get(i).getCodeGarantie().equals("GA014")) { if
		 * (garantieTable.isStatutSROpt1() == true) {
		 * 
		 * garantieTable.setStatutSROpt2(false);
		 * garantieTable.setStatutSROpt3(false);
		 * 
		 * listegarantie.add(selectGaranties.get(i));
		 * garantieTable.setPrimeAnSROpt1(selectGaranties.get(i)
		 * .getPrimeAnnuelle());
		 * garantieTable.setPrimeSROpt1(selectGaranties.get(i) .getPrime());
		 * garantieTable
		 * .setMontantRedSROpt1(selectGaranties.get(i).getMontantRed());
		 * 
		 * } else {
		 * 
		 * listegarantie.remove(selectGaranties.get(i));
		 * garantieTable.setPrimeAnSROpt1(BigDecimal.ZERO);
		 * garantieTable.setPrimeSROpt1(BigDecimal.ZERO);
		 * garantieTable.setMontantRedSROpt1(BigDecimal.ZERO); } } if
		 * (selectGaranties.get(i).getCodeGarantie().equals("GA015")) { if
		 * (garantieTable.isStatutSROpt2() == true) {
		 * 
		 * garantieTable.setStatutSROpt1(false);
		 * garantieTable.setStatutSROpt3(false);
		 * 
		 * listegarantie.add(selectGaranties.get(i));
		 * garantieTable.setPrimeAnSROpt2(selectGaranties.get(i)
		 * .getPrimeAnnuelle());
		 * garantieTable.setPrimeSROpt2(selectGaranties.get(i) .getPrime());
		 * garantieTable
		 * .setMontantRedSROpt2(selectGaranties.get(i).getMontantRed());
		 * 
		 * } else {
		 * 
		 * listegarantie.remove(selectGaranties.get(i));
		 * garantieTable.setPrimeAnSROpt2(BigDecimal.ZERO);
		 * garantieTable.setPrimeSROpt2(BigDecimal.ZERO);
		 * garantieTable.setMontantRedSROpt2(BigDecimal.ZERO); } } if
		 * (selectGaranties.get(i).getCodeGarantie().equals("GA016")) { if
		 * (garantieTable.isStatutSROpt3() == true) {
		 * 
		 * garantieTable.setStatutSROpt2(false);
		 * garantieTable.setStatutSROpt1(false);
		 * 
		 * listegarantie.add(selectGaranties.get(i));
		 * garantieTable.setPrimeAnSROpt3(selectGaranties.get(i)
		 * .getPrimeAnnuelle());
		 * garantieTable.setPrimeSROpt3(selectGaranties.get(i) .getPrime());
		 * garantieTable
		 * .setMontantRedSROpt3(selectGaranties.get(i).getMontantRed());
		 * 
		 * } else {
		 * 
		 * listegarantie.remove(selectGaranties.get(i));
		 * garantieTable.setPrimeAnSROpt3(BigDecimal.ZERO);
		 * garantieTable.setPrimeSROpt3(BigDecimal.ZERO);
		 * garantieTable.setMontantRedSROpt3(BigDecimal.ZERO); } } if
		 * (selectGaranties.get(i).getCodeGarantie().equals("GA017")) { if
		 * (garantieTable.isStatutIndChaufOpt1() == true) {
		 * 
		 * garantieTable.setStatutIndChaufOpt2(false);
		 * garantieTable.setStatutIndChaufOpt3(false);
		 * garantieTable.setStatutIndChaufSp(false);
		 * 
		 * listegarantie.add(selectGaranties.get(i));
		 * garantieTable.setPrimeAnIndChaufOpt1(selectGaranties.get(i)
		 * .getPrimeAnnuelle());
		 * garantieTable.setPrimeIndChaufOpt1(selectGaranties.get(i)
		 * .getPrime());
		 * garantieTable.setMontantRedIndChaufOpt1(selectGaranties.
		 * get(i).getMontantRed());
		 * 
		 * } else {
		 * 
		 * listegarantie.remove(selectGaranties.get(i));
		 * garantieTable.setPrimeAnIndChaufOpt1(BigDecimal.ZERO);
		 * garantieTable.setPrimeIndChaufOpt1(BigDecimal.ZERO);
		 * garantieTable.setMontantRedIndChaufOpt1(BigDecimal.ZERO); } } if
		 * (selectGaranties.get(i).getCodeGarantie().equals("GA018")) { if
		 * (garantieTable.isStatutIndChaufOpt2() == true) {
		 * 
		 * garantieTable.setStatutIndChaufOpt1(false);
		 * garantieTable.setStatutIndChaufOpt3(false);
		 * garantieTable.setStatutIndChaufSp(false);
		 * 
		 * listegarantie.add(selectGaranties.get(i));
		 * garantieTable.setPrimeAnIndChaufOpt2(selectGaranties.get(i)
		 * .getPrimeAnnuelle());
		 * garantieTable.setPrimeIndChaufOpt2(selectGaranties.get(i)
		 * .getPrime());
		 * garantieTable.setMontantRedIndChaufOpt2(selectGaranties.
		 * get(i).getMontantRed());
		 * 
		 * } else {
		 * 
		 * listegarantie.remove(selectGaranties.get(i));
		 * garantieTable.setPrimeAnIndChaufOpt2(BigDecimal.ZERO);
		 * garantieTable.setPrimeIndChaufOpt2(BigDecimal.ZERO);
		 * garantieTable.setMontantRedIndChaufOpt2(BigDecimal.ZERO); } }if
		 * (selectGaranties.get(i).getCodeGarantie().equals("GA019")) { if
		 * (garantieTable.isStatutIndChaufOpt3() == true) {
		 * 
		 * garantieTable.setStatutIndChaufOpt2(false);
		 * garantieTable.setStatutIndChaufOpt1(false);
		 * garantieTable.setStatutIndChaufSp(false);
		 * 
		 * listegarantie.add(selectGaranties.get(i));
		 * garantieTable.setPrimeAnIndChaufOpt3(selectGaranties.get(i)
		 * .getPrimeAnnuelle());
		 * garantieTable.setPrimeIndChaufOpt3(selectGaranties.get(i)
		 * .getPrime());
		 * garantieTable.setMontantRedIndChaufOpt3(selectGaranties.
		 * get(i).getMontantRed());
		 * 
		 * } else {
		 * 
		 * listegarantie.remove(selectGaranties.get(i));
		 * garantieTable.setPrimeAnIndChaufOpt3(BigDecimal.ZERO);
		 * garantieTable.setPrimeIndChaufOpt3(BigDecimal.ZERO);
		 * garantieTable.setMontantRedIndChaufOpt3(BigDecimal.ZERO); } }if
		 * (selectGaranties.get(i).getCodeGarantie().equals("GA020")) { if
		 * (garantieTable.isStatutIndChaufSp() == true) {
		 * 
		 * garantieTable.setStatutIndChaufOpt1(false);
		 * garantieTable.setStatutIndChaufOpt2(false);
		 * garantieTable.setStatutIndChaufOpt3(false);
		 * 
		 * 
		 * listegarantie.add(selectGaranties.get(i));
		 * garantieTable.setPrimeAnIndChaufSp(selectGaranties.get(i)
		 * .getPrimeAnnuelle());
		 * garantieTable.setPrimeIndChaufSp(selectGaranties.get(i) .getPrime());
		 * garantieTable
		 * .setMontantRedIndChaufSp(selectGaranties.get(i).getMontantRed());
		 * 
		 * } else {
		 * 
		 * listegarantie.remove(selectGaranties.get(i));
		 * garantieTable.setPrimeAnIndChaufSp(BigDecimal.ZERO);
		 * garantieTable.setPrimeIndChaufSp(BigDecimal.ZERO);
		 * garantieTable.setMontantRedIndChaufSp(BigDecimal.ZERO); } }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * }
		 * 
		 * primeAnnuelle = BigDecimal.ZERO; primeNetAn = BigDecimal.ZERO; Prime
		 * = BigDecimal.ZERO; primeRC = BigDecimal.ZERO; totalRed =
		 * BigDecimal.ZERO;
		 * 
		 * for (int j = 0; j < listegarantie.size(); j++) { primeAnnuelle =
		 * primeAnnuelle .add(listegarantie.get(j).getPrimeAnnuelle());
		 * primeNetAn = primeNetAn.add(listegarantie.get(j).getPrimeNetAn());
		 * Prime = Prime .add(listegarantie.get(j).getPrime()); primeRC =
		 * primeRC.add(listegarantie.get(j).getPrimeRC());
		 * 
		 * totalRed = totalRed.add(listegarantie.get(j).getMontantRed()) ; }
		 * 
		 * 
		 * }
		 * 
		 * //Methode de choix des ganranties provenant d'un Avenant de
		 * modification des garanties. public void checkboxAvenant(){ if(
		 * getListgarantieAvenant().size()==0){
		 * 
		 * }else{
		 * 
		 * for(int i= 0; i< getListgarantieAvenant().size();i++){
		 * if(getListgarantieAvenant
		 * ().get(i).getCodeGarantie().equalsIgnoreCase("GA001")){
		 * garantieTable.setStatutRC(true); }
		 * 
		 * if(getListgarantieAvenant().get(i).getCodeGarantie().equalsIgnoreCase("GA002")){
		 * garantieTable.setStatutDR(true); }
		 * if(getListgarantieAvenant().get(i).
		 * getCodeGarantie().equalsIgnoreCase("GA003")){ garantieTable.setStatutRA(true);
		 * }
		 * if(getListgarantieAvenant().get(i).getCodeGarantie().equalsIgnoreCase("GA004")
		 * ){ garantieTable.setStatutDommagesCol(true); }
		 * if(getListgarantieAvenant
		 * ().get(i).getCodeGarantie().equalsIgnoreCase("GA005")){
		 * garantieTable.setStatutRA(true); }
		 * if(getListgarantieAvenant().get(i).
		 * getCodeGarantie().equalsIgnoreCase("GA006")){
		 * garantieTable.setStatutBDGOpt1(true); }
		 * if(getListgarantieAvenant().get
		 * (i).getCodeGarantie().equalsIgnoreCase("GA007")){
		 * garantieTable.setStatutVol(true); }
		 * if(getListgarantieAvenant().get(i)
		 * .getCodeGarantie().equalsIgnoreCase("GA008")){
		 * garantieTable.setStatutVolAcc(true); }
		 * if(getListgarantieAvenant().get
		 * (i).getCodeGarantie().equalsIgnoreCase("GA009")){
		 * garantieTable.setStatutVandalisme(true); }
		 * if(getListgarantieAvenant()
		 * .get(i).getCodeGarantie().equalsIgnoreCase("GA010")){
		 * garantieTable.setStatutIncendie(true); }
		 * if(getListgarantieAvenant().get
		 * (i).getCodeGarantie().equalsIgnoreCase("GA011")){
		 * garantieTable.setStatutImmob(true); }
		 * 
		 * } }
		 */
	}

	// les methodes de choix Avenant Affaire Nouvelle
	public void tabChange() {
	}

	public void checkDommageCol() {
		if (garantieTable.isStatutDommagesCol() == true) {
			garantieTable.setStatutDommagesTA(false);
		}
		checkbox();
	}

	public void checkDommageTA() {
		if (garantieTable.isStatutDommagesTA() == true) {
			garantieTable.setStatutDommagesCol(false);
		}
		checkbox();
	}

	public void checkBDGOpt1() {
		if (garantieTable.isStatutBDGOpt1() == true) {
			garantieTable.setStatutBDGOpt2(false);
			garantieTable.setStatutBDGOpt3(false);
		}
		checkbox();
	}

	public void checkBDGOpt2() {
		if (garantieTable.isStatutBDGOpt2() == true) {
			garantieTable.setStatutBDGOpt1(false);
			garantieTable.setStatutBDGOpt3(false);
		}
		checkbox();
	}

	public void checkBDGOpt3() {
		if (garantieTable.isStatutBDGOpt3() == true) {
			garantieTable.setStatutBDGOpt2(false);
			garantieTable.setStatutBDGOpt1(false);
		}
		checkbox();
	}

	public void checkSROpt1() {
		if (garantieTable.isStatutSROpt1() == true) {
			garantieTable.setStatutSROpt2(false);
			garantieTable.setStatutSROpt3(false);
		}
		checkbox();
	}

	public void checkSROpt2() {
		if (garantieTable.isStatutSROpt2() == true) {
			garantieTable.setStatutSROpt1(false);
			garantieTable.setStatutSROpt3(false);
		}
		checkbox();
	}

	public void checkSROpt3() {
		if (garantieTable.isStatutSROpt3() == true) {
			garantieTable.setStatutSROpt2(false);
			garantieTable.setStatutSROpt1(false);
		}
		checkbox();
	}

	public void checkIndChaufOpt1() {
		if (garantieTable.isStatutIndChaufOpt1() == true) {
			garantieTable.setStatutIndChaufOpt2(false);
			garantieTable.setStatutIndChaufOpt3(false);
			garantieTable.setStatutIndChaufSp(false);
		}
		checkbox();
	}

	public void checkIndChaufOpt2() {
		if (garantieTable.isStatutIndChaufOpt2() == true) {
			garantieTable.setStatutIndChaufOpt1(false);
			garantieTable.setStatutIndChaufOpt3(false);
			garantieTable.setStatutIndChaufSp(false);
		}
		checkbox();
	}

	public void checkIndChaufOpt3() {
		if (garantieTable.isStatutIndChaufOpt3() == true) {
			garantieTable.setStatutIndChaufOpt2(false);
			garantieTable.setStatutIndChaufOpt1(false);
			garantieTable.setStatutIndChaufSp(false);
		}
		checkbox();
	}

	public void checkIndChaufSp() {
		if (garantieTable.isStatutIndChaufSp() == true) {
			garantieTable.setStatutIndChaufOpt2(false);
			garantieTable.setStatutIndChaufOpt3(false);
			garantieTable.setStatutIndChaufOpt1(false);
		}
		checkbox();
	}

	/*-----------------les Getter et Setter -------------------------------*/

	public double getDuree() {
		return duree;
	}

	public Boolean getStatuRed() {
		return statuRed;
	}

	public void setStatuRed(Boolean statuRed) {
		this.statuRed = statuRed;
	}

	public Boolean getStatuBonus() {
		return statuBonus;
	}

	public void setStatuBonus(Boolean statuBonus) {
		this.statuBonus = statuBonus;
	}

	public Boolean getStatuMalus() {
		return statuMalus;
	}

	public void setStatuMalus(Boolean statuMalus) {
		this.statuMalus = statuMalus;
	}

	public void setDuree(double duree) {
		this.duree = duree;
	}

	public String getMouvement() {
		return mouvement;
	}

	public void setMouvement(String mouvement) {
		this.mouvement = mouvement;
	}

	public List<Garantie> getGarantiesList() {
		GarantiesList = new ArrayList<Garantie>();
		List<Object> listObject = getObjectService().getObjects("Garantie");
		for (Iterator it = listObject.iterator(); it.hasNext();) {
			Garantie garantie = (Garantie) it.next();
			try {
				GarantiesList.add(garantie);
			} catch (Exception e) {
			}

		}
		return GarantiesList;
	}

	public void setGarantiesList(List<Garantie> garantiesList) {
		this.GarantiesList = garantiesList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLibelleGarantie() {
		return libelleGarantie;
	}

	public void setLibelleGarantie(String libelleGarantie) {
		this.libelleGarantie = libelleGarantie;
	}

	public void setButtonadd(UIComponent buttonadd) {
		this.buttonadd = buttonadd;
	}

	public UIComponent getButtonadd() {
		return buttonadd;
	}

	public UIComponent getButtonupdate() {
		return buttonupdate;
	}

	public void setButtonupdate(UIComponent buttonupdate) {
		this.buttonupdate = buttonupdate;
	}

	public UIComponent getButtondelete() {
		return buttondelete;
	}

	public void setButtondelete(UIComponent buttondelete) {
		this.buttondelete = buttondelete;
	}

	public GarantieTable getGarantieTable() {
		return garantieTable;
	}

	public void setGarantieTable(GarantieTable garantieTable) {
		this.garantieTable = garantieTable;
	}

	public String getStatutList() {
		return statutList;
	}

	public Garantie getObjetGarantie() {
		return objetGarantie;
	}

	public void setObjetGarantie(Garantie objetGarantie) {
		this.objetGarantie = objetGarantie;
	}

	public void setStatutList(String statutList) {
		this.statutList = statutList;
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

		return "false";
	}

	public void setVueQuittance(String vueQuittance) {
		this.vueQuittance = vueQuittance;
	}

	public String getVehiculeSelect() {
		return vehiculeSelect;
	}

	public void setVehiculeSelect(String vehiculeSelect) {
		this.vehiculeSelect = vehiculeSelect;
	}

	public int getValT() {
		return valT;
	}

	public void setValT(int valT) {
		this.valT = valT;
	}

	public String getChoix() {
		return choix;
	}

	public void setChoix(String choix) {
		this.choix = choix;
	}

	public java.math.BigDecimal getTauxBonus() {
		return tauxBonus;
	}

	public void setTauxBonus(java.math.BigDecimal tauxBonus) {
		this.tauxBonus = tauxBonus;
	}

	public java.math.BigDecimal getTauxMalus() {
		return tauxMalus;
	}

	public void setTauxMalus(java.math.BigDecimal tauxMalus) {
		this.tauxMalus = tauxMalus;
	}

	public java.math.BigDecimal getTauxRed() {
		return tauxRed;
	}

	public void setTauxRed(java.math.BigDecimal tauxRed) {
		this.tauxRed = tauxRed;
	}

	public java.math.BigDecimal getTauxFlotte() {
		return tauxFlotte;
	}

	public void setTauxFlotte(java.math.BigDecimal tauxFlotte) {
		this.tauxFlotte = tauxFlotte;
	}

	public java.math.BigDecimal getTauxpermis() {
		return tauxpermis;
	}

	public void setTauxpermis(java.math.BigDecimal tauxpermis) {
		this.tauxpermis = tauxpermis;
	}

	public java.math.BigDecimal getTotalRed() {
		return totalRed;
	}

	public void setTotalRed(java.math.BigDecimal totalRed) {
		this.totalRed = totalRed;
	}

	public java.math.BigDecimal getPrimeTotale() {
		return primeTotale;
	}

	public void setPrimeTotale(java.math.BigDecimal primeTotale) {
		this.primeTotale = primeTotale;
	}

	public void setPrimeAnnuelle(java.math.BigDecimal primeAnnuelle) {
		this.primeAnnuelle = primeAnnuelle;
	}

	public java.math.BigDecimal getPrimeAnnuelle() {
		return primeAnnuelle;
	}

	public java.math.BigDecimal getPrimeNetAn() {
		return primeNetAn;
	}

	public void setPrimeNetAn(java.math.BigDecimal primeNetAn) {
		this.primeNetAn = primeNetAn;
	}

	public java.math.BigDecimal getPrimeRC() {
		return primeRC;
	}

	public void setPrimeRC(java.math.BigDecimal primeRC) {
		this.primeRC = primeRC;
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

	/*
	 * public Garantie getGaranties() { return garanties; }
	 * 
	 * public void setGaranties(Garantie garanties) { this.garanties =
	 * garanties; }
	 */
	public List<Garantie> getGarantiesChoisies() {
		return garantiesChoisies;
	}

	public void setGarantiesChoisies(List<Garantie> garantiesChoisies) {
		this.garantiesChoisies = garantiesChoisies;
	}

	public List<Garantie> getMesGarantiesList() {
		return mesGarantiesList;
	}

	public void setMesGarantiesList(List<Garantie> mesGarantiesList) {
		this.mesGarantiesList = mesGarantiesList;
	}

	public List<Garanties> getListeGroupGaranties() {
		return listeGroupGaranties;
	}

	public void setListeGroupGaranties(List<Garanties> listeGroupGaranties) {
		this.listeGroupGaranties = listeGroupGaranties;
	}

	public List<Garanties> getListegarantieFiltre() {
		return listegarantieFiltre;
	}

	public void setListegarantieFiltre(List<Garanties> listegarantieFiltre) {
		this.listegarantieFiltre = listegarantieFiltre;
	}

	public List<Garanties> getListegarantieAll() {
		return listegarantieAll;
	}

	public void setListegarantieAll(List<Garanties> listegarantieAll) {
		this.listegarantieAll = listegarantieAll;
	}

	public Garanties getSelectGarantieGroup() {
		return selectGarantieGroup;
	}

	public void setSelectGarantieGroup(Garanties selectGarantieGroup) {
		this.selectGarantieGroup = selectGarantieGroup;
	}

	public PrimeCategorie getPrimeCategorie() {
		return primeCategorie;
	}

	public void setPrimeCategorie(PrimeCategorie primeCategorie) {
		this.primeCategorie = primeCategorie;
	}

}
