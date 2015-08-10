package com.j3a.assurance.model;

// Generated 10 ao�t 2015 15:53:59 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AvListeAssureIaId generated by hbm2java
 */
@Embeddable
public class AvListeAssureIaId implements java.io.Serializable {

	private String codeListeAssureIa;
	private String numAvenant;

	public AvListeAssureIaId() {
	}

	public AvListeAssureIaId(String codeListeAssureIa, String numAvenant) {
		this.codeListeAssureIa = codeListeAssureIa;
		this.numAvenant = numAvenant;
	}

	@Column(name = "CODE_LISTE_ASSURE_IA", nullable = false, length = 30)
	public String getCodeListeAssureIa() {
		return this.codeListeAssureIa;
	}

	public void setCodeListeAssureIa(String codeListeAssureIa) {
		this.codeListeAssureIa = codeListeAssureIa;
	}

	@Column(name = "NUM_AVENANT", nullable = false, length = 25)
	public String getNumAvenant() {
		return this.numAvenant;
	}

	public void setNumAvenant(String numAvenant) {
		this.numAvenant = numAvenant;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AvListeAssureIaId))
			return false;
		AvListeAssureIaId castOther = (AvListeAssureIaId) other;

		return ((this.getCodeListeAssureIa() == castOther
				.getCodeListeAssureIa()) || (this.getCodeListeAssureIa() != null
				&& castOther.getCodeListeAssureIa() != null && this
				.getCodeListeAssureIa()
				.equals(castOther.getCodeListeAssureIa())))
				&& ((this.getNumAvenant() == castOther.getNumAvenant()) || (this
						.getNumAvenant() != null
						&& castOther.getNumAvenant() != null && this
						.getNumAvenant().equals(castOther.getNumAvenant())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodeListeAssureIa() == null ? 0 : this
						.getCodeListeAssureIa().hashCode());
		result = 37
				* result
				+ (getNumAvenant() == null ? 0 : this.getNumAvenant()
						.hashCode());
		return result;
	}

}
