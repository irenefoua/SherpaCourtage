package com.j3a.assurance.prime;

import java.math.BigDecimal;

public class CalculPrimeProrata {
	
	
public java.math.BigDecimal primeProrata(double duree, java.math.BigDecimal primeAnuelle){
	java.math.BigDecimal prime= BigDecimal.ZERO;
	if(duree <= 0.5){
	prime =	primeAnuelle.multiply(BigDecimal.valueOf(0.05));
	}
	
	if(duree > 0.5 && duree <= 1){
		prime =	primeAnuelle.multiply(BigDecimal.valueOf(0.1));
		}
	if(duree > 1 && duree <= 2){
		prime =	primeAnuelle.multiply(BigDecimal.valueOf(0.19));
		}
	if(duree > 2 && duree <= 3){
		prime =	primeAnuelle.multiply(BigDecimal.valueOf(0.28));
		}
	if(duree > 3 && duree <= 4){
		prime =	primeAnuelle.multiply(BigDecimal.valueOf(0.37));
		}
	if(duree > 4 && duree <= 5){
		prime =	primeAnuelle.multiply(BigDecimal.valueOf(0.45));
		}
	if(duree > 5 && duree <= 6){
		prime =	primeAnuelle.multiply(BigDecimal.valueOf(0.53));
		}
	if(duree > 6 && duree <= 7){
		prime =	primeAnuelle.multiply(BigDecimal.valueOf(0.61));
		}
	if(duree > 7 && duree <= 8){
		prime =	primeAnuelle.multiply(BigDecimal.valueOf(0.69));
		}
	if(duree > 8 && duree <= 9){
		prime =	primeAnuelle.multiply(BigDecimal.valueOf(0.77));
		}
	if(duree > 9 && duree <= 10){
		prime =	primeAnuelle.multiply(BigDecimal.valueOf(0.85));
		}
	if(duree > 10 && duree <= 11){
		prime =	primeAnuelle.multiply(BigDecimal.valueOf(0.93));
		}
	if(duree > 11 && duree <= 12){
		prime =	primeAnuelle.multiply(BigDecimal.valueOf(1));
		}
	
	return prime;
}

}
