package com.j3a.assurance.managedBean.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;



import org.springframework.stereotype.Component;

import com.j3a.assurance.model.ZoneGeographique;
import com.j3a.assurance.objetService.ObjectService;

@Component
@Scope("session")
public class ManagedZoneGeographique implements Serializable {

	private static final long serialVersionUID = 1L;
	private static  Logger logger=Logger.getLogger(ManagedZoneGeographique.class);
	
	@Autowired
	ObjectService objectService;

	private String id;
	private String libelleZoneGeo;
	private String txt1;

	private List<SelectItem> elements;
	private List<ZoneGeographique> ZoneGeographiqueList;

	private UIComponent buttonadd;
	private UIComponent buttonupdate;
	private UIComponent buttondelete;

	public List<String> complete(String query) {
		List<String> results = new ArrayList<String>();

		for (int i = 0; i < 10; i++) {
			results.add(query + i);
		}

		return results;
	}

	public String validateadd() {// ajouter une ligne dans la base de donnée

		try {
			ZoneGeographique zonegeo = new ZoneGeographique();
			zonegeo.setCodeZoneGeo(getId());
			zonegeo.setLibelleZoneGeo(getLibelleZoneGeo());
			FacesMessage message = new FacesMessage("");
			boolean checkeixte = false;
			for (Object obj : getObjectService().getObjects("ZoneGeographique")) {
				if (zonegeo.getCodeZoneGeo().equals(((ZoneGeographique) obj).getCodeZoneGeo())) {
					checkeixte = true;
				}
			}
			if (checkeixte) {
				message = new FacesMessage(
						"  le code Zone Geographique existe déjà dans la base de donnée!");
			} else {
				getObjectService().addObject(zonegeo);
				message = new FacesMessage(
						"  le code Zone Geographique est bien enregistré dans la base de donnée!");
			}
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(buttonadd.getClientId(context), message);
		} catch (Exception e) {
			logger.error("Echec dans la methode add de la ZoneGeographique", e);
		}
		return null;

		// return SUCCESS;

		// return ERROR;
	}

	public String validateupdate() {// supprimer une ligne dans la base de
									// donnée

		try {
			ZoneGeographique zonegeo = new ZoneGeographique();
			zonegeo.setCodeZoneGeo(getId());
			zonegeo.setLibelleZoneGeo(getLibelleZoneGeo());
			FacesMessage message = new FacesMessage("");
			boolean checkeixte = false;
			for (Object obj : getObjectService().getObjects("ZoneGeographique")) {
				if (zonegeo.getCodeZoneGeo().equals(((ZoneGeographique) obj).getCodeZoneGeo())) {
					checkeixte = true;
				}
			}
			if (checkeixte) {
				getObjectService().updateObject(zonegeo);
				message = new FacesMessage(
						"  le code Zone Geographique est bien modifié dans la base de donnée!");
			} else {
				message = new FacesMessage(
						"  Il n'existe pas ce code Zone Geographique dans la base de donnée!");
			}
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(buttonupdate.getClientId(context), message);
		} catch (Exception e) {
			logger.error("Echec dans la methode update de la ZoneGeographique", e);
		}
		return null;

	}

	public String validatedelete() {// supprimer une ligne dans la base de
									// donnée

		try {
			ZoneGeographique zonegeo = new ZoneGeographique();
			zonegeo.setCodeZoneGeo(getId());
			zonegeo.setLibelleZoneGeo(getLibelleZoneGeo());
			FacesMessage message = new FacesMessage("");
			boolean checkeixte = false;
			for (Object obj : getObjectService().getObjects("ZoneGeographique")) {
				if (zonegeo.getCodeZoneGeo().equals(((ZoneGeographique) obj).getCodeZoneGeo())) {
					checkeixte = true;
				}
			}
			if (checkeixte) {
				getObjectService().deleteObject(zonegeo);
				message = new FacesMessage(
						"   le code Zone Geographique est bien supprimé dans la base de donnée!");
			} else {
				message = new FacesMessage(
						"  Il n'existe pas ce code Zone Geographique dans la base de donnée!");
			}
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(buttondelete.getClientId(context), message);
		} catch (Exception e) {
			logger.error("Echec dans la methode deleta de la ZoneGeographique", e);
		}
		return null;

	}

	/*
	 * public void updatezonegeo() {// mise à jour des modifications dans la
	 * base // de donnée try { ZoneGeographique zonegeo = (ZoneGeographique)
	 * getObjectService() .getObjectById(getId(), "ZoneGeographique");
	 * zonegeo.setId(getId()); zonegeo.setLibelleZoneGeo(getLibelleZoneGeo());
	 * getObjectService().updateObject(zonegeo); } catch (DataAccessException e)
	 * { e.printStackTrace(); }
	 * 
	 * // return ERROR; }
	 */
	public List<ZoneGeographique> getZoneGeographiqueList() {
		ZoneGeographiqueList = new ArrayList<ZoneGeographique>();
		List<Object> listObject = getObjectService().getObjects(
				"ZoneGeographique");
		for (Iterator it = listObject.iterator(); it.hasNext();) {
			ZoneGeographique zonegeo = (ZoneGeographique) it.next();
			try {
				ZoneGeographiqueList.add(zonegeo);
			} catch (Exception e) {
			}

		}
		return ZoneGeographiqueList;
	}

	public void setZoneGeographiqueList(List<ZoneGeographique> zoneGeographiqueList) {
		this.ZoneGeographiqueList = zoneGeographiqueList;
	}

	public List<SelectItem> getElements() {

		if (elements == null) {
			elements = new ArrayList<SelectItem>();
			for (Object obj : getObjectService().getObjects("ZoneGeographique")) {

				elements.add(new SelectItem(((ZoneGeographique) obj).getCodeZoneGeo(),
						((ZoneGeographique) obj).getLibelleZoneGeo()));

			}
		}
		return elements;
	}

	public void setElements(List<SelectItem> elements) {
		this.elements = elements;
	}

	/**
	 * Reset Fields
	 * 
	 */
	public void reset() {
		this.setId("");
		this.setLibelleZoneGeo("");
	}

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

	public String getLibelleZoneGeo() {
		return libelleZoneGeo;
	}

	public void setLibelleZoneGeo(String libelleZoneGeo) {
		this.libelleZoneGeo = libelleZoneGeo;
	}

	public String getTxt1() {
		return txt1;
	}

	public void setTxt1(String txt1) {
		this.txt1 = txt1;
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

}
