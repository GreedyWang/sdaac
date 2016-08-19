package common.util;
import java.beans.IntrospectionException; 
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal; 
import java.math.BigInteger; 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set; 

import app.entity.Tempolyee;

import net.sf.json.JSONArray;
public class JsonUtil{
	
	  public static String object2json(Object obj) {  
		             StringBuilder json = new StringBuilder();  
		             if (obj == null) {  
		                 json.append("\"\"");  
		             }  else if (obj instanceof List) {  
		                json.append(list2json((List<?>) obj));  
		             } 
		             return json.toString();  
		         }  
	
	
     public static String list2json(List<?> list) {  
	             StringBuilder json = new StringBuilder();  
	             json.append("[");  
	             if (list != null && list.size() > 0) {  
	                 for (Object obj : list) {  
	                     json.append(object2json(obj));  
	                     json.append(",");  
	                 }  
	                 json.setCharAt(json.length() - 1, ']');  
	             } else {  
	                 json.append("]");  
	             }  
	             return json.toString();  
	         } 
     public static void main(String[] args) {
		
    	 Tempolyee emp= new Tempolyee();
    	 emp.setUid("8002");
    	 Tempolyee emp2= new Tempolyee();
    	 emp2.setUid("8002");
    	 List<Tempolyee> items =new ArrayList<Tempolyee>();
    	 items.add(emp);items.add(emp2);
    	 System.out.println(JSONArray.fromObject(items));
	}
}