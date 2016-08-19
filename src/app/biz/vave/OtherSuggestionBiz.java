package app.biz.vave;
import app.entity.TotherSuggestion;
import java.util.List;
public interface OtherSuggestionBiz {
	public void doInsert(TotherSuggestion item);
	public List<TotherSuggestion> doSelectByproposalID(String proposalID);
	public void doUpdate(TotherSuggestion item);
	public List<TotherSuggestion> doSelectByConditions(TotherSuggestion item);
	public List<TotherSuggestion> doSelectByOtherSug(TotherSuggestion item);
	public void deleteOtherSugByProposalId(String proposalId);
}
