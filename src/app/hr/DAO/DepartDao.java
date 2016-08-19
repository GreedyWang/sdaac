package app.hr.DAO;

import java.util.List;

import app.entity.Tdepartment;
import common.dao.impl.CommonSpringDAOImpl;

public class DepartDao extends CommonSpringDAOImpl<Tdepartment> {

	public DepartDao(String className) throws InstantiationException,
			IllegalAccessException {
		super(className);
		// TODO Auto-generated constructor stub
	}
	/**
	 * get departmentManager 
	 * @param departmentID
	 */
	public Tdepartment doSelectManagerByDepartID(Integer departmentID)
	{
		String hql="from Tdepartment as dp where dp.id=?";
		List<Tdepartment> list=this.getHibernateTemplate().find(hql, departmentID);
		if(list!=null&&!list.isEmpty())
		return  list.get(0);
		else
		return null;
	}

}
