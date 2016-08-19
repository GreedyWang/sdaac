package app.entity.vave;

import app.entity.Tempolyee;

/**
 * 个人统计类，用于简化数据结构
 * @author SA1KV5
 *
 */
public class PersonalCount {
	private Tempolyee emp=new Tempolyee();
	private Integer ApprovalCount;
	private Double SavingCost;
	
	public PersonalCount()
	{
		
	}
	
	public PersonalCount(Tempolyee emp,Integer ApprovalCount,Double SavingCost)
	{
		this.emp=emp;
		this.ApprovalCount=ApprovalCount;
		this.SavingCost=SavingCost;
	}
	
	public Tempolyee getEmp() {
		return emp;
	}
	public void setEmp(Tempolyee emp) {
		this.emp = emp;
	}
	public Integer getApprovalCount() {
		return ApprovalCount;
	}
	public void setApprovalCount(Integer approvalCount) {
		ApprovalCount = approvalCount;
	}
	public Double getSavingCost() {
		return SavingCost;
	}
	public void setSavingCost(Double savingCost) {
		SavingCost = savingCost;
	}
}
