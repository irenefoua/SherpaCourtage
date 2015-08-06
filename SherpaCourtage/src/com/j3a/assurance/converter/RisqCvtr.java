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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.Risque;
import com.j3a.assurance.objetService.ObjectService;

@Component
@Scope("session")
public class RisqCvtr implements Converter {

	public RisqCvtr() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	ObjectService objectService;

	public List<Risque> risqList = new ArrayList<Risque>();

	Logger logs = Logger.getLogger(RisqCvtr.class);

	public Object getAsObject(FacesContext facesContext, UIComponent component,
			String submittedValue) {
		if (submittedValue.equals("")) {
			return null;
		} else {
			try {
				String X = submittedValue;

				for (Risque c : getRisqList()) {
					if (c.getCodeRisque().equalsIgnoreCase(X)) {
						return c;

					}
				}

			} catch (Exception exception) {
				logs.info(">>>>/ " + exception.getMessage());
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Conversion Error",
						"Not a valid SousCategorie Vehicule"));
			}
		}

		return null;
	}

	public String getAsString(FacesContext facesContext, UIComponent component,
			Object value) {
		if (value == null || value.equals("")) {
			return "";
		} else {
			return ((Risque) value).getCodeRisque();
		}
	}

	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}

	public List<Risque> getRisqList() {
		if (risqList.isEmpty()) {
			for (Object c : getObjectService().getObjects("Risque")) {
				risqList.add((Risque) c);
			}
		}
		return risqList;
	}

	public void setRisqList(List<Risque> risqList) {
		this.risqList = risqList;
	}

}
