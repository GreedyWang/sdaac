package sdaac.wym.app.Service.vave;

import java.util.List;

import sdaac.wym.app.Dao.vave.IsuggestDao;
import sdaac.wym.app.entity.vave.Suggestion;
import app.entity.Tproposal;

public class ProposalManager implements IProposalManager{

	private IsuggestDao sugDao;  
	private SuggestBiz sugBiz;
	

	public void add(Tproposal item) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @see 审批
	 */
	public void approve(Suggestion item) {
		// TODO Auto-generated method stub
		//通过意见类型改变状态
		sugBiz.approve(item);
		//持久化 意见，提案表
		sugDao.insert(item);
		//持久化 提案状态表
	}
	
	public void remove(String id) {
		// TODO Auto-generated method stub
		
	}

	public Tproposal selectDatils(String pk) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Tproposal> selet(Tproposal item) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Tproposal> seletList(Tproposal item) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Tproposal item) {
		// TODO Auto-generated method stub
		
	}
	//getter and setter
	public SuggestBiz getSugBiz() {
		return sugBiz;
	}

	public void setSugBiz(SuggestBiz sugBiz) {
		this.sugBiz = sugBiz;
	}

	public IsuggestDao getSugDao() {
		return sugDao;
	}

	public void setSugDao(IsuggestDao sugDao) {
		this.sugDao = sugDao;
	}

}
