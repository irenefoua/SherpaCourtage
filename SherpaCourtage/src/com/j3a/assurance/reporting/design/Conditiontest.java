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
		  System.out.println("rapport g�n�r�");
			logger.info("le rapport est  g�n�r�e");
		  
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
		 document.addTitle(" Facult�");
		    document.addSubject("Edition Pi�ces");
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
			  table.addCell(new Phrase("Interm�diaire:", normalText));
			   cell = new PdfPCell(new Phrase(" intermediaire",smallText));
			  cell.setBorder(Rectangle.NO_BORDER);
			  table.addCell(cell);
			  table.addCell(new Phrase("R�seaux:",normalText));
			  table.addCell(new Phrase(" reso",smallText));
			  
			  //Tableau g�n�ral information
			  PdfPTable tablo = new PdfPTable(3);
			  tablo.setWidthPercentage(100);
			  tablo.setWidths(new int[]{48,1,47});
			  
			//Ent�te du tableau
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
			  tabQuit.addCell(new Phrase("Assur�e:",normalText));
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
			document.add(new Phrase("Aux conditions g�n�rales qui pr�c�dent, compl�t�es par les conditions particuli�res ci-apr�s et moyennant le  paiement des primes fix�es, les assureurs soussign�s co-assurent l�unit� dont les caract�ristiques sont les suivantes : ",smallText));
	 
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
			  
			  table.addCell(new Phrase("DATE DE 1 �re MISE EN CIRCULATION :", normalText));
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
			  table.addCell(new Phrase("MEMBRE D�EQUIPAGE  :",normalText));
			  table.addCell(new Phrase("rrr ",smallText));  
			 
			  para.add(table);
			  ajoutLigneVide(para, 1);
			//  document.add(para);
			  
			//article2
			  para.add(new Phrase("ARTICLE II - USAGE ET LIMITE DE NAVIGATION" ,subFont));
			  para.setAlignment(Element.ALIGN_LEFT);
			  ajoutLigneVide(para, 1);
		     
			 // cell3=new PdfPCell(new Phrase(""));
			 para.add(new Phrase("Par d�rogation aux conditions g�n�rales, la pr�sente assurance s�applique � l�unit� ci- dessus, arm�e et exploit�e par l�armement BENTLEY repr�sent� par SIGAP pour la p�che sur la c�te ouest d� Afrique, de NOUADHIBOU � POINTE NOIRE. ",smallText));
			 para.add(new Phrase("Toute navigation en dehors de ces limites devra �tre d�clar�e pr�alablement aux assureurs et pourra faire l�objet d�une surprime." ,smallText)) ;
			 
	
			 //document.add(para); 
			 ajoutLigneVide(para,1);
			 //article3
			 para.add(new Phrase("ARTICLE III - DUREE DES RISQUES" ,subFont));
			  para.setAlignment(Element.ALIGN_LEFT);
			  ajoutLigneVide(para,1);
			  para.add(new Phrase("La garantie des assureurs est accord�e pour une dur�e ferme de UN AN sans tacite reconduction du 01 janvier 2005 � z�ro heure  au 31 D�cembre 2005 � minuit. ",smallText));
		      ajoutLigneVide(para,1);
			  document.add(para);
}
	 
//article 4
		private void ajout2(Document document) throws DocumentException {

			  Paragraph para = new Paragraph ();
			  para.add(new Phrase("ARTICLE IV - CONDITIONS D�ASSURANCE" ,subFont)) ;
			  para.setAlignment(Element.ALIGN_LEFT);
			 
			  
			  ajoutLigneVide(para, 1);
			  para.add(new Phrase("A-Garantie corps moteurs et mat�riel de p�che", smallBold));
			  ajoutLigneVide(para, 1);
			  para.add(new Paragraph("La valeur corps moteur et mat�riel de p�che est fix�e � F CFA   7 000 000 r�partis comme suit : ", smallText));
			  ajoutLigneVide(para, 1); 
            //liste liste = new List (true, 20); 
                /*List list = new List(false, 10);
                list.add(new ListItem("Valeur corps                 5 000 000",smallText));
                list.add(new ListItem("Valeur moteur                1 000 000 ",smallText));
                list.add(new ListItem("Valeur mat�riel de p�che     1 000 000",smallText));
                para.add(list);*/
			  List list = new List(false,30);
			  list.setListSymbol(".");
		     // list.setListSymbol(new Chunk("B", FontFactory.getFont(FontFactory.ZAPFDINGBATS, 20)));
			 
              list.add(new ListItem("Valeur corps                 5 000 000",smallText));
              list.add(new ListItem("Valeur moteur                1 000 000 ",smallText));
              list.add(new ListItem("Valeur mat�riel de p�che     1 000 000",smallText));
              para.add(list);
			  
                
                ajoutLigneVide(para, 1);
                para.add(new Paragraph("Par d�rogation � l�article 2-1 des conditions g�n�rales, la pr�sente assurance est conclue Franc d�Avaries Particuli�res Absolument, les assureurs n�interviennent que pour les cas suivants :", smallText));
                List list2 = new List(false, 10);
  
                list2.add(new ListItem("avaries communes ",smallText));
                list2.add(new ListItem("recours des tiers ",smallText));
                list2.add(new ListItem("frais d�assistance et de sauvetage",smallText));
                list2.add(new ListItem("perte totale ou d�laissement du navire assur�.",smallText));
                para.add(list2);
                
                para.add(new Paragraph("Les indemnit�s dues par les assureurs sur un navire et pour un m�me �v�nement seront r�gl�es sous d�duction des franchises fix�es par les pr�sentes conditions particuli�res.", smallText));
                ajoutLigneVide(para, 1);
                     para.setSpacingBefore (50);
                para.add(new Paragraph("Les filets de p�che sont assur�s uniquement en perte totale � la suite de la perte totale du navire.",normalText));
                document.add(para);
		//B
		
                Paragraph para2 = new Paragraph ();
                ajoutLigneVide(para2,1);
    			para2.add(new Phrase("B - Garantie RC propri�taire de navire" ,smallBold));
    			  para2.setAlignment(Element.ALIGN_LEFT);
    			  ajoutLigneVide(para2,1);
    			 para2.add(new Phrase("1�) Garantie",smallBold));
    			 ajoutLigneVide(para2,1);
    	            para2.add(new Phrase("la garantie est accord�e aux conditions de la police fran�aise d�assurance maritime couvrant la responsabilit� du propri�taire de navire de p�che, Imprim� du 1 er Juin 1988.",smallText));
    	            ajoutLigneVide(para2,1);
    	            para2.add(new Phrase("2�) Franchise sur garantie RC",smallBold));
    	            ajoutLigneVide(para2,1);
    	            para2.add(new Phrase("le r�glement des sinistres sera effectu� sous d�duction d�une franchise de 10% des dommages avec un minimum de 2 000 000 F CFA par �v�nement.",smallText));
    	            para2.add(new Phrase("Le r�glement sera effectu� sans franchise dans les cas de perte totale ou de d�laissement du navire assur�", smallText));
    	            ajoutLigneVide(para2,1);
    	             document.add(para2);
                   //tablo a inserer
    //V
   
    Paragraph para1 = new Paragraph ();
    ajoutLigneVide(para1, 1);
	para1.add(new Phrase(" ARTICLE V - PECHE EN BOEUF " ,subFont));
	para1.setAlignment(Element.ALIGN_LEFT);
    
	ajoutLigneVide(para1, 1);
	para1.add(new Phrase("Il est pr�cis� que le navire assur� peut, sous r�serve de d�claration pr�alable aux assureurs, se livrer � la p�che dite � en b�uf �.",smallText));
	para1.add(new Paragraph("Lorsque cette p�che sera pratiqu�e, il est convenu que les assureurs ne rembourseront, dans les conditions de la police que les avaries subies par le navire assur� en cause , tous les recours de l�autre navire �tant formellement exclus. ",smallText) );
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
			para.add(new Phrase("Le r�glement sera effectu� sans franchise dans les cas de perte totale ou de d�laissement du navire assur�.",smallText));
			ajoutLigneVide(para, 1);
			para.add(new Phrase("B - ASSISTANCE", smallBold));
			ajoutLigneVide(para, 1);
			para.add(new Paragraph("Les frais d�assistance et de sauvetage seront indemnis�s en application stricte de la Convention d� Assistance et de Sauvetage dite CONCARNEAU ( imprim� du 01/01/1982 modifi�)." + 
"Il est rappel� que cette convention s�applique exclusivement en mati�re d�assistance et de sauvetage. Il appartiendra en cons�quence � l�assist� et/ou � l�armateur du navire assistant, d�apporter tout justificatifs quant � l�intervention du navire assistant."
,smallText));
		   para.add(new Paragraph("A cet effet, le navire assist� devra faire l�objet � la diligence de l�assur�, d�une expertise par un commissaire d�avaries d�s son retour au port afin que celui-ci constate les avaries qui auraient n�cessit� l�assistance sauvetage."+
"L�expert devra par ailleurs se prononcer sur la cause de l�avarie et sur le bien fond� de l�assistance/sauvetage, eu �gard � la gravit� de ladite avarie."
,smallText));
		   para.add(new Paragraph("Dans l�hypoth�se o� l�expert constaterait :",smallText));
		   ajoutLigneVide(para, 1);
		   List list =new List();
list.setListSymbol("*");

           list.add(new ListItem("Soit que le navire n�a subi aucune avarie ayant n�cessit� l�assistance/sauvetage, ",smallText));
           list.add(new ListItem("Soit que les avaries constat�es par l�expert eu �gard � leur gravit�, ne justifiaient pas d�intervention d�assistance sauvetage, les assureurs ne devront aucune indemnit� au titre de l�assistance/ sauvetage.",smallText));
           para.add(list);
           para.add(new Paragraph("D�autre part, il est rappel� qu�en aucun cas, les frais de remorquage du navire assur� dans un port autre que le port le plus proche, ne sont � la charge des assureurs.",smallText));
           ajoutLigneVide(para,1);
           para.add(new Phrase("C - RECOURS DE TIERS", smallBold));
           ajoutLigneVide(para,1);
		   para.add(new Paragraph("Les recours de tiers, tant corporels que mat�riels sont rembours�s aux 9/10 �me sans que le dixi�me du pr�judice laiss� � la charge de l�assur� ne puisse exc�der F CFA 2 000 000.",smallText));
		   ajoutLigneVide(para, 1);
		   para.add(new Paragraph("Il est pr�cis� que sont formellement exclus de la garantie recours des tiers, les filets et/ou casiers auxquels le navire assur� aurait pu causer un dommage ou la perte.",smallText));
		   ajoutLigneVide(para,1);
		   
			para.add(new Paragraph("ARTICLE VII - CHOMAGE" ,subFont));
			para.setAlignment(Element.ALIGN_LEFT);
			ajoutLigneVide(para, 1);
			para.add(new Phrase("Par d�rogation � toutes dispositions contraires, la pr�sente assurance est souscrite � forfait sans ristourne pour ch�mage. ", smallText));
			ajoutLigneVide(para, 1);
			para.add(new Paragraph("ARTICLE VIII - DECOMPTE DE PRIME" ,subFont));
			para.setAlignment(Element.ALIGN_LEFT);
			ajoutLigneVide(para, 1);
			//a revoir La prime totale annuelle est fix�e � F CFA        31 405 d�compt�e comme suit :
			

			para.add(new Phrase(" ", smallText));
			ajoutLigneVide(para, 1);
			para.add(new Paragraph("ARTICLE IX - PAIEMENT DE LA PRIME", subFont));
			para.add(new Phrase(" La prime est payable selon le calendrier ci-apr�s :",smallText));
			ajoutLigneVide(para, 1);
			para.add(new Phrase(" Il est convenu qu�� d�faut de paiement d�une prime � l��ch�ance indiqu�e, les assureurs ont la facult� soit de suspendre l�assurance, soit d�en demander la r�siliation. la suspension ou la r�siliation ne prend effet que huit jours apr�s l�envoi � l�assur� d�une mise en demeure � son dernier domicile connu des assureurs et par lettre recommand�e, d�une mise en demeure d�avoir � payer. La suspension ou la r�siliation produira ses effets automatiquement � l�expiration de ce d�lai et jusqu�au lendemain � z�ro heure de la date de paiement de la prime de retard. :",smallText));
			para.add(new Paragraph("Pour tout sinistre survenu pendant une suspension des risques, les assureurs n�auront aucune indemnit� � payer, tous leurs droits contre l�assur� en ex�cution du contrat et en particulier, leur doit de recouvrement de la prime enti�re stipul�e, demeurant n�anmoins express�ment r�serv�. Il est en outre convenu qu�en cas de sinistre dont le montant serait sup�rieur � la prime �mise au titre du pr�sent contrat, le paiement de la totalit� des �ch�ances serait imm�diatement exigible par la compagnie ap�ritrice. ",smallText));
			ajoutLigneVide(para,1);
			
			para.add(new Paragraph("ARTICLE X - DOCUMENTS JOINTS", subFont));
			para.add(new Phrase(" L�assur� reconna�t avoir re�u un exemplaire  de chacun des documents suivants  :",smallText));
			ajoutLigneVide(para, 1);
            List list2 = new List(List.UNORDERED);
		  
	           list2.add(new ListItem("POLICE FRANCAISE D�ASSURANCE SUR COPRS DE NAVIRE DE PECHE ARTISANALE Imprim� du 1 er Octobre 2001 ",smallText));
	           list2.add(new ListItem("CONVENTION DE CONCARNEAU Imprim� du 1 er Janvier 1882 modifi� le 1 er Janvier 1988.",smallText));
	           para.add(list2);
			
	           ajoutLigneVide(para,1);
	           para.add(new Paragraph("ARTICLE XI - COASSURANCE", subFont));
	           ajoutLigneVide(para, 1);
	           para.add(new Phrase("Les engagements d�coulant de la garantie de la pr�sente police sont r�partis entre les compagnies figurant sur le tableau de r�partition de coassurance ci-annex�, chacune agissant pour sa part respective et sans solidarit� entre elles.",smallText));
			   para.add(new Phrase("Les pr�sentes conditions particuli�res pr�valent sur les conditions g�n�rales dans tous les cas o� elles y d�rogent.",smallText));
	           para.add(new Phrase("Sont nulles toutes adjonctions, suppressions ou modifications apport�es au texte du pr�sent contrat, qui ne seraient pas rev�tues du VISA des Assureurs.",smallText));
	           ajoutLigneVide(para, 1);
	           
	           para.add(new Phrase("Fait en triple exemplaires � ABIDJAN le 25/02/2014 .",smallText));
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
		
		/* aide a la cr�ation d'une cellule */
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
