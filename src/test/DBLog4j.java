package test;
import   org.apache.log4j.*; 
public class DBLog4j {
	Logger   loger=Logger.getLogger(DBLog4j.class.getName());    
	public DBLog4j(String path)
	{
		PropertyConfigurator.configure(path+"WEB-INF/classes/loger_dabase.property");   
		// PropertyConfigurator.configure("src/loger_dabase.property");   
	}
    public   void   send_database(String context){   
    loger.debug(context);     
    }   
    public static void main(String[] args) {
    	DBLog4j dd=new DBLog4j("dd");
    	dd.send_database("test");
	}
}
