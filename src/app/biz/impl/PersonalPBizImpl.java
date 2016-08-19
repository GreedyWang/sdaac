package app.biz.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sdaac.wym.app.entity.hr.BounceFigure;

import common.dao.CommonDAO;
import common.util.Page;
import app.biz.PersonalPBiz;
import app.dao.Emppdao;
import app.entity.IP;
import app.entity.TemployeeProduct;
import app.entity.Test;

public class PersonalPBizImpl implements PersonalPBiz {

	private CommonDAO<TemployeeProduct> employeeProductdao = null;
	private Emppdao empdao = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public void doInsert(TemployeeProduct item) {
		// TODO �Զ���ɷ������
		employeeProductdao.insert(item);
	}

	public void FlushInsert(List list) {
		employeeProductdao.blukFlushInsert(list);
	}

	public TemployeeProduct doSelectByid(int id) {
		return employeeProductdao.selectByPk(id);
	}

	/**
	 * 计算岗位工资
	 */

	public static String getTime(Calendar calendar) {
		int month = calendar.get(Calendar.MONTH) + 1;

		int year = calendar.get(Calendar.YEAR);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		String beginTime;
		if (day <= 24) {
			month--;
			if (month == 0) {
				year--;
				month = 12;
			}

		}
		beginTime = year + "-" + month + "-" + 25;
		return beginTime;
	}

	public List<Test> selectAll(String myDate, String endTime, String uid,
			Page page) {
		// TODO ￄ1�7�1�7
		String hql = "select uid,"
				+ "sum(case postType when 'A' then sumTime else null end ) as a,"
				+ "sum(case postType when 'B' then sumTime else null end ) as b, "
				+ "sum(case postType when 'C' then sumTime else null end ) as c,"
				+ "sum(case postType when 'D' then sumTime else null end ) as d "
				+ "from view2 where 1=1 ";

		String countHql = "select count(*) from (select uid from view2 group by uid) as f where 1=1";
		if (null != myDate && !myDate.equals("")) {
			// if(myDate.length()<=7)
			// {
			// myDate=myDate+"-"+24;
			// }
			// Date date=new Date();
			// try {
			// date=sdf.parse(myDate);
			// } catch (ParseException e) {
			// // TODO 自动生成 catch 块
			// e.printStackTrace();
			// }
			// Calendar calendar=Calendar.getInstance();
			// calendar.setTime(date);
			// String beginTime=this.getTime(calendar);
			hql += "and datatime between '" + myDate + "' and '" + endTime
					+ "'";
			countHql = "select count(*) from (select uid  from view2 where datatime between '"
					+ myDate
					+ "' and '"
					+ endTime
					+ "' group by uid) as f where 1=1 ";
		} else {
			Calendar calendar = Calendar.getInstance();
			Date date = new Date();
			String nowTime = sdf.format(date);

			String beginTime = this.getTime(calendar);

			hql += "and datatime between '" + beginTime + "' and '" + nowTime
					+ "'";
			countHql = "select count(*) from (select uid  from view2 where datatime between '"
					+ beginTime
					+ "' and '"
					+ nowTime
					+ "' group by uid) as f where 1=1 ";

		}
		if (null != uid && !uid.equals("")) {
			hql += "and uid='" + uid + "'";
			countHql += " and uid='" + uid + "'";
		}
		hql += " group by uid";

		List<Object[]> dd = null;
		if (page == null) {
			dd = empdao.doSelectByCondtion(hql);
		} else {
			dd = empdao.doSelectByPaging(hql, countHql, page, null);
		}

		List<Test> results = new ArrayList<Test>();
		for (Object[] element : dd) {

			Test item = new Test();
			item.setUid(element[0].toString());
			if(element[1]!=null){
				item.setTypeA(Float.parseFloat(element[1].toString()) );	
			}
			if(element[2]!=null){
				item.setTypeB(Float.parseFloat(element[2].toString()) );
			}
			if(element[3]!=null){
				item.setTypeC(Float.parseFloat(element[3].toString()) );	
			}
			if(element[4]!=null){
				item.setTypeD(Float.parseFloat(element[4].toString()) );
			}						
			results.add(item);
		}
		return results;
	}

	/**
	 * 统计岗位时间
	 * 
	 * @return
	 */
	public List<Object[]> selectEachTypeTime(String myDate) {
		String sql = "select view11.pid,view11.alltime,p.postType,p.productName from (select "
				+ "ep.postid as pid, "
				+ "sum(ep.worktime) as alltime "
				+ "from dbo.tEmployeeProduct as ep ";

		if (null != myDate && !myDate.equals("")) {
			if (myDate.length() <= 7) {
				myDate = myDate + "-" + 24;
			}
			Date date = new Date();
			try {
				date = sdf.parse(myDate);
			} catch (ParseException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			String beginTime = this.getTime(calendar);
			sql += "where datatime between '" + beginTime + "' and '" + myDate
					+ "'";
		}
		sql += "group by ep.postid ) as view11 " + "inner join dbo.tPost as p "
				+ "on view11.pid=p.id " + "order by view11.pid ";
		List<Object[]> reslut = this.empdao.doSelectByCondtion(sql);
		return reslut;
	}

	public Emppdao getEmpdao() {
		return empdao;
	}

	public void setEmpdao(Emppdao empdao) {
		this.empdao = empdao;
	}

	public List<TemployeeProduct> getPostSalary() {
		String hql = "from TemployeeProduct as pp inner join fetch pp.tpost";
		List<TemployeeProduct> list = employeeProductdao.select(hql, null);

		return null;
	}

	private String countHql;

	private void getCountHql(String myDate, String uid) {
		countHql = "select count(*) from (select uid from view2 group by uid) as f where 1=1";
		if (null != myDate && !myDate.equals("")) {
			countHql = "select count(*) from (select uid  from view2 where DATEDIFF( day,datatime, '"
					+ myDate + "')<1 group by uid) as f where 1=1 ";
		}
		if (null != uid && !uid.equals("")) {
			countHql += " and uid='" + uid + "'";
		}
	}

	public Map<String, List<IP>> insertIP(String myDate) {
		Map<String, List<IP>> map = new HashMap<String, List<IP>>();
		String hql = "select tmp.uid,postid,ptworktime,ptcount,target,totalWorkTime from dbo.tPost as post,view4, "
				+ "(select uid,postid,sum(worktime) as ptworktime,sum(output) as ptcount "
				+ "from dbo.tEmployeeProduct "
				+ " where DATEDIFF(day, datatime, getdate())=0"
				+ " group by postid,uid) as tmp "
				+ "where post.id=tmp.postid and view4.uid=tmp.uid" + "";
		List<Object[]> dd = empdao.doSelectByCondtion(hql);
		for (Object[] element : dd) {
			IP item = new IP();
			String uid = (String) element[0];
			item.setPostid((String) element[1]);
			item.setPtworktime((Float) element[2]);
			item.setPtcount((Float) element[3]);
			item.setTarget((Float) element[4]);
			item.setSumt((Float) element[5]);
			if (map.containsKey(uid)) {
				List<IP> list = map.get(uid);
				list.add(item);
				map.put(uid, list);
			} else {
				List<IP> list = new ArrayList<IP>();
				list.add(item);
				map.put(uid, list);
			}
		}
		return map;
	}

	public void doInsertIPTMP(List<Object[]> list) {
		for (Object[] object : list) {
			// empdao.(object[0],object[1]);
		}

	}

	public void doUpdate(TemployeeProduct item) {
		String hql = "update TemployeeProduct as ep set ep.worktime=?, ep.output=? ,ep.datatime=?, "
				+ "ep.registerID=?, ep.teamLeaderId=?, ep.tpost.id=? where ep.id=?";
		Object[] params = new Object[7];
		params[0] = item.getWorktime();
		params[1] = item.getOutput();
		params[2] = item.getDatatime();
		params[3] = item.getRegisterID();
		params[4] = item.getTeamLeaderId();
		params[5] = item.getTpost().getId();
		params[6] = item.getId();
		employeeProductdao.bulkUpdate(hql, params);
	}
	
	
	/**
	 * 得到每条线的工作时间
	 * @return
	 */
	public Float[] getEachLineTime(TemployeeProduct item,BounceFigure pBf){
		String sql = "select dd.figureId,remark,CarType,standWorkTime,inbound,picesNum,al,p.type,p.name,_lineNo from HRBounceFigure p "
			+ "right join "
			+ "(select figureId,sum(worktime) al "
			+ "from tEmployeeProduct ep where 1=1 ";
	
			// 车间区域
			if (item.getType() != null) {
				sql += " and ep.type=" + item.getType();
			}
			// 车间区域
			if(item.getTypes()!=null){		
					sql+="and ep.type in "+item.getTypes();			
			}
			// 工作日期
			if (item.getBeginDatatime() != null
					&& !item.getBeginDatatime().equals("")) {
				sql += " and  year(datatime)=" + item.getBeginDatatime().split("-")[0]; //年
					
			}
			if (item.getEndDatatime() != null && !item.getEndDatatime().equals("")) {
				sql += " and  month(datatime)=" + item.getBeginDatatime().split("-")[1];//月
					
			}
			sql += " group by figureId) dd " + "on p.figureId=dd.figureId "
					+ " left join (select * from dbo.HRInbound where 1=1 ";
			// 工作日期
			if (item.getBeginDatatime() != null
					&& !item.getBeginDatatime().equals("")) {
				sql += " and  datediff(day,'" + item.getBeginDatatime()
						+ "',inboundTime)>=0 ";
			}
			if (item.getEndDatatime() != null && !item.getEndDatatime().equals("")) {
				sql += " and  datediff(day,inboundTime,'" + item.getEndDatatime()
						+ "')>=0 ";
			}
		
			sql += " ) b on b.figureId=p.remark ";
			//零件类型
			if(pBf.getType()!=null&&!pBf.getType().equals("All")){
				sql +=" where p.type='"+pBf.getType()+"'";
			}
			return null;
	}
	
	/**
	 * 查询图号每月总工时
	 */
	public Float[] selectFigureAllTime(TemployeeProduct item,BounceFigure pBf,
			List<BounceFigure> bfs) {
		Float pWorktime = 0f;// 清洗总工时
		Float qWorktime = 0f;// 钎焊炉工时

		String sql = "select dd.figureId,remark,CarType,standWorkTime,inbound,picesNum,al,p.type,p.name,_lineNo,productarea from HRBounceFigure p "
				+ "right join "
				+ "(select figureId,sum(worktime) al "
				+ "from tEmployeeProduct ep where 1=1 ";
		
		// 车间区域
		if (item.getType() != null) {
			sql += " and ep.type=" + item.getType();
		}
		// 车间区域
		if(item.getTypes()!=null){		
				sql+="and ep.type in "+item.getTypes();			
		}
		// 工作日期
		if (item.getBeginDatatime() != null
				&& !item.getBeginDatatime().equals("")) {
			sql += " and  year(datatime)=" + item.getBeginDatatime().split("-")[0]; //年
				
		}
		if (item.getEndDatatime() != null && !item.getEndDatatime().equals("")) {
			sql += " and  month(datatime)=" + item.getBeginDatatime().split("-")[1];//月
				
		}
		if(item.getFigureId()!=null&&!item.getFigureId().equals("")){
			sql+=" and ep.postid like '%"+item.getFigureId()+"%'";		
		}
		
		sql += " group by figureId) dd " + "on p.figureId=dd.figureId "
				+ " left join (select * from dbo.HRInbound where 1=1 ";
		// 工作日期
		if (item.getBeginDatatime() != null
				&& !item.getBeginDatatime().equals("")) {
			sql += " and  datediff(day,'" + item.getBeginDatatime()
					+ "',inboundTime)>=0 ";
		}
		if (item.getEndDatatime() != null && !item.getEndDatatime().equals("")) {
			sql += " and  datediff(day,inboundTime,'" + item.getEndDatatime()
					+ "')>=0 ";
		}

		sql += " ) b on b.figureId=p.remark where 1=1 ";
		//零件类型
		if(pBf.getType()!=null&&!pBf.getType().equals("All")){
			sql +="and p.type='"+pBf.getType()+"'";
		}
		//图号的生产区域
		if(item.getTypes()!=null){
			sql+="and p.productArea in "+item.getTypesName();
		}
		sql += " order by al desc";
		List<Object[]> rs = employeeProductdao.selectBySql(sql);
		// 计算
		for (Object[] dd : rs) {
			BounceFigure bf = new BounceFigure();
			if (dd[0] == null) {
				dd[0] = "无";
			}
			bf.setFigureId(dd[0].toString());
			if (dd[1] == null) {
				dd[1] = "无";
			}
			bf.setRemark(dd[1].toString());
			if (dd[2] == null) {
				dd[2] = "无";
			}
			bf.setCarType(dd[2].toString());
			if (dd[3] == null) {
				dd[3] = 0;
			}
			bf.setStandWorkTime(Float.parseFloat(dd[3].toString()));
			if (dd[4] == null) {
				dd[4] = 0;
			}
			bf.setInboundNum(Long.parseLong(dd[4].toString()));
			if (dd[5] == null) {
				dd[5] = 0;
			}
			bf.setPicesNum(Integer.parseInt(dd[5].toString()));
			if (dd[6] == null) {
				dd[6] = 0;
			}
			bf.setRealWorkTime(Float.parseFloat(dd[6].toString()));
			if (bf.getFigureId().startsWith("P000")) {
				pWorktime += Float.parseFloat(dd[6].toString());
			}
			if (bf.getFigureId().startsWith("Q000")) {
				qWorktime += Float.parseFloat(dd[6].toString());
			}
			if (dd[7] == null) {
				dd[7] = "无";
			}
			if (dd[8] == null) {
				dd[8] = "无";
			}
			if(dd[9]==null){
				dd[9] = "无";
			}
			if(dd[10]==null){
				dd[10] = "无";
			}
			bf.setType(dd[7].toString());
			bf.setName(dd[8].toString());
			bf.setLineNO(dd[9].toString());
			bf.setProductArea(dd[10].toString());
			bfs.add(bf);
		}
		return new Float[] { pWorktime, qWorktime };//清洗，切焊
	}

	/**
	 * 查询每日产量
	 */
	public List<TemployeeProduct> doSelectDailyRecord(boolean flag,
			TemployeeProduct item, String beginTime, String endTime, Page page) {
		/** *inner join fetch empProduct.tpost */
		String hql = "from TemployeeProduct as empProduct where 1=1 ";
		String countHql = "select count(*) from TemployeeProduct as empProduct  where 1=1 ";
		if (item.getTempolyee().getUid() != null
				&& !item.getTempolyee().getUid().equals("")) {
			hql += "and empProduct.tempolyee.uid='"
					+ item.getTempolyee().getUid() + "'";
			countHql += "and uid=" + item.getTempolyee().getUid();
		}
		if (item.getTeamLeaderId() != null
				&& !item.getTeamLeaderId().equals("")) {
			hql += "and empProduct.teamLeaderId='" + item.getTeamLeaderId()
					+ "'";
			countHql += "and master=" + item.getTeamLeaderId();
		}
		// 岗位号
		if (item.getTpost().getId() != null
				&& !"".equals(item.getTpost().getId())) {
			hql += "and empProduct.tpost.id like '%" + item.getTpost().getId()
					+ "%'";
			countHql += "and empProduct.postid like '%"
					+ item.getTpost().getId() + "%'";
		}
		// 岗位工资？车间奖金
		if (item.getType() != null && item.getType() != -1) {
			hql += "and empProduct.type='" + item.getType() + "'";
			countHql += "and empProduct.type=" + item.getType();
		}
		// 工作日期
		if (beginTime != null && !beginTime.equals("")) {
			hql += "and  datediff(day,'" + beginTime
					+ "',empProduct.datatime)>=0";
			countHql += "and  datediff(day,'" + beginTime
					+ "',empProduct.datatime)>=0";
		}
		if (endTime != null && !endTime.equals("")) {
			hql += "and  datediff(day,empProduct.datatime,'" + endTime
					+ "')>=0";
			countHql += "and datediff(day,empProduct.datatime,'" + endTime
					+ "')>=0";
		}
		// 记录日期
		if (item.getBeginRegisterTime() != null
				&& !item.getBeginRegisterTime().equals("")) {
			hql += "and  datediff(day,'" + item.getBeginRegisterTime()
					+ "',empProduct.registerTime)>=0";
			countHql += "and  datediff(day,'" + item.getBeginRegisterTime()
					+ "',empProduct.registerTime)>=0";
		}
		if (item.getEndRegisterTime() != null
				&& !item.getEndRegisterTime().equals("")) {
			hql += "and  datediff(day,empProduct.registerTime,'"
					+ item.getEndRegisterTime() + "')>=0";
			countHql += "and datediff(day,empProduct.registerTime,'"
					+ item.getEndRegisterTime() + "')>=0";
		}

		if (flag) {
			hql += " and empProduct.registerID='" + item.getRegisterID() + "'";
			countHql += " and empProduct.registerID='" + item.getRegisterID()
					+ "'";
		}
		if (page == null) {
			return employeeProductdao.select(hql, null);
		}
		return employeeProductdao.selectWithPage(countHql, hql, null, page);
	}

	public void delete(Integer id) {
		employeeProductdao.deleteByPK(id);
	}

	// -------------------------
	public CommonDAO<TemployeeProduct> getEmployeeProductdao() {
		return employeeProductdao;
	}

	public void setEmployeeProductdao(
			CommonDAO<TemployeeProduct> employeeProductdao) {
		this.employeeProductdao = employeeProductdao;
	}

	/**
	 * 把日产量记录的岗位号截取第一段保存到figureid上
	 */
	public void test() {
		String hql = "from TemployeeProduct as p where type=1";
		List<TemployeeProduct> dd = this.empdao.select(hql, null);
		for (TemployeeProduct item : dd) {
			item.setFigureId(item.getTpost().getId().split("_")[0]);
			this.empdao.update(item);
		}
	}
	
	/***
	 * 取得指定组长的上次记录
	 * @param teamLeaderId
	 * @return
	 */
	public List<TemployeeProduct> getLast(String teamLeaderId){
		String sql="select max(groupId) from tEmployeeProduct where master="+teamLeaderId;
	//	List<TemployeeProduct> rs =	employeeProductdao.select(hql, teamLeaderId);
		List rs = employeeProductdao.selectBySql(sql);
		if(rs!=null&&!rs.isEmpty()){
			String hql="from TemployeeProduct as ep inner join fetch ep.tempolyee inner join fetch ep.tpost where groupId=?";
			return employeeProductdao.select(hql, rs.get(0));
		}else{
			return null;
		}		
	}

	
	public static void main(String[] args) {
		 ApplicationContext context = new ClassPathXmlApplicationContext(
		 "applicationContext.xml");
		 PersonalPBiz dd = (PersonalPBiz) context.getBean("ppBiz");
		 dd.getLast("1382");
		//System.out.println(Calendar.getInstance().getTimeInMillis());
	}

}
