package app.entity.vave;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import net.sf.json.JSONString;

import common.util.MyUtil;

import app.entity.Tempolyee;
import app.entity.Tproposal;
/**
 * VaveTeamWork entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VaveTeamWork implements java.io.Serializable,JSONString {

	// Fields

	private Integer workTeamId;
	private Tproposal tproposal=new Tproposal();
	private String teamName;
	private Date projectBeginTime=new Date();//项目开始日期
	private Date planfinishtime;//预计完成日期
	private Date actualEndingTime; //实际完成日期
	private Float estimatedCostSavings;//预计节约金额
	private String planfinish;
	private Float expectedCostSavings;
	private Float actualCostSavings; //实际节约金额

	private Tempolyee projectManager=new Tempolyee();
	private Set vaveTeamWorkProjects = new HashSet(0);
	private String myPlanfinishtime;

	private String meneberName;//组员姓名‘,’
	private String meneberUid;//组员工号‘,’
	private String[] meneberUids;//组员工号s
	private String[] meneberNames;//组员姓名s
	private String projectProcess;//项目进度
	private String projectLogs;//项目日志
	private String vaveDiscuss;//vave会议讨论
	
	public String a;//批准开始日期
	public String b;//批准结束日期
	/**
	 * 是否有权利看项目
	 * @param 工号
	 * @return true/false
	 */
	public boolean getAllowUid(String uid)
	{
		boolean flag=false;
		//是否为项目经理
		if(projectManager.getUid().equals(uid))
		{
			flag=true;
		}
		//是否为项目成员
		if(this.getMeneberUids()!=null)
		{
			for(int i=0;i<this.getMeneberUids().length;i++)
			{
				if(this.getMeneberUids()[i].equals(uid))
				{
					flag=true;
				}
			}
		}
		return flag;
	}
	
	
	
	// Constructors



	public String[] getMeneberNames() {
		if(meneberName!=null)
		{
			return meneberName.split(",");
		}
		return null;
	}

	

	public String getProjectProcess() {
		return projectProcess;
	}

	public void setProjectProcess(String projectProcess) {
		this.projectProcess = projectProcess;
	}

	public String getProjectLogs() {
		return projectLogs;
	}

	public void setProjectLogs(String projectLogs) {
		this.projectLogs = projectLogs;
	}

	public String getVaveDiscuss() {
		return vaveDiscuss;
	}

	public void setVaveDiscuss(String vaveDiscuss) {
		this.vaveDiscuss = vaveDiscuss;
	}

	public String getMyPlanfinishtime() {
		return myPlanfinishtime;
	}

	public void setMyPlanfinishtime(String myPlanfinishtime) {
		this.planfinishtime = MyUtil.formatDate(myPlanfinishtime);
	}
	
	public void setMyPlanfinishtime(Date time) {
		this.planfinishtime =time;
	}

	/** default constructor */
	public VaveTeamWork() {
	}

	/** minimal constructor */
	public VaveTeamWork(Integer workTeamId) {
		this.workTeamId = workTeamId;
	}

	/** full constructor */
	public VaveTeamWork(Integer workTeamId, Tproposal tproposal,
			String teamName, Date planfinishtime, Float estimatedCostSavings,
			Float expectedCostSavings, Float actualCostSavings,
		    Set vaveTeamWorkProjects) {
		this.workTeamId = workTeamId;
		this.tproposal = tproposal;
		this.teamName = teamName;
		this.planfinishtime = planfinishtime;
		this.estimatedCostSavings = estimatedCostSavings;
		this.expectedCostSavings = expectedCostSavings;
		this.actualCostSavings = actualCostSavings;
	
		this.vaveTeamWorkProjects = vaveTeamWorkProjects;
	}

	// Property accessors



	public Tproposal getTproposal() {
		return this.tproposal;
	}

	public void setTproposal(Tproposal tproposal) {
		this.tproposal = tproposal;
	}

	public String getTeamName() {
		return this.teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Date getPlanfinishtime() {
		return this.planfinishtime;
	}

	public void setPlanfinishtime(Date planfinishtime) {
		this.planfinishtime = planfinishtime;
	}

	public Float getEstimatedCostSavings() {
		return this.estimatedCostSavings;
	}

	public void setEstimatedCostSavings(Float estimatedCostSavings) {
		this.estimatedCostSavings = estimatedCostSavings;
	}

	public Float getExpectedCostSavings() {
		return this.expectedCostSavings;
	}

	public void setExpectedCostSavings(Float expectedCostSavings) {
		this.expectedCostSavings = expectedCostSavings;
	}

	public Float getActualCostSavings() {
		return this.actualCostSavings;
	}

	public void setActualCostSavings(Float actualCostSavings) {
		this.actualCostSavings = actualCostSavings;
	}



	public Set getVaveTeamWorkProjects() {
		return this.vaveTeamWorkProjects;
	}

	public void setVaveTeamWorkProjects(Set vaveTeamWorkProjects) {
		this.vaveTeamWorkProjects = vaveTeamWorkProjects;
	}

	public Integer getWorkTeamId() {
		return workTeamId;
	}

	public void setWorkTeamId(Integer workTeamId) {
		this.workTeamId = workTeamId;
	}

	public Tempolyee getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(Tempolyee projectManager) {
		this.projectManager = projectManager;
	}
	
	public String toJSONString() {
		// TODO Auto-generated method stub
		return "{planfinishtime:'"+planfinishtime.toString()+"',teamName:'"+teamName+"',estimatedCostSavings:'"+estimatedCostSavings
				+"',proposalId:'"+tproposal.getId()+"',name:'"+projectManager.getName()+"',state:'"+tproposal.getState()+"',departmentName:'"+projectManager.getTdepartment().getName()+"'}";
	}



	public String getMeneberName() {
		return meneberName;
	}



	public void setMeneberName(String meneberName) {
		this.meneberName = meneberName;
	}



	public String getMeneberUid() {
		return meneberUid;
	}



	public void setMeneberUid(String meneberUid) {
		this.meneberUid = meneberUid;
	}



	public String[] getMeneberUids() {
		if(meneberUid!=null)
		{
			return meneberUid.split(",");
		}
		return null;
	}



	public Date getProjectBeginTime() {
		return projectBeginTime;
	}



	public void setProjectBeginTime(Date projectBeginTime) {
		this.projectBeginTime = projectBeginTime;
	}



	public Date getActualEndingTime() {
		return actualEndingTime;
	}



	public void setActualEndingTime(Date actualEndingTime) {
		this.actualEndingTime = actualEndingTime;
	}

	public String getPlanfinish() {
		return planfinish;
	}



	public void setPlanfinish(String planfinish) {
		this.planfinish = planfinish;
	}





}