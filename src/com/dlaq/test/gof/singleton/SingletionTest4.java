package com.dlaq.test.gof.singleton;

import java.util.Random;

//test
public class SingletionTest4 {
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(){
			public void run() {
				MyJvm4.getInstance();
			};
		};
		t1.start();
		while(true){
			try {
				Thread.sleep(1100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.println(MyJvm4.getInstance()+" --> "+MyJvm4.getInstance().getName());
			System.out.println("in");
			System.out.println(MyJvm4.log);
			MyJvm4.log=new Random().toString()+"";
		}
		
//		MyJvm4 jvm1 = MyJvm4.getInstance();
//		System.out.println(jvm1.getName());
		
	}

	/**
	 * 4、双重校验
	 *  --> 懒汉式的改进，线程安全
	 */
	static class MyJvm4{
		public static String log = "llog";
		private static MyJvm4 instance;
		private String name;
		private MyJvm4(){
			for(int i=0;i>-1;i++){
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				name = "name-"+i;
				if(i<1000){
//					System.out.println("name="+name);	
				}
				
			}
		}
		public static MyJvm4 getInstance(){
			System.out.println("instance is null? --> "+instance);
			if(null==instance){
				synchronized(MyJvm4.class){
					if(null==instance){
						instance = new MyJvm4();
					}
				}
			}
			return instance;
		}
		public String getName(){
			return this.name;
		}
	}
}




