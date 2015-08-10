package com.j3a.assurance.model;

// Generated 10 ao�t 2015 15:05:20 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ReglementApporteurId generated by hbm2java
 */
@Embeddable
public class ReglementApporteurId implements java.io.Serializable {

	private String codeAffaire;
	private String codeReglementApp;
	private Date dateReglementApp;
	private BigDecimal montantReglementApp;
	private String typeReglement;

	public ReglementApporteurId() {
	}

	public ReglementApporteurId(String codeAffaire, String codeReglementApp) {
		this.codeAffaire = codeAffaire;
		this.codeReglementApp = codeReglementApp;
	}

	public ReglementApporteurId(String codeAffaire, String codeReglementApp,
			Date dateReglementApp, BigDecimal montantReglementApp,
			String typeReglement) {
		this.codeAffaire = codeAffaire;
		this.codeReglementApp = codeReglementApp;
		this.dateReglementApp = dateReglementApp;
		this.montantReglementApp = montantReglementApp;
		this.typeReglement = typeReglement;
	}

	@Column(name = "CODE_AFFAIRE", nullable = false, length = 30)
	public String getCodeAffaire() {
		return this.codeAffaire;
	}

	public void setCodeAffaire(String codeAffaire) {
		this.codeAffaire = codeAffaire;
	}

	@Column(name = "CODE_REGLEMENT_APP", nullable = false, length = 20)
	public String getCodeReglementApp() {
		return this.codeReglementApp;
	}

	public void setCodeReglementApp(String codeReglementApp) {
		this.codeReglementApp = codeReglementApp;
	}

	@Column(name = "DATE_REGLEMENT_APP", length = 10)
	public Date getDateReglementApp() {
		return this.dateReglementApp;
	}

	public void setDateReglementApp(Date dateReglementApp) {
		this.dateReglementApp = dateReglementApp;
	}

	@Column(name = "MONTANT_REGLEMENT_APP", precision = 15, scale = 3)
	public BigDecimal getMontantReglementApp() {
		return this.montantReglementApp;
	}

	public void setMontantReglementApp(BigDecimal montantReglementApp) {
		this.montantReglementApp = montantReglementApp;
	}

	@Column(name = "TYPE_REGLEMENT", length = 60)
	public String getTypeReglement() {
		return this.typeReglement;
	}

	public void setTypeReglement(String typeReglement) {
		this.typeReglement = typeReglement;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ReglementApporteurId))
			return false;
		ReglementApporteurId castOther = (ReglementApporteurId) other;

		return ((this.getCodeAffaire() == castOther.getCodeAffaire()) || (this
				.getCodeAffaire() != null && castOther.getCodeAffaire() != null && this
				.getCodeAffaire().equals(castOther.getCodeAffaire())))
				&& ((this.getCodeReglementApp() == castOther
						.getCodeReglementApp()) || (this.getCodeReglementApp() != null
						&& castOther.getCodeReglementApp() != null && this
						.getCodeReglementApp().equals(
								castOther.getCodeReglementApp())))
				&& ((this.getDateReglementApp() == castOther
						.getDateReglementApp()) || (this.getDateReglementApp() != null
						&& castOther.getDateReglementApp() != null && this
						.getDateReglementApp().equals(
								castOther.getDateReglementApp())))
				&& ((this.getMontantReglementApp() == castOther
						.getMontantReglementApp()) || (this
						.getMontantReglementApp() != null
						&& castOther.getMontantReglementApp() != null && this
						.getMontantReglementApp().equals(
								castOther.getMontantReglementApp())))
				&& ((this.getTypeReglement() == castOther.getTypeReglement()) || (this
						.getTypeReglement() != null
						&& castOther.getTypeReglement() != null && this
						.getTypeReglement()
						.equals(castOther.getTypeReglement())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCodeAffaire() == null ? 0 : this.getCodeAffaire()
						.hashCode());
		result = 37
				* result
				+ (getCodeReglementApp() == null ? 0 : this
						.getCodeReglementApp().hashCode());
		result = 37
				* result
				+ (getDateReglementApp() == null ? 0 : this
						.getDateReglementApp().hashCode());
		result = 37
				* result
				+ (getMontantReglementApp() == null ? 0 : this
						.getMontantReglementApp().hashCode());
		result = 37
				* result
				+ (getTypeReglement() == null ? 0 : this.getTypeReglement()
						.hashCode());
		return result;
	}

}
