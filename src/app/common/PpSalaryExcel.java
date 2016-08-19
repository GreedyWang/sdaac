package app.common;

import java.util.List;

import app.entity.IpTmp;
import common.util.WritetoExcel;

public class PpSalaryExcel extends WritetoExcel {

	public PpSalaryExcel(String outputFile) throws Exception {
		super(outputFile);
		// TODO 自动生成构造函数存根
	}

	public void write(List<IpTmp> list, String sheetName) {
		createNewSheet(sheetName);
		setTitle("奖金");
		String[] title={"员工编号","员工姓名","奖金系数"};
		creatNewRow(title);
		for (IpTmp item : list) {
			String uid=item.getUid();
			float pp=item.getIp();
			String[] datas={uid,"zhanwu",pp+""};
			creatNewRow(datas);
		}
		close();
	}

}
