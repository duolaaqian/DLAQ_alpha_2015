package com.dlaq.test.gof.singleton;

//test
public class SingletionTest3 {
	
	public static void main(String[] args) {
		System.out.println("--before--");
		MyJvm1 jvm1 = MyJvm1.getInstance();
		System.out.println("--after--"+jvm1);
	}
	/**
	 * 1、懒汉式
	 *  --> 构造私有化 
	 *  --> 声明私有静态变量
	 *  --> 对外提供访问静态变量方法，并保证获取对象存在
	 */
	static class MyJvm1{
		private static MyJvm1 instance;
		private MyJvm1(){System.out.println("--MyJvm1--"+instance);}
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
	static class MyJvm2{
		private static MyJvm2 instance = new MyJvm2();
		private MyJvm2(){System.out.println("--MyJvm2--");}
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
	static class MyJvm3{
		private static class JVMHolder{
			private static MyJvm3 instance = new MyJvm3();
		}
		private MyJvm3(){System.out.println("--MyJvm3--");}
		public static MyJvm3 getInstance(){
			System.out.println("--MyJvm3 in--");
			return JVMHolder.instance;
		}
	}

	/**
	 * 4、双重校验
	 *  --> 懒汉式的改进，线程安全
	 */
	static class MyJvm4{
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
	/**
	 * 5、枚举类
	 */
	public enum MyJvm5{
		instance;
	}
}




