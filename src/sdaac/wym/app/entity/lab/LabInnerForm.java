package sdaac.wym.app.entity.lab;

import java.util.Date;

/**
 * LabInnerForm entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabInnerForm extends LabForm implements java.io.Serializable {

	// Fields

	private Integer id;
	private String applicantId;
	private Date applicantDate;
	private String applicantPhone;
	private String goodsName;
	private String goodsSn;
	private String goodsFigure;
	private Integer quantity;
	private String testingAim;
	private String testingDesc;
	private String factory;
	private String projectName;
	private String standard;
	private String standardFile;
	private Date requiredFinishDate;
	private String costTime;
	private Byte hasReport;
	private Date sendedDate;
	private String dealinfo;
	private String secrecyRequire;
	private String a1;
	private String a2;
	private String a3;
	private String a4;
	private Integer facilityId;
	private Date fpt;
	private Date fgt;
	private Long totalmoney;
	private String fis;
	private String fcf;
	private Date fcfd;
	private String projectSn;
	private String contactSn;

	// Constructors

	/** default constructor */
	public LabInnerForm() {
	}

	/** full constructor */
	public LabInnerForm(String applicantId, Date applicantDate,
			String applicantPhone, String goodsName, String goodsSn,
			String goodsFigure, Integer quantity, String testingAim,
			String testingDesc, String factory, String projectName,
			String standard, String standardFile, Date requiredFinishDate,
			String costTime, Byte hasReport, Date sendedDate, String dealinfo,
			String secrecyRequire, String a1, String a2, String a3, String a4,
			Integer facilityId, Date fpt, Date fgt, Long totalmoney,
			String fis, String fcf, Date fcfd, String projectSn,
			String contactSn) {
		this.applicantId = applicantId;
		this.applicantDate = applicantDate;
		this.applicantPhone = applicantPhone;
		this.goodsName = goodsName;
		this.goodsSn = goodsSn;
		this.goodsFigure = goodsFigure;
		this.quantity = quantity;
		this.testingAim = testingAim;
		this.testingDesc = testingDesc;
		this.factory = factory;
		this.projectName = projectName;
		this.standard = standard;
		this.standardFile = standardFile;
		this.requiredFinishDate = requiredFinishDate;
		this.costTime = costTime;
		this.hasReport = hasReport;
		this.sendedDate = sendedDate;
		this.dealinfo = dealinfo;
		this.secrecyRequire = secrecyRequire;
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
		this.a4 = a4;
		this.facilityId = facilityId;
		this.fpt = fpt;
		this.fgt = fgt;
		this.totalmoney = totalmoney;
		this.fis = fis;
		this.fcf = fcf;
		this.fcfd = fcfd;
		this.projectSn = projectSn;
		this.contactSn = contactSn;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getApplicantPhone() {
		return this.applicantPhone;
	}

	public void setApplicantPhone(String applicantPhone) {
		this.applicantPhone = applicantPhone;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsSn() {
		return this.goodsSn;
	}

	public void setGoodsSn(String goodsSn) {
		this.goodsSn = goodsSn;
	}

	public String getGoodsFigure() {
		return this.goodsFigure;
	}

	public void setGoodsFigure(String goodsFigure) {
		this.goodsFigure = goodsFigure;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getTestingAim() {
		return this.testingAim;
	}

	public void setTestingAim(String testingAim) {
		this.testingAim = testingAim;
	}

	public String getTestingDesc() {
		return this.testingDesc;
	}

	public void setTestingDesc(String testingDesc) {
		this.testingDesc = testingDesc;
	}

	public String getFactory() {
		return this.factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getStandard() {
		return this.standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getStandardFile() {
		return this.standardFile;
	}

	public void setStandardFile(String standardFile) {
		this.standardFile = standardFile;
	}

	public Date getRequiredFinishDate() {
		return this.requiredFinishDate;
	}

	public void setRequiredFinishDate(Date requiredFinishDate) {
		this.requiredFinishDate = requiredFinishDate;
	}

	public String getCostTime() {
		return this.costTime;
	}

	public void setCostTime(String costTime) {
		this.costTime = costTime;
	}

	public Byte getHasReport() {
		return this.hasReport;
	}

	public void setHasReport(Byte hasReport) {
		this.hasReport = hasReport;
	}

	public Date getSendedDate() {
		return this.sendedDate;
	}

	public void setSendedDate(Date sendedDate) {
		this.sendedDate = sendedDate;
	}

	public String getDealinfo() {
		return this.dealinfo;
	}

	public void setDealinfo(String dealinfo) {
		this.dealinfo = dealinfo;
	}

	public String getSecrecyRequire() {
		return this.secrecyRequire;
	}

	public void setSecrecyRequire(String secrecyRequire) {
		this.secrecyRequire = secrecyRequire;
	}

	public String getA1() {
		return this.a1;
	}

	public void setA1(String a1) {
		this.a1 = a1;
	}

	public String getA2() {
		return this.a2;
	}

	public void setA2(String a2) {
		this.a2 = a2;
	}

	public String getA3() {
		return this.a3;
	}

	public void setA3(String a3) {
		this.a3 = a3;
	}

	public String getA4() {
		return this.a4;
	}

	public void setA4(String a4) {
		this.a4 = a4;
	}

	public Integer getFacilityId() {
		return this.facilityId;
	}

	public void setFacilityId(Integer facilityId) {
		this.facilityId = facilityId;
	}

	public Date getFpt() {
		return this.fpt;
	}

	public void setFpt(Date fpt) {
		this.fpt = fpt;
	}

	public Date getFgt() {
		return this.fgt;
	}

	public void setFgt(Date fgt) {
		this.fgt = fgt;
	}

	public Long getTotalmoney() {
		return this.totalmoney;
	}

	public void setTotalmoney(Long totalmoney) {
		this.totalmoney = totalmoney;
	}

	public String getFis() {
		return this.fis;
	}

	public void setFis(String fis) {
		this.fis = fis;
	}

	public String getFcf() {
		return this.fcf;
	}

	public void setFcf(String fcf) {
		this.fcf = fcf;
	}

	public Date getFcfd() {
		return this.fcfd;
	}

	public void setFcfd(Date fcfd) {
		this.fcfd = fcfd;
	}

	public String getProjectSn() {
		return this.projectSn;
	}

	public void setProjectSn(String projectSn) {
		this.projectSn = projectSn;
	}

	public String getContactSn() {
		return this.contactSn;
	}

	public void setContactSn(String contactSn) {
		this.contactSn = contactSn;
	}

}