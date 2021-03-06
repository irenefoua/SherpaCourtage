package com.j3a.assurance.model;

// Generated 10 ao�t 2015 09:53:29 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * EffectifPersoEtatCimaId generated by hbm2java
 */
@Embeddable
public class EffectifPersoEtatCimaId implements java.io.Serializable {

	private String codeEffectifPersonnel;
	private String codeEtatCima;

	public EffectifPersoEtatCimaId() {
	}

	public EffectifPersoEtatCimaId(String codeEffectifPersonnel,
			String codeEtatCima) {
		this.codeEffectifPersonnel = codeEffectifPersonnel;
		this.codeEtatCima = codeEtatCima;
	}

	@Column(name = "CODE_EFFECTIF_PERSONNEL", nullable = false, length = 20)
	public String getCodeEffectifPersonnel() {
		return this.codeEffectifPersonnel;
	}

	public void setCodeEffectifPersonnel(String codeEffectifPersonnel) {
		this.codeEffectifPersonnel = codeEffectifPersonnel;
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
		if (!(other instanceof EffectifPersoEtatCimaId))
			return false;
		EffectifPersoEtatCimaId castOther = (EffectifPersoEtatCimaId) other;

		return ((this.getCodeEffectifPersonnel() == castOther
				.getCodeEffectifPersonnel()) || (this
				.getCodeEffectifPersonnel() != null
				&& castOther.getCodeEffectifPersonnel() != null && this
				.getCodeEffectifPersonnel().equals(
						castOther.getCodeEffectifPersonnel())))
				&& ((this.getCodeEtatCima() == castOther.getCodeEtatCima()) || (this
						.getCodeEtatCima() != null
						&& castOther.getCodeEtatCima() != null && this
						.getCodeEtatCima().equals(castOther.getCodeEtatCima())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodeEffectifPersonnel() == null ? 0 : this
						.getCodeEffectifPersonnel().hashCode());
		result = 37
				* result
				+ (getCodeEtatCima() == null ? 0 : this.getCodeEtatCima()
						.hashCode());
		return result;
	}

}
