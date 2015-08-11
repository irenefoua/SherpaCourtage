package com.j3a.assurance.model;

// Generated 11 ao�t 2015 12:07:31 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ApporteurAlimentId generated by hbm2java
 */
@Embeddable
public class ApporteurAlimentId implements java.io.Serializable {

	private String codeApporteur;
	private String codeAliment;

	public ApporteurAlimentId() {
	}

	public ApporteurAlimentId(String codeApporteur, String codeAliment) {
		this.codeApporteur = codeApporteur;
		this.codeAliment = codeAliment;
	}

	@Column(name = "CODE_APPORTEUR", nullable = false, length = 10)
	public String getCodeApporteur() {
		return this.codeApporteur;
	}

	public void setCodeApporteur(String codeApporteur) {
		this.codeApporteur = codeApporteur;
	}

	@Column(name = "CODE_ALIMENT", nullable = false, length = 35)
	public String getCodeAliment() {
		return this.codeAliment;
	}

	public void setCodeAliment(String codeAliment) {
		this.codeAliment = codeAliment;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ApporteurAlimentId))
			return false;
		ApporteurAlimentId castOther = (ApporteurAlimentId) other;

		return ((this.getCodeApporteur() == castOther.getCodeApporteur()) || (this
				.getCodeApporteur() != null
				&& castOther.getCodeApporteur() != null && this
				.getCodeApporteur().equals(castOther.getCodeApporteur())))
				&& ((this.getCodeAliment() == castOther.getCodeAliment()) || (this
						.getCodeAliment() != null
						&& castOther.getCodeAliment() != null && this
						.getCodeAliment().equals(castOther.getCodeAliment())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodeApporteur() == null ? 0 : this.getCodeApporteur()
						.hashCode());
		result = 37
				* result
				+ (getCodeAliment() == null ? 0 : this.getCodeAliment()
						.hashCode());
		return result;
	}

}
