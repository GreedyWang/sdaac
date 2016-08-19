package sdaac.wym.app.Service;

import sdaac.wym.app.entity.conf.Conf;
import common.dao.CommonDAO;

public class ConfManager {
	
	private CommonDAO<Conf> confDao;
	
	public CommonDAO<Conf> getConfDao() {
		return confDao;
	}

	public void setConfDao(CommonDAO<Conf> confDao) {
		this.confDao = confDao;
	}

	/**
	 * 显示总游览量
	 * @return 次数 long
	 */
	public long showTheAmountofTour(){
		return confDao.selectByPk(1).getTotleSum();
	}
	
	/**
	 * 增加一次记录
	 */
	public void addOnce(){
		String hql="update Conf set totleSum=?";
		long totalNum=showTheAmountofTour()+1;
		confDao.bulkUpdate(hql, totalNum);
	}
}
