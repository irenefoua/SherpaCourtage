package com.j3a.assurance.utilitaires;

import java.math.BigDecimal;
import java.util.Calendar;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import com.j3a.assurance.model.Avenant;
import com.j3a.assurance.model.Contrat;

public class ContratRw {

	public ContratRw() {
		// TODO Auto-generated constructor stub
	}

	private Contrat ctrat;
	private Avenant lastAven;
	private BigDecimal netPaid;
	private int progress=0;
	
	
	
	public void setCtra (Contrat ctrat){
		this.ctrat=ctrat;
	}
	public Contrat getCtrat(){
		return this.ctrat;
	}
	public Avenant getLastAven() {
		return lastAven;
	}
	public void setLastAven(Avenant lastAven) {
		this.lastAven = lastAven;
	}
	public BigDecimal getNetPaid() {
		return netPaid;
	}
	public void setNetPaid(BigDecimal netPaid) {
		this.netPaid = netPaid;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
		
	
}
