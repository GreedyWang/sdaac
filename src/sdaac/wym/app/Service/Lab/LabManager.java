package sdaac.wym.app.Service.Lab;

import java.util.List;

import sdaac.wym.app.entity.lab.ApproveForm;
import sdaac.wym.app.entity.lab.LabForm;

/**
 * 实验室服务类
 * @author SA1KV5
 * @version 2011-6-9
 */
public class LabManager {
	
	protected FormBiz formBiz;
	private ApproveFormBiz afBiz;
	
	public FormBiz getFormBiz() {
		return formBiz;
	}

	public void setFormBiz(FormBiz formBiz) {
		this.formBiz = formBiz;
	}

	//表单处理
	/**
	 * 申请
	 */
	public void doApply(LabForm item) {
		formBiz.doAdd(item);
	}
	
	/**
	 * 查询
	 * @return
	 */
	public List<LabForm> doSelect(LabForm item) {
		return formBiz.doSelect(item);
	}
	
	public List<LabForm> doSelectSingleForm(LabForm item) {
		return formBiz.doSelectSingleForm(item);
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void doDelete(int id){
		
	}
	
	//流程处理
	/**
	 * 审批
	 * @param item
	 */
	public void approve(ApproveForm item){
		afBiz.doApprove(item);
	}

	public ApproveFormBiz getAfBiz() {
		return afBiz;
	}

	public void setAfBiz(ApproveFormBiz afBiz) {
		this.afBiz = afBiz;
	}
}
