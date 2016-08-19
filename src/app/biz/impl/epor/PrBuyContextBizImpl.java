package app.biz.impl.epor;

import java.util.ArrayList;
import java.util.List;

import common.dao.CommonDAO;

import app.biz.epor.PrBuyContextBiz;
import app.entity.epor.PrBuyContext;

public class PrBuyContextBizImpl implements PrBuyContextBiz {

	private CommonDAO<PrBuyContext> prBuyContextDao=null;
	
	public List<PrBuyContext> doSelectContextByPrFormID(Integer ID) {
		// TODO Auto-generated method stub
		String hql="from PrBuyContext as pbc where pbc.prPrForm.id=?";
		return this.prBuyContextDao.select(hql, ID);
	}
	
	public String doUpdateWithCheck(String finishdate,String order_no){
		String sql = "select prformid from pr_buycontext where order_no = '"+order_no+"'";
		List rs = prBuyContextDao.selectBySql(sql);		
		if(!rs.isEmpty()){
			String sql4update = "update PrBuyContext set finishDate = ? where orderNo = ?";			
			prBuyContextDao.bulkUpdate(sql4update, new String[]{finishdate,order_no});
			return (String)rs.get(0);
		}
		return "";
	}
	
	public CommonDAO<PrBuyContext> getPrBuyContextDao() {
		return prBuyContextDao;
	}
	public void setPrBuyContextDao(CommonDAO<PrBuyContext> prBuyContextDao) {
		this.prBuyContextDao = prBuyContextDao;
	}
}
