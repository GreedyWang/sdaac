package sdaac.wym.app.Service.cw;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import common.dao.CommonDAO;
import common.util.ExcelReader;
import common.util.ExcelReader2007;

import sdaac.wym.app.entity.cw.CwLogs;
import sdaac.wym.app.entity.cw.Tax;
/**
 * 处理GM 发票操作
 * @author sa1kv5
 *
 */
public class GmTaxManager extends TaxManager {

	
	protected ExcelReader2007 excelReader;
	
	protected List<String> others;
	protected Pattern pattern ;

	private static String[] RULES={"^0800","^0850","^NGAKTA","^SGADQA","^0900","^0400","^0401","^0620","^4700","^516",
		"^077","^0801","^570","^0840","^0840","^0840","^0851","^0580","^851","^580","^05600","^0500","^08400"};
	/**
	 * 1.得到客户参照号
	 * 2.找到其中的对应发票
	 * 3.将规则植入
	 * @param is
	 * @param taxs
	 */
	protected void init2(InputStream is,String fileName){
		excelReader=new ExcelReader2007();
		
		try {
			List<ArrayList<String>> results= excelReader.readByIos(is, fileName);
			for (ArrayList<String> objects : results) {
				for(Tax item : taxs){	
					
					boolean flag=true;
					if(item.getNo().equals("00"+objects.get(4))){
						
						map.put(item.getNo(), new String[]{objects.get(13),objects.get(3),objects.get(1)});//本币金额
						for(int i=0;i<RULES.length;i++){
							pattern = Pattern.compile(RULES[i]);							
							if(pattern.matcher(objects.get(2)).find()){						
									nos.get(i).add(item.getNo());										
									flag=false;
							}
						}	
						if(flag){
							others.add(item.getNo());
						}
					}				
				}
			}
			deal();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public GmTaxManager(){		
		super();
	}
	
	@Override
	public void go(InputStream is,List<Tax> pTaxs,String fileName){
		nos = new ArrayList<List<String>>();		
		for(int i=0;i<RULES.length;i++){
			nos.add(new ArrayList<String>());
		}
		taxs=pTaxs;
		others= new ArrayList<String>();
		init2(is,fileName);
	}
	
	/**
	 * 合并
	 */
	public void deal() {
		// TODO Auto-generated method stub					
		for(String no:others){
			List<String> temp  = new ArrayList<String>();
			temp.add(no);
			nos.add(temp);
		}
		for(List<String> no: nos){	
			if(!no.isEmpty()){			
				checkIsMerge(no);
				if(!no.isEmpty()){				
					this.MergeComplex(no.toArray(new String[0]), taxs);
				}
			}			
		}	
		doLogs();
	}
	
	

}
