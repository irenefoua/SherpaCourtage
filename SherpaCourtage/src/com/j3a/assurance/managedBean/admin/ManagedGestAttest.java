package com.j3a.assurance.managedBean.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Set;




//import com.j3a.assurance.model.Appartient;
//import com.j3a.assurance.model.AppartientPK;
import com.j3a.assurance.model.Attestation;
import com.j3a.assurance.model.PointVente;
import com.j3a.assurance.model.SousCatVehicule;
import com.j3a.assurance.model.Stock;
import com.j3a.assurance.objetService.ObjectService;
import com.j3a.assurance.utilitaires.ManagedStock;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component
@Transactional
public class ManagedGestAttest {
	// Spring Apporteur Service is injected...
	@Autowired
	ObjectService objectService;
	private SessionFactory sessionFactory;
	
	private Attestation categorie;
	private Attestation SelectedCateg;
	private List<Attestation> listcateg;
	private Boolean editMode;
	private String testX;
	private String catego;
	private Attestation newCateg;
	private PointVente slctdPtVte;
	
	private Boolean showActions = false;
	private Integer qteStk=0;
	private Integer qteRestitue=0;
	private String msgCreateStk;
	List<Attestation> enstock = new ArrayList<Attestation>();
	List<Attestation> enptVte = new ArrayList<Attestation>();
	List<Attestation> alloue = new ArrayList<Attestation>();
	Stock Stk;
	//List<Appartient> appartientList = new ArrayList<Appartient>();
	private Integer qtTotIni;
	private Integer qtTotAll;
	private Integer qtTotRst;
	private List<Stock> stockList ;
	private Stock slctdStock ;
	private SelectItem[] stkStateOptions;
	private SelectItem[] AtestEtaOptions;
	private int nbMtipl;
	
	//List<Appartient> appartientDel = new ArrayList<Appartient>();
	List<Attestation> AttestRestitue = new ArrayList<Attestation>();
	List<Stock> stockMAJ = new ArrayList<Stock>();
	  
	Logger logs=Logger.getLogger(ManagedGestAttest.class);
	
	 

	public ManagedGestAttest() {
		// TODO Auto-generated constructor stub
	}

public void save() throws IOException {

		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		Boolean existSuch = false;
		Boolean savedOK = false;
		String CltId = null;

		// (SousCatVehicule) getObjectService().getById("sous_cat_vehicule",
		// "CODE_SOUS_CAT_VAHICULE", SelectedCateg.getId(),
		// SousCatVehicule.class)

		if (!FacesContext.getCurrentInstance().isValidationFailed()) {
			savedOK = true;
			if (!(catego.equalsIgnoreCase(SelectedCateg.getCodeAttestation()))) {
				if ((Attestation) getObjectService().getById(
						"attestation", "CODE_ATTESTATION",
						SelectedCateg.getCodeAttestation(),
						Attestation.class) != null) {
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Une Attestation de meme Id existe deja", " ");
					CltId = "Msglib";
					existSuch = Boolean.TRUE;
					SelectedCateg.setCodeAttestation(catego); 
				} else {
					// getSelectedCateg().setPathimgcateg("/images/Categories/"+getSelectedCateg().getLibellecateg());
					getObjectService().updateObject(getSelectedCateg());
					// this.prdTblRows=produitFacade.findAll();
					msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Attestation Successfully Saved", " ");
				}
			} else {

				msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Attestation not updated", " ");
			}

		}
		FacesContext.getCurrentInstance().addMessage(CltId, msg);
		context.addCallbackParam("savedOK", savedOK);
		context.addCallbackParam("existSuch", existSuch);
	}

	public void ligneSelected(org.primefaces.event.SelectEvent event) {
		catego = "";
		catego = SelectedCateg.getCodeAttestation();
		RequestContext.getCurrentInstance().execute("rowAction.show();");
		// this.listcateg=categorieFacade.findAll();
	}

	public void delete() {
		Boolean c = SelectedCateg.getStock()==null && SelectedCateg.getVehicule()==null; 
		//Boolean c = getObjectService().getVehiByCat(SelectedCateg).isEmpty();
		// Boolean d = sltdprdTblRow.getProduit().getStockList() != null;
		Boolean deleted = Boolean.FALSE;
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;

		if (c) {
			getObjectService().deleteObject(getSelectedCateg());
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Attestation Successfully deleted", "");
		} else {
			deleted = Boolean.TRUE;
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Cette Attestation ne peut etre supprimmée", " ");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("deleted", deleted);
	}

	public void addNew() {
		System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyzzzzzzzzzzzzzzzzzzzzzzzz");
		getObjectService().addObject(newCateg);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Attestation Successfully added", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		RequestContext.getCurrentInstance().execute("AddcategDlg.hide();");
	}

	public void preAddNw() {
		String A = getCodeTable("Attes", 5, 4, "attestation", "CODE_ATTESTATION");
		Attestation atest = new Attestation(A);
		atest.setEtat("enstock");
		setNewCateg(atest);
	}
	
	public void addMtpl(){
		String cdAttes=getCodeTable("Attes", 5, 4, "attestation", "CODE_ATTESTATION");
		Integer x = Integer.valueOf(cdAttes.substring(5,9)) ;
		x=x-1;
		//System.out.println(cdAttes);
		int a;
		for(a=1;a<=nbMtipl;a++){
				String zero="0000";
				x++;
				System.out.println(x);
				String vS=x.toString();
				char[] z = zero.toCharArray();
				int j = vS.length()-1;
				int i;
				for(i=zero.length()-1;i>(zero.length()-1)-vS.length();i--){
					//System.out.println("--"+i+"--"+vS.charAt(j)+"--"+z[i]);
					z[i]=vS.charAt(j);j--;	
				}
				String b = "Attes"+String.valueOf(z);
				Attestation At = new Attestation(b);
				At.setEtat("enstock");
				getObjectService().addObject(At);
		}
	}

	public void onToggle(ToggleEvent event) {
		Visibility visibility = event.getVisibility();
		FacesMessage msg = new FacesMessage();
		msg.setSummary("Fieldset " + event.getComponent().getClientId()
				+ " toggled");
		msg.setDetail("Visibility: " + visibility);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void ptVteSlct() {
		System.out.println("testneenenenenenenennanana"+getSlctdPtVte().getCodePointVente());
		if (getSlctdPtVte() != null) {
			showActions = true;
			// RequestContext.getCurrentInstance().execute("qtyWarn.show();");
			System.out.println("test!!!!!");
		} else {
			showActions = false;
		}
	}  

	public void createStk() {
		System.out.println("test0");
		// List<Attestation> enstock = new ArrayList<Attestation>();
		// List<Attestation> enptVte = new ArrayList<Attestation>();
		// List<Attestation> alloue = new ArrayList<Attestation>();
		Attestation a;
		List<Attestation> A = new ArrayList<Attestation>();
		for (Object c : getObjectService().getObjects("Attestation")) {
			a = (Attestation) c;
			A.add(a);
			if (a.getEtat().equalsIgnoreCase("enstock")) {
				enstock.add(a);
			}
			if (a.getEtat().equalsIgnoreCase("enptVte")) {
				enptVte.add(a);
			}
			if (a.getEtat().equalsIgnoreCase("alloue")) {
				alloue.add(a);
			}
			//System.out.println("test1");
		}
		if (qteStk <= 0) {
			msgCreateStk = "La quantité du stock nulle ou negative. Impossible de créer le stock!!";
			RequestContext.getCurrentInstance().execute("qtyWarn.show();");
		}else{
		if (enstock.size() == 0) {
			System.out.println("test2");
			msgCreateStk = "Aucune Attestation disponible en stock veuilez enregistrer de nouvelles attestations";
			RequestContext.getCurrentInstance().execute("qtyWarn.show();");
			System.out.println("test2.1");
		} else {
			if (enstock.size() < qteStk) {
				System.out.println("test3");
				msgCreateStk = "Le Stock d'Attestation disponible est insuffisant. Veuilez enregistrer de nouvelles attestations ou dimunier la quantité du stock à Créer";
				RequestContext.getCurrentInstance().execute("qtyWarn.show();");
				System.out.println("test3.1");
			} else {
				System.out.println("test4");
				Stk = new Stock(getCodeTable("Stock", 5, 4, "stock", "ID_STOCK"), slctdPtVte);
				Stk.setSituation("enattente");
				Stk.setDatestock(Calendar.getInstance().getTime());
				//Stk.setStkalloue(new Long(0));
				Stk.setStkinit(new Long(qteStk));
				Stk.setStkrst(new Long(qteStk));
				int i;
				// List<Appartient> appartientList = new
				// ArrayList<Appartient>();
				for (i = 0; i < qteStk; i++) {
					//System.out.println("test5");
					Attestation At = enstock.get(i);
					At.setEtat("enptVte");
					At.setStock(Stk);
//					AppartientPK ApPK = new AppartientPK(At, Stk,
//							slctdPtVte.getId());
//					Appartient Ap = new Appartient(ApPK);
//					appartientList.add(Ap);

				}
				RequestContext.getCurrentInstance().execute("cfrmStk.show();");
			}
		}
		}

	}

	public void saveStk() {
		getObjectService().addObject(Stk);
		for (Attestation c : enstock) {
			if (c.getEtat().equalsIgnoreCase("enptVte"))
				getObjectService().updateObject(c);
		}
		
		/*for (Appartient c : appartientList) {
			getObjectService().addObject(c);
		}*/
		cancelStk();
	}

	public void cancelStk() {
		enstock.clear();
		enptVte.clear();
		alloue.clear();
		Stk = null;
		//appartientList.clear();
		qteStk=0;    
	}  

	public void CheckExist() {
		System.out.println("-------------->test methode fonctionnelle");
		Boolean exist = false;
		if (getSelectedCateg() != null) {
			exist = true;
		} else {
			exist = false;
			System.out.println("-------------->selection nulle");
		}
		RequestContext.getCurrentInstance().addCallbackParam("exist", exist);
	}
	
	public void restituerAttest(){
		//Verification de la quantité à retirer : si elle n'est pas supérieure à la quatité dispo
		if(qteRestitue>getQtTotRst()){
			msgCreateStk = "La quantité d'attestation à retirer est supérieure à la quantité disponible";
			RequestContext.getCurrentInstance().execute("qtyWarn.show();");
		}else{
			//Verification de la quantité à retirer : si elle n'est pas negative ou nulle
			if(qteRestitue<=0){
				msgCreateStk = "La restitution est impossible avec une pareille quantité";
				RequestContext.getCurrentInstance().execute("qtyWarn.show();");
			}else{
				//Retriving la list des stock en cours ou en attente de ce point de vente
				List<Stock> A = new ArrayList<Stock>();
				//Stock stkEncrs=new Stock();
						for (Object c : getObjectService().getObjects("Stock")) {
							if (((Stock) c).getPointVente().getCodePointVente().equalsIgnoreCase(slctdPtVte.getCodePointVente())) {
								
								if (((Stock) c).getSituation().equalsIgnoreCase("encours") || ((Stock) c).getSituation().equalsIgnoreCase("enattente")  ) {
								A.add((Stock) c);
								}
							}					
						} 
						System.out.println("-------------->Taille de la liste des Stocks avant d'ordonner"+A.size());
						//Mise en ordre de la liste des stock obtenue en fction de leur ancienneté (le plus ancien en tête de liste dc traité le premier)
						  Object[] stkEnatt =  A.toArray();
						  Arrays.sort(stkEnatt, new java.util.Comparator(){ 
						       public int compare(Object o1, Object o2){ 
						           return compareStk(o1,o2); 
						          }//compare 
						        } )  ;
						  System.out.println("-------------->Taille de la liste des Stocks après ordonmt"+stkEnatt.length);
						  //Creation d'une liste vide d'objets de type 
						  //ManagedStock(Stock-StkCtrib(Qté d'attestation à y retirer)-NvlEtat(Etat du Stock après retrait des attestations))
						  List<ManagedStock> B = new ArrayList<ManagedStock>();
						  int i;
						  //int stkContrib=0;
						  long X = qteRestitue;
						  //Charger la liste des ManagedStock à partir du traitement de la liste ordonnée des stocks 
						  //Il s'agit ici de detecter les stocks touchés, leur contributions en nbre d'attestations 
						  //et le nouvel Etat dans lequel ils seront. Tout ce traitement étant ordonné selon l'acienneté des stocks
						  for (i = 0; i <stkEnatt.length ; i++) {
							  System.out.println(((Stock)stkEnatt[i]).getIdStock());
							  if(X > ((Stock)stkEnatt[i]).getStkrst()){
								  X=X-((Stock)stkEnatt[i]).getStkrst();
								  ManagedStock x = new ManagedStock(((Stock)stkEnatt[i]), ((Stock)stkEnatt[i]).getStkrst(), "epuise");
								  B.add(x);
								  System.out.println("Aretirer>StkRestant---epuisé");
								  continue;
							  }
							  if(X < ((Stock)stkEnatt[i]).getStkrst()){
								  //X=X-stkEnatt[i].getStkrst();
								  ManagedStock x = new ManagedStock(((Stock)stkEnatt[i]), X, "encours");
								  B.add(x);
								  System.out.println("Aretirer<StkRestant---encours");
								  break;
							  }
							  if(X==((Stock)stkEnatt[i]).getStkrst()){
								  //X=X-stkEnatt[i].getStkrst();
								  ManagedStock x = new ManagedStock(((Stock)stkEnatt[i]), ((Stock)stkEnatt[i]).getStkrst(), "epuise");
								  B.add(x);
								  System.out.println("Aretirer=StkRestant---epuisé");
								  break;
							  }  
						  }
//						  System.out.println(B.get(0).getStock().getId());
//						  System.out.println(B.get(1).getStock().getId());
						  //Maintenant qu'ont la liste des stocks touchés, on la parcourt; pour chaque stock
						  //on recense X Appartient dont les attestations st à l'Etat "EnptVte" X étant exactement la contribution
						  //du stock dans l'opération on recense également les attestations concernées, et les stocks eux mêmes après
						  //les avoir mis à jour
						  for (ManagedStock c : B) {
							  System.out.println("Stock Traité :"+c.getStock().getIdStock());
							  int j = 0;
							  
							  //c.getStock().getAppartients().size();
							  Stock s = c.getStock();
							  //Hibernate.initialize(s);
							  
							   
							  Set<Attestation> E = s.getAttestations();
							  
								for(Attestation d:E){ 
									System.out.println("Getsize sans petard");
									if(d.getEtat().equalsIgnoreCase("enptVte") && j<c.getStkCtrib()){
										//appartientDel.add(d);
										d.setStock(null);
										AttestRestitue.add(d);
										j++;
									}
								} 
							  c.getStock().setStkinit(c.getStock().getStkinit()-c.getStkCtrib());
							  c.getStock().setStkrst(c.getStock().getStkrst()-c.getStkCtrib());
							  c.getStock().setSituation(c.getNvlEtat());
							  stockMAJ.add(c.getStock());
						  }
						  
						  RequestContext.getCurrentInstance().execute("cfrmRstitu.show();");	  
				}		  

		}
		
						  
	}
	
	
	public void restituerAttestOK(){
		//Persister en BD les informations à savoir:
		// *MAJ & REC des Attestions concernées :enptVte->enstock
		// *Suppression des Appartient concernés
		// *REC des Stock si Stkinit()!=0 sinon DEL
		for (Attestation c : AttestRestitue) {
			c.setEtat("enstock");
			getObjectService().updateObject(c);
		}
//		for (Appartient c : appartientDel) {
//			getObjectService().deleteObject(c);
//		}
		for (Stock c : stockMAJ) {
			if(c.getStkinit()==0){
				getObjectService().deleteObject(c);
			}else{
				getObjectService().updateObject(c);
			}
		}
		//?????A faire :
		//Vu qu'il s'agit d'une restitution automatique présenter dans un composant Dataxxx au sein d'un MSGBox les attestations restituées
	}
	
	public void restituerAttestCancel(){
		AttestRestitue.clear();
		//appartientDel.clear();
		stockMAJ.clear();
		qteRestitue=0;
	}
	
	// la fonction qui compare des stock 
	  public int compareStk(Object o1, Object o2){ 
	    // doit rendre 
	    // -1 si o1 "plus petit que" o2 
	    // 0 si o1 "égal à" o2 
	    // +1 si o1 "plus grand que" o2 
	    Stock s1=(Stock)o1; 
	    Stock s2=(Stock)o2;  
	    long d1=s1.getDatestock().getTime(); 
	    long d2=s2.getDatestock().getTime(); 
	    int a=0;
	    if(d1<d2) a=(-1); 
	      else if (d1==d2){
	    	  if(s1.getSituation().equalsIgnoreCase("encours"))
	    	  a=(-1); 
	    	  if(s2.getSituation().equalsIgnoreCase("encours"))
	    		  a=(+1);
	    	  if(s1.getSituation().equalsIgnoreCase("enattente") && s2.getSituation().equalsIgnoreCase("enattente"))
	    		  a=(0);
	      }
	        else a=1;
		return a; 
	  }//compare1 
	
	private SelectItem[] createstkStateOptions() {  
    	 
		SelectItem[]  Options = new SelectItem[4];  
	    	Options[0] = new SelectItem("", "Select");
	    	Options[1] = new SelectItem("enattente", "En attente");
	    	Options[2] = new SelectItem("encours", "En cours");
	    	Options[3] = new SelectItem("epuise", "Epuise");
	    	
	    	return Options; 
	    	
	    }  
	
	private SelectItem[] createAtesEtaOptions() {  
   	 
		SelectItem[]  Options = new SelectItem[4];  
	    	Options[0] = new SelectItem("", "Select");
	    	Options[1] = new SelectItem("enstock", "En Stock");
	    	Options[2] = new SelectItem("alloue", "Allouée");
	    	Options[3] = new SelectItem("enPtVte", "En Point de Vente");
	    	
	    	return Options; 
	    	
	    }  
	
	
	
	
	
	
//	@Transactional
//	private Set<Appartient> getAppStk(Stock s){
//		return s.getAppartients();
//	}
//	
	
	
	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}

	public Attestation getCategorie() {
		return categorie;
	}

	public void setCategorie(Attestation categorie) {
		this.categorie = categorie;
	}

	public Attestation getSelectedCateg() {
		return SelectedCateg;
	}

	public void setSelectedCateg(Attestation selectedCateg) {
		SelectedCateg = selectedCateg;
	}

	public List<Attestation> getListcateg() {
		List<Attestation> A = new ArrayList<Attestation>();
		for (Object c : getObjectService().getObjects("Attestation")) {
			A.add((Attestation) c);
		}

		return A;
	}

	public void setListcateg(List<Attestation> listcateg) {
		this.listcateg = listcateg;
	}

	public Boolean getEditMode() {
		return editMode;
	}

	public void setEditMode(Boolean editMode) {
		this.editMode = editMode;
	}

	public String getTestX() {
		return testX;
	}

	public void setTestX(String testX) {
		this.testX = testX;
	}

	public String getCatego() {
		return catego;
	}

	public void setCatego(String catego) {
		this.catego = catego;
	}

	public Attestation getNewCateg() {
		return newCateg;
	}

	public void setNewCateg(Attestation newCateg) {
		this.newCateg = newCateg;
	}

	public String getIdXXX(String X) {
		return X + String.valueOf(Calendar.getInstance().getTimeInMillis());
	}

	public PointVente getSlctdPtVte() {
		return slctdPtVte;
	}

	public void setSlctdPtVte(PointVente slctdPtVte) {
		this.slctdPtVte = slctdPtVte;
	}

	public Boolean getShowActions() {
		return showActions;
	}

	public void setShowActions(Boolean showActions) {
		this.showActions = showActions;
	}

	public Integer getQteStk() {
		return qteStk;
	}

	public void setQteStk(Integer qteStk) {
		this.qteStk = qteStk;
	}

	public String getMsgCreateStk() {
		return msgCreateStk;
	}

	public void setMsgCreateStk(String msgCreateStk) {
		this.msgCreateStk = msgCreateStk;
	}

	public Integer getQtTotIni() {
		qtTotIni=0;
		//List<Stock> A = new ArrayList<Stock>();
		for (Object c : getObjectService().getObjects("Stock")) {
			if (((Stock) c).getPointVente().getCodePointVente().equalsIgnoreCase(slctdPtVte.getCodePointVente())) {
				qtTotIni=qtTotIni+((Stock) c).getStkinit().intValue();
			}
			
		}
		return qtTotIni;
	}

	public void setQtTotIni(Integer qtTotIni) {
		this.qtTotIni = qtTotIni;
	}

	public Integer getQtTotAll() {
		qtTotAll=0;
		//List<Stock> A = new ArrayList<Stock>();
		for (Object c : getObjectService().getObjects("Stock")) {
			if (((Stock) c).getPointVente().getCodePointVente().equalsIgnoreCase(slctdPtVte.getCodePointVente())) {
				Integer init = ((Stock) c).getStkinit().intValue();
				Integer rest = ((Stock) c).getStkrst().intValue();
				qtTotAll=qtTotAll+(init-rest);
			}
			
		}
		return qtTotAll;
	}

	public void setQtTotAll(Integer qtTotAll) {
		this.qtTotAll = qtTotAll;
	}

	public Integer getQtTotRst() {
		qtTotRst=0;
		//List<Stock> A = new ArrayList<Stock>();
		for (Object c : getObjectService().getObjects("Stock")) {
			if (((Stock) c).getPointVente().getCodePointVente().equalsIgnoreCase(slctdPtVte.getCodePointVente())) {
				qtTotRst=qtTotRst+((Stock) c).getStkrst().intValue();
			}
			
		}
		return qtTotRst;
	}

	public void setQtTotRst(Integer qtTotRst) {
		this.qtTotRst = qtTotRst;
	}

	public List<Stock> getStockList() {
		
		List<Stock> A = new ArrayList<Stock>();
		for (Object c : getObjectService().getObjects("Stock")) {
			if (((Stock) c).getPointVente().getCodePointVente().equalsIgnoreCase(slctdPtVte.getCodePointVente())) {
				A.add((Stock) c);
			}
			
		}
		System.out.println(A.size());
		return A;
	}

	public void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
	}

	public Stock getSlctdStock() {
		return slctdStock;
	}

	public void setSlctdStock(Stock slctdStock) {
		this.slctdStock = slctdStock;
	}

	public  SelectItem[] getStkStateOptions() {
		return createstkStateOptions();
	}

	public Integer getQteRestitue() {
		return qteRestitue;
	}

	public void setQteRestitue(Integer qteRestitue) {
		this.qteRestitue = qteRestitue;
	}
	
	public String getCodeTable(String pseudo ,int taillCar,int taillChifr, String nomTable, String nomCOL){
		
		//Session sess = getSessionFactory().openSession();
				String zero="000000000";
				zero=zero.substring(0, taillChifr);
				while(pseudo.length()<taillCar){
					pseudo=pseudo+"0";
				}
				if(pseudo.length()>taillCar){
					pseudo=pseudo.substring(0, taillCar-1);
				}
				
				String query ="SELECT MAX(CAST(SUBSTRING("+nomCOL+" FROM "+(taillCar+1)+" FOR "+(taillChifr)+") AS UNSIGNED )) AS NUMBER FROM "+nomTable+"" ;
				Integer v=null;
				try {
					v = (Integer) getSessionFactory().getCurrentSession().createSQLQuery(query).addScalar("NUMBER", StandardBasicTypes.INTEGER).uniqueResult();
				} catch (HibernateException e) {
					logs.info(">>>>/ "+e.getMessage());
					e.printStackTrace();
				}
				//sess.close();
				if(v==null){
					zero=zero.substring(0, taillChifr-1)+"1";
					return pseudo+zero;
				}else{
				v++;
				String vS = v.toString();
				int j = vS.length()-1;
				int i;
				char[] z = zero.toCharArray();
				for(i=zero.length()-1;i>(zero.length()-1)-vS.length();i--){
					//System.out.println("--"+i+"--"+vS.charAt(j)+"--"+z[i]);
					z[i]=vS.charAt(j);j--;	
				}
				zero=String.valueOf(z);
				//System.out.println(zero);
				return pseudo+zero;
				}

		
	
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SelectItem[] getAtestEtaOptions() {
		return createAtesEtaOptions() ;
	}

	public void setAtestEtaOptions(SelectItem[] atestEtaOptions) {
		AtestEtaOptions = atestEtaOptions;
	}

	public int getNbMtipl() {
		return nbMtipl;
	}

	public void setNbMtipl(int nbMtipl) {
		this.nbMtipl = nbMtipl;
	}
	
	

}
