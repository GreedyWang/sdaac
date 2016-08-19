package com.security.biz;

import java.util.List;

import common.entity.TuserRole;

public interface UserRoleBiz {
	public List doSelectByUid(String uid);
	public void addRightsExpectOld(List<TuserRole> items,String uid);
	public void addRights(TuserRole item);
	public void deleteRightsExt(String uid,String roleId);
	public List<TuserRole> doSelectByUid2(String uid);
	public List<TuserRole> doSelectByCondition(String uid,String typeName);
	public List<TuserRole> doSelectByCondition(String typeName,String plant,int i);
	public List<TuserRole> doSelectByCondition(String typeName);
}
