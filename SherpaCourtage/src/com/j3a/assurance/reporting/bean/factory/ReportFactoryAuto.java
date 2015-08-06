package com.j3a.assurance.reporting.bean.factory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.Avenant;
import com.j3a.assurance.model.Contrat;
import com.j3a.assurance.model.HistoMouvement;
import com.j3a.assurance.model.Morale;
import com.j3a.assurance.model.Personne;
import com.j3a.assurance.model.Physique;
import com.j3a.assurance.model.Quittance;
import com.j3a.assurance.model.SocieteAssurance;
import com.j3a.assurance.model.VehiculesAssures;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.reporting.bean.ReportingAuto;

@Component
public class ReportFactoryAuto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ReportFactoryAuto.class);
	private boolean etatcontrat = false;
	//Injection Spring
	@Autowired
	ObjectService objectService;
	@Autowired
	ReportingAuto reportingAuto;
	
		
	//Attibut d'instance
	private String idQuittance;
	
	
	
	public ReportingAuto reportProvider(){
		//Recuperation de la societé d'assurance
		getReportingAuto().setSocieteAssurance((SocieteAssurance) objectService.getObjects("SocieteAssurance").get(0));
		// recueration de la quittance par l'id
			Quittance quittance = (Quittance) getObjectService().getObjectById(getIdQuittance(), "Quittance");
			if(quittance!=null){
				if(quittance.getAvenant().getContrat().getRisque().getCodeRisque().equalsIgnoreCase("1")){
					
					getReportingAuto().setQuittance(quittance);
					
					// recuperation de l'avenant
						Avenant avenant = quittance.getAvenant();
						getReportingAuto().setAvenant(avenant);
						
					// recuperation du contrat
					   Contrat contrat = avenant.getContrat();
					   getReportingAuto().setContrat(contrat);
						   
					// recuperation de l'agence
					   getReportingAuto().setPointVente(contrat.getPointVente());
					   			  
					// recuperation de la personne
						Personne personne = contrat.getPersonne();
						getReportingAuto().setPersonne(personne);
						
						getReportingAuto().setPersonne(personne);
						// peuplement des attributs relatifs souscripteur du bean quittance
						if (personne.getPhysique()!=null) {
							Physique physique = (Physique) getObjectService().getObjectById(personne.getNumSouscripteur(), "Personne");
							getReportingAuto().setPhysique((physique));
							getReportingAuto().setNom(physique.getNomRaisonSociale()+" "+physique.getPrenomPers());

						}else{
							Morale morale = (Morale) getObjectService().getObjectById(personne.getNumSouscripteur(), "Morale");
							getReportingAuto().setMorale(morale);
							getReportingAuto().setNom(morale.getNomRaisonSociale());
						}
						
							
						//recupération du risque du contrat
						getReportingAuto().setRisque(contrat.getRisque());
						
						//Recuperation du vehicule Assuré
						List vehiculeAssureList = new ArrayList<>();
					//	VehiculesAssures vehiculesAssures = (VehiculesAssures) vehiculeAssureList.get(0);
						VehiculesAssures vehiculesAssures = avenant.getVehiculesAssures();
						List listeHistoMouvement = new ArrayList<>();
					//	listeHistoMouvement
						
						
						for(HistoMouvement histoMouvement : getObjectService().recuperLisHistoMouvement(avenant.getNumAvenant())){
							getReportingAuto().getListVehiculeAssure().add(histoMouvement.getVehicule());
						}
				return reportingAuto;
				}else{
					
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"la quittance n'est pas une quittance Auto", "Echec"));	
					return null;	
				}
			}else{
			
				FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"la quittance n'existe pas", "Echec"));
				return null;
			}
			
		
		
	}
	
		  
/***********************Accesseurs**********************/
	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}

	public String getIdQuittance() {
		return idQuittance;
	}

	public void setIdQuittance(String idQuittance) {
		this.idQuittance = idQuittance;
	}


	public ReportingAuto getReportingAuto() {
		return reportingAuto;
	}

	public void setReportingAuto(ReportingAuto reportingAuto) {
		this.reportingAuto = reportingAuto;
	}
	

	public boolean isEtatcontrat() {
		return etatcontrat;
	}


	public void setEtatcontrat(boolean etatcontrat) {
		this.etatcontrat = etatcontrat;
	}

}
