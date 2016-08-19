package sdaac.wym.common.Service.Mail;

import java.util.HashMap;
import java.util.List;

import sdaac.wym.app.Service.PCL.MainData.BaseForm;
import sdaac.wym.app.Service.PCL.MainData.MDForm;
import sdaac.wym.app.Service.PCL.MainData.MDStateManager;
import sdaac.wym.app.Service.PCL.MainData.iManager;

import app.entity.Tempolyee;
import app.entity.epor.StateManager;

/**
 * Material Master Mail
 * 
 * @author SA1KV5
 * 
 */
public class MMMailManager extends MailManager {
	
	private iManager mana;
	
	public iManager getMana() {
		return mana;
	}

	public void setMana(iManager mana) {
		this.mana = mana;
	}
	
	public MMMailManager() {
		states = MDStateManager.states;
	}
	@Override
	public String getBusinessContext(int state, Tempolyee role,
			String otherConditions) {
		// TODO Auto-generated method stub
		
		MDForm item = new MDForm();
		item.setState(state);
		if(state == 1){
			item.getTempolyeeByApplicantId().setTdepartment(role.getTdepartment());
		}else if(state == 4 || state == 7) {
			item.setBuyerName(role.getName());
		}
		
		StringBuffer context = new StringBuffer();
		List<BaseForm> rs = mana.doShow(item);
		for(BaseForm bf : rs) {
			context.append("<tr><td>Indirect Material Master </td>"); // Business
			context.append("<td>"+bf.getUuid()+"</td>");
			context.append("<td>"+bf.getApplicateDate()+"</td>");
			context.append("<td>"+bf.getTempolyeeByApplicantId().getName()+"</td></tr>");
		}
		return context.toString();
	}
	
	
}
