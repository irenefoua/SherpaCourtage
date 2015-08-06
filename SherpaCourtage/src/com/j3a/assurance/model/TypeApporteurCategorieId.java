package com.j3a.assurance.model;

// Generated 6 juil. 2015 11:25:44 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TypeApporteurCategorieId generated by hbm2java
 */
@Embeddable
public class TypeApporteurCategorieId implements java.io.Serializable {

	private String idType;
	private String codeCategorie;

	public TypeApporteurCategorieId() {
	}

	public TypeApporteurCategorieId(String idType, String codeCategorie) {
		this.idType = idType;
		this.codeCategorie = codeCategorie;
	}

	@Column(name = "ID_TYPE", nullable = false, length = 10)
	public String getIdType() {
		return this.idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	@Column(name = "CODE_CATEGORIE", nullable = false, length = 15)
	public String getCodeCategorie() {
		return this.codeCategorie;
	}

	public void setCodeCategorie(String codeCategorie) {
		this.codeCategorie = codeCategorie;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TypeApporteurCategorieId))
			return false;
		TypeApporteurCategorieId castOther = (TypeApporteurCategorieId) other;

		return ((this.getIdType() == castOther.getIdType()) || (this
				.getIdType() != null && castOther.getIdType() != null && this
				.getIdType().equals(castOther.getIdType())))
				&& ((this.getCodeCategorie() == castOther.getCodeCategorie()) || (this
						.getCodeCategorie() != null
						&& castOther.getCodeCategorie() != null && this
						.getCodeCategorie()
						.equals(castOther.getCodeCategorie())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getIdType() == null ? 0 : this.getIdType().hashCode());
		result = 37
				* result
				+ (getCodeCategorie() == null ? 0 : this.getCodeCategorie()
						.hashCode());
		return result;
	}

}
