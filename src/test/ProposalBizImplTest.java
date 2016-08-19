package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.hibernate.SessionFactory;
//import org.hibernate.stat.Statistics;
//import org.h
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sdaac.wym.app.Service.OSManager;

import app.biz.IndexTargetBiz;
import app.biz.vave.ProposalBiz;
import app.entity.Tempolyee;
import app.entity.Tproposal;

import junit.framework.TestCase;

public class ProposalBizImplTest extends TestCase {
ApplicationContext context=	new ClassPathXmlApplicationContext("applicationContext.xml");
	private ProposalBiz item=	(ProposalBiz)context.getBean("proposalBiz");
//	private SessionFactory sf=	(SessionFactory)context.getBean("SessionFactory");
	private IndexTargetBiz indexBiz=(IndexTargetBiz)context.getBean("indexBiz");
	
	private OSManager osManager=(OSManager)context.getBean("osManager");
	Tproposal tproposal=new Tproposal();
//	public void testDoSelect() {
//		//fail("尚未实现");
//		tproposal.setId("id");
//		tproposal.setLastModifyTime(new Date());
//		tproposal.setTitle("test");
//		tproposal.setState(1);
//		this.assertNotNull(	item.doSelect(tproposal));
//	}
//	
//	public void testDoInsert()
//	{
//		tproposal.setId("gid");
//		tproposal.setLastModifyTime(new Date());
//		tproposal.setTitle("test");
//		tproposal.setState(1);
//		tproposal.setProposalPerson(new Tempolyee("8002"));
//		tproposal.setCollectionPersion(new Tempolyee("8002"));
//		this.assertTrue(item.doInsert(tproposal));
//	}
	public void testDoInsertFlush()
	{
		//this.assertNotNull(item.doSelectCompany(null));
//		Statistics s= sf.getStatistics();
//		System.out.println(s.getSessionCloseCount());;
//		System.out.println(s.getSessionOpenCount());;
		//indexBiz.createNewIndexLib();
		List<Integer[]> ss=osManager.getPostion();
		System.out.println(ss.size());
		
	}
}
