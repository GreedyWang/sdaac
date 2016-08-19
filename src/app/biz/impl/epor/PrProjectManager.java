package app.biz.impl.epor;

import java.util.ArrayList;
import java.util.List;

import app.entity.epor.PrCostCenter;
import app.entity.epor.PrProject;
import common.dao.CommonDAO;

/**
 * 处理项目信息
 * version 1
 * 2010-8-4
 * @author sa1kv5
 *
 */
public class PrProjectManager {
	
	private CommonDAO<PrProject> projectDao;
	
	public void doSelectByKey(String key){
		
	}
	
	public PrProject doSelect(int id){
		return projectDao.selectByPk(id);
	}
	
	/**
	 * 查询所有项目
	 */
	public List<PrProject> doSelectAll(){
		String sql = "SELECT managerUid,context,id PR_project,ar_no FROM PR_project where projectstate <> -1";
		List<Object[]> temps =  projectDao.selectBySql(sql);
		List<PrProject> rs = new ArrayList<PrProject>();
		for(Object[] item:temps){
			if(item!=null){
				PrProject p = new PrProject();
				p.setManagerUid(item[0]==null?"":item[0].toString());
				p.setContext(item[1]==null?"":item[1].toString());
				p.setId(item[2]==null?1:Integer.parseInt(item[2].toString()));
				p.setArno(item[3]==null?"":item[3].toString());
				rs.add(p);
			}
		}		
		return rs;
	}
	
	public List<PrProject> doSelectAllarno(){
		String sql = "SELECT ar_no FROM PR_project where projectstate <> -1 GROUP BY ar_no";
		List<Object[]> temps =  projectDao.selectBySql(sql);
		List<PrProject> rs = new ArrayList<PrProject>();
		for(Object item:temps){
			if(item!=null){
				PrProject p = new PrProject();
				p.setArno(item.toString());
				rs.add(p);
			}
		}
		return rs;		
	}
	
	public void doSelect(PrProject pItem){
		
	}
	
	/**
	 * 添加PR项目
	 * @param pItem
	 */
	public void doInsert(PrProject pItem){
		projectDao.insert(pItem);
	}
	
	public void doUpdate(PrProject pItem){
		projectDao.update(pItem);
	}
	
	public void doFlushInsert(List<PrProject> pItems){
		
	}
	
	public void doDelete(PrProject pItem){
		projectDao.deleteByPK(pItem.getId());
	}	
	
	
	public CommonDAO<PrProject> getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(CommonDAO<PrProject> projectDao) {
		this.projectDao = projectDao;
	}
	
}
