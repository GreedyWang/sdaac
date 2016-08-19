package sdaac.wym.app.Service.Lab;

import sdaac.wym.app.entity.lab.ApproveForm;

import common.dao.CommonDAO;
/**
 * 实验室审批流程控制类
 * @author SA1KV5
 *
 */
public class ApproveFormBiz {
	private CommonDAO<ApproveForm> afDao;

	public CommonDAO<ApproveForm> getAfDao() {
		return afDao;
	}

	public void setAfDao(CommonDAO<ApproveForm> afDao) {
		this.afDao = afDao;
	}
	
	public void doApprove(ApproveForm item){
		FlowManager fm = new FlowManager();
		fm.approve(item);
		afDao.insert(item);
	}
}
