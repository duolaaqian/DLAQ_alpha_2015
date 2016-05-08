package com.dlaq.test.thread.status;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 线程等待
 * 	Thread.sleep(1000);
 * 该线程进入等待状态，不释放锁，小心死锁
 */
public class SleepDemo01 {
	public static void main(String[] args) throws InterruptedException {
		Date d1 = new Date();
		System.out.println(new SimpleDateFormat("mm:ss SS").format(d1));
		Thread.sleep(1000);
		d1 = new Date();
		System.out.println(new SimpleDateFormat("mm:ss SS").format(d1));
	}
}
