package com.security.biz.impl;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

import com.security.biz.AclContext;
import com.security.biz.SecurityAdvise;

public class SecurityAdviseImpl implements SecurityAdvise ,MethodBeforeAdvice{

	private AclContext dao=null;
	
	public AclContext getDao() {
		return dao;
	}
	public void setDao(AclContext dao) {
		this.dao = dao;
	}
	public void before(Method method, Object[] params, Object target) throws Throwable {
		// TODO 自动生成方法存根
		String targetName=target.getClass().getName();
		String methodName=method.getName();
		
		
		
		
		method.invoke(target, params);
	}
	public boolean checkUserById(int id) {
		// TODO 自动生成方法存根
		return false;
	}

}
