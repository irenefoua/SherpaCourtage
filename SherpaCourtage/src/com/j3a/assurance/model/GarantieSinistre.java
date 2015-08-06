package com.j3a.assurance.model;

// Generated 6 ao�t 2015 16:35:56 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * GarantieSinistre generated by hbm2java
 */
@Entity
@Table(name = "garantie_sinistre", catalog = "zeusbd")
public class GarantieSinistre implements java.io.Serializable {

	private GarantieSinistreId id;
	private Garantie garantie;
	private Sinistre sinistre;
	private Date dateGarantieSinistre;
	private BigDecimal primeGarantieSinistre;
	private BigDecimal montantEvalutationSin;
	private BigDecimal autresFraisEvaluation;

	public GarantieSinistre() {
	}

	public GarantieSinistre(GarantieSinistreId id, Garantie garantie,
			Sinistre sinistre) {
		this.id = id;
		this.garantie = garantie;
		this.sinistre = sinistre;
	}

	public GarantieSinistre(GarantieSinistreId id, Garantie garantie,
			Sinistre sinistre, Date dateGarantieSinistre,
			BigDecimal primeGarantieSinistre, BigDecimal montantEvalutationSin,
			BigDecimal autresFraisEvaluation) {
		this.id = id;
		this.garantie = garantie;
		this.sinistre = sinistre;
		this.dateGarantieSinistre = dateGarantieSinistre;
		this.primeGarantieSinistre = primeGarantieSinistre;
		this.montantEvalutationSin = montantEvalutationSin;
		this.autresFraisEvaluation = autresFraisEvaluation;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codeGarantie", column = @Column(name = "CODE_GARANTIE", nullable = false, length = 12)),
			@AttributeOverride(name = "codeSinistre", column = @Column(name = "CODE_SINISTRE", nullable = false, length = 40)) })
	public GarantieSinistreId getId() {
		return this.id;
	}

	public void setId(GarantieSinistreId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_GARANTIE", nullable = false, insertable = false, updatable = false)
	public Garantie getGarantie() {
		return this.garantie;
	}

	public void setGarantie(Garantie garantie) {
		this.garantie = garantie;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_SINISTRE", nullable = false, insertable = false, updatable = false)
	public Sinistre getSinistre() {
		return this.sinistre;
	}

	public void setSinistre(Sinistre sinistre) {
		this.sinistre = sinistre;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_GARANTIE_SINISTRE", length = 10)
	public Date getDateGarantieSinistre() {
		return this.dateGarantieSinistre;
	}

	public void setDateGarantieSinistre(Date dateGarantieSinistre) {
		this.dateGarantieSinistre = dateGarantieSinistre;
	}

	@Column(name = "PRIME_GARANTIE_SINISTRE", precision = 15, scale = 3)
	public BigDecimal getPrimeGarantieSinistre() {
		return this.primeGarantieSinistre;
	}

	public void setPrimeGarantieSinistre(BigDecimal primeGarantieSinistre) {
		this.primeGarantieSinistre = primeGarantieSinistre;
	}

	@Column(name = "MONTANT_EVALUTATION_SIN", precision = 15, scale = 3)
	public BigDecimal getMontantEvalutationSin() {
		return this.montantEvalutationSin;
	}

	public void setMontantEvalutationSin(BigDecimal montantEvalutationSin) {
		this.montantEvalutationSin = montantEvalutationSin;
	}

	@Column(name = "AUTRES_FRAIS_EVALUATION", precision = 15, scale = 3)
	public BigDecimal getAutresFraisEvaluation() {
		return this.autresFraisEvaluation;
	}

	public void setAutresFraisEvaluation(BigDecimal autresFraisEvaluation) {
		this.autresFraisEvaluation = autresFraisEvaluation;
	}

}
