package app.hr.DAO;

import java.util.List;

import common.dao.CommonDAO;

import app.entity.TcompanyIndex;
import app.entity.TindexTarget;

public interface IDepartIndexDao extends CommonDAO<TcompanyIndex>{
	//更新指标同时改变score
	public void changeUpdate(Integer dpIndexId,Float output, TindexTarget item); 
	public void updateDpIndexsByindexId(TindexTarget item);
}
