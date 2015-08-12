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
 * Sexe generated by hbm2java
 */
@Entity
@Table(name = "sexe", catalog = "zeusbd")
public class Sexe implements java.io.Serializable {

	private int codeSexe;
	private Character libelleSexe;
	private Set<Physique> physiques = new HashSet<Physique>(0);

	public Sexe() {
	}

	public Sexe(int codeSexe) {
		this.codeSexe = codeSexe;
	}

	public Sexe(int codeSexe, Character libelleSexe, Set<Physique> physiques) {
		this.codeSexe = codeSexe;
		this.libelleSexe = libelleSexe;
		this.physiques = physiques;
	}

	@Id
	@Column(name = "CODE_SEXE", unique = true, nullable = false)
	public int getCodeSexe() {
		return this.codeSexe;
	}

	public void setCodeSexe(int codeSexe) {
		this.codeSexe = codeSexe;
	}

	@Column(name = "LIBELLE_SEXE", length = 1)
	public Character getLibelleSexe() {
		return this.libelleSexe;
	}

	public void setLibelleSexe(Character libelleSexe) {
		this.libelleSexe = libelleSexe;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sexe")
	public Set<Physique> getPhysiques() {
		return this.physiques;
	}

	public void setPhysiques(Set<Physique> physiques) {
		this.physiques = physiques;
	}

}
