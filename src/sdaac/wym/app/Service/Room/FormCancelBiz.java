package sdaac.wym.app.Service.Room;

import java.util.ArrayList;
import java.util.List;

import common.dao.CommonDAO;

public class FormCancelBiz {
	private CommonDAO<FormCancel> formCancelDao;

	public CommonDAO getFormCancelDao() {
		return formCancelDao;
	}

	public void setFormCancelDao(CommonDAO formCancelDao) {
		this.formCancelDao = formCancelDao;
	} 
	
	public void doAdd(FormCancel item) {
		formCancelDao.insert(item);
	}
	
	public List<FormCancel> doSelect(FormCancel item) {
		StringBuffer hql = new StringBuffer();
		List params = new ArrayList();
		hql.append("from FormCancel f where 1=1 ");
		if(item.getCid()!=null && item.getCid()!= 0) {
			hql.append("and f.cid = ? ");
			params.add(item.getCid());
		}
		if(item.getUid()!=null && !"".equals(item.getUid())) {
			hql.append("and f.uid = ? ");
			params.add(item.getUid());
		}
		if (item.getB() != null && item.getE() != null) {
			hql.append("and f.b = ? ");
			params.add(item.getB());
			hql.append("and f.e =? ");
			params.add(item.getE());

		}
		return	formCancelDao.select(hql.toString(), params.toArray());
	}
}
