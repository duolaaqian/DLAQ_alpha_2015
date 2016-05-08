package com.dlaq.test.pbw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlainBoiledWater01 {
	public static void main(String[] args) {
		test3();
	}
	
	public static StringBuffer t(){
		StringBuffer s = new StringBuffer("");
		try {
			System.out.println(s);
			s.append("0");
			System.out.println(s);
			return s;
		} catch (Exception e) {
			// TODO: handle exception
			return s.append("1");
		}finally{
			s = new StringBuffer("aaa");
		
		}
	}
	
	/**
	 * 理论上应该全是false，但是jdk1.7后做了优化，导致Integer小于127时的结果为true
	 * 注：好像有个方法叫equals()
	 */
	public static void test1(){
		Integer a1 = 1;
		Integer b1 = 1;
		Integer a2 = 128;
		Integer b2 = 128;
		System.out.println(a1 == b1);//jdk1.7 --> true
		System.out.println(a2 == b2);//false
	}
	/**
	 * java编译不仅仅是讲.java转成.class，还会做一些优化，比如将一些不变的东西连起来
	 * 注：恩，确实有个方法叫equals()
	 */
	public static void test2(){
		String a = "abc";
		String b = "ab"+"c";
		
		String c1 = "ab";
		String c2 = "c";
		String c = c1+c2;
		
		final String d1 = "ab";
		final String d2 = "c";
		String d = d1+d2;
		
		System.out.println(a == b);//true
		System.out.println(a == c);//false
		System.out.println(a == d);//true
	}
	
	/**
	 * 当list中的泛型为Integer时，remove()调用的是根据索引删除的方法而非按照对象删除
	 * 注：如果它按照索引没找到会不会再按照对象去找呢？当然不会，没找到就报错了啊
	 */
	public static void test3(){
		List<Integer> l = new ArrayList<Integer>();
		l.add(11);
		l.add(22);
		l.add(1);
		System.out.println(l);
		l.remove(1);
		System.out.println(l);
		l.remove(22);
	}
	/**
	 * 三目运算，当?后的两个参数有一个为基本类型时，另一个也会被进行拆箱操作
	 * 所以flag1会报错，而flag2的值为null，如果flag的类型为boolean则都会报错
	 * 注：有时候 m.containsKey(key) 还是挺有用的
	 */
	public static void test4(){
		Map<String,Boolean> m = new HashMap<String,Boolean>();
		Boolean flag1 = (null!=m)?m.get("123"):false;
		Boolean flag2 = (null!=m)?m.get("123"):Boolean.FALSE;
		System.out.println(flag1);
		System.out.println(flag2);
	}
	
	
	
	
}
