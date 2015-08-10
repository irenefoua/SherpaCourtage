package com.j3a.assurance.managedBean.adminCompagnie;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.CompagnieAssurance;
import com.j3a.assurance.objetService.ObjectService;


@Component
public class ManagedCompagnie implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CompagnieAssurance compagnieAssurance;
	@Autowired
	private ObjectService objectService;
	
	
	
	public void ajouterCompagnie(){
		
	try {
		getObjectService().addObject(getCompagnieAssurance());
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Enregistrement éffectué avec succès!"));
	} catch (Exception e) {
				e.printStackTrace();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Enregistrement non éffectué!"));
	}	
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ObjectService getObjectService() {
		return objectService;
	}


















	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}


















	public CompagnieAssurance getCompagnieAssurance() {
		return compagnieAssurance;
	}
	public void setCompagnieAssurance(CompagnieAssurance compagnieAssurance) {
		this.compagnieAssurance = compagnieAssurance;
	}

}
