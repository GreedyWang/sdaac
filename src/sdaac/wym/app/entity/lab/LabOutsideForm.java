package sdaac.wym.app.entity.lab;

import java.util.Date;

/**
 * LabOutsideForm entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabOutsideForm extends LabForm implements java.io.Serializable {

	// Fields

	private Integer id;
	private String contactSn;
	private String applicantId;
	private Date applicantDate;
	private String figure;
	private String driveType;
	private String factory;
	private String size;
	private String autoLock;
	private Long mileage;
	private Byte coolant;
	private String paint;
	private Date arriveDate;
	private Integer key;
	private String projectSn;
	private Date requiredTestingDate;
	private String fuel;
	private String remark;
	private String remarkFile;
	private String r2;
	private String r2file;
	private Integer e;
	private Date at1;
	private Date at2;
	private Date at3;
	private Integer ae;
	private String admin;
	private Date adminDate;
	private String oilq;
	private String coolantq;
	private Long omileage;
	private String o1;
	private String o2;
	private String o3;
	private Byte gassedUp;
	private Byte washCar;
	private Byte ds;
	private Byte o4;
	private String operator;
	private Date operatorDate;
	private String testingRemark;
	private String hasException;
	private String hasExceptionFile;
	private Byte hasSendedData;

	// Constructors

	/** default constructor */
	public LabOutsideForm() {
	}

	/** full constructor */
	public LabOutsideForm(String contactSn, String applicantId,
			Date applicantDate, String figure, String driveType,
			String factory, String size, String autoLock, Long mileage,
			Byte coolant, String paint, Date arriveDate, Integer key,
			String projectSn, Date requiredTestingDate, String fuel,
			String remark, String remarkFile, String r2, String r2file,
			Integer e, Date at1, Date at2, Date at3, Integer ae, String admin,
			Date adminDate, String oilq, String coolantq, Long omileage,
			String o1, String o2, String o3, Byte gassedUp, Byte washCar,
			Byte ds, Byte o4, String operator, Date operatorDate,
			String testingRemark, String hasException, String hasExceptionFile,
			Byte hasSendedData) {
		this.contactSn = contactSn;
		this.applicantId = applicantId;
		this.applicantDate = applicantDate;
		this.figure = figure;
		this.driveType = driveType;
		this.factory = factory;
		this.size = size;
		this.autoLock = autoLock;
		this.mileage = mileage;
		this.coolant = coolant;
		this.paint = paint;
		this.arriveDate = arriveDate;
		this.key = key;
		this.projectSn = projectSn;
		this.requiredTestingDate = requiredTestingDate;
		this.fuel = fuel;
		this.remark = remark;
		this.remarkFile = remarkFile;
		this.r2 = r2;
		this.r2file = r2file;
		this.e = e;
		this.at1 = at1;
		this.at2 = at2;
		this.at3 = at3;
		this.ae = ae;
		this.admin = admin;
		this.adminDate = adminDate;
		this.oilq = oilq;
		this.coolantq = coolantq;
		this.omileage = omileage;
		this.o1 = o1;
		this.o2 = o2;
		this.o3 = o3;
		this.gassedUp = gassedUp;
		this.washCar = washCar;
		this.ds = ds;
		this.o4 = o4;
		this.operator = operator;
		this.operatorDate = operatorDate;
		this.testingRemark = testingRemark;
		this.hasException = hasException;
		this.hasExceptionFile = hasExceptionFile;
		this.hasSendedData = hasSendedData;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContactSn() {
		return this.contactSn;
	}

	public void setContactSn(String contactSn) {
		this.contactSn = contactSn;
	}

	public String getApplicantId() {
		return this.applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

	public Date getApplicantDate() {
		return this.applicantDate;
	}

	public void setApplicantDate(Date applicantDate) {
		this.applicantDate = applicantDate;
	}

	public String getFigure() {
		return this.figure;
	}

	public void setFigure(String figure) {
		this.figure = figure;
	}

	public String getDriveType() {
		return this.driveType;
	}

	public void setDriveType(String driveType) {
		this.driveType = driveType;
	}

	public String getFactory() {
		return this.factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getSize() {
		return this.size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getAutoLock() {
		return this.autoLock;
	}

	public void setAutoLock(String autoLock) {
		this.autoLock = autoLock;
	}

	public Long getMileage() {
		return this.mileage;
	}

	public void setMileage(Long mileage) {
		this.mileage = mileage;
	}

	public Byte getCoolant() {
		return this.coolant;
	}

	public void setCoolant(Byte coolant) {
		this.coolant = coolant;
	}

	public String getPaint() {
		return this.paint;
	}

	public void setPaint(String paint) {
		this.paint = paint;
	}

	public Date getArriveDate() {
		return this.arriveDate;
	}

	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}

	public Integer getKey() {
		return this.key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public String getProjectSn() {
		return this.projectSn;
	}

	public void setProjectSn(String projectSn) {
		this.projectSn = projectSn;
	}

	public Date getRequiredTestingDate() {
		return this.requiredTestingDate;
	}

	public void setRequiredTestingDate(Date requiredTestingDate) {
		this.requiredTestingDate = requiredTestingDate;
	}

	public String getFuel() {
		return this.fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemarkFile() {
		return this.remarkFile;
	}

	public void setRemarkFile(String remarkFile) {
		this.remarkFile = remarkFile;
	}

	public String getR2() {
		return this.r2;
	}

	public void setR2(String r2) {
		this.r2 = r2;
	}

	public String getR2file() {
		return this.r2file;
	}

	public void setR2file(String r2file) {
		this.r2file = r2file;
	}

	public Integer getE() {
		return this.e;
	}

	public void setE(Integer e) {
		this.e = e;
	}

	public Date getAt1() {
		return this.at1;
	}

	public void setAt1(Date at1) {
		this.at1 = at1;
	}

	public Date getAt2() {
		return this.at2;
	}

	public void setAt2(Date at2) {
		this.at2 = at2;
	}

	public Date getAt3() {
		return this.at3;
	}

	public void setAt3(Date at3) {
		this.at3 = at3;
	}

	public Integer getAe() {
		return this.ae;
	}

	public void setAe(Integer ae) {
		this.ae = ae;
	}

	public String getAdmin() {
		return this.admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public Date getAdminDate() {
		return this.adminDate;
	}

	public void setAdminDate(Date adminDate) {
		this.adminDate = adminDate;
	}

	public String getOilq() {
		return this.oilq;
	}

	public void setOilq(String oilq) {
		this.oilq = oilq;
	}

	public String getCoolantq() {
		return this.coolantq;
	}

	public void setCoolantq(String coolantq) {
		this.coolantq = coolantq;
	}

	public Long getOmileage() {
		return this.omileage;
	}

	public void setOmileage(Long omileage) {
		this.omileage = omileage;
	}

	public String getO1() {
		return this.o1;
	}

	public void setO1(String o1) {
		this.o1 = o1;
	}

	public String getO2() {
		return this.o2;
	}

	public void setO2(String o2) {
		this.o2 = o2;
	}

	public String getO3() {
		return this.o3;
	}

	public void setO3(String o3) {
		this.o3 = o3;
	}

	public Byte getGassedUp() {
		return this.gassedUp;
	}

	public void setGassedUp(Byte gassedUp) {
		this.gassedUp = gassedUp;
	}

	public Byte getWashCar() {
		return this.washCar;
	}

	public void setWashCar(Byte washCar) {
		this.washCar = washCar;
	}

	public Byte getDs() {
		return this.ds;
	}

	public void setDs(Byte ds) {
		this.ds = ds;
	}

	public Byte getO4() {
		return this.o4;
	}

	public void setO4(Byte o4) {
		this.o4 = o4;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperatorDate() {
		return this.operatorDate;
	}

	public void setOperatorDate(Date operatorDate) {
		this.operatorDate = operatorDate;
	}

	public String getTestingRemark() {
		return this.testingRemark;
	}

	public void setTestingRemark(String testingRemark) {
		this.testingRemark = testingRemark;
	}

	public String getHasException() {
		return this.hasException;
	}

	public void setHasException(String hasException) {
		this.hasException = hasException;
	}

	public String getHasExceptionFile() {
		return this.hasExceptionFile;
	}

	public void setHasExceptionFile(String hasExceptionFile) {
		this.hasExceptionFile = hasExceptionFile;
	}

	public Byte getHasSendedData() {
		return this.hasSendedData;
	}

	public void setHasSendedData(Byte hasSendedData) {
		this.hasSendedData = hasSendedData;
	}

}