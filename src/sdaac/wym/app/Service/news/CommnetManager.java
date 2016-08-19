package sdaac.wym.app.Service.news;

import common.dao.CommonDAO;
import sdaac.wym.app.entity.News.Comments;

public class CommnetManager {
	private	CommonDAO<Comments> commentsDao;
	
	public CommonDAO<Comments> getCommentsDao() {
		return commentsDao;
	}
	public void setCommentsDao(CommonDAO<Comments> commentsDao) {
		this.commentsDao = commentsDao;
	}
	/**
	 * 评论
	 * @param item
	 */
	public void comment(Comments item){
		commentsDao.insert(item);
	}
	/**
	 * 删除评论
	 * @param item
	 */
	public void delete(Comments item){
		commentsDao.deleteByPK(item.getId());
	}
}	
