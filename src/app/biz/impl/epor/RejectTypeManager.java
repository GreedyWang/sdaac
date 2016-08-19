package app.biz.impl.epor;

import java.util.List;

import app.entity.epor.PrRejectType;
import common.dao.CommonDAO;

public class RejectTypeManager {
	private CommonDAO<PrRejectType> rejectTypeDao;

	public CommonDAO<PrRejectType> getRejectTypeDao() {
		return rejectTypeDao;
	}

	public void setRejectTypeDao(CommonDAO<PrRejectType> rejectTypeDao) {
		this.rejectTypeDao = rejectTypeDao;
	}	
	
	public List<PrRejectType> showRes(){
		return  rejectTypeDao.selectAll();
	}
}
