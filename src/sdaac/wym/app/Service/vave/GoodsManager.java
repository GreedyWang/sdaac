package sdaac.wym.app.Service.vave;

import java.util.List;
import java.util.Vector;

import app.entity.Tempolyee;

import sdaac.wym.app.entity.vave.Account;
import sdaac.wym.app.entity.vave.AccountGoods;
import sdaac.wym.app.entity.vave.Goods;
import common.dao.CommonDAO;

public class GoodsManager {
	private CommonDAO<Goods> goodsDao;
	private CommonDAO<Account> accountDao;
	private CommonDAO<Tempolyee> empDao;
	private CommonDAO<AccountGoods> accountGoodsDao;
	
	/**
	 * 查找全部
	 * @return
	 */
	public List<Goods> show(){
		return goodsDao.selectAll();
	}
	
	/**
	 * 查看购物车
	 * @param pEmp
	 * @return
	 */
	public Account showShopCar(Tempolyee pEmp){
		String hql="from Account where emp.uid=? and state=0";
		List<Account> rs = accountDao.select(hql, new String[]{pEmp.getUid()});
		if(rs!=null&&!rs.isEmpty()){
			return rs.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 查看纪念品详细
	 * @param pk
	 * @return
	 */
	public Goods showDetails(int pk){
		return goodsDao.selectByPk(pk);
	}
	
	/**
	 * 查看历史订单
	 * @param pItem
	 * @return
	 */
	public List<Account> showMyAccount(Account  pItem){
		String hql="from Account ac  join fetch ac.emp as e where 1=1 ";
		Vector<Object> params=new Vector<Object>();
		if(pItem.getEmp().getUid()!=null&&!pItem.getEmp().getUid().equals("")){
			hql+="and e.uid=? ";
			params.add(pItem.getEmp().getUid());
		}
		if(pItem.getBeginTime()!=null&&pItem.getEndTime()!=null){
			hql+="and ";
		}
		return accountDao.select(hql, params.toArray());
		//return accountDao.selectAll();
	}
	
	/**
	 * 查询详细交易记录
	 * @param accountId
	 * @return
	 */
	public List<AccountGoods> showAccountGoods(int accountId){
		String hql="from AccountGoods ag join fetch ag.goods join fetch ag.account as ac where ac.id=?";
		
		return accountGoodsDao.select(hql, accountId);
	}
	
	/**
	 * 查询个人积分
	 * @param pEmp
	 * @return
	 */
	public Tempolyee showAccount(Tempolyee pEmp){
		return empDao.selectByPk(pEmp.getUid());
	}
	
	/**
	 * 下订单
	 * @param pItem
	 */
	public void insertAccount(AccountGoods  pItem){
		//accountDao.insert(pItem.getAccount());
		accountGoodsDao.insert(pItem);
	}
	
	/**
	 * 从购物车删除物品
	 * @param id
	 */
	public void deleteShoppingCar(int id){
		accountGoodsDao.deleteByPK(id);
	}
	
	/**
	 * 添加纪念品
	 * @param pItem
	 */
	public void insertGoods(Goods pItem){
		goodsDao.insert(pItem);
	}
	
	/**
	 * 更新纪念品数量
	 * @param pItem
	 */
	public void updateGoodsQuantity(Goods pItem){
		String hql="update Goods set quantity=? where id=? ";		
		goodsDao.bulkUpdate(hql, new Object[]{pItem.getQuantity(),pItem.getId()});
	}
	
	/**
	 * 更新纪念品信息
	 * @param pItem
	 */
	public void updateGoodsInfo(Goods pItem){
		String hql="update Goods set name=?,price=? where id=? ";		
		goodsDao.bulkUpdate(hql, new Object[]{pItem.getName(),pItem.getPrice(),pItem.getId()});
	}
	
	//--------------------------dao-----------------------
	public CommonDAO<Goods> getGoodsDao() {
		return goodsDao;
	}

	public void setGoodsDao(CommonDAO<Goods> goodsDao) {
		this.goodsDao = goodsDao;
	}

	public CommonDAO<Account> getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(CommonDAO<Account> accountDao) {
		this.accountDao = accountDao;
	}

	public CommonDAO<Tempolyee> getEmpDao() {
		return empDao;
	}

	public void setEmpDao(CommonDAO<Tempolyee> empDao) {
		this.empDao = empDao;
	}
		
	public CommonDAO<AccountGoods> getAccountGoodsDao() {
		return accountGoodsDao;
	}

	public void setAccountGoodsDao(CommonDAO<AccountGoods> accountGoodsDao) {
		this.accountGoodsDao = accountGoodsDao;
	}
}
