package app.common;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

import app.entity.TcompanyIndex;
import app.entity.TempIndex;
import app.entity.TemployeeProduct;
import app.entity.Tempolyee;

import common.util.WritetoExcel;

public class PostDailyRecodeExcel extends WritetoExcel {

	public PostDailyRecodeExcel(String outputFile) throws Exception {
		super(outputFile);
		// TODO Auto-generated constructor stub
	}

	public void write(List<TemployeeProduct>list,String beginTime,String endTime)
	{
		
		
			createNewSheet("录入记录");
			String[] firstRow={"日期",beginTime+"至"+endTime};
			creatNewRow(firstRow);		       
			String [] title={"员工编号","岗位代码", "工作时间(小时)", "产出", "日期", "组长", "记录员" };
			creatNewRow(title);
			Integer totalTime=0;
			for(TemployeeProduct key:list)
			{
				String [] context={key.getTempolyee().getUid(),key.getTpost().getId(),key.getWorktime()+"",
									key.getOutput()+"",key.getDatatime()+"",key.getTeamLeaderId(),key.getRegisterID()};
				creatNewRow(context);
				totalTime+=key.getWorktime().intValue();
			}
			creatNewRow(new String[]{"","","总工作时间："+totalTime});
			close();
	}
	
	
}
