package com.j3a.assurance.objetDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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

@Repository
public class Dao implements IDao {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	private static  Logger logger=Logger.getLogger(Dao.class);

	//Injection par Spring
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void addObject(Object objet) {
		
		getSessionFactory().getCurrentSession().save(objet);
		// TODO Auto-generated method stub
		 
	}

	@Override
	public Object getObjectById(int id, String objet) {
		Session session= getSessionFactory().getCurrentSession();
		String query= "from" +" "+ objet + " "+ " where id =?";
		  List liste = session.createQuery(query).setParameter(0,id).list();
		  if (liste.size()==0){
		   return null;}
		return liste.get(0);
	}

	@Override
	public Object getObjectById(String id, String objet) {
		Session session= getSessionFactory().getCurrentSession();
		String query= "from" +" "+ objet + " "+ " where id =?";
		  List liste = session.createQuery(query).setParameter(0,id).list();
		  if (liste.size()==0){
		   return null;}
		
		return liste.get(0);
	}

	@Override
	@Transactional
	public void updateObject(Object objet) {
		// TODO Auto-generated method stub
	getSessionFactory().getCurrentSession().update(objet);
	}

	@Override
	@Transactional
	public void deleteObject(Object objet) {
		// TODO Auto-generated method stub
		getSessionFactory().getCurrentSession().delete(objet);
	}
	
	@Override
	@Transactional
	public String getCodeTable(String pseudo, int taillCar, int taillChifr,
			String nomTable, String nomCOL) {
		// Methode crï¿½ation d'un id code alphanumrique chronologique d'une
				// ligne de table ds la BD
				

				String query = "SELECT MAX(CAST(SUBSTRING(" + nomCOL + " FROM "
						+ (taillCar + 1) + " FOR " + (taillChifr)
						+ ") AS UNSIGNED )) AS NUMBER FROM " + nomTable + " WHERE "+nomCOL+" LIKE '"+pseudo+"%'";
				Integer v = null;
				try {
					v = (Integer) getSessionFactory().getCurrentSession()
							.createSQLQuery(query)
							.addScalar("NUMBER", StandardBasicTypes.INTEGER).uniqueResult();
					
					String tC = String.valueOf(taillChifr);
					if (v == null) {
						//int numOrdT = i+1;
						String numOrd= String.format("%0"+tC+"d", 1);
						System.out.println("///////Verification requette V null");
						System.out.println("///////Verification requette V null et pseudo = "+pseudo);
						String s = pseudo + numOrd;
						System.out.println("///////Verification requette V null et pseudo + numrd = "+s);
						return s;
					} else {
						v++;
						String numOrd= String.format("%0"+tC+"d", v);
						System.out.println("///////Verification requette V non null");
						String s = pseudo + numOrd;
						return s;
					}
				} catch (HibernateException e) {
					e.printStackTrace();
					return "blag aaa";
				}
				// sess.close();

	}
	
	@Override
	  @Transactional
	public List<Object> getListGarantieByRisque(String codeRisque)
			throws HibernateException {
		List list = getSessionFactory()
				.getCurrentSession()
				.createQuery(
						" from Garantie where CODE_RISQUE='" + codeRisque
								+ "' order by CODE_GARANTIE ASC").list();
		return list;
	}

	@Override
	public List getObjects(String objet) {
		Session session= getSessionFactory().getCurrentSession();
		String query = "from"+" "+objet;
		List list = session.createQuery(query).list();
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public List<Object> getojects(Object object) {
		Session session= getSessionFactory().getCurrentSession();
	List list = session.createQuery("from"+" "+object).list();
		// TODO Auto-generated method stub
		return list ;
	}
	
	@Transactional
	public Object getByIdPK(Object object, String table)
			throws HibernateException {
		Query query = getSessionFactory().getCurrentSession().createQuery(
				"from " + table + " O where O.id=:pk");
		query.setParameter("pk", object);
		List list = query.list();
		System.out
				.println("/********************requï¿½te Pk reussie***********************/");
		if (list.size() == 0)
			return null;
		return list.get(0);
	}
	
	@Override
	@Transactional
	public Object getById(String Table, String key, String id, Class TableClass)
			throws HibernateException {
		String query = "SELECT * FROM " + Table + " a  WHERE a." + key + " ='"
				+ id + "' ";
		Object A = getSessionFactory().getCurrentSession()
				.createSQLQuery(query).addEntity(TableClass).uniqueResult();
		return A;
	}
	public VehiculeZoneGeographique recupDerniereZoneGeo(String codeVehicule){
		String query = "SELECT `vehicule_zone_geographique`.* FROM vehicule_zone_geographique WHERE `vehicule_zone_geographique`.`CODE_VEHICULE` ='"+codeVehicule+"' ORDER BY `vehicule_zone_geographique`.`DATE_ROULER` desc Limit 0,1";
		VehiculeZoneGeographique vehiculeZoneGeographique = (VehiculeZoneGeographique) getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(VehiculeZoneGeographique.class).uniqueResult();
		return vehiculeZoneGeographique;
	}
	
	public GarantieChoisie recuperGarantiChoisie(String codeVehicule, String numAvenant){
		String query = "SELECT `garantie_choisie`.* FROM garantie_choisie WHERE ((`garantie_choisie`.`CODE_VEHICULE` ='"+codeVehicule+"') AND (`garantie_choisie`.`CODE_AVENANT_AUTO` ='"+numAvenant+"'))";
		GarantieChoisie garantieChoisie = (GarantieChoisie) getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(GarantieChoisie.class).uniqueResult();
		return garantieChoisie;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<GarantieGarantieChoisie> recupGartGartChoisie(String codeGartChoisi){
		String query = "SELECT `garantie_garantie_choisie`.* FROM garantie_garantie_choisie WHERE (`garantie_garantie_choisie`.`CODE_GARANTIE_CHOISIE` ='"+codeGartChoisi+"')";
		List<GarantieGarantieChoisie> garantieGarantieChoisie = getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(GarantieGarantieChoisie.class).list();
		return garantieGarantieChoisie;
	}
	
	public ConduireVehicule recupConducteur(String codeVehicule){
		String query = "SELECT c.* FROM conduire_vehicule c WHERE (c.CODE_VEHICULE ='"+codeVehicule+"')";
	ConduireVehicule  vehiculeZoneGeographique = (ConduireVehicule) getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(ConduireVehicule.class).uniqueResult();
		return vehiculeZoneGeographique;
	}
	
	
	
	public ConduireVehicule recupConduireVehicule(String codeVehicule) {
		
		String query ="SELECT c.* FROM conduire_vehicule c WHERE (c.CODE_VEHICULE ='"+codeVehicule+"') ORDER BY c.DATE_CONDUITE DESC Limit 0,1";
		ConduireVehicule conduireVehicule = (ConduireVehicule) getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(ConduireVehicule.class).uniqueResult();
		return conduireVehicule;
	}
	

	@Transactional
	public Avenant getContratLie(String param)
			throws IndexOutOfBoundsException, HibernateException {
		String query = "SELECT DISTINCT A.* "
				+ "FROM  AVENANT A "
				// +
				// "JOIN  avenant_vehicule_assure AVa ON AVa.NUM_AVENANT = A.NUM_AVENANT "
				+ "JOIN  vehicules_assures Va ON Va.ID_VEHICULES_ASSURES = A.ID_VEHICULES_ASSURES "
				// +
				// "JOIN  vehicule_vehicule_assure VaA ON VaA.ID_VEHICULES_ASSURES = Va.ID_VEHICULES_ASSURES "
				+ "JOIN  vehicule V ON V.ID_VEHICULES_ASSURES = Va.ID_VEHICULES_ASSURES "
				+ "JOIN  attestation At ON At.CODE_VEHICULE = V.CODE_VEHICULE "
				+ "WHERE At.CODE_ATTESTATION ='" + param + "'";
		Avenant avenant = (Avenant) getSessionFactory().getCurrentSession()
				.createSQLQuery(query).addEntity(Avenant.class).uniqueResult();
		System.out.println("Avenant :" + avenant);
		return avenant;

	}
	public Exercice exerciceOuvert() throws HibernateException {
		String req = "SELECT * FROM EXERCICE WHERE ETAT_EXERCICE LIKE 'ouvert%' order by CODEEXERCICE DESC LIMIT 0,1";
		Exercice exo = (Exercice) getSessionFactory().getCurrentSession()
				.createSQLQuery(req).addEntity(Exercice.class).uniqueResult();
		return exo;
	}
	
	/**
	 * Méthode de recupération de la société d'Assurance
	 * 
	 * @return societeAssurance
	 */
	@Transactional
	public SocieteAssurance recupererSteAssurance() {
		SocieteAssurance societeAssurance = new SocieteAssurance();
		try {
			String query = "SELECT `societe_assurance`.* FROM societe_assurance Limit 0,1";
			societeAssurance = (SocieteAssurance) getSessionFactory()
					.getCurrentSession().createSQLQuery(query)
					.addEntity(SocieteAssurance.class).uniqueResult();
			System.out.println("<<<<<>>>>> Société trouvé:"
					+ societeAssurance.getPays());
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return societeAssurance;
	}

	@Transactional
	public GarantieChoisie getGarantie(String idVehicule)
			throws IndexOutOfBoundsException, HibernateException {
		String query = "SELECT * FROM   garantie_choisie  WHERE CODE_VEHICULE='"
				+ idVehicule
				+ "' order by DATE_GARANTIE_CHOISIE desc Limit 0,1";

		GarantieChoisie garantieChoisie = (GarantieChoisie) getSessionFactory()
				.getCurrentSession().createSQLQuery(query)
				.addEntity(GarantieChoisie.class).uniqueResult();
		System.out.println("<<>> Id Garantie choisie :" + garantieChoisie);
		return garantieChoisie;
	}

	/**
	 * methode qui retourne la liste des vehicules d'un contrat ayant leur
	 * statut en flotte
	 * 
	 * @param NumPolice
	 * @return List<Vehicule>
	 */
	@Transactional
	public List<Vehicule> getListVehiculesContrat(String police) {
		/**
		 * Liste <<distincte>> de vehicule appartenant à "vehicule Assurés" de
		 * tous les avenants de ce contrat les dit vehicules doivent avoir leur
		 * statut à "actif"
		 */
		List<Vehicule> listVehicules;

		String query = "SELECT DISTINCT V.* "
				+ "FROM  Vehicule V "
				// +
				// "JOIN  vehicule_vehicule_assure Fp ON Fp.CODE_VEHICULE = V.CODE_VEHICULE "
				+ "JOIN  vehicules_assures Va ON Va.ID_VEHICULES_ASSURES = V.ID_VEHICULES_ASSURES "
				// +
				// "JOIN  avenant_vehicule_assure L ON L.ID_VEHICULES_ASSURES = Va.ID_VEHICULES_ASSURES "
				+ "JOIN  AVENANT A ON A.ID_VEHICULES_ASSURES = Va.ID_VEHICULES_ASSURES "
				+ "JOIN  Contrat C ON C.NUM_POLICE = A.NUM_POLICE "
				+ "WHERE C.NUM_POLICE ='" + police + "' AND V.STATUT = 'actif'";
		System.out.println("/****getSessionFactory()" + getSessionFactory());
		System.out.println("/****getSessionFactory().getCurrentSession()"
				+ getSessionFactory().getCurrentSession());
		listVehicules = getSessionFactory().getCurrentSession()
				.createSQLQuery(query).addEntity(Vehicule.class).list();
		return listVehicules;
	}

	

	
	@SuppressWarnings("unchecked")
	public List<Physique> physiqueByNom(String nom){
		String query = "SELECT `physique`.* FROM physique WHERE (`physique`.`NOM_RAISON_SOCIALE` ='"+nom+"')";
		List<Physique> list = (List<Physique>) getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(Physique.class).list();
		System.out.println("------->> Taille de la liste Physique: "+list.size());//Clean after
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Morale> moraleByNom(String nom){
		String query = "SELECT `morale`.* FROM morale WHERE (`morale`.`NOM_RAISON_SOCIALE` ='"+nom+"')";
		List<Morale> list = (List<Morale>) getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(Morale.class).list();
		System.out.println("------->> Taille de la liste Morale: "+list.size());//Clean after
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Personne> personneByNom(String nom){
		String query = "SELECT `personne`.* FROM personne WHERE (`personne`.`NOM_RAISON_SOCIALE` ='"+nom+"')";
		List<Personne> list = (List<Personne>) getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(Personne.class).list();
		System.out.println("------->> Taille de la liste Personne: "+list.size());//Clean after
		return list;
	}
	
	public Vehicule findVehicule(String numImatriculation) throws HibernateException {
		String req = "SELECT `vehicule`.* FROM vehicule WHERE `vehicule`.`NUM_IMMAT` ='"+numImatriculation+"'";
		Vehicule veh = (Vehicule) getSessionFactory().getCurrentSession().createSQLQuery(req).addEntity(Vehicule.class).uniqueResult();
		return veh;
	}
	
	
	public List<Vehicule> trouverVehicules(String numImatriculation) throws HibernateException {
		String myQuery = "SELECT `vehicule`.* FROM vehicule WHERE (`vehicule`.`NUM_IMMAT` ='"+numImatriculation+"')";
		List<Vehicule> vehicules = getSessionFactory().getCurrentSession().createSQLQuery(myQuery).addEntity(Vehicule.class).list();
		return vehicules;
	}
	
	public List<HistoMouvement> recuperHistoMouvements(String codeAvenant) throws HibernateException {
		String query = "from HistoMouvement where HistoMouvement.idAvenant =" + "" + codeAvenant;
		List list = getSessionFactory().getCurrentSession().createQuery(query).list();
		return list;
	}
	
	public List<HistoMouvement> recuperLisHistoMouvement(String codeAvenant) throws HibernateException {
		String query = "SELECT `histo_mouvement`.* FROM histo_mouvement WHERE `histo_mouvement`.`ID_AVENANT` ='"+codeAvenant+"'";
		List list = getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(HistoMouvement.class).list();
		System.out.println("------------->>Taille de la liste"+list.size());
		return list;
	}

	public Quittance findQuittanceByAvenant(String numAvenant) throws HibernateException{
		String myQuery = "SELECT * FROM QUITTANCE WHERE NUM_AVENANT='"+numAvenant+"'";
		return (Quittance) getSessionFactory().getCurrentSession().createSQLQuery(myQuery).addEntity(Quittance.class).uniqueResult();
		
	}
	//irene
	public Quittance findQuittance(String police) throws HibernateException{
				String myQuery = "SELECT  Q.* FROM quittance Q JOIN avenant AV ON AV.NUM_AVENANT = Q.NUM_AVENANT WHERE `NUM_POLICE`='"+police+"' order by  DATE_QUITTANCE asc Limit 0,1";
				return (Quittance) getSessionFactory().getCurrentSession().createSQLQuery(myQuery).addEntity(Quittance.class).uniqueResult();
		
		
	}
	public Avenant DernierAvenant(String paramPolice) {
		String query = "SELECT * FROM AVENANT where NUM_POLICE = '"
				+ paramPolice + "' order by DATE_AVENANT  desc Limit 0,1";
		Avenant av = (Avenant) getSessionFactory().getCurrentSession()
				.createSQLQuery(query).addEntity(Avenant.class).uniqueResult();
		return av;
	}

	// irene
	public Risque findRisque(String police) throws HibernateException {
		String myQuery = "SELECT r . *FROM risque r JOIN contrat C ON C.code_RISQUE = r.code_RISQUE WHERE NUM_POLICE = '"
				+ police + "'";
		Risque risque = (Risque) getSessionFactory().getCurrentSession()
				.createSQLQuery(myQuery).addEntity(Risque.class).uniqueResult();
		return risque;

	}

	public List<Avenant> findAvenant(String souscripteur)
			throws HibernateException {
		String myQuery = "SELECT a. *FROM avenant a JOIN contrat C ON C.num_police = a.num_police WHERE NUM_SOUSCRIPTEUR = '"
				+ souscripteur + "'";
		List avenant = getSessionFactory().getCurrentSession()
				.createSQLQuery(myQuery).addEntity(Avenant.class).list();
		return avenant;

	}

	public List<Quittance> findquitQuittance(String souscripteur)
			throws HibernateException {
		String myQuery = "SELECT q . *FROM quittance q JOIN avenant a ON q.num_avenant = a.num_avenant JOIN contrat c ON c.num_police = a.num_police WHERE NUM_SOUSCRIPTEUR= '"
				+ souscripteur + "'" + "AND ETAT_QUITTANCE='NON SOLDER'";
		List quittance = getSessionFactory().getCurrentSession()
				.createSQLQuery(myQuery).addEntity(Quittance.class).list();
		return quittance;

	}

	
	
	

	
	

	public VehiculeSinistre infoSinistreAuto(String param)
			throws HibernateException {
		String query = "SELECT * FROM VEHICULE_SINISTRE where CODE_SINISTRE='"
				+ param + "' order by DATE_SINISTRE desc Limit 0,1 ";
		VehiculeSinistre assSin = (VehiculeSinistre) getSessionFactory()
				.getCurrentSession().createSQLQuery(query)
				.addEntity(VehiculeSinistre.class).uniqueResult();

		return assSin;
	}
	
	
  
	public List <ApporteurVehicule> infoapporteuVehicule(String codevehicule,String debut,String fin) throws HibernateException{

		
		String query="select * from apporteur_vehicule where CODE_APPORTEUR='"+codevehicule+"' and DATE_APPORTEUR_VEHICULE BETWEEN '"+debut+"' and '"+fin+"'";
		List apporteurVehicule=getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(ApporteurVehicule.class).list();
			return apporteurVehicule;		
	}
	
    public List <Avenant> listeAvenant(String debut,String fin) throws HibernateException{

		
		String query="select * from avenant where effet BETWEEN '"+debut+"' and '"+fin+"'";
		List apporteurVehicule=getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(Avenant.class).list();
			return apporteurVehicule;		
	}
	
     
      
   
	
      
   
	

	

	/* dernier sinistre */
	/* selection du dernier sinistre d'une police */
	public Sinistre DernierSinistre(String police) throws HibernateException {
		String query = "SELECT * FROM SINISTRE where NUM_POLICE = '" + police
				+ "' order by DATE_DECLARATION  desc Limit 0,1";
		Sinistre st = (Sinistre) getSessionFactory().getCurrentSession()
				.createSQLQuery(query).addEntity(Sinistre.class).uniqueResult();
				
		return st;

	}

	/* selection d'un contrat par le client */
	public Contrat Contrattrouve(String souscripteur) {
		String query = "SELECT * FROM Contrat where NUM_SOUSCRIPTEUR = '"
				+ souscripteur + "' order by NUM_POLICE  desc Limit 0,1";
		Contrat cont = (Contrat) getSessionFactory().getCurrentSession()
				.createSQLQuery(query).addEntity(Contrat.class).uniqueResult();

		return cont;
	}

	public List<Contrat> ContratList(String souscripteur) {
		String query = "SELECT * FROM Contrat where NUM_SOUSCRIPTEUR = '"
				+ souscripteur + "'";
		List cont = getSessionFactory().getCurrentSession()
				.createSQLQuery(query).addEntity(Contrat.class).list();

		return cont;
	}

	
	/**
	 * Methode (Requête) permettant de retrouver la quittance du dernier Avenant
	 * d'un contrat
	 * 
	 * @author ALekerand
	 * @return Quittance
	 */
	public Quittance recoverQuittanceOfLasAvenant(String paramNumPolice)
			throws HibernateException {
		String query = "SELECT distinct q.* FROM quittance q , avenant a, contrat c where c.NUM_POLICE = '"
				+ paramNumPolice + "' order by a.DATE_AVENANT desc Limit 0,1";
		Quittance quittance = (Quittance) getSessionFactory()
				.getCurrentSession().createSQLQuery(query)
				.addEntity(Quittance.class).uniqueResult();
		return quittance;
	}

	public Quittance recoverQuittanceAuto(String paramNumPolice)
			throws HibernateException {
		String query = "SELECT q. * FROM quittance q JOIN avenant a ON q.num_avenant=a.num_avenant  WHERE NUM_POLICE = '"
				+ paramNumPolice + "' order by a.DATE_AVENANT desc Limit 0,1";
		Quittance quittance = (Quittance) getSessionFactory()
				.getCurrentSession().createSQLQuery(query)
				.addEntity(Quittance.class).uniqueResult();
		return quittance;
	}
	
	@Override
	public Personne personneByLogin(String login, String motPass) {
		String query = "SELECT `personne`.* FROM personne WHERE ((`personne`.`LOGIN_PERS` ='"+login+"') AND (`personne`.`MOT_PASSE_PERS` ='"+motPass+"'))";
		Personne personne  = (Personne) getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(Personne.class).uniqueResult();
		return personne;
	}
	
	

	//getters et setters
	public SessionFactory getSessionFactory() {
		return sessionFactory;}
	
    public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Physique> checkPersonPhysique(Personne personne, Physique physique) {
	 List<Physique> maListe = new ArrayList<>();
			maListe.clear();
			SimpleDateFormat dateStandard = new SimpleDateFormat("yyyy/MM/dd");			
			String myQuery = "SELECT * FROM physique WHERE NOM_RAISON_SOCIALE ='"+personne.getNomRaisonSociale()+"' " +
					"AND `PRENOM_PERS`='"+physique.getPrenomPers()+"' " +
							"AND `DATE_NAISS_PERS`='"+dateStandard.format(physique.getDateNaissPers())+"' " +
									"AND `LIEU_NAISS_PERS`='"+physique.getLieuNaissPers()+"'";
			 maListe = getSessionFactory().getCurrentSession().createSQLQuery(myQuery).addEntity(Physique.class).list();
	
		 return maListe;
	}

	public List<Sinistre> sinistreparpolice(String NumPolice)
			throws HibernateException {
			List list = getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(
							" SELECT * FROM  sinistre WHERE NUM_POLICE = '"+NumPolice+"'").addEntity(Sinistre.class).list();
			return list;
		}
		public List<Victime> getvictimes (String idsinistre)  
		throws HibernateException {
			List listVictimes = new ArrayList<Victime>();
			List list = getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(
							"SELECT V.* FROM  victime V where V.CODE_SINISTRE = '"+idsinistre
									+"'").addEntity(Victime.class).list();
			listVictimes = list;
			return  listVictimes;
	}
		
		public List<AyantDroit> getayantdroits (String idsinistre)  
		throws HibernateException {
			List listayantdroit = new ArrayList<AyantDroit>();
			List list = getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(
							"SELECT A.* FROM  ayant_droit A where A.NUM_VICTIME = '"+idsinistre
									+"'").addEntity(AyantDroit.class).list();
			listayantdroit = list;
			return  listayantdroit;
	}
		public List<Expertise> getexpertise (String idsinistre)  
		throws HibernateException {
			List listexpertise = new ArrayList<Expertise>();
			List list = getSessionFactory()
					.getCurrentSession()
					.createSQLQuery(
							"SELECT A.* FROM  expertise A where A.CODE_SINISTRE = '"+idsinistre
									+"'").addEntity(Expertise.class).list();
			listexpertise = list;
			return  listexpertise;
	}

		@SuppressWarnings("unchecked")
		@Override
		public List<Avenant> AvenantAFNPeriode(String mouvement, Date Date1,
				Date Date2) {
			List<Avenant> list = new ArrayList<Avenant>();
			String query1= "SELECT avenant.* FROM `avenant` INNER JOIN contrat ON avenant.NUM_POLICE = contrat.NUM_POLICE INNER JOIN point_vente ON contrat.CODE_POINT_VENTE = point_vente.CODE_POINT_VENTE INNER JOIN risque ON contrat.CODE_RISQUE = risque.CODE_RISQUE WHERE ((`avenant`.`MOUVEMENT` ='AFFAIRE NOUVELLE') AND (`avenant`.`DATE_AVENANT` BETWEEN '"+sdf.format(Date1)+"' AND '"+sdf.format(Date2)+"') AND (risque.LIBELLE_RISQUE = 'AUTOMOBILE'))";
			
			//String query = "SELECT `avenant`.* FROM avenant WHERE ((`avenant`.`MOUVEMENT` ='"+mouvement+"') AND (`avenant`.`DATE_AVENANT` BETWEEN '"+sdf.format(Date1)+"' AND '"+sdf.format(Date2)+"' ))";
			list = getSessionFactory().getCurrentSession().createSQLQuery(query1).addEntity(Avenant.class).list();
			return list;
		}
		

		@Override
		public Personne RecupererUtilisateurCourrant() {
			// Recupération du login de l'utilisateur courant
						String paramLogin = "";
						if (FacesContext.getCurrentInstance().getExternalContext()
								.getUserPrincipal() != null) {
							paramLogin = FacesContext.getCurrentInstance().getExternalContext()
									.getUserPrincipal().getName();
							System.out.println("paramLogin:"+paramLogin);

						}
						String query = "SELECT * FROM personne WHERE LOGIN_PERS='"+ paramLogin + "'";
						Personne connected = new Personne();
						try {

							connected = (Personne) getSessionFactory().getCurrentSession()
									.createSQLQuery(query).addEntity(Personne.class)
									.uniqueResult();
						} catch (Exception e) {
							logger.error(" Erreur sur la recupération de l'utilisateur");
						}
						return connected;
		}
		

		@SuppressWarnings("unchecked")
		@Override
		public List<Personne> personneByLogin(String login) {
			List<Personne> maListe = new ArrayList<>();
			String myQuery = "SELECT `personne`.* FROM personne WHERE (`personne`.`LOGIN_PERS` ='"+login+"')";
			 maListe = getSessionFactory().getCurrentSession().createSQLQuery(myQuery).addEntity(Personne.class).list();
			return maListe;
		}
    
		public boolean chercherLogin(String paramLogin) {
			boolean etat;
			String str = paramLogin;
			etat = false;
			try {

				String query = "SELECT * FROM `personne` WHERE `LOGIN_PERS`='"
						+ str + "'";
				List list = (List) getSessionFactory().getCurrentSession()
						.createSQLQuery(query).addEntity(Personne.class).list();
				if (list.size() >= 1) {
					etat = true;
				}
				System.out.println("Etat de la requête:" + etat);
			} catch (Exception e) {
				logger.error(" Problème de Base de données", e);
			}
			return etat;
		}
}
