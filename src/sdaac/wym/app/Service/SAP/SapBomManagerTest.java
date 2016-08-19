package sdaac.wym.app.Service.SAP;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class SapBomManagerTest extends TestCase {
	
	private SapBomManager ma;
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testFillIn() {
		ApplicationContext context= new ClassPathXmlApplicationContext("applicationContext.xml");
		SapBomManager ma = (SapBomManager) context.getBean("SapBomManager");
		ma.fillIn();
	}
	
//	public void testGetTotalPrice(){
//		//5400032
//		ApplicationContext context= new ClassPathXmlApplicationContext("applicationContext.xml");
//		SapBomManager ma = (SapBomManager) context.getBean("SapBomManager");
//		
//		ma.getTotalPrice("5400032");
//		System.out.print("Total Price is = "+ ma.getTotalPrice());
//	}
}
