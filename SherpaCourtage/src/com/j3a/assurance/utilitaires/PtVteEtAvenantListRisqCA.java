package com.j3a.assurance.utilitaires;

import java.math.BigDecimal;
import java.util.List;

import com.j3a.assurance.model.PointVente;

public class PtVteEtAvenantListRisqCA {

	public PtVteEtAvenantListRisqCA() {
		// TODO Auto-generated constructor stub
	}
	private PointVente pointVente;
	private List<DynAvenRisqRow> listDynAvenRisqRw;
	private BigDecimal totalCA;
	
	
	
	
	public PointVente getPointVente() {
		
		return pointVente;
	}
	public void setPointVente(PointVente pointVente) {
		this.pointVente = pointVente;
	}
	
	public BigDecimal getTotalCA() {
		totalCA=BigDecimal.ZERO;
		for(DynAvenRisqRow a :listDynAvenRisqRw){
			//System.out.println("-/--/-/-/-/--/-/-/-/-/--/-/-/----/-/-/ AvenantEtRisqList#columns.toString()"+a.getColumns().toString());
			for(DynAvenRisqRow d : listDynAvenRisqRw){
				if(d.getMtRisqAUTOMOBILE()!=null)
					totalCA=totalCA.add(d.getMtRisqAUTOMOBILE());
				if(d.getMtRisqCorpsMaritimeFluvial()!=null)
					totalCA=totalCA.add(d.getMtRisqCorpsMaritimeFluvial());
				if(d.getMtRisqFaculteAerienne()!=null)
					totalCA=totalCA.add(d.getMtRisqFaculteAerienne());
				if(d.getMtRisqFaculteMaritimeFluvial()!=null)
					totalCA=totalCA.add(d.getMtRisqFaculteMaritimeFluvial());
				if(d.getMtRisqFaculteTerrestre()!=null)
					totalCA=totalCA.add(d.getMtRisqFaculteTerrestre());
				if(d.getMtRisqGestionconfiee()!=null)
					totalCA=totalCA.add(d.getMtRisqGestionconfiee());
				if(d.getMtRisqIndividuelleAccidents()!=null)
					totalCA=totalCA.add(d.getMtRisqIndividuelleAccidents());
				if(d.getMtRisqMultiRisquesHabitation()!=null)
					totalCA=totalCA.add(d.getMtRisqMultiRisquesHabitation());
				if(d.getMtRisqRisqueInformatique()!=null)
					totalCA=totalCA.add(d.getMtRisqRisqueInformatique());
				
			}
		}
		return totalCA;
	}
	public void setTotalCA(BigDecimal totalCA) {
		this.totalCA = totalCA;
	}
	public List<DynAvenRisqRow> getListDynAvenRisqRw() {
		return listDynAvenRisqRw;
	}
	public void setListDynAvenRisqRw(List<DynAvenRisqRow> listDynAvenRisqRw) {
		this.listDynAvenRisqRw = listDynAvenRisqRw;
	}
	
	

}
