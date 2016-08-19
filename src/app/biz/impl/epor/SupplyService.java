package app.biz.impl.epor;

import java.util.List;
import app.entity.epor.PRSupply;
import common.dao.CommonDAO;

public class SupplyService {
	private CommonDAO<PRSupply> supplyDao ;

	
	public CommonDAO<PRSupply> getSupplyDao() {
		return supplyDao;
	}


	public void setSupplyDao(CommonDAO<PRSupply> supplyDao) {
		this.supplyDao = supplyDao;
	}


	/**
	 * display all supply
	 */
	public List<PRSupply> showall(){
		return supplyDao.selectAll();
	}
}
