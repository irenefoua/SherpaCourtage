package com.j3a.assurance.managedBean.Auto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.Garantie;
import com.j3a.assurance.model.Tarif;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.prime.CalculPrimeGlobale;
import com.j3a.assurance.prime.CalculPrimeProrata;
import com.j3a.assurance.prime.ReturnPrimeCategorie;
import com.j3a.assurance.prime.categorie.PrimeCategorie;
import com.j3a.assurance.prime.categorie.PrimeCategorieInterface;
import com.j3a.assurance.utilitaires.Garanties;
import com.j3a.assurance.utilitaires.VehiculeRow;
@Component
public class GarantieMB implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	ObjectService objectService;
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
	
	private java.math.BigDecimal conseiller=BigDecimal.ZERO ,gestionnaire=BigDecimal.ZERO ,coassurance=BigDecimal.ZERO ,intermediaire=BigDecimal.ZERO;
	private java.math.BigDecimal reductionAutres=BigDecimal.ZERO ,reductionFlote=BigDecimal.ZERO ,reductionPermis=BigDecimal.ZERO,totalReductions=BigDecimal.ZERO,primeNette = BigDecimal.ZERO,reductions=BigDecimal.ZERO;
	private java.math.BigDecimal  surprimes=BigDecimal.ZERO,primeProrata=BigDecimal.ZERO, accessoires = BigDecimal.ZERO;
	private java.math.BigDecimal  bonus=BigDecimal.ZERO, malus=BigDecimal.ZERO;
	private int  durreeGarantie;
	
	List<Garanties> listegaranties = new ArrayList<Garanties>();
	List<Garanties> listeGarantiesSelect= new ArrayList<Garanties>();
	public List<Garanties> listeGroupGaranties = new ArrayList<Garanties>();
	public List<Garanties> listegarantieFiltre = new ArrayList<Garanties>();
	public List<Garanties> listegarantieAll = new ArrayList<Garanties>();

	public Garanties selectGarantieGroup;
	public String codeRisque;
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



	public List<Garanties> affichegarantiesAuto(VehiculeRow vehiculeRow) {
		try {
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
		} catch (NullPointerException e) {
			
		return null;
		}
	}

	public List<Garanties> calculGarantieAuto(VehiculeRow vehiculeRow) {
		try {
			System.out.println("-------------Calcul Garantie Debut----------------");
			
				
			setPrimeCategorie(new PrimeCategorie());
			CalculPrimeGlobale primeGlobale = new CalculPrimeGlobale();

			// prévoir une méthode de vérification de la sous catégorie à plus tard
			Tarif tarif;
			
				tarif = (Tarif)getObjectService().getObjectById(vehiculeRow.getSouCatVehi().getTarif().getCodeTarif(), "Tarif");
				System.out.println("-------------Code Tarif----------------"+tarif.getCodeTarif());
			
			//System.out.println("-------------Code RC1----------------"+tarif.getRcTarif1().getCodeRcTarif1());
			primeGlobale.setCategorie(tarif.getCodeTarif());
			primeGlobale.primeGlobale();
			PrimeCategorieInterface prime = primeGlobale.primeGlobale();

			CalculPrimeProrata prorata = new CalculPrimeProrata();
			
			prime.setTarif(tarif);
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
			System.out.println("valeur de primecategorie "
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
				
				System.out.println("-------------Calcul Garantie Debut----------------");
				System.out.println("ssssssssssssssssssssssssssssssssssss"
						+ garantie.getCategorieGarantie());
				garantieList.add(garantie);
			}

			return garantieList;
		} catch (NullPointerException e) {
			FacesMessage msg = new FacesMessage(
					"Tarif non configur� reportez vous � votre Administrateur !");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		return null;	
		}
		
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

		

	}

	public String validerGarantieAuto() {

		return "garantieOK";

	}

	public String addGarantieChoisie() {
		
		return "choix";
	}

	public String vueQuittance() {
		return null;
	}

	public void checkbox() {
		
		
	}

	// les methodes de choix Avenant Affaire Nouvelle
	public void tabChange() {
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

	public List<Garantie> getGarantiesList() {
		GarantiesList = new ArrayList<Garantie>();
		List<Garantie> listObject = getObjectService().getObjects("Garantie");
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

	public int getChekDom() {
		return chekDom;
	}

	public void setChekDom(int chekDom) {
		this.chekDom = chekDom;
	}

	public int getChekSR() {
		return chekSR;
	}

	public void setChekSR(int chekSR) {
		this.chekSR = chekSR;
	}

	public int getChekBDG() {
		return chekBDG;
	}

	public void setChekBDG(int chekBDG) {
		this.chekBDG = chekBDG;
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

	public java.math.BigDecimal getPrimeNette() {
		return primeNette;
	}

	public void setPrimeNette(java.math.BigDecimal primeNette) {
		this.primeNette = primeNette;
	}

	public java.math.BigDecimal getReductions() {
		return reductions;
	}

	public void setReductions(java.math.BigDecimal reductions) {
		this.reductions = reductions;
	}

	public java.math.BigDecimal getSurprimes() {
		return surprimes;
	}

	public void setSurprimes(java.math.BigDecimal surprimes) {
		this.surprimes = surprimes;
	}

	public java.math.BigDecimal getPrimeProrata() {
		return primeProrata;
	}

	public void setPrimeProrata(java.math.BigDecimal primeProrata) {
		this.primeProrata = primeProrata;
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

	public int getDurreeGarantie() {
		return durreeGarantie;
	}

	public void setDurreeGarantie(int durreeGarantie) {
		this.durreeGarantie = durreeGarantie;
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

	public String getMonString() {
		return monString;
	}

	public void setMonString(String monString) {
		this.monString = monString;
	}

	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}

	public String getCodeRisque() {
		return codeRisque;
	}

	public void setCodeRisque(String codeRisque) {
		this.codeRisque = codeRisque;
	}

}
