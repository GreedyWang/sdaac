package sdaac.wym.app.Service.SAP;

import java.util.Date;

import common.dao.CommonDAO;
import java.util.List;;
/**
 * supply CURD for price table 
 * @author SA1KV5
 *
 */
public class PriceBiz {
	
	private CommonDAO<Price> priceDao;
	
	public CommonDAO<Price> getPriceDao() {
		return priceDao;
	}

	public void setPriceDao(CommonDAO<Price> priceDao) {
		this.priceDao = priceDao;
	}
	
	/**
	 * search single price
	 * @param materialSN
	 * @param validateTime
	 * @return
	 */
	public Price doSelect(String materialSN,Date validateTime) {
		String hql = "from Price where material = ?";
		List<Price> rs = priceDao.select(hql, materialSN);
		if(rs !=null && rs.size()>0) {
			return rs.get(0);
		}else {
			return null;
		}
		
	}
}
