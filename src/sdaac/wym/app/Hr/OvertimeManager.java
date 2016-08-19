package sdaac.wym.app.Hr;

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

import app.dao.Emppdao;

/**
 * 加班时间统计
 * @author SA1KV5
 *
 */
public class OvertimeManager {
	private Emppdao sqlDao;
	
	public Emppdao getSqlDao() {
		return sqlDao;
	}

	public void setSqlDao(Emppdao sqlDao) {
		this.sqlDao = sqlDao;
	}

	/**
	 * 查询加班记录
	 * @param productArea
	 * @param beginTime
	 * @param endTime
	 * @return 104B-3线	2010-11-01	206.00	196.00	10.00
	 */
	public Map<Object,  List<Object[]>> search(String productArea,String beginTime,String endTime) {
		Map<Object,  List<Object[]>> rsMap = new HashMap<Object, List<Object[]>>();
		Calendar ca = Calendar.getInstance();
		List<Object[]> rs = sqlDao.selectBySql(getSql( productArea, beginTime, endTime));
		for(Object[] item : rs) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
			try {
				ca.setTime(sdf.parse(item[1].toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			//如果是周末,所有工作时间为OT
			if(ca.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || ca.get(Calendar.DAY_OF_WEEK) ==Calendar.SUNDAY) {
				item[3] = 0;
				item[4] = item[2];
			}
			//整理数据
			if(rsMap.get(item[0])== null) {
				List<Object[]> temp =new ArrayList<Object[]>();
				temp.add(item);
				rsMap.put(item[0],temp);
			}else {
				rsMap.get(item[0]).add(item);
			}
		}
		return rsMap;
	}
	
	private String getSql(String productArea,String beginTime,String endTime) {
		//sql
		StringBuffer sb = new StringBuffer();
		sb.append("select area,_day, sum(_all) _all ,");
		sb.append("sum(case when _all>8 then 8 else _all end) as [ST],");
		sb.append("sum(case when _all>8 then _all-8 else 0 end) as [OT] from ");
		sb.append("(select area,datatime _day, sum(worktime) _all  from dbo.tEmployeeProduct ep ");
		sb.append("inner join dbo.tEmpolyee e on e.uid = ep.uid ");
		sb.append("where area is not null ");
		if(null != beginTime && !beginTime.equals("")) {
			sb.append("and datatime between '"+beginTime+ "' and '"+ endTime+"'");
		}
		if(null != productArea && !productArea.equals("")) {
			sb.append("and ep.type = "+productArea);
		}
		sb.append("group by datatime,area,ep.uid ) as v1 ");
		sb.append("group by _day,area ");
		sb.append("order by _day");	
		return sb.toString();
	}
	
	public static void main(String[] args) {
		  ApplicationContext context= new ClassPathXmlApplicationContext("applicationContext.xml");
		  OvertimeManager ma=(OvertimeManager) context.getBean("overtimeManager");
		   ma.search("1", "2010-10-25", "2010-10-31");
//		  for(Object[] item : rs) {
//			  System.out.println(item[0]+"、"+item[1]+"、"+item[2]+"、"+item[3]+"、"+item[4]);
//		  }
			
	}
}
