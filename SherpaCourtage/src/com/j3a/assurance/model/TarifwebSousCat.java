package com.j3a.assurance.model;

// Generated 12 ao�t 2015 16:21:18 by Hibernate Tools 4.3.1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TarifwebSousCat generated by hbm2java
 */
@Entity
@Table(name = "tarifweb_sous_cat", catalog = "zeusbd")
public class TarifwebSousCat implements java.io.Serializable {

	private TarifwebSousCatId id;
	private SousCatVehicule sousCatVehicule;
	private Tarifweb tarifweb;

	public TarifwebSousCat() {
	}

	public TarifwebSousCat(TarifwebSousCatId id,
			SousCatVehicule sousCatVehicule, Tarifweb tarifweb) {
		this.id = id;
		this.sousCatVehicule = sousCatVehicule;
		this.tarifweb = tarifweb;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codeTarifWeb", column = @Column(name = "CODE_TARIF_WEB", nullable = false, length = 16)),
			@AttributeOverride(name = "codeSousCatVehicule", column = @Column(name = "CODE_SOUS_CAT_VEHICULE", nullable = false, length = 15)) })
	public TarifwebSousCatId getId() {
		return this.id;
	}

	public void setId(TarifwebSousCatId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_SOUS_CAT_VEHICULE", nullable = false, insertable = false, updatable = false)
	public SousCatVehicule getSousCatVehicule() {
		return this.sousCatVehicule;
	}

	public void setSousCatVehicule(SousCatVehicule sousCatVehicule) {
		this.sousCatVehicule = sousCatVehicule;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_TARIF_WEB", nullable = false, insertable = false, updatable = false)
	public Tarifweb getTarifweb() {
		return this.tarifweb;
	}

	public void setTarifweb(Tarifweb tarifweb) {
		this.tarifweb = tarifweb;
	}

}