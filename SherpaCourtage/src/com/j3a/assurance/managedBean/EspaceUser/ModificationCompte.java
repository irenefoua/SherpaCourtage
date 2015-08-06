package com.j3a.assurance.managedBean.EspaceUser;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.managedBean.Auto.ManagedSinistre;
import com.j3a.assurance.model.Personne;
import com.j3a.assurance.model.Physique;
import com.j3a.assurance.objetService.ObjectService;

@Component
public class ModificationCompte {
	private static  Logger logger=Logger.getLogger(ManagedSinistre.class);
	
	@Autowired
	private ObjectService objectService;
	

private	Personne personneConneter = new Personne();
private	Physique physiqueConnecte = new Physique();

private	Personne personneModif = new Personne();
private	Physique physiqmodif = new Physique();
private String loginmodif;
private String mdpmodif;
private String mdpCpnfirmmodif;

@PostConstruct
	public void chargerUtilisateur(){
		//Recuperer l'utilisateur courrant
	//	personneConneter = getObjectService().RecupererUtilisateurCourrant();
		System.out.println("utilisateur: "+personneConneter.getLoginPers());
		//Recuperer la personne Physique concernée
	//	Physique physiqueConnecte = (Physique) getObjectService().getObjectById(personneConneter.getNumSouscripteur(), "Physique");
		
		/*personneModif.setNumSouscripteur(personneConneter.getNumSouscripteur());
		personneModif.setLoginPers(personneConneter.getLoginPers());*/
	}
	
	/*public void modifierCompte(){
		getObjectService().updateObject(personneModif);
	}
	
	public void verifierLogin(){
		List<Personne> listPerLogin = new ArrayList<>();
		listPerLogin = getObjectService().personneByLogin(personneModif.getLoginPers());
		if ((listPerLogin.size()) > 0 && (personneConneter.getLoginPers().equalsIgnoreCase(personneModif.getLoginPers()))){
			//Message possibilité de mis a jour
		}else{
			//Message login utilisable 
		}
	}*/


	public void modifierLogin(){
			//Recuperer l'utilisateur courrant
		personneConneter = getObjectService().RecupererUtilisateurCourrant();
			//Recuperer la personne Physique concernée
		Physique physiqueConnecte = (Physique) getObjectService().getObjectById(personneConneter.getNumSouscripteur(), "Physique");
		
		setPersonneModif(personneConneter);
		personneModif.setLoginPers(getLoginmodif());
		personneModif.setLoginPers(loginmodif);
		
		setPhysiqmodif(physiqueConnecte);
		physiqmodif.setLoginPers(getLoginmodif());
		physiqmodif.setLoginPers(loginmodif);
		
		getObjectService().updateObject(personneModif);
		getObjectService().updateObject(physiqmodif);
		System.out.println("------->>> mis a jour du login ok");//Clean after
	}
	
	public void mofifierMDP(){
			//Recuperer l'utilisateur courrant
		personneConneter = getObjectService().RecupererUtilisateurCourrant();
			//Recuperer la personne Physique concernée
		Physique physiqueConnecte = (Physique) getObjectService().getObjectById(personneConneter.getNumSouscripteur(), "Physique");
				
		setPersonneModif(personneConneter);
		personneModif.setMotPassePers(getMdpmodif());
		setPhysiqmodif(physiqueConnecte);
		physiqmodif.setMotPassePers(getMdpmodif());
		
		getObjectService().updateObject(personneModif);
		getObjectService().updateObject(physiqmodif);
		System.out.println("------>> mise à jour du mot de pass  ok");//Clean after
		
		//Vider les champs
		personneModif.setMotPassePers("");
		physiqmodif.setMotPassePers("");
	}
	
	public void verifierMdpasse(){
		if(mdpmodif.equalsIgnoreCase(mdpCpnfirmmodif)){
			System.out.println("------>> Mot de passe confirmé");// Clean after
		}else{
			System.out.println("------>> Mot de passe non confirmé");// Clean after
		}
	}
	

	/************************ACCESSEUR*************************************/

	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}

	public Personne getPersonneConneter() {
		return personneConneter;
	}

	public void setPersonneConneter(Personne personneConneter) {
		this.personneConneter = personneConneter;
	}

	public Physique getPhysiqueConnecte() {
		return physiqueConnecte;
	}

	public void setPhysiqueConnecte(Physique physiqueConnecte) {
		this.physiqueConnecte = physiqueConnecte;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		ModificationCompte.logger = logger;
	}

	public Personne getPersonneModif() {
		return personneModif;
	}

	public void setPersonneModif(Personne personneModif) {
		this.personneModif = personneModif;
	}

	public Physique getPhysiqmodif() {
		return physiqmodif;
	}

	public void setPhysiqmodif(Physique physiqmodif) {
		this.physiqmodif = physiqmodif;
	}

	public String getLoginmodif() {
		return loginmodif;
	}

	public void setLoginmodif(String loginmodif) {
		this.loginmodif = loginmodif;

	}

	public String getMdpmodif() {
		return mdpmodif;
	}

	public void setMdpmodif(String mdpmodif) {
		this.mdpmodif = mdpmodif;
	}

	public String getMdpCpnfirmmodif() {
		return mdpCpnfirmmodif;
	}

	public void setMdpCpnfirmmodif(String mdpCpnfirmmodif) {
		this.mdpCpnfirmmodif = mdpCpnfirmmodif;
	}

	
}
