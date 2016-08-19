package com.security.biz;
import java.util.List;

import common.entity.Trole;

public interface RoleBiz {
	public List<Trole> doSelectByUid(String uid);
	public List<Trole> getAll();
	public void add(Trole item);
	public void remove(String roleID);
	public List<Trole> doSelectByUid2(String uid);
	public List<Trole> doSelectByCondition(String type) ;
	
}
