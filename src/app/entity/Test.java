package app.entity;

public class Test {
private String uid;
private String name;
private Float typeA=0.0f;
private Float typeB=0.0f;
private Float typeC=0.0f;
private Float typeD=0.0f;
private Float baseSalary;
private Float floattingSalary;
private String salaryType;
private String departmentName;

public Test() {
	super();
}
public Test(String uid, String name, Float typeA, Float typeB,Float typeC,Float typeD,Float baseSalary,String salaryType) {
	super();
	this.uid = uid;
	this.name = name;
	this.typeA = typeA;
	this.typeB = typeB;
	this.typeC = typeC;
	this.typeD = typeD;
	this.salaryType=salaryType;
	this.baseSalary=baseSalary;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Float getTypeA() {
	return typeA;
}
public void setTypeA(Float typeA) {
	this.typeA = typeA;
}
public Float getTypeB() {
	return typeB;
}
public void setTypeB(Float typeB) {
	this.typeB = typeB;
}
public String getUid() {
	return uid;
}
public void setUid(String uid) {
	this.uid = uid;
}
public Float getTypeC() {
	return typeC;
}
public void setTypeC(Float typeC) {
	this.typeC = typeC;
}
public Float getTypeD() {
	return typeD;
}
public void setTypeD(Float typeD) {
	this.typeD = typeD;
}
public Float getBaseSalary() {
	return baseSalary;
}
public void setBaseSalary(Float baseSalary) {
	this.baseSalary = baseSalary;
}
public String getSalaryType() {
	return salaryType;
}
public void setSalaryType(String salaryType) {
	this.salaryType = salaryType;
}
public Float getFloattingSalary() {
	return floattingSalary;
}
public void setFloattingSalary(Float floattingSalary) {
	this.floattingSalary = floattingSalary;
}
public String getDepartmentName() {
	return departmentName;
}
public void setDepartmentName(String departmentName) {
	this.departmentName = departmentName;
}
}
