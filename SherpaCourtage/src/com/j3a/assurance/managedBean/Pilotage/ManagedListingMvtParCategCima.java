package com.j3a.assurance.managedBean.Pilotage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.j3a.assurance.model.PointVente;
import com.j3a.assurance.model.Risque;
import com.j3a.assurance.model.Sinistre;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.utilitaires.IdGenerateur;
import com.j3a.assurance.utilitaires.hybride.SinistreResultQuery;
import com.j3a.assurance.utilitaires.hybride.SinistreResultQueryPvt;


@Component
@Scope("session")
@Transactional
public class ManagedListingMvtParCategCima  {

public  ManagedListingMvtParCategCima(){
	// TODO Auto-generated constructor stub
	}
	
	@Autowired
	ObjectService objectService;
	@Autowired
	private IdGenerateur idGenerateur;
	@Autowired
	private SessionFactory sessionFactory;
	
	private ManagedRequeteP managedRequeteP;
	
	private Date dateDeb;
	private Date datefin;
	//private CategorieCima categorieCima = new CategorieCima();
	private PointVente pointVente = new PointVente();
	private Sinistre sinistre = new Sinistre();
	private ArrayList<Risque> ListCategCima=new ArrayList<Risque>();
	private ArrayList<PointVente> ListPvt=new ArrayList<PointVente>();
	private List<PointVente> elements;
	private List<Risque> elementsCategCima;
	private List<SinistreResultQuery> listreqResult = new ArrayList<SinistreResultQuery>();
	private List<SinistreResultQueryPvt> listreqResultpvt=new ArrayList<SinistreResultQueryPvt>();
	private List<SinistreResultQuery> listreqResultpvtU=new ArrayList<SinistreResultQuery>();
	private String idPvt, idCategCima;
	private String pvtslect ="";
	private SinistreResultQueryPvt selectedListnistre = new SinistreResultQueryPvt();
	private SinistreResultQuery resultatReqGlobal = new SinistreResultQuery();
	private PieChartModel  pieModel1 = new PieChartModel();
	private PieChartModel  pieModel2 = new PieChartModel();
	Logger logs=Logger.getLogger(ManagedListingMvtParCategCima.class);
	
	public void req(){
		listreqResultpvtU.clear();
		listreqResultpvt.clear();
		listreqResult.clear();
		setPvtslect("");
		exeQuery();
		exeQueryPvt();
	}
	public void exeQuery(){
		String myQuery = "select SUM(sinistre.EVALUATION_BRUTE) as TotalBrute, SUM(sinistre.EVALUATION_NETTE) as TotalNette, risque.LIBELLE_RISQUE as libelle FROM sinistre, contrat, risque WHERE sinistre.NUM_POLICE=contrat.NUM_POLICE AND contrat.CODE_RISQUE=risque.CODE_RISQUE and sinistre.DATE_DECLARATION BETWEEN  '"+formatDate(getDateDeb()) +"' AND  '"+formatDate(getDatefin()) +"' GROUP BY risque.LIBELLE_RISQUE";
		List<SinistreResultQuery> rslt =  (List<SinistreResultQuery>) getSessionFactory().getCurrentSession().createSQLQuery(myQuery).addScalar("TotalBrute", StandardBasicTypes.BIG_DECIMAL).addScalar("totalNette", StandardBasicTypes.BIG_DECIMAL).addScalar("libelle", StandardBasicTypes.STRING) .setResultTransformer(Transformers.aliasToBean(SinistreResultQuery.class)).list();
		if (rslt.size()==0){
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"0", "Aucun Enregistrement!"));
		}
		else{
			System.out.println("taille::::::" +rslt.size());
			for(SinistreResultQuery a :rslt){
				listreqResult.add(a);		
			}
			createPieModel1();
		}
		
	}
	
	public void exeQueryPvt(){
		String myQuery="select SUM(sinistre.EVALUATION_BRUTE) as TotalBrute, SUM(sinistre.EVALUATION_NETTE)as TotalNette, point_vente.LIBELLE_POINT_VENTE as libellepvt FROM sinistre, contrat,point_vente WHERE sinistre.NUM_POLICE=contrat.NUM_POLICE AND contrat.CODE_POINT_VENTE=point_vente.CODE_POINT_VENTE and sinistre.DATE_DECLARATION BETWEEN  '"+formatDate(getDateDeb()) +"' AND  '"+formatDate(getDatefin()) +"' GROUP BY point_vente.LIBELLE_POINT_VENTE";
		List<SinistreResultQueryPvt> rslt =  (List<SinistreResultQueryPvt>) getSessionFactory().getCurrentSession().createSQLQuery(myQuery).addScalar("TotalBrute", StandardBasicTypes.BIG_DECIMAL).addScalar("totalNette", StandardBasicTypes.BIG_DECIMAL).addScalar("libellepvt", StandardBasicTypes.STRING) .setResultTransformer(Transformers.aliasToBean(SinistreResultQueryPvt.class)).list();
		if (rslt.size()==0){
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"0", "Aucun Enregistrement!"));
		}
		else{
			for(Iterator it=rslt.iterator();it.hasNext();){
				SinistreResultQueryPvt Pvt=(SinistreResultQueryPvt) it.next();
				listreqResultpvt.add(Pvt);		
			}
			createPieModel2();
		}
	}
	
	
	public void onRowSelect(SelectEvent event){
		listreqResultpvtU.clear();
		setSelectedListnistre((SinistreResultQueryPvt) event.getObject());
		setPvtslect(getSelectedListnistre().getLibellepvt());
		String myQuery = "select SUM(sinistre.EVALUATION_BRUTE) as TotalBrute, SUM(sinistre.EVALUATION_NETTE) as TotalNette, risque.LIBELLE_RISQUE as libelle FROM sinistre, contrat, risque, point_vente WHERE sinistre.NUM_POLICE=contrat.NUM_POLICE AND contrat.CODE_RISQUE=risque.CODE_RISQUE and contrat.CODE_POINT_VENTE=point_vente.CODE_POINT_VENTE and point_vente.LIBELLE_POINT_VENTE='"+getSelectedListnistre().getLibellepvt()+"' and sinistre.DATE_DECLARATION BETWEEN  '"+formatDate(getDateDeb()) +"' AND  '"+formatDate(getDatefin()) +"' GROUP BY risque.LIBELLE_RISQUE";
		List<SinistreResultQuery> rslt =  (List<SinistreResultQuery>) getSessionFactory().getCurrentSession().createSQLQuery(myQuery).addScalar("TotalBrute", StandardBasicTypes.BIG_DECIMAL).addScalar("totalNette", StandardBasicTypes.BIG_DECIMAL).addScalar("libelle", StandardBasicTypes.STRING) .setResultTransformer(Transformers.aliasToBean(SinistreResultQuery.class)).list();
		if (rslt.size()==0){
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"0", "Aucun Enregistrement!"));
		}
		else{
			for(Iterator it=rslt.iterator();it.hasNext();){
				SinistreResultQuery Pvt=(SinistreResultQuery) it.next();
				listreqResultpvtU.add(Pvt);		
			}
		}
		
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
	
//graphe
	private void createPieModel1() {
       
        for(SinistreResultQuery sin:listreqResult){
        	pieModel1.set(sin.getLibelle(), sin.getTotalNette());
        }
	}
	private void createPieModel2(){
		for(SinistreResultQueryPvt sin : listreqResultpvt){
			pieModel2.set(sin.getLibellepvt(), sin.getTotalNette());
		}
	}
	public void callGraphStat(){
		System.out.println("INSIDE callGraphStat////////////////////////////////////////");
		Map<String,Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		  options.put("draggable", false);
		  options.put("resizable", true);
		  options.put("contentHeight", 900);
		  options.put("contentWidth", 1300);
		
		RequestContext.getCurrentInstance().openDialog ("GraphSinistreRisque", options, null);
		
		//RequestContext.getCurrentInstance().execute("PF('Alert').show();");
		System.out.println("AFTER callGraphStat////////////////////////////////////////");
	}
	/*public CategorieCima getCategorieCima() {
		return categorieCima;
	}

	public void setCategorieCima(CategorieCima categorieCima) {
		this.categorieCima = categorieCima;
	}*/

	public PointVente getPointVente() {
		return pointVente;
	}

	public void setPointVente(PointVente pointVente) {
		this.pointVente = pointVente;
	}

	public ArrayList<Risque> getListCategCima() {
		return ListCategCima;
	}

	public void setListCategCima(ArrayList<Risque> listCategCima) {
		ListCategCima = listCategCima;
	}

	public ArrayList<PointVente> getListPvt() {
		return ListPvt;
	}

	public void setListPvt(ArrayList<PointVente> listPvt) {
		ListPvt = listPvt;
	}

	public List<PointVente> getElements() {
		
			elements = new ArrayList<PointVente>();
			
			List pvt=getObjectService().getObjects("PointVente");
			for(Iterator it=pvt.iterator();it.hasNext();){
				PointVente Pvt=(PointVente) it.next();
				elements.add(Pvt);	
			}
			
		return elements;
	}

	public void setElements(List<PointVente> elements) {
		this.elements = elements;
	}

	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}

	public ManagedRequeteP getManagedRequeteP() {
		return managedRequeteP;
	}

	public void setManagedRequeteP(ManagedRequeteP managedRequeteP) {
		this.managedRequeteP = managedRequeteP;
	}

	public IdGenerateur getIdGenerateur() {
		return idGenerateur;
	}

	public void setIdGenerateur(IdGenerateur idGenerateur) {
		this.idGenerateur = idGenerateur;
	}


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public Date getDateDeb() {
		return dateDeb;
	}


	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}


	


	public Date getDatefin() {
		return datefin;
	}


	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}


	public List<Risque> getElementsCategCima() {
		
			elementsCategCima = new ArrayList<Risque>();
			
			List categ=getObjectService().getObjects("Risque");
			for(Iterator it=categ.iterator();it.hasNext();){
				Risque categC=(Risque) it.next();
				elementsCategCima.add(categC);	
			}
			
		return elementsCategCima;
	}

	public void setElementsCategCima(List<Risque> elementsCategCima) {
		this.elementsCategCima = elementsCategCima;
	}

	public String getIdPvt() {
		return idPvt;
	}

	public void setIdPvt(String idPvt) {
		this.idPvt = idPvt;
	}

	public String getIdCategCima() {
		return idCategCima;
	}

	public void setIdCategCima(String idCategCima) {
		this.idCategCima = idCategCima;
	}

	public Sinistre getSinistre() {
		return sinistre;
	}

	public void setSinistre(Sinistre sinistre) {
		this.sinistre = sinistre;
	}

	public String getPvtslect() {
		return pvtslect;
	}

	public void setPvtslect(String pvtslect) {
		this.pvtslect = pvtslect;
	}

	public SinistreResultQuery getResultatReqGlobal() {
		return resultatReqGlobal;
	}

	public void setResultatReqGlobal(SinistreResultQuery resultatReqGlobal) {
		this.resultatReqGlobal = resultatReqGlobal;
	}

	public List<SinistreResultQuery> getListreqResult() {
		return listreqResult;
	}

	public void setListreqResult(ArrayList<SinistreResultQuery> listreqResult) {
		this.listreqResult = listreqResult;
	}

	public List<SinistreResultQueryPvt> getListreqResultpvt() {
		return listreqResultpvt;
	}

	public void setListreqResultpvt(ArrayList<SinistreResultQueryPvt> listreqResultpvt) {
		this.listreqResultpvt = listreqResultpvt;
	}
	public SinistreResultQueryPvt getSelectedListnistre() {
		return selectedListnistre;
	}
	public void setSelectedListnistre(SinistreResultQueryPvt selectedListnistre) {
		this.selectedListnistre = selectedListnistre;
	}
	public List<SinistreResultQuery> getListreqResultpvtU() {
		return listreqResultpvtU;
	}
	public void setListreqResultpvtU(ArrayList<SinistreResultQuery> listreqResultpvtU) {
		this.listreqResultpvtU = listreqResultpvtU;
	}
	public PieChartModel getPieModel1() {
		return pieModel1;
	}
	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}
	public PieChartModel getPieModel2() {
		return pieModel2;
	}
	public void setPieModel2(PieChartModel pieModel2) {
		this.pieModel2 = pieModel2;
	}
	public void setListreqResult(List<SinistreResultQuery> listreqResult) {
		this.listreqResult = listreqResult;
	}
	public void setListreqResultpvt(List<SinistreResultQueryPvt> listreqResultpvt) {
		this.listreqResultpvt = listreqResultpvt;
	}
	public void setListreqResultpvtU(List<SinistreResultQuery> listreqResultpvtU) {
		this.listreqResultpvtU = listreqResultpvtU;
	}


	
}
