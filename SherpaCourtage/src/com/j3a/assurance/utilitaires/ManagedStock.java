package com.j3a.assurance.utilitaires;

import com.j3a.assurance.model.Stock;

public class ManagedStock {

	public ManagedStock(Stock stk, Long stkCtr, String nvEt){
		stock=stk;
		stkCtrib=stkCtr;
		nvlEtat=nvEt;
		
	}
	
	private Stock stock;
	private Long stkCtrib;
	private String nvlEtat;
	
	
	
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public Long getStkCtrib() {
		return stkCtrib;
	}
	public void setStkCtrib(Long stkCtrib) {
		this.stkCtrib = stkCtrib;
	}
	public String getNvlEtat() {
		return nvlEtat;
	}
	public void setNvlEtat(String nvlEtat) {
		this.nvlEtat = nvlEtat;
	}
	
	
	
}
