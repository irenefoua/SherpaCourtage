package com.j3a.assurance.model;

// Generated 12 ao�t 2015 16:21:18 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Profil generated by hbm2java
 */
@Entity
@Table(name = "profil", catalog = "zeusbd")
public class Profil implements java.io.Serializable {

	private String codeProfil;
	private String libelleProfil;
	private Set<ProfilUtilisateur> profilUtilisateurs = new HashSet<ProfilUtilisateur>(
			0);

	public Profil() {
	}

	public Profil(String codeProfil) {
		this.codeProfil = codeProfil;
	}

	public Profil(String codeProfil, String libelleProfil,
			Set<ProfilUtilisateur> profilUtilisateurs) {
		this.codeProfil = codeProfil;
		this.libelleProfil = libelleProfil;
		this.profilUtilisateurs = profilUtilisateurs;
	}

	@Id
	@Column(name = "CODE_PROFIL", unique = true, nullable = false, length = 10)
	public String getCodeProfil() {
		return this.codeProfil;
	}

	public void setCodeProfil(String codeProfil) {
		this.codeProfil = codeProfil;
	}

	@Column(name = "LIBELLE_PROFIL", length = 15)
	public String getLibelleProfil() {
		return this.libelleProfil;
	}

	public void setLibelleProfil(String libelleProfil) {
		this.libelleProfil = libelleProfil;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "profil")
	public Set<ProfilUtilisateur> getProfilUtilisateurs() {
		return this.profilUtilisateurs;
	}

	public void setProfilUtilisateurs(Set<ProfilUtilisateur> profilUtilisateurs) {
		this.profilUtilisateurs = profilUtilisateurs;
	}

}
