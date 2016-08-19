package sdaac.wym.common.Service;

import java.util.List;

import app.entity.Tempolyee;
import sdaac.wym.app.Service.PCL.MainData.MDForm;
import sdaac.wym.app.Service.PCL.MainData.MDStateManager;

public class MDRightsManager extends RightsManager {

	
	@Override
	protected void dealFuns(List rs,Object item,Tempolyee emp) {
		// TODO Auto-generated method stub
		MDForm form = (MDForm)item;
		if(rs!= null && rs.size()>0) {
		for(Object funName : rs) {
			if(funName.equals("DGMSelect")) {
				form.setState(2);
			}
			else if(funName.equals("BuyerControl")){
				form.setState(3);
			}
			else if(funName.equals("DMOST")){
				form.setState(2);
			}
			else if(funName.equals("BuyerSelect")){
				form.setStateRange("4,7");
				form.setBuyerName(emp.getName());
			}
			else if(funName.equals("MatrailControl")){
				form.setState(5);
			}
			else if(funName.equals("SAPControl")){
				form.setState(6);
			}
			else if(funName.equals("SHPMgr")){
				form.setState(MDStateManager.SHPMgr);
			}
			else if(funName.equals("SHPBMgr")){
				form.setState(MDStateManager.SHBMgr);
			}
			else if(funName.equals("MainDataControl")){
				form.setState(8);
			}
			else if(funName.equals("RSselectDepart")) {
				form.setState(1);
				Tempolyee aa = new Tempolyee();
				aa.setTdepartment(emp.getTdepartment());
				form.setTempolyeeByApplicantId(aa);
			}else {
				form.setState(88);
			}
		}
		}
	}
	
	@Override
	protected void dealFunsSingle(String funName,Object item,Tempolyee emp) {
		// TODO Auto-generated method stub
		MDForm form = (MDForm)item;
		//init 
		Tempolyee bb = new Tempolyee();
		form.setTempolyeeByApplicantId(bb);
		form.setState(null);
			if(funName.equals("DMBuyerController")){
				form.setState(3);
			}
			else if(funName.equals("DMBuyer")){
				form.setStateRange("4,7");
				form.setBuyerName(emp.getName());
			}
			else if(funName.equals("DMOST")){
				form.setState(2);
			}
			else if(funName.equals("DMMaterialManager")){
				form.setState(5);
			}
			else if(funName.equals("DMSAPManager")){
				form.setState(6);
			}
			else if(funName.equals("DMMBP")){
				form.setState(8);
			}
			else if(funName.equals("DMSFin")){
				form.setState(13);
			}
			else if(funName.equals("DMSHPMgr")){
				form.setState(MDStateManager.SHPMgr);
			}
			else if(funName.equals("DMSHPBMgr")){
				form.setState(MDStateManager.SHBMgr);
			}
			else if(funName.equals("DMPM")) {
				form.setState(1);
				Tempolyee aa = new Tempolyee();
				aa.setTdepartment(emp.getTdepartment());
				form.setTempolyeeByApplicantId(aa);
			}
		
	}
}
