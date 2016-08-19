package app.biz.vave;

import java.util.List;

import app.entity.vave.VaveProjectSchedule;

public interface ProjectScheduleBiz {
	public void doInsert(VaveProjectSchedule item);
	public void doUpdate(VaveProjectSchedule item);
	public List<VaveProjectSchedule> doSelectByProposalIDMaxVersion(String proposalID);
}
