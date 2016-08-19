/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package app.web.struts.action.lab;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import app.common.web.ToJson;
import app.entity.Tempolyee;
import app.entity.Tuser;

import sdaac.wym.app.Service.Lab.FlowManager;
import sdaac.wym.app.Service.Lab.LabManager;
import sdaac.wym.app.entity.lab.ApproveForm;
import sdaac.wym.app.entity.lab.LabForm;

/** 
 * 处理实验室申请单的action类
 * Creation date: 06-10-2011
 * XDoclet definition:
 * @struts.action parameter="actionType" validate="true"
 */
public class LabActionAction extends LabBaseAction {
	/*
	 * Generated Methods
	 */
	private Tuser user;
	/** 
	 * 申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward doApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LabActionForm labForm = (LabActionForm)form;	
		user =(Tuser) request.getSession().getAttribute("logineduser");
		//申请单类型
		String formType = request.getParameter("type");
		LabManager labM = null;
		LabForm item = null;
		if(formType!=null && formType.equals(LabForm.innerType)) {
			labM = this.getIlabM();
			item = labForm.getItem();
		}else if(formType!=null && formType.equals(LabForm.outype)) {
			labM = this.getOlabM();
			item = labForm.getItem2();
		}
		item.setApplicant(user.getEmp());
		item.setApplicantDate(new Date());
		labM.doApply(item);
		return null;
	}
	
	/**
	 * 审批
	 * @return
	 */
	public ActionForward doApprove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LabActionForm labForm = (LabActionForm)form;
		ApproveForm af = labForm.getApproveFrom();
		user =(Tuser) request.getSession().getAttribute("logineduser");
		Integer id = Integer.parseInt(request.getParameter("id"));//key
		String formType = af.getForm().getType();
		LabManager labM = null;
		if(formType!=null && formType.equals(LabForm.innerType)) {
			labM = this.getIlabM();
		}else if(formType!=null && formType.equals(LabForm.outype)) {
			labM = this.getOlabM();
		}
		labM.approve(af);
		return null;
	}
	
	/**
	 * 查询我的申请单
	 * @return
	 */
	public ActionForward doShowSelf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LabActionForm labForm = (LabActionForm)form;
		ApproveForm af = labForm.getApproveFrom();
		LabForm item = af.getForm();
		String uid = request.getParameter("uid");//当前用户
		item.setApplicant(new Tempolyee(uid));
		LabManager labM = this.getLabM();;
		List<LabForm> rs1 = labM.doSelectSingleForm(item);
		ToJson.listToJsonArrart(rs1, response);
		return null;
	}
	
	/**
	 * 查询我审批的
	 * @return
	 */
	public ActionForward doShowMyApprove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LabActionForm labForm = (LabActionForm)form;
		ApproveForm af = labForm.getApproveFrom();
		LabForm item = af.getForm();
		String uid = request.getParameter("uid");//当前用户	
		user =(Tuser) request.getSession().getAttribute("logineduser");
		//获取权限
//		this.getLRM().checkRights(user.getEmp(),item);
		System.out.println("doShowMyApprove==>"+item.getState());
		item.setApplicant(new Tempolyee(uid));
		LabManager labM = this.getLabM();;
		List<LabForm> rs1 = labM.doSelectSingleForm(item);
		ToJson.listToJsonArrart(rs1, response);
		return null;
	}
	
	/**
	 * 查询申请单by id
	 * @return
	 */
	public ActionForward doShowById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LabActionForm labForm = (LabActionForm)form;
		ApproveForm af = labForm.getApproveFrom();
		LabForm item = af.getForm();
		Integer id = Integer.parseInt(request.getParameter("id"));//key
//		item.setApplicant(new Tempolyee(uid));
		item.setSsid(id);
		LabManager labM = this.getLabM();;
		List<LabForm> rs1 = labM.doSelect(item);
		request.setAttribute("rs", rs1.get(0));
//		ToJson.listToJsonArrart(rs1, response);
		return mapping.findForward("details");
	}
	
}