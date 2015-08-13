package com.j3a.assurance.managedBean.admin;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.CompagnieAssurance;
import com.j3a.assurance.model.RcTarif1;
import com.j3a.assurance.model.RcTarif12;
import com.j3a.assurance.model.RcTarif12b;
import com.j3a.assurance.model.RcTarif2;
import com.j3a.assurance.model.SousCatVehicule;
import com.j3a.assurance.model.Tarif;
import com.j3a.assurance.model.Tarifweb;
import com.j3a.assurance.model.TarifwebSousCat;
import com.j3a.assurance.model.TarifwebSousCatId;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.utilitaires.IdGenerateur;

@Component
public class ManagedTarif12 implements Serializable{

	/**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		private static final String SUCCESS = "succes";
		private static final String ERROR = "error";
		private CompagnieAssurance compagnieAssuranceConnecte=new CompagnieAssurance();
		public CompagnieAssurance getCompagnieAssuranceConnecte() {
			return compagnieAssuranceConnecte;
		}

		public void setCompagnieAssuranceConnecte(
				CompagnieAssurance compagnieAssuranceConnecte) {
			this.compagnieAssuranceConnecte = compagnieAssuranceConnecte;
		}

		@Autowired
		ObjectService objectService;
		@Autowired
		IdGenerateur idGenerateur;
		private  String code;
		private  String codetar;
		
		
		private Tarif tarif = new Tarif();
		private RcTarif12 rcTarif12 = new RcTarif12();
		private RcTarif12b rcTarif12b = new RcTarif12b();
		private Tarifweb tarifweb=new Tarifweb();
		private TarifwebSousCatId tarifwebSousCatId=new TarifwebSousCatId();
		private TarifwebSousCat tarifwebSousCat=new TarifwebSousCat();
		
		
		public void PostConst(CompagnieAssurance compagnieAssurancee){
			setRcTarif12( (RcTarif12) getObjectService().getObjectById("R12"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "RcTarif12"));
			setTarif( (Tarif) getObjectService().getObjectById("T12"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "Tarif"));
		    setTarifweb((Tarifweb) getObjectService().getObjectById("T12"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "Tarifweb"));
			try { 
						if((rcTarif12 !=null) && (tarif !=null) && (tarifweb !=null) ){ 
						setRcTarif12(getRcTarif12());
						setTarif(tarif);
						setTarifweb(getTarifweb());
						}
						
						else{
						    rcTarif12=new RcTarif12();
							rcTarif12.setCodeRcTarif12("R12"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
							tarif=new Tarif();
							tarif.setCodeTarif("T12"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
							tarifweb=new Tarifweb();
							tarif.setCodeTarif("T12"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
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
			code ="R12"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"";
			codetar ="T12"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"";
		RcTarif12 rcTarif1Tempon = (RcTarif12) getObjectService().getObjectById(code, "RcTarif12");
		Tarif tarifTempon = (Tarif) getObjectService().getObjectById(codetar, "Tarif");
		
		if((rcTarif1Tempon==null) && (tarifTempon==null)){
			rcTarif12.setCodeRcTarif12("R12"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"");	
			getObjectService().addObject(rcTarif12);	
			tarif.setCodeTarif("T12"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"");
			tarif.setRcTarif12(rcTarif12);
			tarif.setLibelleTarif("Tarif 12");
			getObjectService().addObject(tarif);
			
			SousCatVehicule sousCatVehicule=new SousCatVehicule();
			sousCatVehicule.setCodeSousCatVehicule("SCAT12");
			
			tarifweb.setCodeTarifWeb(codetar);
			tarifweb.setCompagnieAssurance(getCompagnieAssuranceConnecte());
			tarifweb.setTarif(getTarif());
			tarifweb.setLibelleTarifWeb("Tarif 12");
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
			getObjectService().updateObject(rcTarif12);
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



		public RcTarif12 getRcTarif12() {
			return rcTarif12;
		}


		public void setRcTarif12(RcTarif12 rcTarif12) {
			this.rcTarif12 = rcTarif12;
		}













		public RcTarif12b getRcTarif12b() {
			return rcTarif12b;
		}





		public void setRcTarif12b(RcTarif12b rcTarif12b) {
			this.rcTarif12b = rcTarif12b;
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


	
		
	}



