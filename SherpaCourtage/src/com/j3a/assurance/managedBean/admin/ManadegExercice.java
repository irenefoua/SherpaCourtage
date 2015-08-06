package com.j3a.assurance.managedBean.admin;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



import com.j3a.assurance.model.Exercice;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.utilitaire.InfoAvenantContrat;


@Component
@Scope("session")
public class ManadegExercice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	ObjectService objectService;
	private Exercice exercice= new Exercice();
	private int exo;
	@Autowired
	InfoAvenantContrat infoAvenantContrat;
	private Exercice exerciceOuvert = new Exercice();
	
	public void ouvrir(){

		setExercice((Exercice) getObjectService().getObjectById(getExo(), "Exercice"));
		setExerciceOuvert(getInfoAvenantContrat().exerciceOuvert());
		
			if(getExercice()==null){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Attention", "cet exercice n'existe pas"));}
			else{
				if(getExercice().getEtatExercice().equalsIgnoreCase("ouvert")){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Attention", "cet exercice est déjà ouvert"));
				}
				else{
					if(getExerciceOuvert().getEtatExercice().equalsIgnoreCase("ouvert")){
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Attention", "un exercice est déjà ouvert"));
					}
				else{
					exercice.setEtatExercice("ouvert");
					getObjectService().updateObject(exercice);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Succès", "l'exercice est ouvert"));
				}
				}
			}

		
	}

	
	
    public void ferme(){
		exercice=new Exercice();
	    setExercice(getExerciceOuvert());
		getExercice().setEtatExercice("ferme");
		getObjectService().updateObject(getExercice());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Succès", "l'exercice fermé"));

		}
		
	
     public void creer(){
		Exercice exercice1=new Exercice();
		exercice1=getInfoAvenantContrat().exerciceOuvert();
		if(exercice1!=null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Attention", "l'exercice"+exercice1.getCodeexercice()+"est déjà ouvert!"));
		}
		else{
			exercice.setLibelleExercice(String.valueOf(exercice.getCodeexercice()));
		    exercice.setEtatExercice("ouvert");
		getObjectService().addObject(exercice);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Succès", "exercice crée et ouvert!"));

		}
		
	}



	public ObjectService getObjectService() {
		return objectService;
	}



	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}



	public Exercice getExercice() {
		return exercice;
	}



	public void setExercice(Exercice exercice) {
		this.exercice = exercice;
	}
	public int getExo() {
		return exo;
	}
	public void setExo(int exo) {
		this.exo = exo;
	}



	public InfoAvenantContrat getInfoAvenantContrat() {
		return infoAvenantContrat;
	}



	public void setInfoAvenantContrat(InfoAvenantContrat infoAvenantContrat) {
		this.infoAvenantContrat = infoAvenantContrat;
	}



	public Exercice getExerciceOuvert() {
		setExerciceOuvert(getInfoAvenantContrat().exerciceOuvert());
		return exerciceOuvert;
	}



	public void setExerciceOuvert(Exercice exerciceOuvert) {
		this.exerciceOuvert = exerciceOuvert;
	}
	
	
	
	
}
