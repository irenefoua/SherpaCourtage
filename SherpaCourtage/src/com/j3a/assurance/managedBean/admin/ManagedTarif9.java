package com.j3a.assurance.managedBean.admin;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.RcTarif9;
import com.j3a.assurance.model.Tarif;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.utilitaires.IdGenerateur;

@Component
public class ManagedTarif9 implements Serializable{

	/**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		
		
		@Autowired
		ObjectService objectService;
		@Autowired
		IdGenerateur idGenerateur;
		private Tarif tarif = new Tarif();
		private RcTarif9 rcTarif9 = new RcTarif9();
		private  String code;
		private  String codetar;
		
		
		@PostConstruct
		public void PostConst(){
				setRcTarif9( (RcTarif9) getObjectService().getObjectById("Rctarif9", "RcTarif9"));
				setTarif( (Tarif) getObjectService().getObjectById("tarif9", "Tarif"));
				try { 
					if((rcTarif9 !=null) && (tarif !=null) ){ 
					setRcTarif9(rcTarif9 );
					setTarif(tarif);
					}
					
					else{
					    rcTarif9=new RcTarif9();
						rcTarif9.setCodeRcTarif9("Rctarif10");
						rcTarif9.setVhlRemorqueZone1(new BigDecimal(0));
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
			code ="Rctarif9";
			codetar ="tarif9";
		RcTarif9 rcTarif1Tempon = (RcTarif9) getObjectService().getObjectById(code, "RcTarif9");
		Tarif tarifTempon = (Tarif) getObjectService().getObjectById(codetar, "Tarif");
		
		if((rcTarif1Tempon==null) && (tarifTempon==null)){
		rcTarif9.setCodeRcTarif9("RcTarif9");
		getObjectService().addObject(rcTarif9);
		tarif.setCodeTarif("tarif9");
		tarif.setLibelleTarif("Tarif 9");
		getObjectService().addObject(tarif);
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Succes", "Enregistrement effectué"));
		}
		else {
			getObjectService().updateObject(rcTarif9);
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



		public RcTarif9 getRcTarif9() {
			return rcTarif9;
		}


		public void setRcTarif9(RcTarif9 rcTarif9) {
			this.rcTarif9 = rcTarif9;
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



