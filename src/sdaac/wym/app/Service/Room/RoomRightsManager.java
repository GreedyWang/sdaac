package sdaac.wym.app.Service.Room;

import app.entity.Tempolyee;
import common.entity.Tfunction;
import sdaac.wym.common.Service.RoleManager;

public class RoomRightsManager {
	
	private RoleManager roleManager;
	private String[] funs = {"RSselectAll","RSselectDepart"};
	public RoleManager getRoleManager() {
		return roleManager;
	}

	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}
	
	/**
	 * 检查权限
	 * @param emp
	 * @param item
	 */
	public void checkRight(Tempolyee emp,Form item) {
		if (roleManager.hasRight(new Tfunction(funs[0]),
				emp)) {
			
		} else if (item.getState()!=null && item.getState()==Form.waittingForApprove &&  roleManager.hasRight(new Tfunction(funs[1]),
				emp)) {
			Tempolyee item2 = new Tempolyee();
			item2.setTdepartment(emp.getTdepartment());
			item.setEmp(item2);
		}
			else {
			item.setEmp(emp);
		}
	}
}
