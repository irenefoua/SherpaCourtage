package com.j3a.assurance.model;

// Generated 6 ao�t 2015 16:35:56 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * GestionnaireGcId generated by hbm2java
 */
@Embeddable
public class GestionnaireGcId implements java.io.Serializable {

	private String codeGestionConfie;
	private String codeGestionnaire;

	public GestionnaireGcId() {
	}

	public GestionnaireGcId(String codeGestionConfie, String codeGestionnaire) {
		this.codeGestionConfie = codeGestionConfie;
		this.codeGestionnaire = codeGestionnaire;
	}

	@Column(name = "CODE_GESTION_CONFIE", nullable = false, length = 60)
	public String getCodeGestionConfie() {
		return this.codeGestionConfie;
	}

	public void setCodeGestionConfie(String codeGestionConfie) {
		this.codeGestionConfie = codeGestionConfie;
	}

	@Column(name = "CODE_GESTIONNAIRE", nullable = false, length = 30)
	public String getCodeGestionnaire() {
		return this.codeGestionnaire;
	}

	public void setCodeGestionnaire(String codeGestionnaire) {
		this.codeGestionnaire = codeGestionnaire;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GestionnaireGcId))
			return false;
		GestionnaireGcId castOther = (GestionnaireGcId) other;

		return ((this.getCodeGestionConfie() == castOther
				.getCodeGestionConfie()) || (this.getCodeGestionConfie() != null
				&& castOther.getCodeGestionConfie() != null && this
				.getCodeGestionConfie()
				.equals(castOther.getCodeGestionConfie())))
				&& ((this.getCodeGestionnaire() == castOther
						.getCodeGestionnaire()) || (this.getCodeGestionnaire() != null
						&& castOther.getCodeGestionnaire() != null && this
						.getCodeGestionnaire().equals(
								castOther.getCodeGestionnaire())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodeGestionConfie() == null ? 0 : this
						.getCodeGestionConfie().hashCode());
		result = 37
				* result
				+ (getCodeGestionnaire() == null ? 0 : this
						.getCodeGestionnaire().hashCode());
		return result;
	}

}
