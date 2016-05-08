package com.dlaq.test.core.collection;

import java.util.LinkedList;

public class MyHashMap {
	private int size;
	
	@SuppressWarnings("unchecked")
	private LinkedList<MyHashMap.Entry>[] arr = new LinkedList[10];
	
	public void put(Object key, Object value){
		Entry e = new Entry(key, value);
		int a = key.hashCode()%arr.length;
		if(null == arr[a]){
			LinkedList<Entry> l = new LinkedList<Entry>();
			l.add(e);
			arr[a] = l;
		}else{
			LinkedList<Entry> l = arr[a];
			for(Entry le : l){
				if(le.key.equals(e.key)){
					le.value = e.value;
					return;
				}
			}
			l.add(e);
		}
		size++;
	}
	public Object get(Object key){
		int a = key.hashCode()%arr.length;
		if(null != arr[a]){
			LinkedList<Entry> l = arr[a];
			for(Entry le : l){
				if(le.key.equals(key)){
					return le.value;
				}
			}
		}
		return null;
	}
	
	public int size(){
		return size;
	}
	
	private class Entry{
		private Object key;
		private Object value;
		public Entry(Object key, Object value){
			this.key = key;
			this.value = value;
		}
	}
	
}
