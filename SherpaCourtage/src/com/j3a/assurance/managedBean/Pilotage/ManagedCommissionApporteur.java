package com.j3a.assurance.managedBean.Pilotage;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.component.chart.bar.BarChart;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.Apporteur;
import com.j3a.assurance.model.ApporteurAdherent;
import com.j3a.assurance.model.ApporteurAliment;
import com.j3a.assurance.model.ApporteurAssure;
import com.j3a.assurance.model.ApporteurCorpsEngin;
import com.j3a.assurance.model.ApporteurGestionConfiee;
import com.j3a.assurance.model.ApporteurHabitation;
import com.j3a.assurance.model.ApporteurNta;
import com.j3a.assurance.model.ApporteurVehicule;
import com.j3a.assurance.model.Risque;
import com.j3a.assurance.objetService.InfoAvenantContrat;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.utilitaires.CommissionApporteurRow;

@Component
@Scope("session")
public class ManagedCommissionApporteur implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Apporteur> listapporteur = new ArrayList<Apporteur>();
	private List<Risque> listRisque=new ArrayList<Risque>();
	private Risque selectRisque;
	@Autowired
	 ObjectService objectService;
	private Date debut;
	private Date fin;
	
	@Autowired
	private InfoAvenantContrat infoAvenantContrat = new InfoAvenantContrat();
	private Apporteur apporteur;
	public List<CommissionApporteurRow> commissionApporteurRows=new ArrayList<CommissionApporteurRow>();
	private SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
	public List<CommissionApporteurRow>filtercommission= new ArrayList<CommissionApporteurRow>();
	private PieChartModel modelCommissionApporteur = new PieChartModel();
	private BarChart chartModel=new BarChart();
	private CartesianChartModel charModele;
	private Double minY,maxY = new Double(0);
	
	
	public void afficherCommission(){
		choixriq();
		
		setDebut(getDebut());
		setFin(getFin());
		//RequestContext.getCurrentInstance().execute("commission.show()");
		//return "/pages/Pilotage/commissionApporteur.xhtml";
				
				}
	
	
	
	public void choixriq(){
		
		 
			
			if(getSelectRisque().getLibelleRisque().equalsIgnoreCase("AUTOMOBILE")){
			verifierModel(getModelCommissionApporteur());
			chargerModel();
			verifierbar(getCharModele());
			createBarModel();
		}else if(getSelectRisque().getLibelleRisque().equalsIgnoreCase("Risque NTA Vol") ||getSelectRisque().getLibelleRisque().equalsIgnoreCase("Risque Informatique")){
			
			verifierModel(getModelCommissionApporteur());
			chargerModelnta();
			//verifierbar(getChartModel());
			//createBarModelNta();
		}
		
		else if(getSelectRisque().getLibelleRisque().equalsIgnoreCase("Sante")){
			verifierModel(getModelCommissionApporteur());
			chargerModelSante();
			
		//	verifierbar(getChartModel());
			//createBarModelSante();
		}
		
		else if(getSelectRisque().getLibelleRisque().equalsIgnoreCase("Gestion confiée")){
			verifierModel(getModelCommissionApporteur());
			chargerModelGC();
			//verifierbar(getChartModel());
			//createBarModelGC();
		}
		
		else if(getSelectRisque().getLibelleRisque().equalsIgnoreCase("Faculté Maritime & Fluvial") ||getSelectRisque().getLibelleRisque().equalsIgnoreCase("Faculté Aérienne")||getSelectRisque().getLibelleRisque().equalsIgnoreCase("Faculté Terrestre")){
			verifierModel(getModelCommissionApporteur());
			chargerModelAliment();
		//	verifierbar(getChartModel());
			//createBarModelAliment();
		}
		
		else if(getSelectRisque().getLibelleRisque().equalsIgnoreCase("Corps Maritime & Fluvial") ||getSelectRisque().getLibelleRisque().equalsIgnoreCase("Corps Aérien")){
			verifierModel(getModelCommissionApporteur());
			chargerModelEngin();
			
			//verifierbar(getChartModel());
			//createBarModelEngin();
		}
		
		else if(getSelectRisque().getLibelleRisque().equalsIgnoreCase("MultiRisques Habitation")){
			verifierModel(getModelCommissionApporteur());
			chargerModelHabitation();
			
			//verifierbar(getChartModel());
			//createBarModelHabitation();
		}
		
		else if(getSelectRisque().getLibelleRisque().equalsIgnoreCase("Individuelle Accidents")){
			verifierModel(getModelCommissionApporteur());
			chargerModelIA();
			
			//verifierbar(getChartModel());
			//createBarModelIa();
		}
		}
	
	
	public void choix(Apporteur apporteur,String debut,String fin){
		if(getSelectRisque().getLibelleRisque().equalsIgnoreCase("AUTOMOBILE")){
			
			listCommissionApporteurRow(apporteur, debut, fin);
		}else if(getSelectRisque().getLibelleRisque().equalsIgnoreCase("Risque NTA Vol") ||getSelectRisque().getLibelleRisque().equalsIgnoreCase("Risque Informatique")){
			listCommissionApporteurNta(apporteur, debut, fin);
		}
		
		else if(getSelectRisque().getLibelleRisque().equalsIgnoreCase("Sante")){
			listCommissionApporteurAdherant(apporteur, debut, fin);
		}
		
		else if(getSelectRisque().getLibelleRisque().equalsIgnoreCase("Gestion confiée")){
			listCommissionApporteurGC(apporteur, debut, fin);
		}
		
		else if(getSelectRisque().getLibelleRisque().equalsIgnoreCase("Faculté Maritime & Fluvial") ||getSelectRisque().getLibelleRisque().equalsIgnoreCase("Faculté Aérienne")||getSelectRisque().getLibelleRisque().equalsIgnoreCase("Faculté Terrestre")){
			listCommissionApporteurAliment(apporteur, debut, fin);
		}
		
		else if(getSelectRisque().getLibelleRisque().equalsIgnoreCase("Corps Maritime & Fluvial") ||getSelectRisque().getLibelleRisque().equalsIgnoreCase("Corps Aérien")){
			listCommissionApporteurCorpsEngin(apporteur, debut, fin);
		}
		
		else if(getSelectRisque().getLibelleRisque().equalsIgnoreCase("MultiRisques Habitation")){
			listCommissionApporteurHabitation(apporteur, debut, fin);
		}
		
		else if(getSelectRisque().getLibelleRisque().equalsIgnoreCase("Individuelle Accidents")){
			listCommissionApporteurAssure(apporteur, debut, fin);
		}
	}
			
		
	
	
	public void onRowSelectApporteur(SelectEvent event) {
		
			
			try {
				choix(apporteur,formater.format(getDebut()) ,formater.format(getFin()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		
		}
	
    public void listCommissionApporteurAdherant (Apporteur apporteur,String debut,String fin){
		System.out.println("aporthdjdjdjkdssdsd"+apporteur.getCodeApporteur());

		commissionApporteurRows=new ArrayList<CommissionApporteurRow>();
			
			List<ApporteurAdherent> apporteurAdherents=new ArrayList<ApporteurAdherent>();
			apporteurAdherents=getInfoAvenantContrat().infoapporteurAdherent(apporteur.getCodeApporteur(), debut, fin);
			for(ApporteurAdherent apporteurAdherent:apporteurAdherents){

				if(apporteur.getCodeApporteur().equalsIgnoreCase(apporteurAdherent.getId().getCodeApporteur())){
		 CommissionApporteurRow commissionApporteurRow=new CommissionApporteurRow();
		 commissionApporteurRow.setMontantCommission(apporteurAdherent.getMontantComApporteurAdherent());
		 commissionApporteurRow.setDateAssure(apporteurAdherent.getDateApporteurAdherent());
		 commissionApporteurRow.setElementAssure(apporteurAdherent.getId().getCodeAdherent());
		 commissionApporteurRow.setTauxApporteur(apporteurAdherent.getTauxComApporteurAdherent());
		 commissionApporteurRows.add(commissionApporteurRow);
		
			}
		
			}
	}
	
    
    public void listCommissionApporteurAssure (Apporteur apporteur,String debut,String fin){
		System.out.println("aporthdjdjdjkdssdsd"+apporteur.getCodeApporteur());

		commissionApporteurRows=new ArrayList<CommissionApporteurRow>();
			
			List<ApporteurAssure> apporteurAssures=new ArrayList<ApporteurAssure>();
			apporteurAssures=getInfoAvenantContrat().infoapporteurAssure(apporteur.getCodeApporteur(), debut, fin);
			for(ApporteurAssure apporteurAssure:apporteurAssures){

			if(apporteur.getCodeApporteur().equalsIgnoreCase(apporteurAssure.getId().getCodeApporteur())){
		 CommissionApporteurRow commissionApporteurRow=new CommissionApporteurRow();
		 commissionApporteurRow.setMontantCommission(apporteurAssure.getMontantComApporteurAssure());
		 commissionApporteurRow.setDateAssure(apporteurAssure.getDateApporteurAssure());
		 commissionApporteurRow.setElementAssure(apporteurAssure.getId().getNumIdentification());
		 commissionApporteurRow.setTauxApporteur(apporteurAssure.getTauxComApporteurAssure());
		 commissionApporteurRows.add(commissionApporteurRow);
		
			
		}
			}
	}
    
    
    public void listCommissionApporteurCorpsEngin (Apporteur apporteur,String debut,String fin){
		

		commissionApporteurRows=new ArrayList<CommissionApporteurRow>();
			
			List<ApporteurCorpsEngin> apporteurCorpsEngins=new ArrayList<ApporteurCorpsEngin>();
			apporteurCorpsEngins=getInfoAvenantContrat().infoapporteurCorpsEngin(apporteur.getCodeApporteur(), debut, fin);
			for(ApporteurCorpsEngin apporteurCorpsEngin:apporteurCorpsEngins){

				if(apporteur.getCodeApporteur().equalsIgnoreCase(apporteurCorpsEngin.getId().getCodeApporteur())){
		 CommissionApporteurRow commissionApporteurRow=new CommissionApporteurRow();
		 commissionApporteurRow.setMontantCommission(apporteurCorpsEngin.getMontantComApporteurComEngin());
		 commissionApporteurRow.setDateAssure(apporteurCorpsEngin.getDateApporteurCorpsEngin());
		 commissionApporteurRow.setElementAssure(apporteurCorpsEngin.getId().getCodeEngin());
		 commissionApporteurRow.setTauxApporteur(apporteurCorpsEngin.getTauxComApporteurCorpsEngin());
		 commissionApporteurRows.add(commissionApporteurRow);
		
			
		}
			}
	}
	
    
    
    public void listCommissionApporteurGC (Apporteur apporteur,String debut,String fin){
		

  		commissionApporteurRows=new ArrayList<CommissionApporteurRow>();
  			
  			List<ApporteurGestionConfiee> apporteurGestionConfiees=new ArrayList<ApporteurGestionConfiee>();
  			apporteurGestionConfiees=getInfoAvenantContrat().infoapporteurGC(apporteur.getCodeApporteur(), debut, fin);
  			for(ApporteurGestionConfiee apporteurGestionConfiee:apporteurGestionConfiees){

  				if(apporteur.getCodeApporteur().equalsIgnoreCase(apporteurGestionConfiee.getId().getCodeApporteur())){
  		 CommissionApporteurRow commissionApporteurRow=new CommissionApporteurRow();
  		 commissionApporteurRow.setMontantCommission(apporteurGestionConfiee.getMontantComApporteurGc());
  		 commissionApporteurRow.setDateAssure(apporteurGestionConfiee.getDateApporteurGc());
  		 commissionApporteurRow.setElementAssure(apporteurGestionConfiee.getId().getCodeGestionConfie());
  		 commissionApporteurRow.setTauxApporteur(apporteurGestionConfiee.getTauxComApporteurGc());
  		 commissionApporteurRows.add(commissionApporteurRow);
  		
  			
  		}
  			}
  	}
    
    
     public void listCommissionApporteurHabitation (Apporteur apporteur,String debut,String fin){
  		commissionApporteurRows=new ArrayList<CommissionApporteurRow>();
  			
  			List<ApporteurHabitation> apporteurHabitations=new ArrayList<ApporteurHabitation>();
  			apporteurHabitations=getInfoAvenantContrat().infoapporteurHabitations(apporteur.getCodeApporteur(), debut, fin);
  			for(ApporteurHabitation apporteurHabitation:apporteurHabitations){

  				if(apporteur.getCodeApporteur().equalsIgnoreCase(apporteurHabitation.getId().getCodeApporteur())){
  		 CommissionApporteurRow commissionApporteurRow=new CommissionApporteurRow();
  		 commissionApporteurRow.setMontantCommission(apporteurHabitation.getMontantComApporteurHabitation());
  		 commissionApporteurRow.setDateAssure(apporteurHabitation.getDateApporteurHabitation());
  		 commissionApporteurRow.setElementAssure(apporteurHabitation.getId().getCodeHabitation());
  		 commissionApporteurRow.setTauxApporteur(apporteurHabitation.getTauxComApporteurHabitation());
  		 commissionApporteurRows.add(commissionApporteurRow);
  		
  			
  		}
  			}
  	}
     
     public void listCommissionApporteurNta (Apporteur apporteur,String debut,String fin){
   		commissionApporteurRows=new ArrayList<CommissionApporteurRow>();
   			
   			List<ApporteurNta> apporteurNtas=new ArrayList<ApporteurNta>();
   			apporteurNtas=getInfoAvenantContrat().infoapporteurNtas(apporteur.getCodeApporteur(), debut, fin);
   			for(ApporteurNta apporteurNta:apporteurNtas){

   				if(apporteur.getCodeApporteur().equalsIgnoreCase(apporteurNta.getId().getCodeApporteur())){
   		 CommissionApporteurRow commissionApporteurRow=new CommissionApporteurRow();
   		 commissionApporteurRow.setMontantCommission(apporteurNta.getMontantComApporteurNta());
   		 commissionApporteurRow.setDateAssure(apporteurNta.getDateApporteurNta());
   		 commissionApporteurRow.setElementAssure(apporteurNta.getId().getCodeRisquenta());
   		 commissionApporteurRow.setTauxApporteur(apporteurNta.getTauxComApporteurNta());
   		 commissionApporteurRows.add(commissionApporteurRow);
   		
   			
   		}
   			}
   	}
  	
    
    public void listCommissionApporteurAliment (Apporteur apporteur,String debut,String fin){
		System.out.println("aporthdjdjdjkdssdsd"+apporteur.getCodeApporteur());

		commissionApporteurRows=new ArrayList<CommissionApporteurRow>();
			
			List<ApporteurAliment> apporteurAliments=new ArrayList<ApporteurAliment>();
			apporteurAliments=getInfoAvenantContrat().infoapporteurAliment(apporteur.getCodeApporteur(), debut, fin);
			for(ApporteurAliment apporteurAliment:apporteurAliments){

			
			if(apporteur.getCodeApporteur().equalsIgnoreCase(apporteurAliment.getId().getCodeApporteur())){
		 CommissionApporteurRow commissionApporteurRow=new CommissionApporteurRow();
		 commissionApporteurRow.setMontantCommission(apporteurAliment.getMontantComApporteurAliment());
		 commissionApporteurRow.setDateAssure(apporteurAliment.getDateApporteurAliment());
		 commissionApporteurRow.setElementAssure(apporteurAliment.getId().getCodeAliment());
		 commissionApporteurRow.setTauxApporteur(apporteurAliment.getTauxComApporteurAliment());
		 commissionApporteurRows.add(commissionApporteurRow);
		
			
		}
			}
	}

	
	public void listCommissionApporteurRow (Apporteur apporteur,String debut,String fin){
		
		commissionApporteurRows=new ArrayList<CommissionApporteurRow>();
			System.out.println("aporthdjdjdjkdssdsd"+apporteur.getCodeApporteur());
			
			List<ApporteurVehicule> apporteurVehicules=new ArrayList<ApporteurVehicule>();
			apporteurVehicules=getInfoAvenantContrat().infoapporteuVehicule(apporteur.getCodeApporteur(), debut, fin);
			for(ApporteurVehicule apporteurVehicule:apporteurVehicules){
			if(apporteur.getCodeApporteur().equalsIgnoreCase(apporteurVehicule.getId().getCodeApporteur())){
		 CommissionApporteurRow commissionApporteurRow=new CommissionApporteurRow();
		 commissionApporteurRow.setMontantCommission(apporteurVehicule.getMontantComApporteur());
		 commissionApporteurRow.setDateAssure(apporteurVehicule.getDateApporteurVehicule());
		 commissionApporteurRow.setElementAssure(apporteurVehicule.getId().getCodeVehicule());
		 commissionApporteurRow.setTauxApporteur(apporteurVehicule.getTauxComApporteur());
		 commissionApporteurRows.add(commissionApporteurRow);
		
			
		}
			}
		//return commissionApporteurRows;
	}

      public List<Apporteur>apporteurList(){
		listapporteur=new ArrayList<Apporteur>();
		List apporteur=getObjectService().getObjects("Apporteur");
		for(Iterator it=apporteur.iterator();it.hasNext();){
			Apporteur apporteur1=(Apporteur) it.next();
			listapporteur.add(apporteur1);	
		}
		return listapporteur;
		
	}
	
	public List<Risque>RisqueList(){
		
		listRisque=new ArrayList<Risque>();
		List risques=getObjectService().getObjects("Risque");
		for(Iterator it=risques.iterator();it.hasNext();){
			Risque risque=(Risque) it.next();
			listRisque.add(risque);	
		}
		return listRisque;
		
	}
	
	
	 public Number commissionHabitation(Apporteur apporteur, String debut,String fin){
		    BigDecimal X = BigDecimal.ZERO;
		    List<ApporteurHabitation> apporteurHabitations=new ArrayList<ApporteurHabitation>();
  			apporteurHabitations=getInfoAvenantContrat().infoapporteurHabitations(apporteur.getCodeApporteur(), debut, fin);
  			for(ApporteurHabitation apporteurHabitation:apporteurHabitations){

  				if(apporteur.getCodeApporteur().equalsIgnoreCase(apporteurHabitation.getId().getCodeApporteur())){
					X = X.add(apporteurHabitation.getMontantComApporteurHabitation());	
			}
				};
			return X;
			
		}
	 
	 public Number commissionIa(Apporteur apporteur, String debut,String fin){
		    BigDecimal X = BigDecimal.ZERO;
		    List<ApporteurAssure> apporteurAssures=new ArrayList<ApporteurAssure>();
			apporteurAssures=getInfoAvenantContrat().infoapporteurAssure(apporteur.getCodeApporteur(), debut, fin);
			for(ApporteurAssure apporteurAssure:apporteurAssures){
			if(apporteur.getCodeApporteur().equalsIgnoreCase(apporteurAssure.getId().getCodeApporteur())){
					X = X.add(apporteurAssure.getMontantComApporteurAssure());	
			}
				};
			return X;
			
		}  
	 
	 
	 public Number commissionGC(Apporteur apporteur, String debut,String fin){
		    BigDecimal X = BigDecimal.ZERO;
		    List<ApporteurGestionConfiee> apporteurGestionConfiees=new ArrayList<ApporteurGestionConfiee>();
			apporteurGestionConfiees=getInfoAvenantContrat().infoapporteurGC(apporteur.getCodeApporteur(), debut, fin);
			for(ApporteurGestionConfiee apporteurGestionConfiee:apporteurGestionConfiees){

				if(apporteur.getCodeApporteur().equalsIgnoreCase(apporteurGestionConfiee.getId().getCodeApporteur())){
					X = X.add(apporteurGestionConfiee.getMontantComApporteurGc());	
			}
				};
			return X;
			
		}
	
       public Number commissionEngin(Apporteur apporteur, String debut,String fin){
	    BigDecimal X = BigDecimal.ZERO;
	    List<ApporteurCorpsEngin> apporteurCorpsEngins=new ArrayList<ApporteurCorpsEngin>();
		apporteurCorpsEngins=getInfoAvenantContrat().infoapporteurCorpsEngin(apporteur.getCodeApporteur(), debut, fin);
		for(ApporteurCorpsEngin apporteurCorpsEngin:apporteurCorpsEngins){

			if(apporteur.getCodeApporteur().equalsIgnoreCase(apporteurCorpsEngin.getId().getCodeApporteur())){
				X = X.add(apporteurCorpsEngin.getMontantComApporteurComEngin());
			
		}
			};
		return X;
		
	}
       
       
       public Number commission(Apporteur apporteur, String debut,String fin){
   	    BigDecimal X = BigDecimal.ZERO;
   		
   	       
   			List<ApporteurVehicule> apporteurVehicules=new ArrayList<ApporteurVehicule>();
   			apporteurVehicules=getInfoAvenantContrat().infoapporteuVehicule(apporteur.getCodeApporteur(), debut, fin);
   			for(ApporteurVehicule apporteurVehicule:apporteurVehicules){
   			if(apporteur.getCodeApporteur().equalsIgnoreCase(apporteurVehicule.getId().getCodeApporteur())){
   				X = X.add(apporteurVehicule.getMontantComApporteur());
   			
   		}
   			};
   		return X;
   		
   	}
       
       public Number commissionSante(Apporteur apporteur,String debut,String fin){
      	    BigDecimal X = BigDecimal.ZERO;
      		
      	       
      	    	 List<ApporteurAssure> apporteurAssures=new ArrayList<ApporteurAssure>();
    	 			apporteurAssures=getInfoAvenantContrat().infoapporteurAssure(apporteur.getCodeApporteur(), debut, fin);
    	 			for(ApporteurAssure apporteurAssure:apporteurAssures){

    	 			if(apporteur.getCodeApporteur().equalsIgnoreCase(apporteurAssure.getId().getCodeApporteur())){
   	   					X = X.add(apporteurAssure.getMontantComApporteurAssure());
   	   				}
      		
      			};
      		return X;
      		
      	}
       public Number commissionAliment(Apporteur apporteur,String debut,String fin){
      	    BigDecimal X = BigDecimal.ZERO;
      	  List<ApporteurAliment> apporteurAliments=new ArrayList<ApporteurAliment>();
			apporteurAliments=getInfoAvenantContrat().infoapporteurAliment(apporteur.getCodeApporteur(), debut, fin);
			for(ApporteurAliment apporteurAliment:apporteurAliments){

			
			if(apporteur.getCodeApporteur().equalsIgnoreCase(apporteurAliment.getId().getCodeApporteur())){
					X = X.add(apporteurAliment.getMontantComApporteurAliment());
   	   				}
      		
      			};
      		return X;
      		
      	}
       
       public Number commissionNta(Apporteur apporteur,String debut,String fin){
   	    BigDecimal X = BigDecimal.ZERO;
   		
   	      
   	    	List<ApporteurNta> apporteurNtas=new ArrayList<ApporteurNta>();
	   			apporteurNtas=getInfoAvenantContrat().infoapporteurNtas(apporteur.getCodeApporteur(), debut, fin);
	   			for(ApporteurNta apporteurNta:apporteurNtas){
	   				if(apporteur.getCodeApporteur().equalsIgnoreCase(apporteurNta.getId().getCodeApporteur())){
	   					X = X.add(apporteurNta.getMontantComApporteurNta());
	   				}
   		
   			};
   		return X;
   		
   	}
       
       public void chargerModelAliment() {
     		List<Apporteur> A = new ArrayList<Apporteur>();
     		if (getListapporteur().isEmpty()) {
     			
     		} else {
     			A= listapporteur;
     		}
     		setModelCommissionApporteur(new PieChartModel());
     		
     		for (Apporteur apporteur : A) {
     			getModelCommissionApporteur().set(apporteur.getLibelleApporteur(), commissionAliment(apporteur,formater.format(getDebut()) ,formater.format(getFin())));
     		}
     	}
       
       public void chargerModelEngin() {
    		List<Apporteur> A = new ArrayList<Apporteur>();
    		if (getListapporteur().isEmpty()) {
    			
    		} else {
    			A= listapporteur;
    		}
    		setModelCommissionApporteur(new PieChartModel());
    		
    		for (Apporteur apporteur : A) {
    			getModelCommissionApporteur().set(apporteur.getLibelleApporteur(), commissionEngin(apporteur,formater.format(getDebut()) ,formater.format(getFin())));
    		}
    	}
       
       
       
       public void chargerModelIA() {
    		List<Apporteur> A = new ArrayList<Apporteur>();
    		if (getListapporteur().isEmpty()) {
    			
    		} else {
    			A= listapporteur;
    		}
    		setModelCommissionApporteur(new PieChartModel());
    		
    		for (Apporteur apporteur : A) {
    			getModelCommissionApporteur().set(apporteur.getLibelleApporteur(), commissionIa(apporteur,formater.format(getDebut()) ,formater.format(getFin())));
    		}
    	}
      
      public void chargerModelHabitation() {
   		List<Apporteur> A = new ArrayList<Apporteur>();
   		if (getListapporteur().isEmpty()) {
   			
   		} else {
   			A= listapporteur;
   		}
   		setModelCommissionApporteur(new PieChartModel());
   		
   		
   		for (Apporteur apporteur : A) {
   			getModelCommissionApporteur().set(apporteur.getLibelleApporteur(), commissionHabitation(apporteur,formater.format(getDebut()) ,formater.format(getFin())));
   		}
   	}
      
       public void chargerModelGC() {
      		List<Apporteur> A = new ArrayList<Apporteur>();
      		if (getListapporteur().isEmpty()) {
      			
      		} else {
      			A= listapporteur;
      		}
      		setModelCommissionApporteur(new PieChartModel());
      		
      		for (Apporteur apporteur : A) {
      			getModelCommissionApporteur().set(apporteur.getLibelleApporteur(), commissionGC(apporteur,formater.format(getDebut()) ,formater.format(getFin())));
      		}
      	}

       public void chargerModelnta() {
   		List<Apporteur> A = new ArrayList<Apporteur>();
   		if (getListapporteur().isEmpty()) {
   			
   		} else {
   			A= listapporteur;
   		}
   		setModelCommissionApporteur(new PieChartModel());
   		
   		for (Apporteur apporteur : A) {
   			getModelCommissionApporteur().set(apporteur.getLibelleApporteur(), commissionNta(apporteur,formater.format(getDebut()) ,formater.format(getFin())));
   		}
   	}
       
       public void chargerModelSante() {
      		List<Apporteur> A = new ArrayList<Apporteur>();
      		if (getListapporteur().isEmpty()) {
      			
      		} else {
      			A= listapporteur;
      		}
      		setModelCommissionApporteur(new PieChartModel());
      		
      		for (Apporteur apporteur : A) {
      			getModelCommissionApporteur().set(apporteur.getLibelleApporteur(), commissionSante(apporteur,formater.format(getDebut()) ,formater.format(getFin())));
      		}
      	}
       
      
       
       public void createBarModelGC() {
    	   chartModel = initBarModelGC();
            
    	   chartModel.setTitle("CLASSEMENT DES APPORTEURS PAR COMMISSION");
    	   chartModel.setLegendPosition("e");
    	  // chartModel.setShowPointLabels(true);
    	   
          // Axis xAxis = chartModel.getAxis(AxisType.X);
          // xAxis.setLabel("Apporteur");
          // Axis yAxis = chartModel.getAxis(AxisType.Y);
          // yAxis.setLabel("Montant Commission");
          // yAxis.setMin(0);
          // yAxis.setMax(200);
       }
       
       public BarChart initBarModelNta() {
           BarChart model = new BarChart();
    
           ChartSeries app  = new ChartSeries();
           List<Apporteur> A = new ArrayList<Apporteur>();
   		if (getListapporteur().isEmpty()) {
   			
   		} else {
   			A= listapporteur;
   		}
   		
   		for (Apporteur apporteur : A) {
   			app=new ChartSeries();
   			app.setLabel(apporteur.getLibelleApporteur());
			app.set(apporteur.getLibelleApporteur(), commissionNta(apporteur,formater.format(getDebut()) ,formater.format(getFin())));
			model.setValue(app);
   		}
           return model;
       }
       
       public void createBarModel() {
  		 List<BigDecimal> toSort= new ArrayList<BigDecimal>();
           ChartSeries app  = new ChartSeries();
           List<Apporteur> A = new ArrayList<Apporteur>();
   		if (getListapporteur().isEmpty()) {
   			
   		} else {
   			A= listapporteur;
   		}
   		setCharModele(new CartesianChartModel());
   		for (Apporteur apporteur : A) {
   			app.setLabel(apporteur.getLibelleApporteur());
			app.set(apporteur.getLibelleApporteur(), commission(apporteur,formater.format(getDebut()) ,formater.format(getFin())));
			app.getRenderer();
			charModele.addSeries(app);
			System.out.println("eeeeeeeeeeeeeeeeeeeccccccceeeeee"+charModele.getSeries().size());
			System.out.println("eeeeeeeeeeeeeeeeeeeeeeeee"+app.getData());
   		}
   	
       }
       
       public void createBarModelSante() {
    	   chartModel = initBarModelSante();
            
    	   chartModel.setTitle("CLASSEMENT DES APPORTEURS PAR COMMISSION");
    	   chartModel.setLegendPosition("e");
    	   
    	   
          // Axis xAxis = chartModel.getAxis(AxisType.X);
          // xAxis.setLabel("Apporteur");
          // Axis yAxis = chartModel.getAxis(AxisType.Y);
          // yAxis.setLabel("Montant Commission");
          // yAxis.setMin(0);
          // yAxis.setMax(200);
       }
       
       
       public void createBarModelNta() {
    	   chartModel = initBarModelNta();
            
    	   chartModel.setTitle("CLASSEMENT DES APPORTEURS PAR COMMISSION");
    	   chartModel.setLegendPosition("e");
    	  
    	   
          
       }
       
       
       public void createBarModelAliment() {
    	   chartModel = initBarModelAliment();
            
    	   chartModel.setTitle("CLASSEMENT DES APPORTEURS PAR COMMISSION");
    	   chartModel.setLegendPosition("e");
    	  
    	   
         
       }
       
       public BarChart initBarModelGC() {
           BarChart model = new BarChart();
    
           ChartSeries app  = new ChartSeries();
           List<Apporteur> A = new ArrayList<Apporteur>();
   		if (getListapporteur().isEmpty()) {
   			
   		} else {
   			A= listapporteur;
   		}
   		
   		for (Apporteur apporteur : A) {
   			app=new ChartSeries();
   			app.setLabel(apporteur.getLibelleApporteur());
			app.set(apporteur.getLibelleApporteur(), commissionGC(apporteur,formater.format(getDebut()) ,formater.format(getFin())));
			model.setValue(app);
   		}
           return model;
       }
       
       
       
       public BarChart initBarModelAliment() {
           BarChart model = new BarChart();
    
           ChartSeries app  = new ChartSeries();
           List<Apporteur> A = new ArrayList<Apporteur>();
   		if (getListapporteur().isEmpty()) {
   			
   		} else {
   			A= listapporteur;
   		}
   		
   		for (Apporteur apporteur : A) {
   			app=new ChartSeries();
   			app.setLabel(apporteur.getLibelleApporteur());
			app.set(apporteur.getLibelleApporteur(), commissionAliment(apporteur,formater.format(getDebut()) ,formater.format(getFin())));
			model.setValue(app);
   		}
           return model;
       }
       
       public BarChart initBarModelSante() {
           BarChart model = new BarChart();
    
           ChartSeries app  = new ChartSeries();
           List<Apporteur> A = new ArrayList<Apporteur>();
   		if (getListapporteur().isEmpty()) {
   			
   		} else {
   			A= listapporteur;
   		}
   		
   		for (Apporteur apporteur : A) {
   			app=new ChartSeries();
   			app.setLabel(apporteur.getLibelleApporteur());
			app.set(apporteur.getLibelleApporteur(), commissionSante(apporteur,formater.format(getDebut()) ,formater.format(getFin())));
			model.setValue(app);
   		}
           return model;
       }
	
	public void chargerModel() {
		List<Apporteur> A = new ArrayList<Apporteur>();
		if (getListapporteur().isEmpty()) {
			
		} else {
			A= listapporteur;
		}
		setModelCommissionApporteur(new PieChartModel());
		for (Apporteur apporteur : A) {
			getModelCommissionApporteur().set(apporteur.getLibelleApporteur(), commission(apporteur,formater.format(getDebut()) ,formater.format(getFin())));
		}
	}
	public void verifierModel(PieChartModel model) {
		if (model.getData().isEmpty())
			model.set("Aucune Donnée.", 0);
	}
	
	public void verifierbar(CartesianChartModel model) {
		ChartSeries chartSeries=new ChartSeries();
		if (chartSeries.getData().isEmpty())
			chartSeries.set("Aucune Donnée.", 0);
		model.addSeries(chartSeries);
		
	}
	
	
	
	public void createBarModelEngin() {
  	   chartModel = initBarModelEngin();
          
  	   chartModel.setTitle("CLASSEMENT DES APPORTEURS PAR COMMISSION");
  	   chartModel.setLegendPosition("e");
  	  // chartModel.setShowPointLabels(true);
  	   
       //  Axis xAxis = chartModel.getAxis(AxisType.X);
        // xAxis.setLabel("Apporteur");
        // Axis yAxis = chartModel.getAxis(AxisType.Y);
       //  yAxis.setLabel("Montant Commission");
        // yAxis.setMin(0);
        // yAxis.setMax(200);
     }
     
	public BarChart initBarModelEngin() {
         BarChart model = new BarChart();
  
         ChartSeries app  = new ChartSeries();
         List<Apporteur> A = new ArrayList<Apporteur>();
 		if (getListapporteur().isEmpty()) {
 			
 		} else {
 			A= listapporteur;
 		}
 		
 		for (Apporteur apporteur : A) {
 			app=new ChartSeries();
 			app.setLabel(apporteur.getLibelleApporteur());
			app.set(apporteur.getLibelleApporteur(), commissionEngin(apporteur,formater.format(getDebut()) ,formater.format(getFin())));
			model.setValue(app);
 		}
         return model;
     }
	
	public void callGraphStat(){
		System.out.println("INSIDE callGraphStat////////////////////////////////////////");
		Map<String,Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", true);
		options.put("contentHeight", 900);
		options.put("contentWidth", 1300);
		
		RequestContext.getCurrentInstance().openDialog ("commissionApporteur", options, null);
		
		//RequestContext.getCurrentInstance().execute("PF('Alert').show();");
		System.out.println("AFTER callGraphStat////////////////////////////////////////");
	}
	
	
	
	public void createBarModelHabitation() {
  	   chartModel = initBarModelHabitation();
          
  	   chartModel.setTitle("CLASSEMENT DES APPORTEURS PAR COMMISSION");
  	   chartModel.setLegendPosition("e");
  	   //chartModel.setShowPointLabels(true);
  	   
     }
     
     public BarChart initBarModelHabitation() {
         BarChart model = new BarChart();
  
         ChartSeries app  = new ChartSeries();
         List<Apporteur> A = new ArrayList<Apporteur>();
 		if (getListapporteur().isEmpty()) {
 			
 		} else {
 			A= listapporteur;
 		}
 		
 		for (Apporteur apporteur : A) {
 			app=new ChartSeries();
 			app.setLabel(apporteur.getLibelleApporteur());
			app.set(apporteur.getLibelleApporteur(), commissionHabitation(apporteur,formater.format(getDebut()) ,formater.format(getFin())));
			model.setValue(app);
 		}
         return model;
     }
	
	
	
     public void createBarModelIa() {
  	   chartModel = initBarModelIa();
          
  	   chartModel.setTitle("CLASSEMENT DES APPORTEURS PAR COMMISSION");
  	   chartModel.setLegendPosition("e");
  	  // chartModel.setShowPointLabels(true);
  	   
         
     }
     
     public BarChart initBarModelIa() {
         BarChart model = new BarChart();
  
         ChartSeries app  = new ChartSeries();
         List<Apporteur> A = new ArrayList<Apporteur>();
 		if (getListapporteur().isEmpty()) {
 			
 		} else {
 			A= listapporteur;
 		}
 		
 		for (Apporteur apporteur : A) {
 			app=new ChartSeries();
 			app.setLabel(apporteur.getLibelleApporteur());
			app.set(apporteur.getLibelleApporteur(), commissionIa(apporteur,formater.format(getDebut()) ,formater.format(getFin())));
			model.setValue(app);
 		}
         return model;
     }
	
	
	public Risque getSelectRisque() {
		
		return selectRisque;
	}
	public void setSelectRisque(Risque selectRisque) {
		this.selectRisque = selectRisque;
	}
	public List<Risque> getListRisque() {
		RisqueList();
		return listRisque;
	}
	public void setListRisque(List<Risque> listRisque) {
		this.listRisque = listRisque;
	}

















	public ObjectService getObjectService() {
		return objectService;
	}

















	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}

















	public Date getDebut() {
		return debut;
	}

















	public List<CommissionApporteurRow> getFiltercommission() {
		return filtercommission;
	}




	public void setFiltercommission(List<CommissionApporteurRow> filtercommission) {
		this.filtercommission = filtercommission;
	}




	public List<CommissionApporteurRow> getCommissionApporteurRows() {
		return commissionApporteurRows;
	}


	public void setCommissionApporteurRows(
			List<CommissionApporteurRow> commissionApporteurRows) {
		this.commissionApporteurRows = commissionApporteurRows;
	}


	public void setDebut(Date debut) {
		this.debut = debut;
	}

















	public Date getFin() {
		return fin;
	}

















	public InfoAvenantContrat getInfoAvenantContrat() {
		return infoAvenantContrat;
	}



	public void setInfoAvenantContrat(InfoAvenantContrat infoAvenantContrat) {
		this.infoAvenantContrat = infoAvenantContrat;
	}



	public void setFin(Date fin) {
		this.fin = fin;
	}



















	public List<Apporteur> getListapporteur() {
		apporteurList();
		return listapporteur;
	}



	public void setListapporteur(List<Apporteur> listapporteur) {
		this.listapporteur = listapporteur;
	}


	




	public Apporteur getApporteur() {
		return apporteur;
	}


	public void setApporteur(Apporteur apporteur) {
		this.apporteur = apporteur;
	}




	public SimpleDateFormat getFormater() {
		return formater;
	}




	public void setFormater(SimpleDateFormat formater) {
		this.formater = formater;
	}



	public PieChartModel getModelCommissionApporteur() {
		if(modelCommissionApporteur==null){
			modelCommissionApporteur = new PieChartModel();
			  }
		return modelCommissionApporteur;
	}



	



	public void setModelCommissionApporteur(PieChartModel modelCommissionApporteur) {
		this.modelCommissionApporteur = modelCommissionApporteur;
	}



	public BarChart getChartModel() {
		if(chartModel==null){
			chartModel = new BarChart();
			  }
		return chartModel;
	}



	public void setChartModel(BarChart chartModel) {
		this.chartModel = chartModel;
	}



	public CartesianChartModel getCharModele() {
		if(charModele==null){
			charModele = new CartesianChartModel();
			  }
		return charModele;
	}



	public void setCharModele(CartesianChartModel charModele) {
		this.charModele = charModele;
	}



	public Double getMinY() {
		return minY;
	}



	public void setMinY(Double minY) {
		this.minY = minY;
	}



	public Double getMaxY() {
		return maxY;
	}



	public void setMaxY(Double maxY) {
		this.maxY = maxY;
	}
}
