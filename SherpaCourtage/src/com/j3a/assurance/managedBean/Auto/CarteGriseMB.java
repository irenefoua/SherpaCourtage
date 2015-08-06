package com.j3a.assurance.managedBean.Auto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.converter.ScatVehiCvtr;
import com.j3a.assurance.model.ApporteurVehicule;
import com.j3a.assurance.model.ApporteurVehiculeId;
import com.j3a.assurance.model.Avenant;
import com.j3a.assurance.model.Conducteur;
import com.j3a.assurance.model.ConduireVehicule;
import com.j3a.assurance.model.ConduireVehiculeId;
import com.j3a.assurance.model.Contrat;
import com.j3a.assurance.model.Garantie;
import com.j3a.assurance.model.GarantieChoisie;
import com.j3a.assurance.model.GarantieGarantieChoisie;
import com.j3a.assurance.model.GarantieGarantieChoisieId;
import com.j3a.assurance.model.HistoMouvement;
import com.j3a.assurance.model.HistoProprietesVehicule;
import com.j3a.assurance.model.SousCatVehicule;
import com.j3a.assurance.model.Vehicule;
import com.j3a.assurance.model.VehiculeZoneGeographique;
import com.j3a.assurance.model.VehiculeZoneGeographiqueId;
import com.j3a.assurance.model.VehiculesAssures;
import com.j3a.assurance.model.Ville;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.utilitaires.Garanties;
import com.j3a.assurance.utilitaires.HistoProprietesVehiTool;
import com.j3a.assurance.utilitaires.IdGenerateur;
import com.j3a.assurance.utilitaires.VehiculeRow;

/**
 *
 * @author J3A-Herve
 */
@Component
public class CarteGriseMB implements Serializable {
		private static final long serialVersionUID = 1L;
		
		@Autowired
	    ObjectService objectService;
		@Autowired
		private IdGenerateur idGenerateur;
		@Autowired
		ScatVehiCvtr scatVehiCvtr;
		// private static Logger logs=Logger.getLogger(ManagedCarteGrise.class);
		private VehiculeRow slctdVehiRw = new VehiculeRow();
		private VehiculeRow slctdVehiRwTb = new VehiculeRow();
		private String warnMsg;
		private List<SousCatVehicule> scategList;
		private String slctdville;
		private List<Ville> villeList;
		private List<VehiculeRow> vehiculeList = new ArrayList<VehiculeRow>();
		private Boolean editGarEtat = true;
		private Boolean validVehiEtat = true;
		private Conducteur cduc;
		private Conducteur conduHab = new Conducteur();
		private InputText inputPf;
		private InputText inputPr;
		private InputText inputPlcCab;
		private InputText inputPlcHcab;
		private InputText inputPoidV;
		private InputText inputCu;
		private InputText inputValNeuv;
		private InputText inputValVen;
		private InputText inputPlace;
		private InputText inputNbrTran;
		
		private OutputLabel outputPf;
		private OutputLabel outputPr;
		private OutputLabel outputPlcCab;
		private OutputLabel outputPlcHcab;
		private OutputLabel outputPoidV;
		private OutputLabel outputCu;
		private OutputLabel outputValNeuv;
		private OutputLabel outputValVen;
		private OutputLabel outputPlace;
		private OutputLabel outputNbrTran;
		
		private Boolean buttonSavVehicule = true;
		private Boolean buttonAddVehicule;
		private Boolean buttonDelVehicule = true;
		private Boolean buttonEditGar = true;
		private Boolean buttonPropVehicule;
        private String libelleRisque, renouvellement, remorque ="non";
		private String test;
		//Logger logs = Logger.getLogger(CarteGriseMB.class);
		/*@PostConstruct
		public void postConstuct(){
			System.out.println("Testte BD EJB"+ villeFacade.findAll().size());
			for(Ville v:villeFacade.findAll()){
				System.out.println("Testte BD EJB"+ v.getLibelleVille());	
			}
			
		}*/
        
     /*   @PostConstruct
       public void postconstruct(){
        	GarantieGarantieChoisieId garantieGarantieChoisieId = new GarantieGarantieChoisieId();

			garantieGarantieChoisieId.setCodeGarantie("101");
			garantieGarantieChoisieId.setCodeGarantieChoisie("001KA0315AUT001-V001GA");

			GarantieGarantieChoisie garantieGarantieChoisie =	(GarantieGarantieChoisie)getObjectService().getByIdPK(garantieGarantieChoisieId, "garantieGarantieChoisie");
			System.out
			.println("-------garantieGarantieChoisie----------- "+garantieGarantieChoisie.getId()+"-Prime- "+garantieGarantieChoisie.getPrimeAnnuelle());
			
			GarantieChoisie garantieChoisie =	(GarantieChoisie)getObjectService().getObjectById("001KA0315AUT001-V001GA", "GarantieChoisie");
			for (GarantieGarantieChoisie ggc : garantieChoisie.getGarantieGarantieChoisies()) {
				System.out.println("code Garantie ds GarantieGarantieChoisie :" + ggc.getGarantie().getCodeGarantie());
				System.out.println("code GarantieChoisie ds GarantieGarantieChoisie :" + ggc.getGarantieChoisie().getCodeGarantieChoisie());
				
				System.out.println("prime prorata de GarantieGarantieChoisie :" + ggc.getPrimeAnnuelle());
				
				
			}
        }*/
		public void gestionCarteGrise(Avenant Avn) {

			try {
				Date date = new Date();
				date = Calendar.getInstance().getTime();

				VehiculesAssures vehiAss = new VehiculesAssures();
				vehiAss.setIdVehiculesAssures(getIdGenerateur().getIdVehiAss(Avn.getContrat()));
				Vehicule vehi = new Vehicule();

				if (Avn.getMouvement().equalsIgnoreCase("AFFAIRE NOUVELLE")) {
					Avn.setVehiculesAssures(vehiAss);
					getObjectService().addObject(vehiAss);
				}

				getObjectService().updateObject(Avn);

				// int numOrdV=Avn.getIdVehiculesAssures().getVehicules().size()+1;
				int numOrdV = 1;
				int sizeVehicules = getVehiculeList().size();
				for (VehiculeRow F : getVehiculeList()) {

					HistoProprietesVehicule histoPropVehi = new HistoProprietesVehicule();

					HistoMouvement histoMouvement = new HistoMouvement();

					VehiculeZoneGeographique VehiZoneGeo = new VehiculeZoneGeographique();
					VehiculeZoneGeographiqueId VehiZoneGeoId = new VehiculeZoneGeographiqueId();

					ConduireVehicule conduirVehi = new ConduireVehicule();
					ConduireVehiculeId conduirVehiId = new ConduireVehiculeId();
                 
					String key = getObjectService().getCodeTable("Condu", 5, 4, "conducteur",
							"NUM_COND");
					F.getConduHab().setNumCond(key);

					
					
				/*	ApporteurVehicule ApporteurVehi = new ApporteurVehicule();
					ApporteurVehiculeId ApporteurVehiId = new ApporteurVehiculeId();*/

					// on enregistre ici
					String numOrdVSt = String.format("%02d", numOrdV);

					vehi = F.getVehi();
					// vehi.setId(vehiAss.getId() + "-V" + numOrdVSt);
					vehi.setCodeVehicule(getIdGenerateur().getIdVehicule(Avn.getContrat()));
					vehi.setVehiculesAssures(vehiAss);
					vehi.setNumImmat(vehi.getNumImmat().toUpperCase());
					vehi.setNumImmatPrec(vehi.getNumImmatPrec().toUpperCase());
					
					numOrdV++;
					vehi.setSousCatVehicule(F.getSouCatVehi());
					if (sizeVehicules == 1) {
						vehi.setStatut("actif");

					} else {
						vehi.setStatut("actif");
					}

					VehiZoneGeoId.setCodeVehicule(vehi.getCodeVehicule());
					VehiZoneGeoId.setCodeZoneGeo(F.getZonGeo().getCodeZoneGeo());

					VehiZoneGeo.setId(VehiZoneGeoId);
					VehiZoneGeo.setDateRouler(date);

					histoPropVehi.setCodeHistoVehicule(getIdGenerateur().getIdHistoPropVehi(
							vehi.getCodeVehicule()));
					HistoProprietesVehiTool.setProperties(histoPropVehi, vehi);
					histoPropVehi.setVehicule(vehi);
					histoPropVehi.setDateHisto(date);

					histoMouvement.setCodeHistoMouvement(getIdGenerateur().getIdHistoMvmt(vehi));
					histoMouvement.setVehicule(vehi);
					histoMouvement.setDateHistoMouvement(Calendar.getInstance()
							.getTime());
					histoMouvement.setIdAvenant(Avn.getNumAvenant());
					histoMouvement.setLibelleHistoMouvement(Avn.getMouvement());

					conduirVehiId.setCodeVehicule(vehi.getCodeVehicule());
					conduirVehiId.setNumCond(F.getConduHab().getNumCond());

					conduirVehi.setId(conduirVehiId);
					conduirVehi.setDateConduite(date);

					/*ApporteurVehiId.setCodeVehicule(vehi.getCodeVehicule());
					ApporteurVehiId.setCodeApporteur(F.getApporteur().getCodeApporteur());

					ApporteurVehi.setId(ApporteurVehiId);
					ApporteurVehi.setDateApporteurVehicule(date);
					ApporteurVehi
							.setMontantComApporteur(F.getCommissionApporteur());
					ApporteurVehi.setTauxComApporteur(F
							.getTauxCommissionApporteur());*/
					// conduirVehi.setDateConduite(date);

					// recup de l'id du vehicule
					getObjectService().addObject(vehi);
					getObjectService().addObject(VehiZoneGeo);
					//getObjectService().addObject(ApporteurVehi);

					getObjectService().addObject(histoPropVehi);
					getObjectService().addObject(histoMouvement);
					// add Cond habituel
					
					
					


					cduc =  (Conducteur) getObjectService().getObjectById(getSlctdVehiRw().getConduHab().getNumCond(), "Conducteur");
					if (cduc == null) {
						getObjectService().addObject(F.getConduHab());

					} else {

						getObjectService().updateObject(F.getConduHab());
					}

					getObjectService().addObject(conduirVehi);


					// add Garanties
					GarantieChoisie garchoi = new GarantieChoisie();

					garchoi.setCodeGarantieChoisie(vehi.getCodeVehicule() + "GA");
					garchoi.setDateGarantieChoisie(date);
					garchoi.setVehicule(F.getVehi());
					garchoi.setLibelleGarantieChosie("Garanties Automobile");
					garchoi.setCodeAvenantAuto(Avn.getNumAvenant());

					// Garantie Garantie choisie

					List<GarantieGarantieChoisie> garantieGarantieChoisieList = new ArrayList<GarantieGarantieChoisie>();

					for (Garanties G : F.getListegaranties()) {

						// on calcul la prime

						Garantie gar = new Garantie();
						GarantieGarantieChoisie garantieGarantieChoisie = new GarantieGarantieChoisie();
						GarantieGarantieChoisieId garantieGarantieChoisieId = new GarantieGarantieChoisieId();

						gar.setCodeGarantie(G.getCodeGarantie());
						garantieGarantieChoisieId.setCodeGarantie(gar.getCodeGarantie());
						garantieGarantieChoisieId.setCodeGarantieChoisie(garchoi.getCodeGarantieChoisie());

						garantieGarantieChoisie.setId(garantieGarantieChoisieId);

						garantieGarantieChoisie
								.setDateGarantieGarantieChoisie(date);
						garantieGarantieChoisie.setPrimeAnnuelle(G
								.getPrimesAnnuelle());
						garantieGarantieChoisie.setPrimeNetteAnnuelle(G
								.getPrimesNetteAnnuelle());
						garantieGarantieChoisie.setPrimeNetteProrata(G
								.getPrimesProrata());
						garantieGarantieChoisie.setMontantReduction(G
								.getReductions());

						garantieGarantieChoisie.setAutreReduction(BigDecimal.ZERO);
						garantieGarantieChoisie.setBonus(G.getBonus());
						garantieGarantieChoisie.setMalus(G.getMalus());
						garantieGarantieChoisie.setReductionFlotte(BigDecimal.ZERO);
						garantieGarantieChoisie.setReductionPermis(BigDecimal.ZERO);
						garantieGarantieChoisie
								.setTauxAutreReduction(BigDecimal.ZERO);
						garantieGarantieChoisie.setTauxBonus(G.getBonus());
						garantieGarantieChoisie.setTauxMalus(G.getMalus());
						garantieGarantieChoisie.setTauxFlotte(BigDecimal.ZERO);
						garantieGarantieChoisie.setTauxPermis(BigDecimal.ZERO);

						// on ajoute l'ensemble dans la liste des garantiesChoisies
						garantieGarantieChoisieList.add(garantieGarantieChoisie);

					}

					// Calcul du montant de la prime de la somme des garanties pour
					// garantie choiosie
					BigDecimal prime = BigDecimal.ZERO, primeAnnuelle = BigDecimal.ZERO, primeNetteAnnuelle = BigDecimal.ZERO, red = BigDecimal.ZERO, com = BigDecimal.ZERO, ges = BigDecimal.ZERO, con = BigDecimal.ZERO, interm = BigDecimal.ZERO, taxe = BigDecimal.ZERO, coass;
					for (GarantieGarantieChoisie GC : garantieGarantieChoisieList) {
						prime = prime.add(GC.getPrimeNetteProrata());
						primeAnnuelle = primeAnnuelle.add(GC.getPrimeAnnuelle());
						primeNetteAnnuelle = primeNetteAnnuelle.add(GC
								.getPrimeNetteAnnuelle());
						red = red.add(GC.getMontantReduction());
						/*
						 * com =
						 * com.add(GC.getTauxCom().multiply(prime).divide(BigDecimal
						 * .valueOf(100))); con =
						 * con.add(GC.getTauxConsIa().multiply
						 * (prime).divide(BigDecimal.valueOf(100))); interm =
						 * interm.
						 * add(GC.getTauxintermIa().multiply(prime).divide(BigDecimal
						 * .valueOf(100))); ges =
						 * ges.add(GC.getTauxGesIa().multiply(
						 * prime).divide(BigDecimal.valueOf(100)));
						 */
						// coass = coass+GT.getT*prime/100;
						taxe = taxe.add(prime.divide(BigDecimal.valueOf(0.03), 2));
					}
					garchoi.setPrimeNetteProrata(prime);
					garchoi.setPrimeNetteAnnuelle(primeAnnuelle);
					garchoi.setPrimeAnnuelle(primeNetteAnnuelle);
					garchoi.setCodeAvenantAuto(Avn.getNumAvenant());

					garchoi.setBonus(BigDecimal.ZERO);
					garchoi.setMalus(BigDecimal.ZERO);
					garchoi.setReductionSocioProf(BigDecimal.ZERO);
					garchoi.setReductionPermis(BigDecimal.ZERO);
					garchoi.setReductionCommercial(BigDecimal.ZERO);
					garchoi.setAutre(BigDecimal.ZERO);
					garchoi.setMontantReduction(BigDecimal.ZERO);

					garchoi.setAccessoireauto(BigDecimal.ZERO);
					// garchoi.setMontantGarantieIa(G.getMontantGarantie());
					// garchoi.setTaux(new Float(G.getTaux()));
					// Ajout taux de la franchise

					// ajout de garantie choisie
					try {
						getObjectService().addObject(garchoi);
					} catch (Exception e) {
						//logs.error("Error add GarantieChoisieAuto", e);
					}

					// ajout de garantie garantie choisie
					for (GarantieGarantieChoisie GC : garantieGarantieChoisieList) {

						try {
							getObjectService().addObject(GC);
						} catch (Exception e) {
						//	logs.error("Error add GarantieGarantieChoisieAuto", e);
						}
					}

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//setWarnMsg("pbe à l'enregistrement des véhicules et leurs garanties <br/> <<"
				//		+ e.getMessage() + ">>");
				RequestContext.getCurrentInstance().execute("NoSlctd.show()");

			}
		}

		public void testidVehicule() {
			Contrat c = new Contrat();
			// 001KA1015AUT010
			c.setNumPolice("001KA1015AUT012");
			String idVehicule = getIdGenerateur().getIdVehicule(c);
			System.out.println("id du vehicule:::::::::::" + idVehicule);
			int idv = new BigDecimal(idVehicule.substring(17)).intValue();
			System.out.println("id du vehicule en int:::::::::::" + idv);
		}

		public void addRenouvellementAuto(Avenant Avn) {
			Date date = new Date();
			date = Calendar.getInstance().getTime();

			Avenant avn = new Avenant();
			avn.setUtilisateur(Avn.getUtilisateur());
			avn.setDateAvenant(date);
			avn.setDateEmission(Avn.getDateEmission());
			avn.setDuree(Avn.getDuree());
			avn.setEcheance(Avn.getEcheance());
			avn.setEffet(Avn.getEffet());
			avn.setNumAvenant(Avn.getNumAvenant());
			avn.setMouvement("Renouvellement");
			avn.setContrat(Avn.getContrat());
			avn.setObservation(Avn.getObservation());
			avn.setResiliation(Avn.getResiliation());
			avn.setVehiculesAssures(Avn.getVehiculesAssures());

			try {
				// Date date = new Date();
				// date = Calendar.getInstance().getTime();

				// VehiculesAssures vehiAss = new VehiculesAssures();
				/*
				 * vehiAss.setId(getIdGenerateur().getIdVehiAss(avn));
				 * avn.setIdVehiculesAssures(vehiAss);
				 */
				// Vehicule vehi = new Vehicule();

				getObjectService().addObject(avn);
				// getObjectService().addObject(vehiAss);
				// gestion des vehicules pour l'enregistrement dans la base de
				// donnée

				int sizeVehicules = getVehiculeList().size();
				for (VehiculeRow F : getVehiculeList()) {
					Vehicule vehi = new Vehicule();
					HistoProprietesVehicule histoPropVehi = new HistoProprietesVehicule();
					HistoMouvement histoMouvement = new HistoMouvement();
					vehi = F.getVehi();
					// ajout du vehicule ds la BD s'il n'existe pas
					if (F.getStatutVehiculeBD() == false) {

						vehi.setSousCatVehicule(F.getSouCatVehi());
						vehi.setCodeVehicule(getIdGenerateur().getIdVehicule(
								avn.getContrat()));
						vehi.setVehiculesAssures(avn.getVehiculesAssures());
						VehiculeZoneGeographique VehiZoneGeo = new VehiculeZoneGeographique();
						VehiculeZoneGeographiqueId VehiZoneGeoId = new VehiculeZoneGeographiqueId();

						ConduireVehicule conduirVehi = new ConduireVehicule();
						ConduireVehiculeId conduirVehiId = new ConduireVehiculeId();

						ApporteurVehicule ApporteurVehi = new ApporteurVehicule();
						ApporteurVehiculeId ApporteurVehiId = new ApporteurVehiculeId();

						// on enregistre ici

						if (sizeVehicules == 1) {
							vehi.setStatut("actif");

						} else {
							vehi.setStatut("actif");
						}

						VehiZoneGeoId.setCodeVehicule(vehi.getCodeVehicule());
						VehiZoneGeoId.setCodeZoneGeo(F.getZonGeo().getCodeZoneGeo());

						VehiZoneGeo.setId(VehiZoneGeoId);
						VehiZoneGeo.setDateRouler(date);

						conduirVehiId.setCodeVehicule(vehi.getCodeVehicule());
						conduirVehiId.setNumCond(F.getConduHab().getNumCond());

						conduirVehi.setId(conduirVehiId);

						ApporteurVehiId.setCodeVehicule(vehi.getCodeVehicule());
						ApporteurVehiId.setCodeApporteur(F.getApporteur().getCodeApporteur());

						ApporteurVehi.setId(ApporteurVehiId);
						ApporteurVehi.setDateApporteurVehicule(date);
						ApporteurVehi
								.setMontantComApporteur(F.getCommissionApporteur()
										.add(F.getTauxCommissionApporteur()
												.multiply(F.getPrimeNette())
												.divide(BigDecimal.valueOf(100.0))));
						ApporteurVehi.setTauxComApporteur(F
								.getTauxCommissionApporteur());
						// conduirVehi.setDateConduite(date);

						// recup de l'id du vehicule

						getObjectService().addObject(vehi);
						getObjectService().addObject(VehiZoneGeo);
						getObjectService().addObject(ApporteurVehi);

						// add Cond habituel
						cduc =  (Conducteur) getObjectService().getObjectById(getSlctdVehiRw().getConduHab().getNumCond(), "Conducteur");
						if (cduc == null) {
							getObjectService().addObject(F.getConduHab());
						} else {
							getObjectService().updateObject(F.getConduHab());
						}

						getObjectService().addObject(conduirVehi);

						// int v1 = V.getId();
					}
					// ajout des infos supplementaires du vehicule

					histoMouvement.setCodeHistoMouvement(getIdGenerateur().getIdHistoMvmt(vehi));
					histoMouvement.setVehicule(vehi);
					histoMouvement.setDateHistoMouvement(Calendar.getInstance()
							.getTime());
					histoMouvement.setIdAvenant(avn.getNumAvenant());
					histoMouvement.setLibelleHistoMouvement(avn.getMouvement());
					histoPropVehi.setCodeHistoVehicule(getIdGenerateur().getIdHistoPropVehi(
							vehi.getCodeVehicule()));
					HistoProprietesVehiTool.setProperties(histoPropVehi, vehi);
					histoPropVehi.setVehicule(vehi);
					histoPropVehi.setDateHisto(date);

					getObjectService().addObject(histoPropVehi);
					getObjectService().addObject(histoMouvement);

					// add Garanties
					GarantieChoisie garchoi = new GarantieChoisie();

					String idGarchoi = getIdGenerateur().getIdGarChoisieAuto(
							F.getVehi());
					garchoi.setCodeGarantieChoisie(idGarchoi);
					garchoi.setDateGarantieChoisie(date);
					garchoi.setVehicule(F.getVehi());
					garchoi.setLibelleGarantieChosie("Garanties Automobile");
					garchoi.setCodeAvenantAuto(Avn.getNumAvenant());

					// Garantie Garantie choisie

					List<GarantieGarantieChoisie> garantieGarantieChoisieList = new ArrayList<GarantieGarantieChoisie>();

					for (Garanties G : F.getListegaranties()) {

						// on calcul la prime

						Garantie gar = new Garantie();
						GarantieGarantieChoisie garantieGarantieChoisie = new GarantieGarantieChoisie();
						GarantieGarantieChoisieId garantieGarantieChoisieId = new GarantieGarantieChoisieId();

						gar.setCodeGarantie(G.getCodeGarantie());
						garantieGarantieChoisieId.setCodeGarantie(gar.getCodeGarantie());
						garantieGarantieChoisieId.setCodeGarantieChoisie(garchoi.getCodeGarantieChoisie());

						garantieGarantieChoisie.setId(garantieGarantieChoisieId);

						garantieGarantieChoisie
								.setDateGarantieGarantieChoisie(date);
						garantieGarantieChoisie.setPrimeAnnuelle(G
								.getPrimesAnnuelle());
						garantieGarantieChoisie.setPrimeNetteAnnuelle(G
								.getPrimesNetteAnnuelle());
						garantieGarantieChoisie.setPrimeNetteProrata(G
								.getPrimesProrata());
						garantieGarantieChoisie.setMontantReduction(G
								.getReductions());

						garantieGarantieChoisie.setAutreReduction(BigDecimal.ZERO);
						garantieGarantieChoisie.setBonus(G.getBonus());
						garantieGarantieChoisie.setMalus(G.getMalus());
						garantieGarantieChoisie.setReductionFlotte(BigDecimal.ZERO);
						garantieGarantieChoisie.setReductionPermis(BigDecimal.ZERO);
						garantieGarantieChoisie
								.setTauxAutreReduction(BigDecimal.ZERO);
						garantieGarantieChoisie.setTauxBonus(G.getBonus());
						garantieGarantieChoisie.setTauxMalus(G.getMalus());
						garantieGarantieChoisie.setTauxFlotte(BigDecimal.ZERO);
						garantieGarantieChoisie.setTauxPermis(BigDecimal.ZERO);

						// on ajoute l'ensemble dans la liste des garantiesChoisies
						garantieGarantieChoisieList.add(garantieGarantieChoisie);

					}

					// Calcul du montant de la prime de la somme des garanties pour
					// garantie choiosie
					BigDecimal prime = BigDecimal.ZERO, primeAnnuelle = BigDecimal.ZERO, primeNetteAnnuelle = BigDecimal.ZERO, red = BigDecimal.ZERO, com = BigDecimal.ZERO, ges = BigDecimal.ZERO, con = BigDecimal.ZERO, interm = BigDecimal.ZERO, taxe = BigDecimal.ZERO, coass;
					for (GarantieGarantieChoisie GC : garantieGarantieChoisieList) {
						prime = prime.add(GC.getPrimeNetteProrata());
						primeAnnuelle = primeAnnuelle.add(GC.getPrimeAnnuelle());
						primeNetteAnnuelle = primeNetteAnnuelle.add(GC
								.getPrimeNetteAnnuelle());
						red = red.add(GC.getMontantReduction());
						/*
						 * com =
						 * com.add(GC.getTauxCom().multiply(prime).divide(BigDecimal
						 * .valueOf(100))); con =
						 * con.add(GC.getTauxConsIa().multiply
						 * (prime).divide(BigDecimal.valueOf(100))); interm =
						 * interm.
						 * add(GC.getTauxintermIa().multiply(prime).divide(BigDecimal
						 * .valueOf(100))); ges =
						 * ges.add(GC.getTauxGesIa().multiply(
						 * prime).divide(BigDecimal.valueOf(100)));
						 */
						// coass = coass+GT.getT*prime/100;
						taxe = taxe.add(prime.divide(BigDecimal.valueOf(0.03), 2));
					}
					garchoi.setPrimeNetteProrata(prime);
					garchoi.setPrimeNetteAnnuelle(primeAnnuelle);
					garchoi.setPrimeAnnuelle(primeNetteAnnuelle);
					garchoi.setCodeAvenantAuto(Avn.getNumAvenant());

					garchoi.setBonus(BigDecimal.ZERO);
					garchoi.setMalus(BigDecimal.ZERO);
					garchoi.setReductionSocioProf(BigDecimal.ZERO);
					garchoi.setReductionPermis(BigDecimal.ZERO);
					garchoi.setReductionCommercial(BigDecimal.ZERO);
					garchoi.setAutre(BigDecimal.ZERO);
					garchoi.setMontantReduction(BigDecimal.ZERO);

					garchoi.setAccessoireauto(BigDecimal.ZERO);
					// garchoi.setMontantGarantieIa(G.getMontantGarantie());
					// garchoi.setTaux(new Float(G.getTaux()));
					// Ajout taux de la franchise

					// ajout de garantie choisie
					try {
						getObjectService().addObject(garchoi);
					} catch (Exception e) {
						//logs.error("Error add GarantieChoisieAuto", e);
					}

					// ajout de garantie garantie choisie
					for (GarantieGarantieChoisie GC : garantieGarantieChoisieList) {

						try {
							getObjectService().addObject(GC);
						} catch (Exception e) {
							//logs.error("Error add GarantieGarantieChoisieAuto", e);
						}
					}

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				setWarnMsg("pbe à l'enregistrement des véhicules et leurs garanties <br/> <<"
						+ e.getMessage() + ">>");
				RequestContext.getCurrentInstance().execute("NoSlctd.show()");

			}
		}

		public void gestionAvenantModifContrat() {

			Date date = new Date();
			date = Calendar.getInstance().getTime();

			for (VehiculeRow F : getVehiculeList()) {

				Vehicule V = new Vehicule();


				int index = getObjectService().getObjects("Vehicule")
						.lastIndexOf(V);
				V = (Vehicule) getObjectService().getObjects("Vehicule").get(
						index);

				System.out.println("index du vehicule:::::::::::" + index);
				System.out.println("id du dernier vehicule:::::::::::" + V.getCodeVehicule());
				// int v1 = V.getId();

				// add Garantiechoisie
				for (int i = 0; i < F.getListGarantieparVehicule().size(); i++) {
					GarantieChoisie garchoi = new GarantieChoisie();
					garchoi = F.getListGarantieparVehicule().get(i)
							.getGarantieChoisie();

					int k = getObjectService().getojects("Vehicule").size();
					// garchoi.setId(k+1);
					// int v1 = getObjectService().getObjects("Vehicule").size();
					// F.getVehi().setId(v1);
					garchoi.setVehicule(F.getVehi());
					garchoi.setDateGarantieChoisie(date);

					getObjectService().addObject(garchoi);

					// enreg de GarantieGarantieChoisie
					for (int j = 0; j < F.getListGarantieparVehicule().get(i)
							.getGarantieGarantieChoisieList().size(); j++) {

						GarantieGarantieChoisie GarantieGarantieChoisie = new GarantieGarantieChoisie();
						GarantieGarantieChoisie = F.getListGarantieparVehicule()
								.get(i).getGarantieGarantieChoisieList().get(j);
						GarantieGarantieChoisie.setId(F
								.getListGarantieparVehicule().get(i)
								.getGarantieGarantieChoisieIdList().get(j));
						GarantieGarantieChoisie
								.setDateGarantieGarantieChoisie(date);

						getObjectService().addObject(GarantieGarantieChoisie);
					}
				}

			}

			// enregistrement des Garanties

		}

		public void resetManagedCartegrise() {
			slctdVehiRw = new VehiculeRow();
			slctdVehiRwTb = new VehiculeRow();
			warnMsg = "";
			slctdville = null;
			vehiculeList = new ArrayList<VehiculeRow>();
			editGarEtat = true;
			validVehiEtat = true;

		}

		public void onRowSelect() {
			System.out.println("Inside onRow select...........................");
			setSlctdVehiRw(getSlctdVehiRwTb());
			setValidVehiEtat(false);
			setEditGarEtat(false);
			System.out.println("...........................onRow select well done");
			System.out
					.println("...........................souscatvehi ofSlctdVehiEtat"
							+ getSlctdVehiRw().getVehi().getCodeVehicule());
		}

		public void onRowUnSelect() {
			setSlctdVehiRw(new VehiculeRow());
			setValidVehiEtat(false);
		}

		public void ajoutVehicule() {
			int numOrdre = 0;
			numOrdre = getVehiculeList().size() + 1;
			VehiculeRow vehiculeRow = new VehiculeRow();
			vehiculeRow.setConduHab(conduHab);
			setSlctdVehiRw(vehiculeRow);
			getSlctdVehiRw().setNumOrdr(numOrdre);
		}
		

		public String handleflow(FlowEvent event) {
		
			String oldStep = event.getOldStep();
			String newStep = event.getNewStep();
			String a = newStep;
			// TRAITEMENT POUR LE PASSAGE DE Immat A categorie
			if (newStep.equalsIgnoreCase("ongletCategorie")
					&& oldStep.equalsIgnoreCase("ongletImmatriculation")) {
				System.out.println("TRAITEMENT POUR LE PASSAGE DE Immat A categorie");
				//V�rification des conditions
				if (getSlctdVehiRw().getSouCatVehi().getCodeSousCatVehicule().equalsIgnoreCase("")) {
							
			//on reste sur la partie
					FacesMessage msg = new FacesMessage(
							"Cat�gorie du vehicule non renseign�e");
					FacesContext.getCurrentInstance().addMessage(null, msg);
					a = oldStep;
			}
			}
			// TRAITEMENT POUR LE PASSAGE DE VEHICULE A QUITTANCE
			if (newStep.equalsIgnoreCase("ongletCategorie")
					&& oldStep.equalsIgnoreCase("ongletVehicule")) {
				System.out.println("TRAITEMENT POUR LE PASSAGE DE categorie A vehicule");
			}
			
			// TRAITEMENT POUR LE PASSAGE DE VEHICULE A QUITTANCE
						if (newStep.equalsIgnoreCase("ongletVehicule")
								&& oldStep.equalsIgnoreCase("ongletConducteur")) {
							System.out.println("TRAITEMENT POUR LE PASSAGE DE Vehicule A conducteur");
						}
						
			// TRAITEMENT POUR LE PASSAGE DE VEHICULE A QUITTANCE
						if (newStep.equalsIgnoreCase("ongletVehicule")
								&& oldStep.equalsIgnoreCase("ongletConducteur")) {
							System.out.println("TRAITEMENT POUR LE PASSAGE du conducteur aux garantie");
							
						}
			//logs.info(">>>>/ END -handleflow-");
			return a;
		}

		public void verificationPropV() {
			if (getSlctdVehiRw().getVehi().getNumImmat().equalsIgnoreCase("")) {
				FacesMessage msg = new FacesMessage(
						"Saisir une Immatriculation Valide !");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}

		public void validerProp() {
			getSlctdVehiRw().getListeVehicules().clear();
			// setEditGarEtat(false);
			if (getSlctdVehiRw().getSouCatVehi().getCodeSousCatVehicule().equalsIgnoreCase("")
					&& (getSlctdVehiRw().getVehi().getPuissFisc()
							.equals(BigDecimal.ZERO) || getSlctdVehiRw().getVehi()
							.getPuissReelle().equals(BigDecimal.ZERO))
					|| getSlctdVehiRw().getZonGeo().getLibelleZoneGeo()
							.equalsIgnoreCase("")) {

				if (getSlctdVehiRw().getSouCatVehi().getCodeSousCatVehicule().equalsIgnoreCase("")) {
					FacesMessage msg = new FacesMessage(
							"Cat�gorie du vehicule non renseign�e");
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}

				if (getSlctdVehiRw().getVehi().getPuissFisc()
						.equals(BigDecimal.ZERO)
						&& getSlctdVehiRw().getVehi().getPuissReelle()
								.equals(BigDecimal.ZERO)) {
					FacesMessage msg = new FacesMessage(
							"Choisir une puissance fiscale ou R�elle");
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}

				if (getSlctdVehiRw().getZonGeo().getLibelleZoneGeo()
						.equalsIgnoreCase("")) {
					FacesMessage msg = new FacesMessage(
							"Choisir une zone geographique");
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}

			} else {

				System.out.println("ùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùù"
						+ getSlctdVehiRw().getSouCatVehi().getCodeSousCatVehicule());
				getSlctdVehiRw().getVehi().setSousCatVehicule(getSlctdVehiRw().getSouCatVehi());
				getSlctdVehiRw().getListeVehicules().clear();
				getSlctdVehiRw().getListeVehicules().add(getSlctdVehiRw());

			}
			System.out.println("NumOrdre de getSlctdVehiRw()=== "+getSlctdVehiRw().getNumOrdr());

		}
		public void modifProp() {
			getSlctdVehiRw().getListegaranties().clear();
		}
		
		
		public void supprimeProp() {
			if (getVehiculeList().contains(getSlctdVehiRw())) {
				getVehiculeList().remove(getSlctdVehiRw());
				ajoutVehicule();
			}else{
				ajoutVehicule();
			}
			//setSlctdVehiRw(new VehiculeRow());
			setButtonEditGar(true);
			setButtonAddVehicule(false);
			setButtonSavVehicule(true);
			setSlctdVehiRwTb(null);	
		}


		public void validerVehicule() {
			if (!getVehiculeList().contains(getSlctdVehiRw())) {
				getSlctdVehiRw().setNumOrdr(getVehiculeList().size() + 1);
				getVehiculeList().add(getSlctdVehiRw());

			}
			//setSlctdVehiRw(new VehiculeRow());
			setButtonEditGar(true);
			setButtonAddVehicule(false);
			setSlctdVehiRwTb(null);
			 System.out.println("ùùùùùùùùùùùùùùùùùù"+getVehiculeList().size());
		}

		public void chxVille() {
			Ville v = (Ville)getObjectService().getObjectById(getSlctdville(), "Ville");
			getSlctdVehiRw().setZonGeo(v.getZoneGeographique());
			// Methode lancé lorsque le user choisit la ville de circulation du
			// véhicule
			// Fctionnemt lorsque la ville est choisit la methode permet de setter
			// la zone Géo correspondante ds le slctdVehiRw
			//logs.info(">>>>/ INSIDE -chxVille-");
			//logs.info(">>>>/ set de la Zone Geo correspondante à la ville choisit ds SlctdVehiRw");
			
			//System.out.println("Ville "+getSlctdville().getLibelleVille());
		//	getSlctdVehiRw().setZonGeo(getSlctdville().getZoneGeographique());
			// System.out.println("ùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùùù"+getManagedContrat().getTypeContrat()+getManagedContrat().getBaremes());
			//logs.info(">>>>/ END -chxVille-");
		}
		
		public void chxNbrTran(){
			short nbrT = 0;
			nbrT = (short) (getSlctdVehiRw().getVehi().getNbrePlaceCab()+getSlctdVehiRw().getVehi().getNbrePlaceHorscab());
			getSlctdVehiRw().getVehi().setNbreTransporte(nbrT);
		}
		public void choixRemorque(){
			if(getRemorque().equalsIgnoreCase("oui")){
				getSlctdVehiRw().getVehi().setRemorque(true);
			}else{
				getSlctdVehiRw().getVehi().setRemorque(false);	
			}
			System.out.println("Statut Remorque :"+getSlctdVehiRw().getVehi().getRemorque());	
		}

		public void chxConducteur() {
			// Methode lancé lorsque le user saisit un numéro de conducteur
			// Fctionnemt lorsque le conducteur est choisi la methode permet de
			// vérifier en BD si un tel conducteur exite
			// Si oui ses valeur st chargées ds les champs
			// Si non le processus continue normalement
			//logs.info(">>>>/ INSIDE -chxConducteur-");
			cduc =  (Conducteur) getObjectService().getObjectById(getSlctdVehiRw().getConduHab().getNumCond(), "Conducteur");
			if (cduc != null) {
				getSlctdVehiRw().setConduHab(cduc);
			}
			//logs.info(">>>>/ END -chxConducteur-");
		}

		public void changedateImmat() {
			try {
				getSlctdVehiRw().getVehi().setDateImmatPrec(
						getSlctdVehiRw().getVehi().getDateImmat());
				getSlctdVehiRw().getVehi().setDatePremiereCirc(
						getSlctdVehiRw().getVehi().getDateImmat());
			} catch (Exception e) {

			}

		}

		public void changedateImmatprec() {
			try {

				getSlctdVehiRw().getVehi().setDatePremiereCirc(
						getSlctdVehiRw().getVehi().getDateImmatPrec());
			} catch (Exception e) {

			}

		}

		public void choixSousCat() {
			// default
			inputPr.setRendered(false);
			outputPr.setRendered(false);
			
			inputPf.setRendered(false);
			outputPf.setRendered(false);
			
			inputPlcCab.setRendered(false);
			outputPlcCab.setRendered(false);
			
			inputPlcHcab.setRendered(false);
			outputPlcHcab.setRendered(false);
			
			inputPoidV.setRendered(false);
			outputPoidV.setRendered(false);
			
			inputCu.setRendered(false);
			outputCu.setRendered(false);
			
			inputValNeuv.setRendered(false);
			outputValNeuv.setRendered(false);
			
			inputValVen.setRendered(false);
			outputValVen.setRendered(false);
			
			inputNbrTran.setRendered(false);
			outputNbrTran.setRendered(false);
			
		
			
			
			
			
			
			

			if (getSlctdVehiRw().getSouCatVehi().getCodeSousCatVehicule().equalsIgnoreCase("SCAT1")||getSlctdVehiRw().getSouCatVehi().getCodeSousCatVehicule().equalsIgnoreCase("SCAT10")) {
				
				inputPr.setRendered(false);
				outputPr.setRendered(false);
				
				inputPf.setRendered(true);
				outputPf.setRendered(true);
				
				inputPlcCab.setRendered(false);
				outputPlcCab.setRendered(false);
				
				inputPlcHcab.setRendered(false);
				outputPlcHcab.setRendered(false);
				
				inputPoidV.setRendered(false);
				outputPoidV.setRendered(false);
				
				inputCu.setRendered(false);
				outputCu.setRendered(false);
				
				inputValNeuv.setRendered(true);
				outputValNeuv.setRendered(true);
				
				inputValVen.setRendered(true);
				outputValVen.setRendered(true);
				
				inputNbrTran.setRendered(false);
				outputNbrTran.setRendered(false);
			}
			if (getSlctdVehiRw().getSouCatVehi().getCodeSousCatVehicule().equalsIgnoreCase("SCAT2")||getSlctdVehiRw().getSouCatVehi().getCodeSousCatVehicule().equalsIgnoreCase("SCAT3")) {
				inputPr.setRendered(false);
				outputPr.setRendered(false);
				
				inputPf.setRendered(false);
				outputPf.setRendered(false);
				
				inputPlcCab.setRendered(true);
				outputPlcCab.setRendered(true);
				
				inputPlcHcab.setRendered(true);
				outputPlcHcab.setRendered(true);
				
				inputPoidV.setRendered(false);
				outputPoidV.setRendered(false);
				
				inputCu.setRendered(true);
				outputCu.setRendered(true);
				
				inputValNeuv.setRendered(false);
				outputValNeuv.setRendered(false);
				
				inputValVen.setRendered(false);
				outputValVen.setRendered(false);
				
				inputNbrTran.setRendered(true);
				outputNbrTran.setRendered(true);
				
			}
			if (getSlctdVehiRw().getSouCatVehi().getCodeSousCatVehicule().equalsIgnoreCase("SCAT4")) {
				
				inputPr.setRendered(false);
				outputPr.setRendered(false);
				
				inputPf.setRendered(false);
				outputPf.setRendered(false);
				
				inputPlcCab.setRendered(true);
				outputPlcCab.setRendered(true);
				
				inputPlcHcab.setRendered(true);
				outputPlcHcab.setRendered(true);
				
				inputPoidV.setRendered(false);
				outputPoidV.setRendered(false);
				
				inputCu.setRendered(false);
				outputCu.setRendered(false);
				
				inputValNeuv.setRendered(false);
				outputValNeuv.setRendered(false);
				
				inputValVen.setRendered(false);
				outputValVen.setRendered(false);
				
				inputNbrTran.setRendered(true);
				outputNbrTran.setRendered(true);
			}
			if (getSlctdVehiRw().getSouCatVehi().getCodeSousCatVehicule().equalsIgnoreCase("SCAT5")) {
				inputPr.setRendered(false);
				outputPr.setRendered(false);
				
				inputPf.setRendered(false);
				outputPf.setRendered(false);
				
				inputPlcCab.setRendered(false);
				outputPlcCab.setRendered(false);
				
				inputPlcHcab.setRendered(false);
				outputPlcHcab.setRendered(false);
				
				inputPoidV.setRendered(false);
				outputPoidV.setRendered(false);
				
				inputCu.setRendered(false);
				outputCu.setRendered(false);
				
				inputValNeuv.setRendered(false);
				outputValNeuv.setRendered(false);
				
				inputValVen.setRendered(false);
				outputValVen.setRendered(false);
				
				inputNbrTran.setRendered(false);
				outputNbrTran.setRendered(false);
				
			}
			if (getSlctdVehiRw().getSouCatVehi().getCodeSousCatVehicule().equalsIgnoreCase("SCAT6")||getSlctdVehiRw().getSouCatVehi().getCodeSousCatVehicule().equalsIgnoreCase("SCAT9")) {
				inputPr.setRendered(false);
				outputPr.setRendered(false);
				
				inputPf.setRendered(false);
				outputPf.setRendered(false);
				
				inputPlcCab.setRendered(false);
				outputPlcCab.setRendered(false);
				
				inputPlcHcab.setRendered(false);
				outputPlcHcab.setRendered(false);
				
				inputPoidV.setRendered(false);
				outputPoidV.setRendered(false);
				
				inputCu.setRendered(false);
				outputCu.setRendered(false);
				
				inputValNeuv.setRendered(false);
				outputValNeuv.setRendered(false);
				
				inputValVen.setRendered(false);
				outputValVen.setRendered(false);
				
				inputNbrTran.setRendered(false);
				outputNbrTran.setRendered(false);
				
			}
			if (getSlctdVehiRw().getSouCatVehi().getCodeSousCatVehicule().equalsIgnoreCase("SCAT7")||getSlctdVehiRw().getSouCatVehi().getCodeSousCatVehicule().equalsIgnoreCase("SCAT8")||
					getSlctdVehiRw().getSouCatVehi().getCodeSousCatVehicule().equalsIgnoreCase("SCAT12")	) {
				inputPr.setRendered(false);
				outputPr.setRendered(false);
				
				inputPf.setRendered(false);
				outputPf.setRendered(false);
				
				inputPlcCab.setRendered(false);
				outputPlcCab.setRendered(false);
				
				inputPlcHcab.setRendered(false);
				outputPlcHcab.setRendered(false);
				
				inputPoidV.setRendered(false);
				outputPoidV.setRendered(false);
				
				inputCu.setRendered(false);
				outputCu.setRendered(false);
				
				inputValNeuv.setRendered(false);
				outputValNeuv.setRendered(false);
				
				inputValVen.setRendered(false);
				outputValVen.setRendered(false);
				
				inputNbrTran.setRendered(false);
				outputNbrTran.setRendered(false);
				
			}
			
			
			if (getSlctdVehiRw().getSouCatVehi().getCodeSousCatVehicule().equalsIgnoreCase("SCAT10")) {
				inputPr.setRendered(false);
				outputPr.setRendered(false);
				
				inputPf.setRendered(false);
				outputPf.setRendered(false);
				
				inputPlcCab.setRendered(false);
				outputPlcCab.setRendered(false);
				
				inputPlcHcab.setRendered(false);
				outputPlcHcab.setRendered(false);
				
				inputPoidV.setRendered(false);
				outputPoidV.setRendered(false);
				
				inputCu.setRendered(false);
				outputCu.setRendered(false);
				
				inputValNeuv.setRendered(false);
				outputValNeuv.setRendered(false);
				
				inputValVen.setRendered(false);
				outputValVen.setRendered(false);
				
				inputNbrTran.setRendered(false);
				outputNbrTran.setRendered(false);
				
			}
		}

		public VehiculeRow getSlctdVehiRw() {
			return slctdVehiRw;
		}

		public void setSlctdVehiRw(VehiculeRow slctdVehiRw) {
			this.slctdVehiRw = slctdVehiRw;
		}

		public VehiculeRow getSlctdVehiRwTb() {
			return slctdVehiRwTb;
		}

		public void setSlctdVehiRwTb(VehiculeRow slctdVehiRwTb) {
			this.slctdVehiRwTb = slctdVehiRwTb;
		}

		public List<SousCatVehicule> getScategList() {
			return scategList;
		}

		public void setScategList(List<SousCatVehicule> scategList) {
			this.scategList = scategList;
		}

		public String getSlctdville() {
			return slctdville;
		}

		public void setSlctdville(String slctdville) {
			this.slctdville = slctdville;
		}

		public List<Ville> getVilleList() {
			List<Ville> A = new ArrayList<Ville>();
			for (Object c : getObjectService().getObjects("Ville")) {  
				A.add((Ville) c);  
	            } 
			
			return A;
		}

		public void setVilleList(List<Ville> villeList) {
			this.villeList = villeList;
		}

		public List<VehiculeRow> getVehiculeList() {
			return vehiculeList;
		}

		public void setVehiculeList(List<VehiculeRow> vehiculeList) {
			this.vehiculeList = vehiculeList;
		}

		public Boolean getEditGarEtat() {
			return editGarEtat;
		}

		public void setEditGarEtat(Boolean editGarEtat) {
			this.editGarEtat = editGarEtat;
		}

		public Boolean getValidVehiEtat() {
			return validVehiEtat;
		}

		public void setValidVehiEtat(Boolean validVehiEtat) {
			this.validVehiEtat = validVehiEtat;
		}

		public Conducteur getCduc() {
			return cduc;
		}

		public void setCduc(Conducteur cduc) {
			this.cduc = cduc;
		}

		public Conducteur getConduHab() {
			return conduHab;
		}

		public void setConduHab(Conducteur conduHab) {
			this.conduHab = conduHab;
		}

		public InputText getInputPf() {
			return inputPf;
		}

		public void setInputPf(InputText inputPf) {
			this.inputPf = inputPf;
		}

		public InputText getInputPr() {
			return inputPr;
		}

		public void setInputPr(InputText inputPr) {
			this.inputPr = inputPr;
		}

		public InputText getInputPlcCab() {
			return inputPlcCab;
		}

		public void setInputPlcCab(InputText inputPlcCab) {
			this.inputPlcCab = inputPlcCab;
		}

		public InputText getInputPlcHcab() {
			return inputPlcHcab;
		}

		public void setInputPlcHcab(InputText inputPlcHcab) {
			this.inputPlcHcab = inputPlcHcab;
		}

		public InputText getInputPoidV() {
			return inputPoidV;
		}

		public void setInputPoidV(InputText inputPoidV) {
			this.inputPoidV = inputPoidV;
		}

		public InputText getInputCu() {
			return inputCu;
		}

		public void setInputCu(InputText inputCu) {
			this.inputCu = inputCu;
		}

		public InputText getInputValNeuv() {
			return inputValNeuv;
		}

		public void setInputValNeuv(InputText inputValNeuv) {
			this.inputValNeuv = inputValNeuv;
		}

		public InputText getInputValVen() {
			return inputValVen;
		}

		public void setInputValVen(InputText inputValVen) {
			this.inputValVen = inputValVen;
		}

		public InputText getInputPlace() {
			return inputPlace;
		}

		public void setInputPlace(InputText inputPlace) {
			this.inputPlace = inputPlace;
		}

		public Boolean getButtonSavVehicule() {
			return buttonSavVehicule;
		}

		public void setButtonSavVehicule(Boolean buttonSavVehicule) {
			this.buttonSavVehicule = buttonSavVehicule;
		}

		public Boolean getButtonAddVehicule() {
			return buttonAddVehicule;
		}

		public void setButtonAddVehicule(Boolean buttonAddVehicule) {
			this.buttonAddVehicule = buttonAddVehicule;
		}

		public Boolean getButtonDelVehicule() {
			return buttonDelVehicule;
		}

		public void setButtonDelVehicule(Boolean buttonDelVehicule) {
			this.buttonDelVehicule = buttonDelVehicule;
		}

		public Boolean getButtonEditGar() {
			return buttonEditGar;
		}

		public void setButtonEditGar(Boolean buttonEditGar) {
			this.buttonEditGar = buttonEditGar;
		}

		public Boolean getButtonPropVehicule() {
			return buttonPropVehicule;
		}

		public void setButtonPropVehicule(Boolean buttonPropVehicule) {
			this.buttonPropVehicule = buttonPropVehicule;
		}

		public String getLibelleRisque() {
			return libelleRisque;
		}

		public void setLibelleRisque(String libelleRisque) {
			this.libelleRisque = libelleRisque;
		}

		public String getRenouvellement() {
			return renouvellement;
		}

		public void setRenouvellement(String renouvellement) {
			this.renouvellement = renouvellement;
		}

		public String getRemorque() {
			return remorque;
		}

		public void setRemorque(String remorque) {
			this.remorque = remorque;
		}

		public ObjectService getObjectService() {
			return objectService;
		}

		public void setObjectService(ObjectService objectService) {
			this.objectService = objectService;
		}

		public String getWarnMsg() {
			return warnMsg;
		}

		public void setWarnMsg(String warnMsg) {
			this.warnMsg = warnMsg;
		}

		public IdGenerateur getIdGenerateur() {
			return idGenerateur;
		}

		public void setIdGenerateur(IdGenerateur idGenerateur) {
			this.idGenerateur = idGenerateur;
		}

		public ScatVehiCvtr getScatVehiCvtr() {
			return scatVehiCvtr;
		}

		public void setScatVehiCvtr(ScatVehiCvtr scatVehiCvtr) {
			this.scatVehiCvtr = scatVehiCvtr;
		}

		public InputText getInputNbrTran() {
			return inputNbrTran;
		}

		public void setInputNbrTran(InputText inputNbrTran) {
			this.inputNbrTran = inputNbrTran;
		}

		public OutputLabel getOutputPf() {
			return outputPf;
		}

		public void setOutputPf(OutputLabel outputPf) {
			this.outputPf = outputPf;
		}

		public OutputLabel getOutputPr() {
			return outputPr;
		}

		public void setOutputPr(OutputLabel outputPr) {
			this.outputPr = outputPr;
		}

		public OutputLabel getOutputPlcCab() {
			return outputPlcCab;
		}

		public void setOutputPlcCab(OutputLabel outputPlcCab) {
			this.outputPlcCab = outputPlcCab;
		}

		public OutputLabel getOutputPlcHcab() {
			return outputPlcHcab;
		}

		public void setOutputPlcHcab(OutputLabel outputPlcHcab) {
			this.outputPlcHcab = outputPlcHcab;
		}

		public OutputLabel getOutputPoidV() {
			return outputPoidV;
		}

		public void setOutputPoidV(OutputLabel outputPoidV) {
			this.outputPoidV = outputPoidV;
		}

		public OutputLabel getOutputCu() {
			return outputCu;
		}

		public void setOutputCu(OutputLabel outputCu) {
			this.outputCu = outputCu;
		}

		public OutputLabel getOutputValNeuv() {
			return outputValNeuv;
		}

		public void setOutputValNeuv(OutputLabel outputValNeuv) {
			this.outputValNeuv = outputValNeuv;
		}

		public OutputLabel getOutputValVen() {
			return outputValVen;
		}

		public void setOutputValVen(OutputLabel outputValVen) {
			this.outputValVen = outputValVen;
		}

		public OutputLabel getOutputPlace() {
			return outputPlace;
		}

		public void setOutputPlace(OutputLabel outputPlace) {
			this.outputPlace = outputPlace;
		}

		public OutputLabel getOutputNbrTran() {
			return outputNbrTran;
		}

		public void setOutputNbrTran(OutputLabel outputNbrTran) {
			this.outputNbrTran = outputNbrTran;
		}

		


}
