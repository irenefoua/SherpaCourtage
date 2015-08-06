package com.j3a.assurance.model;

// Generated 6 ao�t 2015 16:35:56 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Prejudice generated by hbm2java
 */
@Entity
@Table(name = "prejudice", catalog = "zeusbd")
public class Prejudice implements java.io.Serializable {

	private String codePrejudice;
	private AyantDroit ayantDroit;
	private Victime victime;
	private String libellePrejudice;
	private Date datePrejudice;
	private BigDecimal taux;
	private BigDecimal montantReel;
	private BigDecimal montantMaximum;
	private String typePrejudice;
	private BigDecimal rente;

	public Prejudice() {
	}

	public Prejudice(String codePrejudice) {
		this.codePrejudice = codePrejudice;
	}

	public Prejudice(String codePrejudice, AyantDroit ayantDroit,
			Victime victime, String libellePrejudice, Date datePrejudice,
			BigDecimal taux, BigDecimal montantReel, BigDecimal montantMaximum,
			String typePrejudice, BigDecimal rente) {
		this.codePrejudice = codePrejudice;
		this.ayantDroit = ayantDroit;
		this.victime = victime;
		this.libellePrejudice = libellePrejudice;
		this.datePrejudice = datePrejudice;
		this.taux = taux;
		this.montantReel = montantReel;
		this.montantMaximum = montantMaximum;
		this.typePrejudice = typePrejudice;
		this.rente = rente;
	}

	@Id
	@Column(name = "CODE_PREJUDICE", unique = true, nullable = false, length = 20)
	public String getCodePrejudice() {
		return this.codePrejudice;
	}

	public void setCodePrejudice(String codePrejudice) {
		this.codePrejudice = codePrejudice;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_AYANT_DROIT")
	public AyantDroit getAyantDroit() {
		return this.ayantDroit;
	}

	public void setAyantDroit(AyantDroit ayantDroit) {
		this.ayantDroit = ayantDroit;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NUM_VICTIME")
	public Victime getVictime() {
		return this.victime;
	}

	public void setVictime(Victime victime) {
		this.victime = victime;
	}

	@Column(name = "LIBELLE_PREJUDICE", length = 50)
	public String getLibellePrejudice() {
		return this.libellePrejudice;
	}

	public void setLibellePrejudice(String libellePrejudice) {
		this.libellePrejudice = libellePrejudice;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_PREJUDICE", length = 10)
	public Date getDatePrejudice() {
		return this.datePrejudice;
	}

	public void setDatePrejudice(Date datePrejudice) {
		this.datePrejudice = datePrejudice;
	}

	@Column(name = "TAUX", precision = 6, scale = 4)
	public BigDecimal getTaux() {
		return this.taux;
	}

	public void setTaux(BigDecimal taux) {
		this.taux = taux;
	}

	@Column(name = "MONTANT_REEL", precision = 15, scale = 3)
	public BigDecimal getMontantReel() {
		return this.montantReel;
	}

	public void setMontantReel(BigDecimal montantReel) {
		this.montantReel = montantReel;
	}

	@Column(name = "MONTANT_MAXIMUM", precision = 15, scale = 3)
	public BigDecimal getMontantMaximum() {
		return this.montantMaximum;
	}

	public void setMontantMaximum(BigDecimal montantMaximum) {
		this.montantMaximum = montantMaximum;
	}

	@Column(name = "TYPE_PREJUDICE", length = 100)
	public String getTypePrejudice() {
		return this.typePrejudice;
	}

	public void setTypePrejudice(String typePrejudice) {
		this.typePrejudice = typePrejudice;
	}

	@Column(name = "RENTE", precision = 15, scale = 3)
	public BigDecimal getRente() {
		return this.rente;
	}

	public void setRente(BigDecimal rente) {
		this.rente = rente;
	}

}
