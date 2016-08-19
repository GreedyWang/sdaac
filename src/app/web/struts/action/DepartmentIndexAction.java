package app.web.struts.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sdaac.wym.app.entity.hr.DepartmentState;

import app.entity.Tuser;

public class DepartmentIndexAction extends IndexAction {
	
	/**
	 * 确认完成填写
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doConfirmFinish (ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Tuser user=(Tuser) request.getSession().getAttribute("logineduser");
		int departID=user.getEmp().getTdepartment().getId();
		// 得到部门id
		if(this.getDepartmentIndexStateManager().isPass(departID,DepartmentState.DEFINED)){
			return super.selectDepEmpsIndexs(mapping, form, request, response);
		}
		return null;
	}
	
	/**
	 * 下载EXCEL
	 */
	@Override
	public ActionForward selectDepEmpsIndexs(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		Tuser user=(Tuser) request.getSession().getAttribute("logineduser");
		int departID=user.getEmp().getTdepartment().getId();
		// 得到部门id
		if(this.getDepartmentIndexStateManager().isPass(departID,DepartmentState.DEFINED)){
			return super.selectDepEmpsIndexs(mapping, form, request, response);
		}else{
			PrintWriter write;
			this.getDepartmentIndexStateManager().updateStateByDepartmentId(departID);
			try {
				response.setCharacterEncoding("utf-8");
				write = response.getWriter();
			//	write.append("<script language=javascript>");
				write.append("<a href='index/departEmpIndex/addSelfDempEmpIndexs.jsp'>该部门的指标还没有通过审批！</a>");
			//	write.append("window.history.back()");
			//	write.append("</script>");
				write.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return null;
		}
		
	}

}
