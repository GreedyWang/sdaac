package sdaac.wym.app.Dao.vave;

import java.util.List;

import sdaac.wym.app.entity.vave.Suggestion;
import common.dao.impl.CommonSpringDAOImpl;

public class SuggestDao extends CommonSpringDAOImpl<Suggestion> implements IsuggestDao {

	public SuggestDao(String className) throws InstantiationException,
			IllegalAccessException {
		super(className);
		// TODO Auto-generated constructor stub
	}

	public List<Suggestion> selectByType(String proposalId, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer selectImplementCounts(String proposalId) {
		// TODO Auto-generated method stub
		return null;
	}

}
