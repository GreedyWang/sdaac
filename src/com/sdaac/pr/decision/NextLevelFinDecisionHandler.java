package com.sdaac.pr.decision;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import com.sdaac.pr.approve.element.ElementHelper;
import com.sdaac.pr.approve.element.FormControlElement;

public class NextLevelFinDecisionHandler implements DecisionHandler {
	
	private static String TO_FIN_NEXT_LV ="to finNextLv";
	private static String TO_FIN_MGR ="to fin Mgr";
		
	@Override
	public String decide(OpenExecution execution) {
		// TODO Auto-generated method stub
		FormControlElement element = (FormControlElement)execution.getVariable(ElementHelper.Control_Element);
		String nextFin = element.getNextFin();
		if(nextFin != null && "true".equals(nextFin)){//Y is temp
			return TO_FIN_NEXT_LV;
		}else{
			return TO_FIN_MGR;
		}
		
	}
	
}