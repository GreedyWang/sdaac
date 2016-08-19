package test;

public class MyClass {
	@TestCase("this is method 1")
	public String Method1(String s){
		return s;
	}
	
	//@TestCase("this is method 1")
	public String Method2(String s){
		return s;
	}
	
	@TestCase("this is method 3")
	public String Method3(String s){
		return s;
	}
}