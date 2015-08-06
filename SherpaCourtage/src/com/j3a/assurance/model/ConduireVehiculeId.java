package com.j3a.assurance.model;

// Generated 6 ao�t 2015 16:35:56 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ConduireVehiculeId generated by hbm2java
 */
@Embeddable
public class ConduireVehiculeId implements java.io.Serializable {

	private String codeVehicule;
	private String numCond;

	public ConduireVehiculeId() {
	}

	public ConduireVehiculeId(String codeVehicule, String numCond) {
		this.codeVehicule = codeVehicule;
		this.numCond = numCond;
	}

	@Column(name = "CODE_VEHICULE", nullable = false, length = 30)
	public String getCodeVehicule() {
		return this.codeVehicule;
	}

	public void setCodeVehicule(String codeVehicule) {
		this.codeVehicule = codeVehicule;
	}

	@Column(name = "NUM_COND", nullable = false, length = 15)
	public String getNumCond() {
		return this.numCond;
	}

	public void setNumCond(String numCond) {
		this.numCond = numCond;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ConduireVehiculeId))
			return false;
		ConduireVehiculeId castOther = (ConduireVehiculeId) other;

		return ((this.getCodeVehicule() == castOther.getCodeVehicule()) || (this
				.getCodeVehicule() != null
				&& castOther.getCodeVehicule() != null && this
				.getCodeVehicule().equals(castOther.getCodeVehicule())))
				&& ((this.getNumCond() == castOther.getNumCond()) || (this
						.getNumCond() != null && castOther.getNumCond() != null && this
						.getNumCond().equals(castOther.getNumCond())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodeVehicule() == null ? 0 : this.getCodeVehicule()
						.hashCode());
		result = 37 * result
				+ (getNumCond() == null ? 0 : this.getNumCond().hashCode());
		return result;
	}

}
