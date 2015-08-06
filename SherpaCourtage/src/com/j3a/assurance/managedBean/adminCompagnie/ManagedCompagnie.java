package com.j3a.assurance.managedBean.adminCompagnie;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.j3a.assurance.model.CompagnieAssurance;


@Component
public class ManagedCompagnie implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CompagnieAssurance compagnieAssurance;
	public CompagnieAssurance getCompagnieAssurance() {
		return compagnieAssurance;
	}
	public void setCompagnieAssurance(CompagnieAssurance compagnieAssurance) {
		this.compagnieAssurance = compagnieAssurance;
	}

}
