package sdaac.wym.app.Service.FinanceReport;

import java.util.ArrayList;
import java.util.List;

import common.util.ExcelReader2007;

/**
 * used to read finance excel data
 * @author SA1KV5
 *
 */
public class ExcelReader4SAP {
	
	private ExcelReader2007 reader = new ExcelReader2007(); 
	
	public void doRead4ZVT11(){
		
	}
	
	public void doRead4CUSTDND(String fileName){
		List<ArrayList<String>> rs = reader.read(fileName);
	}
}
