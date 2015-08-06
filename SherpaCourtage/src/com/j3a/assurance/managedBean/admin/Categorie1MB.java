package com.j3a.assurance.managedBean.admin;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.RcTarif1;
import com.j3a.assurance.model.Tarif;
import com.j3a.assurance.objetService.ObjectService;

@Component
public class Categorie1MB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    ObjectService objectService;
	private RcTarif1 rcTarif1 = new RcTarif1();
	private Tarif tarif =new Tarif();
	private  String code;
	private  String codetar;
	
	
	@PostConstruct
	public void PostConst(){
			setRcTarif1( (RcTarif1) getObjectService().getObjectById("Rctarif1", "RcTarif1"));
			setTarif( (Tarif) getObjectService().getObjectById("tarif1", "Tarif"));
			try { 
				if((rcTarif1 !=null) && (tarif !=null) ){ 
				setRcTarif1(rcTarif1 );
				setTarif(tarif);
				}
				
				else{
				    rcTarif1=new RcTarif1();
					rcTarif1.setCodeRcTarif1("Rctarif1");
					tarif=new Tarif();
					tarif.setCodeTarif("tarif1");
					
				}
				
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("non ok !Enregistrement non recuperée");		
		}		
		
		}
	
	
	
	public void addCategorie(){
	try {
		code ="Rctarif1";
		codetar ="tarif1";
	RcTarif1 rcTarif1Tempon = (RcTarif1) getObjectService().getObjectById(code, "RcTarif1");
	Tarif tarifTempon = (Tarif) getObjectService().getObjectById(codetar, "Tarif");
	
	if((rcTarif1Tempon==null) && (tarifTempon==null)){
		rcTarif1.setCodeRcTarif1("RcTarif1");	
		getObjectService().addObject(rcTarif1);	
		tarif.setCodeTarif("Tarif1");
		tarif.setRcTarif1(rcTarif1);
		tarif.setLibelleTarif("Tarif 1");
		getObjectService().addObject(tarif);	
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success", "Enregistrement effectué"));
	}
	
	else {
		getObjectService().updateObject(rcTarif1);
		getObjectService().updateObject(tarif);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("succes", "La mise à jour a été bien effectuée"));
	}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Echec", "Enregistrement non effectué"));		

	}		

	}
	
	
	public RcTarif1 getRcTarif1() {
		return rcTarif1;
	}


	public void setRcTarif1(RcTarif1 rcTarif1) {
		this.rcTarif1 = rcTarif1;
	}


	public Tarif getTarif() {
		return tarif;
	}


	public void setTarif(Tarif tarif) {
		this.tarif = tarif;
	}


	public ObjectService getObjectService() {
		return objectService;
	}
	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getCodetar() {
		return codetar;
	}


	public void setCodetar(String codetar) {
		this.codetar = codetar;
	}
	
}
