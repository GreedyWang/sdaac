package app.entity.epor;

/**
 * 
 * @author sa1kv5
 *
 */
public class PrForm {
	private String state;
	private String applicantDate;
	private String applicationName;
	private String applicationDepartName;
	private String total;
	private String id;
	private int state2;
	private int version;
	private String isPlane;
	private String isCapital;
	private Integer isPlane2;
	private Integer isCapital2;
	private String costCentre;
	private String projectNo;
	private String type;
	private String buyerName;
	private Integer info=0;
	private int ssid;
	public int getSsid() {
		return ssid;
	}

	public void setSsid(int ssid) {
		this.ssid = ssid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getApplicantDate() {
		return applicantDate;
	}

	public void setApplicantDate(String applicantDate) {
		this.applicantDate = applicantDate;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getApplicationDepartName() {
		return applicationDepartName;
	}

	public void setApplicationDepartName(String applicationDepartName) {
		this.applicationDepartName = applicationDepartName;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getId() {
		return id;
	}

	public int getState2() {
		return state2;
	}

	public void setState2(int state2) {
		this.state2 = state2;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 拷贝属性
	 * @param item
	 */
	public  void copy(PrPrForm item){
			state2 = item.getState();
			version = item.getVersion();
			state=item.getStateName();
			applicantDate = item.getApplicantDate().toString();
			applicationName = item.getTempolyeeByApplicantId().getName();
			applicationDepartName = item.getTempolyeeByApplicantId().getTdepartment().getName();
			total = item.getTotal().toString();
			id = item.getId();
			ssid = item.getSapNO();
			type = item.getType();
			isPlane = item.getIsPlanName();
			isPlane2 = item.getIsPlan();
			isCapital = item.getIsCapitalName();
			isCapital2 = item.getIsCapital();
			//costCentre = item.getPrCostCenter().getCostCenterNameEnglish();
			if(item.getPrProject()!=null){
				projectNo = item.getPrProject().getProjectId();
			}		
			buyerName = item.getTempolyeeByBuyerId();
			info = item.getInfo();
	}
			
	public String getIsPlane() {
		return isPlane;
	}

	public void setIsPlane(String isPlane) {
		this.isPlane = isPlane;
	}

	public String getIsCapital() {
		return isCapital;
	}

	public void setIsCapital(String isCapital) {
		this.isCapital = isCapital;
	}

	public String getCostCentre() {
		return costCentre;
	}

	public void setCostCentre(String costCentre) {
		this.costCentre = costCentre;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public Integer getIsPlane2() {
		return isPlane2;
	}

	public void setIsPlane2(Integer isPlane2) {
		this.isPlane2 = isPlane2;
	}

	public Integer getIsCapital2() {
		return isCapital2;
	}

	public void setIsCapital2(Integer isCapital2) {
		this.isCapital2 = isCapital2;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public Integer getInfo() {
		return info;
	}

	public void setInfo(Integer info) {
		this.info = info;
	}

}
