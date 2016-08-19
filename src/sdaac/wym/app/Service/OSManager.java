package sdaac.wym.app.Service;

import java.util.List;

import app.entity.Tempolyee;

import sdaac.wym.app.Dao.hr.OrganizationStructureDao;
import sdaac.wym.app.entity.hr.OrganizationStructure;

public class OSManager implements IOSManager{
	private OrganizationStructureDao osDAO;

	public OrganizationStructureDao getOsDAO() {
		return osDAO;
	}

	public void setOsDAO(OrganizationStructureDao osDAO) {
		this.osDAO = osDAO;
	}

	public List<OrganizationStructure> getAll() {
		// TODO Auto-generated method stub
		//String hql="select os.postionAx,os.postionAy,os.postionBx,os.postionBy from OrganizationStructure as os";
		return this.osDAO.selectAll();
		//return this.osDAO.getPostion();
	}

	public List<Integer[]> getPostion() {
		// TODO Auto-generated method stub
		return this.osDAO.getPostion();
	}

	public List<String[]> getName() {
		// TODO Auto-generated method stub
		return this.osDAO.getNames();
	}

	public Integer add(OrganizationStructure item) {
		// TODO Auto-generated method stub
	
			return (Integer) this.osDAO.insert(item);
	
	}

	public boolean update(OrganizationStructure item) {
		// TODO Auto-generated method stub
		boolean flag=true; 
		try {
			this.osDAO.update(item);
		} catch (RuntimeException e) {
			flag=false;
		}
		 return flag;
	}
	/**
	 * @see查询下属所有部门id
	 * @param String uid
	 */
	public Integer[] selectEmpsByUid(String uid) {
		// TODO Auto-generated method stub
		String hql="select os.departmenetId from OrganizationStructure os where os.fatherid= (select id from OrganizationStructure where uid=?)";
		return (Integer[]) this.osDAO.select(hql, uid).toArray(new Integer[0]);
		//return null;
	}
}
