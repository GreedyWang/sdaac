package sdaac.wym.app.Service.SAP;

import common.dao.CommonDAO;
import java.util.List;
/**
 * supply CURD for SAPBOM Table 
 * @author SA1KV5
 *
 */
public class BomBiz {
	
	private CommonDAO<SapBom> bomDao;
	
	

	public CommonDAO<SapBom> getBomDao() {
		return bomDao;
	}



	public void setBomDao(CommonDAO<SapBom> bomDao) {
		this.bomDao = bomDao;
	}



	/**
	 * Search SAPBOM Table by assembly
	 * @param materialSN
	 * @return
	 */
	public List<SapBom> doSelect(String materialSN) {
		String hql = "from SapBom where assembly= ?";
		return bomDao.select(hql, materialSN);
	}
}
