package sdaac.wym.app.Service.PCL.MainData;
/**
 * 普通审批 批准下一步，拒绝退回申请人，需要解释更新状态
 * @author SA1KV5
 *
 */
public class OrdinaryApprove  implements IApprove {
	/**
	 * 普通审批
	 * @param item
	 */
	@Override
	public BaseForm doApprove(ApproveForm item) {
		// TODO Auto-generated method stub	
		if(item.getIsApproved()==MDStateManager.APPROVE) {		
			return doAgree(item);
		}else if(item.getIsApproved()==MDStateManager.REFUSE) {
			return doRefuse(item);
		}else if(item.getIsApproved()==MDStateManager.BackTo) {
			return doRefuse(item);
		}else{
			return doNeedExplain(item);
		}	
	}

	@Override
	public BaseForm doAgree(ApproveForm item) {
		// 当前状态
		int curState = item.getItem().getState();
		int nextState = MDStateManager.states.get(curState).nextState;	
		BaseForm form = new BaseForm();
		form.setUuid(item.getItem().getUuid());
		form.setState(nextState);
		form.setNeedExplain(BaseForm.NORMAL);
		return form;
	}

	@Override
	public BaseForm doNeedExplain(ApproveForm item) {
		// TODO Auto-generated method stub
		MDForm form = new MDForm();
		form.setUuid(item.getItem().getUuid());
		form.setNeedExplain(BaseForm.NEEDEXPLAIN);
		return form;
	}

	@Override
	public BaseForm doRefuse(ApproveForm item) {
		// TODO Auto-generated method stub
		int curState = item.getItem().getState();
		int failureState = MDStateManager.states.get(curState).failureState;	
		MDForm form = new MDForm();
		form.setUuid(item.getItem().getUuid());
		form.setState(failureState);
		form.setNeedExplain(BaseForm.RETURN);
		return form;
	}

	@Override
	public BaseForm doBack(ApproveForm item) {
		// TODO Auto-generated method stub
		int curState = item.getItem().getState();	
		//临时写的，会上上一步 
		int backState = curState -1;
		MDForm form = new MDForm();
		form.setUuid(item.getItem().getUuid());
		form.setState(backState);
		form.setNeedExplain(BaseForm.NORMAL);
		return form;
	}

}
