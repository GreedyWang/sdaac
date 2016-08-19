package app.biz.vave.impl;
import java.util.List;

import app.biz.vave.ThemeBiz;
import app.entity.Ttheme;

import common.dao.CommonDAO;

public class ThemeBizImpl implements ThemeBiz {

	private CommonDAO<Ttheme> themedao=null;
	


	public int doInsert(Ttheme item) {
		// TODO 自动生成方法存根
		return (Integer)themedao.insert(item);
	}

	public void doSelect() {
		// TODO 自动生成方法存根
		
	}

	public void doUpdate(Ttheme item) {
		// TODO 自动生成方法存根
		themedao.update(item);
	}
	
	public List<Ttheme> doSelectByProposalID(String id)
	{
		String hql="from Ttheme as theme where theme.tproposal.id=?";
		return themedao.select(hql, id);
	}
	public CommonDAO<Ttheme> getThemedao() {
		return themedao;
	}

	public void setThemedao(CommonDAO<Ttheme> themedao) {
		this.themedao = themedao;
	}

	public void doDeleteByPK(Integer pk) {
		// TODO Auto-generated method stub
		this.themedao.deleteByPK(pk);
	}



}
