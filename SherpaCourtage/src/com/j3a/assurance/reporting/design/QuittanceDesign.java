package com.j3a.assurance.reporting.design;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.j3a.assurance.reporting.bean.QuittanceReport;
import com.j3a.assurance.reporting.bean.factory.QuittanceReportFactory;
import com.lowagie.text.Cell;

public class QuittanceDesign {

	private static Logger logger = Logger.getLogger(QuittanceDesign.class);

	private static int compteur;
	// private static String FILE = "c:/reports/Quittance.pdf";
	private static Font TITRE1 = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD);
	private static Font TITRE3 = new Font(Font.FontFamily.TIMES_ROMAN, 14,
			Font.BOLD);
	private static Font textFont = new Font(Font.FontFamily.HELVETICA, 10);
	private static Font TITRE2 = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 10,
			Font.BOLD);
	private String chemainPDF;
	private QuittanceReportFactory quittanceReportFactory;
	private QuittanceReport reportField;


	public void generateRapport() {
		File dir = new File("c:/reports/quittances");
		dir.mkdirs();	
		System.out.println("<----getQuittanceReportFactory() :"
				+ getQuittanceReportFactory().getIdQuittance());
		reportField = getQuittanceReportFactory().quittanceReportProvider();
		System.out.println("<-----------Quittance id :"
				+ reportField);
		try {
			// On instancie le nouveau "Document"
			Document document = new Document(PageSize.A4);
			// On instancie le nouveau "PdfWriter"
			
			PdfWriter writer = PdfWriter.getInstance(
					document,
					new FileOutputStream("c:/reports/quittances/Quittance_"
							+ reportField.getIdQuittance() + "-" + compteur
							+ ".pdf"));
			
			
			chemainPDF = "C:/reports/quittances/Quittance_"
					+ reportField.getIdQuittance() + "-" + compteur
					+ ".pdf";

			// On ouvre notre "Document"
			document.open();
			// Ici vous allez insérer le code qui vous permettra de créer votre
			// document .pdf
			ajoutTitre(document);
			ajoutTab1(document);
			Paragraph paragraph = new Paragraph();
			ajoutLigneVide(paragraph, 0);
			document.add(paragraph);
			ajoutTab2(document);
			ajoutTab3(document);
			ajoutTab4(document);
			// ajoutCarateristiquesVehicule(document);
			// ajoutGaranties(document);
			// On ferme notre "Document"
			document.close();
			System.out.println("rapport généré");
			logger.info("quittance générée");
			compteur = compteur + 1;
		
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			logger.error("erreur de génération de la quittance");
			e.printStackTrace();
		}
	}

	private void ajoutTab1(Document document) throws DocumentException {

		/*************** Tableau 1 **********/
		PdfPTable table = new PdfPTable(6);
		PdfPCell cell;
		/* construction tableau interne 1 */
		PdfPTable tableInsert = new PdfPTable(3);
		tableInsert.getDefaultCell().setBorder(Cell.NO_BORDER);
		tableInsert.addCell(createCell((Phrase) ajoutElement("Phrase", "Agence", smallBold), 1));
		tableInsert.addCell(createCell((Phrase) ajoutElement("Phrase", reportField.getAgence(),textFont), 2));
		cell = new PdfPCell(tableInsert);
		cell.setColspan(3);
		table.addCell(cell);
		/* construction tableau interne 1 */
		tableInsert = new PdfPTable(3);
		tableInsert.getDefaultCell().setBorder(Cell.NO_BORDER);
		tableInsert.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "Assurance", smallBold), 1));
		tableInsert.addCell(createCell(
				(Phrase) ajoutElement("Phrase",
						reportField.getTypeAssurance(), textFont), 2));
		cell = new PdfPCell(tableInsert);
		cell.setColspan(3);
		table.addCell(cell);
		table.setSpacingAfter(5);
		document.add(table);
		System.out.println("tableau 1 ajouté");

	}

	private void ajoutTab2(Document document) throws DocumentException {
		/*************** Tableau 2 **********/
		PdfPTable table = new PdfPTable(6);
		PdfPCell cell;
		/* construction tableau interne 1 */
		PdfPTable tableInsert = new PdfPTable(3);
		tableInsert.getDefaultCell().setBorder(Cell.NO_BORDER);
		tableInsert.addCell(createCell((Phrase) ajoutElement("Phrase", "Contrat", smallBold), 1));
		tableInsert.addCell(createCell((Phrase) ajoutElement("Phrase", reportField.getPolice(),textFont), 2));
		cell = new PdfPCell(tableInsert);
		cell.setColspan(3);
		table.addCell(cell);
		/* construction tableau interne 1 */
		tableInsert = new PdfPTable(3);
		tableInsert.getDefaultCell().setBorder(Cell.NO_BORDER);
		tableInsert.addCell(createCell((Phrase) ajoutElement("Phrase", "Avenant", smallBold), 1));
		tableInsert.addCell(createCell((Phrase) ajoutElement("Phrase", reportField.getNumAvenant(),textFont), 2));
		cell = new PdfPCell(tableInsert);
		cell.setColspan(3);
		table.addCell(cell);
		table.setSpacingAfter(5);
		document.add(table);
		System.out.println("tableau 2 ajouté");

	}

	private void ajoutTab3(Document document) throws DocumentException {

		PdfPTable table = new PdfPTable(6);
		PdfPCell cell;
		/* insertion du nom */
		PdfPTable tableInsert = new PdfPTable(3);
		tableInsert.getDefaultCell().setBorder(Cell.NO_BORDER);
		tableInsert.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "Nom", smallBold), 1));
		tableInsert
				.addCell(createCell(
						(Phrase) ajoutElement("Phrase", reportField.getNom(),
								textFont), 2));
		cell = new PdfPCell(tableInsert);
		cell.setColspan(6);
		table.addCell(cell);
		/* insertion de l'adresse */
		tableInsert = new PdfPTable(3);
		tableInsert.getDefaultCell().setBorder(Cell.NO_BORDER);
		tableInsert.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "Adresse", smallBold), 1));
		tableInsert.addCell(createCell(
				(Phrase) ajoutElement("Phrase", reportField.getAdresse(),
						textFont), 2));
		cell = new PdfPCell(tableInsert);
		cell.setColspan(6);
		table.addCell(cell);

		/* insertion de date d'effet et écheance */
		tableInsert = new PdfPTable(3);
		tableInsert.getDefaultCell().setBorder(Cell.NO_BORDER);
		tableInsert.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "Date d'effet", smallBold), 1));
		tableInsert.addCell(createCell(
				(Phrase) ajoutElement("Phrase",
						"" + reportField.getDateEffet(), textFont), 2));
		cell = new PdfPCell(tableInsert);
		cell.setColspan(3);
		table.addCell(cell);

		/* insertion de date effet et écheance */
		tableInsert = new PdfPTable(3);
		tableInsert.getDefaultCell().setBorder(Cell.NO_BORDER);
		tableInsert.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "Date d'écheance", smallBold),
				1));
		tableInsert.addCell(createCell(
				(Phrase) ajoutElement("Phrase",
						"" + reportField.getDateEcheance(), textFont), 2));
		cell = new PdfPCell(tableInsert);
		cell.setColspan(3);
		table.addCell(cell);
		table.setSpacingAfter(5);
		document.add(table);
		System.out.println("tableau 3 ajouté");

	}

	private void ajoutTab4(Document document) throws DocumentException {

		PdfPTable table = new PdfPTable(6);
		PdfPCell cell;
		/* reference de paiement */
		PdfPTable tableInsert = new PdfPTable(3);
		tableInsert.getDefaultCell().setBorder(Cell.NO_BORDER);
		tableInsert.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "Références de paiement",
						smallBold), 6));
		cell = new PdfPCell(tableInsert);
		cell.setColspan(3);
		table.addCell(cell);
		/* insertion de l'adresse */
		tableInsert = new PdfPTable(2);
		tableInsert.getDefaultCell().setBorder(Cell.NO_BORDER);
		tableInsert.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "Prime Nette", smallBold), 1));
		tableInsert.addCell(createCell(
				(Phrase) ajoutElement("Phrase",
						"" + reportField.getPrimeNette(), textFont), 1));
		tableInsert.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "Accessoires", smallBold), 1));
		tableInsert.addCell(createCell(
				(Phrase) ajoutElement("Phrase",
						"" + reportField.getAccessoires(), textFont), 1));
		tableInsert.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "Taxes", smallBold), 1));
		tableInsert.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "" + reportField.getTaxes(),
						textFont), 1));
		tableInsert.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "Timbre", smallBold), 1));
		tableInsert.addCell(createCell(
				(Phrase) ajoutElement("Phrase", " ", textFont), 1));
		tableInsert.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "Prime totale", smallBold), 1));
		tableInsert.addCell(createCell(
				(Phrase) ajoutElement("Phrase",
						"" + reportField.getNetAPayer(), smallBold), 1));

		cell = new PdfPCell(tableInsert);
		cell.setColspan(3);
		table.addCell(cell);
		table.setSpacingAfter(5);

		document.add(table);
		System.out.println("tableau 3 ajouté");
	}

	private void ajoutTitre(Document document) throws DocumentException {

		Paragraph titre = new Paragraph();

		Paragraph paragraph = new Paragraph("QUITTANCE N°"
				+ reportField.getIdQuittance(), TITRE3);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		titre.add(paragraph);
		ajoutLigneVide(titre, 2);
		document.add(titre);
	}

	/* Aide à la création d'une cellule */
	private static PdfPCell createCell(Phrase phrase, int colspan) {
		PdfPCell cell = new PdfPCell(phrase);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(colspan);
		// cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		return cell;
	}

	/* construction d'un element */
	private static Element ajoutElement(String elementType, String elementName,
			Font font) {
		if (elementType.equals("Phrase")) {
			Phrase phrase = new Phrase();
			phrase.setFont(font);
			Chunk chunk = new Chunk(elementName);
			phrase.add(chunk);
			return phrase;
		}
		if (elementType.equals("Paragraph")) {
			Paragraph paragraph = new Paragraph();
			Chunk chunk = new Chunk(elementName);
			paragraph.add(chunk);
			paragraph.setFont(font);
			return paragraph;
		}

		return null;
	}

	/* ajout de ligne vide entre paragraph */
	private static void ajoutLigneVide(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	public QuittanceReportFactory getQuittanceReportFactory() {
		return quittanceReportFactory;
	}

	public void setQuittanceReportFactory(
			QuittanceReportFactory quittanceReportFactory) {
		this.quittanceReportFactory = quittanceReportFactory;
	}

	public String getChemainPDF() {
		return chemainPDF;
	}

	public void setChemainPDF(String chemainPDF) {
		this.chemainPDF = chemainPDF;
	}
	
}
