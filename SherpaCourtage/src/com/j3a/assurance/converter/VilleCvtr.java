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

import com.j3a.assurance.model.Ville;
import com.j3a.assurance.objetService.ObjectService;

@Component
@javax.persistence.Converter
public class VilleCvtr implements Converter {

	public VilleCvtr() {
		// TODO Auto-generated constructor stub
	}

	//Spring Apporteur Service is injected...
		 @Autowired
		 ObjectService objectService;
		 
 public List<Ville> villeList; 
		 
		 Logger logs=Logger.getLogger(VilleCvtr.class);
		 
		 public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {  
		        if (submittedValue.trim().equals("")) {  
		            return null;  
		        } else {  
		            try {  
		            	String X = submittedValue;  
		  
		                for (Ville c : getVilleList()) {  
		                    if (c.getCodeVille().equalsIgnoreCase(X)) {  
		                        return c;  
		                    }  
		                }  
		  
		            } catch(Exception exception) {  
		            	logs.info(">>>>/ "+exception.getMessage());
		                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid SousCategorie Vehicule"));  
		            }  
		        }  
		  
		        return null;  
		    }  
		  
		   public String getAsString(FacesContext facesContext, UIComponent component, Object value) {  
		        if (value == null || value.equals("")) {  
		            return "";  
		        } else {  
		            return ((Ville) value).getCodeVille();  
		        }  
		    }

		public ObjectService getObjectService() {
			return objectService;
		}

		public void setObjectService(ObjectService objectService) {
			this.objectService = objectService;
		}

		public List<Ville> getVilleList() {
			List<Ville> A = new ArrayList<Ville>();
			for (Object c : getObjectService().getObjects("Ville")) {  
				A.add((Ville) c);  
	            } 
			
			return A;
		}

		public void setVilleList(List<Ville> villeList) {
			this.villeList = villeList;
		}  
		   
		   
}
