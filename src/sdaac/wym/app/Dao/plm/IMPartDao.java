package sdaac.wym.app.Dao.plm;

import java.util.List;

import sdaac.wym.app.entity.plm.MBom;
import sdaac.wym.app.entity.plm.MPart;
import common.dao.CommonDAO;
import common.util.Page;
public interface IMPartDao extends CommonDAO<MPart>{
	public List<MPart> selectMPartByCondA(String cond,Page page);
	public List<MPart> selectMPartByCond(String cond,Page page);
	public MPart selectByPk(String pk) ;
	public List<MPart> selectByPks(List<MBom> mboms);
}
