package com.j3a.assurance.managedBean.Pilotage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.j3a.assurance.model.Avenant;
import com.j3a.assurance.model.PointVente;
import com.j3a.assurance.model.ReglementSinistreGc;
import com.j3a.assurance.model.Risque;
import com.j3a.assurance.model.Sinistre;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.utilitaires.ClientPV;
import com.j3a.assurance.utilitaires.SinistreparPV;
import com.j3a.assurance.utilitaires.nbrebyrisquesinistre;
import com.j3a.assurance.utilitaires.nbrerisquesinistrebyPV;

@Component
@Scope("session")
@Transactional
public class ManagedListSinistres {

	private static final Logger logger = Logger.getLogger(ManagedListSinistres.class.getName());
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	ObjectService objectService;
	
	private Date dateDeb, dateFin;
	private List<Avenant> listeAvenant;
	private List<Sinistre> listesinistres;
	private List<SinistreparPV> listesinistrestriee;
	private List<ClientPV> listClientPVs;
	private List listPointVente;
	private List listRisque;
	private List<PointVente> selectedPointVente;
	private List<Risque> selectedRisque;
	private List<nbrerisquesinistrebyPV> listnbrerisqbypt;
	
	public List<nbrerisquesinistrebyPV> getListnbrerisqbypt() {
		return listnbrerisqbypt;
	}

	public void setListnbrerisqbypt(List<nbrerisquesinistrebyPV> listnbrerisqbypt) {
		this.listnbrerisqbypt = listnbrerisqbypt;
	}

	private PieChartModel modelrisqPV1= new PieChartModel();
	private PieChartModel modelrisqPV2= new PieChartModel();
	
	private final String requetesinistret = "SELECT A.* FROM  sinistre A JOIN contrat C ON C.NUM_POLICE = A.NUM_POLICE JOIN  point_vente P ON P.CODE_POINT_VENTE = C.CODE_POINT_VENTE JOIN  risque R ON R.CODE_RISQUE = C.CODE_RISQUE  WHERE ";

	
	@PostConstruct 
	public void init(){
		//Charger la liste des point de vente
	listPointVente = getObjectService().getObjects("PointVente");
	//Charger la liste des risque
	listRisque = getObjectService().getObjects("Risque");
	}
	
	public void REcherchSinistre() {
		// TODO
		String Query = requetesinistret + perioderecherche();

		String a = qryPtvtePart();
		if (a != ""){
			Query = Query + " AND " + a;
		}
		String b = qryRisqPart();
		if (b != ""){
		    Query = Query + " AND " + b;
		}
			
		listesinistres = getSessionFactory().getCurrentSession().createSQLQuery(Query).addEntity(Sinistre.class).list();
		chargerSinistre();
		
	}
	
	public void chargerSinistre()
	{
		
		listesinistrestriee = new ArrayList<SinistreparPV>() ;
	
		String libellepv;
		String libeller;
		List<SinistreparPV> sinppv = new ArrayList<SinistreparPV>();
		BigDecimal REgl = BigDecimal.valueOf(0);
		BigDecimal REmb = BigDecimal.valueOf(0);
		for(Sinistre pv: listesinistres)
		{
			SinistreparPV spv = new SinistreparPV();
			libellepv = getpoitvente (pv.getCodeSinistre()).getLibellePointVente() ;
			spv.setPointVente(libellepv);
			spv.setSinistre(pv.getCodeSinistre());
			libeller = getRisque(pv.getCodeSinistre()).getLibelleRisque();
			spv.setRisque(libeller);
			
			listesinistrestriee.add(spv);
			
			/////////////////////////////////////////////////////////////////
			List<ReglementSinistreGc> ll;
			ll = getReglementSinistreGc(pv.getCodeSinistre());
			
			for(ReglementSinistreGc rsg: ll)
			{
				REgl = REgl.add(rsg.getMontantReglement());
				REmb = REmb.add(rsg.getMontantRemboursement());
			}
			spv.setReglement(REgl.toString());
			spv.setRemboursement(REmb.toString());
			REgl.valueOf(0);
			REmb.valueOf(0);
		}
		getnbrerisqueinistreparPV();
		createPieModel1();

	}
	

	public String qryPeriodPart() {
		String X = "";
			X = X + "A.EFFET BETWEEN '" + formatDate(getDateDeb()) + "' AND '"+ formatDate(getDateFin()) + "'";
		return X;
	}
	public String perioderecherche() {
		String X = "";
			X = X + "A.DATE_SURVENANCE BETWEEN '" + formatDate(getDateDeb()) + "' AND '"+ formatDate(getDateFin()) + "'";
		return X;
	}
	
	public String qryPtvtePart() {
		String X = "";
		if (!getSelectedPointVente().isEmpty()) {
			X = " P.CODE_POINT_VENTE IN (";
			for (PointVente p : getSelectedPointVente()) {
				X = X + "'" + p.getCodePointVente() + "',";
			}
			X = X.substring(0, X.length() - 1) + ") ";
		}
		return X;
	}
	
	public String qryRisqPart() {
		String X = "";
		if (!getSelectedRisque().isEmpty()) {
			X = " R.CODE_RISQUE IN (";
			for (Risque r : getSelectedRisque()) {
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
	
	
	public PointVente getpoitvente (String idsinistre)  {
		List list = getSessionFactory()
				.getCurrentSession()
				.createSQLQuery(
						" select P.* from  point_vente P JOIN contrat C ON P.CODE_POINT_VENTE = C.CODE_POINT_VENTE JOIN sinistre A ON C.NUM_POLICE = A.NUM_POLICE"
						+ " where A.CODE_SINISTRE = '"+idsinistre
								+"'").addEntity(PointVente.class).list();
		return (PointVente) list.get(0);
}
	public Risque getRisque (String idsinistre)  {
		List list = getSessionFactory()
				.getCurrentSession()
				.createSQLQuery(
						"SELECT R.* FROM  risque R JOIN contrat C ON  R.CODE_RISQUE = C.CODE_RISQUE JOIN sinistre A ON C.NUM_POLICE = A.NUM_POLICE" 
								+" where A.CODE_SINISTRE = '"+idsinistre
								+"'").addEntity(Risque.class).list();
		return (Risque) list.get(0);
}


	public List<ReglementSinistreGc> getReglementSinistreGc (String idsinistre)  {
		List list = getSessionFactory()
				.getCurrentSession()
				.createSQLQuery(
						"SELECT R.* FROM  reglement_sinistre_gc R JOIN sinistre A ON R.CODE_SINISTRE = A.CODE_SINISTRE" 
								+" where A.CODE_SINISTRE = '"+idsinistre
								+"'").addEntity(ReglementSinistreGc.class).list();
		return  list;
}
	public void getnbrerisqueinistreparPV()
	{
		List<nbrerisquesinistrebyPV> LL = new ArrayList();
		for(PointVente pv : selectedPointVente)
		{
		List<nbrebyrisquesinistre> LN = new ArrayList();
		nbrerisquesinistrebyPV NRBPV = new nbrerisquesinistrebyPV();
		NRBPV.setPtVte(pv);
		for(Risque r : selectedRisque)
		{
			nbrebyrisquesinistre nrs = new nbrebyrisquesinistre();
			nrs.setRisque(r);
			String qry= "SELECT COUNT(A.CODE_SINISTRE) AS SOMME FROM  sinistre A JOIN contrat C ON C.NUM_POLICE = A.NUM_POLICE JOIN  point_vente P ON P.CODE_POINT_VENTE = C.CODE_POINT_VENTE JOIN  risque R ON R.CODE_RISQUE = C.CODE_RISQUE  WHERE "
					+ " R.CODE_RISQUE = '"+r.getCodeRisque()+"' AND P.CODE_POINT_VENTE = '"+pv.getCodePointVente()+"' and A.DATE_SURVENANCE BETWEEN '" + formatDate(getDateDeb()) + "' AND '"+ formatDate(getDateFin()) + "'";
			java.math.BigInteger t = (java.math.BigInteger) getSessionFactory().getCurrentSession().createSQLQuery(qry).addScalar("SOMME").uniqueResult();
			nrs.setNombre(t);
			LN.add(nrs);
		}
		NRBPV.setListCAandRisq(LN);
		LL.add(NRBPV);
		}
		listnbrerisqbypt = LL;
	}
	public void callGraphStat(){
		System.out.println("INSIDE callGraphStat////////////////////////////////////////");
		Map<String,Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", true);
		options.put("contentHeight", 900);
		options.put("contentWidth", 1300);
		
		RequestContext.getCurrentInstance().openDialog ("graphSinistre", options, null);
		
		//RequestContext.getCurrentInstance().execute("PF('Alert').show();");
		System.out.println("AFTER callGraphStat////////////////////////////////////////");
		
	}
	
	private void createPieModel1() {
		setModelrisqPV1(new PieChartModel());
		setModelrisqPV2(new PieChartModel());
			for (nbrerisquesinistrebyPV r : listnbrerisqbypt) {
				if(r.getPtVte().getCodePointVente().equals("PV001"))
					{
						for(nbrebyrisquesinistre nn: r.getListCAandRisq())
						{
							modelrisqPV1.set(nn.getRisque().getLibelleRisque(),nn.getNombre());
						}
					}
				else
					{
						for(nbrebyrisquesinistre nn: r.getListCAandRisq())
						{
								
							modelrisqPV2.set(nn.getRisque().getLibelleRisque(),nn.getNombre());
						}
					}
			}
		}
	/**********ACCESSEURS************************/
public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}


	public List getListPointVente() {
		return listPointVente;
	}

	public void setListPointVente(List listPointVente) {
		this.listPointVente = listPointVente;
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

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public List getListRisque() {
		return listRisque;
	}

	public void setListRisque(List listRisque) {
		this.listRisque = listRisque;
	}

	public List<PointVente> getSelectedPointVente() {
		return selectedPointVente;
	}

	public void setSelectedPointVente(List<PointVente> selectedPointVente) {
		this.selectedPointVente = selectedPointVente;
	}

	public List<Risque> getSelectedRisque() {
		return selectedRisque;
	}

	public void setSelectedRisque(List<Risque> selectedRisque) {
		this.selectedRisque = selectedRisque;
	}

	public List<ClientPV> getListClientPVs() {
		return listClientPVs;
	}

	public void setListClientPVs(List<ClientPV> listClientPVs) {
		this.listClientPVs = listClientPVs;
	}

	public List<Avenant> getListeAvenant() {
		return listeAvenant;
	}

	public void setListeAvenant(List<Avenant> listeAvenant) {
		this.listeAvenant = listeAvenant;
	}


	public List<Sinistre> getListesinistres() {
		return listesinistres;
	}

	public void setListesinistres(List<Sinistre> listesinistres) {
		this.listesinistres = listesinistres;
	}

	public List<SinistreparPV> getListesinistrestriee() {
		return listesinistrestriee;
	}

	public void setListesinistrestriee(List<SinistreparPV> listesinistrestriee) {
		this.listesinistrestriee = listesinistrestriee;
	}

	public PieChartModel getModelrisqPV1() {
		return modelrisqPV1;
	}

	public void setModelrisqPV1(PieChartModel modelrisqPV1) {
		this.modelrisqPV1 = modelrisqPV1;
	}

	public PieChartModel getModelrisqPV2() {
		return modelrisqPV2;
	}

	public void setModelrisqPV2(PieChartModel modelrisqPV2) {
		this.modelrisqPV2 = modelrisqPV2;
	}

}
