package sdaac.wym.app.entity.cw;

import java.math.BigDecimal;

public class TaxItem {
	private String produceName;
	private String standardType;//规格型号
	private String unit;
	private String quantity;
	private String price;
	private String sumPrice;//金额
	private String fax;
	private String faxPrice;//税额
	//unknow 4001~~0~~31.78~~0~~0.000~~15.58000~~0
	private String a="4001";
	private String b="0";
	private String c="0";
	private String d="0.000";
	private String e="0";
	public String getE() {
		return e;
	}
	public void setE(String e) {
		this.e = e;
	}
	public String getA() {
		return a;
	}
	public TaxItem(String produceName, String standardType, String unit,
			String quantity, String price, String sumPrice, String fax,
			String faxPrice, String a, String b, String c, String d, String e) {
		super();
		this.produceName = produceName;
		this.standardType = standardType;
		this.unit = unit;
		this.quantity = quantity;
		this.price = price;
		this.sumPrice = sumPrice;
		this.fax = fax;
		this.faxPrice = faxPrice;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
	}
	
	public TaxItem(){
		
	}
	
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	public TaxItem(String produceName, String standardType, String unit,
			String quantity, String price, String sumPrice, String fax,
			String faxPrice) {
		super();
		this.produceName = produceName;
		this.standardType = standardType;
		this.unit = unit;
		this.quantity = quantity;
		this.price = price;
		this.sumPrice = sumPrice;
		this.fax = fax;
		this.faxPrice = faxPrice;
	}
	public String getProduceName() {
		return produceName;
	}
	public void setProduceName(String produceName) {
		this.produceName = produceName;
	}
	public String getStandardType() {
		return standardType;
	}
	public void setStandardType(String standardType) {
		this.standardType = standardType;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(String sumPrice) {
		this.sumPrice = sumPrice;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getFaxPrice() {
		return faxPrice;
	}
	public void setFaxPrice(String faxPrice) {
		this.faxPrice = faxPrice;
	}
	public TaxItem(String produceName, String unit) {
		super();
		this.produceName = produceName;
		this.unit = unit;
	}
	//累加另一个物品
	public void add(TaxItem taxItem){
		Float tQuantity=Float.parseFloat(quantity)+Float.parseFloat(taxItem.getQuantity());
		quantity=tQuantity.toString();
		BigDecimal   b1   = new BigDecimal(sumPrice);  
		BigDecimal   b2   = new BigDecimal(taxItem.getSumPrice());  
		Float tSumPrice=b1.add(b2).floatValue();
		sumPrice=tSumPrice.toString();
		Float tFaxPrice=Float.parseFloat(faxPrice)+Float.parseFloat(taxItem.getFaxPrice());
		faxPrice=tFaxPrice.toString();		
	}
	
}
