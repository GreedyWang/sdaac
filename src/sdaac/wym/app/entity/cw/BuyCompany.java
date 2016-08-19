package sdaac.wym.app.entity.cw;

public class BuyCompany extends CompanyInfo{
	private String secret; //密码区域

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	public BuyCompany()
	{
		
	}

	public BuyCompany(String secret,String name,String TaxpayersCode,String address,String account)
	{
		super(name,TaxpayersCode,address,account);
		this.secret = secret;	
	}
}
