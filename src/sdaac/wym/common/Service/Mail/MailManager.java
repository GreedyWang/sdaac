package sdaac.wym.common.Service.Mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.entity.Tempolyee;
import app.entity.epor.StateManager;

import sdaac.wym.app.Service.PCL.MainData.MDStateManager;
import sdaac.wym.common.Service.PrRightsManager;

import common.entity.MyMail;
import common.entity.TuserRole;

/**
 * Mail Manager Base Class
 * 
 * @author SA1KV5
 * 
 */
public class MailManager implements IMailManager {

	private PrRightsManager rightsManager;
	private Map<String, MyMail> mailMap; // Result
	HashMap<Integer, StateManager> states; // MM's all

	// states
	// info
	
	public void combineContext(Map<String, MyMail> a, Map<String, MyMail> b) {
		
	}
	
	public List<MyMail> toList(Map<String, MyMail> mailMap3){
		List<MyMail> mailList = new ArrayList();
		for (String key : mailMap3.keySet()) {
			mailList.add(mailMap3.get(key));
		}
		return mailList;
	}
	
	@Override
	public List<MyMail> doSelectToMailTip() {
		mailMap = new HashMap<String, MyMail>();
		// TODO Auto-generated method stub
		for (Integer stateId : states.keySet()) {
			StateManager sm = states.get(stateId);
			int curState = sm.curState;
			String roleName = sm.role;

			List<TuserRole> useRoles = rightsManager
					.doSelectByRoleName(roleName);
			for (TuserRole useRole : useRoles) {

				if (mailMap.get(useRole.getTempolyee().getUid()) == null) { //map里没有数据
					String aa = getBusinessContext(curState, useRole.getTempolyee(), "");
					if(!aa.equals("")) {
						String context = "<tr><td>流程类型</td><td>流水号/SSID</td><td>申请日期/application date</td><td>申请人/applicant</td></tr>";
						context += aa;
						MyMail mail = new MyMail();
						mail.setContext(context);
						mail.setType("Indirect Material Master");
						mail.setEmp(useRole.getTempolyee());
						mailMap.put(useRole.getTempolyee().getUid(), mail);
					}
				} else {
					MyMail mail = mailMap.get(useRole.getTempolyee().getUid());
					String context = mail.getContext();
					mail.setContext(context+ getBusinessContext(curState, useRole.getTempolyee(), ""));
					
				}

			}
		}
		return toList(mailMap);
	}

	/**
	 * 
	 * @param state
	 * @param role
	 * @param otherConditions
	 * @return
	 */
	public String getBusinessContext(int state, Tempolyee role,
			String otherConditions) {
		return "";
	}
	
	public static void main(String[] args) {
      ApplicationContext context= new ClassPathXmlApplicationContext("applicationContext.xml");
      IMailManager aa=(MMMailManager) context.getBean("MMMailManager");
      List<MyMail> rs = aa.doSelectToMailTip();
      for(MyMail mail : rs) {
    	  System.out.println(mail.getEmp().getName()+":"+mail.getContext());
      }
	}

	public PrRightsManager getRightsManager() {
		return rightsManager;
	}

	public void setRightsManager(PrRightsManager rightsManager) {
		this.rightsManager = rightsManager;
	}
}
