package app.common;

import java.util.List;

import app.entity.Tproposal;
import app.entity.epor.PrBuyContext;
import app.entity.epor.PrPrForm;
import app.entity.epor.StateManager;
import common.util.WritetoExcel;

public class EprExcel extends WritetoExcel {

	public EprExcel(String outputFile) throws Exception {
		super(outputFile);
		// TODO Auto-generated constructor stub
	}
	
	public void write(List<PrPrForm> list) {
//		PR流水号	申请人	采购员	申请日期	资本化/费用化	计划内/计划外	状态 	实际供应商	供应商是否在系统内	购买物品	数量	订单号	采购员	IO
		createNewSheet("ePR数据导出");
		String[] title={"PR流水号","申请人","采购员","申请日期","资本化/费用化","计划内/计划外","状态","实际供应商","供应商是否在系统内","购买物品","数量","订单号","IO"};
		creatNewRow(title);
		for (PrPrForm item : list) {
//			String source ="沈阳";
//			if(item.getSource()==1){
//				source="上海";
//			}
			String state=item.getStateName();
			String isCaptail = "";
			if(item.getIsCapital()!=null && item.getIsCapital()==1){
				isCaptail = "费用化";
			}if(item.getIsCapital()!=null && item.getIsCapital()==0){
				isCaptail = "资本化";
			}
			String isPlan = "";
			if(item.getIsPlan()!=null && item.getIsPlan() == 1){
				isPlan = "计划外";
			}if(item.getIsPlan()!=null && item.getIsPlan() == 0){
				isPlan = "计划内";
			}
			String goodsName="";
			String quantity="";
			String poId="";
			String IO="";
			for(PrBuyContext buyContext : item.getPrBuyContexts()){
				goodsName +=buyContext.getDescription()+";\r\n ";
				quantity+=buyContext.getQuantity()+";\r\n ";
				poId+=buyContext.getOrderNo()+";\r\n ";
				IO+=buyContext.getIo()+";\r\n ";
			}
			String[] datas={item.getSapNO()+"",item.getTempolyeeByApplicantId().getName(),item.getTempolyeeByBuyerId(),
					item.getApplicantDate().toString(),isCaptail,isPlan, state,item.getAcutalSupplier(),item.getIsInTheSap()+"",
					goodsName,quantity,poId,IO};
			creatNewRow(datas);
		}
		close();
	}
}
