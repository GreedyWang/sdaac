package app.biz.impl.epor;

import java.util.ArrayList;
import java.util.List;

import common.dao.CommonDAO;

import app.entity.epor.Paydetails;
import app.entity.epor.PrBuyContext;
/**
 * 付款记录
 * @author SA1KV5
 *
 */
public class PaydetailsService {	
	private static int MaxStep = 4;
	private CommonDAO<Paydetails> paydetailsDao;
	public CommonDAO<Paydetails> getPaydetailsDao() {
		return paydetailsDao;
	}

	public void setPaydetailsDao(CommonDAO<Paydetails> paydetailsDao) {
		this.paydetailsDao = paydetailsDao;
	}

	/**
	 * 确认某项付款条目已经付款
	 * @param item
	 */
	public void confirm(Paydetails item){
		
	}
	
	/**
	 * 输入实际付款记录 form Fin
	 * @param PRID
	 * @param IO
	 * @param a_amount
	 * @param a_date
	 */
	public void addActual(String PRID,String IO,String a_amount,String a_date){
		
	}
	
	/**
	 * 显示一个IO的付款合同
	 * 如果有记录显示否者创建4个阶段的付款
	 * @param item
	 * @return
	 */
	private List<Paydetails> showone(PrBuyContext item){
		String prid = item.getPrPrForm().getId();
		String io = item.getIo();
		String name = item.getDescription();
		String hql = "from Paydetails where PRID = ? and io =?";
		List<Paydetails> rs = paydetailsDao.select(hql, new String[]{prid,io});
		if(rs == null || rs.isEmpty()){			
			for(int i = 0 ;i<MaxStep ;i++){
//				rs = new ArrayList<Paydetails>();
				Paydetails e = new Paydetails();
				e.setName(name);
				e.setPRID(prid);
				e.setIo(io);
				rs.add(e);
			}
		}
		return rs;	
	}
	
	/**
	 * 
	 * @param prids
	 * @param ios
	 * @return
	 */
	public List<Paydetails> showAll(List<PrBuyContext> items){
		List<Paydetails> rs = null;
		if(items==null || items.isEmpty()){
			return null;
		}else{
			rs = new ArrayList<Paydetails>();
			for(int i = 0;i<items.size();i++){
				rs.addAll(showone(items.get(i)));
			}
		}
		return rs;
	}
	
	/**
	 * 添加付款预计条目
	 * @param item
	 */
	public void add(List<Paydetails> items){
		if(items.size()>1){
			paydetailsDao.blukFlushInsert(items);
		}else{
			paydetailsDao.insert(items.get(0));
		}
	}
	
	/**
	 * 添加多条付款预计条目
	 * @param item
	 */
	public void addFlash(List<Paydetails> items){
		List<Paydetails> foradd = new ArrayList<Paydetails>();//用于添加
		List<Paydetails> forupdate = new ArrayList<Paydetails>();//用于更新
		for(Paydetails item :items){
			if(item!=null && item.getId()!=0){
				forupdate.add(item);
			}else{
				update(item);
			}
		}
		if(!foradd.isEmpty()){
			add(foradd);
		}
	}	
	
//	/**
//	 * 更新
//	 * @param item
//	 */
//	private void updates(List<Paydetails> items){
////		for(Paydetails )
//	}

	/**
	 * 更新
	 * @param item
	 */
	private void update(Paydetails item){
		String hql = "update Paydetails set f_amount=?,f_date=?,isPaied=? where id=?";
		paydetailsDao.bulkUpdate(hql, new Object[]{item.getF_amount(),item.getF_date(),item.getIsPaied(),item.getId()});
	}
	
	/**
	 * 删除
	 * @param item
	 */
	public void delete(Paydetails item){
		
	}
}
