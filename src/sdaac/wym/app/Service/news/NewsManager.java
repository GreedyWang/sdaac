package sdaac.wym.app.Service.news;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import common.dao.CommonDAO;

import app.entity.Tempolyee;
import sdaac.wym.app.Dao.news.CommentsDao;
import sdaac.wym.app.Dao.news.NewsDao;
import sdaac.wym.app.entity.News.News;

/**
 * 新闻发布，评论
 * 
 * @author SA1KV5
 * 
 */
public class NewsManager {
	private CommonDAO<News> newsDao;

	public CommonDAO<News> getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(CommonDAO<News> newsDao) {
		this.newsDao = newsDao;
	}

	/**
	 * 发布新闻
	 * 
	 * @param item
	 */
	public void release(News item) {
		
		newsDao.insert(item);
	}

	/**
	 * 暂时保存
	 * 
	 * @param item
	 */
	public void save(News item) {
		item.setState(0);
		this.release(item);
	}

	/**
	 * 删除新闻
	 * 
	 * @param item
	 */
	public void delete(News item) {
		newsDao.deleteByPK(item.getId());
	}

	/**
	 * 修改新闻
	 * 
	 * @param item
	 * @return boolean
	 */
	public void modify(News item) {
			newsDao.update(item);
	}

	/**
	 * 搜索新闻
	 * 
	 * @param item
	 * @return
	 */
	public List<News> search(News item) {
		String hql = "from News as n where 1=1 ";
		Vector<Object> params = new Vector<Object>();
		hql=setParams(item,params,hql);
		return newsDao.select(hql, params.toArray());
	}
	
	private String setParams(News item,Vector<Object> params,String hql){
		if(item!=null){
			if (item.getTitle() != null&&!item.getTitle().equals("")) {
				hql += "and title=? ";
				params.add(item.getTitle());
			}
			if (item.getReleaseEmployeeId().getUid() != null) {
				hql += "and n.releaseEmployeeId.uid=? ";
				params.add(item.getReleaseEmployeeId().getUid());
			}
			if (item.getKeyWord() != null&&!item.getKeyWord().equals("")) {
				hql += "and keyWord=? ";
				params.add(item.getKeyWord());
			}
			if (item.getType() != null) {
				hql += "and type=? ";
				params.add(item.getKeyWord());
			}
			if (item.getState() != null) {
				hql += "and type=? ";
				params.add(item.getKeyWord());
			}
			if(item.getReleaseEmployeeId().getTdepartment().getId()!=null){
				hql += "and n.releaseEmployeeId.tdepartment.id=? ";
				params.add(item.getReleaseEmployeeId().getTdepartment().getId());
			}
			if(item.getStartTime()!=null&&!item.getStartTime().equals("")){
				hql += "and releaseTime > '"+item.getStartTime()+"'";		
			}
			if(item.getEndTime()!=null&&!item.getEndTime().equals("")){
				hql += "and releaseTime < '"+item.getEndTime()+"'";		
			}
		}
		return hql;
	}
	/**
	 * 搜索重要新闻
	 * 
	 * @param item
	 * @return
	 */
	public List<News> searchImportantNews(News item) {
		return search(item);
	}

	/**
	 * 搜索热门新闻
	 * @param item
	 * @return
	 */
	public List<News> searchHotNews(News item) {
		String hql = " from News as n inner join fetch n.releaseEmployeeId as emp inner join fetch emp.tdepartment where 1=1 ";
		Vector<Object> params = new Vector<Object>();
		hql=setParams(item,params,hql);
		hql+=" order by releaseTime desc";
		return newsDao.selectTop(hql,10, params.toArray());
	}
	public List<News> searchHotNewsExt() {
		News item=new News();
		return searchHotNews(item);
	}
	/**
	 * 显示新闻
	 * @param item
	 * @return
	 */
	public News show(int pk) {
		String hql = "from News as n inner join fetch n.releaseEmployeeId "
				+ "where id=?";
		List<News> rs = newsDao.select(hql, pk);
		if (!rs.isEmpty()) {
			return rs.get(0);
		} else {
			return null;
		}
	}
}
