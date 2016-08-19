package app.biz.vave.impl;

import java.util.List;

import app.biz.vave.CompanySuggestionBiz;
import app.entity.vave.VaveCompanySuggestion;
import common.dao.CommonDAO;
public class CompanySuggestionBIzImpl implements CompanySuggestionBiz {
	private CommonDAO<VaveCompanySuggestion> companySuggestiondao=null;

	public CommonDAO<VaveCompanySuggestion> getCompanySuggestiondao() {
		return companySuggestiondao;
	}

	public void setCompanySuggestiondao(
			CommonDAO<VaveCompanySuggestion> companySuggestiondao) {
		this.companySuggestiondao = companySuggestiondao;
	}
	
	public void doInsert(VaveCompanySuggestion item)
	{
		this.companySuggestiondao.insert(item);
	}
	public VaveCompanySuggestion doSelectByproposalID(String proposalID)
	{
		String hql="from VaveCompanySuggestion where tproposal.id=?";
		List<VaveCompanySuggestion> list=companySuggestiondao.select(hql, proposalID);
		if(null==list||list.isEmpty())
		{
			return null;
		}
		else
		{
			return list.get(list.size()-1);
		}
	}
}
