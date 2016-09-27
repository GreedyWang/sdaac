package app.entity.epor;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

public class PrPrForm  implements java.io.Serializable {
	
	private String showDate;
	
	// Fields
	private String formURL;
	//jbpm processInstance Id
	private String processInstanceId;
	//jbpm state name
	private String newStateName;
	//jbpm task assignee
	private String assignee;
	//new element
	private String capitalcategroy;
	private String capitalcategroyName;
	
	public String getCapitalcategroyName(){
		if(capitalcategroy!=null){
			if(capitalcategroy.equals("1")){
				return "HVAC";
			}else if(capitalcategroy.equals("2")){
				return "PTC";
			}else if(capitalcategroy.equals("3")){
				return "IT";
			}else if(capitalcategroy.equals("4")){
				return "NP";
			}else if(capitalcategroy.equals("5")){
				return "Engineering";
			}else{
				return "";
			}
		}else{
			return "";
		}
	}
	
	private String buyer1;
	private String buyer2;
	private String pm;
	private String ccowner;
	private String issueFor;
	private String buyerNetid;
	private String bigcountFile;
	private String urgentFile;
	private String singlesourceFile;
	private String buyerFile;
	
	public static int areaToSource(String area){
		if(area.equals("DT02"))return 1;
		else if(area.equals("DT03"))return 2;
		else if(area.equals("DT04"))return 3;
		else if(area.equals("DT05"))return 4;
		else return 1;
		
	}
	
	public static int BUYER_LEVEL = 0;
	private String relateuid;
	private Integer lastState;//保存最新一次状态值
	private String isOtherCostCenter;
	private String sourceRange;
	private boolean IS_CHANGED = false;//是否改变了成本中心
	public static String mailType = "PR审批提示"; 
	public static String mailType2 = "你的PR单被拒绝"; 
	public static String mailType3 = "你的PR单需要解释"; 
	public static String mailType4 = "PR审批提示"; 
	public static int ShenYang = 2; 
	public static int SHPLANT = 4; 
	public static int SDAAC = 1; 
	public static int CAPTIAL = 0;
	public String Mailer;
	private String id;
	private int ssid;
	//update 2014/04/20 ZJP
	private Integer isUrgency;
	private String fileUrgency;
	private Integer isUrgencyB;
	private Integer isAssignation;
	private String fileAssignation;
	private Integer isAssignationB;
	
	
	
	private String departments;//
	private Tempolyee tempolyeeByApplicantId=new Tempolyee();
	private PrCostCenter prCostCenter=new PrCostCenter();
	private PrProject prProject=new PrProject();
	private String tempolyeeByBuyerId;
	private Date applicantDate;
	private String tempApplicantDate;
	private Date _endDate;
	private String prNo;
	private Integer isPlan;//0 计划内 1 计划外
	private Integer isCapital;//是否资本化 0 资本化，1费用化
	private String type;
	private Float total;
	private java.math.BigDecimal totalE;
	private String recommendedSupplier;//推荐供应商
	private String acutalSupplier;//实际供应商
	private String receivingPlacePerpoleTel;
	private Integer state;
	private Integer source;//来源：上海，沈阳
	private String quertState;//用于查询的状态
	private String quertState2;//用于查询的状态
	private String fileName;
	private String buyerfileName;//采购文件
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
	public String stateLz;//state大于0
	private String remark;
	//回传信息
	private Integer info = 0;//1:等待解释,0:正在审批,-1:被退回,4:编辑状态,5:已解释,6:完成;3:审批通过,正在回填
	private String bigCountNo;
	private Integer isInTheSap;//是否在sap系统内
	private String prsn;
	private String io;
	private String north_AmericaFileName;//北美上传文件
	private String[] types={"","固定资产,","日常需求,","工具,","能    源,","租赁,","外包服务,","间接材料,","机器设备,","维修件,","IT","直接物料SPOT BUY","OST","LAB","HR_培训费"};
	private String typeName;
	private String isCapitalName;
	private String isInTheSapName;
	public boolean flag = false;;
	//new property
	public String workshopToUse;
	public String facilityToUse;
	public List<Paydetails> paydetails;
	
	//isfin
	private String isFin;
	public String getIsFin() {
		return isFin;
	}

	public void setIsFin(String isFin) {
		this.isFin = isFin;
	}

	private List<PrBuyContext> prBuyContexts = new ArrayList<PrBuyContext>();
	public static String ITSingle = "10";
	
	//approval process 
	public static Map<Integer, StateManager> scheduleMap;
	public static Map<Integer, StateManager> scheduleSYMap;
	public static Map<Integer, StateManager> scheduleYanTaiMap;
	public static Map<Integer, StateManager> scheduleSHMap;
	
	public boolean isUnFinished(){
		return acutalSupplier !=null  && receivingPlacePerpoleTel!=null;
	}
	
	public String getSysId(){
		DateFormat df = new SimpleDateFormat("yyMMddmmss"); 
		return df.format(new Date())+tempolyeeByApplicantId.getUid();
	}
	
	/**
	 * 返回mail链接
	 * @param prId
	 * @return
	 */
	public static String getMailUrl(String prId) {
		return new StringBuffer(
				"<a href='http://10.243.75.20:9090/bpp/login.do?actionType=mailCheck&no="+prId+"'>PR邮件提示</a>").toString();
	}
	
	/** default constructor */
	public PrPrForm() {
		scheduleMap = new HashMap<Integer, StateManager>();
		/**----SH DOA----- */
		//key :{当前状态,当前状态名称,成功状态,失败状态,成功条件,当前状态审批页面,状态对应的审批权限}
		//总部必须包含所有状态 即使用不到的状态，因为再调用查询状态时 用的是scheduleMap
		scheduleMap.put(0,new StateManager(0,StateManager.states[0],1,0,"approve1","ordinaryApprove.jsp" ,""));// 个人
		scheduleMap.put(1,new StateManager(1,StateManager.states[1],12,0,"approveNewDM","PMApprove.jsp","PRPM") );//部门经理		
		scheduleMap.put(28,new StateManager(28,"主管",1,0,"approve1","ordinaryApprove.jsp" ,"PRSupervisor"));//supervisor		
		scheduleMap.put(22,new StateManager(22,"LAB",1,0,"approve1","PMApprove.jsp","PRLAB") );//LAB部门经理
		scheduleMap.put(24,new StateManager(24,"培训",1,0,"approve1","PMApprove.jsp","PRTRINING") );//培训		
		scheduleMap.put(19,new StateManager(19,"CostCenterOwner",12,0,"approvePmManager","PMApprove.jsp","PRCC") );//CostCenter经理
		scheduleMap.put(191,new StateManager(191,"CostCenterOwner",2,0,"approvePmManager","PMApprove.jsp","PRCC") );//CostCenter经理
		scheduleMap.put(16,new StateManager(16,"OST经理",4,0,"approveOTSManager","ordinaryApprove.jsp","PROTSManager") );//OTS经理
		scheduleMap.put(12,new StateManager(12,"项目经理",4,0,"approve1","ordinaryApprove.jsp","") );//相关部门
		scheduleMap.put(13,new StateManager(13,StateManager.states[3],5,0,"approve1","ordinaryApprove.jsp","") );//相关部门
		//*****************************
		scheduleMap.put(33,new StateManager(33,StateManager.states[3],5,0,"approve1","ordinaryApprove.jsp","PRrelated") );//相关部门2		
		scheduleMap.put(3,new StateManager(3,StateManager.states[4],2,0,"approveSHBuyerManager","buyerManagerApprove.jsp","PRBuyerManager") );//采购经理
		scheduleMap.put(4,new StateManager(4,StateManager.states[5],3,0,"approve1","blank.html" ,"PRBuyer"));//采购员确认	
		scheduleMap.put(2,new StateManager(2,StateManager.states[2],13,0,"approve2","cwControlApprove.jsp","PRFinanceControler" ));//财务协调员
		//2012-7-29 new change
		scheduleMap.put(42,new StateManager(42,"财务费用化审核",5,0,"approve2","cwControlApprove.jsp","PRFinanceControlerCaptial" ));//财务协调员
		scheduleMap.put(23,new StateManager(23,"Operation Manager_李赟",6,0,"approveOperationM","ordinaryApprove.jsp","PROCC" ));//		副总经理审批
		scheduleMap.put(5,new StateManager(5,StateManager.states[6],6,0,"approveDOA","finManagerApprove.jsp","PRFinanceManager") );//财务经理
		scheduleMap.put(6,new StateManager(6,StateManager.states[7],7,0,"approveDOA","ordinaryApprove.jsp","PRDeputyGeneralManager" ));//副总经理审批
		scheduleMap.put(7,new StateManager(7,StateManager.states[8],17,0,"approveDOA","ordinaryApprove.jsp","PRManager") );//		总经理审批		
		scheduleMap.put(8,new StateManager(8,StateManager.states[9],9,0,"approveFeedBack","feedbackApprove.jsp" ,"PRFinanceControlerWeak"));//		回填信息
		scheduleMap.put(9,new StateManager(9,StateManager.states[10],10,0,"approve4","PCLApprove.jsp","PRPCL") );//		等待收料部门确认
		scheduleMap.put(10,new StateManager(10,StateManager.states[11],11,0,"approve1","BuyerWritePN.jsp" ,"PRBuyer"));//		等待采购员填写订单号
		scheduleMap.put(11,new StateManager(11,StateManager.states[12],11,0,"","blank.html","") );//完成
		scheduleMap.put(14,new StateManager(14,StateManager.states[14],7,0,"approveDOA","ordinaryApprove.jsp","PRSYManager") );//sy总经理审批
		scheduleMap.put(17,new StateManager(17,"KK",8,0,"approveDOA","ordinaryApprove.jsp","PRDSB") );//KK审批
		scheduleMap.put(18,new StateManager(18,StateManager.states[6],9,0,"approve5","ordinaryApprove.jsp","PRFinanceManager") );//财务经理2
		scheduleMap.put(31,new StateManager(31,StateManager.statesNameYanTai[6],1,0,"approve1","ordinaryApprove.jsp" ,"PRYanTaiManager"));// 烟台总经理
		//new addition
		scheduleMap.put(52,new StateManager(52,"上海财务审批烟台PR",55,0,"approve1","feedbackApprove.jsp","PRFinanceControlerSH4YT" ));//财务协调员
		scheduleMap.put(55,new StateManager(55,"上海财务经理",7,0,"approve1","ordinaryApprove.jsp","PRFinanceManagerSH4YT" ));//财务协调员
		scheduleMap.put(59,new StateManager(59,"上海PCLSAP录入",10,0,"approve5","PCLApprove.jsp","PRSHPCL") );//等待收料部门确认
		scheduleMap.put(52,new StateManager(52,"总部财务审批",5,0,"zongbuCWApprove","zongbucwControlApprove.jsp","PRFinanceControlerSH4YT" ));//财务协调员
		scheduleMap.put(55,new StateManager(55,"总部财务经理",6,0,"approveDOA2","ordinaryApprove.jsp","PRFinanceManagerSH4YT" ));//财务协调员	
		scheduleMap.put(57,new StateManager(57,"总部采购经理",2,0,"approveBuyerManager","buyerManagerApprove.jsp","PRSHBuyerManager" ));//总部采购经理
		
		//scheduleMap.put(6,new StateManager(6,StateManager.states[7],7,0,"approveDOA2","ordinaryApprove.jsp","PRDeputyGeneralManager" ));//副总经理审批
		//scheduleMap.put(7,new StateManager(7,StateManager.states[8],17,0,"approveDOA2","ordinaryApprove.jsp","PRManager") );//		总经理审批
		scheduleMap.put(56,new StateManager(56,StateManager.SH_CTO_NAME,55,0,"approveDOA2","ordinaryApprove.jsp","SH_CTO") );//		SH CTO
		scheduleMap.put(15,new StateManager(15,StateManager.states[7],14,0,"approveDOA","ordinaryApprove.jsp","PRDeputyGeneralManager2") );//PLANT副总经理审批
		scheduleMap.put(58,new StateManager(58,"总部回填信息",9,0,"approve11","feedbackApprove.jsp","PRFinanceControlerSH4YT" ));//总部回填信息
		
		/**----SY DOA----- */
		scheduleSYMap = new HashMap<Integer, StateManager>();
		//key :{当前状态,当前状态名称,成功状态,失败状态,成功条件,当前状态审批页面,状态对应的审批权限}
		scheduleSYMap.put(0,new StateManager(0,StateManager.states[0],1,0,"approve1","ordinaryApprove.jsp" ,""));// 个人
		scheduleSYMap.put(1,new StateManager(1,StateManager.states[1],19,0,"approveCostCenter","PMApprove.jsp","PRPM") );//部门经理					
		scheduleSYMap.put(19,new StateManager(19,"CostCenterOwner",12,0,"approvePmManager","PMApprove.jsp","PRCC") );//CostCenter经理
		scheduleSYMap.put(12,new StateManager(12,"项目经理",4,0,"approve1","ordinaryApprove.jsp","") );//相关部门
		scheduleSYMap.put(13,new StateManager(13,StateManager.states[3],5,0,"approve3","ordinaryApprove.jsp","") );//相关部门2	
		scheduleSYMap.put(3,new StateManager(3,StateManager.states[4],2,0,"approveBuyerManager","buyerManagerApprove.jsp","PRBuyerManager") );//采购经理
		scheduleSYMap.put(4,new StateManager(4,StateManager.states[5],3,0,"approve1","blank.html" ,"PRBuyer"));//采购员确认			
		scheduleSYMap.put(42,new StateManager(42,"财务费用化审核",5,0,"approve2","cwControlApprove.jsp","PRFinanceControlerCaptial" ));//财务协调员
		scheduleSYMap.put(2,new StateManager(2,StateManager.states[2],42,0,"approveFinanceCYanTai","cwControlApprove.jsp","PRFinanceControler" ));//财务协调员		
		scheduleSYMap.put(5,new StateManager(5,StateManager.states[6],15,0,"approveDOA","ordinaryApprove.jsp","PRFinanceManager") );//财务经理
		//scheduleSYMap.put(16,new StateManager(16,StateManager.states[7],14,0,"approveDOA","ordinaryApprove.jsp","PRDeputyGeneralManager3" ));//副总经理审批		
		scheduleSYMap.put(15,new StateManager(15,StateManager.states[7],14,0,"approveDOA","ordinaryApprove.jsp","PRDeputyGeneralManager2") );//PLANT副总经理审批
		scheduleSYMap.put(14,new StateManager(14,StateManager.states[14],56,0,"approveDOA2","ordinaryApprove.jsp","PRSYManager") );//sy总经理审批	
		scheduleSYMap.put(18,new StateManager(18,StateManager.states[6],9,0,"approve5","ordinaryApprove.jsp","PRFinanceManager") );//财务经理2
		scheduleSYMap.put(8,new StateManager(8,StateManager.states[9],9,0,"approveFeedBack","feedbackApprove.jsp" ,"PRFinanceControler"));//回填信息
		scheduleSYMap.put(9,new StateManager(9,StateManager.states[10],10,0,"approve4","PCLApprove.jsp","PRPCL") );//		等待收料部门确认
		scheduleSYMap.put(10,new StateManager(10,StateManager.states[11],11,0,"approve1","BuyerWritePN.jsp" ,"PRBuyer"));//		等待采购员填写订单号
		scheduleSYMap.put(11,new StateManager(11,StateManager.states[12],12,0,"","blank.html","") );//完成
		scheduleSYMap.put(57,new StateManager(57,"总部采购经理",2,0,"approveBuyerManager","buyerManagerApprove.jsp","PRSHBuyerManager" ));//总部采购经理
		scheduleSYMap.put(16,new StateManager(16,"OST经理",4,0,"approveOTSManager","ordinaryApprove.jsp","") );//OTS经理
		//Other
		/**---*/scheduleSYMap.put(31,new StateManager(31,"工厂总经理",56,0,"approveDOA2","ordinaryApprove.jsp" ,"PRSYManager"));// Plant总经理
		scheduleSYMap.put(52,new StateManager(52,"总部财务审批",5,0,"approve11","ordinaryApprove.jsp","PRFinanceControlerSH4YT" ));//财务协调员
		scheduleSYMap.put(55,new StateManager(55,"总部财务经理",6,0,"approveDOA2","ordinaryApprove.jsp","PRFinanceManagerSH4YT" ));//财务协调员	
		scheduleSYMap.put(6,new StateManager(6,StateManager.states[7],7,0,"approveDOA2","ordinaryApprove.jsp","PRDeputyGeneralManager" ));//副总经理审批
		scheduleSYMap.put(7,new StateManager(7,StateManager.states[8],8,0,"approveDOA2","ordinaryApprove.jsp","PRManager") );//		总经理审批
		scheduleSYMap.put(56,new StateManager(56,StateManager.SH_CTO_NAME,55,0,"approveDOA2","ordinaryApprove.jsp","SH_CTO") );//		SH CTO		
		scheduleSYMap.put(58,new StateManager(58,"总部回填信息",9,0,"approve11","feedbackApprove.jsp","PRFinanceControlerSH4YT" ));//总部回填信息
		/**----YanTai DOA----- */
		scheduleYanTaiMap = new HashMap<Integer, StateManager>();
		scheduleYanTaiMap.put(0,new StateManager(0,StateManager.statesNameYanTai[0],1,0,"approve1","ordinaryApprove.jsp" ,""));// 个人
		scheduleYanTaiMap.put(1,new StateManager(1,StateManager.states[1],19,0,"approveCostCenter","PMApprove.jsp","PRPM") );//部门经理
		scheduleYanTaiMap.put(19,new StateManager(19,"CostCenterOwner",12,0,"approvePmManager","PMApprove.jsp","PRCC") );//CostCenter经理
		scheduleYanTaiMap.put(12,new StateManager(12,"项目经理",4,0,"approve1","ordinaryApprove.jsp","") );//相关部门
		scheduleYanTaiMap.put(4,new StateManager(4,StateManager.states[5],3,0,"approve1","blank.html" ,"PRBuyer"));//采购员确认
		scheduleYanTaiMap.put(3,new StateManager(3,StateManager.states[4],2,0,"approveBuyerManager","buyerManagerApprove.jsp","PRBuyerManager") );//采购经理
		scheduleYanTaiMap.put(2,new StateManager(2,StateManager.states[2],5,0,"approve2","cwControlApprove.jsp","PRFinanceControler" ));//财务协调员
		scheduleYanTaiMap.put(5,new StateManager(5,StateManager.states[6],31,0,"approve1","ordinaryApprove.jsp","PRFinanceManager") );//财务经理
		scheduleYanTaiMap.put(31,new StateManager(31,StateManager.statesNameYanTai[6],56,0,"approveDOA","ordinaryApprove.jsp" ,"PRYanTaiManager"));// Plant总经理
		scheduleYanTaiMap.put(8,new StateManager(8,StateManager.states[9],9,0,"approveFeedBack","feedbackApprove.jsp" ,"PRFinanceControler"));//		回填信息
		scheduleYanTaiMap.put(9,new StateManager(9,StateManager.states[10],10,0,"approve5","PCLApprove.jsp","PRPCL") );//		等待收料部门确认
		scheduleYanTaiMap.put(10,new StateManager(10,StateManager.states[11],11,0,"approve1","BuyerWritePN.jsp" ,"PRBuyer"));//		等待采购员填写订单号
		scheduleYanTaiMap.put(11,new StateManager(11,StateManager.states[12],11,0,"","blank.html","") );//完成	
		//Other
		scheduleYanTaiMap.put(24,new StateManager(24,"培训",1,0,"approve1","PMApprove.jsp","PRTRINING") );//培训
		scheduleYanTaiMap.put(52,new StateManager(52,"总部财务审批",5,0,"approve11","ordinaryApprove.jsp","PRFinanceControlerSH4YT" ));//财务协调员
		scheduleYanTaiMap.put(55,new StateManager(55,"总部财务经理",6,0,"approveDOA","ordinaryApprove.jsp","PRFinanceManagerSH4YT" ));//财务协调员	
		scheduleYanTaiMap.put(6,new StateManager(6,StateManager.states[7],7,0,"approveDOA","ordinaryApprove.jsp","PRDeputyGeneralManager" ));//副总经理审批
		scheduleYanTaiMap.put(7,new StateManager(7,StateManager.states[8],8,0,"approveDOA","ordinaryApprove.jsp","PRManager") );//		总经理审批
		scheduleYanTaiMap.put(56,new StateManager(56,StateManager.SH_CTO_NAME,55,0,"approveDOA","ordinaryApprove.jsp","SH_CTO") );//		SH CTO
		scheduleYanTaiMap.put(58,new StateManager(58,"总部回填信息",9,0,"approve11","feedbackApprove.jsp","PRFinanceControlerSH4YT" ));//总部回填信息
		scheduleYanTaiMap.put(57,new StateManager(57,"总部采购经理",2,0,"approveBuyerManager","buyerManagerApprove.jsp","PRSHBuyerManager" ));//总部采购经理
		scheduleYanTaiMap.put(13,new StateManager(13,StateManager.states[3],5,0,"approve1","ordinaryApprove.jsp","") );//相关部门
		//scheduleYanTaiMap.put(59,new StateManager(59,"上海PCLSAP录入",10,0,"approve5","PCLApprove.jsp","PRSHPCL") );//		等待收料部门确认
		//scheduleYanTaiMap.put(18,new StateManager(18,StateManager.states[6],9,0,"approve5","ordinaryApprove.jsp","PRFinanceManager") );//财务经理2				
		/**----ShangHai Plant DOA----- */
		scheduleSHMap = new HashMap<Integer, StateManager>();	
		scheduleSHMap.put(0,new StateManager(0,StateManager.statesNameYanTai[0],1,0,"approve1","ordinaryApprove.jsp" ,""));// 个人
		scheduleSHMap.put(1,new StateManager(1,StateManager.states[1],19,0,"approveCostCenter","PMApprove.jsp","PRPM") );//部门经理
		scheduleSHMap.put(19,new StateManager(19,"CostCenterOwner",12,0,"approvePmManager","PMApprove.jsp","PRCC") );//CostCenter经理
		scheduleSHMap.put(12,new StateManager(12,"项目经理",4,0,"approve1","ordinaryApprove.jsp","") );//相关部门
		scheduleSHMap.put(4,new StateManager(4,StateManager.states[5],3,0,"approve1","blank.html" ,"PRBuyer"));//采购员确认
		scheduleSHMap.put(3,new StateManager(3,StateManager.states[4],2,0,"approveBuyerManager","buyerManagerApprove.jsp","PRBuyerManager") );//采购经理
		scheduleSHMap.put(2,new StateManager(2,StateManager.states[2],5,0,"approve2","cwControlApprove.jsp","PRFinanceControler" ));//财务协调员
		scheduleSHMap.put(5,new StateManager(5,StateManager.states[6],31,0,"approve1","ordinaryApprove.jsp","PRFinanceManager") );//财务经理
		scheduleSHMap.put(31,new StateManager(31,"上海厂总经理",56,0,"approveDOA","ordinaryApprove.jsp" ,"PRYanTaiManager"));// Plant总经理
		scheduleSHMap.put(8,new StateManager(8,StateManager.states[9],9,0,"approveFeedBack","feedbackApprove.jsp" ,"PRFinanceControler"));//		回填信息
		scheduleSHMap.put(9,new StateManager(9,StateManager.states[10],10,0,"approve5","PCLApprove.jsp","PRPCL") );//		等待收料部门确认
		scheduleSHMap.put(10,new StateManager(10,StateManager.states[11],11,0,"approve1","BuyerWritePN.jsp" ,"PRBuyer"));//		等待采购员填写订单号
		scheduleSHMap.put(11,new StateManager(11,StateManager.states[12],11,0,"","blank.html","") );//完成	
		scheduleSHMap.put(13,new StateManager(13,StateManager.states[3],5,0,"approve1","ordinaryApprove.jsp","") );//相关部门
		//Other
		scheduleSHMap.put(52,new StateManager(52,"总部财务审批",5,0,"zongbuCWApprove","zongbucwControlApprove.jsp","PRFinanceControlerSH4YT" ));//财务协调员
		scheduleSHMap.put(55,new StateManager(55,"总部财务经理",6,0,"approveDOA","ordinaryApprove.jsp","PRFinanceManagerSH4YT" ));//财务协调员	
		scheduleSHMap.put(6,new StateManager(6,StateManager.states[7],7,0,"approveDOA","ordinaryApprove.jsp","PRDeputyGeneralManager" ));//副总经理审批
		scheduleSHMap.put(7,new StateManager(7,StateManager.states[8],8,0,"approveDOA","ordinaryApprove.jsp","PRManager") );//		总经理审批
		scheduleSHMap.put(56,new StateManager(56,StateManager.SH_CTO_NAME,55,0,"approveDOA","ordinaryApprove.jsp","SH_CTO") );//		SH CTO
		scheduleSHMap.put(22,new StateManager(22,"LAB",1,0,"approve1","PMApprove.jsp","PRLAB") );//LAB部门经理
		scheduleSHMap.put(24,new StateManager(24,"培训",1,0,"approve1","PMApprove.jsp","PRTRINING") );//培训
		scheduleSHMap.put(18,new StateManager(18,StateManager.states[6],9,0,"approve5","ordinaryApprove.jsp","PRFinanceManager") );//财务经理2
		scheduleSHMap.put(58,new StateManager(58,"总部回填信息",9,0,"approve11","feedbackApprove.jsp","PRFinanceControlerSH4YT" ));//总部回填信息
		scheduleSHMap.put(16,new StateManager(16,"OST经理",4,0,"approveOTSManager","ordinaryApprove.jsp","") );//OTS经理
		scheduleSHMap.put(33,new StateManager(33,StateManager.states[3],31,0,"approve11","ordinaryApprove.jsp","PRrelated") );//相关部门2
		scheduleSHMap.put(57,new StateManager(57,"总部采购经理",2,0,"approveBuyerManager","buyerManagerApprove.jsp","PRSHBuyerManager" ));//总部采购经理
	}
	
	public void initScheduleMaptoSY(){
		
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
	
	public StateManager getSYStateManager(){
		return scheduleSYMap.get(state);
	}	
	
	public StateManager getYanTaiStateManager() {
		
		return scheduleYanTaiMap.get(state);
	}
	
	public StateManager getSHStateManager() {
		
		return scheduleSHMap.get(state);
	}
	
	public PrPrForm(String id) {
		this.id=id;
	}
	
	public PrPrForm(String id, String tempolyeeByBuyerId) {
		this.id = id;
		this.tempolyeeByBuyerId = tempolyeeByBuyerId;
	}
	
	/** minimal constructor */
	public PrPrForm(String id, Tempolyee tempolyeeByApplicantId,
			PrCostCenter prCostCenter,
			Date applicantDate, Float total, Integer state) {
		this.id = id;
		this.tempolyeeByApplicantId = tempolyeeByApplicantId;
		this.prCostCenter = prCostCenter;
		this.applicantDate = applicantDate;
		this.total = total;
		this.state = state;
	}

	/** full constructor */
	public PrPrForm(String id, Tempolyee tempolyeeByApplicantId,
			PrCostCenter prCostCenter, 
			Date applicantDate, String prNo, String type,
			Float total, String recommendedSupplier,
			String receivingPlacePerpoleTel, Integer state, String fileName,
			Set prPrFormStates, List prApprovedForms, List prBuyContexts,PrProject project) {
		this.id = id;
		this.tempolyeeByApplicantId = tempolyeeByApplicantId;
		this.prCostCenter = prCostCenter;
		this.applicantDate = applicantDate;
		this.prNo = prNo;
	
		this.type = type;
		this.total = total;
		this.recommendedSupplier = recommendedSupplier;
		this.receivingPlacePerpoleTel = receivingPlacePerpoleTel;
		this.state = state;
		this.fileName = fileName;
		this.prPrFormStates = prPrFormStates;
		this.prApprovedForms = prApprovedForms;
		this.prBuyContexts = prBuyContexts;
		this.prProject = project;
	}

	// Property accessors
	
	public String getIsCapitalName(){
		if(isCapital!=null){
			if(isCapital==0){
				return "Capital";
			}else{
				return "Expense";
			}
		}return "";
	}
	
	public String getIsPlanName(){
		if(isPlan!=null){
			if(isPlan==0){
				return "计划内";
			}else{
				return "计划外";
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

	public Tempolyee getTempolyeeByApplicantId() {
		return this.tempolyeeByApplicantId;
	}

	public void setTempolyeeByApplicantId(Tempolyee tempolyeeByApplicantId) {
		this.tempolyeeByApplicantId = tempolyeeByApplicantId;
	}

	public PrCostCenter getPrCostCenter() {
		return this.prCostCenter;
	}

	public void setPrCostCenter(PrCostCenter prCostCenter) {
		this.prCostCenter = prCostCenter;
	}

	public String getTempolyeeByBuyerId() {
		return tempolyeeByBuyerId;
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

	public Float getTotal() {
		   return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

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
	//--bug
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
		if(scheduleMap.get(this.state)!=null){
			return scheduleMap.get(this.state).curStateName;
		}else{
			return state+"";
		}
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

	public PrProject getPrProject() {
		return prProject;
	}

	public void setPrProject(PrProject prProject) {
		if(prProject.getId()==null || prProject.getId()==0){
			prProject.setId(1);
		}
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

	public Float getTotalE() {
		//return totalE;
		float totalCost=0f;
		   for(PrBuyContext elem: prBuyContexts)
		    {	   
		    	if(elem.getUnitPriceE()!=null&&elem.getQuantity()!=null){
		    		 totalCost+=elem.getUnitPriceE()*elem.getQuantity();
		    	}		  
		    }
		   return totalCost;
	}

	public void setTotalE(Float totalE) {
		this.totalE = BigDecimal.valueOf(totalE);
	}

	public String getIsInTheSapName() {
		if(isInTheSap!=null && isInTheSap==1){
			return "是";
		}else {
			return "否";
		}
	}

	public String getArno() {
		return arno;
	}

	public void setArno(String arno) {
		this.arno = arno;
	}

	public Integer getInfo() {
		return info;
	}

	public void setInfo(Integer info) {
		this.info = info;
	}

	public String getQuertState() {
		return quertState;
	}

	public void setQuertState(String quertState) {
		this.quertState = quertState;
	}

	public String getBuyerfileName() {
		return buyerfileName;
	}

	public void setBuyerfileName(String buyerfileName) {
		this.buyerfileName = buyerfileName;
	}

	public String getQuertState2() {
		return quertState2;
	}

	public void setQuertState2(String quertState2) {
		this.quertState2 = quertState2;
	}

	public int getSsid() {
		return ssid;
	}
	
	/**
	 * 计算ssid,累加前几年
	 * @param ssid
	 * @return
	 */
	public int getASSID(int ssid) {
		int temp=0;
		if(ssid/10000==10) {
			temp = ssid%10000;
		} else if (ssid/10000 == 11) {
			temp = 963+ ssid%10000;
		}else if(ssid/10000 == 12){			
			temp = 5273+ ssid%10000;
		}else if(ssid/10000 == 13){			
			temp = 9775+ ssid%10000;
		}else if(ssid/10000 == 14){			
			temp = 15276+ ssid%10000;
		}else if(ssid/10000 == 15){			
			temp = 21498+ ssid%10000;
		}else if(ssid/10000 == 16){			
			temp = 28264+ ssid%10000;
		}
		return temp;
	}

	public void setSsid(int ssid) {
		this.ssid = ssid;
	}

	
	private int sapNO;
	
	/**
	 * update this method should sync EprExcel4Finance.getSapNO manually.
	 * @return
	 */
	public int getSapNO() {
		int newSsid = ssid;
		int tenYearTotal = 963;
		int elYearTotal = 5273;
		int TWYearTotal = 9775;
		int THYearTotal = 15276;
		int FourthTotal = 21498;
		int FiveTotal = 28264;
		if(id != null && !"".equals(id)) {
			int a = Integer.parseInt(this.id.substring(0, 2));	
			if(a == 11) {
				newSsid = ssid - tenYearTotal;
			}else if(a == 12){
				newSsid = ssid - elYearTotal;
			}
			else if(a == 13){
				newSsid = ssid - TWYearTotal;
			}else if(a == 14){
				newSsid = ssid - THYearTotal;
			}else if(a == 15){
				newSsid = ssid - FourthTotal;
			}else if(a == 16){
				newSsid = ssid - FiveTotal;
			}
			return a*100000000+newSsid;
		}else {
			return 0;
		}
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getTempApplicantDate() {
		return tempApplicantDate;
	}

	public void setTempApplicantDate(String tempApplicantDate) {
		this.tempApplicantDate = tempApplicantDate;
	}

	public String getWorkshopToUse() {
		return workshopToUse;
	}

	public void setWorkshopToUse(String workshopToUse) {
		this.workshopToUse = workshopToUse;
	}

	public String getFacilityToUse() {
		return facilityToUse;
	}

	public void setFacilityToUse(String facilityToUse) {
		this.facilityToUse = facilityToUse;
	}

	public String getDepartments() {
		return departments;
	}

	public void setDepartments(String departments) {
		this.departments = departments;
	}

	public String getIsOtherCostCenter() {
		return isOtherCostCenter;
	}

	public void setIsOtherCostCenter(String isOtherCostCenter) {
		this.isOtherCostCenter = isOtherCostCenter;
	}

	public String getSourceRange() {
		return sourceRange;
	}

	public void setSourceRange(String sourceRange) {
		this.sourceRange = sourceRange;
	}

	public List<Paydetails> getPaydetails() {
		return paydetails;
	}

	public void setPaydetails(List<Paydetails> paydetails) {
		this.paydetails = paydetails;
	}

	public String getRelateuid() {
		return relateuid;
	}

	public void setRelateuid(String relateuid) {
		this.relateuid = relateuid;
	}

	public Integer getLastState() {
		return lastState;
	}

	public void setLastState(Integer lastState) {
		this.lastState = lastState;
	}

	public boolean getIS_CHANGED() {
		return IS_CHANGED;
	}

	public void setIS_CHANGED(boolean is_changed) {
		IS_CHANGED = is_changed;
	}

	public Integer getIsUrgency() {
		return isUrgency;
	}

	public void setIsUrgency(Integer isUrgency) {
		this.isUrgency = isUrgency;
	}

	public Integer getIsUrgencyB() {
		return isUrgencyB;
	}

	public void setIsUrgencyB(Integer isUrgencyB) {
		this.isUrgencyB = isUrgencyB;
	}

	public Integer getIsAssignation() {
		return isAssignation;
	}

	public void setIsAssignation(Integer isAssignation) {
		this.isAssignation = isAssignation;
	}

	public Integer getIsAssignationB() {
		return isAssignationB;
	}

	public void setIsAssignationB(Integer isAssignationB) {
		this.isAssignationB = isAssignationB;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getNewStateName() {
		return newStateName;
	}

	public void setNewStateName(String newStateName) {
		this.newStateName = newStateName;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getCapitalcategroy() {
		return capitalcategroy;
	}

	public void setCapitalcategroy(String capitalcategroy) {
		this.capitalcategroy = capitalcategroy;
	}

	public String getPm() {
		return pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}

	public String getCcowner() {
		return ccowner;
	}

	public void setCcowner(String ccowner) {
		this.ccowner = ccowner;
	}

	public String getIssueFor() {
		return issueFor;
	}

	public void setIssueFor(String issueFor) {
		this.issueFor = issueFor;
	}

	public String getFileUrgency() {
		return fileUrgency;
	}

	public void setFileUrgency(String fileUrgency) {
		this.fileUrgency = fileUrgency;
	}

	public String getFileAssignation() {
		return fileAssignation;
	}

	public void setFileAssignation(String fileAssignation) {
		this.fileAssignation = fileAssignation;
	}

	public String getBuyerNetid() {
		return buyerNetid;
	}

	public void setBuyerNetid(String buyerNetid) {
		this.buyerNetid = buyerNetid;
	}

	public String getBigcountFile() {
		return bigcountFile;
	}

	public void setBigcountFile(String bigcountFile) {
		this.bigcountFile = bigcountFile;
	}

	public String getUrgentFile() {
		return urgentFile;
	}

	public void setUrgentFile(String urgentFile) {
		this.urgentFile = urgentFile;
	}

	public String getSinglesourceFile() {
		return singlesourceFile;
	}

	public void setSinglesourceFile(String singlesourceFile) {
		this.singlesourceFile = singlesourceFile;
	}

	public String getFormURL() {
		return formURL;
	}

	public void setFormURL(String formURL) {
		this.formURL = formURL;
	}

	public String getBuyer1() {
		return buyer1;
	}

	public void setBuyer1(String buyer1) {
		this.buyer1 = buyer1;
	}

	public String getBuyer2() {
		return buyer2;
	}

	public void setBuyer2(String buyer2) {
		this.buyer2 = buyer2;
	}

	public String getBuyerFile() {
		return buyerFile;
	}

	public void setBuyerFile(String buyerFile) {
		this.buyerFile = buyerFile;
	}

	public String getShowDate() {
		return showDate;
	}

	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}
}