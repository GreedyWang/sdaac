package sdaac.wym.dms.Service;

import java.util.ArrayList;
import java.util.List;

import sdaac.wym.dms.entity.Templete;
import common.dao.CommonDAO;
/**
 * define roles's indicates
 * @author SA1KV5
 *
 */
public class TempleteService {
	private CommonDAO<Templete> templeteDao;

	public CommonDAO<Templete> getTempleteDao() {
		return templeteDao;
	}

	public void setTempleteDao(CommonDAO<Templete> templeteDao) {
		this.templeteDao = templeteDao;
	}
	
	public List<Templete> doShowByCond(String district , String group){
		String hql="from Templete t inner join fetch t.indicate where t.district=? and t.workgroup=?";
		return templeteDao.select(hql, new Object[]{district,group});
	}
}
