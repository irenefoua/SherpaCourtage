package com.j3a.assurance.converter;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.Garantie;
import com.j3a.assurance.objetService.ObjectService;

@Component
public class GarantieCvtr implements Converter {

	@Autowired
	ObjectService objectService;
	private List<Garantie> listGarantie = new ArrayList<>();
	private List<Garantie> listGarantieCopie = new ArrayList<>();

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component,
			String combodValue) {

		// TODO Auto-generated method stub
		if (combodValue.trim().equals("")) {

			return null;
		} else {
			try {

				for (Garantie gt : getListGarantie()) {
					if (gt.getCodeGarantie().equalsIgnoreCase(combodValue)) {
						return gt;
					}
				}

			} catch (Exception exception) {
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Conversion Error",
						"Not a valid garantie"));
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component,
			Object value) {
		// TODO Auto-generated method stub
		if (value == null || value.equals("")) {
			return "";
		} else {
			return ((Garantie) value).getCodeGarantie();
		}
	}

	// -------------------------------Getter and
	// Setter--------------------------------//

	public List<Garantie> getListGarantie() {
		if (listGarantie.isEmpty()) {
			for (Object gt : getObjectService().getObjects("Garantie")) {
				listGarantie.add((Garantie) gt);
			}
		}
		return listGarantie;
	}

	public void setListGarantie(List<Garantie> listGarantie) {
		this.listGarantie = listGarantie;
	}

	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}

	public List<Garantie> getListGarantieCopie() {
		return listGarantieCopie;
	}

	public void setListGarantieCopie(List<Garantie> listGarantieCopie) {
		this.listGarantieCopie = listGarantieCopie;
	}

}
