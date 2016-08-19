package app.entity.epor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * PrBuyContext entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PrBuyContext implements java.io.Serializable {

	// Fields

	private Integer id;
	private PrPrForm prPrForm=new PrPrForm();
	//private Integer prFormID;
	private String description;
	private Float quantity;
	private String unit;
	private Date expectDeliveryDate;
	private String PODate;
	private String finishDate;
	private Float unitPrice;
	private Float unitPriceE;//估价
	private String buyerId;
	private Float amount;
	private float total;
	private String currency;
	private String glAccount;
	private String orderNo;
	private String remark;
	private String io;
	//2014/04/20 -ZJP
	private String band;
	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public String getIo() {
		return io;
	}

	public void setIo(String io) {
		this.io = io;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isUnFinished(){
		return  unitPrice!=null && unitPrice!=0 ;
	}
	
	public float getTotal(){
		if(unitPrice==null||quantity==null){
			return 0;
		}
		return unitPrice*quantity;
	}
	
	// Constructors

	/** default constructor */
	public PrBuyContext() {
	}

	/** minimal constructor */
	public PrBuyContext(Integer id, PrPrForm prPrForm, String description,
			Float quantity, String unit, String currency) {
		this.id = id;
		this.prPrForm = prPrForm;
		this.description = description;
		this.quantity = quantity;
		this.unit = unit;
		this.currency = currency;
	}

	/** full constructor */
	public PrBuyContext(Integer id, PrPrForm prPrForm, String description,
			Float quantity, String unit, Date expectDeliveryDate,
			Float unitPrice, Float amount, String currency, String glAccount,
			String orderNo,Float unitPriceE,String buyerId,String remark) {
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
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PrPrForm getPrPrForm() {
		return this.prPrForm;
	}

	public void setPrPrForm(PrPrForm prPrForm) {
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

	public String getPODate() {
		return PODate;
	}

	public void setPODate(String date) {
		PODate = date;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}




}