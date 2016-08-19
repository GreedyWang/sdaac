package app.entity.epor;

import java.util.HashSet;
import java.util.Set;

/**
 * PrCostCenter entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PrCostCenter implements java.io.Serializable {

	// Fields

	private String costCenterName;
	private String costCenterNameEnglish;
	private Integer id;
	private String remark;
	private String area;
	private String owner;//管理者
	private Set prPrForms = new HashSet(0);
	private String dprt;
	/*
	 *@time 2016/01/01
	 *@by Finance 
	 *@what add auto write IO by the departments  
	 */
	private String departId;//IO belongs department
	private String io;//IO value
	
	// Constructors

	public String getDprt() {
		return dprt;
	}

	public void setDprt(String dprt) {
		this.dprt = dprt;
	}

	/** default constructor */
	public PrCostCenter() {
	}
	
	public PrCostCenter(Integer id) {
		this.id = id;
	}
	
	/** minimal constructor */
	public PrCostCenter(String costCenterName, String costCenterNameEnglish) {
		this.costCenterName = costCenterName;
		this.costCenterNameEnglish = costCenterNameEnglish;
	}

	/** full constructor */
	public PrCostCenter(String costCenterName, String costCenterNameEnglish,
			Set prPrForms) {
		this.costCenterName = costCenterName;
		this.costCenterNameEnglish = costCenterNameEnglish;
		this.prPrForms = prPrForms;
	}

	// Property accessors

	public String getCostCenterName() {
		return this.costCenterName;
	}

	public void setCostCenterName(String costCenterName) {
		this.costCenterName = costCenterName;
	}

	public String getCostCenterNameEnglish() {
		return this.costCenterNameEnglish;
	}

	public void setCostCenterNameEnglish(String costCenterNameEnglish) {
		this.costCenterNameEnglish = costCenterNameEnglish;
	}

	public Set getPrPrForms() {
		return this.prPrForms;
	}

	public void setPrPrForms(Set prPrForms) {
		this.prPrForms = prPrForms;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public String getIo() {
		return io;
	}

	public void setIo(String io) {
		this.io = io;
	}

}