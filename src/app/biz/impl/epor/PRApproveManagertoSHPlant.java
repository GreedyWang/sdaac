package app.biz.impl.epor;

import app.entity.epor.PrApprovedForm;
import app.entity.epor.PrPrForm;
import app.entity.epor.StateManager;

/**
 * 
 * @author SA1KV5
 * @since 2012-6-4
 *        <p>
 *        Approval Class for YanTai
 *        </p>
 */
public class PRApproveManagertoSHPlant extends PrApprovedFormBizImpl {
	
	private StateManager stateManager; 
	@Override
	public void doApprovePR(PrApprovedForm approvedForm) {
		// TODO Auto-generated method stub
		stateManager = approvedForm.getPrPrForm()
		.getSHStateManager();
		approvedForm.doSetVersion();
		//approvedForm.setType(stateManager.curStateName);
		String methodName = stateManager.condition;
		if (methodName.equals("approve1")) {
			approveOrdinaryYanTai(approvedForm);
		} else if (methodName.equals("approve2")) {
			approveFinanceCYanTai(approvedForm);
		} else if (methodName.equals("approve3")) {
			approve3(approvedForm);
		} else if (methodName.equals("approve4")) {
			approve4(approvedForm);
		} else if (methodName.equals("approve5")) {
			approve5(approvedForm);
		} else if (methodName.equals("approvePmManager")) {
			approvePmManager(approvedForm);
		} else if (methodName.equals("approveDOA")) {
			doApproveYanTaiDOA(approvedForm);
		}else if(methodName.equals("approveBuyerManager")){
			approveBuyerManager(approvedForm);
		}else if(methodName.equals("approveOTSManager")){
			approveOTSManager(approvedForm);
		}else if(methodName.equals("approveFeedBack")){
			approveFeedBackYanTai(approvedForm);
		}else if(methodName.equals("approveCostCenter")){
			approveCostCenter(approvedForm);
		}else if(methodName.equals("approve11")){
			approve11(approvedForm);
		}else if(methodName.equals("zongbuCWApprove")){
			zongbuCWApprove(approvedForm);
		}
	}
	
	
	/**
	 * 回填信息
	 * 
	 * @param approvedForm
	 */
	protected void approveFeedBackYanTai(PrApprovedForm approvedForm) {

		approve1(approvedForm);
		Integer nowstate = null;
		if(approvedForm.getIsApproved() == 1){
			if( approvedForm.getPrPrForm().getIsInTheSap()==null|| approvedForm.getPrPrForm().getIsInTheSap()!=1){
				nowstate = StateManager.PRNO ;				
				approvedForm.getPrPrForm().setState(nowstate);
			}
//			else if(approvedForm.getPrPrForm().getIsInTheSap()!=null && approvedForm.getPrPrForm().getIsInTheSap()==1 && approvedForm.getPrPrForm().getIsCapital()==0){
//				nowstate = 59;//上海PCL
//				approvedForm.getPrPrForm().setState(nowstate);
//			}
		}

		if(nowstate!=null){
			formService.updatePrState(approvedForm.getPrPrForm(),
					nowstate);
		}
		approvedForm.getPrPrForm().setState(null);
		formService.updatePrForm(approvedForm.getPrPrForm());// 更新PR
	}
	

	
	/**
	 * 普通批审提案单
	 */
	private void approveOrdinaryYanTai(PrApprovedForm approvedForm) {
		// StateManager stateManager = approvedForm.getPrPrForm()
		// .getStateManager();

		this.prApprovedFormDao.insert(approvedForm);
		int prState;
		
		StateManager stateManager = approvedForm.getPrPrForm()
		.getYanTaiStateManager();
		// 同意
		if (approvedForm.getIsApproved() == 1){
			prState = stateManager.nextState;
		} else if (approvedForm.getIsApproved() == 0) {
			prState = stateManager.failureState;
			//退回采购员
		}else if(approvedForm.getIsApproved() == 5){ 
			prState = StateManager.BuyConfig;
		}else {
			prState = 88;
		}
		
		if (prState < 88) {
			formService.updatePrState(approvedForm.getPrPrForm(),
					prState);
		}
	}
	
//	/**
//	 * 烟台财务审批
//	 * isCaptial -> SH finance
//	 * isExpense -> Plant Finance Manager
//	 */
//	private void approveFinanceCYanTai(PrApprovedForm approvedForm) {
//		this.prApprovedFormDao.insert(approvedForm);
//		int prState;
//		formService.updatePrForm(approvedForm.getPrPrForm());// 更新PR
//		StateManager stateManager = approvedForm.getPrPrForm()
//		.getYanTaiStateManager();
//		// 同意
//		if (approvedForm.getIsApproved() == 1){
//			int isCpatial = approvedForm.getPrPrForm().getIsCapital();
//			if(isCpatial == PrPrForm.CAPTIAL){
//				//isCaptial
//				prState = StateManager.SH_FINANCE;
//			}else{
//				prState = stateManager.nextState;
//			}
//		} else if (approvedForm.getIsApproved() == 0) {
//			prState = stateManager.failureState;
//			//退回采购员
//		}else if(approvedForm.getIsApproved() == 5){ 
//			prState = StateManager.BuyConfig;
//		}else {
//			prState = 88;
//		}
//		
//		if (prState < 88) {
//			formService.updatePrState(approvedForm.getPrPrForm(),
//					prState);
//		}
//	}
	
}
