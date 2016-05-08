package com.dlaq.test.gof.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Constructor;

/**
 * 单例 防反射、防反序列化 方法
 * 
 * 反射：
 * 	在构造方法中判断实例是否为null，不为null则抛出异常阻止操作
 * 序列化：
 * 	重写 readResolve() 返回单例
 */
public class SingletionTest2 {
	
	public static void main(String[] args){
		//单例普通获取方法
		MyJvm7 jvm1 = MyJvm7.getInstance();
		MyJvm7 jvm2 = MyJvm7.getInstance();
		System.out.println(jvm1);
		System.out.println(jvm2);
		
		//未作处理 通过反射可以获取新的对象实例
		MyJvm3  jvm3 = getOtherInstance1(MyJvm3.class);
		MyJvm3  jvm4 = getOtherInstance1(MyJvm3.class);
		System.out.println(jvm3);
		System.out.println(jvm4);
		
		//处理后 通过反射获取对象实例时会抛出异常
		MyJvm7  jvm5 = getOtherInstance1(MyJvm7.class);
		MyJvm7  jvm6 = getOtherInstance1(MyJvm7.class);
		System.out.println(jvm5);
		System.out.println(jvm6);
		
		//经过 序列化-->反序列化 可以得到新的对象实例
		MyJvm8 jvm7 = MyJvm8.getInstance();
		MyJvm8 jvm8 = getOtherInstance2(MyJvm8.class,jvm7);
		System.out.println(jvm7);
		System.out.println(jvm8);
		
		//处理后 反序列化得到的对象为单例对象
		MyJvm9 jvm9 = MyJvm9.getInstance();
		MyJvm9 jvm10 = getOtherInstance2(MyJvm9.class,jvm9);
		System.out.println(jvm9);
		System.out.println(jvm10);
	}
	
	/**
	 * 测试方法
	 * 根据反射，跳过 安全性检查(private)
	 * 通过构造方法实例化对象
	 */
	public static <T> T getOtherInstance1(Class<T> clazz){
		Constructor<T> c;
		T returnObj = null;
		try {
			c = clazz.getDeclaredConstructor();
			c.setAccessible(true);
			returnObj = c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnObj;
	}
	
	/**
	 * 测试方法
	 * 通过 序列化-->反序列化 获取新的对象实例
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getOtherInstance2(Class<T> clazz,Object instance){
		T returnObj = null;
		try{
			FileOutputStream fos = new FileOutputStream("d:/a.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(instance);
			oos.close();
			fos.close();
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/a.txt"));
			returnObj =  (T) ois.readObject();
			ois.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnObj;
	}

}

class MyJvm7{
	private static class JVMHolder{
		private static MyJvm7 instance = new MyJvm7();
	}
	private MyJvm7(){
		if(null!=JVMHolder.instance){
			throw new RuntimeException();//阻止反射实例化对象
		}
	}
	public static MyJvm7 getInstance(){
		return JVMHolder.instance;
	}
}

class MyJvm8 implements Serializable{
	private static final long serialVersionUID = 1L;
	private static class JVMHolder{
		private static MyJvm8 instance = new MyJvm8();
	}
	private MyJvm8(){}
	public static MyJvm8 getInstance(){
		return JVMHolder.instance;
	}
}

class MyJvm9 implements Serializable{
	private static final long serialVersionUID = 1L;
	private static class JVMHolder{
		private static MyJvm9 instance = new MyJvm9();
	}
	private MyJvm9(){}
	public static MyJvm9 getInstance(){
		return JVMHolder.instance;
	}
	private Object readResolve() throws ObjectStreamException {
		return JVMHolder.instance;//序列化返回单例对象
	}
}

