package com.sdaac.pr.approve.element;

import java.util.HashMap;
import java.util.Map;

import com.sdaac.common.org.OrgManager;

import app.entity.epor.PrPrForm;

public class ElementHelper {
	public static String Approve_Members = "approveMembers";
	public static String Control_Element = "key2";	
	private ApproveMembersElement approveMembers;
	private FormControlElement formControlElement;
	private Map<String,Object> variable = new HashMap<String,Object>();
	
	public ElementHelper(PrPrForm form,String[] roles){
		formControlElement = new FormControlElement();
		formControlElement.setPrformid(form.getId());
		formControlElement.setIscapital(form.getIsCapital()+"");
		formControlElement.setCapitalType(form.getCapitalcategroy());
		formControlElement.setTotalCost(form.getTotal()+"");
		//plant
		formControlElement.setIssueFor(form.getIssueFor());
		formControlElement.setPlant(form.getSource()+"");
		
		approveMembers = new ApproveMembersElement();	
		approveMembers.setFinlv1(roles[OrgManager.FIN_LV1]);
		approveMembers.setFinNextLv(roles[OrgManager.finNextLv]);
		approveMembers.setDm(roles[OrgManager.DPRT]);
		approveMembers.setSupervisor(roles[OrgManager.DMSupervisor]);
		
		if(form.getIsCapital() == 0 && roles[OrgManager.DMSupervisor]!=OrgManager.blankOwner){
			approveMembers.setDm(roles[OrgManager.DMSupervisor]);
		}
		
		approveMembers.setOwner(form.getTempolyeeByApplicantId().getNetid());
		approveMembers.setFin(roles[OrgManager.Fin_Check]);
		//control owner
		approveMembers.setCapital(roles[OrgManager.CapitalOwner]);
		approveMembers.setPm(form.getPm());
		approveMembers.setCcowner(form.getCcowner());

		
		approveMembers.setFinMgr(roles[OrgManager.FINMGR]);
		approveMembers.setDGM(roles[OrgManager.DGM]);
		//approveMembers.setEng(roles[OrgManager.ENG]);
		approveMembers.setPlantMgr(roles[OrgManager.PLANTMgr]);
		approveMembers.setGM(roles[OrgManager.GM]);
		approveMembers.setThermal(roles[OrgManager.THEMRL]);
		approveMembers.setBuyer_check(roles[OrgManager.Buyer_Check]);
		approveMembers.setBuyerMgr(roles[OrgManager.BuyerMgr]);
		approveMembers.setFin_check(roles[OrgManager.Fin_Check]);
		approveMembers.setPCL_check(roles[OrgManager.PCL_Check]);

		variable.put(Approve_Members, approveMembers);	
	}
	
	/**
	 * move to ElementHelper.java
	 */

	private void createApproveMembers(){
//		approveMembers = new ApproveMembersElement();
//		
//		approveMembers.setDm("wym");
//		approveMembers.setOwner("wym");
//		variable.put(Approve_Members, approveMembers);	
	}
	
	private void createFormControlElement(){		
		variable.put(Control_Element, formControlElement);	
	}
	
	public Map<String,Object> createElement(){
		//createApproveMembers();
		createFormControlElement();		
		return variable;
	}

	public ApproveMembersElement getApproveMembers() {
		return approveMembers;
	}

	public void setApproveMembers(ApproveMembersElement approveMembers) {
		this.approveMembers = approveMembers;
	}

	public FormControlElement getFormControlElement() {
		return formControlElement;
	}

	public void setFormControlElement(FormControlElement formControlElement) {
		this.formControlElement = formControlElement;
	}
	
}
