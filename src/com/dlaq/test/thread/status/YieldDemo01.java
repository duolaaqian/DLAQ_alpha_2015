package com.dlaq.test.thread.status;

/**
 * 线程暂停
 * 	Thread.yield();
 * 暂停本线程，但是并不确保一定会运行其他线程
 * 如果cpu再次调度到本线程则会继续执行本线程
 */
public class YieldDemo01 extends Thread{
	public static void main(String[] args) {
		YieldDemo01 y1 = new YieldDemo01();
		Thread t1 = new Thread(y1);
		t1.start();
		
		for(int i=0;i<100;i++){
			System.out.println("main...."+i+"["+i%20+"]");
			if(i%20==0){
				System.out.println("y");
				Thread.yield();//暂停本线程 main
			}
		}
		
	}
	
	@Override
	public void run() {
		for(int i=0;i<1000;i++){
			try {
				Thread.sleep(0, 1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("yield...."+i);
		}
	}
}
