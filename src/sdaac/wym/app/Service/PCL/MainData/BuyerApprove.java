package sdaac.wym.app.Service.PCL.MainData;
/**
 * ---WHO----WHEN--------WHAT-----------------------------
 * ---WYM----2013-12-13--create a new Class for buyer approve that can judge which branch can be reach
 * -----------------------------------------------------------
 */
public class BuyerApprove extends UndateContextApprove implements IApprove {

	@Override
	public BaseForm doAgree(ApproveForm item) {
		// TODO Auto-generated method stub
		BaseForm form = super.doAgree(item);
		if(null!=item.getBuyerid() && "丁兵兵482,朱国平483".contains(item.getBuyerid())){
			form.setState(MDStateManager.SHBMgr);
		}
		return form;
	}
	
}
