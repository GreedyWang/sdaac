/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package app.epor.struts.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import sdaac.wym.app.Service.PCL.MainData.MDForm;

import app.entity.epor.PrApprovedForm;
import app.entity.epor.PrBuyContext;
import app.entity.epor.PrPrForm;
import app.entity.epor.forms.SingleForm;

/** 
 * MyEclipse Struts
 * Creation date: 04-22-2009
 * 
 * XDoclet definition:
 * @struts.form name="eporForm"
 */
public class EporForm extends ActionForm {
	/*
	 * Generated Methods
	 */
	private FormFile formFile;
	private FormFile bigcountFile;
	private FormFile urgencyFile;
	private FormFile singlesourceFile;
	private FormFile buyerFile;
	private PrApprovedForm approvedPR=new PrApprovedForm();
	private PrPrForm prForm=new PrPrForm();
	private PrBuyContext buyContext=new PrBuyContext();
	private MDForm mdform = new MDForm();

	private SingleForm singleForm = new SingleForm();

	public MDForm getMdform() {
		return mdform;
	}

	public void setMdform(MDForm mdform) {
		this.mdform = mdform;
	}

	/**getter & setter*/
	
	public PrPrForm getPrForm() {
		return prForm;
	}

	public void setPrForm(PrPrForm prForm) {
		this.prForm = prForm;
	}

	public PrBuyContext getBuyContext() {
		return buyContext;
	}

	public void setBuyContext(PrBuyContext buyContext) {
		this.buyContext = buyContext;
	}

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

	public PrApprovedForm getApprovedPR() {
		return approvedPR;
	}

	public void setApprovedPR(PrApprovedForm approvedPR) {
		this.approvedPR = approvedPR;
	}

	public FormFile getFormFile() {
		return formFile;
	}

	public void setFormFile(FormFile formFile) {
		this.formFile = formFile;
	}

	public FormFile getBigcountFile() {
		return bigcountFile;
	}

	public void setBigcountFile(FormFile bigcountFile) {
		this.bigcountFile = bigcountFile;
	}

	public FormFile getUrgencyFile() {
		return urgencyFile;
	}

	public void setUrgencyFile(FormFile urgencyFile) {
		this.urgencyFile = urgencyFile;
	}

	public FormFile getSinglesourceFile() {
		return singlesourceFile;
	}

	public void setSinglesourceFile(FormFile singlesourceFile) {
		this.singlesourceFile = singlesourceFile;
	}

	public FormFile getBuyerFile() {
		return buyerFile;
	}

	public void setBuyerFile(FormFile buyerFile) {
		this.buyerFile = buyerFile;
	}

	public SingleForm getSingleForm() {
		return singleForm;
	}

	public void setSingleForm(SingleForm singleForm) {
		this.singleForm = singleForm;
	}

}