package com.dlaq.test.core.test;

public class VolatileTest {
	public static void main(String[] args) throws InterruptedException {
		for(int i=0;i<1000;i++){
			new Thread(new Runnable(){
				@Override
				public void run() {
					MyObj.inc();	
				}
			}).start();
		}
//		Thread.sleep(2000);
//		System.out.println();
//		System.out.println(MyObj.count);
	}
}
class MyObj{
	public static int count = 0;
	public static void inc(){
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		System.out.print(count);
		count++;
		if(count>990){
			System.out.println(count);
		}
	}
}