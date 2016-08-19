package sdaac.wym.app.entity.cw;

public class CompanyInfo {
	private String name;//名称
	private String taxpayersCode;//纳税人识别号
	private String address;//地址电话
	private String account;//开户行及帐号
	public CompanyInfo() {
		super();
	}
	public CompanyInfo(String name, String taxpayersCode, String address,
			String account) {
		super();
		this.name = name;
		this.taxpayersCode = taxpayersCode;
		this.address = address;
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTaxpayersCode() {
		return taxpayersCode;
	}
	public void setTaxpayersCode(String taxpayersCode) {
		this.taxpayersCode = taxpayersCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
}
