package com.dlaq.test.core.test;

public class ExtendsTest01 {
	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		C c = new C();
		a = (A) c;
		
		//0.9313225746154785
	}
}

class A{
	public void say(){
		System.out.println("I'm A.");
	}
}
class B extends A{
	public void say(){
		System.out.println("I'm B.");
	}
}
class C extends A{
	public void say(){
		System.out.println("I'm C.");
	}
}