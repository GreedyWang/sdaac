package app.biz.vave;

import java.util.List;

import app.entity.vave.Pcount;
import app.entity.vave.VaveTeamWork;
public interface TeamWorkBiz {
	public void doInsert(VaveTeamWork item);
	public List<VaveTeamWork> doSelectByCond(VaveTeamWork item);
	public VaveTeamWork doSelectByPorposalID(String proposalID);
	public void updateProjectInfo(VaveTeamWork item);
	public void doManagerMenber(VaveTeamWork item);
	public List<VaveTeamWork> count(VaveTeamWork teamWork);
	public void updateProjectManager(VaveTeamWork item);
	public void changeTeamWorkActSaving(VaveTeamWork pItem);
	public List<Pcount> countTeamWorkSaving(int year);
	public List<Pcount> countTeamWorkSaving2(int year);
}
