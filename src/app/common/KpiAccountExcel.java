package app.common;

import java.text.DecimalFormat;
import java.util.List;
import app.biz.impl.KpiAccount;
import common.util.WritetoExcel;

public class KpiAccountExcel extends WritetoExcel {

	public KpiAccountExcel(String outputFile) throws Exception {
		super(outputFile);
		// TODO Auto-generated constructor stub
	}
	private float COMPANY_SCORE=1.5f;
	public void write(List<KpiAccount> PkpiList){
		createNewSheet("SDAAC_KPI总汇");
		String[] title={"uid","name","departmentName","score"};
		creatNewRow(title);
		for (KpiAccount item : PkpiList) {
			String rs = new DecimalFormat("#.0000").format((item.getScore()*COMPANY_SCORE)).toString();
			if(item.getScore()*COMPANY_SCORE > 2) rs = "2";
			String[] datas={item.getUid(),item.getName(),item.getDepartmentName(),rs};
			creatNewRow(datas);
		}
		close();
	}
}
