package app.biz.vave.impl;

import java.util.List;

import common.dao.CommonDAO;

import app.biz.vave.SupplyBiz;
import app.entity.vave.VaveSupply;

public class SupplyBizImpl implements SupplyBiz {

	private CommonDAO<VaveSupply> supplyDao=null;
	
	public CommonDAO<VaveSupply> getSupplyDao() {
		return supplyDao;
	}

	public void setSupplyDao(CommonDAO<VaveSupply> supplyDao) {
		this.supplyDao = supplyDao;
	}
	
	public List<String> doSelectSimpleName() {
		// TODO Auto-generated method stub
		String hql="select s.simpleName from VaveSupply as s";
		List list= this.supplyDao.select(hql, null);
		return list;
	}
}
