package com.sdaac.pr.decision;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

import com.sdaac.common.org.OrgManager;
import com.sdaac.pr.approve.element.ApproveMembersElement;
import com.sdaac.pr.approve.element.ElementHelper;
import com.sdaac.pr.approve.element.FormControlElement;

public class IsCapitalDecisionForDMSupervisorHandler implements DecisionHandler {
	
	private static String ISCAPITAL = "0";
	private static String TO_CAPITAL_CHECK ="to capital_check";
	//private static String TO_DPTMGRS_SUPERVISOR ="to dpt Mgrs supervisor";
	private static String TO_PM ="to pm";
	private static String TO_CC="to cost_center_owner";
	private static String TO_buyerMgr = "to fin";
	@Override
	public String decide(OpenExecution execution) {
		// TODO Auto-generated method stub
		FormControlElement element = (FormControlElement)execution.getVariable(ElementHelper.Control_Element);
		ApproveMembersElement approveMembersElement = (ApproveMembersElement)execution.getVariable(ElementHelper.Approve_Members);
		String isCapital = element.getIscapital();
		
//		if(approveMembersElement.getSupervisor()!=null && !"".equals(approveMembersElement.getSupervisor()) && !OrgManager.blankOwner.equals(approveMembersElement.getSupervisor())){
//			return TO_DPTMGRS_SUPERVISOR;
//		}
		
		if(isCapital!=null && isCapital.equals(ISCAPITAL)){
			if(element.getCapitalType()!=null && element.getCapitalType().equals("1") && !approveMembersElement.getDm().equals(approveMembersElement.getCapital())){
				return TO_CAPITAL_CHECK;
			}else if(element.getCapitalType()!=null && element.getCapitalType().equals("2") && !approveMembersElement.getDm().equals(approveMembersElement.getCapital())){
				return TO_CAPITAL_CHECK;
			}
			if(approveMembersElement.getPm()!=null && !"".equals(approveMembersElement.getPm()) && !approveMembersElement.getDm().equals(approveMembersElement.getPm())){
				return TO_PM;
			}else{
				return TO_buyerMgr;
			}

		}else{
//			if(approveMembersElement.getSupervisor()!=null && !"".equals(approveMembersElement.getSupervisor()) && !OrgManager.blankOwner.equals(approveMembersElement.getSupervisor())){
//				return TO_DPTMGRS_SUPERVISOR;
//			}
//			else 
			if(approveMembersElement.getCcowner()!=null && !"".equals(approveMembersElement.getCcowner()) && !OrgManager.blankOwner.equals(approveMembersElement.getCcowner())&& !"undefined".equals(approveMembersElement.getCcowner())
					&& !approveMembersElement.getDm().equals(approveMembersElement.getCcowner())){
				return TO_CC;
			}else{
				return TO_buyerMgr;
			}
		}
		//return TO_CAPITAL_CHECK;
	}

}
