package sdaac.wym.app.Service.Room;

import java.util.List;

import common.dao.CommonDAO;

public class AccessoryBiz {
	private CommonDAO<Accessory> accDao;

	public CommonDAO getAccDao() {
		return accDao;
	}

	public void setAccDao(CommonDAO accDao) {
		this.accDao = accDao;
	}

	public void  doUpdateState(Form item) {
		String hql= "";
		accDao.bulkUpdate(hql, "");
	}
	
	/**
	 * 查询状态为空闲的，指定类型的配件
	 * @param type
	 * @return
	 */
	public List<Accessory>  doSelectAvailableByType(Integer type) {
		String hql= "from Accessory where state=0 and type=?";
		return accDao.select(hql, type);
	}
	
	/**
	 * 显示配件
	 * @return
	 */
	public List<Accessory> doSelectAll(){
		return accDao.selectAll();
	}
	
	/**
	 * 显示配件
	 * @return
	 */
	public Accessory doSelect(int id){
		return accDao.selectByPk(id);
	}
	
	/**
	 * 添加配件
	 * @param item
	 */
	public void doAdd(Accessory item) {
		accDao.insert(item);
	}
	
	public void doUpdate(Accessory item){
		String hql = "update Accessory set name =? , set remark =?,set state=?,type=? where id = ?";
		Object[] param = new Object[5];
		param[0]=item.getName();
		param[1]=item.getRemark();
		param[2]=item.getState();
		param[3]=item.getType();
		param[4]=item.getId();
		accDao.bulkUpdate(hql, param);
	}
	
}
