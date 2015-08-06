package com.j3a.assurance.reporting.bean.factory;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.Avenant;
import com.j3a.assurance.model.Contrat;
import com.j3a.assurance.model.Personne;
import com.j3a.assurance.model.PointVente;
import com.j3a.assurance.model.Quittance;
import com.j3a.assurance.model.Vehicule;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.reporting.bean.QuittanceReport;
@Component
public class QuittanceReportFactory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(QuittanceReportFactory.class);
	@Autowired
	ObjectService objectService;
	private String idQuittance;
	private QuittanceReport quittanceReport;
	private String codeAssure;

	public QuittanceReport quittanceReportProvider() {

		try {
			// recueration de la quittance par l'id
			System.out.println("<----getIdQuittance :" + getIdQuittance());// Clean after
			Quittance quittance = (Quittance) getObjectService().getObjectById(
					getIdQuittance(), "Quittance");
			System.out.println("<----quittance :" + quittance);// Clean after
			// peuplement des attributs relatifs à la quittance du bean quittance
			getQuittanceReport().setIdQuittance(quittance.getCodeQuittance());
			getQuittanceReport().setTaxes(quittance.getTaxes());
			getQuittanceReport().setAccessoires(quittance.getAccessoire());
			getQuittanceReport().setPrimeNette(quittance.getPrimeNette());
			getQuittanceReport().setNetAPayer(quittance.getNetAPayer());
			getQuittanceReport().setAccesoirCompagnie(quittance.getAccCnie());
			getQuittanceReport().setAccesoirIntermediair(quittance.getAccIntrerm());
			getQuittanceReport().setAccessoires(quittance.getAccessoire());
			getQuittanceReport().setAcessoirgest(quittance.getAccGestionnaire());
			getQuittanceReport().setTaxes(quittance.getTaxes());
			getQuittanceReport().setComAperition(quittance.getComAperition());
			getQuittanceReport().setComConseil(quittance.getComConseil());
			getQuittanceReport().setComGestionnaire(quittance.getComGestionnaire());
			getQuittanceReport().setCommission(quittance.getCommision());
			
			// recuperation de l'avenant
			Avenant avenant = quittance.getAvenant();
			
			// peuplement des attributs relatifs à l'avenant du bean quittance
			getQuittanceReport().setNumAvenant(avenant.getNumAvenant());
			getQuittanceReport().setDateEffet(avenant.getEffet());
			getQuittanceReport().setDateEcheance(avenant.getEcheance());
			getQuittanceReport().setEmission(avenant.getDateEmission());
			getQuittanceReport().setMouvement(avenant.getMouvement());
			getQuittanceReport().setDuree(avenant.getDuree());
			
			
			// recuperation du contrat
			Contrat contrat = avenant.getContrat();
			
			// peuplement des attributs relatifs au contrat du bean quittance
			getQuittanceReport().setPolice(contrat.getNumPolice());
			
			//recupération du risque du contrat
			getQuittanceReport().setRisque(contrat.getRisque().getLibelleRisque());
			
			//recupération du barème du contrat
			getQuittanceReport().setBareme(contrat.getBareme());
			
			
			// recupération du type d'assurance
			getQuittanceReport().setTypeAssurance(
					this.getTypeAssurance(contrat));
			
			// recuperation de la personne
			Personne personne = contrat.getPersonne();
			// peuplement des attributs relatifs souscripteur du bean quittance
			getQuittanceReport().setNom(this.getNomSouscripteur(personne));
			
			// recuperation de l'adresse du souscripteur
			getQuittanceReport().setAdresse(personne.getAdresse());
			getQuittanceReport().setMail(personne.getEmail());
			getQuittanceReport().setTelephone(personne.getAdresse());
			getQuittanceReport().setFax(personne.getFax());
			
			// recuperation de l'agence
			PointVente pointVente = contrat.getPointVente();
			
			// peuplement des attributs relatifs a l'agence du bean quittance
			getQuittanceReport().setAgence(pointVente.getLibellePointVente());
			logger.info("conditions particulières générées");
			return getQuittanceReport();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("erreur de génération de la quittance N°: "
					+ getIdQuittance());
			return null;
		}

	}

	public String getNomSouscripteur(Personne personne) {
		StringBuffer buf = new StringBuffer();
		buf.append(personne.getNomRaisonSociale());
		buf.append(" ");
		if (personne.getPhysique()!=null) {
			buf.append( personne.getPhysique().getPrenomPers());
		}
		System.out
				.println("methode du recupération du nom executée avec succès");
		return buf.toString();

	}

	public String getTypeAssurance(Contrat contrat) {

		List<Vehicule> vehiculelist = getObjectService().getListVehiculesContrat(
				contrat.getNumPolice());

		System.out.println("le type de l'assurance est bien ajouté :"
				+ vehiculelist);
		if (vehiculelist.size() == 1) {
			Vehicule vehicule = vehiculelist.get(0);
			String typeAssurance = vehicule.getSousCatVehicule()
					.getCategorie().getLibelleCategorie();
			return typeAssurance;
		} else {
			return "Flotte";
		}

	}

	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}

	public String getIdQuittance() {
		return idQuittance;
	}

	public void setIdQuittance(String idQuittance) {
		this.idQuittance = idQuittance;
	}

	public void setQuittanceReport(QuittanceReport quittanceReport) {
		this.quittanceReport = quittanceReport;
	}

	public QuittanceReport getQuittanceReport() {
		return quittanceReport;
	}

	public String getCodeAssure() {
		return codeAssure;
	}

	public void setCodeAssure(String codeAssure) {
		this.codeAssure = codeAssure;
	}
}
