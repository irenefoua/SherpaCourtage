package com.j3a.assurance.managedBean.admin;

import java.io.Serializable;




import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.CompagnieAssurance;
import com.j3a.assurance.model.RcTarif4;
import com.j3a.assurance.model.SousCatVehicule;
import com.j3a.assurance.model.Tarif;
import com.j3a.assurance.model.Tarifweb;
import com.j3a.assurance.model.TarifwebSousCat;
import com.j3a.assurance.model.TarifwebSousCatId;
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
		private TarifwebSousCatId tarifwebSousCatId=new TarifwebSousCatId();
		private TarifwebSousCat tarifwebSousCat=new TarifwebSousCat();
		@Autowired
		ObjectService objectService;
		@Autowired
		IdGenerateur idGenerateur;
		private CompagnieAssurance compagnieAssuranceConnecte=new CompagnieAssurance();
		private Tarifweb tarifweb=new Tarifweb();
		
		public CompagnieAssurance getCompagnieAssuranceConnecte() {
			return compagnieAssuranceConnecte;
		}

		public void setCompagnieAssuranceConnecte(
				CompagnieAssurance compagnieAssuranceConnecte) {
			this.compagnieAssuranceConnecte = compagnieAssuranceConnecte;
		}

		private Tarif tarif = new Tarif();
		private RcTarif4 rcTarif4 = new RcTarif4();
		
		
		private  String code ;
		private  String codetar,codetarw ;
		
		
		public void PostConst(CompagnieAssurance compagnieAssurancee){
			setRcTarif4( (RcTarif4) getObjectService().getObjectById("R4"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "RcTarif4"));
			setTarif( (Tarif) getObjectService().getObjectById("T4"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "Tarif"));
		    setTarifweb((Tarifweb) getObjectService().getObjectById("TW4"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "Tarifweb"));
			try { 
						if((rcTarif4 !=null) && (tarif !=null) && (tarifweb !=null) ){ 
						setRcTarif4(getRcTarif4());
						setTarif(tarif);
						setTarifweb(getTarifweb());
						}
						
						else{
						    rcTarif4=new RcTarif4();
							rcTarif4.setCodeRcTarif4("R4"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
							tarif=new Tarif();
							tarif.setCodeTarif("T4"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
							tarifweb=new Tarifweb();
							tarifweb.setCodeTarifWeb("TW4"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
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
			code ="R4"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"";
			codetar ="T4"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"";
			codetarw ="TW4"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"";
		RcTarif4 rcTarif1Tempon = (RcTarif4) getObjectService().getObjectById(code, "RcTarif4");
		Tarif tarifTempon = (Tarif) getObjectService().getObjectById(codetar, "Tarif");
		
		Tarifweb tarifweb1=(Tarifweb) getObjectService().getObjectById(codetarw, "Tarifweb");
		if((rcTarif1Tempon==null) && (tarifTempon==null) && (tarifweb1==null)){
			rcTarif4.setCodeRcTarif4("R4"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"");	
			getObjectService().addObject(rcTarif4);	
			tarif.setCodeTarif("T4"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"");
			tarif.setRcTarif4(rcTarif4);
			tarif.setLibelleTarif("Tarif 4");
			getObjectService().addObject(tarif);
			
			SousCatVehicule sousCatVehicule=new SousCatVehicule();
			sousCatVehicule.setCodeSousCatVehicule("SCAT4");
			
			tarifweb.setCodeTarifWeb(codetarw);
			tarifweb.setCompagnieAssurance(getCompagnieAssuranceConnecte());
			tarifweb.setTarif(getTarif());
			tarifweb.setLibelleTarifWeb("Tarif 4");
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
			getObjectService().updateObject(rcTarif4);
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



