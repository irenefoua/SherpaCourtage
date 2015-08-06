package com.j3a.assurance.managedBean.Auto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.AffaireApporteur;
import com.j3a.assurance.model.Apporteur;
import com.j3a.assurance.model.Avenant;
import com.j3a.assurance.model.CompteApporteur;
import com.j3a.assurance.model.Quittance;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.utilitaires.ClasseCalculDure;
import com.j3a.assurance.utilitaires.Garanties;
import com.j3a.assurance.utilitaires.IdGenerateur;
import com.j3a.assurance.utilitaires.QuittanceAuto;
import com.j3a.assurance.utilitaires.VehiculeRow;
@Component
public class ManagedQuittanceAuto {


	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ManagedQuittanceAuto.class);
	@Autowired
	ObjectService objectService;
	@Autowired
	IdGenerateur idGenerateur;
	

	private boolean disableaddContrats = true;

	// code herve bah
	private List<VehiculeRow> listVehicules =new ArrayList<VehiculeRow>();
	private QuittanceAuto quittanceAuto = new QuittanceAuto();
	private String quittanceid;
	private Avenant avenant = new Avenant();
	private int exercice;
//	private 	java.math.BigDecimal commissionApporteur = BigDecimal.ZERO
	

	// = new QuittanceList()
	public ManagedQuittanceAuto() {

	}
	public void genererIdQuittance(){
		// methode de génération de la Clé d'une Quittance

		try {

			String key = getObjectService().getCodeTable("QUIT", 4, 7,
					"quittance", "CODE_QUITTANCE");
			// if(quittanceid.equalsIgnoreCase(key)){

			// }else{
			quittanceid = key;

			// }
		} catch (Exception e) {
			logger.error(
					"Erreur dans la génération de la clé d'une Quittance !", e);
		}
	}
	
	public void calculPrime(){
		java.math.BigDecimal primeNette = BigDecimal.ZERO;
		java.math.BigDecimal primeTotale = BigDecimal.ZERO;
		java.math.BigDecimal comCon = BigDecimal.ZERO;
		java.math.BigDecimal comGes = BigDecimal.ZERO;
		java.math.BigDecimal comInter = BigDecimal.ZERO;
		java.math.BigDecimal comComp = BigDecimal.ZERO;
		java.math.BigDecimal comCoass = BigDecimal.ZERO;
		
		java.math.BigDecimal acceComp = BigDecimal.ZERO;
		System.out.println("Taille de VehiculeRow ds la quittance = "+listVehicules.size());
		for(VehiculeRow vrw:listVehicules){
		
		
		//calcul de la prime de chaque Cargaison
				
			java.math.BigDecimal prime = BigDecimal.ZERO;
			java.math.BigDecimal Con = BigDecimal.ZERO;
			java.math.BigDecimal Ges = BigDecimal.ZERO;
			java.math.BigDecimal Inter = BigDecimal.ZERO;
			java.math.BigDecimal Comp = BigDecimal.ZERO;
			java.math.BigDecimal Coass = BigDecimal.ZERO;
											 for (Garanties G :vrw.getListegaranties()){
										prime = prime.add(G.getPrimesProrata());
										Con = Con.add(G.getComCons());
										Ges = Ges.add(G.getComGes());
										Inter =Inter.add(G.getComInter());
											Coass	 = Coass.add(G.getComCoass());
											 }
												
								

		primeNette = primeNette.add(prime).setScale(0, BigDecimal.ROUND_HALF_UP);


		//Accessoire Compagnie
		
		acceComp = primeNette.multiply(BigDecimal.valueOf(0.1)).setScale(0, BigDecimal.ROUND_HALF_UP);
		//calcul de la commission de l'apporteur
		
		comGes = comGes.add(Ges).setScale(0, BigDecimal.ROUND_HALF_UP);
		comCoass = comCoass.add(Coass).setScale(0, BigDecimal.ROUND_HALF_UP);
						
		}
		primeTotale = primeTotale.add(primeNette).add(acceComp).setScale(0, BigDecimal.ROUND_HALF_UP);
		quittanceAuto.setPrimeNette(primeNette);
		quittanceAuto.setPrimeTotale(primeTotale);
		quittanceAuto.setCommissionCoassu(comCoass);
		quittanceAuto.setCommissionConseil(comCon);
		quittanceAuto.setCommissionInterm(BigDecimal.ZERO);
		quittanceAuto.setCommissionGest(comGes);
		
		quittanceAuto.setAccessoireComp(acceComp);
		
		System.out.println("Prime Nette de la quittance = "+quittanceAuto.getPrimeNette());
		
	}
public void calculQuittance(){
	
	//genererIdQuittance();
	//calcul de la taxe d'enregistrement
	java.math.BigDecimal taxeEnr = BigDecimal.ZERO,taxeFGA = BigDecimal.ZERO;
	taxeEnr =  quittanceAuto.getPrimeNette().multiply(BigDecimal.valueOf(0.145)).setScale(0, BigDecimal.ROUND_HALF_UP);
	 quittanceAuto.setTaxeEnr(taxeEnr);
	 
	 taxeFGA =  quittanceAuto.getPrimeNette().multiply(BigDecimal.valueOf(0.02)).setScale(0, BigDecimal.ROUND_HALF_UP);
	 quittanceAuto.setTaxeFGA(taxeFGA);
	//calcul de la prime à payer
	java.math.BigDecimal netteApayer = BigDecimal.ZERO;
	java.math.BigDecimal totalAccessoire = BigDecimal.ZERO;
	java.math.BigDecimal totalCommission = BigDecimal.ZERO;
	
	//Calcul de la primeExo en cours, prime à repporter et du Prec
	//Recupération des durées de Contrat DC, DEC, DAR
	
	long DC = 0;
	long DEC = 0;
	long DAR = 0;
	java.math.BigDecimal primeExo = BigDecimal.ZERO;
	java.math.BigDecimal primeRepport = BigDecimal.ZERO;
	java.math.BigDecimal prec = BigDecimal.ZERO;
	
	ClasseCalculDure calculDuree = new ClasseCalculDure(getAvenant().getEffet(), getAvenant().getEcheance(), getExercice());
	DC = calculDuree.CalculDureeContrat();
	DEC = calculDuree.calculDEC();
	DAR = calculDuree.calculDAR();
	System.out.println("************Quittance Auto***********");
	System.out.println("DC = "+DC+"Jr  ****DEC = "+DEC+"jr ****DAR = "+DAR+"Jr");
	
	double Dexo = (double)DEC/(double)DC;
	double Drep = (double)DAR/(double)DC;
	/*primeExo = quittanceAuto.getPrimeTotale().multiply(BigDecimal.valueOf(DEC)).divide(BigDecimal.valueOf(DC));
	primeRepport= quittanceAuto.getPrimeTotale().multiply(BigDecimal.valueOf(DAR)).divide(BigDecimal.valueOf(DC));*/
	primeExo = primeExo.add(quittanceAuto.getPrimeTotale().multiply(BigDecimal.valueOf(Dexo))).setScale(0, BigDecimal.ROUND_HALF_UP);
	primeRepport= quittanceAuto.getPrimeTotale().multiply(BigDecimal.valueOf(Drep)).setScale(0, BigDecimal.ROUND_HALF_UP);
	prec = primeRepport.multiply(BigDecimal.valueOf(0.72)).setScale(0, BigDecimal.ROUND_HALF_UP);
	System.out.println("primeExo = "+primeExo+"cfa  ****Prime à Repporter = "+primeRepport+"cfa ****PREC = "+prec+"cfa");
	
	
	quittanceAuto.setPrimeExoEncours(primeExo);
	quittanceAuto.setPrimeReport(primeRepport);
	quittanceAuto.setPrec(prec);
	 totalAccessoire = quittanceAuto.getAccessoireComp().add(quittanceAuto.getAccessoireInterm()).setScale(0, BigDecimal.ROUND_HALF_UP);
	 quittanceAuto.setTotalAccessoire(totalAccessoire);
	 
	 totalCommission = quittanceAuto.getCommissionAper().add(quittanceAuto.getCommissionCoassu()).add(quittanceAuto.getCommissionConseil())
			 .add(quittanceAuto.getCommissionInterm()).setScale(0, BigDecimal.ROUND_HALF_UP);
	 quittanceAuto.setTotalCommission(totalCommission);
	
	 
	 netteApayer = (quittanceAuto.getPrimeNette().add(quittanceAuto.getTaxeEnr()).add(quittanceAuto.getTaxeFGA()).add(quittanceAuto.getTotalAccessoire()))
			 .subtract((quittanceAuto.getPrimeCedee().add(
					 quittanceAuto.getRedCommerciale()).add(quittanceAuto.getRedSpeciale())).setScale(0, BigDecimal.ROUND_HALF_UP));
	 
	quittanceAuto.setNetteApayer(netteApayer);
	
	System.out.println(" Dans Quittance Auto primeExo = "+quittanceAuto.getPrimeExoEncours()+"cfa  ****Prime à Repporter = "+quittanceAuto.getPrimeReport()+"cfa ****PREC = "+quittanceAuto.getPrec()+"cfa");
}

public void calculAccessoires(){
	java.math.BigDecimal totalAccessoire = BigDecimal.ZERO;
	java.math.BigDecimal totalCommission = BigDecimal.ZERO;
	totalAccessoire = quittanceAuto.getAccessoireComp().add(quittanceAuto.getAccessoireInterm()).setScale(0, BigDecimal.ROUND_HALF_UP);
	 quittanceAuto.setTotalAccessoire(totalAccessoire);
	 
	 totalCommission = quittanceAuto.getCommissionAper().add(quittanceAuto.getCommissionCoassu()).add(quittanceAuto.getCommissionConseil())
			 .add(quittanceAuto.getCommissionInterm()).setScale(0, BigDecimal.ROUND_HALF_UP);
	 quittanceAuto.setTotalCommission(totalCommission);
}

public void addcommissionApporteur(Apporteur apporteur, Avenant Avn){
	java.math.BigDecimal commission = BigDecimal.ZERO;
	
	/*//calcul de la commission de l'apporteur
			if(apporteur.getTypeApporteur().getIdType().equals("1") || apporteur.getTypeApporteur().getIdType().equals("2")){
				commission = commission.add(quittanceAuto.getCommissionInterm());	
			}
			
			if(apporteur.getTypeApporteur().getIdType().equals("3")){
				commission = commission.add(quittanceAuto.getCommissionConseil());
			}
			System.out.println(" <<<<<<Commission  de l'apporteur= "+commission); */
	//Ajout de l'Affaire de l'apporteur et maj du compte de l'apporteur
			Date date = new Date();
			date = Calendar.getInstance().getTime();
			AffaireApporteur affaire = new AffaireApporteur();
			affaire.setApporteur(apporteur);
			affaire.setCodeAffaire(getIdGenerateur().getIdAffaireApporteur(apporteur));
			//affaire.getComAff().add(
			affaire.setComAff(commission);
			affaire.setDateAff(date);
			affaire.setEtatReglAff("NON REGLEE");
			//affaire.getResteAPayerAff().add(
			affaire.setResteAPayerAff(commission);
			affaire.setMouvementAffApp(Avn.getMouvement());
			affaire.setNumeroPoliceAff(Avn.getContrat().getNumPolice());
			affaire.setNumAvenantAff(Avn.getNumAvenant());
			CompteApporteur compte = new CompteApporteur();
			compte = (CompteApporteur)getObjectService().getById("compte_apporteur", "CODE_APPORTEUR", apporteur.getCodeApporteur(), CompteApporteur.class);
			System.out.println(" <<<<<<Compte de l'apporteur= "+compte.getCodeCompteApp()); 
			System.out.println(" <<<<<<Compte de l'apporteur Total Commission= "+compte.getTotalComApp()); 
		
			compte.setTotalComApp(compte.getTotalComApp().add(commission));
			compte.setResteComPaye(compte.getResteComPaye().add(commission));
			getObjectService().addObject(affaire);
			getObjectService().updateObject(compte);
}

	
	public String genererRegmentId(String numQuittance){//By ALekerand
		String KeyReglement = "R"+numQuittance;
		return KeyReglement;
	}
	
	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}

	public String getQuittanceid() {
		return quittanceid;
	}

	public void setQuittanceid(String quittanceid) {
		this.quittanceid = quittanceid;
	}

	
	public void addQuittance(Avenant Avn) {
		Date date = new Date();
		date = Calendar.getInstance().getTime();
		Quittance quittances = new Quittance();

		quittances.setCodeQuittance(getQuittanceid());
		quittances.setEtatQuittance("NON SOLDEE");
		quittances.setAccessoire(getQuittanceAuto()
				.getTotalAccessoire());
		quittances.setAccCnie(getQuittanceAuto()
				.getAccessoireComp());
		quittances.setAccGestionnaire(getQuittanceAuto()
			.getAccessoireGest());
		quittances.setAccIntrerm(getQuittanceAuto()
				.getAccessoireInterm());

		quittances.setComAperition(getQuittanceAuto()
				.getCommissionAper());
		quittances.setComConseil(getQuittanceAuto()
				.getCommissionConseil());
		quittances.setComGestionnaire(getQuittanceAuto()
				.getCommissionGest());
		quittances.setComInterm(getQuittanceAuto()
				.getCommissionInterm());
		quittances.setPrimeCedee(getQuittanceAuto()
				.getPrimeCedee());
		quittances.setFga(getQuittanceAuto().getTaxeFGA());
		quittances.setCommision(getQuittanceAuto().getTotalCommission());
		/*quittances.setPrimeAnnuelle(getQuittanceList()
				.getPrimeAnn()));*/
		quittances.setPrimeNette(getQuittanceAuto()
				.getPrimeNette());
		quittances.setNetAPayer(getQuittanceAuto()
				.getNetteApayer());
		// quittances.setReduction(getQuittanceList().getTotalRed());
		quittances.setTaxes(getQuittanceAuto().getTaxeEnr());
		//quittances.setFga(getQuittanceList().getFga()));
		
		//les primes exo et prec
		quittances.setPrimeTotale(getQuittanceAuto().getPrimeTotale());
		quittances.setPrec(getQuittanceAuto().getPrec());
		quittances.setPrimeExoEncours(getQuittanceAuto().getPrimeExoEncours());
		quittances.setPrimeReport(getQuittanceAuto().getPrimeReport());
		quittances.setAvenant(Avn);
		quittances.setDateQuittance(date);
		try {
			getObjectService().addObject(quittances);

			quittanceid = quittances.getCodeQuittance();// recupère l'id de la quittance

		} catch (DataAccessException e) {
			logger.error(
					" Problème lors de l'enregistrement dans la Table quittance",
					e);
		}

	}
	public void resetQuittance(){
		//vehiculeList.clear();
		
		 quittanceid = null;

		/*primeAnn = 0;
		primePror =0;
				fga =0;
				taxe =0;
				taxeRed =0;
				redFlotte=0;
				bonus =0;
				malus =0;
				reductionG =0;
				flotte =0;
				permis = 0;
		commissionAP =0;
				commission =0; 
				accessoireAP =0;
				commissionCon =0; 
				accessoire = 0;
		totalAccessoire =0; 
				totalCommission =0;
				taxeAccessoire = 0;
		redCommerciale =0;
				redSpeciale = 0;
	taxeCommission =0; 
			taxePrime =0; 
			totalPrime =0;
				totalRed =0;
				totaltaxe =0;
				totalApayer = 0;	*/
	}

	
	public String newContrat(){
	return "contrats";
	}
	
	public void viewVehicule(){
		
	}
	/* Méthodes de calcule et d'enregistrement de l'avenant Changement de véhicule*/
	public void addQuitannceChangementVehicule(){
		
	}


	public QuittanceAuto getQuittanceAuto() {
		return quittanceAuto;
	}
	public void setQuittanceAuto(QuittanceAuto quittanceAuto) {
		this.quittanceAuto = quittanceAuto;
	}
	
	public boolean isDisableaddContrats() {
		return disableaddContrats;
	}

	public void setDisableaddContrats(boolean disableaddContrats) {
		this.disableaddContrats = disableaddContrats;
	}
	public List<VehiculeRow> getListVehicules() {
		return listVehicules;
	}
	public void setListVehicules(List<VehiculeRow> listVehicules) {
		this.listVehicules = listVehicules;
	}
	public Avenant getAvenant() {
		return avenant;
	}
	public void setAvenant(Avenant avenant) {
		this.avenant = avenant;
	}
	public int getExercice() {
		return exercice;
	}
	public void setExercice(int exercice) {
		this.exercice = exercice;
	}
	public IdGenerateur getIdGenerateur() {
		return idGenerateur;
	}
	public void setIdGenerateur(IdGenerateur idGenerateur) {
		this.idGenerateur = idGenerateur;
	}
	




}
