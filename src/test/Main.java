package test;

import java.io.IOException;
import java.util.List;
import javax.naming.NamingException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.biz.EmpBiz;
import app.entity.Tempolyee;
import app.entity.Tuser;

public class Main {

    public static void main(String[] args) {

    			

    	String sql = "select * from (";
    	String tmp="";
    	String value = "10/29/2012	10/30/2012	10/31/2012	11/01/2012	11/02/2012	11/03/2012	11/04/2012	11/05/2012	11/06/2012	11/07/2012	11/08/2012	11/09/2012	11/10/2012	11/11/2012	11/12/2012	11/19/2012	11/26/2012	12/01/2012	12/03/2012	12/10/2012	12/17/2012	12/24/2012	12/31/2012";
    	for(String aa : value.split("	")){
    		
    		String bb = aa.split("\\/")[1]+"/"+aa.split("\\/")[0]+"/"+aa.split("\\/")[2];
//    		String cc = aa.split("\\.")[0]+"#"+aa.split("\\.")[1]+"#"+aa.split("\\.")[2];
    		sql +="select Plant,[ship to],part,[Dist# Chan],日期 = '"+aa+"' , value = ["+aa+"] from cd union all ";
    	}
    	sql +=" ) t";
    	System.out.print(sql);
    	//=====synch testing 
//    	trail("NotSynch",CALL_COUNT,new NotSynch());
//    	trail("Synch",CALL_COUNT,new Synch());
    	
    	
    }
    private static void trail(String msg,Long count ,Object obj) {
    	long start_time = System.currentTimeMillis();
    	for(long i = 0;i<count;i++) {
    		obj.toString();
    	}
    	System.out.println(msg + ": END");
    	System.out.println("Elapsed tiem = "+(System.currentTimeMillis() - start_time) + "msec.");
    }
    private static final Long CALL_COUNT = 1000000000L;
}
class NotSynch {
	private final String name = "NotSynch";
	public String toString() {
		return "["+name+"]";
	}
}

class Synch {
	private final String name = "Synch";
	public synchronized String toString() {
		return "["+name+"]";
	}
}

