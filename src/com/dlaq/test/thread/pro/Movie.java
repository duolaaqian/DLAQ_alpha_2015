package com.dlaq.test.thread.pro;

/**
 * 同一场景，同一资源
 * 生产者消费者  信号灯法
 * 	wait() --> 等待、释放锁、等待其他线程唤醒
 * 	sleep(millis) --> 等待、不释放锁、等待 millis 后自动向下执行
 *  notify() --> 唤醒
 */
public class Movie {
	private String pic;
	
	/**
	 * 信号灯
	 * flag=true  --> 生产者生产，消费者等待，生产完成后通知消费
	 * flag=false --> 消费者消费，生产者等待，消费完成后通知等待
	 */
	private boolean flag = true;
	
	public synchronized void play(String pic){
		if(!flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.pic = pic;
		System.out.println("[生产]"+pic);
		this.flag = false;
		this.notify();
	}
	
	public synchronized void watch(){
		if(flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("[消费]"+pic);
		this.pic = "没了";
		
		this.flag=true;
		this.notify();
	}

}
