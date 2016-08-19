package app.biz.impl.epor;

import java.util.List;

import app.entity.epor.PrBuyer;
import common.dao.CommonDAO;

/**
 * PR采购员数据库操作类
 * @author SA1KV5
 *
 */
public class PrBuyerManager {
	private CommonDAO<PrBuyer> buyerDao;
	
	public PrBuyer doSelectBuyerId(String uname){
		String hql="from PrBuyer where buyId like ?";
		String buyerName = uname;
		if(uname.length() > 3) {
			buyerName = "%"+uname.substring(0, uname.length()-3)+"%";
		}
		return buyerDao.selectOne(hql, buyerName);
	}
	
	public PrBuyer doSelectBuyerId2(String netid){
		String hql="from PrBuyer b join fetch b.uid as e where e.netid = ?";
		return buyerDao.selectOne(hql, netid);
	}
	
	
	/**
	 * 添加采购员
	 * @param pItem
	 */
	public void doAdd(PrBuyer pItem){
		buyerDao.insert(pItem);
	}
	
	/**
	 * 删除采购员
	 * @param key
	 */
	public void doDelete(String key){
		buyerDao.deleteByPK(key);
	}
	
	/**
	 * 得到所有采购员
	 */
	public List<PrBuyer> doSelectAll() {
		return this.buyerDao.selectAll();
	}
	
	/**
	 * 得到所有采购员with NetId
	 */
	public List<PrBuyer> doSelectAllWithNetId() {
		String sql ="from PrBuyer b inner join fetch b.uid";
		return this.buyerDao.select(sql, null);
	}
	
	public List<PrBuyer> doSelectAll(String area) {
		if(area ==null || area.equals("")){
			return doSelectAll();
		}
		String hql = "from PrBuyer b join fetch b.uid where b.area = ?";
		return this.buyerDao.select(hql, area);
	} 

	public CommonDAO<PrBuyer> getBuyerDao() {
		return buyerDao;
	}

	public void setBuyerDao(CommonDAO<PrBuyer> buyerDao) {
		this.buyerDao = buyerDao;
	}
}
