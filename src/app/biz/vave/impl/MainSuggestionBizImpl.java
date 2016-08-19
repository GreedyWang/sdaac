package app.biz.vave.impl;

import java.util.List;
import java.util.Set;
import java.util.Vector;
import common.dao.CommonDAO;
import app.biz.vave.MainSuggestionBiz;
import app.entity.Tempolyee;
import app.entity.TmainSuggestion;
import app.entity.Tproposal;
import app.entity.vave.VaveProposalState;

public class MainSuggestionBizImpl implements MainSuggestionBiz {
	private  CommonDAO<TmainSuggestion> mainsugdao=null;

	public List<TmainSuggestion> doSelectByDepartID(Tempolyee employee,Tproposal item) {
		// TODO 自动生成方法存根
		String othersid="%"+employee.getTdepartment().getId()+",%";
		String hql="from TmainSuggestion as ms inner join fetch ms.tproposal as tp inner join fetch ms.tproposal.proposalPerson inner join fetch ms.tproposal.proposalPerson.tdepartment where ms.tproposal.state>=1 and ms.implementationPartment like ?";
		
		Vector<Object> params=new Vector<Object>();
		params.add(othersid);
		if(item.getProposalPerson().getTdepartment().getId()!=null&&item.getProposalPerson().getTdepartment().getId()!=0)
		{
			hql+="and tp.proposalPerson.tdepartment.id=? and tp.state>0"; 
			params.add(item.getProposalPerson().getTdepartment().getId());
		}
		if(item.getProposalPerson().getName()!=null&&!item.getProposalPerson().getName().equals(""))
		{
			hql+="and tp.proposalPerson.proposalPerson.name=? "; 
			params.add(item.getProposalPerson().getName());
		}
		if(item.getState()!=null&&!item.getState().equals("")&&item.getState()!=-3)
		{
			hql+="and tp.state=? ";
			params.add(item.getState());
		}
		if(item.getTitle()!=null&&!item.getTitle().equals(""))
		{
			hql+="and tp.title=? ";
			params.add(item.getTitle());
		}
		if(item.getLastModifyTime()!=null&&!item.getLastModifyTime().equals(""))
		{
			if(item.get_selectBeginTime()!=null&&!item.get_selectBeginTime().equals(""))
			{
				hql+="and tp.lastModifyTime between ? and ?";
				params.add(item.getLastModifyTime());
				params.add(item.get_selectBeginTime());
			}
			else
			{
				hql+="and DATEDIFF(day, tp.lastModifyTime, ?) <1 and DATEDIFF(day, tp.lastModifyTime, ?) >-1";
				params.add(item.getLastModifyTime());
				params.add(item.getLastModifyTime());
			}
		}
		if(item.getId()!=null&&!item.getId().equals(""))
		{
			hql+="and tp.id=?";
			params.add(item.getId());
		}
		if(item.getProposalPerson().getUid()!=null&&!item.getProposalPerson().getUid().equals(""))
		{
			hql+="and tp.proposalPerson.uid=?";
			params.add(item.getProposalPerson().getUid());
		}
		List<TmainSuggestion> list=mainsugdao.select(hql, params.toArray());
		return this.setProposalState(list, employee.getUid());
	}

	public CommonDAO<TmainSuggestion> getMainsugdao() {
		return mainsugdao;
	}

	public void setMainsugdao(CommonDAO<TmainSuggestion> mainsugdao) {
		this.mainsugdao = mainsugdao;
	}
	public void doInsert(TmainSuggestion mainSug)
	{
		mainsugdao.insert(mainSug);
	}
	
	public TmainSuggestion doSelectByProposalID(String id)
	{
		String hql="from TmainSuggestion as ms where ms.tproposal.id=?"; 
		List<TmainSuggestion> mainSugList= mainsugdao.select(hql, id);
		if(mainSugList!=null&!mainSugList.isEmpty())
		{
			return mainSugList.get(mainSugList.size()-1);
		}
		else
		{
			return null;
		}
	}
	
	private List<TmainSuggestion> setProposalState(List<TmainSuggestion> list,String uid)
	{
		for(TmainSuggestion item:list)
		{
			Tproposal proposal=item.getTproposal();
			if(proposal.getVaveProposalState()!=null&&!proposal.getVaveProposalState().isEmpty())
			{
				Set<VaveProposalState> set=proposal.getVaveProposalState();
				for(VaveProposalState state:set)
				{
					if(state.getId().getOwner().equals(uid))
					{
						proposal.setIsOpen(state.getIsOpen());
					}
				}
				
			}
		}
		return list;
	}
}
