package com.j3a.assurance.utilitaire;

import org.springframework.beans.factory.annotation.Autowired;

import com.j3a.assurance.model.Personne;
import com.j3a.assurance.model.Physique;
import com.j3a.assurance.objetService.ObjectService;

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
			//v�rifi� s'il ya une personne connect�e
		if(personneConnecte == null){
			//lever une bo�te de dialog ici
		}
		
	}
	
	
	public void cr�erCompte(Physique physique){
		
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
