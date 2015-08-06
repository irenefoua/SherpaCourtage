package com.j3a.assurance.reporting.design;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.j3a.assurance.model.ConduireVehicule;
import com.j3a.assurance.model.Garantie;
import com.j3a.assurance.model.GarantieChoisie;
import com.j3a.assurance.model.GarantieGarantieChoisie;
import com.j3a.assurance.model.GarantieGarantieChoisieId;
import com.j3a.assurance.model.SousCatVehicule;
import com.j3a.assurance.model.Vehicule;
import com.j3a.assurance.model.VehiculeZoneGeographique;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.reporting.bean.ReportingAuto;
import com.j3a.assurance.reporting.bean.factory.ReportFactoryAuto;
import com.j3a.assurance.utilitaires.Garanties;
import com.j3a.assurance.utilitaires.VehiculeRow;
import com.lowagie.text.Cell;

/**
 * 
 * @author J3A-POSTE1-A.Lekerand Bean permettant d'editer une quittance
 * 
 */

@Component
public class ConditionPartAuto implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	// Injection par spring
	@Autowired
	ObjectService objectService;
	@Autowired
	ReportFactoryAuto reportFactoryAuto;

	ReportingAuto reportingAuto = new ReportingAuto();
	

	// Attribut d'instance
	private String idQuittance; // Détacher pour les tests (parametre d'edition)
	private File repectoire;
	private Float primeTTC = new Float(0);
	private String nomFichier;
	private BigDecimal totalAccessoir;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
	public static final String RESOURCE = "http://localhost:8080/SherpaWebUse/resources/images/logo_j3a.jpg";

	// Pour la mise en forme
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 28,
			Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.NORMAL, BaseColor.GREEN);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			Font.BOLD);

	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.BOLD);

	private static Font normalText = new Font(Font.FontFamily.TIMES_ROMAN, 8,
			Font.BOLD);
	private static Font normalText1 = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.NORMAL);
	private static Font normalText3 = new Font(Font.FontFamily.TIMES_ROMAN, 15,
			Font.BOLD,BaseColor.BLUE);
	private static Font normalText1Gras = new Font(Font.FontFamily.TIMES_ROMAN,
			12, Font.BOLD);

	private static Font normalTitle = new Font(Font.FontFamily.TIMES_ROMAN, 9,
			Font.BOLD);

	private static Font smallText = new Font(Font.FontFamily.COURIER, 8,
			Font.NORMAL);
	private static Font smallText2 = new Font(Font.FontFamily.COURIER, 5,
			Font.NORMAL);

	private static Font smallTextGras = new Font(Font.FontFamily.COURIER, 8,
			Font.BOLD);

	private static Font TITRE3 = new Font(Font.FontFamily.TIMES_ROMAN, 14,
			Font.BOLD);
	
	
public void printPdf(ReportingAuto reporting,
		HttpServletRequest request, HttpServletResponse response)
		throws IOException, Exception {
	
	repectoire = new File("c:/Etats/Cond_Part/AUTO");
	repectoire.mkdirs();
	nomFichier = "attestation-" + "001" + ".pdf";
	String fichier = repectoire + "/"
			+ nomFichier;
	

	Document document = new Document(PageSize.A4);
	document.setMargins(20, 20, 20, 20);
	// step 2
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PdfWriter.getInstance(document, baos);
	PdfWriter.getInstance(document, new FileOutputStream(repectoire + "/"
			+ nomFichier));

	// step 3
	document.open();
	System.out.println("Avant le véhicule");
	for (VehiculeRow vehiculeRow : reporting.getListVehiculeRow()) {
		System.out.println("dans le véhicule");
		document.newPage();
		
	
		try {

			addContentAttestation(document, vehiculeRow);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur1");
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erreur2");
		}
	}

	document.close();
	  // setting some response headers response.setHeader("Expires", "0");
	  response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
//	  response.setHeader("Pragma", "public"); // setting the content type
	  response.setContentType("application/pdf"); // the contentlength
	  response.setContentLength(baos.size()); // write ByteArrayOutputStream to the ServletOutputStream 
	  OutputStream os = response.getOutputStream(); 
	  baos.writeTo(os);
	  os.flush();
	  os.close();
	  
	openFile(fichier);
	  }

public void addContentAttestation(Document document, VehiculeRow vehiculeRow) throws DocumentException,
MalformedURLException, IOException {
// Ajout de logo
Image logo = Image.getInstance(new URL(RESOURCE));
logo.scalePercent(40f);
document.add(logo);
/*Paragraph saut = new Paragraph();
addEmptyLine(saut, 1);
document.add(sautLigne(1));*/
//Ajout du nom de la societe d'assurance
AjouterNomEntreprise(document);

// Entête du document
creerTitreDocumentAttestation(document);

// Information de la quittance
creatTableAttestation(document, vehiculeRow);

// Emagement
creerEmagementAttestation(document);

}

 public void creatTableAttestation(Document document, VehiculeRow vehiculeRow) throws DocumentException {
	// Info sur le Souscripteur
	System.out.println("Info sur le Souscripteur");
	PdfPTable tabSous = new PdfPTable(4);
	tabSous.setWidths(new int[] { 30, 25, 10, 35 });

	tabSous.getDefaultCell().setBorder(Cell.NO_BORDER);
	PdfPCell cell;

	// 1er ligne Assuré et adresse
	System.out.println("Info sur le Souscripteur nom personne");
	String nomAssure = "";
	if(reportingAuto.getPersonne().getNomRaisonSociale()!=null){
		nomAssure = nomAssure+ reportingAuto.getPersonne().getNomRaisonSociale() +" "+reportingAuto.getPhysique().getPrenomPers();
	}
	if(reportingAuto.getPersonne().getAdresse()!=null){
		nomAssure = nomAssure+"  "+reportingAuto.getPersonne().getAdresse();
	}
		
	
	
	cell = new PdfPCell(new Phrase("ASSURE NOM ET ADRESSE : ", smallText));
	cell.setBorder(Rectangle.NO_BORDER);
	//cell.setColspan(2);
	tabSous.addCell(cell);
	
	cell = new PdfPCell(new Phrase(nomAssure, normalText));
	cell.setBorder(Rectangle.NO_BORDER);
	cell.setColspan(3);
	tabSous.addCell(cell);
	
	
	// 2em ligne Profession
	tabSous.addCell(new Phrase("Profession :",smallText));
	
	if(reportingAuto.getPhysique().getProfession()!=null){
		cell = new PdfPCell(new Phrase(reportingAuto.getPhysique().getProfession(),
				normalText));
		}else{cell = new PdfPCell(new Phrase(" ",smallText));
	}
	
	
	//cell = new PdfPCell(new Phrase(reportingAuto.getPersonne().getPhysique().getProfession(), normalText));
	cell.setBorder(Rectangle.NO_BORDER);
	cell.setColspan(3);
	tabSous.addCell(cell);
	
	

	// 3em ligne Police
	System.out.println("Info sur le contrat police et avenant");
	tabSous.addCell(new Phrase("Police :", smallText));
	if(reportingAuto.getContrat().getNumPolice()!=null){
		cell = new PdfPCell(new Phrase(reportingAuto.getContrat().getNumPolice(),
				normalText));}else{cell = new PdfPCell(new Phrase("",
						smallText));
	}
	cell.setBorder(Rectangle.NO_BORDER);
	cell.setColspan(3);
	tabSous.addCell(cell);
	
	

	// 4em ligne date contrat
	tabSous.addCell(new Phrase("DU ", smallText));
	if(reportingAuto.getAvenant().getEffet()!=null){cell = new PdfPCell(new Phrase(sdf.format(reportingAuto.getAvenant().getEffet()), normalText));}else{
		cell = new PdfPCell(new Phrase("", smallText));
	}
	cell.setBorder(Rectangle.NO_BORDER);
	tabSous.addCell(cell);
	tabSous.addCell(new Phrase(" AU ", smallText));
	if(reportingAuto.getAvenant().getEcheance()!=null){cell = new PdfPCell(new Phrase(sdf.format(reportingAuto.getAvenant().getEcheance())+" A MINUIT", normalText));}else{
		cell = new PdfPCell(new Phrase("", smallText));
		
	}
	cell.setBorder(Rectangle.NO_BORDER);
	tabSous.addCell(cell);
	
	System.out.println("Info sur le vehicule");
	// 5em ligne vehicule
	tabSous.addCell(new Phrase("Vehicule :", smallText));
	if(vehiculeRow.getVehi().getGenre()!=null){
	cell = new PdfPCell(new Phrase(vehiculeRow.getVehi().getGenre(), normalText));
	}else{
		cell = new PdfPCell(new Phrase("", smallText2));	
	}
	cell.setBorder(Rectangle.NO_BORDER);
	cell.setColspan(3);
	tabSous.addCell(cell);
	
	// 6em ligne marque
	tabSous.addCell(new Phrase("Marque :", smallText));
	if(vehiculeRow.getVehi().getMarque()!=null){
		cell = new PdfPCell(new Phrase(vehiculeRow.getVehi().getMarque(), normalText));
		}else{
			cell = new PdfPCell(new Phrase("", smallText2));	
		}
	cell.setBorder(Rectangle.NO_BORDER);
	cell.setColspan(3);
	tabSous.addCell(cell);
	
	// 7em ligne Immatriculation
	tabSous.addCell(new Phrase("Immatriculation :", smallText));
	if(vehiculeRow.getVehi().getNumImmat()!=null){
		cell = new PdfPCell(new Phrase(vehiculeRow.getVehi().getNumImmat(), normalText));
		}else{
			cell = new PdfPCell(new Phrase("", smallText2));	
		}
	cell.setBorder(Rectangle.NO_BORDER);
	cell.setColspan(3);
	tabSous.addCell(cell);
	
	
	System.out.println("Info sur la sous catégorie");
	// Tableau général information
	PdfPTable tabInfo = new PdfPTable(1);

	tabInfo.addCell(tabSous);
	if(vehiculeRow.getSouCatVehi().getCategorie().getCodeCategorie()!=null){
		String tarif = "";
	if(vehiculeRow.getSouCatVehi().getTarif()!=null){
		tarif = vehiculeRow.getSouCatVehi().getTarif().getCodeTarif();
	}
		cell = new PdfPCell(new Phrase(tarif+" "+vehiculeRow.getSouCatVehi().getCategorie().getLibelleCategorie(), normalText3));
		cell.setPaddingLeft(100);
		tabInfo.addCell(cell);
		
		}else{
			tabInfo.addCell("");
		}
	
	
	tabInfo.setSpacingAfter(15);
	document.add(tabInfo);
	System.out.println("Fin info");
}



	public void editerAttestation(ReportingAuto reporting,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, Exception {
		// Créer le dosier de stockage des fichier générés
		repectoire = new File("c:/Etats/Cond_Part/AUTO");
		repectoire.mkdirs();
		
		

		Document document = new Document(PageSize.A4);
		document.setMargins(20, 20, 20, 20);
		nomFichier = "attestation-" + "001" + ".pdf";
		// step 2
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, baos);
		PdfWriter.getInstance(document, new FileOutputStream(repectoire + "/"
				+ nomFichier));

		// step 3
		document.open();
		System.out.println("Avant le véhicule");
		for (VehiculeRow vehiculeRow : reporting.getListVehiculeRow()) {
			System.out.println("dans le véhicule");
			document.newPage();
			
		
			try {

				addContent(document, vehiculeRow);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Erreur1");
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Erreur2");
			}
		}

		document.close();
		  // setting some response headers response.setHeader("Expires", "0");
		  response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
//		  response.setHeader("Pragma", "public"); // setting the content type
		  response.setContentType("application/pdf"); // the contentlength
		  response.setContentLength(baos.size()); // write ByteArrayOutputStream to the ServletOutputStream 
		  OutputStream os = response.getOutputStream(); 
		  baos.writeTo(os);
		  os.flush();
		  os.close();
		  
		  
	}

	public void editerConditionPart(String idQuittance,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, Exception {
		// Créer le dosier de stockage des fichier générés
		repectoire = new File("c:/Etats/Cond_Part/AUTO");
		repectoire.mkdirs();
		// Passer les info nécessaires à notre rapport à générer
		getReportFactoryAuto().setIdQuittance(idQuittance);
		setReportingAuto(getReportFactoryAuto().reportProvider());
		if(reportingAuto!=null){

		Document document = new Document(PageSize.A4);
		document.setMargins(20, 20, 20, 20);
		nomFichier = "CP-" + reportingAuto.getQuittance().getCodeQuittance() + ".pdf";
		// step 2
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, baos);
		PdfWriter.getInstance(document, new FileOutputStream(repectoire + "/"
				+ nomFichier));

		// step 3
		document.open();

		for (Vehicule vehicule : getReportingAuto().getListVehiculeAssure()) {
			document.newPage();
			// Recuperer la sous catégorie
			SousCatVehicule sousCatVehicule = vehicule.getSousCatVehicule();

			// Recuperer la zone de geographie
			VehiculeZoneGeographique vehiculeZoneGeographique = getObjectService()
					.recupDerniereZoneGeo(vehicule.getCodeVehicule());

			// Recuperer le conducteur habituel
			ConduireVehicule conduireVehicule = getObjectService()
					.recupConducteur(vehicule.getCodeVehicule());

			// recuperer la derniere garantieChoisie
			GarantieChoisie garantieChoisie = getObjectService()
					.recuperGarantiChoisie(vehicule.getCodeVehicule(),
							getReportingAuto().getAvenant().getNumAvenant());

			// Recuperer la liste des gartgartchoisies
			List<GarantieGarantieChoisie> listgartieGartieChoisies = new ArrayList<GarantieGarantieChoisie>(
					getObjectService().recupGartGartChoisie(
							garantieChoisie.getCodeGarantieChoisie()));
			try {

				addContent(document, vehicule, garantieChoisie,
						listgartieGartieChoisies, vehiculeZoneGeographique,
						conduireVehicule);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Erreur1");
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Erreur2");
			}
		}

		document.close();
		  // setting some response headers response.setHeader("Expires", "0");
		  response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
//		  response.setHeader("Pragma", "public"); // setting the content type
		  response.setContentType("application/pdf"); // the contentlength
		  response.setContentLength(baos.size()); // write ByteArrayOutputStream to the ServletOutputStream 
		  OutputStream os = response.getOutputStream(); 
		  baos.writeTo(os);
		  os.flush();
		  os.close();
		  }
	}
	
	public void editerConditionPart(ReportingAuto reporting,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, Exception {
		// Créer le dosier de stockage des fichier générés
		String fichier="";
		repectoire = new File("c:/Etats/Cond_Part/AUTO");
		repectoire.mkdirs();
		
		

		Document document = new Document(PageSize.A4);
		document.setMargins(20, 20, 20, 20);
		nomFichier = "devis-" + "001" + ".pdf";
		fichier = repectoire + "/"
				+ nomFichier;
		// step 2
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, baos);
		PdfWriter.getInstance(document, new FileOutputStream(fichier));

		// step 3
		document.open();
		System.out.println("Avant le véhicule");
		for (VehiculeRow vehiculeRow : reporting.getListVehiculeRow()) {
			System.out.println("dans le véhicule");
			document.newPage();
			
		
			try {

				addContent(document, vehiculeRow);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Erreur1");
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Erreur2");
			}
		}

		document.close();
		  // setting some response headers response.setHeader("Expires", "0");
		  response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
//		  response.setHeader("Pragma", "public"); // setting the content type
		  response.setContentType("application/pdf"); // the contentlength
		  response.setContentLength(baos.size()); // write ByteArrayOutputStream to the ServletOutputStream 
		  OutputStream os = response.getOutputStream(); 
		  baos.writeTo(os);
		  os.flush();
		  os.close();
		openFile(fichier);
		  
	}

	private void addContent(Document document, Vehicule vehicule,
			GarantieChoisie garantieChoisie,
			List<GarantieGarantieChoisie> listgartieGartieChoisies,
			VehiculeZoneGeographique vehiculeZoneGeographique,
			ConduireVehicule conduireVehicule) throws DocumentException,
			MalformedURLException, IOException {
		// Ajout de logo
		Image logo = Image.getInstance(new URL(RESOURCE));
		logo.scalePercent(40f);
		document.add(logo);
		/*Paragraph saut = new Paragraph();
		addEmptyLine(saut, 1);
		document.add(sautLigne(1));*/
		//Ajout du nom de la societe d'assurance
		System.out.println("Entète du document");
		AjouterNomEntreprise(document);
		
		// Entête du document
		creerTitreDocument(document);

		// Information de la quittance
		creatTableInfo(document);

		// Titre du document
		ajoutTitre(document);

		// Information du vehicule
		identifierVehicule(document, vehicule, vehiculeZoneGeographique);

		// Tarif du vehicule
		tarifVehicule(document, vehicule, conduireVehicule);

		// Tableau de garantie
		creerTableauGaranties(document, listgartieGartieChoisies,
				garantieChoisie);

		// Recap de la prime
		createtableRecapPrime(document, garantieChoisie);

		// Emagement
		creerEmagement(document);

	}
	
	private void addContent(Document document, VehiculeRow vehiculeRow) throws DocumentException,
			MalformedURLException, IOException {
		// Ajout de logo
		Image logo = Image.getInstance(new URL(RESOURCE));
		logo.scalePercent(40f);
		document.add(logo);
		/*Paragraph saut = new Paragraph();
		addEmptyLine(saut, 1);
		document.add(sautLigne(1));*/
		//Ajout du nom de la societe d'assurance
		AjouterNomEntreprise(document);
		
		// Entête du document
		creerTitreDocument(document);

		// Information de la quittance
		creatTableInfo(document);

		// Titre du document
		ajoutTitre(document);

		// Information du vehicule
		VehiculeZoneGeographique vz = new VehiculeZoneGeographique();
		vz.setVehicule(vehiculeRow.getVehi());
		vz.setZoneGeographique(vehiculeRow.getZonGeo());
		identifierVehicule(document, vehiculeRow.getVehi(), vz);

		// conducteur du vehicule
		ConduireVehicule cv = new ConduireVehicule();
		if(vehiculeRow.getConduHab()!= null){
			cv.setConducteur(vehiculeRow.getConduHab());
		}
		cv.setVehicule(vehiculeRow.getVehi());
		tarifVehicule(document, vehiculeRow.getVehi(), cv);
		
		//creation de GarantieChoisie et ListeGarantieGarantieChoisie
		// add Garanties
		GarantieChoisie garchoi = new GarantieChoisie();



		// Garantie Garantie choisie

		List<GarantieGarantieChoisie> garantieGarantieChoisieList = new ArrayList<GarantieGarantieChoisie>();

		for (Garanties G : vehiculeRow.getListegaranties()) {

			// on calcul la prime

			Garantie gar = new Garantie();
			GarantieGarantieChoisie garantieGarantieChoisie = new GarantieGarantieChoisie();
			GarantieGarantieChoisieId garantieGarantieChoisieId = new GarantieGarantieChoisieId();

			gar.setCodeGarantie(G.getCodeGarantie());
			gar.setLibelleGarantie(G.getLibelleGarantie());
			gar.setFranchise(G.getFranchise());
			garantieGarantieChoisieId.setCodeGarantie(gar.getCodeGarantie());
			garantieGarantieChoisieId.setCodeGarantieChoisie(garchoi.getCodeGarantieChoisie());
			
			garantieGarantieChoisie.setId(garantieGarantieChoisieId);

			garantieGarantieChoisie.setGarantie(gar);
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
		System.out.println("Liste Garantie "+garantieGarantieChoisieList.size());

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


		// Tableau de garantie
		creerTableauGaranties(document, garantieGarantieChoisieList,
				garchoi);

		// Recap de la prime
		createtableRecapPrime(document, garchoi);

		// Emagement
		creerEmagement(document);

	}

	// Titre du document
	public void creerTitreDocument(Document document) throws DocumentException {

		Paragraph titreDocument = new Paragraph(new Chunk(
				"POLICE RISQUE AUTOMOBILE", TITRE3));
		titreDocument.setAlignment(Element.ALIGN_CENTER);
		titreDocument.setSpacingAfter(30);
		document.add(titreDocument);
	}
	
	// Titre du document
		public void creerTitreDocumentAttestation(Document document) throws DocumentException {

			Paragraph titreDocument = new Paragraph(new Chunk(
					"ATTESTATION PROVISOIRE", TITRE3));
			titreDocument.setAlignment(Element.ALIGN_CENTER);
			titreDocument.setSpacingAfter(40);
			document.add(titreDocument);
		}

	private void creatTableInfo(Document document) throws DocumentException {
		// Info sur le Souscripteur
		System.out.println("Info sur le Souscripteur");
		PdfPTable tabSous = new PdfPTable(4);
		tabSous.setWidths(new int[] { 15, 20, 15, 20 });

		tabSous.getDefaultCell().setBorder(Cell.NO_BORDER);
		PdfPCell cell;

		// 1er ligne
		tabSous.addCell(new Phrase("Nom:", normalText));
		if(reportingAuto.getNom()==null){
			cell = new PdfPCell(new Phrase("", smallText));
			}else{
		
		cell = new PdfPCell(new Phrase(reportingAuto.getNom(), smallText));
	   }
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(3);
		tabSous.addCell(cell);
		// 2em ligne
		tabSous.addCell(new Phrase("Adresse:", normalText));
		if(reportingAuto.getPersonne().getAdresse()==null){
			cell = new PdfPCell(new Phrase("", smallText));
		}else{
		
		cell = new PdfPCell(new Phrase(
				reportingAuto.getPersonne().getAdresse(), smallText));
			}
		cell.setBorder(Rectangle.NO_BORDER);
		tabSous.addCell(cell);
		tabSous.addCell(new Phrase("Tel:", normalText));
		if(reportingAuto.getPersonne().getTelephone()==null){
			tabSous.addCell(new Phrase("",smallText));
		}else{
		tabSous.addCell(new Phrase(reportingAuto.getPersonne().getTelephone(),
				smallText));
		}
		// 3em ligne
		tabSous.addCell(new Phrase("Mail:", normalText));
		if(reportingAuto.getPersonne().getEmail()!=null){
			cell = new PdfPCell(new Phrase(reportingAuto.getPersonne().getEmail(),
					smallText));
		}else{
			cell = new PdfPCell(new Phrase("",
					smallText));
		}
		System.out.println("Info sur le Souscripteur3");
		
		cell.setBorder(Rectangle.NO_BORDER);
		tabSous.addCell(cell);
		tabSous.addCell(new Phrase("Fax:", normalText));
		if(reportingAuto.getPersonne().getFax()!=null){
			tabSous.addCell(new Phrase(reportingAuto.getPersonne().getFax(),
				smallText));}else{
		tabSous.addCell(new Phrase("",smallText));}
		System.out.println("Info sur le Souscripteur4");
		// 4em ligne
		tabSous.addCell(new Phrase("Intermédiaire:", normalText));
		cell = new PdfPCell(new Phrase(" "));
		cell.setBorder(Rectangle.NO_BORDER);
		tabSous.addCell(cell);
		tabSous.addCell(new Phrase("Réseaux:", normalText));
		if(reportingAuto.getPointVente().getLibellePointVente()!=null){
			tabSous.addCell(new Phrase(reportingAuto.getPointVente()
					.getLibellePointVente(), smallText));
		}else{
		
		tabSous.addCell(new Phrase("", smallText));
		}
		System.out.println("Info sur le Souscripteur5");
		// Tableau général information
		PdfPTable tabInfo = new PdfPTable(3);
		tabInfo.setWidthPercentage(100);
		tabInfo.setWidths(new int[] { 48, 1, 47 });

		// Entête du tableau
		PdfPCell cellTitre = new PdfPCell(new Phrase("SOUSCRIPTEUR",
				normalTitle));
		cellTitre.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellTitre.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabInfo.addCell(cellTitre);
		tabInfo.addCell("");
		cellTitre = new PdfPCell(new Phrase("REFFERENCES", normalTitle));
		cellTitre.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellTitre.setHorizontalAlignment(Element.ALIGN_CENTER);
		tabInfo.addCell(cellTitre);
		
		// Info sur la quittance
		PdfPTable tabQuit = new PdfPTable(4);
		tabQuit.setWidths(new int[] { 21, 29, 21, 29 });
		tabQuit.getDefaultCell().setBorder(Cell.NO_BORDER);

		// 1er ligne
		tabQuit.addCell(new Phrase("Police:", normalText));
		if(reportingAuto.getContrat().getNumPolice()!=null){
			tabQuit.addCell(new Phrase(reportingAuto.getContrat().getNumPolice(),smallText));
		}else{
		
		tabQuit.addCell(new Phrase("",smallText));
		}
		tabQuit.addCell(new Phrase("Categorie:", normalText));
		if(reportingAuto.getRisque().getCodeRisque()!=null){
			tabQuit.addCell(new Phrase(reportingAuto.getRisque().getCodeRisque(), smallText));
			}else{
				tabQuit.addCell(new Phrase("", smallText));
			}
		
		

		// 2em ligne
		tabQuit.addCell(new Phrase("Avenant:", normalText));
		if(reportingAuto.getAvenant().getNumAvenant()!=null){
			cell = new PdfPCell(new Phrase(reportingAuto.getAvenant().getNumAvenant(),
					smallText));}else{cell = new PdfPCell(new Phrase("",
							smallText));
		}
		
		cell.setBorder(Rectangle.NO_BORDER);

		tabQuit.addCell(cell);
		tabQuit.addCell(new Phrase("Mouvement:", normalText));
		if(reportingAuto.getAvenant().getMouvement()!=null){tabQuit.addCell(new Phrase(reportingAuto.getAvenant().getMouvement(),
				smallText));}else{tabQuit.addCell(new Phrase("",
						smallText));
				}
		System.out.println("Info sur le Souscripteur5");

		// 3e Ligne
		tabQuit.addCell(new Phrase("Effet:", normalText));
		if(reportingAuto.getAvenant().getEffet()!=null){cell = new PdfPCell(new Phrase(sdf.format(reportingAuto.getAvenant().getEffet()), smallText));}else{
			cell = new PdfPCell(new Phrase("", smallText));
		}
		
	
		cell.setBorder(Rectangle.NO_BORDER);

		tabQuit.addCell(cell);
		tabQuit.addCell(new Phrase("Echéance:", normalText));
		if(reportingAuto.getAvenant().getEcheance()!=null){cell = new PdfPCell(new Phrase(sdf.format(reportingAuto.getAvenant().getEcheance()), smallText));}else{
			cell = new PdfPCell(new Phrase("", smallText));
		}

		// 4e Ligne
		tabQuit.addCell(new Phrase("Barème:", normalText));
		if(reportingAuto.getContrat().getBareme()!=null){cell = new PdfPCell(new Phrase(reportingAuto.getContrat().getBareme(),
				smallText));}else{
					cell = new PdfPCell(new Phrase("",
							smallText));
				}
		
		cell.setBorder(Rectangle.NO_BORDER);

		tabQuit.addCell(cell);
		tabQuit.addCell(new Phrase("Durée:", normalText));
		if(reportingAuto.getAvenant().getDuree()!=null){tabQuit.addCell(new Phrase("" + reportingAuto.getAvenant().getDuree(),
				smallText));}else{
					tabQuit.addCell(new Phrase("",
							smallText));
				}
		

		tabInfo.addCell(tabSous);
		cell = new PdfPCell(new Phrase(""));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		tabInfo.addCell(cell);
		tabInfo.addCell(tabQuit);
		tabInfo.addCell("Tab quittance");
		
		tabInfo.setSpacingAfter(15);
		document.add(tabInfo);
		System.out.println("Fin info");
	}

	private static void ajoutTitre(Document document) throws DocumentException{
		System.out.println("Ajout titre");
		Paragraph titre = new Paragraph();
		Paragraph paragraph = new Paragraph("CONDITIONS PARTICULIERES", TITRE3);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		titre.add(paragraph);
		titre.setSpacingAfter(20);
		document.add(titre);
	}
	
	
	public void AjouterNomEntreprise(Document document) throws DocumentException{
		Paragraph societeAssurance = new Paragraph(reportingAuto.getSocieteAssurance().getAbrege());
		societeAssurance.setAlignment(Element.ALIGN_LEFT);
		document.add(societeAssurance);
	}

	public void identifierVehicule(Document document, Vehicule vehicule,
			VehiculeZoneGeographique vehiculeZoneGeographique)
			throws DocumentException {
		// Tableau général information
		System.out.println("debut Identité vehicule");
		PdfPTable tableauVehicul = new PdfPTable(6);
		tableauVehicul.setWidthPercentage(100);
		tableauVehicul.setWidths(new int[] { 16, 16, 16, 16, 16, 16 });

		// Contenu du tableau

		PdfPCell cell;
		PdfPCell cellLib;
		PdfPCell cellCont;

		// Titre tableau
		cell = new PdfPCell(new Phrase("INFORMATIONS SUR LE VEHICULE",
				normalTitle));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setColspan(6);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableauVehicul.addCell(cell);

		// 1ere Ligne
		cellLib = new PdfPCell(new Phrase("Imatriculation", normalText));
		cellCont = new PdfPCell(new Phrase(vehicule.getNumImmat(), smallText));
		tableauVehicul.addCell(cellLib);
		tableauVehicul.addCell(cellCont);

		cellLib = new PdfPCell(new Phrase("1° mis en circulation", normalText));
		cellCont = new PdfPCell(new Phrase(sdf.format(vehicule
				.getDatePremiereCirc()), smallText));
		tableauVehicul.addCell(cellLib);
		tableauVehicul.addCell(cellCont);

		cellLib = new PdfPCell(new Phrase("Charge utile", normalText));
		cellCont = new PdfPCell(new Phrase("" + vehicule.getChargeUtile(),
				smallText));
		tableauVehicul.addCell(cellLib);
		tableauVehicul.addCell(cellCont);

		// 2e Ligne
		cellLib = new PdfPCell(new Phrase("Marque", normalText));
		cellCont = new PdfPCell(new Phrase(vehicule.getMarque(), smallText));
		tableauVehicul.addCell(cellLib);
		tableauVehicul.addCell(cellCont);

		cellLib = new PdfPCell(new Phrase("Energie", normalText));
		cellCont = new PdfPCell(new Phrase(vehicule.getEnergie(), smallText));
		tableauVehicul.addCell(cellLib);
		tableauVehicul.addCell(cellCont);

		cellLib = new PdfPCell(new Phrase("PTAC", normalText));
		cellCont = new PdfPCell(new Phrase(""
				+ vehicule.getPoidsVide().add(vehicule.getChargeUtile()),
				smallText));
		tableauVehicul.addCell(cellLib);
		tableauVehicul.addCell(cellCont);

		// 3e Ligne
		cellLib = new PdfPCell(new Phrase("Type", normalText));
		cellCont = new PdfPCell(new Phrase(vehicule.getEnergie(), smallText));
		tableauVehicul.addCell(cellLib);
		tableauVehicul.addCell(cellCont);

		cellLib = new PdfPCell(new Phrase("Puissance", normalText));
		cellCont = new PdfPCell(new Phrase("" + vehicule.getPuissFisc(),
				smallText));
		tableauVehicul.addCell(cellLib);
		tableauVehicul.addCell(cellCont);

		cellLib = new PdfPCell(new Phrase("Poid vide", normalText));
		cellCont = new PdfPCell(new Phrase("" + vehicule.getPoidsVide(),
				smallText));
		tableauVehicul.addCell(cellLib);
		tableauVehicul.addCell(cellCont);

		// 4e Ligne
		cellLib = new PdfPCell(new Phrase("Genre", normalText));
		cellCont = new PdfPCell(new Phrase(vehicule.getGenre(), smallText));
		tableauVehicul.addCell(cellLib);
		tableauVehicul.addCell(cellCont);

		cellLib = new PdfPCell(new Phrase("Puissance Réelle", normalText));
		cellCont = new PdfPCell(new Phrase("" + vehicule.getPuissReelle(),
				smallText));
		tableauVehicul.addCell(cellLib);
		tableauVehicul.addCell(cellCont);

		cellLib = new PdfPCell(new Phrase("Zone Geo", normalText));
		cellCont = new PdfPCell(new Phrase(vehiculeZoneGeographique
				.getZoneGeographique().getLibelleZoneGeo(), smallText));
		tableauVehicul.addCell(cellLib);
		tableauVehicul.addCell(cellCont);

		// 5e Ligne
		cellLib = new PdfPCell(new Phrase("Carosserie", normalText));
		cellCont = new PdfPCell(
				new Phrase(vehicule.getCarrosserie(), smallText));
		tableauVehicul.addCell(cellLib);
		tableauVehicul.addCell(cellCont);

		cellLib = new PdfPCell(new Phrase("place cabine", normalText));
		cellCont = new PdfPCell(new Phrase("" + vehicule.getNbrePlaceCab(),
				smallText));
		tableauVehicul.addCell(cellLib);
		tableauVehicul.addCell(cellCont);

		cellLib = new PdfPCell(new Phrase("places hors cabine", normalText));
		cellCont = new PdfPCell(new Phrase("" + vehicule.getNbrePlaceHorscab(),
				smallText));
		tableauVehicul.addCell(cellLib);
		tableauVehicul.addCell(cellCont);

		// 5e Ligne
		cellLib = new PdfPCell(new Phrase("N° chasis", normalText));
		cellCont = new PdfPCell(new Phrase(vehicule.getChassis(), smallText));
		tableauVehicul.addCell(cellLib);
		tableauVehicul.addCell(cellCont);

		cellLib = new PdfPCell(new Phrase("Valeur à neuf", normalText));
		cellCont = new PdfPCell(new Phrase("" + vehicule.getValNeuf(),
				smallText));
		tableauVehicul.addCell(cellLib);
		tableauVehicul.addCell(cellCont);

		cellLib = new PdfPCell(new Phrase("Valeur venale", normalText));
		cellCont = new PdfPCell(new Phrase("" + vehicule.getValVenale(),
				smallText));
		tableauVehicul.addCell(cellLib);
		tableauVehicul.addCell(cellCont);

		document.add(tableauVehicul);
		System.out.println("fin Identité vehicule");
	}

	// Tableau detail Vehicule
	public void tarifVehicule(Document document, Vehicule vehicule,
			ConduireVehicule conduireVehicule) throws DocumentException {
		// Tableau général information
		System.out.println("debut Tarif véhicule");
		PdfPTable tableauVehicul = new PdfPTable(6);
		tableauVehicul.setWidthPercentage(100);
		tableauVehicul.setWidths(new int[] { 16, 16, 16, 16, 16, 16 });

		// Contenu du tableau
		PdfPCell cellLib;
		PdfPCell cellCont;

		// 1ere colonne
		cellLib = new PdfPCell(new Phrase("Tarif", normalText));
		cellCont = new PdfPCell(new Phrase(vehicule.getSousCatVehicule()
				.getTarif().getLibelleTarif(), smallText));
		tableauVehicul.addCell(cellLib);
		tableauVehicul.addCell(cellCont);
		System.out.println("Tarif véhicule1");
		// 2e colonne
		cellLib = new PdfPCell(new Phrase("Catégorie", normalText));
		cellCont = new PdfPCell(new Phrase(vehicule.getSousCatVehicule()
				.getTarif().getLibelleTarif(), smallText));
		tableauVehicul.addCell(cellLib);
		tableauVehicul.addCell(cellCont);

		// 3e colonne
		cellLib = new PdfPCell(new Phrase("Conducteur habituel", normalText));
		if (conduireVehicule != null && conduireVehicule
				.getConducteur().getNonCond()!=null ) {
			cellCont = new PdfPCell(new Phrase(conduireVehicule
					.getConducteur().getNonCond(), smallText));
		} else {
			cellCont = new PdfPCell(new Phrase("", smallText));
		}
		System.out.println("Tarif véhicule2");
		tableauVehicul.addCell(cellLib);
		tableauVehicul.addCell(cellCont);

		tableauVehicul.setSpacingAfter(10);
		document.add(tableauVehicul);
		System.out.println("fin Tarif véhicule");
	}

	// Tableau de recap des garanties
	public void creerTableauGaranties(Document document,
			List<GarantieGarantieChoisie> listGartGartChoisies,
			GarantieChoisie garantieChoisie) throws DocumentException {
		PdfPTable tableauGaranties = new PdfPTable(11);
		tableauGaranties.setWidthPercentage(100);
		tableauGaranties.setWidths(new int[] { 13, 10, 10, 10, 7, 7, 7, 7, 7,
				10, 12 });
		PdfPCell cell;
		PdfPCell cellLib;
		PdfPCell cellCont;
		System.out.println("Tableau de recap des garanties");
		// Titre du tableau
		cell = new PdfPCell(new Phrase("GARANTIES SOUSCRITES", normalTitle));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setColspan(12);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableauGaranties.addCell(cell);

		// 1ere Ligne
		// Entête des colonnes
		cellLib = new PdfPCell(new Phrase("Nature risque", normalText));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellLib.setRowspan(2);
		tableauGaranties.addCell(cellLib);

		cellLib = new PdfPCell(new Phrase("Somme garantie", normalText));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellLib.setRowspan(2);
		tableauGaranties.addCell(cellLib);

		cellLib = new PdfPCell(new Phrase("Franchise", normalText));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellLib.setRowspan(2);
		tableauGaranties.addCell(cellLib);

		cellLib = new PdfPCell(new Phrase("Prime Annuelle", normalText));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellLib.setRowspan(2);
		tableauGaranties.addCell(cellLib);

		cellLib = new PdfPCell(new Phrase("Réduction & Majoration", normalText));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellLib.setColspan(5);
		tableauGaranties.addCell(cellLib);

		cellLib = new PdfPCell(new Phrase("Prime nette annuelle", normalTitle));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setRowspan(2);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableauGaranties.addCell(cellLib);

		cellLib = new PdfPCell(new Phrase("Prime comptant", normalTitle));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setRowspan(2);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableauGaranties.addCell(cellLib);

		cellLib = new PdfPCell(new Phrase("Bonus", normalText));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableauGaranties.addCell(cellLib);

		cellLib = new PdfPCell(new Phrase("Autre", normalText));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableauGaranties.addCell(cellLib);

		cellLib = new PdfPCell(new Phrase("Flotte", normalText));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableauGaranties.addCell(cellLib);

		cellLib = new PdfPCell(new Phrase("Malus", normalText));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableauGaranties.addCell(cellLib);

		cellLib = new PdfPCell(new Phrase("Montant", normalText));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableauGaranties.addCell(cellLib);

		// Fonction de la taille des garanties souscrite
		for (GarantieGarantieChoisie occurenceGartGartChoisie : listGartGartChoisies) {
			// Recuperer la gartgartchoisie
			
			GarantieGarantieChoisie garantieGarantieChoisie = new GarantieGarantieChoisie();
			garantieGarantieChoisie = occurenceGartGartChoisie;
			// recup la garantie
			Garantie garantie = new Garantie();
			garantie = garantieGarantieChoisie.getGarantie();

			// Contenu
			// 1e Colonne
			cellCont = new PdfPCell(new Phrase(garantie.getLibelleGarantie(),
					smallText));// Garanties
			cellCont.setHorizontalAlignment(Element.ALIGN_LEFT);
			tableauGaranties.addCell(cellCont);
			System.out.println(" Recuperer la gartgartchoisie2");
			// 2e Colonne
			cellCont = new PdfPCell(new Phrase(""
					+ garantieGarantieChoisie.getPrimeNetteProrata().setScale(
							2, BigDecimal.ROUND_HALF_UP), smallText));// Nature-Franchise
			cellCont.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tableauGaranties.addCell(cellCont);
			// 3e Colonne
			cellCont = new PdfPCell(new Phrase("" + garantie.getFranchise(),
					smallText));
			cellCont.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tableauGaranties.addCell(cellCont);
			// 4e Colonne
			cellCont = new PdfPCell(new Phrase(""
					+ garantieGarantieChoisie.getPrimeAnnuelle().setScale(2,
							BigDecimal.ROUND_HALF_UP), smallText));
			cellCont.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tableauGaranties.addCell(cellCont);
			// 5e Colonne
			cellCont = new PdfPCell(new Phrase(""
					+ garantieGarantieChoisie.getBonus().setScale(2,
							BigDecimal.ROUND_HALF_UP), smallText));
			cellCont.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tableauGaranties.addCell(cellCont);

			// 6e Colonne
			cellCont = new PdfPCell(new Phrase("non fourni", smallText));
			cellCont.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tableauGaranties.addCell(cellCont);

			// 7e Colonne
			cellCont = new PdfPCell(new Phrase("non fourni", smallText));
			cellCont.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tableauGaranties.addCell(cellCont);

			// 8e Colonne
			cellCont = new PdfPCell(new Phrase(""
					+ garantieGarantieChoisie.getMalus().setScale(2,
							BigDecimal.ROUND_HALF_UP), smallText));
			cellCont.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tableauGaranties.addCell(cellCont);

			// 9e Colonne
			cellCont = new PdfPCell(new Phrase("non fourni", smallText));
			cellCont.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tableauGaranties.addCell(cellCont);

			// 10e Colonne
			cellCont = new PdfPCell(new Phrase(""
					+ garantieGarantieChoisie.getPrimeNetteAnnuelle().setScale(
							2, BigDecimal.ROUND_HALF_UP), smallText));
			cellCont.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tableauGaranties.addCell(cellCont);

			// 11e Colonne
			cellCont = new PdfPCell(new Phrase(""
					+ garantieGarantieChoisie.getPrimeNetteProrata().setScale(
							2, BigDecimal.ROUND_HALF_UP), smallText));
			cellCont.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tableauGaranties.addCell(cellCont);
		}

		cellLib = new PdfPCell(new Phrase("Total", normalText));
		cellLib.setHorizontalAlignment(Element.ALIGN_LEFT);
		cellLib.setColspan(10);
		tableauGaranties.addCell(cellLib);
		System.out.println(" Recuperer la gartgartchoisie out");
		cellCont = new PdfPCell(new Phrase(""
				+ garantieChoisie.getPrimeNetteProrata().setScale(2,
						BigDecimal.ROUND_HALF_UP), smallText));
		cellCont.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableauGaranties.addCell(cellCont);


		document.add(tableauGaranties);
	}

	// Recap de la prime
	public void createtableRecapPrime(Document document,
			GarantieChoisie garantieChoisie) throws DocumentException {
		
		PdfPTable tableTotall = new PdfPTable(2);
		PdfPCell cell;
		System.out.println(" Recuperer la garchoisie");
		cell = new PdfPCell(new Phrase("Prime nette:", normalText));
		cell.setBorder(Rectangle.NO_BORDER);
		tableTotall.addCell(cell);
		cell = new PdfPCell(new Phrase(""
				+ garantieChoisie.getPrimeNetteProrata(), smallTextGras));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBorder(Rectangle.NO_BORDER);
		tableTotall.addCell(cell);

		cell = new PdfPCell(new Phrase("Accessoire:", normalText));
		cell.setBorder(Rectangle.NO_BORDER);
		tableTotall.addCell(cell);
		cell = new PdfPCell(
				new Phrase(""
						+ reportingAuto.getQuittance().getAccessoire()
								.doubleValue()
						/ (reportingAuto.getListVehiculeAssure().size()),
						smallTextGras));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBorder(Rectangle.NO_BORDER);
		tableTotall.addCell(cell);

		cell = new PdfPCell(new Phrase("Taxe d'enregistrement:", normalText));
		cell.setBorder(Rectangle.NO_BORDER);
		tableTotall.addCell(cell);
		cell = new PdfPCell(new Phrase(""
				+ reportingAuto.getQuittance().getTaxes(), smallTextGras));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBorder(Rectangle.NO_BORDER);
		tableTotall.addCell(cell);

		cell = new PdfPCell(new Phrase("FGA:", normalText));
		cell.setBorder(Rectangle.NO_BORDER);
		tableTotall.addCell(cell);
		cell = new PdfPCell(new Phrase(""
				+ reportingAuto.getQuittance().getFga(), smallTextGras));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBorder(Rectangle.NO_BORDER);
		tableTotall.addCell(cell);

		cell = new PdfPCell(new Phrase("Prime TTC:", normalText));
		cell.setBorder(Rectangle.NO_BORDER);
		tableTotall.addCell(cell);
		cell = new PdfPCell(new Phrase(""
				+ reportingAuto.getQuittance().getNetAPayer(), smallTextGras));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBorder(Rectangle.NO_BORDER);
		tableTotall.addCell(cell);

		// Tableau général
		PdfPTable tabgeneral = new PdfPTable(3);
		tabgeneral.setWidthPercentage(100);
		tabgeneral.setWidths(new int[] { 30, 30, 40 });
		PdfPCell cellVide = new PdfPCell();
		cellVide.setBorder(Rectangle.NO_BORDER);
		tabgeneral.addCell(cellVide);
		tabgeneral.addCell(cellVide);
		// PdfPCell cellTotal = new PdfPCell();
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.addElement(tableTotall);
		tabgeneral.addCell(cell);

		tabgeneral.setSpacingBefore(2);
		document.add(tabgeneral);
	}

	// Emargement
	public void creerEmagement(Document document) throws DocumentException {
String pv = "Abidjan2";
System.out.println(" Fin de document debut");
if(reportingAuto.getPointVente().getVille()!=null){
	pv = reportingAuto.getPointVente().getVille()
			.getLibelleVille();
}
		Paragraph dateJour = new Paragraph(new Chunk("Fait en 3 exemplaires à "
				+ pv + ", le " + sdf.format(new Date())));
		dateJour.setIndentationLeft(200);

		PdfPTable tabEmerg = new PdfPTable(3);
		tabEmerg.setWidthPercentage(100);
		PdfPCell cell;

		Chunk chunkSous = new Chunk("Le souscripteur");
		chunkSous.setUnderline(0.1f, -2f);
		cell = new PdfPCell(new Phrase(chunkSous));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);
		tabEmerg.addCell(cell);

		cell = new PdfPCell(new Phrase(""));
		cell.setBorder(Rectangle.NO_BORDER);
		tabEmerg.addCell(cell);

		Chunk chunkComp = new Chunk("Pour la Compagnie");
		chunkComp.setUnderline(0.1f, -2);
		cell = new PdfPCell(new Phrase(chunkComp));
		cell.setBorder(Rectangle.NO_BORDER);
		tabEmerg.addCell(cell);

		tabEmerg.setSpacingBefore(15);
		document.add(dateJour);
		document.add(tabEmerg);
		System.out.println(" Fin de document fin");
	}
	
	
	// Emargement
		public void creerEmagementAttestation(Document document) throws DocumentException {
	String pv = "Abidjan";
	System.out.println(" Fin de document debut");
	if(reportingAuto.getPointVente().getVille()!=null){
		pv = reportingAuto.getPointVente().getVille()
				.getLibelleVille();
	}
			Paragraph dateJour = new Paragraph(new Chunk("Fait à "
					+ pv + ", le " + sdf.format(new Date())+", "+"Valable pour 7 jours"));
			dateJour.setIndentationLeft(200);

			PdfPTable tabEmerg = new PdfPTable(3);
			tabEmerg.setWidthPercentage(100);
			PdfPCell cell;

			Chunk chunkSous = new Chunk("");
			chunkSous.setUnderline(0.1f, -2f);
			cell = new PdfPCell(new Phrase(chunkSous));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.NO_BORDER);
			tabEmerg.addCell(cell);

			cell = new PdfPCell(new Phrase(""));
			cell.setBorder(Rectangle.NO_BORDER);
			tabEmerg.addCell(cell);

			Chunk chunkComp = new Chunk("");
			chunkComp.setUnderline(0.1f, -2);
			cell = new PdfPCell(new Phrase(chunkComp));
			cell.setBorder(Rectangle.NO_BORDER);
			tabEmerg.addCell(cell);

			tabEmerg.setSpacingBefore(15);
			document.add(dateJour);
			document.add(tabEmerg);
			System.out.println(" Fin de document fin");
		}
		
	

	public static void addEmptyLine(Paragraph paragraph, int nbrLigne) {
		for (int i = 0; i < nbrLigne; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	public Paragraph sautLigne(int number) {
		Paragraph saut = new Paragraph();
		for (int i = 0; i < number; i++) {
			saut.add(new Paragraph(" "));
		}
		return saut;
	}

	public void ouvrirFicher() {
		System.out.println("début ouverture du fichier pdf");// clean After
		try {

			if ((new File("C:\\Etats\\Cond_Part\\AUTO\\" + nomFichier + ""))
					.exists()) {

				Process p = Runtime.getRuntime().exec(
						"rundll32 url.dll,FileProtocolHandler "
								+ "C:\\Etats\\Cond_Part\\AUTO\\" + nomFichier
								+ "");
				p.waitFor();

			} else {

				System.out.println("File is not exists");

			}

			System.out.println("Done");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println("Fin ouverture du fichier pdf");

	}
	
	public void printAttestation(){
		
	}

	public void printFile() {
		if (Desktop.isDesktopSupported()) {
			if (Desktop.getDesktop().isSupported(java.awt.Desktop.Action.PRINT)) {
				try {
					java.awt.Desktop.getDesktop().print(
							new File(repectoire + nomFichier));
				} catch (IOException ex) {
					// Traitement de l'exception
				}
			} else {
				// La fonction n'est pas supportée par votre système
				// d'exploitation
			}
		} else {
			// Desktop pas supportée par votre système d'exploitation
		}
	}

	public void openFile(String nomFichier) {

		if (Desktop.isDesktopSupported()) {
			if (Desktop.getDesktop().isSupported(java.awt.Desktop.Action.OPEN)) {
				try {
					java.awt.Desktop.getDesktop().open(
							new File(nomFichier));
				} catch (IOException ex) {
					// Traitement de l'exception
				}
			} else {
				// La fonction n'est pas supportée par votre système
				// d'exploitation
			}
		} else {
			// Desktop pas supportée par votre système d'exploitation
		}
	}

	public BigDecimal getTotalAccessoir() {
		return totalAccessoir;
	}

	public void setTotalAccessoir(BigDecimal totalAccessoir) {
		this.totalAccessoir = totalAccessoir;
	}

	public String getIdQuittance() {
		return idQuittance;
	}

	public void setIdQuittance(String idQuittance) {
		this.idQuittance = idQuittance;
	}

	public ReportFactoryAuto getReportFactoryAuto() {
		return reportFactoryAuto;
	}

	public void setReportFactoryAuto(ReportFactoryAuto reportFactoryAuto) {
		this.reportFactoryAuto = reportFactoryAuto;
	}

	public ReportingAuto getReportingAuto() {
		return reportingAuto;
	}

	public void setReportingAuto(ReportingAuto reportingAuto) {
		this.reportingAuto = reportingAuto;
	}

	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}

	

	

}
