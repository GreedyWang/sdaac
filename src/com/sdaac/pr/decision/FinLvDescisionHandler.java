package com.sdaac.pr.decision;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

import com.sdaac.common.org.OrgManager;
import com.sdaac.pr.approve.element.ApproveMembersElement;
import com.sdaac.pr.approve.element.ElementHelper;
import com.sdaac.pr.approve.element.FormControlElement;

public class FinLvDescisionHandler implements DecisionHandler {
	
	private static int LEVEL_COST = 100000;
	private static String TO_LV1 ="to proctrl";
	private static String TO_FIN="to fin";
	@Override
	public String decide(OpenExecution execution) {
		// TODO Auto-generated method stub
		FormControlElement element = (FormControlElement)execution.getVariable(ElementHelper.Control_Element);
		float totalcost = Float.parseFloat(element.getTotalCost());
		
		ApproveMembersElement approveMembersElement = (ApproveMembersElement)execution.getVariable(ElementHelper.Approve_Members);
		if(approveMembersElement.getFinlv1()==null || OrgManager.blankOwner.equals(approveMembersElement.getFinlv1())){
			return TO_FIN;
		}else {	
			if(totalcost >= LEVEL_COST){
				return TO_LV1;
			}else{
				return TO_FIN;
			}
		}
	}

}
