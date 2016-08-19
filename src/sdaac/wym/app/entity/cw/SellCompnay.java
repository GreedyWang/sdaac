package sdaac.wym.app.entity.cw;

public class SellCompnay extends CompanyInfo {
	private String remarks;//备注，PO&Delivery NO:

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public SellCompnay() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SellCompnay(String name, String taxpayersCode, String address,
			String account,String remarks) {
		super(name, taxpayersCode, address, account);
		this.remarks=remarks;
		// TODO Auto-generated constructor stub
	}
}	
