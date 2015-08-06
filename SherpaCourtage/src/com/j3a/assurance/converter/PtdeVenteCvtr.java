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

import com.j3a.assurance.model.PointVente;
import com.j3a.assurance.objetService.ObjectService;

@Component
@Scope("session")
public class PtdeVenteCvtr implements Converter {

	public PtdeVenteCvtr() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	ObjectService objectService;

	public List<PointVente> pointVenteList = new ArrayList<PointVente>();

	Logger logs = Logger.getLogger(PtdeVenteCvtr.class);

	public Object getAsObject(FacesContext facesContext, UIComponent component,
			String submittedValue) {
		if (submittedValue.equals("")) {
			return null;
		} else {
			try {
				String X = submittedValue;

				for (PointVente p : getPointVenteList()) {
					if (p.getCodePointVente().equalsIgnoreCase(X)) {
						return p;

					}
				}

			} catch (Exception exception) {
				logs.info(">>>>/ " + exception.getMessage());
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Conversion Error",
						"Not a valid argm"));
			}
		}

		return null;
	}

	public String getAsString(FacesContext facesContext, UIComponent component,
			Object value) {
		if (value == null || value.equals("")) {
			return "";
		} else {
			return ((PointVente) value).getCodePointVente();
		}
	}

	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}

	public List<PointVente> getPointVenteList() {
		if (pointVenteList.isEmpty()) {
			for (Object p : getObjectService().getObjects("PointVente")) {
				pointVenteList.add((PointVente) p);
			}
		}
		return pointVenteList;
	}

	public void setPointVenteList(List<PointVente> pointVenteList) {
		this.pointVenteList = pointVenteList;
	}

}
