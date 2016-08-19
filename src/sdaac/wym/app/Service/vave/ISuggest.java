package sdaac.wym.app.Service.vave;

import sdaac.wym.app.entity.vave.Suggestion;

public interface ISuggest {
	public void approve(Suggestion item);
	public void commend(Suggestion item);
	public void NeedtoImprove(Suggestion item);
	public void Rationalization(Suggestion item);
}
