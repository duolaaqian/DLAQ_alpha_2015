package com.dlaq.test.gof.prototype;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 深克隆(取决于clone方法的实现程度)
 */
public class PrototypeDemo02 {
	public static void main(String[] args) throws Exception{
		System.out.println("**************深克隆**************");
		Date birthday = new Date(111111111);
		DeepSheep s1 = new DeepSheep("多利",birthday);
		DeepSheep s2 = (DeepSheep) s1.clone();
		System.out.println(s1);
		System.out.println(s2);
		birthday.setTime(211111111);;
		System.out.println("----------------------------------");
		System.out.println(s1);
		System.out.println(s2);
	}
}
class DeepSheep implements Cloneable{
	private String name;
	private Date birthday;
	public DeepSheep(String name, Date birthday){
		this.name = name;
		this.birthday = birthday;
	}
	@Override
	protected Object clone(){
		try {
			DeepSheep cloneObj = (DeepSheep)super.clone();
			cloneObj.birthday = (Date) this.birthday.clone();
			return cloneObj;
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