package com.security.biz.impl;

import java.util.List;

import app.entity.Tempolyee;

import com.security.biz.UserRoleBiz;
import common.dao.CommonDAO;
import common.entity.Trole;
import common.entity.TuserRole;

public class UserRoleBizImpl implements UserRoleBiz {
	
	private CommonDAO<TuserRole> userRoleDao=null;
	
	public CommonDAO<TuserRole> getUserRoleDao() {
		return userRoleDao;
	}

	public void setUserRoleDao(CommonDAO<TuserRole> userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

	public List<TuserRole> doSelectByUid2(String uid) {
		// TODO Auto-generated method stub
		 String hql="from TuserRole as ur where ur.tempolyee.uid=?";
		 List<TuserRole> list=this.userRoleDao.select(hql, uid);
		 if(list!=null&&!list.isEmpty())
		 {
			 return list;
		 }
		 else
		 {
			 return null;
		 }
	}
	/**
	 * 按照工号，角色类型查询角色
	 * @param uid,typeName
	 * @return
	 */
	public List<TuserRole> doSelectByCondition(String uid,String typeName) {
		if(typeName==null || "".equals(typeName)) return doSelectByUid2(uid);
		// TODO Auto-generated method stub
		 String hql="from TuserRole as ur where ur.tempolyee.uid=? and ur.trole.type=?";
		 List<TuserRole> list=this.userRoleDao.select(hql, new String[]{uid,typeName});
		 if(list!=null&&!list.isEmpty())
		 {
			 return list;
		 }
		 else
		 {
			 return null;
		 }
	}
	
	public List doSelectByUid(String uid) {
		// TODO Auto-generated method stub
		 String hql="select ur.trole.roleid from TuserRole as ur where ur.tempolyee.uid=?";
		 List list=this.userRoleDao.select(hql, uid);
		 if(list!=null&&!list.isEmpty())
		 {
			 return list;
		 }
		 else
		 {
			 return null;
		 }
	}

	public void addRightsExpectOld(List<TuserRole> items,String uid) {
		// TODO Auto-generated method stub
		String hql="from TuserRole as ur where ur.tempolyee.uid=? ";
		List<TuserRole> rs=this.userRoleDao.select(hql, uid);
		List<TuserRole> addRights=items;
		for(TuserRole ur:rs)
		{
			for(TuserRole ur2:items)
			{
				if(ur2.getTrole().getRoleid()==ur.getTrole().getRoleid())
				{
					addRights.remove(ur2);		
				}
			}
		}
		this.userRoleDao.blukFlushInsert(addRights);
	}

	public void addRights(TuserRole item) {
		// TODO Auto-generated method stub
		this.userRoleDao.insert(item);
	}

	public void deleteRightsExt(String uid, String roleId) {
		// TODO Auto-generated method stub
		//TuserRole item=new TuserRole(new Trole(roleId),new Tempolyee(uid));
		String hql="delete from TuserRole as tr where tr.tempolyee.uid=? and tr.trole.roleid=?";
		this.userRoleDao.bulkUpdate(hql, new String[]{uid,roleId});
	}
	
	
	public List<TuserRole> doSelectByCondition(String typeName,String plant,int i) {
		//if(typeName==null || "".equals(typeName)) return doSelectByUid2(uid);
		// TODO Auto-generated method stub trole
		 String hql="from TuserRole as ur inner join fetch ur.tempolyee inner join fetch ur.trole where ur.trole.type=? and ur.tempolyee.eprownerarea like ?";
		 List<TuserRole> list=this.userRoleDao.select(hql, new String[]{typeName,"%"+plant+"%"});
		 if(list!=null&&!list.isEmpty())
		 {
			 return list;
		 }
		 else
		 {
			 return null;
		 }
	}
	
	public List<TuserRole> doSelectByCondition(String typeName) {
		//if(typeName==null || "".equals(typeName)) return doSelectByUid2(uid);
		// TODO Auto-generated method stub trole
		 String hql="from TuserRole as ur inner join fetch ur.tempolyee inner join fetch ur.trole where ur.trole.type=? ";//and ur.tempolyee.uid = '5003'
		 List<TuserRole> list=this.userRoleDao.select(hql, new String[]{typeName});
		 if(list!=null&&!list.isEmpty())
		 {
			 return list;
		 }
		 else
		 {
			 return null;
		 }
	}

}
