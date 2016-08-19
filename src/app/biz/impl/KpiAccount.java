package app.biz.impl;
/**
 * KPI统计查询实体类
 * @author SA1KV5
 *
 */
public class KpiAccount {
	private String uid;
	private String name;
	private String departmentName;
	private float score;
	
	public KpiAccount(){
		
	}
	
	public KpiAccount(String Puid,String Pname,String PdepartmentName,float Pscore){
		uid=Puid;
		name=Pname;
		departmentName=PdepartmentName;
		score=Pscore;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
}
