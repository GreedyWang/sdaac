package app.web.struts.form;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import app.entity.TeamWork;
import app.entity.Tempolyee;


public class EmpForm extends ActionForm {

	private Tempolyee item=new Tempolyee();
	private TeamWork teamWork =new TeamWork();
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		// TODO �Զ���ɷ������		
		try
        {
			arg1.setCharacterEncoding("utf-8");
        }
        catch (UnsupportedEncodingException ex)
        {
        }

	}

	
	public ActionErrors validate(ActionMapping arg0, HttpServletRequest arg1) {
		// TODO �Զ���ɷ������
		return null;
	}

	public Tempolyee getItem() {
		return item;
	}

	public void setItem(Tempolyee item) {
		this.item = item;
	}


	public TeamWork getTeamWork() {
		return teamWork;
	}


	public void setTeamWork(TeamWork teamWork) {
		this.teamWork = teamWork;
	}

}
