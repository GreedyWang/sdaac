package app.web.struts.action.hr;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import app.web.struts.action.BaseAction;

public class ChejianjiangjinAction extends BaseAction {
	
	/**
	 * 搜索加班时间
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doConfirmFinish (ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String productArea = request.getParameter("productArea");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		productArea = "1";
		beginTime = "2010-10-25";
		endTime = "2010-10-31";
		Map<Object,  List<Object[]>> rs = this.getOvertimeManager().search(productArea, beginTime, endTime);
		request.setAttribute("map",rs );
		return mapping.findForward("overtimeManager");
	}
	
	

}
