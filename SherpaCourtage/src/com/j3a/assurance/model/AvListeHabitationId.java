package com.j3a.assurance.model;

// Generated 10 ao�t 2015 15:53:59 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AvListeHabitationId generated by hbm2java
 */
@Embeddable
public class AvListeHabitationId implements java.io.Serializable {

	private String codeListeHabitation;
	private String numAvenant;

	public AvListeHabitationId() {
	}

	public AvListeHabitationId(String codeListeHabitation, String numAvenant) {
		this.codeListeHabitation = codeListeHabitation;
		this.numAvenant = numAvenant;
	}

	@Column(name = "CODE_LISTE_HABITATION", nullable = false, length = 27)
	public String getCodeListeHabitation() {
		return this.codeListeHabitation;
	}

	public void setCodeListeHabitation(String codeListeHabitation) {
		this.codeListeHabitation = codeListeHabitation;
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
		if (!(other instanceof AvListeHabitationId))
			return false;
		AvListeHabitationId castOther = (AvListeHabitationId) other;

		return ((this.getCodeListeHabitation() == castOther
				.getCodeListeHabitation()) || (this.getCodeListeHabitation() != null
				&& castOther.getCodeListeHabitation() != null && this
				.getCodeListeHabitation().equals(
						castOther.getCodeListeHabitation())))
				&& ((this.getNumAvenant() == castOther.getNumAvenant()) || (this
						.getNumAvenant() != null
						&& castOther.getNumAvenant() != null && this
						.getNumAvenant().equals(castOther.getNumAvenant())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodeListeHabitation() == null ? 0 : this
						.getCodeListeHabitation().hashCode());
		result = 37
				* result
				+ (getNumAvenant() == null ? 0 : this.getNumAvenant()
						.hashCode());
		return result;
	}

}
