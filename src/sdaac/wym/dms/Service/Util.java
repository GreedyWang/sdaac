package sdaac.wym.dms.Service;

import java.util.ArrayList;
import java.util.List;

import sdaac.wym.dms.entity.Indicate;
import sdaac.wym.dms.entity.Score;
import sdaac.wym.dms.entity.ScoreDetails;
import sdaac.wym.dms.entity.ScoreShow;

import app.entity.Tempolyee;

public class Util {
	
	public static List<ScoreShow> AtoB(List<Tempolyee> emps , List<Indicate> indicates){
		List<ScoreShow> rs = new ArrayList<ScoreShow>();
		for(Tempolyee emp:emps){
			ScoreShow ss = new ScoreShow();
			ss.setEmp(emp);
			ss.indicates = indicates;
			for(Indicate item : indicates){
				Result srs = new Result();
				if(item.getType().equals("1")){ //类型为1 不需要系数和目标值				
					srs.setTarget(-1.0f);
					srs.setXushu(-1.0f);
				}
				if(item.getType().equals("2")){ //类型为2 不需要系数				
//					srs.setTarget(-1.0f);	
					srs.setXushu(-1.0f);
				}
				ss.results.add(srs);
			}
			rs.add(ss);
		}
		return rs;
	}
	
	//把数据库表ScoreDetails转换成显示格式ScoreShow
	public static List<ScoreShow> CtoB(List<ScoreDetails> sds){
		List<ScoreShow> rs = new ArrayList<ScoreShow>();
		String lastuid="";
		for(ScoreDetails sd:sds){
			
			if(sd.getEmp().getUid().equals(lastuid)){
				rs.get(rs.size()-1).indicates.add(sd.getIndicate());
				Result ars = new Result();
				ars.setScore(sd.getScore());
				ars.setTarget(sd.getTarget());
				ars.setXushu(sd.getXishu());
				ars.setActual(sd.getActual());
				rs.get(rs.size()-1).getResults().add(ars);	
			}else{
				ScoreShow ss = new ScoreShow();
				ss.setEmp(sd.getEmp());
				ss.indicates.add(sd.getIndicate());
				Result ars = new Result();
				ars.setScore(sd.getScore());
				ars.setTarget(sd.getTarget());
				ars.setXushu(sd.getXishu());
				ars.setActual(sd.getActual());
				ss.getResults().add(ars);	
				lastuid = sd.getEmp().getUid();
				rs.add(ss);
			}						
		}		
		return rs;
	}
	
	//把数据库表ScoreDetails转换成显示格式ScoreShow
	public static List<ScoreShow> CtoB4F(List<ScoreDetails> sds){
		List<ScoreShow> rs = new ArrayList<ScoreShow>();
		String lastuid="";
		for(ScoreDetails sd:sds){
			
			if(sd.getEmp().getUid().equals(lastuid)){
				Indicate i = sd.getIndicate();
				i.setWeight(sd.getWight().intValue());
				rs.get(rs.size()-1).indicates.add(i);
				Result ars = new Result();
				ars.setScore(sd.getScore());
				ars.setTarget(sd.getTarget());
				ars.setXushu(sd.getXishu());
				ars.setActual(sd.getActual());
				rs.get(rs.size()-1).getResults().add(ars);	
			}else{
				ScoreShow ss = new ScoreShow();
				ss.setEmp(sd.getEmp());
				Indicate i = sd.getIndicate();
				i.setWeight(sd.getWight().intValue());
				ss.indicates.add(i);
				Result ars = new Result();
				ars.setScore(sd.getScore());
				ars.setTarget(sd.getTarget());
				ars.setXushu(sd.getXishu());
				ars.setActual(sd.getActual());
				ss.getResults().add(ars);	
				lastuid = sd.getEmp().getUid();
				rs.add(ss);
			}						
		}		
		return rs;
	}
	
	
	
	//把数据库表ScoreDetails转换成显示格式ScoreShow
	public static List<Score> ScoreShowToScore(List<ScoreShow> sds){
		List<Score> rs = new ArrayList<Score>();		
		for(ScoreShow item:sds){
			Score scoreItem = new Score();
			scoreItem.setUname(item.getEmp().getName());
			scoreItem.setArea(item.getEmp().getArea());
			scoreItem.setWorkgroup(item.getEmp().getGroup()+"");
			scoreItem.setScore1(item.getResults().get(0).getScore());
			scoreItem.setScore2(item.getResults().get(1).getScore());
			scoreItem.setScore3(item.getResults().get(2).getScore());
			scoreItem.setPeopelCount(item.peopelCount);
			scoreItem.setTotalScore(item.totalScore);	
			rs.add(scoreItem);
		}
		return rs;
	}
	
	public static List<ScoreDetails> DtoC(String[] a,String[] b,String[] c,String[] d,String[] e){
		List<ScoreDetails> rs = new ArrayList<ScoreDetails>();
//		for(e)
		for(int i=0;i<a.length;i++){
			ScoreDetails sd = new ScoreDetails();
			sd.setEmp(new Tempolyee(a[i]));
//			sd.setTarget(b[i]);
//			sd.setXishu(c[i]);
			sd.setActual(Float.parseFloat(d[i]));
			
			
		}
//	}
		return rs;
	}
	
	
}

