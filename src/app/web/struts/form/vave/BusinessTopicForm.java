/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package app.web.struts.form.vave;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import sdaac.wym.app.entity.vave.BusinessTopic;

/** 
 * MyEclipse Struts
 * Creation date: 03-09-2010
 * 
 * XDoclet definition:
 * @struts.form name="businessTopicForm"
 */
public class BusinessTopicForm extends ActionForm {
	/*
	 * Generated Methods
	 */
	private BusinessTopic bt = new BusinessTopic();
	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	public BusinessTopic getBt() {
		return bt;
	}

	public void setBt(BusinessTopic bt) {
		this.bt = bt;
	}
}