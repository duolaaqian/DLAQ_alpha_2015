package com.dlaq.test.core.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class IteratorTest01 {
	public static void main(String[] args) {
//		listTest();
//		setTest();
		mapTest();
	}
	
	public static void listTest(){
		List<Object> l = new ArrayList<Object>();
		l.add("123");
		l.add(999);
		l.add(new IteratorTest01());
		l.add(new Date());
		Iterator<Object> it = l.iterator();
		System.out.println(l.size());
		while(it.hasNext()){
			Object o = it.next();
			System.out.println(o);
			if(o instanceof Integer){
				it.remove();
			}
		}
		System.out.println(l.size());
	}
	public static void setTest(){
		Set<Object> s = new HashSet<Object>();
		s.add("123");
		s.add(999);
		s.add(new IteratorTest01());
		s.add(new Date());
		Iterator<Object> it = s.iterator();
		System.out.println(s.size());
		while(it.hasNext()){
			Object o = it.next();
			System.out.println(o);
			if(o instanceof Integer){
				it.remove();
			}
		}
		System.out.println(s.size());
	}
	public static void mapTest(){
		Map<Object,Object> m = new HashMap<Object,Object>();
		m.put("aa", "aaaa");
		m.put("bb", "bbbb");
		
		//
		Set<Object> keySet = m.keySet();
		Iterator<Object> it = keySet.iterator();
		while(it.hasNext()){
			Object o = it.next();
			System.out.println(o+" >==< "+m.get(o));
		}
		
		System.out.println("*********");
		//
		Set<Entry<Object, Object>> entrySet = m.entrySet();
		Iterator<Entry<Object, Object>> it2 = entrySet.iterator();
		while(it2.hasNext()){
			Entry<Object, Object> o = it2.next();
			System.out.println(o.getKey()+" >==< "+o.getValue());
		}
	}
}
