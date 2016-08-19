package app.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import app.entity.Test;

public  class CountPostSalary {
	public static void count(List<Test> list,HttpServletRequest request)
	{
		//System.out.println("======================CountPostSalary========================");
		for (Test test : list) {
			float a=0;
			float b=0;
			float c=0;
			float d=0;
			float sum=0;
			Map<String,Float> abcd=new HashMap<String, Float>();
			if(null!=test.getTypeA())
			{
				 a=test.getTypeA();
				 sum+=a;
				 abcd.put("A", a);
			}
			if(null!=test.getTypeB())
			{
				 b=test.getTypeB();
				 sum+=b;
				 abcd.put("B", b);
			}
			if(null!=test.getTypeC())
			{
				 c=test.getTypeC();
				 sum+=c;
				 abcd.put("C", c);
			}
			if(null!=test.getTypeD())
			{
				 d=test.getTypeD();
				 sum+=d;
				 abcd.put("D", d);
			}
			
			Set<String> type=abcd.keySet();
			
		
			float salary=0;
			Map<String , Float[]> map=(Map<String, Float[]>) request.getSession().getServletContext().getAttribute("postType");
		
				for (String elment : type) {
					if(elment.equals(test.getSalaryType()))//如果是自己的岗位等级
					{
						salary+=test.getBaseSalary()*abcd.get(elment)/sum;
					}
					else//不是自己的岗位等级
					{
						//System.out.println(elment+elment.charAt(0));
						String empSalaryType="D";
						if(test.getSalaryType()!=null)
						{
							empSalaryType=test.getSalaryType();
						}
						if(elment.charAt(0) >empSalaryType.charAt(0))
						{
							salary+=map.get(elment)[0]*abcd.get(elment)/sum;//*小的70%
						}
						else
						{
							salary+=map.get(elment)[1]*abcd.get(elment)/sum;//*大的30%
						}
						
						
					}	
				}
							
		
			test.setFloattingSalary(salary);
			
		}
	
	}
	
}
