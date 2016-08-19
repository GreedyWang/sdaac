package app.biz.impl.epor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.sdaac.pr.approve.PRProcessEngine;
import com.sdaac.pr.approve.element.FormControlElement;
import common.dao.CommonDAO;

import app.entity.epor.*;
public class AuthorizedImpl {
	
	private FormService formService;
	private PRProcessEngine processEng ;
	private CommonDAO<AuthorizedForm> authorizedFormDao;
	
	private PrPrForm createCondition(int type,int total){
		PrPrForm cond = new PrPrForm();
		cond.setTotal((float)total);
		cond.setIsCapital(type);
		
		return cond;
	}
	
	/**
	 * query the AuthorizedForm
	 * @param uid
	 * @return
	 */
	private List<AuthorizedForm> queryAuthorized(String uid){
		Calendar c = Calendar.getInstance();
		Date now = c.getTime();
		// and from < ? and to > ?
		String hql = "from AuthorizedForm where uid = ?";
		return authorizedFormDao.select(hql, new String[]{uid});
	}
	
	/**
	 * 根据uid查询所有授权的请购单
	 * @param uid
	 * @return
	 */
	public List<PrPrForm> hasAuthorized(String uid){
		List<AuthorizedForm> afs = queryAuthorized(uid);
		List<PrPrForm> rs = new ArrayList<PrPrForm>();;
		if(afs ==null || afs.isEmpty()) return rs;
		for(AuthorizedForm af : afs){
			rs.addAll(getAuthorizedForms(af.getOwner(),af.getUid(),af.getType(),af.getTotal()));
		}
		return rs;
	}
	
	/**
	 * 查询单个授权的请购单
	 * @param owner
	 * @param uid
	 * @param type
	 * @param total
	 * @return
	 */
	private List<PrPrForm> getAuthorizedForms(String owner,String uid,int type,int total){
		List<FormControlElement> forms = processEng.doQueryApprovingList(owner);
		String noid ="'-1'";
		StringBuffer sb = new StringBuffer();
		for(FormControlElement e:forms){
			if(e!=null){
				sb.append("'");
				sb.append(e.getPrformid());
				sb.append("',");
			}
		}
		sb.append(noid);
		
		List<PrPrForm> rs = formService.doSelectLight(createCondition(type,total),sb.toString());
		return rs;
	}
	
	public List<PrPrForm> excludeAuthorizedForms(List<PrPrForm> forms){
		return null;
	}
	
	private void addAuthorized(AuthorizedForm item){
		authorizedFormDao.insert(item);
	}
	
	public void addAuthorized(String owner,String uid,String type,String total,String from,String to){
		AuthorizedForm form = new AuthorizedForm();
		form.setOwner(owner);
		form.setFrom(from);
		form.setTo(to);
		form.setUid(uid);
		form.setTotal(Integer.parseInt(total));
		form.setType(Integer.parseInt(type));
		addAuthorized(form);
	}
	
	public List<AuthorizedForm> selectAuthorizedForm(String uid){
		String hql ="from AuthorizedForm where owner = ?";
		return authorizedFormDao.select(hql, uid);
		//return null;
	}
	
	public void deleteAuthorized(){
		
	}
	
	public void updateAuthorized(){
		
	}

	public FormService getFormService() {
		return formService;
	}

	public void setFormService(FormService formService) {
		this.formService = formService;
	}

	public PRProcessEngine getProcessEng() {
		return processEng;
	}

	public void setProcessEng(PRProcessEngine processEng) {
		this.processEng = processEng;
	}

	public CommonDAO<AuthorizedForm> getAuthorizedFormDao() {
		return authorizedFormDao;
	}

	public void setAuthorizedFormDao(CommonDAO<AuthorizedForm> authorizedFormDao) {
		this.authorizedFormDao = authorizedFormDao;
	}

	
	
	
}
