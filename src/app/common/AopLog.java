package app.common;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;

public class AopLog implements AfterReturningAdvice {
	
	private final  Log log=LogFactory.getLog(getClass());
	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {
		// TODO 自动生成方法存根
		String agrments=null;
		for (int i = 0; i < args.length; i++) {
			agrments+=","+args[i];
		}
		log.warn(method.getName()+"被调用"+agrments);
		System.out.println("AopLog....Start");
	}
	
}
