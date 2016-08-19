package sdaac.wym.dms.biz;

import java.util.List;

import sdaac.wym.dms.entity.Bonus;
import common.dao.CommonDAO;

/**
 * 每月每个区域的奖金数字
 * 对于不用组的奖金查询
 * @author SA1KV5
 *
 */
public class BonusBiz {
	
	private CommonDAO<Bonus> bonusDao;
	
	public CommonDAO<Bonus> getBonusDao() {
		return bonusDao;
	}

	public void setBonusDao(CommonDAO<Bonus> bonusDao) {
		this.bonusDao = bonusDao;
	}

	/**
	 * 根据不同的等级来获得奖金
	 * @param time
	 * @return
	 */
	public float getBonusByLevel(Bonus item){
		String hql = "from Bonus where area=? and version=?";
		Bonus rs = bonusDao.selectOne(hql, new Object[]{item.getArea(),item.getVersion()});
		if(rs == null){
			return 1;
		}else{
			return rs.getBonus();
		}
	}
	
	public void blukAdd(List<Bonus> bonus){
		bonusDao.blukFlushInsert(bonus);
	}

	public List<Bonus> show(String version , String area){
		String hql = "from Bonus where version = ? and area = ?";
		return bonusDao.select(hql, new String[]{version,area});
	}
	
	public void add(String version , String area){
		Bonus item = new Bonus();
		item.setArea(area);
		item.setVersion(version);
		String hql = "from Bonus where version=? and area=?";
		if(bonusDao.select(hql, new String[]{version,area}).isEmpty()){
			bonusDao.insert(item);
		}		
	}
	
	public void update(int id , float bonus){
		String hql ="update Bonus set bonus = ? where id = ?";
		bonusDao.bulkUpdate(hql, new Object[]{bonus,id});
	}
	
	/**
	 * 得到最新版本号
	 * @return
	 */
	public String getlastVersion(){
		String sql = "select max(version) from dms_bonus ";
		List<Object[]> rs = bonusDao.selectBySql(sql);
		if(!rs.isEmpty()){
			return rs.get(0)[0].toString();
		}
		return "无法打分-没有创建版本";
	}
	
	/**
	 * 得到所有版本
	 * @return
	 */
	public String[] getAllVersion(){
		String sql = "select version from dms_bonus group by version order by version desc";
		List<Object[]> rs = bonusDao.selectBySql(sql);
		String[] s = new String[rs.size()];
		if(!rs.isEmpty()){
			for(int i = 0; i<rs.size();i++){
				s[i] = rs.get(i)+"";				
			}
			return s;
		}
		return null;
	}
}
