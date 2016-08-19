package sdaac.wym.app.Service.Lab;

import java.util.ArrayList;
import java.util.List;
import sdaac.wym.app.entity.lab.LabForm;
import sdaac.wym.app.entity.lab.SLabForm;
import common.dao.CommonDAO;

public class FormBiz {
	private CommonDAO<LabForm> formDao;
	private CommonDAO<SLabForm> sformDao;
	
	public CommonDAO<SLabForm> getSformDao() {
		return sformDao;
	}

	public void setSformDao(CommonDAO<SLabForm> sformDao) {
		this.sformDao = sformDao;
	}

	public CommonDAO<LabForm> getFormDao() {
		return formDao;
	}

	public void setFormDao(CommonDAO<LabForm> formDao) {
		this.formDao = formDao;
	}
	
	public void doAdd(LabForm item){
		formDao.insert(item);
	}
	
	public List<LabForm> doSelectALL(LabForm item){
		return formDao.selectAll();
	}
	
	public List<LabForm> doSelectSingleForm(LabForm item){
//		 f join fetch f.applicant join fetch f.applicant.tdepartment
		StringBuffer sb = new StringBuffer("from SLabForm f join fetch f.applicant join fetch f.applicant.tdepartment where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(item.getApplicant().getUid()!=null && !item.getApplicant().getUid().equals("")) {
			sb.append("and f.applicant.uid = ?");
			params.add(item.getApplicant().getUid());
		}			
		return formDao.select(sb.toString(),params.toArray());		
	}
	
	/**
	 * 查询
	 * @param item
	 * @return
	 */
	public List<LabForm> doSelect(LabForm item){
//		 
		StringBuffer sb = new StringBuffer("from LabForm f join fetch f.applicant join fetch f.applicant.tdepartment where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(item.getApplicant().getUid()!=null && !item.getApplicant().getUid().equals("")) {
			sb.append("and f.applicant.uid = ?");
			params.add(item.getApplicant().getUid());
		}
		if(item.getSsid()!=null ) {
			sb.append("and f.ssid = ?");
			params.add(item.getSsid());
		}	
		if(params!=null && params.size()>0)
			return formDao.select(sb.toString(),params.toArray());
		else
			return formDao.select(sb.toString(),null);
	}
}
