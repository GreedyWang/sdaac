package app.biz.vave.impl;
import java.util.List;
import java.util.Vector;
import common.dao.CommonDAO;
import app.biz.vave.TeamWorkProjectBiz;
import app.entity.Tempolyee;
import app.entity.vave.VaveTeamWorkProject;
public class TeamWorkProjectBizImpl implements TeamWorkProjectBiz {
	private CommonDAO<VaveTeamWorkProject> teamWorkProjectdao=null;

	public CommonDAO<VaveTeamWorkProject> getTeamWorkProjectdao() {
		return teamWorkProjectdao;
	}

	public void setTeamWorkProjectdao(
			CommonDAO<VaveTeamWorkProject> teamWorkProjectdao) {
		this.teamWorkProjectdao = teamWorkProjectdao;
	}
	public void doInsert(VaveTeamWorkProject item)
	{
		teamWorkProjectdao.insert(item);
	}
	public List<VaveTeamWorkProject> doSelectByCond(VaveTeamWorkProject item)
	{
		// 
		String hql="from VaveTeamWorkProject as twProject  inner join fetch twProject.responsiblePerson" +
				" inner join fetch twProject.responsiblePerson.tdepartment " +
				"inner join fetch twProject.vaveTeamWork as tmwork " +
				"inner join fetch tmwork.projectManager where 1=1 ";
		Vector<Object> params=new Vector<Object>();
		if(null!=item.getFinishTiem()&&!item.getFinishTiem().equals(""))
		{
			hql+=" and twProject.finishTiem =? ";
			params.add(item.getFinishTiem());
		}
		if(null!=item.getPlanTiem()&&!item.getPlanTiem().equals(""))
		{
			hql+=" and twProject.planTiem =? ";
			params.add(item.getPlanTiem());
		}
		if(null!=item.getJobContent()&&!item.getJobContent().equals(""))
		{
			hql+=" and twProject.jobContent=?";
			params.add(item.getJobContent());
		}
		if(null!=item.getResponsiblePerson().getName()&&!item.getResponsiblePerson().getName().equals(""))
		{
			hql+=" and twProject.responsiblePerson.name=?";
			params.add(item.getResponsiblePerson().getName());
			
		}
		if(null!=item.getResponsiblePerson().getTdepartment().getId()&&item.getResponsiblePerson().getTdepartment().getId()!=0)
		{
			hql+=" and twProject.responsiblePerson.tdepartment.id=?";
			params.add(item.getResponsiblePerson().getTdepartment().getId());
		}
		if(null!=item.getVaveTeamWork().getTeamName()&&!item.getVaveTeamWork().getTeamName().equals(""))
		{
			hql+=" and tmwork.teamName=?";
			params.add(item.getVaveTeamWork().getTeamName());
		}
	
		if(null!=item.getVaveTeamWork().getProjectManager().getName()&&!item.getVaveTeamWork().getProjectManager().getName().equals(""))
		{
			hql+=" and tmwork.projectManager.name=?";
			params.add(item.getVaveTeamWork().getProjectManager().getName());
		}
		if(null!=item.getVaveTeamWork().getProjectManager().getTdepartment().getId())
		{
			hql+=" and tmwork.projectManager.tdepartment.id=?";
			params.add(item.getVaveTeamWork().getProjectManager().getTdepartment().getId());
		}
		List<VaveTeamWorkProject> list= teamWorkProjectdao.select(hql, params.toArray());
		 return list;
	}
	/**
	 * @param 项目团队id
	 * 查看指定项目下的所有任务 
	 */
	public List<VaveTeamWorkProject> doSelectByTeamWorkID(Integer vaveTeamWorkID) {
		// TODO Auto-generated method stub
		String hql="from VaveTeamWorkProject as tmp inner join fetch tmp.responsiblePerson inner join fetch tmp.responsiblePerson.tdepartment where tmp.vaveTeamWork.id=?";
		return teamWorkProjectdao.select(hql, vaveTeamWorkID);		
	}
	/**
	 * 查看一个项目下，属于自己的任务
	 */
	public List<VaveTeamWorkProject> selectMyJobsByTeamWork(Integer vaveTeamWorkID,Tempolyee emp)
	{
		String hql="from VaveTeamWorkProject as tmp inner join fetch tmp.responsiblePerson " +
					"where tmp.vaveTeamWork.id=? and tmp.responsiblePerson.uid=?";
		return teamWorkProjectdao.select(hql, new Object[]{vaveTeamWorkID,emp.getUid()} );	
	}
	/**
	 * 删除指定任务
	 * @任务ID
	 */
	public void doDelete(Integer PK) {
		// TODO Auto-generated method stub
		this.teamWorkProjectdao.deleteByPK(PK);
	}
	/**
	 * 更新完成日期
	 */
	public void doUpdateFinishTime(List<VaveTeamWorkProject> teamWorkProject)
	{
		String hql="update VaveTeamWorkProject as tp set tp.finishTiem=?,tp.remark=?,tp.state=? where tp.id=? ";
		
		for(VaveTeamWorkProject element:teamWorkProject)
		{
			
				Object[] param=new Object[4];
				param[0]=element.getFinishTiem();
				param[1]=element.getRemark();
				param[2]=VaveTeamWorkProject.JOB_FINISH;
				param[3]=element.getId();
				if(element.getFinishTiem()==null)
				{
					param[2]=VaveTeamWorkProject.JOB_START;
				}
				this.teamWorkProjectdao.bulkUpdate(hql, param);
			
		}
	}
	/**
	 * 管理我的任务
	 */
	public void updateJob(VaveTeamWorkProject item)
	{
		String hql="update VaveTeamWorkProject as tp set ";
		Vector<Object> params=new Vector<Object>();
		if(item.getState()!=null)
		{
			hql+="tp.state=? ,";
			params.add(item.getState());
		}
		if(item.getJobLogs()!=null)
		{
			hql+="tp.jobLogs=? ,";
			params.add(item.getJobLogs());
		}
		if(params.size()>0)
		{
			hql=hql.substring(0, hql.length()-1);
			hql+=" where tp.id=?";
			params.add(item.getId());
			this.teamWorkProjectdao.bulkUpdate(hql, params.toArray());
		}
	}
}
