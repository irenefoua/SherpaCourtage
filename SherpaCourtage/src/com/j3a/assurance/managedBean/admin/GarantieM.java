package com.j3a.assurance.managedBean.admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.Garantie;
import com.j3a.assurance.model.Risque;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.prime.categorie.PrimeCategorie;
import com.j3a.assurance.utilitaires.Garanties;

@Component
public class GarantieM {
	Logger logger = Logger.getLogger(GarantieM.class);
	
	public List<Garanties> listeGroupGaranties = new ArrayList<Garanties>();
	public List<Garanties> listegarantieFiltre = new ArrayList<Garanties>();
	public List<Garanties> listegarantieAll = new ArrayList<Garanties>();

	public Garanties selectGarantieGroup;
	@Autowired
    ObjectService objectService;
	public String mouvement;
	public Garantie objetGarantie;
	private List<Garantie> garantiesChoisies;
	private List<Garantie> mesGarantiesList;
	public String monString;
    private String codeCategorie;
	// déclaration & appels par Benhanna
	private String id;
	private String libelleGarantie;
	private List<Garantie> GarantiesList;
	private String slctRisque;
	 private List<Risque> risqueList;
	private UIComponent buttonadd;
	private UIComponent buttonupdate;
	private UIComponent buttondelete;
	private PrimeCategorie primeCategorie = new PrimeCategorie();

	public GarantieM() {
	}

	public String validateadd() {// ajouter une ligne dans la base de donnée

		Garantie garantie = new Garantie();
		garantie.setCodeGarantie(getId());
		garantie.setLibelleGarantie(getLibelleGarantie());
		garantie.setCategorieGarantie(getCodeCategorie());
		 Risque risque=new Risque();
		    risque.setCodeRisque(getSlctRisque());
		    garantie.setRisque(risque);
		FacesMessage message = new FacesMessage("");
		boolean checkeixte = false;
		for (Object obj : getObjectService().getObjects("Garantie")) {
			if (garantie.getCodeGarantie().equals(((Garantie) obj).getCodeGarantie())) {
				checkeixte = true;
			}
		}
		if (checkeixte) {
			message = new FacesMessage(
					"  le code Granatie existe déjà dans la base de donnée!");
		} else {
			getObjectService().addObject(garantie);
			message = new FacesMessage(
					"  le code Garantie est bien enregistré dans la base de donnée!");
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(buttonadd.getClientId(context), message);
		return null;

		// return SUCCESS;

		// return ERROR;
	}

	public String validateupdate() {// supprimer une ligne dans la base de
		// donnée

		Garantie garantie = new Garantie();
		garantie.setCodeGarantie(getId());
		garantie.setLibelleGarantie(getLibelleGarantie());
		FacesMessage message = new FacesMessage("");
		boolean checkeixte = false;
		for (Object obj : getObjectService().getObjects("Garantie")) {
			if (garantie.getCodeGarantie().equals(((Garantie) obj).getCodeGarantie())) {
				checkeixte = true;
			}
		}
		if (checkeixte) {
			getObjectService().updateObject(garantie);
			message = new FacesMessage(
					"  le code Granatie est bien modifié dans la base de donnée!");
		} else {
			message = new FacesMessage(
					"  Il n'existe pas ce code Granatie dans la base de donnée!");
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(buttonupdate.getClientId(context), message);
		return null;

	}

	public String validatedelete() {// supprimer une ligne dans la base de
		// donnée

		Garantie garantie = new Garantie();
		garantie.setCodeGarantie(getId());
		garantie.setLibelleGarantie(getLibelleGarantie());
		FacesMessage message = new FacesMessage("");
		boolean checkeixte = false;
		for (Object obj : getObjectService().getObjects("Garantie")) {
			if (garantie.getCodeGarantie().equals(((Garantie) obj).getCodeGarantie())) {
				checkeixte = true;
			}
		}
		if (checkeixte) {
			getObjectService().deleteObject(garantie);
			message = new FacesMessage(
					"   le code Granatie est bien supprimé dans la base de donnée!");
		} else {
			message = new FacesMessage(
					"  Il n'existe pas ce code Granatie dans la base de donnée!");
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(buttondelete.getClientId(context), message);
		return null;

	}

	/*-------ici on retrouve les methodes pour afficher et calculer les garanties ----------------*/

	public String addGarantie() {
		Garantie garantie = new Garantie();
		//ClasseKey key = new ClasseKey();
		try {
			List listObject = getObjectService().getObjects(
					"Garantie ORDER BY CODE_GARANTIE ASC");
			if (listObject.size() == 0) {
				System.out.println("taille de la Table=" + listObject.size());
				// System.out.println(key.KeyGarantie("0"));
				garantie.setCodeGarantie("0");
			} else {
				Garantie gar = (Garantie) listObject.get(listObject.size() - 1);
				

				// System.out.println("retourne l'id="+key.KeyGarantie(gar.getId()));
				garantie.setCodeGarantie(gar.getCodeGarantie());
			}

		} catch (Exception e1) {
			logger.error("Echec de la récupération des garanties", e1);
		}

		try {
			getObjectService().addObject(garantie);

			return "GarantieOK";
		} catch (DataAccessException e) {
			logger.error("Echec de la connexion", e);

		}

		return "GarantieNO";
	}

	public List<Risque> getRisqeList() {
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

	public String validerGarantieAuto() {

		return "garantieOK";

	}

	

	// les methodes de choix Avenant Affaire Nouvelle
	public void tabChange() {
	}

	
	
	/*-----------------les Getter et Setter -------------------------------*/

	

	public String getMouvement() {
		return mouvement;
	}

	public void setMouvement(String mouvement) {
		this.mouvement = mouvement;
	}

	public List<Garantie> getGarantiesList() {
		GarantiesList = new ArrayList<Garantie>();
		List<Object> listObject = getObjectService().getObjects("Garantie");
		for (Iterator it = listObject.iterator(); it.hasNext();) {
			Garantie garantie = (Garantie) it.next();
			try {
				GarantiesList.add(garantie);
			} catch (Exception e) {
			}

		}
		return GarantiesList;
	}

	public void setGarantiesList(List<Garantie> garantiesList) {
		this.GarantiesList = garantiesList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLibelleGarantie() {
		return libelleGarantie;
	}

	public void setLibelleGarantie(String libelleGarantie) {
		this.libelleGarantie = libelleGarantie;
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

	

	public Garantie getObjetGarantie() {
		return objetGarantie;
	}

	public void setObjetGarantie(Garantie objetGarantie) {
		this.objetGarantie = objetGarantie;
	}

	

	public List<Garantie> getMesGarantiesList() {
		return mesGarantiesList;
	}

	public void setMesGarantiesList(List<Garantie> mesGarantiesList) {
		this.mesGarantiesList = mesGarantiesList;
	}

	public List<Garanties> getListeGroupGaranties() {
		return listeGroupGaranties;
	}

	public void setListeGroupGaranties(List<Garanties> listeGroupGaranties) {
		this.listeGroupGaranties = listeGroupGaranties;
	}

	public List<Garanties> getListegarantieFiltre() {
		return listegarantieFiltre;
	}

	public void setListegarantieFiltre(List<Garanties> listegarantieFiltre) {
		this.listegarantieFiltre = listegarantieFiltre;
	}

	public List<Garanties> getListegarantieAll() {
		return listegarantieAll;
	}

	public void setListegarantieAll(List<Garanties> listegarantieAll) {
		this.listegarantieAll = listegarantieAll;
	}

	public Garanties getSelectGarantieGroup() {
		return selectGarantieGroup;
	}

	public void setSelectGarantieGroup(Garanties selectGarantieGroup) {
		this.selectGarantieGroup = selectGarantieGroup;
	}

	public PrimeCategorie getPrimeCategorie() {
		return primeCategorie;
	}

	public void setPrimeCategorie(PrimeCategorie primeCategorie) {
		this.primeCategorie = primeCategorie;
	}

	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}

	public String getCodeCategorie() {
		return codeCategorie;
	}

	public void setCodeCategorie(String codeCategorie) {
		this.codeCategorie = codeCategorie;
	}

	public String getSlctRisque() {
		return slctRisque;
	}

	public void setSlctRisque(String slctRisque) {
		this.slctRisque = slctRisque;
	}

	public List<Risque> getRisqueList() {
		getRisqeList();
		return risqueList;
	}

	public void setRisqueList(List<Risque> risqueList) {
		this.risqueList = risqueList;
	}

}
