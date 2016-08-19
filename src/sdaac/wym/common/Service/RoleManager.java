package sdaac.wym.common.Service;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.security.biz.RoleBiz;
import com.security.biz.UserRoleBiz;

import common.dao.CommonDAO;
import common.entity.Tfunction;
import common.entity.Trole;
import common.entity.TroleFunction;
import common.entity.TuserRole;
import app.entity.Tempolyee;

/**
 * 角色及权限控制
 * @author SA1KV5
 * 
 */
public class RoleManager {

	private CommonDAO<TuserRole> userRoleDao;
	private CommonDAO<TroleFunction> roleFunctionDao;
	private RoleBiz roleBiz;
	private UserRoleBiz userRoleBiz;
	
	public List<TuserRole> doSelcetAll(){
		return null;
	}
	
	/**
	 * 查询某个权限下的用户
	 * @param roleName
	 * @return
	 */
	public List<TuserRole> doSelectByRoleName(String roleName){
		String hql = "from TuserRole ur join fetch ur.tempolyee e join fetch e.tdepartment where ur.trole.roleid=? and e.isSeparation <> 0";
		return userRoleDao.select(hql, roleName);
	}
	
	/**
	 * 查询某个权限下的用户
	 * @param roleName plant
	 * @return
	 */
	public List<TuserRole> doSelectByRoleNameNPlant(String roleName,int plant){
		String hql = "from TuserRole ur join fetch ur.tempolyee e join fetch e.tdepartment where e.eprownerarea like ? and ur.trole.roleid=? and e.isSeparation <> 0";
		String plantString = plant+"";
		return userRoleDao.select(hql, new Object[]{'%'+plantString+'%',roleName});
	}
	
	public List<TuserRole> doSelectByAll(String roleName,int plant,String capitalType,int dprt){
		String hql = "from TuserRole ur join fetch ur.tempolyee e join fetch e.tdepartment where ur.capitalType=? and dprts like ? and e.eprownerarea like ? and ur.trole.roleid=? and e.isSeparation <> 0";
		String plantString = plant+"";
		String dprtString = dprt+"";
		return userRoleDao.select(hql, new Object[]{capitalType,'%'+dprtString+'%','%'+plantString+'%',roleName});
	}
	
	
	
	/**
	 * 查询某个权限下的用户
	 * @param roleName plant
	 * @return
	 */
	public List<TuserRole> doSelectByRoleNameNPlantNDprt(String roleName,int plant,int dprt){
		String hql = "from TuserRole ur join fetch ur.tempolyee e join fetch e.tdepartment where e.eprownerarea like ? and e.managedms like ? and ur.trole.roleid=? and e.isSeparation <> 0";
		String plantString = String.valueOf(plant);
		String dprtString = String.valueOf(dprt);
		return userRoleDao.select(hql, new Object[]{'%'+plantString+'%','%'+dprtString+'%',roleName});
	}
	
	/**
	 * 查询某个权限和部门下的用户
	 * @param roleName
	 * @return
	 */
	public List<TuserRole> doSelectByRoleName(String roleName,Integer departId){
		String hql = "from TuserRole ur join fetch ur.tempolyee e where ur.trole.roleid=? and e.tdepartment.id=?";
		return userRoleDao.select(hql, new Object[]{roleName,departId});
	}
	
	/**
	 * 某项功能是否有权限
	 * @param fun
	 * @param temp
	 * @return
	 */
	public boolean hasRight(Tfunction fun, Tempolyee temp) {
		String hql = "from TuserRole as ur inner join ur.trole.troleFunctions b inner join b.tfunction f where f.functionName=? and ur.tempolyee.uid=?";
		List rs = userRoleDao.select(hql, new Object[] { fun.getFunctionName(),
				temp.getUid() });
		if (rs != null && !rs.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 某用户下有多少功能
	 * @param roleType
	 * @param uid
	 * @return
	 */
	public List hasFuns(String roleType, String uid) {
		StringBuffer hql = new StringBuffer("select distinct(functionName) from tuserrole a ");
		hql.append("inner join trole r on r.roleid= a.roleid ");
		hql.append("inner join tRole_Function rf on rf.roleid = a.roleid ");
		hql.append("inner join tFunction f on f.functionid = rf.functionid ");
		hql.append("where a.uid = '"+uid+"' and (r.type='all' or r.type='"+roleType+"')");
		return userRoleDao.selectBySql(hql.toString());
	}
	
	/**
	 * 某个用户是否有这个角色
	 * 
	 * @param role
	 * @param temp
	 * @return
	 */
	public boolean hasRight(Trole role, Tempolyee temp) {
		for (String roleName : temp.getRoleids_b()) {
			if (role.getRoleid().equals("")
					|| role.getRoleid().equals(roleName)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasRight(Trole role, Tempolyee temp,int departmentId) {
		for (String roleName : temp.getRoleids_b()) {
			if (role.getRoleid().equals("")
					|| (role.getRoleid().equals(roleName)&& temp.getTdepartment().getId()==departmentId)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 变更用户权限
	 * @param flag
	 * @param pUserRole
	 */
	public void doChangeUserRole(boolean flag ,TuserRole pUserRole){
		if(flag){
			userRoleBiz.addRights(pUserRole);
		}else{
			userRoleBiz.deleteRightsExt(pUserRole.getTempolyee().getUid(),
					pUserRole.getTrole().getRoleid());
		}
	}
	
	/**
	 * 查询用户权限矩阵
	 * @param uid
	 * @return
	 */
	public List<Trole> doSelectRightsRet(String uid,String typeName){
		List<TuserRole> userRoles = userRoleBiz.doSelectByCondition(uid, typeName);//得到指定用户的权限
		List<Trole> roles = roleBiz.doSelectByCondition(typeName);//得到所有角色
		for(Trole item: roles){
			if(userRoles!=null){
				for(TuserRole userRole : userRoles){
					item.check(userRole);
				}
			}					
		}
		return roles;
	}
	
	// ---------get & set ----------------------
	public CommonDAO<TuserRole> getUserRoleDao() {
		return userRoleDao;
	}

	public void setUserRoleDao(CommonDAO<TuserRole> userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

	public CommonDAO<TroleFunction> getRoleFunctionDao() {
		return roleFunctionDao;
	}

	public void setRoleFunctionDao(CommonDAO<TroleFunction> roleFunctionDao) {
		this.roleFunctionDao = roleFunctionDao;
	}

	public RoleBiz getRoleBiz() {
		return roleBiz;
	}

	public void setRoleBiz(RoleBiz roleBiz) {
		this.roleBiz = roleBiz;
	}

	public UserRoleBiz getUserRoleBiz() {
		return userRoleBiz;
	}

	public void setUserRoleBiz(UserRoleBiz userRoleBiz) {
		this.userRoleBiz = userRoleBiz;
	}
}
