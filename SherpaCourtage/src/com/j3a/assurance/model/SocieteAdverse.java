package com.j3a.assurance.model;

// Generated 10 ao�t 2015 09:53:29 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SocieteAdverse generated by hbm2java
 */
@Entity
@Table(name = "societe_adverse", catalog = "zeusbd")
public class SocieteAdverse implements java.io.Serializable {

	private String codeScteadverse;
	private String raisonsocialScteadverse;
	private String adresseScteadverse;
	private String telephoneScteadverse;
	private String faxSteadverse;
	private Set<PartieAdverse> partieAdverses = new HashSet<PartieAdverse>(0);

	public SocieteAdverse() {
	}

	public SocieteAdverse(String codeScteadverse) {
		this.codeScteadverse = codeScteadverse;
	}

	public SocieteAdverse(String codeScteadverse,
			String raisonsocialScteadverse, String adresseScteadverse,
			String telephoneScteadverse, String faxSteadverse,
			Set<PartieAdverse> partieAdverses) {
		this.codeScteadverse = codeScteadverse;
		this.raisonsocialScteadverse = raisonsocialScteadverse;
		this.adresseScteadverse = adresseScteadverse;
		this.telephoneScteadverse = telephoneScteadverse;
		this.faxSteadverse = faxSteadverse;
		this.partieAdverses = partieAdverses;
	}

	@Id
	@Column(name = "CODE_SCTEADVERSE", unique = true, nullable = false, length = 30)
	public String getCodeScteadverse() {
		return this.codeScteadverse;
	}

	public void setCodeScteadverse(String codeScteadverse) {
		this.codeScteadverse = codeScteadverse;
	}

	@Column(name = "RAISONSOCIAL_SCTEADVERSE", length = 30)
	public String getRaisonsocialScteadverse() {
		return this.raisonsocialScteadverse;
	}

	public void setRaisonsocialScteadverse(String raisonsocialScteadverse) {
		this.raisonsocialScteadverse = raisonsocialScteadverse;
	}

	@Column(name = "ADRESSE_SCTEADVERSE", length = 25)
	public String getAdresseScteadverse() {
		return this.adresseScteadverse;
	}

	public void setAdresseScteadverse(String adresseScteadverse) {
		this.adresseScteadverse = adresseScteadverse;
	}

	@Column(name = "TELEPHONE_SCTEADVERSE", length = 25)
	public String getTelephoneScteadverse() {
		return this.telephoneScteadverse;
	}

	public void setTelephoneScteadverse(String telephoneScteadverse) {
		this.telephoneScteadverse = telephoneScteadverse;
	}

	@Column(name = "FAX_STEADVERSE", length = 20)
	public String getFaxSteadverse() {
		return this.faxSteadverse;
	}

	public void setFaxSteadverse(String faxSteadverse) {
		this.faxSteadverse = faxSteadverse;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "societeAdverse")
	public Set<PartieAdverse> getPartieAdverses() {
		return this.partieAdverses;
	}

	public void setPartieAdverses(Set<PartieAdverse> partieAdverses) {
		this.partieAdverses = partieAdverses;
	}

}
