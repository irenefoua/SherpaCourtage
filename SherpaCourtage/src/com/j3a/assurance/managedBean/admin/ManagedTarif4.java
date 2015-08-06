package com.j3a.assurance.managedBean.admin;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.RcTarif4;
import com.j3a.assurance.model.Tarif;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.utilitaires.IdGenerateur;

@Component
public class ManagedTarif4 implements Serializable{

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
		
		
		
		private Tarif tarif = new Tarif();
		private RcTarif4 rcTarif4 = new RcTarif4();
		
		
		private  String code ;
		private  String codetar ;
		
		
		@PostConstruct
		public void PostConst(){
			
			RcTarif4 rcTarif4t2 = (RcTarif4) getObjectService().getObjectById("Rctarif4", "RcTarif4");
			Tarif tarift = (Tarif) getObjectService().getObjectById("tarif4", "Tarif");
			try {
				 
				if(rcTarif4t2 !=null ){ 
				setRcTarif4(rcTarif4t2 );}
				
				if (tarift !=null){
				setTarif(tarift);
				System.out.println("ok Enregistrement recuperee");}
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("non ok !Enregistrement non recuperée");		

			}		
			
			}
		
		public  void enregistrer(){
			
			
			try {
				
				code ="Rctarif4";
				codetar ="tarif4";
			RcTarif4 rcTarif4Tempon = (RcTarif4) getObjectService().getObjectById(code, "RcTarif4");
			Tarif tarifTempon = (Tarif) getObjectService().getObjectById(codetar, "Tarif");
		
				
				if((rcTarif4Tempon==null) && (tarifTempon==null)){
				rcTarif4.setCodeRcTarif4("Rctarif4");
				getObjectService().addObject(rcTarif4);
				
				
				
				tarif.setCodeTarif("tarif4");
				tarif.setRcTarif4(rcTarif4);
				tarif.setLibelleTarif("Tarif 4");
				getObjectService().addObject(tarif);
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("succes", "Enregistrement effectué"));
			}
				else{
					getObjectService().updateObject(rcTarif4);
					getObjectService().updateObject(tarif);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("succes", "La mise à jour a été bien effectuée"));
			}
		
		
		
		
		
		} catch (NullPointerException e) {
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



		public RcTarif4 getRcTarif4() {
			return rcTarif4;
		}


		public void setRcTarif4(RcTarif4 rcTarif4) {
			this.rcTarif4 = rcTarif4;
		}








		public String getCodetar() {
			return codetar;
		}








		public void setCodetar(String codetar) {
			this.codetar = codetar;
		}



		
		
		
		
		
		
	}



