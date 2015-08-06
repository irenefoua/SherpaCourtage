package com.j3a.assurance.model;

// Generated 6 ao�t 2015 16:35:56 by Hibernate Tools 4.3.1

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
 * ListeTrafic generated by hbm2java
 */
@Entity
@Table(name = "liste_trafic", catalog = "zeusbd")
public class ListeTrafic implements java.io.Serializable {

	private String codeListeTrafic;
	private String cadence;
	private String ressortie;
	private BigDecimal pleinExped;
	private Integer reductionListeTrafic;
	private Set<AvTrafic> avTrafics = new HashSet<AvTrafic>(0);
	private Set<Trafic> trafics = new HashSet<Trafic>(0);

	public ListeTrafic() {
	}

	public ListeTrafic(String codeListeTrafic) {
		this.codeListeTrafic = codeListeTrafic;
	}

	public ListeTrafic(String codeListeTrafic, String cadence,
			String ressortie, BigDecimal pleinExped,
			Integer reductionListeTrafic, Set<AvTrafic> avTrafics,
			Set<Trafic> trafics) {
		this.codeListeTrafic = codeListeTrafic;
		this.cadence = cadence;
		this.ressortie = ressortie;
		this.pleinExped = pleinExped;
		this.reductionListeTrafic = reductionListeTrafic;
		this.avTrafics = avTrafics;
		this.trafics = trafics;
	}

	@Id
	@Column(name = "CODE_LISTE_TRAFIC", unique = true, nullable = false, length = 27)
	public String getCodeListeTrafic() {
		return this.codeListeTrafic;
	}

	public void setCodeListeTrafic(String codeListeTrafic) {
		this.codeListeTrafic = codeListeTrafic;
	}

	@Column(name = "CADENCE", length = 20)
	public String getCadence() {
		return this.cadence;
	}

	public void setCadence(String cadence) {
		this.cadence = cadence;
	}

	@Column(name = "RESSORTIE", length = 20)
	public String getRessortie() {
		return this.ressortie;
	}

	public void setRessortie(String ressortie) {
		this.ressortie = ressortie;
	}

	@Column(name = "PLEIN_EXPED", precision = 15, scale = 3)
	public BigDecimal getPleinExped() {
		return this.pleinExped;
	}

	public void setPleinExped(BigDecimal pleinExped) {
		this.pleinExped = pleinExped;
	}

	@Column(name = "REDUCTION_LISTE_TRAFIC")
	public Integer getReductionListeTrafic() {
		return this.reductionListeTrafic;
	}

	public void setReductionListeTrafic(Integer reductionListeTrafic) {
		this.reductionListeTrafic = reductionListeTrafic;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "listeTrafic")
	public Set<AvTrafic> getAvTrafics() {
		return this.avTrafics;
	}

	public void setAvTrafics(Set<AvTrafic> avTrafics) {
		this.avTrafics = avTrafics;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "listeTrafic")
	public Set<Trafic> getTrafics() {
		return this.trafics;
	}

	public void setTrafics(Set<Trafic> trafics) {
		this.trafics = trafics;
	}

}
