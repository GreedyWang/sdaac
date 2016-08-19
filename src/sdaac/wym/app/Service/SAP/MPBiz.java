package sdaac.wym.app.Service.SAP;

import java.util.List;

import common.dao.CommonDAO;

public class MPBiz {

	private CommonDAO<SrMaterialPrice> materialPriceDao;

	public CommonDAO<SrMaterialPrice> getMaterialPriceDao() {
		return materialPriceDao;
	}

	public void setMaterialPriceDao(CommonDAO<SrMaterialPrice> materialPriceDao) {
		this.materialPriceDao = materialPriceDao;
	}
	
	/**
	 * get no price material list
	 * @return
	 */
	public List<SrMaterialPrice> doSelect() {
		String hql ="from SrMaterialPrice where unitPrice is null";
		return materialPriceDao.select(hql, null);
	}
	
	/**
	 * update the assembly material's price
	 * @param materialSN
	 * @param price
	 * @param curr
	 */
	public void doUpdate(String materialSN,float price,String curr ){
		String hql ="update SrMaterialPrice set unitPrice = ? where material=?";
		materialPriceDao.bulkUpdate(hql, new Object[]{price,materialSN});
	}
}
