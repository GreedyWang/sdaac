package app.common;

import java.util.List;

import app.entity.vave.Pcount;

import sdaac.wym.app.entity.hr.BounceFigure;
import common.util.WritetoExcel;

public class VaveCountExcel extends WritetoExcel {

	public VaveCountExcel(String outputFile) throws Exception {
		super(outputFile);
		// TODO Auto-generated constructor stub
	}
	
	public void write(List<Pcount> pls)//时间和车间
	{
		createNewSheet("sheet1");
		//	setTitle(params[0]+"---"+params[1]);
		//图号	物料号(sap号)	名称	标准工时	入库数	翅片数	实际工作时间	得分		//	 	 			

		String[] title={"提案总数","批准数","完成数","部门","名称","合理化建议数"};
		creatNewRow(title);
		for (Pcount item : pls) {
			String[] datas={item.getProposalNum()+"",item.getDoNum()+"",item.getFinishNum()+"",
							item.getDepartmentName()+"",item.getUname()+"",item.getHlh()+""
						};
			creatNewRow(datas);
		}
		close();
	}
}
