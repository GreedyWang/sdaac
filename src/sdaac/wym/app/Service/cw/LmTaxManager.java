package sdaac.wym.app.Service.cw;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import sdaac.wym.app.entity.cw.Tax;

import common.util.ExcelReader;
import common.util.ExcelReader2007;
/**
 * 长安铃木， 以20开头的合并，或无20开头的合并。即以20区分
 * @author SA1KV5
 *
 */
public class LmTaxManager extends GmTaxManager {

	private static String RULE="^20";
	private static String[] RULES={"^0800","^0850"};
	@Override
	protected void init2(InputStream is,String fileName) {
		// TODO Auto-generated method stub
		excelReader=new ExcelReader2007();		
		try {
			List<ArrayList<String>> results= excelReader.readByIos(is, fileName);
			for (ArrayList<String> objects : results) {
				for(Tax item : taxs){	
					if(item.getNo().equals("00"+objects.get(4))){						
						map.put(item.getNo(), new String[]{objects.get(13),objects.get(3),objects.get(1)});//本币金额
							pattern = Pattern.compile(RULE);
							if(pattern.matcher(objects.get(2)).find()){						
									nos.get(0).add(item.getNo());										
									
							}else{
								nos.get(1).add(item.getNo());	
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
	
}
