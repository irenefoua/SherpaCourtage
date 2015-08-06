package com.j3a.assurance.managedBean.admin;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.RcTarif10;
import com.j3a.assurance.model.Tarif;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.utilitaires.IdGenerateur;

@Component
public class ManagedTarif10 implements Serializable{

	/**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		private static final String SUCCESS = "succes";
		private static final String ERROR = "error";
		
		@Autowired
		ObjectService objectService;
		@Autowired
		IdGenerateur idGenerateur;
		private  String code;
		private  String codetar;
		private Tarif tarif = new Tarif();
		private RcTarif10 rcTarif10 = new RcTarif10();
		
		
		@PostConstruct
		public void PostConst(){
				setRcTarif10( (RcTarif10) getObjectService().getObjectById("Rctarif10", "RcTarif10"));
				setTarif( (Tarif) getObjectService().getObjectById("tarif10", "Tarif"));
				try { 
					if((rcTarif10 !=null) && (tarif !=null) ){ 
					setRcTarif10(rcTarif10 );
					setTarif(tarif);
					}
					
					else{
					    rcTarif10=new RcTarif10();
						rcTarif10.setCodeRcTarif10("Rctarif10");
						rcTarif10.setPaE1011D78Zone1(new BigDecimal(0));
						tarif=new Tarif();
						tarif.setCodeTarif("tarif10");
						
					}
					
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("non ok !Enregistrement non recuperée");		
			}		
			
			}
		
		public  void enregistrer(){
		try{	
			code ="Rctarif10";
			codetar ="tarif10";
		RcTarif10 rcTarif1Tempon = (RcTarif10) getObjectService().getObjectById(code, "RcTarif10");
		Tarif tarifTempon = (Tarif) getObjectService().getObjectById(codetar, "Tarif");
		
		if((rcTarif1Tempon==null) && (tarifTempon==null)){
		rcTarif10.setCodeRcTarif10("RcTarif10");
		getObjectService().addObject(rcTarif10);
		tarif.setCodeTarif("tarif10");
		tarif.setLibelleTarif("Tarif 10");
		getObjectService().addObject(tarif);
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Succes", "Enregistrement effectué"));
		}
		else {
			getObjectService().updateObject(rcTarif10);
			getObjectService().updateObject(tarif);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("succes", "La mise à jour a été bien effectuée"));
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Echec", "Enregistrement non effectué"));		

		}		
		
		}

		
		
		
		
		
		

		
		
		
		

//getters et setters
		public ObjectService getObjectService() {
			return objectService;
		}



		public void setObjectService(ObjectService objectService) {
			this.objectService = objectService;
		}



		public IdGenerateur getIdGenerateur() {
			return idGenerateur;
		}



		public void setIdGenerateur(IdGenerateur idGenerateur) {
			this.idGenerateur = idGenerateur;
		}



		public Tarif getTarif() {
			return tarif;
		}



		public void setTarif(Tarif tarif) {
			this.tarif = tarif;
		}



		public RcTarif10 getRcTarif10() {
			return rcTarif10;
		}


		public void setRcTarif10(RcTarif10 rcTarif10) {
			this.rcTarif10 = rcTarif10;
		}


	
		
	}



