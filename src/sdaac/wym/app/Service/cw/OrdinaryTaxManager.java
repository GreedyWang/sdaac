package sdaac.wym.app.Service.cw;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import common.util.ExcelReader;
import common.util.ExcelReader2007;

import sdaac.wym.app.entity.cw.Tax;

public class OrdinaryTaxManager extends TaxManager {
	
	private ExcelReader2007 excelReader;
	public OrdinaryTaxManager(){
		super();
	}
	private void init(InputStream is,String fileName){
		excelReader=new ExcelReader2007();
		
		try {
			List<ArrayList<String>> results= excelReader.readByIos(is, fileName);
			for (ArrayList<String> objects : results) {
				for(Tax item : taxs){	
					
					//boolean flag=true;
					if(item.getNo().equals("00"+objects.get(4))){					
						map.put(item.getNo(), new String[]{objects.get(13),objects.get(3),objects.get(1)});//本币金额
					}
				}
			}						
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void go(InputStream is, List<Tax> pTaxs,String fileName) {
		// TODO Auto-generated method stub
		//super.go(is, taxs);
		taxs=pTaxs;
		init(is,fileName);
		nos = new ArrayList<List<String>>();		
		nos.add(new ArrayList<String>());
		for(Tax item: taxs){
			nos.get(0).add(item.getNo());
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
