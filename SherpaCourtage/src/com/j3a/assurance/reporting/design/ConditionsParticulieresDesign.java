package com.j3a.assurance.reporting.design;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.log4j.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.j3a.assurance.model.Quittance;
import com.j3a.assurance.reporting.bean.ContratReport;
import com.j3a.assurance.reporting.bean.PrimeByGarantie;
import com.j3a.assurance.reporting.bean.VehiculeAttributsBean;
import com.j3a.assurance.reporting.bean.factory.ContratReportFactory;
import com.lowagie.text.Cell;

/**
 * classe qui construit le mod�le du rapport de la condition particuli�re par
 * vehicule
 * 
 * @author J3A-Poste4
 * 
 */
public class ConditionsParticulieresDesign {

	private static Logger logger = Logger
			.getLogger(ConditionsParticulieresDesign.class);

	private static String FILE = "c:/reports/Contrat.pdf";
	private static Font TITRE1 = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD);
	private static Font TITRE3 = new Font(Font.FontFamily.TIMES_ROMAN, 14,
			Font.BOLD);
	private static Font textFont = new Font(Font.FontFamily.HELVETICA, 8);
	private static Font TITRE2 = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 8,
			Font.BOLD);
	private static int compteur;
	private String idQuittance;
	/**
	 * @see ContratReportFactory
	 */
	private ContratReportFactory contratReportFactory;

	/**
	 * methode qui edite les conditions particuliere de chaque vehicule actif
	 * de la flotte
	 * @author N'ZI
	 */
	public void generateRapports() {
		compteur = 0;
		getContratReportFactory().ContratReportProvider(getIdQuittance());
		ContratReport contratReport = getContratReportFactory()
				.getContratReport();
		for (VehiculeAttributsBean attributs : contratReport
				.getListVehiculeAttributs()) {
			compteur = compteur + 1;
			this.constructeurRapport(contratReport, attributs);
		}

	}

	/**
	 * methode qui construit les conditions Particuliere d'un vehicule
	 * 
	 * @author J3A-Poste4
	 * @param contratReport
	 * @param vehiculeAttributsBean
	 */
	public void constructeurRapport(ContratReport contratReport,
			VehiculeAttributsBean vehiculeAttributsBean) {
		File dir = new File("c:/reports/contrats");
		dir.mkdirs();
		try {

			// On instancie le nouveau "Document"
			Document document = new Document(PageSize.A4);
			document.setMargins(4, 4, 20, 20);
			// On instancie le nouveau "PdfWriter"
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream("c:/reports/contrats/Contrat_"
							+ contratReport.getContrat().getNumPolice() + "_"
							+ compteur + ".pdf"));// libelle<<contrat_police_compteur>>

			// On ouvre notre "Document"
			document.open();
			// Ici vous allez ins�rer le code qui vous permettra de cr�er votre
			// document .pdf
			ajoutTitre(document);
			ajoutInfosGenerales(document, contratReport);
			ajoutCarateristiquesVehicule(document, contratReport,
					vehiculeAttributsBean);
			ajoutGaranties(document, contratReport, vehiculeAttributsBean);
			calculPrimeGlobale(document, contratReport.getQuittance());
			// On ferme notre "Document"
			document.close();
			System.out.println("rapport g�n�r�");
			logger.info("conditions particuli�res g�n�r�es");
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			logger.error("erreur de g�n�ration des conditions particuli�res");
			e.printStackTrace();
		}
	}

	public void ajoutInfosGenerales(Document document,
			ContratReport contratReport) throws DocumentException {

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100);
		// the cell object
		PdfPCell cell;

		/********* Premi�re ligne element 1 **********/
		Chunk chunk = new Chunk("NUMERO CLIENT : ");// construction du chunk
		Phrase phrase = (Phrase) ajoutElement("Phrase", "", textFont);
		phrase.add(chunk);// ajout du chunk � la phrase
		// on renseigne le numero du client
		chunk = new Chunk(contratReport.getPersonne().getNumSouscripteur());
		phrase.add(chunk);
		// we add a cell with colspan 2
		table.addCell(createCell(phrase, 2));
		/********* Premi�re ligne element 2 **********/
		chunk = new Chunk("POLICE : ");// construction du chunk
		phrase = (Phrase) ajoutElement("Phrase", "", textFont);
		phrase.add(chunk);// ajout du chunk � la phrase
		chunk = new Chunk(contratReport.getContrat().getNumPolice());
		phrase.add(chunk);
		// we add a cell with colspan 2
		table.addCell(createCell(phrase, 2));
		/********* Premi�re ligne element 3 **********/
		chunk = new Chunk("ATTESTATION : ");// construction du chunk
		phrase = (Phrase) ajoutElement("Phrase", "", textFont);
		phrase.add(chunk);// ajout du chunk � la phrase
		chunk = new Chunk("101852");// construction du chunk
		phrase.add(chunk);
		// we add a cell with colspan 2
		table.addCell(createCell(phrase, 2));

		/********* Rubrique Souscripteur **********/
		chunk = new Chunk("SOUSCRIPTEUR");// construction du chunk
		phrase = (Phrase) ajoutElement("Phrase", "", smallBold);
		phrase.add(chunk);// ajout du chunk � la phrase
		// we add a cell with colspan 3
		table.addCell(createCell(phrase, 3));

		/********* Rubrique ASSURE(E) **********/
		chunk = new Chunk("ASSURE(E)");// construction du chunk
		phrase = (Phrase) ajoutElement("Phrase", "", smallBold);
		phrase.add(chunk);// ajout du chunk � la phrase
		// we add a cell with colspan 3
		table.addCell(createCell(phrase, 3));

		/********* detail rubrique Souscripteur **********/
		phrase = (Phrase) ajoutElement("Phrase", "", textFont);
		Phrase phNom = new Phrase(contratReport.getPersonne()
				.getNomRaisonSociale());
		phrase.add(phNom + "\n");
		// adresse et t�l�phone
		Phrase phInfos = new Phrase();
		Phrase adresse = new Phrase("Adresse ");
		phInfos.add(adresse);
		Phrase tel = new Phrase("T�l�phone ");
		phInfos.add(tel);
		phrase.add(phInfos);
		// we add a cell with colspan 3
		table.addCell(createCell(phrase, 3));

		/********* detail rubrique Assur�(e) **********/
		phrase = (Phrase) ajoutElement("Phrase", "", textFont);
		phNom = new Phrase(contratReport.getPersonne().getNomRaisonSociale());
		phrase.add(phNom + "\n");
		// adresse et t�l�phone
		phInfos = new Phrase();
		adresse = new Phrase("Adresse ");
		phInfos.add(adresse);
		tel = new Phrase("T�l�phone ");
		phInfos.add(tel);
		phrase.add(phInfos);
		// we add a cell with colspan 3
		table.addCell(createCell(phrase, 3));

		/****** informations suppl�mentaires ******/
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "DATE EFFET", smallBold), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "DATE ECHEANCE", smallBold), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "DUREE", smallBold), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "TYPE DE CONTRAT", smallBold),
				1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "AVENANT", smallBold), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "AGENT", smallBold), 1));

		/****** informations suppl�mentaires ******/
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", ""
						+ contratReport.getAvenant().getEffet(), textFont), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", ""
						+ contratReport.getAvenant().getEcheance(), textFont),
				1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", ""
						+ contratReport.getAvenant().getDuree(), textFont), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", ""
						+ contratReport.getContrat().getModeReconduction(), textFont), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", contratReport.getAvenant()
						.getNumAvenant(), textFont), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "B000", textFont), 1));
		table.setSpacingAfter(4);
		document.add(table);
		System.out.println("tableau ajout�");
	}

	/**
	 * Cette methode peuple la partie des carateristiques d'un vehicule de la
	 * flotte de la conditions Particuli�res
	 * 
	 * @author J3A-Poste4
	 * @param document
	 * @param contratReport
	 * @param vehiculeAttributsBean
	 * @throws DocumentException
	 */
	public void ajoutCarateristiquesVehicule(Document document,
			ContratReport contratReport,
			VehiculeAttributsBean vehiculeAttributsBean)
			throws DocumentException {

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100);
		PdfPCell cell;
		/* titre du tableau */
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase",
						"CARACTERISTIQUES DU VEHICULE ASSURE", smallBold), 6));
		/* construction tableau interne 1 */
		PdfPTable tableInsert = new PdfPTable(2);

		tableInsert.getDefaultCell().setBorder(Cell.NO_BORDER);

		tableInsert.addCell(new Phrase("Immatriculation : ", textFont));
		tableInsert.addCell(new Phrase(vehiculeAttributsBean
				.getVehicule().getNumImmat(), textFont));

		tableInsert.addCell(new Phrase("Marque : ", textFont));
		tableInsert.addCell(new Phrase(vehiculeAttributsBean
				.getVehicule().getMarque(), textFont));

		tableInsert.addCell(new Phrase("Type : ", textFont));
		tableInsert.addCell(new Phrase(vehiculeAttributsBean
				.getVehicule().getTypeVehicule(), textFont));

		tableInsert.addCell(new Phrase("Genre : ", textFont));
		tableInsert.addCell(new Phrase(vehiculeAttributsBean
				.getVehicule().getGenre(), textFont));

		tableInsert.addCell(new Phrase("Carroserie : ", textFont));
		tableInsert.addCell(new Phrase(vehiculeAttributsBean
				.getVehicule().getCarrosserie(), textFont));

		tableInsert.addCell(new Phrase("Num�ro de s�rie : ", textFont));
		tableInsert.addCell(new Phrase(vehiculeAttributsBean
				.getVehicule().getChassis(), textFont));

		tableInsert.addCell(new Phrase("Immat pr�c�d. : ", textFont));
		tableInsert.addCell(new Phrase(vehiculeAttributsBean
				.getVehicule().getNumImmatPrec(), textFont));

		tableInsert.addCell(new Phrase("1�re Mise en cir : ", textFont));
		tableInsert.addCell(new Phrase(""
				+ vehiculeAttributsBean.getVehicule()
						.getDatePremiereCirc(), textFont));

		/* Insertion du tableau dans la cellule */
		cell = new PdfPCell(tableInsert);
		cell.setColspan(2);
		table.addCell(cell);

		/* construction tableau interne 2 */
		tableInsert = new PdfPTable(2);

		tableInsert.getDefaultCell().setBorder(Cell.NO_BORDER);

		tableInsert.addCell(new Phrase("Source d'�nergie: ", textFont));
		tableInsert.addCell(new Phrase(vehiculeAttributsBean
				.getVehicule().getEnergie(), textFont));

		tableInsert.addCell(new Phrase("Nbre de place : ", textFont));
		tableInsert.addCell(new Phrase(""
				+ vehiculeAttributsBean.getVehicule()
						.getNbrePlaceCab(), textFont));

		tableInsert.addCell(new Phrase("Puis Adminis : ", textFont));
		tableInsert.addCell(new Phrase(""
				+ vehiculeAttributsBean.getVehicule().getPuissFisc(),
				textFont));

		tableInsert.addCell(new Phrase("P.T.A.C : ", textFont));
		tableInsert.addCell(new Phrase("", textFont));

		tableInsert.addCell(new Phrase("P.V : ", textFont));
		tableInsert.addCell(new Phrase("", textFont));

		tableInsert.addCell(new Phrase("C.U : ", textFont));
		tableInsert.addCell(new Phrase("", textFont));

		tableInsert.addCell(new Phrase("Zone de circulation : ", textFont));
		tableInsert.addCell(new Phrase("Zone1", textFont));

		tableInsert.addCell(new Phrase("Propri�taire : ", textFont));
		tableInsert.addCell(new Phrase(contratReport.getPersonne()
				.getNomRaisonSociale(), textFont));

		/* Insertion du tableau dans la cellule */
		cell = new PdfPCell(tableInsert);
		cell.setColspan(2);
		table.addCell(cell);

		/* construction tableau interne 3 */
		tableInsert = new PdfPTable(2);

		tableInsert.getDefaultCell().setBorder(Cell.NO_BORDER);

		tableInsert.addCell(new Phrase("Hydrocarbure : ", textFont));
		tableInsert.addCell(new Phrase("Non", textFont));

		tableInsert.addCell(new Phrase("Passagers : ", textFont));
		tableInsert.addCell(new Phrase(""
				+ vehiculeAttributsBean.getVehicule()
						.getNbrePlaceCab()
				+ vehiculeAttributsBean.getVehicule()
						.getNbrePlaceCab(), textFont));

		tableInsert.addCell(new Phrase("Cylindre : ", textFont));
		tableInsert.addCell(new Phrase("0", textFont));

		tableInsert.addCell(new Phrase("P.T.H.C : ", textFont));
		tableInsert.addCell(new Phrase("", textFont));

		tableInsert.addCell(new Phrase("trp personnel : ", textFont));
		tableInsert.addCell(new Phrase("NON", textFont));

		tableInsert.addCell(new Phrase("Transport eleves : ", textFont));
		tableInsert.addCell(new Phrase("Non", textFont));

		tableInsert.addCell(new Phrase("Tracteur : ", textFont));
		tableInsert.addCell(new Phrase("", textFont));

		tableInsert.addCell(new Phrase("N�Attestation : ", textFont));
		tableInsert.addCell(new Phrase("", textFont));

		/* Insertion du tableau dans la cellule */
		cell = new PdfPCell(tableInsert);
		cell.setColspan(2);
		table.addCell(cell);
		/* Bas de tableau */
		tableInsert = new PdfPTable(4);
		tableInsert.getDefaultCell().setBorder(Cell.NO_BORDER);
		// premi�re ligne
		tableInsert.addCell(new Phrase("Cat�gorie du V�hicule : ", smallBold));
		tableInsert
				.addCell(new Phrase(vehiculeAttributsBean.getVehicule()
						.getSousCatVehicule().getLibelleSousCatVehicule(),
						textFont));
		tableInsert.addCell(new Phrase("Usage du v�hicule : ", smallBold));
		tableInsert.addCell(new Phrase("Promenade et affaires", textFont));
		// 2�me ligne
		tableInsert.addCell(new Phrase("Valeur � neuf : ", smallBold));
		tableInsert.addCell(new Phrase(""
				+ vehiculeAttributsBean.getVehicule().getValNeuf(),
				textFont));
		tableInsert.addCell(new Phrase("Valeur venale : ", smallBold));
		tableInsert.addCell(new Phrase(""
				+ vehiculeAttributsBean.getVehicule().getValVenale(),
				textFont));
		// 3�me ligne
		tableInsert.addCell(new Phrase("N� P.C: ", smallBold));
		/*tableInsert.addCell(new Phrase(""
				+ vehiculeAttributsBean.getVehicule().getNumSouscripteur()
						.getPermis(), textFont));*/
		tableInsert.addCell(new Phrase("Conducteur Habituel : ", smallBold));
		/*tableInsert.addCell(new Phrase(vehiculeAttributsBean.getVehicule()
				.getNumSouscripteur().getNomRaisonSocial(), textFont));*/
		// 4�me ligne
		tableInsert.addCell(new Phrase("Date P.C : ", smallBold));
		tableInsert.addCell(new Phrase("10/10/195", textFont));
		tableInsert.addCell(new Phrase("Statut socio prof : ", smallBold));
		tableInsert.addCell(new Phrase(""
				+ vehiculeAttributsBean.getVehicule().getStatut(), textFont));
		// Insertion du tableau dans la cellule
		cell = new PdfPCell(tableInsert);
		cell.setColspan(6);
		table.addCell(cell);
		table.setSpacingAfter(4);
		document.add(table);
		System.out.println("tableau 2 ajout�");
	}

	/**
	 * methode qui creer une ligne de garantie et ses primes associ�es
	 */
	public void creerLigne(PdfPTable table, PrimeByGarantie primeByGarantie) {
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", ""
						+ primeByGarantie.getGarantie().getLibelleGarantie(),
						textFont), 2));

		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", ""
						+ primeByGarantie.getGarantie().getCapitalGarantie(),
						textFont), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", ""
						+ primeByGarantie.getGarantie().getFranchise(),
						textFont), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", ""
						+ primeByGarantie.getChoisie().getPrimeAnnuelle(),
						textFont), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", ""
						+ primeByGarantie.getChoisie().getPrimeNetteProrata(),
						textFont), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "BNS", textFont), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "AUTRE", textFont), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "FLOTTE", textFont), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "MLS", textFont), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "MONTANT", textFont), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "PRIME NETTE", textFont), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "PRIME COMPTANT", textFont), 1));

	}

	public void ajoutGaranties(Document document, ContratReport contratReport,
			VehiculeAttributsBean vehiculeAttributsBean)
			throws DocumentException {
		PdfPTable table = new PdfPTable(13);
		table.setWidthPercentage(100);
		/* Construction de l'ent�te */
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "GARANTIES ACCORDEES",
						smallBold), 2));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "SOMMES GARANTIES", smallBold),
				1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "FRANCHISE", smallBold), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "PRIME ANNUELLE", smallBold), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "PRIME PRORATA", smallBold), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "BNS", smallBold), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "AUTRE", smallBold), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "FLOTTE", smallBold), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "MLS", smallBold), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "MONTANT", smallBold), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "PRIME NETTE", smallBold), 1));
		table.addCell(createCell(
				(Phrase) ajoutElement("Phrase", "PRIME COMPTANT", smallBold), 1));

		/***************** Construction des lignes ************************/
		List<PrimeByGarantie> listPrimeGaranties = vehiculeAttributsBean
				.getListPrimeGaranties();
		for (PrimeByGarantie primeGarantie : listPrimeGaranties) {
			this.creerLigne(table, primeGarantie);
		}

		PdfPCell cell = new PdfPCell();
		cell.setBorder(Cell.NO_BORDER);
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);

		cell = createCell(
				(Phrase) ajoutElement("Phrase", "TOTAL VEHICULE", smallBold),
				3, BaseColor.LIGHT_GRAY);
		table.addCell(cell);

		cell = createCell(
				(Phrase) ajoutElement("Phrase", vehiculeAttributsBean
						.getVehicule().getNumImmat(), smallBold), 1,
				BaseColor.LIGHT_GRAY);
		table.addCell(cell);

		cell = createCell(
				(Phrase) ajoutElement("Phrase", ""
						+ vehiculeAttributsBean.getGarantieChoisie()
								.getPrimeAnnuelle(), smallBold), 1,
				BaseColor.LIGHT_GRAY);
		table.addCell(cell);

		cell = createCell((Phrase) ajoutElement("Phrase", "", smallBold), 1,
				BaseColor.LIGHT_GRAY);
		cell.setColspan(5);
		table.addCell(cell);

		cell = createCell(
				(Phrase) ajoutElement("Phrase", ""
						+ vehiculeAttributsBean.getGarantieChoisie()
								.getMontantReduction(), smallBold), 1,
				BaseColor.LIGHT_GRAY);
		table.addCell(cell);

		cell = createCell(
				(Phrase) ajoutElement("Phrase", ""
						+ vehiculeAttributsBean.getGarantieChoisie()
								.getPrimeNetteAnnuelle(), smallBold), 1,
				BaseColor.LIGHT_GRAY);
		table.addCell(cell);

		cell = createCell(
				(Phrase) ajoutElement("Phrase", ""
						+ vehiculeAttributsBean.getGarantieChoisie()
								.getPrimeNetteProrata(), smallBold), 1,
				BaseColor.LIGHT_GRAY);
		table.addCell(cell);

		table.setSpacingAfter(4);

		document.add(table);
		System.out.println("tableau 3 ajout�");
	}

	public void calculPrimeGlobale(Document document, Quittance quittance)
			throws DocumentException {
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		table.getDefaultCell().setBorder(Cell.NO_BORDER);

		PdfPCell cell = new PdfPCell();
		cell.setBorder(Cell.NO_BORDER);

		/* construction tableau interne 1 */
		PdfPTable tableInsert = new PdfPTable(2);

		tableInsert.getDefaultCell().setBorder(Cell.NO_BORDER);

		tableInsert.addCell(new Phrase("Prime nette : ", smallBold));
		tableInsert.addCell(new Phrase("" + quittance.getPrimeNette(),
				smallBold));
		tableInsert.addCell(new Phrase("Accessoire : ", smallBold));
		tableInsert.addCell(new Phrase("" + quittance.getAccessoire(),
				smallBold));
		tableInsert.addCell(new Phrase("Taxe d'enregistrement : ", smallBold));
		tableInsert.addCell(new Phrase("" + quittance.getTaxes(), smallBold));
		tableInsert.addCell(new Phrase("FGA : ", smallBold));
		tableInsert.addCell(new Phrase("" + quittance.getFga(), smallBold));
		tableInsert.addCell(new Phrase("Prime TTC : ", smallBold));
		tableInsert
				.addCell(new Phrase("" + quittance.getNetAPayer(), smallBold));

		
		table.addCell(cell);
		table.addCell(cell);
		
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.addElement(tableInsert);
		table.addCell(cell);
		
		cell=new PdfPCell();
		cell.setBorder(Cell.NO_BORDER);
		table.addCell(cell);
		table.addCell(cell);
		
		tableInsert=new PdfPTable(2);
		tableInsert.getDefaultCell().setBorder(Cell.NO_BORDER);
		tableInsert.addCell(new Phrase("TOTAL NET A PAYER : ", smallBold));
		tableInsert
				.addCell(new Phrase("" + quittance.getNetAPayer(), smallBold));
		cell.addElement(tableInsert);
		table.addCell(cell);
		

		document.add(table);
		System.out.println("tableau 4 ajout�");
	}

	private static void ajoutTitre(Document document) throws DocumentException {

		Paragraph titre = new Paragraph();

		Paragraph paragraph = new Paragraph(
				"CONDITIONS PARTICULIERES DU CONTRAT D'ASSURANCE AUTOMOBILE",
				TITRE3);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		titre.add(paragraph);
		ajoutLigneVide(titre, 2);
		document.add(titre);
	}

	/* Aide � la cr�ation d'une cellule */
	private static PdfPCell createCell(Phrase phrase, int colspan) {
		PdfPCell cell = new PdfPCell(phrase);
		cell.setColspan(colspan);
		cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
		cell.setVerticalAlignment(Cell.ALIGN_CENTER);
		return cell;
	}

	private static PdfPCell createCell(Phrase phrase, int colspan,
			BaseColor color) {
		PdfPCell cell = new PdfPCell(phrase);
		cell.setBorder(Cell.NO_BORDER);
		cell.setBackgroundColor(color);
		cell.setColspan(colspan);
		cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
		cell.setVerticalAlignment(Cell.ALIGN_CENTER);
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

	public String getIdQuittance() {
		return idQuittance;
	}

	public void setIdQuittance(String idQuittance) {
		this.idQuittance = idQuittance;
	}

	public ContratReportFactory getContratReportFactory() {
		return contratReportFactory;
	}

	public void setContratReportFactory(
			ContratReportFactory contratReportFactory) {
		this.contratReportFactory = contratReportFactory;
	}

}
