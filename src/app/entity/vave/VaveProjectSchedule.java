package app.entity.vave;

import app.entity.Tproposal;

/**
 * VaveProjectSchedule entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VaveProjectSchedule implements java.io.Serializable {

	// Fields

	
	private Tproposal tproposal=new Tproposal();
	private String project;
	private String quantity;
	private String unit;
	private Integer version;

	// Constructors

	/** default constructor */
	public VaveProjectSchedule() {
	}

	/** minimal constructor */
	public VaveProjectSchedule(Tproposal tproposal, String project,
			Integer version) {
	
		this.tproposal = tproposal;
		this.project = project;
		this.version = version;
	}

	/** full constructor */
	public VaveProjectSchedule( Tproposal tproposal, String project,
			String quantity, String unit, Integer version) {
	
		this.tproposal = tproposal;
		this.project = project;
		this.quantity = quantity;
		this.unit = unit;
		this.version = version;
	}

	// Property accessors



	public Tproposal getTproposal() {
		return this.tproposal;
	}

	public void setTproposal(Tproposal tproposal) {
		this.tproposal = tproposal;
	}

	public String getProject() {
		return this.project;
	}

	public void setProject(String project) {
		this.project = project;
	}



	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}