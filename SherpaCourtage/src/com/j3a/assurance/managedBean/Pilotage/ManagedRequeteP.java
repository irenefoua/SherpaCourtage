package com.j3a.assurance.managedBean.Pilotage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.j3a.assurance.utilitaires.hybride.SinistreResultQuery;
@Transactional
@Component
@Scope("session")
public class ManagedRequeteP implements Serializable {
private static final long serialVersionUID = 1L;
	
private List<SinistreResultQuery> listreqResult=new ArrayList<SinistreResultQuery>();
private SessionFactory sessionFactory;

public List<SinistreResultQuery> exeQuery(Date debut, Date fin){
	String myQuery = "select sum(sinistre.EVALUATION_BRUTE), sum(sinistre.EVALUATION_NETTE), risque.LIBELLE_RISQUE, point_vente.LIBELLE_POINT_VENTE FROM sinistre, contrat,risque,point_vente WHERE sinistre.NUM_POLICE=contrat.NUM_POLICE AND contrat.CODE_RISQUE=risque.CODE_RISQUE and contrat.CODE_POINT_VENTE=point_vente.CODE_POINT_VENTE and sinistre.DATE_DECLARATION BETWEEN  '"+debut +"' AND  '"+fin +"'";
	List rslt =  getSessionFactory().getCurrentSession().createSQLQuery(myQuery).addEntity(SinistreResultQuery.class).list();
	System.out.println("la tailleeeee "+rslt.size());
	if (rslt.size()==0){
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"0", "Aucun Enregistrement!"));
		
	}
	else{
		listreqResult=new ArrayList<SinistreResultQuery>();
		for(Iterator it=rslt.iterator();it.hasNext();){
			SinistreResultQuery Pvt=(SinistreResultQuery) it.next();
			listreqResult.add(Pvt);	
		}
	}
	return listreqResult;
}
	public List<SinistreResultQuery> getListreqResult() {
		return listreqResult;
	}

	public void setListreqResult(List<SinistreResultQuery> listreqResult) {
		this.listreqResult = listreqResult;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	
	
	

}
