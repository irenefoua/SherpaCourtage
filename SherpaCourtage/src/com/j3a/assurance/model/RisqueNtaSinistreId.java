package com.j3a.assurance.model;

// Generated 10 ao�t 2015 09:53:29 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RisqueNtaSinistreId generated by hbm2java
 */
@Embeddable
public class RisqueNtaSinistreId implements java.io.Serializable {

	private String codeSinistre;
	private String codeRisquenta;

	public RisqueNtaSinistreId() {
	}

	public RisqueNtaSinistreId(String codeSinistre, String codeRisquenta) {
		this.codeSinistre = codeSinistre;
		this.codeRisquenta = codeRisquenta;
	}

	@Column(name = "CODE_SINISTRE", nullable = false, length = 40)
	public String getCodeSinistre() {
		return this.codeSinistre;
	}

	public void setCodeSinistre(String codeSinistre) {
		this.codeSinistre = codeSinistre;
	}

	@Column(name = "CODE_RISQUENTA", nullable = false, length = 30)
	public String getCodeRisquenta() {
		return this.codeRisquenta;
	}

	public void setCodeRisquenta(String codeRisquenta) {
		this.codeRisquenta = codeRisquenta;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RisqueNtaSinistreId))
			return false;
		RisqueNtaSinistreId castOther = (RisqueNtaSinistreId) other;

		return ((this.getCodeSinistre() == castOther.getCodeSinistre()) || (this
				.getCodeSinistre() != null
				&& castOther.getCodeSinistre() != null && this
				.getCodeSinistre().equals(castOther.getCodeSinistre())))
				&& ((this.getCodeRisquenta() == castOther.getCodeRisquenta()) || (this
						.getCodeRisquenta() != null
						&& castOther.getCodeRisquenta() != null && this
						.getCodeRisquenta()
						.equals(castOther.getCodeRisquenta())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodeSinistre() == null ? 0 : this.getCodeSinistre()
						.hashCode());
		result = 37
				* result
				+ (getCodeRisquenta() == null ? 0 : this.getCodeRisquenta()
						.hashCode());
		return result;
	}

}
