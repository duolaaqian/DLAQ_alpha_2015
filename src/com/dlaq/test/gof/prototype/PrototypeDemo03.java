package com.dlaq.test.gof.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 序列化克隆(完全克隆)
 */
public class PrototypeDemo03 {
	public static void main(String[] args) throws Exception{
		System.out.println("**************序列化克隆**************");
		Date birthday = new Date(111111111);
		Sheep s1 = new Sheep("多利",birthday);
		
		//通过序列化、反序列化生成新的对象
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(s1);
		byte[] bytes = bos.toByteArray();
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = new ObjectInputStream(bis);
		Sheep s2 = (Sheep) ois.readObject();
		
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s1.getObj());
		System.out.println(s2.getObj());
	}
}
class Sheep implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private Date birthday;
	//引用类型属性测试
	private Object obj = new Serializable(){
		private static final long serialVersionUID = 1L;};
	public Sheep(String name, Date birthday){
		this.name = name;
		this.birthday = birthday;
	}
	public Object getObj(){
		return this.obj;
	}
	@Override
	public String toString() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return "["+this.hashCode()+"]姓名："+this.name+"，生日："+sf.format(this.birthday);
	}
}