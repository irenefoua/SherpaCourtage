package com.j3a.assurance.managedBean.admin;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.CompagnieAssurance;
import com.j3a.assurance.model.RcTarif1;
import com.j3a.assurance.model.RcTarif2;
import com.j3a.assurance.model.SousCatVehicule;
import com.j3a.assurance.model.Tarif;
import com.j3a.assurance.model.Tarifweb;
import com.j3a.assurance.model.TarifwebSousCat;
import com.j3a.assurance.model.TarifwebSousCatId;
import com.j3a.assurance.objetService.ObjectService;

@Component
public class Categorie2MB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    ObjectService objectService;
	private RcTarif2 rcTarif2 = new RcTarif2();
	private Tarif tarif = new Tarif();
	private  String code;
	private  String codetar;
	private CompagnieAssurance compagnieAssuranceConnecte=new CompagnieAssurance();
	private Tarifweb tarifweb=new Tarifweb();
	private TarifwebSousCatId tarifwebSousCatId=new TarifwebSousCatId();
	private TarifwebSousCat tarifwebSousCat=new TarifwebSousCat();
	
	public void PostConst(CompagnieAssurance compagnieAssurancee){
		setRcTarif2( (RcTarif2) getObjectService().getObjectById("R2"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "RcTarif2"));
		setTarif( (Tarif) getObjectService().getObjectById("T2"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "Tarif"));
	    setTarifweb((Tarifweb) getObjectService().getObjectById("T2"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "Tarifweb"));
		try { 
					if((rcTarif2 !=null) && (tarif !=null) && (tarifweb !=null) ){ 
					setRcTarif2(getRcTarif2());
					setTarif(tarif);
					setTarifweb(getTarifweb());
					}
					
					else{
					    rcTarif2=new RcTarif2();
						rcTarif2.setCodeRcTarif2("R2"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
						tarif=new Tarif();
						tarif.setCodeTarif("T2"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
						tarifweb=new Tarifweb();
						tarif.setCodeTarif("T2"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
					}
					
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("non ok !Enregistrement non recuperée");		
			}
						
			
			}
	public void addCategorie(){
		try {
			setCompagnieAssuranceConnecte(getObjectService().RecupererCompagnieCourrant());
			code ="R2"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+ "";
			codetar ="T2"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+ "";
		RcTarif2 rcTarif1Tempon = (RcTarif2) getObjectService().getObjectById(code, "RcTarif2");
		Tarif tarifTempon = (Tarif) getObjectService().getObjectById(codetar, "Tarif");
		
		if((rcTarif1Tempon==null) && (tarifTempon==null)){
			rcTarif2.setCodeRcTarif2("R2"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+ "");	
			getObjectService().addObject(rcTarif2);	
			tarif.setCodeTarif("T2"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+ "");
			tarif.setRcTarif2(rcTarif2);
			tarif.setLibelleTarif("Tarif 2");
			getObjectService().addObject(tarif);
			
			SousCatVehicule sousCatVehicule=new SousCatVehicule();
			sousCatVehicule.setCodeSousCatVehicule("SCAT2");
			
			tarifweb.setCodeTarifWeb(codetar);
			tarifweb.setCompagnieAssurance(getCompagnieAssuranceConnecte());
			tarifweb.setTarif(getTarif());
			tarifweb.setLibelleTarifWeb("Tarif 2");
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
			getObjectService().updateObject(rcTarif2);
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
	


	public ObjectService getObjectService() {
		return objectService;
	}


	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}


	public RcTarif2 getRcTarif2() {
		return rcTarif2;
	}


	public void setRcTarif2(RcTarif2 rcTarif2) {
		this.rcTarif2 = rcTarif2;
	}


	public Tarif getTarif() {
		return tarif;
	}


	public void setTarif(Tarif tarif) {
		this.tarif = tarif;
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

	public void setCompagnieAssuranceConnecte(CompagnieAssurance compagnieAssuranceConnecte) {
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
