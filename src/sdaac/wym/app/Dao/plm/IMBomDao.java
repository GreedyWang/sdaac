package sdaac.wym.app.Dao.plm;

import java.util.List;

import sdaac.wym.app.entity.plm.Bom;
import sdaac.wym.app.entity.plm.MBom;

public interface IMBomDao {
	public List<Bom>  selectFather(String pk);
	public List<Bom> selectChild(String pk);
}
