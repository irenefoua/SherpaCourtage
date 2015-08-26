package com.j3a.assurance.managedBean.admin;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.CompagnieAssurance;
import com.j3a.assurance.model.RcTarif1;
import com.j3a.assurance.model.RcTarif3;
import com.j3a.assurance.model.SousCatVehicule;
import com.j3a.assurance.model.Tarif;
import com.j3a.assurance.model.Tarifweb;
import com.j3a.assurance.model.TarifwebSousCat;
import com.j3a.assurance.model.TarifwebSousCatId;
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
		private Tarifweb tarifweb=new Tarifweb();
		@Autowired
		ObjectService objectService;
		@Autowired
		IdGenerateur idGenerateur;
		
		
		private TarifwebSousCatId tarifwebSousCatId=new TarifwebSousCatId();
		private TarifwebSousCat tarifwebSousCat=new TarifwebSousCat();
		private Tarif tarif = new Tarif();
		private RcTarif3 rcTarif3 = new RcTarif3();
		private  String code;
		private  String codetar,codetarw;
		
		private CompagnieAssurance compagnieAssuranceConnecte=new CompagnieAssurance();
		
		
		
		public void PostConst(CompagnieAssurance compagnieAssurancee){
			setRcTarif3( (RcTarif3) getObjectService().getObjectById("R3"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "RcTarif3"));
			setTarif( (Tarif) getObjectService().getObjectById("T3"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "Tarif"));
		    setTarifweb((Tarifweb) getObjectService().getObjectById("TW3"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "Tarifweb"));
			try { 
						if((rcTarif3 !=null) && (tarif !=null) && (tarifweb !=null) && (tarifweb !=null) ){ 
						setRcTarif3(getRcTarif3());
						setTarif(tarif);
						setTarifweb(getTarifweb());
						}
						
						else{
						    rcTarif3=new RcTarif3();
							rcTarif3.setCodeRcTarif3("R3"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
							tarif=new Tarif();
							tarif.setCodeTarif("T3"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
							tarifweb=new Tarifweb();
							tarifweb.setCodeTarifWeb("TW3"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
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
				code ="R3"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"";
				codetar ="T3"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"";
			RcTarif3 rcTarif1Tempon = (RcTarif3) getObjectService().getObjectById(code, "RcTarif3");
			Tarif tarifTempon = (Tarif) getObjectService().getObjectById(codetar, "Tarif");
			
			Tarifweb tarifweb1=(Tarifweb) getObjectService().getObjectById(codetarw, "Tarifweb");
			if((rcTarif1Tempon==null) && (tarifTempon==null) && (tarifweb1==null)){
				rcTarif3.setCodeRcTarif3("R3"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+ "");	
				getObjectService().addObject(rcTarif3);	
				tarif.setCodeTarif("T3"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+ "");
				tarif.setRcTarif3(rcTarif3);
				tarif.setLibelleTarif("Tarif 3");
				getObjectService().addObject(tarif);
				
				SousCatVehicule sousCatVehicule=new SousCatVehicule();
				sousCatVehicule.setCodeSousCatVehicule("SCAT3");
				
				
				tarifweb.setCodeTarifWeb(codetarw);
				tarifweb.setCompagnieAssurance(getCompagnieAssuranceConnecte());
				tarifweb.setTarif(getTarif());
				tarifweb.setLibelleTarifWeb("Tarif 3");
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
				getObjectService().updateObject(rcTarif3);
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



