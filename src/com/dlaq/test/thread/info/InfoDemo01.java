package com.dlaq.test.thread.info;

/**
 * 线程中的属性
 * 	Thread.currentThread() --> 当前线程
 * 	setName()/getName() --> 设置/获取 线程名称(默认为'Thread-'+线程个数)
 * 	isAlive() --> 判断线程状态是否处于活动状态
 * 
 * 	setPriority() --> 设置线程优先级
 * 		该优先级只是概率，并不是绝对优先级
 * 		MAX_PRIORITY  10
 * 		NORM_PRIORITY 5 (默认)
 * 		MIN_PRIORITY  1
 */
public class InfoDemo01 {
	public static void main(String[] args) throws InterruptedException {
		InfoThread it = new InfoThread();
		Thread t = new Thread(it,"挨踢");
		t.setPriority(Thread.MAX_PRIORITY);
		
		t.start();
		System.out.println("启动后的状态:"+t.isAlive());
		Thread.sleep(1000);
		it.stop();
		Thread.sleep(1000);
		System.out.println("停止后的状态:"+t.isAlive());
	}

}

class InfoThread implements Runnable{
	private boolean flag = true;
	private int num =0;
	@Override
	public void run() {
		while(flag){
			System.out.println(Thread.currentThread().getName()+" --> "+num++);
		}
	}
	public void stop(){
		this.flag = false;
	}
}