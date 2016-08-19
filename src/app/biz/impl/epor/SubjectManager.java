package app.biz.impl.epor;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.entity.epor.Subject;
import common.dao.CommonDAO;
/**
 * 总账科目业务类
 * @author sa1kv5
 *
 */
public class SubjectManager {
	
	private CommonDAO<Subject> subjectDao;
		
	public CommonDAO<Subject> getSubjectDao() {
		return subjectDao;
	}

	public void setSubjectDao(CommonDAO<Subject> subjectDao) {
		this.subjectDao = subjectDao;
	}
	
	public Subject doSelect(int id){
		return subjectDao.selectByPk(id);
	}
	
	public List<Subject> doSelectAll(){
		return subjectDao.selectAll();
	}
	
	public List<Subject> doSelectAll(String area){
		if(area == null || area.equals("")) {
			return doSelectAll();
		}
		String hql="from Subject where area = ? ";
		return subjectDao.select(hql, area);
	}
	
	public void doUpdate(Subject pItem) {
		subjectDao.update(pItem);
	}
	
	public void doAdd(Subject pItem) {
		subjectDao.insert(pItem);
	}
	
	/**
	 * add subject for all sites
	 * @param pItem
	 */
	public void doAddAllSite(Subject pItem) {
		String[] sites = {"DT02","DT03"};
		for(String site : sites) {
			pItem.setArea(site);
			subjectDao.insert(pItem);
		}
		
	}
	
	public void doDel(Subject pItem) {
		subjectDao.deleteByPK(pItem.getId());
	}
	
//	public static void main(String[] args) {
//		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		SubjectManager sm = (SubjectManager)ac.getBean("subjectManager");
//		System.out.println(sm.doSelectAll());;
//	}
}
