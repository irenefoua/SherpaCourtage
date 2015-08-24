package com.j3a.assurance.managedBean.adminCompagnie;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



















import org.springframework.transaction.annotation.Transactional;

import com.j3a.assurance.managedBean.admin.Categorie1MB;
import com.j3a.assurance.managedBean.admin.Categorie2MB;
import com.j3a.assurance.managedBean.admin.ManagedTarif10;
import com.j3a.assurance.managedBean.admin.ManagedTarif12;
import com.j3a.assurance.managedBean.admin.ManagedTarif3;
import com.j3a.assurance.managedBean.admin.ManagedTarif4;
import com.j3a.assurance.managedBean.admin.ManagedTarif5;
import com.j3a.assurance.managedBean.admin.ManagedTarif6;
import com.j3a.assurance.managedBean.admin.ManagedTarif7;
import com.j3a.assurance.managedBean.admin.ManagedTarif8;
import com.j3a.assurance.managedBean.admin.ManagedTarif9;
import com.j3a.assurance.model.Avenant;
import com.j3a.assurance.model.CompagnieAssurance;
import com.j3a.assurance.model.CompteCompagnieAssurance;
import com.j3a.assurance.model.Pays;
import com.j3a.assurance.model.Personne;
import com.j3a.assurance.model.UserRole;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.utilitaires.AvenantByPointVenteRow;
import com.j3a.assurance.utilitaires.hybride.ChiffreAffaireRow;

@Transactional
@Scope("session")
@Component
public class ManagedCompagnie implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CompagnieAssurance compagnieAssuranceConnecte=new CompagnieAssurance();
	@Autowired
	private Categorie1MB categorie1MB;
	@Autowired
	private Categorie2MB categorie2mb;
	@Autowired
	private ManagedTarif5 managedTarif5;
	@Autowired
	private ManagedTarif6 managedTarif6;
	@Autowired
	private ManagedTarif10 managedTarif10;
	@Autowired
	private ManagedTarif12 managedTarif12;
	@Autowired
	private ManagedTarif3 managedTarif3;
	@Autowired
	private ManagedTarif4 managedTarif4;
	@Autowired
	private ManagedTarif7 managedTarif7;
	@Autowired
	private ManagedTarif8 managedTarif8;
	@Autowired
	private ManagedTarif9 managedTarif9;
	private List<ChiffreAffaireRow> listChiffreAffaireRow=new ArrayList<ChiffreAffaireRow>();
	private List<ChiffreAffaireRow> listChiffreAffaireRows=new ArrayList<ChiffreAffaireRow>();
	private CompagnieAssurance compagnieAssurance= new CompagnieAssurance();
	@Autowired
	private ObjectService objectService;
	@Autowired
	private SessionFactory sessionFactory;
	private List<Pays> pays=new ArrayList<Pays>();
	private String choixPays;
	UserRole userRole=new UserRole();
	private List<Avenant> listAvenant=new ArrayList<Avenant>();
	private List<Personne> listClient=new ArrayList<Personne>();
	
	
	
	
	public void ajouterCompagnie(){
		
	try {
		Pays pays=new Pays();
		pays.setCodePays(getChoixPays());
		compagnieAssurance.setPays(pays);
		compagnieAssurance.setActiviteCompagnie(true);
		userRole=(UserRole) getObjectService().getObjectById(2, "UserRole");
		compagnieAssurance.setUserRole(userRole);
		getObjectService().addObject(getCompagnieAssurance());
        CompteCompagnieAssurance compteCompagnieAssurance=new CompteCompagnieAssurance();
        compteCompagnieAssurance.setCodeCompteCompAss(getCompagnieAssurance().getCodeCompagnieAssurance());
        compteCompagnieAssurance.setCompagnieAssurance(getCompagnieAssurance());
        compteCompagnieAssurance.setDateCreaCompte(Calendar.getInstance().getTime());
        compteCompagnieAssurance.setPrimeAVersee(new BigDecimal(0));
        compteCompagnieAssurance.setTotalPrimeDue(new BigDecimal(0));
        compteCompagnieAssurance.setTotamPrimeRecu(new BigDecimal(0));
		getObjectService().addObject(compteCompagnieAssurance);
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Enregistrement éffectué avec succès!"));
	} catch (Exception e) {
				e.printStackTrace();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Enregistrement non éffectué!"));
	}	
		
		
		
	}
	
	
	public String chargerT1()
	{
		categorie1MB.PostConst(getCompagnieAssuranceConnecte());
		categorie2mb.PostConst(getCompagnieAssuranceConnecte());
		managedTarif3.PostConst(getCompagnieAssuranceConnecte());
		managedTarif4.PostConst(getCompagnieAssuranceConnecte());
		managedTarif5.PostConst(getCompagnieAssuranceConnecte());
		managedTarif6.PostConst(getCompagnieAssuranceConnecte());
		managedTarif7.PostConst(getCompagnieAssuranceConnecte());
		managedTarif8.PostConst(getCompagnieAssuranceConnecte());
		managedTarif9.PostConst(getCompagnieAssuranceConnecte());
		managedTarif10.PostConst(getCompagnieAssuranceConnecte());
		managedTarif12.PostConst(getCompagnieAssuranceConnecte());	
		return "/Page/EspaceCompagnie/Tarif1?faces-redirect=true";
	}
	
	public String ChargerAvenantCompagnie(){
		List<Avenant> listAnt=new ArrayList<>();
		listAnt=getObjectService().listAvenantCompagnie(getCompagnieAssuranceConnecte().getCodeCompagnieAssurance());
		 if (getListAvenant().size()==0){
			    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"0", "Aucun Enregistrement!"));
			  }
			  else{
			   for(Iterator it=listAnt.iterator();it.hasNext();){
				   Avenant avenant=(Avenant) it.next();
				   getListAvenant().add(avenant);  
			   }
			   }
		return "/Page/EspaceCompagnie/listeContrat?faces-redirect=true";
	}
	
	public String ChargerClientCompagnie(){
		//List<Personne> listpers=new ArrayList<>();
		listClient=getObjectService().listClientCompagnie(getCompagnieAssuranceConnecte().getCodeCompagnieAssurance());
		/* if (getListClient().size()==0){
			    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"0", "Aucun Enregistrement!"));
			  }
			  else{
			   for(Iterator it=listpers.iterator();it.hasNext();){
				   Personne personne=(Personne) it.next();
				  
				   getListClient().add(personne);  
				   System.out.println("persoooooooooooo"+personne.getNomRaisonSociale());
			   }*/
			  // }
		return "/Page/EspaceCompagnie/ListeClient?faces-redirect=true";
	} 
	
	List<Pays> paysList(){
		pays=new ArrayList<Pays>();
		for(Object pa:getObjectService().getojects("Pays")){
			pays.add((Pays) pa);
		}
		return pays;
	}
	
	
	public void listeCaAn(){
		 listChiffreAffaireRow=new ArrayList<ChiffreAffaireRow>();
			String Requete = "SELECT SUM(q.NET_A_PAYER)as ca, a.CODEEXERCICE as coExercice FROM avenant a,quittance q,compagnie_assurance c WHERE a.num_avenant=q.num_avenant and c.CODE_COMPAGNIE_ASSURANCE=a.CODE_COMPAGNIE_ASSURANCE and c.CODE_COMPAGNIE_ASSURANCE='"+getCompagnieAssuranceConnecte().getCodeCompagnieAssurance()+"' group by a.CODEEXERCICE";
		  setListChiffreAffaireRow((List<ChiffreAffaireRow>) getSessionFactory().getCurrentSession().createSQLQuery(Requete).addScalar("ca", StandardBasicTypes.BIG_DECIMAL).addScalar("coExercice", StandardBasicTypes.INTEGER).setResultTransformer(Transformers.aliasToBean(ChiffreAffaireRow.class)).list());
     		
	}
	
	
	public void listeCaMois(){
		 listChiffreAffaireRows=new ArrayList<ChiffreAffaireRow>();
			String Requete = "SELECT SUM(q.NET_A_PAYER)as ca,extract(month from a.effet)as mois, a.CODEEXERCICE as coExercice FROM avenant a,quittance q,compagnie_assurance c WHERE a.num_avenant=q.num_avenant and c.CODE_COMPAGNIE_ASSURANCE=a.CODE_COMPAGNIE_ASSURANCE and c.CODE_COMPAGNIE_ASSURANCE='"+getCompagnieAssuranceConnecte().getCodeCompagnieAssurance()+"' group by extract(month from a.effet)";
		  setListChiffreAffaireRows((List<ChiffreAffaireRow>) getSessionFactory().getCurrentSession().createSQLQuery(Requete).addScalar("ca", StandardBasicTypes.BIG_DECIMAL).addScalar("mois", StandardBasicTypes.INTEGER).addScalar("coExercice", StandardBasicTypes.INTEGER).setResultTransformer(Transformers.aliasToBean(ChiffreAffaireRow.class)).list());
    		
	}

	
   public String exeQuery(){
	   listeCaAn();
	   listeCaMois();
	return "/Page/EspaceCompagnie/ChiffreAffaire?faces-redirect=true";
	
}
	
	public CompagnieAssurance getCompagnieAssurance() {
		return compagnieAssurance;
	}
	public void setCompagnieAssurance(CompagnieAssurance compagnieAssurance) {
		this.compagnieAssurance = compagnieAssurance;
	}

	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}

	public String getChoixPays() {
		return choixPays;
	}

	public void setChoixPays(String choixPays) {
		this.choixPays = choixPays;
	}

	public List<Pays> getPays() {
		paysList();
		return pays;
	}

	public void setPays(List<Pays> pays) {
		this.pays = pays;
	}

	public CompagnieAssurance getCompagnieAssuranceConnecte() {
		setCompagnieAssuranceConnecte(getObjectService().RecupererCompagnieCourrant());
		return compagnieAssuranceConnecte;
	}

	public void setCompagnieAssuranceConnecte(CompagnieAssurance compagnieAssuranceConnecte) {
		this.compagnieAssuranceConnecte = compagnieAssuranceConnecte;
	}

	public Categorie1MB getCategorie1MB() {
		return categorie1MB;
	}

	public void setCategorie1MB(Categorie1MB categorie1mb) {
		categorie1MB = categorie1mb;
	}

	public Categorie2MB getCategorie2mb() {
		return categorie2mb;
	}

	public void setCategorie2mb(Categorie2MB categorie2mb) {
		this.categorie2mb = categorie2mb;
	}


	public ManagedTarif10 getManagedTarif10() {
		return managedTarif10;
	}


	public void setManagedTarif10(ManagedTarif10 managedTarif10) {
		this.managedTarif10 = managedTarif10;
	}


	public ManagedTarif12 getManagedTarif12() {
		return managedTarif12;
	}


	public void setManagedTarif12(ManagedTarif12 managedTarif12) {
		this.managedTarif12 = managedTarif12;
	}


	public ManagedTarif3 getManagedTarif3() {
		return managedTarif3;
	}


	public void setManagedTarif3(ManagedTarif3 managedTarif3) {
		this.managedTarif3 = managedTarif3;
	}


	public ManagedTarif4 getManagedTarif4() {
		return managedTarif4;
	}


	public void setManagedTarif4(ManagedTarif4 managedTarif4) {
		this.managedTarif4 = managedTarif4;
	}


	public ManagedTarif5 getManagedTarif5() {
		return managedTarif5;
	}


	public void setManagedTarif5(ManagedTarif5 managedTarif5) {
		this.managedTarif5 = managedTarif5;
	}


	public ManagedTarif6 getManagedTarif6() {
		return managedTarif6;
	}


	public void setManagedTarif6(ManagedTarif6 managedTarif6) {
		this.managedTarif6 = managedTarif6;
	}


	public ManagedTarif7 getManagedTarif7() {
		return managedTarif7;
	}


	public void setManagedTarif7(ManagedTarif7 managedTarif7) {
		this.managedTarif7 = managedTarif7;
	}


	public ManagedTarif8 getManagedTarif8() {
		return managedTarif8;
	}


	public void setManagedTarif8(ManagedTarif8 managedTarif8) {
		this.managedTarif8 = managedTarif8;
	}


	public ManagedTarif9 getManagedTarif9() {
		return managedTarif9;
	}


	public void setManagedTarif9(ManagedTarif9 managedTarif9) {
		this.managedTarif9 = managedTarif9;
	}




	public List<Avenant> getListAvenant() {
		return listAvenant;
	}


	public void setListAvenant(List<Avenant> listAvenant) {
		this.listAvenant = listAvenant;
	}


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public List<ChiffreAffaireRow> getListChiffreAffaireRow() {
		return listChiffreAffaireRow;
	}


	public void setListChiffreAffaireRow(List<ChiffreAffaireRow> listChiffreAffaireRow) {
		this.listChiffreAffaireRow = listChiffreAffaireRow;
	}


	public List<ChiffreAffaireRow> getListChiffreAffaireRows() {
		return listChiffreAffaireRows;
	}


	public void setListChiffreAffaireRows(List<ChiffreAffaireRow> listChiffreAffaireRows) {
		this.listChiffreAffaireRows = listChiffreAffaireRows;
	}


	public List<Personne> getListClient() {
		return listClient;
	}


	public void setListClient(List<Personne> listClient) {
		this.listClient = listClient;
	}

	

	

}
