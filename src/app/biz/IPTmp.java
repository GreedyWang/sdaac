package app.biz;

import java.util.List;

import common.util.Page;

import app.entity.IpTmp;

public interface IPTmp {
	public void doFlushInsert(List list);
	public List<IpTmp> doSelectAll(String myDate,String uid,Page page);
}
