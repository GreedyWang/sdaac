package app.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonValueProcessor implements JsonValueProcessor {
	 private final String format="yyyy-MM-dd";  
	     public Object processArrayValue(Object arg0, JsonConfig arg1) {  
	         // TODO Auto-generated method stub  
	         return null;  
	     }  
	   
	     public Object processObjectValue(String key, Object value,JsonConfig arg2) {  
	          if(value==null)    
	               return "";     
	           if (value instanceof Date) {     
	                String str = new SimpleDateFormat(format).format((Date) value);     
	                return str;     
	             }     
	          return value.toString();  
	     }
}
