package app.biz.vave.impl.proposalStateModul;

import app.entity.Tproposal;
import common.dao.CommonDAO;

public abstract class ProposalState {
	public abstract void  agree();
	public abstract void deny();
	public abstract void needImprove();
	public abstract void rationalize();
	
	private CommonDAO<Tproposal> proposalDao;
	public CommonDAO<Tproposal> getProposalDao() {
		return proposalDao;
	}
	public void setProposalDao(CommonDAO<Tproposal> proposalDao) {
		this.proposalDao = proposalDao;
	}
}
