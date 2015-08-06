package com.j3a.assurance.managedBean.EspaceUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.context.RequestContext;
import org.primefaces.event.ToggleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.Contrat;
import com.j3a.assurance.model.Personne;
import com.j3a.assurance.utilitaires.ContratRw;
import com.j3a.assurance.utilitaires.VehiAssur;

@Component
@Scope("session")
public class ManagedUserVehiAssList {

	@Autowired
	RequeteUtilisateur requeteUtilisateur;
	
	@Autowired
	ManagedUserCtraSinList managedUserCtraSinList ;
	
	@Autowired
	ManagedUserVehiSinList managedUserVehiSinList;
	
	public ManagedUserVehiAssList() {
		// TODO Auto-generated constructor stub
	}
    
    @PostConstruct
    public void init() {
    	
    }
 
    private Contrat ctra;
    private List<VehiAssur> listVehiAssur = new ArrayList<VehiAssur>();
    private List<VehiAssur> fltrdVehiLst = new ArrayList<VehiAssur>();
    /*private List<String> risqLibList = new ArrayList<String>();*/
    private VehiAssur slctdVehiAss;
    

    
    public void viewVehiSin(){
    	//getManagedUserVehiSinList().setListVehiSin(getRequeteUtilisateur().vehiSinByVehi(getSlctdVehiAss().getVehi()));
    	callListSinistre();
    }
    
    public void onRowToggle(ToggleEvent event) {
        
        setSlctdVehiAss((VehiAssur) event.getData());
    }
    
    public void consultSin(){
    	getManagedUserVehiSinList().setVehic(getSlctdVehiAss().getVehi());
    	callDialog("listVehiSin");
    }
    
    public void callDialog(String X){
		System.out.println("INSIDE callListVehi////////////////////////////////////////");
		
		RequestContext.getCurrentInstance().openDialog(X);
		
		//RequestContext.getCurrentInstance().execute("PF('Alert').show();");
		System.out.println("AFTER callGraphStat////////////////////////////////////////");
	}
    public void callListSinistre(){
		System.out.println("INSIDE callGraphStat////////////////////////////////////////");
		Map<String,Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", true);
		options.put("contentHeight", 900);
		options.put("contentWidth", 1300);
		
		RequestContext.getCurrentInstance().openDialog ("listVehiSin", options, null);
		
		//RequestContext.getCurrentInstance().execute("PF('Alert').show();");
		System.out.println("AFTER callGraphStat////////////////////////////////////////");
	}
    
    
    


	public RequeteUtilisateur getRequeteUtilisateur() {
		return requeteUtilisateur;
	}

	public void setRequeteUtilisateur(RequeteUtilisateur requeteUtilisateur) {
		this.requeteUtilisateur = requeteUtilisateur;
	}

	

	public Contrat getCtra() {
		return ctra;
	}

	public void setCtra(Contrat ctra) {
		this.ctra = ctra;
	}

	public List<VehiAssur> getListVehiAssur() {
		
		if(listVehiAssur.isEmpty()){
			if(getManagedUserCtraSinList().getSlctdCtraRw()!=null)
				{
				//System.out.println("listVehiAssur////"+listVehiAssur);
				listVehiAssur.addAll(getRequeteUtilisateur().retrvInsuredVehi(getManagedUserCtraSinList().getSlctdCtraRw().getCtrat()));
				}
		}
		return listVehiAssur;
	}

	public void setListVehiAssur(List<VehiAssur> listVehiAssur) {
		this.listVehiAssur = listVehiAssur;
	}

	public List<VehiAssur> getFltrdVehiLst() {
		return fltrdVehiLst;
	}

	public void setFltrdVehiLst(List<VehiAssur> fltrdVehiLst) {
		this.fltrdVehiLst = fltrdVehiLst;
	}

	public VehiAssur getSlctdVehiAss() {
		return slctdVehiAss;
	}

	public void setSlctdVehiAss(VehiAssur slctdVehiAss) {
		this.slctdVehiAss = slctdVehiAss;
	}

	public ManagedUserCtraSinList getManagedUserCtraSinList() {
		return managedUserCtraSinList;
	}

	public void setManagedUserCtraSinList(
			ManagedUserCtraSinList managedUserCtraSinList) {
		this.managedUserCtraSinList = managedUserCtraSinList;
	}

	public ManagedUserVehiSinList getManagedUserVehiSinList() {
		return managedUserVehiSinList;
	}

	public void setManagedUserVehiSinList(
			ManagedUserVehiSinList managedUserVehiSinList) {
		this.managedUserVehiSinList = managedUserVehiSinList;
	}


    
    
	
    
    
    
    
}
