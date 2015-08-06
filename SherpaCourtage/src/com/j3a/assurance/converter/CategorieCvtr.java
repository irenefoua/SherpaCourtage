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

import com.j3a.assurance.model.Categorie;
import com.j3a.assurance.objetService.ObjectService;

@Component
public class CategorieCvtr implements Converter {

	@Autowired
	ObjectService objectService;
	private List<Categorie> listCategorie = new ArrayList<>();
	private List<Categorie> listCategorieCopie = new ArrayList<>();

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component,
			String submittedValue) {

		// TODO Auto-generated method stub
		if (submittedValue.trim().equals("")) {

			return null;
		} else {
			try {

				for (Categorie c : getListCategorie()) {
					if (c.getCodeCategorie().equalsIgnoreCase(submittedValue)) {
						return c;
					}
				}

			} catch (Exception exception) {
				throw new ConverterException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Conversion Error",
						"Not a valid Categorie"));
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component,
			Object value) {
		if (value == null || value.equals("")) {
			return "";
		} else {
			return ((Categorie) value).getCodeCategorie();
		}
	}

	// -------------------------------Getter and
	// Setter--------------------------------//

	public List<Categorie> getListCategorie() {
		if (listCategorie.isEmpty()) {
			for (Object c : getObjectService().getObjects("Categorie")) {
				listCategorie.add((Categorie) c);
			}
		}
		return listCategorie;
	}

	public void setListCategorie(List<Categorie> listCategorie) {
		this.listCategorie = listCategorie;
	}

	public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}

	public List<Categorie> getListCategorieCopie() {
		return listCategorieCopie;
	}

	public void setListCategorieCopie(List<Categorie> listCategorieCopie) {
		this.listCategorieCopie = listCategorieCopie;
	}

}
