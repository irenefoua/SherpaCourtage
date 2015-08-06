package com.j3a.assurance.objetService;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.j3a.assurance.model.ApporteurVehicule;
import com.j3a.assurance.model.Avenant;
import com.j3a.assurance.model.AyantDroit;
import com.j3a.assurance.model.ConduireVehicule;
import com.j3a.assurance.model.Contrat;
import com.j3a.assurance.model.Exercice;
import com.j3a.assurance.model.Expertise;
import com.j3a.assurance.model.GarantieChoisie;
import com.j3a.assurance.model.GarantieGarantieChoisie;
import com.j3a.assurance.model.HistoMouvement;
import com.j3a.assurance.model.Morale;
import com.j3a.assurance.model.Personne;
import com.j3a.assurance.model.Physique;
import com.j3a.assurance.model.Quittance;
import com.j3a.assurance.model.Risque;
import com.j3a.assurance.model.Sinistre;
import com.j3a.assurance.model.SocieteAssurance;
import com.j3a.assurance.model.Vehicule;
import com.j3a.assurance.model.VehiculeSinistre;
import com.j3a.assurance.model.VehiculeZoneGeographique;
import com.j3a.assurance.model.Victime;
import com.j3a.assurance.objetDao.IDao;

@org.springframework.stereotype.Service
@Transactional(readOnly=true)
public class Service implements ObjectService {

	@Autowired
	IDao dao;
 
 
	@Override
	@Transactional(readOnly=false)
	public void addObject(Object objet) {
		
		// TODO Auto-generated method stub
		getDao().addObject(objet);
	}

	@Override
	@Transactional
	public Object getObjectById(int id, String objet) {
		// TODO Auto-generated method stub
	return	getDao().getObjectById(id, objet);
		 
	}

	@Override
	@Transactional
	public Object getObjectById(String id, String objet) {
		// TODO Auto-generated method stub
		return getDao().getObjectById(id, objet);
		 
	}
	
	
	@Transactional(readOnly=false)
	@Override
	public void updateObject(Object objet) {
		// TODO Auto-generated method stub
		getDao().updateObject(objet);
	}
	
	@Transactional(readOnly=false)
	@Override
	public void deleteObject(Object objet) {
		// TODO Auto-generated method stub
		getDao().deleteObject(objet);
	}
	
	
	public String getCodeTable(String pseudo, int taillCar, int taillChifr,
			String nomTable, String nomCOL){
		return getDao().getCodeTable(pseudo, taillCar, taillChifr, nomTable, nomCOL);
	}

	@Override
	public List getObjects(String objet) {
		// TODO Auto-generated method stub
	return	getDao().getObjects(objet);
		 
	}

	@Override
	public List<Object> getojects(Object object) {
		// TODO Auto-generated method stub
	return	getDao().getojects(object);
		 
	}
	/**
	 * Get Object List Garantie by Code Risque
	 * 
	 */

	public List<Object> getListGarantieByRisque(String codeRisque)
			throws HibernateException {
		return getDao().getListGarantieByRisque(codeRisque);
	}
	

	@Transactional(readOnly=false)
	@Override
	public Object getByIdPK(Object object, String table){
		
		return getDao().getByIdPK(object, table);
	}
	
	@Override
	@Transactional
	public Object getById(String Table, String key, String id, Class TableClass) {
		return getDao().getById(Table, key, id, TableClass);
	}
	public Exercice exerciceOuvert(){
		
		return getDao().exerciceOuvert();
	}
	public VehiculeZoneGeographique recupDerniereZoneGeo(String codeVehicule){
		return getDao().recupDerniereZoneGeo(codeVehicule);
			}
	
	public GarantieChoisie recuperGarantiChoisie(String codeVehicule, String numAvenant){
		return getDao().recuperGarantiChoisie(codeVehicule, numAvenant);
		}
	

	public List<GarantieGarantieChoisie> recupGartGartChoisie(String codeGartChoisi){
		return getDao().recupGartGartChoisie(codeGartChoisi);
		}
	
	public ConduireVehicule recupConducteur(String codeVehicule){
		return getDao().recupConducteur(codeVehicule);
		}
	
	public ConduireVehicule recupConduireVehicule(String codeVehicule) {
		return getDao().recupConduireVehicule(codeVehicule);
		}
	
	public Avenant getContratLie(String param){
		return getDao().getContratLie(param);
		}
	public SocieteAssurance recupererSteAssurance() {
		return getDao().recupererSteAssurance();
		}
	
	public GarantieChoisie getGarantie(String idVehicule){
		return getDao().getGarantie(idVehicule);
		}
	public List<Vehicule> getListVehiculesContrat(String police) {
		return getDao().getListVehiculesContrat(police);
		}
	public List<Physique> physiqueByNom(String nom){
		return getDao().physiqueByNom(nom);
		}
	public List<Morale> moraleByNom(String nom){
		return getDao().moraleByNom(nom);
		}	
	public List<Personne> personneByNom(String nom){
		return getDao().personneByNom(nom);
		}
	public Vehicule findVehicule(String numImatriculation){
		return getDao().findVehicule(numImatriculation);
		}
	
	public List<Vehicule> trouverVehicules(String numImatriculation){
		return getDao().trouverVehicules(numImatriculation);
		}
	public List<HistoMouvement> recuperHistoMouvements(String codeAvenant){
		return getDao().recuperHistoMouvements(codeAvenant);
		}
	public List<HistoMouvement> recuperLisHistoMouvement(String codeAvenant) {
		return getDao().recuperLisHistoMouvement(codeAvenant);
		}

	public Quittance findQuittanceByAvenant(String numAvenant)
	{return getDao().findQuittanceByAvenant(numAvenant);
	}
	public Quittance findQuittance(String police) {
		return getDao().findQuittance(police);
		}
	public Avenant DernierAvenant(String paramPolice) {
		return getDao().DernierAvenant(paramPolice);
				}

	public Risque findRisque(String police) {
		return getDao().findRisque(police);
		}
	
	public List<Avenant> findAvenant(String souscripteur){
		return getDao().findAvenant(souscripteur);
		}
	
	public List<Quittance> findquitQuittance(String souscripteur){
		return getDao().findquitQuittance(souscripteur);
		}

	public VehiculeSinistre infoSinistreAuto(String param){
		return getDao().infoSinistreAuto(param);
		}
	
	public List <ApporteurVehicule> infoapporteuVehicule(String codevehicule,String debut,String fin){
		return getDao().infoapporteuVehicule(codevehicule, debut, fin);
		}
	
    public List <Avenant> listeAvenant(String debut,String fin) {
    	return getDao().listeAvenant(debut, fin);}
    
	public Sinistre DernierSinistre(String police) {
		return getDao().DernierSinistre(police);
		}
	
	public Contrat Contrattrouve(String souscripteur) {
		return getDao().Contrattrouve(souscripteur);
		}
	public List<Contrat> ContratList(String souscripteur) {
		return getDao().ContratList(souscripteur);
		}
	public Quittance recoverQuittanceOfLasAvenant(String paramNumPolice){
		return getDao().recoverQuittanceOfLasAvenant(paramNumPolice);
		}		
	public Quittance recoverQuittanceAuto(String paramNumPolice){
		return getDao().recoverQuittanceAuto(paramNumPolice);
		}
	
	
	@Override
	public Personne personneByLogin(String login, String motPass) {
		return getDao().personneByLogin(login, motPass);
	}
	
	@Override
	public List<Physique> checkPersonPhysique(Personne personne, Physique physique) {
		return getDao().checkPersonPhysique(personne, physique);
	}
	
	@Override
	public List<Avenant> AvenantAFNPeriode(String mouvement, Date Date1,
			Date Date2) {
		return getDao().AvenantAFNPeriode(mouvement, Date1, Date2);
	}
	
	public List<Sinistre> sinistreparpolice(String NumPolice)
			throws HibernateException {
			return getDao().sinistreparpolice(NumPolice);
		}
		public List<Victime> getvictimes (String idsinistre)  
				throws HibernateException{
			return getDao().getvictimes(idsinistre);
		}
		public List<AyantDroit> getayantdroits (String idsinistre)  
		throws HibernateException{
			return getDao().getayantdroits (idsinistre);
		}
		public List<Expertise> getexpertise (String idsinistre)  
				throws HibernateException{	
			return getDao().getexpertise(idsinistre);
		}
		
	@Override
	public List<String> getVictime() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean chercherLogin(String paramLogin) {
		return getDao().chercherLogin(paramLogin);
	}

	
	
	@Override
	public Personne RecupererUtilisateurCourrant() {
		// TODO Auto-generated method stub
		return getDao().RecupererUtilisateurCourrant();
	}

		@Override
		public List<Personne> personneByLogin(String login) {
			// TODO Auto-generated method stub
			return getDao().personneByLogin(login);
		}
		
		//getter et setter de Idao qui a été injecté
		
			public IDao getDao() {
				return dao;
				}
			
			public void setDao(IDao dao) {
				this.dao = dao;
			}

}
