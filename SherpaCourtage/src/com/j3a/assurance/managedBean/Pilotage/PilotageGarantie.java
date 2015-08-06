package com.j3a.assurance.managedBean.Pilotage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

import org.hibernate.SessionFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.j3a.assurance.converter.CategorieCvtr;
import com.j3a.assurance.converter.GarantieCvtr;
import com.j3a.assurance.converter.RisqCvtr;
import com.j3a.assurance.model.Categorie;
import com.j3a.assurance.model.Garantie;
import com.j3a.assurance.model.Risque;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.reporting.design.AvenantDesign;
import com.j3a.assurance.utilitaires.Garanties;
import com.j3a.assurance.utilitaires.GarantiesCategorie;
import com.j3a.assurance.utilitaires.GarantiesRisque;

@Component
@Scope("session")
@Transactional
public class PilotageGarantie {
	@Autowired
	private AvenantDesign avenantDesign;

	@Autowired
	private ObjectService objectService;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RisqCvtr risqCvtr;
	@Autowired
	private GarantieCvtr garantieCvtr;
	@Autowired
	private CategorieCvtr categorieCvtr;

	private List<Garantie> slctdGarantie;
	private List<Risque> slctdRisques = new ArrayList<>();
	private List<Categorie> slctdCategorie;
	List<Garanties> garantiesList;
	List<Garanties> garantiesListSelectd = new ArrayList<>();
	List<Garanties> filterGarantiesListSelectd = new ArrayList<>();
	private Date dateDebut, dateFin;
	private Boolean renduChart = false;
	private final String qryCmnPart = "SELECT A.* FROM  avenant A JOIN  contrat C ON C.NUM_POLICE = A.NUM_POLICE JOIN  point_vente P ON P.CODE_POINT_VENTE = C.CODE_POINT_VENTE JOIN  risque R ON R.CODE_RISQUE = C.CODE_RISQUE  WHERE ";

	public PilotageGarantie() {
		// TODO Auto-generated constructor stub
	}

	private PieChartModel modelCategorie;
	private PieChartModel modelGarantie;
	private PieChartModel modelRisque ;
	private Boolean okDisblr = true;
	private List<GarantiesRisque> listGarantiesRisque;
	private List<GarantiesCategorie> listGarantiesCategorie;
	
	@PostConstruct
	public void postConstru() {
		modelCategorie = new PieChartModel();
		
		modelCategorie.set("C",(double)1 );
		
		modelGarantie = new PieChartModel();
		
		modelGarantie.set("G",(double)1 );
		
		modelRisque = new PieChartModel();
		
		modelRisque.set("R",(double)1 );
		
		
		
	}

	public void choixRisque() {
		
		getCategorieCvtr().getListCategorieCopie().clear();
		getGarantieCvtr().getListGarantieCopie().clear();
		getSlctdCategorie().clear();
		getSlctdGarantie().clear();

		if (!getSlctdRisques().isEmpty())
			
			for (Risque r : getSlctdRisques()) {
				// alimente les catégorie

				for (Categorie c : getCategorieCvtr().getListCategorie()) {

					if (c.getRisque().getCodeRisque().equalsIgnoreCase(r.getCodeRisque()))
						getCategorieCvtr().getListCategorieCopie().add(c);
				}
				// alimente les garanties

				for (Garantie g : getGarantieCvtr().getListGarantie()) {

					if (g.getRisque().getCodeRisque().equalsIgnoreCase(r.getCodeRisque()))
						getGarantieCvtr().getListGarantieCopie().add(g);
				}
			}
	}

	public void getListeGarantie() {
		// Chargement de la liste des garanties de la base de donnee
		garantiesList = new ArrayList<Garanties>();

		List listObject = getObjectService().getObjects("Garantie");
		for (Iterator it = listObject.iterator(); it.hasNext();) {
			Garantie garantie = (Garantie) it.next();
			Garanties garanties = new Garanties();
			garanties.setCodeGarantie(garantie.getCodeGarantie());
			garanties.setLibelleGarantie(garantie.getLibelleGarantie());
			garanties.getRisque().setCodeRisque(garantie.getRisque().getCodeRisque());
			garanties.getRisque().setLibelleRisque(
					garantie.getRisque().getLibelleRisque());
			if (garantie.getRisque().getCategories().iterator().hasNext())
				garanties.setCategorie(garantie.getRisque().getCategories()
						.iterator().next());
			garantiesList.add(garanties);
		}

	}

	public BigDecimal getMontantGarantie(Garanties g) {
		String X = "";
		// Risque NTA par defaut
		X = "SELECT SUM(gGcN.PRIME_NETTE_NTA)" + "as TOTAL_GARANTIE FROM "
				+ "garantie_garantie_choisie_nta gGcN WHERE "
				+ "gGcN.CODE_GARANTIE='" + g.getCodeGarantie() + "'"
				+ " AND (gGcN.DATE_GARANTIE_GARANTIE_CHOISIE_NTA BETWEEN '"
				+ formatDate(getDateDebut()) + "' AND '"
				+ formatDate(getDateFin()) + "')";

		if (g.getRisque().getCodeRisque().equalsIgnoreCase("1")) {
			// Risque Automobile 1
			X = "SELECT SUM(gGc.PRIME_NETTE_PRORATA) "
					+ "as TOTAL_GARANTIE FROM "
					+ "garantie_garantie_choisie gGc WHERE "
					+ "gGc.CODE_GARANTIE='" + g.getCodeGarantie() + "'"
					+ " AND (gGc.DATE_GARANTIE_GARANTIE_CHOISIE BETWEEN '"
					+ formatDate(getDateDebut()) + "' AND '"
					+ formatDate(getDateFin()) + "')";

		}
		if (g.getRisque().getCodeRisque().equalsIgnoreCase("2")
				|| g.getRisque().getCodeRisque().equalsIgnoreCase("3")
				|| g.getRisque().getCodeRisque().equalsIgnoreCase("4")) {
			// Risque Transport Faculté 2,3,4
			X = "SELECT SUM(gGcT.MONTANT_GARANTIE) "
					+ "as TOTAL_GARANTIE FROM "
					+ "garantie_garantie_choisie_transport gGcT WHERE "
					+ "gGcT.CODE_GARANTIE='" + g.getCodeGarantie() + "'"
					+ " AND (gGcT.DATE_GARANTIE_CHOISIE BETWEEN '"
					+ formatDate(getDateDebut()) + "' AND '"
					+ formatDate(getDateFin()) + "')";
		}
		if (g.getRisque().getCodeRisque().equalsIgnoreCase("5")
				|| g.getRisque().getCodeRisque().equalsIgnoreCase("6")) {
			// Risque Transport Corps 5,6
			X = "SELECT SUM(gGcC.MONTANT_GARANTIE_CORPS) "
					+ "as TOTAL_GARANTIE FROM "
					+ "garantie_garantie_choisie_corps gGcC WHERE "
					+ "gGcC.CODE_GARANTIE='" + g.getCodeGarantie() + "'"
					+ " AND (gGcC.DATE_GARANTIE_CHOISIE_CORPS BETWEEN '"
					+ formatDate(getDateDebut()) + "' AND '"
					+ formatDate(getDateFin()) + "')";
		}
		if (g.getRisque().getCodeRisque().equalsIgnoreCase("8")) {
			// Risque IA
			X = "SELECT SUM(gGcI.PRIME_NETTE_IA)" + "as TOTAL_GARANTIE FROM "
					+ "garantie_garantie_choisie_ia gGcI WHERE "
					+ "gGcI.CODE_GARANTIE='" + g.getCodeGarantie() + "'"
					+ " AND (gGcI.DATE_GARANTIE_GARANTIE_CHOISIE_IA BETWEEN '"
					+ formatDate(getDateDebut()) + "' AND '"
					+ formatDate(getDateFin()) + "')";
		}

		if (g.getRisque().getCodeRisque().equalsIgnoreCase("7")) {
			// Risque MRH
			X = "SELECT SUM(gGcM.PRIME_NETTE_MRH) " + "as TOTAL_GARANTIE FROM "
					+ "garantie_garantie_choisie_mrh gGcM WHERE "
					+ "gGcM.CODE_GARANTIE='" + g.getCodeGarantie() + "'"
					+ " AND (gGcM.DATE_GARANTIE_GARANTIE_CHOISIE_MRH BETWEEN '"
					+ formatDate(getDateDebut()) + "' AND '"
					+ formatDate(getDateFin()) + "')";
		}
		if (g.getRisque().getCodeRisque().equalsIgnoreCase("11")) {
			// Risque SANTE
			X = "SELECT SUM(gGcS.PRIME_NETTE_SANTE) "
					+ "as TOTAL_GARANTIE FROM "
					+ "garantie_garantie_choisie_sante gGcS WHERE "
					+ "gGcS.CODE_GARANTIE='"
					+ g.getCodeGarantie()
					+ "'"
					+ " AND (gGcS.DATE_GARANTIE_GARANTIE_CHOISIE_SANTE BETWEEN '"
					+ formatDate(getDateDebut()) + "' AND '"
					+ formatDate(getDateFin()) + "')";
		}

		// execution de la requette
		BigDecimal prime = (BigDecimal) getSessionFactory().getCurrentSession()
				.createSQLQuery(X).addScalar("TOTAL_GARANTIE").uniqueResult();
		if (prime == null)
			prime = BigDecimal.ZERO;
		return prime;
	}

	public void chargerGarantiesBD() {
		System.out.println("Test 2");
		getGarantiesListSelectd().clear();
		// Liste des Garanties BD
		getListeGarantie();
		// Chargement du montant(prime) de chaque Garantie avec les contrats de
		// la base de données;
		for (Garanties g : getGarantiesList()) {
			g.setPrime(getMontantGarantie(g));
		}
		// On charge les listes
		// Liste Garanties
		if (!getSlctdGarantie().isEmpty()){
			for (Garantie g : getSlctdGarantie()) {
				for (Garanties gs : getGarantiesList()) {
					if (g.getCodeGarantie().equalsIgnoreCase(gs.getCodeGarantie()))
						getGarantiesListSelectd().add(gs);
				}
			}
		}
	}

	public void submitCriterias() {
		System.out.println("Test 1");
		chargerGarantiesBD();
		loadModelGarantie();
		checkIfModelEmpty(modelGarantie);
		loadModelCategorie();
		checkIfModelEmpty(modelCategorie);
		loadModelRisq();
		checkIfModelEmpty(modelRisque);

	}

	private void checkIfModelEmpty(PieChartModel model) {
		if (model.getData().isEmpty())
			model.set("No records found.", 0);
	}

	public String qryPeriodPart() {
		String X = "";
		X = X + "A.EFFET BETWEEN '" + formatDate(getDateDebut()) + "' AND '"
				+ formatDate(getDateFin()) + "'";
		return X;
	}

	public String formatDate(Date d) {
		String X = "";
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		// AAAA-MM-JJ
		X = X + c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-"
				+ c.get(Calendar.DATE);

		return X;
	}

	public Number catPieChartData(Categorie cat) {
		BigDecimal X = BigDecimal.ZERO;

		for (Categorie c : getSlctdCategorie()) {
			if (c.getCodeCategorie().equalsIgnoreCase(cat.getCodeCategorie())) {
				for (Garanties g : getGarantiesListSelectd()) {
					if (g.getRisque().getCodeRisque()
							.equalsIgnoreCase(c.getRisque().getCodeRisque()))
						X = X.add(g.getPrime());
				}
			}
		}
		return X;
	}

	public void loadModelCategorie() {
		List<Categorie> L = new ArrayList<Categorie>();
		if (getSlctdCategorie().isEmpty()) {
			L = getCategorieCvtr().getListCategorie();
		} else {
			L = getSlctdCategorie();
		}
		modelCategorie=new PieChartModel();
		/*getModelCategorie().setLegendCols(2);
		getModelCategorie().setLegendPosition("w");
		getModelCategorie().setShowDataLabels(true);
		getModelCategorie().setTitle(
				"REPARTION DES PRIMES GARANTIES PAR CATEGORIE");*/

		for (Categorie c : L) {
			modelCategorie
					.set(c.getLibelleCategorie(), catPieChartData(c));
		}
	}

	public Number garPieChartData(Garantie gar) {
		BigDecimal X = BigDecimal.ZERO;

		for (Garanties g : getGarantiesListSelectd()) {
			if (g.getCodeGarantie().equalsIgnoreCase(gar.getCodeGarantie()))
				X = X.add(g.getPrime());
		}

		return X;
	}

	public void loadModelGarantie() {
		List<Garantie> L = new ArrayList<Garantie>();
		if (getSlctdGarantie().isEmpty()) {
			L = getGarantieCvtr().getListGarantie();
		} else {
			L = getSlctdGarantie();
		}
		modelGarantie= new PieChartModel();
		/*getModelGarantie().setLegendCols(2);
		getModelGarantie().setLegendPosition("e");
		getModelGarantie().setTitle(
				"REPARTION DES PRIMES GARANTIES PAR GARANTIE");
		getModelGarantie().setShowDataLabels(true);*/
		for (Garantie r : L) {
			modelGarantie.set(r.getLibelleGarantie(), garPieChartData(r));
		}
	}

	public Number risqPieChartData(Risque risq) {
		BigDecimal X = BigDecimal.ZERO;
		for (Risque r : getSlctdRisques()) {
			if (r.getCodeRisque().equalsIgnoreCase(risq.getCodeRisque()))
				for (Garanties g : getGarantiesListSelectd()) {
					if (g.getRisque().getCodeRisque().equalsIgnoreCase(risq.getCodeRisque()))
						X = X.add(g.getPrime());
				}
		}

		return X;
	}

	public void loadModelRisq() {
		List<Risque> L = new ArrayList<Risque>();
		if (getSlctdRisques().isEmpty()) {
			L = getRisqCvtr().getRisqList();
		} else {
			L = getSlctdRisques();
		}
		modelRisque=new PieChartModel();
		/*getModelRisque().setLegendCols(2);
		getModelRisque().setLegendPosition("e");
		getModelRisque().setTitle("REPARTION DES PRIMES GARANTIES PAR RISQUE");
		getModelRisque().setShowDataLabels(true);*/
		for (Risque r : L) {
			modelRisque.set(r.getLibelleRisque(), risqPieChartData(r));
		}
	}
	public void callGraphStat(){
		  System.out.println("INSIDE callGraphStat////////////////////////////////////////");
		  Map<String,Object> options = new HashMap<String, Object>();
		  	options.put("modal", true);
			options.put("draggable", false);
			options.put("resizable", true);
			options.put("contentHeight", 900);
			options.put("contentWidth", 1300);
		  
		  RequestContext.getCurrentInstance().openDialog ("graphGarantie", options, null);
		  
		  //RequestContext.getCurrentInstance().execute("PF('Alert').show();");
		  System.out.println("AFTER graphGarantie////////////////////////////////////////");
		 }

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Risque> getSlctdRisques() {
		return slctdRisques;
	}

	public void setSlctdRisques(List<Risque> slctdRisques) {
		this.slctdRisques = slctdRisques;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

   public RisqCvtr getRisqCvtr() {
		return risqCvtr;
	}

	public void setRisqCvtr(RisqCvtr risqCvtr) {
		this.risqCvtr = risqCvtr;
	}

	public Boolean getOkDisblr() {
		if (getDateDebut() != null && getDateFin() != null
				&& getDateDebut().before(getDateFin()))
			okDisblr = false;
		return okDisblr;
	}

	public void setOkDisblr(Boolean okDisblr) {
		this.okDisblr = okDisblr;
	}

	public SelectItem[] getEtatQuitOptions() {

		SelectItem[] Options = new SelectItem[3];
		Options[0] = new SelectItem("", "Select");
		Options[1] = new SelectItem("solder", "Soldé");
		Options[2] = new SelectItem("non solder", "Non Soldé");

		return Options;
	}

	public AvenantDesign getAvenantDesign() {
		return avenantDesign;
	}

	public void setAvenantDesign(AvenantDesign avenantDesign) {
		this.avenantDesign = avenantDesign;
	}

	public List<Garantie> getSlctdGarantie() {
		return slctdGarantie;
	}

	public void setSlctdGarantie(List<Garantie> slctdGarantie) {
		this.slctdGarantie = slctdGarantie;
	}

	public List<GarantiesCategorie> getListGarantiesCategorie() {
		return listGarantiesCategorie;
	}

	public void setListGarantiesCategorie(
			List<GarantiesCategorie> listGarantiesCategorie) {
		this.listGarantiesCategorie = listGarantiesCategorie;
	}

	public List<Categorie> getSlctdCategorie() {
		return slctdCategorie;
	}

	public void setSlctdCategorie(List<Categorie> slctdCategorie) {
		this.slctdCategorie = slctdCategorie;
	}

	public GarantieCvtr getGarantieCvtr() {
		return garantieCvtr;
	}

	public void setGarantieCvtr(GarantieCvtr garantieCvtr) {
		this.garantieCvtr = garantieCvtr;
	}

	public CategorieCvtr getCategorieCvtr() {
		return categorieCvtr;
	}

	public void setCategorieCvtr(CategorieCvtr categorieCvtr) {
		this.categorieCvtr = categorieCvtr;
	}

	public List<GarantiesRisque> getListGarantiesRisque() {
		return listGarantiesRisque;
	}

	public void setListGarantiesRisque(List<GarantiesRisque> listGarantiesRisque) {
		this.listGarantiesRisque = listGarantiesRisque;
	}

	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}

	public List<Garanties> getGarantiesList() {
		return garantiesList;
	}

	public void setGarantiesList(List<Garanties> garantiesList) {
		this.garantiesList = garantiesList;
	}

	public List<Garanties> getGarantiesListSelectd() {
		return garantiesListSelectd;
	}

	public void setGarantiesListSelectd(List<Garanties> garantiesListSelectd) {
		this.garantiesListSelectd = garantiesListSelectd;
	}

	public List<Garanties> getFilterGarantiesListSelectd() {
		return filterGarantiesListSelectd;
	}

	public void setFilterGarantiesListSelectd(
			List<Garanties> filterGarantiesListSelectd) {
		this.filterGarantiesListSelectd = filterGarantiesListSelectd;
	}

	public Boolean getRenduChart() {
		return renduChart;
	}

	public void setRenduChart(Boolean renduChart) {
		this.renduChart = renduChart;
	}

	public PieChartModel getModelCategorie() {
		return modelCategorie;
	}

	public void setModelCategorie(PieChartModel modelCategorie) {
		this.modelCategorie = modelCategorie;
	}

	public PieChartModel getModelGarantie() {
		return modelGarantie;
	}

	public void setModelGarantie(PieChartModel modelGarantie) {
		this.modelGarantie = modelGarantie;
	}

	public PieChartModel getModelRisque() {
		return modelRisque;
	}

	public void setModelRisque(PieChartModel modelRisque) {
		this.modelRisque = modelRisque;
	}

}
