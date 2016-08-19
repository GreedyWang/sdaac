package app.biz.vave;

import app.entity.vave.VaveProposalMore;

public interface ProposalMoreBiz {
	public void doInset(VaveProposalMore item);
	public VaveProposalMore doSelectByProposalIDMaxVersion(String proposalID);
}
