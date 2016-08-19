package app.web.struts.action.pcl.maindata;

import org.springframework.web.struts.DispatchActionSupport;

import sdaac.wym.app.Service.PCL.MainData.ApproveManager;
import sdaac.wym.app.Service.PCL.MainData.Manager;
import sdaac.wym.app.Service.PCL.MainData.OrdinaryApprove;


public class PCLBaseAction extends DispatchActionSupport {
	private Manager manager;
	
	private ApproveManager approveManager;
	private ApproveManager apManager;
	
	
	public ApproveManager getApManager() {
		apManager = (ApproveManager)this.getWebApplicationContext().getBean("ApproveManager");
		return apManager;
	}


	public ApproveManager getApproveManager() {
		approveManager = (ApproveManager)this.getWebApplicationContext().getBean("ApproveManager");
		return approveManager;
	}


	public Manager getManager() {
		manager = (Manager)this.getWebApplicationContext().getBean("Manager");
		return manager;
	}

	
}
