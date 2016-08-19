package sdaac.wym.dms.Service;

import java.util.List;

import sdaac.wym.dms.entity.Score;

import common.dao.CommonDAO;

public class ScoreSnapBiz {
	private CommonDAO<Score> snapDao;


	public CommonDAO<Score> getSnapDao() {
		return snapDao;
	}

	public void setSnapDao(CommonDAO<Score> snapDao) {
		this.snapDao = snapDao;
	}
	
	
	/**
	 * 添加
	 * @param ls
	 */
	private void doAdd(List<Score> ls){		
		snapDao.blukFlushInsert(ls);
	}
	
	/**
	 * 删除
	 * @param item
	 */
	private void doDelete(Score item){		
		String hql="delete from Score s where s.area='"+item.getArea()+"' and s.workgroup='"+item.getWorkgroup()+"' and s.version='"+item.getVersion()+"' and s.leaderUid='"+item.getLeaderUid()+"'";
		snapDao.blukDelete(hql);
	}
	/**
	 * 保存得分结果到ScoreSnap表
	 * @param ls
	 */
	public void doSaveSnap(List<Score> ls){
		if(ls!=null && !ls.isEmpty()){
			doDelete(ls.get(0));
			doAdd(ls);
		}
	}
	
	/**
	 * 查询 少个leaderUid
	 * @param item
	 * @return
	 */
	public List<Score> doSelect(Score item){
		String hql="from Score s inner join fetch s.emp where s.area=? and s.workgroup=? and s.version=? and s.leaderUid=?";
		return snapDao.select(hql, new Object[]{item.getArea(),item.getWorkgroup(),item.getVersion(),item.getLeaderUid()});
	}
	
	public Score doSelectOne(Score item){
		String hql="from Score s inner join fetch s.emp where s.area=? and s.workgroup=? and s.version=? and s.emp.uid=?";
		return snapDao.selectOne(hql, new Object[]{item.getArea(),item.getWorkgroup(),item.getVersion(),item.getLeaderUid()});
	}
	
	/**
	 * 得到区域平均奖金
	 * @return
	 */
	public float getAreaAvgBonus(Score item){
		String sql = "select avg(totalBouns)/2 from DMS_ScoreSnap s inner join tempolyee e on e.uid = s.uid where e.district='"+item.getArea()+"' and s.workgroup='"+item.getWorkgroup()+"' and version='"+item.getVersion()+"' and leaderuid='"+item.getLeaderUid()+"'";
		List<Object[]> rs = snapDao.selectBySql(sql);
		if(rs!=null && !rs.isEmpty()&& rs.get(0)!=null){
			
			return Float.parseFloat(rs.get(0)+"");
		}else{
			return 0f;
		}
	}
}
