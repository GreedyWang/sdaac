package app.biz.vave.impl;

import java.util.List;

import common.dao.CommonDAO;

import app.biz.vave.ProgramBiz;
import app.entity.Tprogram;

public class ProgramBizImpl implements ProgramBiz {

	private CommonDAO<Tprogram> programdao=null;
	public void doInsert(Tprogram item) {
		// TODO 自动生成方法存根
		programdao.insert(item);
	}

	public void doSelect() {
		// TODO 自动生成方法存根

	}

	public void doUpdate(Tprogram item) {
		// TODO 自动生成方法存根
		//programdao.update(item);
		String hql="update Tprogram as tp set tp.insteadProgram=? where tp.ttheme.id=?";
		Integer	param=	item.getTtheme().getId();
		Object[] params=new Object[2];
		params[0]=item.getInsteadProgram();
		params[1]=item.getTtheme().getId();
		programdao.bulkUpdate(hql, param);
	}

	public CommonDAO<Tprogram> getProgramdao() {
		return programdao;
	}

	public void setProgramdao(CommonDAO<Tprogram> programdao) {
		this.programdao = programdao;
	}

	public List<Tprogram> doSelelctByThemeID(int themeID) {
		// TODO 自动生成方法存根
		String hql="from Tprogram as program where program.ttheme.id=?";
		return programdao.select(hql, themeID);
		
	}



}
