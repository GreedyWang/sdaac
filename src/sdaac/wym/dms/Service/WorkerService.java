package sdaac.wym.dms.Service;

import java.util.List;

import sdaac.wym.dms.Service.i.IWorkerService;

import common.dao.CommonDAO;

import app.entity.Tempolyee;

/**
 * @des DMS人员组织
 * @author SA1KV5
 *
 */
public class WorkerService implements IWorkerService {

	private CommonDAO<Tempolyee> empdao;

	public CommonDAO<Tempolyee> getEmpdao() {
		return empdao;
	}

	public void setEmpdao(CommonDAO<Tempolyee> empdao) {
		this.empdao = empdao;
	}

	@Override
	public List<Tempolyee> doGetUnderlings(Tempolyee item) {
		// TODO Auto-generated method stub
		String level = Tempolyee.getNextLevel(item.getGroup());
		String hql = "from Tempolyee where district = ? and group = ?";
		return empdao.select(hql, new Object[] { item.getDistrict(), level });

	}

}
