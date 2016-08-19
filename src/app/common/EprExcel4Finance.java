package app.common;

import java.util.List;
import java.util.Map;

import app.entity.Tproposal;
import app.entity.epor.PrBuyContext;
import app.entity.epor.PrForm;
import app.entity.epor.PrPrForm;
import app.entity.epor.StateManager;
import common.util.WritetoExcel;

public class EprExcel4Finance extends WritetoExcel {

	public EprExcel4Finance(String outputFile) throws Exception {
		super(outputFile);
		// TODO Auto-generated constructor stub
	}
	
	public void write(List<PrPrForm> list) {
//		PR流水号	申请人	采购员	申请日期	资本化/费用化	计划内/计划外	状态 	实际供应商	供应商是否在系统内	购买物品	数量	订单号	采购员	IO
		createNewSheet("ePR数据导出");
		String[] title={"PR流水号","购买理由","申请人","部门","成本中心","项目编号","总账科目","AR_NO","PR_SN","总价","采购员","申请日期","资本化/费用化","计划内/计划外","状态","实际供应商","供应商是否在系统内","用于那台设备","用于那个车间","购买物品","数量","订单号","PO日期","到货日期","IO","Remark"};
		creatNewRow(title);
		for (PrPrForm item : list) {
//			String source ="沈阳";
//			if(item.getSource()==1){
//				source="上海";
//			}
			

			
			String state=item.getInfo()+"";
			if(state!= null){
				if(state.equals("0")){
					state = "正在审批";
				}else if(state.equals("1")){
					state = "等待解释";
				}else if(state.equals("6")){
					state = "完成";
				}else if(state.equals("3")){
					state = "审批通过,正在回填";
				}
			}
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
			String PODate="";
			String finishDate="";
			String poId="";
			String IO="";
			String goodsRemark = "";
			for(PrBuyContext buyContext : item.getPrBuyContexts()){
				goodsName +=buyContext.getDescription()+";\r\n ";
				quantity+=buyContext.getQuantity()+";\r\n ";
				PODate+=buyContext.getPODate()+";\r\n ";
				finishDate+=buyContext.getFinishDate()+";\r\n ";
				poId+=buyContext.getOrderNo()+";\r\n ";
				IO+=buyContext.getIo()+";\r\n ";
				goodsRemark+=buyContext.getRemark()+";\r\n ";
			}
			String[] datas={item.getSapNO()+"",item.getRemark(),item.getTempolyeeByApplicantId().getName(),item.getTempolyeeByApplicantId().getTdepartment().getName(),
					item.getPrCostCenter().getRemark(),item.getPrProject().getSapNo(),					
					item.getTotalCategroy(),item.getArno(),item.getPrsn(),item.getTotal().toString(),item.getTempolyeeByBuyerId(),
					item.getApplicantDate().toString(),isCaptail,isPlan, state,item.getAcutalSupplier(),item.getIsInTheSap()+"",
					item.getFacilityToUse(),item.getWorkshopToUse(),
					goodsName,quantity,poId,PODate,finishDate,IO,goodsRemark};
			creatNewRow(datas);
		}
		close();
	}
	
	private int getSapNO(int ssid,String id) {
		int newSsid = ssid;
		int tenYearTotal = 963;
		int elYearTotal = 5273;
		int TWYearTotal = 9775;
		int THYearTotal = 15276;
		int FourthTotal = 21498;
		if(id != null && !"".equals(id)) {
			int a = Integer.parseInt(id.substring(0, 2));	
			if(a == 11) {
				newSsid = ssid - tenYearTotal;
			}else if(a == 12){
				newSsid = ssid - elYearTotal;
			}
			else if(a == 13){
				newSsid = ssid - TWYearTotal;
			}else if(a == 14){
				newSsid = ssid - THYearTotal;
			}else if(a == 15){
				newSsid = ssid - FourthTotal;
			}
			return a*100000000+newSsid;
		}else {
			return 0;
		}
	}
	
	/**
	 * 用于SQL查询结果创建EXCEL
	 * @param list
	 */
	public void writeByArray(List<Object[]> list) {
//		PR流水号	申请人	采购员	申请日期	资本化/费用化	计划内/计划外	状态 	实际供应商	供应商是否在系统内	购买物品	数量	订单号	采购员	IO
		createNewSheet("ePR数据导出");
		String[] title={"PR流水号","购买理由","申请人","部门","成本中心","项目编号","总账科目","AR_NO","PR_SN","总价","采购员","申请日期","资本化/费用化","计划内/计划外","状态","实际供应商","供应商是否在系统内","用于那台设备","用于那个车间","购买物品","数量","订单号","PO日期","到货日期","IO","Remark"};
		creatNewRow(title);
		PrPrForm prform = new PrPrForm();
		Map<Integer, StateManager> map = prform.scheduleMap;
		int i = 0;
		for (Object[] item : list) {
			
			//String state = map.get(Integer.parseInt(item[14].toString())).curStateName;
			
			String state=item[14].toString();
			if(state!= null){
				if(state.equals("0")){
					state = "正在审批";
				}else if(state.equals("1")){
					state = "等待解释";
				}else if(state.equals("6")){
					state = "完成";
				}else if(state.equals("3")){
					state = "审批通过,正在回填";
				}
			}
			
//			String state=item[14]==null?"":item[14].toString();
			String isCaptail = "";
			if(item[12]!=null && item[12].toString().equals("1")){
				isCaptail = "费用化";
			}if(item[12]!=null && item[12].toString().equals("0")){
				isCaptail = "资本化";
			}
			String isPlan = "";
			if(item[13]!=null && item[13].toString().equals("1")){
				isPlan = "计划外";
			}if(item[13]!=null && item[13].toString().equals("0")){
				isPlan = "计划内";
			}
			String io = item[19]==null?"":item[19].toString();
			String[] datas={getSapNO(Integer.parseInt(item[0].toString()),item[26].toString())+"",
					item[1]==null?"":item[1].toString(),
					item[2].toString(),
					item[3].toString(),
					item[4]==null?"":item[4].toString(),
					item[5]==null?"":item[5].toString(),
					//item[5].toString(),					
					item[6]==null?"":item[6].toString(),
					item[7]==null?"":item[7].toString(),
					item[8]==null?"":item[8].toString(),
					item[9].toString(),
					item[10].toString(),
					item[11]==null?"":item[11].toString(),
					isCaptail,isPlan, state,
					item[15]==null?"":item[15].toString(),
					item[16]==null?"":item[16].toString(),
					item[17]==null?"":item[17].toString(),
					item[18]==null?"":item[18].toString(),
					io,
					item[20]==null?"":item[20].toString(),
					item[21]==null?"":item[21].toString(),
					item[22]==null?"":item[22].toString(),
					item[23]==null?"":item[23].toString(),
							item[24]==null?"":item[24].toString(),
									item[25]==null?"":item[25].toString()};
			creatNewRow(datas);
			i++;
		}
		close();
	}
}
