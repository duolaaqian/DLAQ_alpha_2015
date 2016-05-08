package com.dlaq.test.thread.create;

/**
 * 线程创建方法2：继承Runnable接口
 * 	1、创建类并继承Runnable接口，重写 run() 方法
 * 	2、通过 Thread 生成代理类
 * 	3、调用代理类的 start() 方法启动线程
 */
public class CreateTest2 {
	public static void main(String[] args) {
		Work1 w1 = new Work1();
		Work2 w2 = new Work2();
		
		Thread t1 = new Thread(w1);
		Thread t2 = new Thread(w2);
		
		t1.start();
		t2.start();
		
		for(int i=0;i<1000;i++){
			System.out.println("main....");
		}
	}
}

class Work1 implements Runnable{
	@Override
	public void run() {
		for(int i=0;i<1000;i++){
			System.out.println("一边聊QQ....");
		}
	}
}
class Work2 implements Runnable{
	@Override
	public void run() {
		for(int i=0;i<1000;i++){
			System.out.println("一边敲hello world....");
		}
	}
}