package app.common;

import java.util.List;
import app.entity.Ttheme;
import app.entity.vave.VaveProposalMore;
import app.entity.vave.VaveTeamWork;
import common.util.WritetoExcel;

public class VaveAccountExcel extends WritetoExcel {

	private Integer[] contexts;
	
	public VaveAccountExcel(String outputFile,Integer[] pContexts) throws Exception {
		super(outputFile);
		contexts=pContexts;
		// TODO Auto-generated constructor stub
	}
	
	public void write(List<VaveTeamWork> list) {
		createNewSheet("VAVE项目统计");							
		String[] title={"负责人","提案人","项目名称","零件","零件代号","适应车型","数量","计划完成日期","单位节约金额","	预计节约金额","分享比例","批准日期","提案编号"};
		creatNewRow(title);
		for (VaveTeamWork item : list) {
			String Parts="";
			String PartsNO="";
			String suitableCar="";
			
			for( Ttheme theme: item.getTproposal().getTthemes())
			{
				 Parts+=theme.getPartsName()+",";
				 PartsNO+=theme.getPartsId()+",";
				 suitableCar+=theme.getCustomer()+",";
			
			}	
			String[] datas={item.getProjectManager().getName(),item.getTproposal().getProposalPerson().getName(),item.getTeamName(),Parts,PartsNO,suitableCar,item.getTproposal().getAnnualConsumption()+"",
					item.getTproposal().getVm().getExpectFinishDate()+"",
							item.getTproposal().getPp()+"",item.getEstimatedCostSavings()+"",item.getTproposal().getShareValue(),"",item.getTproposal().getId()};//item.getTproposal().ApproveDate()
			creatNewRow(datas);
		}
		close();
	}

	public boolean creatNewRow(String[] datas) {
		// TODO Auto-generated method stub
		String[] newContext=new String[contexts.length];
		for(int i=0;i<contexts.length;i++)
		{
			newContext[i]=datas[contexts[i]];
		}
		return super.creatNewRow(newContext);
	}

}
