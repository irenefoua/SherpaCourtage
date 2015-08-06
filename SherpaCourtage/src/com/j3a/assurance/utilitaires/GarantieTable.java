
package com.j3a.assurance.utilitaires;


public class GarantieTable {


	private String libelleRC, idRC, capitalRC, franchiseRC;
	private String libelleDR, idDR, capitalDR, franchiseDR;
	private String libelleSROpt1, idSROpt1, capitalSROpt1, franchiseSROpt1;
	private String libelleSROpt2, idSROpt2, capitalSROpt2, franchiseSROpt2;
	private String libelleSROpt3, idSROpt3, capitalSROpt3, franchiseSROpt3;
	private String libelleIndChaufSp, idIndChaufSp, capitalIndChaufSp, franchiseIndChaufSp;
	private String libelleIndChaufOpt1, idIndChaufOpt1, capitalIndChaufOpt1, franchiseIndChaufOpt1;
	private String libelleIndChaufOpt2, idIndChaufOpt2, capitalIndChaufOpt2, franchiseIndChaufOpt2;
	private String libelleIndChaufOpt3, idIndChaufOpt3, capitalIndChaufOpt3, franchiseIndChaufOpt3;
	private String libelleDommagesCol, idDommagesCol, capitalDommagesCol, franchiseDommagesCol;
	private String libelleDommagesTA, idDommagesTA, capitalDommagesTA, franchiseDommagesTA;
	private String libelleBDGOpt1, idBDGOpt1 ,capitalBDGOpt1, franchiseBDGOpt1;
	private String libelleBDGOpt2, idBDGOpt2 ,capitalBDGOpt2, franchiseBDGOpt2;
	private String libelleBDGOpt3, idBDGOpt3 ,capitalBDGOpt3, franchiseBDGOpt3;
	private String libelleVol, idVol, capitalVol, franchiseVol;
	private String libelleRA, idRA, capitalRA, franchiseRA;
	private String libelleIncendie, idIncendie, capitalIncendie, franchiseIncendie;
	private String libelleImmob, idImmob, capitalImmob, franchiseImmob;
	private String libelleVolAcc, idVolAcc, capitalVolAcc, franchiseVolAcc;
	private String libelleVandalisme, idVandalisme, capitalVandalisme, franchiseVandalisme;
	
	private java.math.BigDecimal  primeAnRC, primeProrataRC,primeNetAnRC, montantRedRC;
	private int tauxRedRC,tauxFlotteRC, tauxBonusRC, tauxMalusRC;
	
	private java.math.BigDecimal  primeAnDR, primeProrataDR, primeNetAnDR, montantRedDR;
	private int tauxRedDR,tauxFlotteDR,tauxBonusDR, tauxMalusDR;
	
	private java.math.BigDecimal  primeAnSROpt1, primeProrataSROpt1,primeNetAnSROpt1, montantRedSROpt1;
	private int tauxRedSROpt1, tauxFlotteSROpt1,tauxBonusSROpt1, tauxMalusSROpt1;
	
	private java.math.BigDecimal  primeAnSROpt2, primeProrataSROpt2,primeNetAnSROpt2, montantRedSROpt2;
	
	private int tauxRedSROpt2,tauxFlotteSROpt2, tauxBonusSROpt2, tauxMalusSROpt2;
	
	private java.math.BigDecimal  primeAnSROpt3, primeProrataSROpt3,primeNetAnSROpt3, montantRedSROpt3;
	private int tauxRedSROpt3,tauxFlotteSROpt3, tauxBonusSROpt3, tauxMalusSROpt3;
	
	private java.math.BigDecimal  primeAnIndChaufSp, primeProrataIndChaufSp, primeNetAnIndChaufSp, montantRedIndChaufSp;
	private int tauxRedIndChaufSp,tauxFlotteIndChaufSp, tauxBonusIndChaufSp, tauxMalusIndChaufSp;
	
	private java.math.BigDecimal  primeAnIndChaufOpt1, primeProrataIndChaufOpt1, primeNetAnIndChaufOpt1, montantRedIndChaufOpt1;
	private int tauxRedIndChaufOpt1,tauxFlotteIndChaufOpt1, tauxBonusIndChaufOpt1, tauxMalusIndChaufOpt1;
	
	private java.math.BigDecimal  primeAnIndChaufOpt2, primeProrataIndChaufOpt2, primeNetAnIndChaufOpt2, montantRedIndChaufOpt2;
	
private int tauxRedIndChaufOpt2,tauxFlotteIndChaufOpt2, tauxBonusIndChaufOpt2, tauxMalusIndChaufOpt2;
	
	private java.math.BigDecimal  primeAnIndChaufOpt3, primeProrataIndChaufOpt3, primeNetAnIndChaufOpt3, montantRedIndChaufOpt3;
	private int tauxRedIndChaufOpt3,tauxFlotteIndChaufOpt3, tauxBonusIndChaufOpt3, tauxMalusIndChaufOpt3;
	
	
	
	private java.math.BigDecimal  primeAnDommagesCol, primeProrataDommagesCol,primeNetAnDommagesCol, montantRedDommagesCol;
	private int tauxRedDommagesCol,tauxFlotteDommagesCol, tauxBonusDommagesCol, tauxMalusDommagesCol;
	
	private java.math.BigDecimal  primeAnDommagesTA, primeProrataDommagesTA, primeNetAnDommagesTA, montantRedDommagesTA;
	private int tauxRedDommagesTA,tauxFlotteDommagesTA, tauxBonusDommagesTA, tauxMalusDommagesTA;
	
	private java.math.BigDecimal  primeAnBDGOpt1, primeProrataBDGOpt1,primeNetAnBDGOpt1, montantRedBDGOpt1;
	private int tauxRedBDGOpt1,tauxFlotteBDGOpt1, tauxBonusBDGOpt1, tauxMalusBDGOpt1;
	
	private java.math.BigDecimal  primeAnBDGOpt2, primeProrataBDGOpt2,primeNetAnBDGOpt2, montantRedBDGOpt2;
	private int tauxRedBDGOpt2,tauxFlotteBDGOpt2, tauxBonusBDGOpt2, tauxMalusBDGOpt2;
	
	private java.math.BigDecimal  primeAnBDGOpt3, primeProrataBDGOpt3, primeNetAnBDGOpt3, montantRedBDGOpt3;
	private int tauxRedBDGOpt3,tauxFlotteBDGOpt3, tauxBonusBDGOpt3, tauxMalusBDGOpt3;
	
	private java.math.BigDecimal  primeAnVol, primeProrataVol,primeNetAnVol,  montantRedVol;
	private int tauxRedVol,tauxFlotteVol, tauxBonusVol, tauxMalusVol;
	
	private java.math.BigDecimal  primeAnRA, primeProrataRA,primeNetAnRA, montantRedRA;
	private int tauxRedRA,tauxFlotteRA, tauxBonusRA, tauxMalusRA;
	
	private java.math.BigDecimal  primeAnIncendie, primeProrataIncendie,primeNetAnIncendie, montantRedIncendie;
	private int tauxRedIncendie,tauxFlotteIncendie, tauxBonusIncendie, tauxMalusIncendie;
	
	private java.math.BigDecimal  primeAnImmob, primeProrataImmob,primeNetAnImmob, montantRedImmob;
	private int tauxRedImmob,tauxFlotteImmob, tauxBonusImmob, tauxMalusImmob;
	
	private java.math.BigDecimal  primeAnVolAcc, primeProrataVolAcc, primeNetAnVolAcc,montantRedVolAcc;
	private int tauxRedVolAcc,tauxFlotteVolAcc, tauxBonusVolAcc, tauxMalusVolAcc;
	
	
	private java.math.BigDecimal  primeAnVandalisme, primeProrataVandalisme, primeNetAnVandalisme,montantRedVandalisme;
	private int tauxRedVandalisme,tauxFlotteVandalisme, tauxBonusVandalisme, tauxMalusVandalisme;
	
	private boolean statutRC,modeRC,choixRC;
	private boolean statutDR,modeDR,choixDR;
	private boolean statutIndChaufSp,modeIndChaufSp,choixIndChaufSp;
	private boolean statutIndChaufOpt1,modeIndChaufOpt1,choixIndChaufOpt1;
	private boolean statutIndChaufOpt2,modeIndChaufOpt2,choixIndChaufOpt2;
	private boolean statutIndChaufOpt3,modeIndChaufOpt3,choixIndChaufOpt3;
	
	private boolean statutSROpt1,modeSROpt1,choixSROpt1;
	private boolean statutSROpt2,modeSROpt2,choixSROpt2;
	private boolean statutSROpt3,modeSROpt3,choixSROpt3;
	
	
	private boolean statutDommagesCol,modeDommagesCol,choixDommagesCol;
	private boolean statutDommagesTA,modeDommagesTA,choixDommagesTA;
	private boolean statutBDGOpt1,modeBDGOpt1,choixOpt1;
	private boolean statutBDGOpt2,modeBDGOpt2,choixOpt2;
	private boolean statutBDGOpt3,modeBDGOpt3,choixOpt3;
	private boolean statutVol,modeVol,choixVol;
	private boolean statutRA,modeRA,choixRA;
	private boolean statutIncendie,modeIncendie,choixIncendie;
	private boolean statutImmob,modeImmob,choixImmob;
	private boolean statutVolAcc,modeVolAcc,choixVolAcc;
	private boolean statutVandalisme,modeVandalisme,choixVandalisme;
	public GarantieTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getLibelleRC() {
		return libelleRC;
	}
	public void setLibelleRC(String libelleRC) {
		this.libelleRC = libelleRC;
	}
	public String getIdRC() {
		return idRC;
	}
	public void setIdRC(String idRC) {
		this.idRC = idRC;
	}
	public String getCapitalRC() {
		return capitalRC;
	}
	public void setCapitalRC(String capitalRC) {
		this.capitalRC = capitalRC;
	}
	public String getFranchiseRC() {
		return franchiseRC;
	}
	public void setFranchiseRC(String franchiseRC) {
		this.franchiseRC = franchiseRC;
	}
	public String getLibelleDR() {
		return libelleDR;
	}
	public void setLibelleDR(String libelleDR) {
		this.libelleDR = libelleDR;
	}
	public String getIdDR() {
		return idDR;
	}
	public void setIdDR(String idDR) {
		this.idDR = idDR;
	}
	public String getCapitalDR() {
		return capitalDR;
	}
	public void setCapitalDR(String capitalDR) {
		this.capitalDR = capitalDR;
	}
	public String getFranchiseDR() {
		return franchiseDR;
	}
	public void setFranchiseDR(String franchiseDR) {
		this.franchiseDR = franchiseDR;
	}
	public String getLibelleSROpt1() {
		return libelleSROpt1;
	}
	public void setLibelleSROpt1(String libelleSROpt1) {
		this.libelleSROpt1 = libelleSROpt1;
	}
	public String getIdSROpt1() {
		return idSROpt1;
	}
	public void setIdSROpt1(String idSROpt1) {
		this.idSROpt1 = idSROpt1;
	}
	public String getCapitalSROpt1() {
		return capitalSROpt1;
	}
	public void setCapitalSROpt1(String capitalSROpt1) {
		this.capitalSROpt1 = capitalSROpt1;
	}
	public String getFranchiseSROpt1() {
		return franchiseSROpt1;
	}
	public void setFranchiseSROpt1(String franchiseSROpt1) {
		this.franchiseSROpt1 = franchiseSROpt1;
	}
	public String getLibelleSROpt2() {
		return libelleSROpt2;
	}
	public void setLibelleSROpt2(String libelleSROpt2) {
		this.libelleSROpt2 = libelleSROpt2;
	}
	public String getIdSROpt2() {
		return idSROpt2;
	}
	public void setIdSROpt2(String idSROpt2) {
		this.idSROpt2 = idSROpt2;
	}
	public String getCapitalSROpt2() {
		return capitalSROpt2;
	}
	public void setCapitalSROpt2(String capitalSROpt2) {
		this.capitalSROpt2 = capitalSROpt2;
	}
	public String getFranchiseSROpt2() {
		return franchiseSROpt2;
	}
	public void setFranchiseSROpt2(String franchiseSROpt2) {
		this.franchiseSROpt2 = franchiseSROpt2;
	}
	public String getLibelleSROpt3() {
		return libelleSROpt3;
	}
	public void setLibelleSROpt3(String libelleSROpt3) {
		this.libelleSROpt3 = libelleSROpt3;
	}
	public String getIdSROpt3() {
		return idSROpt3;
	}
	public void setIdSROpt3(String idSROpt3) {
		this.idSROpt3 = idSROpt3;
	}
	public String getCapitalSROpt3() {
		return capitalSROpt3;
	}
	public void setCapitalSROpt3(String capitalSROpt3) {
		this.capitalSROpt3 = capitalSROpt3;
	}
	public String getFranchiseSROpt3() {
		return franchiseSROpt3;
	}
	public void setFranchiseSROpt3(String franchiseSROpt3) {
		this.franchiseSROpt3 = franchiseSROpt3;
	}
	public String getLibelleIndChaufOpt1() {
		return libelleIndChaufOpt1;
	}
	public void setLibelleIndChaufOpt1(String libelleIndChaufOpt1) {
		this.libelleIndChaufOpt1 = libelleIndChaufOpt1;
	}
	public String getIdIndChaufOpt1() {
		return idIndChaufOpt1;
	}
	public void setIdIndChaufOpt1(String idIndChaufOpt1) {
		this.idIndChaufOpt1 = idIndChaufOpt1;
	}
	public String getCapitalIndChaufOpt1() {
		return capitalIndChaufOpt1;
	}
	public void setCapitalIndChaufOpt1(String capitalIndChaufOpt1) {
		this.capitalIndChaufOpt1 = capitalIndChaufOpt1;
	}
	public String getFranchiseIndChaufOpt1() {
		return franchiseIndChaufOpt1;
	}
	public void setFranchiseIndChaufOpt1(String franchiseIndChaufOpt1) {
		this.franchiseIndChaufOpt1 = franchiseIndChaufOpt1;
	}
	public String getLibelleIndChaufOpt2() {
		return libelleIndChaufOpt2;
	}
	public void setLibelleIndChaufOpt2(String libelleIndChaufOpt2) {
		this.libelleIndChaufOpt2 = libelleIndChaufOpt2;
	}
	public String getIdIndChaufOpt2() {
		return idIndChaufOpt2;
	}
	public void setIdIndChaufOpt2(String idIndChaufOpt2) {
		this.idIndChaufOpt2 = idIndChaufOpt2;
	}
	public String getCapitalIndChaufOpt2() {
		return capitalIndChaufOpt2;
	}
	public void setCapitalIndChaufOpt2(String capitalIndChaufOpt2) {
		this.capitalIndChaufOpt2 = capitalIndChaufOpt2;
	}
	public String getFranchiseIndChaufOpt2() {
		return franchiseIndChaufOpt2;
	}
	public void setFranchiseIndChaufOpt2(String franchiseIndChaufOpt2) {
		this.franchiseIndChaufOpt2 = franchiseIndChaufOpt2;
	}
	public String getLibelleIndChaufOpt3() {
		return libelleIndChaufOpt3;
	}
	public void setLibelleIndChaufOpt3(String libelleIndChaufOpt3) {
		this.libelleIndChaufOpt3 = libelleIndChaufOpt3;
	}
	public String getIdIndChaufOpt3() {
		return idIndChaufOpt3;
	}
	public void setIdIndChaufOpt3(String idIndChaufOpt3) {
		this.idIndChaufOpt3 = idIndChaufOpt3;
	}
	public String getCapitalIndChaufOpt3() {
		return capitalIndChaufOpt3;
	}
	public void setCapitalIndChaufOpt3(String capitalIndChaufOpt3) {
		this.capitalIndChaufOpt3 = capitalIndChaufOpt3;
	}
	public String getFranchiseIndChaufOpt3() {
		return franchiseIndChaufOpt3;
	}
	public void setFranchiseIndChaufOpt3(String franchiseIndChaufOpt3) {
		this.franchiseIndChaufOpt3 = franchiseIndChaufOpt3;
	}
	public String getLibelleDommagesCol() {
		return libelleDommagesCol;
	}
	public void setLibelleDommagesCol(String libelleDommagesCol) {
		this.libelleDommagesCol = libelleDommagesCol;
	}
	public String getIdDommagesCol() {
		return idDommagesCol;
	}
	public void setIdDommagesCol(String idDommagesCol) {
		this.idDommagesCol = idDommagesCol;
	}
	public String getCapitalDommagesCol() {
		return capitalDommagesCol;
	}
	public void setCapitalDommagesCol(String capitalDommagesCol) {
		this.capitalDommagesCol = capitalDommagesCol;
	}
	public String getFranchiseDommagesCol() {
		return franchiseDommagesCol;
	}
	public void setFranchiseDommagesCol(String franchiseDommagesCol) {
		this.franchiseDommagesCol = franchiseDommagesCol;
	}
	public String getLibelleDommagesTA() {
		return libelleDommagesTA;
	}
	public void setLibelleDommagesTA(String libelleDommagesTA) {
		this.libelleDommagesTA = libelleDommagesTA;
	}
	public String getIdDommagesTA() {
		return idDommagesTA;
	}
	public void setIdDommagesTA(String idDommagesTA) {
		this.idDommagesTA = idDommagesTA;
	}
	public String getCapitalDommagesTA() {
		return capitalDommagesTA;
	}
	public void setCapitalDommagesTA(String capitalDommagesTA) {
		this.capitalDommagesTA = capitalDommagesTA;
	}
	public String getFranchiseDommagesTA() {
		return franchiseDommagesTA;
	}
	public void setFranchiseDommagesTA(String franchiseDommagesTA) {
		this.franchiseDommagesTA = franchiseDommagesTA;
	}
	public String getLibelleBDGOpt1() {
		return libelleBDGOpt1;
	}
	public void setLibelleBDGOpt1(String libelleBDGOpt1) {
		this.libelleBDGOpt1 = libelleBDGOpt1;
	}
	public String getIdBDGOpt1() {
		return idBDGOpt1;
	}
	public void setIdBDGOpt1(String idBDGOpt1) {
		this.idBDGOpt1 = idBDGOpt1;
	}
	public String getCapitalBDGOpt1() {
		return capitalBDGOpt1;
	}
	public void setCapitalBDGOpt1(String capitalBDGOpt1) {
		this.capitalBDGOpt1 = capitalBDGOpt1;
	}
	public String getFranchiseBDGOpt1() {
		return franchiseBDGOpt1;
	}
	public void setFranchiseBDGOpt1(String franchiseBDGOpt1) {
		this.franchiseBDGOpt1 = franchiseBDGOpt1;
	}
	public String getLibelleBDGOpt2() {
		return libelleBDGOpt2;
	}
	public void setLibelleBDGOpt2(String libelleBDGOpt2) {
		this.libelleBDGOpt2 = libelleBDGOpt2;
	}
	public String getIdBDGOpt2() {
		return idBDGOpt2;
	}
	public void setIdBDGOpt2(String idBDGOpt2) {
		this.idBDGOpt2 = idBDGOpt2;
	}
	public String getCapitalBDGOpt2() {
		return capitalBDGOpt2;
	}
	public void setCapitalBDGOpt2(String capitalBDGOpt2) {
		this.capitalBDGOpt2 = capitalBDGOpt2;
	}
	public String getFranchiseBDGOpt2() {
		return franchiseBDGOpt2;
	}
	public void setFranchiseBDGOpt2(String franchiseBDGOpt2) {
		this.franchiseBDGOpt2 = franchiseBDGOpt2;
	}
	public String getLibelleBDGOpt3() {
		return libelleBDGOpt3;
	}
	public void setLibelleBDGOpt3(String libelleBDGOpt3) {
		this.libelleBDGOpt3 = libelleBDGOpt3;
	}
	public String getIdBDGOpt3() {
		return idBDGOpt3;
	}
	public void setIdBDGOpt3(String idBDGOpt3) {
		this.idBDGOpt3 = idBDGOpt3;
	}
	public String getCapitalBDGOpt3() {
		return capitalBDGOpt3;
	}
	public void setCapitalBDGOpt3(String capitalBDGOpt3) {
		this.capitalBDGOpt3 = capitalBDGOpt3;
	}
	public String getFranchiseBDGOpt3() {
		return franchiseBDGOpt3;
	}
	public void setFranchiseBDGOpt3(String franchiseBDGOpt3) {
		this.franchiseBDGOpt3 = franchiseBDGOpt3;
	}
	public String getLibelleVol() {
		return libelleVol;
	}
	public void setLibelleVol(String libelleVol) {
		this.libelleVol = libelleVol;
	}
	public String getIdVol() {
		return idVol;
	}
	public void setIdVol(String idVol) {
		this.idVol = idVol;
	}
	public String getCapitalVol() {
		return capitalVol;
	}
	public void setCapitalVol(String capitalVol) {
		this.capitalVol = capitalVol;
	}
	public String getFranchiseVol() {
		return franchiseVol;
	}
	public void setFranchiseVol(String franchiseVol) {
		this.franchiseVol = franchiseVol;
	}
	public String getLibelleRA() {
		return libelleRA;
	}
	public void setLibelleRA(String libelleRA) {
		this.libelleRA = libelleRA;
	}
	public String getIdRA() {
		return idRA;
	}
	public void setIdRA(String idRA) {
		this.idRA = idRA;
	}
	public String getCapitalRA() {
		return capitalRA;
	}
	public void setCapitalRA(String capitalRA) {
		this.capitalRA = capitalRA;
	}
	public String getFranchiseRA() {
		return franchiseRA;
	}
	public void setFranchiseRA(String franchiseRA) {
		this.franchiseRA = franchiseRA;
	}
	public String getLibelleIncendie() {
		return libelleIncendie;
	}
	public void setLibelleIncendie(String libelleIncendie) {
		this.libelleIncendie = libelleIncendie;
	}
	public String getIdIncendie() {
		return idIncendie;
	}
	public void setIdIncendie(String idIncendie) {
		this.idIncendie = idIncendie;
	}
	public String getCapitalIncendie() {
		return capitalIncendie;
	}
	public void setCapitalIncendie(String capitalIncendie) {
		this.capitalIncendie = capitalIncendie;
	}
	public String getFranchiseIncendie() {
		return franchiseIncendie;
	}
	public void setFranchiseIncendie(String franchiseIncendie) {
		this.franchiseIncendie = franchiseIncendie;
	}
	public String getLibelleImmob() {
		return libelleImmob;
	}
	public void setLibelleImmob(String libelleImmob) {
		this.libelleImmob = libelleImmob;
	}
	public String getIdImmob() {
		return idImmob;
	}
	public void setIdImmob(String idImmob) {
		this.idImmob = idImmob;
	}
	public String getCapitalImmob() {
		return capitalImmob;
	}
	public void setCapitalImmob(String capitalImmob) {
		this.capitalImmob = capitalImmob;
	}
	public String getFranchiseImmob() {
		return franchiseImmob;
	}
	public void setFranchiseImmob(String franchiseImmob) {
		this.franchiseImmob = franchiseImmob;
	}
	public String getLibelleVolAcc() {
		return libelleVolAcc;
	}
	public void setLibelleVolAcc(String libelleVolAcc) {
		this.libelleVolAcc = libelleVolAcc;
	}
	public String getIdVolAcc() {
		return idVolAcc;
	}
	public void setIdVolAcc(String idVolAcc) {
		this.idVolAcc = idVolAcc;
	}
	public String getCapitalVolAcc() {
		return capitalVolAcc;
	}
	public void setCapitalVolAcc(String capitalVolAcc) {
		this.capitalVolAcc = capitalVolAcc;
	}
	public String getFranchiseVolAcc() {
		return franchiseVolAcc;
	}
	public void setFranchiseVolAcc(String franchiseVolAcc) {
		this.franchiseVolAcc = franchiseVolAcc;
	}
	public String getLibelleVandalisme() {
		return libelleVandalisme;
	}
	public void setLibelleVandalisme(String libelleVandalisme) {
		this.libelleVandalisme = libelleVandalisme;
	}
	public String getIdVandalisme() {
		return idVandalisme;
	}
	public void setIdVandalisme(String idVandalisme) {
		this.idVandalisme = idVandalisme;
	}
	public String getCapitalVandalisme() {
		return capitalVandalisme;
	}
	public void setCapitalVandalisme(String capitalVandalisme) {
		this.capitalVandalisme = capitalVandalisme;
	}
	public String getFranchiseVandalisme() {
		return franchiseVandalisme;
	}
	public void setFranchiseVandalisme(String franchiseVandalisme) {
		this.franchiseVandalisme = franchiseVandalisme;
	}
	public java.math.BigDecimal getPrimeAnRC() {
		return primeAnRC;
	}
	public void setPrimeAnRC(java.math.BigDecimal primeAnRC) {
		this.primeAnRC = primeAnRC;
	}
	public java.math.BigDecimal getPrimeProrataRC() {
		return primeProrataRC;
	}
	public void setPrimeProrataRC(java.math.BigDecimal primeProrataRC) {
		this.primeProrataRC = primeProrataRC;
	}
	public int getTauxRedRC() {
		return tauxRedRC;
	}
	public void setTauxRedRC(int tauxRedRC) {
		this.tauxRedRC = tauxRedRC;
	}
	public int getTauxBonusRC() {
		return tauxBonusRC;
	}
	public void setTauxBonusRC(int tauxBonusRC) {
		this.tauxBonusRC = tauxBonusRC;
	}
	public int getTauxMalusRC() {
		return tauxMalusRC;
	}
	public void setTauxMalusRC(int tauxMalusRC) {
		this.tauxMalusRC = tauxMalusRC;
	}
	public java.math.BigDecimal getPrimeAnDR() {
		return primeAnDR;
	}
	public void setPrimeAnDR(java.math.BigDecimal primeAnDR) {
		this.primeAnDR = primeAnDR;
	}
	public java.math.BigDecimal getPrimeProrataDR() {
		return primeProrataDR;
	}
	public void setPrimeProrataDR(java.math.BigDecimal primeProrataDR) {
		this.primeProrataDR = primeProrataDR;
	}
	public int getTauxRedDR() {
		return tauxRedDR;
	}
	public void setTauxRedDR(int tauxRedDR) {
		this.tauxRedDR = tauxRedDR;
	}
	public int getTauxBonusDR() {
		return tauxBonusDR;
	}
	public void setTauxBonusDR(int tauxBonusDR) {
		this.tauxBonusDR = tauxBonusDR;
	}
	public int getTauxMalusDR() {
		return tauxMalusDR;
	}
	public void setTauxMalusDR(int tauxMalusDR) {
		this.tauxMalusDR = tauxMalusDR;
	}
	public java.math.BigDecimal getPrimeAnSROpt1() {
		return primeAnSROpt1;
	}
	public void setPrimeAnSROpt1(java.math.BigDecimal primeAnSROpt1) {
		this.primeAnSROpt1 = primeAnSROpt1;
	}
	public java.math.BigDecimal getPrimeProrataSROpt1() {
		return primeProrataSROpt1;
	}
	public void setPrimeProrataSROpt1(java.math.BigDecimal primeProrataSROpt1) {
		this.primeProrataSROpt1 = primeProrataSROpt1;
	}
	public int getTauxRedSROpt1() {
		return tauxRedSROpt1;
	}
	public void setTauxRedSROpt1(int tauxRedSROpt1) {
		this.tauxRedSROpt1 = tauxRedSROpt1;
	}
	public int getTauxBonusSROpt1() {
		return tauxBonusSROpt1;
	}
	public void setTauxBonusSROpt1(int tauxBonusSROpt1) {
		this.tauxBonusSROpt1 = tauxBonusSROpt1;
	}
	public int getTauxMalusSROpt1() {
		return tauxMalusSROpt1;
	}
	public void setTauxMalusSROpt1(int tauxMalusSROpt1) {
		this.tauxMalusSROpt1 = tauxMalusSROpt1;
	}
	public java.math.BigDecimal getPrimeAnSROpt2() {
		return primeAnSROpt2;
	}
	public void setPrimeAnSROpt2(java.math.BigDecimal primeAnSROpt2) {
		this.primeAnSROpt2 = primeAnSROpt2;
	}
	public java.math.BigDecimal getPrimeProrataSROpt2() {
		return primeProrataSROpt2;
	}
	public void setPrimeProrataSROpt2(java.math.BigDecimal primeProrataSROpt2) {
		this.primeProrataSROpt2 = primeProrataSROpt2;
	}
	public int getTauxRedSROpt2() {
		return tauxRedSROpt2;
	}
	public void setTauxRedSROpt2(int tauxRedSROpt2) {
		this.tauxRedSROpt2 = tauxRedSROpt2;
	}
	public int getTauxBonusSROpt2() {
		return tauxBonusSROpt2;
	}
	public void setTauxBonusSROpt2(int tauxBonusSROpt2) {
		this.tauxBonusSROpt2 = tauxBonusSROpt2;
	}
	public int getTauxMalusSROpt2() {
		return tauxMalusSROpt2;
	}
	public void setTauxMalusSROpt2(int tauxMalusSROpt2) {
		this.tauxMalusSROpt2 = tauxMalusSROpt2;
	}
	public java.math.BigDecimal getPrimeAnSROpt3() {
		return primeAnSROpt3;
	}
	public void setPrimeAnSROpt3(java.math.BigDecimal primeAnSROpt3) {
		this.primeAnSROpt3 = primeAnSROpt3;
	}
	public java.math.BigDecimal getPrimeProrataSROpt3() {
		return primeProrataSROpt3;
	}
	public void setPrimeProrataSROpt3(java.math.BigDecimal primeProrataSROpt3) {
		this.primeProrataSROpt3 = primeProrataSROpt3;
	}
	public int getTauxRedSROpt3() {
		return tauxRedSROpt3;
	}
	public void setTauxRedSROpt3(int tauxRedSROpt3) {
		this.tauxRedSROpt3 = tauxRedSROpt3;
	}
	public int getTauxBonusSROpt3() {
		return tauxBonusSROpt3;
	}
	public void setTauxBonusSROpt3(int tauxBonusSROpt3) {
		this.tauxBonusSROpt3 = tauxBonusSROpt3;
	}
	public int getTauxMalusSROpt3() {
		return tauxMalusSROpt3;
	}
	public void setTauxMalusSROpt3(int tauxMalusSROpt3) {
		this.tauxMalusSROpt3 = tauxMalusSROpt3;
	}
	public java.math.BigDecimal getPrimeAnDommagesCol() {
		return primeAnDommagesCol;
	}
	public void setPrimeAnDommagesCol(java.math.BigDecimal primeAnDommagesCol) {
		this.primeAnDommagesCol = primeAnDommagesCol;
	}
	public java.math.BigDecimal getPrimeProrataDommagesCol() {
		return primeProrataDommagesCol;
	}
	public void setPrimeProrataDommagesCol(java.math.BigDecimal primeProrataDommagesCol) {
		this.primeProrataDommagesCol = primeProrataDommagesCol;
	}
	public int getTauxRedDommagesCol() {
		return tauxRedDommagesCol;
	}
	public void setTauxRedDommagesCol(int tauxRedDommagesCol) {
		this.tauxRedDommagesCol = tauxRedDommagesCol;
	}
	public int getTauxBonusDommagesCol() {
		return tauxBonusDommagesCol;
	}
	public void setTauxBonusDommagesCol(int tauxBonusDommagesCol) {
		this.tauxBonusDommagesCol = tauxBonusDommagesCol;
	}
	public int getTauxMalusDommagesCol() {
		return tauxMalusDommagesCol;
	}
	public void setTauxMalusDommagesCol(int tauxMalusDommagesCol) {
		this.tauxMalusDommagesCol = tauxMalusDommagesCol;
	}
	public java.math.BigDecimal getPrimeAnDommagesTA() {
		return primeAnDommagesTA;
	}
	public void setPrimeAnDommagesTA(java.math.BigDecimal primeAnDommagesTA) {
		this.primeAnDommagesTA = primeAnDommagesTA;
	}
	public java.math.BigDecimal getPrimeProrataDommagesTA() {
		return primeProrataDommagesTA;
	}
	public void setPrimeProrataDommagesTA(java.math.BigDecimal primeProrataDommagesTA) {
		this.primeProrataDommagesTA = primeProrataDommagesTA;
	}
	public int getTauxRedDommagesTA() {
		return tauxRedDommagesTA;
	}
	public void setTauxRedDommagesTA(int tauxRedDommagesTA) {
		this.tauxRedDommagesTA = tauxRedDommagesTA;
	}
	public int getTauxBonusDommagesTA() {
		return tauxBonusDommagesTA;
	}
	public void setTauxBonusDommagesTA(int tauxBonusDommagesTA) {
		this.tauxBonusDommagesTA = tauxBonusDommagesTA;
	}
	public int getTauxMalusDommagesTA() {
		return tauxMalusDommagesTA;
	}
	public void setTauxMalusDommagesTA(int tauxMalusDommagesTA) {
		this.tauxMalusDommagesTA = tauxMalusDommagesTA;
	}
	public java.math.BigDecimal getPrimeAnBDGOpt1() {
		return primeAnBDGOpt1;
	}
	public void setPrimeAnBDGOpt1(java.math.BigDecimal primeAnBDGOpt1) {
		this.primeAnBDGOpt1 = primeAnBDGOpt1;
	}
	public java.math.BigDecimal getPrimeProrataBDGOpt1() {
		return primeProrataBDGOpt1;
	}
	public void setPrimeProrataBDGOpt1(java.math.BigDecimal primeProrataBDGOpt1) {
		this.primeProrataBDGOpt1 = primeProrataBDGOpt1;
	}
	public int getTauxRedBDGOpt1() {
		return tauxRedBDGOpt1;
	}
	public void setTauxRedBDGOpt1(int tauxRedBDGOpt1) {
		this.tauxRedBDGOpt1 = tauxRedBDGOpt1;
	}
	public int getTauxBonusBDGOpt1() {
		return tauxBonusBDGOpt1;
	}
	public void setTauxBonusBDGOpt1(int tauxBonusBDGOpt1) {
		this.tauxBonusBDGOpt1 = tauxBonusBDGOpt1;
	}
	public int getTauxMalusBDGOpt1() {
		return tauxMalusBDGOpt1;
	}
	public void setTauxMalusBDGOpt1(int tauxMalusBDGOpt1) {
		this.tauxMalusBDGOpt1 = tauxMalusBDGOpt1;
	}
	public java.math.BigDecimal getPrimeAnBDGOpt2() {
		return primeAnBDGOpt2;
	}
	public void setPrimeAnBDGOpt2(java.math.BigDecimal primeAnBDGOpt2) {
		this.primeAnBDGOpt2 = primeAnBDGOpt2;
	}
	public java.math.BigDecimal getPrimeProrataBDGOpt2() {
		return primeProrataBDGOpt2;
	}
	public void setPrimeProrataBDGOpt2(java.math.BigDecimal primeProrataBDGOpt2) {
		this.primeProrataBDGOpt2 = primeProrataBDGOpt2;
	}
	public int getTauxRedBDGOpt2() {
		return tauxRedBDGOpt2;
	}
	public void setTauxRedBDGOpt2(int tauxRedBDGOpt2) {
		this.tauxRedBDGOpt2 = tauxRedBDGOpt2;
	}
	public int getTauxBonusBDGOpt2() {
		return tauxBonusBDGOpt2;
	}
	public void setTauxBonusBDGOpt2(int tauxBonusBDGOpt2) {
		this.tauxBonusBDGOpt2 = tauxBonusBDGOpt2;
	}
	public int getTauxMalusBDGOpt2() {
		return tauxMalusBDGOpt2;
	}
	public void setTauxMalusBDGOpt2(int tauxMalusBDGOpt2) {
		this.tauxMalusBDGOpt2 = tauxMalusBDGOpt2;
	}
	public java.math.BigDecimal getPrimeAnBDGOpt3() {
		return primeAnBDGOpt3;
	}
	public void setPrimeAnBDGOpt3(java.math.BigDecimal primeAnBDGOpt3) {
		this.primeAnBDGOpt3 = primeAnBDGOpt3;
	}
	public java.math.BigDecimal getPrimeProrataBDGOpt3() {
		return primeProrataBDGOpt3;
	}
	public void setPrimeProrataBDGOpt3(java.math.BigDecimal primeProrataBDGOpt3) {
		this.primeProrataBDGOpt3 = primeProrataBDGOpt3;
	}
	public int getTauxRedBDGOpt3() {
		return tauxRedBDGOpt3;
	}
	public void setTauxRedBDGOpt3(int tauxRedBDGOpt3) {
		this.tauxRedBDGOpt3 = tauxRedBDGOpt3;
	}
	public int getTauxBonusBDGOpt3() {
		return tauxBonusBDGOpt3;
	}
	public void setTauxBonusBDGOpt3(int tauxBonusBDGOpt3) {
		this.tauxBonusBDGOpt3 = tauxBonusBDGOpt3;
	}
	public int getTauxMalusBDGOpt3() {
		return tauxMalusBDGOpt3;
	}
	public void setTauxMalusBDGOpt3(int tauxMalusBDGOpt3) {
		this.tauxMalusBDGOpt3 = tauxMalusBDGOpt3;
	}
	public java.math.BigDecimal getPrimeAnVol() {
		return primeAnVol;
	}
	public void setPrimeAnVol(java.math.BigDecimal primeAnVol) {
		this.primeAnVol = primeAnVol;
	}
	public java.math.BigDecimal getPrimeProrataVol() {
		return primeProrataVol;
	}
	public void setPrimeProrataVol(java.math.BigDecimal primeProrataVol) {
		this.primeProrataVol = primeProrataVol;
	}
	public int getTauxRedVol() {
		return tauxRedVol;
	}
	public void setTauxRedVol(int tauxRedVol) {
		this.tauxRedVol = tauxRedVol;
	}
	public int getTauxBonusVol() {
		return tauxBonusVol;
	}
	public void setTauxBonusVol(int tauxBonusVol) {
		this.tauxBonusVol = tauxBonusVol;
	}
	public int getTauxMalusVol() {
		return tauxMalusVol;
	}
	public void setTauxMalusVol(int tauxMalusVol) {
		this.tauxMalusVol = tauxMalusVol;
	}
	public java.math.BigDecimal getPrimeAnRA() {
		return primeAnRA;
	}
	public void setPrimeAnRA(java.math.BigDecimal primeAnRA) {
		this.primeAnRA = primeAnRA;
	}
	public java.math.BigDecimal getPrimeProrataRA() {
		return primeProrataRA;
	}
	public void setPrimeProrataRA(java.math.BigDecimal primeProrataRA) {
		this.primeProrataRA = primeProrataRA;
	}
	public int getTauxRedRA() {
		return tauxRedRA;
	}
	public void setTauxRedRA(int tauxRedRA) {
		this.tauxRedRA = tauxRedRA;
	}
	public int getTauxBonusRA() {
		return tauxBonusRA;
	}
	public void setTauxBonusRA(int tauxBonusRA) {
		this.tauxBonusRA = tauxBonusRA;
	}
	public int getTauxMalusRA() {
		return tauxMalusRA;
	}
	public void setTauxMalusRA(int tauxMalusRA) {
		this.tauxMalusRA = tauxMalusRA;
	}
	public java.math.BigDecimal getPrimeAnIncendie() {
		return primeAnIncendie;
	}
	public void setPrimeAnIncendie(java.math.BigDecimal primeAnIncendie) {
		this.primeAnIncendie = primeAnIncendie;
	}
	public java.math.BigDecimal getPrimeProrataIncendie() {
		return primeProrataIncendie;
	}
	public void setPrimeProrataIncendie(java.math.BigDecimal primeProrataIncendie) {
		this.primeProrataIncendie = primeProrataIncendie;
	}
	public int getTauxRedIncendie() {
		return tauxRedIncendie;
	}
	public void setTauxRedIncendie(int tauxRedIncendie) {
		this.tauxRedIncendie = tauxRedIncendie;
	}
	public int getTauxBonusIncendie() {
		return tauxBonusIncendie;
	}
	public void setTauxBonusIncendie(int tauxBonusIncendie) {
		this.tauxBonusIncendie = tauxBonusIncendie;
	}
	public int getTauxMalusIncendie() {
		return tauxMalusIncendie;
	}
	public void setTauxMalusIncendie(int tauxMalusIncendie) {
		this.tauxMalusIncendie = tauxMalusIncendie;
	}
	public java.math.BigDecimal getPrimeAnImmob() {
		return primeAnImmob;
	}
	public void setPrimeAnImmob(java.math.BigDecimal primeAnImmob) {
		this.primeAnImmob = primeAnImmob;
	}
	public java.math.BigDecimal getPrimeProrataImmob() {
		return primeProrataImmob;
	}
	public void setPrimeProrataImmob(java.math.BigDecimal primeProrataImmob) {
		this.primeProrataImmob = primeProrataImmob;
	}
	public int getTauxRedImmob() {
		return tauxRedImmob;
	}
	public void setTauxRedImmob(int tauxRedImmob) {
		this.tauxRedImmob = tauxRedImmob;
	}
	public int getTauxBonusImmob() {
		return tauxBonusImmob;
	}
	public void setTauxBonusImmob(int tauxBonusImmob) {
		this.tauxBonusImmob = tauxBonusImmob;
	}
	public int getTauxMalusImmob() {
		return tauxMalusImmob;
	}
	public void setTauxMalusImmob(int tauxMalusImmob) {
		this.tauxMalusImmob = tauxMalusImmob;
	}
	public java.math.BigDecimal getPrimeAnVolAcc() {
		return primeAnVolAcc;
	}
	public void setPrimeAnVolAcc(java.math.BigDecimal primeAnVolAcc) {
		this.primeAnVolAcc = primeAnVolAcc;
	}
	public java.math.BigDecimal getPrimeProrataVolAcc() {
		return primeProrataVolAcc;
	}
	public void setPrimeProrataVolAcc(java.math.BigDecimal primeProrataVolAcc) {
		this.primeProrataVolAcc = primeProrataVolAcc;
	}
	public int getTauxRedVolAcc() {
		return tauxRedVolAcc;
	}
	public void setTauxRedVolAcc(int tauxRedVolAcc) {
		this.tauxRedVolAcc = tauxRedVolAcc;
	}
	public int getTauxBonusVolAcc() {
		return tauxBonusVolAcc;
	}
	public void setTauxBonusVolAcc(int tauxBonusVolAcc) {
		this.tauxBonusVolAcc = tauxBonusVolAcc;
	}
	public int getTauxMalusVolAcc() {
		return tauxMalusVolAcc;
	}
	public void setTauxMalusVolAcc(int tauxMalusVolAcc) {
		this.tauxMalusVolAcc = tauxMalusVolAcc;
	}
	public java.math.BigDecimal getPrimeAnVandalisme() {
		return primeAnVandalisme;
	}
	public void setPrimeAnVandalisme(java.math.BigDecimal primeAnVandalisme) {
		this.primeAnVandalisme = primeAnVandalisme;
	}
	public java.math.BigDecimal getPrimeProrataVandalisme() {
		return primeProrataVandalisme;
	}
	public void setPrimeProrataVandalisme(java.math.BigDecimal primeProrataVandalisme) {
		this.primeProrataVandalisme = primeProrataVandalisme;
	}
	public int getTauxRedVandalisme() {
		return tauxRedVandalisme;
	}
	public void setTauxRedVandalisme(int tauxRedVandalisme) {
		this.tauxRedVandalisme = tauxRedVandalisme;
	}
	public int getTauxBonusVandalisme() {
		return tauxBonusVandalisme;
	}
	public void setTauxBonusVandalisme(int tauxBonusVandalisme) {
		this.tauxBonusVandalisme = tauxBonusVandalisme;
	}
	public int getTauxMalusVandalisme() {
		return tauxMalusVandalisme;
	}
	public void setTauxMalusVandalisme(int tauxMalusVandalisme) {
		this.tauxMalusVandalisme = tauxMalusVandalisme;
	}
	public boolean isStatutRC() {
		return statutRC;
	}
	public void setStatutRC(boolean statutRC) {
		this.statutRC = statutRC;
	}
	public boolean isModeRC() {
		return modeRC;
	}
	public void setModeRC(boolean modeRC) {
		this.modeRC = modeRC;
	}
	public boolean isChoixRC() {
		return choixRC;
	}
	public void setChoixRC(boolean choixRC) {
		this.choixRC = choixRC;
	}
	public boolean isStatutDR() {
		return statutDR;
	}
	public void setStatutDR(boolean statutDR) {
		this.statutDR = statutDR;
	}
	public boolean isModeDR() {
		return modeDR;
	}
	public void setModeDR(boolean modeDR) {
		this.modeDR = modeDR;
	}
	public boolean isChoixDR() {
		return choixDR;
	}
	public void setChoixDR(boolean choixDR) {
		this.choixDR = choixDR;
	}
	public boolean isStatutSROpt1() {
		return statutSROpt1;
	}
	public void setStatutSROpt1(boolean statutSROpt1) {
		this.statutSROpt1 = statutSROpt1;
	}
	public boolean isModeSROpt1() {
		return modeSROpt1;
	}
	public void setModeSROpt1(boolean modeSROpt1) {
		this.modeSROpt1 = modeSROpt1;
	}
	public boolean isChoixSROpt1() {
		return choixSROpt1;
	}
	public void setChoixSROpt1(boolean choixSROpt1) {
		this.choixSROpt1 = choixSROpt1;
	}
	public boolean isStatutSROpt2() {
		return statutSROpt2;
	}
	public void setStatutSROpt2(boolean statutSROpt2) {
		this.statutSROpt2 = statutSROpt2;
	}
	public boolean isModeSROpt2() {
		return modeSROpt2;
	}
	public void setModeSROpt2(boolean modeSROpt2) {
		this.modeSROpt2 = modeSROpt2;
	}
	public boolean isChoixSROpt2() {
		return choixSROpt2;
	}
	public void setChoixSROpt2(boolean choixSROpt2) {
		this.choixSROpt2 = choixSROpt2;
	}
	public boolean isStatutSROpt3() {
		return statutSROpt3;
	}
	public void setStatutSROpt3(boolean statutSROpt3) {
		this.statutSROpt3 = statutSROpt3;
	}
	public boolean isModeSROpt3() {
		return modeSROpt3;
	}
	public void setModeSROpt3(boolean modeSROpt3) {
		this.modeSROpt3 = modeSROpt3;
	}
	public boolean isChoixSROpt3() {
		return choixSROpt3;
	}
	public void setChoixSROpt3(boolean choixSROpt3) {
		this.choixSROpt3 = choixSROpt3;
	}
	public boolean isStatutDommagesCol() {
		return statutDommagesCol;
	}
	public void setStatutDommagesCol(boolean statutDommagesCol) {
		this.statutDommagesCol = statutDommagesCol;
	}
	public boolean isModeDommagesCol() {
		return modeDommagesCol;
	}
	public void setModeDommagesCol(boolean modeDommagesCol) {
		this.modeDommagesCol = modeDommagesCol;
	}
	public boolean isChoixDommagesCol() {
		return choixDommagesCol;
	}
	public void setChoixDommagesCol(boolean choixDommagesCol) {
		this.choixDommagesCol = choixDommagesCol;
	}
	public boolean isStatutDommagesTA() {
		return statutDommagesTA;
	}
	public void setStatutDommagesTA(boolean statutDommagesTA) {
		this.statutDommagesTA = statutDommagesTA;
	}
	public boolean isModeDommagesTA() {
		return modeDommagesTA;
	}
	public void setModeDommagesTA(boolean modeDommagesTA) {
		this.modeDommagesTA = modeDommagesTA;
	}
	public boolean isChoixDommagesTA() {
		return choixDommagesTA;
	}
	public void setChoixDommagesTA(boolean choixDommagesTA) {
		this.choixDommagesTA = choixDommagesTA;
	}
	public boolean isStatutBDGOpt1() {
		return statutBDGOpt1;
	}
	public void setStatutBDGOpt1(boolean statutBDGOpt1) {
		this.statutBDGOpt1 = statutBDGOpt1;
	}
	public boolean isModeBDGOpt1() {
		return modeBDGOpt1;
	}
	public void setModeBDGOpt1(boolean modeBDGOpt1) {
		this.modeBDGOpt1 = modeBDGOpt1;
	}
	public boolean isChoixOpt1() {
		return choixOpt1;
	}
	public void setChoixOpt1(boolean choixOpt1) {
		this.choixOpt1 = choixOpt1;
	}
	public boolean isStatutBDGOpt2() {
		return statutBDGOpt2;
	}
	public void setStatutBDGOpt2(boolean statutBDGOpt2) {
		this.statutBDGOpt2 = statutBDGOpt2;
	}
	public boolean isModeBDGOpt2() {
		return modeBDGOpt2;
	}
	public void setModeBDGOpt2(boolean modeBDGOpt2) {
		this.modeBDGOpt2 = modeBDGOpt2;
	}
	public boolean isChoixOpt2() {
		return choixOpt2;
	}
	public void setChoixOpt2(boolean choixOpt2) {
		this.choixOpt2 = choixOpt2;
	}
	public boolean isStatutBDGOpt3() {
		return statutBDGOpt3;
	}
	public void setStatutBDGOpt3(boolean statutBDGOpt3) {
		this.statutBDGOpt3 = statutBDGOpt3;
	}
	public boolean isModeBDGOpt3() {
		return modeBDGOpt3;
	}
	public void setModeBDGOpt3(boolean modeBDGOpt3) {
		this.modeBDGOpt3 = modeBDGOpt3;
	}
	public boolean isChoixOpt3() {
		return choixOpt3;
	}
	public void setChoixOpt3(boolean choixOpt3) {
		this.choixOpt3 = choixOpt3;
	}
	public boolean isStatutVol() {
		return statutVol;
	}
	public void setStatutVol(boolean statutVol) {
		this.statutVol = statutVol;
	}
	public boolean isModeVol() {
		return modeVol;
	}
	public void setModeVol(boolean modeVol) {
		this.modeVol = modeVol;
	}
	public boolean isChoixVol() {
		return choixVol;
	}
	public void setChoixVol(boolean choixVol) {
		this.choixVol = choixVol;
	}
	public boolean isStatutRA() {
		return statutRA;
	}
	public void setStatutRA(boolean statutRA) {
		this.statutRA = statutRA;
	}
	public boolean isModeRA() {
		return modeRA;
	}
	public void setModeRA(boolean modeRA) {
		this.modeRA = modeRA;
	}
	public boolean isChoixRA() {
		return choixRA;
	}
	public void setChoixRA(boolean choixRA) {
		this.choixRA = choixRA;
	}
	public boolean isStatutIncendie() {
		return statutIncendie;
	}
	public void setStatutIncendie(boolean statutIncendie) {
		this.statutIncendie = statutIncendie;
	}
	public boolean isModeIncendie() {
		return modeIncendie;
	}
	public void setModeIncendie(boolean modeIncendie) {
		this.modeIncendie = modeIncendie;
	}
	public boolean isChoixIncendie() {
		return choixIncendie;
	}
	public void setChoixIncendie(boolean choixIncendie) {
		this.choixIncendie = choixIncendie;
	}
	public boolean isStatutImmob() {
		return statutImmob;
	}
	public void setStatutImmob(boolean statutImmob) {
		this.statutImmob = statutImmob;
	}
	public boolean isModeImmob() {
		return modeImmob;
	}
	public void setModeImmob(boolean modeImmob) {
		this.modeImmob = modeImmob;
	}
	public boolean isChoixImmob() {
		return choixImmob;
	}
	public void setChoixImmob(boolean choixImmob) {
		this.choixImmob = choixImmob;
	}
	public boolean isStatutVolAcc() {
		return statutVolAcc;
	}
	public void setStatutVolAcc(boolean statutVolAcc) {
		this.statutVolAcc = statutVolAcc;
	}
	public boolean isModeVolAcc() {
		return modeVolAcc;
	}
	public void setModeVolAcc(boolean modeVolAcc) {
		this.modeVolAcc = modeVolAcc;
	}
	public boolean isChoixVolAcc() {
		return choixVolAcc;
	}
	public void setChoixVolAcc(boolean choixVolAcc) {
		this.choixVolAcc = choixVolAcc;
	}
	public boolean isStatutVandalisme() {
		return statutVandalisme;
	}
	public void setStatutVandalisme(boolean statutVandalisme) {
		this.statutVandalisme = statutVandalisme;
	}
	public boolean isModeVandalisme() {
		return modeVandalisme;
	}
	public void setModeVandalisme(boolean modeVandalisme) {
		this.modeVandalisme = modeVandalisme;
	}
	public boolean isChoixVandalisme() {
		return choixVandalisme;
	}
	public void setChoixVandalisme(boolean choixVandalisme) {
		this.choixVandalisme = choixVandalisme;
	}
	public java.math.BigDecimal getPrimeAnIndChaufOpt1() {
		return primeAnIndChaufOpt1;
	}
	public void setPrimeAnIndChaufOpt1(java.math.BigDecimal primeAnIndChaufOpt1) {
		this.primeAnIndChaufOpt1 = primeAnIndChaufOpt1;
	}
	public java.math.BigDecimal getPrimeProrataIndChaufOpt1() {
		return primeProrataIndChaufOpt1;
	}
	public void setPrimeProrataIndChaufOpt1(java.math.BigDecimal primeProrataIndChaufOpt1) {
		this.primeProrataIndChaufOpt1 = primeProrataIndChaufOpt1;
	}
	public int getTauxRedIndChaufOpt1() {
		return tauxRedIndChaufOpt1;
	}
	public void setTauxRedIndChaufOpt1(int tauxRedIndChaufOpt1) {
		this.tauxRedIndChaufOpt1 = tauxRedIndChaufOpt1;
	}
	public int getTauxBonusIndChaufOpt1() {
		return tauxBonusIndChaufOpt1;
	}
	public void setTauxBonusIndChaufOpt1(int tauxBonusIndChaufOpt1) {
		this.tauxBonusIndChaufOpt1 = tauxBonusIndChaufOpt1;
	}
	public int getTauxMalusIndChaufOpt1() {
		return tauxMalusIndChaufOpt1;
	}
	public void setTauxMalusIndChaufOpt1(int tauxMalusIndChaufOpt1) {
		this.tauxMalusIndChaufOpt1 = tauxMalusIndChaufOpt1;
	}
	public java.math.BigDecimal getPrimeAnIndChaufOpt2() {
		return primeAnIndChaufOpt2;
	}
	public void setPrimeAnIndChaufOpt2(java.math.BigDecimal primeAnIndChaufOpt2) {
		this.primeAnIndChaufOpt2 = primeAnIndChaufOpt2;
	}
	public java.math.BigDecimal getPrimeProrataIndChaufOpt2() {
		return primeProrataIndChaufOpt2;
	}
	public void setPrimeProrataIndChaufOpt2(java.math.BigDecimal primeProrataIndChaufOpt2) {
		this.primeProrataIndChaufOpt2 = primeProrataIndChaufOpt2;
	}
	public int getTauxRedIndChaufOpt2() {
		return tauxRedIndChaufOpt2;
	}
	public void setTauxRedIndChaufOpt2(int tauxRedIndChaufOpt2) {
		this.tauxRedIndChaufOpt2 = tauxRedIndChaufOpt2;
	}
	public int getTauxBonusIndChaufOpt2() {
		return tauxBonusIndChaufOpt2;
	}
	public void setTauxBonusIndChaufOpt2(int tauxBonusIndChaufOpt2) {
		this.tauxBonusIndChaufOpt2 = tauxBonusIndChaufOpt2;
	}
	public int getTauxMalusIndChaufOpt2() {
		return tauxMalusIndChaufOpt2;
	}
	public void setTauxMalusIndChaufOpt2(int tauxMalusIndChaufOpt2) {
		this.tauxMalusIndChaufOpt2 = tauxMalusIndChaufOpt2;
	}
	public java.math.BigDecimal getPrimeAnIndChaufOpt3() {
		return primeAnIndChaufOpt3;
	}
	public void setPrimeAnIndChaufOpt3(java.math.BigDecimal primeAnIndChaufOpt3) {
		this.primeAnIndChaufOpt3 = primeAnIndChaufOpt3;
	}
	public java.math.BigDecimal getPrimeProrataIndChaufOpt3() {
		return primeProrataIndChaufOpt3;
	}
	public void setPrimeProrataIndChaufOpt3(java.math.BigDecimal primeProrataIndChaufOpt3) {
		this.primeProrataIndChaufOpt3 = primeProrataIndChaufOpt3;
	}
	public int getTauxRedIndChaufOpt3() {
		return tauxRedIndChaufOpt3;
	}
	public void setTauxRedIndChaufOpt3(int tauxRedIndChaufOpt3) {
		this.tauxRedIndChaufOpt3 = tauxRedIndChaufOpt3;
	}
	public int getTauxBonusIndChaufOpt3() {
		return tauxBonusIndChaufOpt3;
	}
	public void setTauxBonusIndChaufOpt3(int tauxBonusIndChaufOpt3) {
		this.tauxBonusIndChaufOpt3 = tauxBonusIndChaufOpt3;
	}
	public int getTauxMalusIndChaufOpt3() {
		return tauxMalusIndChaufOpt3;
	}
	public void setTauxMalusIndChaufOpt3(int tauxMalusIndChaufOpt3) {
		this.tauxMalusIndChaufOpt3 = tauxMalusIndChaufOpt3;
	}
	public boolean isStatutIndChaufOpt1() {
		return statutIndChaufOpt1;
	}
	public void setStatutIndChaufOpt1(boolean statutIndChaufOpt1) {
		this.statutIndChaufOpt1 = statutIndChaufOpt1;
	}
	public boolean isModeIndChaufOpt1() {
		return modeIndChaufOpt1;
	}
	public void setModeIndChaufOpt1(boolean modeIndChaufOpt1) {
		this.modeIndChaufOpt1 = modeIndChaufOpt1;
	}
	public boolean isChoixIndChaufOpt1() {
		return choixIndChaufOpt1;
	}
	public void setChoixIndChaufOpt1(boolean choixIndChaufOpt1) {
		this.choixIndChaufOpt1 = choixIndChaufOpt1;
	}
	public boolean isStatutIndChaufOpt2() {
		return statutIndChaufOpt2;
	}
	public void setStatutIndChaufOpt2(boolean statutIndChaufOpt2) {
		this.statutIndChaufOpt2 = statutIndChaufOpt2;
	}
	public boolean isModeIndChaufOpt2() {
		return modeIndChaufOpt2;
	}
	public void setModeIndChaufOpt2(boolean modeIndChaufOpt2) {
		this.modeIndChaufOpt2 = modeIndChaufOpt2;
	}
	public boolean isChoixIndChaufOpt2() {
		return choixIndChaufOpt2;
	}
	public void setChoixIndChaufOpt2(boolean choixIndChaufOpt2) {
		this.choixIndChaufOpt2 = choixIndChaufOpt2;
	}
	public boolean isStatutIndChaufOpt3() {
		return statutIndChaufOpt3;
	}
	public void setStatutIndChaufOpt3(boolean statutIndChaufOpt3) {
		this.statutIndChaufOpt3 = statutIndChaufOpt3;
	}
	public boolean isModeIndChaufOpt3() {
		return modeIndChaufOpt3;
	}
	public void setModeIndChaufOpt3(boolean modeIndChaufOpt3) {
		this.modeIndChaufOpt3 = modeIndChaufOpt3;
	}
	public boolean isChoixIndChaufOpt3() {
		return choixIndChaufOpt3;
	}
	public void setChoixIndChaufOpt3(boolean choixIndChaufOpt3) {
		this.choixIndChaufOpt3 = choixIndChaufOpt3;
	}
	public java.math.BigDecimal getPrimeNetAnRC() {
		return primeNetAnRC;
	}
	public void setPrimeNetAnRC(java.math.BigDecimal primeNetAnRC) {
		this.primeNetAnRC = primeNetAnRC;
	}
	public java.math.BigDecimal getMontantRedRC() {
		return montantRedRC;
	}
	public void setMontantRedRC(java.math.BigDecimal montantRedRC) {
		this.montantRedRC = montantRedRC;
	}
	public java.math.BigDecimal getPrimeNetAnDR() {
		return primeNetAnDR;
	}
	public void setPrimeNetAnDR(java.math.BigDecimal primeNetAnDR) {
		this.primeNetAnDR = primeNetAnDR;
	}
	public java.math.BigDecimal getMontantRedDR() {
		return montantRedDR;
	}
	public void setMontantRedDR(java.math.BigDecimal montantRedDR) {
		this.montantRedDR = montantRedDR;
	}
	public java.math.BigDecimal getPrimeNetAnSROpt1() {
		return primeNetAnSROpt1;
	}
	public void setPrimeNetAnSROpt1(java.math.BigDecimal primeNetAnSROpt1) {
		this.primeNetAnSROpt1 = primeNetAnSROpt1;
	}
	public java.math.BigDecimal getMontantRedSROpt1() {
		return montantRedSROpt1;
	}
	public void setMontantRedSROpt1(java.math.BigDecimal montantRedSROpt1) {
		this.montantRedSROpt1 = montantRedSROpt1;
	}
	public java.math.BigDecimal getPrimeNetAnSROpt2() {
		return primeNetAnSROpt2;
	}
	public void setPrimeNetAnSROpt2(java.math.BigDecimal primeNetAnSROpt2) {
		this.primeNetAnSROpt2 = primeNetAnSROpt2;
	}
	public java.math.BigDecimal getMontantRedSROpt2() {
		return montantRedSROpt2;
	}
	public void setMontantRedSROpt2(java.math.BigDecimal montantRedSROpt2) {
		this.montantRedSROpt2 = montantRedSROpt2;
	}
	public java.math.BigDecimal getPrimeNetAnSROpt3() {
		return primeNetAnSROpt3;
	}
	public void setPrimeNetAnSROpt3(java.math.BigDecimal primeNetAnSROpt3) {
		this.primeNetAnSROpt3 = primeNetAnSROpt3;
	}
	public java.math.BigDecimal getMontantRedSROpt3() {
		return montantRedSROpt3;
	}
	public void setMontantRedSROpt3(java.math.BigDecimal montantRedSROpt3) {
		this.montantRedSROpt3 = montantRedSROpt3;
	}
	public java.math.BigDecimal getPrimeNetAnIndChaufOpt1() {
		return primeNetAnIndChaufOpt1;
	}
	public void setPrimeNetAnIndChaufOpt1(java.math.BigDecimal primeNetAnIndChaufOpt1) {
		this.primeNetAnIndChaufOpt1 = primeNetAnIndChaufOpt1;
	}
	public java.math.BigDecimal getMontantRedIndChaufOpt1() {
		return montantRedIndChaufOpt1;
	}
	public void setMontantRedIndChaufOpt1(java.math.BigDecimal montantRedIndChaufOpt1) {
		this.montantRedIndChaufOpt1 = montantRedIndChaufOpt1;
	}
	public java.math.BigDecimal getPrimeNetAnIndChaufOpt2() {
		return primeNetAnIndChaufOpt2;
	}
	public void setPrimeNetAnIndChaufOpt2(java.math.BigDecimal primeNetAnIndChaufOpt2) {
		this.primeNetAnIndChaufOpt2 = primeNetAnIndChaufOpt2;
	}
	public java.math.BigDecimal getMontantRedIndChaufOpt2() {
		return montantRedIndChaufOpt2;
	}
	public void setMontantRedIndChaufOpt2(java.math.BigDecimal montantRedIndChaufOpt2) {
		this.montantRedIndChaufOpt2 = montantRedIndChaufOpt2;
	}
	public java.math.BigDecimal getPrimeNetAnIndChaufOpt3() {
		return primeNetAnIndChaufOpt3;
	}
	public void setPrimeNetAnIndChaufOpt3(java.math.BigDecimal primeNetAnIndChaufOpt3) {
		this.primeNetAnIndChaufOpt3 = primeNetAnIndChaufOpt3;
	}
	public java.math.BigDecimal getMontantRedIndChaufOpt3() {
		return montantRedIndChaufOpt3;
	}
	public void setMontantRedIndChaufOpt3(java.math.BigDecimal montantRedIndChaufOpt3) {
		this.montantRedIndChaufOpt3 = montantRedIndChaufOpt3;
	}
	public java.math.BigDecimal getPrimeNetAnDommagesCol() {
		return primeNetAnDommagesCol;
	}
	public void setPrimeNetAnDommagesCol(java.math.BigDecimal primeNetAnDommagesCol) {
		this.primeNetAnDommagesCol = primeNetAnDommagesCol;
	}
	public java.math.BigDecimal getMontantRedDommagesCol() {
		return montantRedDommagesCol;
	}
	public void setMontantRedDommagesCol(java.math.BigDecimal montantRedDommagesCol) {
		this.montantRedDommagesCol = montantRedDommagesCol;
	}
	public java.math.BigDecimal getPrimeNetAnDommagesTA() {
		return primeNetAnDommagesTA;
	}
	public void setPrimeNetAnDommagesTA(java.math.BigDecimal primeNetAnDommagesTA) {
		this.primeNetAnDommagesTA = primeNetAnDommagesTA;
	}
	public java.math.BigDecimal getMontantRedDommagesTA() {
		return montantRedDommagesTA;
	}
	public void setMontantRedDommagesTA(java.math.BigDecimal montantRedDommagesTA) {
		this.montantRedDommagesTA = montantRedDommagesTA;
	}
	public java.math.BigDecimal getPrimeNetAnBDGOpt1() {
		return primeNetAnBDGOpt1;
	}
	public void setPrimeNetAnBDGOpt1(java.math.BigDecimal primeNetAnBDGOpt1) {
		this.primeNetAnBDGOpt1 = primeNetAnBDGOpt1;
	}
	public java.math.BigDecimal getMontantRedBDGOpt1() {
		return montantRedBDGOpt1;
	}
	public void setMontantRedBDGOpt1(java.math.BigDecimal montantRedBDGOpt1) {
		this.montantRedBDGOpt1 = montantRedBDGOpt1;
	}
	public java.math.BigDecimal getPrimeNetAnBDGOpt2() {
		return primeNetAnBDGOpt2;
	}
	public void setPrimeNetAnBDGOpt2(java.math.BigDecimal primeNetAnBDGOpt2) {
		this.primeNetAnBDGOpt2 = primeNetAnBDGOpt2;
	}
	public java.math.BigDecimal getMontantRedBDGOpt2() {
		return montantRedBDGOpt2;
	}
	public void setMontantRedBDGOpt2(java.math.BigDecimal montantRedBDGOpt2) {
		this.montantRedBDGOpt2 = montantRedBDGOpt2;
	}
	public java.math.BigDecimal getPrimeNetAnBDGOpt3() {
		return primeNetAnBDGOpt3;
	}
	public void setPrimeNetAnBDGOpt3(java.math.BigDecimal primeNetAnBDGOpt3) {
		this.primeNetAnBDGOpt3 = primeNetAnBDGOpt3;
	}
	public java.math.BigDecimal getMontantRedBDGOpt3() {
		return montantRedBDGOpt3;
	}
	public void setMontantRedBDGOpt3(java.math.BigDecimal montantRedBDGOpt3) {
		this.montantRedBDGOpt3 = montantRedBDGOpt3;
	}
	public java.math.BigDecimal getPrimeNetAnVol() {
		return primeNetAnVol;
	}
	public void setPrimeNetAnVol(java.math.BigDecimal primeNetAnVol) {
		this.primeNetAnVol = primeNetAnVol;
	}
	public java.math.BigDecimal getPrimeNetAnRA() {
		return primeNetAnRA;
	}
	public void setPrimeNetAnRA(java.math.BigDecimal primeNetAnRA) {
		this.primeNetAnRA = primeNetAnRA;
	}
	public java.math.BigDecimal getMontantRedRA() {
		return montantRedRA;
	}
	public void setMontantRedRA(java.math.BigDecimal montantRedRA) {
		this.montantRedRA = montantRedRA;
	}
	public java.math.BigDecimal getPrimeNetAnIncendie() {
		return primeNetAnIncendie;
	}
	public void setPrimeNetAnIncendie(java.math.BigDecimal primeNetAnIncendie) {
		this.primeNetAnIncendie = primeNetAnIncendie;
	}
	public java.math.BigDecimal getMontantRedIncendie() {
		return montantRedIncendie;
	}
	public void setMontantRedIncendie(java.math.BigDecimal montantRedIncendie) {
		this.montantRedIncendie = montantRedIncendie;
	}
	public java.math.BigDecimal getPrimeNetAnImmob() {
		return primeNetAnImmob;
	}
	public void setPrimeNetAnImmob(java.math.BigDecimal primeNetAnImmob) {
		this.primeNetAnImmob = primeNetAnImmob;
	}
	public java.math.BigDecimal getMontantRedImmob() {
		return montantRedImmob;
	}
	public void setMontantRedImmob(java.math.BigDecimal montantRedImmob) {
		this.montantRedImmob = montantRedImmob;
	}
	public java.math.BigDecimal getPrimeNetAnVolAcc() {
		return primeNetAnVolAcc;
	}
	public void setPrimeNetAnVolAcc(java.math.BigDecimal primeNetAnVolAcc) {
		this.primeNetAnVolAcc = primeNetAnVolAcc;
	}
	public java.math.BigDecimal getMontantRedVolAcc() {
		return montantRedVolAcc;
	}
	public void setMontantRedVolAcc(java.math.BigDecimal montantRedVolAcc) {
		this.montantRedVolAcc = montantRedVolAcc;
	}
	public java.math.BigDecimal getPrimeNetAnVandalisme() {
		return primeNetAnVandalisme;
	}
	public void setPrimeNetAnVandalisme(java.math.BigDecimal primeNetAnVandalisme) {
		this.primeNetAnVandalisme = primeNetAnVandalisme;
	}
	public java.math.BigDecimal getMontantRedVandalisme() {
		return montantRedVandalisme;
	}
	public void setMontantRedVandalisme(java.math.BigDecimal montantRedVandalisme) {
		this.montantRedVandalisme = montantRedVandalisme;
	}
	public java.math.BigDecimal getMontantRedVol() {
		return montantRedVol;
	}
	public void setMontantRedVol(java.math.BigDecimal montantRedVol) {
		this.montantRedVol = montantRedVol;
	}
	public int getTauxFlotteRC() {
		return tauxFlotteRC;
	}
	public void setTauxFlotteRC(int tauxFlotteRC) {
		this.tauxFlotteRC = tauxFlotteRC;
	}
	public int getTauxFlotteDR() {
		return tauxFlotteDR;
	}
	public void setTauxFlotteDR(int tauxFlotteDR) {
		this.tauxFlotteDR = tauxFlotteDR;
	}
	public int getTauxFlotteSROpt1() {
		return tauxFlotteSROpt1;
	}
	public void setTauxFlotteSROpt1(int tauxFlotteSROpt1) {
		this.tauxFlotteSROpt1 = tauxFlotteSROpt1;
	}
	public int getTauxFlotteSROpt2() {
		return tauxFlotteSROpt2;
	}
	public void setTauxFlotteSROpt2(int tauxFlotteSROpt2) {
		this.tauxFlotteSROpt2 = tauxFlotteSROpt2;
	}
	public int getTauxFlotteSROpt3() {
		return tauxFlotteSROpt3;
	}
	public void setTauxFlotteSROpt3(int tauxFlotteSROpt3) {
		this.tauxFlotteSROpt3 = tauxFlotteSROpt3;
	}
	public int getTauxFlotteIndChaufOpt1() {
		return tauxFlotteIndChaufOpt1;
	}
	public void setTauxFlotteIndChaufOpt1(int tauxFlotteIndChaufOpt1) {
		this.tauxFlotteIndChaufOpt1 = tauxFlotteIndChaufOpt1;
	}
	public int getTauxFlotteIndChaufOpt2() {
		return tauxFlotteIndChaufOpt2;
	}
	public void setTauxFlotteIndChaufOpt2(int tauxFlotteIndChaufOpt2) {
		this.tauxFlotteIndChaufOpt2 = tauxFlotteIndChaufOpt2;
	}
	public int getTauxFlotteIndChaufOpt3() {
		return tauxFlotteIndChaufOpt3;
	}
	public void setTauxFlotteIndChaufOpt3(int tauxFlotteIndChaufOpt3) {
		this.tauxFlotteIndChaufOpt3 = tauxFlotteIndChaufOpt3;
	}
	public int getTauxFlotteDommagesCol() {
		return tauxFlotteDommagesCol;
	}
	public void setTauxFlotteDommagesCol(int tauxFlotteDommagesCol) {
		this.tauxFlotteDommagesCol = tauxFlotteDommagesCol;
	}
	public int getTauxFlotteDommagesTA() {
		return tauxFlotteDommagesTA;
	}
	public void setTauxFlotteDommagesTA(int tauxFlotteDommagesTA) {
		this.tauxFlotteDommagesTA = tauxFlotteDommagesTA;
	}
	public int getTauxFlotteBDGOpt1() {
		return tauxFlotteBDGOpt1;
	}
	public void setTauxFlotteBDGOpt1(int tauxFlotteBDGOpt1) {
		this.tauxFlotteBDGOpt1 = tauxFlotteBDGOpt1;
	}
	public int getTauxFlotteBDGOpt2() {
		return tauxFlotteBDGOpt2;
	}
	public void setTauxFlotteBDGOpt2(int tauxFlotteBDGOpt2) {
		this.tauxFlotteBDGOpt2 = tauxFlotteBDGOpt2;
	}
	public int getTauxFlotteBDGOpt3() {
		return tauxFlotteBDGOpt3;
	}
	public void setTauxFlotteBDGOpt3(int tauxFlotteBDGOpt3) {
		this.tauxFlotteBDGOpt3 = tauxFlotteBDGOpt3;
	}
	public int getTauxFlotteVol() {
		return tauxFlotteVol;
	}
	public void setTauxFlotteVol(int tauxFlotteVol) {
		this.tauxFlotteVol = tauxFlotteVol;
	}
	public int getTauxFlotteRA() {
		return tauxFlotteRA;
	}
	public void setTauxFlotteRA(int tauxFlotteRA) {
		this.tauxFlotteRA = tauxFlotteRA;
	}
	public int getTauxFlotteIncendie() {
		return tauxFlotteIncendie;
	}
	public void setTauxFlotteIncendie(int tauxFlotteIncendie) {
		this.tauxFlotteIncendie = tauxFlotteIncendie;
	}
	public int getTauxFlotteImmob() {
		return tauxFlotteImmob;
	}
	public void setTauxFlotteImmob(int tauxFlotteImmob) {
		this.tauxFlotteImmob = tauxFlotteImmob;
	}
	public int getTauxFlotteVolAcc() {
		return tauxFlotteVolAcc;
	}
	public void setTauxFlotteVolAcc(int tauxFlotteVolAcc) {
		this.tauxFlotteVolAcc = tauxFlotteVolAcc;
	}
	public int getTauxFlotteVandalisme() {
		return tauxFlotteVandalisme;
	}
	public void setTauxFlotteVandalisme(int tauxFlotteVandalisme) {
		this.tauxFlotteVandalisme = tauxFlotteVandalisme;
	}
	public String getLibelleIndChaufSp() {
		return libelleIndChaufSp;
	}
	public void setLibelleIndChaufSp(String libelleIndChaufSp) {
		this.libelleIndChaufSp = libelleIndChaufSp;
	}
	public String getIdIndChaufSp() {
		return idIndChaufSp;
	}
	public void setIdIndChaufSp(String idIndChaufSp) {
		this.idIndChaufSp = idIndChaufSp;
	}
	public String getCapitalIndChaufSp() {
		return capitalIndChaufSp;
	}
	public void setCapitalIndChaufSp(String capitalIndChaufSp) {
		this.capitalIndChaufSp = capitalIndChaufSp;
	}
	public String getFranchiseIndChaufSp() {
		return franchiseIndChaufSp;
	}
	public void setFranchiseIndChaufSp(String franchiseIndChaufSp) {
		this.franchiseIndChaufSp = franchiseIndChaufSp;
	}
	public java.math.BigDecimal getPrimeAnIndChaufSp() {
		return primeAnIndChaufSp;
	}
	public void setPrimeAnIndChaufSp(java.math.BigDecimal primeAnIndChaufSp) {
		this.primeAnIndChaufSp = primeAnIndChaufSp;
	}
	public java.math.BigDecimal getPrimeProrataIndChaufSp() {
		return primeProrataIndChaufSp;
	}
	public void setPrimeProrataIndChaufSp(java.math.BigDecimal primeProrataIndChaufSp) {
		this.primeProrataIndChaufSp = primeProrataIndChaufSp;
	}
	public java.math.BigDecimal getPrimeNetAnIndChaufSp() {
		return primeNetAnIndChaufSp;
	}
	public void setPrimeNetAnIndChaufSp(java.math.BigDecimal primeNetAnIndChaufSp) {
		this.primeNetAnIndChaufSp = primeNetAnIndChaufSp;
	}
	public java.math.BigDecimal getMontantRedIndChaufSp() {
		return montantRedIndChaufSp;
	}
	public void setMontantRedIndChaufSp(java.math.BigDecimal montantRedIndChaufSp) {
		this.montantRedIndChaufSp = montantRedIndChaufSp;
	}
	public int getTauxRedIndChaufSp() {
		return tauxRedIndChaufSp;
	}
	public void setTauxRedIndChaufSp(int tauxRedIndChaufSp) {
		this.tauxRedIndChaufSp = tauxRedIndChaufSp;
	}
	public int getTauxFlotteIndChaufSp() {
		return tauxFlotteIndChaufSp;
	}
	public void setTauxFlotteIndChaufSp(int tauxFlotteIndChaufSp) {
		this.tauxFlotteIndChaufSp = tauxFlotteIndChaufSp;
	}
	public int getTauxBonusIndChaufSp() {
		return tauxBonusIndChaufSp;
	}
	public void setTauxBonusIndChaufSp(int tauxBonusIndChaufSp) {
		this.tauxBonusIndChaufSp = tauxBonusIndChaufSp;
	}
	public int getTauxMalusIndChaufSp() {
		return tauxMalusIndChaufSp;
	}
	public void setTauxMalusIndChaufSp(int tauxMalusIndChaufSp) {
		this.tauxMalusIndChaufSp = tauxMalusIndChaufSp;
	}
	public boolean isStatutIndChaufSp() {
		return statutIndChaufSp;
	}
	public void setStatutIndChaufSp(boolean statutIndChaufSp) {
		this.statutIndChaufSp = statutIndChaufSp;
	}
	public boolean isModeIndChaufSp() {
		return modeIndChaufSp;
	}
	public void setModeIndChaufSp(boolean modeIndChaufSp) {
		this.modeIndChaufSp = modeIndChaufSp;
	}
	public boolean isChoixIndChaufSp() {
		return choixIndChaufSp;
	}
	public void setChoixIndChaufSp(boolean choixIndChaufSp) {
		this.choixIndChaufSp = choixIndChaufSp;
	}
	

	
}
