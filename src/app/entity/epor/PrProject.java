package app.entity.epor;
/**
 * PR 项目实体类
 * @author SA1KV5
 * @version 1
 */
public class PrProject {
	private String projectId;
	private String managerUid;
	private Integer id;
	private String sapNo;
	private String context;
	private String number;
	private String arno;
	private String projectstate;
	
	
	public String getProjectstate() {
		return projectstate;
	}
	public void setProjectstate(String projectstate) {
		this.projectstate = projectstate;
	}
	public String getArno() {
		return arno;
	}
	public void setArno(String arno) {
		this.arno = arno;
	}
	public String getSapNo() {
		return sapNo;
	}
	public void setSapNo(String sapNo) {
		this.sapNo = sapNo;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getManagerUid() {
		return managerUid;
	}
	public void setManagerUid(String managerUid) {
		this.managerUid = managerUid;
	}
}
