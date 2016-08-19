package app.biz.vave.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import common.dao.CommonDAO;
import app.biz.vave.ProposalMoreBiz;
import app.biz.vave.TeamWorkBiz;
import app.entity.Tproposal;
import app.entity.vave.Pcount;
import app.entity.vave.VaveProposalMore;
import app.entity.vave.VaveTeamWork;
public class TeamWorkBizImpl implements TeamWorkBiz {
	private CommonDAO<VaveTeamWork> teamWorkDao=null;
//	private CommonDAO<VaveProposalMore> pmDao=null; 

	public CommonDAO<VaveTeamWork> getTeamWorkDao() {
		return teamWorkDao;
	}

	public void setTeamWorkDao(CommonDAO<VaveTeamWork> teamWorkDao) {
		this.teamWorkDao = teamWorkDao;
	}
	public void doInsert(VaveTeamWork item)
	{
		this.teamWorkDao.insert(item);
	}
	/**
	 * 查找我所牵头的项目
	 */
	public List<VaveTeamWork> doSelectByCond(VaveTeamWork item) {
		String hql="from VaveTeamWork as tw inner join fetch tw.projectManager as pm inner join fetch pm.tdepartment " +
				"inner join fetch tw.tproposal as p where " +
				"1=1";
		Vector<Object> params=new Vector<Object>();
		if(item.getTproposal().getState()!=null)
		{
			hql+="and p.state =?";
			params.add(item.getTproposal().getState());
		}
		if(item.getProjectManager().getName()!=null&&!item.getProjectManager().getName().equals(""))
		{
			hql+="and tw.projectManager.name =?";
			params.add(item.getProjectManager().getName());
		}
		if(item.getProjectManager().getTdepartment().getId()!=null)
		{
			hql+="and tw.projectManager.tdepartment.id =?";
			params.add(item.getProjectManager().getTdepartment().getId());
		}
		if(item.getProjectManager().getTdepartment().getName()!=null)
		{
			hql+="and tw.projectManager.tdepartment.name =?";
			params.add(item.getProjectManager().getTdepartment().getName());
		}
		if(item.getPlanfinishtime()!=null&&!item.getPlanfinishtime().equals(""))
		{
			hql+="and tw.planfinishtime =?";
			params.add(item.getPlanfinishtime());
		}
		if(item.getTeamName()!=null&&!item.getTeamName().equals(""))
		{
			hql+=" and tw.teamName like ?";
			params.add("%"+item.getTeamName()+"%");
		}if(item.getWorkTeamId()!=null)
		{
			hql+=" and tw.workTeamId=?";
			params.add(item.getWorkTeamId());
		}
		return	teamWorkDao.select(hql, params.toArray());
		
	}
	/**
	 * @param 提案单id
	 * 通过proposalID查 项目团队
	 */
	public VaveTeamWork doSelectByPorposalID(String proposalID) {
		// TODO Auto-generated method stub
		String hql="from VaveTeamWork as tw inner join fetch tw.projectManager where tw.tproposal.id=?";
		List<VaveTeamWork> list= teamWorkDao.select(hql, proposalID);
		if(list.isEmpty())
		{
			return null;
		}
		else
		{
			return list.get(0);
		}
	}
	/**
	 * @see 跟踪项目
	 */
	public void updateProjectInfo(VaveTeamWork item)
	{
		String hql="update VaveTeamWork as p set p.projectProcess=?, p.projectLogs=?, p.vaveDiscuss=? " +
					"where p.workTeamId=?";
		this.teamWorkDao.bulkUpdate(hql, new Object[]{item.getProjectProcess(),item.getProjectLogs(),item.getVaveDiscuss(),item.getWorkTeamId()});
	}
	/**
	 * 管理项目团队成员
	 */
	public void doManagerMenber(VaveTeamWork item) {
		String hql="update VaveTeamWork as p set p.meneberUid=?, p.meneberName=? where  p.workTeamId=?";
		this.teamWorkDao.bulkUpdate(hql, new Object[]{item.getMeneberUid(),item.getMeneberName(),item.getWorkTeamId()});
	}
	/**
	 * 更变项目经理
	 * @param item
	 */
	public void updateProjectManager(VaveTeamWork item)
	{
		String hql="update VaveTeamWork set projectManager.uid=? where tproposal.id=?";
		teamWorkDao.bulkUpdate(hql, new Object[]{ item.getProjectManager().getUid(),item.getTproposal().getId()});
	}
	/**
	 * 更新vave项目预计节约成本
	 * @param args
	 */
	public void test(ProposalMoreBiz dd) {
		for(VaveTeamWork tw: this.teamWorkDao.selectAll())
		{
			String proposalID=tw.getTproposal().getId();
		
			VaveProposalMore asd=dd.doSelectByProposalIDMaxVersion(proposalID);
			String hql="update VaveTeamWork set estimatedCostSavings=? where workTeamId=?";
			teamWorkDao.bulkUpdate(hql, new Object[]{asd.getExpectSaving(),tw.getWorkTeamId()});
		}
	}
	
	/**
	 * 改变项目实际节约金额
	 * @param pItem
	 */
	public void changeTeamWorkActSaving(VaveTeamWork pItem){
		
		String hql="update VaveTeamWork vt set ";
		List<Object> param = new ArrayList<Object>();
		//param.add(pItem.getWorkTeamId());
		if(pItem.getEstimatedCostSavings()!=null){
			hql+="vt.estimatedCostSavings=?,";
			param.add(pItem.getEstimatedCostSavings());
		}
		if(pItem.getActualCostSavings()!=null){
			hql+="vt.actualCostSavings=?,";
			param.add(pItem.getActualCostSavings());
		}
		if(pItem.getActualEndingTime()!=null){
			hql+="vt.actualEndingTime=?,";
			param.add(pItem.getActualEndingTime());
		}
		if(pItem.getPlanfinish()!=null){
			hql+="vt.planfinishtime=?,";
			param.add(pItem.getPlanfinish());
		}
		hql = hql.substring(0, hql.length()-1);
		hql+=" where vt.workTeamId=?";
		param.add(pItem.getWorkTeamId());
		teamWorkDao.bulkUpdate(hql, param.toArray(new Object[0]));
	}
	
	
	/**
	 * 统计vave项目
	 * @param teamWork
	 * @return
	 */
	public List<VaveTeamWork> count(VaveTeamWork teamWork)
	{
		/** sql->
		select teamName, name,partsName,partsID,applicable_models,planfinishtime,dyjycb,estimated_cost_savings 
		from dbo.VAVE_TeamWork  t
		inner join hr1.dbo.tEmpolyee e on e.uid=t.projectManager
		inner join tProposal p on p.id=t.proposalId
		inner join tTheme tt on tt.proposalID = p.id
		where p.state=4
		*/
		Vector<Object> params=new Vector<Object>();
		String hql="from VaveTeamWork tw join fetch tw.projectManager " +
				"join fetch tw.tproposal p " +
				"join fetch p.vaveCompanySuggestions cs "+
				"join fetch p.proposalPerson where 1=1 ";				
		if(teamWork.a!=null&&!teamWork.a.equals("")){
			hql+=" and cs.suggestionDate>='"+teamWork.a+"'";
		}
		if(teamWork.b!=null&&!teamWork.b.equals("")){
			hql+=" and cs.suggestionDate<='"+teamWork.b+"'";
		}
		if(teamWork.getTproposal().getState()!=null&&teamWork.getTproposal().getState()!=-3){
			hql+=" and p.state=?";
			params.add(teamWork.getTproposal().getState());
		}
		if(teamWork.getTproposal().getSource()!=null){
			hql+=" and p.source=?";
			params.add(teamWork.getTproposal().getSource());
		}
		return teamWorkDao.select(hql, params.toArray());
	}
	
	/**
	 * 得到项目节约金额统计系数
	 * @param pdate
	 * @param year
	 * @return
	 */
	private float getSaving(Date pdate , int year){
		int a = pdate.getYear()+1900;
		if(a==year ){
			return (float)(12-pdate.getMonth())/12;
		}else if (a==year-1){
			return (float)(pdate.getMonth())/12;
		}else{
			System.out.println("ERROR:"+a);
			return 0;
		}
			
	}
	
	
	/**
	 * 统计项目当年/年度预计节约金额
	 * @param year
	 * @return
	 */
	public List<Pcount> countTeamWorkSaving(int year){
		int intyear=year;
		String hql="select projectManager.uid,sum(estimatedCostSavings) from VaveTeamWork where year(projectBeginTime)=? group by projectManager.uid";
		//年度预计节约金额
		String hql2="select projectManager,sum(estimated_cost_savings) from VAVE_TeamWork where year(planfinishtime)='"+intyear+"' group by projectManager";
		//年度节约金额
		String hql3="from VaveTeamWork vt join fetch vt.projectManager as pm join fetch pm.tdepartment where year(planfinishtime)=? or year(planfinishtime)=?";//当年和前一年实际节约金额
		List<Object[]> rs2=teamWorkDao.selectBySql(hql2);
		List<VaveTeamWork> rs3= teamWorkDao.select(hql3, new Object[]{intyear,intyear-1});
		List<Pcount> pcounts = new ArrayList<Pcount>();
		//List<VaveTeamWork> rs4 = new ArrayList<VaveTeamWork>();
		Map<String, VaveTeamWork> map = new HashMap<String, VaveTeamWork>();
		//合并同项
		for(VaveTeamWork item : rs3){	
			VaveTeamWork temp = map.get(item.getProjectManager().getUid());
			if(temp==null){
				item.setEstimatedCostSavings(getSaving(item.getPlanfinishtime(),intyear)*item.getEstimatedCostSavings());
				map.put(item.getProjectManager().getUid(), item);
			}else{
				temp.setEstimatedCostSavings(temp.getEstimatedCostSavings()+getSaving(temp.getPlanfinishtime(),intyear)*temp.getEstimatedCostSavings());
				 
			}
		}
		
		for(String key:map.keySet()){	
			Pcount pcount = new Pcount();
			VaveTeamWork temp = map.get(key);
			
			System.out.println("====>"+temp.getWorkTeamId());
			
			pcount.setUname(temp.getProjectManager().getName());
			
			pcount.setEsyearSaving( pcount.getEsyearSaving() + temp.getEstimatedCostSavings());
			pcount.setDepartmentName(temp.getProjectManager().getTdepartment().getName());
			for(Object[] item2:rs2){
				if(temp.getProjectManager().getUid().equals(item2[0])){
					pcount.setCuresyearSaving((Float) item2[1]);
					
				}		
			}
			pcounts.add(pcount);		
		}
		return pcounts;
	}
	
	/**
	 * 统计项目当年/年度节约金额
	 * @param year
	 * @return
	 */
	public List<Pcount> countTeamWorkSaving2(int year){
		int intyear=year;
		//String hql="select projectManager.uid,sum(estimatedCostSavings) from VaveTeamWork where year(projectBeginTime)=? group by projectManager.uid";
		//年度节约金额
		String hql2="select projectManager,sum(actual_cost_savings) from VAVE_TeamWork where year(actualfinishtime)='"+intyear+"' group by projectManager";
		//年度节约金额
		String hql3="from VaveTeamWork vt join fetch vt.projectManager as pm join fetch pm.tdepartment where year(actualfinishtime)=? or year(actualfinishtime)=?";//当年和前一年实际节约金额
		List<Object[]> rs2=teamWorkDao.selectBySql(hql2);
		List<VaveTeamWork> rs3= teamWorkDao.select(hql3, new Object[]{intyear,intyear-1});
		List<Pcount> pcounts = new ArrayList<Pcount>();
		//List<VaveTeamWork> rs4 = new ArrayList<VaveTeamWork>();
		Map<String, VaveTeamWork> map = new HashMap<String, VaveTeamWork>();
		//合并同项
		for(VaveTeamWork item : rs3){	
			VaveTeamWork temp = map.get(item.getProjectManager().getUid());
			if(temp==null){
				item.setActualCostSavings(getSaving(item.getActualEndingTime(),intyear)*item.getActualCostSavings());
				map.put(item.getProjectManager().getUid(), item);
			}else{
				temp.setActualCostSavings(temp.getActualCostSavings()+getSaving(temp.getActualEndingTime(),intyear)*temp.getActualCostSavings());
			}
		}
		
		for(String key:map.keySet()){	
			Pcount pcount = new Pcount();
			VaveTeamWork temp = map.get(key);
			pcount.setUname(temp.getProjectManager().getName());
			pcount.setYearSaving( pcount.getYearSaving() + temp.getActualCostSavings());
			pcount.setDepartmentName(temp.getProjectManager().getTdepartment().getName());
			for(Object[] item2:rs2){
				if(temp.getProjectManager().getUid().equals(item2[0])){
					pcount.setCuryearSaving((Float) item2[1]);
				}		
			}
			pcounts.add(pcount);		
		}
		return pcounts;
	}
	
	
	
	public static void main(String[] args) {
//		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
//		TeamWorkBizImpl p=(TeamWorkBizImpl)context.getBean("teamWorkBiz");
//		ProposalMoreBiz s=(ProposalMoreBizImpl) context.getBean("proposalMoreBiz");
//		List<Pcount> ss = p.countTeamWorkSaving(2009);
		
		String aa="update app.entity.vave.VaveTeamWork set workTeamId=?,estimatedCostSavings=?,";
		System.out.println(aa);
		System.out.println(aa.substring(0, aa.length()-1));
		
//		for(Pcount a : ss){
//			System.out.println(a.getUid()+":"+a.getCuresyearSaving()+":"+a.getEsyearSaving());
//		}
	
	}
}
