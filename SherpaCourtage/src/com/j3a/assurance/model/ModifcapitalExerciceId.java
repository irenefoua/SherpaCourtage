package com.j3a.assurance.model;

// Generated 10 ao�t 2015 09:53:29 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ModifcapitalExerciceId generated by hbm2java
 */
@Embeddable
public class ModifcapitalExerciceId implements java.io.Serializable {

	private String codeEtatCima;
	private String codeModifCap;

	public ModifcapitalExerciceId() {
	}

	public ModifcapitalExerciceId(String codeEtatCima, String codeModifCap) {
		this.codeEtatCima = codeEtatCima;
		this.codeModifCap = codeModifCap;
	}

	@Column(name = "CODE_ETAT_CIMA", nullable = false, length = 20)
	public String getCodeEtatCima() {
		return this.codeEtatCima;
	}

	public void setCodeEtatCima(String codeEtatCima) {
		this.codeEtatCima = codeEtatCima;
	}

	@Column(name = "CODE_MODIF_CAP", nullable = false, length = 20)
	public String getCodeModifCap() {
		return this.codeModifCap;
	}

	public void setCodeModifCap(String codeModifCap) {
		this.codeModifCap = codeModifCap;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ModifcapitalExerciceId))
			return false;
		ModifcapitalExerciceId castOther = (ModifcapitalExerciceId) other;

		return ((this.getCodeEtatCima() == castOther.getCodeEtatCima()) || (this
				.getCodeEtatCima() != null
				&& castOther.getCodeEtatCima() != null && this
				.getCodeEtatCima().equals(castOther.getCodeEtatCima())))
				&& ((this.getCodeModifCap() == castOther.getCodeModifCap()) || (this
						.getCodeModifCap() != null
						&& castOther.getCodeModifCap() != null && this
						.getCodeModifCap().equals(castOther.getCodeModifCap())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodeEtatCima() == null ? 0 : this.getCodeEtatCima()
						.hashCode());
		result = 37
				* result
				+ (getCodeModifCap() == null ? 0 : this.getCodeModifCap()
						.hashCode());
		return result;
	}

}
