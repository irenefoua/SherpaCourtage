package com.j3a.assurance.managedBean.admin;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.CompagnieAssurance;
import com.j3a.assurance.model.RcTarif2;
import com.j3a.assurance.model.RcTarif6;
import com.j3a.assurance.model.RcTarif7;
import com.j3a.assurance.model.SousCatVehicule;
import com.j3a.assurance.model.Tarif;
import com.j3a.assurance.model.Tarifweb;
import com.j3a.assurance.model.TarifwebSousCat;
import com.j3a.assurance.model.TarifwebSousCatId;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.utilitaires.IdGenerateur;

@Component
public class ManagedTarif7 implements Serializable{

	/**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		private  String code;
		private  String codetar;
		@Autowired
		ObjectService objectService;
		@Autowired
		IdGenerateur idGenerateur;
		private Tarif tarif = new Tarif();
		private RcTarif7 rcTarif7 = new RcTarif7();
		private CompagnieAssurance compagnieAssuranceConnecte=new CompagnieAssurance();
		private Tarifweb tarifweb=new Tarifweb();
		private TarifwebSousCatId tarifwebSousCatId=new TarifwebSousCatId();
		private TarifwebSousCat tarifwebSousCat=new TarifwebSousCat();
		
		
		public void PostConst(CompagnieAssurance compagnieAssurancee){
			setRcTarif7( (RcTarif7) getObjectService().getObjectById("R7"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "RcTarif7"));
			setTarif( (Tarif) getObjectService().getObjectById("T7"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "Tarif"));
		    setTarifweb((Tarifweb) getObjectService().getObjectById("T7"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "Tarifweb"));
			try { 
						if((rcTarif7 !=null) && (tarif !=null) && (tarifweb !=null) ){ 
						setRcTarif7(getRcTarif7());
						setTarif(tarif);
						setTarifweb(getTarifweb());
						}
						
						else{
						    rcTarif7=new RcTarif7();
							rcTarif7.setCodeRcTarif7("R7"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
							tarif=new Tarif();
							tarif.setCodeTarif("T7"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
							tarifweb=new Tarifweb();
							tarif.setCodeTarif("T7"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
						}
						
					} catch (NullPointerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("non ok !Enregistrement non recuper�e");		
				}
							
				
				}
		
		
		public  void enregistrer(){
		try{

			setCompagnieAssuranceConnecte(getObjectService().RecupererCompagnieCourrant());
			code ="R7"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"";
			codetar ="T7"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"";
		RcTarif7 rcTarif1Tempon = (RcTarif7) getObjectService().getObjectById(code, "RcTarif7");
		Tarif tarifTempon = (Tarif) getObjectService().getObjectById(codetar, "Tarif");
		
		if((rcTarif1Tempon==null) && (tarifTempon==null)){
			rcTarif7.setCodeRcTarif7("R7"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"");	
			getObjectService().addObject(rcTarif7);	
			tarif.setCodeTarif("T7"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"");
			tarif.setRcTarif7(rcTarif7);
			tarif.setLibelleTarif("Tarif 7");
			//tarif.setCompagnieAssurance(getCompagnieAssuranceConnecte());
			getObjectService().addObject(tarif);
			
			SousCatVehicule sousCatVehicule=new SousCatVehicule();
			sousCatVehicule.setCodeSousCatVehicule("SCAT7");
			
			tarifweb.setCodeTarifWeb(codetar);
			tarifweb.setCompagnieAssurance(getCompagnieAssuranceConnecte());
			tarifweb.setTarif(getTarif());
			tarifweb.setLibelleTarifWeb("Tarif 7");
			getObjectService().addObject(tarifweb);
			
			tarifwebSousCatId=new TarifwebSousCatId();
			tarifwebSousCatId.setCodeSousCatVehicule(sousCatVehicule.getCodeSousCatVehicule());
			tarifwebSousCatId.setCodeTarifWeb(getTarifweb().getCodeTarifWeb());
			
			tarifwebSousCat=new TarifwebSousCat();
			tarifwebSousCat.setId(tarifwebSousCatId);
			tarifwebSousCat.setSousCatVehicule(sousCatVehicule);
			tarifwebSousCat.setTarifweb(tarifweb);
			getObjectService().addObject(tarifwebSousCat);
			
			sousCatVehicule.getTarifwebSousCats().add(tarifwebSousCat);
			tarifweb.getTarifwebSousCats().add(tarifwebSousCat);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success", "Enregistrement effectu�"));
		}
		
		else {
			getObjectService().updateObject(rcTarif7);
			getObjectService().updateObject(tarif);
			getObjectService().updateObject(tarifweb);
			getObjectService().updateObject(tarifwebSousCat);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("succes", "La mise � jour a �t� bien effectu�e"));
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Echec", "Enregistrement non effectu�"));		

		}
		
	
		
		}

		
		
		
		
		
		

		
		
		
		

public CompagnieAssurance getCompagnieAssuranceConnecte() {
			return compagnieAssuranceConnecte;
		}


		public void setCompagnieAssuranceConnecte(
				CompagnieAssurance compagnieAssuranceConnecte) {
			this.compagnieAssuranceConnecte = compagnieAssuranceConnecte;
		}


		//getters et setters
		public ObjectService getObjectService() {
			return objectService;
		}



		public void setObjectService(ObjectService objectService) {
			this.objectService = objectService;
		}



		public IdGenerateur getIdGenerateur() {
			return idGenerateur;
		}



		public void setIdGenerateur(IdGenerateur idGenerateur) {
			this.idGenerateur = idGenerateur;
		}



		public Tarif getTarif() {
			return tarif;
		}



		public void setTarif(Tarif tarif) {
			this.tarif = tarif;
		}



		public RcTarif7 getRcTarif7() {
			return rcTarif7;
		}


		public void setRcTarif7(RcTarif7 rcTarif7) {
			this.rcTarif7 = rcTarif7;
		}













		public String getCode() {
			return code;
		}













		public void setCode(String code) {
			this.code = code;
		}













		public String getCodetar() {
			return codetar;
		}













		public void setCodetar(String codetar) {
			this.codetar = codetar;
		}


		public Tarifweb getTarifweb() {
			return tarifweb;
		}


		public void setTarifweb(Tarifweb tarifweb) {
			this.tarifweb = tarifweb;
		}


		public TarifwebSousCatId getTarifwebSousCatId() {
			return tarifwebSousCatId;
		}


		public void setTarifwebSousCatId(TarifwebSousCatId tarifwebSousCatId) {
			this.tarifwebSousCatId = tarifwebSousCatId;
		}


		public TarifwebSousCat getTarifwebSousCat() {
			return tarifwebSousCat;
		}


		public void setTarifwebSousCat(TarifwebSousCat tarifwebSousCat) {
			this.tarifwebSousCat = tarifwebSousCat;
		}



		
		
		
		
		/*private String slctdApporteur;
		private String slctdTypeApporteur;
		private String id;
		private Avenant avnt=new Avenant();
		private Personne persAvenant = new Personne();
	    private Exercice exercice = new Exercice();
		private String pointVenteid, pointVentelib,utilisateurTest;
		private Personne numSouscripteur = new Personne();
		private Risque codeRisque = new Risque();
		private String risqueid, risquelib;
		private String mentionParticuliere;
		private String typeContratid, typeContratlib;
		private String remplace;
		private String mouvement = "AFFAIRE NOUVELLE", typeContrat,
				modeReconduction;
		private String codeTypeContrats;
		private BigDecimal commission;
		private String bareme, baremeR;
        private SocieteAssurance societeAssurance = new SocieteAssurance();
		private String observation;
        private PointVente pointVente;
		public Personne getPersAvenant() {
			return persAvenant;
		}
		
		

		public void setPersAvenant(Personne persAvenant) {
			this.persAvenant = persAvenant;
		}
		
		private String numAvenant;
        private Risque risque;
		private String nature;
		private Date effet = Calendar.getInstance().getTime();
		private Date resiliation;
		private Date echeance;
		private double duree = 12;
		private Date dateContrats = Calendar.getInstance().getTime();
		private Date emission = Calendar.getInstance().getTime();
		private Date dateAv = Calendar.getInstance().getTime();
		private Date dateAvenant;
		private String critere;
		private Contrat monContrat = new Contrat();
        private Utilisateur utilisateur;
		private Avenant monAvenant = new Avenant();
		private Quittance maQuittance;
		private Quittance quittance=new Quittance();
		private String codePointVente;
		private PointVente rechPintVente;
		private Apporteur resultatApporteur;
		
		private Personne maPersonne;
		private Physique monPhysique;
		
		private String monStringPersonne;
		private String policeDeRecherche;
		private Date dateResiliation;
	
		private Vehicule selectedVehicule;
		private SousCatVehicule maSoucartegorieVehicule;
		private Vehicule vehiculeModifierGarantie;
		private Quittance quittanceAnnulation;
		private Long dureeEnjour;

		private ManagedGarantie garantiemb;
		private List<Vehicule> vehiculeContrat;
		List<Vehicule> listVehiAssures;
		List<Avenant> avenantList;
		Contrat contratss = new Contrat();
		Avenant avenantss = new Avenant();
		
		private SelectItem attestationCheck;

		// Contrat contrat = new Contrat();
		public Avenant avenant = new Avenant();

		private Contrat contrat = new Contrat();
		
		
		public Exercice getExerciceOuvert(){
			
			return (Exercice)getObjectService().exerciceOuvert();
		}
	
	   public void majExercice(){
		getObjectService().updateObject(getExercice());
	}

	   public void societe() {
			SocieteAssurance sta = getObjectService().recupererSteAssurance();
			societeAssurance.setCodeSocieteAssurance(sta.getCodeSocieteAssurance());
			societeAssurance.setAbrege(sta.getAbrege());
			societeAssurance.setAdressePostale(sta.getAdressePostale());
			societeAssurance.setDeviseUtilise(sta.getDeviseUtilise());
			societeAssurance.setNomSocieteAssurance(sta.getNomSocieteAssurance());

		}

		*//**
		 * Reset Fields
		 * 
		 *//*
		public void reset() {

			this.setId("");
			this.setMentionParticuliere("");
			this.setRemplace("");
			this.setBareme("");
			this.setObservation("");
			codeTypeContrats = "";
			contrat = new Contrat();
			avenant = new Avenant();

			//codeApporteur = new Apporteur();
			nature = "";
			effet = Calendar.getInstance().getTime();
			duree = 0;
			dateContrats = Calendar.getInstance().getTime();
			emission = Calendar.getInstance().getTime();

			
			 * FacesContext context = FacesContext.getCurrentInstance();
			 * 
			 * context.addMessage(null, new FacesMessage("Successful", "Hello "));
			 

		}

		public void createAvenant(Avenant avenant) {
		
			try {
				
				getObjectService().addObject(avenant);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	public void viderChamp(){
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		sessionMap.remove("avenantMB");
		sessionMap.remove("contratMB");
		sessionMap.remove("societeAssuranceMB");

	}
		
	public Utilisateur utlsateur() {
		try {
			Utilisateur user;
			user=(Utilisateur) getObjectService().getObjectById("WEB1", "Utilisateur");
			utilisateur.setCodeUtilisateur(user.getCodeUtilisateur());
			utilisateur.setLoginUtilisateur(user.getLoginUtilisateur());
			utilisateur.setMailUtilisateur(user.getMailUtilisateur());
			utilisateur.setMotPasse(user.getMotPasse());
			utilisateur.setNomUtilisateur(user.getNomUtilisateur());
			utilisateur.setPrenomUtilisateur(user.getPrenomUtilisateur());
			utilisateur.setDateCreationUtilisateur(user
					.getDateCreationUtilisateur());
			utilisateur.setPointVente(user.getPointVente());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

		
		public ObjectService getObjectService() {
			return objectService;
		}

		*//**
		 * Set Contrat Service
		 * 
		 * @param IContratService
		 *            - Contrat Service
		 *//*
		public void setObjectService(ObjectService objectService) {
			this.objectService = objectService;
		}

		

		public void recupererPersonne() {
			setMaPersonne(monContrat.getPersonne());
			Object personnePhysique = getObjectService().getObjectById(monContrat.getPersonne().getNumSouscripteur(), "Physique");
		}

		public String addContrat() {
			try {
				getObjectService().addObject(contrat);
				//logger.info("Enregistrement des informations de base du contrat");
	
				getObjectService().addObject(avenant);
				

				System.out.println("Enregistrement de l'avenant");
				//logger.info("Enregistrement de l'avenant affaire nouvelle");
				return "contratOk";
			} catch (DataAccessException e) {

				//logger.error("echec de l'enregistrement");
				e.printStackTrace();
			}

			return "contratOk";
		}	

		// ------------------------- Here Setter and Getter
		// ----------------------------//

		public void setId(String id) {
			this.id = id;
		}
		
		public String getId(){
			return id;
		}
		
		

		public Personne getNumSouscripteur() {
			return numSouscripteur;
		}

		public void setNumSouscripteur(Personne numSouscripteur) {
			this.numSouscripteur = numSouscripteur;
		}

		public String getMentionParticuliere() {
			return mentionParticuliere;
		}

		public void setMentionParticuliere(String mentionParticuliere) {
			this.mentionParticuliere = mentionParticuliere;
		}

		public String getRemplace() {
			return remplace;
		}

		public void setRemplace(String remplace) {
			this.remplace = remplace;
		}

		

		
		public Contrat getContrat() {
			return contrat;
		}

		public void setContrat(Contrat contrat) {
			this.contrat = contrat;
		}

		public Date getDateContrats() {
			return dateContrats;
		}

		public void setDateContrats(Date dateContrats) {
			this.dateContrats = dateContrats;
		}

		public String getCodeTypeContrats() {
			return codeTypeContrats;
		}

		public void setCodeTypeContrats(String codeTypeContrats) {
			this.codeTypeContrats = codeTypeContrats;
		}

		*//**
		 * Generate Setter and Getter
		 * 
		 * @param List
		 *            - Contrat List
		 *//*

		public Risque getCodeRisque() {
			return codeRisque;
		}

		public void setCodeRisque(Risque codeRisque) {
			this.codeRisque = codeRisque;
		}

		public BigDecimal getCommission() {
			return commission;
		}

		public void setCommission(BigDecimal commission) {
			this.commission = commission;
		}

		public String getBareme() {
			return bareme;
		}

		public void setBareme(String bareme) {
			this.bareme = bareme;
		}

		public String getObservation() {
			return observation;
		}

		public void setObservation(String observation) {
			this.observation = observation;
		}

		public Date getEffet() {
			return effet;
		}

		public void setEffet(Date effet) {
			this.effet = effet;
		}

		public Date getEmission() {
			return emission;
		}

		public void setEmission(Date emission) {
			this.emission = emission;
		}

		public Date getEcheance() {
			majEcheance();
			return echeance;
		}

		
		public void majEcheance() {
			Calendar calender = Calendar.getInstance();
			calender.setTime(effet);

			if (duree == 0.5) {
				calender.add(Calendar.DATE, 15);
			} else {
				calender.add(Calendar.MONTH, (int) getDuree());
			}
			calender.add(Calendar.DATE, -1);
			calender.set(Calendar.HOUR_OF_DAY, 23);
			calender.set(Calendar.MINUTE, 59);
			setEcheance(calender.getTime());
			System.out.println(" nbjour :" + getDureeEnjour());
			
		}
		
		
		public Date getDateEcheance(Date effets, short durees) {
			Calendar calender = Calendar.getInstance();
			calender.setTime(effets);

			calender.add(Calendar.DATE, durees - 1);
			calender.set(Calendar.HOUR_OF_DAY, 23);
			calender.set(Calendar.MINUTE, 59);
			return calender.getTime();
		}

		public void ConvertDureeEnjour() {
			Long nbjour = null;
			int temp = 0;
			if (duree == 0.5) {
				nbjour = (long) 15;
			} else {

				Calendar dateDebut = Calendar.getInstance();
				dateDebut.setTime(effet);

				Calendar dateEnd = Calendar.getInstance();
				dateEnd.setTime(echeance);

				Long millDebut = dateDebut.getTimeInMillis();
				Long millEnd = dateEnd.getTimeInMillis() ;
				//+ (long)86400000
				Long diff = millEnd - millDebut;

				nbjour = diff / (24 * 60 * 60 * 1000);
			}
			setDureeEnjour(nbjour);
		}

		public void recupererPointVente() {
			try {
				setRechPintVente((PointVente) getObjectService().getObjectById(
						monContrat.getPointVente().getCodePointVente(), "PointVente"));
			} catch (Exception e) {
			}
		}
		
		public Long getDureeEnjour() {
			ConvertDureeEnjour();
			return dureeEnjour;
		}

		public void setDureeEnjour(Long dureeEnjour) {
			this.dureeEnjour = dureeEnjour;
		}

		public void setEcheance(Date echeance) {
			this.echeance = echeance;
		}

		public Date getResiliation() {
			return resiliation;
		}

		public void setResiliation(Date resiliation) {
			this.resiliation = resiliation;
		}

		public double getDuree() {
			return duree;
		}

		public void setDuree(double duree) {
			this.duree = duree;
		}

		public Date getdateAvenant() {
			Date dat = Calendar.getInstance().getTime();
			setdateAvenant(dat);
			return dateAvenant;
		}

		public String getBaremeR() {
			return baremeR;
		}

		public void setBaremeR(String baremeR) {
			this.baremeR = baremeR;
		}

		public String getPointVenteid() {
			return pointVenteid;
		}

		public void setPointVenteid(String pointVenteid) {
			this.pointVenteid = pointVenteid;
		}

		public String getPointVentelib() {
			return pointVentelib;
		}

		public void setPointVentelib(String pointVentelib) {
			this.pointVentelib = pointVentelib;
		}

		public String getRisqueid() {
			return risqueid;
		}

		public void setRisqueid(String risqueid) {
			this.risqueid = risqueid;
		}

		public String getRisquelib() {
			return risquelib;
		}

		public void setRisquelib(String risquelib) {
			this.risquelib = risquelib;
		}

		public String getTypeContratid() {
			return typeContratid;
		}

		public void setTypeContratid(String typeContratid) {
			this.typeContratid = typeContratid;
		}

		public String getTypeContratlib() {
			return typeContratlib;
		}

		public void setTypeContratlib(String typeContratlib) {
			this.typeContratlib = typeContratlib;
		}

		public String getNature() {
			return nature;
		}

		public void setNature(String nature) {
			this.nature = nature;
		}

		public void setdateAvenant(Date dateAvenant) {
			this.dateAvenant = dateAvenant;
		}

		public String getNumAvenant() {
			return numAvenant;
		}

		public void setNumAvenant(String numAvenant) {
			this.numAvenant = numAvenant;
		}

		public Avenant getMonAvenant() {
			return monAvenant;
		}

		public void setMonAvenant(Avenant monAvenant) {
			this.monAvenant = monAvenant;
		}

		public Contrat getContratss() {
			return contratss;
		}

		public void setContratss(Contrat contratss) {
			this.contratss = contratss;
		}

		public Avenant getAvenantss() {
			return avenantss;
		}

		public void setAvenantss(Avenant avenantss) {
			this.avenantss = avenantss;
		}

		public PointVente getRechPintVente() {
			return rechPintVente;
		}

		public void setRechPintVente(PointVente rechPintVente) {
			this.rechPintVente = rechPintVente;
		}

		public Apporteur getResultatApporteur() {
			return resultatApporteur;
		}

		public void setResultatApporteur(Apporteur resultatApporteur) {
			this.resultatApporteur = resultatApporteur;
		}

		

		@SuppressWarnings("deprecation")
		public void changeDate() {
			int d = (int) (duree * 30);
			this.echeance.setDate(effet.getDate() + (d - 1));
		}

		
		public Physique getMonPhysique() {
			return monPhysique;
		}

		public void setMonPhysique(Physique monPhysique) {
			this.monPhysique = monPhysique;
		}

		

		public String getMonStringPersonne() {
			return monStringPersonne;
		}

		public void setMonStringPersonne(String monStringPersonne) {
			this.monStringPersonne = monStringPersonne;
		}

		

		public Vehicule getSelectedVehicule() {
			return selectedVehicule;
		}

		public void setSelectedVehicule(Vehicule selectedVehicule) {
			// garantiemb.afficherGarantiesChoisies(); //afficher les Garanties
			// Choisies (M�thode depuis ManagedContrat);
			this.selectedVehicule = selectedVehicule;
		}

		public List<Vehicule> getListVehiAssures() {
			return listVehiAssures;
		}

		public void setListVehiAssures(List<Vehicule> listVehiAssures) {
			this.listVehiAssures = listVehiAssures;
		}

		public Personne getMaPersonne() {
			return maPersonne;
		}

		public void setMaPersonne(Personne maPersonne) {
			this.maPersonne = maPersonne;
		}

		public ManagedGarantie getGarantiemb() {
			return garantiemb;
		}

		public void setGarantiemb(ManagedGarantie garantiemb) {
			this.garantiemb = garantiemb;
		}

		public Vehicule getVehiculeModifierGarantie() {
			return vehiculeModifierGarantie;
		}

		public void setVehiculeModifierGarantie(Vehicule vehiculeModifierGarantie) {
			this.vehiculeModifierGarantie = vehiculeModifierGarantie;
		}

		public SousCatVehicule getMaSoucartegorieVehicule() {
			return maSoucartegorieVehicule;
		}

		public void setMaSoucartegorieVehicule(
				SousCatVehicule maSoucartegorieVehicule) {
			this.maSoucartegorieVehicule = maSoucartegorieVehicule;
		}

		
		 * public CategorieProfConducteur getMaCategorieProf() { return
		 * maCategorieProf; }
		 * 
		 * public void setMaCategorieProf(CategorieProfConducteur maCategorieProf) {
		 * this.maCategorieProf = maCategorieProf; }
		 

		public Quittance getMaQuittance() {
			return maQuittance;
		}

		public void setMaQuittance(Quittance maQuittance) {
			this.maQuittance = maQuittance;
		}

		public Avenant getAvenant() {
			return avenant;
		}

		public void setAvenant(Avenant avenant) {
			this.avenant = avenant;
		}

		
		

		public String getPoliceDeRecherche() {
			return policeDeRecherche;
		}

		public void setPoliceDeRecherche(String policeDeRecherche) {
			this.policeDeRecherche = policeDeRecherche;
		}

		public String getCritere() {
			return critere;
		}

		public void setCritere(String critere) {
			this.critere = critere;
		}

		public Date getDateResiliation() {
			return dateResiliation;
		}

		public void setDateResiliation(Date dateResiliation) {
			this.dateResiliation = dateResiliation;
		}

		public Contrat getMonContrat() {
			return monContrat;
		}

		public void setMonContrat(Contrat monContrat) {
			this.monContrat = monContrat;
		}

		

		public List<Vehicule> getVehiculeContrat() {

			return vehiculeContrat;
		}

		public void setVehiculeContrat(List<Vehicule> vehiculeContrat) {
			this.vehiculeContrat = vehiculeContrat;
		}


		public String getModeReconduction() {
			return modeReconduction;
		}

		public void setModeReconduction(String modeReconduction) {
			this.modeReconduction = modeReconduction;
		}

		public String getMouvement() {

			return mouvement;
		}

		public void setMouvement(String mouvement) {
			this.mouvement = mouvement;
		}

		public String getTypeContrat() {
			return typeContrat;
		}

		public void setTypeContrat(String typeContrat) {
			this.typeContrat = typeContrat;
		}

		public String getUtilisateurTest() {
			setUtilisateurTest(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());
			return utilisateurTest;
		}

		public void setUtilisateurTest(String utilisateurTest) {
			this.utilisateurTest = utilisateurTest;
		}
		
		
		public IdGenerateur getIdGenerateur() {
			return idGenerateur;
		}

		


		public Date getDateAvenant() {
			return dateAvenant;
		}



		public void setDateAvenant(Date dateAvenant) {
			this.dateAvenant = dateAvenant;
		}



		public List<Avenant> getAvenantList() {
			return avenantList;
		}



		public void setAvenantList(List<Avenant> avenantList) {
			this.avenantList = avenantList;
		}



		public void setIdGenerateur(IdGenerateur idGenerateur) {
			this.idGenerateur = idGenerateur;
		}

		public SelectItem getAttestationCheck() {
			return attestationCheck;
		}

		public void setAttestationCheck(SelectItem attestationCheck) {
			this.attestationCheck = attestationCheck;
		}

		public Quittance getQuittanceAnnulation() {
			return quittanceAnnulation;
		}

		public void setQuittanceAnnulation(Quittance quittanceAnnulation) {
			this.quittanceAnnulation = quittanceAnnulation;
		}

		public Avenant getAvnt() {
			return avnt;
		}

		public void setAvnt(Avenant avnt) {
			this.avnt = avnt;
		}

		public Quittance getQuittance() {
			return quittance;
		}

		public void setQuittance(Quittance quittance) {
			this.quittance = quittance;
		}

		


		public Date getDateAv() {
			return dateAv;
		}

		public void setDateAv(Date dateAv) {
			this.dateAv = dateAv;
		}

		

		public String getSlctdTypeApporteur() {
			return slctdTypeApporteur;
		}

		public void setSlctdTypeApporteur(String slctdTypeApporteur) {
			this.slctdTypeApporteur = slctdTypeApporteur;
		}

		

		public String getSlctdApporteur() {
			return slctdApporteur;
		}

		public void setSlctdApporteur(String slctdApporteur) {
			this.slctdApporteur = slctdApporteur;
		}

		

		public Exercice getExercice() {
			return exercice;
		}

		public void setExercice(Exercice exercice) {
			this.exercice = exercice;
		}

		

		


		public SocieteAssurance getSocieteAssurance() {
			societe();
			return societeAssurance;
		}



		public void setSocieteAssurance(SocieteAssurance societeAssurance) {
			this.societeAssurance = societeAssurance;
		}



		public PointVente getPointVente() {
			return pointVente;
		}



		public void setPointVente(PointVente pointVente) {
			this.pointVente = pointVente;
		}



		public Risque getRisque() {
			Risque risque=(Risque) getObjectService().getObjectById("1", "Risque");
			return risque;
		}



		public void setRisque(Risque risque) {
			this.risque = risque;
		}


		public Utilisateur getUtilisateur() {
			utilisateur=(Utilisateur) getObjectService().getObjectById("WEB1", "Utilisateur");
			return utilisateur;
		}



		public void setUtilisateur(Utilisateur utilisateur) {
			this.utilisateur = utilisateur;
		}



		public String getCodePointVente() {
			return codePointVente;
		}



		public void setCodePointVente(String codePointVente) {
			this.codePointVente = codePointVente;
		}
*/
		
	}



