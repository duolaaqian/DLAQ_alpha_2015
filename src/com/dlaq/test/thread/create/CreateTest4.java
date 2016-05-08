package com.dlaq.test.thread.create;

/**
 * 推荐使用继承 Runnable 方法创建线程
 * 	1、避免单继承局限性
 * 	2、便于共享资源
 * 
 * 	模仿买票，票为共享资源，多线程操作同一资源
 */
public class CreateTest4 {
	public static void main(String[] args) {
		Web12306 web = new Web12306();
		
		Thread t1 = new Thread(web,"路人甲");
		Thread t2 = new Thread(web,"黄牛乙");
		Thread t3 = new Thread(web,"工程狮");
		
		t1.start();
		t2.start();
		t3.start();
	}
}

class Web12306 implements Runnable{
	private int num = 50;
	
	@Override
	public void run() {
		while(true){
			if(num<=0){
				break;
			}
			System.out.println(Thread.currentThread().getName()+"抢到了"+num--);
		}
	}
}