package sdaac.wym.dms.biz;
import java.util.ArrayList;
import java.util.List;

import common.dao.CommonDAO;

import app.entity.Tempolyee;
/**
 * <p>为dms人员信息提供业务方法</p>
 * @author SA1KV5
 *
 */
public class EmpBiz {
	
	private CommonDAO<Tempolyee> empdao;	
	public CommonDAO<Tempolyee> getEmpdao() {
		return empdao;
	}
	public void setEmpdao(CommonDAO<Tempolyee> empdao) {
		this.empdao = empdao;
	}
	
	private int[] groupLevels = {0,0,0,2,3};//组等级编号 3区域 2大组 1小组
	
	/*
	 * 得到下级组编号
	 */
	private int getLowGroup(int group){
		return groupLevels[group];
	}
	
	/**
	 * <p>查询我的下属</p>
	 * @param item <area,group>
	 * @return
	 */
	public List<Tempolyee> doShowMyTeam(Tempolyee item){
		String hql="from Tempolyee where district =? and group=? and leaderID=? and isSeparation=1";
		return empdao.select(hql, new Object[]{item.getDistrict(),Tempolyee.getNextLevel(item.selectedGroup),item.getUid()});
	}
	
	/**
	 * <p>查询我的下属</p>
	 * @param item <area,group>
	 * @return
	 */
	List<Tempolyee> rsAll;
	private void doShowMyTeamAllC(Tempolyee item){
		String hql="from Tempolyee where district =? and group=? and leaderID=? and isSeparation=1";
		List<Tempolyee> rs = empdao.select(hql, new Object[]{item.getDistrict(),Tempolyee.getNextLevel(item.selectedGroup),item.getUid()});
		
		for(Tempolyee emp : rs){
			rsAll.add(emp);
			if(emp.getGroup()!=null && !"0".equals(emp.getGroup()) && !"1".equals(emp.getGroup()))
				emp.selectedGroup = emp.getGroup();
				doShowMyTeamAllC(emp);
		}
	}
	
	public List<Tempolyee> doShowMyTeamAll(Tempolyee item){
		rsAll = new ArrayList<Tempolyee>();
		doShowMyTeamAllC(item);
		return rsAll;
	}
	
	/**
	 * 得到综合组人数
	 * @param area
	 * @return
	 */
	public int getGenMemberCount(String area,String group){
		return 0;
	}
	
	/**
	 * 得到所有下属员工人数
	 * @param item
	 * @return
	 */
	public int totalCount=0;
	public void doGetMemberCount(Tempolyee item){
		List<Tempolyee> ls = getSupper(item);
		if(ls.isEmpty()){
//			totalCount += ls.size();
		}else{
			totalCount += ls.size();
			for(Tempolyee emp : ls){
				doGetMemberCount(emp);
			}
		}
//		totalCount = 0;
	}
	
	private List<Tempolyee> getSupper(Tempolyee item){
		String hql = "from Tempolyee where leaderID=? and isSeparation=1";
		List<Tempolyee> ls = empdao.select(hql, new Object[]{item.getUid()});
		return ls;
	}
	
	/**
	 * 转岗
	 * @param item
	 */
	public void doChangePosition(String district,String group, String[] uids){

		String hql = "update Tempolyee set district=? and group=? where uid in (?)";
		empdao.bulkUpdate(hql,new Object[]{district,group,uids});
	}
	
	/**
	 * 离职
	 * @param item
	 */
	public void dismission(Tempolyee item){
		
	}
	
	/**
	 * 多名员工离职
	 * @param uids
	 */
	public void dismission(String[] uids){
		if(uids == null || "".equals(uids)) return;
		String param="'a'";
		for(String uid:uids){
			param += ",'"+uid+"'";
		}
		String hql = "update Tempolyee set isSeparation = 0 where uid in ("+param+")";

		empdao.bulkUpdate(hql, null);
	}
	
	/**
	 * 查询组人数
	 * @param area
	 * @param group
	 */
	public int doCount(String area,String group,String leaderUid){
		return 0;
	}
	
	/**
	 * 得到综合组的人员系数乘积和
	 */
	public float doCountGen(){
		return 1*1;
	}
	
	public String getLeader(String uid){
		String hql = "from Tempolyee where uid =?";
		Tempolyee emp = empdao.selectOne(hql, uid);
		return emp.getLeaderID();
	}
	
	public String[] getSameAreaGroupNames(String district,String workgroup){
		String sql = "select name,uid from tEmpolyee where district='"+district+"' and workgroup ='"+workgroup+"'";
		List<Object[]> rs = empdao.selectBySql(sql);
		String[] names = new String[rs.size()];
		int i = 0;
		for(Object[] object : rs){
			names[i] = object[1]+"";
			System.out.println(object[0]+"");
			i++;
		}
		return names;
	}

}
