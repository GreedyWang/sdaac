package app.entity.vave;
public class Schedule {
	private VaveStatisticsSchedule statistics=new VaveStatisticsSchedule();
	private VaveProjectSchedule project=new VaveProjectSchedule();
	public VaveStatisticsSchedule getStatistics() {
		return statistics;
	}
	public void setStatistics(VaveStatisticsSchedule statistics) {
		this.statistics = statistics;
	}
	public VaveProjectSchedule getProject() {
		return project;
	}
	public void setProject(VaveProjectSchedule project) {
		this.project = project;
	}
}
