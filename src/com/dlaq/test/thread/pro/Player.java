package com.dlaq.test.thread.pro;

/**
 * 生产者，生产资源
 */
public class Player implements Runnable{
	private Movie m;
	public Player(Movie m){
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
			if(0==i%2){
				m.play("左青龙");
			}else{
				m.play("右白虎");
			}
		}
	}
}
