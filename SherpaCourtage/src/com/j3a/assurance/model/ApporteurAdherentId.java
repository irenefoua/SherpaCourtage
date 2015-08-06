package com.j3a.assurance.model;

// Generated 6 ao�t 2015 16:35:56 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ApporteurAdherentId generated by hbm2java
 */
@Embeddable
public class ApporteurAdherentId implements java.io.Serializable {

	private String codeApporteur;
	private String codeAdherent;

	public ApporteurAdherentId() {
	}

	public ApporteurAdherentId(String codeApporteur, String codeAdherent) {
		this.codeApporteur = codeApporteur;
		this.codeAdherent = codeAdherent;
	}

	@Column(name = "CODE_APPORTEUR", nullable = false, length = 10)
	public String getCodeApporteur() {
		return this.codeApporteur;
	}

	public void setCodeApporteur(String codeApporteur) {
		this.codeApporteur = codeApporteur;
	}

	@Column(name = "CODE_ADHERENT", nullable = false, length = 60)
	public String getCodeAdherent() {
		return this.codeAdherent;
	}

	public void setCodeAdherent(String codeAdherent) {
		this.codeAdherent = codeAdherent;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ApporteurAdherentId))
			return false;
		ApporteurAdherentId castOther = (ApporteurAdherentId) other;

		return ((this.getCodeApporteur() == castOther.getCodeApporteur()) || (this
				.getCodeApporteur() != null
				&& castOther.getCodeApporteur() != null && this
				.getCodeApporteur().equals(castOther.getCodeApporteur())))
				&& ((this.getCodeAdherent() == castOther.getCodeAdherent()) || (this
						.getCodeAdherent() != null
						&& castOther.getCodeAdherent() != null && this
						.getCodeAdherent().equals(castOther.getCodeAdherent())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodeApporteur() == null ? 0 : this.getCodeApporteur()
						.hashCode());
		result = 37
				* result
				+ (getCodeAdherent() == null ? 0 : this.getCodeAdherent()
						.hashCode());
		return result;
	}

}
