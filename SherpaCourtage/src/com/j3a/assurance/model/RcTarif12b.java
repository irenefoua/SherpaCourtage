package com.j3a.assurance.model;

// Generated 10 ao�t 2015 15:05:20 by Hibernate Tools 4.3.1

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
 * RcTarif12b generated by hbm2java
 */
@Entity
@Table(name = "rc_tarif12b", catalog = "zeusbd")
public class RcTarif12b implements java.io.Serializable {

	private String codeRcTarif12b;
	private BigDecimal pfCu1t12bZone1;
	private BigDecimal pfCu2t12bZone1;
	private BigDecimal pfCu3t12bZone1;
	private BigDecimal pfCu4t12bZone1;
	private BigDecimal pfCu5t12bZone1;
	private BigDecimal pfCu6t12bZone1;
	private BigDecimal pfCu7t12bZone1;
	private BigDecimal pfCu8t12bZone1;
	private BigDecimal pfCu9t12bZone1;
	private BigDecimal pfCu1t12bZone2;
	private BigDecimal pfCu2t12bZone2;
	private BigDecimal pfCu3t12bZone2;
	private BigDecimal pfCu4t12bZone2;
	private BigDecimal pfCu5t12bZone2;
	private BigDecimal pfCu6t12bZone2;
	private BigDecimal pfCu7t12bZone2;
	private BigDecimal pfCu8t12bZone2;
	private BigDecimal pfCu9t12bZone2;
	private BigDecimal pfCu1t12bZone3;
	private BigDecimal pfCu2t12bZone3;
	private BigDecimal pfCu3t12bZone3;
	private BigDecimal pfCu4t12bZone3;
	private BigDecimal pfCu5t12bZone3;
	private BigDecimal pfCu6t12bZone3;
	private BigDecimal pfCu7t12bZone3;
	private BigDecimal pfCu8t12bZone3;
	private BigDecimal pfCu9t12bZone3;
	private BigDecimal surprimePassSupHcabt12b;
	private BigDecimal surprimeTranspElevt12b;
	private BigDecimal surprimeTranspCamt12b;
	private BigDecimal surpimeTranspAutocart12b;
	private Float tauxPassagerClandt12b;
	private Float tauxTranspHydrocart12b;
	private Float tauxMajredRemorquet12b;
	private Float tauxMajredPermi1t12b;
	private Float tauxMajredPermist12b;
	private Float tauxMajredStatutproat12b;
	private Float tauxMajredStatutprobt12b;
	private Float tauxMajredStatutproct12b;
	private Float tauxMajredStatutproautrest12b;
	private Set<Tarif> tarifs = new HashSet<Tarif>(0);

	public RcTarif12b() {
	}

	public RcTarif12b(String codeRcTarif12b) {
		this.codeRcTarif12b = codeRcTarif12b;
	}

	public RcTarif12b(String codeRcTarif12b, BigDecimal pfCu1t12bZone1,
			BigDecimal pfCu2t12bZone1, BigDecimal pfCu3t12bZone1,
			BigDecimal pfCu4t12bZone1, BigDecimal pfCu5t12bZone1,
			BigDecimal pfCu6t12bZone1, BigDecimal pfCu7t12bZone1,
			BigDecimal pfCu8t12bZone1, BigDecimal pfCu9t12bZone1,
			BigDecimal pfCu1t12bZone2, BigDecimal pfCu2t12bZone2,
			BigDecimal pfCu3t12bZone2, BigDecimal pfCu4t12bZone2,
			BigDecimal pfCu5t12bZone2, BigDecimal pfCu6t12bZone2,
			BigDecimal pfCu7t12bZone2, BigDecimal pfCu8t12bZone2,
			BigDecimal pfCu9t12bZone2, BigDecimal pfCu1t12bZone3,
			BigDecimal pfCu2t12bZone3, BigDecimal pfCu3t12bZone3,
			BigDecimal pfCu4t12bZone3, BigDecimal pfCu5t12bZone3,
			BigDecimal pfCu6t12bZone3, BigDecimal pfCu7t12bZone3,
			BigDecimal pfCu8t12bZone3, BigDecimal pfCu9t12bZone3,
			BigDecimal surprimePassSupHcabt12b,
			BigDecimal surprimeTranspElevt12b,
			BigDecimal surprimeTranspCamt12b,
			BigDecimal surpimeTranspAutocart12b, Float tauxPassagerClandt12b,
			Float tauxTranspHydrocart12b, Float tauxMajredRemorquet12b,
			Float tauxMajredPermi1t12b, Float tauxMajredPermist12b,
			Float tauxMajredStatutproat12b, Float tauxMajredStatutprobt12b,
			Float tauxMajredStatutproct12b,
			Float tauxMajredStatutproautrest12b, Set<Tarif> tarifs) {
		this.codeRcTarif12b = codeRcTarif12b;
		this.pfCu1t12bZone1 = pfCu1t12bZone1;
		this.pfCu2t12bZone1 = pfCu2t12bZone1;
		this.pfCu3t12bZone1 = pfCu3t12bZone1;
		this.pfCu4t12bZone1 = pfCu4t12bZone1;
		this.pfCu5t12bZone1 = pfCu5t12bZone1;
		this.pfCu6t12bZone1 = pfCu6t12bZone1;
		this.pfCu7t12bZone1 = pfCu7t12bZone1;
		this.pfCu8t12bZone1 = pfCu8t12bZone1;
		this.pfCu9t12bZone1 = pfCu9t12bZone1;
		this.pfCu1t12bZone2 = pfCu1t12bZone2;
		this.pfCu2t12bZone2 = pfCu2t12bZone2;
		this.pfCu3t12bZone2 = pfCu3t12bZone2;
		this.pfCu4t12bZone2 = pfCu4t12bZone2;
		this.pfCu5t12bZone2 = pfCu5t12bZone2;
		this.pfCu6t12bZone2 = pfCu6t12bZone2;
		this.pfCu7t12bZone2 = pfCu7t12bZone2;
		this.pfCu8t12bZone2 = pfCu8t12bZone2;
		this.pfCu9t12bZone2 = pfCu9t12bZone2;
		this.pfCu1t12bZone3 = pfCu1t12bZone3;
		this.pfCu2t12bZone3 = pfCu2t12bZone3;
		this.pfCu3t12bZone3 = pfCu3t12bZone3;
		this.pfCu4t12bZone3 = pfCu4t12bZone3;
		this.pfCu5t12bZone3 = pfCu5t12bZone3;
		this.pfCu6t12bZone3 = pfCu6t12bZone3;
		this.pfCu7t12bZone3 = pfCu7t12bZone3;
		this.pfCu8t12bZone3 = pfCu8t12bZone3;
		this.pfCu9t12bZone3 = pfCu9t12bZone3;
		this.surprimePassSupHcabt12b = surprimePassSupHcabt12b;
		this.surprimeTranspElevt12b = surprimeTranspElevt12b;
		this.surprimeTranspCamt12b = surprimeTranspCamt12b;
		this.surpimeTranspAutocart12b = surpimeTranspAutocart12b;
		this.tauxPassagerClandt12b = tauxPassagerClandt12b;
		this.tauxTranspHydrocart12b = tauxTranspHydrocart12b;
		this.tauxMajredRemorquet12b = tauxMajredRemorquet12b;
		this.tauxMajredPermi1t12b = tauxMajredPermi1t12b;
		this.tauxMajredPermist12b = tauxMajredPermist12b;
		this.tauxMajredStatutproat12b = tauxMajredStatutproat12b;
		this.tauxMajredStatutprobt12b = tauxMajredStatutprobt12b;
		this.tauxMajredStatutproct12b = tauxMajredStatutproct12b;
		this.tauxMajredStatutproautrest12b = tauxMajredStatutproautrest12b;
		this.tarifs = tarifs;
	}

	@Id
	@Column(name = "CODE_RC_TARIF12B", unique = true, nullable = false, length = 16)
	public String getCodeRcTarif12b() {
		return this.codeRcTarif12b;
	}

	public void setCodeRcTarif12b(String codeRcTarif12b) {
		this.codeRcTarif12b = codeRcTarif12b;
	}

	@Column(name = "PF_CU1T12B_ZONE1", precision = 15, scale = 3)
	public BigDecimal getPfCu1t12bZone1() {
		return this.pfCu1t12bZone1;
	}

	public void setPfCu1t12bZone1(BigDecimal pfCu1t12bZone1) {
		this.pfCu1t12bZone1 = pfCu1t12bZone1;
	}

	@Column(name = "PF_CU2T12B_ZONE1", precision = 15, scale = 3)
	public BigDecimal getPfCu2t12bZone1() {
		return this.pfCu2t12bZone1;
	}

	public void setPfCu2t12bZone1(BigDecimal pfCu2t12bZone1) {
		this.pfCu2t12bZone1 = pfCu2t12bZone1;
	}

	@Column(name = "PF_CU3T12B_ZONE1", precision = 15, scale = 3)
	public BigDecimal getPfCu3t12bZone1() {
		return this.pfCu3t12bZone1;
	}

	public void setPfCu3t12bZone1(BigDecimal pfCu3t12bZone1) {
		this.pfCu3t12bZone1 = pfCu3t12bZone1;
	}

	@Column(name = "PF_CU4T12B_ZONE1", precision = 15, scale = 3)
	public BigDecimal getPfCu4t12bZone1() {
		return this.pfCu4t12bZone1;
	}

	public void setPfCu4t12bZone1(BigDecimal pfCu4t12bZone1) {
		this.pfCu4t12bZone1 = pfCu4t12bZone1;
	}

	@Column(name = "PF_CU5T12B_ZONE1", precision = 15, scale = 3)
	public BigDecimal getPfCu5t12bZone1() {
		return this.pfCu5t12bZone1;
	}

	public void setPfCu5t12bZone1(BigDecimal pfCu5t12bZone1) {
		this.pfCu5t12bZone1 = pfCu5t12bZone1;
	}

	@Column(name = "PF_CU6T12B_ZONE1", precision = 15, scale = 3)
	public BigDecimal getPfCu6t12bZone1() {
		return this.pfCu6t12bZone1;
	}

	public void setPfCu6t12bZone1(BigDecimal pfCu6t12bZone1) {
		this.pfCu6t12bZone1 = pfCu6t12bZone1;
	}

	@Column(name = "PF_CU7T12B_ZONE1", precision = 15, scale = 3)
	public BigDecimal getPfCu7t12bZone1() {
		return this.pfCu7t12bZone1;
	}

	public void setPfCu7t12bZone1(BigDecimal pfCu7t12bZone1) {
		this.pfCu7t12bZone1 = pfCu7t12bZone1;
	}

	@Column(name = "PF_CU8T12B_ZONE1", precision = 15, scale = 3)
	public BigDecimal getPfCu8t12bZone1() {
		return this.pfCu8t12bZone1;
	}

	public void setPfCu8t12bZone1(BigDecimal pfCu8t12bZone1) {
		this.pfCu8t12bZone1 = pfCu8t12bZone1;
	}

	@Column(name = "PF_CU9T12B_ZONE1", precision = 15, scale = 3)
	public BigDecimal getPfCu9t12bZone1() {
		return this.pfCu9t12bZone1;
	}

	public void setPfCu9t12bZone1(BigDecimal pfCu9t12bZone1) {
		this.pfCu9t12bZone1 = pfCu9t12bZone1;
	}

	@Column(name = "PF_CU1T12B_ZONE2", precision = 15, scale = 3)
	public BigDecimal getPfCu1t12bZone2() {
		return this.pfCu1t12bZone2;
	}

	public void setPfCu1t12bZone2(BigDecimal pfCu1t12bZone2) {
		this.pfCu1t12bZone2 = pfCu1t12bZone2;
	}

	@Column(name = "PF_CU2T12B_ZONE2", precision = 15, scale = 3)
	public BigDecimal getPfCu2t12bZone2() {
		return this.pfCu2t12bZone2;
	}

	public void setPfCu2t12bZone2(BigDecimal pfCu2t12bZone2) {
		this.pfCu2t12bZone2 = pfCu2t12bZone2;
	}

	@Column(name = "PF_CU3T12B_ZONE2", precision = 15, scale = 3)
	public BigDecimal getPfCu3t12bZone2() {
		return this.pfCu3t12bZone2;
	}

	public void setPfCu3t12bZone2(BigDecimal pfCu3t12bZone2) {
		this.pfCu3t12bZone2 = pfCu3t12bZone2;
	}

	@Column(name = "PF_CU4T12B_ZONE2", precision = 15, scale = 3)
	public BigDecimal getPfCu4t12bZone2() {
		return this.pfCu4t12bZone2;
	}

	public void setPfCu4t12bZone2(BigDecimal pfCu4t12bZone2) {
		this.pfCu4t12bZone2 = pfCu4t12bZone2;
	}

	@Column(name = "PF_CU5T12B_ZONE2", precision = 15, scale = 3)
	public BigDecimal getPfCu5t12bZone2() {
		return this.pfCu5t12bZone2;
	}

	public void setPfCu5t12bZone2(BigDecimal pfCu5t12bZone2) {
		this.pfCu5t12bZone2 = pfCu5t12bZone2;
	}

	@Column(name = "PF_CU6T12B_ZONE2", precision = 15, scale = 3)
	public BigDecimal getPfCu6t12bZone2() {
		return this.pfCu6t12bZone2;
	}

	public void setPfCu6t12bZone2(BigDecimal pfCu6t12bZone2) {
		this.pfCu6t12bZone2 = pfCu6t12bZone2;
	}

	@Column(name = "PF_CU7T12B_ZONE2", precision = 15, scale = 3)
	public BigDecimal getPfCu7t12bZone2() {
		return this.pfCu7t12bZone2;
	}

	public void setPfCu7t12bZone2(BigDecimal pfCu7t12bZone2) {
		this.pfCu7t12bZone2 = pfCu7t12bZone2;
	}

	@Column(name = "PF_CU8T12B_ZONE2", precision = 15, scale = 3)
	public BigDecimal getPfCu8t12bZone2() {
		return this.pfCu8t12bZone2;
	}

	public void setPfCu8t12bZone2(BigDecimal pfCu8t12bZone2) {
		this.pfCu8t12bZone2 = pfCu8t12bZone2;
	}

	@Column(name = "PF_CU9T12B_ZONE2", precision = 15, scale = 3)
	public BigDecimal getPfCu9t12bZone2() {
		return this.pfCu9t12bZone2;
	}

	public void setPfCu9t12bZone2(BigDecimal pfCu9t12bZone2) {
		this.pfCu9t12bZone2 = pfCu9t12bZone2;
	}

	@Column(name = "PF_CU1T12B_ZONE3", precision = 15, scale = 3)
	public BigDecimal getPfCu1t12bZone3() {
		return this.pfCu1t12bZone3;
	}

	public void setPfCu1t12bZone3(BigDecimal pfCu1t12bZone3) {
		this.pfCu1t12bZone3 = pfCu1t12bZone3;
	}

	@Column(name = "PF_CU2T12B_ZONE3", precision = 15, scale = 3)
	public BigDecimal getPfCu2t12bZone3() {
		return this.pfCu2t12bZone3;
	}

	public void setPfCu2t12bZone3(BigDecimal pfCu2t12bZone3) {
		this.pfCu2t12bZone3 = pfCu2t12bZone3;
	}

	@Column(name = "PF_CU3T12B_ZONE3", precision = 15, scale = 3)
	public BigDecimal getPfCu3t12bZone3() {
		return this.pfCu3t12bZone3;
	}

	public void setPfCu3t12bZone3(BigDecimal pfCu3t12bZone3) {
		this.pfCu3t12bZone3 = pfCu3t12bZone3;
	}

	@Column(name = "PF_CU4T12B_ZONE3", precision = 15, scale = 3)
	public BigDecimal getPfCu4t12bZone3() {
		return this.pfCu4t12bZone3;
	}

	public void setPfCu4t12bZone3(BigDecimal pfCu4t12bZone3) {
		this.pfCu4t12bZone3 = pfCu4t12bZone3;
	}

	@Column(name = "PF_CU5T12B_ZONE3", precision = 15, scale = 3)
	public BigDecimal getPfCu5t12bZone3() {
		return this.pfCu5t12bZone3;
	}

	public void setPfCu5t12bZone3(BigDecimal pfCu5t12bZone3) {
		this.pfCu5t12bZone3 = pfCu5t12bZone3;
	}

	@Column(name = "PF_CU6T12B_ZONE3", precision = 15, scale = 3)
	public BigDecimal getPfCu6t12bZone3() {
		return this.pfCu6t12bZone3;
	}

	public void setPfCu6t12bZone3(BigDecimal pfCu6t12bZone3) {
		this.pfCu6t12bZone3 = pfCu6t12bZone3;
	}

	@Column(name = "PF_CU7T12B_ZONE3", precision = 15, scale = 3)
	public BigDecimal getPfCu7t12bZone3() {
		return this.pfCu7t12bZone3;
	}

	public void setPfCu7t12bZone3(BigDecimal pfCu7t12bZone3) {
		this.pfCu7t12bZone3 = pfCu7t12bZone3;
	}

	@Column(name = "PF_CU8T12B_ZONE3", precision = 15, scale = 3)
	public BigDecimal getPfCu8t12bZone3() {
		return this.pfCu8t12bZone3;
	}

	public void setPfCu8t12bZone3(BigDecimal pfCu8t12bZone3) {
		this.pfCu8t12bZone3 = pfCu8t12bZone3;
	}

	@Column(name = "PF_CU9T12B_ZONE3", precision = 15, scale = 3)
	public BigDecimal getPfCu9t12bZone3() {
		return this.pfCu9t12bZone3;
	}

	public void setPfCu9t12bZone3(BigDecimal pfCu9t12bZone3) {
		this.pfCu9t12bZone3 = pfCu9t12bZone3;
	}

	@Column(name = "SURPRIME_PASS_SUP_HCABT12B", precision = 15, scale = 3)
	public BigDecimal getSurprimePassSupHcabt12b() {
		return this.surprimePassSupHcabt12b;
	}

	public void setSurprimePassSupHcabt12b(BigDecimal surprimePassSupHcabt12b) {
		this.surprimePassSupHcabt12b = surprimePassSupHcabt12b;
	}

	@Column(name = "SURPRIME_TRANSP_ELEVT12B", precision = 15, scale = 3)
	public BigDecimal getSurprimeTranspElevt12b() {
		return this.surprimeTranspElevt12b;
	}

	public void setSurprimeTranspElevt12b(BigDecimal surprimeTranspElevt12b) {
		this.surprimeTranspElevt12b = surprimeTranspElevt12b;
	}

	@Column(name = "SURPRIME_TRANSP_CAMT12B", precision = 15, scale = 3)
	public BigDecimal getSurprimeTranspCamt12b() {
		return this.surprimeTranspCamt12b;
	}

	public void setSurprimeTranspCamt12b(BigDecimal surprimeTranspCamt12b) {
		this.surprimeTranspCamt12b = surprimeTranspCamt12b;
	}

	@Column(name = "SURPIME_TRANSP_AUTOCART12B", precision = 15, scale = 3)
	public BigDecimal getSurpimeTranspAutocart12b() {
		return this.surpimeTranspAutocart12b;
	}

	public void setSurpimeTranspAutocart12b(BigDecimal surpimeTranspAutocart12b) {
		this.surpimeTranspAutocart12b = surpimeTranspAutocart12b;
	}

	@Column(name = "TAUX_PASSAGER_CLANDT12B", precision = 12, scale = 0)
	public Float getTauxPassagerClandt12b() {
		return this.tauxPassagerClandt12b;
	}

	public void setTauxPassagerClandt12b(Float tauxPassagerClandt12b) {
		this.tauxPassagerClandt12b = tauxPassagerClandt12b;
	}

	@Column(name = "TAUX_TRANSP_HYDROCART12B", precision = 12, scale = 0)
	public Float getTauxTranspHydrocart12b() {
		return this.tauxTranspHydrocart12b;
	}

	public void setTauxTranspHydrocart12b(Float tauxTranspHydrocart12b) {
		this.tauxTranspHydrocart12b = tauxTranspHydrocart12b;
	}

	@Column(name = "TAUX_MAJRED_REMORQUET12B", precision = 12, scale = 0)
	public Float getTauxMajredRemorquet12b() {
		return this.tauxMajredRemorquet12b;
	}

	public void setTauxMajredRemorquet12b(Float tauxMajredRemorquet12b) {
		this.tauxMajredRemorquet12b = tauxMajredRemorquet12b;
	}

	@Column(name = "TAUX_MAJRED_PERMI1T12B", precision = 12, scale = 0)
	public Float getTauxMajredPermi1t12b() {
		return this.tauxMajredPermi1t12b;
	}

	public void setTauxMajredPermi1t12b(Float tauxMajredPermi1t12b) {
		this.tauxMajredPermi1t12b = tauxMajredPermi1t12b;
	}

	@Column(name = "TAUX_MAJRED_PERMIST12B", precision = 12, scale = 0)
	public Float getTauxMajredPermist12b() {
		return this.tauxMajredPermist12b;
	}

	public void setTauxMajredPermist12b(Float tauxMajredPermist12b) {
		this.tauxMajredPermist12b = tauxMajredPermist12b;
	}

	@Column(name = "TAUX_MAJRED_STATUTPROAT12B", precision = 12, scale = 0)
	public Float getTauxMajredStatutproat12b() {
		return this.tauxMajredStatutproat12b;
	}

	public void setTauxMajredStatutproat12b(Float tauxMajredStatutproat12b) {
		this.tauxMajredStatutproat12b = tauxMajredStatutproat12b;
	}

	@Column(name = "TAUX_MAJRED_STATUTPROBT12B", precision = 12, scale = 0)
	public Float getTauxMajredStatutprobt12b() {
		return this.tauxMajredStatutprobt12b;
	}

	public void setTauxMajredStatutprobt12b(Float tauxMajredStatutprobt12b) {
		this.tauxMajredStatutprobt12b = tauxMajredStatutprobt12b;
	}

	@Column(name = "TAUX_MAJRED_STATUTPROCT12B", precision = 12, scale = 0)
	public Float getTauxMajredStatutproct12b() {
		return this.tauxMajredStatutproct12b;
	}

	public void setTauxMajredStatutproct12b(Float tauxMajredStatutproct12b) {
		this.tauxMajredStatutproct12b = tauxMajredStatutproct12b;
	}

	@Column(name = "TAUX_MAJRED_STATUTPROAUTREST12B", precision = 12, scale = 0)
	public Float getTauxMajredStatutproautrest12b() {
		return this.tauxMajredStatutproautrest12b;
	}

	public void setTauxMajredStatutproautrest12b(
			Float tauxMajredStatutproautrest12b) {
		this.tauxMajredStatutproautrest12b = tauxMajredStatutproautrest12b;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rcTarif12b")
	public Set<Tarif> getTarifs() {
		return this.tarifs;
	}

	public void setTarifs(Set<Tarif> tarifs) {
		this.tarifs = tarifs;
	}

}
