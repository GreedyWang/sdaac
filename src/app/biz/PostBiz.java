package app.biz;

import app.entity.Tpost;

public interface PostBiz {

public Tpost doSelectById(String id);
public void doUpdate(Tpost item);
public void doInsert(Tpost item);
public void getNameIntoOther();
public void doCopy(String newNo,String OldNo,String productArea,String productArea2);
public void test();
}
