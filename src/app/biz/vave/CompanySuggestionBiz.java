package app.biz.vave;

import app.entity.vave.VaveCompanySuggestion;
public interface CompanySuggestionBiz {
	public void doInsert(VaveCompanySuggestion item);
	public VaveCompanySuggestion doSelectByproposalID(String proposalID);
}
