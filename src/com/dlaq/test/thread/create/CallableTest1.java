package com.dlaq.test.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * juc中的Callable接口
 * 使主线程可以获得其他线程运行后的返回值
 * 
 * 	1、创建线程池
 * 	2、通过线程池启动线程，并记录返回结果
 * 	3、停止服务
 */
public class CallableTest1 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//创建线程池
		ExecutorService ser = Executors.newFixedThreadPool(2);
		Race rabbit = new Race("小兔子",500);
		Race tortoise = new Race("老不死",1000);
		
		//启动线程、获取值
		Future<Integer> result1 =ser.submit(rabbit);
		Future<Integer> result2 =ser.submit(tortoise);
		
		Thread.sleep(3000);
		rabbit.setFlag(false);
		tortoise.setFlag(false);
		
		int num1 = result1.get();
		int num2 = result2.get();
		ser.shutdownNow();
		System.out.println("兔子跑了 --> "+num1+"步");
		System.out.println("乌龟跑了 --> "+num2+"步");
	}
}

class Race implements Callable<Integer>{
	private String name;	//名字
	private long time;		//延时时间
	private boolean flag=true;
	private int step;		//所走步数
	
	public Race(String name,long time) {
		super();
		this.name = name;
		this.time =time;
	}
	
	@Override
	public Integer call() throws Exception {
		while(flag){
			Thread.sleep(time); //延时
			step++;
		}
		return step;
	}
	
	public String getName() {
		return name;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}