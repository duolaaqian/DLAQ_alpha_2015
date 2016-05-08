package com.dlaq.test.gof.prototype;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 原型模式
 * 
 * 拷贝一个对象(浅拷贝、深拷贝)
 * 	1、继承Cloneable接口
 * 	2、重写clone()方法(深拷贝需要在此方法中对引用类型属性进行拷贝)
 * 
 * 优点：
 * 	1、底层调用native方法，效率高
 * 	2、跳过了构造方法，如果调用构造方法耗时较多，克隆则可以省略这部分时间
 * 缺点：
 * 	1、深度拷贝涉及的层数不易过多，否则会造成混乱
 * 
 * 注意：与单例模式冲突，使用时要小心
 * 
 * 场景：(常与工厂模式同时出现)
 * 	1、spring的bean(singleton/prototype)
 * 	2、orm框架update时
 */

/**
 * 浅拷贝(只拷贝值类型对象属性，引用类型属性则拷贝的是引用)
 */
public class PrototypeDemo01 {
	public static void main(String[] args) throws Exception{
		System.out.println("**************浅克隆**************");
		Date birthday = new Date(111111111);
		ShallowSheep s1 = new ShallowSheep("多利",birthday);
		ShallowSheep s2 = (ShallowSheep) s1.clone();
		System.out.println(s1);
		System.out.println(s2);
		birthday.setTime(211111111);;
		System.out.println("---------------------------------");
		System.out.println(s1);
		System.out.println(s2);
	}
}
class ShallowSheep implements Cloneable{
	private String name;
	private Date birthday;
	public ShallowSheep(String name, Date birthday){
		this.name = name;
		this.birthday = birthday;
	}
	@Override
	protected Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public String toString() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return "["+this.hashCode()+"]姓名："+this.name+"，生日："+sf.format(this.birthday);
	}
}