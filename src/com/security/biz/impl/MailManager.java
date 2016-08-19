package com.security.biz.impl;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sdaac.pr.MailProvider4NPR;

import sdaac.wym.app.Service.Room.RoomMailManager;
import sdaac.wym.common.Service.Mail.IMailManager;
import sdaac.wym.common.Service.Mail.MMMailManager;

import app.biz.impl.epor.PRMailManager;
import app.entity.Tempolyee;

//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;


import org.apache.log4j.Logger;
import common.dao.CommonDAO;
import common.entity.MyMail;
import common.util.SdaacMail;
import common.util.mail.DataCollectioner;
import common.util.mail.PRAll;
import common.util.mail.PRBuyer;
import common.util.mail.PRDepartment;
import common.util.mail.PRProject;
import common.util.mail.PRSelf;

public class MailManager {
	
	private CommonDAO<MyMail> myMailDao;
	private PRMailManager prMailManager;
	private RoomMailManager rmManager;
	private IMailManager mmMailManager;
	private PRAll prAll;
	private MailProvider4NPR npr;
	private PRDepartment prDep;
	private PRBuyer prBuyer;
	private PRProject prProject;
	private PRSelf prSelf;
	private Logger logger = Logger.getLogger(MailManager.class);
	public CommonDAO<MyMail> getMyMailDao() {
		return myMailDao;
	}

	public void setMyMailDao(CommonDAO<MyMail> myMailDao) {
		this.myMailDao = myMailDao;
	}
	
	public List<MyMail> doSelectAllMail(){
		//String hql="from MyMail where ";
		return myMailDao.selectAll();
	}
	
	/**
	 * 添加邮件到邮件列
	 */
	public void addMailList(MyMail item)
	{
		if(item.getEmp()!=null&&item.getEmp().getUid()!=null&&!item.getEmp().getUid().equals("")){
			this.myMailDao.insert(item);
		}
	}
	
	
	/**
	 * 把邮件设为已发送
	 */
	private void changeMailState(MyMail item,String[] delayTypes)
	{
		String hql="update  MyMail set isSended=1 ,sendTime=? where isSended=0 and emp.uid=? and type not in ?";
		myMailDao.bulkUpdate(hql, new Object[]{item.getSendTime(),item.getEmp().getUid(),delayTypes});
	}
	
	public void changeMailStateSingle(MyMail item)
	{
		String hql="update  MyMail set isSended=1 ,sendTime=? where isSended=0 and emp.uid=? and context like ?";
		myMailDao.bulkUpdate(hql, new Object[]{item.getSendTime(),item.getEmp().getUid(),item.getContext()});
	}
	
	/**
	 * 发邮件
	 * PR_部门经理 级其他状态
	 */
	public void sendMail() {
		DataCollectioner manager1 = npr;
		List<MyMail> rs1 = manager1.doALL();		
		for(MyMail item :rs1){
			item.getEmp().setMail(item.getEmp().getMail());
			try {
				if(item.getEmp().getUid()!=null) {
					SdaacMail.send(item);
					logger.error(item.getEmp().getName());
				}				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.warn("Need Mail Address for "+item.getEmp().getUid());
			}
		}		
	}
	


	
	/**
	 * 发邮件
	 * PR——采购员;项目经理
	 */
	public void sendMailStep2()
	{
		DataCollectioner manager1 = prBuyer;
		List<MyMail> mailList = manager1.doALL();	
		List<MyMail> mailList2 = prSelf.doALL();
		mailList.addAll(mailList2);	
		for(MyMail item :mailList){
			item.getEmp().setMail(item.getEmp().getUid());
//			if(item.getEmp().getMail()!=null) {
//				SdaacMail.send(item);
//					System.out.println(item.getEmp().getMail());
//			}
			try {
 				if(item.getEmp().getMail()!=null) {
					SdaacMail.send(item); 					
				}				
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				logger.warn("Need Mail Address for "+item.getEmp().getUid());
			}
		}
	}
		
	public static void main(String[] args) {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		MailManager aa=(MailManager)ac.getBean("mailBiz");
			aa.sendMail();		
	}

	public PRMailManager getPrMailManager() {
		return prMailManager;
	}

	public void setPrMailManager(PRMailManager prMailManager) {
		this.prMailManager = prMailManager;
	}

	public RoomMailManager getRmManager() {
		return rmManager;
	}

	public void setRmManager(RoomMailManager rmManager) {
		this.rmManager = rmManager;
	}

	public IMailManager getMmMailManager() {
		return mmMailManager;
	}

	public void setMmMailManager(IMailManager mmMailManager) {
		this.mmMailManager = mmMailManager;
	}

	public PRAll getPrAll() {
		return prAll;
	}

	public void setPrAll(PRAll prAll) {
		this.prAll = prAll;
	}

	public PRDepartment getPrDep() {
		return prDep;
	}

	public void setPrDep(PRDepartment prDep) {
		this.prDep = prDep;
	}

	public PRBuyer getPrBuyer() {
		return prBuyer;
	}

	public void setPrBuyer(PRBuyer prBuyer) {
		this.prBuyer = prBuyer;
	}

	public PRProject getPrProject() {
		return prProject;
	}

	public void setPrProject(PRProject prProject) {
		this.prProject = prProject;
	}

	public PRSelf getPrSelf() {
		return prSelf;
	}

	public void setPrSelf(PRSelf prSelf) {
		this.prSelf = prSelf;
	}

	public MailProvider4NPR getNpr() {
		return npr;
	}

	public void setNpr(MailProvider4NPR npr) {
		this.npr = npr;
	}


}
