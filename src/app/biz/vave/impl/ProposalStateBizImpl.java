package app.biz.vave.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import common.dao.CommonDAO;

import app.biz.vave.ProposalStateBiz;
import app.entity.Tproposal;
import app.entity.vave.VaveProposalState;
import app.entity.vave.VaveProposalStateId;

public class ProposalStateBizImpl implements ProposalStateBiz {

	private CommonDAO<VaveProposalState> proposalStateDao=null;
	public CommonDAO<VaveProposalState> getProposalStateDao() {
		return proposalStateDao;
	}

	public void setProposalStateDao(CommonDAO<VaveProposalState> proposalStateDao) {
		this.proposalStateDao = proposalStateDao;
	}

	public void doInsert(VaveProposalState item) {
		// TODO Auto-generated method stub
		this.proposalStateDao.insert(item);
	}

	public VaveProposalState doSelectByPK(VaveProposalState item) {
		// TODO Auto-generated method stub
		String hql="from VaveProposalState as ps where ps.id.tproposal.id=? and ps.id.owner=?";
		List<VaveProposalState> list=this.proposalStateDao.select(hql,new Object[]{item.getId().getTproposal().getId(),item.getId().getOwner()} );
		if(list!=null&&!list.isEmpty())
			return list.get(0);
		else
		return null;
	}
	/**
	 * 更新（提案-人表）的isOpen
	 */
	public void doUpdate(VaveProposalState item) {
		// TODO Auto-generated method stub
		String hql="update VaveProposalState as ps set ps.isOpen=? where ps.id.tproposal.id=? and ps.id.owner=?";
		this.proposalStateDao.bulkUpdate(hql, new Object[]{item.getIsOpen(),item.getId().getTproposal().getId(),item.getId().getOwner()});
	}
	/**
	 * 更新（提案-人表）的isOpen为打开状态
	 * @param proposalID,UserID
	 */
	public void doUpateOpen(String proposalID,String ownerID)
	{
		String hql="update VaveProposalState as ps set ps.isOpen=1 where ps.id.tproposal.id=? and ps.id.owner=?";
		this.proposalStateDao.bulkUpdate(hql, new String[]{proposalID,ownerID} );
	}
	/**
	 * 更新（提案-人表）的isOpen为未打开状态
	 * @param proposalID,UserID
	 */
	public void doUpateOpen(String proposalID,String ownerID,Integer isOpen)
	{
		String hql="update VaveProposalState as ps set ps.isOpen=0 where ps.id.tproposal.id=? and ps.id.owner=?";
		this.proposalStateDao.bulkUpdate(hql, new String[]{proposalID,ownerID} );
	}
	/**
	 * 删除（提案-人表）
	 */
	public void doDelete(VaveProposalStateId psID)
	{
		VaveProposalState item2=new VaveProposalState(psID);
		VaveProposalState ss=this.doSelectByPK(item2);
		if(ss!=null)
		{
			VaveProposalState item=new VaveProposalState(psID,null);
			this.proposalStateDao.delete(item);
		}
		
	}
	/**
	 * 插入（提案-人表）
	 * @param proposalID,UserID
	 */
	public void doSimpleInsert(String proposalID,String ownerID)
	{
		VaveProposalState item=new VaveProposalState();
		item.setId(new VaveProposalStateId(new Tproposal(proposalID),ownerID));
		item.setIsOpen(0);
		this.proposalStateDao.insert(item);
	}
	/**
	 * 查询我所有的提案
	 * @param uid
	 */
	public List<VaveProposalState> doSelectByUid(String uid)
	{
		String hql="from VaveProposalState as ps where ps.id.owner=?";
		return this.proposalStateDao.select(hql, uid);
	}
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		ProposalStateBiz ddd=(ProposalStateBiz)
		context.getBean("proposalStateBiz");
		//test
		Tproposal proposal=new Tproposal();
		proposal.setId("VAVE_88887_Wed Apr 01 09:57:31 CST 2009");
		VaveProposalStateId id=new VaveProposalStateId(proposal,"8002");
		//Integer flag=1;
		//VaveProposalState item=new VaveProposalState(id,flag);
		
		
		//ddd.doInsert(item);
		//System.out.println(ddd.doSelectByPK(item));
		//ddd.doUpdate(item);
		ddd.doDelete(id);
		
	}
	
}
