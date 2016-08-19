package sdaac.wym.app.Service.PCL.MainData;
/**
 * <tr>
 * 	<td>
 *  	PCL 物料主数据申请 管理状态
 *  </td>
 * </tr>
 * @version 1.0
 * @author SA1KV5
 * @since 2011-11-9
 */
import java.util.HashMap;

import app.entity.epor.StateManager;
/**
 * 将步骤5和6合并
 * 取消步骤8
 * 2013-01-24
 * @author SA1KV5
 *---WHO----WHEN----------WHAT---------
 *---WYM----2013-12-31----Add the 2 state in the approve process
 *------------------------------------------
 */
public class MDStateManager {	
	public static HashMap<Integer, StateManager> states;
	public static int SELF=0;
	public static int APPROVE = 1,REFUSE=2,NEEDEXPLAIN=3,BackTo=4;
	public static int DM=1,DGM=2,SHPMgr=14,SHBMgr=15;		
	static {
		states = new HashMap<Integer, StateManager>();
		StateManager item1 = new StateManager(0,"申请人",1,0,"ordinaryApprove.jsp","","");
		StateManager item2 = new StateManager(1,"部门经理",2,0,"ordinaryApprove.jsp","ordinaryApprove.jsp","PRPM");		
		StateManager item13 = new StateManager(2,"OST经理",4,0,"ordinaryApprove.jsp","ordinaryApprove.jsp","DMOST");		
		StateManager item5 = new StateManager(4,"采购员",3,0,"BuyerApprove","buyer.jsp","DMBuyer");
		StateManager item4 = new StateManager(3,"采购部",13,0,"ordinaryApprove.jsp","ordinaryApprove.jsp","DMBuyerController");	
		//StateManager item6 = new StateManager(5,"创建Delphi物料号",13,0,"updateContext","pcl.jsp","DMMaterialManager");
		//new state: Finance approval
		StateManager item12 = new StateManager(13,"财务审核",6,0,"FinApprove","ordinaryApprove.jsp","DMSFin");
		StateManager item7 = new StateManager(6,"创建SAP",7,0,"updateContext","pcl.jsp","DMSAPManager");				
		StateManager item8 = new StateManager(7,"采购部维护采购信息",9,0,"updateContext","buyer2.jsp","DMBuyer");
		StateManager item9 = new StateManager(8,"维护MRP数据",9,0,"updateContext","mrp.jsp","DMMBP");
		StateManager item10 = new StateManager(9,"完成",1,1,"","","");
			StateManager item11 = new StateManager(12,"ME包装负责人",4,0,"","","PRPM");
		StateManager item14 = new StateManager(14,"SH PLANT Mgr",6,0,"ordinaryApprove.jsp","ordinaryApprove.jsp","SHPMgr");//DGM Li
		StateManager item15 = new StateManager(15,"SH PLANT Buyer Mgr",13,0,"ordinaryApprove.jsp","ordinaryApprove.jsp","SHPBMgr");//PLANT Buyer Manager
		//init the state map
		states.put(0,item1);
		states.put(1,item2);
		states.put(2,item13);
		states.put(3,item4);
		states.put(4,item5);
		//states.put(5,item6);
		states.put(6,item7);
		states.put(7,item8);
		states.put(8,item9);
		states.put(9,item10);
		states.put(12,item11);
		states.put(13,item12);
		states.put(14,item14);
		states.put(15,item15);
		
	}
	public MDStateManager(){
		
	}
	
	/**
	 * 得到对应的审批表格
	 * @param state
	 * @return
	 */
	public static String getApproveURL(int state){
		return states.get(state).destination;
	}

}

