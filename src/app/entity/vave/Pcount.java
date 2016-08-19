package app.entity.vave;

import java.math.BigDecimal;

/**
 * 简化部门提案统计
 * @author SA1KV5
 *
 */
public class Pcount extends FatherCount {
	private String departmentName;
	private Integer proposalNum=0;//提案数
	private Integer doNum=0;//批准数
	private Integer finishNum=0;//完成数

	private Float money;
	private String Uid;
	private String uname;
	private Integer NOid;
	private Integer hlh=0;//合理化建议
	private Integer WAITCHANGE=0;//WAITCHANGE
	private Integer DO_PROJECT=0;//DO_PROJECT
	private Integer BEGIN_PROJECT=0;//BEGIN_PROJECT
	private Integer allProjects=0;//所有项目
	private BigDecimal total_saving=new BigDecimal(0);//总节约金额
	
	private float curesyearSaving;  //当年预计节约金额
	private float esyearSaving;	//年预计节约金额
	private float curyearSaving; 	//当年节约金额
	private float yearSaving;		//年节约金额
	
	
	private int level=0;
	private final static int  LEVELS[]={10,30,50,80,100};
	private final static int UNIT=10000;
	
	
	
	
	public BigDecimal getTotal_saving() {
		return total_saving;
	}

	public void setTotal_saving(BigDecimal total_saving) {
		this.total_saving = total_saving;
	}

	/**
	 * 得到星级
	 * @return
	 */
	public int getLevel() {
		double temp=0;
		if(total_saving!=null){
			temp=total_saving.doubleValue()/UNIT;
		}
		if(temp<LEVELS[0]){
			return 0;
		}else if(temp<LEVELS[1]){
			return 1;
		}else if(temp<LEVELS[2]){
			return 2;
		}else if(temp<LEVELS[3]){
			return 3;
		}else if(temp<LEVELS[4]){
			return 4;
		}else{
			return 5;
		}		
	}
	
	public Integer getAllProjects() {
		return allProjects;
	}
	public void setAllProjects(Integer allProjects) {
		this.allProjects = allProjects;
	}
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
	public String getUid() {
		return Uid;
	}
	public void setUid(String uid) {
		Uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public Integer getHlh() {
		return hlh;
	}
	public void setHlh(Integer hlh) {
		this.hlh = hlh;
	}
	public Integer getWAITCHANGE() {
		return WAITCHANGE;
	}
	public void setWAITCHANGE(Integer waitchange) {
		WAITCHANGE = waitchange;
	}
	public Integer getDO_PROJECT() {
		return DO_PROJECT;
	}
	public void setDO_PROJECT(Integer do_project) {
		DO_PROJECT = do_project;
	}
	public Integer getBEGIN_PROJECT() {
		return BEGIN_PROJECT;
	}
	public void setBEGIN_PROJECT(Integer begin_project) {
		BEGIN_PROJECT = begin_project;
	}

	public float getCuresyearSaving() {
		return curesyearSaving;
	}

	public void setCuresyearSaving(float curesyearSaving) {
		this.curesyearSaving = curesyearSaving;
	}

	public float getEsyearSaving() {
		return esyearSaving;
	}

	public void setEsyearSaving(float esyearSaving) {
		this.esyearSaving = esyearSaving;
	}

	public float getCuryearSaving() {
		return curyearSaving;
	}

	public void setCuryearSaving(float curyearSaving) {
		this.curyearSaving = curyearSaving;
	}

	public float getYearSaving() {
		return yearSaving;
	}

	public void setYearSaving(float yearSaving) {
		this.yearSaving = yearSaving;
	}




}
