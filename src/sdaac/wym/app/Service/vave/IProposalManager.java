package sdaac.wym.app.Service.vave;

import app.entity.Tproposal;
import java.util.List;

import sdaac.wym.app.entity.vave.Suggestion;
public interface IProposalManager {
	public void add(Tproposal item);
	public void remove(String id);
	public void update(Tproposal item);
	//didn't osiv
	public List<Tproposal> seletList(Tproposal item);
	// osiv modul
	public List<Tproposal> selet(Tproposal item);
	public Tproposal selectDatils(String pk);
	public void approve(Suggestion item);
}
