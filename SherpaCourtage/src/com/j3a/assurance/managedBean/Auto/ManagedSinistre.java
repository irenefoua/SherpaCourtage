package com.j3a.assurance.managedBean.Auto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.j3a.assurance.model.ActeMedical;
import com.j3a.assurance.model.Avenant;
import com.j3a.assurance.model.Conducteur;
import com.j3a.assurance.model.ConducteurSinistre;
import com.j3a.assurance.model.ConduireVehicule;
import com.j3a.assurance.model.Contrat;
import com.j3a.assurance.model.Facture;
import com.j3a.assurance.model.Garantie;
import com.j3a.assurance.model.Intervenant;
import com.j3a.assurance.model.Intervention;
import com.j3a.assurance.model.Medecin;
import com.j3a.assurance.model.Personne;
import com.j3a.assurance.model.Sinistre;
import com.j3a.assurance.model.SinistreConducteur;
import com.j3a.assurance.model.SinistreConducteurId;
import com.j3a.assurance.model.Vehicule;
import com.j3a.assurance.model.VehiculeSinistre;
import com.j3a.assurance.model.VehiculeSinistreId;
import com.j3a.assurance.model.VehiculesAssures;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.utilitaires.ContratResum;
import com.j3a.assurance.utilitaires.IdGenerateur;

@Component
public class ManagedSinistre implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static  Logger logger=Logger.getLogger(ManagedSinistre.class);
	@Autowired
	private IdGenerateur idGenerateur;
	@Autowired
	private ObjectService objectService;

	private Sinistre sinistre = new Sinistre();
	private ConducteurSinistre conducteurSinistre = new ConducteurSinistre();
    private VehiculeSinistre vehiculeSinistre = new VehiculeSinistre();
    private Conducteur conducteur;
	private String optionRech ="1"; 
	private String optionConducteur = "1";
	private String valeurDeRecherche;
	private ContratResum monContratResum;
	private List<ContratResum> listeContratResum = new ArrayList<>();
	private List<Sinistre> listSinistre = new ArrayList<Sinistre>();
	private List<ContratResum> listContratVehicule = new ArrayList<>();
	private ContratResum selectedContratResum;
	private ContratResum selectedContratResumVeh = new ContratResum();
	private Intervention intervention = new Intervention();
	private Intervenant intervenant = new Intervenant();
	private ActeMedical acteMedical = new ActeMedical();
	private Medecin medecin = new Medecin();
	private Facture facAct = new Facture();
	private Facture facPv = new Facture();
	private List<Sinistre> listSinistreEnCours = new ArrayList<Sinistre>();
	private Date dateDeclaration = Calendar.getInstance().getTime();
	private boolean bPv, bRap, bActMed;
	private boolean etatPv=true, etatRap=true, etatActMed=true;
	private String numPolice = "";
	private String codeVehicule;
	private Facture facture;
    private String libelleDomageCorp;
	private String typeFiltre;
	private String filtre;
	private String idIntervenant, idMedecin;
	private List<SelectItem> elementsNomInt;
	private List<SelectItem> elementsNomMed;
	private UploadedFile filePv;
	private UploadedFile fileRap;
	private UploadedFile fileActMed;
	private UIComponent buttonadd;
	private static String FILE = "c:/upload/temp/default.pdf";
	private String destination = "c:\\upload\\temp\\";
	private java.lang.String typeRapport;
	private String nomfichier;
   private BigDecimal rcAssure=new BigDecimal(0);
   private static final int DEFAULT_BUFFER_SIZE = 10240000;
   private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.NORMAL, BaseColor.RED);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.BOLD);

	
		Boolean etatRecherche = false;
		private Contrat resultacontrat = new Contrat();
		private Personne resultatPersonne = new Personne();
		private List<Garantie> listeGarantieSelected = new ArrayList<>();
	
		
		public String onFlowProcess(FlowEvent event) {
		 String oldStep = event.getOldStep();
		 String newStep = event.getNewStep();
		 
	        if(selectedContratResum != null) {
	        	 return event.getNewStep();
	        }else{
	        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Veuillez selectionner le vehicule"));
	        	return event.getOldStep();
	        }
	    }
	

	public void creeSinitre(){//By Alekerand
		
		//Declaration necessaire pour la création du sinistre
		vehiculeSinistre = new VehiculeSinistre();
        SinistreConducteur sinistreConducteur = new SinistreConducteur();
		
		//Generation du code du sinistre
	sinistre.setCodeSinistre(getIdGenerateur().getIdSinistre(getMonContratResum().getPoliceResum()));
	sinistre.setContrat(getMonContratResum().getPoliceResum());
	sinistre.setDateDeclaration(dateDeclaration);
	

	//Créer le conducteur du sinistre
		conducteurSinistre.setCodeConducteurSinistre(getIdGenerateur().getIdCondSinistre());
		//Creer vehiculeSinistre
        VehiculeSinistreId vehiculeSinistreId = new VehiculeSinistreId(getSinistre().getCodeSinistre(), getMonContratResum().getVehiculeResum().getCodeVehicule());
        vehiculeSinistre.setId(vehiculeSinistreId);
        

		//créer ConducteurSinistre
		SinistreConducteurId sinistreConducteurId = new SinistreConducteurId(getConducteurSinistre().getCodeConducteurSinistre(), getSinistre().getCodeSinistre());
        sinistreConducteur.setId(sinistreConducteurId);
		sinistreConducteur.setDateConducteurSinistre(Calendar.getInstance().getTime());

		//Enregistrement
		getObjectService().addObject(sinistre);
		getObjectService().addObject(conducteurSinistre);
		getObjectService().addObject(vehiculeSinistre);
        getObjectService().addObject(sinistreConducteur);
        
        //mettre à jour les mail et le téléphone
        	getObjectService().updateObject(monContratResum.getPersonneResum()); 
        	soumettre();
        	
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sinistre déclaré et enregistré!", ""));
        //Vder le formulaire
        viderObjet();
	}
	
	
	public void viderObjet(){
		//Vider la recherche
		setValeurDeRecherche("");
		//Effacer le sinistre
		sinistre.setDateSurvenance(null);
		sinistre.setDateDeclaration(null);
		sinistre.setCirconstance(null);
		
		//Effacer conducteur sinistre
		conducteurSinistre.setNomCondSinistre(null);
		conducteurSinistre.setPrenomCondSinistre(null);
		conducteurSinistre.setDateNaissCondSinistre(null);
		conducteurSinistre.setNumPermis(null);
		conducteurSinistre.setCategoriePermis(numPolice);
		conducteurSinistre.setAdresse(null);
		conducteurSinistre.setTelFixe(null);
		conducteurSinistre.setTelCell(null);
		conducteurSinistre.setEmail(null);
		
		//Vider VehiculeSininistre
		vehiculeSinistre.setLieuSinistre(numPolice);
		vehiculeSinistre.setNbreBlesse(null);
		vehiculeSinistre.setNbreDeces(null);
		vehiculeSinistre.setRcAssure(null);
		vehiculeSinistre.setLibelleDomageCorp(null);
		vehiculeSinistre.setLibelleDomageMat(null);
		
		setMonContratResum(null);
		//Vider l'objet contratresum
				
		//Vider la liste 
		listeContratResum.clear();	
		listContratVehicule.clear();
	}
	
	@Transactional
	public void rechercherDossierProduction(){//By ALekerand
		//Vider la liste a chaque fois que la methode est appele
		listeContratResum.clear();
		
		monContratResum = new ContratResum();
		if(optionRech.equalsIgnoreCase(null)){
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info", "Faites un choix!"));

		}else{
		switch (optionRech) {
		
		case "1"://Recherche par le num police
			//recherche le contrat
			Contrat contrat = new Contrat();
			contrat = (Contrat) getObjectService().getObjectById(valeurDeRecherche, "Contrat");
			
			//Si le contrat existe recuperer les autres infos
			if(!(contrat==null)){
				ContratResum contratResum = new ContratResum();
				contratResum.setPoliceResum(contrat);
				
				//Recuperer le souscripteur
				contratResum.setPersonneResum(contrat.getPersonne());
				
				//Nature de la personne
				if(contrat.getPersonne().getPhysique() != null){
					contratResum.setPhysiqueResum(contrat.getPersonne().getPhysique());
				}else{
					contratResum.setMoraleResum(contrat.getPersonne().getMorale());
				}
				
				//recuperer le dernier avenant
				 contratResum.setAvenantResum(getObjectService().DernierAvenant(contrat.getNumPolice()));
				 
				 //Recuperation de la liste de vehicule
				 VehiculesAssures vehiculesAssures = contratResum.getAvenantResum().getVehiculesAssures();
				 for(Vehicule vat: vehiculesAssures.getVehicules()){

					//Ajouter le Vehicule
					contratResum.setVehiculeResum(vat);
				}
				 
				 //Recuperer le conducteur
				 contratResum.setConducteurResum(recupererCondHabituel(contratResum.getVehiculeResum()));
				 
				  //Editer le conducteur sinistre
				 chargerConducteurSinistre(contratResum.getConducteurResum(), conducteurSinistre);
				 
				 setMonContratResum(contratResum);
				 listeContratResum.add(contratResum);
			}
			
			break;
		
		case"2"://Recherche par l'imatricultion vehicule
			listContratVehicule.clear();
			
			//Rechercher le vehicule
			 List<Vehicule> vehiculeList = getObjectService().trouverVehicules(valeurDeRecherche);
			//Recuperer tous les contrat lier à cette imatriculation
			for(Vehicule vehicul : vehiculeList){
				ContratResum contratResum = new ContratResum();
				contratResum.setVehiculeResum(vehicul);
				
				//Recuperer vehicule_Assure
				VehiculesAssures vehiculesAssures = vehicul.getVehiculesAssures();
				//Recuperer la liste des avenants
				List<Avenant> listeAvenant = new ArrayList<Avenant>();
				for(Avenant avenant:vehiculesAssures.getAvenants()){
					listeAvenant.add(avenant);
				}
				
				//Recuperer le Contrat
				Contrat contrat2 = listeAvenant.get(0).getContrat();
				contratResum.setPoliceResum(contrat2);
				
				//Recuperer le dernier avenant
				contratResum.setAvenantResum(getObjectService().DernierAvenant(contrat2.getNumPolice()));
				 
				 //Recuperer la prsonne
				 contratResum.setPersonneResum(contrat2.getPersonne());
				 listContratVehicule.add(contratResum);
				 
				//Nature de la personne
					if(contrat2.getPersonne().getPhysique() != null){
						contratResum.setPhysiqueResum(contrat2.getPersonne().getPhysique());
					}else{
						contratResum.setMoraleResum(contrat2.getPersonne().getMorale());
					}
				}
				//lEVER LA BOITE DE DIALOG POUR LE CHOIX DU CONTRAT
				RequestContext.getCurrentInstance().execute("contrat_Dialog.show();");
				
			break;
		}
		}
	}
	
	
	@Transactional
	public void chargerlechoix(){
		//Charger la liste
		listeContratResum.add(selectedContratResumVeh);
		//Renseigner info de base
		setMonContratResum(selectedContratResumVeh);
		
		//Recuperer le conducteur
		monContratResum.setConducteurResum(recupererCondHabituel(monContratResum.getVehiculeResum()));

		//Charger 
		chargerConducteurSinistre(monContratResum.getConducteurResum(), conducteurSinistre);
	}
	
	
	public Conducteur recupererCondHabituel(Vehicule vehicule){
		ConduireVehicule conduireVehicule = getObjectService().recupConduireVehicule(vehicule.getCodeVehicule());
		//Recuperation du conducteur
		conducteur = new Conducteur();
		try {
			conducteur = conduireVehicule.getConducteur();
		} catch (Exception e) {
			conducteur = new Conducteur(getIdGenerateur().getIdConducteur(monContratResum.getPersonneResum()));
			setOptionConducteur("2");
		}
		return conducteur;
	}


	public void chargerConducteurSinistre(Conducteur paramCond, ConducteurSinistre paramCondSinist){
		//Renseigner le conducteurSinistre par les info du conducteur
		paramCondSinist.setNomCondSinistre(paramCond.getNonCond());
		paramCondSinist.setPrenomCondSinistre(paramCond.getPrenomsCond());
		paramCondSinist.setDateNaissCondSinistre(paramCond.getDateNaissCond());
		paramCondSinist.setNumPermis(paramCond.getNumpermiscond());
	}

	
	public void clearSinistre() {
		// instancier sinistre afin de cree une nouvelle donnÃ©e
		numPolice = "";
		codeVehicule = "";
	//	sinistre = new Sinistre();
	}

	public String getNumPolice() {
		
		return numPolice;
	}
	
	
	public Contrat recherchePoliceContrat(String paramPolice){//By ALekerand
		etatRecherche = false;
			try {
				resultacontrat = (Contrat) getObjectService().getObjectById(paramPolice, "Contrat");
				if(!(resultacontrat==null)){
					etatRecherche = true;
					System.out.println("Contrat trouvï¿½!");//clean After
				}
			} catch (Exception e) {
				logger.error("Erreur lors de la recherche d'un contrat en production");
				e.printStackTrace();
			}
		return resultacontrat;
	}
	
	
	public Personne recupererPersonne(String paramNumSous){//By ALekerand
		etatRecherche = false;
			try {
				resultatPersonne = (Personne) getObjectService().getObjectById(paramNumSous, "Personne");
				if(!(resultatPersonne == null)){
					etatRecherche = true;
				}
			} catch (Exception e) {
				logger.error("Erreur lors de la recherche d'une Personne liï¿½ ï¿½ un contrat en production" );
				e.printStackTrace();
			}
				return resultatPersonne;
		}
	
	//CheckBox En fonction des choix
	public void ChangeValueChexBox(){
		//bouton PV
		if (isbPv()==true ){
			setEtatPv(false);
		}
		else{
			setEtatPv(true);
		}
		 //bouton rapport
		if (isbRap()==true){
			setEtatRap(false);
		}
		else{
			setEtatRap(true);
		}
		
		//Bouton Acte
		if(isbActMed()==true){
			setEtatActMed(false);
		}
		else{
			setEtatActMed(true);
		}
	}
	
	//Recup l'intervenant
			public void RecupInt() {
				setIntervenant((Intervenant) getObjectService().getObjectById(
						idIntervenant, "Intervenant"));
			}
			
	//Recup Medecin
			public void RecupMed() {
				setMedecin((Medecin) getObjectService().getObjectById(
						idMedecin, "Medecin"));
			}
		public void soumettre(){
			if(isbPv()==true){
				AjouPv();
			}
			else{
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Avertissement", "PV non Soumis"));
			}
			
			if(isbRap()==true){
				AjouRap();
			}
			else{
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Avertissement", "RAPPORT non Soumis"));
			}
			
			if(isbActMed()==true){
				AjouActMed();
			}
			else{
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Avertissement", "Acte Médical non Soumis"));
			}
		}
		
		//Enregistrement 
public void AjouPv(){
			
			handleFileUploadpv();
		}
		
public void AjouActMed(){
			facAct.setCodeFacture(getIdGenerateur().getIdFactureActeMed());
			getObjectService().addObject(facAct);
			acteMedical.setReferenceActe(getIdGenerateur().getIdActeMed(getSinistre()));
			acteMedical.setFacture(facAct);
			acteMedical.setMedecin(getMedecin());
			getObjectService().addObject(acteMedical);
			handleFileUploadActeMed();
		}
		
		public void AjouRap(){
			facPv.setCodeFacture(getIdGenerateur().getIdFactureIntervention(intervention));
			getObjectService().addObject(facPv);
			intervention.setRefIntervention(getIdGenerateur().getIdIntervention(getSinistre()));
			intervention.setTypeRapport("Rapport Intervenant");
			intervention.setIntervenant(getIntervenant());
			intervention.setSinistre(sinistre);
			getObjectService().addObject(intervenant);
			handleFileUploadRap();
		}
		
		//PDF
		public void handleFileUploadpv() {
			byte[] bFile = new byte[102400];
			if (filePv != null) {
				try {
					//copyFile(filePv.getFileName(), filePv.getInputstream());
					String nom = filePv.getFileName();
					setNomfichier(nom);
					System.out.print("file.getFileName()" + filePv.getFileName());
					System.out.print("nomfichier" + nomfichier);

					if (copyFile(nom, filePv.getInputstream())) {
						FacesMessage msg = new FacesMessage("Succès téléchargement fichier! ",
								filePv.getFileName() + " a été téléchargé!");
						FacesContext.getCurrentInstance().addMessage(null, msg);
						typeRapport = nom;
					} else {
						FacesMessage msg = new FacesMessage("Failure! ");
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				File fileupload = new File(destination + nomfichier);
				bFile = filePv.toString().getBytes();
				try {
					FileInputStream fileInputStream = new FileInputStream(
							fileupload);
					// convert file into array of bytes
					fileInputStream.read(bFile);
					fileInputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				creerPdf();
				File fileupload = new File(destination + "default.pdf");
				bFile = fileupload.toString().getBytes();
				try {
					FileInputStream fileInputStream = new FileInputStream(
							fileupload);
					// convert file into array of bytes
					fileInputStream.read(bFile);
					fileInputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

				typeRapport = "default.pdf";
			}
		}
		
		public void handleFileUploadRap() {
			byte[] bFile = new byte[102400];
			if (fileRap != null) {
				try {
					//copyFile(fileRap.getFileName(), fileRap.getInputstream());
					String nom = fileRap.getFileName();
					setNomfichier(nom);
					System.out.print("file.getFileName()" + fileRap.getFileName());
					System.out.print("nomfichier" + nomfichier);

					if (copyFile(nom, fileRap.getInputstream())) {
						FacesMessage msg = new FacesMessage("Succès téléchargement fichier! ",
								fileRap.getFileName() + " a été téléchargé!");
						FacesContext.getCurrentInstance().addMessage(null, msg);
						typeRapport = nom;
					} else {
						FacesMessage msg = new FacesMessage("Failure! ");
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				File fileupload = new File(destination + nomfichier);
				bFile = fileRap.toString().getBytes();
				try {
					FileInputStream fileInputStream = new FileInputStream(
							fileupload);
					// convert file into array of bytes
					fileInputStream.read(bFile);
					fileInputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				creerPdf();
				File fileupload = new File(destination + "default.pdf");
				bFile = fileupload.toString().getBytes();
				try {
					FileInputStream fileInputStream = new FileInputStream(
							fileupload);
					// convert file into array of bytes
					fileInputStream.read(bFile);
					fileInputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

				typeRapport = "default.pdf";
			}
		}
		
		public void handleFileUploadActeMed() {
			byte[] bFile = new byte[102400];
			if (fileActMed != null) {
				try {
					//copyFile(fileActMed.getFileName(), fileActMed.getInputstream());
					String nom = fileActMed.getFileName();
					setNomfichier(nom);
					System.out.print("file.getFileName()" + fileActMed.getFileName());
					System.out.print("nomfichier" + nomfichier);

					if (copyFile(nom, fileActMed.getInputstream())) {
						FacesMessage msg = new FacesMessage("Succès téléchargement fichier! ",
								fileActMed.getFileName() + " a été téléchargé!");
						FacesContext.getCurrentInstance().addMessage(null, msg);
						typeRapport = nom;
					} else {
						FacesMessage msg = new FacesMessage("Failure! ");
						FacesContext.getCurrentInstance().addMessage(null, msg);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				File fileupload = new File(destination + nomfichier);
				bFile = fileActMed.toString().getBytes();
				try {
					FileInputStream fileInputStream = new FileInputStream(
							fileupload);
					// convert file into array of bytes
					fileInputStream.read(bFile);
					fileInputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				creerPdf();
				File fileupload = new File(destination + "default.pdf");
				bFile = fileupload.toString().getBytes();
				try {
					FileInputStream fileInputStream = new FileInputStream(
							fileupload);
					// convert file into array of bytes
					fileInputStream.read(bFile);
					fileInputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

				typeRapport = "default.pdf";
			}
		}
		public void creerPdf() {
			File dir = new File("c:\\upload\\temp\\");
			dir.mkdirs();
			try {
				Document document = new Document();
				PdfWriter.getInstance(document, new FileOutputStream(FILE));
				document.open();
				addMetaData(document);
				addTitlePage(document);
				// addContent(document);
				document.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private static void addMetaData(Document document) {
			document.addTitle("My first PDF");
			document.addSubject("Using iText");
			document.addKeywords("Java, PDF, iText");
			document.addAuthor("Lars Vogel");
			document.addCreator("Lars Vogel");
		}

		private static void addTitlePage(Document document)
				throws DocumentException {
			Paragraph preface = new Paragraph();
			// We add one empty line

			// Lets write a big header
			preface.add(new Paragraph(
					"Vous n'avez pas encore  importer un fichier! ", catFont));

			document.add(preface);
			// Start a new page
			document.newPage();
		}
		
		public boolean copyFile(String fileName, InputStream in) {

			try {
				File dir = new File("c:\\upload\\temp\\");
				dir.mkdirs();

				// FileWriter newJsp = new FileWriter("c:\\upload\\temp\\test.txt");

				OutputStream out = new FileOutputStream(new File(destination
						+ fileName));

				// ExternalContext
				// extContext=FacesContext.getCurrentInstance().getExternalContext();
				// destination = "//resources//file//" +file.getFileName();
				// File dir = new File(extContext.getRealPath(destination));
				// dir.mkdirs();
				// OutputStream out = new FileOutputStream(new File(destination));
				int read = 0;

				byte[] bytes = new byte[1024];

				while ((read = in.read(bytes)) != -1) {

					out.write(bytes, 0, read);

				}

				in.close();

				out.flush();

				out.close();

				System.out
						.println("Un nouveau fichier a été ajouté dans le dossier c:\\upload\\temp\\ !");
				return true;

			} catch (IOException e) {

				System.out.println(e.getMessage());
				return false;

			}

		}

		public void upload(UploadedFile file) {
			try {
				// copyFile(file.getFileName(), file.getInputstream());
				String nom = file.getFileName();
				setNomfichier(nom);
				System.out.print("file.getFileName()" + file.getFileName());
				System.out.print("nomfichier" + nomfichier);

				if (copyFile(nom, file.getInputstream())) {
					FacesMessage msg = new FacesMessage("Succès! ",
							file.getFileName() + " a été téléchargé!");
					FacesContext.getCurrentInstance().addMessage(null, msg);
				} else {
					FacesMessage msg = new FacesMessage("Failure! ");
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	/*******************************ACCESSEUR recherche****************/

	public Sinistre getSinistre() {
		return sinistre;
	}

	public void setSinistre(Sinistre sinistre) {
		this.sinistre = sinistre;
	}
	public void setNumPolice(String numPolice) {
		this.numPolice = numPolice;
	}

	
	public List<Sinistre> getListSinistre() {
		return listSinistre;
	}

	public void setListSinistre(List<Sinistre> listSinistre) {
		this.listSinistre = listSinistre;
	}

	public String getTypeFiltre() {
		return typeFiltre;
	}

	public void setTypeFiltre(String typeFiltre) {
		this.typeFiltre = typeFiltre;
	}

	public String getFiltre() {
		return filtre;
	}

	public void setFiltre(String filtre) {
		this.filtre = filtre;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	public List<Sinistre> getListSinistreEnCours() {
		return listSinistreEnCours;
	}

	public void setListSinistreEnCours(List<Sinistre> listSinistreEnCours) {
		this.listSinistreEnCours = listSinistreEnCours;
	}

	
	public String getCodeVehicule() {
		return codeVehicule;
	}

	public void setCodeVehicule(String codeVehicule) {
		this.codeVehicule = codeVehicule;
	}

	public ConducteurSinistre getConducteurSinistre() {
		return conducteurSinistre;
	}

	public void setConducteurSinistre(ConducteurSinistre conducteurSinistre) {
		this.conducteurSinistre = conducteurSinistre;
	}

	public IdGenerateur getIdGenerateur() {
		return idGenerateur;
	}

	public void setIdGenerateur(IdGenerateur idGenerateur) {
		this.idGenerateur = idGenerateur;
	}

	public VehiculeSinistre getVehiculeSinistre() {
		return vehiculeSinistre;
	}

	public void setVehiculeSinistre(VehiculeSinistre vehiculeSinistre) {
		this.vehiculeSinistre = vehiculeSinistre;
	}

	public String getLibelleDomageCorp() {
		return libelleDomageCorp;
	}

	public void setLibelleDomageCorp(String libelleDomageCorp) {
		this.libelleDomageCorp = libelleDomageCorp;
	}

	public BigDecimal getRcAssure() {
		return rcAssure;
	}

	public void setRcAssure(BigDecimal rcAssure) {
		this.rcAssure = rcAssure;
	}

	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}


	public String getValeurDeRecherche() {
		return valeurDeRecherche;
	}


	public void setValeurDeRecherche(String valeurDeRecherche) {
		this.valeurDeRecherche = valeurDeRecherche;
	}

	public String getOptionRech() {
		return optionRech;
	}

	public void setOptionRech(String optionRech) {
		this.optionRech = optionRech;
	}

	public Personne getResultatPersonne() {
		return resultatPersonne;
	}

	public void setResultatPersonne(Personne resultatPersonne) {
		this.resultatPersonne = resultatPersonne;
	}

	public List<Garantie> getListeGarantieSelected() {
		return listeGarantieSelected;
	}

	public void setListeGarantieSelected(List<Garantie> listeGarantieSelected) {
		this.listeGarantieSelected = listeGarantieSelected;
	}

	public ContratResum getMonContratResum() {
		return monContratResum;
	}

	public void setMonContratResum(ContratResum monContratResum) {
		this.monContratResum = monContratResum;
	}

	public ContratResum getSelectedContratResum() {
		return selectedContratResum;
	}

	public void setSelectedContratResum(ContratResum selectedContratResum) {
		this.selectedContratResum = selectedContratResum;
	}

	public List<ContratResum> getListeContratResum() {
		return listeContratResum;
	}

	public void setListeContratResum(List<ContratResum> listeContratResum) {
		this.listeContratResum = listeContratResum;
	}

	public List<ContratResum> getListContratVehicule() {
		return listContratVehicule;
	}

	public void setListContratVehicule(List<ContratResum> listContratVehicule) {
		this.listContratVehicule = listContratVehicule;
	}

	public ContratResum getSelectedContratResumVeh() {
		return selectedContratResumVeh;
	}

	public void setSelectedContratResumVeh(ContratResum selectedContratResumVeh) {
		this.selectedContratResumVeh = selectedContratResumVeh;
	}


	public String getOptionConducteur() {
		return optionConducteur;
	}


	public void setOptionConducteur(String optionConducteur) {
		this.optionConducteur = optionConducteur;
	}


	public Date getDateDeclaration() {
		return dateDeclaration;
	}


	public void setDateDeclaration(Date dateDeclaration) {
		this.dateDeclaration = dateDeclaration;
	}


	public boolean isbPv() {
		return bPv;
	}


	public void setbPv(boolean bPv) {
		this.bPv = bPv;
	}


	public boolean isbRap() {
		return bRap;
	}


	public void setbRap(boolean bRap) {
		this.bRap = bRap;
	}


	public boolean isbActMed() {
		return bActMed;
	}


	public void setbActMed(boolean bActMed) {
		this.bActMed = bActMed;
	}


	public boolean isEtatPv() {
		return etatPv;
	}


	public void setEtatPv(boolean etatPv) {
		this.etatPv = etatPv;
	}


	public boolean isEtatRap() {
		return etatRap;
	}


	public void setEtatRap(boolean etatRap) {
		this.etatRap = etatRap;
	}


	public boolean isEtatActMed() {
		return etatActMed;
	}


	public void setEtatActMed(boolean etatActMed) {
		this.etatActMed = etatActMed;
	}


	public String getIdIntervenant() {
		return idIntervenant;
	}


	public void setIdIntervenant(String idIntervenant) {
		this.idIntervenant = idIntervenant;
	}


	public List<SelectItem> getElementsNomInt() {
		if (elementsNomInt == null) {
			elementsNomInt = new ArrayList<SelectItem>();
			try {
				for (Object obj : getObjectService().getObjects("Intervenant")) {
       
					elementsNomInt.add(new SelectItem(((Intervenant) obj)
							.getCodeIntervenant(), ((Intervenant) obj).getNomIntervenant()));

				}
			} catch (Exception e) {

			}
		}
		return elementsNomInt;
	}


	public void setElementsNomInt(List<SelectItem> elementsNomInt) {
		this.elementsNomInt = elementsNomInt;
	}


	public List<SelectItem> getElementsNomMed() {
		if (elementsNomMed == null) {
			elementsNomMed = new ArrayList<SelectItem>();
			try {
				for (Object obj : getObjectService().getObjects("Medecin")) {
       
					elementsNomMed.add(new SelectItem(((Medecin) obj)
							.getCodeMedecin(), ((Medecin) obj).getRaisonSociale()));

				}
			} catch (Exception e) {

			}
		}
		return elementsNomMed;
	}


	public void setElementsNomMed(List<SelectItem> elementsNomMed) {
		this.elementsNomMed = elementsNomMed;
	}


	public UploadedFile getFilePv() {
		return filePv;
	}


	public void setFilePv(UploadedFile filePv) {
		this.filePv = filePv;
	}


	public UploadedFile getFileRap() {
		return fileRap;
	}


	public void setFileRap(UploadedFile fileRap) {
		this.fileRap = fileRap;
	}


	public UploadedFile getFileActMed() {
		return fileActMed;
	}


	public void setFileActMed(UploadedFile fileActMed) {
		this.fileActMed = fileActMed;
	}


	public UIComponent getButtonadd() {
		return buttonadd;
	}


	public void setButtonadd(UIComponent buttonadd) {
		this.buttonadd = buttonadd;
	}


	public Intervention getIntervention() {
		return intervention;
	}


	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}


	public Intervenant getIntervenant() {
		return intervenant;
	}


	public void setIntervenant(Intervenant intervenant) {
		this.intervenant = intervenant;
	}


	public ActeMedical getActeMedical() {
		return acteMedical;
	}


	public void setActeMedical(ActeMedical acteMedical) {
		this.acteMedical = acteMedical;
	}


	public Facture getFacAct() {
		return facAct;
	}


	public void setFacAct(Facture facAct) {
		this.facAct = facAct;
	}


	public Facture getFacPv() {
		return facPv;
	}


	public void setFacPv(Facture facPv) {
		this.facPv = facPv;
	}


	public Medecin getMedecin() {
		return medecin;
	}


	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}


	public String getIdMedecin() {
		return idMedecin;
	}


	public void setIdMedecin(String idMedecin) {
		this.idMedecin = idMedecin;
	}


	public static String getFILE() {
		return FILE;
	}


	public static void setFILE(String fILE) {
		FILE = fILE;
	}


	public String getDestination() {
		return destination;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}


	public java.lang.String getTypeRapport() {
		return typeRapport;
	}


	public void setTypeRapport(java.lang.String typeRapport) {
		this.typeRapport = typeRapport;
	}


	public String getNomfichier() {
		return nomfichier;
	}


	public void setNomfichier(String nomfichier) {
		this.nomfichier = nomfichier;
	}

}
