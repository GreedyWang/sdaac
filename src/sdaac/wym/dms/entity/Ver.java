package sdaac.wym.dms.entity;

public class Ver {
	private Integer id;
	private String	version;
	private String	area;
	private String	workgourp;
	private String	leaderuid;
	private Integer	isFinish;//1 is finished ,0 is not	
	
	public Ver(){}
	
	public Ver(Integer id, String version, String area, String workgourp,
			String leaderuid, Integer isFinish) {
		super();
		this.id = id;
		this.version = version;
		this.area = area;
		this.workgourp = workgourp;
		this.leaderuid = leaderuid;
		this.isFinish = isFinish;
	}

	public Ver(String version, String area, String workgourp,
			String leaderuid) {
		super();
		this.version = version;
		this.area = area;
		this.workgourp = workgourp;
		this.leaderuid = leaderuid;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getWorkgourp() {
		return workgourp;
	}
	public void setWorkgourp(String workgourp) {
		this.workgourp = workgourp;
	}
	public String getLeaderuid() {
		return leaderuid;
	}
	public void setLeaderuid(String leaderuid) {
		this.leaderuid = leaderuid;
	}
	public Integer getIsFinish() {
		return isFinish;
	}
	public void setIsFinish(Integer isFinish) {
		this.isFinish = isFinish;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	
	
}
