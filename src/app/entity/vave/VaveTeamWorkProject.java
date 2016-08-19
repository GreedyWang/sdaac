package app.entity.vave;

import java.util.Date;

import app.entity.Tempolyee;
import common.util.MyUtil;

/**
 * VaveTeamWorkProject entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VaveTeamWorkProject implements java.io.Serializable {

	// Fields

	private Integer id;
	private VaveTeamWork vaveTeamWork=new VaveTeamWork();
	private String jobContent;
	private Tempolyee responsiblePerson=new Tempolyee();
	private Date planTiem;
	private Date finishTiem;
	private String myTime;
	private String myFinishTime;
	private final String[] state_context={"任务终止","拒绝任务","任务发布","任务开始","任务完成"}; 
	private String pstate;
	private String type;
	private Integer grade;
	private String remark;
	private Integer state;
	private String jobLogs;
	public static Integer JOB_TERMIN=0;
	public static Integer JOB_REFUSE=1;
	public static Integer JOB_PUBLISH=2;
	public static Integer JOB_START=3;
	public static Integer JOB_FINISH=4;
	// Constructors

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getJobLogs() {
		return jobLogs;
	}

	public void setJobLogs(String jobLogs) {
		this.jobLogs = jobLogs;
	}

	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}


	public void setMyTime(String myTime) {
		this.planTiem = MyUtil.formatDate(myTime);
	}

	/** default constructor */
	public VaveTeamWorkProject() {
	}

	/** minimal constructor */
	public VaveTeamWorkProject(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public VaveTeamWorkProject(Integer id, VaveTeamWork vaveTeamWork,
			String jobContent,  Date planTiem,
			Date finishTiem) {
		this.id = id;
		this.vaveTeamWork = vaveTeamWork;
		this.jobContent = jobContent;

		this.planTiem = planTiem;
		this.finishTiem = finishTiem;
	}

	// Property accessors



	public VaveTeamWork getVaveTeamWork() {
		return this.vaveTeamWork;
	}

	public void setVaveTeamWork(VaveTeamWork vaveTeamWork) {
		this.vaveTeamWork = vaveTeamWork;
	}

	public String getJobContent() {
		return this.jobContent;
	}

	public void setJobContent(String jobContent) {
		this.jobContent = jobContent;
	}



	public Tempolyee getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setResponsiblePerson(Tempolyee responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public Date getPlanTiem() {
		return this.planTiem;
	}

	public void setPlanTiem(Date planTiem) {
		this.planTiem = planTiem;
	}

	public Date getFinishTiem() {
		return this.finishTiem;
	}

	public void setFinishTiem(Date finishTiem) {
		this.finishTiem = finishTiem;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getMyTime() {
		return myTime;
	}

	public String getMyFinishTime() {
		return myFinishTime;
	}

	public void setMyFinishTime(String myFinishTime) {
		this.finishTiem = MyUtil.formatDate(myFinishTime);
	}

	public String getPstate() {
		return state_context[state];
		//return pstate;
	}

	public void setPstate(String pstate) {
		this.pstate = pstate;
	}



}