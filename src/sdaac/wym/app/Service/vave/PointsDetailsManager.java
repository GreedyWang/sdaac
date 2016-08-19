package sdaac.wym.app.Service.vave;

import java.io.Serializable;
import java.util.List;

import sdaac.wym.app.entity.vave.PointsDetails;
import common.dao.CommonDAO;

/**
 * 积分管理
 * @author SA1KV5
 *
 */
public class PointsDetailsManager {
	
	private CommonDAO<PointsDetails> pointsdetailsDao;
	
	/**
	 * 查询个人某年节约总金额
	 * @param pItem
	 * @return
	 */
	public PointsDetails getTotlalSaving(PointsDetails pItem){
		String hql="from PointsDetails where year=? and uid=?";
		List<PointsDetails> rs= pointsdetailsDao.select(hql, new Object[]{pItem.getYear(),pItem.getUid()});
		if(rs!=null&&!rs.isEmpty()){
			return rs.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 添加积分
	 * @param pItem
	 */
	public void add(PointsDetails pItem){
		PointsDetails pd=null;
		if(getTotlalSaving(pItem)!=null){
			pd=getTotlalSaving(pItem);
		}else{
			pd=new PointsDetails();
			pd.setUid(pItem.getUid());
			pd.setYear(pItem.getYear());
			pd.setTotalSaving(new Long(0));
			pd.setId((Integer) newRecord(pd));
		}		
		String hql="update PointsDetails set totalSaving=? where id=? ";
		double aa=pd.getTotalSaving()+pItem.getTotalSaving();
		pointsdetailsDao.bulkUpdate(hql, new Object[]{aa,pd.getId()});
	}
	
	/**
	 * 添加一条新记录 
	 * @param pItem
	 * @return
	 */
	public Serializable newRecord(PointsDetails pItem){
		return pointsdetailsDao.insert(pItem);
	}

	public CommonDAO<PointsDetails> getPointsdetailsDao() {
		return pointsdetailsDao;
	}

	public void setPointsdetailsDao(CommonDAO<PointsDetails> pointsdetailsDao) {
		this.pointsdetailsDao = pointsdetailsDao;
	}
}
