/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package app.web.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import app.web.struts.form.OSForm;

/** 
 * MyEclipse Struts
 * Creation date: 06-18-2009
 * 
 * XDoclet definition:
 * @struts.action path="/oS" name="oSForm" input="/form/oS.jsp" parameter="actionType" scope="request" validate="true"
 */
public class OSAction extends BaseAction {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward selectBy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OSForm oSForm = (OSForm) form;// TODO Auto-generated method stub
		List<Integer[]> postioList= this.getOsManager().getPostion();
		request.setAttribute("postioList", postioList);
		return mapping.findForward("OsManager");
	}
}