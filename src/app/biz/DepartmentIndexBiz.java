package app.biz;
import java.util.List;
import java.util.Map;

import app.entity.TcompanyIndex;
public interface DepartmentIndexBiz {

	public List<TcompanyIndex> doSelectByindexid(Integer id);
	public void doInsert(TcompanyIndex item );
	public void update(TcompanyIndex item);
	public boolean addnUpdate(List<TcompanyIndex> dpIndexs,Integer departID,List<TcompanyIndex> indexUpdates,String version);
	public boolean deleteByPK(Integer pk);
	public List<TcompanyIndex> doSelectCompanyIndex(Integer departmemtID,String version);
	public Map<Integer,List<TcompanyIndex>> selectEveryDepartCompanyIndexs(Integer departmemtID,String version);
	public List<TcompanyIndex> doSelectDepartmentIndex(Integer departmemtID,String version);
}
