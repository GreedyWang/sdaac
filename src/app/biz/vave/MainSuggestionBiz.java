package app.biz.vave;

import java.util.List;

import app.entity.Tempolyee;
import app.entity.TmainSuggestion;
import app.entity.Tproposal;

public interface MainSuggestionBiz {
public List<TmainSuggestion> doSelectByDepartID(Tempolyee employee,Tproposal item);
public void doInsert(TmainSuggestion mainSug);
public TmainSuggestion doSelectByProposalID(String id);
}

