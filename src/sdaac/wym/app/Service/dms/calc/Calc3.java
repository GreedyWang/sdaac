package sdaac.wym.app.Service.dms.calc;

import java.util.List;

import sdaac.wym.dms.entity.ScoreDetails;
/**
 * y≥ W设定值+（ 1-设定值）/2	Score=W1*系数/max(W1*系数…Wn*系数)*Z
W设定值≤ W实际值<W设定值+（ 1-设定值）/2	Score=W1*系数/max(X1*系数…XWn*系数)*Z-5
W实际 值< W设定值	Score=0

 * @author SA1KV5
 *
 */
public class Calc3 implements Calc{

	private List<ScoreDetails> others;
	
	public List<ScoreDetails> getOthers() {
		return others;
	}

	public void setOthers(List<ScoreDetails> others) {
		this.others = others;
	}

	@Override
	public void doScore(ScoreDetails item) {
		// TODO Auto-generated method stub
		if((item.getActual())>item.getTarget()+(1-item.getTarget())/2){
			//	Score=W1*系数/max(W1*系数…Wn*系数)*Z
			item.setScore((item.getActual())*item.getXishu()/getOtherMax());
		}else if((item.getActual())>item.getTarget()){
			item.setScore((item.getActual())*item.getXishu()/getOtherMax()-5);
		}else {
			item.setScore(0);
		}
	}
	
	private Float maxActual=0f; 
	
	private Float getOtherMax(){
		for(ScoreDetails detail : others){		
			if(maxActual < (detail.getActual())*detail.getXishu()){
				maxActual = (detail.getActual())*detail.getXishu();
			}
		}
		return maxActual;
	}
}
