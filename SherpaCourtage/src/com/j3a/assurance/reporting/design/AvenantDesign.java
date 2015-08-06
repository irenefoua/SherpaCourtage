package com.j3a.assurance.reporting.design;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
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
import com.j3a.assurance.model.PointVente;
import com.j3a.assurance.model.Risque;
import com.j3a.assurance.utilitaires.AvenantByPointVenteRow;
import com.j3a.assurance.utilitaires.AvenantPvRow;
import com.j3a.assurance.utilitaires.MouvementRow;
import com.j3a.assurance.utilitaires.PointVenteRow;
import com.j3a.assurance.utilitaires.RisqueRow;
import com.lowagie.text.Cell;

/**
 * 
 * @author J3A-POSTE1-A.Lekerand Bean permettant d'editer un Etat d'avenant
 * 
 */

@Component
@Scope("request")
public class AvenantDesign implements Serializable {

	private static final long serialVersionUID = 1L;

	public AvenantDesign() {
		// TODO Auto-generated constructor stub
	}

	// Attribut d'instance
	AvenantPvRow avenantPv = new AvenantPvRow();
	private File repectoire;
	private String nomFichier;

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
	private static Font normalText1Gras = new Font(Font.FontFamily.TIMES_ROMAN,
			12, Font.BOLD);

	private static Font normalTitle = new Font(Font.FontFamily.TIMES_ROMAN, 9,
			Font.BOLD);

	private static Font smalTitle = new Font(Font.FontFamily.TIMES_ROMAN, 6,
			Font.BOLD);

	private static Font smallText = new Font(Font.FontFamily.COURIER, 5,
			Font.NORMAL);
	private static Font smallText2 = new Font(Font.FontFamily.COURIER, 8,
			Font.NORMAL);

	private static Font smallTextGras = new Font(Font.FontFamily.COURIER, 8,
			Font.BOLD);

	private static Font TITRE3 = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.BOLD);

	public AvenantPvRow getAvenantPoinVenteRow(
			List<AvenantByPointVenteRow> ListAvenantByPv,
			List<PointVente> slctdPtVtes, List<Risque> slctdRisques,
			List<String> slctdMvmts) {

		AvenantPvRow A = new AvenantPvRow();
		// préchargement de A
		for (PointVente P : slctdPtVtes) {
			PointVenteRow Pv = new PointVenteRow();
			System.out.println("Point de Vente----- "
					+ P.getLibellePointVente());
			Pv.setPointVent(P);

			for (Risque R : slctdRisques) {
				RisqueRow Risq = new RisqueRow();
				Risq.setRisque(R);
				System.out.println("Risque----- " + R.getLibelleRisque());
				for (String M : slctdMvmts) {
					MouvementRow mouv = new MouvementRow();
					mouv.setMouvement(M);
					System.out.println("Mouvement---- " + M);
					// On set les Avenants par mouvement
					for (AvenantByPointVenteRow AvPv : ListAvenantByPv) {
						if (AvPv.getPtVte().getLibellePointVente()
								.equalsIgnoreCase(P.getLibellePointVente())
								&& AvPv.getContrat().getRisque()
										.getLibelleRisque()
										.equalsIgnoreCase(R.getLibelleRisque())
								&& AvPv.getAvenant().getMouvement()
										.equalsIgnoreCase(M)) {
							mouv.getListAvenantRow().add(AvPv);
							System.out.println("Ligne AvenantRow------- "
									+ AvPv.getAvenant().getContrat());
						}
					}
					Risq.getListMouvement().add(mouv);

				}
				Pv.getListRisque().add(Risq);
			}
			A.getListPointVente().add(Pv);
		}
		return A;
	}

	public void editerAvenantPointDeVente(AvenantPvRow avenantPv,
			Date dateDebut, Date dateFin, HttpServletRequest request,
			HttpServletResponse response) throws IOException, Exception {

		// Donnée de test
		// Créer le dosier de stockage des fichier générés
		repectoire = new File("c:/Etats/Rapport");
		repectoire.mkdirs();
		// Passer les info nécessaires à notre rapport à générer

		// step 1
		Document document = new Document(PageSize.A4);
		document.setMargins(20, 20, 20, 20);
		nomFichier = "Etat-EmissionPV" + "2015" + ".pdf";
		// step 2
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, baos);
		PdfWriter.getInstance(document, new FileOutputStream(repectoire + "/"
				+ nomFichier));

		// step 3
		document.open();
		// Ouverture d'une page

		// document.newPage();

		try {

			addContent(document, avenantPv, dateDebut, dateFin);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur1");
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erreur2");
		}
		document.close();

		// Ouverture du Pdf
		openFile();
	}

	private void addContent(Document document, AvenantPvRow avenantPv,
			Date dateDebut, Date dateFin) throws DocumentException,
			MalformedURLException, IOException {
		// Boucle sur les points de ventes et les risques
		for (PointVenteRow P : avenantPv.getListPointVente()) {

			for (RisqueRow R : P.getListRisque()) {
				document.newPage();
				// Ajout de logo
				Image logo = Image.getInstance(new URL(RESOURCE));
				logo.scalePercent(40f);
				document.add(logo);
				Paragraph saut = new Paragraph();
				addEmptyLine(saut, 1);
				document.add(sautLigne(1));
				// Entête du document
				creerTitreDocument(document, dateDebut, dateFin);
				for (MouvementRow M : R.getListMouvement()) {
					creatTableInfo(document, P.getPointVent(), R.getRisque(),
							M.getMouvement());
					creerTabeauAvenant(document, M.getListAvenantRow());
				}
			}
		}

		// Emagement
		creerEmagement(document);

	}

	public void creerTabeauAvenant(Document document,
			List<AvenantByPointVenteRow> listeAvenant)
			throws DocumentException, IOException {
		PdfPTable tableauAvenants = new PdfPTable(11);
		tableauAvenants.setWidthPercentage(100);
		tableauAvenants.setWidths(new int[] { 5, 10, 12, 9, 12, 12, 12, 6, 6,
				8, 8 });
		PdfPCell cell;
		PdfPCell cellLib;
		PdfPCell cellCont;

		// Titre du tableau
		cell = new PdfPCell(new Phrase("Emissions", normalTitle));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.setColspan(12);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableauAvenants.addCell(cell);

		// 1ere Ligne

		// Entête des colonnes
		cellLib = new PdfPCell(new Phrase("Exercice", smalTitle));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellLib.setRowspan(2);
		tableauAvenants.addCell(cellLib);

		cellLib = new PdfPCell(new Phrase("Point de Vente", smalTitle));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellLib.setRowspan(2);
		tableauAvenants.addCell(cellLib);

		cellLib = new PdfPCell(new Phrase("Risque", smalTitle));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellLib.setRowspan(2);
		tableauAvenants.addCell(cellLib);

		cellLib = new PdfPCell(new Phrase("Police", smalTitle));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellLib.setRowspan(2);
		tableauAvenants.addCell(cellLib);

		cellLib = new PdfPCell(new Phrase("Souscripteur", smalTitle));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellLib.setRowspan(2);
		tableauAvenants.addCell(cellLib);

		cellLib = new PdfPCell(new Phrase("Avenant", smalTitle));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellLib.setColspan(4);
		tableauAvenants.addCell(cellLib);

		cellLib = new PdfPCell(new Phrase("Quittance", smalTitle));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellLib.setColspan(2);
		tableauAvenants.addCell(cellLib);

		cellLib = new PdfPCell(new Phrase("Num Avenant", smalTitle));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableauAvenants.addCell(cellLib);

		cellLib = new PdfPCell(new Phrase("Mouvement", smalTitle));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableauAvenants.addCell(cellLib);

		cellLib = new PdfPCell(new Phrase("Effet", smalTitle));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableauAvenants.addCell(cellLib);

		cellLib = new PdfPCell(new Phrase("Echeance", smalTitle));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableauAvenants.addCell(cellLib);

		cellLib = new PdfPCell(new Phrase("Net A Payer", smalTitle));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableauAvenants.addCell(cellLib);

		cellLib = new PdfPCell(new Phrase("statut", smalTitle));
		cellLib.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellLib.setHorizontalAlignment(Element.ALIGN_CENTER);
		tableauAvenants.addCell(cellLib);

		// Ajout des Avenants pour chaque ligne du tableau
		// Fonction de la taille des avenants
		for (AvenantByPointVenteRow avenants : listeAvenant) { // Recuperer
																// l'AvenantPV

			// Contenu 1e Colonne
			if (avenants.getExercice() != null) {
				cellCont = new PdfPCell(new Phrase(avenants.getExercice()
						.getLibelleExercice(), smallText));// Garanties
			} else {
				cellCont = new PdfPCell(new Phrase("", smallText));// Garanties
			}

			cellCont.setHorizontalAlignment(Element.ALIGN_LEFT);
			tableauAvenants.addCell(cellCont);

			// 2e Colonne
			cellCont = new PdfPCell(new Phrase(""
					+ avenants.getPtVte().getLibellePointVente(), smallText));// Nature-Franchise
			cellCont.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tableauAvenants.addCell(cellCont);

			// 3e Colonne
			cellCont = new PdfPCell(new Phrase(""
					+ avenants.getContrat().getRisque().getLibelleRisque(),
					smallText));
			cellCont.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tableauAvenants.addCell(cellCont);

			// 4e Colonne
			cellCont = new PdfPCell(new Phrase(""
					+ avenants.getContrat().getNumPolice(), smallText));
			cellCont.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tableauAvenants.addCell(cellCont);

			// 5e Colonne le Client
			cellCont = new PdfPCell(new Phrase("" + avenants.getNomRsClient()
					+ " " + "" + avenants.getPrenomClient(), smallText));
			cellCont.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tableauAvenants.addCell(cellCont);

			// 6e Colonne
			cellCont = new PdfPCell(new Phrase(""
					+ avenants.getAvenant().getContrat(), smallText));
			cellCont.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tableauAvenants.addCell(cellCont);

			// 7e Colonne
			cellCont = new PdfPCell(new Phrase(""
					+ avenants.getAvenant().getMouvement(), smallText));
			cellCont.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tableauAvenants.addCell(cellCont);

			// 8e Colonne
			cellCont = new PdfPCell(new Phrase(""
					+ sdf.format(avenants.getAvenant().getEffet()), smallText));
			cellCont.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tableauAvenants.addCell(cellCont);

			// 9e Colonne
			cellCont = new PdfPCell(new Phrase(""
					+ sdf.format(avenants.getAvenant().getEcheance()),
					smallText));
			cellCont.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tableauAvenants.addCell(cellCont);

			// 10e Colonne
			if(avenants.getQuittance()!=null){
				cellCont = new PdfPCell(new Phrase(""
						+ avenants.getQuittance().getNetAPayer(), smallText));
				cellCont.setHorizontalAlignment(Element.ALIGN_RIGHT);
				tableauAvenants.addCell(cellCont);
				
				// 11e Colonne
				cellCont = new PdfPCell(new Phrase(""
						+ avenants.getQuittance().getEtatQuittance(), smallText));
				cellCont.setHorizontalAlignment(Element.ALIGN_RIGHT);
				tableauAvenants.addCell(cellCont);
				
			}else{
				cellCont = new PdfPCell(new Phrase("", smallText));
				cellCont.setHorizontalAlignment(Element.ALIGN_RIGHT);
				tableauAvenants.addCell(cellCont);
				
				// 11e Colonne
				cellCont = new PdfPCell(new Phrase("", smallText));
				cellCont.setHorizontalAlignment(Element.ALIGN_RIGHT);
				tableauAvenants.addCell(cellCont);
			}
			

			

		}

		tableauAvenants.setSpacingAfter(10);
		document.add(tableauAvenants);

	}

	// Titre du document
	public void creerTitreDocument(Document document, Date dateDebut,
			Date dateFin) throws DocumentException {

		Paragraph titreDocument = new Paragraph(new Chunk(
				"LISTE DES MOUVEMENTS DU " + sdf.format(dateDebut) + " AU "
						+ sdf.format(dateFin)
						+ " PAR RISQUE PAR POINT DE VENTE", TITRE3));
		titreDocument.setAlignment(Element.ALIGN_CENTER);
		titreDocument.setSpacingAfter(30);
		document.add(titreDocument);
	}

	private void creatTableInfo(Document document, PointVente pointVente,
			Risque risque, String mouvement) throws DocumentException {
		// Info sur le Souscripteur

		PdfPTable tabSous = new PdfPTable(6);
		tabSous.setWidths(new int[] { 20, 25, 10, 10, 15, 20 });

		tabSous.getDefaultCell().setBorder(Cell.NO_BORDER);
		PdfPCell cell;

		// 1er ligne
		// Entête du tableau
		PdfPCell cellTitre = new PdfPCell(new Phrase("ETAT", normalTitle));
		cellTitre.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellTitre.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellTitre.setColspan(6);

		// Ligne2
		tabSous.addCell(new Phrase("Point de vente:", normalText));
		cell = new PdfPCell(new Phrase(pointVente.getLibellePointVente(),
				smallText2));
		cell.setBorder(Rectangle.NO_BORDER);
		// cell.setColspan(3);
		tabSous.addCell(cell);

		tabSous.addCell(new Phrase("Risque:", normalText));
		cell = new PdfPCell(new Phrase(risque.getLibelleRisque(), smallText2));
		cell.setBorder(Rectangle.NO_BORDER);
		// cell.setColspan(3);
		tabSous.addCell(cell);

		tabSous.addCell(new Phrase("Mouvement:", normalText));
		cell = new PdfPCell(new Phrase(mouvement, smallText2));
		cell.setBorder(Rectangle.NO_BORDER);
		// cell.setColspan(3);
		tabSous.addCell(cell);

		// Tableau général information
		PdfPTable tabInfo = new PdfPTable(1);
		tabInfo.setWidthPercentage(100);

		tabInfo.addCell(cellTitre);
		tabInfo.addCell(tabSous);
		tabInfo.setSpacingAfter(15);
		document.add(tabInfo);
	}

	// Emargement
	public void creerEmagement(Document document) throws DocumentException {

		Paragraph dateJour = new Paragraph(new Chunk("Fait le "
				+ sdf.format(new Date())));
		dateJour.setIndentationLeft(200);

		PdfPTable tabEmerg = new PdfPTable(3);
		tabEmerg.setWidthPercentage(100);
		PdfPCell cell;

		Chunk chunkSous = new Chunk("La Compagnie");
		chunkSous.setUnderline(0.1f, -2f);
		cell = new PdfPCell(new Phrase(chunkSous));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);
		tabEmerg.addCell(cell);

		cell = new PdfPCell(new Phrase(""));
		cell.setBorder(Rectangle.NO_BORDER);
		tabEmerg.addCell(cell);

		Chunk chunkComp = new Chunk("J3ATECHNOLOGIE");
		chunkComp.setUnderline(0.1f, -2);
		cell = new PdfPCell(new Phrase(chunkComp));
		cell.setBorder(Rectangle.NO_BORDER);
		tabEmerg.addCell(cell);

		tabEmerg.setSpacingBefore(15);
		document.add(dateJour);
		document.add(tabEmerg);
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

	public AvenantPvRow getAvenantPv() {
		return avenantPv;
	}

	public void setAvenantPv(AvenantPvRow avenantPv) {
		this.avenantPv = avenantPv;
	}

}
