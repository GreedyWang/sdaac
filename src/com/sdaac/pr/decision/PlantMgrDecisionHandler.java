package com.sdaac.pr.decision;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

import com.sdaac.common.org.OrgManager;
import com.sdaac.pr.Constant;
import com.sdaac.pr.approve.element.ApproveMembersElement;
import com.sdaac.pr.approve.element.ElementHelper;
import com.sdaac.pr.approve.element.FormControlElement;

public class PlantMgrDecisionHandler extends AbstractDecisionHandler implements DecisionHandler{
	
	private static String TO_GM ="to GM";
	private static String TO_DMG ="to Eng Dr/DGM";
	private String next ="next";
	private static int cost_level2_capital =100000;
	private static int cost_level2_expense =15000;
	private static int eng_cost =2000;
	@Override
	public String decide(OpenExecution execution) {
		// TODO Auto-generated method stub

		ApproveMembersElement approveMembersElement = (ApproveMembersElement)execution.getVariable(ElementHelper.Approve_Members);
		FormControlElement element = (FormControlElement)execution.getVariable(ElementHelper.Control_Element);
		String total = element.getTotalCost();
		String iscapital = element.getIscapital();
		Float costTotal = Float.parseFloat(total);
		int targetCostLevel;
		if(costTotal<eng_cost){
			return next;
		}
		if(iscapital!=null && iscapital.equals(Constant.iscapital)){
			targetCostLevel = cost_level2_capital;
		}else{
			targetCostLevel = cost_level2_expense;
		}
		if(approveMembersElement.getDGM()==null 
				|| approveMembersElement.getDGM().equals(OrgManager.blankOwner)) {
			if(costTotal>=targetCostLevel){
				//go to GM
				return TO_GM;
			}else{
				return next;
			}
		}else{
			return stepCapitalDMG2(element,costTotal,approveMembersElement);
			//return TO_DMG;
		}
	}

}
