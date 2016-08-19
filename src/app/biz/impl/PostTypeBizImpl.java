package app.biz.impl;

import java.util.List;

import common.dao.CommonDAO;

import app.biz.PostTypeBiz;
import app.entity.Tpost;
import app.entity.TpostType;

public class PostTypeBizImpl implements PostTypeBiz {
private CommonDAO<TpostType> postTypedao=null;
	public CommonDAO<TpostType> getPostTypedao() {
	return postTypedao;
}
public void setPostTypedao(CommonDAO<TpostType> postTypedao) {
	this.postTypedao = postTypedao;
}
	public List<TpostType> doSelect() {
		// TODO �
	//	String hql="from TpostType where type <>?";Զ���ɷ������
		String hql="from TpostType where type <>?";
		return postTypedao.select(hql, "ES");
		
	}
	public void doUpdate(TpostType item)
	{
		postTypedao.update(item);
		
	}

}
