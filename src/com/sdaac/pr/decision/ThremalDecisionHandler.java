package com.sdaac.pr.decision;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

import com.sdaac.pr.Constant;
import com.sdaac.pr.approve.element.ElementHelper;
import com.sdaac.pr.approve.element.FormControlElement;

public class ThremalDecisionHandler implements DecisionHandler{
	
	private static int cost_level2_capital =250*1000*6;
	private static int cost_level2_expense =100*1000*6;
	private static String TO_GM ="to GM";
	private static String TO_END ="toEnd";
	private static String TO_Thermal ="to Thermal";
	
	/**
	 * decide whether thermal GM(KK)  
	 */
	@Override
	public String decide(OpenExecution execution) {
		// TODO Auto-generated method stub
		FormControlElement element = (FormControlElement)execution.getVariable(ElementHelper.Control_Element);
		String total = element.getTotalCost();
		String iscapital = element.getIscapital();
		if(total!=null && !total.equals("")){
			Float costTotal = Float.parseFloat(total);
			int targetCostLevel;
			if(iscapital!=null && iscapital.equals(Constant.iscapital)){
				targetCostLevel = cost_level2_capital;
			}else{
				targetCostLevel = cost_level2_expense;
			}
			if(costTotal>targetCostLevel)return TO_Thermal;
			else{
				return TO_END;
			}
		}
		return TO_END;
	}

}
