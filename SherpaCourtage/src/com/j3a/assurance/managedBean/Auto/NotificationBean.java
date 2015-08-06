package com.j3a.assurance.managedBean.Auto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.Avenant;
import com.j3a.assurance.objetService.ObjectService;

/**
 * 
 * @author PCALekerand
 *
 */

@Component
public class NotificationBean {

	@Autowired
    ObjectService objectService;
	private Integer nombreNotification = new Integer(0);
	private Date dateduJour = Calendar.getInstance().getTime();
	List<Avenant> listAffairesNouvelles = new ArrayList<Avenant>();
	int nbrsouscription;
	
	
	@PostConstruct
	public List<Avenant> afficherListContrat(){
		
		//obtenir la date de demain cause Mysql exclu l'extremité sup de la clause between
		
		Calendar cal = Calendar.getInstance();  
		cal.add(Calendar.DATE, 1);  
		Date demain = cal.getTime();
		
		//Obtenir la date d'hier
		Calendar cal1 = Calendar.getInstance();  
		cal.add(Calendar.DATE, -1);  
		Date hier = cal1.getTime();
		
		System.out.println("----> date Hier:"+hier);//clean after
		System.out.println("----> date dateJour:"+dateduJour);//clean after
		System.out.println("----> date demain:"+demain);//clean after
		listAffairesNouvelles = getObjectService().AvenantAFNPeriode("AFFAIRE NOUVELLE", hier, demain);
		nbrsouscription = listAffairesNouvelles.size();
		
		return listAffairesNouvelles;
	}
	
	
	/***************ACCESSEUR**************/

	public Integer getNombreNotification() {
		return nombreNotification;
	}

	public void setNombreNotification(Integer nombreNotification) {
		this.nombreNotification = nombreNotification;
	}
	
	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}




	public List<Avenant> getListAffairesNouvelles() {
		return listAffairesNouvelles;
	}




	public void setListAffairesNouvelles(List<Avenant> listAffairesNouvelles) {
		this.listAffairesNouvelles = listAffairesNouvelles;
	}


	public int getNbrsouscription() {
		return nbrsouscription;
	}


	public void setNbrsouscription(int nbrsouscription) {
		this.nbrsouscription = nbrsouscription;
	}

}
