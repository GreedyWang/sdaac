package app.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import app.entity.Tproposal;
import app.entity.Ttheme;
import app.entity.vave.Schedule;
import app.entity.vave.VaveProposalMore;
import app.entity.vave.VaveTeamWork;
import common.util.WritetoExcel;

public class VaveProposalAccountExcel extends WritetoExcel {

	private Integer[] contexts;
	
	public VaveProposalAccountExcel(String outputFile) throws Exception {
		super(outputFile);
		//	contexts=pContexts;
		// TODO Auto-generated constructor stub
	}
	
	public void write(List<Tproposal> list) {
		createNewSheet("VAVE提案统计");							
		String[] title={"提案人","部门","提案日期","收集人","来源","供应商名称","状态","项目名称","零件","零件代号","适应车型","原方案","新方案","现在方案成本","代用方案成本","年用量合计","总节约成本","一次性费用","净节约成本","C/D/S分配比例%","SDAAC净节约成本","预计节约金额","计划完成日期","提案编号","项目预计节约金额","项目计划完成日期","最后审批日期"};
		creatNewRow(title);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		for (Tproposal item : list) {
			String applicantData = sdf.format(item.getLastModifyTime());
			String Parts="";
			String PartsNO="";
			String suitableCar="";
			String expectSaving="";
			String expectFinishDate="";
			String expectSaving2 = "";
			String expectFinishDate2 = "";
			if(item.proposalMore2!=null){
				expectSaving = item.proposalMore2.getExpectSaving()+"";
				expectFinishDate = item.proposalMore2.getExpectFinishDate()+"";
			}
			if(item.vtw!=null){
				expectSaving2 = item.vtw.getEstimatedCostSavings()+"";
				expectFinishDate2 = item.vtw.getMyPlanfinishtime();
			}
			String[] item1= new String[8];
			
			for( Ttheme theme: item.getTthemes())
			{
				 Parts+=theme.getPartsName()+",";
				 PartsNO+=theme.getPartsId()+",";
				 suitableCar+=theme.getCustomer()+",";
			
			}	
			for(int i=0;i<item.schedules.size();i++){
				item1[i]=item.schedules.get(i).getProject().getQuantity();
			}
		
			String[] datas={item.getCollectionPersion().getName(),item.getCollectionPersion().getTdepartment().getName(),applicantData, item.getProposalPerson().getName(),item.getSource(),item.getSupply().getSimpleName(), item.getState_contexts(item.getState()),item.getTitle(),Parts,PartsNO,suitableCar,item.getCurProgram(),item.getIns_Program() ,item1[0],item1[1],item1[2],item1[3],item1[4],
					item1[5],item1[6],item1[7],expectSaving,expectFinishDate,item.getId(),expectSaving2,expectFinishDate2,item.lastApproveDate};
			creatNewRow(datas);
		}
		close();
	}

	public boolean creatNewRow(String[] datas) {
		// TODO Auto-generated method stub
		return super.creatNewRow(datas);
	}

}
