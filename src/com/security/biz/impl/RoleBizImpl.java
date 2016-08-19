package com.security.biz.impl;

import java.util.List;
import com.security.biz.RoleBiz;
import common.dao.CommonDAO;
import common.entity.Trole;

public class RoleBizImpl implements RoleBiz {

	private CommonDAO<Trole> roleDao=null;
	
	
	public CommonDAO<Trole> getRoleDao() {
		return roleDao;
	}


	public void setRoleDao(CommonDAO<Trole> roleDao) {
		this.roleDao = roleDao;
	}


	public List<Trole> doSelectByUid(String uid) {
		// TODO Auto-generated method stub
		String hql="from Trole where ";
		this.roleDao.bulkUpdate(hql, uid);
		return null;
	}
	
	public List<Trole> doSelectByUid2(String uid) {
		// TODO Auto-generated method stub
		String hql="from Trole r left join fetch r.tuserRoles ur where ur.tempolyee.uid=?";
		return this.roleDao.select(hql, uid);
	}
	
//	public List doShowAll(String type){
//		String hql = "";
//		roleDao.select(hql, param);
//		return null;
//	}


	public void add(Trole item) {
		// TODO Auto-generated method stub
		this.roleDao.insert(item);
	}


	public List<Trole> getAll() {
		// TODO Auto-generated method stub
		return this.roleDao.selectAll();
	}
	

	public List<Trole> doSelectByCondition(String type) {
		if(type == null || "".equals(type)) return getAll();
		// TODO Auto-generated method stub
		String hql="from Trole r where r.type=?";
		return this.roleDao.select(hql, type);
	}

	public void remove(String roleID) {
		// TODO Auto-generated method stub
		this.roleDao.deleteByPK(roleID);
	}

}
