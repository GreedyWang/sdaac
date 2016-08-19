package sdaac.wym.app.Service.Room;

import java.util.Calendar;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.biz.EmpBiz;
import app.entity.Tempolyee;
import junit.framework.TestCase;

public class RoomManagerTest extends TestCase {
	
	ApplicationContext context= new ClassPathXmlApplicationContext("applicationContext.xml");
	RoomManager mamager=(RoomManager) context.getBean("RoomManager");
//	public void testDoCheckIn() {
//		Calendar ca = Calendar.getInstance();
//		ca.set(2011, 3, 1, 8, 0);
//		//ca.getTime();
//		Calendar ca2 = Calendar.getInstance();
//		ca2.set(2011, 3, 1, 8, 30);
//		
//		//fail("Not yet implemented");
//		Form item = new Form();
//		item.setEmp(new Tempolyee("1382"));
//		item.setBegintime(ca.getTime());
//		item.setEndtime(ca2.getTime());
//		item.setContext("this is a test");
//		item.setRoomId(1);
////		System.out.print(mamager.doCheckIn(item));
////		this.assertNotNull(mamager.doCheckIn(item));
//	}
//
//	public void testDoUpdate() {
//		Form item = new Form();
//		item.setId(1);
//		item.setId(1);
//	//	System.out.print(mamager.doUpdate(item));
//		this.ass (mamager.doUpdate(item));
//	}
////
//	public void testDoCancle() {
//		Form item = new Form();
//		item.setId(1);
//		System.out.print(mamager.doShowSingle(item));
//		this.assertNotNull(mamager.doShowSingle(item));
//	}
////
//	public void testDoShow() {
//		Form item = new Form();
//		System.out.print(mamager.doShow(item));
//		this.assertNotNull(mamager.doShow(item));
//	}
//	
	public void testDoShowSingle() {
		Form item = new Form();
		item.setId(1);
		System.out.print(mamager.doShowSingle(item));
		this.assertNotNull(mamager.doShowSingle(item));
	}

}
