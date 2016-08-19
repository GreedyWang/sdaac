package app.biz.vave;

import java.util.List;

import app.entity.Tempolyee;
import app.entity.vave.VaveTeamWorkProject;
public interface TeamWorkProjectBiz {
	public void doInsert(VaveTeamWorkProject item);
	public List<VaveTeamWorkProject> doSelectByCond(VaveTeamWorkProject item);
	public List<VaveTeamWorkProject> doSelectByTeamWorkID(Integer vaveTeamWorkID);
	public void doDelete(Integer PK);
	public void doUpdateFinishTime(List<VaveTeamWorkProject> teamWorkProject);
	public List<VaveTeamWorkProject> selectMyJobsByTeamWork(Integer vaveTeamWorkID,Tempolyee emp);
	public void updateJob(VaveTeamWorkProject item);
}
