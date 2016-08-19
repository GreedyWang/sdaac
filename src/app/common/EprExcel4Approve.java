package app.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import app.entity.epor.PrPrForm;
import app.entity.epor.StateManager;
import common.util.WritetoExcel;

public class EprExcel4Approve extends WritetoExcel{

	public EprExcel4Approve(String outputFile) throws Exception {
		super(outputFile);
		// TODO Auto-generated constructor stub
	}
	
	public void writeByArray(List<Object[]> list) {
//		PR流水号	申请人	采购员	申请日期	资本化/费用化	计划内/计划外	状态 	实际供应商	供应商是否在系统内	购买物品	数量	订单号	采购员	IO
		createNewSheet("ePR数据导出");
		String[] title={"ID","Name","Date","applicant_date","type","det","det2"};
		creatNewRow(title);
		PrPrForm prform = new PrPrForm();
		Map<Integer, StateManager> map = prform.scheduleMap;
		int i = 0;
		int maxLine = list.size();
		String lastID = null;
		String lastDate = null;
		for (Object[] item : list) {
			String det;
			if(item[0].toString().equals(lastID)){
				det = getDiffDate(item[2].toString(),lastDate);
			}else{
				det = getDiffDate(item[2].toString(),item[3].toString());
			}
			String c4 = "";
			if(item[4]!=null)c4 = item[4].toString();
			String[] datas={item[0].toString(),
					item[1].toString(),
					item[2].toString(),
					item[3].toString(),
					c4,
					det};
			creatNewRow(datas);
			lastID = item[0].toString();
			lastDate = item[2].toString();
			i++;
		}
		close();
	}
	
	private String getDiffDate(String a,String b){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
		Date dateb = null,datea = null;
		try {
			datea = sdf.parse(a);
			dateb = sdf.parse(b);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return (datea.getTime() - dateb.getTime())*1.0/1000/60/60/24+"";
	}
	
}
