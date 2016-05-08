package com.dlaq.test.thread.pro.em;

/**
 * 消费者(玩家放技能)
 */
public class Player implements Runnable{
	private EM em;
	public Player(EM em){
		this.em = em;
	}
	@Override
	public void run() {
		for(int i=0;i<20;i++){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			em.skillRelease();
		}
	}
}
