package com.j3a.assurance.managedBean.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.print.attribute.standard.Severity;

import org.apache.log4j.Logger;
import org.primefaces.component.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;




import org.springframework.stereotype.Component;

import com.j3a.assurance.model.SocieteAssurance;
import com.j3a.assurance.model.Utilisateur;
import com.j3a.assurance.objetService.ObjectService;

@Component
@Scope("session")
public class ManagedSocieteAssurance implements Serializable {

	private static final long serialVersionUID = 1L;
	private static  Logger logger=Logger.getLogger(ManagedSocieteAssurance.class);
	// private static final String SUCCESS = "success";
	// private static final String ERROR = "error";
	@Autowired
	ObjectService objectService;
	

	private String id;
	private String abrege;
	private String nomSocieteAssurance;
	private String adressePostale;
	private String deviseUtilise;
	private List<SelectItem> elements;
	private List<SocieteAssurance> SocieteAssuranceList;

	private UIComponent buttonadd;
	private UIComponent buttonupdate;
	private UIComponent buttondelete;
	private SocieteAssurance societeAssuranceMod = new SocieteAssurance();

	public List<String> complete(String query) {
		List<String> results = new ArrayList<String>();

		for (int i = 0; i < 10; i++) {
			results.add(query + i);
		}

		return results;
	}

	public String validateadd() {// ajouter une ligne dans la base de donnée
		// vérifier que la société est déjà renseignée
		if(getObjectService().getObjects("SocieteAssurance").size()>0){
	        reset();
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
	        	(FacesMessage.SEVERITY_ERROR,"Société déjà renseignée", ""));
		}
		else{
			String key = getObjectService().getCodeTable("STE", 3, 2,"societe_assurance", "CODE_SOCIETE_ASSURANCE");
			//Reconnaitre l'utilisateur connecté avec la session
			String utilisateur = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
//Renseigner les info de la société d'asurance
			SocieteAssurance societe = new SocieteAssurance();
			societe.setCodeSocieteAssurance(key);
			societe.setNomSocieteAssurance(getNomSocieteAssurance());
			societe.setAbrege(getAbrege());
			societe.setAdressePostale(getAdressePostale());
			societe.setDeviseUtilise(getDeviseUtilise());
			FacesMessage message = new FacesMessage("");
		//	boolean checkeixte = false;
				try {
					//Enregistrement en base
					getObjectService().addObject(societe);
					
					
					logger.info("*****Enredistremen de la société d'assurance: "+getNomSocieteAssurance()+
							" par l'utilisateur "+utilisateur);
					message = new FacesMessage(" Societé enregistré!");
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(buttonadd.getClientId(context), message);
					reset();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.error("***** Erreur lors de l'enregistrement de la société d'assurance:"+key+", nom: "+getNomSocieteAssurance()+"******", e);
					e.printStackTrace();
				}
			
		}
	    
		return null;
	}

	public String validateupdate() { // Reprise by ALekerand
		// supprimer une ligne dans la base de donnée
		FacesMessage message = new FacesMessage("");
		//renseigner les info à modifier
		societeAssuranceMod.setNomSocieteAssurance(getNomSocieteAssurance());
		societeAssuranceMod.setAbrege(getAbrege());
		societeAssuranceMod.setAdressePostale(getAdressePostale());
		societeAssuranceMod.setDeviseUtilise(getDeviseUtilise());
		
		//Reconnaitre l'utilisateur connecté avec la session
		String utilisateur = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
		//Enregistrement en base
			try {
				getObjectService().updateObject(getSocieteAssuranceMod());
				
				message = new FacesMessage(" Modfication effectuée!");
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(buttonupdate.getClientId(context), message);
				logger.info("*******Modification de la société d'assurance par "+utilisateur+" **********");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("Erreur lors de la modification de la société", e);
			}

		return null;

	}
	
	

	public String validatedelete() {// supprimer une ligne dans la base de
									// donnée
		FacesMessage message = new FacesMessage("");
			try {
				getObjectService().deleteObject(getSocieteAssuranceMod());
				message = new FacesMessage("  suppression effectuée!");
			} catch (org.springframework.dao.DataIntegrityViolationException e) {
				// TODO Auto-generated catch block
				logger.error("*******Tentative de suppression de la Société d'assurance*******", e);
			}
	
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(buttondelete.getClientId(context), message);
		return null;

	}


	
	public void chargerSelection(){// By ALekerand
		this.nomSocieteAssurance = societeAssuranceMod.getNomSocieteAssurance();
		this.abrege = societeAssuranceMod.getAbrege();
		this.adressePostale = societeAssuranceMod.getAdressePostale();
		this.deviseUtilise = societeAssuranceMod.getAdressePostale();
	}
	
	
	public List<SocieteAssurance> getSocieteAssuranceList() {
		SocieteAssuranceList = new ArrayList<SocieteAssurance>();
		List<Object> listObject = getObjectService().getObjects(
				"SocieteAssurance");
		for (Iterator it = listObject.iterator(); it.hasNext();) {
			SocieteAssurance societe = (SocieteAssurance) it.next();
			try {
				SocieteAssuranceList.add(societe);
			} catch (Exception e) {
			}

		}
		return SocieteAssuranceList;
	}

	public void setSocieteAssuranceList(List<SocieteAssurance> SocieteAssuranceList) {
		this.SocieteAssuranceList = SocieteAssuranceList;
	}

	public List<SelectItem> getElements() {

		if (elements == null) {
			elements = new ArrayList<SelectItem>();
			for (Object obj : getObjectService().getObjects("SocieteAssurance")) {

				elements.add(new SelectItem(((SocieteAssurance) obj).getCodeSocieteAssurance(),
						((SocieteAssurance) obj).getNomSocieteAssurance()));

			}
		}
		return elements;
	}

	public void setElements(List<SelectItem> elements) {
		this.elements = elements;
	}

	/**
	 * Reset Fields
	 * 
	 */
	public void reset() {
		this.setId("");
		this.setNomSocieteAssurance("");
		this.setAbrege("");
		this.setAdressePostale("");
		this.setDeviseUtilise("");
		
	}
	
	



	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id=id;
	}

	public void setButtonadd(UIComponent buttonadd) {
		this.buttonadd = buttonadd;
	}

	public UIComponent getButtonadd() {
		return buttonadd;
	}

	public UIComponent getButtonupdate() {
		return buttonupdate;
	}

	public void setButtonupdate(UIComponent buttonupdate) {
		this.buttonupdate = buttonupdate;
	}

	public UIComponent getButtondelete() {
		return buttondelete;
	}

	public void setButtondelete(UIComponent buttondelete) {
		this.buttondelete = buttondelete;
	}

	public String getNomSocieteAssurance() {
		return nomSocieteAssurance;
	}

	public void setNomSocieteAssurance(String nomSocieteAssurance) {
		this.nomSocieteAssurance = nomSocieteAssurance;
	}

	public String getAbrege() {
		return abrege;
	}

	public void setAbrege(String abrege) {
		this.abrege = abrege;
	}

	public String getAdressePostale() {
		return adressePostale;
	}

	public void setAdressePostale(String adressePostale) {
		this.adressePostale = adressePostale;
	}

	public String getDeviseUtilise() {
		return deviseUtilise;
	}

	public void setDeviseUtilise(String deviseUtilise) {
		this.deviseUtilise = deviseUtilise;
	}

	public SocieteAssurance getSocieteAssuranceMod() {
		return societeAssuranceMod;
	}

	public void setSocieteAssuranceMod(SocieteAssurance societeAssuranceMod) {
		this.societeAssuranceMod = societeAssuranceMod;
	}

}


