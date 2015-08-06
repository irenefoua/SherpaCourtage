package com.j3a.assurance.model;

// Generated 6 juil. 2015 11:25:44 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * RcTarif7 generated by hbm2java
 */
@Entity
@Table(name = "rc_tarif7", catalog = "zeusbd")
public class RcTarif7 implements java.io.Serializable {

	private String codeRcTarif7;
	private Float tauxVhldcTourisme;
	private Float tauxVhldcUtilTransp;
	private Float tauxVhlTourismeZone;
	private Float tauxVhlUtilTranspZone;
	private Set<Tarif> tarifs = new HashSet<Tarif>(0);

	public RcTarif7() {
	}

	public RcTarif7(String codeRcTarif7) {
		this.codeRcTarif7 = codeRcTarif7;
	}

	public RcTarif7(String codeRcTarif7, Float tauxVhldcTourisme,
			Float tauxVhldcUtilTransp, Float tauxVhlTourismeZone,
			Float tauxVhlUtilTranspZone, Set<Tarif> tarifs) {
		this.codeRcTarif7 = codeRcTarif7;
		this.tauxVhldcTourisme = tauxVhldcTourisme;
		this.tauxVhldcUtilTransp = tauxVhldcUtilTransp;
		this.tauxVhlTourismeZone = tauxVhlTourismeZone;
		this.tauxVhlUtilTranspZone = tauxVhlUtilTranspZone;
		this.tarifs = tarifs;
	}

	@Id
	@Column(name = "CODE_RC_TARIF7", unique = true, nullable = false, length = 16)
	public String getCodeRcTarif7() {
		return this.codeRcTarif7;
	}

	public void setCodeRcTarif7(String codeRcTarif7) {
		this.codeRcTarif7 = codeRcTarif7;
	}

	@Column(name = "TAUX_VHLDC_TOURISME", precision = 12, scale = 0)
	public Float getTauxVhldcTourisme() {
		return this.tauxVhldcTourisme;
	}

	public void setTauxVhldcTourisme(Float tauxVhldcTourisme) {
		this.tauxVhldcTourisme = tauxVhldcTourisme;
	}

	@Column(name = "TAUX_VHLDC_UTIL_TRANSP", precision = 12, scale = 0)
	public Float getTauxVhldcUtilTransp() {
		return this.tauxVhldcUtilTransp;
	}

	public void setTauxVhldcUtilTransp(Float tauxVhldcUtilTransp) {
		this.tauxVhldcUtilTransp = tauxVhldcUtilTransp;
	}

	@Column(name = "TAUX_VHL_TOURISME_ZONE", precision = 12, scale = 0)
	public Float getTauxVhlTourismeZone() {
		return this.tauxVhlTourismeZone;
	}

	public void setTauxVhlTourismeZone(Float tauxVhlTourismeZone) {
		this.tauxVhlTourismeZone = tauxVhlTourismeZone;
	}

	@Column(name = "TAUX_VHL_UTIL_TRANSP_ZONE", precision = 12, scale = 0)
	public Float getTauxVhlUtilTranspZone() {
		return this.tauxVhlUtilTranspZone;
	}

	public void setTauxVhlUtilTranspZone(Float tauxVhlUtilTranspZone) {
		this.tauxVhlUtilTranspZone = tauxVhlUtilTranspZone;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rcTarif7")
	public Set<Tarif> getTarifs() {
		return this.tarifs;
	}

	public void setTarifs(Set<Tarif> tarifs) {
		this.tarifs = tarifs;
	}

}
