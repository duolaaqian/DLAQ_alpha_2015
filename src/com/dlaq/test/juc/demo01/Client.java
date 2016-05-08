package com.dlaq.test.juc.demo01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Client {
	public static void main(String[] args) {
		System.out.println("--start--");
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new Processor("线程一"));
		exec.execute(new Processor("线程二"));
		exec.execute(new Processor("线程三"));
		exec.shutdown();
		try {
			exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.out.println("执行并发错误！");
			e.printStackTrace();
		}
		System.out.println("--end--");
	}
}
