package sdaac.wym.app.Service.KPI;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.ToolMain;

import app.biz.EmpBiz;
import app.biz.EmpIndexBiz;
import app.biz.impl.EmpBizImpl;
import app.biz.impl.EmpIndexBizImpl;
import app.entity.TempIndex;
import app.entity.Tempolyee;

/**
 * response to KPI Business Method
 * @author SA1KV5
 *
 */
public class KPIManager {
	
	private ExcelReader4PKI reader = new ExcelReader4PKI();
	private EmpBiz empBiz;
	private EmpIndexBiz empIndexBiz;
	private Logger log = Logger.getLogger(KPIManager.class);

	public EmpBiz getEmpBiz() {
		return empBiz;
	}
	public void setEmpBiz(EmpBiz empBiz) {
		this.empBiz = empBiz;
	}
	public EmpIndexBiz getEmpIndexBiz() {
		return empIndexBiz;
	}
	public void setEmpIndexBiz(EmpIndexBiz empIndexBiz) {
		this.empIndexBiz = empIndexBiz;
	}
	/** 
	 * bulk update KPI
	 */
	public void doUpdate(String fileName) {
		//step 1 : read date from excel		
		List<TempIndex> rs = reader.doUpdate(fileName);
//		if (rs.get(0).getTempolyee().getUid()!=null && !rs.get(0).getTempolyee().getUid().endsWith("")) {
			List<Tempolyee> t = empBiz.selectFuzzy(rs.get(0).getTempolyee());
		
		String uid="",version="2011-05";
		if(t!=null && t.size()==1) {
			uid = t.get(0).getUid();
			//step 3 : update - first delete 
			empIndexBiz.doDeletebyUidnVersion(uid,version);
			for(TempIndex item : rs) {
				//step 2 : add more info as userID by Name,if didn't get write to the log
				item.getTempolyee().setUid(uid);
				item.getIndex().setVersion(version);	
				//step 3 : update - than insert
				empIndexBiz.doInsert(item);
			}
		}else{
			log.error("this username is not found :"+rs.get(0).getTempolyee().getName()+",uid:"+rs.get(0).getTempolyee().getUid());
		}		
	}
	
	public void doBulkUpdate(String directName){
		File file = new File(directName); 
		String[] filelist = file.list(); 
		for(String name : filelist){
			System.out.println("---start ---"+name);
			doUpdate(directName+"/"+name);
			System.out.println("---end---");
//			new File(directName+"/"+name).delete();
		}

		
	}
	
	public static void main(String[] args) {
	  ApplicationContext context= new ClassPathXmlApplicationContext("applicationContext.xml");
	  KPIManager manager=(KPIManager) context.getBean("KPIManager");
	  manager.doBulkUpdate("E:/KPI_Data");
	  
		
	}

}
