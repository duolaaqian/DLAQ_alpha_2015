package com.dlaq.test.gof.singleton;

/**
 * 单例设计模式(单ClassLoader)
 * 
 * 1、懒汉式：
 * 		线程不安全、调用效率高、可以延迟加载
 * 		线程安全、调用效率低、可以延迟加载(加 synchronized 线程安全)
 * 2、恶汉式：
 * 		线程安全、调用效率高、不可以延迟加载
 * 3、静态内部类：
 * 		线程安全、调用效率高、可以延迟加载
 * 4、双重检测锁：
 * 		线程不安全、调用效率高、可以延迟加载
 * 		看似线程安全，但基于JVM内部模型，我们并不能完全控制程序执行过程
 * 		虽然 volatile 关键字可以解决该问题，但不兼容JDK1.5之前的版本
 * 5、枚举单例：
 * 		线程安全、调用效率高、不可以延迟加载
 * 		但是缺少了普通类的一些特性，不兼容JDK1.5之前的版本
 */
public class SingletionTest1 {
	
	public static void main(String[] args) {
		MyJvm1 jvm1 = MyJvm1.getInstance();
		MyJvm1 jvm1a = MyJvm1.getInstance();
		MyJvm2 jvm2 = MyJvm2.getInstance();
		MyJvm2 jvm2a = MyJvm2.getInstance();
		MyJvm3 jvm3 = MyJvm3.getInstance();
		MyJvm3 jvm3a = MyJvm3.getInstance();
		MyJvm4 jvm4 = MyJvm4.getInstance();
		MyJvm4 jvm4a = MyJvm4.getInstance();
		
		MyJvm5 jvm5 = MyJvm5.instance;
		MyJvm5 jvm5a = MyJvm5.instance;
		
		System.out.println(jvm1==jvm1a);
		System.out.println(jvm2==jvm2a);
		System.out.println(jvm3==jvm3a);
		System.out.println(jvm4==jvm4a);
		System.out.println(jvm5==jvm5a);
	}
	
	/**
	 * 5、枚举类
	 */
	public enum MyJvm5{
		instance;
		public void doSomething(){
		}
	}
}

/**
 * 1、懒汉式
 *  --> 构造私有化 
 *  --> 声明私有静态变量
 *  --> 对外提供访问静态变量方法，并保证获取对象存在
 */
class MyJvm1{
	private static MyJvm1 instance;
	private MyJvm1(){}
	public static MyJvm1 getInstance(){
		if(null == instance){
			instance = new MyJvm1();
		}
		return instance;
	}
}

/**
 * 2、恶汉式
 *  --> 构造私有化
 *  --> 声明私有变量，并初始化
 *  --> 对外提供访问静态变量方法
 */
class MyJvm2{
	private static MyJvm2 instance = new MyJvm2();
	private MyJvm2(){}
	public static MyJvm2 getInstance(){
		return instance;
	}
}

/**
 * 3、静态内部类
 *  --> 构造私有化
 *  --> 声明静态内部类，内部类中声明私有变量并初始化
 *  --> 对外提供访问 静态内部类的静态变量 的方法
 */
class MyJvm3{
	private static class JVMHolder{
		private static MyJvm3 instance = new MyJvm3();
	}
	private MyJvm3(){}
	public static MyJvm3 getInstance(){
		return JVMHolder.instance;
	}
}

/**
 * 4、双重校验
 *  --> 懒汉式的改进，线程安全
 */
class MyJvm4{
	private static MyJvm4 instance;
	private MyJvm4(){}
	public static MyJvm4 getInstance(){
		if(null==instance){
			synchronized(MyJvm4.class){
				if(null==instance){
					instance = new MyJvm4();
				}
			}
		}
		return instance;
	}
}


