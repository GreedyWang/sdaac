package sdaac.wym.dms.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import sdaac.wym.dms.Service.i.IIndicateService;
import sdaac.wym.dms.entity.Indicate;
import app.entity.Tempolyee;

import common.dao.CommonDAO;
/**
 * 指标管理
 * @author SA1KV5
 *
 */
public class IndicateService implements IIndicateService {

	private CommonDAO<Indicate> indicateDao;
	
	
	public CommonDAO<Indicate> getIndicateDao() {
		return indicateDao;
	}

	public void setIndicateDao(CommonDAO<Indicate> indicateDao) {
		this.indicateDao = indicateDao;
	}
	
	/**
	 * 新建指标
	 */
	@Override
	public void doNewIndicate(Indicate item) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 批量新建指标
	 */
	@Override
	public void doNewIndicates(List<Indicate> items) {
		// TODO Auto-generated method stub
		indicateDao.blukFlushInsert(items);
	}

	
	private List<Indicate> doShow(Tempolyee emp) {
		// TODO Auto-generated method stub

		String hql ="from Indicate where area=? and group=? and groupID=? order by worder";
		return indicateDao.select(hql, new Object[]{emp.getDistrict(),emp.getGroup(),emp.getUid()});
	}
	/**
	 * 按照指定格式返回指标查询结果
	 * 格式为 类型-{指标1、指标2、...}
	 * @param emp
	 * @return
	 */
	
	@Override
	public Map<String,List<Indicate>> doShowWithForm(Tempolyee emp){
		List<Indicate> rs = doShow(emp);
		Map<String,List<Indicate>> mapRs = new LinkedHashMap<String, List<Indicate>>();
		for(Indicate item : rs){
			if(mapRs.get(item.getTypeName())==null){
				//如果Map中没有
				List<Indicate> indlist = new ArrayList<Indicate>();
				indlist.add(item);
				mapRs.put(item.getTypeName(),indlist);
			}else{
				mapRs.get(item.getTypeName()).add(item);
			}
		}
		return mapRs;
	}
	
	/**
	 * 查询指标
	 */
	@Override
	public List<Indicate> doShowByCondition(Indicate item) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 弃用指标
	 * @param keys
	 */
	public void doDelete(int[] keys){
		String hql = "update Indicate set state = -1 where id in {?)";
		indicateDao.bulkUpdate(hql, keys);
	}
	
	/**
	 * 查询改组下的指标模板
	 * @param area
	 * @param group
	 * @return
	 */
//	llllllllllll
	public List<Indicate> getModel(String area,String group,String id){
		String hql = "from Indicate where area =? and group=? and groupID=? order by worder";
		return indicateDao.select(hql, new Object[]{area,group,id});
	}
	/**
	 * 更新指标状态
	 * @param state
	 * @param key
	 * @return 1 是禁用了某个类型的所以指标
	 */
	public int doUpdate(int state,int key,String typeName){
		String hql = "update Indicate set state = ? where id =?";
		indicateDao.bulkUpdate(hql, new Object[]{state,key});
		String hql4serach = "from Indicate where id = ?";
		if(state == 0) return 0;//如果是启用指标直接返回0
		Indicate one = indicateDao.selectOne(hql4serach, key);
		//查询该类型的指标是否全部禁用
		String hql2 = "from Indicate where typeName = ? and state = 0 and groupID=? and group=? and area=?";
		if(indicateDao.select(hql2, new Object[]{typeName,one.getGroupID(),one.getGroup(),one.getArea()}).isEmpty()){
			return 1;
			
		}else{
			return 0;
		}
	}
	
	/**
	 * 输入指标ID，
	 * @param key
	 */
	public void doSelectOneANDUpdate(int key,int weight){
		String hql4serach = "from Indicate where id = ?";
		Indicate one = indicateDao.selectOne(hql4serach, key);
		String hql2 = "update Indicate set weight = ? where typeName = ? and groupID=? and group=? and area=?";
		indicateDao.bulkUpdate(hql2, new Object[]{weight,one.getTypeName(),one.getGroupID(),one.getGroup(),one.getArea()});
	}
	
	
	
	/**
	 * 更新指标权重
	 * @param state
	 * @param key
	 */
	public void doUpdateWeight(int value,int key){
		String hql = "update Indicate set weight = ? where id =?";
		indicateDao.bulkUpdate(hql, new Object[]{value,key});
	}
}
