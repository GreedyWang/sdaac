package com.sdaac.pr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jbpm.api.task.Task;

import app.biz.impl.epor.FormService;
import app.entity.epor.PrPrForm;

import com.sdaac.pr.approve.PRProcessEngine;
import com.sdaac.pr.approve.element.FormControlElement;
import com.security.biz.UserRoleBiz;

import common.entity.MyMail;
import common.entity.TuserRole;
import common.util.mail.DataCollectioner;

public class MailProvider4NPR extends DataCollectioner{
	
	private UserRoleBiz urBiz;
	private PRProcessEngine processEng;
	private FormService formService;
	@Override
	protected List<MyMail> doSelectToMailTip() {
		mailList = new ArrayList<MyMail>();
		Map<String,List<MyMail>> map = new HashMap<String,List<MyMail>>();
		// TODO Auto-generated method stub
//		1.select all new pr roles
		String typeName = "newpr";
		List<TuserRole> urs = urBiz.doSelectByCondition(typeName);
//		2.get the uid
//		3.get task size	
		String title = "<tr><td>Initiator</td><td>Dept.</td>" +
				"<td>Description</td>" +
				"<td>Amount</td>" +
				"<td>Cap./Exp.</td>" +
				"<td>Detail</td>" +			
				"<td>PR No.</td>"+
				"</tr>";
		Set<String> netids = new HashSet<String>();
//		for(TuserRole item:urs){
//			try{
//			
//			if(item.getTempolyee()!=null && item.getTempolyee().getNetid()!=null){
//				netids.add(item.getTempolyee().getNetid());
//			}}catch(Exception ex){
//				System.out.println(item.getTempolyee().getUid());
//			}
//		}
		for(TuserRole item:urs){
			if(item.getTempolyee()!=null){
				//System.out.println("=============================>"+item.getTempolyee().getUid());
				try{
					String netid = item.getTempolyee().getNetid();
					if(netids.contains(netid)){
						continue;
					}else{
						netids.add(netid);
					}
					//String netid = "kz8dx0";
					List<Task> tasks = processEng.doQueryApprovingList4Mail(netid);
					if(!tasks.isEmpty()){
						MyMail mail = new MyMail();
						mail.setEmp(item.getTempolyee());
						mail.setContext(title);
						String taskids="'a'";
						for(Task task:tasks){
							taskids+=",'"+task.getExecutionId()+"'";		
						}
						List<PrPrForm> rs = formService.doSelectByProcessEngIds(taskids);
						if (rs != null && !rs.isEmpty()) {
							boolean needSend = false;
							for (PrPrForm form : rs) {
								//System.out.println(form.getSsid()+":"+form.getInfo());
								if(form!=null && form.getInfo() == 0 ){
									mail.setContext(mail.getContext()
											+ "<tr><td>"
											+ form.getTempolyeeByApplicantId()
													.getName()
											+ "</td><td>"
											+ form.getTempolyeeByApplicantId()
													.getTdepartment().getName()
											+ "</td><td>" + form.getRemark()
											+ "</td><td>" + form.getTotal()
											+ "</td><td>" + form.getIsCapitalName()
											+ "</td><td>" + form.getShowDate()
											+ "</td><td>" + "15"
											+ (form.getSsid() - 21498) + "</td>");
									needSend = true;
								}
							}
							if(needSend){
								// mailList.add(mail);
								if (map.containsKey(netid)) {
									map.get(netid).add(mail);
								} else {
									List<MyMail> list = new ArrayList<MyMail>();
									list.add(mail);
									map.put(netid, list);
								}
							}
						}
					}
				}catch(Exception ex){
					System.out.println(ex);
				}
			}
		}
		if(map!=null && !map.isEmpty()){
			for(List<MyMail> list :map.values()){
				mailList.addAll(list);
			}	
		}
		return mailList;
	}
	
	
	public UserRoleBiz getUrBiz() {
		return urBiz;
	}
	public void setUrBiz(UserRoleBiz urBiz) {
		this.urBiz = urBiz;
	}
	public PRProcessEngine getProcessEng() {
		return processEng;
	}
	public void setProcessEng(PRProcessEngine processEng) {
		this.processEng = processEng;
	}
	public FormService getFormService() {
		return formService;
	}
	public void setFormService(FormService formService) {
		this.formService = formService;
	}

}
