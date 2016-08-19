package sdaac.wym.app.Service.PCL.MainData;

import common.dao.CommonDAO;

/**
 * <tr>
 * 	<td>
 *  	PCL 物料主数据申请 审批管理
 *  </td>
 * </tr>
 * @version 1.0
 * @author SA1KV5
 * @since 2011-11-10
 */
public class ApproveManager {
	private CommonDAO<ApproveForm> approveFormDao;
	private FormService formService;
	private IApprove approveFun;
	
	public IApprove getApproveFun() {
		return approveFun;
	}

	public void setApproveFun(IApprove approveFun) {
		this.approveFun = approveFun;
	}

	public FormService getFormService() {
		return formService;
	}

	public void setFormService(FormService formService) {
		this.formService = formService;
	}

	public CommonDAO<ApproveForm> getApproveFormDao() {
		return approveFormDao;
	}

	public void setApproveFormDao(CommonDAO<ApproveForm> approveFormDao) {
		this.approveFormDao = approveFormDao;
	}
	
	/**
	 * 审批
	 * @param item
	 */
	public void doApprove(ApproveForm item){
		int curState = item.getItem().getState();// 当前状态
		//每个状态对应的审批方法
		if(MDStateManager.states.get(curState).condition.equals("ordinaryApprove.jsp")) {
			this.setApproveFun(new OrdinaryApprove());
			BaseForm newFormState = approveFun.doApprove(item);
			//更新申请单状态
			formService.doUpdate(newFormState);
		}else if (MDStateManager.states.get(curState).condition.equals("updateContext")) {
			this.setApproveFun(new UndateContextApprove());
			BaseForm newFormState = approveFun.doApprove(item);
			formService.doUpdateMD((MDForm) newFormState);
		}else if (MDStateManager.states.get(curState).condition.equals("BuyerApprove")) {
			this.setApproveFun(new BuyerApprove());
			BaseForm newFormState = approveFun.doApprove(item);
			formService.doUpdateMD((MDForm) newFormState);
		}else if (MDStateManager.states.get(curState).condition.equals("FinApprove")) {
			this.setApproveFun(new FinApprove());
			BaseForm newFormState = approveFun.doApprove(item);
			formService.doUpdateMD((MDForm) newFormState);
		}				
		//持久化审批意见
		approveFormDao.insert(item);	
	}
	
	public void doJustSave(ApproveForm item){
		approveFormDao.insert(item);	
	}
}
