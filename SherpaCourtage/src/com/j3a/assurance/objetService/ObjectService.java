package com.j3a.assurance.objetService;

import java.util.Date;
import java.util.List;

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

public interface ObjectService {
public void addObject(Object objet);
	
	public Object getObjectById(int id, String objet);
	
	public Object getObjectById(String id, String objet);
	
	public void updateObject(Object objet);
	
	public void deleteObject(Object objet);
	
	public  List getObjects(String objet);
	
	public List<Object> getojects(Object object);
	
	public String getCodeTable(String pseudo, int taillCar, int taillChifr,
			String nomTable, String nomCOL);
	public List<Object> getListGarantieByRisque(String codeRisque);
	
	public Object getByIdPK(Object object, String table);
	
	public Object getById(String Table, String key, String id, Class TableClass);
	public VehiculeZoneGeographique recupDerniereZoneGeo(String codeVehicule);
	public GarantieChoisie recuperGarantiChoisie(String codeVehicule, String numAvenant);
	
	public Exercice exerciceOuvert();
	public List<GarantieGarantieChoisie> recupGartGartChoisie(String codeGartChoisi);
	public ConduireVehicule recupConducteur(String codeVehicule);
	public ConduireVehicule recupConduireVehicule(String codeVehicule) ;
	public Avenant getContratLie(String param);
	public SocieteAssurance recupererSteAssurance() ;
	
	public GarantieChoisie getGarantie(String idVehicule);
	public List<Vehicule> getListVehiculesContrat(String police) ;
	public List<Physique> physiqueByNom(String nom);
	public List<Morale> moraleByNom(String nom);	
	public List<Personne> personneByNom(String nom);
	public Vehicule findVehicule(String numImatriculation);
	
	public List<Vehicule> trouverVehicules(String numImatriculation);
	public List<HistoMouvement> recuperHistoMouvements(String codeAvenant);
	public List<HistoMouvement> recuperLisHistoMouvement(String codeAvenant) ;

	public Quittance findQuittanceByAvenant(String numAvenant);
	public Quittance findQuittance(String police) ;
	public Avenant DernierAvenant(String paramPolice);

	public Risque findRisque(String police) ;
	public List<Avenant> findAvenant(String souscripteur);
	public List<Quittance> findquitQuittance(String souscripteur);

	public VehiculeSinistre infoSinistreAuto(String param);			
	public List <ApporteurVehicule> infoapporteuVehicule(String codevehicule,String debut,String fin);
    public List <Avenant> listeAvenant(String debut,String fin) ;
	public Sinistre DernierSinistre(String police) ;
	public Contrat Contrattrouve(String souscripteur) ;
	public List<Contrat> ContratList(String souscripteur) ;
	public Quittance recoverQuittanceOfLasAvenant(String paramNumPolice);		
	public Quittance recoverQuittanceAuto(String paramNumPolice);
	public Personne personneByLogin(String login, String motPass);
	public List<Physique> checkPersonPhysique(Personne personne , Physique physique);
	public List<Avenant> AvenantAFNPeriode(String mouvement, Date Date1, Date Date2);
	public List<Victime> getvictimes (String idsinistre);
	public List<String> getVictime();
	public List<Sinistre> sinistreparpolice(String NumPolice);
	public List<AyantDroit> getayantdroits (String idsinistre);
	public List<Expertise> getexpertise (String idsinistre);
	public Personne RecupererUtilisateurCourrant();
	public List <Personne> personneByLogin(String login);
	public boolean chercherLogin(String paramLogin);

}
