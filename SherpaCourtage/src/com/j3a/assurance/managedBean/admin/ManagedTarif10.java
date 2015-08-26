package com.j3a.assurance.managedBean.admin;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.CompagnieAssurance;
import com.j3a.assurance.model.RcTarif10;
import com.j3a.assurance.model.SousCatVehicule;
import com.j3a.assurance.model.Tarif;
import com.j3a.assurance.model.Tarifweb;
import com.j3a.assurance.model.TarifwebSousCat;
import com.j3a.assurance.model.TarifwebSousCatId;
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
		private CompagnieAssurance compagnieAssuranceConnecte=new CompagnieAssurance();
		@Autowired
		ObjectService objectService;
		@Autowired
		IdGenerateur idGenerateur;
		private  String code;
		private  String codetar,codetarw;
		private Tarif tarif = new Tarif();
		private RcTarif10 rcTarif10 = new RcTarif10();
		private Tarifweb tarifweb=new Tarifweb();
		private TarifwebSousCatId tarifwebSousCatId=new TarifwebSousCatId();
		private TarifwebSousCat tarifwebSousCat=new TarifwebSousCat();
		public void PostConst(CompagnieAssurance compagnieAssurancee){
			setRcTarif10( (RcTarif10) getObjectService().getObjectById("R10"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "RcTarif10"));
			setTarif( (Tarif) getObjectService().getObjectById("T10"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "Tarif"));
		    setTarifweb((Tarifweb) getObjectService().getObjectById("TW10"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "Tarifweb"));
			try { 
						if((rcTarif10 !=null) && (tarif !=null) && (tarifweb !=null) ){ 
						setRcTarif10(getRcTarif10());
						setTarif(tarif);
						setTarifweb(getTarifweb());
						}
						
						else{
						    rcTarif10=new RcTarif10();
							rcTarif10.setCodeRcTarif10("R10"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
							tarif=new Tarif();
							tarif.setCodeTarif("T10"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
							tarifweb=new Tarifweb();
							tarifweb.setCodeTarifWeb("TW10"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
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
			code ="R10"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"";
			codetar ="T10"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"";
			codetarw ="TW10"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"";
		RcTarif10 rcTarif1Tempon = (RcTarif10) getObjectService().getObjectById(code, "RcTarif10");
		Tarif tarifTempon = (Tarif) getObjectService().getObjectById(codetar, "Tarif");
		
		Tarifweb tarifweb1=(Tarifweb) getObjectService().getObjectById(codetarw, "Tarifweb");
		if((rcTarif1Tempon==null) && (tarifTempon==null) && (tarifweb1==null)){
			rcTarif10.setCodeRcTarif10("R10"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+ "");	
			getObjectService().addObject(rcTarif10);	
			tarif.setCodeTarif("T10"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+ "");
			tarif.setRcTarif10(rcTarif10);
			tarif.setLibelleTarif("Tarif 10");
			getObjectService().addObject(tarif);
			
			SousCatVehicule sousCatVehicule=new SousCatVehicule();
			sousCatVehicule.setCodeSousCatVehicule("SCAT10");
			
			tarifweb.setCodeTarifWeb(codetarw);
			tarifweb.setCompagnieAssurance(getCompagnieAssuranceConnecte());
			tarifweb.setTarif(getTarif());
			tarifweb.setLibelleTarifWeb("Tarif 10");
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
			getObjectService().updateObject(rcTarif10);
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



		public RcTarif10 getRcTarif10() {
			return rcTarif10;
		}


		public void setRcTarif10(RcTarif10 rcTarif10) {
			this.rcTarif10 = rcTarif10;
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



