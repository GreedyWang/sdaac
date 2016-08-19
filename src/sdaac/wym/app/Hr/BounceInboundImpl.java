package sdaac.wym.app.Hr;

import java.util.List;

import sdaac.wym.app.entity.hr.Inbound;

import common.dao.CommonDAO;

public class BounceInboundImpl {
	private CommonDAO<Inbound> inboundDao;

	public CommonDAO<Inbound> getInboundDao() {
		return inboundDao;
	}

	public void setInboundDao(CommonDAO<Inbound> inboundDao) {
		this.inboundDao = inboundDao;
	}
	/**
	 * 查看指定图号的入库数
	 * @param figureId
	 * @return
	 */
	public List<Inbound> selectByFigure(String figureId)
	{
		String hql=" from Inbound where figureId=? order by inboundTime desc";
		return inboundDao.selectTop(hql, 6, new Object[]{figureId});
	}
	/**
	 * 修改入库数
	 * @param inbound
	 */
	public void update(Inbound inbound)
	{
		if(inbound.getId()!=null&&!inbound.getId().equals("")
				)
		{
			String hql="update Inbound set inbound=? where id=?";
			inboundDao.bulkUpdate(hql, new Object[]{inbound.getInbound(),inbound.getId()});
		}
	}
}
