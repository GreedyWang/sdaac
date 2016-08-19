package app.biz.impl.epor;


import java.util.List;

import app.entity.epor.PrPrForm;
import app.entity.epor.SubjectDetail;
import common.dao.CommonDAO;

public class SubjectDetailService {
	private CommonDAO<SubjectDetail> subjectDetailDao;
	private CommonDAO<PrPrForm> prformDao;
	public CommonDAO<PrPrForm> getPrformDao() {
		return prformDao;
	}

	public void setPrformDao(CommonDAO<PrPrForm> prformDao) {
		this.prformDao = prformDao;
	}

	public CommonDAO<SubjectDetail> getSubjectDetailDao() {
		return subjectDetailDao;
	}

	public void setSubjectDetailDao(CommonDAO<SubjectDetail> subjectDetailDao) {
		this.subjectDetailDao = subjectDetailDao;
	}
	
	/**
	 * 已经使用的
	 * @param id
	 * @return
	 */
	public float gethasUsed(int id){
		String sql = "select count(total) where subjectid = ? and year()=? and state = ?";
		List<Object[]> rs = prformDao.selectBySql(sql);
		return Float.parseFloat(rs.get(0)[0]+"");
	}
	
	public float getinprocess(){
		String state = "state >=5 and state <> 12 and state <>13";
		String sql = "select count(total) where subjectid = ? and year()=? and state = ?";
		List<Object[]> rs = prformDao.selectBySql(sql);
		return Float.parseFloat(rs.get(0)[0]+"");
	}
	
	public void show(String subject){
		
	}
}
