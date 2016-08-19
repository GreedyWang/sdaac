package app.web.struts.action.meeting;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import sdaac.wym.app.Service.Room.Form;

public class RoomSystemForm extends ActionForm {
	
	private Form form = new Form();
	private FormFile formFile;
	public FormFile getFormFile() {
		return formFile;
	}

	public void setFormFile(FormFile formFile) {
		this.formFile = formFile;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}
}
