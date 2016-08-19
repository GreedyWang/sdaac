package sdaac.wym.common.Service;

import java.util.ArrayList;
import java.util.List;

import app.biz.impl.epor.FormService;
import app.entity.Tdepartment;
import app.entity.Tempolyee;
import app.entity.epor.PrCostCenter;
import app.entity.epor.PrPrForm;
import app.entity.epor.PrProject;
import app.entity.epor.StateManager;
import common.entity.Tfunction;
import common.entity.Trole;
import common.entity.TuserRole;
/**
 * PR权限控制类
 * @author SA1KV5
 *
 */
public class PrRightsManager {
	
	private RoleManager roleManager;
	
	public List<TuserRole> doSelectByRoleName(String roleName,Integer departId){
		if(departId!=null){
			return roleManager.doSelectByRoleName(roleName, departId);
		}else{
			return roleManager.doSelectByRoleName(roleName);
		}
	}
	
	public List<TuserRole> doSelectByRoleName(String roleName){
		return roleManager.doSelectByRoleName(roleName);
	}
	/**
	 * 检查权限
	 * @param emp
	 * @param item
	 */
	public void checkRight(Tempolyee emp,PrPrForm item) {
		if (roleManager.hasRight(new Tfunction("selectAll"),
				emp)) {
//			if(emp.getUid().equals(StateManager.U_DGM2)){ //李总
//				item.setDepartments(StateManager.DGM2);
//			}else if(emp.getUid().equals(StateManager.U_DGM1)){//马琦雷
//				item.setDepartments(StateManager.DGM1);
//			}else if(emp.getUid().equals(StateManager.U_CIO)){//董国平
//				item.setDepartments(StateManager.CIO);
//			}else if(emp.getUid().equals(StateManager.U_CIO2)){//董国平
//				item.setDepartments(StateManager.CIO2);
//			}
			item.setDepartments(emp.getManagedms());
		} else if (roleManager.hasRight(new Tfunction("selectBuyer"),
				emp)) {
			item.setTempolyeeByBuyerId(emp.getName());
		} else if (roleManager.hasRight(
				new Tfunction("selectDepart"), emp)) {
			item.getTempolyeeByApplicantId().setTdepartment(
					emp.getTdepartment());
		} else if (roleManager.hasRight(
				new Tfunction("selectSelfApporve"), emp)) {

		} else if(roleManager.hasRight(new Tfunction("selectProject"), emp)){
				//item.setQuertState(StateManager.Realtion+"");
			PrProject p = new PrProject();
			p.setId(-1);
			p.setManagerUid(emp.getUid());			
			item.setPrProject(p);
		}else {
			item.setTempolyeeByApplicantId(emp);
		}
	}
	
	public void checkRights(String roleName,Tempolyee emp, PrPrForm item) {
		
			String quertState = "88";
			if (roleName.equals("PRDeputyGeneralManager")) {
				quertState += "," + StateManager.DeputyGeneral;
//				if(emp.getUid().equals(StateManager.U_DGM2)){ //李总
//					item.setDepartments(StateManager.DGM2);
//				}else if(emp.getUid().equals(StateManager.U_DGM1)){//马琦雷
//					item.setDepartments(StateManager.DGM1);
//				}else if(emp.getUid().equals(StateManager.U_CIO)){//li Gang
//					item.setDepartments(StateManager.CIO);
//				}else if(emp.getUid().equals(StateManager.U_CIO2)){//Anr
//					item.setDepartments(StateManager.CIO2);
//				}else if(emp.getUid().equals(StateManager.U_DGM3)){//Anr
//					item.setDepartments(StateManager.DGM3);
//				}
				item.setDepartments(emp.getManagedms());
				
			}
			if (roleName.equals("PROTSManager")) {
				quertState += "," + StateManager.OTS;
			}
			if (roleName.equals("PRSHBuyerManager")) {
				quertState += "," + 57;
			}
			if (roleName.equals("PRYanTaiManager")) {
				quertState += "," + StateManager.YTGM;
			}
			if (roleName.equals("PRManager")) {
				quertState += "," + StateManager.PRManager;

			}
			if (roleName.equals("PRLAB")) {
				quertState += "," + StateManager.LAB;

			}
			if (roleName.equals("PRSH_CTO")) {
				quertState += "," + StateManager.SH_CTO;

			}
			//项目经理
			if(roleName.equals("PRProjectManager")){
				quertState += "," + StateManager.Realtion;				
				item.setRemark(emp.getUid());
			}
			if(roleName.equals("PRrelated")){
				quertState += "," + StateManager.Realtion3;				
				item.setRelateuid(emp.getUid());
			}
			if (roleName.equals("PRTRINING")) {
				quertState += "," + 24;

			}
			if (roleName.equals("PROCC")) {
				quertState += "," + StateManager.OCC;

			}
			if (roleName.equals("PRDeputyGeneralManager2")) {
				quertState += "," + StateManager.PLANTDeputyGeneralManager;

			}
			if (roleName.equals("PRFinanceManagerSH4YT")) {
				quertState += "," + 55;

			}
			if (roleName.equals("PRFinanceControlerSH4YT")) {
				quertState += ",58," + 52;

			}
			if (roleName.equals("PRSHPCL")) {
				quertState += "," + 59;

			}
			if (roleName.equals("")) {
				quertState += "," + StateManager.SH_CTO;

			}
			if(roleName.equals("PRSYManager")){
				quertState += "," + 14;
			}
			if (roleName.equals("PRBuyer")) {	
					item.setTempolyeeByBuyerId(emp.getName());
					quertState += "," + StateManager.BuyConfig + ","
							+ StateManager.PRNO;
			}
			if (roleName.equals("PRBuyerManager")) {
				quertState += "," + StateManager.BuyManager;
			}
				
				if (roleName.equals("PRFinanceControler")) {
					quertState += "," + StateManager.CWCONTROL ;
				}
				//财务成本中心管理员
				if (roleName.equals("PRFinanceControlerCaptial")) {
					quertState += "," + StateManager.CWCONTROLCaptial;
				}
				//回填信息费用化
				if (roleName.equals("PRFinanceFeedbackExpense")) {
					quertState += "," + StateManager.FEEDBACK;
					//add isCaptial condition
					item.setIsCapital(1);
				}
				//回填信息资本化
				if (roleName.equals("PRFinanceFeedbackCaptial")) {
					quertState += "," + StateManager.FEEDBACK;
					//add isCaptial condition
					item.setIsCapital(0);
				}
//				//财务控制
//				if (roleName.equals("PRFinanceControlerWeak")) {
//					quertState += "," + StateManager.CWCONTROL ;
//				}
	
				if (roleName.equals("PRFinanceManager")) {
					quertState += "," + StateManager.FinanceManager + "," + 18;
				}
				if (roleName.equals("PRPCL")) {
					quertState += "," + StateManager.PCL;
				}
				if (roleName.equals("PRDSB")) {
					quertState += "," + StateManager.KK;
				}
				if (roleName.equals("PRPM")) {
					quertState += "," + StateManager.DEPART;
					item.getTempolyeeByApplicantId().setTdepartment(
							emp.getTdepartment());
					
					
				}
				if (roleName.equals("PRSupervisor")) {
					quertState += "," + StateManager.SUPERVISOR;
					item.getTempolyeeByApplicantId().setTdepartment(
							emp.getTdepartment());
					item.getTempolyeeByApplicantId().setSupervisor(emp.getUid());
					
				}
				if (roleName.equals("PRCC")) {
					quertState += "," + 19;
					PrCostCenter prCostCenter = new PrCostCenter();
					prCostCenter.setOwner(emp.getUid());
					item.setPrCostCenter(prCostCenter);

				}
				if (quertState.length() > 2) {
					item.setQuertState(quertState);
				} else {
					item.setState(88);
				}

			}

	/**
	 * 检查权限
	 * @param emp
	 * @param item	
	 */
	public int checkRight2(Tempolyee emp,PrPrForm item) {
		String quertState="88";
		int key = 1;
		if (roleManager.hasRight(new Trole("PRDeputyGeneralManager"),
				emp)) {
			quertState += ","+StateManager.DeputyGeneral;
		}  if (roleManager.hasRight(new Trole("PRManager"),
				emp)) {
			quertState += ","+StateManager.PRManager;
		
		} if (roleManager.hasRight(new Trole("PRBuyer"),
				emp)) {
			if (roleManager.hasRight(new Trole("PRBuyerManager"),
					emp)) {
				quertState += ","+StateManager.BuyManager;
				key = 2;
			} else{
				item.setTempolyeeByBuyerId(emp.getName());
				quertState += ","+StateManager.BuyConfig+","+StateManager.PRNO;
			}
		} 

		if (roleManager.hasRight(new Trole("PRFinanceControler"),
				emp)) {
			quertState += ","+StateManager.CWCONTROL+","+StateManager.FEEDBACK;
		} if (roleManager.hasRight(new Trole("PRFinanceManager"),
				emp)) {
			quertState += ","+StateManager.FinanceManager;

		} if (roleManager.hasRight(new Trole("PRPCL"),
				emp)) {
			quertState += ","+StateManager.PCL;

		}
		if (roleManager.hasRight(new Trole("PRPM"),
				emp)) {
			quertState += ","+StateManager.DEPART;
			item.getTempolyeeByApplicantId().setTdepartment(
					emp.getTdepartment());
		}
		if(quertState.length()>2){
			item.setQuertState(quertState);
		}
		else {
			item.setState(88);
		}
		return key;
	}
	
	public void checkRight2Other(Tempolyee emp,PrPrForm item){
		String quertState="88";
		if (roleManager.hasRight(new Trole("PRBuyer"),
				emp)) {
			if (roleManager.hasRight(new Trole("PRBuyerManager"),
					emp)) {
				item.setTempolyeeByBuyerId(emp.getName());
				quertState += ","+StateManager.BuyConfig+","+StateManager.PRNO;
				item.setQuertState(quertState);
			} 

		} 
	}
	
	
	
	public List<String> checkNprRights4All(List<String> roleNames){
		List<String> r = new ArrayList<String>();
		String n = "";
		if(roleNames == null) return r;
		if(roleNames.size() == 1){
			n = roleNames.get(0);
		}
				
		for (String roleName : roleNames) {		
			if(roleName.equals("PRDeputyGeneralManager")
					||roleName.equals("PRDSB")
					||roleName.equals("PRManager")){
				n = "PRManager";
			}
		}
		
		for (String roleName : roleNames) {		
			if (roleName.equals("PRYanTaiManager")
					|| roleName.equals("PRPCL")
					|| roleName.equals("PRFinanceManager")) {
				n = "PRYanTaiManager";
			}
		}
		if(!n.equals("")){
			r.add(n);
		}
		return r;
	}
	
	public int checkNprRights(String roleName,Tempolyee emp, PrPrForm item) {
		
//		//String quertState = "88";
//		if (roleName.equals("PRDeputyGeneralManager")) {
//			//item.setDepartments(emp.getManagedms());
//			
//		}
		item.getPrCostCenter().setOwner(null);
		int rs = 1;
		if (roleName.equals("PRYanTaiManager") || roleName.equals("PRPCL")
				//|| roleName.equals("PRFinanceManager")
				|| roleName.equals("PRFinanceManager")) {
			if(emp.getEprownerarea()!=null && emp.getEprownerarea().length()==1)
			item.setSource(Integer.parseInt(emp.getEprownerarea()));
		}else if(roleName.equals("PRInterAudit")){
			if(emp.getEprownerarea()!=null ){				
				item.setSourceRange(emp.getEprownerarea());			
			}
		}else if (roleName.equals("PRFinanceControlerCaptial")) {
			item.setIsCapital(0);
		}else if (roleName.equals("PRFinanceControler")) {
			if(emp.getEprownerarea()!=null && emp.getEprownerarea().length()==1){
				item.setSource(Integer.parseInt(emp.getEprownerarea()));
			}
			item.setIsCapital(1);
		}else if (roleName.equals("prcapitalownerhvac")) {
			item.setCapitalcategroy("1");

		}else if (roleName.equals("prcapitalownerptc")) {
			item.setCapitalcategroy("2");

		}else if (roleName.equals("prdmsupervisor")) {
			item.setDepartments(emp.getManagedms());
		}else if (roleName.equals("PRPM")) {
			//item.setDepartments(emp.getManagedms());
			Tempolyee newemp = new Tempolyee();
			newemp.setTdepartment(emp.getTdepartment());
			item.setTempolyeeByApplicantId(newemp);
		}else if (roleName.equals("PRProjectManager")) {
			PrProject p = new PrProject();
			p.setId(-2);
			p.setManagerUid(emp.getUid());			
			item.setPrProject(p);
		}else if (roleName.equals("PRCC")) {
			PrCostCenter prCostCenter = new PrCostCenter();
			prCostCenter.setOwner(emp.getUid());
			item.setPrCostCenter(prCostCenter);
		}else if (roleName.equals("PRBuyer")) {
			item.setTempolyeeByBuyerId(emp.getUid());
		}else if (roleName.equals("PRManager")) {
			//item.setTempolyeeByBuyerId(emp.getUid());
		}else{
			rs=0;
		}
		return rs;
//		else{
//			Tempolyee newemp = new Tempolyee();
//			newemp.setUid("na");
//			item.setTempolyeeByApplicantId(newemp);
//			//item.setTotal(-1f);
//		}
		
		//DepartmentBizImpl.selectByManagerId()
	}
	
	
	public RoleManager getRoleManager() {
		return roleManager;
	}

	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}
}
