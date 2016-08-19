package app.entity.vave;
/**
 * 简化部门提案统计
 * @author SA1KV5
 *
 */
public class Count extends FatherCount{
	private String departmentName;
	private Integer proposalNum;
	private Integer finishNum;
	private Integer doNum;
	private Float money;
	private String realDepartmentName;
	private Integer NOid;
	public Integer getNOid() {
		return NOid;
	}
	public void setNOid(Integer oid) {
		NOid = oid;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Integer getProposalNum() {
		return proposalNum;
	}
	public void setProposalNum(Integer proposalNum) {
		this.proposalNum = proposalNum;
	}
	public Integer getFinishNum() {
		return finishNum;
	}
	public void setFinishNum(Integer finishNum) {
		this.finishNum = finishNum;
	}
	public Integer getDoNum() {
		return doNum;
	}
	public void setDoNum(Integer doNum) {
		this.doNum = doNum;
	}
	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	public String getRealDepartmentName() {
		return realDepartmentName;
	}
	public void setRealDepartmentName(String realDepartmentName) {
		this.realDepartmentName = realDepartmentName;
	}
}
