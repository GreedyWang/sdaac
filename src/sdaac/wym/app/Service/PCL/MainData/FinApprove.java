package sdaac.wym.app.Service.PCL.MainData;

import app.entity.epor.StateManager;

/**
 * ---WHO----WHEN--------WHAT-----------------------------
 * ---WYM----2013-12-13--4 Fin approve that can judge which branch can be reach
 * -----------------------------------------------------------
 */
public class FinApprove extends UndateContextApprove implements IApprove {

	@Override
	public BaseForm doAgree(ApproveForm item) {
		// TODO Auto-generated method stub
		BaseForm form = super.doAgree(item);
		//System.out.println(item.getItem().getTempolyeeByApplicantId().getTdepartment().getId());
		if(null!=item.item.getTempolyeeByApplicantId().getTdepartment().getId() 
				&& !StateManager.DGM2.contains(item.item.getTempolyeeByApplicantId().getTdepartment().getId()+"")){
			form.setState(MDStateManager.SHPMgr);
		}
		return form;
	}
	
}
