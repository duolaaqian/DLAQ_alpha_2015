package com.dlaq.test.core.collection;

import java.util.Date;

/**
 * 简单模拟
 * 
 * 	MyArrayList：自定义数组list，内部数组实现
 * 	MyLinkedList：自定义链表list，内部链表实现
 * 	MyHashMap：自定义hashMap，内部数组+链表实现
 * 	MyStack：自定义堆栈，内部通过Deque(双向堆栈)实现
 */
public class BasicDemo02 {
	public static void main(String[] args) {
		myArrayListTest();
		myLinkedListTest();
		myHashMapTest();
		mySatckTest();
	}
	public static void myArrayListTest(){
		System.out.println("****myArrayListTest************************");
		MyArrayList myArr = new MyArrayList();
		myArr.add("1234");
		myArr.add(2345);
		myArr.add(new Date());
		System.out.println(myArr);
		myArr.remove(1);
		System.out.println(myArr);
		myArr.add(1,"￥￥￥￥");
		System.out.println(myArr);
	}
	public static void myLinkedListTest(){
		System.out.println("****myLinkedListTest************************");
		MyLinkedList myArr = new MyLinkedList();
		myArr.add("1234");
		myArr.add(2345);
		myArr.add(new Date());
		System.out.println(myArr);
		myArr.remove(1);
		System.out.println(myArr);
		myArr.add(1,"￥￥￥￥");
		System.out.println(myArr);
		System.out.println(myArr.getFirst());
		System.out.println(myArr.getLast());
	}
	public static void myHashMapTest(){
		System.out.println("****myHashMapTest************************");
		MyHashMap myArr = new MyHashMap();
		myArr.put("123", 12);
		myArr.put("sxce", 100);
		myArr.put(900, 9);
		System.out.println(myArr.get("123"));
		System.out.println(myArr.get("sxce"));
		System.out.println(myArr.get(900));
	}
	public static void mySatckTest(){
		System.out.println("****mySatckTest************************");
		MyStack<String> ms = new MyStack<String>(10);
		ms.push("123");
		ms.push("aaa");
		ms.push("阿萨帝");
		ms.push("。。。");
		while(ms.size()>0){
			System.out.println(ms.pop());
		}
	}
}
