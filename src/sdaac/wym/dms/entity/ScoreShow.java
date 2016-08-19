package sdaac.wym.dms.entity;

import java.util.ArrayList;
import java.util.List;

import sdaac.wym.dms.Service.Result;

import app.entity.Tempolyee;

/**
 * 网页显示版本的ScoreDetail
 * @author SA1KV5
 *
 */
public class ScoreShow {
	
	private Tempolyee emp;
	public List<Indicate> indicates = new ArrayList<Indicate>();//Indicate[20];
	public List<Result> results = new ArrayList<Result>();
	public float totalScore;
	public int peopelCount;
	public int totalBouns;
	public int ranking;
	
	/**
	 * 得到总分
	 */
	public float getTotalScore(List<Float> x,int i){
		float totalScore=0;
		for(Result rs : results){
			totalScore+=rs.getScore();
		}
		if(emp!=null && emp.getGroup()!=null &&(emp.getGroup().equals("1")||emp.getGroup().equals("0"))){
			totalScore = totalScore * emp.getDmsratio()==0?1:emp.getDmsratio();
			if(emp.getGroup().equals("1")){//出勤 
				if(x!=null && x.size()>i)
				totalScore = (float) (totalScore*(21.75 - x.get(i)));				
			}
		}
		return totalScore;
	}
	
	public Tempolyee getEmp() {
		return emp;
	}
	public void setEmp(Tempolyee emp) {
		this.emp = emp;
	}
//	public Indicate[] getIndicates() {
//		return indicates;
//	}
//	public void setIndicates(Indicate[] indicates) {
//		this.indicates = indicates;
//	}

	public List<Indicate> getIndicates() {
		return indicates;
	}
	public void setIndicates(List<Indicate> indicates) {
		this.indicates = indicates;
	}		

	public List<Result> getResults() {
		return results;
	}
	public void setResults(List<Result> results) {
		this.results = results;
	}
}


