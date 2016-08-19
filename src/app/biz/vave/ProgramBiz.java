package app.biz.vave;

import java.util.List;

import app.entity.Tprogram;

public interface ProgramBiz {
	public void doInsert(Tprogram item);
	public void doSelect();
	public void doUpdate(Tprogram item);
	public List<Tprogram> doSelelctByThemeID(int themeID);
}
