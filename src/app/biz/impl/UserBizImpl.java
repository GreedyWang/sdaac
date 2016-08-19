package app.biz.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

import common.dao.CommonDAO;

import app.biz.UserBiz;
import app.entity.Test;
import app.entity.Tuser;

public class UserBizImpl implements UserBiz, UserDetailsService {
	private CommonDAO<Tuser> userdao = null;

	public CommonDAO<Tuser> getUserdao() {
		return userdao;
	}

	public void setUserdao(CommonDAO<Tuser> userdao) {
		this.userdao = userdao;
	}

	public Tuser checkUser(String uid) {
		// TODO �Զ���ɷ������
		String hql = "from Tuser as u where u.uid=?";
		List<Tuser> list = userdao.select(hql, uid);
		if (list.isEmpty()) {
			return new Tuser();
		} else {
			return list.get(0);
		}

	}

	public void doInsert(String uid, int rights) {
		// TODO 自动生成方法存根
		Tuser user = new Tuser();
		user.setUid(uid);
		user.setRights(rights);
		userdao.insert(user);
	}

	public UserDetails loadUserByUsername(String uname)
			throws UsernameNotFoundException, DataAccessException {
		// TODO 自动生成方法存根
		String hql = "from Tuser as u where u.name=?";
		return userdao.select(hql, uname).get(0);
	}

}
