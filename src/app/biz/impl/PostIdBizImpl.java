package app.biz.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import common.dao.CommonDAO;
import app.biz.PostIdBiz;
import app.entity.TpostId;

public class PostIdBizImpl implements PostIdBiz {
	
	private CommonDAO<TpostId> postIDdao=null;
	public List<TpostId> doSelectAll() {
		// TODO 自动生成方法存根
		List<TpostId> list=postIDdao.selectAll();
		return list;
	}
	
	public void doInsert(TpostId item)
	{
		postIDdao.insert(item);
	}
	public CommonDAO<TpostId> getPostIDdao() {
		return postIDdao;
	}
	public void setPostIDdao(CommonDAO<TpostId> postIDdao) {
		this.postIDdao = postIDdao;
	}
	public static void main(String[] args) {
		ApplicationContext context=new  ClassPathXmlApplicationContext("applicationContext.xml");
		((PostIdBiz) context.getBean("postIdBiz")).doSelectAll();
		
	}

	
	/*
	 * DWR --> postid 
	 */
	public List<TpostId> selectFirstName() {
		// TODO 自动生成方法存根
		String hql="select DISTINCT tid.firstName from TpostId as tid";
		return postIDdao.select(hql, null);
		
	}

	public List<TpostId> selectLastName(String firstName, String midName) {
		// TODO 自动生成方法存根
		String hql="select DISTINCT tid.lastName from TpostId as tid where tid.firstName=? and tid.midName=?";
		String[] params=new String[2];
		params[0]=firstName;
		params[1]=midName;
		return postIDdao.select(hql, params);
		
		
	}

	public List<TpostId> selectMidName(String firstName) {
		// TODO 自动生成方法存根
		String hql="select DISTINCT tid.midName from TpostId as tid where tid.firstName=?";
		return postIDdao.select(hql, firstName);
	}

	public List<TpostId> selectFitstNameByTwo(String name) {
		// TODO 自动生成方法存根
		String param="'"+name+"%'";
		String hql="select DISTINCT tid.firstName from TpostId as tid where tid.firstName like "+param;
		
		
		
		return postIDdao.select(hql, null);
		
	}
	
	
	
	
	
	
	
	
	
}
