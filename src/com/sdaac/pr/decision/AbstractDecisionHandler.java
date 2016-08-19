package com.sdaac.pr.decision;

import java.util.HashMap;
import java.util.Map;
import com.sdaac.pr.Constant;
import com.sdaac.pr.approve.element.ApproveMembersElement;
import com.sdaac.pr.approve.element.FormControlElement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class AbstractDecisionHandler {
	
	private static int cost_ENG_capital = 100000;
	private static int cost_ENG_EXPNESE = 15000;
	private static String TO_DMG ="to Eng Dr/DGM";
	private static String TO_END ="toEnd";
	private static String TO_NEXT ="next";
	
	//Zhang Tao 张涛 >=CNY15,000
	private static Map<String,Integer> C_RoleLevle = new HashMap<String,Integer>();
	private static Map<String,Integer> E_RoleLevle = new HashMap<String,Integer>();
	
	public static final String DMG = "czbt6g";
//	static{
//		C_RoleLevle.put("DZHD7J", 15000);//张涛
//		C_RoleLevle.put("pzwthg", 50000);
//		C_RoleLevle.put("czbt6g", 2000);
//		C_RoleLevle.put("HZQ85D", 2000);
//		//C_RoleLevle.put("rj", 50000);
//		
//		E_RoleLevle.put("DZHD7J", 15000);
//		E_RoleLevle.put("pzwthg", 10000);
//		E_RoleLevle.put("czbt6g", 2000);
//		E_RoleLevle.put("HZQ85D", 2000);
//		
//	}
	
//	public String stepCapitalDMG(FormControlElement element,float costTotal){
//		String iscapital = element.getIscapital();	
//		if(element.isHAVCorPTC()//RJ or Yu Rui approved,
//				&& costTotal <= (iscapital.equals(Constant.iscapital)?cost_ENG_capital:cost_ENG_EXPNESE)){
//			return TO_END;
//		}else{			
//			return TO_DMG;
//		}			
//	}
	static{
		loadConf();
	}
	public AbstractDecisionHandler(){
		//loadConf();
	}
	
	private static void loadConf(){
		final String pre_C = "C_";
		final String pre_E = "E_";
		Properties properties = new Properties();
		//d:/Program Files/Apache Software Foundation/Tomcat 6.0/bin/
	    File file = new File("eprConf.properties");
	    
	    System.out.println(file.getAbsolutePath()); 
	    
	    try {
	        InputStream inputStream = new FileInputStream(file);
	        properties.load(inputStream);
	        Enumeration enu = properties.propertyNames();
	        while(enu.hasMoreElements()){
	        	String key = (String)enu.nextElement();
	        	String value = properties.getProperty(key);
	        	System.out.println("key :"+key+" value:"+value);
	        	if(key.startsWith(pre_C)){
	        		C_RoleLevle.put(key.split("_")[1], Integer.parseInt(value));
	        	}else if(key.startsWith(pre_E)){
	        		E_RoleLevle.put(key.split("_")[1], Integer.parseInt(value));
	        	}
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }catch (Exception e) {
	        //e.printStackTrace();
	    	System.out.println("init failure, check the conf  "+e.getMessage());
	    }
	}
	
	public String stepCapitalDMG(FormControlElement element, float costTotal,
			ApproveMembersElement approveMembersElement) {
		String iscapital = element.getIscapital();
		String issuefor = element.getIssueFor();
		
		Integer targetCost = 0;
		String dgm = approveMembersElement.getDGM();

		if (iscapital != null && iscapital.equals(Constant.iscapital)) {
			targetCost = C_RoleLevle.get(dgm);
			
		} else {
			targetCost = E_RoleLevle.get(dgm);
		}
		if(targetCost == null) targetCost = 0;
		//todo : hard code for expense dgm if plant is SY or YT , cost = 2000
		if(dgm!=null && dgm.equals(DMG) && issuefor!=null && (issuefor.equals("2") || issuefor.equals("3"))){
			targetCost = 2000;
		}
		if (dgm != null && !"".equals(dgm)) {
			if (costTotal >= targetCost) {
				return TO_DMG;
			} else {
				return TO_END;
			}
		}else{
			return TO_END;
		}

	}
	
	public String stepCapitalDMG2(FormControlElement element, float costTotal,
			ApproveMembersElement approveMembersElement) {
		String iscapital = element.getIscapital();
		int targetCost = 0;
		String dgm = approveMembersElement.getDGM();

		if (iscapital != null && iscapital.equals(Constant.iscapital)) {
			targetCost = C_RoleLevle.get(dgm);
			
		} else {
			targetCost = E_RoleLevle.get(dgm);
		}
		
		//todo : hard code for expense dgm if plant is SY or YT , cost = 2000
		String issuefor = element.getIssueFor();
		if(dgm!=null && dgm.equals(DMG) && issuefor!=null && (issuefor.equals("2") || issuefor.equals("3"))){
			targetCost = 2000;
		}
		
		if (dgm != null && !"".equals(dgm)) {
			if (costTotal >= targetCost) {
				return TO_DMG;
			} else {
				return TO_NEXT;
			}
		}else{
			return TO_NEXT;
		}

	}
}
