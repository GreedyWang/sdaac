package app.biz.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import common.dao.CommonDAO;
import common.dao.impl.CommonSpringDAOImpl;
import app.biz.IndexTargetBiz;
import app.entity.TcompanyIndex;
import app.entity.TempIndex;
import app.entity.TindexTarget;
import app.hr.DAO.IDepartIndexDao;
import app.hr.DAO.IndexDao;

public class IndexTargetBizImpl implements IndexTargetBiz {
	private IndexDao indexdao = null;
	// private CommonDAO<TcompanyIndex> dpIndexDao=null;

	private IDepartIndexDao dpIndexDao;
	private CommonDAO<TempIndex> empIndexDao = null;

	// 构造
	public IndexTargetBizImpl() {

	}

	public IndexTargetBizImpl(IndexDao indexdao) {
		this.indexdao = indexdao;
	}

	/**
	 * 创建新一轮指标 把以前最新的TindexTarget插入份，并把version改为新的版本号
	 * 同时把empIndex和departIndex按照上述思想插入一份，indexID(FK)指向 新version的指标
	 * 
	 * @return 新产生的版本编号
	 */
	public String doCreateNewIndexLib() {
		// 得到最新版本的指标
		String version = this.indexdao.selectMaxVersionIndex();
		String indexHql = "from TindexTarget as index ";// left join fetch
														// index.tcompanyIndexes
														// left join fetch
														// index.empIndexs";
		if (version != null) {
			indexHql += " where index.version='" + version + "'";

		}
		List<TindexTarget> indexs = this.indexdao.select(indexHql, null);
		// 为他附值新的版本信息，然后再重新插入
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String newVersion = sdf.format(new Date());

		List<TindexTarget> newIndexs = new LinkedList<TindexTarget>();
		for (TindexTarget elem : indexs) {
			TindexTarget item = new TindexTarget();
			item.setVersion(newVersion);
			item.setA(elem.getA());
			item.setB(elem.getB());
			item.setC(elem.getC());
			item.setType(elem.getType());
			item.setName(elem.getName());
			item.setFormula(elem.getFormula());
			item.setIsChoice(elem.getIsChoice());
			item.setDepartID(elem.getDepartID());
			newIndexs.add(item);
			Integer indexID = (Integer) this.indexdao.insert(item);

			// 翻新部门指标
			String dpHql = "from TcompanyIndex as dp where dp.tindexTarget.id='"
					+ elem.getId() + "'";
			List<TcompanyIndex> dpIndexs = dpIndexDao.select(dpHql, null);
			dpIndexs = TcompanyIndex.copyChangeIndex(dpIndexs, indexID);
			dpIndexDao.blukFlushInsert(dpIndexs);
			// 翻新人员指标
			String empHql = "from TempIndex as empIndex where empIndex.index.id='"
					+ elem.getId() + "'";
			List<TempIndex> empIndexs = this.empIndexDao.select(empHql, null);
			empIndexDao.blukFlushInsert(TempIndex.copyChangeIndex(empIndexs,
					indexID));
		}
		return newVersion;
	}

	/**
	 * 删除指标
	 */
	public void doDelete(Integer indexKey) {
		indexdao.deleteByPK(indexKey);
	}

	/**
	 * 查询指标类型为C --暂时无用
	 */
	public List selectByType() {
		// TODO �Զ���ɷ������
		String hql = "select index.name from TindexTarget as index where index.type <> ? ";
		String param = "C";
		return indexdao.select(hql, param);
	}

	/**
	 * 按条件查询
	 * 
	 * @param 指标类型，部门ID
	 */
	public List<TindexTarget> selectByCondition(String indexType,
			Integer departID) {
		// TODO �Զ���ɷ������
		String hql = "from TindexTarget as index where 1=1";
		Vector<Object> vector = new Vector<Object>();
		if (indexType != null && !indexType.equals("")) {
			hql += " and index.type = ?";
			vector.add(indexType);
		}
		if (departID != null && !departID.equals("")) {
			hql += " and index.departID=?";
			vector.add(departID);
		}
		return indexdao.select(hql, vector.toArray());
	}

	/**
	 * 查询公司所有指标
	 */
	public List<TindexTarget> selectAll() {
		// TODO �Զ���ɷ������
		String hql = " from TindexTarget as index where index.departID= ? ";
		Integer param = 1;
		return indexdao.select(hql, param);
	}

	/**
	 * 插入指标
	 */
	public void insertIndex(TindexTarget item) {
		indexdao.insert(item);
	}

	/**
	 * 更新指标
	 */
	public boolean updateIndex(TindexTarget item, String target, Integer id,
			Float output) {
		boolean flag = true;
		if (target.equals("1"))// 公司指标
		{
			this.dpIndexDao.updateDpIndexsByindexId(item);

		} else if (target.equals("2"))// 部门
		{
			this.dpIndexDao.changeUpdate(id, output, item);
			// 更新员工指标
			String hql = "update TempIndex ei set score=? where ei.index.id=?";
			Float newScore = TindexTarget.cal(item, output);
			this.empIndexDao.bulkUpdate(hql, new Object[] { newScore,
					item.getId() });
		}
		if (item != null) {
			
				try {
					String hql = "update TindexTarget as i set i.name=?,i.type=?,i.isChoice=?,i.a=?,i.b=?,i.c=?,i.formula=? where i.id=?";
					// this.indexdao.update(item);
					this.indexdao.bulkUpdate(hql, new Object[] {
							item.getName(), item.getType(), item.getIsChoice(),
							item.getA(), item.getB(), item.getC(),
							item.getFormula(), item.getId() });

				} catch (RuntimeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					flag = false;
				}
		} else {
			flag = false;
		}
		return flag;
	}
	 
	/**
	 * 全部修改
	 */
	public boolean updateIndexAll(TindexTarget item, String target, Integer id,
			Float output,int departmentId) {
		TindexTarget index= indexdao.selectByPk(item.getId());
//		select * from tindex_target as t 
//		inner join tEmpIndex as e on t.id=e.indexID 
//		where name='PPAP 按时完成率' and type='量化指标'
//		and a='85%' and b='90%' and c='100%' and version='2009-07'
		String selhql="from TempIndex e join fetch e.index join fetch e.tempolyee where e.index.name=? and " +
				" e.index.type=? and e.index.a=? and e.index.b=? and e.index.c=? and e.index.version=? and e.tempolyee.tdepartment.id=?";
		Object[] selParams={index.getName(),index.getType(),index.getA(),index.getB(),index.getC(),index.getVersion(),departmentId};
		List<TempIndex> empIndexs= empIndexDao.select(selhql, selParams);
		for(TempIndex empIndex:empIndexs){
			empIndex.getIndex().setAll(item);
		}
//		String hql = "update TindexTarget as i set i.name=?,i.type=?,i.isChoice=?,i.a=?,i.b=?,i.c=?,i.formula=? " +
//				"where i.id=?";
//		// this.indexdao.update(item);
//		this.indexdao.bulkUpdate(hql, new Object[] {
//				item.getName(), item.getType(), item.getIsChoice(),
//				item.getA(), item.getB(), item.getC(),
//				item.getFormula(), item.getId() });
		return updateIndex(item, target, id, output);
	}
	/**
	 * 查询指标by指标名称
	 * 
	 * @param indexName
	 */
	public TindexTarget selectByIndexName(String indexName) {
		// TODO 自动生成方法存根
		String hql = "from TindexTarget as index where index.name=?";
		List<TindexTarget> list = indexdao.select(hql, indexName);
		if (!list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 批插入指标
	 */
	public boolean doAddIndex(List<TindexTarget> indexs) {
		boolean flag = true;
		try {
			this.indexdao.blukFlushInsert(indexs);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	/**
	 * 通过主键查询
	 * 
	 * @param pk
	 */
	public TindexTarget doSelectByPK(Integer pk) {
		if (pk == null) {
			return null;
		} else {
			return this.indexdao.selectByPk(pk);
		}
	}

	/**
	 * 查看所有指标版本
	 * 
	 * @return
	 */
	public String[] doSelectVersions() {
		String hql = "select index.version from TindexTarget as index group by index.version";
		List versions = this.indexdao.select(hql, null);
		String[] resluts = (String[]) versions.toArray(new String[0]);
		return resluts;
	}

	// get & set

	public IndexDao getIndexdao() {
		return indexdao;
	}

	public void setIndexdao(IndexDao indexdao) {
		this.indexdao = indexdao;
	}

	public CommonDAO<TempIndex> getEmpIndexDao() {
		return empIndexDao;
	}

	public void setEmpIndexDao(CommonDAO<TempIndex> empIndexDao) {
		this.empIndexDao = empIndexDao;
	}

	public IDepartIndexDao getDpIndexDao() {
		return dpIndexDao;
	}

	public void setDpIndexDao(IDepartIndexDao dpIndexDao) {
		this.dpIndexDao = dpIndexDao;
	}

	/**
	 * @see 更新指标的a,b,c时修改部门，人员的得分。
	 */
	public void updateScoreWhenChangeABC(TindexTarget item) {
		// this.updateIndex(item, target, id, output)
	}

	/**
	 * 转化半年/全年指标
	 */
	public void doChangeHalf(String version) {
		String hql2 = "from TindexTarget where departID=1 and version=?";
		List<TindexTarget> rs = this.indexdao.select(hql2, version);

		// String hql="update TindexTarget set a=a*0.5,b=b*0.5,c=c*0.5 where
		// departID=1 and version=?";
		// this.indexdao.bulkUpdate(hql, version);
		for (TindexTarget index : rs) {
			String newAA;
			String newBB;
			String newCC;
			String newFormula;
			DecimalFormat df = new DecimalFormat("#.00");
			if (index.getFormula().split("-半年").length < 2) {

				String hql3 = "update TindexTarget set a=?,b=?,c=?,formula=? where departID=1 and version=? and id=? ";
				Double newA = Double.parseDouble(index.getA()) / 2;
				Double newB = Double.parseDouble(index.getB()) / 2;
				Double newC = Double.parseDouble(index.getC()) / 2;

				newAA = df.format(newA);
				newBB = df.format(newB);
				newCC = df.format(newC);
				newFormula = index.getFormula() + "-半年指标";
				this.indexdao.bulkUpdate(hql3, new Object[] { newAA, newBB,
						newCC, newFormula, version, index.getId() });
			} else {
				String hql4 = "update TindexTarget set a=?,b=?,c=?,formula=? where departID=1 and version=? and id=?";
				double newA = Double.parseDouble(index.getA()) * 2;
				double newB = Double.parseDouble(index.getB()) * 2;
				double newC = Double.parseDouble(index.getC()) * 2;

				newAA = df.format(newA);
				newBB = df.format(newB);
				newCC = df.format(newC);
				newFormula = index.getFormula().split("-半年")[0];
				this.indexdao.bulkUpdate(hql4, new Object[] { newAA, newBB,
						newCC, newFormula, version, index.getId() });
			}
			TindexTarget item = new TindexTarget();
			item.setA(newAA);
			item.setB(newBB);
			item.setC(newCC);
			item.setType(index.getType());
			item.setId(index.getId());
			this.dpIndexDao.updateDpIndexsByindexId(item);
		}

	}

}
