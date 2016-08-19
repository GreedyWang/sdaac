package app.entity.epor;

import java.util.Date;

import app.entity.Tempolyee;

/**
 * PrApprovedForm entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PrApprovedForm implements java.io.Serializable {

	// Fields
	
	private Integer id;
	private PrPrForm prPrForm=new PrPrForm();
	private Tempolyee tempolyee=new Tempolyee();
	private Date datetime=new Date();
	private String context;
	private Integer isApproved;//1 is pass,0 is denyc
	private String isApprovedName;
	private String relateUids;//相关人员
	private String type;		//类型
	private String rejectType;		//类型
	private Integer version;		//版本
	public static String  CWContorlType="财务";//财务审批类型
	public static String  RelationType="相关部门";//相关审批类型
	public static String  DepType="DepType";//部门审批类型
	public static String  FinManagerType="财务经理";//财务经理审批类型
	public static String  BuyManagerType="采购经理";//采购经理审批类型
	public static String  BuyType="采购员";//采购员审批类型
	private Integer helpExplain;//帮助解释
	// Constructors
	
	public Integer getHelpExplain() {
		return helpExplain;
	}

	public void setHelpExplain(Integer helpExplain) {
		this.helpExplain = helpExplain;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getVersion() {
		return version;
	}
	
	/**
	 * 得到Pr版本
	 * @return
	 */
	public Integer doGetVersion(){
		return prPrForm.getVersion();
	}
	
	public void doSetVersion(){
		version = prPrForm.getVersion();
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * 得到相关人员
	 */
	public String[] getRalate(){
		if(relateUids==null){
			return null;
		}else{
			return relateUids.split(";");
		}
	}
	
	public String getRelateUids() {
		return relateUids;
	}

	public void setRelateUids(String relateUids) {
		this.relateUids = relateUids;
	}

	public Integer getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Integer isApproved) {
		this.isApproved = isApproved;
	}

	/** default constructor */
	public PrApprovedForm() {
	}

	/** minimal constructor */
	public PrApprovedForm(Integer id, PrPrForm prPrForm, Tempolyee tempolyee,
			Date datetime) {
		this.id = id;
		this.prPrForm = prPrForm;
		this.tempolyee = tempolyee;
		this.datetime = datetime;
	}

	/** full constructor */
	public PrApprovedForm(Integer id, PrPrForm prPrForm, Tempolyee tempolyee,
			Date datetime, String context) {
		this.id = id;
		this.prPrForm = prPrForm;
		this.tempolyee = tempolyee;
		this.datetime = datetime;
		this.context = context;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PrPrForm getPrPrForm() {
		return this.prPrForm;
	}

	public void setPrPrForm(PrPrForm prPrForm) {
		this.prPrForm = prPrForm;
	}

	public Tempolyee getTempolyee() {
		return this.tempolyee;
	}

	public void setTempolyee(Tempolyee tempolyee) {
		this.tempolyee = tempolyee;
	}

	public Date getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getContext() {
		return this.context;
	}
	private String newContext;
	
	public String getNewContext() {
		if(rejectType!=null && !"".equals(rejectType)){
			return rejectType+":"+this.context;
		}
		return this.context;
	}

	public void setNewContext(String newContext) {
		this.newContext = newContext;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getIsApprovedName() {
		if(isApproved!=null&&isApproved==1){
			return "批准/Approved";
		}else if(isApproved!=null&&isApproved==0){
			return "不批准/Not Approved";
		}else if(isApproved!=null&&isApproved==2){
			return "需要解释/Need Explain";
		}else if(isApproved!=null&&isApproved==5){
			return "退回到采购员";
		}
		return isApprovedName;
	}

	public String getRejectType() {
		return rejectType;
	}

	public void setRejectType(String rejectType) {
		this.rejectType = rejectType;
	}


}