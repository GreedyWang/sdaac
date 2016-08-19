package app.web.struts.action.dms;

import org.springframework.web.struts.DispatchActionSupport;
import sdaac.wym.dms.Service.DMSManager;
import sdaac.wym.dms.Service.TempleteService;
import sdaac.wym.dms.Service.i.ISocreDetailsService;
import sdaac.wym.dms.Service.i.IWorkerService;
import sdaac.wym.dms.biz.BonusBiz;
import sdaac.wym.dms.biz.EmpBiz;
import sdaac.wym.dms.biz.VersionBiz;

public class DMSBaseAction extends DispatchActionSupport {
	private DMSManager mdsManager;
	private TempleteService templete;
	private ISocreDetailsService socreDetailsService;
	private IWorkerService workerService;
	private EmpBiz empBiz;
	private BonusBiz bonusBiz;
	private VersionBiz verBiz;
	
	public VersionBiz getVersionBiz() {
		verBiz = (VersionBiz)this.getWebApplicationContext().getBean("verBiz");
		return verBiz;
	}
	
	public EmpBiz getEmpBiz() {
		empBiz = (EmpBiz)this.getWebApplicationContext().getBean("dmsEmpBiz");
		return empBiz;
	}

	public BonusBiz getBonusBiz() {
		bonusBiz = (BonusBiz)this.getWebApplicationContext().getBean("bonusBiz");
		return bonusBiz;
	}
	
	public DMSManager getDMSManager() {
		mdsManager = (DMSManager)this.getWebApplicationContext().getBean("DMSManager");
		return mdsManager;
	}
	
	public TempleteService getTempleteService() {
		templete = (TempleteService)this.getWebApplicationContext().getBean("templeteService");
		return templete;
	}
	
	public ISocreDetailsService getSocreDetailsService() {
		socreDetailsService = (ISocreDetailsService)this.getWebApplicationContext().getBean("scoreDetailsService");
		return socreDetailsService;
	}
	
	public IWorkerService getWorkerService() {
		workerService = (IWorkerService)this.getWebApplicationContext().getBean("workerService");
		return workerService;
	}
}
