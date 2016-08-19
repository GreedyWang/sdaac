package app.biz;

import java.util.List;

import app.entity.TpostId;

public interface PostIdBiz {
	public List<TpostId> doSelectAll();
	public void doInsert(TpostId item);
	
	public List<TpostId> selectFirstName();
	public List<TpostId> selectMidName(String firstName);
	public List<TpostId> selectLastName(String firstName,String midName);
	public List<TpostId> selectFitstNameByTwo(String name);
	
}
