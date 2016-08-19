package sdaac.wym.dms.Service.i;

import java.util.List;

import app.entity.Tempolyee;

public interface IWorkerService {
	/**
	 * 得到下属
	 * @param item
	 * @return
	 */
	public List<Tempolyee> doGetUnderlings(Tempolyee item);
}
