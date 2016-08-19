package app.biz;

import java.util.List;

import app.entity.TindexTarget;

public interface IndexTargetBiz {
public List<String> selectByType();

public List<TindexTarget> selectAll();
public void insertIndex(TindexTarget item);
public boolean updateIndex(TindexTarget item,String target,Integer id,Float output);
public List<TindexTarget> selectByCondition(String indexType,Integer departID);
public TindexTarget selectByIndexName(String indexName);
public void doDelete(Integer indexKey);
public TindexTarget doSelectByPK(Integer pk);
public String[] doSelectVersions();
public String doCreateNewIndexLib();
public void doChangeHalf(String version );
public boolean updateIndexAll(TindexTarget item, String target, Integer id,
		Float output,int departmentId);
}
