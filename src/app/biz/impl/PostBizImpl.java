package app.biz.impl;
import java.util.ArrayList;
import java.util.List;
import common.dao.CommonDAO;
import app.biz.PostBiz;
import app.entity.Tpost;
import app.entity.TpostId;
public class PostBizImpl implements PostBiz{

	private CommonDAO<Tpost> postdao=null;
	private CommonDAO<TpostId> postIdDao=null;
	
	public Tpost doSelectById(String id) {
		// TODO �Զ���ɷ������
		String hql="from Tpost as p where p.id=?";
		List<Tpost> list=postdao.select(hql, id);
		if(list.isEmpty())
		{
			return null;
		}
		else
		{
			return list.get(0);
		}
		
	}
	/**
	 * 把post表的id分3块插入postid表
	 */
	public void getNameIntoOther() {
		List<Tpost>list=postdao.selectAll();
	
		for (Tpost tpost : list) {
			String[]ids=tpost.getId().split("_");
			
			if(ids.length==3)
			{
				TpostId item=new TpostId();
				item.setFirstName(ids[0]);
				item.setMidName(ids[1]);
				item.setLastName(ids[2]);
				postIdDao.insert(item);
			}	
		}
	}
	
	
	public void doInsert(Tpost item)
	{
		postdao.insert(item);
	}
	/**
	 * 按主键更新岗位名称和类型
	 */
	public void doUpdate(Tpost item)
	{
		 String hql="update Tpost set productName=?,tpostType.type=? where id=?";
		 postdao.bulkUpdate(hql, new Object[]{item.getProductName(),item.getTpostType().getType(),item.getId()});
	}
	public void doDelByid(String id)
	{
		 postdao.deleteByPK(id);
	}

	public CommonDAO<Tpost> getPostdao() {
		return postdao;
	}

	public void setPostdao(CommonDAO<Tpost> postdao) {
		this.postdao = postdao;
	}
	public CommonDAO<TpostId> getPostIdDao() {
		return postIdDao;
	}
	public void setPostIdDao(CommonDAO<TpostId> postIdDao) {
		this.postIdDao = postIdDao;
	}
	
	/**
	 * 104A添加图号，用于工序位和另外个相似
	 */
	public void doCopy(String newNo,String OldNo,String productArea,String productArea2){
		//add tpost
		String hql="from Tpost where id like ?";
		String param = "%"+OldNo+"_"+productArea2+"%";
		List<Tpost> rs = postdao.select(hql, param);
		List<Tpost> targers =new ArrayList<Tpost>();
		for(Tpost item:rs){
			Tpost aa = new Tpost();
			String ids[] = item.getId().split("_");		
			aa.setId(newNo+"_"+productArea+"_"+ids[2]);
			aa.setProductName(item.getProductName());
			aa.setTpostType(item.getTpostType());
			aa.setType(item.getType());
			targers.add(aa);
		}
		postdao.blukFlushInsert(targers);
		//add tpostId
		String hql2="from TpostId where firstName =? ";
		List<TpostId> rs2 = postIdDao.select(hql2, OldNo);
		List<TpostId> targers2 =new ArrayList<TpostId>();
		for(TpostId item:rs2){
			TpostId aa = new TpostId();
			aa.setFirstName(newNo);
			aa.setMidName(productArea);
			aa.setLastName(item.getLastName());
			targers2.add(aa);
		}
		postIdDao.blukFlushInsert(targers2);
	}
	
	
	public void test(){
		System.out.println(postdao.selectAll());;
	}
	
	
}
