package com.dlaq.test.thread.syn;

/**
 * 线程同步(安全、效率低)
 * synchronized关键字主要是锁定资源
 * synchronized关键字修饰的资源有线程访问，则该资源对于其他线程的状态就为锁定状态
 * 该线程执行完synchronized修饰的代码块后，该资源解除锁定状态
 * 其他线程如果访问被锁定的资源，则会等待，等待该资源解除锁定状态
 */
public class SynDemo01 {
	public static void main(String[] args) {
		MyJvm mj = new MyJvm();
		Thread t1 = new Thread(mj,"张三");
		Thread t2 = new Thread(mj,"老王");
		
		t1.start();
		t2.start();
	}
}

class MyJvm implements Runnable{
	@Override
	public void run() {
		synchronized(MyJvm.class){
			for(int i=0;i<1;i++){
				System.out.println(Thread.currentThread().getName()+" 线程执行开始 ");	
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(int i=0;i<1;i++){
				System.out.println(Thread.currentThread().getName()+" 线程执行结束 ");	
			}
		}
	}
}