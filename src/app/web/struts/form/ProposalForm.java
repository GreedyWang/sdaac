/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package app.web.struts.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import java.util.List;
import common.util.AutoArrayList;
import app.entity.Tprogram;
import app.entity.Tproposal;
import app.entity.Ttheme;

/** 
 * MyEclipse Struts
 * Creation date: 02-19-2009
 * 
 * XDoclet definition:
 * @struts.form name="perposalForm"
 */
public class ProposalForm extends ActionForm {
	/*
	 * Generated Methods
	 */
	private Tproposal item=new Tproposal();
	private Tprogram program =new Tprogram();
	
	private List<Ttheme> themes=new AutoArrayList(Ttheme.class);
	/** 
	 * Method validatethemes
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

	public Tproposal getItem() {
		return item;
	}

	public void setItem(Tproposal item) {
		this.item = item;
	}

	public Tprogram getProgram() {
		return program;
	}

	public void setProgram(Tprogram program) {
		this.program = program;
	}

	public List<Ttheme> getThemes() {
		return themes;
	}

	public void setThemes(List<Ttheme> themes) {
		this.themes.clear();
		this.themes.addAll(themes);
	}




}