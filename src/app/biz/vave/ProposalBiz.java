package app.biz.vave;

import java.util.List;

import app.entity.Tempolyee;
import app.entity.Tproposal;
import app.entity.vave.Figure;
import app.entity.vave.PersonalCount;

public interface ProposalBiz {
	public boolean doInsert(Tproposal item);
	public List<Tproposal> doSelect(Tproposal item);
	public List<Tproposal> doSelectByManager(Tempolyee item);
	public void doUpdateState(Tproposal item);
	public Tproposal doSelectByPK(String proposalID);
	public List<PersonalCount> doSelectCount(Integer year);
	public Integer[][] doSelectCountDepartment(Integer year);
	public void doUpdateProposal(Tproposal item);
	public void doDelete(String proposalID);
	public  boolean doFlushInsert(List<Tproposal> items);
	public List<Tproposal> doSelectCompany(Tproposal item,String uid) ;
	public List<Tproposal> doSelectDepartment(Tproposal item,String uid) ;
	public List<Figure> countFigure();
	public List<Tproposal> count1(Tproposal item) ;
	public void updateMoreInfo(Tproposal item);
	public List<Object[]> doProposalRate(String year);
	public List<Object[]> doApprovedPreRate(String year);
	public List<Object[]> doParticipationRate(String year);
}
