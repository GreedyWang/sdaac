/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package app.web.struts.action.cw;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import sdaac.wym.app.entity.cw.CwLogs;
import sdaac.wym.app.entity.cw.Tax;

/** 
 * MyEclipse Struts
 * Creation date: 08-27-2009
 * 
 * XDoclet definition:
 * @struts.form name="cwForm"
 */
public class CwForm extends ActionForm {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	private Tax tax=new Tax();
	private CwLogs cwLogs = new CwLogs();
	//上传的txt
	private FormFile file ;
	private FormFile file2 ;
	
	public FormFile getFile2() {
		return file2;
	}

	public void setFile2(FormFile file2) {
		this.file2 = file2;
	}

	public Tax getTax() {
		return tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}

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

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	public CwLogs getCwLogs() {
		return cwLogs;
	}

	public void setCwLogs(CwLogs cwLogs) {
		this.cwLogs = cwLogs;
	}


}