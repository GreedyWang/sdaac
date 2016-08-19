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
 * 沈阳兴远东，以G，H 分别合并
 * @author SA1KV5
 *
 */
public class XdyTaxManager extends GmTaxManager {
	//private static String RULE="^20";
	private static String[] RULES={"^G","^H"};
	@Override
	protected void init2(InputStream is,String fileName) {
		// TODO Auto-generated method stub
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
}
