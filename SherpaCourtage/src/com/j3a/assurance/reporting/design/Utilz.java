package com.j3a.assurance.reporting.design;

import java.awt.Color;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;

public class Utilz {

	/* Création du Header du document */
	public static HeaderFooter createHeader() {
		Paragraph para = new Paragraph();
		para.add("Entete du fichier PDF");
		HeaderFooter header = new HeaderFooter(para, false);
		header.setAlignment(HeaderFooter.ALIGN_CENTER);
		return header;
	}

	/* Ajout d'un enseignement à notre emploi du temps */
	public static Cell ajoutEnseignement(String cours, String prof,
			String salle, int type, int heure) {
		Cell cell = new Cell("");
		Paragraph p = new Paragraph();
		p.add(cours + "\n" + prof + "\n" + salle);
		cell.add(p);
		cell.setColspan(type);
		cell.setRowspan(heure / 2);
		cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
		cell.setVerticalAlignment(Cell.ALIGN_CENTER);
		return cell;
	}

	/* Création du tableau de notre emploi du temps */
	public static Table createTable(int debut) throws BadElementException {
		String sFin = Integer.toString(debut + 4);
		Table table = new Table(11);
		table.setBorderWidth(2);
		table.setBorderColor(new Color(0, 0, 255));
		table.setPadding(5);
		table.setSpacing(3);

		Cell cell = createCell("Semaine du " + debut + " au " + sFin
				+ " décembre", 11);
		table.addCell(cell);
		cell = createCell("Jours\n/\nHeure", 1);
		table.addCell(cell);
		cell = createCell("Lundi " + Integer.toString(debut), 2);
		table.addCell(cell);
		cell = createCell("Mardi " + Integer.toString(debut + 1), 2);
		table.addCell(cell);
		cell = createCell("Mercredi " + Integer.toString(debut + 2), 2);
		table.addCell(cell);
		cell = createCell("Jeudi " + Integer.toString(debut + 3), 2);
		table.addCell(cell);
		cell = createCell("Vendredi " + Integer.toString(debut + 4), 2);
		table.addCell(cell);
		return table;
	}

	/* Aide à la création d'une cellule */
	private static Cell createCell(String sCell, int colspan) {
		Cell cell = new Cell(sCell);
		cell.setColspan(colspan);
		cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
		cell.setVerticalAlignment(Cell.ALIGN_CENTER);
		return cell;
	}
}