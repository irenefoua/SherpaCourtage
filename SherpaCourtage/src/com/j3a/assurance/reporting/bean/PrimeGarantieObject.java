package com.j3a.assurance.reporting.bean;

import com.j3a.assurance.model.Garantie;
import com.j3a.assurance.model.GarantieGarantieChoisie;
import com.j3a.assurance.model.GarantieGarantieChoisieCorps;
import com.j3a.assurance.model.GarantieGarantieChoisieIa;
import com.j3a.assurance.model.GarantieGarantieChoisieMrh;
import com.j3a.assurance.model.GarantieGarantieChoisieNta;
import com.j3a.assurance.model.GarantieGarantieChoisieSante;
import com.j3a.assurance.model.GarantieGarantieChoisieTransport;

public class PrimeGarantieObject {
private Garantie garantie;
private GarantieGarantieChoisie choisieAuto;
private GarantieGarantieChoisieTransport choisie;
private GarantieGarantieChoisieCorps choisieCorps;
private GarantieGarantieChoisieMrh choisieMRH;
private GarantieGarantieChoisieIa choisieIA;
private GarantieGarantieChoisieNta choisieNTA;
private GarantieGarantieChoisieSante choisieSante;	

	public PrimeGarantieObject() {
		
	}

	public Garantie getGarantie() {
		return garantie;
	}

	public void setGarantie(Garantie garantie) {
		this.garantie = garantie;
	}

	public GarantieGarantieChoisieTransport getChoisie() {
		return choisie;
	}

	public void setChoisie(GarantieGarantieChoisieTransport choisie) {
		this.choisie = choisie;
	}

	public GarantieGarantieChoisieCorps getChoisieCorps() {
		return choisieCorps;
	}

	public void setChoisieCorps(GarantieGarantieChoisieCorps choisieCorps) {
		this.choisieCorps = choisieCorps;
	}

	public GarantieGarantieChoisieMrh getChoisieMRH() {
		return choisieMRH;
	}

	public void setChoisieMRH(GarantieGarantieChoisieMrh choisieMRH) {
		this.choisieMRH = choisieMRH;
	}

	public GarantieGarantieChoisieIa getChoisieIA() {
		return choisieIA;
	}

	public void setChoisieIA(GarantieGarantieChoisieIa choisieIA) {
		this.choisieIA = choisieIA;
	}

	public GarantieGarantieChoisieNta getChoisieNTA() {
		return choisieNTA;
	}

	public void setChoisieNTA(GarantieGarantieChoisieNta choisieNTA) {
		this.choisieNTA = choisieNTA;
	}

	public GarantieGarantieChoisieSante getChoisieSante() {
		return choisieSante;
	}

	public void setChoisieSante(GarantieGarantieChoisieSante choisieSante) {
		this.choisieSante = choisieSante;
	}

	public GarantieGarantieChoisie getChoisieAuto() {
		return choisieAuto;
	}

	public void setChoisieAuto(GarantieGarantieChoisie choisieAuto) {
		this.choisieAuto = choisieAuto;
	}
	

}
