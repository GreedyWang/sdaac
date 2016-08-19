package sdaac.wym.dms.entity;

import app.entity.Tempolyee;

public class ScoreDetails {
	private String uid;
	private Tempolyee emp = new Tempolyee();
	private float wight;
	private float actual;
	private float score;
	private float xishu;
	private float target;
	private Indicate indicate = new Indicate();
	private String version;
	
	private String leaderUid;
	private String workgroup;
	private String area;
	
	private int id ;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}

	public Float getWight() {
		return wight;
	}
	public void setWight(Float wight) {
		this.wight = wight;
	}
	public float getActual() {
		return actual;
	}
	public void setActual(float actual) {
		this.actual = actual;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public float getXishu() {
		return xishu;
	}
	public void setXishu(float xishu) {
		this.xishu = xishu;
	}
	public float getTarget() {
		return target;
	}
	public void setTarget(float target) {
		this.target = target;
	}
	public Indicate getIndicate() {
		return indicate;
	}
	public void setIndicate(Indicate indicate) {
		this.indicate = indicate;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Tempolyee getEmp() {
		return emp;
	}
	public void setEmp(Tempolyee emp) {
		this.emp = emp;
	}
	public String getLeaderUid() {
		return leaderUid;
	}
	public void setLeaderUid(String leaderUid) {
		this.leaderUid = leaderUid;
	}
	public String getWorkgroup() {
		return workgroup;
	}
	public void setWorkgroup(String workgroup) {
		this.workgroup = workgroup;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setWight(float wight) {
		this.wight = wight;
	}
	
	
}
