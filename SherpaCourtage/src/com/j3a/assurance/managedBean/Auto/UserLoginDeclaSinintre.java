package com.j3a.assurance.managedBean.Auto;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.j3a.assurance.model.Personne;
import com.j3a.assurance.objetService.ObjectService;

/**
 * 
 * @author PCALekerand
 *
 */
@Component
public class UserLoginDeclaSinintre {
	
	private String login;
	private String motPass;
	
	//Injection spring
	@Autowired
	private ObjectService objectService;
	private String validation;
	

@Transactional
	public String loginConnexion() {
	validation="";
		FacesMessage message = null;
			 Personne personne = new Personne();
			personne = getObjectService().personneByLogin(login, motPass);
			if (personne == null) {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Désolé! Paramètres de connexion incorrectes","");
				FacesContext.getCurrentInstance().addMessage(null, message);
			}else {
				validation="declarationSinnistreAuto";
				System.out.println(validation);
			}
			return validation;
    }  
	
	
	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}


	public String getMotPass() {
		return motPass;
	}


	public void setMotPass(String motPass) {
		this.motPass = motPass;
	}
	
	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}
   
}
