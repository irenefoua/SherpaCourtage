package com.j3a.assurance.managedBean.admin;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.Categorie;
import com.j3a.assurance.model.CompagnieAssurance;
import com.j3a.assurance.model.RcTarif1;
import com.j3a.assurance.model.SousCatVehicule;
import com.j3a.assurance.model.Tarif;
import com.j3a.assurance.model.Tarifweb;
import com.j3a.assurance.model.TarifwebSousCat;
import com.j3a.assurance.model.TarifwebSousCatId;
import com.j3a.assurance.objetService.ObjectService;

@Component
public class Categorie1MB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
    ObjectService objectService;
	private RcTarif1 rcTarif1 = new RcTarif1();
	private Tarif tarif =new Tarif();
	private Tarifweb tarifweb=new Tarifweb();
	private  String code;
	private  String codetar;
	 private CompagnieAssurance compagnieAssuranceConnecte=new CompagnieAssurance();
	private RcTarif1 monRcTarif1= new RcTarif1();
	private TarifwebSousCatId tarifwebSousCatId=new TarifwebSousCatId();
	private TarifwebSousCat tarifwebSousCat=new TarifwebSousCat();
	
	
	
	
	
	public void PostConst(CompagnieAssurance compagnieAssurancee){
	setMonRcTarif1( (RcTarif1) getObjectService().getObjectById("R1"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "RcTarif1"));
	setTarif( (Tarif) getObjectService().getObjectById("T1"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "Tarif"));
    setTarifweb((Tarifweb) getObjectService().getObjectById("T1"+compagnieAssurancee.getCodeCompagnieAssurance()+"", "Tarifweb"));
	try { 
				if((monRcTarif1 !=null) && (tarif !=null) && (tarifweb !=null) ){ 
				setRcTarif1(monRcTarif1 );
				setTarif(tarif);
				setTarifweb(getTarifweb());
				}
				
				else{
				    rcTarif1=new RcTarif1();
					rcTarif1.setCodeRcTarif1("R1"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
					tarif=new Tarif();
					tarif.setCodeTarif("T1"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
					tarifweb=new Tarifweb();
					tarif.setCodeTarif("T1"+compagnieAssurancee.getCodeCompagnieAssurance()+ "");
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
			code ="R1"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"";
			codetar ="T1"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"";
		RcTarif1 rcTarif1Tempon = (RcTarif1) getObjectService().getObjectById(code, "RcTarif1");
		Tarif tarifTempon = (Tarif) getObjectService().getObjectById(codetar, "Tarif");
		
		if((rcTarif1Tempon==null) && (tarifTempon==null)){
			rcTarif1.setCodeRcTarif1("R1"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"");	
			getObjectService().addObject(rcTarif1);	
			tarif.setCodeTarif("T1"+compagnieAssuranceConnecte.getCodeCompagnieAssurance()+"");
			tarif.setRcTarif1(rcTarif1);
			tarif.setLibelleTarif("Tarif 1");
			getObjectService().addObject(tarif);
			
			tarifweb.setCodeTarifWeb(codetar);
			tarifweb.setCompagnieAssurance(getCompagnieAssuranceConnecte());
			tarifweb.setTarif(getTarif());
			tarifweb.setLibelleTarifWeb("Tarif 1");
			getObjectService().addObject(tarifweb);
			
			SousCatVehicule sousCatVehicule=new SousCatVehicule();
			sousCatVehicule.setCodeSousCatVehicule("SCAT1");
			
			tarifwebSousCatId=new TarifwebSousCatId();
			tarifwebSousCatId.setCodeSousCatVehicule("SCAT1");
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
			getObjectService().updateObject(rcTarif1);
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
	
	
	public RcTarif1 getRcTarif1() {
		return rcTarif1;
	}


	public void setRcTarif1(RcTarif1 rcTarif1) {
		this.rcTarif1 = rcTarif1;
	}


	public Tarif getTarif() {
		return tarif;
	}


	public void setTarif(Tarif tarif) {
		this.tarif = tarif;
	}


	public ObjectService getObjectService() {
		return objectService;
	}
	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
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
		setCompagnieAssuranceConnecte(getObjectService().RecupererCompagnieCourrant());
		
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



	public RcTarif1 getMonRcTarif1() {
		return monRcTarif1;
	}



	public void setMonRcTarif1(RcTarif1 monRcTarif1) {
		this.monRcTarif1 = monRcTarif1;
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
