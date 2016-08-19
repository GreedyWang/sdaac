package sdaac.wym.app.Dao.plm;

import java.util.ArrayList;
import java.util.List;
import common.dao.impl.CommonSpringDAOImpl;
import sdaac.wym.app.entity.plm.Bom;
import sdaac.wym.app.entity.plm.Ebom;

public abstract class BomDao extends CommonSpringDAOImpl<Bom> {

	private String tableName;
	
	protected abstract String getTableName();
	
	public BomDao(String className, String pTableName)
			throws InstantiationException, IllegalAccessException {
		super(className);
		tableName = pTableName;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 得到最高层的节点id
	 */
	public List<Bom> selectFather(String pk) {
		// TODO Auto-generated method stub
		String hql2 = "from " + tableName
				+" as t inner join fetch t.fpart where t.cpart.id=? and t.state='A' and t.wkaid='1' order by t.bompst";
		return select(hql2, pk);					
	}

	/**
	 * 得到子节点
	 * 
	 * @param pk
	 * @return
	 */
	public List<Bom> selectChild(String pk) {
//		from TempIndex as e inner join fetch e.tempolyee inner join fetch e.index where e.tempolyee.uid=? and e.index.version=?
		String hql = "from "+tableName+" as t left join fetch t.cpart as c  where t.fpart.id=? and t.state='A' and t.wkaid='1' order by t.bompst";
		return this.select(hql, pk);
	}

	/**
	 * 是否有节点
	 * @param mpart.id
	 * @return
	 */
	public List<Bom> isLeaf(String pk) {
		List<Bom> mboms = selectChild(pk);
		if (mboms != null && !mboms.isEmpty()) {
			return mboms;
		} else {
			return null;
		}
	}
}
