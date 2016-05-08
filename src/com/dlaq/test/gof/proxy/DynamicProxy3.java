package com.dlaq.test.gof.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 通过 java.lang.reflect.Proxy 实现动态代理
 * 
 * 	1、定义公共接口
 * 	2、定义真实对象 继承接口 实现功能
 * 	3、定义方法处理实现类(TimeHandler/LogHandler)
 * 
 * 拥有静态代理的优点，同时实现了 实现类 和 代理类 的分离(TimeHandler/LogHandler 可用于任何被代理类)
 * 接口修改后只需修改具体的实现类，不需要修改代理类
 * 比 静态代理 重用性更好，维护性更好
 */
public class DynamicProxy3 {
	public static void main(String[] args) {
		test1();
		System.out.println("-----------------------------");
		test2();
		System.out.println("-----------------------------");
		test3();
		System.out.println("-----------------------------");
		test4();
	}
	/**
	 * Tank 先进行时间记录，再进行log记录
	 */
	public static void test1(){
		Tank t = new Tank();
		LogHandler lh = new LogHandler(t);
		IMoveable logProxy = (IMoveable)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), 
				new Class[]{IMoveable.class}, lh);
		TimeHandler th = new TimeHandler(logProxy);
		IMoveable timeProxy = (IMoveable)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), 
				new Class[]{IMoveable.class}, th);
		timeProxy.move();
	}
	/**
	 * Tank 先进行log记录，再进行时间记录
	 */
	public static void test2(){
		Tank t = new Tank();
		TimeHandler th = new TimeHandler(t);
		IMoveable timeProxy = (IMoveable)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), 
				new Class[]{IMoveable.class}, th);
		LogHandler lh = new LogHandler(timeProxy);
		IMoveable logProxy = (IMoveable)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), 
				new Class[]{IMoveable.class}, lh);
		logProxy.move();
	}
	/**
	 * Aircraft 先进行时间记录，再进行log记录
	 */
	public static void test3(){
		Aircraft a = new Aircraft();
		LogHandler lh = new LogHandler(a);
		IFlyable logProxy = (IFlyable)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), 
				new Class[]{IFlyable.class}, lh);
		TimeHandler th = new TimeHandler(logProxy);
		IFlyable timeProxy = (IFlyable)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), 
				new Class[]{IFlyable.class}, th);
		timeProxy.fly();
	}
	/**
	 * Aircraft 先进行log记录，再进行时间记录
	 */
	public static void test4(){
		Aircraft a = new Aircraft();
		TimeHandler th = new TimeHandler(a);
		IFlyable timeProxy = (IFlyable)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), 
				new Class[]{IFlyable.class}, th);
		LogHandler lh = new LogHandler(timeProxy);
		IFlyable logProxy = (IFlyable)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), 
				new Class[]{IFlyable.class}, lh);
		logProxy.fly();
	}
}
/**
 * 记录时间具体实现
 */
class TimeHandler implements InvocationHandler{
	Object obj;
	public TimeHandler(Object o){
		this.obj = o;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object object = null;
		System.out.println("方法"+method.getName()+"开始调用 开始计时....");
		long start = System.currentTimeMillis();
		object = method.invoke(obj, args);
		long end = System.currentTimeMillis();
		System.out.println("方法"+method.getName()+"调用结束 用时"+(end-start));
		return object;
	}
}
/**
 * 记录日志具体实现
 */
class LogHandler implements InvocationHandler{
	Object obj;
	public LogHandler(Object o){
		this.obj = o;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object object = null;
		System.out.println("[log]记录"+method.getName()+"方法开始调用日志");
		object = method.invoke(obj, args);
		System.out.println("[log]记录"+method.getName()+"方法结束调用日志");
		return object;
	}
}