package sdaac.wym.common.Service;

import java.util.List;

import app.entity.Tempolyee;

/**
 * 权限控制 基类
 * @author sa1kv5
 * @version 1.0
 */
public abstract class RightsManager {
	protected RoleManager roleManager;
	
	public RoleManager getRoleManager() {
		return roleManager;
	}

	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}
    
	protected abstract void dealFuns(List rs,Object item,Tempolyee emp);
	protected abstract void dealFunsSingle(String funName,Object item,Tempolyee emp);
	
	public void checkRights(String roleType,Tempolyee emp,Object item) {
		List rs = roleManager.hasFuns(roleType, emp.getUid());
		dealFuns(rs,item,emp);
	}
	
	
	
	public void checkRight(String funName,Tempolyee emp,Object item) {

		dealFunsSingle(funName,item,emp);
	}
}
