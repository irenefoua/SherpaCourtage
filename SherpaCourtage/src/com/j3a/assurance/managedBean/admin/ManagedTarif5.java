package com.j3a.assurance.managedBean.admin;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.RcTarif5;
import com.j3a.assurance.model.Tarif;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.utilitaires.IdGenerateur;

@Component
public class ManagedTarif5 implements Serializable{

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
		private RcTarif5 rcTarif5 = new RcTarif5();
		private  String code;
		private  String codetar;
		
		
		
		@PostConstruct
		public void PostConst(){
			
			RcTarif5 rcTarif5t2 = (RcTarif5) getObjectService().getObjectById("Rctarif5", "RcTarif5");
			Tarif tarift = (Tarif) getObjectService().getObjectById("tarif5", "Tarif");
			try {
				 
				if(rcTarif5t2 !=null ){ 
				setRcTarif5(rcTarif5t2 );}
				
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
				
				code ="Rctarif5";
				codetar ="tarif5";
			RcTarif5 rcTarif5Tempon = (RcTarif5) getObjectService().getObjectById(code, "RcTarif5");
			Tarif tarifTempon = (Tarif) getObjectService().getObjectById(codetar, "Tarif");
		
				
				if((rcTarif5Tempon==null) && (tarifTempon==null)){
				rcTarif5.setCodeRcTarif5("Rctarif5");
				getObjectService().addObject(rcTarif5);
				
				
				
				tarif.setCodeTarif("tarif5");
				tarif.setRcTarif5(rcTarif5);
				tarif.setLibelleTarif("Tarif 5");
				getObjectService().addObject(tarif);
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("succes", "Enregistrement effectué"));
			}
				else{
					getObjectService().updateObject(rcTarif5);
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



		public RcTarif5 getRcTarif5() {
			return rcTarif5;
		}


		public void setRcTarif5(RcTarif5 rcTarif5) {
			this.rcTarif5 = rcTarif5;
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



