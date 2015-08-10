package com.j3a.assurance.model;

// Generated 10 ao�t 2015 09:53:29 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Lgc1debit generated by hbm2java
 */
@Entity
@Table(name = "lgc1debit", catalog = "zeusbd")
public class Lgc1debit implements java.io.Serializable {

	private String idlgc1debit;
	private CategorieCima categorieCima;
	private Etatc1 etatc1;
	private BigDecimal sinistresPayes;
	private BigDecimal fraisaccessoires;
	private BigDecimal participexceddeb;
	private BigDecimal adedrrecrsdeb;
	private BigDecimal arrapcstitutiondeb;
	private BigDecimal prestaaccpaiddeb;
	private BigDecimal provsinm31decprdeb;
	private BigDecimal provsinp31decdeb;
	private BigDecimal provexdm31decprdeb;
	private BigDecimal provexdp31decdeb;
	private BigDecimal prevrcrsm31decprdeb;
	private BigDecimal prevrcrsp31decdeb;
	private BigDecimal provmathm31decprdeb;
	private BigDecimal provmathp31decdeb;
	private BigDecimal dotprovpresfraisdeb;
	private BigDecimal commissionsdeb;
	private BigDecimal autreschargesdeb;
	private BigDecimal primceddeb;
	private BigDecimal provprmreasm31decprdeb;
	private BigDecimal provprmreasp31decdeb;
	private BigDecimal prmacquireasdeb;
	private BigDecimal soldcrediteur;
	private BigDecimal totaldeb;

	public Lgc1debit() {
	}

	public Lgc1debit(String idlgc1debit) {
		this.idlgc1debit = idlgc1debit;
	}

	public Lgc1debit(String idlgc1debit, CategorieCima categorieCima,
			Etatc1 etatc1, BigDecimal sinistresPayes,
			BigDecimal fraisaccessoires, BigDecimal participexceddeb,
			BigDecimal adedrrecrsdeb, BigDecimal arrapcstitutiondeb,
			BigDecimal prestaaccpaiddeb, BigDecimal provsinm31decprdeb,
			BigDecimal provsinp31decdeb, BigDecimal provexdm31decprdeb,
			BigDecimal provexdp31decdeb, BigDecimal prevrcrsm31decprdeb,
			BigDecimal prevrcrsp31decdeb, BigDecimal provmathm31decprdeb,
			BigDecimal provmathp31decdeb, BigDecimal dotprovpresfraisdeb,
			BigDecimal commissionsdeb, BigDecimal autreschargesdeb,
			BigDecimal primceddeb, BigDecimal provprmreasm31decprdeb,
			BigDecimal provprmreasp31decdeb, BigDecimal prmacquireasdeb,
			BigDecimal soldcrediteur, BigDecimal totaldeb) {
		this.idlgc1debit = idlgc1debit;
		this.categorieCima = categorieCima;
		this.etatc1 = etatc1;
		this.sinistresPayes = sinistresPayes;
		this.fraisaccessoires = fraisaccessoires;
		this.participexceddeb = participexceddeb;
		this.adedrrecrsdeb = adedrrecrsdeb;
		this.arrapcstitutiondeb = arrapcstitutiondeb;
		this.prestaaccpaiddeb = prestaaccpaiddeb;
		this.provsinm31decprdeb = provsinm31decprdeb;
		this.provsinp31decdeb = provsinp31decdeb;
		this.provexdm31decprdeb = provexdm31decprdeb;
		this.provexdp31decdeb = provexdp31decdeb;
		this.prevrcrsm31decprdeb = prevrcrsm31decprdeb;
		this.prevrcrsp31decdeb = prevrcrsp31decdeb;
		this.provmathm31decprdeb = provmathm31decprdeb;
		this.provmathp31decdeb = provmathp31decdeb;
		this.dotprovpresfraisdeb = dotprovpresfraisdeb;
		this.commissionsdeb = commissionsdeb;
		this.autreschargesdeb = autreschargesdeb;
		this.primceddeb = primceddeb;
		this.provprmreasm31decprdeb = provprmreasm31decprdeb;
		this.provprmreasp31decdeb = provprmreasp31decdeb;
		this.prmacquireasdeb = prmacquireasdeb;
		this.soldcrediteur = soldcrediteur;
		this.totaldeb = totaldeb;
	}

	@Id
	@Column(name = "IDLGC1DEBIT", unique = true, nullable = false, length = 40)
	public String getIdlgc1debit() {
		return this.idlgc1debit;
	}

	public void setIdlgc1debit(String idlgc1debit) {
		this.idlgc1debit = idlgc1debit;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODECATEGORIECIMA")
	public CategorieCima getCategorieCima() {
		return this.categorieCima;
	}

	public void setCategorieCima(CategorieCima categorieCima) {
		this.categorieCima = categorieCima;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDETATC1")
	public Etatc1 getEtatc1() {
		return this.etatc1;
	}

	public void setEtatc1(Etatc1 etatc1) {
		this.etatc1 = etatc1;
	}

	@Column(name = "SINISTRES_PAYES", precision = 15, scale = 3)
	public BigDecimal getSinistresPayes() {
		return this.sinistresPayes;
	}

	public void setSinistresPayes(BigDecimal sinistresPayes) {
		this.sinistresPayes = sinistresPayes;
	}

	@Column(name = "FRAISACCESSOIRES", precision = 15, scale = 3)
	public BigDecimal getFraisaccessoires() {
		return this.fraisaccessoires;
	}

	public void setFraisaccessoires(BigDecimal fraisaccessoires) {
		this.fraisaccessoires = fraisaccessoires;
	}

	@Column(name = "PARTICIPEXCEDDEB", precision = 15, scale = 3)
	public BigDecimal getParticipexceddeb() {
		return this.participexceddeb;
	}

	public void setParticipexceddeb(BigDecimal participexceddeb) {
		this.participexceddeb = participexceddeb;
	}

	@Column(name = "ADEDRRECRSDEB", precision = 15, scale = 3)
	public BigDecimal getAdedrrecrsdeb() {
		return this.adedrrecrsdeb;
	}

	public void setAdedrrecrsdeb(BigDecimal adedrrecrsdeb) {
		this.adedrrecrsdeb = adedrrecrsdeb;
	}

	@Column(name = "ARRAPCSTITUTIONDEB", precision = 15, scale = 3)
	public BigDecimal getArrapcstitutiondeb() {
		return this.arrapcstitutiondeb;
	}

	public void setArrapcstitutiondeb(BigDecimal arrapcstitutiondeb) {
		this.arrapcstitutiondeb = arrapcstitutiondeb;
	}

	@Column(name = "PRESTAACCPAIDDEB", precision = 15, scale = 3)
	public BigDecimal getPrestaaccpaiddeb() {
		return this.prestaaccpaiddeb;
	}

	public void setPrestaaccpaiddeb(BigDecimal prestaaccpaiddeb) {
		this.prestaaccpaiddeb = prestaaccpaiddeb;
	}

	@Column(name = "PROVSINM31DECPRDEB", precision = 15, scale = 3)
	public BigDecimal getProvsinm31decprdeb() {
		return this.provsinm31decprdeb;
	}

	public void setProvsinm31decprdeb(BigDecimal provsinm31decprdeb) {
		this.provsinm31decprdeb = provsinm31decprdeb;
	}

	@Column(name = "PROVSINP31DECDEB", precision = 15, scale = 3)
	public BigDecimal getProvsinp31decdeb() {
		return this.provsinp31decdeb;
	}

	public void setProvsinp31decdeb(BigDecimal provsinp31decdeb) {
		this.provsinp31decdeb = provsinp31decdeb;
	}

	@Column(name = "PROVEXDM31DECPRDEB", precision = 15, scale = 3)
	public BigDecimal getProvexdm31decprdeb() {
		return this.provexdm31decprdeb;
	}

	public void setProvexdm31decprdeb(BigDecimal provexdm31decprdeb) {
		this.provexdm31decprdeb = provexdm31decprdeb;
	}

	@Column(name = "PROVEXDP31DECDEB", precision = 15, scale = 3)
	public BigDecimal getProvexdp31decdeb() {
		return this.provexdp31decdeb;
	}

	public void setProvexdp31decdeb(BigDecimal provexdp31decdeb) {
		this.provexdp31decdeb = provexdp31decdeb;
	}

	@Column(name = "PREVRCRSM31DECPRDEB", precision = 15, scale = 3)
	public BigDecimal getPrevrcrsm31decprdeb() {
		return this.prevrcrsm31decprdeb;
	}

	public void setPrevrcrsm31decprdeb(BigDecimal prevrcrsm31decprdeb) {
		this.prevrcrsm31decprdeb = prevrcrsm31decprdeb;
	}

	@Column(name = "PREVRCRSP31DECDEB", precision = 15, scale = 3)
	public BigDecimal getPrevrcrsp31decdeb() {
		return this.prevrcrsp31decdeb;
	}

	public void setPrevrcrsp31decdeb(BigDecimal prevrcrsp31decdeb) {
		this.prevrcrsp31decdeb = prevrcrsp31decdeb;
	}

	@Column(name = "PROVMATHM31DECPRDEB", precision = 15, scale = 3)
	public BigDecimal getProvmathm31decprdeb() {
		return this.provmathm31decprdeb;
	}

	public void setProvmathm31decprdeb(BigDecimal provmathm31decprdeb) {
		this.provmathm31decprdeb = provmathm31decprdeb;
	}

	@Column(name = "PROVMATHP31DECDEB", precision = 15, scale = 3)
	public BigDecimal getProvmathp31decdeb() {
		return this.provmathp31decdeb;
	}

	public void setProvmathp31decdeb(BigDecimal provmathp31decdeb) {
		this.provmathp31decdeb = provmathp31decdeb;
	}

	@Column(name = "DOTPROVPRESFRAISDEB", precision = 15, scale = 3)
	public BigDecimal getDotprovpresfraisdeb() {
		return this.dotprovpresfraisdeb;
	}

	public void setDotprovpresfraisdeb(BigDecimal dotprovpresfraisdeb) {
		this.dotprovpresfraisdeb = dotprovpresfraisdeb;
	}

	@Column(name = "COMMISSIONSDEB", precision = 15, scale = 3)
	public BigDecimal getCommissionsdeb() {
		return this.commissionsdeb;
	}

	public void setCommissionsdeb(BigDecimal commissionsdeb) {
		this.commissionsdeb = commissionsdeb;
	}

	@Column(name = "AUTRESCHARGESDEB", precision = 15, scale = 3)
	public BigDecimal getAutreschargesdeb() {
		return this.autreschargesdeb;
	}

	public void setAutreschargesdeb(BigDecimal autreschargesdeb) {
		this.autreschargesdeb = autreschargesdeb;
	}

	@Column(name = "PRIMCEDDEB", precision = 15, scale = 3)
	public BigDecimal getPrimceddeb() {
		return this.primceddeb;
	}

	public void setPrimceddeb(BigDecimal primceddeb) {
		this.primceddeb = primceddeb;
	}

	@Column(name = "PROVPRMREASM31DECPRDEB", precision = 15, scale = 3)
	public BigDecimal getProvprmreasm31decprdeb() {
		return this.provprmreasm31decprdeb;
	}

	public void setProvprmreasm31decprdeb(BigDecimal provprmreasm31decprdeb) {
		this.provprmreasm31decprdeb = provprmreasm31decprdeb;
	}

	@Column(name = "PROVPRMREASP31DECDEB", precision = 15, scale = 3)
	public BigDecimal getProvprmreasp31decdeb() {
		return this.provprmreasp31decdeb;
	}

	public void setProvprmreasp31decdeb(BigDecimal provprmreasp31decdeb) {
		this.provprmreasp31decdeb = provprmreasp31decdeb;
	}

	@Column(name = "PRMACQUIREASDEB", precision = 15, scale = 3)
	public BigDecimal getPrmacquireasdeb() {
		return this.prmacquireasdeb;
	}

	public void setPrmacquireasdeb(BigDecimal prmacquireasdeb) {
		this.prmacquireasdeb = prmacquireasdeb;
	}

	@Column(name = "SOLDCREDITEUR", precision = 15, scale = 3)
	public BigDecimal getSoldcrediteur() {
		return this.soldcrediteur;
	}

	public void setSoldcrediteur(BigDecimal soldcrediteur) {
		this.soldcrediteur = soldcrediteur;
	}

	@Column(name = "TOTALDEB", precision = 15, scale = 3)
	public BigDecimal getTotaldeb() {
		return this.totaldeb;
	}

	public void setTotaldeb(BigDecimal totaldeb) {
		this.totaldeb = totaldeb;
	}

}
