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
import com.j3a.assurance.model.Risque;
import com.j3a.assurance.objetService.ObjectService;

@Component
public class MnagedCategorie implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 @Autowired
	 ObjectService objectService;

	 private String id;
	 private String libelleCategorie;
	 
	 private List<SelectItem> elements;
	 private List<Categorie> categorieList;
	 
	 private UIComponent buttonadd;
	 private UIComponent buttonupdate;
	 private UIComponent buttondelete;
	 private Categorie categorieSup;
	 private String slctRisque;
	 private List<Risque> risqueList;
	

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

	

	public List<Categorie> getCategoriList() {	
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

	
	public List<SelectItem> getElements() {
		
		if (elements == null) {
			 elements = new ArrayList<SelectItem>();
			 for (Object obj : getObjectService().getObjects("Categorie")) {
		
		        	elements.add(new SelectItem(  ((Categorie) obj).getCodeCategorie(),   ((Categorie) obj).getLibelleCategorie()));
		        
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
	    String key = getObjectService().getCodeTable("Categorie", 5, 3,"categorie", "CODE_CATEGORIE");
	    Categorie categorie = new Categorie();
	    categorie.setCodeCategorie(key);
	    categorie.setLibelleCategorie(getLibelleCategorie());
	    Risque risque=new Risque();
	    risque.setCodeRisque(getSlctRisque());
	    categorie.setRisque(risque);
	    //Enregistrement en base
	    getObjectService().addObject(categorie);
	    viderCategorie();
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
       		(FacesMessage.SEVERITY_INFO,"Gestion des Categorie", "Categorie enregistrée!"));  
       
      // org.primefaces.context.RequestContext.getCurrentInstance().update("growl");
       System.out.println("Enregistremen ok!");//Clean after
		return null;

	}
	
	
	public void affecterCategorie(){
		setLibelleCategorie(getCategorieSup().getLibelleCategorie());
		
	}
	
	
	public void viderCategorie(){
		setLibelleCategorie("");
	}
	
	
	public String validateupdate() {
		categorieSup.setLibelleCategorie(libelleCategorie);
		getObjectService().updateObject(categorieSup);
		viderCategorie();
		 FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_INFO,"Succès"," Modification efféctué");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
	}
	
	   
	public void validatedelete()  {
		try {
			getObjectService().deleteObject(categorieSup);
		} catch ( org.springframework.dao.DataIntegrityViolationException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
	        		(FacesMessage.SEVERITY_ERROR,"Erreur", "Cette Catégorie ne peut être suprimée"));
		}
		viderCategorie();
	}

	

	

	

	public List<Categorie> getCategorieList() {
		getCategoriList();
		return categorieList;
	}

	public void setCategorieList(List<Categorie> categorieList) {
		this.categorieList = categorieList;
	}

	public String getLibelleCategorie() {
		return libelleCategorie;
	}

	public void setLibelleCategorie(String libelleCategorie) {
		this.libelleCategorie = libelleCategorie;
	}

	public String getSlctRisque() {
		return slctRisque;
	}

	public void setSlctRisque(String slctRisque) {
		this.slctRisque = slctRisque;
	}

	public List<Risque> getRisqueList() {
		risqueList = new ArrayList<Risque>();
		List<Object> listObject = getObjectService().getObjects("Risque");			
		for (Iterator it = listObject.iterator(); it.hasNext();) {
			Risque risque = (Risque) it.next();
			try {
				risqueList.add(risque);
				System.out.println("risque"+risqueList.size());
			} catch (Exception e) {
			}
	
		}
		return risqueList;
	}

	public void setRisqueList(List<Risque> risqueList) {
		this.risqueList = risqueList;
	}

	public Categorie getCategorieSup() {
		return categorieSup;
	}

	public void setCategorieSup(Categorie categorieSup) {
		this.categorieSup = categorieSup;
	}
	
	
	
}
