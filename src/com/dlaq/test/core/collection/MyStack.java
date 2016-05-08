package com.dlaq.test.core.collection;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyStack<E> {
	//容器 双端队列
	private Deque<E> container = new ArrayDeque<E>();
	
	//容量
	private int cap;
	
	public MyStack(int cap){
		this.cap = cap;
	}

	//压栈
	public boolean push(E e){
		if(this.container.size()+1>cap){
			return false;
		}
		return this.container.offerLast(e);
	}
	//弹栈
	public E pop(){
		return this.container.pollLast();
	}
	//获取
	public E peek(){
		return this.container.peekLast();
	}
	public int size(){
		return this.container.size();
	}
	
}
