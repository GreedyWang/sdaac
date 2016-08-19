package app.entity.epor;

import java.util.Date;

/**
 * PrBuyContext entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasePrBuyContext implements java.io.Serializable {

	// Fields

	private Integer id;
	private String prPrForm;
	//private Integer prFormID;
	private String description;
	private Float quantity;
	private String unit;
	private Date expectDeliveryDate;
	private Float unitPrice;
	private Float unitPriceE;//估价
	private String buyerId;
	private Float amount;
	private float total;
	private String currency;
	private String glAccount;
	private String orderNo;
	
	public boolean isUnFinished(){
		return unitPrice!=0 && !currency.equals("");
	}
	
	public float getTotal(){
		if(unitPrice==null||quantity==null){
			return 0;
		}
		return unitPrice*quantity;
	}
	
	// Constructors

	/** default constructor */
	public BasePrBuyContext() {
	}

	/** minimal constructor */
	public BasePrBuyContext(Integer id, String prPrForm, String description,
			Float quantity, String unit, String currency) {
		this.id = id;
		this.prPrForm = prPrForm;
		this.description = description;
		this.quantity = quantity;
		this.unit = unit;
		this.currency = currency;
	}

	/** full constructor */
	public BasePrBuyContext(Integer id, String prPrForm, String description,
			Float quantity, String unit, Date expectDeliveryDate,
			Float unitPrice, Float amount, String currency, String glAccount,
			String orderNo,Float unitPriceE,String buyerId) {
		this.id = id;
		this.prPrForm = prPrForm;
		this.description = description;
		this.quantity = quantity;
		this.unit = unit;
		this.expectDeliveryDate = expectDeliveryDate;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.currency = currency;
		this.glAccount = glAccount;
		this.orderNo = orderNo;
		this.unitPriceE = unitPriceE;
		this.buyerId = buyerId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrPrForm() {
		return prPrForm;
	}

	public void setPrPrForm(String prPrForm) {
		this.prPrForm = prPrForm;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}


	public Date getExpectDeliveryDate() {
		return expectDeliveryDate;
	}

	public void setExpectDeliveryDate(Date expectDeliveryDate) {
		this.expectDeliveryDate = expectDeliveryDate;
	}

	public Float getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Float getAmount() {
		return this.amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getGlAccount() {
		return this.glAccount;
	}

	public void setGlAccount(String glAccount) {
		this.glAccount = glAccount;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Float getUnitPriceE() {
		return unitPriceE;
	}

	public void setUnitPriceE(Float unitPriceE) {
		this.unitPriceE = unitPriceE;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}




}