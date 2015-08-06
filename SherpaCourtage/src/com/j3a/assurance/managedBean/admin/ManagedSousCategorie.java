package com.j3a.assurance.managedBean.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.Categorie;
import com.j3a.assurance.model.SousCatVehicule;
import com.j3a.assurance.model.Tarif;
import com.j3a.assurance.objetService.ObjectService;

@Component
public class ManagedSousCategorie implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 @Autowired
	 ObjectService objectService;

	 private String id;
	 private String libelleCategorie;
	 
	 private List<SelectItem> elements;
	 private List<SousCatVehicule> sousCatVehiculeList;
	 
	 private UIComponent buttonadd;
	 private UIComponent buttonupdate;
	 private UIComponent buttondelete;
	 private SousCatVehicule sousCatVehiculeSup;
	 private String slctCategorie;
	 private String sltTarif;
	 private List<Categorie> categorieList;
	 private List<Tarif> tarifList;

	 public ObjectService getObjectService() {
		 return objectService;
	 }

	 public void setObjectService(ObjectService objectService) {
		 this.objectService = objectService;
	 }
	 
	public String getId() {
		 
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public List<SousCatVehicule> getCategoriList() {	
		sousCatVehiculeList = new ArrayList<SousCatVehicule>();
		List<Object> listObject = getObjectService().getObjects("SousCatVehicule");			
		for (Iterator it = listObject.iterator(); it.hasNext();) {
			SousCatVehicule sousCatVehicule = (SousCatVehicule) it.next();
			try {
				sousCatVehiculeList.add(sousCatVehicule);
			} catch (Exception e) {
			}
	
		}
		return sousCatVehiculeList;
	}		

	
	public List<SelectItem> getElements() {
		
		if (elements == null) {
			 elements = new ArrayList<SelectItem>();
			 for (Object obj : getObjectService().getObjects("SousCatVehicule")) {
		
		        	elements.add(new SelectItem(  ((SousCatVehicule) obj).getCodeSousCatVehicule(),   ((SousCatVehicule) obj).getLibelleSousCatVehicule()));
		        
			 }
		    }
		return elements;
	}

	public void setElements(List<SelectItem> elements) {
		this.elements = elements;
	}

		

	public void setButtonadd(UIComponent buttonadd) {
	        this.buttonadd = buttonadd;
	    }
	
	    public UIComponent getButtonadd() {
	        return buttonadd;
	    }
	 
	
	public UIComponent getButtonupdate() {
		return buttonupdate;
	}
	public void setButtonupdate(UIComponent buttonupdate) {
		this.buttonupdate = buttonupdate;
	}
	
	public UIComponent getButtondelete() {
		return buttondelete;
	}
	public void setButtondelete(UIComponent buttondelete) {
		this.buttondelete = buttondelete;
	}
	
	/**
	  * Reset Fields
	  *
	  */
	public void reset() {
		this.setId("");
		this.setLibelleCategorie("");
	}
	
	public String validateadd() { 
	    String key = getObjectService().getCodeTable("SCAT", 4, 2,"sous_cat_vehicule", "CODE_SOUS_CAT_VEHICULE");
	    SousCatVehicule sousCatVehicule = new SousCatVehicule();
	    sousCatVehicule.setCodeSousCatVehicule(key);
	    sousCatVehicule.setLibelleSousCatVehicule(getLibelleCategorie());
	    
	    Categorie categorie=new Categorie();
	    categorie.setCodeCategorie(slctCategorie);
	    sousCatVehicule.setCategorie(categorie);
	    
	    Tarif tarif=new Tarif();
	    tarif.setCodeTarif(sltTarif);
	    sousCatVehicule.setTarif(tarif);
	    
	    //Enregistrement en base
	    getObjectService().addObject(sousCatVehicule);
	    viderCategorie();
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
       		(FacesMessage.SEVERITY_INFO,"Gestion des Sous Categorie", "Categorie enregistrée!"));  
       
      // org.primefaces.context.RequestContext.getCurrentInstance().update("growl");
       System.out.println("Enregistremen ok!");//Clean after
		return null;

	}
	
	
	public void affecterCategorie(){
		setLibelleCategorie(getSousCatVehiculeSup().getLibelleSousCatVehicule());
		
	}
	
	
	public void viderCategorie(){
		setLibelleCategorie("");
	}
	
	
	public String validateupdate() {
		sousCatVehiculeSup.setLibelleSousCatVehicule(libelleCategorie);
		getObjectService().updateObject(sousCatVehiculeSup);
		viderCategorie();
		 FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,"Succès"," Modification efféctué");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
	}
	
	   
	public void validatedelete()  {
		try {
			getObjectService().deleteObject(sousCatVehiculeSup);
		} catch ( org.springframework.dao.DataIntegrityViolationException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
	        		(FacesMessage.SEVERITY_ERROR,"Erreur", "Cette Catégorie ne peut être suprimée"));
		}
		viderCategorie();
	}

	
	public List<Categorie> getCategLorielist() {
		categorieList = new ArrayList<Categorie>();
		List<Object> listObject = getObjectService().getObjects("Categorie");			
		for (Iterator it = listObject.iterator(); it.hasNext();) {
			Categorie categorie = (Categorie) it.next();
			try {
				categorieList.add(categorie);
			} catch (Exception e) {
			}
	
		}
		return categorieList;
	}
	

	public List<Tarif> getTariflist() {
		tarifList = new ArrayList<Tarif>();
		List<Object> listObject = getObjectService().getObjects("Tarif");			
		for (Iterator it = listObject.iterator(); it.hasNext();) {
			Tarif tarif = (Tarif) it.next();
			try {
				tarifList.add(tarif);
			} catch (Exception e) {
			}
	
		}
		return tarifList;
	}


	public String getLibelleCategorie() {
		return libelleCategorie;
	}

	public void setLibelleCategorie(String libelleCategorie) {
		this.libelleCategorie = libelleCategorie;
	}

	public List<SousCatVehicule> getSousCatVehiculeList() {
		getCategoriList();
		return sousCatVehiculeList;
	}

	public void setSousCatVehiculeList(List<SousCatVehicule> sousCatVehiculeList) {
		this.sousCatVehiculeList = sousCatVehiculeList;
	}

	public SousCatVehicule getSousCatVehiculeSup() {
		return sousCatVehiculeSup;
	}

	public void setSousCatVehiculeSup(SousCatVehicule sousCatVehiculeSup) {
		this.sousCatVehiculeSup = sousCatVehiculeSup;
	}

	public String getSlctCategorie() {
		return slctCategorie;
	}

	public void setSlctCategorie(String slctCategorie) {
		this.slctCategorie = slctCategorie;
	}

	public String getSltTarif() {
		return sltTarif;
	}

	public void setSltTarif(String sltTarif) {
		this.sltTarif = sltTarif;
	}

	public List<Categorie> getCategorieList() {
		getCategLorielist();
		return categorieList;
	}

	public void setCategorieList(List<Categorie> categorieList) {
		this.categorieList = categorieList;
	}

	public List<Tarif> getTarifList() {
		getTariflist();
		return tarifList;
	}

	public void setTarifList(List<Tarif> tarifList) {
		this.tarifList = tarifList;
	}


	

	

	
	
	
	
}
