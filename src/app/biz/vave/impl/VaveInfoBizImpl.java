package app.biz.vave.impl;

import java.util.ArrayList;
import java.util.List;

import common.dao.CommonDAO;

import app.biz.vave.VaveInfoBiz;
import app.entity.vave.VaveInfo;

public class VaveInfoBizImpl implements VaveInfoBiz {
	private CommonDAO<VaveInfo> vaveInfoDao=null;

	public CommonDAO<VaveInfo> getVaveInfoDao() {
		return vaveInfoDao;
	}

	public void setVaveInfoDao(CommonDAO<VaveInfo> vaveInfoDao) {
		this.vaveInfoDao = vaveInfoDao;
	}

	/**
	 * 删除销售计划
	 */	
	public void doDeleteInfo(VaveInfo item) {
		// TODO Auto-generated method stub
		this.vaveInfoDao.delete(item);
	}
	/**
	 * 增加销售计划
	 */
	public void doInsertInfo(VaveInfo item) {
		// TODO Auto-generated method stub
		this.vaveInfoDao.insert(item);
		
	}
	/**
	 * 查询所有顾客代码
	 */
	public List<String> doSelectCustomCode() {
		// TODO Auto-generated method stub
		String hql="select vi.id.costomCode from VaveInfo as vi group by vi.id.costomCode";
		List list=this.vaveInfoDao.select(hql, null);
		return list;
	}
	/**
	 * 查询车型代码
	 * @param 顾客id
	 */
	public List<String> doSelectVehicleByCustomCode(String custom_code) {
		// TODO Auto-generated method stub
		String hql="select vi.id.vehicles from VaveInfo as vi where vi.id.costomCode=?";
		List list=this.vaveInfoDao.select(hql, custom_code);	
		return list;
	}
	/**
	 * 查询销售量
	 * @param 顾客id,车型id
	 */
	public Long doSelectVolumeByPK(String custom_code, String Vehicle) {
		// TODO Auto-generated method stub
		String hql="select vi.id.volume from VaveInfo as vi where vi.id.costomCode=? and vi.id.vehicles=?";
		List list=this.vaveInfoDao.select(hql, new String[]{custom_code,Vehicle});
		if(list!=null&&!list.isEmpty()&&!list.contains(null))
		{
			return (Long)list.get(0);
		}
		else
		{
		
			return null;
		}
	}
	/**
	 * 查询所有info
	 */
	public List<VaveInfo> doSelectAll()
	{
		return this.vaveInfoDao.selectAll();
	}
}
