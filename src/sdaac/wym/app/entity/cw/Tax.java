package sdaac.wym.app.entity.cw;
import java.util.ArrayList;
import java.util.List;
public class Tax implements java.io.Serializable{
	
	@Override
	public String toString() {
		//0076825534~~2~~上海通用汽车有限公司~~310046607371748~~上海浦东申江路1500号 (021)-28902890~~工行浦东分大道分 1001181309016288873
//		~~PO: JQ02763\n\nDelivery No: 0055960588~~~~~~~~20090801~~交通银行上海浦东分行 310066580018002229956
		StringBuilder sb=new StringBuilder(no+"~~"+taxItems.size()+"~~"+buyer.getName()+"~~"+buyer.getTaxpayersCode()+"~~"
				+buyer.getAddress()+"~~"+buyer.getAccount()+"~~"+seller.getRemarks()+"~~~~~~~~"+createtime+"~~"+seller.getAccount()+"\r\n");
//		软管总成~~PC~~5484337~~24.000000~~747.84~~0.17~~4001~~0~~127.13~~0~~0.000~~31.16000~~0
//		软管总成~~PC~~5484338~~30.000000~~1965.90~~0.17~~4001~~0~~334.21~~0~~0.000~~65.53000~~0
		for(TaxItem item:taxItems)
		{
			sb.append(item.getProduceName()+"~~"+item.getUnit()+"~~"+item.getStandardType()+"~~"+item.getQuantity()+"~~"+
					item.getSumPrice()+"~~"+item.getFax()+"~~"+item.getA()+"~~"+item.getB()+"~~"+
					item.getFaxPrice()+"~~"+item.getC()+"~~"+item.getD()+"~~"+item.getPrice()+"~~"+item.getE()+"\r\n");
		}
		return sb.toString();
		}
	
	private String no;
	private String invoiceTypeCode;//发票类型代码
	private BuyCompany buyer=new BuyCompany();
	private SellCompnay seller=new SellCompnay();
	private List<TaxItem> taxItems=new ArrayList<TaxItem>();
	private String createtime;//开票日期
	
	public float getTotalMoney(){
		float result=0.00f;
		for(TaxItem item:taxItems){
			result +=Float.parseFloat(item.getPrice());
		}
		return result;
	}
	
	/**
	 * 得到PO号
	 * @return
	 */
	public String getPo(){
		if(seller.getRemarks()!=null&&!seller.getRemarks().equals("")){
			return seller.getRemarks().substring(4, 14);
		}else{
			return "";
		}
	}
	
	public Tax(String no, String createtime,BuyCompany buyer, SellCompnay seller
			) {
		super();
		this.no = no;
		this.buyer = buyer;
		this.seller = seller;
		this.createtime = createtime;
	}

	public Tax()
	{
		
	}
	
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getInvoiceTypeCode() {
		return invoiceTypeCode;
	}
	public void setInvoiceTypeCode(String invoiceTypeCode) {
		this.invoiceTypeCode = invoiceTypeCode;
	}
	public BuyCompany getBuyer() {
		return buyer;
	}
	public void setBuyer(BuyCompany buyer) {
		this.buyer = buyer;
	}
	public SellCompnay getSeller() {
		return seller;
	}
	public void setSeller(SellCompnay seller) {
		this.seller = seller;
	}
	public List<TaxItem> getTaxItems() {
		return taxItems;
	}
	public void setTaxItems(List<TaxItem> taxItems) {
		this.taxItems = taxItems;
	}
}
