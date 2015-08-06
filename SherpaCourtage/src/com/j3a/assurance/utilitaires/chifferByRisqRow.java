package com.j3a.assurance.utilitaires;

import java.math.BigDecimal;
import java.util.List;

import com.j3a.assurance.model.PointVente;

public class chifferByRisqRow {

	public chifferByRisqRow() {
		// TODO Auto-generated constructor stub
	}


	private PointVente ptVte;
	private List<ChiffreAffAndRisq> listCAandRisq;
	private BigDecimal CATotal = new BigDecimal(0);
	
	public PointVente getPtVte() {
		return ptVte;
	}
	public List<ChiffreAffAndRisq> getListCAandRisq() {
		return listCAandRisq;
	}
	public void setPtVte(PointVente ptVte) {
		this.ptVte = ptVte;
	}
	public void setListCAandRisq(List<ChiffreAffAndRisq> listCAandRisq) {
		this.listCAandRisq = listCAandRisq;
	}
	
	public BigDecimal getCATotal() {
		BigDecimal X = BigDecimal.ZERO;
		for (ChiffreAffAndRisq c : getListCAandRisq()){
			if (c.getChiffrAffaire() != null )
				X = X.add(c.getChiffrAffaire());	
//			System.out.println("chifree" +getCATotal());
//				if (X == null) {
//					X =BigDecimal.ZERO;
//					System.out.println("oooooooooooooooooooooooooooooooo  test   null");
//					
//				}
			
		}
		return X;
	}
	public void setCATotal(BigDecimal cATotal) {
		CATotal = cATotal;
	}
	
	
	
}
