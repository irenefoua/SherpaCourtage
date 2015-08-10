package com.j3a.assurance.model;

// Generated 10 ao�t 2015 15:53:59 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * PointVente generated by hbm2java
 */
@Entity
@Table(name = "point_vente", catalog = "zeusbd")
public class PointVente implements java.io.Serializable {

	private String codePointVente;
	private TypePointVente typePointVente;
	private Ville ville;
	private String libellePointVente;
	private String adressePointVente;
	private String telFixePointVente;
	private String telFaxPointVente;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private Set<Utilisateur> utilisateurs = new HashSet<Utilisateur>(0);
	private Set<Stock> stocks = new HashSet<Stock>(0);
	private Set<Contrat> contrats = new HashSet<Contrat>(0);

	public PointVente() {
	}

	public PointVente(String codePointVente, TypePointVente typePointVente,
			Ville ville) {
		this.codePointVente = codePointVente;
		this.typePointVente = typePointVente;
		this.ville = ville;
	}

	public PointVente(String codePointVente, TypePointVente typePointVente,
			Ville ville, String libellePointVente, String adressePointVente,
			String telFixePointVente, String telFaxPointVente,
			BigDecimal latitude, BigDecimal longitude,
			Set<Utilisateur> utilisateurs, Set<Stock> stocks,
			Set<Contrat> contrats) {
		this.codePointVente = codePointVente;
		this.typePointVente = typePointVente;
		this.ville = ville;
		this.libellePointVente = libellePointVente;
		this.adressePointVente = adressePointVente;
		this.telFixePointVente = telFixePointVente;
		this.telFaxPointVente = telFaxPointVente;
		this.latitude = latitude;
		this.longitude = longitude;
		this.utilisateurs = utilisateurs;
		this.stocks = stocks;
		this.contrats = contrats;
	}

	@Id
	@Column(name = "CODE_POINT_VENTE", unique = true, nullable = false, length = 15)
	public String getCodePointVente() {
		return this.codePointVente;
	}

	public void setCodePointVente(String codePointVente) {
		this.codePointVente = codePointVente;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_TYPE_POINT_VENTE", nullable = false)
	public TypePointVente getTypePointVente() {
		return this.typePointVente;
	}

	public void setTypePointVente(TypePointVente typePointVente) {
		this.typePointVente = typePointVente;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODE_VILLE", nullable = false)
	public Ville getVille() {
		return this.ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	@Column(name = "LIBELLE_POINT_VENTE", length = 20)
	public String getLibellePointVente() {
		return this.libellePointVente;
	}

	public void setLibellePointVente(String libellePointVente) {
		this.libellePointVente = libellePointVente;
	}

	@Column(name = "ADRESSE_POINT_VENTE", length = 60)
	public String getAdressePointVente() {
		return this.adressePointVente;
	}

	public void setAdressePointVente(String adressePointVente) {
		this.adressePointVente = adressePointVente;
	}

	@Column(name = "TEL_FIXE_POINT_VENTE", length = 14)
	public String getTelFixePointVente() {
		return this.telFixePointVente;
	}

	public void setTelFixePointVente(String telFixePointVente) {
		this.telFixePointVente = telFixePointVente;
	}

	@Column(name = "TEL_FAX_POINT_VENTE", length = 14)
	public String getTelFaxPointVente() {
		return this.telFaxPointVente;
	}

	public void setTelFaxPointVente(String telFaxPointVente) {
		this.telFaxPointVente = telFaxPointVente;
	}

	@Column(name = "LATITUDE", precision = 15, scale = 3)
	public BigDecimal getLatitude() {
		return this.latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	@Column(name = "LONGITUDE", precision = 15, scale = 3)
	public BigDecimal getLongitude() {
		return this.longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pointVente")
	public Set<Utilisateur> getUtilisateurs() {
		return this.utilisateurs;
	}

	public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pointVente")
	public Set<Stock> getStocks() {
		return this.stocks;
	}

	public void setStocks(Set<Stock> stocks) {
		this.stocks = stocks;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pointVente")
	public Set<Contrat> getContrats() {
		return this.contrats;
	}

	public void setContrats(Set<Contrat> contrats) {
		this.contrats = contrats;
	}

}
