package com.j3a.assurance.managedBean.admin;

import java.io.Serializable;




import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.CompagnieAssurance;
import com.j3a.assurance.model.RcTarif6;
import com.j3a.assurance.model.SousCatVehicule;
import com.j3a.assurance.model.Tarif;
import com.j3a.assurance.model.Tarifweb;
import com.j3a.assurance.model.TarifwebSousCat;
import com.j3a.assurance.model.TarifwebSousCatId;
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
		private Tarifweb tarifweb=new Tarifweb();
		@Autowired
		ObjectService objectService;
		@Autowired
		IdGenerateur idGenerateur;
		
		private CompagnieAssurance compagnieAssuranceConnecte=new CompagnieAssurance();
		private TarifwebSousCatId tarifwebSousCatId=new TarifwebSousCatId();
		private TarifwebSousCat tarifwebSousCat=new TarifwebSousCat();
		
		public CompagnieAssurance getCompagnieAssuranceConnecte() {
			return compagnieAssuranceConnecte;
		}

		public void setCompagnieAssuranceConnecte(
				CompagnieAssurance compagnieAssuranceConnecte) {
			this.compagnieAssuranceConnecte = compagnieAssuranceConnecte;
		}


		private Tarif tarif = new Tarif();
		private RcTarif6 rcTarif6 = new RcTarif6();
		
		
		private  String code;
		private  String codetar,codetarw;
;
		
		
		
		public void PostConst(CompagnieAssurance compagnieAssurancee){
			setRcTarif6( (RcTarif6) getObjectService().getObjectById("R6"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "RcTarif6"));
			setTarif( (Tarif) getObjectService().getObjectById("T6"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "Tarif"));
		    setTarifweb((Tarifweb) getObjectService().getObjectById("TW6"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "Tarifweb"));
			try { 
						if((rcTarif6 !=null) && (tarif !=null) && (tarifweb !=null) ){ 
						setRcTarif6(getRcTarif6());
						setTarif(tarif);
						setTarifweb(getTarifweb());
						}
						
						else{
						    rcTarif6=new RcTarif6();
							rcTarif6.setCodeRcTarif6("R6"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
							tarif=new Tarif();
							tarif.setCodeTarif("T6"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
							tarifweb=new Tarifweb();
							tarifweb.setCodeTarifWeb("TW6"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
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
				code ="R6"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"";
				codetar ="T6"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"";
				codetarw ="TW6"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"";
			RcTarif6 rcTarif1Tempon = (RcTarif6) getObjectService().getObjectById(code, "RcTarif6");
			Tarif tarifTempon = (Tarif) getObjectService().getObjectById(codetar, "Tarif");
			
			Tarifweb tarifweb1=(Tarifweb) getObjectService().getObjectById(codetarw, "Tarifweb");
			if((rcTarif1Tempon==null) && (tarifTempon==null) && (tarifweb1==null)){
				rcTarif6.setCodeRcTarif6("R6"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"");	
				getObjectService().addObject(rcTarif6);	
				tarif.setCodeTarif("T6"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"");
				tarif.setRcTarif6(rcTarif6);
				tarif.setLibelleTarif("Tarif 6");
				//tarif.setCompagnieAssurance(getCompagnieAssuranceConnecte());
				getObjectService().addObject(tarif);
				
				SousCatVehicule sousCatVehicule=new SousCatVehicule();
				sousCatVehicule.setCodeSousCatVehicule("SCAT6");
				
				tarifweb.setCodeTarifWeb(codetarw);
				tarifweb.setCompagnieAssurance(getCompagnieAssuranceConnecte());
				tarifweb.setTarif(getTarif());
				tarifweb.setLibelleTarifWeb("Tarif 6");
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
				getObjectService().updateObject(rcTarif6);
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



		public RcTarif6 getRcTarif6() {
			return rcTarif6;
		}


		public void setRcTarif6(RcTarif6 rcTarif6) {
			this.rcTarif6 = rcTarif6;
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



