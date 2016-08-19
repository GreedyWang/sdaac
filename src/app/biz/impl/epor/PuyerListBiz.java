package app.biz.impl.epor;

import java.util.ArrayList;
import java.util.List;

import app.entity.epor.PrBuyerList;
import app.entity.epor.PrCostCenter;
import common.dao.CommonDAO;

public class PuyerListBiz {
	private CommonDAO<PrBuyerList> prFormDao ;

	public CommonDAO<PrBuyerList> getPrFormDao() {
		return prFormDao;
	}

	public void setPrFormDao(CommonDAO<PrBuyerList> prFormDao) {
		this.prFormDao = prFormDao;
	}
	
	
	public List<PrBuyerList> doSelectAllKind(){	
		String sql = "SELECT kind FROM PR_BuyerList GROUP BY kind";
		List<Object[]> temps =  prFormDao.selectBySql(sql);
		List<PrBuyerList> rs = new ArrayList<PrBuyerList>();
		for(Object item:temps){
			if(item!=null){
				PrBuyerList p = new PrBuyerList();
				p.setKind(item.toString());
				rs.add(p);
			}
		}
		return rs;
	}
	
	public List<PrBuyerList> doSelectAllCategroy(String kind){	
		String sql = "SELECT categroy FROM PR_BuyerList where kind ='"+kind+"' GROUP BY categroy";
		List<Object[]> temps =  prFormDao.selectBySql(sql);
		List<PrBuyerList> rs = new ArrayList<PrBuyerList>();
		for(Object item:temps){
			if(item!=null){
				PrBuyerList p = new PrBuyerList();
				p.setCategroy(item.toString());
				rs.add(p);
			}
		}
		return rs;
	}
	
	public List<PrBuyerList> doSelectBuyerByCondition(String kind,String categroy){	
		String sql = "SELECT buyerName,buyerNetid FROM PR_BuyerList where kind ='"+kind+"' and categroy ='"+categroy+"'";
		List<Object[]> temps =  prFormDao.selectBySql(sql);
		List<PrBuyerList> rs = new ArrayList<PrBuyerList>();
		for(Object[] item:temps){
			if(item!=null){
				PrBuyerList p = new PrBuyerList();
				p.setBuyerName(item[0].toString());
				p.setBuyerNetid(item[1].toString());				
				rs.add(p);
			}
		}
		return rs;
	}
}
