package com.j3a.assurance.managedBean.Pilotage;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.j3a.assurance.converter.PtdeVenteCvtr;
import com.j3a.assurance.model.Apporteur;
import com.j3a.assurance.model.Avenant;
import com.j3a.assurance.model.Contrat;
import com.j3a.assurance.model.PointVente;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.utilitaires.AvenantByPointVenteRow;

@Component
@Scope("session")
@Transactional
public class ManagedCApporteur {
    
	@Autowired
	PtdeVenteCvtr ptdeVenteCvtr;
	@Autowired
	private SessionFactory sessionFactory;
	private List<Apporteur> apporteurs=new ArrayList<Apporteur>();
	private List<AvenantByPointVenteRow> listAvenByPtvte;
	private final String Requete ="SELECT SUM( Q.PRIME_TOTALE) as chiffreAff  FROM avenant A JOIN contrat C ON C.NUM_POLICE = A.NUM_POLICE JOIN point_vente P ON P.CODE_POINT_VENTE = C.CODE_POINT_VENTE JOIN apporteur ap ON ap.CODE_APPORTEUR = C.CODE_APPORTEUR JOIN quittance Q ON Q.NUM_AVENANT = A.NUM_AVENANT WHERE";
	private Date dateDeb, dateFin;
	private Apporteur apporteur;
	@Autowired
	 ObjectService objectService;
	private SimpleDateFormat formate= new SimpleDateFormat("yyyy-MM-dd");
	public List<PointVente> pointVenteList=new ArrayList<PointVente>();
    private PointVente pointVente;
	public void listAporteurPV(){	
	}
	
	
	
	public void listeCApporteur(PointVente pointVente, List<Apporteur>appList) {
		
		
		for(Apporteur apporteur:apporteurs){
			for(Contrat contrat:apporteur.getContrats()){
     		for (Avenant aV : contrat.getAvenants()) {
     			if(aV.equals(appList.iterator().next())){
			if(aV.getMouvement().equalsIgnoreCase("AFFAIRE NOUVELLE")|| aV.getMouvement().equalsIgnoreCase("Renouvellement")){
				AvenantByPointVenteRow ap = new AvenantByPointVenteRow();
			ap.setAvenant(aV);
			ap.setContrat(aV.getContrat());
			ap.setApporteur(aV.getContrat().getApporteur());
			if (!aV.getQuittances().isEmpty()) {
				ap.setQuittance(aV.getQuittances().iterator().next());
			}
			listAvenByPtvte.add(ap);
		}
		}
	}}
		}
	}
	
	public void valider(){
		System.out.println("ffffffffffffffffffffff"+getPointVente());
		exeQuery(getPointVente().getCodePointVente());
		
	}
	
	public void exeQuery(String codePv){
		   System.out.println("lifegfeghhhhhhhhhhhhhhhhhhhhhhhh");

		 String Requete="SELECT SUM(quittance.PRIME_TOTALE) as chiffreAff,apporteur.CODE_APPORTEUR as coAp,apporteur.LIBELLE_APPORTEUR as libelle FROM avenant, contrat,apporteur,quittance where contrat.NUM_POLICE = avenant.NUM_POLICE and quittance.NUM_AVENANT = avenant.NUM_AVENANT and contrat.CODE_POINT_VENTE='"+codePv+"' and avenant.effet between '"+formate.format(getDateDeb()) +"' AND  '"+formate.format(getDateFin()) +"' and avenant.mouvement='AFFAIRE NOUVELLE' or avenant.mouvement='Renouvellement'"; 
		  List<AvenantByPointVenteRow> rslt =  (List<AvenantByPointVenteRow>) getSessionFactory().getCurrentSession().createSQLQuery(Requete).addScalar("chiffreAff", StandardBasicTypes.BIG_DECIMAL).addScalar("coAp", StandardBasicTypes.STRING).addScalar("libelle", StandardBasicTypes.STRING).setResultTransformer(Transformers.aliasToBean(AvenantByPointVenteRow.class)).list();
		  if (rslt.size()==0){
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"0", "Aucun Enregistrement!"));
		  }
		  else{
		   for(Iterator it=rslt.iterator();it.hasNext();){
			   AvenantByPointVenteRow Pvt=(AvenantByPointVenteRow) it.next();
			   getListAvenByPtvte().add(Pvt);  
			   System.out.println("lifegfeghhhhhhhhhhhhhhhhhhhhhhhh"+getListAvenByPtvte().size());

		   }
		  }
	}
	
	public Number ptvtePieChartData(PointVente ptVte) {
		BigDecimal X = BigDecimal.ZERO;
		for (AvenantByPointVenteRow a : getListAvenByPtvte()) {
			if (a.getPtVte().getCodePointVente().equalsIgnoreCase(ptVte.getCodePointVente())) {
				if (a.getQuittance() != null)
					X = X.add(a.getQuittance().getNetAPayer());
			}
		}
		;
		return X;
	}



	

	
	
	
	
	
	public List<AvenantByPointVenteRow> getListAvenByPtvte() {
		return listAvenByPtvte;
	}




	public void setListAvenByPtvte(List<AvenantByPointVenteRow> listAvenByPtvte) {
		this.listAvenByPtvte = listAvenByPtvte;
	}




	public List<Apporteur> getApporteurs() {
		return apporteurs;
	}




	public void setApporteurs(List<Apporteur> apporteurs) {
		this.apporteurs = apporteurs;
	}




	public List<PointVente> getPointVenteList() {
		if(pointVenteList.isEmpty()){
			for (Object c : getObjectService().getObjects("PointVente")) {  
				pointVenteList.add((PointVente) c);  
	            } 
		}
		return pointVenteList;
	}






	public Date getDateDeb() {
		return dateDeb;
	}











	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}











	public Date getDateFin() {
		return dateFin;
	}




	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}


	public SimpleDateFormat getFormate() {
		return formate;
	}




	public void setFormate(SimpleDateFormat formate) {
		this.formate = formate;
	}



	public PointVente getPointVente() {
		return pointVente;
	}




	public PtdeVenteCvtr getPtdeVenteCvtr() {
		return ptdeVenteCvtr;
	}





	public void setPtdeVenteCvtr(PtdeVenteCvtr ptdeVenteCvtr) {
		this.ptdeVenteCvtr = ptdeVenteCvtr;
	}




	public ObjectService getObjectService() {
		return objectService;
	}




	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}




	public String getRequete() {
		return Requete;
	}




	public void setPointVenteList(List<PointVente> pointVenteList) {
		this.pointVenteList = pointVenteList;
	}




	public void setPointVente(PointVente pointVente) {
		this.pointVente = pointVente;
	}



	public Apporteur getApporteur() {
		return apporteur;
	}



	public void setApporteur(Apporteur apporteur) {
		this.apporteur = apporteur;
	}



	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}



	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
