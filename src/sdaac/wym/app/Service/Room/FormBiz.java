package sdaac.wym.app.Service.Room;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import common.dao.CommonDAO;

public class FormBiz {
	private CommonDAO formDao;

	public CommonDAO getFormDao() {
		return formDao;
	}

	public void setFormDao(CommonDAO formDao) {
		this.formDao = formDao;
	}

	/**
	 * 添加
	 * 
	 * @param item
	 */
	public void doInsert(Form item) {
		formDao.insert(item);
	}

	/**
	 * 跟新登记表状态
	 * 
	 * @param state
	 * @param id
	 */
	public void doUpdateState(int state, int id) {
		Form item = new Form();
		item.setId(id);
		item.setState(state);
		doUpdate(item);
	}

	/**
	 * 更新
	 * 
	 * @param item
	 */
	public void doUpdate(Form item) {
		StringBuffer hql = new StringBuffer("update Form ");
		List params = new ArrayList();
		if (item.getBegintime() != null && item.getEndtime() != null) {
			hql.append("set begintime = ?, ");
			params.add(item.getBegintime());
		}
		if (item.getEndtime() != null) {
			hql.append("set endtime = ?, ");
			params.add(item.getEndtime());
		}
		if (item.getContext() != null) {
			hql.append("set contact = ?, ");
			params.add(item.getContext());
		}
		if (item.getContact() != null) {
			hql.append("set contact = ?, ");
			params.add(item.getContact());
		}
		if (item.getState() != null) {
			hql.append("set state = ?, ");
			params.add(item.getState());
		}
		if (item.getId() != null) {
			hql.delete(hql.length()-2, hql.length());
			hql.append("where id = ?");
			params.add(item.getId());
		}
		formDao.bulkUpdate(hql.toString(), params.toArray());
	}
	
	/**
	 * 查询周期会议里是否有冲突
	 * @param item
	 * @return
	 */
	public List<Form> doSelectCycle2(Form item) {
		List<Form> temps = new ArrayList<Form>();
		Calendar ca = Calendar.getInstance();
		Date s = item.getBegintime();
		//Date e = item.getEndtime();
		ca.setTime(s);
		int week = ca.get(ca.DAY_OF_WEEK);
		item.setCycleTypeInfo(week);
		item.setCycleType(1);
		temps.addAll(doSelectInner(item,1));
		int month = ca.get(ca.DAY_OF_MONTH);
		item.setCycleTypeInfo(month);
		item.setCycleType(2);
		temps.addAll(doSelectInner(item,1));
		return temps;

	}
	
	
	public List<Form> doSelectCycle(List<Form> itms) {
		List<Form> temps = new ArrayList<Form>();
		for (Form item : itms) {
			if (item.getCycleType() != 0) {
				switch (item.getCycleType()) {
				case 1: {
					temps.addAll(item.getOtherFormAfterWeekly(item));
					break;
				}
				case 2: {
					temps.addAll(item.getOtherFormAfterMonthly(item));
					break;
				}
				}

			}

		}
		for(Form aa :temps){
			System.out.println(">>>>>>>>>>>"+aa.getBegintime());
		}
		
		return temps;

	}
	
	/**
	 * 查询
	 * @param item
	 * @return
	 */
	public List<Form> doSelect(Form item) {
		List<Form> rs = doSelectInner(item,0);
		rs.addAll(doSelectCycle(doSelectInner(item,1)));
		return rs;
	}
	
	/**
	 * 查询
	 * @param item
	 * @return
	 */
	public List<Form> doSelect2(Form item) {
		List<Form> rs = doSelectInner(item,0);
		rs.addAll(doSelectCycle2(item));
		return rs;
	}
	
	/**
	 * 查询
	 * 
	 * @param item
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Form> doSelectInner(Form item,int flag) {
		StringBuffer hql = new StringBuffer(
				"from Form f join fetch f.roomId join fetch f.emp join fetch f.emp.tdepartment where 1=1 and state > -2");
		List params = new ArrayList();
	
	    if(item.getState() != null) {
			 hql.append("and f.state = ?");
			 params.add(item.getState());
		 }
	    if(item.getEmp().getUid() != null && !"".equals(item.getEmp().getUid())) {
			 hql.append("and f.emp.uid = ? ");
			 params.add(item.getEmp().getUid());
		 }
	    if(item.getEmp().getTdepartment().getId() != null && 0 != item.getEmp().getTdepartment().getId()) {
			 hql.append("and f.emp.tdepartment.id = ? ");
			 params.add(item.getEmp().getTdepartment().getId());
		 }
		if (item.getRoomId() != null && item.getRoomId().getId()!=null && item.getRoomId().getId() != 0) {
			hql.append("and f.roomId = ? ");
			params.add(item.getRoomId());
		}
		if (item.getCycleTypeInfo() != null ) {
			hql.append("and f.cycleTypeInfo = ? ");
			params.add(item.getCycleTypeInfo());
		}
		if (item.getCycleType() != null && item.getCycleType() !=0) {
			hql.append("and f.cycleType = ? ");
			params.add(item.getCycleType());
		}
		//有循环的会议室预定
		if (flag == 1) {
			hql.append("and f.cycleType > 0");
		}else if(flag == 3){
			
		}else{
			hql.append("and f.cycleType < 1");
		}
		return formDao.select(hql.toString(), params.toArray());
	}

	/**
	 * 查询详细
	 */
	public Form doSelectById(int id) {
		StringBuffer hql = new StringBuffer(
				"from Form f join fetch f.roomId join fetch f.emp join fetch f.emp.tdepartment where f.id=? ");
		List<Form> rs = formDao.select(hql.toString(), id);
		if (!rs.isEmpty()) {
			return rs.get(0);
		}
		return null;

	}
}
