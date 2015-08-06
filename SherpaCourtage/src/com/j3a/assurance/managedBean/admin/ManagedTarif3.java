package com.j3a.assurance.managedBean.admin;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.RcTarif3;
import com.j3a.assurance.model.Tarif;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.utilitaires.IdGenerateur;

@Component
public class ManagedTarif3 implements Serializable{

	/**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		private static final String succes = "succes";
		private static final String ERROR = "error";
		
		@Autowired
		ObjectService objectService;
		@Autowired
		IdGenerateur idGenerateur;
		
		
		
		private Tarif tarif = new Tarif();
		private RcTarif3 rcTarif3 = new RcTarif3();
		private  String code;
		private  String codetar;
		
		
		@PostConstruct
		public void PostConst(){
			
			RcTarif3 rcTarif3t2 = (RcTarif3) getObjectService().getObjectById("Rctarif3", "RcTarif3");
			Tarif tarift = (Tarif) getObjectService().getObjectById("tarif3", "Tarif");
			try {
				 
				if(rcTarif3t2 !=null ){ 
				setRcTarif3(rcTarif3t2 );}
				
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
				
				code ="Rctarif3";
				codetar ="tarif3";
			RcTarif3 rcTarif3Tempon = (RcTarif3) getObjectService().getObjectById(code, "RcTarif3");
			Tarif tarifTempon = (Tarif) getObjectService().getObjectById(codetar, "Tarif");
		
				
				if((rcTarif3Tempon==null) && (tarifTempon==null)){
				rcTarif3.setCodeRcTarif3("Rctarif3");
				getObjectService().addObject(rcTarif3);
				
				
				
				tarif.setCodeTarif("tarif3");
				tarif.setRcTarif3(rcTarif3);
				tarif.setLibelleTarif("Tarif 3");
				getObjectService().addObject(tarif);
				
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("succes", "Enregistrement effectué"));
				/*Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
				sessionMap.clear();*/
				}
				else{
					getObjectService().updateObject(rcTarif3);
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



		public RcTarif3 getRcTarif3() {
			return rcTarif3;
		}



		public void setRcTarif3(RcTarif3 rcTarif3) {
			this.rcTarif3 = rcTarif3;
		}














		public String getCodetar() {
			return codetar;
		}

		public void setCodetar(String codetar) {
			this.codetar = codetar;
		}

		public String getCode() {
			return code;
		}














		public void setCode(String code) {
			this.code = code;
		}
		
		
		
		
		
		
	}



