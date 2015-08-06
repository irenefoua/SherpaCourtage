package com.j3a.assurance.utilitaires.hybride;

import java.math.BigDecimal;

public class SinistreResultQueryPvt {
	private java.lang.String libellepvt;
	private java.math.BigDecimal totalNette;
	private java.math.BigDecimal totalBrute;
	
	public SinistreResultQueryPvt(){
		setLibellepvt("");
		setTotalBrute(BigDecimal.ZERO);
		setTotalNette(BigDecimal.ZERO);
	}

	public java.lang.String getLibellepvt() {
		return libellepvt;
	}

	public void setLibellepvt(java.lang.String libellepvt) {
		this.libellepvt = libellepvt;
	}

	public java.math.BigDecimal getTotalNette() {
		return totalNette;
	}

	public void setTotalNette(java.math.BigDecimal totalNette) {
		this.totalNette = totalNette;
	}

	public java.math.BigDecimal getTotalBrute() {
		return totalBrute;
	}

	public void setTotalBrute(java.math.BigDecimal totalBrute) {
		this.totalBrute = totalBrute;
	}
}
