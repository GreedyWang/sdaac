package sdaac.wym.dms.biz;

import java.util.List;
import sdaac.wym.dms.entity.Ver;
import common.dao.CommonDAO;

public class VersionBiz {	
	private CommonDAO<Ver> myverDao;


	public CommonDAO<Ver> getMyverDao() {
		return myverDao;
	}

	public void setMyverDao(CommonDAO<Ver> myverDao) {
		this.myverDao = myverDao;
	}

	/**
	 * 在查询的条件下的版本是否已经完成
	 * @param param
	 * @return
	 */
	public boolean isFinish(Ver param){
		String hql = "from Ver where version=? and area=? and workgourp=? and leaderuid=? and isFinish=?";
		List<Ver> rs = myverDao.select(hql, new Object[]{param.getVersion(),param.getArea(),param.getWorkgourp(),param.getLeaderuid(),1});
		if(rs.isEmpty()) return false;
		else{
			return true;
		}
	}
	
	/**
	 * 确认完成
	 * @param param
	 */
	public void finish(Ver param){
		String hql = "from Ver where version=? and area=? and workgourp=? and leaderuid=?";
		List<Ver> rs = myverDao.select(hql, new Object[]{param.getVersion(),param.getArea(),param.getWorkgourp(),param.getLeaderuid()});
		if(rs.isEmpty()){
			param.setIsFinish(1);
			myverDao.insert(param);
		}else{
			String hql2 = "update Ver set isFinish = 1 where version=? and area=? and workgourp=? and leaderuid=? ";
			myverDao.bulkUpdate(hql2, new Object[]{param.getVersion(),param.getArea(),param.getWorkgourp(),param.getLeaderuid()});
		}
	}
	
	/**
	 * 取消完成
	 * @param param
	 */
	public void undo(Ver param){
		String hql = "from Ver where version=? and area=? and workgourp=? and leaderuid=?";
		List<Ver> rs = myverDao.select(hql, new Object[]{param.getVersion(),param.getArea(),param.getWorkgourp(),param.getLeaderuid()});
		if(rs.isEmpty()){
			param.setIsFinish(0);
			myverDao.insert(param);
		}else{
			String hql2 = "update Ver set isFinish = 0 where version=? and area=? and workgourp=? and leaderuid=? ";
			myverDao.bulkUpdate(hql2, new Object[]{param.getVersion(),param.getArea(),param.getWorkgourp(),param.getLeaderuid()});
		}
	}
}
