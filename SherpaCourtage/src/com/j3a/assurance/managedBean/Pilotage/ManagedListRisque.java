package com.j3a.assurance.managedBean.Pilotage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.j3a.assurance.converter.PtdeVenteCvtr;
import com.j3a.assurance.converter.RisqCvtr;
import com.j3a.assurance.model.PointVente;
import com.j3a.assurance.model.Risque;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.utilitaires.ChiffreAffAndRisq;
import com.j3a.assurance.utilitaires.chifferByRisqRow;


@Component

@Scope("session")
@Transactional
public class ManagedListRisque {
 
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	ObjectService objectService;
	@Autowired
	private RisqCvtr risqCvtr;
	@Autowired
	private PtdeVenteCvtr ptdeVenteCvtr;
	
	
	private List listRisque;
	private static Logger logger = Logger.getLogger(ManagedListRisque.class);
	private List<Risque> selectRisque = new ArrayList<Risque>()  ;
	private List<PointVente> selectPv;
	
	
	private PieChartModel modelrisq = new PieChartModel();
	private PieChartModel modelPv = new PieChartModel();
	
	private Date dateDeb, dateFin;
	private final String RequetPart ="SELECT SUM( Q.PRIME_TOTALE) as chiffreAff  FROM avenant A JOIN contrat C ON C.NUM_POLICE = A.NUM_POLICE JOIN point_vente P ON P.CODE_POINT_VENTE = C.CODE_POINT_VENTE JOIN risque R ON R.CODE_RISQUE = C.CODE_RISQUE JOIN quittance Q ON Q.NUM_AVENANT = A.NUM_AVENANT WHERE ";
	private List<chifferByRisqRow> listChiffrAf;
	
/*@PostConstruct
	public void init(){
		listRisque = getObjectService().getObjects("Risque");
		listPV = getObjectService().getObjects("PointVente");
		logger.info("Taille de la liste: "+listRisque.size());
		System.out.println("Taille de la liste: "+listRisque.size() +"Taille de la liste: "+listPV.size());
	}*/
		
	 /*@PostConstruct
	    public void init() {
	        createPieModel();
	    }
*/


public void  recupererinfo() {
	
	setListChiffrAf(buildListChAffbyPte());
	createPieModel1();
	verifierModel(getModelrisq());
	createPieModel2();
	verifierModel(getModelPv());
	
	}

public void callGraphStat(){
	System.out.println("INSIDE callGraphStat////////////////////////////////////////");
	Map<String,Object> options = new HashMap<String, Object>();
	options.put("modal", true);
	options.put("draggable", false);
	options.put("resizable", true);
	options.put("contentHeight", 900);
	options.put("contentWidth", 1300);
	
	RequestContext.getCurrentInstance().openDialog ("graphStatListrisq", options, null);
	
	//RequestContext.getCurrentInstance().execute("PF('Alert').show();");
	System.out.println("AFTER callGraphStat////////////////////////////////////////");
}


public List<chifferByRisqRow> buildListChAffbyPte(){
	List<chifferByRisqRow> X =new ArrayList<chifferByRisqRow>();
	for(PointVente p : selectPv){
		chifferByRisqRow a = new chifferByRisqRow();
		a.setPtVte(p);
		a.setListCAandRisq(buildListCA_Risq(p, getSelectRisque()));
		X.add(a);
	}
	return X;
}

public List<ChiffreAffAndRisq> buildListCA_Risq(PointVente p, List<Risque> risqList){
	System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXRISQLIST"+risqList);//clean
	//risqList=getSelectRisque();
	List<ChiffreAffAndRisq> X =new ArrayList<ChiffreAffAndRisq>();
	for(Risque r : risqList){
		
		ChiffreAffAndRisq c = new ChiffreAffAndRisq();
		c.setRisque(r);
		
		String myQuery = RequetPart + qryPeriodPart();

		String b = qryPtvtePart(p.getCodePointVente());
		if (b != "")
			myQuery = myQuery + " AND " + b;

		String a = qryRisqPart(r.getCodeRisque());
		if (a != "")
			myQuery = myQuery + " AND " + a;


		System.out
				.println("PREVISUALISATION DE LA REQUETE////////////////////////////////////////");
		System.out.println(myQuery);
		System.out
				.println("PREVISUALISATION DE LA REQUETE////////////////////////////////////////");

		BigDecimal A = (BigDecimal) getSessionFactory().getCurrentSession()
				.createSQLQuery(myQuery).addScalar("chiffreAff").uniqueResult();
		//if (A == null)
		//	A = BigDecimal.ZERO;
		c.setChiffrAffaire(A);
		X.add(c);
	}
	return X;
}


public String qryPtvtePart(String codePtVte) {
	String X = "";
	X = " P.CODE_POINT_VENTE = '"+codePtVte+"'";
	return X;
}

/*public String qryQuit() {
	String X = "";
	if (!getSelectQuit().isEmpty()) {
		X = " Q.NUM_AVENANT IN (";
		for (Quittance q : getSelectQuit()) {
			X = X + "'" + q.getId() + "',";
		}
		X = X.substring(0, X.length() - 1) + ") ";
	}
	return X;
}*/

public String qryPeriodPart() {
	String X = " ";
	X = X + "A.EFFET BETWEEN '" + formatDate(getDateDeb()) + "' AND '"
			+ formatDate(getDateFin()) + "'";
	return X;
}

public String qryRisqPart(String codeRisq) {
	String X = "";
	X = " R.CODE_RISQUE = '"+codeRisq+"' ";
	return X;
	
}

public String formatDate(Date d) {
	String X = " ";
	Calendar c = Calendar.getInstance();
	c.setTime(d);
	// AAAA-MM-JJ
	X = X + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-"
			+ c.get(Calendar.DATE);

	return X;
}

private void verifierModel(PieChartModel model) {
	if (model.getData().isEmpty())
		model.set("Aucune Donnée.", 0);
}




private void createPieModel1() {
   // modelrisq = new PieChartModel();
	List<Risque> L = new ArrayList<Risque>();
	if (getSelectRisque().isEmpty()) {
		L = getRisqCvtr().getRisqList();
	} else {
		L = getSelectRisque();
	}
	setModelrisq(new PieChartModel());
	
	 //modelrisq.setTitle("REPARTION DU CHIFFRE D'AFFAIRE PAR RISQUES");
	// modelrisq.setLegendPosition("e");
	for (Risque r : getSelectRisque()) {
    modelrisq.set(r.getLibelleRisque(),risqpie(r));
  // modelrisq.set("Brand 2", 325);
    }
      
}



public Number pvPie(PointVente pv) {
	BigDecimal X = BigDecimal.ZERO;

	for (chifferByRisqRow c : getListChiffrAf()) {
		if(c.getPtVte().getCodePointVente().equalsIgnoreCase(pv.getCodePointVente()))
	X=c.getCATotal();
	}
	return X;
			}
				
public Number risqpie(Risque risq) {
	BigDecimal X = BigDecimal.ZERO;
	
	for (chifferByRisqRow c : getListChiffrAf()) {
		for(ChiffreAffAndRisq ca :c.getListCAandRisq()){
			if(ca.getRisque().getCodeRisque().equalsIgnoreCase(risq.getCodeRisque())){
				if(ca.getChiffrAffaire()!=null)
					X=X.add(ca.getChiffrAffaire());
			}
			
		}
	}
		return X;
	}
					



private void createPieModel2() {
	   
		List<PointVente> L = new ArrayList<PointVente>();
		if (getSelectPv().isEmpty()) {
			L = getPtdeVenteCvtr().getPointVenteList();
		} else {
			L = getSelectPv();
		}
		setModelPv(new PieChartModel());
		//getListChiffrAf().clear();
		// modelPv.setTitle("REPARTION DU CHIFFRE D'AFFAIRE PAR POINT DE VENTE");
		// modelPv.setLegendPosition("e");
		// modelPv.setShowDataLabels(true);
		 
		 
		for (chifferByRisqRow c : getListChiffrAf()) {
	     modelPv.set(c.getPtVte().getLibellePointVente(), c.getCATotal());
	   // modelrisq.set("Brand 2", 325);
	    }
	    
	     
	   
	}

public  void reinitialiser(){
	
	Map<String, Object> sessionMap = FacesContext.getCurrentInstance()
			.getExternalContext().getSessionMap();
	sessionMap.clear();

	
	}



//getter 

public SessionFactory getSessionFactory() {
	return sessionFactory;
}


public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}



public ObjectService getObjectService() {
	return objectService;
}


public void setObjectService(ObjectService objectService) {
	this.objectService = objectService;
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



/*public List<Risque> getSelectRisque() {
	return selectRisque;
}

public void setSelectRisque(List<Risque> selectRisque) {
	this.selectRisque = selectRisque;
}
*/




public List<PointVente> getSelectPv() {
	return selectPv;
}

public String getRequetPart() {
	return RequetPart;
}



public void setSelectPv(List<PointVente> selectPv) {
	this.selectPv = selectPv;
}


public List getListRisque() {
	return listRisque;
}

public void setListRisque(List listRisque) {
	this.listRisque = listRisque;
}



public RisqCvtr getRisqCvtr() {
	return risqCvtr;
}


public PtdeVenteCvtr getPtdeVenteCvtr() {
	return ptdeVenteCvtr;
}



public void setRisqCvtr(RisqCvtr risqCvtr) {
	this.risqCvtr = risqCvtr;
}



public void setPtdeVenteCvtr(PtdeVenteCvtr ptdeVenteCvtr) {
	this.ptdeVenteCvtr = ptdeVenteCvtr;
}


public List<chifferByRisqRow> getListChiffrAf() {
	return listChiffrAf;
}


public void setListChiffrAf(List<chifferByRisqRow> listChiffrAf) {
	this.listChiffrAf = listChiffrAf;
}


public PieChartModel getModelrisq() {
	return modelrisq;
}


public void setModelrisq(PieChartModel modelrisq) {
	this.modelrisq = modelrisq;
}


public List<Risque> getSelectRisque() {
	return selectRisque;
}


public void setSelectRisque(List<Risque> selectRisque) {
	this.selectRisque = selectRisque;
}


public PieChartModel getModelPv() {
	return modelPv;
}


public void setModelPv(PieChartModel modelPv) {
	this.modelPv = modelPv;
}






}
	