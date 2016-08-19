package app.biz.impl.epor;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import app.entity.Tempolyee;
import app.entity.epor.PrApprovedForm;
import app.entity.epor.PrPrForm;
import app.entity.epor.StateManager;

public class PRApproveManagertoSY extends PrApprovedFormBizImpl {
	private static Logger logger = Logger.getLogger(PRApproveManagertoSY.class);
	
	private StateManager stateManager;
	public void doApprovePR(PrApprovedForm approvedForm) {
		logger.warn(approvedForm.getTempolyee().getUid()+":========SY=========");
		//approvedForm.getPrPrForm().initScheduleMaptoSY();
		stateManager = approvedForm.getPrPrForm()
				.getSYStateManager();
		approvedForm.doSetVersion();
		//approvedForm.setType(stateManager.curStateName);
		String methodName = stateManager.condition;
		if (methodName.equals("approve1")) {
			approve1(approvedForm);
		} else if (methodName.equals("approve2")) {
			approve2(approvedForm);
		} 
		else if (methodName.equals("approveFinanceCYanTai")) {
			approveFinanceCYanTai(approvedForm);
		} 
		else if (methodName.equals("approve3")) {
			approve3(approvedForm);
		} else if (methodName.equals("approve4")) {
			approve4(approvedForm);
		} else if (methodName.equals("approve5")) {
			approve5(approvedForm);
		} else if (methodName.equals("approveFeedBack")) {
			approveFeedBack(approvedForm);
		} else if (methodName.equals("approvePmManagerSY")) {
			approvePmManagerSY(approvedForm);
		} else if (methodName.equals("approveDOA")) {
			this.approveDOA2(approvedForm);
		} else if (methodName.equals("approveBuyerManager")) {
			approveBuyerManager2(approvedForm);
		}else if (methodName.equals("approve11")) {
			approve11(approvedForm);
		}else if (methodName.equals("approveDOA2")) {
			this.doApproveYanTaiDOA(approvedForm);
		}else if(methodName.equals("approveCostCenter")){
			approveCostCenter(approvedForm);
		}else if (methodName.equals("approvePmManager")) {
			approvePmManager(approvedForm);
		}else if (methodName.equals("approveOTSManager")) {
			approveOTSManager(approvedForm);
		}
	}
	
	
	/**
	 * SY部门经理审批
	 * @param approvedForm
	 */
	private void approvePmManagerSY(PrApprovedForm approvedForm) {
		// TODO Auto-generated method stub
		String relateUids = "";
		if (approvedForm.getPrPrForm().getType().contains(PrPrForm.ITSingle)) {
			relateUids = ITManager;
			if(approvedForm.getPrPrForm().getSource()==2){
				relateUids="sy112;5003";
			}
		}
		if (approvedForm.getPrPrForm().getPrProject().getManagerUid() != null) {
			relateUids += approvedForm.getPrPrForm().getPrProject()
					.getManagerUid();
		}				
	
		approvedForm.setRelateUids(relateUids);
		List<PrApprovedForm> list = new ArrayList<PrApprovedForm>();
		list.add(approvedForm);
		int prState;
		formService.updatePrForm(approvedForm.getPrPrForm());// 更新PR

		prState = nextStep(approvedForm);
		
		if(prState == 19) prState = 12;//沈阳公司没有costcenter owner
		if (approvedForm.getIsApproved() == 1)// 同意
		{
			// prState = stateManager.nextState;
			if (approvedForm.getRelateUids() != null
					&& !approvedForm.getRelateUids().equals("")) {
				for (String uid : approvedForm.getRalate()) {
					PrApprovedForm item = new PrApprovedForm();
					item.setType(PrApprovedForm.RelationType);
					item.setTempolyee(new Tempolyee(uid));
					item.setVersion(approvedForm.getVersion());
					item.setPrPrForm(approvedForm.getPrPrForm());
					list.add(item);
				}
			} else {
				prState = 4;			
			}
		}
		// 保存审批表和相关评审
		prApprovedFormDao.blukFlushInsert(list);
		if (prState < 88) {
			formService.updatePrState(approvedForm.getPrPrForm(),
					prState);
		}
	}


	protected void approveDOA2(PrApprovedForm approvedForm) {
		formService.updatePrForm(approvedForm.getPrPrForm());// 更新PR
		int step = 0;
		double total_cost = approvedForm.getPrPrForm().getTotal();// 总金额
		PrPrForm prForm = approvedForm.getPrPrForm();
		int departId = approvedForm.getPrPrForm().getTempolyeeByApplicantId().getTdepartment().getId();//申请人部门ID
		step = dm.selectDOASY(total_cost, prForm.getIsPlan(), prForm
				.getIsCapital(), stateManager,departId);
		

		
		// 如果类型是
//		if (prForm.getType() != null && prForm.getType().contains("11"))
//			step = StateManager.FEEDBACK;
		this.prApprovedFormDao.insert(approvedForm);
		int prState;
		if (approvedForm.getIsApproved() == 1)// 同意
		{//StateManager.SYGM
			if(stateManager.curState== 14&& approvedForm.getPrPrForm().getIsCapital()==0){
				//is Capital then go to SH
				step = 52;
			}
			
			if (step == -3) {
				prState = stateManager.nextState;
			} else {
				prState = step;
			}
		} else if (approvedForm.getIsApproved() == 0) {
			prState = stateManager.failureState;
		} else {
			return;
		}
		formService.updatePrState(approvedForm.getPrPrForm(), prState);
	}
	
	/**
	 * 采购经理审批
	 */
	/**
	 * 采购经理审批
	 */
	protected void approveBuyerManager2(PrApprovedForm approvedForm) {
		// StateManager stateManager = approvedForm.getPrPrForm()
		// .getStateManager();
		if (approvedForm.getIsApproved() == 1
				&& approvedForm.getPrPrForm().getTotal() <= 1 )// 同意&& approvedForm.getPrPrForm().getIsPlan()!=null&&approvedForm.getPrPrForm().getIsPlan()==0
		{
			prApprovedFormDao.insert(approvedForm);
			formService.updatePrState(approvedForm.getPrPrForm(),
					StateManager.PCL);
			return;
		}
		this.prApprovedFormDao.insert(approvedForm);
		int prState = nextStep(approvedForm);
		if (prState < 88) {
			formService.updatePrState(approvedForm.getPrPrForm(),
					prState);
		}
	}
}
