package com.j3a.assurance.prime.categorie;
public class ReductionPrime {

	private double dureePermis;
	private String statut;
	private boolean remorque;

	/**************** applique la reduction de la prime en RC en fonction des parametres ****************/
	public double reduction(Double primeRC) {
		double primeReduite = primeRC;
		// String classe;
		if (getDureePermis() > 2) {
			primeReduite = primeReduite * 0.95;
		}

		if (getStatut().equals("a") || getStatut().equals("b")
				|| getStatut().equals("c")) {
			primeReduite = primeReduite * 0.95;
		}

		if (getStatut().equals("d") || getStatut().equals("e")) {
			primeReduite = primeReduite * 0.90;
		}

		if (isRemorque() == true) {
			primeReduite = primeReduite * 1.1;
		}

		return primeReduite;

	}

	/************************** Setters and getters *******************************/

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public double getDureePermis() {
		return dureePermis;
	}

	public void setDureePermis(double dureePermis) {
		this.dureePermis = dureePermis;
	}

	public boolean isRemorque() {
		return remorque;
	}

	public void setRemorque(boolean remorque) {
		this.remorque = remorque;
	}

}
