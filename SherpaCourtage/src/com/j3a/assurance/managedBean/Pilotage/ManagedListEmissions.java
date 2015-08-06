package com.j3a.assurance.managedBean.Pilotage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.j3a.assurance.converter.PtdeVenteCvtr;
import com.j3a.assurance.converter.RisqCvtr;
import com.j3a.assurance.model.Avenant;
import com.j3a.assurance.model.Exercice;
import com.j3a.assurance.model.PointVente;
import com.j3a.assurance.model.Risque;
import com.j3a.assurance.reporting.design.AvenantDesign;
import com.j3a.assurance.utilitaires.AvenantByPointVenteRow;
import com.j3a.assurance.utilitaires.ColumnModel;
import com.j3a.assurance.utilitaires.DynAvenRisqRow;
import com.j3a.assurance.utilitaires.PtVteEtAvenantListRisqCA;

@Component
@Scope("session")
@Transactional
public class ManagedListEmissions {

	public ManagedListEmissions() {
		// TODO Auto-generated constructor stub

	}

	@Autowired
	private AvenantDesign avenantDesign;

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RisqCvtr risqCvtr;
	@Autowired
	private PtdeVenteCvtr ptdeVenteCvtr;

	private List<PointVente> slctdPtVtes;
	private List<Risque> slctdRisques;
	private List<Exercice> slctdExos;
	private List<String> slctdMvmts;
	private List<PtVteEtAvenantListRisqCA> listStatByPtVte;
	private CartesianChartModel modelTemp = new CartesianChartModel();
	private Double minY,maxY = new Double(0);
	private Date dateDeb, dateFin;
	private final String qryCmnPart = "SELECT {A.*} FROM  avenant A JOIN  contrat C ON C.NUM_POLICE = A.NUM_POLICE JOIN  point_vente P ON P.CODE_POINT_VENTE = C.CODE_POINT_VENTE JOIN  risque R ON R.CODE_RISQUE = C.CODE_RISQUE  WHERE ";
	private final List<String> listMvmt = new ArrayList<String>() {
		{
			add("Affaire Nouvelle");
			add("Incorporation");
			add("Retrait");
			add("Modification de Garanties");
			add("Annulation");
			add("Réémission");
			add("Suspension");
			add("Remise en vigueur");
			add("Résiliation");
			add("Modification de prise d'effet");
			add("Renouvellement");
		}
	};
	private List<AvenantByPointVenteRow> listAvenByPtvte;
	private PieChartModel modelMvmt= new PieChartModel();
	private PieChartModel modelPtvte= new PieChartModel();
	private PieChartModel modelRisque= new PieChartModel();
	private Boolean okDisblr = true;
	private SelectItem[] etatQuitOptions;
	private List<AvenantByPointVenteRow> filteredavenByPtvte;
	private List<ColumnModel> columns = new ArrayList<ColumnModel>();

	public void submitCriterias() {

		// TODO
		String myQuery = qryCmnPart + qryPeriodPart();

		String b = qryPtvtePart();
		if (b != "")
			myQuery = myQuery + " AND " + b;

		String a = qryRisqPart();
		if (a != "")
			myQuery = myQuery + " AND " + a;

		String c = qryMvmtPart();
		if (c != "")
			myQuery = myQuery + " AND " + c;

		System.out.println("PREVISUALISATION DE LA REQUETE////////////////////////////////////////");
		System.out.println(myQuery);
		System.out.println("PREVISUALISATION DE LA REQUETE////////////////////////////////////////");

		List<Avenant> X = getSessionFactory().getCurrentSession().createSQLQuery(myQuery).addEntity("A",Avenant.class).list();//
		//
		buildColumnModel();
		//buildRisqpropLib(r)
		setListAvenByPtvte(parseListAven(X));
		
		
		
		//initListStatByPtVte(X);
		
		//smallTest(getListStatByPtVte());
		afficheGraphStat();
		
	}
	public void callGraphStat(){
		System.out.println("INSIDE callGraphStat////////////////////////////////////////");
		Map<String,Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", true);
		options.put("contentHeight", 900);
		options.put("contentWidth", 1300);
		
		RequestContext.getCurrentInstance().openDialog ("graphStatListEmiss", options, null);
		
		//RequestContext.getCurrentInstance().execute("PF('Alert').show();");
		System.out.println("AFTER callGraphStat////////////////////////////////////////");
	}
	
	public void afficheGraphStat(){
		loadModelMvmt();
		checkIfModelEmpty(getModelMvmt());
		System.out
				.println("PREVISUALISATION DE modelMvmt////////////////////////////////////////"
						+ getModelMvmt().getData());
		loadModelPtVte();
		checkIfModelEmpty(getModelPtvte());
		System.out
				.println("PREVISUALISATION DE modelPtvte////////////////////////////////////////"
						+ getModelPtvte().getData());
		loadModelRisq();
		checkIfModelEmpty(getModelRisque());
		System.out
				.println("PREVISUALISATION DE modelRisq////////////////////////////////////////"
						+ getModelRisque().getData());
		
		buildPtvteSeries(getDateDeb(), getDateFin(), getSlctdPtVtes());
		System.out
		.println("PREVISUALISATION DE modelTemp////////////////////////////////////////");
		for(ChartSeries c: getModelTemp().getSeries()){
			System.out.println("label--"+c.getLabel()+"!!!!  Value---"+c.getData());
		}
	}
	
	
	
	private void buildColumnModel(){
		int i = 0;
		columns.clear();
		columns.add(new ColumnModel("Avenant", "mvmt",i));
		i++;
		for(Risque r : getSlctdRisques()){
			//traitement pour obtenir le"String" property dans DynAvenRisq
			String prop = buildRisqpropLib(r);
			columns.add(new ColumnModel("risque.libelleRisque", prop, i));
			i++;
		}
		
	}
	
	private String buildRisqpropLib(Risque r){
		String X ="";
		if(r!=null){
			//replace : espace, &,->""
			//replace : é->"e"
			
			X=r.getLibelleRisque().replaceAll(" ", "");
			X=X.replaceAll("&", "");
			X=X.replaceAll("é", "");
			X="mtRisq"+X;
		}
		return X;
	}
	
	private BigDecimal CAbyPtvteAvenRisq(PointVente P, Avenant A, Risque R){
		StringBuilder sB = new StringBuilder();
		sB.append("SELECT SUM( Q.PRIME_TOTALE) AS totalCA ");
		sB.append("FROM avenant A ");
		sB.append("JOIN contrat C ON C.NUM_POLICE = A.NUM_POLICE ");
		sB.append("JOIN point_vente P ON P.CODE_POINT_VENTE = C.CODE_POINT_VENTE ");
		sB.append("JOIN risque R ON R.CODE_RISQUE = C.CODE_RISQUE ");
		sB.append("JOIN quittance Q ON Q.NUM_AVENANT = A.NUM_AVENANT ");
		sB.append("WHERE C.CODE_POINT_VENTE = '");
		sB.append(P.getCodePointVente());
		sB.append("' AND R.CODE_RISQUE ='");
		sB.append(R.getCodeRisque());
		sB.append("' AND A.MOUVEMENT ='");
		sB.append(A.getMouvement());
		sB.append("' ");
		
		String myQuery = sB.toString();
		
		
		System.out
				.println("PREVISUALISATION DE LA REQUETE ++CAbyPtvteAvenRisq++////////////////////////////////////////");
		System.out.println(myQuery);
		System.out
				.println("PREVISUALISATION DE LA REQUETE++CAbyPtvteAvenRisq++////////////////////////////////////////");
		
		BigDecimal X =  (BigDecimal) getSessionFactory().getCurrentSession()
				.createSQLQuery(myQuery).addScalar("totalCA").uniqueResult();
		
		return X;
		
	}
	
	
	private BigDecimal CAbyPtvteInPeriod(PointVente P, LocalDate dateD, LocalDate dateF){
		StringBuilder sB = new StringBuilder();
		sB.append("SELECT SUM( Q.PRIME_TOTALE) AS totalCA ");
		sB.append("FROM avenant A ");
		sB.append("JOIN contrat C ON C.NUM_POLICE = A.NUM_POLICE ");
		sB.append("JOIN point_vente P ON P.CODE_POINT_VENTE = C.CODE_POINT_VENTE ");
		sB.append("JOIN risque R ON R.CODE_RISQUE = C.CODE_RISQUE ");
		sB.append("JOIN quittance Q ON Q.NUM_AVENANT = A.NUM_AVENANT ");
		sB.append("WHERE C.CODE_POINT_VENTE = '");
		sB.append(P.getCodePointVente()+"'");
		sB.append(" AND ");
		String X ="A.EFFET BETWEEN '" + dateD.toString("yyyy-MM-dd") + "' AND '"
				+ dateF.toString("yyyy-MM-dd") + "' ";
		sB.append(X);
		
		String myQuery = sB.toString();
		
		
		System.out
				.println("PREVISUALISATION DE LA REQUETE ++CAbyPtvteAvenRisq++////////////////////////////////////////");
		System.out.println(myQuery);
		System.out
				.println("PREVISUALISATION DE LA REQUETE++CAbyPtvteAvenRisq++////////////////////////////////////////");
		
		BigDecimal Ca =  (BigDecimal) getSessionFactory().getCurrentSession()
				.createSQLQuery(myQuery).addScalar("totalCA").uniqueResult();
		
		return Ca;
		
	}
	
	private void buildPtvteSeries(Date deb, Date fin, List<PointVente> ptVteList){
		 LocalDate date1 = new LocalDate(deb.getTime());
		 LocalDate date2 = new LocalDate(fin.getTime()); 
		 List<BigDecimal> toSort= new ArrayList<BigDecimal>();
		 getModelTemp().clear();
		 for(PointVente p : ptVteList){
			 ChartSeries ptVte = new ChartSeries();
			 ptVte.setLabel(p.getLibellePointVente());
			 
			 
			 while(date1.isBefore(date2)){
			     //System.out.println(date1.toString("MMM/yyyy"));
			     LocalDate startOfMonth = date1.dayOfMonth().withMinimumValue();
			     LocalDate endOfMonth = date1.dayOfMonth().withMaximumValue();
			     BigDecimal b =CAbyPtvteInPeriod(p, startOfMonth, endOfMonth);
			     ptVte.set(date1.toString("MMM/yyyy"), b);
			     if(b!=null){
			    	 toSort.add(b);
			     }
			     
			     date1 = date1.plus(Period.months(1));
			 }
			 getModelTemp().addSeries(ptVte);
		 }
		 Collections.sort(toSort);
		 setMinY(toSort.get(0).doubleValue()+100000);
		 setMaxY(toSort.get(toSort.size()-1).doubleValue()-100000);
		 
		 if(getModelTemp().getSeries().isEmpty())
			 getModelTemp().addSeries( new ChartSeries("No records found."));
	}
	
	private void smallTest(List<PtVteEtAvenantListRisqCA> X){
		System.out.println("========================PtVteEtAvenantListRisqCA CONSTRUIT===============================");
		/*for (PtVteEtAvenantListRisqCA P:X){
			System.out.println("Point de Vente========================"+P.getPointVente().getLibellePointVente());
			System.out.println("CA totalPoint de Vente================"+P.getTotalCA());
			for(AvenantEtRisqList A:P.getListAvenRisqCA()){
				System.out.println("======================================AVENANT EFFECTUEE:"+A.getAvenant().getMouvement());
				for(ColumnModel C:A.getColumns()){
					System.out.println("======================================COLUMNmODELS=============================");
					System.out.println("================Headear:"+C.getHeader()+"  PROPERTY:"+C.getProperty());
				}
				System.out.println("======================================RISQUE & CA===============================");
				for(RisqEtChiffreAff R:A.getListRisqEtCA()){
					System.out.println("================Risque:"+R.getRisque().getLibelleRisque()+"  CA:"+R.getcAByPtveAvenRisq());
				}
				
			}
		}*/
		
	}
	
	
	private void initListStatByPtVte(List<Avenant> listAv){
		List<PtVteEtAvenantListRisqCA> X = new ArrayList<PtVteEtAvenantListRisqCA>();
		for(PointVente p : getSlctdPtVtes()){
			PtVteEtAvenantListRisqCA Y = new PtVteEtAvenantListRisqCA();
			Y.setPointVente(p);
			List<DynAvenRisqRow> A = new ArrayList<DynAvenRisqRow>();
			//System.out.println("-/--/-/-/-/--/-/-/-/-/--/-/-/----/-/-/ List<Object> listAv"+listAv.toString());
			for(Avenant o : listAv){
				DynAvenRisqRow dynAvRisq = new DynAvenRisqRow();
				dynAvRisq.setMvmt(o.getMouvement());
				for (Risque r :getSlctdRisques()){
					BigDecimal Sum = CAbyPtvteAvenRisq(p, (Avenant) o, r);
					if(r.getLibelleRisque().equalsIgnoreCase("AUTOMOBILE")){
						dynAvRisq.setMtRisqAUTOMOBILE(Sum);
					}
					if(r.getLibelleRisque().equalsIgnoreCase("Risque NTA Vol")){
						dynAvRisq.setMtRisqAUTOMOBILE(Sum);
					}
					if(r.getLibelleRisque().equalsIgnoreCase("Sante")){
						dynAvRisq.setMtRisqAUTOMOBILE(Sum);
					}
					if(r.getLibelleRisque().equalsIgnoreCase("Gestion confiée")){
						dynAvRisq.setMtRisqAUTOMOBILE(Sum);
					}
					if(r.getLibelleRisque().equalsIgnoreCase("Faculté Maritime & Fluvial")){
						dynAvRisq.setMtRisqAUTOMOBILE(Sum);
					}
					if(r.getLibelleRisque().equalsIgnoreCase("Faculté Aérienne")){
						dynAvRisq.setMtRisqAUTOMOBILE(Sum);
					}
					if(r.getLibelleRisque().equalsIgnoreCase("Faculté Terrestre")){
						dynAvRisq.setMtRisqAUTOMOBILE(Sum);
					}
					if(r.getLibelleRisque().equalsIgnoreCase("Corps Maritime & Fluvial")){
						dynAvRisq.setMtRisqAUTOMOBILE(Sum);
					}
					if(r.getLibelleRisque().equalsIgnoreCase("Corps Aérien")){
						dynAvRisq.setMtRisqAUTOMOBILE(Sum);
					}
					if(r.getLibelleRisque().equalsIgnoreCase("MultiRisques Habitation")){
						dynAvRisq.setMtRisqAUTOMOBILE(Sum);
					}
					if(r.getLibelleRisque().equalsIgnoreCase("Individuelle Accidents")){
						dynAvRisq.setMtRisqAUTOMOBILE(Sum);
					}
					if(r.getLibelleRisque().equalsIgnoreCase("Risque Informatique")){
						dynAvRisq.setMtRisqAUTOMOBILE(Sum);
					}
						
				}
				A.add(dynAvRisq);
			}
			Y.setListDynAvenRisqRw(A);
			
			X.add(Y);		
		}
		setListStatByPtVte(X);
	}
	
	private void checkIfModelEmpty(PieChartModel model) {
		if(model.getData().isEmpty())
			model.set("No records found.", 0);
	}

	public void imprimerPDF() {

		try {
			getAvenantDesign().editerAvenantPointDeVente(
					getAvenantDesign().getAvenantPoinVenteRow(
							getListAvenByPtvte(), slctdPtVtes, slctdRisques,
							slctdMvmts),
					dateDeb,
					dateFin,
					(HttpServletRequest) FacesContext.getCurrentInstance()
							.getExternalContext().getRequest(),
					(HttpServletResponse) FacesContext.getCurrentInstance()
							.getExternalContext().getResponse());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public String qryPtvtePart() {
		String X = "";
		if (!getSlctdPtVtes().isEmpty()) {
			X = " P.CODE_POINT_VENTE IN (";
			for (PointVente p : getSlctdPtVtes()) {
				X = X + "'" + p.getCodePointVente() + "',";
			}
			X = X.substring(0, X.length() - 1) + ") ";
		}
		return X;
	}

	public String qryMvmtPart() {
		String X = "";
		if (!getSlctdMvmts().isEmpty()) {
			X = " A.MOUVEMENT IN (";
			for (String m : getSlctdMvmts()) {
				X = X + "'" + m + "',";
			}
			X = X.substring(0, X.length() - 1) + ") ";
		}
		return X;
	}

	public String qryPeriodPart() {
		String X = "";
		X = X + "A.EFFET BETWEEN '" + formatDate(getDateDeb()) + "' AND '"
				+ formatDate(getDateFin()) + "'";
		return X;
	}

	public String qryRisqPart() {
		String X = "";
		if (!getSlctdRisques().isEmpty()) {
			X = " R.CODE_RISQUE IN (";
			for (Risque r : getSlctdRisques()) {
				X = X + "'" + r.getCodeRisque() + "',";
			}
			X = X.substring(0, X.length() - 1) + ") ";
		}
		return X;
	}

	public String formatDate(Date d) {
		String X = "";
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		// AAAA-MM-JJ
		X = X + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-"
				+ c.get(Calendar.DATE);

		return X;
	}

	public List<AvenantByPointVenteRow> parseListAven(List<Avenant> listaven) {
		List<AvenantByPointVenteRow> X = new ArrayList<AvenantByPointVenteRow>();
		for (Avenant a : listaven) {
			// TO DO
			AvenantByPointVenteRow ap = new AvenantByPointVenteRow();
			Avenant aV = a;
			ap.setAvenant(aV);
			ap.setClient(aV.getContrat().getPersonne());
			ap.setContrat(aV.getContrat());
			ap.setExercice(aV.getExercice());
			ap.setPtVte(aV.getContrat().getPointVente());
			if (!aV.getQuittances().isEmpty()) {
				ap.setQuittance(aV.getQuittances().iterator().next());
			}
			X.add(ap);
		}
		return X;
	}

	public Number ptvtePieChartData(PointVente ptVte) {
		BigDecimal X = BigDecimal.ZERO;
		for (AvenantByPointVenteRow a : getListAvenByPtvte()) {
			if (a.getPtVte().getCodePointVente().equalsIgnoreCase(ptVte.getCodePointVente())) {
				if (a.getQuittance() != null)
					X = X.add(a.getQuittance().getNetAPayer());
			}
		}
		;
		return X;
	}

	public void loadModelPtVte() {
		List<PointVente> L = new ArrayList<PointVente>();
		if (getSlctdPtVtes().isEmpty()) {
			L = getPtdeVenteCvtr().getPointVenteList();
		} else {
			L = getSlctdPtVtes();
		}
		setModelPtvte(new PieChartModel());
//		getModelPtvte().setLegendCols(2);
//		getModelPtvte().setLegendPosition("w");
//		getModelPtvte().setTitle("REPARTION DU CHIFFRE D'AFFAIRE PAR POINTS DE VENTE");
		
		for (PointVente p : L) {
			getModelPtvte().set(p.getLibellePointVente(), ptvtePieChartData(p));
		}
	}

	public Number mvmtPieChartData(String mvmt) {
		BigDecimal X = BigDecimal.ZERO;
		for (AvenantByPointVenteRow a : getListAvenByPtvte()) {
			if (a.getAvenant().getMouvement().equalsIgnoreCase(mvmt)) {
				if (a.getQuittance() != null)
					X = X.add(a.getQuittance().getNetAPayer());
			}
		}
		;
		return X;
	}

	public void loadModelMvmt() {
		List<String> L = new ArrayList<String>();
		if (getSlctdMvmts().isEmpty()) {
			L = listMvmt;
		} else {
			L = getSlctdMvmts();
		}
		setModelMvmt(new PieChartModel());
//		getModelMvmt().setLegendCols(2);
//		getModelMvmt().setLegendPosition("w");
//		getModelMvmt().setTitle("REPARTION DU CHIFFRE D'AFFAIRE PAR AVENANTS" );
		for (String m : L) {
			getModelMvmt().set(m, mvmtPieChartData(m));
		}
	}

	public Number risqPieChartData(Risque risq) {
		BigDecimal X = BigDecimal.ZERO;
		for (AvenantByPointVenteRow a : getListAvenByPtvte()) {
			if (a.getAvenant().getContrat().getRisque().getCodeRisque()
					.equalsIgnoreCase(risq.getCodeRisque())) {
				if (a.getQuittance() != null)
					X = X.add(a.getQuittance().getNetAPayer());
			}
		}
		;
		return X;
	}

	public void loadModelRisq() {
		List<Risque> L = new ArrayList<Risque>();
		if (getSlctdRisques().isEmpty()) {
			L = getRisqCvtr().getRisqList();
		} else {
			L = getSlctdRisques();
		}
		setModelRisque(new PieChartModel());
//		getModelRisque().setLegendCols(2);
//		getModelRisque().setLegendPosition("e");
//		getModelRisque().setTitle("REPARTION DU CHIFFRE D'AFFAIRE PAR RISQUES");
		for (Risque r : L) {
			getModelRisque().set(r.getLibelleRisque(), risqPieChartData(r));
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<PointVente> getSlctdPtVtes() {
		return slctdPtVtes;
	}

	public void setSlctdPtVtes(List<PointVente> slctdPtVtes) {
		this.slctdPtVtes = slctdPtVtes;
	}

	public List<Risque> getSlctdRisques() {
		return slctdRisques;
	}

	public void setSlctdRisques(List<Risque> slctdRisques) {
		this.slctdRisques = slctdRisques;
	}

	public List<Exercice> getSlctdExos() {
		return slctdExos;
	}

	public void setSlctdExos(List<Exercice> slctdExos) {
		this.slctdExos = slctdExos;
	}

	public List<String> getSlctdMvmts() {
		return slctdMvmts;
	}

	public void setSlctdMvmts(List<String> slctdMvmts) {
		this.slctdMvmts = slctdMvmts;
	}

	public Date getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public List<AvenantByPointVenteRow> getListAvenByPtvte() {
		return listAvenByPtvte;
	}

	public void setListAvenByPtvte(List<AvenantByPointVenteRow> listAvenByPtvte) {
		this.listAvenByPtvte = listAvenByPtvte;
	}

	public PieChartModel getModelMvmt() {
		/*if(modelMvmt== null)
			modelMvmt = new PieChartModel();*/
		return modelMvmt;
	}

	public void setModelMvmt(PieChartModel modelMvmt) {
		this.modelMvmt = modelMvmt;
	}

	public PieChartModel getModelPtvte() {
		/*if(modelPtvte== null)
			modelPtvte = new PieChartModel();*/
		return modelPtvte;
	}

	public void setModelPtvte(PieChartModel modelPtvte) {
		this.modelPtvte = modelPtvte;
	}

	public PieChartModel getModelRisque() {
		/*if(modelRisque== null)
			modelRisque = new PieChartModel();*/
		return modelRisque;
	}

	public void setModelRisque(PieChartModel modelRisque) {
		this.modelRisque = modelRisque;
	}

	public RisqCvtr getRisqCvtr() {
		return risqCvtr;
	}

	public void setRisqCvtr(RisqCvtr risqCvtr) {
		this.risqCvtr = risqCvtr;
	}

	public PtdeVenteCvtr getPtdeVenteCvtr() {
		return ptdeVenteCvtr;
	}

	public void setPtdeVenteCvtr(PtdeVenteCvtr ptdeVenteCvtr) {
		this.ptdeVenteCvtr = ptdeVenteCvtr;
	}

	public Boolean getOkDisblr() {
		if (getDateDeb() != null && getDateFin() != null
				&& getDateDeb().before(getDateFin()))
			okDisblr = false;
		return okDisblr;
	}

	public void setOkDisblr(Boolean okDisblr) {
		this.okDisblr = okDisblr;
	}

	public SelectItem[] getEtatQuitOptions() {

		SelectItem[] Options = new SelectItem[3];
		Options[0] = new SelectItem("", "Select");
		Options[1] = new SelectItem("solder", "Soldé");
		Options[2] = new SelectItem("non solder", "Non Soldé");

		return Options;
	}

	public void setEtatQuitOptions(SelectItem[] etatQuitOptions) {
		this.etatQuitOptions = etatQuitOptions;
	}

	public List<AvenantByPointVenteRow> getFilteredavenByPtvte() {
		return filteredavenByPtvte;
	}

	public void setFilteredavenByPtvte(
			List<AvenantByPointVenteRow> filteredavenByPtvte) {
		this.filteredavenByPtvte = filteredavenByPtvte;
	}

	public AvenantDesign getAvenantDesign() {
		return avenantDesign;
	}

	public void setAvenantDesign(AvenantDesign avenantDesign) {
		this.avenantDesign = avenantDesign;
	}

	public List<PtVteEtAvenantListRisqCA> getListStatByPtVte() {
		return listStatByPtVte;
	}

	public void setListStatByPtVte(List<PtVteEtAvenantListRisqCA> listStatByPtVte) {
		this.listStatByPtVte = listStatByPtVte;
	}
	public List<ColumnModel> getColumns() {
		return columns;
	}
	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}
	public CartesianChartModel getModelTemp() {
		return modelTemp;
	}
	public void setModelTemp(CartesianChartModel modelTemp) {
		this.modelTemp = modelTemp;
	}
	public Double getMinY() {
		return minY;
	}
	public void setMinY(Double minY) {
		this.minY = minY;
	}
	public Double getMaxY() {
		return maxY;
	}
	public void setMaxY(Double maxY) {
		this.maxY = maxY;
	}
	
	



}
