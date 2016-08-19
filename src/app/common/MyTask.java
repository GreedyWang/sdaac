package app.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.biz.IPTmp;
import app.biz.PersonalPBiz;
import app.entity.IP;
import app.entity.IpTmp;

public class MyTask  {

	private static PersonalPBiz ppBiz=null;
	private static IPTmp iptmpBiz=null;
	public MyTask()
	{
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		ppBiz=(PersonalPBiz) context.getBean("ppBiz");
		iptmpBiz=(IPTmp) context.getBean("ipTmpBiz");
	}

	public void run() {
		
		// TODO �Զ���ɷ������
System.out.println("===============MyTask==============");
Date thisDate=new Date();
SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
String myDate=sdf.format(thisDate);
Map<String ,List<IP>> map=ppBiz.insertIP(myDate);		
Set<String>set=map.keySet();
List<IpTmp> list=new ArrayList<IpTmp>();
for (String element : set) {
	IpTmp items=new IpTmp();
	items.setDate(new Date());
	items.setUid(element);			
	List<IP> el=map.get(element);
	float result=0.00f;
	for (IP object : el) {
			result+=object.getPtworktime()*object.getPtcount()/object.getSumt()/object.getTarget();
	}
	items.setIp(result);
	list.add(items);
}
	iptmpBiz.doFlushInsert(list);	
	}
public static void main(String[] args) {
	MyTask d=new MyTask();
	d.run();
}
}
