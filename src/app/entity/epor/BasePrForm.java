package app.entity.epor;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import app.entity.Tempolyee;

/**
 * PrPrForm entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasePrForm implements java.io.Serializable {

	// Fields
	public static String mailType = "PR审批提示"; 
	private String id;
	private String tempolyeeByApplicantId;
	private String prCostCenter;
	private String prProject;
	private String tempolyeeByBuyerId;
	private Date applicantDate;
	private Date _endDate;
	private String prNo;
	private Integer isPlan;//0 计划内 1 计划外
	private Integer isCapital;//是否资本化
	private String type;
//	private Float total;
//	private Float totalE;
	private String recommendedSupplier;//推荐供应商
	private String acutalSupplier;//实际供应商
	private String receivingPlacePerpoleTel;
	private Integer state;
	private String fileName;
	private Integer departMentID;
	private String telephone;
	private String stateName;
	private Set prPrFormStates = new HashSet(0);
	private List<PrApprovedForm> prApprovedForms = new ArrayList<PrApprovedForm>(0);
	private List<PrApprovedForm>  copyPrApprovedForm = new ArrayList<PrApprovedForm>(0);
	private String begintime;
	private String endTime;
	private Integer version;//版本
	private String totalCategroy;//总账科目
	private String arno;
	//回传信息
	private String bigCountNo;
	private Integer isInTheSap;//是否在sap系统内
	private String prsn;
	private String io;
	private String north_AmericaFileName;//北美上传文件
	private String[] types={"","固定资产,","日常需求,","工具,","能    源,","租赁,","外包服务,","间接材料,","机器设备,","维修件,","IT"};
	private String typeName;
	private String isCapitalName;
	private String isInTheSapName;
	
	private List<PrBuyContext> prBuyContexts = new ArrayList<PrBuyContext>();
	public static String ITSingle = "10";
	public Map<Integer, StateManager> scheduleMap = new HashMap<Integer, StateManager>();
	
	public boolean isUnFinished(){
		return acutalSupplier !=null && isInTheSap!=null && receivingPlacePerpoleTel!=null;
	}
	
	/** default constructor */
	public BasePrForm() {

		//key :{当前状态,当前状态名称,成功状态,失败状态,成功条件,当前状态审批页面,状态对应的审批权限}
		scheduleMap.put(0,new StateManager(0,StateManager.states[0],1,0,"approve1","ordinaryApprove.jsp" ,""));// 个人
		scheduleMap.put(1,new StateManager(1,StateManager.states[1],12,0,"approvePmManager","PMApprove.jsp","PRPM") );//部门经理
	
		scheduleMap.put(12,new StateManager(12,StateManager.states[3],4,0,"approve3","ordinaryApprove.jsp","") );//相关部门
		scheduleMap.put(13,new StateManager(13,StateManager.states[3],5,0,"approve3","ordinaryApprove.jsp","") );//相关部门2
		
		scheduleMap.put(3,new StateManager(3,StateManager.states[4],2,0,"approve1","ordinaryApprove.jsp","PRBuyerManager") );//采购经理
		scheduleMap.put(4,new StateManager(4,StateManager.states[5],3,0,"approve1","blank.html" ,"PRBuyer"));//采购员确认
		
		scheduleMap.put(2,new StateManager(2,StateManager.states[2],13,0,"approve2","cwControlApprove.jsp","FinanceControler" ));//财务协调员
		
		scheduleMap.put(5,new StateManager(5,StateManager.states[6],6,0,"approveDOA","ordinaryApprove.jsp","FinanceManager") );//财务经理
		scheduleMap.put(6,new StateManager(6,StateManager.states[7],7,0,"approveDOA","ordinaryApprove.jsp","DeputyGeneralManager" ));//		副总经理审批
		scheduleMap.put(7,new StateManager(7,StateManager.states[8],8,0,"approveDOA","ordinaryApprove.jsp","PRManager") );//		总经理审批
		//---------------------------
		scheduleMap.put(8,new StateManager(8,StateManager.states[9],9,0,"approve5","feedbackApprove.jsp" ,"FinanceControler"));//		回填信息
		scheduleMap.put(9,new StateManager(9,StateManager.states[10],10,0,"approve5","PCLApprove.jsp","PCL") );//		等待收料部门确认
		scheduleMap.put(10,new StateManager(10,StateManager.states[11],11,0,"approve1","BuyerWritePN.jsp" ,"PRBuyer"));//		等待采购员填写订单号
		scheduleMap.put(11,new StateManager(11,StateManager.states[12],12,0,"","blank.html","") );//完成
	
	}
	
	/**
	 * 得到对应的状态
	 * @param key
	 * @return
	 */
	public int getNext2Step(){
		int a = scheduleMap.get(state).nextState;
		return scheduleMap.get(a).nextState;
	}
	
	/**
	 * 得到对应的状态
	 * @param key
	 * @return
	 */
	public StateManager getStateManager(){
		return scheduleMap.get(state);
	}
	
	public BasePrForm(String id) {
		this.id=id;
	}
	
	public BasePrForm(String id, String tempolyeeByBuyerId) {
		this.id = id;
		this.tempolyeeByBuyerId = tempolyeeByBuyerId;
	}
	

	/** full constructor */
	public BasePrForm(String id, String tempolyeeByApplicantId,
			String prCostCenter, 
			Date applicantDate, String prNo, String type,
//			Float total, 
			String recommendedSupplier,
			String receivingPlacePerpoleTel, Integer state, String fileName,
			Set prPrFormStates, List prApprovedForms, List prBuyContexts,String project,
			Integer isCapital,Integer isPlan,Integer isInTheSap,String bigCountNo,
			String arno) {
		this.id = id;
		this.tempolyeeByApplicantId = tempolyeeByApplicantId;
		this.prCostCenter = prCostCenter;
		this.applicantDate = applicantDate;
		this.prNo = prNo;
		
		this.type = type;
		//this.total = total;
		this.recommendedSupplier = recommendedSupplier;
		this.receivingPlacePerpoleTel = receivingPlacePerpoleTel;
		this.state = state;
		this.fileName = fileName;
		this.prPrFormStates = prPrFormStates;
		this.prApprovedForms = prApprovedForms;
		this.prBuyContexts = prBuyContexts;
		this.prProject = project;
		this.isCapital =isCapital;
		this.isPlan = isPlan;
		this.isInTheSap = isInTheSap;
		this.bigCountNo = bigCountNo;
		this.arno=arno;
	}
	
	
	
	
	

	// Property accessors
	
	public String getIsCapitalName(){
		if(isCapital!=null){
			if(isCapital==0){
				return "资本化";
			}else{
				return "费用化";
			}
		}return "";
	}
	
	public String getIsPlanName(){
		if(isCapital!=null){
			if(isCapital==0){
				return "计划外";
			}else{
				return "计划内";
			}
		}return "";
	}
	
	public String getTypeName(){
		String reslut="";
		if(type!=null&&!type.equals("")){
			for(String item:type.split(",")){
				if(item!=null&&!item.equals("")&&!item.equals("null")){
					reslut+=types[Integer.parseInt(item)];
				}
			}
		}
		return reslut;
	}
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public void setTempolyeeByBuyerId(String tempolyeeByBuyerId) {
		this.tempolyeeByBuyerId = tempolyeeByBuyerId;
	}

	public Date getApplicantDate() {
		return this.applicantDate;
	}

	public void setApplicantDate(Date applicantDate) {
		this.applicantDate = applicantDate;
	}

	public String getPrNo() {
		return this.prNo;
	}

	public void setPrNo(String prNo) {
		this.prNo = prNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

//	public Float getTotal() {
//		return this.total;
//	}
//
//	public void setTotal(Float total) {
//		this.total = total;
//	}

	public String getRecommendedSupplier() {
		return this.recommendedSupplier;
	}

	public void setRecommendedSupplier(String recommendedSupplier) {
		this.recommendedSupplier = recommendedSupplier;
	}

	public String getReceivingPlacePerpoleTel() {
		return this.receivingPlacePerpoleTel;
	}

	public void setReceivingPlacePerpoleTel(String receivingPlacePerpoleTel) {
		this.receivingPlacePerpoleTel = receivingPlacePerpoleTel;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Set getPrPrFormStates() {
		return this.prPrFormStates;
	}

	public void setPrPrFormStates(Set prPrFormStates) {
		this.prPrFormStates = prPrFormStates;
	}

	public List<PrApprovedForm> getPrApprovedForms() {
		return prApprovedForms;
	}

	public void setPrApprovedForms(List<PrApprovedForm> prApprovedForms) {
		this.prApprovedForms = prApprovedForms;
	}
	
	public List<PrApprovedForm> getCopyPrApprovedForm() {
		 List<PrApprovedForm>  aa = new ArrayList<PrApprovedForm>();
		 aa.addAll(prApprovedForms);
			for(PrApprovedForm item : prApprovedForms){
				if(item==null){
					aa.remove(item);
				}
			}	
		return aa;
	}

	public void setRs(List<PrApprovedForm> rs) {
		this.copyPrApprovedForm = rs;
	}

	public List<PrBuyContext> getPrBuyContexts() {
		return this.prBuyContexts;
	}

	public void setPrBuyContexts(List prBuyContexts) {
		this.prBuyContexts = prBuyContexts;
	}
	public Date get_endDate() {
		return _endDate;
	}
	public void set_endDate(Date date) {
		_endDate = date;
	}
	public Integer getIsPlan() {
		return isPlan;
	}
	public void setIsPlan(Integer isPlan) {
		this.isPlan = isPlan;
	}
	public Integer getDepartMentID() {
		return departMentID;
	}
	public void setDepartMentID(Integer departMentID) {
		this.departMentID = departMentID;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getStateName() {
		return scheduleMap.get(this.state).curStateName;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getIsCapital() {
		return isCapital;
	}

	public void setIsCapital(Integer isCapital) {
		this.isCapital = isCapital;
	}

	public String getPrsn() {
		return prsn;
	}

	public void setPrsn(String prsn) {
		this.prsn = prsn;
	}

	public String getIo() {
		return io;
	}

	public void setIo(String io) {
		this.io = io;
	}

	public String getNorth_AmericaFileName() {
		return north_AmericaFileName;
	}

	public void setNorth_AmericaFileName(String north_AmericaFileName) {
		this.north_AmericaFileName = north_AmericaFileName;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	//初始化版本号
	public void initVersion(){
		setVersion(1);
	}
	
	//版本增加
	public Integer versionUp(){
		return this.version+=1;
	}
	
	public void stateUp(){
		this.state++;
	}
	
	//保存PR
	public void saveState(){
		state=StateManager.SELF;
	}
	
	public String getTotalCategroy() {
		return totalCategroy;
	}

	public void setTotalCategroy(String totalCategroy) {
		this.totalCategroy = totalCategroy;
	}

	public String getAcutalSupplier() {
		return acutalSupplier;
	}

	public void setAcutalSupplier(String acutalSupplier) {
		this.acutalSupplier = acutalSupplier;
	}


	public String getTempolyeeByBuyerId() {
		return tempolyeeByBuyerId;
	}

	public String getTempolyeeByApplicantId() {
		return tempolyeeByApplicantId;
	}

	public void setTempolyeeByApplicantId(String tempolyeeByApplicantId) {
		this.tempolyeeByApplicantId = tempolyeeByApplicantId;
	}
	


	public String getPrProject() {
		return prProject;
	}

	public void setPrProject(String prProject) {
		this.prProject = prProject;
	}

	public Integer getIsInTheSap() {
		return isInTheSap;
	}

	public void setIsInTheSap(Integer isInTheSap) {
		this.isInTheSap = isInTheSap;
	}

	public String getBigCountNo() {
		return bigCountNo;
	}

	public void setBigCountNo(String bigCountNo) {
		this.bigCountNo = bigCountNo;
	}

//	public Float getTotalE() {
//		return totalE;
//	}
//
//	public void setTotalE(Float totalE) {
//		this.totalE = totalE;
//	}

	public String getIsInTheSapName() {
		if(isInTheSap==1){
			return "是";
		}else {
			return "否";
		}
	}

	public String getPrCostCenter() {
		return prCostCenter;
	}

	public void setPrCostCenter(String prCostCenter) {
		this.prCostCenter = prCostCenter;
	}

	public String getArno() {
		return arno;
	}

	public void setArno(String arno) {
		this.arno = arno;
	}

}