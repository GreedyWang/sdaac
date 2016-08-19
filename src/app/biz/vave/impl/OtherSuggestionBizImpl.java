package app.biz.vave.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;

import common.dao.CommonDAO;

import app.biz.vave.OtherSuggestionBiz;
import app.entity.TotherSuggestion;

public class OtherSuggestionBizImpl implements OtherSuggestionBiz {

	private CommonDAO<TotherSuggestion> othersugdao=null;

	public CommonDAO<TotherSuggestion> getOthersugdao() {
		return othersugdao;
	}

	public void setOthersugdao(CommonDAO<TotherSuggestion> othersugdao) {
		this.othersugdao = othersugdao;
	}

	public void doInsert(TotherSuggestion item) {
		// TODO Auto-generated method stub
		othersugdao.insert(item);
	}
	/**
	 * 指定proposalID的最新外部门意见
	 * @param proposalID
	 * @return 最大时间
	 */
	private Object[] doSelectMaxDate(String proposalID)
	{
		String hql="select max(os.suggestionDate),os.departmentId from TotherSuggestion as os where os.tproposal.id=? group by os.departmentId";
		List list=this.othersugdao.select(hql, proposalID);
		if(list!=null&&!list.contains(null)&&!list.isEmpty())
		{
			return list.toArray(new Object[0]);
		}
		else
		{
			return null;
		}
	}

	/**
	 * 查看最后次外部门意见
	 * @param 提案id
	 */
	public List<TotherSuggestion> doSelectByproposalID(String proposalID) {
		// TODO Auto-generated method stub	
		Object[] conditions=this.doSelectMaxDate(proposalID);
		String hql="from TotherSuggestion as os where os.tproposal.id=? and os.suggestionDate=? and os.departmentId=?";
		List<TotherSuggestion> results=new ArrayList<TotherSuggestion>();
		if(conditions!=null)
		{
			for(int i=0;i<conditions.length;i++)
			{
				Object[] condition=(Object[])conditions[i];
				List<TotherSuggestion> list =othersugdao.select(hql, new Object[]{proposalID,(Date)condition[0],(Integer)condition[1]});
				results.addAll(list);
			}
		}
		if(results.isEmpty())
		{
			return null;
		}
		else
		{
			return results;
		}
		
	}
	/**
	 * 查最新
	 * @param item
	 * @return
	 */
	public List<TotherSuggestion> doSelectByOtherSug(TotherSuggestion item)
	{
		String hql="from TotherSuggestion as os where os.tproposal.id=? and os.tmainSuggestion.id=?";
		List<TotherSuggestion> list =othersugdao.select(hql, new Object[]{item.getTproposal().getId(),item.getTmainSuggestion().getId()});
		return list;
	}
	
	public List<TotherSuggestion> doSelectByConditions(TotherSuggestion item)
	{
		String hql="from TotherSuggestion as otherSug where otherSug.tproposal.id=? " ;
				//	"and otherSug.departmentId=?";
		Object[] params=new Object[1];
		params[0]=item.getTproposal().getId();
		//params[1]=item.getDepartmentId();
		return othersugdao.select(hql, params);
		
	}
	
	public void doUpdate(TotherSuggestion item)
	{
		String hql="update TotherSuggestion otherSug set otherSug.suggestionType=? ,otherSug.suggest_context=?, otherSug.suggestionDate=?," +
				"otherSug.departmentName=? ,otherSug.approveName=? "+
				" where otherSug.tproposal.id=? and otherSug.departmentId=? and otherSug.tmainSuggestion.id=?";
		Object[] params=new Object[8];
		params[0]=item.getSuggestionType();
		params[1]=item.getSuggest_context();
		params[2]=item.getSuggestionDate();
		params[3]=item.getDepartmentName();
		params[4]=item.getApproveName();
		params[5]=item.getTproposal().getId();
		params[6]=item.getDepartmentId();
		params[7]=item.getTmainSuggestion().getId();
		othersugdao.bulkUpdate(hql, params);
	}

	public void deleteOtherSugByProposalId(String proposalId) {
		// TODO Auto-generated method stub
		String hql="delete from TotherSuggestion as os where os.tproposal.id='"+proposalId+"'";
		this.othersugdao.blukDelete(hql);
	}
}
