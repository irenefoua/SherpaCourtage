package com.j3a.assurance.managedBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.j3a.assurance.model.ActeMedical;
import com.j3a.assurance.model.Contrat;
import com.j3a.assurance.model.Facture;
import com.j3a.assurance.model.Intervenant;
import com.j3a.assurance.model.Intervention;
import com.j3a.assurance.model.Sinistre;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.utilitaires.IdGenerateur;


@Component
public class ManagedSoumission {
	//Injection Spring
		@Autowired
		ObjectService objectService;
		@Autowired
		IdGenerateur idGenerateur;
		
		
		
		private UploadedFile filePv;
		private UploadedFile fileRap;
		private UploadedFile fileActMed;
		private UIComponent buttonadd;
		
		private boolean bPv, bRap, bActMed;
		private boolean etatPv=true, etatRap=true, etatActMed=true;
		private Sinistre sinistre = new Sinistre();
		private Sinistre sinistreSelectionne=new Sinistre();
		private Contrat contrat = new Contrat();
		private Intervention intervention = new Intervention();
		private Intervenant intervenant = new Intervenant();
		private ActeMedical acteMedical = new ActeMedical();
		private Facture facAct = new Facture();
		private Facture facPv = new Facture();
		private static String FILE = "c:/upload/temp/default.pdf";
		private String destination = "c:\\upload\\temp\\";
		private java.lang.String typeRapport;
		private String nomfichier;
		private String idIntervenant;
		private String critere;
		private List<SelectItem> elementsNomInt;
		ArrayList<Sinistre> listSinistre = new ArrayList<Sinistre>();
		private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
				Font.BOLD);
		private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
				Font.NORMAL, BaseColor.RED);
		private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
				Font.BOLD);
		private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
				Font.BOLD);

		
		public void soumettre(){
			if(isEtatPv()==true){
				AjouPv();
			}
			
			if(isEtatRap()==true){
				AjouRap();
			}
			
			if(isEtatActMed()==true){
				AjouActMed();
			}
		}
		
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
		
		public void AjouPv(){
			
			handleFileUploadpv();
		}
		
public void AjouActMed(){
			facAct.setCodeFacture(getIdGenerateur().getIdFactureActeMed());
			getObjectService().addObject(facAct);
			acteMedical.setReferenceActe(getIdGenerateur().getIdActeMed(getSinistre()));
			acteMedical.setFacture(facAct);
	
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
		//Recup l'intervenant
		public void RecupInt() {
			setIntervenant((Intervenant) getObjectService().getObjectById(
					idIntervenant, "Intervenant"));
			
		}
		public void handleFileUploadpv() {
			byte[] bFile = new byte[102400];
			if (filePv != null) {
				try {
					// copyFile(file.getFileName(), file.getInputstream());
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
					// copyFile(file.getFileName(), file.getInputstream());
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
					// copyFile(file.getFileName(), file.getInputstream());
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

		
		public void RechercheSinistreParContrat() {
			setContrat((Contrat) getObjectService().getObjectById(critere,
					"Contrat"));
			if (getContrat() == null) {
				System.out.println("Police Inexistante");
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Avertissement",
								"Ce numéro de Police n'est pas attribué!"));
			} else {
				if (!(getContrat().getRisque().getCodeRisque().equals("1"))) {
					System.out
							.println("Cette Police n'est pas une police du risque Auto");
					FacesContext
							.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage(FacesMessage.SEVERITY_INFO,
											"Avertissement",
											"Cette Police n'est pas une police du risque Auto"));
				} else {
					if (getContrat().getSinistres().size() == 0) {
						System.out.println("Aucun Sinistre pour cette police");
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_INFO,
										"Avertissement",
										"Aucun Sinistre pour cette police"));
					} else {
						for (Sinistre sin : getContrat().getSinistres()) {
							getListSinistre().add(sin);
						}
					}
				}
			}
		}

		public void onRowSelect(SelectEvent event) {
			//Sinistre Selectionnee
			setSinistre(((Sinistre) event.getObject()));
		}
		 
	    public UploadedFile getFilePv() {
	        return filePv;
	    }
	 
	    public void setFilePv(UploadedFile filePv) {
	        this.filePv = filePv;
	    }

		public ObjectService getObjectService() {
			return objectService;
		}

		public void setObjectService(ObjectService objectService) {
			this.objectService = objectService;
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

		public Sinistre getSinistre() {
			return sinistre;
		}

		public void setSinistre(Sinistre sinistre) {
			this.sinistre = sinistre;
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

		public IdGenerateur getIdGenerateur() {
			return idGenerateur;
		}

		public void setIdGenerateur(IdGenerateur idGenerateur) {
			this.idGenerateur = idGenerateur;
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

		public UIComponent getButtonadd() {
			return buttonadd;
		}

		public void setButtonadd(UIComponent buttonadd) {
			this.buttonadd = buttonadd;
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

		public String getCritere() {
			return critere;
		}

		public void setCritere(String critere) {
			this.critere = critere;
		}

		public Contrat getContrat() {
			return contrat;
		}

		public void setContrat(Contrat contrat) {
			this.contrat = contrat;
		}

		public ArrayList<Sinistre> getListSinistre() {
			return listSinistre;
		}

		public void setListSinistre(ArrayList<Sinistre> listSinistre) {
			this.listSinistre = listSinistre;
		}

		public Sinistre getSinistreSelectionne() {
			return sinistreSelectionne;
		}

		public void setSinistreSelectionne(Sinistre sinistreSelectionne) {
			this.sinistreSelectionne = sinistreSelectionne;
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
	
}
