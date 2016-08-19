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
 * PO:5500
 * @author SA1KV5
 *
 */
public class PoTaxManager extends GmTaxManager {

	private static String RULE="^5500";
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
							if(pattern.matcher(item.getPo()).find()){						
									nos.get(0).add(item.getNo());										
							}else{
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
