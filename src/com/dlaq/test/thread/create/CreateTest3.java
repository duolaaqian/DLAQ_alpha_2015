package com.dlaq.test.thread.create;

/**
 * 线程创建方法3：匿名内部类
 * 	1、创建 Thread 类并重写 run() 方法
 * 	3、调用 start() 方法启动线程
 */
public class CreateTest3 {
	public static void main(String[] args) {
		Thread t1 = new Thread(){
			@Override
			public void run() {
				for(int i=0;i<1000;i++){
					System.out.println("匿名....");
				}
			}
		};
		t1.start();
		for(int i=0;i<1000;i++){
			System.out.println("main....");
		}
	}
}