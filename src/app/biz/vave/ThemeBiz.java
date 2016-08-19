package app.biz.vave;

import java.util.List;

import app.entity.Ttheme;

public interface ThemeBiz {
	public int doInsert(Ttheme item);
	public void doSelect();
	public void doUpdate(Ttheme item);
	public List<Ttheme> doSelectByProposalID(String id);
	public void doDeleteByPK(Integer pk);
}
