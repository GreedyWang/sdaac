package sdaac.wym.app.Dao.vave;

import java.util.List;

import common.dao.CommonDAO;

import sdaac.wym.app.entity.vave.Suggestion;

public interface IsuggestDao extends CommonDAO<Suggestion>{
	//查询部门审批时外部们的个数
	public Integer selectImplementCounts(String proposalId);
	public List<Suggestion> selectByType(String proposalId,String type);
}
