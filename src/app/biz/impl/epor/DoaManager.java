package app.biz.impl.epor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.entity.epor.StateManager;

/**
 * 
 * @author sa1kv5
 * @version 1
 * @since 2010-8-3
 * @Doa处理
 */
public class DoaManager {

	private int a =  800000;
	private int b =  200000;
	private int ak = 1500000;
	private int bk =600000;
	private int[] levels = { a, b };
	private int[] Klevels = { ak, bk };
	private int c = 2000;
	private int[] s = { c, b, a };
	private int NO_PR = 500;// 不需要PR
	private List<DoaEntity> doas = new ArrayList<DoaEntity>();
	private Map<Integer, StateManager> scheduleMap = new HashMap<Integer, StateManager>();
	private int xues[] = { 10069, 10070, 10071, 10072, 10077 };
	private int gao[] = { 10073 };

	/**
	 * DOA内部审核
	 * 
	 * @param total_cost
	 * @return
	 */
	public int selectDOA(double total_cost, int isPlan, int isCapital,
			StateManager stateManager) {

		if (total_cost <= NO_PR) {
			return StateManager.PCL;
		} else if (total_cost > NO_PR && total_cost <= c) {
			if (stateManager.curState == StateManager.FinanceManager) {
				return StateManager.FINISHDOA[isPlan];
			}
		} else if (total_cost > c && total_cost <= levels[isCapital]) {
			if (stateManager.curState == StateManager.DeputyGeneral) {
				return StateManager.FINISHDOA[isPlan];
			}
		} else if (total_cost > levels[isCapital]) {
			// return -3;
		} 
		if(stateManager.curState == StateManager.PRManager && total_cost < Klevels[isCapital]) {
			return StateManager.FEEDBACK;
		}
		return -3;
	}

	/**
	 * DOA内部审核
	 * 
	 * @param total_cost
	 * @return
	 */
	public int selectDOASY(double total_cost, int isPlan, int isCapital,
			StateManager stateManager, int departId) {

		if (stateManager.curState == StateManager.FinanceManager) {
			return getSYDeputy(departId);
		}
		if (total_cost <= 1) {
			return StateManager.PCL;
		} 

		return -3;
	}

	/**
	 * 根据部门id判断主管副总
	 * 
	 * @param departId
	 * @return
	 */
	private int getSYDeputy(int departId) {

		for (int i = 0; i < gao.length; i++) {
			if (gao[i] == departId) {
				return StateManager.PLANTDeputyGeneralManager;
			}
		}
		return StateManager.SYGM;
	}

	public static void main(String[] args) {
		DoaManager dm = new DoaManager();
		double total_cost = 600000;
		StateManager stateManager = new StateManager(7, "", 66, 0, "", "", "");
		System.out.println(dm
				.selectDOA(total_cost, 1, 1, stateManager));

	}

	public void aa() {
		// key :{当前状态,当前状态名称,成功状态,失败状态,成功条件,当前状态审批页面,状态对应的审批权限}
		scheduleMap.put(0, new StateManager(0, StateManager.states[0], 1, 0,
				"approve1", "ordinaryApprove.jsp", ""));// 个人
		scheduleMap.put(1, new StateManager(1, StateManager.states[1], 12, 0,
				"approvePmManager", "PMApprove.jsp", "PRPM"));// 部门经理

		scheduleMap.put(12, new StateManager(12, StateManager.states[3], 3, 0,
				"approve3", "ordinaryApprove.jsp", ""));// 相关部门
		scheduleMap.put(13, new StateManager(13, StateManager.states[3], 5, 0,
				"approve3", "ordinaryApprove.jsp", ""));// 相关部门2

		scheduleMap.put(3, new StateManager(3, StateManager.states[4], 4, 0,
				"approve4", "buyerManagerApprove.jsp", "PRBuyerManager"));// 采购经理
		scheduleMap.put(4, new StateManager(4, StateManager.states[5], 2, 0,
				"approve1", "buyerConfirm.jsp", "PRBuyer"));// 采购员确认

		scheduleMap.put(2, new StateManager(2, StateManager.states[2], 13, 0,
				"approve2", "cwControlApprove.jsp", "FinanceControler"));// 财务协调员

		scheduleMap.put(5, new StateManager(5, StateManager.states[6], 6, 0,
				"approveDOA", "ordinaryApprove.jsp", "FinanceManager"));// 财务经理
		scheduleMap.put(6, new StateManager(6, StateManager.states[7], 7, 0,
				"approveDOA", "ordinaryApprove.jsp", "DeputyGeneralManager"));// 副总经理审批
		scheduleMap.put(7, new StateManager(7, StateManager.states[8], 8, 0,
				"approveDOA", "ordinaryApprove.jsp", "PRManager"));// 总经理审批
		// ---------------------------
		scheduleMap.put(8, new StateManager(8, StateManager.states[9], 9, 0,
				"approve5", "feedbackApprove.jsp", "FinanceControler"));// 回填信息
		scheduleMap.put(9, new StateManager(9, StateManager.states[10], 10, 0,
				"approve5", "PCLApprove.jsp", "PCL"));// 等待收料部门确认
		scheduleMap.put(10, new StateManager(10, StateManager.states[11], 11,
				0, "approve1", "ordinaryApprove.jsp", "PRBuyer"));// 等待采购员填写订单号
		scheduleMap.put(11, new StateManager(11, StateManager.states[12], 12,
				0, "", "blank.html", ""));// 完成
	}

	public void initDoa() {

		doas.add(new DoaEntity(0, 0, 2000, "DeputyGeneralManager", false));
		doas.add(new DoaEntity(0, 1, 2000, "DeputyGeneralManager", false));
		doas.add(new DoaEntity(1, 0, 2000, "DeputyGeneralManager", false));
		doas.add(new DoaEntity(1, 1, 2000, "DeputyGeneralManager", false));

		doas.add(new DoaEntity(0, 0, 20000, "DeputyGeneralManager", false));
		doas.add(new DoaEntity(0, 1, 20000, "DeputyGeneralManager", false));
		doas.add(new DoaEntity(1, 0, 20000, "DeputyGeneralManager", false));
		doas.add(new DoaEntity(1, 1, 20000, "DeputyGeneralManager", false));

	}

	public int nextStep() {
		return 1;
	}

}
