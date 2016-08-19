package com.sdaac.pr.approve.element;

import java.io.Serializable;

public class FormControlElement implements Serializable{
	
	private static final long serialVersionUID = 9086091937275724063L;
	private String prformid;
	private String iscapital;
	private String plant;
	private String issueFor;
	private String dptId;
	private String capitalType;
	private String projectId;
	private String totalCost;	
	private String state;
	private String assignee;
	private String formUrl;	
	private String nextFin;
		 	
	public boolean isHAVCorPTC(){
		return (capitalType!=null 
				&& (capitalType.equals("1")||capitalType.equals("2")));
	}
	
	public String getFormUrl() {
		return formUrl;
	}
	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}
	public String getIscapital() {
		return iscapital;
	}
	public void setIscapital(String iscapital) {
		this.iscapital = iscapital;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getDptId() {
		return dptId;
	}
	public void setDptId(String dptId) {
		this.dptId = dptId;
	}
	public String getCapitalType() {
		return capitalType;
	}
	public void setCapitalType(String capitalType) {
		this.capitalType = capitalType;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	public String getPrformid() {
		return prformid;
	}
	public void setPrformid(String prformid) {
		this.prformid = prformid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getIssueFor() {
		return issueFor;
	}
	public void setIssueFor(String issueFor) {
		this.issueFor = issueFor;
	}

	public String getNextFin() {
		return nextFin;
	}

	public void setNextFin(String nextFin) {
		this.nextFin = nextFin;
	}
	
}
