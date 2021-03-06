package com.j3a.assurance.model;

// Generated 10 ao�t 2015 09:53:29 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * GestionConfieFormuleId generated by hbm2java
 */
@Embeddable
public class GestionConfieFormuleId implements java.io.Serializable {

	private String codeGestionConfie;
	private String codeFormuleGc;

	public GestionConfieFormuleId() {
	}

	public GestionConfieFormuleId(String codeGestionConfie, String codeFormuleGc) {
		this.codeGestionConfie = codeGestionConfie;
		this.codeFormuleGc = codeFormuleGc;
	}

	@Column(name = "CODE_GESTION_CONFIE", nullable = false, length = 60)
	public String getCodeGestionConfie() {
		return this.codeGestionConfie;
	}

	public void setCodeGestionConfie(String codeGestionConfie) {
		this.codeGestionConfie = codeGestionConfie;
	}

	@Column(name = "CODE_FORMULE_GC", nullable = false, length = 60)
	public String getCodeFormuleGc() {
		return this.codeFormuleGc;
	}

	public void setCodeFormuleGc(String codeFormuleGc) {
		this.codeFormuleGc = codeFormuleGc;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GestionConfieFormuleId))
			return false;
		GestionConfieFormuleId castOther = (GestionConfieFormuleId) other;

		return ((this.getCodeGestionConfie() == castOther
				.getCodeGestionConfie()) || (this.getCodeGestionConfie() != null
				&& castOther.getCodeGestionConfie() != null && this
				.getCodeGestionConfie()
				.equals(castOther.getCodeGestionConfie())))
				&& ((this.getCodeFormuleGc() == castOther.getCodeFormuleGc()) || (this
						.getCodeFormuleGc() != null
						&& castOther.getCodeFormuleGc() != null && this
						.getCodeFormuleGc()
						.equals(castOther.getCodeFormuleGc())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodeGestionConfie() == null ? 0 : this
						.getCodeGestionConfie().hashCode());
		result = 37
				* result
				+ (getCodeFormuleGc() == null ? 0 : this.getCodeFormuleGc()
						.hashCode());
		return result;
	}

}
