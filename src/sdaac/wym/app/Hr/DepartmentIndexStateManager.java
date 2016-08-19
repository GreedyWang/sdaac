package sdaac.wym.app.Hr;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sdaac.wym.app.entity.hr.DepartmentState;
import common.dao.CommonDAO;

public class DepartmentIndexStateManager {
	private CommonDAO<DepartmentState> departmentStateDao;
	
	/**
	 * 显示部门指标状态
	 * @return
	 */
	public List<DepartmentState> show(){
		String hql="from DepartmentState ds join fetch ds.department";
		return departmentStateDao.select(hql, null);
	}
	/**
	 * 更新部门指标状态
	 * @param item
	 */
	public void updateState(DepartmentState item){
		String hql="update DepartmentState set context=?,state=? where id=?";
		departmentStateDao.bulkUpdate(hql, new Object[]{item.getContext(),item.getState(),item.getId()});	
	}
	/**
	 * 更新部门指标状态
	 * @param item
	 */
	public void updateStateByDepartmentId(int departmentId){
		String hql="update DepartmentState set context=? where department.id=?";
		departmentStateDao.bulkUpdate(hql, new Object[]{"等待审批",departmentId});	
	}
	/**
	 * 查询部门指标状态
	 * @param departmentId
	 * @return
	 */
	public boolean isPass(int departmentId,int state){
		String hql="from DepartmentState where department.id=?";
		List<DepartmentState> rs= departmentStateDao.select(hql, departmentId);
		if(rs==null||rs.isEmpty()){
			return false;
		}else{
			if(rs.get(0).getState()==state){
				return true;
			}else{
				return false;
			}
		}
	}
	
	/**
	 * 重设指标状态
	 */
	public void doReset(){
		String hql="update DepartmentState set state=?,context=?";
		departmentStateDao.bulkUpdate(hql, new Object[]{0,""});
	}
	
	public CommonDAO<DepartmentState> getDepartmentStateDao() {
		return departmentStateDao;
	}
	public void setDepartmentStateDao(CommonDAO<DepartmentState> departmentStateDao) {
		this.departmentStateDao = departmentStateDao;
	}
//	public static void main(String[] args) {
//		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
//		DepartmentIndexStateManager diM=(DepartmentIndexStateManager)ac.getBean("departIndexStateManager");
//		DepartmentState item=diM.show().get(0);
//		item.setContext("test");
//		item.setState(1);
////		diM.updateState(item);
//	}
}
