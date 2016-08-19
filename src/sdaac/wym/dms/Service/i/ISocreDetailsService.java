package sdaac.wym.dms.Service.i;

import java.util.List;

import app.entity.Tempolyee;

import sdaac.wym.dms.entity.ScoreDetails;
import sdaac.wym.dms.entity.Templete;

public interface ISocreDetailsService {
	public void doScore(ScoreDetails item);
	public void doScores(List<ScoreDetails>items,String version);
	public String[] getVersion();
	/**
	 * 查询具体得分
	 * @param emps
	 * @param version
	 * @return
	 */
	public List<ScoreDetails> doShow(String version,String leaderUid);
	public List<ScoreDetails> doShow(List<Tempolyee> emps,String version,List<Templete> templetes);
	public List<ScoreDetails> doShowWithScore(List<Tempolyee> emps,String version,List<Templete> templetes);
	
}
