package com.j3a.assurance.model;

// Generated 12 ao�t 2015 16:21:18 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * RcTarif3 generated by hbm2java
 */
@Entity
@Table(name = "rc_tarif3", catalog = "zeusbd")
public class RcTarif3 implements java.io.Serializable {

	private String codeRcTarif3;
	private BigDecimal pfCu1Zone1t3;
	private BigDecimal pfCu2Zone1t3;
	private BigDecimal pfCu3Zone1t3;
	private BigDecimal pfCu4Zone1t3;
	private BigDecimal pfCu5Zone1t3;
	private BigDecimal pfCu6Zone1t3;
	private BigDecimal pfCu7Zone1t3;
	private BigDecimal pfCu1Zone2t3;
	private BigDecimal pfCu2Zone2t3;
	private BigDecimal pfCu3Zone2t3;
	private BigDecimal pfCu4Zone2t3;
	private BigDecimal pfCu5Zone2t3;
	private BigDecimal pfCu6Zone2t3;
	private BigDecimal pfCu7Zone2t3;
	private BigDecimal pfCu1Zone3t3;
	private BigDecimal pfCu2Zone3t3;
	private BigDecimal pfCu3Zone3t3;
	private BigDecimal pfCu4Zone3t3;
	private BigDecimal pfCu5Zone3t3;
	private BigDecimal pfCu6Zone3t3;
	private BigDecimal pfCu7Zone3t3;
	private Float tauxRemorquet3;
	private Float tauxTranspHydrocar3t3;
	private Float tauxMajredRemorquet3;
	private Float tauxMajredPermi1t3;
	private Float tauxMajredPermis2t3;
	private Float tauxMajredStatutproat3;
	private Float tauxMajredStatutprobt3;
	private Float tauxMajredStatutproct3;
	private Float tauxMajredStatutprodt3;
	private Float tauxMajredStatutproet3;
	private Float tauxMajredStatutproautrest3;
	private Set<Tarif> tarifs = new HashSet<Tarif>(0);

	public RcTarif3() {
	}

	public RcTarif3(String codeRcTarif3) {
		this.codeRcTarif3 = codeRcTarif3;
	}

	public RcTarif3(String codeRcTarif3, BigDecimal pfCu1Zone1t3,
			BigDecimal pfCu2Zone1t3, BigDecimal pfCu3Zone1t3,
			BigDecimal pfCu4Zone1t3, BigDecimal pfCu5Zone1t3,
			BigDecimal pfCu6Zone1t3, BigDecimal pfCu7Zone1t3,
			BigDecimal pfCu1Zone2t3, BigDecimal pfCu2Zone2t3,
			BigDecimal pfCu3Zone2t3, BigDecimal pfCu4Zone2t3,
			BigDecimal pfCu5Zone2t3, BigDecimal pfCu6Zone2t3,
			BigDecimal pfCu7Zone2t3, BigDecimal pfCu1Zone3t3,
			BigDecimal pfCu2Zone3t3, BigDecimal pfCu3Zone3t3,
			BigDecimal pfCu4Zone3t3, BigDecimal pfCu5Zone3t3,
			BigDecimal pfCu6Zone3t3, BigDecimal pfCu7Zone3t3,
			Float tauxRemorquet3, Float tauxTranspHydrocar3t3,
			Float tauxMajredRemorquet3, Float tauxMajredPermi1t3,
			Float tauxMajredPermis2t3, Float tauxMajredStatutproat3,
			Float tauxMajredStatutprobt3, Float tauxMajredStatutproct3,
			Float tauxMajredStatutprodt3, Float tauxMajredStatutproet3,
			Float tauxMajredStatutproautrest3, Set<Tarif> tarifs) {
		this.codeRcTarif3 = codeRcTarif3;
		this.pfCu1Zone1t3 = pfCu1Zone1t3;
		this.pfCu2Zone1t3 = pfCu2Zone1t3;
		this.pfCu3Zone1t3 = pfCu3Zone1t3;
		this.pfCu4Zone1t3 = pfCu4Zone1t3;
		this.pfCu5Zone1t3 = pfCu5Zone1t3;
		this.pfCu6Zone1t3 = pfCu6Zone1t3;
		this.pfCu7Zone1t3 = pfCu7Zone1t3;
		this.pfCu1Zone2t3 = pfCu1Zone2t3;
		this.pfCu2Zone2t3 = pfCu2Zone2t3;
		this.pfCu3Zone2t3 = pfCu3Zone2t3;
		this.pfCu4Zone2t3 = pfCu4Zone2t3;
		this.pfCu5Zone2t3 = pfCu5Zone2t3;
		this.pfCu6Zone2t3 = pfCu6Zone2t3;
		this.pfCu7Zone2t3 = pfCu7Zone2t3;
		this.pfCu1Zone3t3 = pfCu1Zone3t3;
		this.pfCu2Zone3t3 = pfCu2Zone3t3;
		this.pfCu3Zone3t3 = pfCu3Zone3t3;
		this.pfCu4Zone3t3 = pfCu4Zone3t3;
		this.pfCu5Zone3t3 = pfCu5Zone3t3;
		this.pfCu6Zone3t3 = pfCu6Zone3t3;
		this.pfCu7Zone3t3 = pfCu7Zone3t3;
		this.tauxRemorquet3 = tauxRemorquet3;
		this.tauxTranspHydrocar3t3 = tauxTranspHydrocar3t3;
		this.tauxMajredRemorquet3 = tauxMajredRemorquet3;
		this.tauxMajredPermi1t3 = tauxMajredPermi1t3;
		this.tauxMajredPermis2t3 = tauxMajredPermis2t3;
		this.tauxMajredStatutproat3 = tauxMajredStatutproat3;
		this.tauxMajredStatutprobt3 = tauxMajredStatutprobt3;
		this.tauxMajredStatutproct3 = tauxMajredStatutproct3;
		this.tauxMajredStatutprodt3 = tauxMajredStatutprodt3;
		this.tauxMajredStatutproet3 = tauxMajredStatutproet3;
		this.tauxMajredStatutproautrest3 = tauxMajredStatutproautrest3;
		this.tarifs = tarifs;
	}

	@Id
	@Column(name = "CODE_RC_TARIF3", unique = true, nullable = false, length = 16)
	public String getCodeRcTarif3() {
		return this.codeRcTarif3;
	}

	public void setCodeRcTarif3(String codeRcTarif3) {
		this.codeRcTarif3 = codeRcTarif3;
	}

	@Column(name = "PF_CU1_ZONE1T3", precision = 15, scale = 3)
	public BigDecimal getPfCu1Zone1t3() {
		return this.pfCu1Zone1t3;
	}

	public void setPfCu1Zone1t3(BigDecimal pfCu1Zone1t3) {
		this.pfCu1Zone1t3 = pfCu1Zone1t3;
	}

	@Column(name = "PF_CU2_ZONE1T3", precision = 15, scale = 3)
	public BigDecimal getPfCu2Zone1t3() {
		return this.pfCu2Zone1t3;
	}

	public void setPfCu2Zone1t3(BigDecimal pfCu2Zone1t3) {
		this.pfCu2Zone1t3 = pfCu2Zone1t3;
	}

	@Column(name = "PF_CU3_ZONE1T3", precision = 15, scale = 3)
	public BigDecimal getPfCu3Zone1t3() {
		return this.pfCu3Zone1t3;
	}

	public void setPfCu3Zone1t3(BigDecimal pfCu3Zone1t3) {
		this.pfCu3Zone1t3 = pfCu3Zone1t3;
	}

	@Column(name = "PF_CU4_ZONE1T3", precision = 15, scale = 3)
	public BigDecimal getPfCu4Zone1t3() {
		return this.pfCu4Zone1t3;
	}

	public void setPfCu4Zone1t3(BigDecimal pfCu4Zone1t3) {
		this.pfCu4Zone1t3 = pfCu4Zone1t3;
	}

	@Column(name = "PF_CU5_ZONE1T3", precision = 15, scale = 3)
	public BigDecimal getPfCu5Zone1t3() {
		return this.pfCu5Zone1t3;
	}

	public void setPfCu5Zone1t3(BigDecimal pfCu5Zone1t3) {
		this.pfCu5Zone1t3 = pfCu5Zone1t3;
	}

	@Column(name = "PF_CU6_ZONE1T3", precision = 15, scale = 3)
	public BigDecimal getPfCu6Zone1t3() {
		return this.pfCu6Zone1t3;
	}

	public void setPfCu6Zone1t3(BigDecimal pfCu6Zone1t3) {
		this.pfCu6Zone1t3 = pfCu6Zone1t3;
	}

	@Column(name = "PF_CU7_ZONE1T3", precision = 15, scale = 3)
	public BigDecimal getPfCu7Zone1t3() {
		return this.pfCu7Zone1t3;
	}

	public void setPfCu7Zone1t3(BigDecimal pfCu7Zone1t3) {
		this.pfCu7Zone1t3 = pfCu7Zone1t3;
	}

	@Column(name = "PF_CU1_ZONE2T3", precision = 15, scale = 3)
	public BigDecimal getPfCu1Zone2t3() {
		return this.pfCu1Zone2t3;
	}

	public void setPfCu1Zone2t3(BigDecimal pfCu1Zone2t3) {
		this.pfCu1Zone2t3 = pfCu1Zone2t3;
	}

	@Column(name = "PF_CU2_ZONE2T3", precision = 15, scale = 3)
	public BigDecimal getPfCu2Zone2t3() {
		return this.pfCu2Zone2t3;
	}

	public void setPfCu2Zone2t3(BigDecimal pfCu2Zone2t3) {
		this.pfCu2Zone2t3 = pfCu2Zone2t3;
	}

	@Column(name = "PF_CU3_ZONE2T3", precision = 15, scale = 3)
	public BigDecimal getPfCu3Zone2t3() {
		return this.pfCu3Zone2t3;
	}

	public void setPfCu3Zone2t3(BigDecimal pfCu3Zone2t3) {
		this.pfCu3Zone2t3 = pfCu3Zone2t3;
	}

	@Column(name = "PF_CU4_ZONE2T3", precision = 15, scale = 3)
	public BigDecimal getPfCu4Zone2t3() {
		return this.pfCu4Zone2t3;
	}

	public void setPfCu4Zone2t3(BigDecimal pfCu4Zone2t3) {
		this.pfCu4Zone2t3 = pfCu4Zone2t3;
	}

	@Column(name = "PF_CU5_ZONE2T3", precision = 15, scale = 3)
	public BigDecimal getPfCu5Zone2t3() {
		return this.pfCu5Zone2t3;
	}

	public void setPfCu5Zone2t3(BigDecimal pfCu5Zone2t3) {
		this.pfCu5Zone2t3 = pfCu5Zone2t3;
	}

	@Column(name = "PF_CU6_ZONE2T3", precision = 15, scale = 3)
	public BigDecimal getPfCu6Zone2t3() {
		return this.pfCu6Zone2t3;
	}

	public void setPfCu6Zone2t3(BigDecimal pfCu6Zone2t3) {
		this.pfCu6Zone2t3 = pfCu6Zone2t3;
	}

	@Column(name = "PF_CU7_ZONE2T3", precision = 15, scale = 3)
	public BigDecimal getPfCu7Zone2t3() {
		return this.pfCu7Zone2t3;
	}

	public void setPfCu7Zone2t3(BigDecimal pfCu7Zone2t3) {
		this.pfCu7Zone2t3 = pfCu7Zone2t3;
	}

	@Column(name = "PF_CU1_ZONE3T3", precision = 15, scale = 3)
	public BigDecimal getPfCu1Zone3t3() {
		return this.pfCu1Zone3t3;
	}

	public void setPfCu1Zone3t3(BigDecimal pfCu1Zone3t3) {
		this.pfCu1Zone3t3 = pfCu1Zone3t3;
	}

	@Column(name = "PF_CU2_ZONE3T3", precision = 15, scale = 3)
	public BigDecimal getPfCu2Zone3t3() {
		return this.pfCu2Zone3t3;
	}

	public void setPfCu2Zone3t3(BigDecimal pfCu2Zone3t3) {
		this.pfCu2Zone3t3 = pfCu2Zone3t3;
	}

	@Column(name = "PF_CU3_ZONE3T3", precision = 15, scale = 3)
	public BigDecimal getPfCu3Zone3t3() {
		return this.pfCu3Zone3t3;
	}

	public void setPfCu3Zone3t3(BigDecimal pfCu3Zone3t3) {
		this.pfCu3Zone3t3 = pfCu3Zone3t3;
	}

	@Column(name = "PF_CU4_ZONE3T3", precision = 15, scale = 3)
	public BigDecimal getPfCu4Zone3t3() {
		return this.pfCu4Zone3t3;
	}

	public void setPfCu4Zone3t3(BigDecimal pfCu4Zone3t3) {
		this.pfCu4Zone3t3 = pfCu4Zone3t3;
	}

	@Column(name = "PF_CU5_ZONE3T3", precision = 15, scale = 3)
	public BigDecimal getPfCu5Zone3t3() {
		return this.pfCu5Zone3t3;
	}

	public void setPfCu5Zone3t3(BigDecimal pfCu5Zone3t3) {
		this.pfCu5Zone3t3 = pfCu5Zone3t3;
	}

	@Column(name = "PF_CU6_ZONE3T3", precision = 15, scale = 3)
	public BigDecimal getPfCu6Zone3t3() {
		return this.pfCu6Zone3t3;
	}

	public void setPfCu6Zone3t3(BigDecimal pfCu6Zone3t3) {
		this.pfCu6Zone3t3 = pfCu6Zone3t3;
	}

	@Column(name = "PF_CU7_ZONE3T3", precision = 15, scale = 3)
	public BigDecimal getPfCu7Zone3t3() {
		return this.pfCu7Zone3t3;
	}

	public void setPfCu7Zone3t3(BigDecimal pfCu7Zone3t3) {
		this.pfCu7Zone3t3 = pfCu7Zone3t3;
	}

	@Column(name = "TAUX_REMORQUET3", precision = 12, scale = 0)
	public Float getTauxRemorquet3() {
		return this.tauxRemorquet3;
	}

	public void setTauxRemorquet3(Float tauxRemorquet3) {
		this.tauxRemorquet3 = tauxRemorquet3;
	}

	@Column(name = "TAUX_TRANSP_HYDROCAR3T3", precision = 12, scale = 0)
	public Float getTauxTranspHydrocar3t3() {
		return this.tauxTranspHydrocar3t3;
	}

	public void setTauxTranspHydrocar3t3(Float tauxTranspHydrocar3t3) {
		this.tauxTranspHydrocar3t3 = tauxTranspHydrocar3t3;
	}

	@Column(name = "TAUX_MAJRED_REMORQUET3", precision = 12, scale = 0)
	public Float getTauxMajredRemorquet3() {
		return this.tauxMajredRemorquet3;
	}

	public void setTauxMajredRemorquet3(Float tauxMajredRemorquet3) {
		this.tauxMajredRemorquet3 = tauxMajredRemorquet3;
	}

	@Column(name = "TAUX_MAJRED_PERMI1T3", precision = 12, scale = 0)
	public Float getTauxMajredPermi1t3() {
		return this.tauxMajredPermi1t3;
	}

	public void setTauxMajredPermi1t3(Float tauxMajredPermi1t3) {
		this.tauxMajredPermi1t3 = tauxMajredPermi1t3;
	}

	@Column(name = "TAUX_MAJRED_PERMIS2T3", precision = 12, scale = 0)
	public Float getTauxMajredPermis2t3() {
		return this.tauxMajredPermis2t3;
	}

	public void setTauxMajredPermis2t3(Float tauxMajredPermis2t3) {
		this.tauxMajredPermis2t3 = tauxMajredPermis2t3;
	}

	@Column(name = "TAUX_MAJRED_STATUTPROAT3", precision = 12, scale = 0)
	public Float getTauxMajredStatutproat3() {
		return this.tauxMajredStatutproat3;
	}

	public void setTauxMajredStatutproat3(Float tauxMajredStatutproat3) {
		this.tauxMajredStatutproat3 = tauxMajredStatutproat3;
	}

	@Column(name = "TAUX_MAJRED_STATUTPROBT3", precision = 12, scale = 0)
	public Float getTauxMajredStatutprobt3() {
		return this.tauxMajredStatutprobt3;
	}

	public void setTauxMajredStatutprobt3(Float tauxMajredStatutprobt3) {
		this.tauxMajredStatutprobt3 = tauxMajredStatutprobt3;
	}

	@Column(name = "TAUX_MAJRED_STATUTPROCT3", precision = 12, scale = 0)
	public Float getTauxMajredStatutproct3() {
		return this.tauxMajredStatutproct3;
	}

	public void setTauxMajredStatutproct3(Float tauxMajredStatutproct3) {
		this.tauxMajredStatutproct3 = tauxMajredStatutproct3;
	}

	@Column(name = "TAUX_MAJRED_STATUTPRODT3", precision = 12, scale = 0)
	public Float getTauxMajredStatutprodt3() {
		return this.tauxMajredStatutprodt3;
	}

	public void setTauxMajredStatutprodt3(Float tauxMajredStatutprodt3) {
		this.tauxMajredStatutprodt3 = tauxMajredStatutprodt3;
	}

	@Column(name = "TAUX_MAJRED_STATUTPROET3", precision = 12, scale = 0)
	public Float getTauxMajredStatutproet3() {
		return this.tauxMajredStatutproet3;
	}

	public void setTauxMajredStatutproet3(Float tauxMajredStatutproet3) {
		this.tauxMajredStatutproet3 = tauxMajredStatutproet3;
	}

	@Column(name = "TAUX_MAJRED_STATUTPROAUTREST3", precision = 12, scale = 0)
	public Float getTauxMajredStatutproautrest3() {
		return this.tauxMajredStatutproautrest3;
	}

	public void setTauxMajredStatutproautrest3(Float tauxMajredStatutproautrest3) {
		this.tauxMajredStatutproautrest3 = tauxMajredStatutproautrest3;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rcTarif3")
	public Set<Tarif> getTarifs() {
		return this.tarifs;
	}

	public void setTarifs(Set<Tarif> tarifs) {
		this.tarifs = tarifs;
	}

}
