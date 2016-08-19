package app.entity.vave;

import java.util.ArrayList;
import java.util.List;

public class VaveSchedule {
	private List<VaveProjectSchedule> projectList=new ArrayList<VaveProjectSchedule>();
	private List<VaveStatisticsSchedule> statisticsList=new ArrayList<VaveStatisticsSchedule>();
	public List<VaveProjectSchedule> getProjectList() {
		return projectList;
	}
	public void setProjectList(List<VaveProjectSchedule> projectList) {
		this.projectList = projectList;
	}
	public List<VaveStatisticsSchedule> getStatisticsList() {
		return statisticsList;
	}
	public void setStatisticsList(List<VaveStatisticsSchedule> statisticsList) {
		this.statisticsList = statisticsList;
	}
	
	
}
