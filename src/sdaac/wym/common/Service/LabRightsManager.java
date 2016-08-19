//package sdaac.wym.common.Service;
//
//import common.entity.Trole;
//
//import sdaac.wym.app.Service.Lab.FlowManager;
//import sdaac.wym.app.entity.lab.LabForm;
//import app.entity.Tempolyee;
//
//public class LabRightsManager extends RightsManager {
//
//	@Override
//	public void checkRights(Tempolyee emp, Object item) {
//		// TODO Auto-generated method stub
//		LabForm labForm = (LabForm)item;
//		if(roleManager.hasRight(new Trole("indexPM"), emp)) { //如果是主管
//			labForm.setState(FlowManager.SUPERVISOR);
//			System.out.println("checkRights==>"+FlowManager.SUPERVISOR);
//		}
//		if(roleManager.hasRight(new Trole("LabManager"), emp,labForm.getApplicant().getTdepartment().getId())) {//如果是部门经理
//			labForm.setState(FlowManager.DM);
//		}
//		if(roleManager.hasRight(new Trole("LabManager"), emp)) {//如果是实验室经理
//			labForm.setState(FlowManager.LABMANAGER);
//		}
//		if(roleManager.hasRight(new Trole("LabTester"), emp)) {//试验台负责人接收确认
//			labForm.setState(FlowManager.TESTER);
//		}
//	}
//	
//}
