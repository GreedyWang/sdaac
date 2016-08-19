package app.biz.vave;
import java.util.List;

import app.entity.vave.VaveStatisticsSchedule;
public interface StatisticsScheduleBiz {
	public void doInsert(VaveStatisticsSchedule item);
	public void doUpdate(VaveStatisticsSchedule item);
	public void doInsert(
			String[] projects,String[] quantitys,String[] units,String proposalID);
	public List<VaveStatisticsSchedule> doSelectByPK(String proposalID);
	
}
