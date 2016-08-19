package app.entity.epor;

import app.entity.Tempolyee;

/**
 * PrBuyer entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PrBuyer implements java.io.Serializable {

	// Fields

	private String buyId;
	private String buyName;
	private Tempolyee uid =new Tempolyee();
	private String area;
	private String uuid;
	// Constructors

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Tempolyee getUid() {
		return uid;
	}

	public void setUid(Tempolyee uid) {
		this.uid = uid;
	}

	/** default constructor */
	public PrBuyer() {
	}

	/** minimal constructor */
	public PrBuyer(String buyId) {
		this.buyId = buyId;
	}

	/** full constructor */
	public PrBuyer(String buyId, String buyName) {
		this.buyId = buyId;
		this.buyName = buyName;
	}

	// Property accessors

	public String getBuyId() {
		return this.buyId;
	}

	public void setBuyId(String buyId) {
		this.buyId = buyId;
	}

	public String getBuyName() {
		return this.buyName;
	}

	public void setBuyName(String buyName) {
		this.buyName = buyName;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}