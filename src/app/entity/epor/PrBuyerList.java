package app.entity.epor;

/**
 * PrBuyer entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PrBuyerList implements java.io.Serializable {

	// Fields
	private Integer id;
	private String kind;
	private String categroy;
	private String buyerName;
	private String buyerNetid;
	// Constructors
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getCategroy() {
		return categroy;
	}
	public void setCategroy(String categroy) {
		this.categroy = categroy;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getBuyerNetid() {
		return buyerNetid;
	}
	public void setBuyerNetid(String buyerNetid) {
		this.buyerNetid = buyerNetid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


}