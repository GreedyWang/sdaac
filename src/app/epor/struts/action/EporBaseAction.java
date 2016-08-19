package app.epor.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.DispatchActionSupport;

import com.sdaac.common.org.OrgManager;
import com.sdaac.pr.approve.PRProcessEngine;
import com.sdaac.pr.tools.Tools;
import com.security.biz.impl.MailManager;

import common.util.LoadProperties;
import common.util.Reports.ApprovedDate;
import common.util.Reports.PCLPostingDate;
import common.util.Reports.R4PR;
import common.util.download.UploadFile;

import sdaac.wym.app.Service.PCL.MainData.Manager;
import sdaac.wym.common.Service.MDRightsManager;
import sdaac.wym.common.Service.PrRightsManager;
import sdaac.wym.common.Service.RoleManager;

import app.biz.EmpBiz;
import app.biz.epor.PrBuyContextBiz;
import app.biz.epor.PrFormBiz;
import app.biz.impl.epor.AuthorizedImpl;
import app.biz.impl.epor.PRApproveManagertoSY;
import app.biz.impl.epor.PRApproveManagertoYanTai;
import app.biz.impl.epor.PaydetailsService;
import app.biz.impl.epor.PrApprovedFormBizImpl;
import app.biz.impl.epor.PrFormBizImpl;
import app.common.FileUpLoad;
import app.entity.epor.PrBuyContext;

public class EporBaseAction extends DispatchActionSupport {
	private PrFormBizImpl prFormBiz=null;
	private PrFormBizImpl prSYFormBiz=null;	
	private PrFormBizImpl prYTFormBiz=null;	
	private PrFormBizImpl prSHFormBiz=null;	
	private PrBuyContextBiz prBuyContextBiz=null;
	private RoleManager roleManager;
	private PrRightsManager prRightsManager;
	private PRApproveManagertoSY ApproveManagertoSY;
	private PRApproveManagertoYanTai approveManagertoYanTai;
	private PrApprovedFormBizImpl approveManager;
	private MDRightsManager mdRightsManager;
	private PaydetailsService paydetailsService;
	private Manager manager;
	private PCLPostingDate ppd;
	private R4PR p4PR;
	private ApprovedDate approvedDate;
	private OrgManager orgManger;
	private EmpBiz empBiz=null;
	private MailManager mailMgr;
	private PRProcessEngine processEng;
	private Tools tools;
	private AuthorizedImpl authImpl;
	
	public AuthorizedImpl getAuthImpl() {
		authImpl=(AuthorizedImpl) this.getWebApplicationContext().getBean("AuthorizedFormBiz");
		return authImpl;
	}
	
	public Tools getTools() {
		tools=(Tools) this.getWebApplicationContext().getBean("tools");
		return tools;
	}
	
	public PRProcessEngine getPRProcessEngine() {
		processEng=(PRProcessEngine) this.getWebApplicationContext().getBean("prProcessEngine");
		return processEng;
	}
	
	public EmpBiz getEmpBiz() {
		empBiz=(EmpBiz) this.getWebApplicationContext().getBean("empBiz");
		return empBiz;
	}
	
	public MailManager getMailManager() {
		mailMgr=(MailManager) this.getWebApplicationContext().getBean("mailBiz");
		return mailMgr;
	}
	
	public OrgManager getOrgManger() {
		orgManger = (OrgManager)this.getWebApplicationContext().getBean("orgManager");
		return orgManger;
	}
	
	public ApprovedDate getApprovedDate() {
		approvedDate = (ApprovedDate)this.getWebApplicationContext().getBean("ApprovedDate");
		return approvedDate;
	}

	public R4PR getP4PR() {
		p4PR = (R4PR)this.getWebApplicationContext().getBean("R4PR");
		return p4PR;
	}
	
	public PCLPostingDate getPpd() {
		ppd = (PCLPostingDate)this.getWebApplicationContext().getBean("PCLPostingDateReport");
		return ppd;
	}	
	
	public PaydetailsService getPaydetailsService() {
		paydetailsService = (PaydetailsService)this.getWebApplicationContext().getBean("PaydetailsService");
		return paydetailsService;
	}	
	
	public MDRightsManager getMdRightsManager() {
		mdRightsManager = (MDRightsManager)this.getWebApplicationContext().getBean("MDRightsManager");
		return mdRightsManager;
	}
	public Manager getManager() {
		manager = (Manager)this.getWebApplicationContext().getBean("Manager");
		return manager;
	}
	public PrRightsManager getPrRightsManager() {
		prRightsManager=(PrRightsManager)this.getWebApplicationContext().getBean("PrRightsManager");
		return prRightsManager;
	}
	
	public RoleManager getRoleManager() {
		roleManager=(RoleManager)this.getWebApplicationContext().getBean("roleManager");
		return roleManager;
	}
	
	public PRApproveManagertoSY getPRApproveManagertoSY() {
		ApproveManagertoSY=(PRApproveManagertoSY)this.getWebApplicationContext().getBean("prApprovedFormBizSY");
		return ApproveManagertoSY;
	}
	
	public PrApprovedFormBizImpl getPRApproveManagertoSDAAC() {
		approveManager=(PrApprovedFormBizImpl)this.getWebApplicationContext().getBean("prApprovedFormBiz");
		return approveManager;
	}
	
	public PrBuyContextBiz getPrBuyContextBiz() {
		prBuyContextBiz=(PrBuyContextBiz)this.getWebApplicationContext().getBean("buyContextBiz");
		return prBuyContextBiz;
	}

	public PrFormBizImpl getPrFormBiz() {
		prFormBiz=(PrFormBizImpl)this.getWebApplicationContext().getBean("prFormBiz");
		return prFormBiz;
	}
	
	public PrFormBizImpl getPrSYFormBiz() {
		prFormBiz=(PrFormBizImpl)this.getWebApplicationContext().getBean("prSYFormBiz");
		return prFormBiz;
	}
	
	public PrFormBizImpl getPrYTFormBiz() {
		prYTFormBiz=(PrFormBizImpl)this.getWebApplicationContext().getBean("prYTFormBiz");
		return prYTFormBiz;
	}

	public PrFormBizImpl getPrSHFormBiz() {
		prSHFormBiz=(PrFormBizImpl)this.getWebApplicationContext().getBean("prSHFormBiz");
		return prSHFormBiz;
	}
	
	public ActionForward doDownload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UploadFile upload = new UploadFile();
		String filePath = LoadProperties.newInstance().getValue(LoadProperties.K_PR_UPLAODDIR);
		String prid = request.getParameter("");
		String filename = request.getParameter("");
		String path = filePath+"\\"+prid+"\\"+filename;
		upload.getFile(response, path);
		return null;
	}

}
