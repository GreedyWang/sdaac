package com.sdaac.pr.approve.element;

import java.io.Serializable;

public class ApproveMembersElement implements Serializable{
	private static final long serialVersionUID = 5515604918939474016L;
	private String owner;
	private String dm;
	private String fin;
	private String finMgr;
	//private String[] roles = {"fin","finMgr","eng","dgm","gm","therml","dprt"};
	//private String eng;
	private String DGM;
	private String GM;
	private String thermal;
	private String plantMgr;
	private String buyerMgr;
	private String buyer_check;
	private String Fin_check;
	private String PCL_check;
	
	private String finlv1;
	private String finNextLv;
	//private String capitalowner;
	private String capital;
	private String pm;
	//private String projectowner;
	private String ccowner;
	private String supervisor;
	
	public String getFinMgr() {
		return finMgr;
	}
	public void setFinMgr(String finMgr) {
		this.finMgr = finMgr;
	}
//	public String getEng() {
//		return eng;
//	}
//	public void setEng(String eng) {
//		this.eng = eng;
//	}
	

	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public String getPm() {
		return pm;
	}
	public void setPm(String pm) {
		this.pm = pm;
	}

	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getFin() {
		return fin;
	}
	public void setFin(String fin) {
		this.fin = fin;
	}
	public String getCcowner() {
		return ccowner;
	}
	public void setCcowner(String ccowner) {
		this.ccowner = ccowner;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getBuyerMgr() {
		return buyerMgr;
	}
	public void setBuyerMgr(String buyerMgr) {
		this.buyerMgr = buyerMgr;
	}

	public String getBuyer_check() {
		return buyer_check;
	}
	public void setBuyer_check(String buyer_check) {
		this.buyer_check = buyer_check;
	}
	public String getFin_check() {
		return Fin_check;
	}
	public void setFin_check(String fin_check) {
		Fin_check = fin_check;
	}
	public String getPCL_check() {
		return PCL_check;
	}
	public void setPCL_check(String pcl_check) {
		PCL_check = pcl_check;
	}
	public String getDGM() {
		return DGM;
	}
	public void setDGM(String dgm) {
		DGM = dgm;
	}
	public String getGM() {
		return GM;
	}
	public void setGM(String gm) {
		GM = gm;
	}
	public String getThermal() {
		return thermal;
	}
	public void setThermal(String thermal) {
		this.thermal = thermal;
	}
	public String getPlantMgr() {
		return plantMgr;
	}
	public void setPlantMgr(String plantMgr) {
		this.plantMgr = plantMgr;
	}
	
	public void changeApprovor(String netid,String stepName){
		if(stepName.equals("Fin_check")){
			this.setFin_check(netid);
		}else if(stepName.equals("DM")){
			this.setDm(netid);
		}else if(stepName.equals("PCL_check")){
			this.setPCL_check(netid);
		}
	}
	public String getFinlv1() {
		return finlv1;
	}
	public void setFinlv1(String finlv1) {
		this.finlv1 = finlv1;
	}
	public String getFinNextLv() {
		return finNextLv;
	}
	public void setFinNextLv(String finNextLv) {
		this.finNextLv = finNextLv;
	}

}
