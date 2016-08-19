package app.common.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

import app.entity.Tempolyee;
import app.entity.epor.PrCostCenter;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

public class ToJson {
	
	public static void listToJsonArrart(Object list,HttpServletResponse response)
	{
		JSONArray ja=JSONArray.fromObject(list);
		PrintWriter out;
		try {
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			out.print(ja.toString()); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void listToJsonArrartAddTotalCount(Object list,HttpServletResponse response,int totalCount)
	{
		JSONArray ja=JSONArray.fromObject(list);
		PrintWriter out;
		try {
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			out.print("{totalCount:"+totalCount+",root:"+ja.toString()+"}"); 

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void listToJsonArrartWithConf(Object list,HttpServletResponse response,JsonConfig jcf)
	{
//		JsonConfig jsonConfig=new JsonConfig();
//		jsonConfig.setExcludes(infos);
		JSONArray ja=JSONArray.fromObject(list,jcf);
		PrintWriter out;
		try {
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			out.print(ja.toString()); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
