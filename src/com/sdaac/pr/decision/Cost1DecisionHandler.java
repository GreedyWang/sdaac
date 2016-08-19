package com.sdaac.pr.decision;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

import com.sdaac.pr.Constant;
import com.sdaac.pr.approve.element.ApproveMembersElement;
import com.sdaac.pr.approve.element.ElementHelper;
import com.sdaac.pr.approve.element.FormControlElement;

public class Cost1DecisionHandler extends AbstractDecisionHandler implements DecisionHandler {
	
	private static int cost_level1 = 2000;
//	private static int cost_level2_capital =250*1000*6;
//	private static int cost_level2_expense =100*1000*6;
	private static int cost_ENG_capital = 50000;
	private static int cost_ENG_EXPNESE = 10000;
	private static String TO_PLANT_MGR ="to Plant_Mgr";
	private static String TO_DMG ="to Eng Dr/DGM";
	private static String TO_END ="toEnd";
	//private static String TO_CC ="toEnd";
	
	@Override
	public String decide(OpenExecution execution) {
		// TODO Auto-generated method stub
		FormControlElement element = (FormControlElement)execution.getVariable(ElementHelper.Control_Element);
		
		String total = element.getTotalCost();
		float costTotal = 0;
//		String iscapital = element.getIscapital();
		if(total!=null && !total.equals("")){
			 costTotal = Float.parseFloat(total);
		}
		if(isNeedPlantMgr(element)){
			ApproveMembersElement approveMembersElement = (ApproveMembersElement)execution.getVariable(ElementHelper.Approve_Members);
			if(element.getPlant()!=null && element.getPlant().equals(Constant.SH_COMPANY)){//is SH Company	
				if(!approveMembersElement.getSupervisor().equals(approveMembersElement.getDGM())){
					return stepCapitalDMG(element,costTotal,approveMembersElement);
				}else{
					return TO_END;
				}
			}else{
				
				if(approveMembersElement.getDm().equals(approveMembersElement.getPlantMgr())){
					PlantMgrDecisionHandler c2 = new PlantMgrDecisionHandler();
					String reslutStep = c2.decide(execution);
					return reslutStep.equals("next")?TO_END:reslutStep;
				}else{
					return TO_PLANT_MGR;
				}
			}				
		}else{
			return TO_END;
		}
	}
	
	private boolean isNeedPlantMgr(FormControlElement element){
		boolean flag = false;
		float costTotal = 0;
		String total = element.getTotalCost();
		if(total!=null && !total.equals("")){
			costTotal = Float.parseFloat(total);
		}
		if(costTotal >= cost_level1) {
			flag = true;
		}else if(element.getPlant().equals(Constant.SY) || element.getPlant().equals(Constant.YT)){
			flag = true;
		}
		
		return flag;
	}

}
