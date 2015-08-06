package com.j3a.assurance.managedBean.admin;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.RcTarif6;
import com.j3a.assurance.model.Tarif;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.utilitaires.IdGenerateur;

@Component
public class ManagedTarif6 implements Serializable{

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
		private RcTarif6 rcTarif6 = new RcTarif6();
		
		
		private  String code;
		private  String codetar;
		
		
		
		@PostConstruct
		public void PostConst(){
			
			RcTarif6 rcTarif6t2 = (RcTarif6) getObjectService().getObjectById("Rctarif6", "RcTarif6");
			Tarif tarift = (Tarif) getObjectService().getObjectById("tarif6", "Tarif");
			try {
				 
				if(rcTarif6t2 !=null ){ 
				setRcTarif6(rcTarif6t2 );}
				
				if (tarift !=null){
				setTarif(tarift);
				}
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
						

			}		
			
			}
		
		public  void enregistrer(){
			
			
			try {
				
				code ="Rctarif6";
				codetar ="tarif6";
			RcTarif6 rcTarif6Tempon = (RcTarif6) getObjectService().getObjectById(code, "RcTarif6");
			Tarif tarifTempon = (Tarif) getObjectService().getObjectById(codetar, "Tarif");
		
				
				if((rcTarif6Tempon==null) && (tarifTempon==null)){
				rcTarif6.setCodeRcTarif6("Rctarif6");
				getObjectService().addObject(rcTarif6);
				
				
				
				tarif.setCodeTarif("tarif6");
				tarif.setRcTarif6(rcTarif6);
				tarif.setLibelleTarif("Tarif 6");
				getObjectService().addObject(tarif);
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("succes", "Enregistrement effectué"));
			}
				else{
					getObjectService().updateObject(rcTarif6);
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



		public RcTarif6 getRcTarif6() {
			return rcTarif6;
		}


		public void setRcTarif6(RcTarif6 rcTarif6) {
			this.rcTarif6 = rcTarif6;
		}



		
		
		
		
		
		
	}



