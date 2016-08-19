package app.entity.epor.forms;

import java.util.Date;

import app.entity.Tempolyee;

public class SingleForm {
	private String prno;
	private Date requestdate;
	private String es_amount;
	private String es_cost;
	private String des;
	
	private String sname;
	private String semail;
	private String sphone;
	private String scontact;
	
	private String reason;
	private String reason2;
	
	private String otherReason;
	private String sno;
	private int id;
	public static final String SINGLE = "S";
	public static final String URGENT = "U";
	/**
	 * 判断类型
	 * @return
	 */
	public String getFormType(){
		if(sname!=null && !"".equals(sname)){
			return SINGLE;
		}else{
			return URGENT;
		}
	}
	
	/**
	 * 计算SNO
	 * @param n
	 * @return
	 */
	public String getSNO(int n){
		int r = 150000+n;
		return getFormType()+r;
	}
	
	private Tempolyee applicantId=new Tempolyee();
	
	/**
	 * 紧急采购属性
	 */
	private String purchaseGoodsName;
	private String arno;
	private String supplier;
	private String totalAmount;
	private String contact;
	private String buyer;
	//private String 
	
	public String getPrno() {
		return prno;
	}

	public void setPrno(String prno) {
		this.prno = prno;
	}



	public String getEs_amount() {
		return es_amount;
	}

	public void setEs_amount(String es_amount) {
		this.es_amount = es_amount;
	}

	public String getEs_cost() {
		return es_cost;
	}

	public void setEs_cost(String es_cost) {
		this.es_cost = es_cost;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSemail() {
		return semail;
	}

	public void setSemail(String semail) {
		this.semail = semail;
	}

	public String getSphone() {
		return sphone;
	}

	public void setSphone(String sphone) {
		this.sphone = sphone;
	}

	public String getScontact() {
		return scontact;
	}

	public void setScontact(String scontact) {
		this.scontact = scontact;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason2() {
		return reason2;
	}

	public void setReason2(String reason2) {
		this.reason2 = reason2;
	}

	public String getOtherReason() {
		return otherReason;
	}

	public void setOtherReason(String otherReason) {
		this.otherReason = otherReason;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Tempolyee getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(Tempolyee applicantId) {
		this.applicantId = applicantId;
	}

	public String getPurchaseGoodsName() {
		return purchaseGoodsName;
	}

	public void setPurchaseGoodsName(String purchaseGoodsName) {
		this.purchaseGoodsName = purchaseGoodsName;
	}

	public String getArno() {
		return arno;
	}

	public void setArno(String arno) {
		this.arno = arno;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public Date getRequestdate() {
		return requestdate;
	}

	public void setRequestdate(Date requestdate) {
		this.requestdate = requestdate;
	}
}
