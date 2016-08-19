package sdaac.wym.app.Service.SAP;

import java.math.BigDecimal;
import java.util.Date;

/**
 * buyer price
 * @author SA1KV5
 *
 */
public class Price {
	
	private Float price;
	
	private String curr;
	private Date docDate;
	private Date entrydate;
	private String i;
	private Integer id;
	private String item;
	private String material;
	private String matlGroup;
	private Float netOrderValue;
	private String opun;
	private Float orderQuantity;
	private String oun;
	private String pgr;
	private String plnt;
	private String porg;
	private String purchdoc;
	private String shortText;
	private String sloc;
	private String splt;
	private String type;

	private String vendor;
	
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getCurr() {
		return curr;
	}

	public void setCurr(String curr) {
		this.curr = curr;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public String getI() {
		return i;
	}

	public void setI(String i) {
		this.i = i;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getMatlGroup() {
		return matlGroup;
	}

	public void setMatlGroup(String matlGroup) {
		this.matlGroup = matlGroup;
	}

	public Float getNetOrderValue() {
		return netOrderValue;
	}

	public void setNetOrderValue(Float netOrderValue) {
		this.netOrderValue = netOrderValue;
	}

	public String getOpun() {
		return opun;
	}

	public void setOpun(String opun) {
		this.opun = opun;
	}

	public Float getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Float orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public String getOun() {
		return oun;
	}

	public void setOun(String oun) {
		this.oun = oun;
	}

	public String getPgr() {
		return pgr;
	}

	public void setPgr(String pgr) {
		this.pgr = pgr;
	}

	public String getPlnt() {
		return plnt;
	}

	public void setPlnt(String plnt) {
		this.plnt = plnt;
	}

	public String getPorg() {
		return porg;
	}

	public void setPorg(String porg) {
		this.porg = porg;
	}

	public String getPurchdoc() {
		return purchdoc;
	}

	public void setPurchdoc(String purchdoc) {
		this.purchdoc = purchdoc;
	}

	public String getShortText() {
		return shortText;
	}

	public void setShortText(String shortText) {
		this.shortText = shortText;
	}

	public String getSloc() {
		return sloc;
	}

	public void setSloc(String sloc) {
		this.sloc = sloc;
	}

	public String getSplt() {
		return splt;
	}

	public void setSplt(String splt) {
		this.splt = splt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
}
