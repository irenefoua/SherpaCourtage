package com.j3a.assurance.model;

// Generated 10 ao�t 2015 09:53:29 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AccordCimaId generated by hbm2java
 */
@Embeddable
public class AccordCimaId implements java.io.Serializable {

	private String codeAccord;
	private String codeEtatCima;

	public AccordCimaId() {
	}

	public AccordCimaId(String codeAccord, String codeEtatCima) {
		this.codeAccord = codeAccord;
		this.codeEtatCima = codeEtatCima;
	}

	@Column(name = "CODE_ACCORD", nullable = false, length = 20)
	public String getCodeAccord() {
		return this.codeAccord;
	}

	public void setCodeAccord(String codeAccord) {
		this.codeAccord = codeAccord;
	}

	@Column(name = "CODE_ETAT_CIMA", nullable = false, length = 20)
	public String getCodeEtatCima() {
		return this.codeEtatCima;
	}

	public void setCodeEtatCima(String codeEtatCima) {
		this.codeEtatCima = codeEtatCima;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AccordCimaId))
			return false;
		AccordCimaId castOther = (AccordCimaId) other;

		return ((this.getCodeAccord() == castOther.getCodeAccord()) || (this
				.getCodeAccord() != null && castOther.getCodeAccord() != null && this
				.getCodeAccord().equals(castOther.getCodeAccord())))
				&& ((this.getCodeEtatCima() == castOther.getCodeEtatCima()) || (this
						.getCodeEtatCima() != null
						&& castOther.getCodeEtatCima() != null && this
						.getCodeEtatCima().equals(castOther.getCodeEtatCima())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodeAccord() == null ? 0 : this.getCodeAccord()
						.hashCode());
		result = 37
				* result
				+ (getCodeEtatCima() == null ? 0 : this.getCodeEtatCima()
						.hashCode());
		return result;
	}

}
