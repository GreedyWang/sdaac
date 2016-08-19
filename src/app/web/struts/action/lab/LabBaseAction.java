package app.web.struts.action.lab;

import org.springframework.web.struts.DispatchActionSupport;

import sdaac.wym.app.Service.Lab.ILabM;
import sdaac.wym.app.Service.Lab.LabManager;
import sdaac.wym.app.Service.Lab.OLabM;

public class LabBaseAction extends DispatchActionSupport {
	private  ILabM ilabM =null;
	private  OLabM olabM =null;
	private LabManager labM = null;
//	private LabRightsManager lrm;
	
	public LabManager getLabM() {
		labM=(LabManager)this.getWebApplicationContext().getBean("LabManager");
		return labM;
	}
	
//	public LabRightsManager getLRM() {
//		lrm=(LabRightsManager)this.getWebApplicationContext().getBean("labRightsManager");
//		return lrm;
//	}
	
	public LabManager getSLabM() {
		labM=(LabManager)this.getWebApplicationContext().getBean("SLabManager");
		return labM;
	}
	
	public OLabM getOlabM() {
		olabM=(OLabM)this.getWebApplicationContext().getBean("oLabManager");
		return olabM;
	}

	public ILabM getIlabM() {
		ilabM=(ILabM)this.getWebApplicationContext().getBean("iLabManager");
		return ilabM;
	}
}
