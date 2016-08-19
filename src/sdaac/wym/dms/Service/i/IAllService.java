package sdaac.wym.dms.Service.i;

import java.util.List;

import sdaac.wym.dms.entity.ScoreDetails;
import app.entity.Tempolyee;

public interface IAllService {
	public void getScoreCard();

	public List<ScoreDetails> getScoreCard(Tempolyee item);
}
