package app.biz.vave;

import java.util.List;

import app.entity.vave.VaveProposalState;
import app.entity.vave.VaveProposalStateId;

public interface ProposalStateBiz {
	public void doUpdate(VaveProposalState item);
	public void doInsert(VaveProposalState item);
	public VaveProposalState doSelectByPK(VaveProposalState item);
	public List<VaveProposalState> doSelectByUid(String uid);
	public void doUpateOpen(String proposalID,String ownerID);
	public void doSimpleInsert(String proposalID,String ownerID);
	public void doDelete(VaveProposalStateId item);
	
}
