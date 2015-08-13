package com.j3a.assurance.managedBean.admin;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.CompagnieAssurance;
import com.j3a.assurance.model.RcTarif5;
import com.j3a.assurance.model.SousCatVehicule;
import com.j3a.assurance.model.Tarif;
import com.j3a.assurance.model.Tarifweb;
import com.j3a.assurance.model.TarifwebSousCat;
import com.j3a.assurance.model.TarifwebSousCatId;
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
		private TarifwebSousCatId tarifwebSousCatId=new TarifwebSousCatId();
		private TarifwebSousCat tarifwebSousCat=new TarifwebSousCat();
		private CompagnieAssurance compagnieAssuranceConnecte=new CompagnieAssurance();
		
		private Tarif tarif = new Tarif();
		private RcTarif5 rcTarif5 = new RcTarif5();
		private  String code;
		private  String codetar;
		private Tarifweb tarifweb=new Tarifweb();
		
		
		public void PostConst(CompagnieAssurance compagnieAssurancee){
			setRcTarif5( (RcTarif5) getObjectService().getObjectById("R5"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "RcTarif5"));
			setTarif( (Tarif) getObjectService().getObjectById("T5"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "Tarif"));
		    setTarifweb((Tarifweb) getObjectService().getObjectById("T5"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "Tarifweb"));
			try { 
						if((rcTarif5 !=null) && (tarif !=null) && (tarifweb !=null) ){ 
						setRcTarif5(getRcTarif5());
						setTarif(tarif);
						setTarifweb(getTarifweb());
						}
						
						else{
						    rcTarif5=new RcTarif5();
							rcTarif5.setCodeRcTarif5("R5"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
							tarif=new Tarif();
							tarif.setCodeTarif("T5"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
							tarifweb=new Tarifweb();
							tarif.setCodeTarif("T5"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
						}
						
					} catch (NullPointerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("non ok !Enregistrement non recuperée");		
				}
							
				
				}
		public  void enregistrer(){
			
			
			try {
				setCompagnieAssuranceConnecte(getObjectService().RecupererCompagnieCourrant());
				code ="R5"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"";
				codetar ="T5"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"";
			RcTarif5 rcTarif1Tempon = (RcTarif5) getObjectService().getObjectById(code, "RcTarif5");
			Tarif tarifTempon = (Tarif) getObjectService().getObjectById(codetar, "Tarif");
			
			if((rcTarif1Tempon==null) && (tarifTempon==null)){
				rcTarif5.setCodeRcTarif5("R5"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"");	
				getObjectService().addObject(rcTarif5);	
				tarif.setCodeTarif("T5"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"");
				tarif.setRcTarif5(rcTarif5);
				tarif.setLibelleTarif("Tarif 5");
			//	tarif.setCompagnieAssurance(getCompagnieAssuranceConnecte());
				getObjectService().addObject(tarif);
				
				SousCatVehicule sousCatVehicule=new SousCatVehicule();
				sousCatVehicule.setCodeSousCatVehicule("SCAT5");
				
				tarifweb.setCodeTarifWeb(codetar);
				tarifweb.setCompagnieAssurance(getCompagnieAssuranceConnecte());
				tarifweb.setTarif(getTarif());
				tarifweb.setLibelleTarifWeb("Tarif 5");
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
				getObjectService().updateObject(rcTarif5);
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



		
		
		
		
	
	 
		
		
	}



