package com.dlaq.test.gof.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 通过 java.lang.reflect.Proxy 实现动态代理基本写法
 * 
 * 由于代理类实现代理功能的模式基本相同，所以我们可以写一个通用的方法去代理所有的实现类
 * 
 * 实现大致思路(动态编译)：
 * 	1、通过String去拼 XxxProxy.java 文件中的代码
 * 	2、生成文件
 * 	3、编译 运行 返回结果
 * (JVM底层直接拼接字节码来生成结果)
 * 
 * 实现过程类似于js中的 eval('alert()') 方法
 * 
 * 调用：
 * 	1、创建接口、实体类
 * 	2、创建对应实体类的 Handler 类，重写 invoke 方法，添加前后逻辑
 * 	3、通过 Proxy.newProxyInstance() 生成代理类
 * 	4、通过代理类执行方法
 */
public class DynamicProxy2 {
	public static void main(String[] args) {
		test1();
	}
	public static void test1(){
		Aircraft a = new Aircraft();
		AircraftHandler ah = new AircraftHandler(a);
		IFlyable proxy = (IFlyable) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), 
				new Class[]{IFlyable.class}, ah);
		proxy.fly();
	}
}
interface IFlyable{
	public void fly();
}
class Aircraft implements IFlyable{
	@Override
	public void fly(){
		for(int i=1;i<5;i++){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("飞机飞行"+i*100+"米....");
		}
	}
}
class AircraftHandler implements InvocationHandler{
	Aircraft a;
	public AircraftHandler(Aircraft a){
		this.a = a;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object object = null;
		if("fly".equals(method.getName())){
			System.out.println("开始计时....");
			System.out.println("[log]开始日志记录");
			long start = System.currentTimeMillis();
			object = method.invoke(a, args);
			long end = System.currentTimeMillis();
			System.out.println("[log]结束日志记录");
			System.out.println("计时结束 共运行"+(end-start));
		}
		return object;
	}
}