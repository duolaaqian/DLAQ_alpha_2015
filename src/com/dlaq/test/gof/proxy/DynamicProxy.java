package com.dlaq.test.gof.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {
	public static void main(String[] args) {
		IStar realStar = new RealStar();
		StarHandler handler = new StarHandler(realStar);
		
		IStar proxy = (IStar) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), 
				new Class[]{IStar.class}, handler);
		
		proxy.sing();
	}
}
class StarHandler implements InvocationHandler{
	IStar realStar;
	
	public StarHandler(IStar realStar){
		this.realStar = realStar;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object object = null;
		object = method.invoke(realStar, args);
		return object;
	}
	
}