package com.security.biz;
import java.util.List;
import common.entity.TroleFunction;
public interface RoleFunctionBiz {
	public List<TroleFunction> doSelectAuthorizationContext();
}
