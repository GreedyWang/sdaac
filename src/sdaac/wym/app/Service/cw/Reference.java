package sdaac.wym.app.Service.cw;

public class Reference {
	private String taxId;
	private String referenceId;
	
	public Reference(String pTaxId,String pReferenceId){
		taxId = pTaxId;
		referenceId = pReferenceId;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
}
