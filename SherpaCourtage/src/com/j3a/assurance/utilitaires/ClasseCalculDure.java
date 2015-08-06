package com.j3a.assurance.utilitaires;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.j3a.assurance.model.Avenant;
import com.j3a.assurance.model.Exercice;
import com.j3a.assurance.model.Quittance;




public class ClasseCalculDure implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date effet ,echeance;
	private double duree = 12;
	private int exercice=0;
	private Long dureExoCours;
	private Long dureAReporter;
	private Long dureeEnjour;
	private BigDecimal primeTot;
	private BigDecimal primeExerEnCours;
	private BigDecimal primeToReport;
	private BigDecimal prec;
	private List<Avenant> avenantsList;
	private Avenant treatedAven;
	private Exercice treatedExo;
	private Quittance quittance;
	private Avenant avenPrec, avenOrig;
	
	
	
	public ClasseCalculDure(Date effet, Date echeance, int exercice ){
		this.echeance=echeance;
		this.effet=effet;
		this.exercice= exercice;
	}
	
	public ClasseCalculDure(List<Avenant> avenantsList, Avenant treatedAven, Exercice treatedExo, Quittance quittance){
		this.avenantsList=avenantsList;
		this.treatedAven=treatedAven;
		this.treatedExo= treatedExo;
		this.quittance=quittance;
	}
	
	public void processCalculation(){
		//Avenant à Prime non nulle !!!!!!!!! consider Annulation date effet decalé de dateEffet aff new
		if(quittance.getPrimeTotale().compareTo(BigDecimal.ZERO)!=0){
			
			if(treatedAven.getMouvement().equalsIgnoreCase("Annulation")){
				//Evaluation du montant Actuel du contrat
				BigDecimal curValueCtra = contratValues(avenantsList);
				//Evaluation des Primes Spé(Pec, Par, Prec) à retirer
				evaluatePrimeSpe(getAvenPrec().getEffet(), getAvenPrec().getEcheance(), getAvenPrec().getExercice().getCodeexercice(), curValueCtra);
				//Retrait des primeSpé de l'exo
				BigDecimal E = getAvenPrec().getExercice().getPrimeExercice();
				getAvenPrec().getExercice().setPrimeExercice( E.subtract(getPrimeExerEnCours()));
				BigDecimal R = getAvenPrec().getExercice().getPrimeAReporterExo();
				getAvenPrec().getExercice().setPrimeAReporterExo( R.subtract(getPrimeToReport()));
				BigDecimal prC = getAvenPrec().getExercice().getPrecExo();
				getAvenPrec().getExercice().setPrecExo( prC.subtract(getPrec()));
				
				quittance.setPrimeExoEncours(getPrimeExerEnCours().negate());
				quittance.setPrimeReport(getPrimeToReport().negate());
				quittance.setPrec(getPrec().negate());
			}else{
				evaluatePrimeSpe(treatedAven.getEffet(), treatedAven.getEcheance(), treatedExo.getCodeexercice(), quittance.getPrimeTotale());
				treatedExo.setPrimeExercice(treatedExo.getPrimeExercice().add(getPrimeExerEnCours()));
				treatedExo.setPrimeAReporterExo(treatedExo.getPrimeAReporterExo().add(getPrimeToReport()));
				treatedExo.setPrecExo(treatedExo.getPrecExo().add(getPrec()));
				
				quittance.setPrimeExoEncours(getPrimeExerEnCours());
				quittance.setPrimeReport(getPrimeToReport());
				quittance.setPrec(getPrec());
			}
			
			//Cas de l'annulation traitement particulier 
			//àcause du decalage de sa date d'effet par rapport à celle du Contrat
			
		}
		//Avenant à prime Nulle sans modif de date
			// PrimeGénérée : 0 
			// Aucun mouvement de date
			//Baaaaa life is beautifull!!! What could be better? :D
		
		//Avenant à prime nulle avec modif de date (Modif Date Effet é RemizenVigeur avec prorogation)
		if((quittance.getPrimeTotale().compareTo(BigDecimal.ZERO)==0) && (treatedAven.getEcheance().after(getAvenOrig().getEcheance()))  ){
			//Evaluation du montant Actuel du contrat
			BigDecimal curValueCtra = contratValues(avenantsList);
			//Evaluation des Primes Spé(Pec, Par, Prec) à retirer
			evaluatePrimeSpe(getAvenPrec().getEffet(), getAvenPrec().getEcheance(), getAvenPrec().getExercice().getCodeexercice(), curValueCtra);
			//Retrait des primeSpé de l'exo
			BigDecimal E = getAvenPrec().getExercice().getPrimeExercice();
			getAvenPrec().getExercice().setPrimeExercice( E.subtract(getPrimeExerEnCours()));
			BigDecimal R = getAvenPrec().getExercice().getPrimeAReporterExo();
			getAvenPrec().getExercice().setPrimeAReporterExo( R.subtract(getPrimeToReport()));
			BigDecimal prC = getAvenPrec().getExercice().getPrecExo();
			getAvenPrec().getExercice().setPrecExo( prC.subtract(getPrec()));
			//Evaluation des primeSpé à Ajouter
			evaluatePrimeSpe(treatedAven.getEffet(), treatedAven.getEcheance(), treatedExo.getCodeexercice(), curValueCtra);
			// Ajout des primeSpé à l'Exo
			BigDecimal adE = treatedExo.getPrimeExercice();
			treatedExo.setPrimeExercice( adE.add(getPrimeExerEnCours()));
			BigDecimal adR = treatedExo.getPrimeAReporterExo();
			treatedExo.setPrimeAReporterExo( adR.add(getPrimeToReport()));
			BigDecimal adprC = treatedExo.getPrecExo();
			treatedExo.setPrecExo( adprC.add(getPrec()));
			
			//MAJ de la quittance de l'avenant en traitement :"Treated Aven"
			quittance.setPrimeExoEncours(getPrimeExerEnCours());
			quittance.setPrimeReport(getPrimeToReport());
			quittance.setPrec(getPrec());
			
		}
		
	}
	
	public void evaluatePrimeSpe(Date effet, Date echeance, int exercice, BigDecimal primeTot){
		this.echeance=echeance;
		this.effet=effet;
		this.exercice = exercice;
		this.primeTot=primeTot;
		//#d=Prime Exercice en cour = [(b=Prime Totale)*DEC]/DC
		BigDecimal DEC = new BigDecimal( calculDEC());
		BigDecimal DC = new BigDecimal(CalculDureeContrat());
		this.primeExerEnCours = getPrimeTot().multiply(DEC).divide(DC,3, BigDecimal.ROUND_UP);
		//#e=Prime à reporter = [(b=Prime Totale)*DAR]/DC
		BigDecimal DAR = new BigDecimal( calculDAR());
		//BigDecimal DC = new BigDecimal(CalculDureeContrat());
		this.primeToReport = getPrimeTot().multiply(DAR).divide(DC,3, BigDecimal.ROUND_UP);
		//#f=Prec = 72% e
		BigDecimal perCent72 = new BigDecimal("0.72");
		this.prec = getPrimeToReport().multiply(perCent72).setScale(3, BigDecimal.ROUND_UP);
	}
		
	public void calculDureEcheance(){
			Calendar calender = Calendar.getInstance();
			calender.setTime(effet);

			if (duree == 0.5) {
				calender.add(Calendar.DATE, 15);
			} else {
				calender.add(Calendar.MONTH, (int) getDuree());
			}
			calender.add(Calendar.DATE, 0);
			calender.set(Calendar.HOUR_OF_DAY, 23);
			calender.set(Calendar.MINUTE, 59);
			System.out.println("CJKDFRIOEI"+calender.getTime());
			setEcheance(calender.getTime());	
		}
	
	
	
	public long CalculDureeContrat() {
		Long nbjour = null;
		
		if (duree == 0.5) {
			nbjour = (long) 15;
		} else {

			Calendar dateDebut = Calendar.getInstance();
			dateDebut.setTime(effet);

			Calendar dateEnd = Calendar.getInstance();
			dateEnd.setTime(echeance);

			Long millDebut = dateDebut.getTimeInMillis();
			Long millEnd = dateEnd.getTimeInMillis();
			Long diff = millEnd - millDebut;

			nbjour = diff / (24 * 60 * 60 * 1000);
		}
		setDureeEnjour(nbjour);
		return nbjour;	
	}

	
	
	
	public long calculDEC(){
		Long nbjour = null;
	
		if (duree == 0.5) {
			nbjour = (long) 15;
		} else {
	Calendar calendar=Calendar.getInstance();
	
	//l'annee de l'exo en cours
	calendar.set(Calendar.YEAR, exercice);
	calendar.set(Calendar.MONTH, 11);
	calendar.set(Calendar.DAY_OF_MONTH, 31);
	Date finExo = calendar.getTime();
	
	System.out.println("dsfgdgtergtrg"+finExo);
	
	//convertion en jours
	Calendar finExo1=Calendar.getInstance();
	finExo1.setTime(finExo);
	
	Calendar ech=Calendar.getInstance();
	ech.setTime(effet);
	
	Long millDebut = finExo1.getTimeInMillis();
	Long millfin = ech.getTimeInMillis();
	
	
	//la duree de l'exo en cours
	Long diff = millDebut - millfin ;
	nbjour=diff/ (1000*60*60*24);
		}
	
	setDureExoCours(nbjour);	
	return nbjour;	
	}
			
	public long calculDAR(){
	//convertir la date echeance
		Long nbjour = null;
		
		if (duree == 0.5) {
			nbjour = (long) 15;
		} else {
	Calendar calendar=Calendar.getInstance();
	
	//l'annee de l'exo en cours
	calendar.set(Calendar.YEAR, exercice);
	calendar.set(Calendar.MONTH, 11);
	calendar.set(Calendar.DAY_OF_MONTH, 31);
	Date finExo = calendar.getTime();
	
	System.out.println("dsfgdgtergtrg"+finExo);
	
	//convertion en jours
	Calendar finExo1=Calendar.getInstance();
	finExo1.setTime(finExo);
	
	Calendar ech=Calendar.getInstance();
	ech.setTime(echeance);
	
	Long millDebut = finExo1.getTimeInMillis();
	Long millfin = ech.getTimeInMillis();
	
	
	//la duree de l'exo en cours
	Long diff = millfin - millDebut;
	nbjour=diff/ (1000*60*60*24);
		}
		
		setDureAReporter(nbjour);
		return nbjour;	
	}	
	
	public BigDecimal contratValues(List<Avenant> avenantsList){
		BigDecimal actuCtratValue = BigDecimal.ZERO;
		//BigDecimal actuCtratValue = BigDecimal.ZERO;
		List<Avenant> sortedSlctdAvenList = sortedSlctdAven(avenantsList);
		 setAvenPrec(sortedSlctdAvenList.get(sortedSlctdAvenList.size()-1));
		 setAvenOrig(sortedSlctdAvenList.get(0));
		for(Avenant a : sortedSlctdAvenList){
			int durCons =0;
			
			
			durCons = dureeConso(a.getEffet(), getAvenPrec().getEffet());
			BigDecimal primInit =a.getQuittances().iterator().next().getPrimeTotale();
			BigDecimal primConso = primConso(a.getDuree(), primInit, durCons);
			BigDecimal primRest = primInit.subtract(primConso);
			actuCtratValue = actuCtratValue.add(primRest);	
		}
		
		return actuCtratValue;
		
	}
	
	//Recupérer les avenants d'une même reconduction (Renouvellement) ou affaire nouvelle
	public List<Avenant> sortedSlctdAven(List<Avenant> avenantsList){
		List<Avenant> sortedSlctdAvenList = new ArrayList<Avenant>();
		List<Avenant> reNewAvenList =  new ArrayList<Avenant>();
		//Recup Renew
		for(Avenant a : avenantsList){
			if(a.getMouvement().equalsIgnoreCase("Renouvellement"))
				reNewAvenList.add(a);
		}
		if(reNewAvenList.isEmpty()){
			sortedSlctdAvenList=avenantsList;
		}else{
			Avenant lastRenewAven; 
			triCroissant(reNewAvenList);
			lastRenewAven=reNewAvenList.get(reNewAvenList.size()-1);
			for(Avenant a : avenantsList){
				if(a.getEffet().after(lastRenewAven.getEffet()))
					sortedSlctdAvenList.add(a);
			}  
		}	  
		triCroissant(sortedSlctdAvenList);
		return sortedSlctdAvenList;
	}
	
	public void triCroissant(List<Avenant>Y){
		Collections.sort(Y, new Comparator<Avenant>() {
	        @Override
	        public int compare(Avenant o1, Avenant o2)
	        {
	        	return  compareAvenant(o1, o2);
	        }
	    });
	}
	
	public int dureeConso(Date effetAven, Date actu ){
		long e = actu.getTime() - effetAven.getTime();
		System.out
				.println("...........................Durée consommée en Jour-->"
						+ (e / (1000 * 60 * 60 * 24)));
		 int d = (int) (e / (1000 * 60 * 60 * 24));
		 if(d<0)
			 d=0;
		 return d;
	}
	
	public BigDecimal primConso(int durInit, BigDecimal primInit, int durConso){
		BigDecimal X = primInit.multiply(new BigDecimal(String.valueOf(durConso))).divide(new BigDecimal(String.valueOf(durInit)), 3);
		return X;
	}
	
	public int compareAvenant(Avenant o1, Avenant o2){ 
	    // doit rendre 
	    // -1 si o1 "plus petit que" o2 
	    // 0 si o1 "égal à" o2 
	    // +1 si o1 "plus grand que" o2 
	     
	    long d1=o1.getEffet().getTime(); 
	    long d2=o2.getEffet().getTime(); 
	    int a=0;
	    if(d1<d2) a=(-1); 
	    if (d1==d2)a=(0);
	    if(d1>d2) a=(1);
		return a; 
	  }//compare1 
	
   public void test(){
	   
	  /* calculDEC();
	   calculDAR();  
	   ConvertDureeEnjour();*/
   }		


	public Date getEffet() {
		return effet;
	}


	public void setEffet(Date effet) {
		this.effet = effet;
	}


	public Date getEcheance() {
		calculDureEcheance();
		return echeance;
	}


	public void setEcheance(Date echeance) {
		this.echeance = echeance;
	}

	public double getDuree() {
		return duree;
	}

	public void setDuree(double duree) {
		this.duree = duree;
	}

	public Long getDureExoCours() {
		//calculDEC();
		return dureExoCours;
	}

	public void setDureExoCours(Long dureExoCours) {
		this.dureExoCours = dureExoCours;
	}

	public int getExercice() {
		return exercice;
	}

	public void setExercice(int exercice) {
		this.exercice = exercice;
	}

	public Long getDureAReporter() {
		//calculDAR();
		return dureAReporter;
	}

	public void setDureAReporter(Long dureAReporter) {
		this.dureAReporter = dureAReporter;
	}

	public Long getDureeEnjour() {
	//	ConvertDureeEnjour();
		return dureeEnjour;
	}

	public void setDureeEnjour(Long dureeEnjour) {
		this.dureeEnjour = dureeEnjour;
	}

	public BigDecimal getPrimeTot() {
		return primeTot;
	}

	public void setPrimeTot(BigDecimal primeTot) {
		this.primeTot = primeTot;
	}
	
	
////////////////
	public BigDecimal getPrimeExerEnCours() {
		//#d=Prime Exercice en cour = [(b=Prime Totale)*DEC]/DC
		BigDecimal DEC = new BigDecimal( calculDEC());
		BigDecimal DC = new BigDecimal(CalculDureeContrat());
		this.primeExerEnCours = getPrimeTot().multiply(DEC).divide(DC,3, BigDecimal.ROUND_UP);
		return primeExerEnCours;
	}

	public void setPrimeExerEnCours(BigDecimal primeExerEnCours) {
		this.primeExerEnCours = primeExerEnCours;
	}
	
///////////////
	public BigDecimal getPrimeToReport() {
		//#e=Prime à reporter = [(b=Prime Totale)*DAR]/DC
		BigDecimal DAR = new BigDecimal( calculDAR());
		BigDecimal DC = new BigDecimal(CalculDureeContrat());
		this.primeToReport = getPrimeTot().multiply(DAR).divide(DC,3, BigDecimal.ROUND_UP);
		return primeToReport;
	}

	public void setPrimeToReport(BigDecimal primeToReport) {
		this.primeToReport = primeToReport;
	}
/////////////
	public BigDecimal getPrec() {
		//#e=Prime à reporter = [(b=Prime Totale)*DAR]/DC
		BigDecimal perCent72 = new BigDecimal("0.72");
		this.prec = getPrimeToReport().multiply(perCent72).setScale(3, BigDecimal.ROUND_UP);

		return prec;
	}

	public void setPrec(BigDecimal prec) {
		this.prec = prec;
	}

	public Avenant getAvenPrec() {
		return avenPrec;
	}

	public void setAvenPrec(Avenant avenPrec) {
		this.avenPrec = avenPrec;
	}

	public Avenant getAvenOrig() {
		return avenOrig;
	}

	public void setAvenOrig(Avenant avenOrig) {
		this.avenOrig = avenOrig;
	}

	
	
	
	
	
}
