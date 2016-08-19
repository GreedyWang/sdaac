package com.sdaac.common.org;

import java.util.List;
import sdaac.wym.common.Service.RoleManager;
import app.biz.EmpBiz;
import app.entity.Tempolyee;
import app.entity.epor.PrPrForm;
import com.sdaac.pr.Constant;
import com.sdaac.pr.decision.AbstractDecisionHandler;

import common.entity.TuserRole;

public class OrgManager {
	
	private String[] rolenames;
	private EmpBiz empBiz;
	private RoleManager rm;
	public static String blankOwner = "pzbwv3";
	
	public OrgManager(){}
	
	/**
	 * get DM
	 * @return
	 */
	private String getDMNetId(int dptId){
		String netid = empBiz.doSelectManagerBydepartId2(dptId);
		if(netid!=null && !netid.equals("")){
			return netid;
		}else{
			return blankOwner;
		}
	}
	
	public String[] getRoles(PrPrForm form){
		String capitalType = form.getCapitalcategroy();
		String isCapital;
		if(form.getIsCapital() == null){
			isCapital = Constant.iscapital;
			form.setIsCapital(Integer.parseInt(isCapital));
		}else{
			isCapital = form.getIsCapital().toString();
		}

		int plant = form.getSource();
		int dptid = form.getTempolyeeByApplicantId().getTdepartment().getId();
		rolenames = new String[20];
		
		rolenames[DPRT] = getDMNetId(dptid);
		rolenames[CapitalOwner] = getCapitalOwner(capitalType,plant);
		rolenames[DMSupervisor] = getDMSupervisor(dptid, plant);
		rolenames[PLANTMgr] = getPlantMgr(plant);
		//rolenames[Cost_Center_Onwer] = ;
		rolenames[BuyerMgr] = getRoleByRole(plant,Constant.PRBuyerManager);
		rolenames[Fin_Check] = getFinByRole(plant,Constant.PRFinanceControler,isCapital);
		rolenames[PCL_Check] = getRoleByRole(form.getIssueFor()==null?plant:Integer.parseInt(form.getIssueFor()),Constant.PRPCL);
		rolenames[Buyer_Check] = form.getBuyerNetid();
		rolenames[FINMGR] = getFinMgr(plant,isCapital);
		rolenames[GM] = getRoleByRole(plant,Constant.PRManager);
		rolenames[THEMRL] = getRoleByRole(plant,Constant.PRDSB);
		
		rolenames[DGM] = getDMG(plant,Constant.PRDeputyGeneralManager,isCapital,dptid,form.getTotal());
		
		rolenames[OrgManager.FIN_LV1] = getFinLv1(form.getTotal(),plant);
		rolenames[OrgManager.finNextLv] = getFinNextLv(form.getTotal(),plant);
		
		return rolenames;
	}
	
	/**
	 * get level finance approver
	 * @return
	 */
	private String getFinNextLv(Float totalCost,int plant ){
		String role = Constant.FinNextLv;
		return getRoleByRole(plant,role);
	}
	
	/**
	 * get level finance approver
	 * @return
	 */
	private String getFinLv1(Float totalCost,int plant ){
		//if(totalCost >= 1000000){
		String role = Constant.PRFinanceLvl1;
		return getRoleByRole(plant,role);
		//}else{
		//	return blankOwner;
		//}
	}
	
	private String getFinMgr(int plant,String capitalType) {
		// TODO Auto-generated method stub
		String role = Constant.PRFinanceManager;
		if(capitalType.equals(Constant.iscapital)){
			plant = 1;
			role = Constant.PRFinMgrCapital;
		}
		return getRoleByRole(plant,role);
	}

	private String getRoleByRole(int plant,String role){		
		List<TuserRole> rolename =  rm.doSelectByRoleNameNPlant(role,plant);
		if(!rolename.isEmpty()){
			return rolename.get(0).getTempolyee().getNetid();
		}else{
			return blankOwner;
		}
	}

	private String getFinByRole(int plant,String role,String capitalType){	
		if(capitalType.equals(Constant.iscapital)){
			role = Constant.PRFinanceControlerCaptial;
		}
		
		List<TuserRole> rolename =  rm.doSelectByRoleNameNPlant(role,plant);
		if(!rolename.isEmpty()){
			return rolename.get(0).getTempolyee().getNetid();
		}else{
			return blankOwner;
		}
	}
	
	private String getRoleByRole(int plant, String role, String capitalType, int dptid) {
		// TODO Auto-generated method stub
		List<TuserRole> rolename =  rm.doSelectByAll(role,plant,capitalType,dptid);
		if(!rolename.isEmpty()){
			return rolename.get(0).getTempolyee().getNetid();
		}else{
			return blankOwner;
		}
	}
	
	/**
	 * 查找副总,
	 *	////DEL如果是arn 小于10,000 替换成李刚
	 * @param plant
	 * @param role
	 * @param capitalType
	 * @param dptid
	 * @param total
	 * @return
	 */
	private String getDMG(int plant, String role, String capitalType, int dptid,float total){
		String rolename = getRoleByRole(plant,Constant.PRDeputyGeneralManager,capitalType,dptid);
		//todo: @date 2016/06/04 @desc 新需求授权，带条件，可以多人的，支持条件语法树。 
		if( rolename.equalsIgnoreCase(AbstractDecisionHandler.DMG) && total <= 10000 && capitalType.equals(Constant.EXPENSE)){ //rolename.equals(AbstractDecisionHandler.DMG) &&
			rolename = "HZQ85D";
		}
		return rolename;
	}
	
	public String getRoleName(int i){
		if(rolenames!=null){
			return rolenames[i];
		}else{
			return "init fisrt";
		}
	}
	
	/***
	 * 工厂经理
	 * @return
	 */
	private String getPlantMgr(int plant){
		String role ="PRYanTaiManager";
		List<TuserRole> rolename =  rm.doSelectByRoleNameNPlant(role,plant);
		if(!rolename.isEmpty()){
			return rolename.get(0).getTempolyee().getNetid();
		}else{
			return blankOwner;
		}
	}
	

	
	/**
	 * 部门经理的主管
	 * @param dptid
	 * @return
	 */
	private String getDMSupervisor(int dptid,int plant){
		String role ="prdmsupervisor";
		List<TuserRole> rolename =  rm.doSelectByRoleNameNPlantNDprt(role,plant,dptid);
		if(!rolename.isEmpty()){
			return rolename.get(0).getTempolyee().getNetid();
		}else{
			return blankOwner;
		}
	}
	
	/**
	 * 资本化负责人
	 * @param type
	 * @return
	 */
	private String getCapitalOwner(String type,int plant){
		String role = "Na";
		if(type!=null && type.equals("2")){
			role = "prcapitalownerptc";
		}else if(type!=null && type.equals("1")){
			role = "prcapitalownerhvac";
		}else{
			return blankOwner;
		}
		List<TuserRole> rolename = rm.doSelectByRoleName(role);
		if(!rolename.isEmpty()){
			return rolename.get(0).getTempolyee().getNetid();
		}else{
			return blankOwner;
		}
	}
	
	public static int FIN = 0;
	public static int FINMGR = 1;
	public static int ENG = 2;
	public static int DGM = 3;
	public static int GM = 4;
	public static int THEMRL = 5;
	public static int DPRT = 6;
	public static int CapitalOwner = 7;
	public static int DMSupervisor = 8;
	public static int PLANTMgr = 9;
	public static int BuyerMgr = 10;
	public static int Buyer_Check = 13;
	public static int PCL_Check = 12;
	public static int Fin_Check = 11;
	public static int Cost_Center_Onwer = 14;
	public static int FIN_LV1 = 15;
	public static int finNextLv = 16;
	
	public EmpBiz getEmpBiz() {
		return empBiz;
	}

	public void setEmpBiz(EmpBiz empBiz) {
		this.empBiz = empBiz;
	}

	public RoleManager getRm() {
		return rm;
	}

	public void setRm(RoleManager rm) {
		this.rm = rm;
	}
}


