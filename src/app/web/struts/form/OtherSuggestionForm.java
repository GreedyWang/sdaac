/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package app.web.struts.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import app.entity.TotherSuggestion;

/** 
 * MyEclipse Struts
 * Creation date: 03-06-2009
 * 
 * XDoclet definition:
 * @struts.form name="otherSuggestionForm"
 */
public class OtherSuggestionForm extends ActionForm {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	private TotherSuggestion item=new TotherSuggestion();
	
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

	public TotherSuggestion getItem() {
		return item;
	}

	public void setItem(TotherSuggestion item) {
		this.item = item;
	}
}