package com.dlaq.test.thread.create;

/**
 * 线程创建方法1：创建线程类
 * 	1、创建线程类，继承Thread并重写 run() 方法
 * 	2、创建对象实例，调用对象 start() 方法来启动线程
 */
public class CreateTest1 {
	public static void main(String[] args) {
		Rabbit rab = new Rabbit();
		Tortoise tor = new Tortoise();
		rab.start();
		tor.start();
		for(int i=0;i<1000;i++){
			System.out.println("main==>"+i);
		}
	}
}

class Rabbit extends Thread{
	@Override
	public void run(){
		for(int i=0;i<50;i++){
			System.out.println("兔子跑了 "+i+" 步");
		}
	}
}
class Tortoise extends Thread{
	@Override
	public void run(){
		for(int i=0;i<50;i++){
			System.out.println("乌龟跑了 "+i+" 步");
		}
	}
}