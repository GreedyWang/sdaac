package app.entity.vave;

import app.entity.Tproposal;

/**
 * VaveStatisticsSchedule entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VaveStatisticsSchedule implements java.io.Serializable {

	// Fields
	private Tproposal tproposal=new Tproposal();
	private Float cycle;
	private String progress;
	private Float oneTimeCosts;
	private String item;
	private Integer version;

	// Constructors

	/** default constructor */
	public VaveStatisticsSchedule() {
	}

	/** minimal constructor */
	public VaveStatisticsSchedule(Tproposal tproposal, String item,
			Integer version) {
		this.tproposal = tproposal;
		this.item = item;
		this.version = version;
	}

	/** full constructor */
	public VaveStatisticsSchedule( Tproposal tproposal, Float cycle,
			String progress, Float oneTimeCosts, String item, Integer version) {
		this.tproposal = tproposal;
		this.cycle = cycle;
		this.progress = progress;
		this.oneTimeCosts = oneTimeCosts;
		this.item = item;
		this.version = version;
	}


	public Tproposal getTproposal() {
		return this.tproposal;
	}

	public void setTproposal(Tproposal tproposal) {
		this.tproposal = tproposal;
	}

	public Float getCycle() {
		return this.cycle;
	}

	public void setCycle(Float cycle) {
		this.cycle = cycle;
	}



	public Float getOneTimeCosts() {
		return this.oneTimeCosts;
	}

	public void setOneTimeCosts(Float oneTimeCosts) {
		this.oneTimeCosts = oneTimeCosts;
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

}