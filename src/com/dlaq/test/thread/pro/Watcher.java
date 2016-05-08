package com.dlaq.test.thread.pro;

/**
 * 消费者、消耗资源
 */
public class Watcher implements Runnable{

	private Movie m;
	public Watcher(Movie m){
		super();
		this.m = m;
	}
	
	@Override
	public void run() {
		for(int i=0;i<20;i++){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			m.watch();
		}
	}

}
