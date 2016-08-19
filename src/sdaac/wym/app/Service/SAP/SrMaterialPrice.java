package sdaac.wym.app.Service.SAP;

import java.util.Date;

/**
 * SrMaterialPrice entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SrMaterialPrice implements java.io.Serializable {

	// Fields

	private Integer id;
	private String material;
	private Long unitPrice;
	private String oun;
	private String curr;
	private String description;
	private Date entrydate;

	// Constructors

	/** default constructor */
	public SrMaterialPrice() {
	}

	/** full constructor */
	public SrMaterialPrice(String material, Long unitPrice, String oun,
			String curr, String description, Date entrydate) {
		this.material = material;
		this.unitPrice = unitPrice;
		this.oun = oun;
		this.curr = curr;
		this.description = description;
		this.entrydate = entrydate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMaterial() {
		return this.material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Long getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Long unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getOun() {
		return this.oun;
	}

	public void setOun(String oun) {
		this.oun = oun;
	}

	public String getCurr() {
		return this.curr;
	}

	public void setCurr(String curr) {
		this.curr = curr;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEntrydate() {
		return this.entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

}