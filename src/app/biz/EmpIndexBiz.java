package app.biz;

import java.util.List;

import app.entity.TcompanyIndex;
import app.entity.TempIndex;

public interface EmpIndexBiz {
public List<TempIndex> doSelectByIndexID(int id);
public void doInsert(TempIndex item);
public List<TempIndex> doSelectByUid(String id,String version);
public boolean checkDate(String uid, int indexID);
public void doUpdate(TempIndex item);
public void doDelete(TempIndex item);
public boolean doInsertIndex(TempIndex item,Integer departID);
public void doDeleteByPk(Integer indexID);
public TempIndex doSelectByPk(int indexId);
public void doCopy(List<TcompanyIndex> list);
public void doCopyByCondition(List<TcompanyIndex> list,String[] uids);
public void doDeletebyUidnVersion(String uid,String version);
}
