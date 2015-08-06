package com.j3a.assurance.converter;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.Categorie;
import com.j3a.assurance.model.RcTarif1;
import com.j3a.assurance.model.RcTarif10;
import com.j3a.assurance.model.RcTarif12;
import com.j3a.assurance.model.RcTarif2;
import com.j3a.assurance.model.RcTarif3;
import com.j3a.assurance.model.RcTarif5;
import com.j3a.assurance.model.RcTarif6;
import com.j3a.assurance.model.RcTarif7;
import com.j3a.assurance.model.RcTarif8;
import com.j3a.assurance.model.RcTarif9;
import com.j3a.assurance.model.SousCatVehicule;
import com.j3a.assurance.model.Tarif;
import com.j3a.assurance.objetService.ObjectService;


@Component
public class ScatVehiCvtr implements Converter {

	public ScatVehiCvtr() {
		// TODO Auto-generated constructor stub
	}
	//Spring Apporteur Service is injected...
	@Autowired
	 ObjectService objectService;
	 
	 public List<SousCatVehicule> CatVehiList=new ArrayList<SousCatVehicule>(); 
	 
	 Logger logs=Logger.getLogger(ScatVehiCvtr.class);
	

	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {  
		/*System.out.print("Avant lapel");
		getCatVehiList();
		System.out.print("Apres lapel");*/
	        if (submittedValue.trim().equals("")) {  
	            return null;  
	        } else {  
	            try {
	            	String X = submittedValue;
	                for (SousCatVehicule c : getCatVehiList()) {  
	                    if (c.getLibelleSousCatVehicule().equalsIgnoreCase(X)) {  
	                        return c;  
	                    }  
	                }  
	            } catch(Exception exception) {  
	            	logs.info(">>>>/ "+exception.getMessage());
	            	exception.printStackTrace();
	                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid SousCategorie Vehicule"));  
	            }  
	        }  
	  
	        return null;  
	    }  
	  
	   public String getAsString(FacesContext facesContext, UIComponent component, Object value) {  
	        if (value == null || value.equals("")) {  
	            return "";  
	        } else {  
	            return ((SousCatVehicule) value).getLibelleSousCatVehicule();  
	        }  
	    }   
	 
	 
	 
	public ObjectService getObjectService() {
		return objectService;
	}
	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}
	/*public List<SousCatVehicule> getsCatVehiList() {
		List<SousCatVehicule> A = new ArrayList<SousCatVehicule>();
		for (Object c : getObjectService().getObjects("SousCatVehicule")) {  
			A.add((SousCatVehicule) c);  
            } 
		
		return A;
	}
	public void setsCatVehiList(List<SousCatVehicule> sCatVehiList) {
		this.sCatVehiList = sCatVehiList;
	}
*/
	 public List<SousCatVehicule> getCatVehiList() {
			if(CatVehiList.isEmpty()){
				Tarif tarif1  = new Tarif();
				tarif1.setCodeTarif("Tarif1");
				RcTarif1 rc1  = new RcTarif1();
				tarif1.setRcTarif1(rc1);
				
				rc1.setCodeRcTarif1("RcTarif1");
				SousCatVehicule A = new SousCatVehicule("SCAT1");
				A.setLibelleSousCatVehicule("Vehicule de Tourisme");
				Categorie catA = new Categorie();
				catA.setCodeCategorie("1");
				catA.setLibelleCategorie("Promenade et Affaires");
				A.setCategorie(catA);
				A.setTarif_1("TARIF1");
				A.setTarif(tarif1);
				CatVehiList.add(A);
				
				
				Tarif tarif12  = new Tarif();
				tarif12.setCodeTarif("Tarif12");
				RcTarif12 rc12  = new RcTarif12();
				tarif12.setRcTarif12(rc12);
				rc12.setCodeRcTarif12("RcTarif12");
				
				SousCatVehicule B = new SousCatVehicule("SCAT12");
				B.setLibelleSousCatVehicule("Vehicule Professionnel");
				B.setCategorie(catA);
				B.setTarif_1("TARIF12");
				B.setTarif(tarif12);
				CatVehiList.add(B);
				
				
				Tarif tarif2  = new Tarif();
				tarif2.setCodeTarif("Tarif2");
				RcTarif2 rc2  = new RcTarif2();
				tarif2.setRcTarif2(rc2);
				rc2.setCodeRcTarif2("RcTarif2");
				
				SousCatVehicule C = new SousCatVehicule("SCAT2");
				C.setLibelleSousCatVehicule("Transport pour propre compte");
				Categorie catC = new Categorie();
				catC.setCodeCategorie("2");
				catC.setLibelleCategorie("Transport pour propre compte");
				C.setCategorie(catC);
				C.setTarif_1("TARIF2");
				C.setTarif(tarif2);
				CatVehiList.add(C);
				
				
				Tarif tarif3  = new Tarif();
				tarif3.setCodeTarif("Tarif3");
				RcTarif3 rc3  = new RcTarif3();
				tarif3.setRcTarif3(rc3);
				rc3.setCodeRcTarif3("RcTarif3");
				
				SousCatVehicule D = new SousCatVehicule("SCAT3");
				D.setLibelleSousCatVehicule("Transport Public de Marchandises");
				Categorie catD = new Categorie();
				catD.setCodeCategorie("3");
				catD.setLibelleCategorie("Transport Public de Marchandises");
				D.setCategorie(catD);
				D.setTarif_1("TARIF3");
				D.setTarif(tarif3);
				CatVehiList.add(D);
				
				/*SousCatVehicule E = new SousCatVehicule("SCAT4");
				E.setLibelleSousCatVehicule("Transport Public de Voyageurs");
				Categorie catE = new Categorie();
				catE.setId("4");
				catE.setLibelleCategorie("Transport Public de Voyageurs");
				E.setCodeCategorie(catE);
				E.setTarif("TARIF4");
				CatVehiList.add(E);*/
				
				
				Tarif tarif5  = new Tarif();
				tarif5.setCodeTarif("Tarif5");
				RcTarif5 rc5  = new RcTarif5();
				tarif5.setRcTarif5(rc5);
				rc5.setCodeRcTarif5("RcTarif5");
				
				SousCatVehicule F = new SousCatVehicule("SCAT5");
				F.setLibelleSousCatVehicule("Vehicules Motorises 2 OU 3 Roues");
				Categorie catF = new Categorie();
				catF.setCodeCategorie("5");
				catF.setLibelleCategorie("Vehicules Motorises 2 OU 3 Roues");
				F.setCategorie(catF);
				F.setTarif_1("TARIF5");
				F.setTarif(tarif5);
				CatVehiList.add(F);
				
				
				Tarif tarif6  = new Tarif();
				tarif6.setCodeTarif("Tarif6");
				RcTarif6 rc6  = new RcTarif6();
				tarif6.setRcTarif6(rc6);
				rc6.setCodeRcTarif6("RcTarif6");
				
				SousCatVehicule G = new SousCatVehicule("SCAT6");
				G.setLibelleSousCatVehicule("Garagistes");
				Categorie catG = new Categorie();
				catG.setCodeCategorie("6");
				catG.setLibelleCategorie("Garagistes");
				G.setCategorie(catG);
				G.setTarif_1("TARIF6");
				G.setTarif(tarif6);
				CatVehiList.add(G);
				
				
				Tarif tarif7  = new Tarif();
				tarif7.setCodeTarif("Tarif7");
				RcTarif7 rc7  = new RcTarif7();
				tarif7.setRcTarif7(rc7);
				rc7.setCodeRcTarif7("RcTarif7");
				
				SousCatVehicule H = new SousCatVehicule("SCAT7");
				H.setLibelleSousCatVehicule("Auto Ecole");
				Categorie catH = new Categorie();
				catH.setCodeCategorie("7");
				catH.setLibelleCategorie("Auto Ecole");
				H.setCategorie(catH);
				H.setTarif_1("TARIF7");
				H.setTarif(tarif7);
				CatVehiList.add(H);
				
				
				Tarif tarif8  = new Tarif();
				tarif8.setCodeTarif("Tarif8");
				RcTarif8 rc8  = new RcTarif8();
				tarif8.setRcTarif8(rc8);
				rc8.setCodeRcTarif8("RcTarif8");
				
				SousCatVehicule I = new SousCatVehicule("SCAT8");
				I.setLibelleSousCatVehicule("Véhicule de Location");
				Categorie catI = new Categorie();
				catI.setCodeCategorie("8");
				catI.setLibelleCategorie("Véhicule de Location");
				I.setCategorie(catI);
				I.setTarif_1("TARIF8");
				I.setTarif(tarif8);
				CatVehiList.add(I);
				
				
				Tarif tarif9  = new Tarif();
				tarif9.setCodeTarif("Tarif9");
				RcTarif9 rc9  = new RcTarif9();
				tarif9.setRcTarif9(rc9);
				rc9.setCodeRcTarif9("RcTarif9");
				
				SousCatVehicule J = new SousCatVehicule("SCAT9");
				J.setLibelleSousCatVehicule("Engins de Chantier");
				Categorie catJ = new Categorie();
				catJ.setCodeCategorie("9");
				catJ.setLibelleCategorie("Engins de Chantier");
				J.setCategorie(catJ);
				J.setTarif_1("TARIF9");
				J.setTarif(tarif9);
				CatVehiList.add(J);
				
				
				Tarif tarif10  = new Tarif();
				tarif10.setCodeTarif("Tarif10");
				RcTarif10 rc10  = new RcTarif10();
				tarif10.setRcTarif10(rc10);
				rc10.setCodeRcTarif10("RcTarif10");
				
				SousCatVehicule K = new SousCatVehicule("SCAT10");
				K.setLibelleSousCatVehicule("Véhicule spéciaux");
				Categorie catK = new Categorie();
				catK.setCodeCategorie("10");
				catK.setLibelleCategorie("Véhicule spéciaux");
				K.setCategorie(catK);
				K.setTarif_1("TARIF10");
				K.setTarif(tarif10);
				CatVehiList.add(K);
			}
			return CatVehiList;
			}

		public void setCatVehiList(List<SousCatVehicule> catVehiList) {
			CatVehiList = catVehiList;
		}
	 
	 
}
