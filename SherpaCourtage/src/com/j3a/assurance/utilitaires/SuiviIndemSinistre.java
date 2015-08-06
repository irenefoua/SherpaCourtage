package com.j3a.assurance.utilitaires;

import java.math.BigDecimal;

public class SuiviIndemSinistre {
	
	private String sinistre;
	private String victime;
	private String ayantdroit;
	private String statutpaymenet;
	private BigDecimal indem; 

	
	public String getSinistre() {
		return sinistre;
	}
	public void setSinistre(String sinistre) {
		this.sinistre = sinistre;
	}
	public String getVictime() {
		return victime;
	}
	public void setVictime(String victime) {
		this.victime = victime;
	}
	public String getAyantdroit() {
		return ayantdroit;
	}
	public void setAyantdroit(String ayantdroit) {
		this.ayantdroit = ayantdroit;
	}
	public BigDecimal getIndem() {
		return indem;
	}
	public void setIndem(BigDecimal indem) {
		this.indem = indem;
	}
	public String getStatutpaymenet() {
		return statutpaymenet;
	}
	public void setStatutpaymenet(String statutpaymenet) {
		this.statutpaymenet = statutpaymenet;
	}

	
	

}
