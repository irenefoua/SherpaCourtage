package com.j3a.assurance.reporting.design;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Cell;

@Component
@Scope
public class Conditiontest implements Serializable {
	 
	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger(Conditiontest.class);
	 private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 28,
		      Font.BOLD);
		  private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 9,
		      Font.NORMAL, BaseColor.GREEN);
		  private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 11,
		      Font.BOLD | Font.UNDERLINE);
		 
		  private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 11,
			      Font.BOLD | Font.ITALIC);
		  
		  private static Font normalText = new Font(Font.FontFamily.TIMES_ROMAN,10,
		      Font.BOLD);
		  private static Font normalTitle = new Font(Font.FontFamily.TIMES_ROMAN, 9,
			      Font.BOLD);
		  
		  private static Font smallText = new Font(Font.FontFamily.COURIER,9,
				  Font.NORMAL);
		  
		  private static Font smallTextGras = new Font(Font.FontFamily.COURIER,8,
				  Font.BOLD);
	private static final long serialVersionUID = 1L;
	private File repectoire;
	
	
	 public void creerEtat() throws IOException{
		
		//  repectoire = new File("D:/Rapport/Quittances");
		 // repectoire.mkdirs();
		 String chemin ="D:/monDoc.pdf";
		
		
	  try {
		Document document = new Document(PageSize.A4);
		document.setMargins(20, 20, 20, 20);

		PdfWriter.getInstance(document, new FileOutputStream(chemin));

		  document.open();
		  addMetaData(document);
		  
		  Paragraph paragraph = new Paragraph();
		  ajoutLigneVide(paragraph, 2);
		  document.add(paragraph);
		  addcontenu(document);
		  
		  document.add(paragraph);
		  creatTable(document);
		  ajout1(document);
		  ajout2(document);
		  ajout3(document);
		  emagement(document);
		  document.close();
		  System.out.println("rapport généré");
			logger.info("le rapport est  générée");
		  
	//	  ouvrirFicher();
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


	private void addcontenu(Document document) throws DocumentException {
		Paragraph para= new Paragraph();
		para.add(new Paragraph("Conditions Particulieres"));
		document.add(para);
		// TODO Auto-generated method stub
		
	}


	private void addMetaData(Document document) {
		 document.addTitle(" Faculté");
		    document.addSubject("Edition Pièces");
		    document.addKeywords("Java, PDF, iText");
		    document.addAuthor("Eddy");
		    document.addCreator("Eddy");
		
	}
	private void contenuTable(Document document){
		
		
	}
	
	  
	 private  void creatTable(Document document) throws DocumentException{
			//Info 
			  PdfPTable table = new PdfPTable(4);
			  table.setWidths(new int[]{15,20,15,20});

			  table.getDefaultCell().setBorder(Cell.NO_BORDER);
			  PdfPCell cell;

			  //1er ligne
			  table.addCell(new Phrase("Numero",normalText));
			  cell = new PdfPCell(new Phrase("0012",smallText));
			  cell.setBorder(Rectangle.NO_BORDER);
			  table.addCell(cell);
			  
			  table.addCell(new Phrase("Titre",normalText));
			  cell = new PdfPCell(new Phrase(" hotel",smallText));
			  cell.setBorder(Rectangle.NO_BORDER);
			  table.addCell(cell);
			  
			  table.addCell(new Phrase("Nom:", normalText));
			  cell = new PdfPCell(new Phrase("Eddy",smallText));
			  cell.setBorder(Rectangle.NO_BORDER);
			  cell.setColspan(3);
			  
			  table.addCell(cell);

			  //2em ligne
			  table.addCell(new Phrase("Adresse:", normalText));
			  cell = new PdfPCell(new Phrase(" abj ",smallText));
			  cell.setBorder(Rectangle.NO_BORDER);
			  table.addCell(cell);
			  table.addCell(new Phrase("Tel:",normalText));
			  table.addCell(new Phrase("06141460",smallText));
			  
			  //3em ligne
			  table.addCell(new Phrase("Mail:", normalText));
			   cell = new PdfPCell(new Phrase(" roselyneddy",smallText));
			  cell.setBorder(Rectangle.NO_BORDER);
			  table.addCell(cell);
			  table.addCell(new Phrase("Fax:",normalText));
			  table.addCell(new Phrase(" ",smallText));
			//4em ligne
			  table.addCell(new Phrase("Intermédiaire:", normalText));
			   cell = new PdfPCell(new Phrase(" intermediaire",smallText));
			  cell.setBorder(Rectangle.NO_BORDER);
			  table.addCell(cell);
			  table.addCell(new Phrase("Réseaux:",normalText));
			  table.addCell(new Phrase(" reso",smallText));
			  
			  //Tableau général information
			  PdfPTable tablo = new PdfPTable(3);
			  tablo.setWidthPercentage(100);
			  tablo.setWidths(new int[]{48,1,47});
			  
			//Entête du tableau
			  PdfPCell cellTitre = new PdfPCell(new Phrase("SOUSCRIPTEUR",normalTitle));
			  cellTitre.setHorizontalAlignment(Element.ALIGN_CENTER);
			  tablo.addCell(cellTitre);
			  tablo.addCell("");
			  cellTitre = new PdfPCell(new Phrase("REFFERENCES",normalTitle));
			  cellTitre.setHorizontalAlignment(Element.ALIGN_CENTER);
			  tablo.addCell(cellTitre);
			  
			  
			//Info sur la quittance
			  PdfPTable tabQuit = new PdfPTable(4);
			  tabQuit.setWidths(new int[]{21,29,21,29});
			  tabQuit.getDefaultCell().setBorder(Cell.NO_BORDER);
			  PdfPCell cell1;

			//1er ligne
			  
			  
			  
			  tabQuit.addCell(new Phrase("Quittance:", normalText));
			  cell1 = new PdfPCell(new Phrase("ma quitance",smallText));
			  cell1.setBorder(Rectangle.NO_BORDER);
			  tabQuit.addCell(cell1);
			  tabQuit.addCell(new Phrase("Police:",normalText));
			  tabQuit.addCell(new Phrase("police",smallText));
			  
			  tabQuit.addCell(new Phrase("Emission:", normalText));
			  cell1 = new PdfPCell(new Phrase("25",smallText));
			  cell1.setBorder(Rectangle.NO_BORDER);
			  tabQuit.addCell(cell1);
			  tabQuit.addCell(new Phrase("Assurée:",normalText));
			  tabQuit.addCell(new Phrase("rosy",smallText));
			  
			//ligne 2
			  tabQuit.addCell(new Phrase("Avenant:", normalText));
			   cell1 = new PdfPCell(new Phrase("avenant1",smallText));
			  cell1.setBorder(Rectangle.NO_BORDER);
			 // cell1.setColspan(4);
			 // cell1.setRowspan(4);
			  tabQuit.addCell(cell1);
			  tabQuit.addCell(new Phrase("Mouvement:",normalText));
			  tabQuit.addCell(new Phrase("mouv",smallText));
			  
			  //3e Ligne
			  tabQuit.addCell(new Phrase("Effet:", normalText));
			   cell1 = new PdfPCell(new Phrase("effetttttttt",smallText));
			  cell1.setBorder(Rectangle.NO_BORDER);
			  tabQuit.addCell(cell1);
			  tabQuit.addCell(new Phrase("Expiration:",normalText));
			  tabQuit.addCell(new Phrase("le ",smallText));
			  
			  tablo.addCell(table);
			  tablo.addCell("////");
			  tablo.addCell(tabQuit);
			//  tablo.addCell("Tab quittance");

			

			  document.add(tablo);
			 
		  }
	 
	 
	 

		private void ajout1(Document document) throws DocumentException {
			PdfPTable conteneur=new PdfPTable(1);
			 conteneur.getDefaultCell().setBorder(Cell.NO_BORDER);
			PdfPCell cell;
			cell=new PdfPCell(new Phrase(""));
			document.add(new Phrase("Aux conditions générales qui précèdent, complétées par les conditions particulières ci-après et moyennant le  paiement des primes fixées, les assureurs soussignés co-assurent l’unité dont les caractéristiques sont les suivantes : ",smallText));
	 
		//article1
			 
			
			Paragraph para = new Paragraph ();
			ajoutLigneVide(para,1);
			para.add(new Phrase("ARTICLE I - DESCRIPTION DU NAVIRE" ,subFont));
			 
			  para.setAlignment(Element.ALIGN_LEFT);
			  ajoutLigneVide(para, 1);
			// document.add(para);
			
			  //
			  PdfPTable table = new PdfPTable(2);
			  table.getDefaultCell().setBorder(Cell.NO_BORDER);
			  PdfPCell cell2;
             //1er ligne
			  table.addCell(new Phrase("ARMATEUR :",normalText));
			  cell = new PdfPCell(new Phrase("mmm",smallText));
			  cell.setBorder(Rectangle.NO_BORDER);
			  table.addCell(cell);
			  
			  table.addCell(new Phrase("NOM DU NAVIRE :",normalText));
			  cell = new PdfPCell(new Phrase("poblo",smallText));
			  cell.setBorder(Rectangle.NO_BORDER);
			  table.addCell(cell);
			  
			  table.addCell(new Phrase("LONGUEUR :", normalText));
			  cell = new PdfPCell(new Phrase("80",smallText));
			  cell.setBorder(Rectangle.NO_BORDER);
			 // cell.setColspan(3);
			  
			  table.addCell(cell);

			  //2em ligne
			  table.addCell(new Phrase("LARGEUR:", normalText));
			  cell = new PdfPCell(new Phrase("20",smallText));
			  cell.setBorder(Rectangle.NO_BORDER);
			  table.addCell(cell);
			  table.addCell(new Phrase("PAVILLON:",normalText));
			  table.addCell(new Phrase("06141460",smallText));
			  
			  //3em ligne
			  table.addCell(new Phrase("COQUE:", normalText));
			   cell = new PdfPCell(new Phrase("rosel",smallText));
			  cell.setBorder(Rectangle.NO_BORDER);
			  table.addCell(cell);
			  table.addCell(new Phrase("TYPE :",normalText));
			  table.addCell(new Phrase(" ",smallText));
			//4em ligne  
			  table.addCell(new Phrase("IMMATRICULATION:", normalText));
			  cell = new PdfPCell(new Phrase(" 20",smallText));
			  cell.setBorder(Rectangle.NO_BORDER);
			  table.addCell(cell);
			  
			  table.addCell(new Phrase("JAUGE BRUTE  :",normalText));
			  cell = new PdfPCell(new Phrase("mmm",smallText));
			  cell.setBorder(Rectangle.NO_BORDER);
			  table.addCell(cell);
			  
			  table.addCell(new Phrase("JAUGE NETTE :",normalText));
			  cell = new PdfPCell(new Phrase("poblo",smallText));
			  cell.setBorder(Rectangle.NO_BORDER);
			  table.addCell(cell);
			  
			  table.addCell(new Phrase("DATE DE 1 ère MISE EN CIRCULATION :", normalText));
			  cell = new PdfPCell(new Phrase("80",smallText));
			  cell.setBorder(Rectangle.NO_BORDER);
			  table.addCell(cell);
              //
			  table.addCell(new Phrase("ZONE DE NAVIGATION:", normalText));
			  cell = new PdfPCell(new Phrase("20",smallText));
			  cell.setBorder(Rectangle.NO_BORDER);
			  table.addCell(cell);
			  table.addCell(new Phrase("PUISSANCE ET MARQUE DU MOTEUR:",normalText));
			  table.addCell(new Phrase("06141460",smallText));
			  //
			  table.addCell(new Phrase("NOMBRE MAXI DE PERSONNE A BORD:", normalText));
			   cell = new PdfPCell(new Phrase("rosel",smallText));
			  cell.setBorder(Rectangle.NO_BORDER);
			  table.addCell(cell);
			  table.addCell(new Phrase("MEMBRE D’EQUIPAGE  :",normalText));
			  table.addCell(new Phrase("rrr ",smallText));  
			 
			  para.add(table);
			  ajoutLigneVide(para, 1);
			//  document.add(para);
			  
			//article2
			  para.add(new Phrase("ARTICLE II - USAGE ET LIMITE DE NAVIGATION" ,subFont));
			  para.setAlignment(Element.ALIGN_LEFT);
			  ajoutLigneVide(para, 1);
		     
			 // cell3=new PdfPCell(new Phrase(""));
			 para.add(new Phrase("Par dérogation aux conditions générales, la présente assurance s’applique à l’unité ci- dessus, armée et exploitée par l’armement BENTLEY représenté par SIGAP pour la pêche sur la côte ouest d’ Afrique, de NOUADHIBOU à POINTE NOIRE. ",smallText));
			 para.add(new Phrase("Toute navigation en dehors de ces limites devra être déclarée préalablement aux assureurs et pourra faire l’objet d’une surprime." ,smallText)) ;
			 
	
			 //document.add(para); 
			 ajoutLigneVide(para,1);
			 //article3
			 para.add(new Phrase("ARTICLE III - DUREE DES RISQUES" ,subFont));
			  para.setAlignment(Element.ALIGN_LEFT);
			  ajoutLigneVide(para,1);
			  para.add(new Phrase("La garantie des assureurs est accordée pour une durée ferme de UN AN sans tacite reconduction du 01 janvier 2005 à zéro heure  au 31 Décembre 2005 à minuit. ",smallText));
		      ajoutLigneVide(para,1);
			  document.add(para);
}
	 
//article 4
		private void ajout2(Document document) throws DocumentException {

			  Paragraph para = new Paragraph ();
			  para.add(new Phrase("ARTICLE IV - CONDITIONS D’ASSURANCE" ,subFont)) ;
			  para.setAlignment(Element.ALIGN_LEFT);
			 
			  
			  ajoutLigneVide(para, 1);
			  para.add(new Phrase("A-Garantie corps moteurs et matériel de pêche", smallBold));
			  ajoutLigneVide(para, 1);
			  para.add(new Paragraph("La valeur corps moteur et matériel de pêche est fixée à F CFA   7 000 000 répartis comme suit : ", smallText));
			  ajoutLigneVide(para, 1); 
            //liste liste = new List (true, 20); 
                /*List list = new List(false, 10);
                list.add(new ListItem("Valeur corps                 5 000 000",smallText));
                list.add(new ListItem("Valeur moteur                1 000 000 ",smallText));
                list.add(new ListItem("Valeur matériel de pêche     1 000 000",smallText));
                para.add(list);*/
			  List list = new List(false,30);
			  list.setListSymbol(".");
		     // list.setListSymbol(new Chunk("B", FontFactory.getFont(FontFactory.ZAPFDINGBATS, 20)));
			 
              list.add(new ListItem("Valeur corps                 5 000 000",smallText));
              list.add(new ListItem("Valeur moteur                1 000 000 ",smallText));
              list.add(new ListItem("Valeur matériel de pêche     1 000 000",smallText));
              para.add(list);
			  
                
                ajoutLigneVide(para, 1);
                para.add(new Paragraph("Par dérogation à l’article 2-1 des conditions générales, la présente assurance est conclue Franc d’Avaries Particulières Absolument, les assureurs n’interviennent que pour les cas suivants :", smallText));
                List list2 = new List(false, 10);
  
                list2.add(new ListItem("avaries communes ",smallText));
                list2.add(new ListItem("recours des tiers ",smallText));
                list2.add(new ListItem("frais d’assistance et de sauvetage",smallText));
                list2.add(new ListItem("perte totale ou délaissement du navire assuré.",smallText));
                para.add(list2);
                
                para.add(new Paragraph("Les indemnités dues par les assureurs sur un navire et pour un même événement seront réglées sous déduction des franchises fixées par les présentes conditions particulières.", smallText));
                ajoutLigneVide(para, 1);
                     para.setSpacingBefore (50);
                para.add(new Paragraph("Les filets de pêche sont assurés uniquement en perte totale à la suite de la perte totale du navire.",normalText));
                document.add(para);
		//B
		
                Paragraph para2 = new Paragraph ();
                ajoutLigneVide(para2,1);
    			para2.add(new Phrase("B - Garantie RC propriétaire de navire" ,smallBold));
    			  para2.setAlignment(Element.ALIGN_LEFT);
    			  ajoutLigneVide(para2,1);
    			 para2.add(new Phrase("1°) Garantie",smallBold));
    			 ajoutLigneVide(para2,1);
    	            para2.add(new Phrase("la garantie est accordée aux conditions de la police française d’assurance maritime couvrant la responsabilité du propriétaire de navire de pêche, Imprimé du 1 er Juin 1988.",smallText));
    	            ajoutLigneVide(para2,1);
    	            para2.add(new Phrase("2°) Franchise sur garantie RC",smallBold));
    	            ajoutLigneVide(para2,1);
    	            para2.add(new Phrase("le règlement des sinistres sera effectué sous déduction d’une franchise de 10% des dommages avec un minimum de 2 000 000 F CFA par événement.",smallText));
    	            para2.add(new Phrase("Le règlement sera effectué sans franchise dans les cas de perte totale ou de délaissement du navire assuré", smallText));
    	            ajoutLigneVide(para2,1);
    	             document.add(para2);
                   //tablo a inserer
    //V
   
    Paragraph para1 = new Paragraph ();
    ajoutLigneVide(para1, 1);
	para1.add(new Phrase(" ARTICLE V - PECHE EN BOEUF " ,subFont));
	para1.setAlignment(Element.ALIGN_LEFT);
    
	ajoutLigneVide(para1, 1);
	para1.add(new Phrase("Il est précisé que le navire assuré peut, sous réserve de déclaration préalable aux assureurs, se livrer à la pêche dite « en bœuf ».",smallText));
	para1.add(new Paragraph("Lorsque cette pêche sera pratiquée, il est convenu que les assureurs ne rembourseront, dans les conditions de la police que les avaries subies par le navire assuré en cause , tous les recours de l’autre navire étant formellement exclus. ",smallText) );
	document.add(para1);
		}
		
		public void ajout3(Document document) throws DocumentException{
			
			Paragraph para = new Paragraph ();
			 ajoutLigneVide(para,1);
			para.add(new Phrase("ARTICLE VI - CONDITIONS DE REGLEMENT DES SINISTRES" ,subFont));
			para.setAlignment(Element.ALIGN_RIGHT);
			ajoutLigneVide(para, 1);
			para.add(new Phrase("A - PERTE TOTALE ET DELAISSEMENT", smallBold));
			ajoutLigneVide(para, 1);
			para.add(new Phrase("Le règlement sera effectué sans franchise dans les cas de perte totale ou de délaissement du navire assuré.",smallText));
			ajoutLigneVide(para, 1);
			para.add(new Phrase("B - ASSISTANCE", smallBold));
			ajoutLigneVide(para, 1);
			para.add(new Paragraph("Les frais d’assistance et de sauvetage seront indemnisés en application stricte de la Convention d’ Assistance et de Sauvetage dite CONCARNEAU ( imprimé du 01/01/1982 modifié)." + 
"Il est rappelé que cette convention s’applique exclusivement en matière d’assistance et de sauvetage. Il appartiendra en conséquence à l’assisté et/ou à l’armateur du navire assistant, d’apporter tout justificatifs quant à l’intervention du navire assistant."
,smallText));
		   para.add(new Paragraph("A cet effet, le navire assisté devra faire l’objet à la diligence de l’assuré, d’une expertise par un commissaire d’avaries dès son retour au port afin que celui-ci constate les avaries qui auraient nécessité l’assistance sauvetage."+
"L’expert devra par ailleurs se prononcer sur la cause de l’avarie et sur le bien fondé de l’assistance/sauvetage, eu égard à la gravité de ladite avarie."
,smallText));
		   para.add(new Paragraph("Dans l’hypothèse où l’expert constaterait :",smallText));
		   ajoutLigneVide(para, 1);
		   List list =new List();
list.setListSymbol("*");

           list.add(new ListItem("Soit que le navire n’a subi aucune avarie ayant nécessité l’assistance/sauvetage, ",smallText));
           list.add(new ListItem("Soit que les avaries constatées par l’expert eu égard à leur gravité, ne justifiaient pas d’intervention d’assistance sauvetage, les assureurs ne devront aucune indemnité au titre de l’assistance/ sauvetage.",smallText));
           para.add(list);
           para.add(new Paragraph("D’autre part, il est rappelé qu’en aucun cas, les frais de remorquage du navire assuré dans un port autre que le port le plus proche, ne sont à la charge des assureurs.",smallText));
           ajoutLigneVide(para,1);
           para.add(new Phrase("C - RECOURS DE TIERS", smallBold));
           ajoutLigneVide(para,1);
		   para.add(new Paragraph("Les recours de tiers, tant corporels que matériels sont remboursés aux 9/10 ème sans que le dixième du préjudice laissé à la charge de l’assuré ne puisse excéder F CFA 2 000 000.",smallText));
		   ajoutLigneVide(para, 1);
		   para.add(new Paragraph("Il est précisé que sont formellement exclus de la garantie recours des tiers, les filets et/ou casiers auxquels le navire assuré aurait pu causer un dommage ou la perte.",smallText));
		   ajoutLigneVide(para,1);
		   
			para.add(new Paragraph("ARTICLE VII - CHOMAGE" ,subFont));
			para.setAlignment(Element.ALIGN_LEFT);
			ajoutLigneVide(para, 1);
			para.add(new Phrase("Par dérogation à toutes dispositions contraires, la présente assurance est souscrite à forfait sans ristourne pour chômage. ", smallText));
			ajoutLigneVide(para, 1);
			para.add(new Paragraph("ARTICLE VIII - DECOMPTE DE PRIME" ,subFont));
			para.setAlignment(Element.ALIGN_LEFT);
			ajoutLigneVide(para, 1);
			//a revoir La prime totale annuelle est fixée à F CFA        31 405 décomptée comme suit :
			

			para.add(new Phrase(" ", smallText));
			ajoutLigneVide(para, 1);
			para.add(new Paragraph("ARTICLE IX - PAIEMENT DE LA PRIME", subFont));
			para.add(new Phrase(" La prime est payable selon le calendrier ci-après :",smallText));
			ajoutLigneVide(para, 1);
			para.add(new Phrase(" Il est convenu qu’à défaut de paiement d’une prime à l’échéance indiquée, les assureurs ont la faculté soit de suspendre l’assurance, soit d’en demander la résiliation. la suspension ou la résiliation ne prend effet que huit jours après l’envoi à l’assuré d’une mise en demeure à son dernier domicile connu des assureurs et par lettre recommandée, d’une mise en demeure d’avoir à payer. La suspension ou la résiliation produira ses effets automatiquement à l’expiration de ce délai et jusqu’au lendemain à zéro heure de la date de paiement de la prime de retard. :",smallText));
			para.add(new Paragraph("Pour tout sinistre survenu pendant une suspension des risques, les assureurs n’auront aucune indemnité à payer, tous leurs droits contre l’assuré en exécution du contrat et en particulier, leur doit de recouvrement de la prime entière stipulée, demeurant néanmoins expressément réservé. Il est en outre convenu qu’en cas de sinistre dont le montant serait supérieur à la prime émise au titre du présent contrat, le paiement de la totalité des échéances serait immédiatement exigible par la compagnie apéritrice. ",smallText));
			ajoutLigneVide(para,1);
			
			para.add(new Paragraph("ARTICLE X - DOCUMENTS JOINTS", subFont));
			para.add(new Phrase(" L’assuré reconnaît avoir reçu un exemplaire  de chacun des documents suivants  :",smallText));
			ajoutLigneVide(para, 1);
            List list2 = new List(List.UNORDERED);
		  
	           list2.add(new ListItem("POLICE FRANCAISE D’ASSURANCE SUR COPRS DE NAVIRE DE PECHE ARTISANALE Imprimé du 1 er Octobre 2001 ",smallText));
	           list2.add(new ListItem("CONVENTION DE CONCARNEAU Imprimé du 1 er Janvier 1882 modifié le 1 er Janvier 1988.",smallText));
	           para.add(list2);
			
	           ajoutLigneVide(para,1);
	           para.add(new Paragraph("ARTICLE XI - COASSURANCE", subFont));
	           ajoutLigneVide(para, 1);
	           para.add(new Phrase("Les engagements découlant de la garantie de la présente police sont répartis entre les compagnies figurant sur le tableau de répartition de coassurance ci-annexé, chacune agissant pour sa part respective et sans solidarité entre elles.",smallText));
			   para.add(new Phrase("Les présentes conditions particulières prévalent sur les conditions générales dans tous les cas où elles y dérogent.",smallText));
	           para.add(new Phrase("Sont nulles toutes adjonctions, suppressions ou modifications apportées au texte du présent contrat, qui ne seraient pas revêtues du VISA des Assureurs.",smallText));
	           ajoutLigneVide(para, 1);
	           
	           para.add(new Phrase("Fait en triple exemplaires à ABIDJAN le 25/02/2014 .",smallText));
	           document.add(para);
		   
		}
	  
	 
		 public  void emagement(Document document) throws DocumentException{
			  PdfPTable tab = new PdfPTable(3);
			  tab.setWidthPercentage(100);
			  PdfPCell cell;
			  
			  cell = new PdfPCell(new Phrase("LE SOUSCRIPTEUR",normalText));
			  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			  cell.setBorder(Rectangle.NO_BORDER);
			  tab.addCell(cell);
			  cell = new PdfPCell(new Phrase(""));
			  cell.setBorder(Rectangle.NO_BORDER);
			  tab.addCell(cell);
			  cell = new PdfPCell(new Phrase("POUR LA COMPAGNIE",normalText));
			  cell.setBorder(Rectangle.NO_BORDER);
			  tab.addCell(cell);
			  
			  tab.setSpacingBefore(15);
			  document.add(tab);
		  }
		
		
		//ajouter ligne vide
		private static void ajoutLigneVide(Paragraph paragraph, int number) {
			for (int i = 0; i < number; i++) {
				paragraph.add(new Paragraph(" "));}
			}
		
		/* aide a la création d'une cellule */
		private static PdfPCell createCell(Phrase phrase, int colspan) {
			PdfPCell cell = new PdfPCell(phrase);
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
	 

}
