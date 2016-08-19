package app.entity;

import java.util.Date;

/**
 * TprojectTeam generated by MyEclipse Persistence Tools
 */

public class TprojectTeam implements java.io.Serializable {

	// Fields

	private String id;

	private Ttheme ttheme;

	private String responsiblePerson;

	private String projectTitle;

	private Date planFinishTime;

	private Date factFinishTime;

	private Date beginTime;

	private Date endTime;

	private Float estimateSavingsCost;

	private Float factSavingsCost;

	private Float expectSavingsCost;

	// Constructors

	/** default constructor */
	public TprojectTeam() {
	}

	/** minimal constructor */
	public TprojectTeam(String id) {
		this.id = id;
	}

	/** full constructor */
	public TprojectTeam(String id, Ttheme ttheme, String responsiblePerson,
			String projectTitle, Date planFinishTime, Date factFinishTime,
			Date beginTime, Date endTime, Float estimateSavingsCost,
			Float factSavingsCost, Float expectSavingsCost) {
		this.id = id;
		this.ttheme = ttheme;
		this.responsiblePerson = responsiblePerson;
		this.projectTitle = projectTitle;
		this.planFinishTime = planFinishTime;
		this.factFinishTime = factFinishTime;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.estimateSavingsCost = estimateSavingsCost;
		this.factSavingsCost = factSavingsCost;
		this.expectSavingsCost = expectSavingsCost;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Ttheme getTtheme() {
		return this.ttheme;
	}

	public void setTtheme(Ttheme ttheme) {
		this.ttheme = ttheme;
	}

	public String getResponsiblePerson() {
		return this.responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public String getProjectTitle() {
		return this.projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public Date getPlanFinishTime() {
		return this.planFinishTime;
	}

	public void setPlanFinishTime(Date planFinishTime) {
		this.planFinishTime = planFinishTime;
	}

	public Date getFactFinishTime() {
		return this.factFinishTime;
	}

	public void setFactFinishTime(Date factFinishTime) {
		this.factFinishTime = factFinishTime;
	}

	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Float getEstimateSavingsCost() {
		return this.estimateSavingsCost;
	}

	public void setEstimateSavingsCost(Float estimateSavingsCost) {
		this.estimateSavingsCost = estimateSavingsCost;
	}

	public Float getFactSavingsCost() {
		return this.factSavingsCost;
	}

	public void setFactSavingsCost(Float factSavingsCost) {
		this.factSavingsCost = factSavingsCost;
	}

	public Float getExpectSavingsCost() {
		return this.expectSavingsCost;
	}

	public void setExpectSavingsCost(Float expectSavingsCost) {
		this.expectSavingsCost = expectSavingsCost;
	}

}