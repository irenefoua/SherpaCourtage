package com.j3a.assurance.managedBean.admin;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.managedBean.adminCompagnie.ManagedCompagnie;
import com.j3a.assurance.model.CompagnieAssurance;
import com.j3a.assurance.model.RcTarif9;
import com.j3a.assurance.model.SousCatVehicule;
import com.j3a.assurance.model.Tarif;
import com.j3a.assurance.model.Tarifweb;
import com.j3a.assurance.model.TarifwebSousCat;
import com.j3a.assurance.model.TarifwebSousCatId;
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
		private  String codetar,codetarw;
		private CompagnieAssurance compagnieAssuranceConnecte=new CompagnieAssurance();
        private Tarifweb tarifweb=new Tarifweb();
		
        private TarifwebSousCatId tarifwebSousCatId=new TarifwebSousCatId();
    	private TarifwebSousCat tarifwebSousCat=new TarifwebSousCat();
        
        
        public void PostConst(CompagnieAssurance compagnieAssurancee){
    		setRcTarif9( (RcTarif9) getObjectService().getObjectById("R9"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "RcTarif9"));
    		setTarif( (Tarif) getObjectService().getObjectById("T9"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "Tarif"));
    	    setTarifweb((Tarifweb) getObjectService().getObjectById("TW9"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "Tarifweb"));
    		try { 
    					if((rcTarif9 !=null) && (tarif !=null) && (tarifweb !=null) ){ 
    					setRcTarif9(getRcTarif9());
    					setTarif(tarif);
    					setTarifweb(getTarifweb());
    					}
    					
    					else{
    					    rcTarif9=new RcTarif9();
    						rcTarif9.setCodeRcTarif9("R9"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
    						tarif=new Tarif();
    						tarif.setCodeTarif("T9"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
    						tarifweb=new Tarifweb();
    						tarifweb.setCodeTarifWeb("TW9"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
    					}
    					
    				} catch (NullPointerException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    					System.out.println("non ok !Enregistrement non recuperée");		
    			}
    						
    			
    			}
		
		public  void enregistrer(){
		try{	
			setCompagnieAssuranceConnecte(getObjectService().RecupererCompagnieCourrant());
			code ="R9"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"";
			codetar ="T9"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"";
			codetarw ="TW9"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"";

		RcTarif9 rcTarif1Tempon = (RcTarif9) getObjectService().getObjectById(code, "RcTarif9");
		Tarif tarifTempon = (Tarif) getObjectService().getObjectById(codetar, "Tarif");
		
		Tarifweb tarifweb1=(Tarifweb) getObjectService().getObjectById(codetarw, "Tarifweb");
		if((rcTarif1Tempon==null) && (tarifTempon==null) && (tarifweb1==null)){
			rcTarif9.setCodeRcTarif9("R9"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+ "");	
			getObjectService().addObject(rcTarif9);	
			tarif.setCodeTarif("T9"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+ "");
			tarif.setRcTarif9(rcTarif9);
			tarif.setLibelleTarif("Tarif 9");
		//	tarif.setCompagnieAssurance(getCompagnieAssuranceConnecte());
			getObjectService().addObject(tarif);
			
			SousCatVehicule sousCatVehicule=new SousCatVehicule();
			sousCatVehicule.setCodeSousCatVehicule("SCAT9");
			
			tarifweb.setCodeTarifWeb(codetarw);
			tarifweb.setCompagnieAssurance(getCompagnieAssuranceConnecte());
			tarifweb.setTarif(getTarif());
			tarifweb.setLibelleTarifWeb("Tarif 9");
			getObjectService().addObject(tarifweb);
			
			tarifwebSousCatId=new TarifwebSousCatId();
			tarifwebSousCatId.setCodeSousCatVehicule(sousCatVehicule.getCodeSousCatVehicule());
			tarifwebSousCatId.setCodeTarifWeb(getTarifweb().getCodeTarifWeb());
			
			tarifwebSousCat=new TarifwebSousCat();
			tarifwebSousCat.setId(tarifwebSousCatId);
			tarifwebSousCat.setSousCatVehicule(sousCatVehicule);
			tarifwebSousCat.setTarifweb(tarifweb);
			getObjectService().addObject(tarifwebSousCat);
			
			sousCatVehicule.getTarifwebSousCats().add(tarifwebSousCat);
			tarifweb.getTarifwebSousCats().add(tarifwebSousCat);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success", "Enregistrement effectué"));
		}
		
		else {
			getObjectService().updateObject(rcTarif9);
			getObjectService().updateObject(tarif);
			getObjectService().updateObject(tarifweb);
			getObjectService().updateObject(tarifwebSousCat);
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

		public CompagnieAssurance getCompagnieAssuranceConnecte() {
			return compagnieAssuranceConnecte;
		}

		public void setCompagnieAssuranceConnecte(
				CompagnieAssurance compagnieAssuranceConnecte) {
			this.compagnieAssuranceConnecte = compagnieAssuranceConnecte;
		}

		public Tarifweb getTarifweb() {
			return tarifweb;
		}

		public void setTarifweb(Tarifweb tarifweb) {
			this.tarifweb = tarifweb;
		}

	

		public TarifwebSousCatId getTarifwebSousCatId() {
			return tarifwebSousCatId;
		}

		public void setTarifwebSousCatId(TarifwebSousCatId tarifwebSousCatId) {
			this.tarifwebSousCatId = tarifwebSousCatId;
		}

		public TarifwebSousCat getTarifwebSousCat() {
			return tarifwebSousCat;
		}

		public void setTarifwebSousCat(TarifwebSousCat tarifwebSousCat) {
			this.tarifwebSousCat = tarifwebSousCat;
		}

		public String getCodetarw() {
			return codetarw;
		}

		public void setCodetarw(String codetarw) {
			this.codetarw = codetarw;
		}



		
		
		
		
		
		
	}



