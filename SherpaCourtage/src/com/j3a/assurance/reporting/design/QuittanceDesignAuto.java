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

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
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
import com.j3a.assurance.reporting.bean.ReportingAuto;
import com.j3a.assurance.reporting.bean.factory.ReportFactoryAuto;
import com.lowagie.text.Cell;

/**
 * 
 * @author J3A-POSTE1-A.Lekerand Bean permettant d'editer une quittance
 * 
 */

@Component
public class QuittanceDesignAuto implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	@Autowired
	ReportingAuto reportingAuto;
	@Autowired
	ReportFactoryAuto reportFactoryAuto;

	private String idQuittance;
	private BigDecimal totalAccessoir;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
	private File repectoire;
	private String nomFichier;

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
	private static Font normalTitle = new Font(Font.FontFamily.TIMES_ROMAN, 9,
			Font.BOLD);

	private static Font smallText = new Font(Font.FontFamily.COURIER, 8,
			Font.NORMAL);

	private static Font smallTextGras = new Font(Font.FontFamily.COURIER, 8,
			Font.BOLD);

	private static Font TITRE3 = new Font(Font.FontFamily.TIMES_ROMAN, 14,
			Font.BOLD);

	public static final String RESOURCE = "http://localhost:8080/Sherpa/resources/images/logo_j3a.jpg";

	public void editerQuittance(String idQuittance, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// Créer le dosier de stockage des fichier générés
		repectoire = new File("c:/Etats/Quittances/AUTO");
		repectoire.mkdirs();
		// Passer les info nécessaires à notre rapport à générer
		getReportFactoryAuto().setIdQuittance(idQuittance);
		reportingAuto = getReportFactoryAuto().reportProvider();
		if(reportingAuto!=null){
		
		try {
			Document document = new Document(PageSize.A4);
			document.setMargins(20, 20, 20, 20);
			nomFichier = reportingAuto.getQuittance().getCodeQuittance() + ".pdf";

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, baos);
			PdfWriter.getInstance(document, new FileOutputStream(repectoire
					+ "/" + nomFichier));

			document.open();
			addMetaData(document);
			// addTitlePage(document);
			addContent(document);
			document.close();

			/*
			 * // setting some response headers response.setHeader("Expires",
			 * "0"); response.setHeader("Cache-Control",
			 * "must-revalidate, post-check=0, pre-check=0");
			 * response.setHeader("Pragma", "public"); // setting the content
			 * type response.setContentType("application/pdf"); // the
			 * contentlength response.setContentLength(baos.size()); // write
			 * ByteArrayOutputStream to the ServletOutputStream OutputStream os
			 * = response.getOutputStream(); baos.writeTo(os); os.flush();
			 * os.close();
			 */
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur1");
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erreur2");

		}
		openFile();
		}
	}

	private static void addMetaData(Document document) {
		document.addTitle("Quittance Faculté");
		document.addSubject("Edition Pièces");
		document.addKeywords("Java, PDF, iText");
		document.addAuthor("J3A");
		document.addCreator("A.Lekerand");

	}

	private void addContent(Document document) throws DocumentException,
			MalformedURLException, IOException {
		// Ajout de logo
		Image logo = Image.getInstance(new URL(RESOURCE));
		logo.scalePercent(40f);
		document.add(logo);

		// Entête du document
		creerTitreDocument(document);
		createEteteDoc(document);

		// Information de la quittance
		creatTableInfo(document);

		// Table prime
		createTableDetailPrime(document);

		// Tableau des commissions
		createTableCommis(document);

		// Tableau de primeRecap
		createtableRecapPrime(document);

		// Emagement
		addEmagement(document);

	}

	// Titre du document
	public void creerTitreDocument(Document document) throws DocumentException {

		Paragraph titreDocument = new Paragraph(new Chunk("QUITTANCE", TITRE3));
		titreDocument.setAlignment(Element.ALIGN_CENTER);
		titreDocument.setSpacingAfter(30);
		document.add(titreDocument);
	}

	private void createEteteDoc(Document document) throws DocumentException {
		String quit = "Quittance N° " + reportingAuto.getQuittance().getCodeQuittance();
		PdfPTable tabEntete = new PdfPTable(1);

		PdfPCell cell = new PdfPCell();
		cell = new PdfPCell(new Phrase(quit, smallText));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.NO_BORDER);
		tabEntete.addCell(cell);

		// Date d'emission de la quittance
		cell = new PdfPCell(new Phrase("Date: "
				+ sdf.format(reportingAuto.getAvenant().getDateEmission()),
				smallText));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.NO_BORDER);
		tabEntete.addCell(cell);
		tabEntete.setSpacingAfter(25);
		document.add(tabEntete);
	}

	private void creatTableInfo(Document document) throws DocumentException {
		// Info sur le Souscripteur
		PdfPTable tabSous = new PdfPTable(4);
		tabSous.setWidths(new int[] { 15, 20, 15, 20 });

		tabSous.getDefaultCell().setBorder(Cell.NO_BORDER);
		PdfPCell cell;

		// 1er ligne
		tabSous.addCell(new Phrase("Nom:", normalText));
		cell = new PdfPCell(new Phrase(reportingAuto.getNom(), smallText));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(3);
		tabSous.addCell(cell);

		// 2em ligne
		tabSous.addCell(new Phrase("Adresse:", normalText));
		cell = new PdfPCell(new Phrase(
				reportingAuto.getPersonne().getAdresse(), smallText));
		cell.setBorder(Rectangle.NO_BORDER);
		tabSous.addCell(cell);
		tabSous.addCell(new Phrase("Tel:", normalText));
		tabSous.addCell(new Phrase(reportingAuto.getPersonne().getTelephone(),
				smallText));
		// 3em ligne
		tabSous.addCell(new Phrase("Mail:", normalText));
		cell = new PdfPCell(new Phrase(reportingAuto.getPersonne().getEmail(),
				smallText));
		cell.setBorder(Rectangle.NO_BORDER);
		tabSous.addCell(cell);
		tabSous.addCell(new Phrase("Fax:", normalText));
		tabSous.addCell(new Phrase(reportingAuto.getPersonne().getFax(),
				smallText));
		// 4em ligne
		tabSous.addCell(new Phrase("Intermédiaire:", normalText));
		cell = new PdfPCell(new Phrase("Non fornir", smallText));
		cell.setBorder(Rectangle.NO_BORDER);
		tabSous.addCell(cell);
		tabSous.addCell(new Phrase("Réseaux:", normalText));
		tabSous.addCell(new Phrase("non fourni", smallText));

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
		tabQuit.addCell(new Phrase(reportingAuto.getContrat().getNumPolice(),
				smallText));

		tabQuit.addCell(new Phrase("Categorie:", normalText));
		tabQuit.addCell(new Phrase(reportingAuto.getRisque().getCodeRisque(), smallText));

		// 2em ligne
		tabQuit.addCell(new Phrase("Avenant:", normalText));
		cell = new PdfPCell(new Phrase(reportingAuto.getAvenant().getNumAvenant(),
				smallText));
		cell.setBorder(Rectangle.NO_BORDER);

		tabQuit.addCell(cell);
		tabQuit.addCell(new Phrase("Mouvement:", normalText));
		tabQuit.addCell(new Phrase(reportingAuto.getAvenant().getMouvement(),
				smallText));

		// 3e Ligne
		tabQuit.addCell(new Phrase("Effet:", normalText));
		cell = new PdfPCell(new Phrase(sdf.format(reportingAuto.getAvenant()
				.getEffet()), smallText));
		cell.setBorder(Rectangle.NO_BORDER);

		tabQuit.addCell(cell);
		tabQuit.addCell(new Phrase("Echéance:", normalText));
		tabQuit.addCell(new Phrase(sdf.format(reportingAuto.getAvenant()
				.getEcheance()), smallText));

		// 4e Ligne
		tabQuit.addCell(new Phrase("Barème:", normalText));
		cell = new PdfPCell(new Phrase("Bareme", smallText));
		cell.setBorder(Rectangle.NO_BORDER);

		tabQuit.addCell(cell);
		tabQuit.addCell(new Phrase("Durée:", normalText));
		tabQuit.addCell(new Phrase("" + reportingAuto.getAvenant().getDuree(),
				smallText));
		tabInfo.addCell(tabSous);

		cell = new PdfPCell(new Phrase(""));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		tabInfo.addCell(cell);
		tabInfo.addCell(tabQuit);
		tabInfo.addCell("Tab quittance");

		tabInfo.setSpacingAfter(25);
		document.add(tabInfo);
	}

	@SuppressWarnings("unused")
	public void createTableDetailPrime(Document document)
			throws DocumentException {
		PdfPTable monTableau = new PdfPTable(8);
		monTableau.setWidthPercentage(100);
		monTableau.setWidths(new int[] { 30, 10, 10, 10, 10, 10, 10, 10 });// Fixer
																			// la
																			// taille
																			// du
																			// taleau
		PdfPCell cell;
		PdfPCell celFL2;

		// Libellé du tableau
		cell = new PdfPCell(new Phrase("Detail de la Prime", normalText));
		cell.setColspan(8);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.NO_BORDER);
		monTableau.addCell(cell);

		celFL2 = new PdfPCell(new Phrase("Catégorie", normalText));
		celFL2.setRowspan(2);
		celFL2.setBackgroundColor(BaseColor.LIGHT_GRAY);
		celFL2.setHorizontalAlignment(Element.ALIGN_LEFT);
		monTableau.addCell(celFL2);

		celFL2 = new PdfPCell(new Phrase("Prime nette", normalText));
		celFL2.setRowspan(2);
		celFL2.setBackgroundColor(BaseColor.LIGHT_GRAY);
		celFL2.setHorizontalAlignment(Element.ALIGN_CENTER);
		monTableau.addCell(celFL2);

		// Gerer la fusion des collones
		PdfPCell celFC4;
		celFC4 = new PdfPCell(new Phrase("Accesoires", normalText));
		celFC4.setColspan(4);
		celFC4.setBackgroundColor(BaseColor.LIGHT_GRAY);
		celFC4.setHorizontalAlignment(Cell.ALIGN_CENTER);
		monTableau.addCell(celFC4);

		celFL2 = new PdfPCell(new Phrase("Taxes", normalText));
		celFL2.setRowspan(2);
		celFL2.setBackgroundColor(BaseColor.LIGHT_GRAY);
		celFL2.setHorizontalAlignment(Cell.ALIGN_CENTER);
		monTableau.addCell(celFL2);

		celFL2 = new PdfPCell(new Phrase("Prime Total", normalText));
		celFL2.setRowspan(2);
		celFL2.setBackgroundColor(BaseColor.LIGHT_GRAY);
		celFL2.setHorizontalAlignment(Cell.ALIGN_CENTER);
		monTableau.addCell(celFL2);
		// deuxieme ligne
		cell = new PdfPCell(new Phrase("CNie", normalText));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		monTableau.addCell(cell);
		cell = new PdfPCell(new Phrase("Interméd.", normalText));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		monTableau.addCell(cell);
		cell = new PdfPCell(new Phrase("Gest°", normalText));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		monTableau.addCell(cell);
		cell = new PdfPCell(new Phrase("Total", normalText));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		monTableau.addCell(cell);

		// Troisième Ligne
		monTableau.addCell(new Phrase(reportingAuto.getRisque()
				.getLibelleRisque(), normalText));
		cell = new PdfPCell(new Phrase(reportingAuto.getQuittance()
				.getPrimeNette().toString(), smallText));// prime nette
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		monTableau.addCell(cell);

		cell = new PdfPCell(new Phrase(reportingAuto.getQuittance()
				.getAccCnie().toString(), smallText));// Acc compagnie
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		monTableau.addCell(cell);

		cell = new PdfPCell(new Phrase(reportingAuto.getQuittance()
				.getAccIntrerm().toString(), smallText));// acc interm
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		monTableau.addCell(cell);

		cell = new PdfPCell(new Phrase(reportingAuto.getQuittance()
				.getAccGestionnaire().toString(), smallText));// Acc
																// Gestionnaire
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		monTableau.addCell(cell);

		cell = new PdfPCell(new Phrase(reportingAuto.getQuittance()
				.getAccessoire().toString(), smallText));// Total Accessoire
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		monTableau.addCell(cell);

		cell = new PdfPCell(new Phrase(reportingAuto.getQuittance().getTaxes()
				.toString(), smallText));// prime
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		monTableau.addCell(cell);

		cell = new PdfPCell(new Phrase(reportingAuto.getQuittance()
				.getNetAPayer().toString(), smallText));// prime
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		monTableau.addCell(cell);

		// Quatrième Lignes
		cell = new PdfPCell(new Phrase("Total", normalText));
		cell.setHorizontalAlignment(Rectangle.LEFT);
		monTableau.addCell(cell);

		cell = new PdfPCell(new Phrase(reportingAuto.getQuittance()
				.getPrimeNette().toString(), smallTextGras));// Total prime
																// nette
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		monTableau.addCell(cell);

		cell = new PdfPCell(new Phrase(reportingAuto.getQuittance()
				.getAccCnie().toString(), smallTextGras));// total compagnie
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		monTableau.addCell(cell);

		cell = new PdfPCell(new Phrase("", smallText));// total intermediaire
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		monTableau.addCell(cell);

		cell = new PdfPCell(new Phrase("", smallText));// toal gestion
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		monTableau.addCell(cell);

		cell = new PdfPCell(new Phrase(reportingAuto.getQuittance()
				.getAccessoire().toString(), smallTextGras));// Total
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		monTableau.addCell(cell);

		cell = new PdfPCell(new Phrase(""
				+ reportingAuto.getQuittance().getTaxes(), smallTextGras));// Total
																			// taxe
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		monTableau.addCell(cell);

		cell = new PdfPCell(new Phrase(reportingAuto.getQuittance()
				.getNetAPayer().toString(), smallTextGras));// Total général
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		monTableau.addCell(cell);

		monTableau.setSpacingAfter(25);
		document.add(monTableau);
	}

	public void createTableCommis(Document document) throws DocumentException {
		PdfPTable tableau = new PdfPTable(6);
		tableau.setWidthPercentage(100);
		tableau.setWidths(new int[] { 25, 15, 15, 15, 15, 15 });// Fixer la
																// taille du
																// taleau
		PdfPCell cell;

		// Libellé du tableau
		cell = new PdfPCell(new Phrase("Detail des commissions", normalText));
		cell.setColspan(6);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(Rectangle.NO_BORDER);
		tableau.addCell(cell);

		// 1ere ligne
		cell = new PdfPCell(new Phrase("Catégorie", normalText));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableau.addCell(cell);

		cell = new PdfPCell(new Phrase("Commission intermédiaire", normalText));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableau.addCell(cell);

		cell = new PdfPCell(new Phrase("Commission conseiller", normalText));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableau.addCell(cell);

		cell = new PdfPCell(new Phrase("Commission gestionnaire", normalText));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableau.addCell(cell);

		cell = new PdfPCell(new Phrase("Commission d'apérition", normalText));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableau.addCell(cell);

		cell = new PdfPCell(new Phrase("Total commision", normalText));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableau.addCell(cell);

		// 2e Ligne
		cell = new PdfPCell(new Phrase(reportingAuto.getRisque().getCodeRisque(),
				normalText));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		tableau.addCell(cell);

		cell = new PdfPCell(new Phrase(reportingAuto.getQuittance()
				.getComConseil().toString(), smallText));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableau.addCell(cell);

		cell = new PdfPCell(new Phrase(reportingAuto.getQuittance()
				.getComConseil().toString(), smallText));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableau.addCell(cell);

		cell = new PdfPCell(new Phrase(reportingAuto.getQuittance()
				.getComGestionnaire().toString(), smallText));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableau.addCell(cell);

		cell = new PdfPCell(new Phrase(reportingAuto.getQuittance()
				.getComAperition().toString(), smallText));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableau.addCell(cell);

		cell = new PdfPCell(new Phrase(reportingAuto.getQuittance()
				.getCommision().toString(), smallText));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		tableau.addCell(cell);
		document.add(tableau);

	}

	public void createtableRecapPrime(Document document)
			throws DocumentException {
		PdfPTable tableTotall = new PdfPTable(2);
		PdfPCell cell;

		cell = new PdfPCell(new Phrase("Prime nette:", normalText));
		cell.setBorder(Rectangle.NO_BORDER);
		tableTotall.addCell(cell);
		cell = new PdfPCell(new Phrase(""
				+ reportingAuto.getQuittance().getPrimeNette(), smallText));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBorder(Rectangle.NO_BORDER);
		tableTotall.addCell(cell);

		cell = new PdfPCell(new Phrase("Accessoire:", normalText));
		cell.setBorder(Rectangle.NO_BORDER);
		tableTotall.addCell(cell);
		cell = new PdfPCell(new Phrase(reportingAuto.getQuittance()
				.getAccessoire().toString(), smallText));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBorder(Rectangle.NO_BORDER);
		tableTotall.addCell(cell);

		cell = new PdfPCell(new Phrase("Taxe d'enregistrement:", normalText));
		cell.setBorder(Rectangle.NO_BORDER);
		tableTotall.addCell(cell);
		cell = new PdfPCell(new Phrase(reportingAuto.getQuittance().getTaxes()
				.toString(), smallText));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBorder(Rectangle.NO_BORDER);
		tableTotall.addCell(cell);

		cell = new PdfPCell(new Phrase("FGA:", normalText));
		cell.setBorder(Rectangle.NO_BORDER);
		tableTotall.addCell(cell);
		cell = new PdfPCell(new Phrase(""
				+ reportingAuto.getQuittance().getFga(), smallText));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBorder(Rectangle.NO_BORDER);
		tableTotall.addCell(cell);

		cell = new PdfPCell(new Phrase("Prime TTC:", normalText));
		cell.setBorder(Rectangle.NO_BORDER);
		tableTotall.addCell(cell);
		cell = new PdfPCell(new Phrase(reportingAuto.getQuittance()
				.getNetAPayer().toString(), smallTextGras));
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
		PdfPCell cellTotal = new PdfPCell();
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.addElement(tableTotall);
		tabgeneral.addCell(cell);
		tabgeneral.setSpacingBefore(2);
		document.add(tabgeneral);
	}

	public void addEmagement(Document document) throws DocumentException {

		Paragraph dateEdition = new Paragraph(new Chunk(
				"Fait en 3 exemplaires à "
						+ reportingAuto.getPointVente().getVille()
								.getLibelleVille()
						+ ", le "
						+ sdf.format(reportingAuto.getAvenant()
								.getDateEmission())));
		dateEdition.setIndentationLeft(200);
		document.add(dateEdition);

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
		// document.add(dateJour);
		document.add(tabEmerg);

	}

	public void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	// Ouverture du fichier pdf de la quittance

	public void ouvrirFicher() {
		System.out.println("début ouverture du fichier pdf");// clean After
		try {

			if ((new File("C:\\Etats\\Quittances\\AUTO\\" + nomFichier + ""))
					.exists()) {

				Process p = Runtime.getRuntime().exec(
						"rundll32 url.dll,FileProtocolHandler "
								+ "C:\\Etats\\Quittances\\AUTO\\" + nomFichier
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

	public void openFile() {

		if (Desktop.isDesktopSupported()) {
			if (Desktop.getDesktop().isSupported(java.awt.Desktop.Action.OPEN)) {
				try {
					java.awt.Desktop.getDesktop().open(
							new File(repectoire + "/" + nomFichier));
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

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			// step 1
			Document document = new Document();
			// step 2
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, baos);
			// step 3
			document.open();
			// step 4
			document.add(new Paragraph(
					String.format(
							"You have submitted the following text using the %s method:",
							request.getMethod())));
			document.add(new Paragraph(""));
			// step 5
			document.close();

			// setting some response headers
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control",
					"must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			// setting the content type
			response.setContentType("application/pdf");
			// the contentlength
			response.setContentLength(baos.size());
			// write ByteArrayOutputStream to the ServletOutputStream
			OutputStream os = response.getOutputStream();
			baos.writeTo(os);
			os.flush();
			os.close();
		} catch (DocumentException e) {
			throw new IOException(e.getMessage());
		}
	}

	public void editionQuittanceService() throws ServletException, IOException {
		editerQuittance(idQuittance, (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest(),
				(HttpServletResponse) FacesContext.getCurrentInstance()
						.getExternalContext().getResponse());
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

	public ReportingAuto getReportingAuto() {
		return reportingAuto;
	}

	public void setReportingAuto(ReportingAuto reportingAuto) {
		this.reportingAuto = reportingAuto;
	}

	public ReportFactoryAuto getReportFactoryAuto() {
		return reportFactoryAuto;
	}

	public void setReportFactoryAuto(ReportFactoryAuto reportFactoryAuto) {
		this.reportFactoryAuto = reportFactoryAuto;
	}
}
