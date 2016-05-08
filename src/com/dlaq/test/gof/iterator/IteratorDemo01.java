package com.dlaq.test.gof.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 迭代器模式
 * 
 * 
 * 
 */
public class IteratorDemo01 {
	public static void main(String[] args) {
		MyIteratorList l = new MyIteratorList();
		l.add("123");
		l.add("aaa");
		l.add("sde");
		l.add("000");
		l.add("+++");
		MyIterator iter = l.getIterator();
		while(iter.hasNext()){
			System.out.println(iter.getCurrentObj());
			iter.next();
		}
	}
}

class MyIteratorList{
	List<Object> l = new ArrayList<Object>();
	public void add(Object obj){
		l.add(obj);
	}
	public Object get(int index){
		return l.get(index);
	}
	public Object remove(int index){
		return l.remove(index);
	}
	public Object remove(Object obj){
		return l.remove(obj);
	}
	public MyIterator  getIterator(){
		return new Iter();
	}
	private class Iter implements MyIterator{
		private int cursor;//游标
		@Override
		public void first() {
			cursor = 0;
		}
		@Override
		public void next() {
			cursor++;
		}
		@Override
		public boolean hasNext() {
			return cursor<l.size();
		}
		@Override
		public boolean isFirst() {
			return 0 == cursor;
		}
		@Override
		public boolean isLast() {
			return (l.size()+1) == cursor;
		}
		@Override
		public Object getCurrentObj() {
			return l.get(cursor);
		}
	}
}

interface MyIterator{
	void first();
	void next();
	boolean hasNext();
	boolean isFirst();
	boolean isLast();
	Object getCurrentObj();
}