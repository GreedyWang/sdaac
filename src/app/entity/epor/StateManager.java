package app.entity.epor;

public class StateManager {
	public static String[] states = {"个人","部门","财务","相关部门","采购经理","采购员确认","财务经理审批","副总经理审批","总经理审批","回填信息","PCLSAP录入","等待采购员填写订单号","完成","删除","沈阳总经理","申请人填写到货日期"};
	public static String[] statesName = {"个人","部门","财务","相关部门","采购经理","采购员确认","财务经理审批","副总经理审批","总经理审批","回填信息","PCLSAP录入","等待采购员填写订单号","完成","删除","沈阳总经理"};
	//YanTai process statues name
	public static String[] statesNameYanTai = {"个人","部门","采购员","采购经理","财务","财务经理","工厂总经理","SDAAC总经理","回填信息","PCLSAP录入","等待采购员填写订单号","完成","删除"};
	
	public static String ITManager = "5003";
	public static int SUPERVISOR=28;
	public static int DELETE=-1;
	public static int SELF=0;
	public static int DEPART=1;
	public static int CWCONTROL=2;
	public static int CWCONTROLCaptial=42;
	public static int BuyManager=3;
	public static int BuyConfig=4;
	public static int FinanceManager=5;
	public static int Realtion=12;
	public static int Realtion3=33;
	public static int Realtion2=13;
	public static int NoRelation=-9;
	public static int FINISH=11;
	public static int OTS=16;
	public static int DeputyGeneral=6;
	public static int PCL=9;
	public static int PRNO=10;
	public static int PRManager=7;
	public static int FEEDBACK=8;
	public static int KK=17;
	public static int LAB=22;
	public static int OCC=23;
	public static int TRINING=24;
	public static int YTGM=31;
	public static int SYGM=14;
	public static int SDAACGM=7;
	public static int PLANTDeputyGeneralManager=15;
	public static int SH_FINANCE = 52;
	public static int SH_FINANCE_MANAGER = 55;
	public static int SH_CTO = 56;
	public static int SH_FEEDBACK = 58;
	public static int CostCenter = 19;
	public static int[] FINISHDOA={8,7};
	public String curStateName;
	public int curState;
	public int nextState;
	public int failureState;
	public String condition;
	public String destination;
	public String role;
	
	public static String SH_CTO_NAME = "总部技术总监";
	
	public static String CIO = "10046,10058"; //技术总监负责部门(IT<LAB)
	public static String CIO2 = "10040,10044"; //技术总监负责部门
	public static String DGM1 = "10047,10049,10031,10048,10088"; //副总——马 负责部门
	public static String DGM2 = "others-10036,10047,10049,10031,10048,10040,10044,10046,10058,10088"; //副总——李 负责部门
	public static String DGM3 = "10057,10036,10067,10068,10069,10070,10071,10072,10073,10074,10075,10076,10077,10078,10079,10080,10081,10082,10083,10084,10085,10086,10087";
	
	public static String U_CIO = "0107"; //技术总监负责部门4IT LAB
	public static String U_CIO2 = "1628"; //技术总监负责部门4 PE ME
	public static String U_DGM1 = "1367"; //副总——马 负责部门
	public static String U_DGM2 = "1227"; //副总——李 负责部门
	public static String U_DGM3 = "9013"; //副总——蔡 负责部门
	public static String U_DGM4 = "9015"; //副总——李2 负责部门
	/**
	 * 
	 * @param curState 
	 * @param curStateName
	 * @param nextState
	 * @param failureState
	 * @param condition
	 * @param destination
	 * @param role
	 */
	public StateManager(int curState,String curStateName, int nextState, int failureState,
			String condition,String destination,String role) {
		super();
		this.curState = curState;
		this.curStateName=curStateName;
		this.nextState = nextState;
		this.failureState = failureState;
		this.condition = condition;
		this.role=role;
		this.destination = destination;
	}	
	
	public static int getStateBySuggestionType(String type) {
		if(type.equals("部门")) return DEPART;
		else if(type.equals("采购员")) return BuyConfig;
		else if(type.equals("采购经理")) return BuyManager;
		else if(type.equals("财务")) return CWCONTROL;
		else if(type.equals("财务经理审批")) return FinanceManager;
		else if(type.equals("副总经理审批")) return DeputyGeneral;
		else if(type.equals("总经理审批")) return PRManager;
		else if(type.equals("回填信息")) return FEEDBACK;
		else if(type.equals("等待采购员填写订单号")) return PRNO;
		else if(type.equals("PCLSAP录入")) return PCL;
		else return 0;
	}
	
	public static boolean isRealtion(int state){
		return state==Realtion||state==Realtion2;
	}
	
	public static boolean isBuyer(int state){
		return BuyConfig==state;
	}
}
