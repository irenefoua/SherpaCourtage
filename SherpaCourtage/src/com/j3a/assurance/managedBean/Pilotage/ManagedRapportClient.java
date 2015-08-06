package com.j3a.assurance.managedBean.Pilotage;

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
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.j3a.assurance.model.Avenant;
import com.j3a.assurance.model.PointVente;
import com.j3a.assurance.model.Risque;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.utilitaires.ClientPV;


/**
 * 
 * @author J3A-ALekerand
 *
 */
@Transactional
@Component
public class ManagedRapportClient {
	
	private static final Logger logger = Logger.getLogger(ManagedRapportClient.class.getName());
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	ObjectService objectService;
	
	private Date dateDeb, dateFin;
	private List<Avenant> listeAvenant;
	private List<ClientPV> listClientPVs;
	private List listPointVente;
	private List listRisque;
	private List<PointVente> selectedPointVente;
	private List<Risque> selectedRisque;
	private PieChartModel pieChartModel = new PieChartModel();
	private final String qryCmnPart = "SELECT A.* FROM  avenant A JOIN contrat C ON C.NUM_POLICE = A.NUM_POLICE JOIN  point_vente P ON P.CODE_POINT_VENTE = C.CODE_POINT_VENTE JOIN  risque R ON R.CODE_RISQUE = C.CODE_RISQUE  WHERE ";

	
	@PostConstruct 
	public void init(){
		//Charger la liste des point de vente
	listPointVente = getObjectService().getObjects("PointVente");
	//Charger la liste des risque
	listRisque = getObjectService().getObjects("Risque");
		
	}
	
	public void submitCriterias() {
		// TODO
		String myQuery = qryCmnPart + qryPeriodPart();

		String a = qryPtvtePart();
		if (a != "")
			myQuery = myQuery + " AND " + a;

		String b = qryRisqPart();
		if (b != "")
			myQuery = myQuery + " AND " + b;
			
		myQuery = myQuery + " AND " + " A.MOUVEMENT IN ('Affaire Nouvelle','Renouvellement')";
		
		listeAvenant = getSessionFactory().getCurrentSession().createSQLQuery(myQuery).addEntity(Avenant.class).list();
		chargerListClientPV();		
	}
	
	public void chargerListClientPV(){
		pieChartModel.clear();
		List<Avenant> listAfN = new ArrayList<Avenant>();
		List<Avenant> listRenouv = new ArrayList<Avenant>();
		listClientPVs = new ArrayList<ClientPV>();
		for(Avenant av:listeAvenant){
			if(av.getMouvement().equalsIgnoreCase("Affaire Nouvelle")){
				listAfN.add(av);
				
			}else {
				listRenouv.add(av);
			}	
		}
		
		//Gerer la partie Renouvellement
		for(PointVente pv: selectedPointVente){
			
			ClientPV clientPVAF = new ClientPV();
			ClientPV clientPVRENOUV = new ClientPV();
			
			//Partie Affaire nouvelle
			clientPVAF.setPointVente(pv.getLibellePointVente());

			clientPVAF.setTotalSouscripteur(0);
			clientPVAF.setMouvement("Affaire Nouvelle");
			
			//Partie Affaire nouvelle
			clientPVRENOUV.setPointVente(pv.getLibellePointVente());
			clientPVRENOUV.setTotalSouscripteur(0);
			clientPVRENOUV.setMouvement("Renouvellement");
			
				//Gestion de la partie Affaire nouvelle
			for(Avenant av: listAfN){
					//Vérifier que le point de vente correspont à celui que nous manipulons
				if(av.getContrat().getPointVente().equals(pv)){
					clientPVAF.setTotalSouscripteur(clientPVAF.getTotalSouscripteur()+1);
				}	
			}
			//Ajouter l'élément à la liste des clientPV
			listClientPVs.add(clientPVAF);
			//Elaboration du graph
			pieChartModel.set(pv.getLibellePointVente()+": AFFAIRE NOUVELLE", clientPVAF.getTotalSouscripteur());
			
			//Gestion de la partie Renouvellement
			for(Avenant av: listRenouv){
					//Vérifier que le point de vente correspont à celui que nous manipulons
				if(av.getContrat().getPointVente().equals(pv)){
					clientPVRENOUV.setTotalSouscripteur(clientPVRENOUV.getTotalSouscripteur()+1);
				}	
			}
			//Ajouter l'élément à la liste des clientPV
			listClientPVs.add(clientPVRENOUV);
			pieChartModel.set(pv.getLibellePointVente()+" RENOUVELLEMNT", clientPVRENOUV.getTotalSouscripteur());
	}		
	}
	
	public void viewCarsCustomized() {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", false);
        options.put("contentHeight", 320);
        RequestContext.getCurrentInstance().openDialog("graphSouscripteur", options, null);
    }
	
	
	
	
	
	
	public String qryPeriodPart() {
		String X = "";
			X = X + "A.EFFET BETWEEN '" + formatDate(getDateDeb()) + "' AND '"+ formatDate(getDateFin()) + "'";
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

	public PieChartModel getPieChartModel() {
		return pieChartModel;
	}

	public void setPieChartModel(PieChartModel pieChartModel) {
		this.pieChartModel = pieChartModel;
	}

}
