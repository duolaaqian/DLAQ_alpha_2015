package com.dlaq.test.thread.status;

/**
 * 线程阻塞
 * 	t1.join();
 * 阻塞本线程，t1执行完后再执行本线程
 * 相当于将本线程追加到t1后(合并线程)
 */
public class JoinDemo01 extends Thread{
	public static void main(String arg[]) throws InterruptedException{
		JoinDemo01 demo1 = new JoinDemo01("t1");
		JoinDemo01 demo2 = new JoinDemo01("t2");
		Thread t1 = new Thread(demo1);
		Thread t2 = new Thread(demo2);
		
		t1.start();
		t2.start();
		for(int i=0;i<1000;i++){
			if(100==i){
				t1.join();//main阻塞，t执行完后main再执行(阻塞当前线程，并将当前线程追加到t1后)
			}
			System.out.println("main"+i+" .... ");
		}
	}
	
	private String name;
	public JoinDemo01(String name){
		this.name = name;
	}
	
	@Override
	public void run() {
		for(int i=0;i<2000;i++){
			System.out.println("["+name+"]"+i+" .... .... ");
		}
	}
}
