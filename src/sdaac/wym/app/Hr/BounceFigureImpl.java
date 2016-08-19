package sdaac.wym.app.Hr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.entity.Tpost;
import app.entity.TpostType;
import sdaac.wym.app.entity.hr.BounceFigure;
import sdaac.wym.app.entity.hr.BouncePost;
import sdaac.wym.app.entity.hr.Inbound;
import common.dao.CommonDAO;
import common.util.ExcelReader;
/**
 * 图号处理类
 * @author sa1kv5
 *
 */
public class BounceFigureImpl {
	private CommonDAO<BounceFigure> bfDao;
	private CommonDAO<BouncePost> bpDao;
	private CommonDAO<Tpost> postdao;
	private CommonDAO<Inbound> inboundDao;

	public CommonDAO<Inbound> getInboundDao() {
		return inboundDao;
	}

	public void setInboundDao(CommonDAO<Inbound> inboundDao) {
		this.inboundDao = inboundDao;
	}

	public CommonDAO<BouncePost> getBpDao() {
		return bpDao;
	}

	public void setBpDao(CommonDAO<BouncePost> bpDao) {
		this.bpDao = bpDao;
	}

	public CommonDAO<BounceFigure> getBfDao() {
		return bfDao;
	}

	public void setBfDao(CommonDAO<BounceFigure> bfDao) {
		this.bfDao = bfDao;
	}

	public CommonDAO<Tpost> getPostdao() {
		return postdao;
	}

	public void setPostdao(CommonDAO<Tpost> postdao) {
		this.postdao = postdao;
	}

	// method
	public List<BounceFigure> selectAll(String zone) {
		return this.bfDao.selectAll();
	}

	private void addPosts(List<Tpost> posts) {
		for(Tpost item :posts){
			Tpost rs = postdao.selectByPk(item.getId());
			if(rs!=null&&!rs.getProductName().equals("")){
				
			}else{
				this.postdao.insert(item);
			}
		}
	
	}

	/**
	 * 按区域查询工序
	 * @param zone
	 * @return
	 */
	public List<BouncePost> selectBouncePostAll(String zone) {
		String hql = "from BouncePost as bp where bp.remark=?";
		return this.bpDao.select(hql, zone);

	}

	/**
	 * @param bf
	 * @see 添加工序，组合图号添加岗位
	 */
	public void addBouncePost(BouncePost bp) {
		this.bpDao.insert(bp);
		List<Tpost> posts = new ArrayList<Tpost>();
		// List<BouncePost> bps=this.selectBouncePostAll();
		List<BounceFigure> bfs = this.selectBounceFigure(bp.getRemark());
		for (BounceFigure bf : bfs) {
			Tpost post = new Tpost();
			post.setId(bf.getFigureId() + "_" + bf.getProductArea() + "_"
					+ bp.getPostId());
			post.setProductName(bf.getCarType() + "_" + bf.getProductArea()
					+ "_" + bp.getPostName());
			post.setType(Tpost.Tpost_PostSalary);
			post.setTpostType(new TpostType("ES"));
			posts.add(post);

		}
		addPosts(posts);
	}

	/**
	 * @param bf
	 * @see 添加图号，组合工序添加岗位
	 */
	public void addBounceFigure(BounceFigure bf) {
		this.bfDao.insert(bf);
		List<Tpost> posts = new ArrayList<Tpost>();
		List<BouncePost> bps = this.selectBouncePostAll(bf.getProductArea());// 根据车间查找全部工序
		for (BouncePost bp : bps) {
			Tpost post = new Tpost();
			post.setId(bf.getFigureId() + "_" + bf.getProductArea() + "_"
					+ bp.getPostId());
			post.setProductName(bf.getCarType() + "_" + bf.getProductArea()
					+ "_" + bp.getPostName());
			post.setType(Tpost.Tpost_PostSalary);
			post.setTpostType(new TpostType("ES"));
			posts.add(post);
		}
		addPosts(posts);
	}
	
	/**
	 * 查看图号
	 * 
	 * @param 车间
	 */
	public List<BounceFigure> selectBounceFigure(String zone) {
		String hql = "from BounceFigure where productArea=? order by figureId";
		return bfDao.select(hql, zone);
	}

	/**
	 * 更新
	 */
	public void update(BounceFigure item) {
		bfDao.update(item);
	}

	/**
	 * 批量更新
	 */
	public void updateBluk(List<BounceFigure> bfs) {
		for (int i = 0; i < bfs.size(); i++) {
			this.updateByRemark(bfs.get(i));
		}
	}

	/**
	 * 按条件更新-主键为物料号（remark）
	 */
	public void updateByRemark(BounceFigure item) {
		String hql = "update BounceFigure as bf set ";
		Vector<Object> params = new Vector<Object>();
		if (item.getStandWorkTime() != null) {
			hql += " standWorkTime=?,";
			params.add(item.getStandWorkTime());
		}
		if (item.getProductArea() != null && !"".equals(item.getProductArea())) {
			params.add(item.getProductArea());
			hql += " bf.productArea=?,";
		}
		if (item.getType() != null&&!item.getType().equals("")) {
			hql += " type =?,";
			params.add(item.getType());
		}
		if (item.getPicesNum() != null) {
			hql += " picesNum=?,";
			params.add(item.getPicesNum());
		}
		hql = hql.substring(0, hql.length() - 1);// 去‘,’
		hql += " where remark=?";
		params.add(item.getRemark());
		this.bfDao.bulkUpdate(hql, params.toArray());

	}

	/**
	 * 按条件更新-主键为ID
	 */
	public void updateById(BounceFigure item) {
		String hql = "update BounceFigure as bf set ";
		Vector<Object> params = new Vector<Object>();
		if (item.getStandWorkTime() != null) {
			hql += " standWorkTime=?,";
			params.add(item.getStandWorkTime());
		}
		if (item.getProductArea() != null && !"".equals(item.getProductArea())) {
			params.add(item.getProductArea());
			hql += " bf.productArea=?,";
		}
		if (item.getType() != null&&!item.getType().equals("")) {
			hql += " type =?,";
			params.add(item.getType());
		}
		if (item.getPicesNum() != null) {
			hql += " picesNum=?,";
			params.add(item.getPicesNum());
		}
		if (item.getRemark() != null) {
			hql += " remark=?,";
			params.add(item.getRemark());
		}
		hql = hql.substring(0, hql.length() - 1);// 去‘,’
		hql += " where id=?";
		params.add(item.getId());
		this.bfDao.bulkUpdate(hql, params.toArray());

	}
	
	/**
	 * 删除
	 */
	public void del(int id,String newProductArea) {
		//bfDao.deleteByPK(figureId);
		BounceFigure item = new BounceFigure();
		item.setId(id);
		//item.setRemark(figureId);
		item.setProductArea(newProductArea);
		updateById(item);
	}

	/**
	 * 按条件查询
	 */
	public List<BounceFigure> selectByCond(BounceFigure item) {
		String hql = "from BounceFigure as bf  where 1=1 ";
		Vector<Object> param = new Vector<Object>();
		if (item.getFigureId() != null && !"".equals(item.getFigureId())) {
			param.add(item.getFigureId());
			hql += "and bf.figureId=? ";
		}
		if (item.getProductArea() != null && !"".equals(item.getProductArea())) {
			param.add(item.getProductArea());
			hql += "and bf.productArea=? ";
		}
		if (item.getCarType() != null && !"".equals(item.getCarType())) {
			param.add(item.getCarType());
			hql += "and bf.carType=? ";
		}
		if (item.getRemark() != null && !"".equals(item.getRemark())) {
			param.add(item.getRemark());
			hql += "and bf.remark=? ";
		}
		return this.bfDao.select(hql, param.toArray());

	}

	/**
	 * 更新，添加入库数
	 */
	public void updateInbound(List<Inbound> inbounds) {
		String hql = "from Inbound where figureId=? and InboundTime=?";
		String hql2 = "update Inbound set inbound=? where figureId=? and inboundTime=?";
		// inboundDao.blukFlushInsert(inbounds);
		for (Inbound item : inbounds) {
			Object[] params = new Object[] { item.getInbound(),
					item.getFigureId(), item.getInboundTime() };
			// 如果有就更新，没有就插入
			if (inboundDao.select(hql, new Object[] { params[1], params[2] })
					.isEmpty()) {
				inboundDao.insert(item);
			} else {
				System.out.println(item.getFigureId()+":"+item.getInbound()+":"+item.getInboundTime());
				inboundDao.bulkUpdate(hql2, params);
			}
		}
	}

}
