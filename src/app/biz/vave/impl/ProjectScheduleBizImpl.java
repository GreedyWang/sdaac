package app.biz.vave.impl;

import java.util.List;

import common.dao.CommonDAO;

import app.biz.vave.ProjectScheduleBiz;
import app.entity.vave.VaveProjectSchedule;

public class ProjectScheduleBizImpl implements ProjectScheduleBiz {
	private CommonDAO<VaveProjectSchedule> projectScheduleDao=null;

	public CommonDAO<VaveProjectSchedule> getProjectScheduleDao() {
		return projectScheduleDao;
	}

	public void setProjectScheduleDao(
			CommonDAO<VaveProjectSchedule> projectScheduleDao) {
		this.projectScheduleDao = projectScheduleDao;
	}

	public void doInsert(VaveProjectSchedule item) {
		// TODO Auto-generated method stub
		
	}

	public void doUpdate(VaveProjectSchedule item) {
		// TODO Auto-generated method stub
		
	}
	/**
	 *  查询最大version的指定proposalID的计算附表
	 */
	public List<VaveProjectSchedule> doSelectByProposalIDMaxVersion(
			String proposalID) {
		// TODO Auto-generated method stub
		String hql="from VaveProjectSchedule as ss where ss.tproposal.id=? and ss.version=" +
		"(select max(vss.version) from VaveProjectSchedule as vss where vss.tproposal.id=? )";		
		List<VaveProjectSchedule> list=this.projectScheduleDao.select(hql, new String[]{proposalID,proposalID});
		if(list==null||list.isEmpty())
		return null;
		else
			return list;
	}

	
}
