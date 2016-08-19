/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package app.web.struts.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import test.DBLog4j;

import common.util.DownLoad;
import common.util.Page;

import app.common.PostDailyRecodeExcel;
import app.entity.Logs;
import app.entity.TemployeeProduct;
import app.entity.Tuser;
import app.web.struts.form.EmployeeProductForm;

/** 
 * MyEclipse Struts
 * Creation date: 01-21-2009
 * XDoclet definition:
 * @struts.action path="/employeeProduct" name="employeeProductForm" input="/form/employeeProduct.jsp" parameter="actionType" scope="request" validate="true"
 */
public class EmployeeProductAction extends BaseAction {
	
	/** 
	 * 下载每日产量记录
	 */
	public ActionForward doSelectDailyRecordByExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EmployeeProductForm employeeProductForm = (EmployeeProductForm) form;// TODO Auto-generated method stub
		String beginTime=employeeProductForm.getBegin();
		String endTime=employeeProductForm.getEnd();
		Integer pageIndex=1;
		TemployeeProduct empp= employeeProductForm.getItem();
		List<TemployeeProduct>list=null;
		boolean flag=true;
		if(request.getParameter("page")!=null)
		pageIndex=Integer.parseInt(request.getParameter("page"));
	
		Tuser user=(Tuser)request.getSession().getAttribute("logineduser");
		if(user.getRights()>1)
		{
			flag=false;
		}	
		empp.setRegisterID(user.getUid());
		list=this.getPpBiz().doSelectDailyRecord(flag, empp,beginTime,endTime,null);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		String now=sdf.format(new Date());
		String fileName=request.getRealPath("/")+"excelDown\\DailyPostSalary_"+now+".xls";	
		
		try {
			PostDailyRecodeExcel pdre=new PostDailyRecodeExcel(fileName);
			pdre.write(list, beginTime, endTime);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DownLoad dl=new DownLoad();
		dl.dLoad(request, response, fileName);
		return null;
	}
	/** 
	 * 查询每日产量
	 */
	public ActionForward doSelectDailyRecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		EmployeeProductForm employeeProductForm = (EmployeeProductForm) form;// TODO Auto-generated method stub
		String beginTime=employeeProductForm.getBegin();
		String endTime=employeeProductForm.getEnd();
		Integer pageIndex=1;
		TemployeeProduct empp= employeeProductForm.getItem();
		List<TemployeeProduct>list=null;
		boolean flag=true;
		if(request.getParameter("page")!=null)
		pageIndex=Integer.parseInt(request.getParameter("page"));
		Page page=new Page(pageIndex);
		Tuser user=(Tuser)request.getSession().getAttribute("logineduser");
		if(user.getRights()!=null&&user.getRights()>1)
		{
			flag=false;
		}	
		empp.setRegisterID(user.getUid());
		list=this.getPpBiz().doSelectDailyRecord(flag, empp,beginTime,endTime,page);
		request.setAttribute("DailyRecordQuery", list);
		request.setAttribute("page", page);
		return mapping.findForward("DailyRecordQuery");
	}
	/**
	 * 删除每日产量
	 */
	public ActionForward deleteDailyRecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Integer	 id=Integer.parseInt(request.getParameter("id"));
		TemployeeProduct empp= this.getPpBiz().doSelectByid(id);
		this.getPpBiz().delete(id);
		Tuser user=(Tuser) request.getSession().getAttribute("logineduser");
		String context=user.getUid()+"删除记录："+empp.getTempolyee().getUid()+"-"+empp.getTpost().getId()+"-"
		+empp.getWorktime()+"小时-"+empp.getOutput();
//		DBLog4j log=new DBLog4j(request.getRealPath("/"));
//		log.send_database(context);
		Logs logInfo=new Logs(user.getEmp().getName(),new Date(),context);
		this.getLogBiz().doInsert(logInfo);
		return mapping.findForward("DailyRecordQuery");
	}
}