package app.entity;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sdaac.wym.app.entity.vave.PointsDetails;

import net.sf.json.JSONString;

/**
 * Tempolyee generated by MyEclipse Persistence Tools
 * Employee Entity Object
 */

public class Tempolyee implements java.io.Serializable,JSONString {

	// Fields
	private String ename;
	private String managedms;
	private String supervisor;
	private String netid;
	private String district;
	private String group;
	public  String selectedGroup;//被选中的组
	private float bonusCoeff;//奖金系数
	private String uid;
	private String emailcc;
	private String eprownerarea;
	private Float dmsratio;
	private Tdepartment tdepartment =new Tdepartment();
	private String[] groupNames = {"综合组","员工","小组长","大组长","区域经理"};
	private String groupName;
	private String backer;

	private String name;

	private String leaderID;

	private Float baseSalary;

	private Float floatingSalary;

	private String salaryType;

	private Set tproposalsForCollectionPersion = new HashSet(0);

	private Set tproposalsForProposalPerson = new HashSet(0);

	private Set temployeeProducts = new HashSet(0);

	private Set tempIndexes = new HashSet(0);
	
	private Set roles=new HashSet(0);
	
	private Integer level;
	
	private String mail;
	
	private String roleids;
	
	private List<String> roleids_b;
	
	private String type; //salary worker
	
	private Integer isSeparation=1;//是否离职,1为在职，0为离职
	
	private  Set  pointsDetails=new HashSet(0);
	
	private String area;//工作区域

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getRoleids_b() {
		return roleids_b;
	}

	public void setRoleids_b(List<String> roleids_b) {
		this.roleids_b = roleids_b;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	/** default constructor */
	public Tempolyee() {
	}

	/** minimal constructor */
	public Tempolyee(String uid, String name) {
		this.uid = uid;
		this.name = name;
	}
	
	public Tempolyee(String uid) {
		this.uid = uid;
	
	}
	
	/** full constructor */
	public Tempolyee(String uid, Tdepartment tdepartment, String name,
			String monitor, Float baseSalary, Float floatingSalary,
			String salaryType, Set tproposalsForCollectionPersion,
			Set tproposalsForProposalPerson, Set temployeeProducts,
			Set tempIndexes) {
		this.uid = uid;
		this.tdepartment = tdepartment;
		this.name = name;
		this.leaderID = monitor;
		this.baseSalary = baseSalary;
		this.floatingSalary = floatingSalary;
		this.salaryType = salaryType;
		this.tproposalsForCollectionPersion = tproposalsForCollectionPersion;
		this.tproposalsForProposalPerson = tproposalsForProposalPerson;
		this.temployeeProducts = temployeeProducts;
		this.tempIndexes = tempIndexes;
	}

	// Property accessors

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Tdepartment getTdepartment() {
		return this.tdepartment;
	}

	public void setTdepartment(Tdepartment tdepartment) {
		this.tdepartment = tdepartment;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getBaseSalary() {
		return this.baseSalary;
	}

	public void setBaseSalary(Float baseSalary) {
		this.baseSalary = baseSalary;
	}

	public Float getFloatingSalary() {
		return this.floatingSalary;
	}

	public void setFloatingSalary(Float floatingSalary) {
		this.floatingSalary = floatingSalary;
	}

	public String getSalaryType() {
		return this.salaryType;
	}

	public void setSalaryType(String salaryType) {
		this.salaryType = salaryType;
	}

	public Set getTproposalsForCollectionPersion() {
		return this.tproposalsForCollectionPersion;
	}

	public void setTproposalsForCollectionPersion(
			Set tproposalsForCollectionPersion) {
		this.tproposalsForCollectionPersion = tproposalsForCollectionPersion;
	}

	public Set getTproposalsForProposalPerson() {
		return this.tproposalsForProposalPerson;
	}

	public void setTproposalsForProposalPerson(Set tproposalsForProposalPerson) {
		this.tproposalsForProposalPerson = tproposalsForProposalPerson;
	}

	public Set getTemployeeProducts() {
		return this.temployeeProducts;
	}

	public void setTemployeeProducts(Set temployeeProducts) {
		this.temployeeProducts = temployeeProducts;
	}

	public Set getTempIndexes() {
		return this.tempIndexes;
	}

	public void setTempIndexes(Set tempIndexes) {
		this.tempIndexes = tempIndexes;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getLeaderID() {
		return leaderID;
	}

	public void setLeaderID(String leaderID) {
		this.leaderID = leaderID;
	}

	public Set getRoles() {
		return roles;
	}

	public void setRoles(Set roles) {
		this.roles = roles;
	}

	public String getRoleids() {
		return roleids;
	}

	public void setRoleids(String roleids) {
		this.roleids = roleids;
	}

	public String toJSONString() {
		// TODO Auto-generated method stub
		return "{uid:'"+uid+"',uname:'"+name+"',departId:'"+tdepartment.getName()+"'}";
	}

	public Integer getIsSeparation() {
		return isSeparation;
	}

	public void setIsSeparation(Integer isSeparation) {
		this.isSeparation = isSeparation;
	}

	public Set getPointsDetails() {
		return pointsDetails;
	}

	public void setPointsDetails(Set pointsDetails) {
		this.pointsDetails = pointsDetails;
	}

	public String getBacker() {
		return backer;
	}

	public void setBacker(String backer) {
		this.backer = backer;
	}

	public String getEprownerarea() {
		return eprownerarea;
	}

	public void setEprownerarea(String eprownerarea) {
		this.eprownerarea = eprownerarea;
	}

	public String getEmailcc() {
		return emailcc;
	}

	public void setEmailcc(String emailcc) {
		this.emailcc = emailcc;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
	
	private static String[] levels = {"4","3","2","1","0"};//员工等级 0 是综合组
	
	/**
	 * 返回DMS下一级
	 * @param group
	 * @return
	 */
	public static String getNextLevel(String group){
		for(int i = 0; i < levels.length ; i++){
			if(group!=null && group.equals(levels[i])) {
				return levels[i+1];
			}
		}
		return "0";
	}

	public float getBonusCoeff() {
		return bonusCoeff;
	}

	public void setBonusCoeff(float bonusCoeff) {
		this.bonusCoeff = bonusCoeff;
	}

	public String getSelectedGroup() {
		return selectedGroup;
	}

	public void setSelectedGroup(String selectedGroup) {
		this.selectedGroup = selectedGroup;
	}

	public Float getDmsratio() {
		return dmsratio;
	}

	public void setDmsratio(Float dmsratio) {
		this.dmsratio = dmsratio;
	}

	public String getGroupName() {
		if(group!=null)
		return groupNames[Integer.parseInt(group)];
		else return "";
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getNetid() {
		return netid;
	}

	public void setNetid(String netid) {
		this.netid = netid;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getManagedms() {
		return managedms;
	}

	public void setManagedms(String managedms) {
		this.managedms = managedms;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}
	
}