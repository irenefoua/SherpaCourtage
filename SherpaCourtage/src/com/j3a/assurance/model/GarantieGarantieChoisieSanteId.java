package com.j3a.assurance.model;

// Generated 10 ao�t 2015 09:53:29 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * GarantieGarantieChoisieSanteId generated by hbm2java
 */
@Embeddable
public class GarantieGarantieChoisieSanteId implements java.io.Serializable {

	private String codeGarantie;
	private String codeGarantieChoisiesante;

	public GarantieGarantieChoisieSanteId() {
	}

	public GarantieGarantieChoisieSanteId(String codeGarantie,
			String codeGarantieChoisiesante) {
		this.codeGarantie = codeGarantie;
		this.codeGarantieChoisiesante = codeGarantieChoisiesante;
	}

	@Column(name = "CODE_GARANTIE", nullable = false, length = 12)
	public String getCodeGarantie() {
		return this.codeGarantie;
	}

	public void setCodeGarantie(String codeGarantie) {
		this.codeGarantie = codeGarantie;
	}

	@Column(name = "CODE_GARANTIE_CHOISIESANTE", nullable = false, length = 35)
	public String getCodeGarantieChoisiesante() {
		return this.codeGarantieChoisiesante;
	}

	public void setCodeGarantieChoisiesante(String codeGarantieChoisiesante) {
		this.codeGarantieChoisiesante = codeGarantieChoisiesante;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GarantieGarantieChoisieSanteId))
			return false;
		GarantieGarantieChoisieSanteId castOther = (GarantieGarantieChoisieSanteId) other;

		return ((this.getCodeGarantie() == castOther.getCodeGarantie()) || (this
				.getCodeGarantie() != null
				&& castOther.getCodeGarantie() != null && this
				.getCodeGarantie().equals(castOther.getCodeGarantie())))
				&& ((this.getCodeGarantieChoisiesante() == castOther
						.getCodeGarantieChoisiesante()) || (this
						.getCodeGarantieChoisiesante() != null
						&& castOther.getCodeGarantieChoisiesante() != null && this
						.getCodeGarantieChoisiesante().equals(
								castOther.getCodeGarantieChoisiesante())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodeGarantie() == null ? 0 : this.getCodeGarantie()
						.hashCode());
		result = 37
				* result
				+ (getCodeGarantieChoisiesante() == null ? 0 : this
						.getCodeGarantieChoisiesante().hashCode());
		return result;
	}

}
