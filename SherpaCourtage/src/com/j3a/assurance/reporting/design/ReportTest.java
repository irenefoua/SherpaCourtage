package com.j3a.assurance.reporting.design;

import com.itextpdf.text.Font;

public class ReportTest {
	private static String FILE = "c:/reports/test.pdf";
	private static Font TITRE1 = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD);
	private static Font TITRE3 = new Font(Font.FontFamily.TIMES_ROMAN, 14,
			Font.BOLD);
	private static Font textFont = new Font(Font.FontFamily.HELVETICA, 10);
	private static Font TITRE2 = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 10,
			Font.BOLD);
	
	QuittanceDesign quittanceDesign;

	public void generateRapport() {
		// création du rapport quittance
		/*QuittanceReportFactory quittanceFactory = new QuittanceReportFactory();
		quittanceFactory.setIdQuittance("QUIT0000005");
		System.out.println("getIdQuittance :"
				+ quittanceFactory.getIdQuittance());
		QuittanceDesign quittanceDesign = new QuittanceDesign();
		quittanceDesign.setQuittanceReportFactory(quittanceFactory);
		System.out.println("quittanceDesign.getQuittanceReportFactory() "
				+ quittanceDesign.getQuittanceReportFactory().getIdQuittance());
		quittanceDesign.generateRapport();
		System.out.println("--------rapport générer---------"
				+ "getidQuittance :"
				+ quittanceDesign.getQuittanceReportFactory().getIdQuittance());*/
		getQuittanceDesign().generateRapport();
	}

	public QuittanceDesign getQuittanceDesign() {
		return quittanceDesign;
	}

	public void setQuittanceDesign(QuittanceDesign quittanceDesign) {
		this.quittanceDesign = quittanceDesign;
	}
}
