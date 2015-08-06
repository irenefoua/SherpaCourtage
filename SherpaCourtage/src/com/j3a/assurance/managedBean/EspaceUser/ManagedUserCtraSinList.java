package com.j3a.assurance.managedBean.EspaceUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.Personne;
import com.j3a.assurance.utilitaires.ContratRw;

@Component
@Scope("session")
public class ManagedUserCtraSinList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9074248436076212187L;

	@Autowired
	RequeteUtilisateur requeteUtilisateur;
	
	@Autowired
	ManagedUserVehiSinList managedUserVehiSinList;
	
	public ManagedUserCtraSinList() {
		// TODO Auto-generated constructor stub
	}
    
    @PostConstruct
    public void init() {
    
    }
 
    private Personne cntedPerson;
    private List<ContratRw> listCtrat = new ArrayList<ContratRw>();
    private List<ContratRw> fltrdCtraLst = new ArrayList<ContratRw>();
    /*private List<String> risqLibList = new ArrayList<String>();*/
    private ContratRw slctdCtraRw;
    
   
    /**/
    
    

    
    public void consultVehiAss(){
    	//getManagedUserVehiAssList().setCtra(getSlctdCtraRw().getCtrat());;
    	System.out.println("getSlctdCtraRw().getCtrat()////////////////////////////////////////"+getSlctdCtraRw().getCtrat());
    	RequestContext.getCurrentInstance().execute("dlg.show();");
    	//callDialog("listVehiAssures");
    }
    public void consultSin(){
    	getManagedUserVehiSinList().setCtra(getSlctdCtraRw().getCtrat());
    	RequestContext.getCurrentInstance().execute("dlgSin.show();");
    	//callDialog("listVehiSin");
    }
    
    
    public void callDialog(String X){
		System.out.println("INSIDE callListVehi////////////////////////////////////////");
		
		RequestContext.getCurrentInstance().openDialog(X);
		
		//RequestContext.getCurrentInstance().execute("PF('Alert').show();");
		System.out.println("AFTER callGraphStat////////////////////////////////////////");
	}
    
    
    


	public RequeteUtilisateur getRequeteUtilisateur() {
		return requeteUtilisateur;
	}

	public void setRequeteUtilisateur(RequeteUtilisateur requeteUtilisateur) {
		this.requeteUtilisateur = requeteUtilisateur;
	}

	public Personne getCntedPerson() {
		return cntedPerson;
	}

	public void setCntedPerson(Personne cntedPerson) {
		this.cntedPerson = cntedPerson;
	}

	public List<ContratRw> getListCtrat() {
		if(listCtrat.isEmpty()){
			setCntedPerson(getRequeteUtilisateur().RecupererUtilisateurCourrant());
			if(getCntedPerson()!=null){
				listCtrat.addAll(getRequeteUtilisateur().retriveCtrat(getCntedPerson()));
			}
			
		}
		return listCtrat;
	}

	public void setListCtrat(List<ContratRw> listCtrat) {
		this.listCtrat = listCtrat;
	}

	public ContratRw getSlctdCtraRw() {
		return slctdCtraRw;
	}

	public void setSlctdCtraRw(ContratRw slctdCtraRw) {
		this.slctdCtraRw = slctdCtraRw;
	}

	public List<ContratRw> getFltrdCtraLst() {
		return fltrdCtraLst;
	}

	public void setFltrdCtraLst(List<ContratRw> fltrdCtraLst) {
		this.fltrdCtraLst = fltrdCtraLst;
	}

	public ManagedUserVehiSinList getManagedUserVehiSinList() {
		return managedUserVehiSinList;
	}

	public void setManagedUserVehiSinList(
			ManagedUserVehiSinList managedUserVehiSinList) {
		this.managedUserVehiSinList = managedUserVehiSinList;
	}

	
	
/*	public List<String> getRisqLibList() {
		return risqLibList;
	}

	public void setRisqLibList(List<String> risqLibList) {
		this.risqLibList = risqLibList;
	}
    */
	
    
    
    
    
    
    
    
}
