package com.j3a.assurance.model;

// Generated 10 ao�t 2015 15:53:59 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ListeGestionConfie generated by hbm2java
 */
@Entity
@Table(name = "liste_gestion_confie", catalog = "zeusbd")
public class ListeGestionConfie implements java.io.Serializable {

	private String codeListeGestionConfie;
	private String libelleListeGestionConfie;
	private Set<AvenantListeGestionConfie> avenantListeGestionConfies = new HashSet<AvenantListeGestionConfie>(
			0);
	private Set<GestionConfiee> gestionConfiees = new HashSet<GestionConfiee>(0);

	public ListeGestionConfie() {
	}

	public ListeGestionConfie(String codeListeGestionConfie) {
		this.codeListeGestionConfie = codeListeGestionConfie;
	}

	public ListeGestionConfie(String codeListeGestionConfie,
			String libelleListeGestionConfie,
			Set<AvenantListeGestionConfie> avenantListeGestionConfies,
			Set<GestionConfiee> gestionConfiees) {
		this.codeListeGestionConfie = codeListeGestionConfie;
		this.libelleListeGestionConfie = libelleListeGestionConfie;
		this.avenantListeGestionConfies = avenantListeGestionConfies;
		this.gestionConfiees = gestionConfiees;
	}

	@Id
	@Column(name = "CODE_LISTE_GESTION_CONFIE", unique = true, nullable = false, length = 60)
	public String getCodeListeGestionConfie() {
		return this.codeListeGestionConfie;
	}

	public void setCodeListeGestionConfie(String codeListeGestionConfie) {
		this.codeListeGestionConfie = codeListeGestionConfie;
	}

	@Column(name = "LIBELLE_LISTE_GESTION_CONFIE", length = 60)
	public String getLibelleListeGestionConfie() {
		return this.libelleListeGestionConfie;
	}

	public void setLibelleListeGestionConfie(String libelleListeGestionConfie) {
		this.libelleListeGestionConfie = libelleListeGestionConfie;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "listeGestionConfie")
	public Set<AvenantListeGestionConfie> getAvenantListeGestionConfies() {
		return this.avenantListeGestionConfies;
	}

	public void setAvenantListeGestionConfies(
			Set<AvenantListeGestionConfie> avenantListeGestionConfies) {
		this.avenantListeGestionConfies = avenantListeGestionConfies;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "listeGestionConfie")
	public Set<GestionConfiee> getGestionConfiees() {
		return this.gestionConfiees;
	}

	public void setGestionConfiees(Set<GestionConfiee> gestionConfiees) {
		this.gestionConfiees = gestionConfiees;
	}

}
