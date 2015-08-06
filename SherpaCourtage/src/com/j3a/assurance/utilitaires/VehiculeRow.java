package com.j3a.assurance.utilitaires;


//import com.j3a.sherpawebuser.dbEntityClasses.ConducteurHabituel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.j3a.assurance.model.Apporteur;
import com.j3a.assurance.model.Categorie;
import com.j3a.assurance.model.Conducteur;
import com.j3a.assurance.model.GarantieChoisie;
import com.j3a.assurance.model.GarantieGarantieChoisie;
import com.j3a.assurance.model.Permis;
import com.j3a.assurance.model.SousCatVehicule;
import com.j3a.assurance.model.Tarif;
import com.j3a.assurance.model.Vehicule;
import com.j3a.assurance.model.ZoneGeographique;

public class VehiculeRow {
	//AJOUT AU CONSTRUCTEUR PAR DEFAUT D'INSTRUCTION POUR INITIALISER LES DIFFERENTS CHAMPS DE L'OBJET AFIN D4EVITER LES NULLPOINTER EXCEPTION 
	// AUCHARGEMENT DE LA PAGE
	public VehiculeRow() {
		setConduHab(new Conducteur());
		getConduHab().setDureepermiscond((short)0);
		/*setConduHabPerm(new Permis());*/
		
		SousCatVehicule sv = new SousCatVehicule();
		sv.setCodeSousCatVehicule("");
		sv.setLibelleSousCatVehicule("");
		Tarif tarif = new Tarif();
		tarif.setCodeTarif("");
		tarif.setLibelleTarif("");
		sv.setTarif(tarif);
		Categorie cat = new Categorie();
		cat.setCodeCategorie("");
		cat.setLibelleCategorie("");
		sv.setCategorie(cat);
		setSouCatVehi(sv);
		Vehicule vehi = new Vehicule();
		vehi.setNbreCarte((short) 0);
		vehi.setNbrePlaceCab((short) 0);
		vehi.setNbrePlaceHorscab((short) 0);
		vehi.setNbreTransporte((short) 0);
		vehi.setPoidsVide(BigDecimal.ZERO);
		vehi.setPuissFisc(BigDecimal.ZERO);
		vehi.setPuissReelle(BigDecimal.ZERO);
		vehi.setValNeuf(BigDecimal.ZERO);
		vehi.setValVenale(BigDecimal.ZERO);
		vehi.setChargeUtile(BigDecimal.ZERO);
		vehi.setTypeTransporte("");
		vehi.setRemorque(false);
		setVehi(vehi);
	    ZoneGeographique zone= new ZoneGeographique();
	    zone.setCodeZoneGeo("zone1");
	    zone.setLibelleZoneGeo("Zone1");
		setZonGeo(zone);
		//setListGarantieparVehicule(new ListGarantieparVehicule());
		
		setCgDisblr(false);
		setGaDisblr(false);
		setSelectGaranties(new ArrayList<Garanties>());
		setListegaranties(new ArrayList<Garanties>());
		setListeVehicules(new ArrayList<VehiculeRow>());
		//setGarantieTable(new GarantieTable());
	}
/** 
 * cette classe permet de regrouper un vehicule avec sa carte grise et ces garanties associ�es
 * **/
	private Conducteur  conduHab = new Conducteur();
	private Permis conduHabPerm; 
	private Vehicule vehi;
	private SousCatVehicule souCatVehi;
	private ZoneGeographique zonGeo;
	private List<ListGarantieparVehicule> listGarantieparVehicule = new ArrayList<ListGarantieparVehicule>();
	private int numOrdr;
	private Boolean CgDisblr;
	private Boolean GaDisblr;
	private Boolean statutVehiculeBD = false;
	private List<Garanties> selectGaranties;
	//private GarantieTable garantieTable;
	private List<Garanties> listegaranties;
	private List<VehiculeRow> listeVehicules;
	private java.math.BigDecimal commissionApporteur = BigDecimal.ZERO;
	private java.math.BigDecimal tauxCommissionApporteur = BigDecimal.ZERO;
	private java.math.BigDecimal primeNette = BigDecimal.ZERO;
	private Apporteur apporteur = new Apporteur();
	private GarantieChoisie gcIa = new GarantieChoisie();
	private List<GarantieGarantieChoisie> ggca=new ArrayList<GarantieGarantieChoisie>();
	
	
	
	
	public Conducteur getConduHab() {
		return conduHab;
	}
	public void setConduHab(Conducteur conduHab) {
		this.conduHab = conduHab;
	}
	public Vehicule getVehi() {
		return vehi;
	}
	public void setVehi(Vehicule vehi) {
		this.vehi = vehi;
	}
	public SousCatVehicule getSouCatVehi() {
		return souCatVehi;
	}
	public void setSouCatVehi(SousCatVehicule souCatVehi) {
		this.souCatVehi = souCatVehi;
	}
	public ZoneGeographique getZonGeo() {
		return zonGeo;
	}
	public void setZonGeo(ZoneGeographique zonGeo) {
		this.zonGeo = zonGeo;
	}
	
	public int getNumOrdr() {
		return numOrdr;
	}
	public void setNumOrdr(int numOrdr) {
		this.numOrdr = numOrdr;
	}
	public Permis getConduHabPerm() {
		return conduHabPerm;
	}
	public void setConduHabPerm(Permis conduHabPerm) {
		this.conduHabPerm = conduHabPerm;
	}
	public Boolean getCgDisblr() {
		return CgDisblr;
	}
	public void setCgDisblr(Boolean cgDisblr) {
		CgDisblr = cgDisblr;
	}
	public Boolean getGaDisblr() {
		return GaDisblr;
	}
	public void setGaDisblr(Boolean gaDisblr) {
		GaDisblr = gaDisblr;
	}
	public List<Garanties> getSelectGaranties() {
		return selectGaranties;
	}
	public void setSelectGaranties(List<Garanties> selectGaranties) {
		this.selectGaranties = selectGaranties;
	}
	/*public GarantieTable getGarantieTable() {
		return garantieTable;
	}
	public void setGarantieTable(GarantieTable garantieTable) {
		this.garantieTable = garantieTable;
	}*/
	public List<Garanties> getListegaranties() {
		return listegaranties;
	}
	public void setListegaranties(List<Garanties> listegaranties) {
		this.listegaranties = listegaranties;
	}
	public List<ListGarantieparVehicule> getListGarantieparVehicule() {
		return listGarantieparVehicule;
	}
	public void setListGarantieparVehicule(
			List<ListGarantieparVehicule> listGarantieparVehicule) {
		this.listGarantieparVehicule = listGarantieparVehicule;
	}
	public List<VehiculeRow> getListeVehicules() {
		return listeVehicules;
	}
	public void setListeVehicules(List<VehiculeRow> listeVehicules) {
		this.listeVehicules = listeVehicules;
	}
	public Boolean getStatutVehiculeBD() {
		return statutVehiculeBD;
	}
	public void setStatutVehiculeBD(Boolean statutVehiculeBD) {
		this.statutVehiculeBD = statutVehiculeBD;
	}
	public java.math.BigDecimal getCommissionApporteur() {
		return commissionApporteur;
	}
	public void setCommissionApporteur(java.math.BigDecimal commissionApporteur) {
		this.commissionApporteur = commissionApporteur;
	}
	public java.math.BigDecimal getPrimeNette() {
		return primeNette;
	}
	public void setPrimeNette(java.math.BigDecimal primeNette) {
		this.primeNette = primeNette;
	}
	public java.math.BigDecimal getTauxCommissionApporteur() {
		return tauxCommissionApporteur;
	}
	public void setTauxCommissionApporteur(
			java.math.BigDecimal tauxCommissionApporteur) {
		this.tauxCommissionApporteur = tauxCommissionApporteur;
	}
	public Apporteur getApporteur() {
		return apporteur;
	}
	public void setApporteur(Apporteur apporteur) {
		this.apporteur = apporteur;
	}
	public GarantieChoisie getGcIa() {
		return gcIa;
	}
	public void setGcIa(GarantieChoisie gcIa) {
		this.gcIa = gcIa;
	}
	public List<GarantieGarantieChoisie> getGgca() {
		return ggca;
	}
	public void setGgca(List<GarantieGarantieChoisie> ggca) {
		this.ggca = ggca;
	}
		
	
}
