package com.j3a.assurance.model;

// Generated 10 ao�t 2015 15:53:59 by Hibernate Tools 4.3.1

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
 * GarantieGarantieChoisieTransport generated by hbm2java
 */
@Entity
@Table(name = "garantie_garantie_choisie_transport", catalog = "zeusbd")
public class GarantieGarantieChoisieTransport implements java.io.Serializable {

	private GarantieGarantieChoisieTransportId id;
	private Garantie garantie;
	private GarantieChoisieTransport garantieChoisieTransport;
	private String nature;
	private BigDecimal taux;
	private BigDecimal minimum;
	private BigDecimal maximum;
	private Date dateGarantieChoisie;
	private BigDecimal montantGarantie;
	private BigDecimal tauxfranchise;
	private BigDecimal capitalTransport;

	public GarantieGarantieChoisieTransport() {
	}

	public GarantieGarantieChoisieTransport(
			GarantieGarantieChoisieTransportId id, Garantie garantie,
			GarantieChoisieTransport garantieChoisieTransport) {
		this.id = id;
		this.garantie = garantie;
		this.garantieChoisieTransport = garantieChoisieTransport;
	}

	public GarantieGarantieChoisieTransport(
			GarantieGarantieChoisieTransportId id, Garantie garantie,
			GarantieChoisieTransport garantieChoisieTransport, String nature,
			BigDecimal taux, BigDecimal minimum, BigDecimal maximum,
			Date dateGarantieChoisie, BigDecimal montantGarantie,
			BigDecimal tauxfranchise, BigDecimal capitalTransport) {
		this.id = id;
		this.garantie = garantie;
		this.garantieChoisieTransport = garantieChoisieTransport;
		this.nature = nature;
		this.taux = taux;
		this.minimum = minimum;
		this.maximum = maximum;
		this.dateGarantieChoisie = dateGarantieChoisie;
		this.montantGarantie = montantGarantie;
		this.tauxfranchise = tauxfranchise;
		this.capitalTransport = capitalTransport;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codeGarantieChoisieTransport", column = @Column(name = "CODE_GARANTIE_CHOISIE_TRANSPORT", nullable = false, length = 40)),
			@AttributeOverride(name = "codeGarantie", column = @Column(name = "CODE_GARANTIE", nullable = false, length = 12)) })
	public GarantieGarantieChoisieTransportId getId() {
		return this.id;
	}

	public void setId(GarantieGarantieChoisieTransportId id) {
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
	@JoinColumn(name = "CODE_GARANTIE_CHOISIE_TRANSPORT", nullable = false, insertable = false, updatable = false)
	public GarantieChoisieTransport getGarantieChoisieTransport() {
		return this.garantieChoisieTransport;
	}

	public void setGarantieChoisieTransport(
			GarantieChoisieTransport garantieChoisieTransport) {
		this.garantieChoisieTransport = garantieChoisieTransport;
	}

	@Column(name = "NATURE", length = 20)
	public String getNature() {
		return this.nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	@Column(name = "TAUX", precision = 6, scale = 4)
	public BigDecimal getTaux() {
		return this.taux;
	}

	public void setTaux(BigDecimal taux) {
		this.taux = taux;
	}

	@Column(name = "MINIMUM", precision = 15, scale = 3)
	public BigDecimal getMinimum() {
		return this.minimum;
	}

	public void setMinimum(BigDecimal minimum) {
		this.minimum = minimum;
	}

	@Column(name = "MAXIMUM", precision = 15, scale = 3)
	public BigDecimal getMaximum() {
		return this.maximum;
	}

	public void setMaximum(BigDecimal maximum) {
		this.maximum = maximum;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_GARANTIE_CHOISIE", length = 10)
	public Date getDateGarantieChoisie() {
		return this.dateGarantieChoisie;
	}

	public void setDateGarantieChoisie(Date dateGarantieChoisie) {
		this.dateGarantieChoisie = dateGarantieChoisie;
	}

	@Column(name = "MONTANT_GARANTIE", precision = 15, scale = 3)
	public BigDecimal getMontantGarantie() {
		return this.montantGarantie;
	}

	public void setMontantGarantie(BigDecimal montantGarantie) {
		this.montantGarantie = montantGarantie;
	}

	@Column(name = "TAUXFRANCHISE", precision = 6, scale = 4)
	public BigDecimal getTauxfranchise() {
		return this.tauxfranchise;
	}

	public void setTauxfranchise(BigDecimal tauxfranchise) {
		this.tauxfranchise = tauxfranchise;
	}

	@Column(name = "CAPITAL_TRANSPORT", precision = 15, scale = 3)
	public BigDecimal getCapitalTransport() {
		return this.capitalTransport;
	}

	public void setCapitalTransport(BigDecimal capitalTransport) {
		this.capitalTransport = capitalTransport;
	}

}
