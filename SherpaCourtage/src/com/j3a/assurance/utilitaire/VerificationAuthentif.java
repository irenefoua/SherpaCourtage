package com.j3a.assurance.utilitaire;

import javax.faces.view.facelets.FaceletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.Personne;
import com.j3a.assurance.model.Physique;
import com.j3a.assurance.objetService.ObjectService;
import com.sun.xml.internal.ws.client.RequestContext;

@Component
public class VerificationAuthentif {
	/**
	 * @author ALekerand
	 */
	
	 @Autowired
	 ObjectService objectService;
	 private Personne personneConnecte;
	
	public void recupererUtilisateur(){
		personneConnecte = new Personne();
		setPersonneConnecte(getObjectService().RecupererUtilisateurCourrant());
			//vérifié s'il ya une personne connectée
		if(personneConnecte == null){
			//lever une boîte de dialog ici
			
		}
		
	}
	
	
	public void créerCompte(Physique physique){
		
	}

	public Personne getPersonneConnecte() {
		return personneConnecte;
	}

	public void setPersonneConnecte(Personne personneConnecte) {
		this.personneConnecte = personneConnecte;
	}

	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}
}
