/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package app.web.struts.action.SAP;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.springframework.web.struts.DispatchActionSupport;

import common.util.download.UploadFile;

import sdaac.wym.app.Service.SAP.BaseDataMaintance.Manager;


/** 
 * MyEclipse Struts
 * Creation date: 12-18-2011
 * 
 * XDoclet definition:
 * @struts.action path="/sap" name="sapForm" input="/form/sap.jsp" parameter="actionType" scope="request" validate="true"
 */
public class SapAction extends DispatchActionSupport {
	/*
	 * Generated Methods
	 */
	private static String uploadUrl="D:\\Apache Software Foundation\\Tomcat 6.0\\webapps\\uploadFolder";
	/** 
	 * 上传数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward doUpload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SapForm sapForm = (SapForm) form;// TODO Auto-generated method stub
		FormFile file = sapForm.getFormFile();
		String filePath =uploadUrl;// request.getRealPath("/uploadFolder");//上传到指定的upload包中
		String type = request.getParameter("type");
		
		Manager ma = new Manager();
		
		UploadFile uf = new UploadFile();
		String fileName = uf.uFile(file, filePath, "tt");
		System.out.println(fileName);  
		ma.doUpload(fileName,type);
		request.setAttribute("result", "success");
		return mapping.findForward("rs");
	}
}