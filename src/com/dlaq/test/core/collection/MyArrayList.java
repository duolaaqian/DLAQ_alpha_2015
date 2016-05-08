package com.dlaq.test.core.collection;

import java.util.Arrays;

/**
 * 自定义ArrayList
 */
public class MyArrayList {
	
	private int size;//list大小
	
	private Object[] elementData;//元素数组
	
	public MyArrayList(){
		this(10);
	}
	public MyArrayList(int initialCapacity){
		if(initialCapacity<0){
			throw new IllegalArgumentException("初始大小不能为负数！");
		}
		this.elementData = new Object[initialCapacity];
	}
	public int size(){
		return size;
	}
	
	public boolean add(Object e){
		ensureCapacityInternal(size+1);
		elementData[size++] = e;
		return true;
	}
	public boolean add(int index,Object e){
		ensureCapacityInternal(size+1);
		int numMoved = size - index - 1;
		System.arraycopy(elementData, index, elementData, index+1, numMoved);
		elementData[index] = e;
		size++;
		return true;
	}
	public Object get(int index){
		return elementData[index];
	}
	public Object remove(int index){
		Object oldValue = elementData[index];
		int numMoved = size - index - 1;
		System.arraycopy(elementData, index+1, elementData, index, numMoved);
		elementData[size--] = null;
		return oldValue;
	}
	
	
	
	private void ensureCapacityInternal(int minCapacity){
		if(minCapacity>elementData.length){
			grow(minCapacity);
		}
	}
	private void grow(int minCapacity){
		int oldCapacity = elementData.length;
		int newCapacity = oldCapacity + (oldCapacity>>1);
		if(newCapacity - minCapacity < 0){
			newCapacity = minCapacity;
		}
		this.elementData = Arrays.copyOf(elementData, newCapacity);
	}
	
	
	
	@Override
	public String toString() {
		String tmp = "[";
		for(Object o : elementData){
			tmp += o + ",";
		}
		tmp += "]";
		return tmp;
	}
	
}
