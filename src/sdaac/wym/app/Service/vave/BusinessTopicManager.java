package sdaac.wym.app.Service.vave;

import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import common.dao.CommonDAO;

import sdaac.wym.app.entity.vave.BusinessTopic;

public class BusinessTopicManager {
	
	private CommonDAO<BusinessTopic> businessTopicDao;
	
	public CommonDAO<BusinessTopic> getBusinessTopicDao() {
		return businessTopicDao;
	}

	public void setBusinessTopicDao(CommonDAO<BusinessTopic> businessTopicDao) {
		this.businessTopicDao = businessTopicDao;
	}

	/**
	 * 发布新闻
	 * @param pItem
	 */
	public void release(BusinessTopic pItem){
		businessTopicDao.insert(pItem);
	}
	
	/**
	 * 修改
	 * @param pItem
	 */
	public void update(BusinessTopic pItem){
		String hql="update BusinessTopic set businessTitle=?,description=?,createTime=? where id=?";
		
		businessTopicDao.bulkUpdate(hql, new Object[]{pItem.getBusinessTitle(),pItem.getDescription(),pItem.getCreateTime(),pItem.getId()});
	}
	
	/**
	 * 查询
	 * @param pItem
	 * @return
	 */
	public List<BusinessTopic> show(BusinessTopic pItem){
		String hql="from BusinessTopic as bt join fetch bt.publisher as p where id=?";
		return businessTopicDao.select(hql, pItem.getId());
	}
	
	/**
	 * 查询我发布
	 * @param pItem
	 * @return
	 */
	public List<BusinessTopic> showMyRelease(BusinessTopic pItem){
		String hql="from BusinessTopic as bt join fetch bt.publisher as p where p.uid=?";
		return businessTopicDao.select(hql, pItem.getPublisher().getUid());
	}
	
	/**
	 * 按条件查询 年
	 * @param pItem
	 * @return
	 */
	public List<BusinessTopic> doSelect(BusinessTopic pItem){
		String hql="from BusinessTopic as bt join fetch bt.publisher as p where year(createTime)=? ";
		return businessTopicDao.select(hql, Calendar.getInstance().get(Calendar.YEAR));
		
	}
	
	/**
	 * 删除
	 * @param pk
	 */
	public void doDelete(int pk){
		businessTopicDao.deleteByPK(pk);
	}
}
