package com.dlaq.test.core.collection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;


/**
 * ArrayList：底层数组实现。查询快，修改、插入、删除慢。
 * LinkedList：底层链表实现。查询慢，修改、插入、删除快。
 * Vector(向量,可增长数组,线程安全的ArrayList)：底层数组实现。线程安全，效率低。
 * Stack：堆栈，后进先出，继承于Vector
 * 
 * HashMap：底层数组+链表实现。线程不安全，效率高。
 * Hashtable(线程安全的HashMap)：线程安全，效率低，k/v都不能为null
 * Properties：继承于HashTable，操作配置文件(.properties/.xml)
 * 
 * HashSet:底层HashMap实现(只有key)
 * 
 * TreeSet:可排序set，元素不可重复(添加参数时会调用对比方法对比)
 * 	传入的对象必须继承Comparable接口或者传入一个排序器Comparator
 * 	在添加元素的时候排序，改变已添加的元素顺序不会改变
 * 	所以一般不允许改变已传入元素的值，也可用final修饰相关属性
 * TreeMap:同TreeSet，根据key排序
 * 
 * LinkedHashMap：定义一个容量，当添加元素超过容量时，会将最“老”的值移除掉
 * EnumMap：枚举map(初始化指定key的枚举类型)，key不能为空，底层数组，效率高
 * EnumSet：枚举map(不能new，通过静态方法获取)
 * WeakHashMap：弱类型map，key为弱类型，会被回收
 * IdentityHashMap：比较地址map，key只有在==的时候才被认为是相同的，而非equals
 * 
 * ArrayDeque：队列
 * PriorityQueue：优先级队列，每次添加时进行排序
 * 
 * Collections：
 * 	1、binarySearch 有序容器 二分法查找对象所在位置
 * 	2、sort 对容器里的元素进行排序
 * 	3、reverse 将容器中的元素顺序进行反转
 * 	4、shuffle 将容器中的元素顺序进行随机排序(洗牌)
 * 	5、swap 将指定位置的指定元素进行替换
 * 	6、synchronizedCollection：synchronizedXXX方法，返回线程同步的容器
 * 	7、unmodifiableCollection：unmodifiableXXX方法，返回不可改变的容器
 * 
 */
public class BasicDemo01 {
	public static void main(String[] args) throws InterruptedException {
//		arrayListTest();		
//		vectorTest();
//		stackTest();
		hashMapTest();
//		hashtableTest();
//		enumSetTest();
	}
	public static void arrayListTest() throws InterruptedException{
		final List<String> l = new ArrayList<String>();
		l.add("123");
		l.add("111");
		l.add("098");
		Iterator<String> iter = l.iterator();
		new Thread(new Runnable(){
			@Override
			public void run() {
				l.clear();
			}
		}).start();
		Thread.sleep(1000);
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
	public static void vectorTest() throws InterruptedException{
		final Vector<String> vc = new Vector<String>();
		vc.add("1a");
		vc.add("2a");
		vc.add("3a");
		Iterator<String> iterator = vc.iterator();
		new Thread(new Runnable(){
			@Override
			public void run() {
				vc.remove(0);
			}
		}).start();
		Thread.sleep(1000);
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
	public static void stackTest(){
		Stack<String> st = new Stack<String>();
		st.add("123");
		st.add("999");
		st.add("&&&");
		while(!st.empty()){
			System.out.println(st.pop());
		}
	}
	public static void hashMapTest() throws InterruptedException{
		final Map<String,String> m = new HashMap<String,String>();
		m.put("a", "aaa");
		m.put("b", "bbb");
		m.put("c", "ccc");
		Iterator<String> iter = m.values().iterator();
		new Thread(new Runnable(){
			@Override
			public void run() {
				m.clear();
			}
		}).start();
		Thread.sleep(1000);
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
	public static void hashtableTest() throws InterruptedException{
		final Map<String,String> m = new Hashtable<String,String>();
		m.put("a", "aaa");
		m.put("b", "bbb");
		m.put("c", "ccc");
		Iterator<String> iter = m.values().iterator();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				System.out.println("in");
				m.clear();
				System.out.println("out");
			}
		}).start();
		Thread.sleep(1000);
		while(iter.hasNext()){
			System.out.println("while");
			System.out.println(iter.next());
		}
	}
	public static void propertiesTest(){
		
	}
	
	
	
	
	
	
	public static void treeSetTest(){
		Person p1 = new Person("姚明",35);
		Person p2 = new Person("郭德纲",41);
		Person p3 = new Person("刘晓庆",59);
		Person p4 = new Person("郭艾伦",24);
		Person p5 = new Person("谭咏麟",18);
		Person p6 = new Person("姚明",35);
		
		TreeSet<Person> ts = new TreeSet<Person>(new Comparator<Person>(){
			@Override
			public int compare(Person p1, Person p2) {
				return p1.getAge()-p2.getAge();
			}
		});
		ts.add(p1);
		ts.add(p2);
		ts.add(p3);
		ts.add(p4);
		ts.add(p5);
		ts.add(p6);
		
		System.out.println(ts);
	}
	
	public static void enumMapTest(){
		Map<Season,String> map = new EnumMap<Season,String>(Season.class);
		map.put(Season.AUTUMN, "");
		map.put(Season.SPRING, null);
		System.out.println(map.size());
	}
	public static void enumSetTest(){
		Set<Season> set = EnumSet.noneOf(Season.class);
		set.add(Season.WINTER);
		set.add(Season.SPRING);
		Iterator<Season> iter = set.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
	
}

class Person{
	private String name;
	private int age;
	public Person(String name,int age){
		this.name = name;
		this.age = age;
	}
	public int getAge(){
		return this.age;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("姓名：").append(this.name)
		.append("    年龄：").append(this.age).append("\r\n");
		return sb.toString();
	}
}
enum Season{
	SPRING,SUMMER,AUTUMN,WINTER
}
