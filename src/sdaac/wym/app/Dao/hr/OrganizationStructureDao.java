package sdaac.wym.app.Dao.hr;

import java.util.List;

import sdaac.wym.app.entity.hr.OrganizationStructure;
import common.dao.impl.CommonSpringDAOImpl;

public class OrganizationStructureDao extends CommonSpringDAOImpl<OrganizationStructure> implements IOrganizationStructure {

	public OrganizationStructureDao(String className)
			throws InstantiationException, IllegalAccessException {
		super(className);
		// TODO Auto-generated constructor stub
	}

	public List<Integer[]> getPostion() {
		// TODO Auto-generated method stub
		String hql="select os.postionAx,os.postionAy from OrganizationStructure as os";
		return this.getHibernateTemplate().find(hql);
	}
	
	public List<String[]> getNames() {
		// TODO Auto-generated method stub
		String hql="select os.id,os.context from OrganizationStructure as os";
		return this.getHibernateTemplate().find(hql);
	}
}
