package com.j3a.assurance.managedBean.adminCompagnie;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;














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
import com.j3a.assurance.model.CompagnieAssurance;
import com.j3a.assurance.model.CompteCompagnieAssurance;
import com.j3a.assurance.model.Pays;
import com.j3a.assurance.model.UserRole;
import com.j3a.assurance.objetService.ObjectService;


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
	private CompagnieAssurance compagnieAssurance= new CompagnieAssurance();
	@Autowired
	private ObjectService objectService;
	private List<Pays> pays=new ArrayList<Pays>();
	private String choixPays;
	UserRole userRole=new UserRole();
	
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
	
	
	
	
	public List<Pays> paysList(){
		pays=new ArrayList<Pays>();
		for(Object pa:getObjectService().getojects("Pays")){
			pays.add((Pays) pa);
		}
		return pays;
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

	

	

}
