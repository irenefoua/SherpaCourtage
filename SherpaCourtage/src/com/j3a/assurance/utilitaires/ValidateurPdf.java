package com.j3a.assurance.utilitaires;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.model.UploadedFile;

@FacesValidator("com.j3a.assurance.utilitaire.ValidateurPdf")
public class ValidateurPdf implements Validator {
	private UploadedFile file;
	
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object obj)
			throws ValidatorException {
		// TODO Auto-generated method stub
		file=(UploadedFile) obj;
		String fileName = file.getFileName();
		fileName = fileName.substring(Math.max(fileName.length() - 3, 0));
		if (!FileUploadController.contains(fileName, "pdf")) {
			FacesMessage msg = new FacesMessage("PDF validation failed.",
					"Invalid dossier format.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

	}
	public UploadedFile getFile() {
		return file;
	}
	public void setFile(UploadedFile file) {
		this.file = file;
	}
}