package com.j3a.assurance.objetService;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.j3a.assurance.model.AffilieListeAffilie;
import com.j3a.assurance.model.AlimentSinistre;
import com.j3a.assurance.model.Apporteur;
import com.j3a.assurance.model.ApporteurAdherent;
import com.j3a.assurance.model.ApporteurAliment;
import com.j3a.assurance.model.ApporteurAssure;
import com.j3a.assurance.model.ApporteurCorpsEngin;
import com.j3a.assurance.model.ApporteurGestionConfiee;
import com.j3a.assurance.model.ApporteurHabitation;
import com.j3a.assurance.model.ApporteurNta;
import com.j3a.assurance.model.ApporteurVehicule;
import com.j3a.assurance.model.AssureIaSinistre;
import com.j3a.assurance.model.AvListeAdherent;
import com.j3a.assurance.model.AvListeAssureIa;
import com.j3a.assurance.model.Avenant;
import com.j3a.assurance.model.Contrat;
import com.j3a.assurance.model.CorpsSinistre;
import com.j3a.assurance.model.Exercice;
import com.j3a.assurance.model.GestionConfieeSinistre;
import com.j3a.assurance.model.HabitationSinistre;
import com.j3a.assurance.model.ListeAffilie;
import com.j3a.assurance.model.Personne;
import com.j3a.assurance.model.Quittance;
import com.j3a.assurance.model.Risque;
import com.j3a.assurance.model.RisqueNtaSinistre;
import com.j3a.assurance.model.Sinistre;
import com.j3a.assurance.model.SocieteAssurance;
import com.j3a.assurance.model.Utilisateur;
import com.j3a.assurance.model.Vehicule;
import com.j3a.assurance.model.VehiculeSinistre;
import com.j3a.assurance.utilitaires.hybride.CompteClient;

@Transactional
/* Selection du dernier avenant */
public class InfoAvenantContrat {
	private SessionFactory sessionFactory;
	private static Logger logger = Logger.getLogger(InfoAvenantContrat.class);
	private Avenant resultatAvenant = new Avenant();
	private SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public Avenant DernierAvenant(String paramPolice) {
		String query = "SELECT * FROM AVENANT where NUM_POLICE = '"
				+ paramPolice + "' order by DATE_AVENANT  desc Limit 0,1";
		Avenant av = (Avenant) getSessionFactory().getCurrentSession()
				.createSQLQuery(query).addEntity(Avenant.class).uniqueResult();
		setResultatAvenant(av);
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

	public Exercice exerciceOuvert() throws HibernateException {
		String req = "SELECT * FROM EXERCICE WHERE ETAT_EXERCICE LIKE 'ouvert%' order by CODEEXERCICE DESC LIMIT 0,1";
		Exercice exo = (Exercice) getSessionFactory().getCurrentSession()
				.createSQLQuery(req).addEntity(Exercice.class).uniqueResult();
		return exo;
	}

	public AvListeAssureIa DerniereListeAvAssure(String param)
			throws HibernateException {
		String query = "SELECT * FROM AV_LISTE_ASSURE_IA where NUM_AVENANT='"
				+ param + "' order by DATE_AV_LISTE_ASSURE_IA desc Limit 0,1 ";
		AvListeAssureIa avlistass = (AvListeAssureIa) getSessionFactory()
				.getCurrentSession().createSQLQuery(query)
				.addEntity(AvListeAssureIa.class).uniqueResult();
		logger.info("Selection du dernier ListeAvenantAssuré sur de la police '"
				+ param + "'");
		return avlistass;
	}

	public AvListeAdherent DerniereListeAdherent(String param)
			throws HibernateException {
		String query = "SELECT * FROM AV_LISTE_ADHERENT where NUM_AVENANT='"
				+ param + "' order by DATE_AV_ADHERENT desc Limit 0,1 ";
		AvListeAdherent avadh = (AvListeAdherent) getSessionFactory()
				.getCurrentSession().createSQLQuery(query)
				.addEntity(AvListeAdherent.class).uniqueResult();
		logger.info("Selection du dernier ListeAherent sur de la police '"
				+ param + "'");
		return avadh;
	}

	public ListeAffilie DerniereListeAffilie(String param)
			throws HibernateException {
		String query = "SELECT * FROM LISTE_AFFILIE where CODE_ADHERENT='"
				+ param + "' Limit 0,1 ";
		ListeAffilie lstaff = (ListeAffilie) getSessionFactory()
				.getCurrentSession().createSQLQuery(query)
				.addEntity(ListeAffilie.class).uniqueResult();
		logger.info("Selection du dernier ListeAherent sur de la police '"
				+ param + "'");
		return lstaff;
	}

	public AffilieListeAffilie DerniereAffiliListeAffilie(String param)
			throws HibernateException {
		String query = "SELECT * FROM AFFILIE_LISTE_AFFILIE where CODE_LISTE_AFFILIE='"
				+ param + "' order by DATE_LISTE_AFFILIE desc Limit 0,1 ";
		AffilieListeAffilie afflstaff = (AffilieListeAffilie) getSessionFactory()
				.getCurrentSession().createSQLQuery(query)
				.addEntity(AffilieListeAffilie.class).uniqueResult();
		logger.info("Selection du dernier ListeAherent sur de la police '"
				+ param + "'");
		return afflstaff;
	}

	// SInistre Infos Sinistre

	public AssureIaSinistre infoSinistre(String param)
			throws HibernateException {
		String query = "SELECT * FROM ASSURE_IA_SINISTRE where CODE_SINISTRE='"
				+ param + "' order by DATE_SINISTRE_IA desc Limit 0,1 ";
		AssureIaSinistre assSin = (AssureIaSinistre) getSessionFactory()
				.getCurrentSession().createSQLQuery(query)
				.addEntity(AssureIaSinistre.class).uniqueResult();
		logger.info("Selection des information du sinistre '" + param + "'");
		return assSin;
	}

	public VehiculeSinistre infoSinistreAuto(String param)
			throws HibernateException {
		String query = "SELECT * FROM VEHICULE_SINISTRE where CODE_SINISTRE='"
				+ param + "' order by DATE_SINISTRE desc Limit 0,1 ";
		VehiculeSinistre assSin = (VehiculeSinistre) getSessionFactory()
				.getCurrentSession().createSQLQuery(query)
				.addEntity(VehiculeSinistre.class).uniqueResult();
		logger.info("Selection des information du sinistre '" + param + "'");
		return assSin;
	}
	
	
   public List <Apporteur> ApporteurPV(String pv) throws HibernateException{
		String query="select distinct CODE_APPORTEUR from contrat where CODE_POINT_VENTE='"+pv+"'";
		List apporteur=getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(Apporteur.class).list();
			return apporteur;		
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
	
      public List <ApporteurAdherent> infoapporteurAdherent(String codeapporteur,String debut,String fin) throws HibernateException{

		
		String query="select * from apporteur_adherent where CODE_APPORTEUR='"+codeapporteur+"' and DATE_APPORTEUR_ADHERENT BETWEEN '"+debut+"' and '"+fin+"'";
		List apporteurAdereht=getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(ApporteurAdherent.class).list();
			return apporteurAdereht;		
	}
      
      
      public List <ApporteurAliment> infoapporteurAliment(String codeapporteur,String debut,String fin) throws HibernateException{
  		String query="select * from apporteur_aliment where CODE_APPORTEUR='"+codeapporteur+"' and DATE_APPORTEUR_ALIMENT BETWEEN '"+debut+"' and '"+fin+"'";
  		List apporteurAliment=getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(ApporteurAliment.class).list();
  			return apporteurAliment;		
  	}
      
      public List <ApporteurAssure> infoapporteurAssure(String codeapporteur,String debut,String fin) throws HibernateException{
    		String query="select * from apporteur_assure where CODE_APPORTEUR='"+codeapporteur+"' and DATE_APPORTEUR_ASSURE BETWEEN '"+debut+"' and '"+fin+"'";
    		List apporteurAssure=getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(ApporteurAssure.class).list();
    			return apporteurAssure;		
    	}
	
      
      public List <ApporteurCorpsEngin> infoapporteurCorpsEngin(String codeapporteur,String debut,String fin) throws HibernateException{
  		String query="select * from apporteur_corps_engin where CODE_APPORTEUR='"+codeapporteur+"' and DATE_APPORTEUR_CORPS_ENGIN BETWEEN '"+debut+"' and '"+fin+"'";
  		List apporteurAssure=getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(ApporteurCorpsEngin.class).list();
  			return apporteurAssure;		
  	}
      
      public List <ApporteurGestionConfiee> infoapporteurGC(String codeapporteur,String debut,String fin) throws HibernateException{
    		String query="select * from apporteur_gestion_confiee where CODE_APPORTEUR='"+codeapporteur+"' and DATE_APPORTEUR_GC BETWEEN '"+debut+"' and '"+fin+"'";
    		List apporteurAssure=getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(ApporteurGestionConfiee.class).list();
    			return apporteurAssure;		
    	}
      
      public List <ApporteurHabitation> infoapporteurHabitations(String codeapporteur,String debut,String fin) throws HibernateException{
  		String query="select * from apporteur_habitation where CODE_APPORTEUR='"+codeapporteur+"' and DATE_APPORTEUR_HABITATION BETWEEN'"+debut+"' and '"+fin+"'";
  		List apporteurAssure=getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(ApporteurHabitation.class).list();
  			return apporteurAssure;		
  	}
	
      public List <ApporteurNta> infoapporteurNtas(String codeapporteur,String debut,String fin) throws HibernateException{
    		String query="select * from apporteur_nta where CODE_APPORTEUR='"+codeapporteur+"' and DATE_APPORTEUR_NTA BETWEEN'"+debut+"' and '"+fin+"'";
    		List apporteurnta=getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(ApporteurNta.class).list();
    			return apporteurnta;		
    	}
	
	// SInistre Infos Sinistre Habitation

	public HabitationSinistre infoSinistreH(String param)
			throws HibernateException {
		String query = "SELECT * FROM HABITATION_SINISTRE where CODE_SINISTRE='"
				+ param + "' order by DATE_SINISTRE_HABITATION desc Limit 0,1 ";
		HabitationSinistre habSin = (HabitationSinistre) getSessionFactory()
				.getCurrentSession().createSQLQuery(query)
				.addEntity(HabitationSinistre.class).uniqueResult();
		logger.info("Selection des information du sinistre '" + param + "'");
		return habSin;
	}

	// SInistre Infos Sinistre Risques Divers

	public RisqueNtaSinistre infoSinistreNta(String param)
			throws HibernateException {
		String query = "SELECT * FROM RISQUE_NTA_SINISTRE where CODE_SINISTRE='"
				+ param + "' order by DATE_SINISTRE_NTA desc Limit 0,1 ";
		RisqueNtaSinistre ntasin = (RisqueNtaSinistre) getSessionFactory()
				.getCurrentSession().createSQLQuery(query)
				.addEntity(RisqueNtaSinistre.class).uniqueResult();
		logger.info("Selection des information du sinistre '" + param + "'");
		return ntasin;
	}

	// SInistre Infos Sinistre Faculte

	public AlimentSinistre infoSinistreFAC(String param)
			throws HibernateException {
		String query = "SELECT * FROM ALIMENT_SINISTRE where CODE_SINISTRE='"
				+ param + "' order by DATE_SINISTRE_FACULTE desc Limit 0,1 ";
		AlimentSinistre ntasin = (AlimentSinistre) getSessionFactory()
				.getCurrentSession().createSQLQuery(query)
				.addEntity(AlimentSinistre.class).uniqueResult();
		logger.info("Selection des information du sinistre '" + param + "'");
		return ntasin;
	}

	// SInistre Infos Sinistre Corps

	public CorpsSinistre infoSinistreCORPS(String param)
			throws HibernateException {
		String query = "SELECT * FROM CORPS_SINISTRE where CODE_SINISTRE='"
				+ param + "' order by DATE_SINISTRE_CORPS desc Limit 0,1 ";
		CorpsSinistre ntasin = (CorpsSinistre) getSessionFactory()
				.getCurrentSession().createSQLQuery(query)
				.addEntity(CorpsSinistre.class).uniqueResult();
		logger.info("Selection des information du sinistre '" + param + "'");
		return ntasin;
	}

	// sinistre GC
	public GestionConfieeSinistre infoSinistreGC(String param)
			throws HibernateException {

		String query = "SELECT * FROM GESTION_CONFIEE_SINISTRE where CODE_SINISTRE='"
				+ param + "' order by DATE_SINISTRE_GC desc Limit 0,1 ";
		GestionConfieeSinistre ntasin = (GestionConfieeSinistre) getSessionFactory()
				.getCurrentSession().createSQLQuery(query)
				.addEntity(GestionConfieeSinistre.class).uniqueResult();
		logger.info("Selection des information du sinistre '" + param + "'");
		return ntasin;
	}

	/* dernier sinistre */
	/* selection du dernier sinistre d'une police */
	public Sinistre DernierSinistre(String police) throws HibernateException {
		String query = "SELECT * FROM SINISTRE where NUM_POLICE = '" + police
				+ "' order by DATE_DECLARATION  desc Limit 0,1";
		Sinistre st = (Sinistre) getSessionFactory().getCurrentSession()
				.createSQLQuery(query).addEntity(Sinistre.class).uniqueResult();
		logger.info("Selection du dernier Sinistre sur de la police '" + police
				+ "'");
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

	/* selection d'un contrat qui a été annulé */
	public Avenant ContratAnnule(String police) {
		String query = "SELECT * FROM AVENANT where NUM_POLICE = '"
				+ police
				+ "' AND MOUVEMENT='Annulation' order by DATE_AVENANT  desc Limit 0,1";
		Avenant av = (Avenant) getSessionFactory().getCurrentSession()
				.createSQLQuery(query).addEntity(Avenant.class).uniqueResult();
		logger.info("Selection du dernier Avenant Annulé sur de la police '"
				+ police + "'");
		return av;
	}

	/* selection d'un contrat qui a été suspendu */
	public Avenant ContratSuspendu(String police) {
		String query = "SELECT * FROM AVENANT where NUM_POLICE = '"
				+ police
				+ "' AND MOUVEMENT='Suspension' order by DATE_AVENANT  desc Limit 0,1";
		Avenant av = (Avenant) getSessionFactory().getCurrentSession()
				.createSQLQuery(query).addEntity(Avenant.class).uniqueResult();
		logger.info("Selection du dernier Avenant suspendu sur la police '"
				+ police + "'");
		return av;
	}

	/* Selection de la societe d'assurance propietaire de l'application */
	public SocieteAssurance SteAss() {
		String query = "SELECT * FROM SOCIETE_ASSURANCE Limit 0, 1";
		SocieteAssurance as = (SocieteAssurance) getSessionFactory()
				.getCurrentSession().createSQLQuery(query)
				.addEntity(SocieteAssurance.class).uniqueResult();
		logger.info("Selection de la société d'assurance propriétaire de l'application");
		return as;
	}

	public Utilisateur User() {
		String query = "SELECT * FROM UTILISATEUR Limit 0, 1";
		Utilisateur us = (Utilisateur) getSessionFactory().getCurrentSession()
				.createSQLQuery(query).addEntity(Utilisateur.class)
				.uniqueResult();
		return us;
	}

	public Personne Pers(String perso) {
		String query = " SELECT * FROM CONTRAT WHERE NUM_POLICE='" + perso
				+ "' ";
		Contrat ct = (Contrat) getSessionFactory().getCurrentSession()
				.createSQLQuery(query).addEntity(Contrat.class).uniqueResult();
		String dobe = ct.getPersonne().getNumSouscripteur();
		String querys = "SELECT * FROM PERSONNE WHERE NUM_SOUSCRIPTEUR='"
				+ dobe + "' ";
		Personne pers = (Personne) getSessionFactory().getCurrentSession()
				.createSQLQuery(querys).addEntity(Personne.class)
				.uniqueResult();

		System.out
				.print("****************************************************************");
		System.out.print("query contrat    " + query);
		System.out.print("query souscripteur   " + querys);

		return pers;
	}

	public List<CompteClient> compte(String client) {
		String query = "SELECT PERSONNE.NOM_RAISON_SOCIAL, QUITTANCE.CODE_QUITTANCE,QUITTANCE.ETAT_QUITTANCE, QUITTANCE.PRIME_TTC, QUITTANCE.NUM_AVENANT, AVENANT.NUM_POLICE, REGLEMENT.CODE_REGLEMENT, REGLEMENT.MONTANT_REGLEMENT, REGLEMENT.DATE_REGLEMENT, REGLEMENT.CODE_TYPE_REGLEMENT  FROM PERSONNE, QUITTANCE, AVENANT, CONTRAT, REGLEMENT, TYPE_REGLEMENT WHERE PERSONNE.NUM_SOUSCRIPTEUR='"
				+ client
				+ "' AND REGLEMENT.CODE_TYPE_REGLEMENT=TYEPE_REGLEMENT.CODE_TYPE_REGLEMENT AND REGLEMENT.NUM_SOUSCRIPTEUR=PERSONNE.NUM_SOUSCRIPTEUR AND REGLEMENT.CODE_QUITTANCE=QUITTANCE.CODE_QUITTANCE AND QUITTANCE.NUM_AVENANT=AVENANT.NUM_AVENANT AND avenant.NUM_POLICE=CONTRAT.NUM_POLICE";
		@SuppressWarnings("unchecked")
		List<CompteClient> obj = (List<CompteClient>) getSessionFactory()
				.getCurrentSession().createSQLQuery(query)
				.addEntity(CompteClient.class).list();
		System.out.println("////////////////////query  =" + query);
		for (CompteClient cc : obj) {
			System.out.println("////////////////////query  ="
					+ cc.getPersonne().getNumSouscripteur());
		}
		return obj;
	}

	/**
	 * Méthode (requête) pour recuperer le dernier avenant de modification de
	 * prise d'effet d'une police de contrat
	 * 
	 * @return resultatAvenant
	 * @author ALekerand
	 */
	public Avenant recoverLastAvenantModifEffet(String police)
			throws HibernateException {// By ALekerand
		String query = "SELECT * FROM `avenant` WHERE `NUM_POLICE`='"
				+ police
				+ "' AND `CODE_MOUVEMENT`='MOUV02' order by DATE_AVENANT desc Limit 0,1";
		resultatAvenant = (Avenant) getSessionFactory().getCurrentSession()
				.createSQLQuery(query).addEntity(Avenant.class).uniqueResult();
		return resultatAvenant;

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

	/**
	 * selection d'un avenant affairenouvelle
	 * 
	 * @author Irene
	 */
	public Avenant trouverContratAffaireNouvelle(String police) {
		String query = "SELECT * FROM AVENANT where NUM_POLICE = '" + police
				+ "' AND MOUVEMENT='AFFAIRE NOUVELLE' OR MOUVEMENT='Modification Prise Effet' OR MOUVEMENT='Renouvellement' order by DATE_AVENANT desc Limit 0,1 ";
		Avenant av = (Avenant) getSessionFactory().getCurrentSession()
				.createSQLQuery(query).addEntity(Avenant.class).uniqueResult();
		logger.info("Selection de l'avenant affaire nouvelle sur la police '"
				+ police + "'");
		return av;
	}

	// Proprietes recentes d'un véhicule
	public Vehicule pVehicule(String id) {
		String query = "SELECT * FROM VEHICULE where CODE_VEHICULE = '" + id
				+ "'";
		Vehicule pv = (Vehicule) getSessionFactory().getCurrentSession()
				.createSQLQuery(query).addEntity(Vehicule.class).uniqueResult();
		logger.info("Selection du dernières propriétés du véhicule '" + id
				+ "'");
		return pv;
	}

	@Transactional
	public List<Contrat> getContratParAnneeParRisque(String codeRisque,
			int annee) throws HibernateException {
		String myQuery = "SELECT c.*"
				+ " FROM contrat c, avenant a WHERE c.NUM_POLICE = a.NUM_POLICE and c.code_risque = '"
				+ codeRisque + "' and extract(year from a.date_avenant)='"
				+ annee + "'";
		List contrat = getSessionFactory().getCurrentSession()
				.createSQLQuery(myQuery).addEntity(Contrat.class).list();

		return contrat;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Avenant getResultatAvenant() {
		return resultatAvenant;
	}

	public void setResultatAvenant(Avenant resultatAvenant) {
		this.resultatAvenant = resultatAvenant;
	}

	public SimpleDateFormat getFormater() {
		return formater;
	}

	public void setFormater(SimpleDateFormat formater) {
		this.formater = formater;
	}

}
