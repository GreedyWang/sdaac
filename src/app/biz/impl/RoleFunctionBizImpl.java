package app.biz.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import common.dao.CommonDAO;
import common.entity.TroleFunction;

import app.biz.RoleFunctionBiz;
import app.biz.vave.ProposalStateBiz;
import app.entity.Tproposal;
import app.entity.vave.VaveProposalStateId;

public class RoleFunctionBizImpl implements RoleFunctionBiz {

	private CommonDAO<TroleFunction> roleFunDao=null;
	
	public CommonDAO<TroleFunction> getRoleFunDao() {
		return roleFunDao;
	}
	
	public void setRoleFunDao(CommonDAO<TroleFunction> roleFunDao) {
		this.roleFunDao = roleFunDao;
	}	
	
	public List<TroleFunction> doSelectAuthorizationContext() {
		// TODO Auto-generated method stub
		String hql="from TroleFunction as rf inner join fetch rf.id.trole inner join fetch rf.id.tfunction";
		return this.roleFunDao.select(hql, null);
	}
	
//	public static void main(String[] args) {
//		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
//		
//		RoleFunctionBiz rolefunBiz=(RoleFunctionBiz) context.getBean("roleFunctionBiz");
//		List<TroleFunction> AuthorizationContext=rolefunBiz.doSelectAuthorizationContext();
//		
//	}
	
}
