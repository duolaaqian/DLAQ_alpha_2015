package com.dlaq.test.core.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IntegerTest01 {
	public static void main(String[] args) {
/*		Integer a = 2;
		Integer b = 2;
		Integer c = 200;
		Integer d = 200;
		Integer aa = new Integer(2);
		Integer bb = new Integer(2);
		Integer cc = new Integer(200);
		Integer dd = new Integer(200);
		
		System.out.println(a==b);
		System.out.println(c==d);
		System.out.println(aa==bb);
		System.out.println(cc==dd);*/
		
//		System.out.println(new Date()==new Date());
		List<Object> l = new ArrayList<Object>();
		l.add(new Date());
		System.out.println(l);
		System.out.println(l.size());
		boolean remove = l.remove(new Date());
		System.out.println(remove);
		System.out.println(l.size());
		System.out.println(l);
	}
}
