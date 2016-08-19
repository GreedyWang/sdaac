package app.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TemployeeProduct generated by MyEclipse Persistence Tools
 */

public class TemployeeProduct implements java.io.Serializable {

	// Fields

	private Integer id;

	private Tpost tpost=new Tpost();

	private Tempolyee tempolyee=new Tempolyee();
	
	private String teamLeaderId;

	private float worktime;

	private float output;
	
	private Date datatime;//when the man do this job
	
	private String registerID;
	
	private String types;
	private Integer type;
	private Date registerTime;//记录日期
	private String figureId;//图号
	private String beginRegisterTime;
	private String endRegisterTime;
	private String beginDatatime;
	private String endDatatime;
	private String typeName;
	private String groupId;
	private static Integer[] WORKSHOP={1,2,3,6,7,4,5};
	private static String[] WORKSHOP2={"'104B'","'104A'","'100'","'101'","'102'","'103'","'chukou'"};
	// Constructors



	public String getTypeName() {
		String rs="";
		if(type==1){
			rs= "岗位工资";
		}else if(type==2)
		{
			rs="车间奖金-104A";
		}else if(type==3){
			rs="车间奖金-100";
		}else if(type==4){
			rs="车间奖金-103";
		}else if(type==5){
			rs="车间奖金-chukou";
		}else if(type==6){
			rs="车间奖金-101";
		}else if(type==7){
			rs="车间奖金-102";
		}
		return rs;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getRegisterID() {
		return registerID;
	}

	public void setRegisterID(String registerID) {
		this.registerID = registerID;
	}

	public Date getDatatime() {
		return datatime;
	}

	public void setDatatime(Date datatime) {
		this.datatime = datatime;
	}
	
	/** default constructor */
	public TemployeeProduct() {
	}

	/** minimal constructor */
	public TemployeeProduct(Integer id, Tpost tpost, Tempolyee tempolyee) {
		this.id = id;
		this.tpost = tpost;
		this.tempolyee = tempolyee;
	}

	/** full constructor */
	public TemployeeProduct(Integer id, Tpost tpost, Tempolyee tempolyee,
			Float worktime, Float output, Date datatime) {
		this.id = id;
		this.tpost = tpost;
		this.tempolyee = tempolyee;
		this.worktime = worktime;
		this.output = output;
	
			this.datatime=datatime;
	
	}

	// Property accessors
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Tpost getTpost() {
		return this.tpost;
	}

	public void setTpost(Tpost tpost) {
		this.tpost = tpost;
	}

	public Tempolyee getTempolyee() {
		return this.tempolyee;
	}

	public void setTempolyee(Tempolyee tempolyee) {
		this.tempolyee = tempolyee;
	}

	public Float getWorktime() {
		return this.worktime;
	}

	public void setWorktime(Float worktime) {
		this.worktime = worktime;
	}

	public Float getOutput() {
		return this.output;
	}

	public void setOutput(Float output) {
		this.output = output;
	}

	public String getTeamLeaderId() {
		return teamLeaderId;
	}

	public void setTeamLeaderId(String teamLeaderId) {
		this.teamLeaderId = teamLeaderId;
	}

	public void setOutput(float output) {
		this.output = output;
	}

	public void setWorktime(float worktime) {
		this.worktime = worktime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getBeginRegisterTime() {
		return beginRegisterTime;
	}

	public void setBeginRegisterTime(String beginRegisterTime) {
		this.beginRegisterTime = beginRegisterTime;
	}

	public String getEndRegisterTime() {
		return endRegisterTime;
	}

	public void setEndRegisterTime(String endRegisterTime) {
		this.endRegisterTime = endRegisterTime;
	}

	public String getBeginDatatime() {
		return beginDatatime;
	}

	public void setBeginDatatime(String beginDatatime) {
		this.beginDatatime = beginDatatime;
	}

	public String getEndDatatime() {
		return endDatatime;
	}

	public void setEndDatatime(String endDatatime) {
		this.endDatatime = endDatatime;
	}

	public String getFigureId() {
		return figureId;
	}

	public void setFigureId(String figureId) {
		this.figureId = figureId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	/**
	 * 得到成产区域名称用于employeeProduct表
	 * @return
	 */
	public String getTypes() {
		boolean flag=false;
		if(types!=null&&types.split(",")!=null){
			String[] aa = types.split(",");
			String result="(";
			for(int i=0;i<WORKSHOP.length;i++){
				if(aa[i].equals("true")){
					result+=WORKSHOP[i]+",";
					flag=true;
				}
			}
			result = result.substring(0, result.length()-1);
			result+=")";
			if(flag){
				return result;
			}else{
				return null;
			}
		}else{
			return null;
		}
		
	}
	
	/**
	 * 得到成产区域名称用于BounceFigure表
	 * @return
	 */
	public String getTypesName() {
		boolean flag=false;
		if(types!=null&&types.split(",")!=null){
			String[] aa = types.split(",");
			String result="(";
			for(int i=0;i<WORKSHOP2.length;i++){
				if(aa[i].equals("true")){
					result+=WORKSHOP2[i]+",";
					flag=true;
				}
			}
			result = result.substring(0, result.length()-1);
			result+=")";
			if(flag){
				return result;
			}else{
				return null;
			}
		}else{
			return null;
		}	
	}
	
	public void setTypes(String types) {
		this.types = types;
	}



}