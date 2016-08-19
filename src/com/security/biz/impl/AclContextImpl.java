package com.security.biz.impl;

import app.entity.Tuser;

import com.security.biz.AclContext;

public class AclContextImpl implements AclContext {

	private Tuser user=null;

	public Tuser getUser() {
		return user;
	}

	public void setUser(Tuser user) {
		this.user = user;
	}
}
