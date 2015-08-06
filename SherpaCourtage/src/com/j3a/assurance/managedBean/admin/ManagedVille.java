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
import com.j3a.assurance.model.Province;
import com.j3a.assurance.model.Ville;
import com.j3a.assurance.objetService.ObjectService;

@Component
@Scope("session")
public class ManagedVille implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ManagedVille.class);

	@Autowired	
	ObjectService objectService;

	private String id;

	private String libelleVille;

	private ZoneGeographique codeZoneGeo = new ZoneGeographique();
	private Province codeProvince = new Province();

	private List<SelectItem> elements;
	private List<Ville> VilleList;

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
			Ville ville = new Ville();
			ville.setCodeVille(getId());
			ville.setLibelleVille(getLibelleVille());
			ville.setZoneGeographique(getCodeZoneGeo());
			ville.setProvince(getCodeProvince());
			FacesMessage message = new FacesMessage("");
			boolean checkeixte = false;
			boolean checkeixtezonegeo = false;
			boolean checkeixteprovince = false;
			for (Object obj : getObjectService().getObjects("Ville")) {
				if (ville.getCodeVille().equals(((Ville) obj).getCodeVille())) {
					checkeixte = true;
				}
			}
			for (Object obj : getObjectService().getObjects("ZoneGeographique")) {

				if (ville.getZoneGeographique().getCodeZoneGeo()
						.equals(((ZoneGeographique) obj).getCodeZoneGeo())) {
					checkeixtezonegeo = true;
				}
			}
			

			for (Object obj : getObjectService().getObjects("Province")) {

				if (ville.getProvince().getCodeProvince().equals(((Province) obj).getCodeProvince())) {
					checkeixteprovince = true;
				}
			}

			if (checkeixte) {
				message = new FacesMessage(
						"  le code ville existe déjà dans la base de donnée!");
			} else {
				if ((checkeixtezonegeo) && (checkeixteprovince)) {
					getObjectService().addObject(ville);
					message = new FacesMessage(
							"  le code ville est bien enregistré dans la base de donnée!");
				} else {
					if ((checkeixteprovince) && (!checkeixtezonegeo)) {
						message = new FacesMessage(
								"  le code zonegeo n'existe pas dans la base de donnée!");
					}
					if ((!checkeixteprovince) && (checkeixtezonegeo)) {
						message = new FacesMessage(
								"  le code province n'existe pas dans la base de donnée!");
					}
					if ((!checkeixteprovince) && (!checkeixtezonegeo)) {
						message = new FacesMessage(
								"  le code zonegeo et code province n'existe pas dans la base de donnée!");
					}
				}
			}
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(buttonadd.getClientId(context), message);
		} catch (Exception e) {
			logger.error("Echec dans la methode add de la Ville", e);
		}
		return null;

		// return SUCCESS;

		// return ERROR;
	}

	public String validateupdate() {// supprimer une ligne dans la base de
									// donnée

		try {
			Ville ville = new Ville();
			ville.setCodeVille(getId());
			ville.setLibelleVille(getLibelleVille());
			ville.setZoneGeographique(getCodeZoneGeo());
			ville.setProvince(getCodeProvince());
			FacesMessage message = new FacesMessage("");
			boolean checkeixte = false;
			for (Object obj : getObjectService().getObjects("Ville")) {
				if (ville.getCodeVille().equals(((Ville) obj).getCodeVille())) {
					checkeixte = true;
				}
			}
			if (checkeixte) {
				getObjectService().updateObject(ville);
				message = new FacesMessage(
						"  le code ville est bien modifié dans la base de donnée!");
			} else {
				message = new FacesMessage(
						"  Il n'existe pas ce code ville dans la base de donnée!");
			}
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(buttonupdate.getClientId(context), message);
		} catch (Exception e) {
			logger.error("Echec dans la methode update de la Ville", e);
		}
		return null;

	}

	public String validatedelete() {// supprimer une ligne dans la base de
									// donnée

		try {
			Ville ville = new Ville();
			ville.setCodeVille(getId());
			ville.setLibelleVille(getLibelleVille());
			ville.setZoneGeographique(getCodeZoneGeo());
			ville.setProvince(getCodeProvince());
			FacesMessage message = new FacesMessage("");
			boolean checkeixte = false;
			for (Object obj : getObjectService().getObjects("Ville")) {
				if (ville.getCodeVille().equals(((Ville) obj).getCodeVille())) {
					checkeixte = true;
				}
			}
			if (checkeixte) {
				getObjectService().deleteObject(ville);
				message = new FacesMessage(
						"   le code ville est bien supprimé dans la base de donnée!");
			} else {
				message = new FacesMessage(
						"  Il n'existe pas ce code ville dans la base de donnée!");
			}
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(buttondelete.getClientId(context), message);
		} catch (Exception e) {
			logger.error("Echec dans la methode delete de la Ville", e);
		}
		return null;

	}

	/*
	 * public void updatezonegeo() {// mise à jour des modifications dans la
	 * base // de donnée try { SocieteAssurance zonegeo = (SocieteAssurance)
	 * getObjectService() .getObjectById(getId(), "SocieteAssurance");
	 * zonegeo.setId(getId());
	 * zonegeo.setNomSocieteAssurance(getNomSocieteAssurance());
	 * getObjectService().updateObject(zonegeo); } catch (DataAccessException e)
	 * { e.printStackTrace(); }
	 * 
	 * // return ERROR; }
	 */

	public List<SelectItem> getElements() {

		if (elements == null) {
			elements = new ArrayList<SelectItem>();
			for (Object obj : getObjectService().getObjects("SocieteAssurance")) {

				elements.add(new SelectItem(((Ville) obj).getCodeVille(),
						((Ville) obj).getLibelleVille()));

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

	public String getLibelleVille() {
		return libelleVille;
	}

	public void setLibelleVille(String libelleVille) {
		this.libelleVille = libelleVille;
	}

	public ZoneGeographique getCodeZoneGeo() {
		return codeZoneGeo;
	}

	public void setCodeZoneGeo(ZoneGeographique codeZoneGeo) {
		this.codeZoneGeo = codeZoneGeo;
	}

	public List<Ville> getVilleList() {
		VilleList = new ArrayList<Ville>();
		List<Object> listObject = getObjectService().getObjects("Ville");
		for (Iterator it = listObject.iterator(); it.hasNext();) {
			Ville ville = (Ville) it.next();
			try {
				VilleList.add(ville);
			} catch (Exception e) {
			}
		}
		return VilleList;
	}

	public void setVilleList(List<Ville> villeList) {
		this.VilleList = villeList;
	}

	public Province getCodeProvince() {
		return codeProvince;
	}

	public void setCodeProvince(Province codeProvince) {
		this.codeProvince = codeProvince;
	}

}
