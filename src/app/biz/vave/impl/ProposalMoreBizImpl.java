package app.biz.vave.impl;

import java.util.List;

import common.dao.CommonDAO;

import app.biz.vave.ProposalMoreBiz;
import app.entity.vave.VaveProposalMore;

public class ProposalMoreBizImpl implements ProposalMoreBiz {
	private CommonDAO<VaveProposalMore> proposalMoreDao=null;

	public CommonDAO<VaveProposalMore> getProposalMoreDao() {
		return proposalMoreDao;
	}

	public void setProposalMoreDao(CommonDAO<VaveProposalMore> proposalMoreDao) {
		this.proposalMoreDao = proposalMoreDao;
	}
	/**
	 * 查询指定proposalID的最大version
	 * @param pk
	 * @return version最大值
	 */
	private Integer doSelectMaxVersion(String pk)
	{
		Integer version=0;
		String hql="select max(vss.version) from VaveProposalMore as vss where vss.proposalId=?";
		List list=this.proposalMoreDao.select(hql, pk);
		if(list!=null&&!list.isEmpty()&&!list.contains(null))
		{
			version=(Integer)list.get(list.size()-1)+1;
		}
		return version;
	}
	/**
	 * 插入提案单详细
	 * @param vaveProposalMore
	 */
	public void doInset(VaveProposalMore item) {
		// TODO Auto-generated method stub
		Integer version=this.doSelectMaxVersion(item.getProposalId());
		item.setVersion(version);
		this.proposalMoreDao.insert(item);
	}
	/**
	 * 查询指定proposalID的最大version的提案单详细
	 * @param String
	 */
	public VaveProposalMore doSelectByProposalIDMaxVersion(String proposalID) {
		// TODO Auto-generated method stub
		String hql="from VaveProposalMore as ss where ss.proposalId=? and ss.version=" +
		"(select max(vss.version) from VaveProposalMore as vss where vss.proposalId=? )";		
		List<VaveProposalMore> list=this.proposalMoreDao.select(hql, new String[]{proposalID,proposalID});
		if(list!=null&&!list.isEmpty())
			return list.get(0);
		else
		return null;
	}
	
}
