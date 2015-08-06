package com.j3a.assurance.managedBean.EspaceUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.context.RequestContext;
import org.primefaces.event.ToggleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.Contrat;
import com.j3a.assurance.model.Personne;
import com.j3a.assurance.model.Vehicule;
import com.j3a.assurance.model.VehiculeSinistre;
import com.j3a.assurance.utilitaires.ContratRw;
import com.j3a.assurance.utilitaires.VehiAssur;

@Component
public class ManagedUserVehiSinList {

	@Autowired
	RequeteUtilisateur requeteUtilisateur;
	
	
	
	public ManagedUserVehiSinList() {
		// TODO Auto-generated constructor stub
	}
    
    @PostConstruct
    public void init() {
    	
    }
 
    private Contrat ctra;
    private Vehicule vehic;
    private List<VehiculeSinistre> listVehiSin = new ArrayList<VehiculeSinistre>();
    private List<VehiculeSinistre> fltrdVehiSin = new ArrayList<VehiculeSinistre>();
    /*private List<String> risqLibList = new ArrayList<String>();*/
    private VehiculeSinistre slctdVehiSin;
    
   
    
    

    
    
    
    


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

	public List<VehiculeSinistre> getListVehiSin() {
		if(listVehiSin.isEmpty()){
			if(getCtra()!=null){
				//System.out.println("listVehiAssur////"+listVehiAssur);
				listVehiSin.addAll(getRequeteUtilisateur().vehiSinByCtra(getCtra()));
				}
			if(getVehic()!=null){
				listVehiSin.addAll(getRequeteUtilisateur().vehiSinByVehi(getVehic()));
			}
		}
		return listVehiSin;
	}

	public void setListVehiSin(List<VehiculeSinistre> listVehiSin) {
		this.listVehiSin = listVehiSin;
	}

	public List<VehiculeSinistre> getFltrdVehiSin() {
		return fltrdVehiSin;
	}

	public void setFltrdVehiSin(List<VehiculeSinistre> fltrdVehiSin) {
		this.fltrdVehiSin = fltrdVehiSin;
	}

	public VehiculeSinistre getSlctdVehiSin() {
		return slctdVehiSin;
	}

	public void setSlctdVehiSin(VehiculeSinistre slctdVehiSin) {
		this.slctdVehiSin = slctdVehiSin;
	}

	public Vehicule getVehic() {
		return vehic;
	}

	public void setVehic(Vehicule vehic) {
		this.vehic = vehic;
	}

    
    
	
    
    
    
    
}
