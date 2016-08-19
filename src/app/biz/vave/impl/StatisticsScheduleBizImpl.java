package app.biz.vave.impl;

import java.util.ArrayList;
import java.util.List;
import common.dao.CommonDAO;
import app.biz.vave.StatisticsScheduleBiz;
import app.entity.Tproposal;
import app.entity.vave.VaveProjectSchedule;
import app.entity.vave.VaveStatisticsSchedule;

public class StatisticsScheduleBizImpl implements StatisticsScheduleBiz {
	private CommonDAO<VaveStatisticsSchedule> statisticsScheduleDao=null;
	private CommonDAO<VaveProjectSchedule> projectScheduleDao=null;

	public CommonDAO<VaveStatisticsSchedule> getStatisticsScheduleDao() {
		return statisticsScheduleDao;
	}

	public void setStatisticsScheduleDao(
			CommonDAO<VaveStatisticsSchedule> statisticsScheduleDao) {
		this.statisticsScheduleDao = statisticsScheduleDao;
	}

	public void doInsert(VaveStatisticsSchedule item) {
		// TODO Auto-generated method stub
		
	}

	public void doUpdate(VaveStatisticsSchedule item) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 插入计算附表
	 */
	public void doInsert(
			String[] projects,String[] quantitys,String[] units,String proposalID)
	{
//		List<VaveStatisticsSchedule> list=new ArrayList<VaveStatisticsSchedule>();
		List<VaveProjectSchedule> projectList=new ArrayList<VaveProjectSchedule>();
		Tproposal proposal=new Tproposal();
		proposal.setId(proposalID);
		Integer version;
		String hql=" from VaveProjectSchedule as ss where ss.tproposal.id=?  order by ss.version desc";
		List<VaveProjectSchedule> flag=projectScheduleDao.select(hql, proposalID);
		if(flag!=null&&!flag.isEmpty())
		{
			version=flag.get(0).getVersion()+1;
		}
		else
		{
			version=0;
		}

		for(int j=0;j<projects.length;j++)
		{
			//get project_schedule
			VaveProjectSchedule project=new VaveProjectSchedule();
			project.setProject(projects[j]);
			project.setQuantity(quantitys[j]);
			project.setUnit(units[j]);
			project.setTproposal(proposal);
			project.setVersion(version);		
			projectList.add(project);
		}
		projectScheduleDao.blukFlushInsert(projectList);
	}
	/**
	 * 查找指定proposalID，最大的Version的计算附表
	 */
	public List<VaveStatisticsSchedule> doSelectByPK(String proposalID)
	{
		String hql="from VaveStatisticsSchedule as ss where ss.tproposal.id=? and ss.version=" +
				"(select max(vss.version) from VaveStatisticsSchedule as vss where vss.tproposal.id=? )";		
		List<VaveStatisticsSchedule> list=this.statisticsScheduleDao.select(hql, new String[]{proposalID,proposalID});
		if(list!=null&&!list.isEmpty())
		return list;
		else
		return null;
	}
	
	public CommonDAO<VaveProjectSchedule> getProjectScheduleDao() {
		return projectScheduleDao;
	}

	public void setProjectScheduleDao(
			CommonDAO<VaveProjectSchedule> projectScheduleDao) {
		this.projectScheduleDao = projectScheduleDao;
	}

}
