package test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.biz.PostBiz;

import sdaac.wym.app.Hr.BounceFigureImpl;
import sdaac.wym.app.Hr.EmpSourceManager;
import sdaac.wym.app.entity.hr.BounceFigure;

import common.util.ExcelReader;

public class ToolMain {
	
	/**
	 * 导入图号数据，之前先在数据库中确认好工序
	 * @param args
	 * @throws Exception
	 */
	public  void addHB() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		BounceFigureImpl ma=(BounceFigureImpl)ac.getBean("bfBiz");
		//String contexts[][]
		String fileName="d:/102.xls";
		ExcelReader excelReader = new ExcelReader(fileName);		                  
		List<Object[]> results = excelReader.getList();                  
		for(Object[] aa:results){
			
			BounceFigure item = new BounceFigure();
			item.setFigureId(aa[1].toString());
			item.setRemark(aa[1].toString());
			item.setCarType(aa[0].toString());
			item.setStandWorkTime(Float.parseFloat(aa[2].toString()));
			item.setType("零件");
			item.setProductArea("102");
			//System.out.println(aa[0]+":"+aa[1]);
			ma.addBounceFigure(item);
		}
		
	}
	
	/**
	 * 同步HR数据库
	 * @param args
	 */
	public void empSourceManagerSynchronization() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		EmpSourceManager em=(EmpSourceManager)ac.getBean("empSourceManager");
		em.Synchronization();
	}
	
	/**
	 * 同步HR数据库
	 * @param args
	 */
	public void empSourceManagerSynchronizationSY() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		EmpSourceManager em=(EmpSourceManager)ac.getBean("empSourceManager");
		em.SynchronizationSY();
	}
//	synMail
	public void empSourceManagerSynMail() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		EmpSourceManager em=(EmpSourceManager)ac.getBean("empSourceManager");
		em.synMail();
	}
	
	/**
	 * 104B拷贝图号
	 * @param args
	 */
	public void Copy104B() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		PostBiz manager = (PostBiz)ac.getBean("postBiz");
		//new old productArea     
		
//		manager.doCopy("16458613", "52439675", "104B","104A");
//		manager.doCopy("16458614", "52439675", "104B","104A");
//		manager.doCopy("16465885", "05400143", "104A");
//		manager.doCopy("16465886", "05400144", "104A");
//		manager.doCopy("16465887", "05400144", "104A");
//		manager.doCopy("16465888", "05400144", "104A");
//		manager.doCopy("16465915", "05400123", "104A");
//		manager.doCopy("16465916", "05400143", "104A");
//		manager.doCopy("16465917", "05400144", "104A");

	}
	
	public static void main(String[] args) {
		ToolMain tm = new ToolMain();
//		tm.empSourceManagerSynMail();
//		tm.Copy104B();
		tm.empSourceManagerSynchronization();
//		tm.empSourceManagerSynchronizationSY();
	}

}
