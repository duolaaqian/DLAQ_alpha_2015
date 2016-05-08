package com.dlaq.test.core.test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;




public class TmpTest01 {
	public static void main(String[] args) {
/*		int a = -4;//1100 1010
		System.out.println(a);
		System.out.println(a>>1);
		System.out.println(a>>>1);
		
		System.out.println("==================");
		
		System.out.println(Integer.toBinaryString(a));
		System.out.println(Integer.toBinaryString(a>>1));
		System.out.println(Integer.toBinaryString(a>>>1));*/
		
//		double x = Math.pow(2, 4);
//		System.out.println(x);
		
/*		String s = "12";
		String s2 = "12";
		StringBuffer sb = new StringBuffer("12");
		StringBuffer sb2 = new StringBuffer("12");
//		System.out.println(sb2.equals(sb));
		System.out.println(s.hashCode());
		System.out.println(s2.hashCode());*/
		
/*		Demo0001 d1 = new Demo0001(1,"d1");
		Demo0001 d2 = new Demo0001(1,"d2");
//		System.out.println(d1.equals(d2));
//		System.out.println(d1.hashCode());
//		System.out.println(d2.hashCode());
		
		Map<Object,Object> a = new HashMap<Object,Object>();
		Object put = a.put(d1, "111sdwew");
		System.out.println(put);
//		System.out.println(a.get(d2));
*/		
		
/*		int h = 3;
		int length = 3;
		System.out.println(h & (length-1));
		UNIT.values();*/
		
/*		List<String> l = new ArrayList<String>();
		l.add("111");
		l.add("222");
		l.add("333");
		Iterator<String> ite = l.iterator();
		while(ite.hasNext()){
			System.out.println(ite.next());
		}
		System.out.println("------");
		System.out.println(ite.hasNext());*/
		
/*		String[] arr1 = {"1","2","3"};
		String[] arr2 = new String[2];
		System.arraycopy(arr1, 0, arr2, 0, 2);
		System.out.println(Arrays.toString(arr2));*/
		
/*		List<Integer> l = new ArrayList<Integer>();
		l.add(11);
		l.add(22);
		l.add(1);
		System.out.println(l);
		l.remove(1);//按index处理
		System.out.println(l);*/
		
/*		String a = "1234";
		String b = new String("1234");
		a.intern();
		b.intern();
		System.out.println(a==b.intern());*/
		
/*		TreeSet<String> ts = new TreeSet<String>();
		ts.add("123");
		ts.add("asd");
		ts.add("a1s2d3");
		System.out.println(ts);*/
		
		
//		Double d1 = new Double(-1.0/0.0);
//		Double d2 = new Double(0.0/0.0);
//		// returns true if this Double value is a Not-a-Number (NaN) 
//		System.out.println(d1 + " = " + d1.isNaN(d1));
//		// returns false for other cases
//		System.out.println(d2 + " = " + d2.isNaN(d2));
		
//		double d = 0.0/0.0;
//		System.out.println(d);
//		System.out.println(Double.isNaN(d));
		
//		Map m = new HashMap();
//		m.put("111", "aaa");
//		m.put("scde", null);
//		JSONArray ja = JSONArray.fromObject(m);
//		System.out.println(ja);
		
//		Integer[] a = {10};
//		JSONArray ja = JSONArray.fromObject(a);
		
		
//		Integer[] a = {10};
//		Object o = a;
//		Class<?> type = o.getClass().getComponentType();
//		System.out.println(type.isPrimitive());
		
//		List l = new ArrayList();
//		Map m = new HashMap();
//		m.put("2", "222");
//		l.add(m);
//		
//		Set s = new HashSet();
//		s.add(l);
//		s.add(m);
//		typeTest(s);
		
//		System.out.println(2<<10);
		
//		String a = "abc";
//		String b = "ab"+"c";
//		
//		String c1 = "ab";
//		String c2 = "c";
//		String c = c1+c2;
//		
//		final String d1 = "ab";
//		final String d2 = "c";
//		String d = d1+d2;
//		
//		System.out.println(a == b);
//		System.out.println(a == c);
//		System.out.println(a == d);
		
//		String s1 = "123";
//		String s2 = "123";
//		if(1==1){
//			s2 = "123";
//		}
//		System.out.println(s1==s2);
		
//		System.out.println(5/2);
		
//		dfs(0);
		
//		String str = "";
//		String arr[] = str.split(";");
//		System.out.println(arr.length);
		
	}
	
	static int[] a = {0,0,0,0};
	static int[] book = {0,0,0,0};
	static int num = 4;
	public static void dfs(int step){
		if(step==num){
			System.out.println(Arrays.toString(a));
		}else{
			for(int i=0;i<num;i++){
				if(book[i]==0){
					a[step]=i;
					book[step]=1;
					dfs(step+1);
					book[step]=0;
					//Arrays.fill(a, 0);
				}
			}
		}
	}
	
	
	
	public static void typeTest(Collection c){
		Iterator iter = c.iterator();
		
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		
		
	}
}
class Demo0001{
	private int id;
	private String name;
	public Demo0001(int id,String name){
		this.id = id;
		this.name = name;
	}
	public int getId(){
		return this.id;
	}

	@Override
	public int hashCode() {
/*		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;*/
		return 111;
	}
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Demo0001 && getId() == ((Demo0001) obj).getId();
	}
	@Override
	public String toString() {
		return this.id+" --> "+this.name;
	}
}

enum UNIT{
	abc("abc","123");
	public String LABEL;
	public String VALUE;
	UNIT(String label,String value){
		this.LABEL = label;
		this.VALUE = value;
	}
}

interface in1{
	public void fire();
}
interface in2{
	public void fire();
}
class in implements in1,in2{
	@Override
	public void fire() {
		System.out.println("----");
	}
}


class SingletonTest{
	private SingletonTest(){
	}
	private static class Honder{
		private static SingletonTest instence = new SingletonTest();
	}
	
	public static SingletonTest getSingleton(){
		return Honder.instence;
	}
}
