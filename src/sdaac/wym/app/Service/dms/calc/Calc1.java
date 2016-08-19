package sdaac.wym.app.Service.dms.calc;

import sdaac.wym.dms.entity.ScoreDetails;

/**
 * X>0?0:A
 * @author SA1KV5
 *
 */
public class Calc1 implements Calc {

	@Override
	public void doScore(ScoreDetails item) {
		// TODO Auto-generated method stub
		item.setScore((item.getActual())>item.getTarget()?0f:item.getWight());
	}

}
