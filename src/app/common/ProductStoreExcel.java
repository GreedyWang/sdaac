package app.common;

import java.util.List;
import sdaac.wym.app.entity.hr.BounceFigure;
import common.util.WritetoExcel;

public class ProductStoreExcel extends WritetoExcel {

	public ProductStoreExcel(String outputFile) throws Exception {
		super(outputFile);
		// TODO Auto-generated constructor stub
	}
	
	public void write(List<BounceFigure> bFs,String[] params)//时间和车间
	{
		createNewSheet("产品绩效");
		setTitle(params[0]+"---"+params[1]+"---"+params[2]);
		//图号	物料号(sap号)	名称	标准工时	入库数	翅片数	实际工作时间	得分
		

		String[] title={"图号","物料号(sap号)","名称","类型","标准工时","入库数","翅片数","实际工作时间","得分"};
		creatNewRow(title);
		for (BounceFigure item : bFs) {
			String[] datas={item.getFigureId(),item.getRemark(),item.getCarType(),item.getType(),
							item.getStandWorkTime()+"",item.getInboundNum()+"",item.getPicesNum()+"",
							item.getRealWorkTime()+"",item.getScore()+""};
			creatNewRow(datas);
		}
		close();
	}
}
