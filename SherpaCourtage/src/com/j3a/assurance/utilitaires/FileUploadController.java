package com.j3a.assurance.utilitaires;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.UploadedFile;

@ManagedBean(name="fileUploadController")

public class FileUploadController {
	private static  Logger logger=Logger.getLogger(FileUploadController.class);

	private String destination="c:\\upload\\temp\\";
	
	private UploadedFile file;  
	
	public UploadedFile getFile() {  
	    return file;  
	}  
	
	public void setFile(UploadedFile file) {  
	    this.file = file;  
	}  
//	
//	public void upload2(FileUploadEvent event) { 
//	
//		FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded."); 
//		
//		FacesContext.getCurrentInstance().addMessage(null, msg);
//		
//		try {
//		
//		copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
//		
//		} catch (IOException e) {
//		
//		e.printStackTrace();
//		
//		}
////	
////	} 
//	
//	public void upload_quittance() { 	
//		try {
//		//copyFile(file.getFileName(), file.getInputstream());
//		if(copyFile("quittancebdd.xml", file.getInputstream())){
//			FacesMessage msg = new FacesMessage("Succès! ", file.getFileName() + " a été téléchargé!");
//			FacesContext.getCurrentInstance().addMessage(null, msg);
//		}
//		else {
//			FacesMessage msg = new FacesMessage("Failure! ");
//			FacesContext.getCurrentInstance().addMessage(null, msg);
//		}		
//		} catch (IOException e) {		
//		e.printStackTrace();		
//		}	
//	} 
	
	public void upload() { 	
		try {
		//copyFile(file.getFileName(), file.getInputstream());
			String nom= getNomFile(file.getFileName());
			System.out.print("file.getFileName()"+file.getFileName());
			
		if(copyFile(nom, file.getInputstream())){
			FacesMessage msg = new FacesMessage("Succès! ", file.getFileName() + " a été téléchargé!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		else {
			FacesMessage msg = new FacesMessage("Failure! ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
		} catch (IOException e) {		
		e.printStackTrace();		
		}	
	}
	public String getNomFile(String nom){
		String NomFile=null;
		
		 if(contains(nom,"acteMedical")){
				NomFile="acteMedicalbdd.xml";
			}
		else if(contains(nom,"apporteur")){
			NomFile="apporteurbdd.xml";
		}
		else if(contains(nom,"attestation")){
			NomFile="attestationbdd.xml";
		}
		else if(contains(nom,"avenant")){
			NomFile="avenantbdd.xml";
		}
		else if(contains(nom,"avn_vehic_assur")){
			NomFile="avn_vehic_assurbdd.xml";
		}
		else if(contains(nom,"ayantDroit")){
			NomFile="ayantDroitbdd.xml";
		}
		 	 
		else if(contains(nom,"coassurance")){
			NomFile="coassurancebdd.xml";
		}
		else if(contains(nom,"coassur_compagn")){
			NomFile="coassur_compagnbdd.xml";
		}
		else if(contains(nom,"compagnie")){
			NomFile="compagniebdd.xml";
		}
		else if(contains(nom,"conducteur")){
			NomFile="conducteurbdd.xml";
		}
		 		 
		else if(contains(nom,"cdctr_advrs")){
			NomFile="cdctr_advrsbdd.xml";
		}
		else if(contains(nom,"conduct_sinistre")){
			NomFile="conduct_sinistrebdd.xml";
		}
		else if(contains(nom,"conduire_Vehicl")){
			NomFile="conduire_Vehiclbdd.xml";
		}
		else if(contains(nom,"contact")){
			NomFile="contactbdd.xml";
		} 
		else if(contains(nom,"contrat")){
			NomFile="contratbdd.xml";
		}
		 
		else if(contains(nom,"etre")){
			NomFile="etrebdd.xml";
		}
		else if(contains(nom,"facture")){
			NomFile="facturebdd.xml";
		}
		else if(contains(nom,"grt")){
			NomFile="grtbdd.xml";
		}
		else if(contains(nom,"gart_Chois")){
			NomFile="gart_Choisbdd.xml";
		}
		 	 
		else if(contains(nom,"garantieGarantieChoisie")){
			NomFile="garantieGarantieChoisiebdd.xml";
		}
		else if(contains(nom,"grat_trspt")){
			NomFile="grat_trspt.xml";
		}
		else if(contains(nom,"grti_ch_tras")){
			NomFile="grti_ch_tras.xml";
		}
		else if(contains(nom,"ggct")){
			NomFile="ggct.xml";
		}
		else if(contains(nom,"avtra")){
			NomFile="avtra.xml";
		}
		 
 
		else if(contains(nom,"histoProprietesVehicule")){
			NomFile="histoProprietesVehiculebdd.xml";
		}
		else if(contains(nom,"intervenant")){
			NomFile="intervenantbdd.xml";
		}
		else if(contains(nom,"intervention")){
			NomFile="interventionbdd.xml";
		}
		 
		else if(contains(nom,"medecin")){
			NomFile="medecinbdd.xml";
		}
		else if(contains(nom,"morale")){
			NomFile="moralebdd.xml";
		}
		 
		else if(contains(nom,"modereconduction")){
			NomFile="modereconductionbdd.xml";
		}
		else if(contains(nom,"mouvement")){
			NomFile="mouvementbdd.xml";
		}
		 
		else if(contains(nom,"nationlt")){
			NomFile="nationltbdd.xml";
		}
		 	 
		else if(contains(nom,"partieAdverse")){
			NomFile="partieAdversebdd.xml";
		}
 
		else if(contains(nom,"permis")){
			NomFile="permisbdd.xml";
		}
		else if(contains(nom,"prs_Nationalite")){
			NomFile="prs_Nationalitebdd.xml";
		}
		else if(contains(nom,"physique")){
			NomFile="physiquebdd.xml";
		}

		else if(contains(nom,"pv_tp_Apportr")){
			NomFile="pv_tp_Apportrbdd.xml";
		}
		else if(contains(nom,"prejudice")){
			NomFile="prejudicebdd.xml";
		}
		else if(contains(nom,"personne")){
			NomFile="personnebdd.xml";
		}
		else if(contains(nom,"pointvente")){
			NomFile="pointventebdd.xml";
		}
		 
		else if(contains(nom,"province")){
			NomFile="provincebdd.xml";
		}
		else if(contains(nom,"pays")){
			NomFile="paysbdd.xml";
		}
		
		else if(contains(nom,"profluser")){
			NomFile="profluserbdd.xml";
		}
		else if(contains(nom,"profil")){
			NomFile="profilbdd.xml";
		}
		else if(contains(nom,"quittance")){
			NomFile="quittancebdd.xml";
		}
		else if(contains(nom,"reassur")){
			NomFile="reassurbdd.xml";
		}
		 	 
		else if(contains(nom,"reassuranceCompagnie")){
			NomFile="reassuranceCompagniebdd.xml";
		}
 
		else if(contains(nom,"recours")){
			NomFile="recoursbdd.xml";
		}
		else if(contains(nom,"rgl")){
			NomFile="rglbdd.xml";
		}
		else if(contains(nom,"reglementSinistre")){
			NomFile="reglementSinistrebdd.xml";
		}
		else if(contains(nom,"risque")){
			NomFile="risquebdd.xml";
		}
		else if(contains(nom,"societeassurance")){
			NomFile="societeassurancebdd.xml";
		}
		 
		else if(contains(nom,"sexe")){
			NomFile="sexebdd.xml";
		}
		else if(contains(nom,"sinst")){
			NomFile="sinstbdd.xml";
		}
		else if(contains(nom,"sous_Cat_Vehcl")){
			NomFile="sous_Cat_Vehclbdd.xml";
		}
		 	 
		else if(contains(nom,"stock")){
			NomFile="stockbdd.xml";
		}
 
		else if(contains(nom,"typePersonne")){
			NomFile="typePersonnebdd.xml";
		}
		else if(contains(nom,"typepointvt")){
			NomFile="typepointvtbdd.xml";
		}
		else if(contains(nom,"typeapporter")){
			NomFile="typeapporterbdd.xml";
		}
		 
		else if(contains(nom,"utilisateur")){
			NomFile="utilisateurbdd.xml";
		}
		else if(contains(nom,"vhc")){
			NomFile="vhcbdd.xml";
		}
		else if(contains(nom,"vehiculeVehiculeAssure")){
			NomFile="vehiculeVehiculeAssurebdd.xml";
		}
		 
		else if(contains(nom,"vehiculeZoneGeographique")){
			NomFile="vehiculeZoneGeographiquebdd.xml";
		}
 
		else if(contains(nom,"vehiculesAssures")){
			NomFile="vehiculesAssuresbdd.xml";
		}
		else if(contains(nom,"victime")){
			NomFile="victimebdd.xml";
		}

		else if(contains(nom,"ville")){
			NomFile="villebdd.xml";
		}
		else if(contains(nom,"zonegeographique")){
			NomFile="zonegeographiquebdd.xml";
		}
		else if(contains(nom,"lst_Tfc")){
			NomFile="lst_Tfc.xml";
		}
		 
		 else if(contains(nom,"trafic")){
				NomFile="trafic.xml";
			}
		 else if(contains(nom,"aliment")){
				NomFile="aliment.xml";
			}
	    else if(contains(nom,"classeia")){
				NomFile="classe_ia.xml";
			}
	    else if(contains(nom,"clsmrh")){
			NomFile="classe_mrh.xml";
		}
	    else if(contains(nom,"listai")){
			NomFile="liste_assure_ia.xml";
		}
	    else if(contains(nom,"lstc")){
			NomFile="liste_corps_engin.xml";
		}
	    else if(contains(nom,"lsta")){
			NomFile="liste_habitation.xml";
		}
	    else if(contains(nom,"rsqnta")){
			NomFile="risque_nta.xml";
		}
		 
		 
		else {
			NomFile="nullbdd.xml";
		}
		
		return NomFile;
	}
	
	public static boolean contains(String str, String searchStr) {
	      if (str == null || searchStr == null) {
	          return false;
	      }
	      return str.indexOf(searchStr) >= 0;
	  }
	
	public boolean copyFile(String fileName, InputStream in) {
	
		try {
			File dir = new File("c:\\upload\\temp\\");
			dir.mkdirs();	
			
		//FileWriter newJsp = new FileWriter("c:\\upload\\temp\\test.txt");
		OutputStream out = new FileOutputStream(new File(destination + fileName));
		
		
		int read = 0;
		
		byte[] bytes = new byte[1024];
		
		while ((read = in.read(bytes)) != -1) {
		
		out.write(bytes, 0, read);
		
		}
		
		in.close();
		
		out.flush();
		
		out.close();
		
		System.out.println("Un nouveau fichier a été ajouté dans le dossier c:\\upload\\temp\\ !");
		return true;
		
		} catch (IOException e) {
		
		System.out.println(e.getMessage());
		return false;
		
		}
	
	}

}
