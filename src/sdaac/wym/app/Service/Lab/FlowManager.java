package sdaac.wym.app.Service.Lab;

import java.util.HashMap;
import java.util.Map;

import sdaac.wym.app.entity.lab.*;

import app.entity.epor.StateManager;

/**
 * 负责实验室申请单的流程扭转
 * @author SA1KV5
 * @version 2011-5-31
 */
public class FlowManager {
	
	public static int DELETE=-1;
	public static int SELF=0;
	public static int SUPERVISOR=1;
	public static int DM=2;
	public static int SALESMANAGER=3;
	public static int PRMANAGER=4;
	public static int LABMANAGER=5;
	public static int TESTER = 6;
	public static int FINISH=7;	
	public static String[] StatesName = {"删除","个人","主管","部门经理","市场部经理","PR经理","实验室经理","试验台负责人","完成"};
	private static Map<Integer,StateManager> statesMap = new HashMap<Integer,StateManager>();

	//初始化流程图
	public FlowManager() {
		//key :{当前状态,当前状态名称,成功状态,失败状态,成功条件,当前状态审批页面,状态对应的审批权限}
		//0,StateManager.states[0],1,0,"approve1","ordinaryApprove.jsp" ,""
		statesMap.put(0, new StateManager(0,StatesName[1],1,0,"approve1","ordinaryApprove.jsp",""));
		statesMap.put(1, new StateManager(1,StatesName[2],2,0,"approve1","ordinaryApprove.jsp",""));
	}
	
	/**
	 * 批准
	 * @param state 当前状态
	 * @param type 申请单类型
	 */
	private int goApproved(int state,String type) {
		int nextState;
		if(type.equals(LabForm.innerType)) {
			//内部申请单
			 nextState = statesMap.get(state).nextState;
		}else {
			//外协
			 nextState = statesMap.get(state).nextState+2;
		}
		return nextState;
		//code: update form info
	}
	
	/**
	 * 不批准
	 * @param state
	 */
	private int goNotApproved(int state) {
		int nextState = statesMap.get(state).failureState;
		return nextState;
		//code: update form info
	}
	
	public void approve(ApproveForm item) {
		if(item.getOpinion() == ApproveForm.APPROVED ) {
			goApproved(item.getForm().getState(),item.getForm().getType());
		}else if(item.getOpinion() == ApproveForm.NEED_EXPLAIN) {
			
		}else if(item.getOpinion() == ApproveForm.NOT_APPROVED) {
			goNotApproved(item.getForm().getState());
		}
	}
}
