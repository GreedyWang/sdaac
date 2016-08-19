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
public class PRApproveManagertoYanTai extends PrApprovedFormBizImpl {
	
	private StateManager stateManager; 
	@Override
	public void doApprovePR(PrApprovedForm approvedForm) {
		// TODO Auto-generated method stub
		stateManager = approvedForm.getPrPrForm()
		.getYanTaiStateManager();
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
	
//	/**
//	 *<p> YanTai DOA </p>
//	 */
//	private void doApproveYanTaiDOA(PrApprovedForm approvedForm){
//		PrPrForm form = approvedForm.getPrPrForm();
//		float total = form.getTotal();
//		StateManager stateManager = form.getYanTaiStateManager();
//		int nextState = stateManager.curState;
//		int level1 = 20000 ,level2 = 200000,level3 = 600000;
//		if (approvedForm.getIsApproved() == 1)// 同意
//		{
//			if(stateManager.curState == StateManager.YTGM) {
//				//State is Plant Manager 
//				if(true){
//					//IF Dept is Engineering DOA is 2k
//					nextState = stateManager.nextState;
//				}else if(total > level1){
//					nextState = StateManager.SH_FINANCE_MANAGER ;//SH_FINANCE_MANAGER
//				}else{
//					nextState = stateManager.FEEDBACK;
//				}
//				
//			}else if(stateManager.curState == StateManager.SH_CTO) {
//				//State is SH_CTO
//				if(total > level1) {					
//					nextState = stateManager.nextState;
//				}else {
//					nextState = stateManager.FEEDBACK;
//				}
//			}else if(stateManager.curState == StateManager.SH_FINANCE_MANAGER) {
//				//State is SH_FINANCE_MANAGER
//				if(total > level2) {					
//					nextState = stateManager.nextState;
//				}else {
//					nextState = stateManager.FEEDBACK;
//				}
//			}else if(stateManager.curState == StateManager.DeputyGeneral) {
//				//State is SDAACGM
//				if(total > level3) {					
//					nextState = stateManager.nextState;
//				}else {
//					nextState = stateManager.FEEDBACK;
//				}
//		
//			}
//		}else if(approvedForm.getIsApproved() == 0){//refuse
//			nextState = stateManager.failureState;
//		}else if(approvedForm.getIsApproved() == 2){
//			approvedForm.getPrPrForm().setInfo(1);//需要解释
//		}
//		formService.updatePrState(approvedForm.getPrPrForm(), nextState);
//		this.prApprovedFormDao.insert(approvedForm);
//	}
	
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
