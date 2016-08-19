package test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestMyClass {
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args){
		// TODO Auto-generated method stub
		Class c = null;
		try {
			c = Class.forName("test.MyClass");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Method[] ms = c.getDeclaredMethods();
		for(Method m :ms){
			if(m.isAnnotationPresent(TestCase.class)){
			TestCase tc = m.getAnnotation(TestCase.class);
			Object o;
			try {
				o = c.newInstance();
				String s = tc.value();
				System.out.println(m.invoke(o, s));;
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		
		}
	}
}
