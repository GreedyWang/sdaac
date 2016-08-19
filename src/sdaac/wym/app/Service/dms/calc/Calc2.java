package sdaac.wym.app.Service.dms.calc;

import java.util.List;

import sdaac.wym.dms.entity.ScoreDetails;
/**
 * " y=y+1
 *	Z=Z/(y1/min(y1….yn))"	
 * @author SA1KV5
 *
 */
public class Calc2 implements Calc {
	
	private List<ScoreDetails> others;
	public List<ScoreDetails> getOthers() {
		return others;
	}

	public void setOthers(List<ScoreDetails> others) {
		this.others = others;
	}

	@Override
	public void doScore(ScoreDetails item) {
		// TODO Auto-generated method stubs
		System.out.println("Calc2==>"+getOtherMin());
		item.setScore((item.getActual())/getOtherMin()*item.getWight());
	}
	
	private Float minActual=10000f; 
	
	private Float getOtherMin(){
		for(ScoreDetails detail : others){		
			if(minActual > (detail.getActual())){
				minActual = (detail.getActual());
			}
		}
		return minActual;
	}
	
	
}
