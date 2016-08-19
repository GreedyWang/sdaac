package app.biz.impl.epor;

import java.util.ArrayList;
import java.util.List;

import app.entity.epor.PrCostCenter;

import common.dao.CommonDAO;

public class CostCentreManager {
	
	private CommonDAO<PrCostCenter> costCentreDao;
	
	public List<PrCostCenter> doSelectAll(){
		return costCentreDao.selectAll();
	}
	
	public List<PrCostCenter> doSelectAll(String area){
		if(area ==null || area.equals("")) {
			return doSelectAll();
		}
		String hql="from PrCostCenter where area = ?";
		return costCentreDao.select(hql, area);
	}
	
	public List<PrCostCenter> doSelectByDprt(String area,String dprt){
		if(area ==null || area.equals("")) {
			return doSelectAll();
		}
		String hql="from PrCostCenter where area = ? and dprt = ?";
		return costCentreDao.select(hql, new String[]{area,dprt});
	}
	
	public List<PrCostCenter> doSelectAllDprt(String area){	
		String sql = "SELECT dprt FROM PR_costCenter where area = '"+area+"' GROUP BY dprt";
		//String hql="from PrCostCenter where area = ?";
		List<Object[]> temps =  costCentreDao.selectBySql(sql);
		List<PrCostCenter> rs = new ArrayList<PrCostCenter>();
		for(Object item:temps){
			if(item!=null){
				PrCostCenter p = new PrCostCenter();
				p.setDprt(item.toString());
				rs.add(p);
			}
		}
		return rs;
	}
	
	public void doAdd(PrCostCenter pItem){
		costCentreDao.insert(pItem);
	}
	
	/**
	 * add cost center for all sites
	 * @param pItem
	 */
	public void doAddAllSites(PrCostCenter pItem){
		String sites[] = {"DT02","DT03"};
		for(String site : sites ) {
			pItem.setArea(site);
			costCentreDao.insert(pItem);
		}		
	}
	
	public void doDel(PrCostCenter pItem) {
		costCentreDao.deleteByPK(pItem.getId());
	}
	
	public void doUpdate(PrCostCenter pItem) {
		String hql = "update PrCostCenter set ";
		List params = new ArrayList();
		if(pItem.getCostCenterName()!= null &&
				!pItem.getCostCenterName().equals("")) {
			params.add(pItem.getCostCenterName());
			hql += "costCenterName = ?,";
		}
		if(pItem.getCostCenterNameEnglish()!= null &&
				!pItem.getCostCenterNameEnglish().equals("")) {
			params.add(pItem.getCostCenterNameEnglish());
			hql += "costCenterNameEnglish = ?,";
		}
		if(pItem.getRemark()!= null &&
				!pItem.getRemark().equals("")) {
			params.add(pItem.getRemark());
			hql += "remark = ?,";
		}
		if(params.size()!= 0 && pItem.getId()!= null) {
			hql = hql.substring(0, hql.length()-1);
			hql += "where id=?";
			params.add(pItem.getId());
		}
		costCentreDao.bulkUpdate(hql, params.toArray());
	}
	
	public CommonDAO<PrCostCenter> getCostCentreDao() {
		return costCentreDao;
	}

	public void setCostCentreDao(CommonDAO<PrCostCenter> costCentreDao) {
		this.costCentreDao = costCentreDao;
	}
	
	
}
