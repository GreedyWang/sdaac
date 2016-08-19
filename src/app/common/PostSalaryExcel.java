package app.common;

import java.util.List;

import app.entity.Test;
import common.util.WritetoExcel;

public class PostSalaryExcel extends WritetoExcel {

	public PostSalaryExcel(String outputFile) throws Exception {
		super(outputFile);
		// TODO 自动生成构造函数存根
	}

	
	public void write(List<Test> list, String sheetName) {
		// TODO 自动生成方法存根
		//super.write(list, sheetName);
		createNewSheet(sheetName);
		setTitle("岗位工资");
		String[] title={"员工编号","员工姓名","员工部门","档案工资","档案工资等级","A类装配",
				"B类装配","C类装配","D类装配","实际岗位工资"};
		creatNewRow(title);
		for (Test test : list) {
			
			Float a=0.0f;
				Float b=0.0f;
					Float c=0.0f;
						Float d=0.0f;
							if(test.getTypeA()!=null) a=test.getTypeA();
							if(test.getTypeB()!=null) b=test.getTypeB();
							if(test.getTypeC()!=null) c=test.getTypeC();
							if(test.getTypeD()!=null) d=test.getTypeD();
			String[] dates={test.getUid(),
			test.getName(),
			test.getDepartmentName(),
			test.getBaseSalary()+"",
			test.getSalaryType(),
			a+"",
			b+"",
			c+"",
			d+"",
			test.getFloattingSalary()+""};
			creatNewRow(dates);
		}
		close();
	}

}
