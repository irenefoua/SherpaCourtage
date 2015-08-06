package com.j3a.assurance.managedBean.Pilotage;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.AyantDroit;
import com.j3a.assurance.model.Expertise;
import com.j3a.assurance.model.Sinistre;
import com.j3a.assurance.model.Victime;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.utilitaires.SuiviIndemSinistre;
import com.j3a.assurance.utilitaires.SuiviIndemexpertiseSinistre;



@Component
@Scope("session")
public class ManagedEtatavcmtSinistre {
	
	@Autowired
	ObjectService objectService;
	
	private String sinistrechoisi;
	private List<Sinistre> listsinistre = new ArrayList<Sinistre>();
	private List<Sinistre> listsinistreppolice = new ArrayList<Sinistre>();
	private List<AyantDroit> listayantdroit;
	private List<Victime> listVictimes;
	private List<SuiviIndemSinistre> listsuivi = new ArrayList<SuiviIndemSinistre>();
	private List<SuiviIndemexpertiseSinistre> listsuiviexpertise = new ArrayList<SuiviIndemexpertiseSinistre>();
	private List<Expertise> listexpertise = new ArrayList<Expertise>();
	private String valeurRecherche ="";
	private String optionRech = "1";
	private Sinistre sinistre = new Sinistre();
	private String ss = "";
	
	
	public String getSs() {
		return ss;
	}
	public void setSs(String ss) {
		this.ss = ss;
	}
	private static  Logger logger=Logger.getLogger(ManagedEtatavcmtSinistre.class);
	
	public void getvictimes (String idsinistre)  {
		listVictimes = new ArrayList<Victime>();
		listVictimes =  getObjectService().getvictimes(idsinistre);
}
	public void getayantdroits (String idsinistre)  {
		listayantdroit = new ArrayList<AyantDroit>();
		listayantdroit =  getObjectService().getayantdroits(idsinistre);
}
	
	public void rechercherexpertise()
	{
		listsuiviexpertise.clear();
		listexpertise = objectService.getexpertise(sinistre.getCodeSinistre());
		for(Expertise e : listexpertise)
		{
			SuiviIndemexpertiseSinistre suiviexpertises = new SuiviIndemexpertiseSinistre();
			suiviexpertises.setSinistre(sinistre.getCodeSinistre());
			suiviexpertises.setRefexpertise(e.getRefExpertise());
			suiviexpertises.setCodeexpert(e.getExpertSinistre().getCodeExpertSinistre());
			suiviexpertises.setNomexpert(e.getExpertSinistre().getNomExpert());
			suiviexpertises.setCodefacture(e.getFacture().getCodeFacture());
			suiviexpertises.setFrais(e.getFacture().getMontantHtFact());
			suiviexpertises.setStatut(e.getFacture().getEtatFacture());
			
			listsuiviexpertise.add(suiviexpertises);
		}
	}

	public void rechercherSinistreparpolice()
	{
		try {
			listsinistreppolice.clear();
			listsinistre.clear();
			listsinistreppolice = getObjectService().sinistreparpolice(sinistrechoisi);
			listsuivi.clear();
			}
			catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "pas de sinistre pour cette police",""));

			e.printStackTrace();
			
		}
	}
	
	public void rechercherSinistre(){
		Sinistre sin = (Sinistre) objectService.getObjectById(sinistrechoisi, "Sinistre");
		if(sin == null)
		{
			listsinistreppolice.clear();
			listsuivi.clear();
			listsuiviexpertise.clear();
			System.out.println("pas de sinistre avec ce num");
		}
		else
		{
			listsinistreppolice.clear();
			listsinistreppolice.add(sin);
		}
	}
	public void chercher(){
		switch (optionRech) {
		case "1": 
			try {
				rechercherSinistre();
			} catch (Exception e) {
				// TODO Auto-generated catch block
			logger.error("Erreur recherche par l'option numéro de sinistre: ");
				e.printStackTrace();
			}
			
			break;
			
		case "2":
			try {
				rechercherSinistreparpolice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("Erreur recherche par l'option numéro Police : ");
				e.printStackTrace();
			}
			break;
			
		default:
			// Envoi de message: option non selectionné
			break;
		}
	}
	
	public void rechercherinfoSinistre() {
		try {
			//listsinistreppolice.clear();
			listsuivi.clear();
			getvictimes(sinistre.getCodeSinistre());
			if(!listVictimes.isEmpty())
			{
			for(Victime v : listVictimes)
			{
				getayantdroits (v.getNumVictime());
				for(AyantDroit a : listayantdroit)
				{
					SuiviIndemSinistre s = new SuiviIndemSinistre();
					s.setSinistre(sinistre.getCodeSinistre());
					s.setVictime(v.getNumVictime());
					s.setAyantdroit(a.getCodeAyantDroit());
					s.setIndem(a.getMontantPrejudice());
					if(a.getEtatIndemniser())
						s.setStatutpaymenet("soldé");
					else
						s.setStatutpaymenet("non soldé");
					listsuivi.add(s);
				}
			}
			}
			rechercherexpertise();
		
				} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Aucun sinistre trouvé",""));

			e.printStackTrace();
			
		}	
		
		}
	public void selectionVictetAD(SelectEvent event){
		
	
		try
		{
		
			if(sinistre == null)
			{
				System.out.println("pointeur null");
				listsuivi.clear();
				sinistre = new Sinistre();
			}
			else
			{
				System.out.println("le sinistre choisi : "+sinistre.getCodeSinistre());
				rechercherinfoSinistre();
			}
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
		logger.error("Erreur recherche du sinistre par police: ");
			e.printStackTrace();
		}
		
	}
	
	//////////////////getters && setters///////////////////////////////
	

	public List getListsinistre() {
		return listsinistre;
	}

	public void setListsinistre(List listsinistre) {
		this.listsinistre = listsinistre;
	}
	public List getListsinistreppolice() {
		return listsinistreppolice;
	}
	public void setListsinistreppolice(List listsinistreppolice) {
		this.listsinistreppolice = listsinistreppolice;
	}
	public String getSinistrechoisi() {
		return sinistrechoisi;
	}

	public void setSinistrechoisi(String sinistrechoisi) {
		this.sinistrechoisi = sinistrechoisi;
	}

	public ObjectService getObjectService() {
		return objectService;
	}
	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}

	public List<AyantDroit> getListayantdroit() {
		return listayantdroit;
	}

	public void setListayantdroit(List<AyantDroit> listayantdroit) {
		this.listayantdroit = listayantdroit;
	}

	public List<Victime> getListVictimes() {
		return listVictimes;
	}

	public void setListVictimes(List<Victime> listVictimes) {
		this.listVictimes = listVictimes;
	}

	public List<SuiviIndemSinistre> getListsuivi() {
		return listsuivi;
	}

	public void setListsuivi(List<SuiviIndemSinistre> Listsuivi) {
		listsuivi = Listsuivi;
	}


	public String getValeurRecherche() {
		return valeurRecherche;
	}
	public void setValeurRecherche(String valeurRecherche) {
		this.valeurRecherche = valeurRecherche;
	}
	public String getOptionRech() {
		return optionRech;
	}


	public void setOptionRech(String optionRech) {
		this.optionRech = optionRech;
	}
	public Sinistre getSinistre() {
		return sinistre;
	}
	public void setSinistre(Sinistre sinistre) {
		this.sinistre = sinistre;
	}
	public List<Expertise> getListexpertise() {
		return listexpertise;
	}
	public void setListexpertise(List<Expertise> listexpertise) {
		this.listexpertise = listexpertise;
	}
	public List<SuiviIndemexpertiseSinistre> getListsuiviexpertise() {
		return listsuiviexpertise;
	}
	public void setListsuiviexpertise(
			List<SuiviIndemexpertiseSinistre> listsuiviexpertise) {
		this.listsuiviexpertise = listsuiviexpertise;
	}

}
