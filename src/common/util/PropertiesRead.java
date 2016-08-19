package common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesRead {
	
    /** 

     * 读取配置文件 

     */ 

    private PropertiesRead() { 



        try { 

//          FileInputStream is=new FileInputStream("config.properties"); 

            InputStream is=this.getClass().getResourceAsStream("/runtime.properties"); 

            prop.load(is); 

            is.close(); 

        } catch (IOException e) { 

            e.printStackTrace(); 

        } 

        //return prop; 

    } 
    
    private static Properties prop=new Properties(); 
    
    private static PropertiesRead instance = new PropertiesRead();
    
    public static PropertiesRead getInstance(){
    	return instance;
    }
    
    public String getValue(String key){
    	return prop.getProperty(key);
    }
    
    
    public boolean isRelease(){
    	try{
    		String env = getValue(Keys.env.name());
    		if(env != null && env.equalsIgnoreCase("test")){
    			return false;
    		}
    	}catch(Exception ex){
    		
    	}
    	return true;
    }
    
    public enum Keys{
    	env
    }
    
    public static void main(String[] args) {
    	System.out.print(PropertiesRead.getInstance().getValue("env"));
    	//System.out.print(PropertiesRead.getInstance().getEnv());
	}
}
