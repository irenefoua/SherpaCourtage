package com.j3a.assurance.managedBean.EspaceUser;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.j3a.assurance.model.Avenant;
import com.j3a.assurance.model.Contrat;
import com.j3a.assurance.model.GarantieChoisie;
import com.j3a.assurance.model.GarantieGarantieChoisie;
import com.j3a.assurance.model.Personne;
import com.j3a.assurance.model.Vehicule;
import com.j3a.assurance.model.VehiculeSinistre;
import com.j3a.assurance.utilitaires.ContratRw;
import com.j3a.assurance.utilitaires.Garanties;
import com.j3a.assurance.utilitaires.VehiAssur;

@Component
@Transactional
public class RequeteUtilisateur implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(RequeteUtilisateur.class);
	@Autowired
	private SessionFactory sessionFactory;

	// private Utilisateur utilisateur = new Utilisateur();

	/**
	 * Méthode pour l'utilisateur de la session
	 * 
	 * @return utilisateur
	 * @throws HibernateException
	 */
	public Personne RecupererUtilisateurCourrant() throws HibernateException {
		// Recupération du login de l'utilisateur courant
		String paramLogin = "";
		if (FacesContext.getCurrentInstance().getExternalContext()
				.getUserPrincipal() != null) {
			paramLogin = FacesContext.getCurrentInstance().getExternalContext()
					.getUserPrincipal().getName();
			
		}
		String query = "SELECT * FROM personne WHERE LOGIN_PERS='"
				+ paramLogin + "'";
		System.out.println("LA requete de recuperation de PERSONNE"+query);
		Personne connected = null;
		try {

			connected = (Personne) getSessionFactory().getCurrentSession()
					.createSQLQuery(query).addEntity(Personne.class)
					.uniqueResult();
		} catch (Exception e) {
			logger.error(" Erreur sur la recupération de l'utilisateur");
		}
		return connected;
	}
	
	public BigDecimal montantNetPaid(Contrat ctrat){
    	BigDecimal X = BigDecimal.ZERO;
    	String query = "SELECT  SUM(NET_A_PAYER) FROM quittance Q JOIN avenant A ON A.NUM_AVENANT = Q.NUM_AVENANT JOIN contrat C ON C.NUM_POLICE = A.NUM_POLICE WHERE C.NUM_POLICE='"+ctrat.getNumPolice()+"'"; 
    	try {
			X=(BigDecimal)getSessionFactory().getCurrentSession().createSQLQuery(query).uniqueResult();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return X;
    }
	
	public List<ContratRw> retriveCtrat(Personne pers){
		List<ContratRw> lC = null;
		String q1="SELECT {ctrat.*}, {lastAven.*}, SUM(Q.NET_A_PAYER) AS netPaid "
				+ "FROM quittance Q, "
				+ "contrat ctrat JOIN avenant lastAven ON lastAven.NUM_POLICE=ctrat.NUM_POLICE "
				+ "WHERE ctrat.NUM_SOUSCRIPTEUR = '"+pers.getNumSouscripteur()+"' "
						+ "AND lastAven.DATE_AVENANT= (SELECT max(A1.DATE_AVENANT) FROM avenant A1 WHERE A1.NUM_POLICE=ctrat.NUM_POLICE) "
						+ "AND Q.NUM_AVENANT IN (SELECT A2.NUM_AVENANT FROM avenant A2 WHERE A2.NUM_POLICE=ctrat.NUM_POLICE) "
						+ "GROUP BY ctrat.NUM_POLICE";
		//String query = "SELECT {ctrat.*}, {lastAven.*} FROM contrat ctrat JOIN avenant lastAven ON lastAven.NUM_POLICE=ctrat.NUM_POLICE WHERE ctrat.NUM_SOUSCRIPTEUR = '"+pers.getNumSouscripteur()+"' 	AND lastAven.DATE_AVENANT= (SELECT max(A1.DATE_AVENANT) FROM avenant A1 WHERE A1.NUM_POLICE=ctrat.NUM_POLICE)";
		//System.out.println("/////////"+q1);
		try {
			lC=getSessionFactory().getCurrentSession().createSQLQuery(q1).addEntity("ctrat", Contrat.class).addEntity("lastAven", Avenant.class).addScalar("netPaid", StandardBasicTypes.BIG_DECIMAL).setResultTransformer(Transformers.aliasToBean(ContratRw.class)).list();
			calculateProgress (lC);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return lC;
    }
	
	public List<VehiculeSinistre> vehiSinByVehi(Vehicule vehi){
		List<VehiculeSinistre> vSList = null;
		String q1="SELECT  * FROM vehicule_sinistre Vs WHERE Vs.CODE_VEHICULE = '"+vehi.getCodeVehicule()+"'";
		System.out.println("/////////"+q1);
		try {
			vSList=getSessionFactory().getCurrentSession().createSQLQuery(q1).addEntity(VehiculeSinistre.class).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return vSList;
    }
	
	public List<VehiculeSinistre> vehiSinByCtra(Contrat ctrat){
		List<VehiculeSinistre> vSList = null;
		String q1="SELECT  * FROM vehicule_sinistre Vs JOIN sinistre S ON  S.CODE_SINISTRE=Vs.CODE_SINISTRE WHERE S.NUM_POLICE = '"+ctrat.getNumPolice()+"' ";
		System.out.println("/////////"+q1);
		try {
			vSList=getSessionFactory().getCurrentSession().createSQLQuery(q1).addEntity(VehiculeSinistre.class).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return vSList;
    }
	
	
	
	public void calculateProgress (List<ContratRw> Lc){
		for(ContratRw cR: Lc){
			LocalDate effet = new LocalDate(cR.getLastAven().getEffet().getTime());
				LocalDate echeance = new LocalDate(cR.getLastAven().getEcheance().getTime()); 
				LocalDate actu = new LocalDate(Calendar.getInstance());
				int dur = Days.daysBetween(effet,echeance).getDays();
				int used= Days.daysBetween(effet,actu).getDays();
				if(dur>=1){
					cR.setProgress((int) ((double)used/((double)dur)*100));
					}
				//System.out.println("Police: "+cR.getCtrat().getNumPolice()+" DateActu:"+actu.toString("dd-MMM-yyyy")+" Duree totate:"+dur+" Duree à ce jour"+used+ " Pourcentage de Progression "+(double)used/((double)dur));
			}
	}

	
	public List<VehiAssur> retrvInsuredVehi(Contrat ctra){
		List<VehiAssur> lVa = null;
		String q1="SELECT {vehi.*}, {vehiGc.*} "
				+ "FROM vehicule vehi "
				+ "JOIN garantie_choisie vehiGc ON vehiGc.CODE_VEHICULE = vehi.CODE_VEHICULE "
				+ "JOIN vehicules_assures Va ON Va.ID_VEHICULES_ASSURES = vehi.ID_VEHICULES_ASSURES "
				+ "JOIN avenant A ON A.ID_VEHICULES_ASSURES=Va.ID_VEHICULES_ASSURES "
				+ "JOIN contrat C ON C.NUM_POLICE=A.NUM_POLICE "
				+ "WHERE C.NUM_POLICE = '"+ctra.getNumPolice()+"' 	 	  "
				+ "AND vehiGc.DATE_GARANTIE_CHOISIE= (SELECT max(Gc1.DATE_GARANTIE_CHOISIE) FROM garantie_choisie Gc1 			"
				+ "WHERE Gc1.CODE_VEHICULE=vehi.CODE_VEHICULE) 	  ";
		System.out.println("/////////"+q1);
		try {
			lVa=getSessionFactory().getCurrentSession().createSQLQuery(q1).addEntity("vehi", Vehicule.class).addEntity("vehiGc", GarantieChoisie.class).setResultTransformer(Transformers.aliasToBean(VehiAssur.class)).list();
			loadGarantiesList(lVa);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("/////////"+lVa);
    	return lVa;
    }
	
	public void loadGarantiesList (List<VehiAssur> lV){
		for(VehiAssur vA: lV){
			List<GarantieGarantieChoisie> X = new ArrayList<GarantieGarantieChoisie>(vA.getVehiGc().getGarantieGarantieChoisies());
			vA.setInfoGarList(X);
			}
	}
	
	
/*	public List<String> retriveBrchLib(){
		List<String> lBrLib = null;
		String q1="SELECT DISTINCT(R.LIBELLE_RISQUE) AS libelle FROM risque R";
		System.out.println("/////////"+q1);
		try {
			lBrLib=getSessionFactory().getCurrentSession().createSQLQuery(q1).addScalar("libelle", StandardBasicTypes.STRING).list();
			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return lBrLib;
    }*/

/*	public boolean chercherLogin(String paramLogin) {
		boolean etat;
		String str = paramLogin;
		etat = false;
		try {

			String query = "SELECT * FROM `utilisateur` WHERE `LOGIN_UTILISATEUR`='"
					+ str + "'";
			List list = (List) getSessionFactory().getCurrentSession()
					.createSQLQuery(query).addEntity(Utilisateur.class).list();
			if (list.size() >= 1) {
				etat = true;
			}
			System.out.println("Etat de la requête:" + etat);
		} catch (Exception e) {
			logger.error(" Problème de Base de données", e);
		}
		return etat;
	}*/
	
	
	

	
	

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
