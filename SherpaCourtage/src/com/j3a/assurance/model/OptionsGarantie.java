package com.j3a.assurance.model;

// Generated 11 ao�t 2015 12:07:31 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OptionsGarantie generated by hbm2java
 */
@Entity
@Table(name = "options_garantie", catalog = "zeusbd")
public class OptionsGarantie implements java.io.Serializable {

	private String codeOptionsGarantie;
	private String libelleOptionsGarantie;

	public OptionsGarantie() {
	}

	public OptionsGarantie(String codeOptionsGarantie) {
		this.codeOptionsGarantie = codeOptionsGarantie;
	}

	public OptionsGarantie(String codeOptionsGarantie,
			String libelleOptionsGarantie) {
		this.codeOptionsGarantie = codeOptionsGarantie;
		this.libelleOptionsGarantie = libelleOptionsGarantie;
	}

	@Id
	@Column(name = "CODE_OPTIONS_GARANTIE", unique = true, nullable = false, length = 30)
	public String getCodeOptionsGarantie() {
		return this.codeOptionsGarantie;
	}

	public void setCodeOptionsGarantie(String codeOptionsGarantie) {
		this.codeOptionsGarantie = codeOptionsGarantie;
	}

	@Column(name = "LIBELLE_OPTIONS_GARANTIE", length = 80)
	public String getLibelleOptionsGarantie() {
		return this.libelleOptionsGarantie;
	}

	public void setLibelleOptionsGarantie(String libelleOptionsGarantie) {
		this.libelleOptionsGarantie = libelleOptionsGarantie;
	}

}
