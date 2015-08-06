package com.j3a.assurance.managedBean.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.Risque;
import com.j3a.assurance.objetService.ObjectService;

@Component
public class ManagedRisque implements Serializable{
	private static final long serialVersionUID = 1L;
	private static  Logger logger=Logger.getLogger(ManagedRisque.class);
	
	 @Autowired
	 ObjectService objectService;

	 private String id;
	 private String libelleRisque;
	 
	 private List<SelectItem> elements;
	 private List<Risque> RisqueList;
	 
	 private UIComponent buttonadd;
	 private UIComponent buttonupdate;
	 private UIComponent buttondelete;
	 private Risque risqueSup = new Risque();
	 
	 
	

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
		this.id = id;
	}

	public String getLibelleRisque() {
		return libelleRisque;
	}

	public void setLibelleRisque(String libelleRisque) {
		this.libelleRisque = libelleRisque;
	}

	public List<Risque> getRisqueList() {	
		RisqueList = new ArrayList<Risque>();
		List<Object> listObject = getObjectService().getObjects("Risque");			
		for (Iterator it = listObject.iterator(); it.hasNext();) {
			Risque risque = (Risque) it.next();
			try {
				RisqueList.add(risque);
			} catch (Exception e) {
			}
	
		}
		return RisqueList;
	}		

	public void setRisqueList(List<Risque> risqueList) {
		RisqueList = risqueList;
	}
	
	public List<SelectItem> getElements() {
		
		if (elements == null) {
			 elements = new ArrayList<SelectItem>();
			 for (Object obj : getObjectService().getObjects("Risque")) {
		
		        	elements.add(new SelectItem(  ((Risque) obj).getCodeRisque(),   ((Risque) obj).getLibelleRisque()));
		        
			 }
		    }
		return elements;
	}

	public void setElements(List<SelectItem> elements) {
		this.elements = elements;
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
	
	/**
	  * Reset Fields
	  *
	  */
	public void reset() {
		this.setId("");
		this.setLibelleRisque("");
	}
	
	public String validateadd() { //Réprise By ALekerand
	    String key = getObjectService().getCodeTable("RISQUE", 5, 3,"risque", "CODE_RISQUE");
		Risque risque = new Risque();
		risque.setCodeRisque(key);
	    risque.setLibelleRisque(getLibelleRisque());
	    
	    //Enregistrement en base
	    getObjectService().addObject(risque);
	    viderRisque();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
        		(FacesMessage.SEVERITY_INFO,"Gestion des risque", "Risque enregistré!"));  
        
       // org.primefaces.context.RequestContext.getCurrentInstance().update("growl");
        System.out.println("Enregistremen ok!");//Clean after
		return null;

	}
	
	
	public void affecterRisque(){
		setLibelleRisque(getRisqueSup().getLibelleRisque());
		
	}
	
	
	public void viderRisque(){
		setLibelleRisque("");
	}
	
	
	public String validateupdate() {
		risqueSup.setLibelleRisque(libelleRisque);
		getObjectService().updateObject(risqueSup);
		viderRisque();
		 FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,"Succès"," Modification efféctué");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
	}
	
	   
	public void validatedelete()  {// Reprise By ALekerand
		try {
			getObjectService().deleteObject(risqueSup);
		} catch ( org.springframework.dao.DataIntegrityViolationException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.info("Tentative de suppression du risque lié à des contact:"+getRisqueSup().getCodeRisque());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
	        		(FacesMessage.SEVERITY_ERROR,"Erreur", "Ce risque ne peut être suprimé"));
		}
		viderRisque();
	}

	public Risque getRisqueSup() {
		return risqueSup;
	}

	public void setRisqueSup(Risque risqueSup) {
		this.risqueSup = risqueSup;
	}
}
