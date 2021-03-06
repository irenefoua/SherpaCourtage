package com.j3a.assurance.model;

// Generated 10 ao�t 2015 09:53:29 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ApporteurGestionConfieeId generated by hbm2java
 */
@Embeddable
public class ApporteurGestionConfieeId implements java.io.Serializable {

	private String codeApporteur;
	private String codeGestionConfie;

	public ApporteurGestionConfieeId() {
	}

	public ApporteurGestionConfieeId(String codeApporteur,
			String codeGestionConfie) {
		this.codeApporteur = codeApporteur;
		this.codeGestionConfie = codeGestionConfie;
	}

	@Column(name = "CODE_APPORTEUR", nullable = false, length = 10)
	public String getCodeApporteur() {
		return this.codeApporteur;
	}

	public void setCodeApporteur(String codeApporteur) {
		this.codeApporteur = codeApporteur;
	}

	@Column(name = "CODE_GESTION_CONFIE", nullable = false, length = 60)
	public String getCodeGestionConfie() {
		return this.codeGestionConfie;
	}

	public void setCodeGestionConfie(String codeGestionConfie) {
		this.codeGestionConfie = codeGestionConfie;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ApporteurGestionConfieeId))
			return false;
		ApporteurGestionConfieeId castOther = (ApporteurGestionConfieeId) other;

		return ((this.getCodeApporteur() == castOther.getCodeApporteur()) || (this
				.getCodeApporteur() != null
				&& castOther.getCodeApporteur() != null && this
				.getCodeApporteur().equals(castOther.getCodeApporteur())))
				&& ((this.getCodeGestionConfie() == castOther
						.getCodeGestionConfie()) || (this
						.getCodeGestionConfie() != null
						&& castOther.getCodeGestionConfie() != null && this
						.getCodeGestionConfie().equals(
								castOther.getCodeGestionConfie())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodeApporteur() == null ? 0 : this.getCodeApporteur()
						.hashCode());
		result = 37
				* result
				+ (getCodeGestionConfie() == null ? 0 : this
						.getCodeGestionConfie().hashCode());
		return result;
	}

}
