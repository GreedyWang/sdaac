package sdaac.wym.app.Dao.plm;

import java.util.ArrayList;
import java.util.List;
import sdaac.wym.app.entity.plm.Bom;
import sdaac.wym.app.entity.plm.MBom;
import sdaac.wym.app.entity.plm.MPart;
import sdaac.wym.app.entity.plm.Part;
import common.dao.impl.CommonSpringDAOImpl;
import common.util.Page;

public class PartDao extends CommonSpringDAOImpl<Part> {

	private String tableName;

	public PartDao(String className, String pTableName)
			throws InstantiationException, IllegalAccessException {
		super(className);
		tableName = pTableName;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 模糊查询part
	 */
	public List<Part> selectMPartByCond(String cond, Page page) {
		// TODO Auto-generated method stub
		String hql = "from "
				+ tableName
				+ " where state='A' and wkaid=1 and no like ? or name like ? or ename like ?";
		String hqlCount = "select count(*) from MPart where state='A' and wkaid=1 and no like ? or name like ? or ename like ?";
		return selectWithPage(hqlCount, hql, new String[] { "%" + cond + "%",
				"%" + cond + "%", "%" + cond + "%" }, page);
	}

	/**
	 * 精确查询part
	 */
	public List<Part> selectMPartByCondA(String cond, Page page) {
		// TODO Auto-generated method stub
		String hql = "from " + tableName
				+ " where state='A' and wkaid=1 and no=? or name=? or ename=?";
		String hqlCount = "select count(*) from MPart where state='A' and wkaid=1 and no=? or name=? or ename=?";
		return selectWithPage(hqlCount, hql, new String[] { cond, cond, cond },
				page);

	}

	/**
	 * 查询by part.id
	 */
	public Part selectByPk(String pk) {
		String hql = "from " + tableName + " where id=? ";
		// return select(hql, pk).get(0);

		if (select(hql, pk) != null && !select(hql, pk).isEmpty()) {
			return select(hql, pk).get(0);
		} else
			return new MPart("已删除");

	}

	/**
	 * 查询s by part.ids
	 */
	public List<Part> selectByPks(List params) {
		// TODO Auto-generated method stub
		List<Bom> boms = MBom.change(params);
		List<Part> rs = new ArrayList<Part>();
		for (Bom pa : boms) {
			rs.add(this.selectByPk(pa.getFpart().getId()));
		}
		return rs;
	}
}
