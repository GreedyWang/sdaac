package sdaac.wym.app.Service.vave;

import app.entity.Tproposal;
import sdaac.wym.app.entity.vave.Suggestion;

public class SuggestBiz implements ISuggest {

	public void NeedtoImprove(Suggestion item) {
		// TODO Auto-generated method stub
		item.getTproposal().setState(Tproposal.SELF);
	}

	public void Rationalization(Suggestion item) {
		// TODO Auto-generated method stub
		item.getTproposal().setState(Tproposal.RETIONAL);
	}

	public void approve(Suggestion item) {
		// TODO Auto-generated method stub
		if(item.getSuggestionType().endsWith("批准"))
		{
			this.commend(item);
		}else if(item.getSuggestionType().endsWith("合理化意见"))
		{
			this.Rationalization(item);
		}else if(item.getSuggestionType().endsWith("需完善")||(item.getSuggestionType().endsWith("不予批准")))
		{
			this.NeedtoImprove(item);
		}
	}

	public void commend(Suggestion item) {
		// TODO Auto-generated method stub
		item.getTproposal().setState(item.getTproposal().getState());
	}

}
