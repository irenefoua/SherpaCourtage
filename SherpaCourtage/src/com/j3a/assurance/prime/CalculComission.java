package com.j3a.assurance.prime;

public class CalculComission {

	private Long prime;
	private Long pourcentage;

	/*************** getters et setters ********************/
	public Long getPrime() {
		return prime;
	}

	public void setPrime(Long prime) {
		this.prime = prime;
	}

	public Long getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(Long pourcentage) {
		this.pourcentage = pourcentage;
	}

	/********** calcul la commission de l'apporteur d'affaire *********/
	public double CalculCommission() {
		double commission = prime * pourcentage;
		return commission;
	}

}
